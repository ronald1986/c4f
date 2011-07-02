package com.biztrace.model;

/**
 * Common persistent object interface for system with the use of Hibernate
 */
public interface PersistentObject {
    /**
     * Gets Id
     * @return id
     */
    public String getId();
    /**
     * Gets version
     * @return version record in hibernate
     */
    public int getVersion();
}
