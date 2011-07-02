package com.biztrace.web.security.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.biztrace.model.PersistentUpdatableObject;

@Entity
@Table(name="organization")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Organization extends PersistentUpdatableObject {
    private String code;
    private String name;
    private String postalCode;
    private String postalAddress1;
    private String postalAddress2;
    private String postalAddress3;

    @Column(name="code")
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    
    @Column(name="name")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    @Column(name="postal_code")
    public String getPostalCode() {
        return postalCode;
    }
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    
    @Column(name="postal_address1")
    public String getPostalAddress1() {
        return postalAddress1;
    }
    public void setPostalAddress1(String postalAddress1) {
        this.postalAddress1 = postalAddress1;
    }
    
    @Column(name="postal_address2")
    public String getPostalAddress2() {
        return postalAddress2;
    }
    public void setPostalAddress2(String postalAddress2) {
        this.postalAddress2 = postalAddress2;
    }
    
    @Column(name="postal_address3")
    public String getPostalAddress3() {
        return postalAddress3;
    }
    public void setPostalAddress3(String postalAddress3) {
        this.postalAddress3 = postalAddress3;
    }
    
}
