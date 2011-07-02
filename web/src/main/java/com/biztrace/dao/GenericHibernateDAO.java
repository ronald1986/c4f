package com.biztrace.dao;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.biztrace.dao.model.Criteria;
import com.biztrace.dao.model.Order;
import com.biztrace.dao.model.Records;
import com.biztrace.exception.BusinessException;
import com.biztrace.model.GenericEntity;

public class GenericHibernateDAO extends HibernateDaoSupport {
	private Logger logger = LoggerFactory.getLogger(GenericHibernateDAO.class);
	private SessionFactory sessionFactory;

//	@Autowired
//	private HibernateTemplate hibernateTemplate;

	private static class CriterionBuilder {
		static void addCriterions(DetachedCriteria criteria, final Criteria... criterias) {
			for (Criteria c : criterias) {
				if (c.getOperator().equals(Criteria.OPERATOR.EQ)) {
					criteria.add(Restrictions.eq(c.getPropertyName(), c.getPropertyValue()));
				}
			}
		}
		
		static void addOrders(DetachedCriteria criteria, final Order... orders) {
            for (Order o : orders) {
                if (o.getOrder().equals(Order.ORDER.ASC)) {
                    criteria.addOrder(org.hibernate.criterion.Order.asc(o.getPropertyName()));
                } else {
                    criteria.addOrder(org.hibernate.criterion.Order.desc(o.getPropertyName()));
                }
            }
        }
	}

    public Object getUniquePojo(final Class clazz, final Criteria... criterias) {
        return getUnique(clazz.getName(), false, criterias);
    }

    public Object getUniquePojo(final Class clazz, final boolean reportError,
            final Criteria... criterias) {
        return getUnique(clazz.getName(), reportError, criterias);
    }

    public GenericEntity getUniqueEntity(final String entityName, boolean reportError, final Criteria... criterias) {
        return (GenericEntity) getUnique(entityName, reportError, criterias);
    }

    public GenericEntity getUniqueEntity(final String entityName, final Criteria... criterias) {
        return (GenericEntity) getUnique(entityName, false, criterias);
    }
    
	public Object getUnique(final String entityName, final boolean reportError,
			final Criteria... criterias) {
		DetachedCriteria criteria = DetachedCriteria.forEntityName(entityName);
		CriterionBuilder.addCriterions(criteria, criterias);
		List result = getHibernateTemplate().findByCriteria(criteria);
		boolean isEmpty = CollectionUtils.isEmpty(result);
		if (reportError && (isEmpty || result.size() > 1)) {
			throw new BusinessException(1000002L);
		}
		return isEmpty ? null : result.get(0);
	}

	public Records list(final String entityName) {
        return list(entityName, (Criteria[]) null, null, null, null);
    }

    public Records list(final String entityName, final Criteria[] criterias,
            final Order[] orders) {
        return list(entityName, criterias, orders, null, null);
    }

	public Records list(final String entityName, final Criteria[] criterias,
            final Order[] orders, final Integer pageNumber, final Integer recordPerPage) {
	    DetachedCriteria criteria = DetachedCriteria.forEntityName(entityName);
	    CriterionBuilder.addCriterions(criteria, criterias);
	    CriterionBuilder.addOrders(criteria, orders);

	    Records records = new Records();
	    List<GenericEntity> entities = null;
	    if (pageNumber == null) {
	        entities = getHibernateTemplate().findByCriteria(criteria);
	    } else {
	        entities = getHibernateTemplate().findByCriteria(
	            criteria, (pageNumber - 1) * recordPerPage, recordPerPage);
	    }
        records.setRecords(entities);
        records.setCurrentPage(pageNumber);
        //records.setTotalPages(entities.size());
        //records.setTotalPages(totalPages);
        //records.setTotalRecords(totalRecords);
        return records;
	}
}
