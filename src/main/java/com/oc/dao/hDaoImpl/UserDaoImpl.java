package com.oc.dao.hDaoImpl;

import com.oc.dao.UserDao;
import com.oc.entity.User;
import org.springframework.stereotype.Repository;

/**
 * 用户认证安全类 - dao
 */
@Repository("userDaoImpl")
public class UserDaoImpl extends BaseDaoImpl<User, Long> implements UserDao {

}
