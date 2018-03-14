package com.oc.service.mService;

import com.oc.entity.User;
import com.oc.dao.mDao.UserDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

//@Service
public class UserService {
    @Resource
    private UserDao userDao;

    public User getUserDao(String account) {
        return userDao.getUser(account);
    }
}
