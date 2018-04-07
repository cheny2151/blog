package com.oc.dao.impl;

import com.oc.dao.UserDao;
import com.oc.entity.jpa.AuthUser;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;

/**
 * 用户认证安全类 - dao
 */
@Repository("userDaoImpl")
public class UserDaoImpl extends BaseDaoImpl<AuthUser, Long> implements UserDao {

    @Override
    public AuthUser findByUsername(String username) {
        if (StringUtils.isEmpty(username)) {
            return null;
        }
        String jpql = "select user from AuthUser user where user.username = :username";
        try {
            return entityManager.createQuery(jpql, AuthUser.class).setParameter("username", username).setFlushMode(FlushModeType.COMMIT).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}
