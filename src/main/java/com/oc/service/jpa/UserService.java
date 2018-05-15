package com.oc.service.jpa;

import com.oc.entity.jpa.AuthUser;
import com.oc.utils.jwt.JwtPrincipal;

public interface UserService extends BaseService<AuthUser, Long> {

    /**
     * 通过username查找user
     */
    AuthUser findByUsername(String username);

    /**
     * 登陆认证
     */
    JwtPrincipal authenticated(String username, String password);

}
