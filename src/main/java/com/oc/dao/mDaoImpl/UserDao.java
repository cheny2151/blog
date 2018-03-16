package com.oc.dao.mDaoImpl;

import com.oc.entity.Blogger;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
    Blogger getUser(String account);
}
