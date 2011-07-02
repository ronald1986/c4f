package com.biztrace.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.biztrace.dao.model.Criteria;
import com.biztrace.dao.model.Order;
import com.biztrace.dao.model.Records;
import com.biztrace.exception.BusinessException;
import com.biztrace.model.GenericEntity;
import com.biztrace.model.GenericEntityImpl;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;

public class GenericMongoDAO implements GenericDAO {
    private Logger logger = LoggerFactory.getLogger(GenericMongoDAO.class);
    private String dbName;
    private Mongo mongo;

    private static class CriterionBuilder {
        static DBObject buildCriterions(final Criteria... criterias) {
            BasicDBObject criteria = new BasicDBObject();
            if (criterias != null) {
                for (Criteria c : criterias) {
                    if (c.getOperator().equals(Criteria.OPERATOR.EQ)) {
                        criteria.put(c.getPropertyName(), c.getPropertyValue());
                    }
                }
            }
            System.out.println("criteria : " + criteria);
            return criteria;
        }
    }
    
    private static class OrderBuilder {
        static DBObject buildOrders(final Order... orders) {
            DBObject order = new BasicDBObject();
            if (orders != null) {
                for (Order o : orders) {
                    if (o.getOrder().equals(Order.ORDER.ASC)) {
                        order.put(o.getPropertyName(), 1);
                    } else {
                        order.put(o.getPropertyName(), -1);
                    }
                }
            }
            System.out.println("order: " + order);
            return order;
        }
    }

    public GenericEntity getUniqueEntity(final String entityName, boolean reportError, final Criteria... criterias) {
        return (GenericEntity) getUnique(entityName, reportError, criterias);
    }

    public GenericEntity getUniqueEntity(final String entityName, final Criteria... criterias) {
        return (GenericEntity) getUnique(entityName, false, criterias);
    }
    
    public Object getUnique(final String entityName, final boolean reportError,
            final Criteria... criterias) {
        DBObject criteria = CriterionBuilder.buildCriterions(criterias);
        DB db = mongo.getDB(dbName);
        DBCollection dbColl = db.getCollection(entityName);
        DBCursor cursor = dbColl.find(criteria);
        GenericEntityImpl entity = null;
        while(cursor.hasNext()) {
            entity = new GenericEntityImpl();
            entity.putAll(cursor.next());
        }
        boolean isEmpty = (cursor.count() == 0);
        if (reportError && cursor.count() > 1) {
            throw new BusinessException(1000002L);
        }
        return isEmpty ? null : entity;
    }

    public Records list(final String entityName) {
        return list(entityName, null, null, 1, 20);
    }

    public Records list(final String entityName, final Criteria[] criterias,
            final Order[] orders) {
        return list(entityName, criterias, orders, 1, 20);
    }

    public Records list(final String entityName, final Criteria[] criterias,
            final Order[] orders, final Integer pageNumber, final Integer recordPerPage) {
        DB db = mongo.getDB(dbName);
        DBCollection dbColl = db.getCollection(entityName);
        DBCursor cursor = dbColl.find(CriterionBuilder.buildCriterions(criterias))
            .sort(OrderBuilder.buildOrders(orders));
        Integer totalRecords = cursor.count();
        Integer totalPages = null;

        Records records = new Records();
        if (pageNumber != null) {
            totalPages = new Double(
                Math.ceil((double) totalRecords / (double) recordPerPage)).intValue();
            cursor = cursor.skip((pageNumber - 1) * recordPerPage).limit(recordPerPage);
        }

        List<GenericEntity> documents = new ArrayList<GenericEntity>();
        while(cursor.hasNext()) {
            GenericEntityImpl entity = new GenericEntityImpl();
            entity.putAll(cursor.next());
            documents.add(entity);
        }
        records.setRecords(documents);
        records.setTotalPages(totalPages);
        records.setCurrentPage(pageNumber);
        records.setTotalRecords(totalRecords);
        return records;
    }
    
    public void save(final String entityName, final GenericEntity entity) {
        mongo.getDB(dbName);
        DB db = mongo.getDB(dbName);
        DBCollection dbColl = db.getCollection(entityName);
        Integer version = entity.getVersion();
        if (version == null) {
            version = new Integer(0);
            entity.setVersion(version);
            dbColl.save(entity);
        } else {
            entity.setVersion(version + 1);

            GenericEntity query = new GenericEntityImpl();
            query.setId(entity.getId());
            query.setVersion(entity.getVersion());
            dbColl.update(query, entity, false, false);
        }

        // Getting result
        DBObject result = db.command("getlasterror");
        System.out.println(result);
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public void setMongo(Mongo mongo) {
        this.mongo = mongo;
    }
}
