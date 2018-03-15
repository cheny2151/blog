package com.oc.dao.mDao;

import com.oc.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
    User getUser(String account);
}
