package com.oc.dao.jpa.impl;

import com.oc.dao.jpa.AdminDao;
import com.oc.entity.jpa.Admin;
import org.springframework.stereotype.Repository;

/**
 * admin - dao
 */
@Repository("adminDaoImpl")
public class AdminDaoImpl extends BaseDaoImpl<Admin, Long> implements AdminDao {

}
