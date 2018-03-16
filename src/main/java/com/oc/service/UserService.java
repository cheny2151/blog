package com.oc.service;

import com.oc.entity.Blogger;
import com.oc.dao.mDaoImpl.UserDao;
import org.springframework.stereotype.Service;

@Service
public class UserService {
//    @Autowired
    private UserDao userDao;

    public Blogger getUser(String account) {
        return userDao.getUser(account);
    }
}
