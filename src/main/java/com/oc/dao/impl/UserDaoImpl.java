package com.oc.dao.impl;

import com.oc.dao.UserDao;
import com.oc.entity.User;
import com.oc.system.page.Page;
import com.oc.system.page.Pageable;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;

/**
 * 用户认证安全类 - dao
 */
@Repository("userDaoImpl")
public class UserDaoImpl extends BaseDaoImpl<User, Long> implements UserDao {

    @Override
    public User findByUsername(String username) {
        if (StringUtils.isEmpty(username)) {
            return null;
        }
        String jpql = "select user from User user where user.username = :username";
        try {
            return entityManager.createQuery(jpql, User.class).setParameter("username", username).setFlushMode(FlushModeType.COMMIT).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}
