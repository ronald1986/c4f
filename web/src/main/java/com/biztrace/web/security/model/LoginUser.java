package com.biztrace.web.security.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.biztrace.model.PersistentUpdatableObject;

@Entity
@Table(name="usr")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class LoginUser extends PersistentUpdatableObject {
    @NotNull
    private String orgCode;
    @NotNull
    private String loginId;
    @NotNull
    private String password;

    @Column(name="org_code")
    public String getOrgCode() {
        return orgCode;
    }
    public void setOrgCode(final String orgCode) {
        this.orgCode = orgCode;
    }

    @Column(name="loginid")
    public String getLoginId() {
        return loginId;
    }
    public void setLoginId(final String loginId) {
        this.loginId = loginId;
    }

    @Column(name="password")
    public String getPassword() {
        return password;
    }
    public void setPassword(final String password) {
        this.password = password;
    }
    @Transient
    public String getSystemName() {
        return orgCode + "|" + loginId;
    }
}
