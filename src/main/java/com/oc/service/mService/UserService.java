package com.oc.service.mService;

import com.oc.entity.User;
import com.oc.dao.mDao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public User getUser(String account) {
        return userDao.getUser(account);
    }
}
