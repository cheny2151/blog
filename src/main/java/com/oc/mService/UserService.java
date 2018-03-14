package com.oc.mService;

import com.oc.entity.User;
import com.oc.mDao.UserDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {
    @Resource
    private UserDao userDao;

    public User getUserDao(String account) {
        return userDao.getUser(account);
    }
}
