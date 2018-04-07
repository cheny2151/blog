package com.oc.service;

import com.oc.entity.jpa.Blogger;

public interface BloggerService extends BaseService<Blogger, Long> {

    /**
     * 获取当前登陆的用户
     */
    Blogger getCurrent();

}
