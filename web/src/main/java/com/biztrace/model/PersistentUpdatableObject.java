package com.biztrace.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class PersistentUpdatableObject extends PersistentCreatableObject {
    private Date updateDate;
    private String updateUserId;
    private String updateUser;

    /**
     * @return the updateDate
     */
    @Column(name = "update_date")
    public Date getUpdateDate() {
        return this.updateDate;
    }
    /**
     * @param updateDate the updateDate to set
     */
    public void setUpdateDate(final Date updateDate) {
        this.updateDate = updateDate;
    }
    /**
     * @return the updateUserId
     */
    @Column(name = "update_user_id")
    public String getUpdateUserId() {
        return this.updateUserId;
    }
    /**
     * @param updateUserId the updateUserId to set
     */
    public void setUpdateUserId(final String updateUserId) {
        this.updateUserId = updateUserId;
    }
    @Column(name = "update_user")
    public String getUpdateUser() {
        return updateUser;
    }
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }
    
}