package com.oc.dao.impl;

import com.oc.dao.AdminDao;
import com.oc.entity.Admin;
import org.springframework.stereotype.Repository;

/**
 * admin - dao
 */
@Repository("adminDaoImpl")
public class AdminDaoImpl extends BaseDaoImpl<Admin, Long> implements AdminDao {

}
