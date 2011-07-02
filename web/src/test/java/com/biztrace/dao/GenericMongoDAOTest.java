package com.biztrace.dao;

import java.util.List;

import junit.framework.TestCase;

import com.biztrace.dao.model.Criteria;
import com.biztrace.dao.model.Order;
import com.biztrace.dao.model.Records;
import com.biztrace.model.GenericEntity;
import com.biztrace.model.GenericEntityImpl;
import com.mongodb.Mongo;

public class GenericMongoDAOTest extends TestCase {
    Mongo mongo = null;
    GenericMongoDAO dao = new GenericMongoDAO();
    
    @Override
    protected void setUp() throws Exception {
        mongo = new Mongo();
        dao.setMongo(mongo);
        dao.setDbName("local");
    }
    
    public void testList() {

        Order[] order = new Order[1];
        order[0] = new Order("code");

        Records record = dao.list("product", null, order, null, null);
        assertEquals(7, record.getTotalRecords().intValue());
        assertEquals(null, record.getTotalPages());
        
        record = dao.list("product", null, order, 2, 3);
        assertEquals(2, record.getCurrentPage().intValue());
        assertEquals(3, record.getTotalPages().intValue());
        List<GenericEntity> data = record.getRecords();
        assertEquals(3, data.size());
        assertEquals("d", data.get(0).get("code"));
        assertEquals("e", data.get(1).get("code"));
        assertEquals("f", data.get(2).get("code"));
        
        record = dao.list("product", null, order, 3, 3);
        assertEquals(3, record.getCurrentPage().intValue());
        assertEquals(3, record.getTotalPages().intValue());
        data = record.getRecords();
        assertEquals(1, data.size());
        assertEquals("g", data.get(0).get("code"));
        
        record = dao.list("product", null, order, 1, 10);
        assertEquals(1, record.getCurrentPage().intValue());
        assertEquals(1, record.getTotalPages().intValue());
    }
    
    public void testGet() {
        Criteria criteria = new Criteria("_id", "2");
        GenericEntity entity = dao.getUniqueEntity("product", true, criteria);
        assertNotNull(entity);
        assertEquals("2", entity.getId());
    }
    
    public void testNew() {
        GenericEntity entity = new GenericEntityImpl();
        entity.put("code", "T-T43");
        entity.put("name", "Thinkpad T43");
        dao.save("product", entity);
        
        Criteria criteria = new Criteria("_id", entity.getId());
        entity = dao.getUniqueEntity("product", criteria);
        assertNotNull(entity);
        assertEquals(entity.get("code"), "T-T43");
    }
    
    public void testUpdate() {
        Criteria criteria = new Criteria("_id", "3");
        GenericEntity entity = dao.getUniqueEntity("product", true, criteria);
        entity.put("code", "3");
        dao.save("product", entity);
        
        entity = dao.getUniqueEntity("product", criteria);
        assertNotNull(entity);
        assertEquals(entity.get("code"), "3");
    }
}
