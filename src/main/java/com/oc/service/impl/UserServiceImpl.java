package com.oc.service.impl;

import com.oc.dao.UserDao;
import com.oc.entity.User;
import com.oc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * User - serviceImpl
 */
@Service
@Transactional
public class UserServiceImpl extends BaseServiceImpl<User, Long> implements UserService {

    @Resource(name = "userDaoImpl")
    private UserDao userDao;

    @Autowired
    protected void setBaseDao(UserDao userDao) {
        super.setBaseDao(userDao);
    }

}
