package com.oc.dao;

import com.oc.entity.Blogger;

public interface BloggerDao extends BaseDao<Blogger, Long> {
    /**
     * 通过username查找用户
     */
    Blogger findByUsername(String username);
}
