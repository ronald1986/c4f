package com.biztrace.web.security.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.biztrace.model.PersistentUpdatableObject;

@Entity
@Table(name="usr")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class User extends PersistentUpdatableObject {
	private String orgCode;
	private String loginId;
	private String name;
	private String email;
	private String password;
	private Organization org;

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

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE},
	    fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "org_id")
	public Organization getOrg() {
        return org;
    }
    public void setOrg(final Organization org) {
        this.org = org;
    }

	@Column(name="name")
    public String getName() {
        return name;
    }
    public void setName(final String name) {
        this.name = name;
    }

    @Column(name="email")
    public String getEmail() {
        return email;
    }
    public void setEmail(final String email) {
        this.email = email;
    }
    @Transient
    public String getSystemName() {
	    return orgCode + "|" + loginId;
	}

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
