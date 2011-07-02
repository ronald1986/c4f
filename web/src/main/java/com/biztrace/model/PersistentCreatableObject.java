package com.biztrace.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class PersistentCreatableObject extends BasicPersistentObject {
    private Date createDate;
    private String createUserId;
    private String createUser;

    /**
     * @return the createDate
     */
    @Column(name = "create_date")
    public Date getCreateDate() {
        return this.createDate;
    }
    /**
     * @param createDate the createDate to set
     */
    public void setCreateDate(final Date createDate) {
        this.createDate = createDate;
    }
    /**
     * @return the createUserId
     */
    @Column(name = "create_user_id")
    public String getCreateUserId() {
        return this.createUserId;
    }
    /**
     * @param createUserId the createUserId to set
     */
    public void setCreateUserId(final String createUserId) {
        this.createUserId = createUserId;
    }
    @Column(name = "create_user")
    public String getCreateUser() {
        return createUser;
    }
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }
}