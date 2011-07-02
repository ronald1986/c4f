package com.biztrace.model;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.bson.BSONObject;

import com.biztrace.util.GUIDGenerator;
import com.mongodb.DBObject;

public class GenericEntityImpl extends HashMap<String, Object> implements GenericEntity {
    private String entityName;

    public GenericEntityImpl() {
        super();
        this.setId(GUIDGenerator.getInstance().getGUID());
    }

    public String getId() {
        return (String) this.get("_id");
    }

    public void setId(String id) {
        this.put("_id", id);
    }

    public Integer getVersion() {
        return (Integer) this.get("version");
    }

    public void setVersion(Integer version) {
        this.put("version", version);
    }

    public String getEntityName() {
        return entityName;
    }
    public void setEntityName(String entityName) {
        this.entityName = entityName;
        this.put("entityName", entityName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !(o instanceof GenericEntityImpl)) {
            return false;
        }

        GenericEntityImpl other = (GenericEntityImpl) o;
        if (this.getId() == null) {
            return false;
        }

        return StringUtils.equals(this.getId(), other.getId());
    }

    @Override
    public int hashCode() {
        if (this.getId() != null) {
            return this.getId().hashCode();
        } else {
            return super.hashCode();
        }
    }

    public boolean containsField(String key) {
        return this.containsKey(key);
    }

    public boolean containsKey(String key) {
        return super.containsKey(key);
    }

    public Object get(String key) {
        return super.get(key);
    }

    public boolean isPartialObject() {
        return false;
    }

    public void markAsPartialObject() {
    }

    @SuppressWarnings("unchecked")
    public void putAll(DBObject dbObj) {
        this.putAll(dbObj.toMap());
    }
    
    public void putAll(BSONObject bsonObj) {
        this.putAll(bsonObj.toMap());
    }

    public void putAll(Map map) {
        super.putAll(map);
    }

    public Object removeField(String key) {
        Object obj = this.get(key);
        this.remove(key);
        return obj;
    }

    public Map toMap() {
        return this;
    }
}
