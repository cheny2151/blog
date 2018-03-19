package com.oc.service.security;

import com.oc.dao.UserDao;
import com.oc.entity.User;
import com.oc.utils.security.UserDetailsFactory;
import org.junit.Test;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 *
 */
@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource(name = "userDaoImpl")
    private UserDao userDao;

    /**
     * 获取认证信息
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);
//        if (user == null) {
        throw new UsernameNotFoundException("unknown username");
//        }
//        return UserDetailsFactory.create(user);
    }

}
