package com.oc.dao;

import com.oc.entity.jpa.AuthUser;

public interface UserDao extends BaseDao<AuthUser, Long> {

    AuthUser findByUsername(String username);

}
