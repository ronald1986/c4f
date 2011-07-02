package com.biztrace.model;

import java.util.Map;

import com.mongodb.DBObject;

public interface GenericEntity extends Map<String, Object>, DBObject {
    String getId();
    void setId(String id);
    Integer getVersion();
    void setVersion(Integer version);
    String getEntityName();
    void setEntityName(String entityName);
    boolean equals(Object obj);
}
