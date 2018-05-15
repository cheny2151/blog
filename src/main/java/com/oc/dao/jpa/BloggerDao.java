package com.oc.dao.jpa;

import com.oc.entity.jpa.Blogger;

public interface BloggerDao extends BaseDao<Blogger, Long> {
    /**
     * 通过username查找用户
     */
    Blogger findByUsername(String username);
}
