package com.oc.service;

import com.oc.entity.User;
import com.oc.utils.jwt.JwtPrincipal;

public interface UserService extends BaseService<User, Long> {

    /**
     * 通过username查找user
     */
    User findByUsername(String username);

    /**
     * 登陆认证
     */
    JwtPrincipal authenticated(String username, String password);

}
