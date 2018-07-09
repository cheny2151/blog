package com.oc.dao.mybatis;

import com.oc.entity.pojo.Blogger;
import org.springframework.stereotype.Repository;

@Repository
public interface BloggerDao {

    Blogger getUserByUsername(String username);

}
