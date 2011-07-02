package com.biztrace.model;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import com.biztrace.util.GUIDGenerator;

/**
 * Basic implementation of PersistentObject
 */
@MappedSuperclass
public class BasicPersistentObject implements PersistentObject {
    private String id = GUIDGenerator.getInstance().getGUID();
    private int version;

    /**
     * Returns a generated GUID
     */
    @Id
    public String getId() {
        return id;
    }
    /**
     * @see com.biztrace.model.PersistentObject#getVersion()
     */
    @Version
    public int getVersion() {
        return version;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    public void setVersion(int version) {
        this.version = version;
    }
}
