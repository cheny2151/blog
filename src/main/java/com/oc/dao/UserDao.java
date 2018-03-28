package com.oc.dao;

import com.oc.entity.User;

public interface UserDao extends BaseDao<User, Long> {

    User findByUsername(String username);

}
