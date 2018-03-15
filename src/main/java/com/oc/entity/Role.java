package com.oc.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "m_role")
public class Role extends BaseEntity {

    private static final long serialVersionUID = -7329592756490405641L;

    private String name;

    private String auth;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

}
