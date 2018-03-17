package com.oc.service.impl;

import com.oc.dao.BaseDao;
import com.oc.entity.BaseEntity;
import com.oc.service.BaseService;

import java.io.Serializable;
import java.util.List;

public class BaseServiceImpl<T extends BaseEntity, ID extends Serializable> implements BaseService<T, ID> {

    private BaseDao<T, ID> baseDao;

    protected void setBaseDao(BaseDao<T, ID> baseDao) {
        this.baseDao = baseDao;
    }

    @Override
    public T find(ID id) {
        return baseDao.find(id);
    }

    @Override
    public List<T> findAll() {
        return baseDao.findAll();
    }

    @Override
    public void save(T entity) {
        baseDao.persist(entity);
    }

    @Override
    public void update(T entity) {
        baseDao.merge(entity);
    }

    @Override
    public void delete(ID id) {
        baseDao.remove(id);
    }

    @Override
    public void delete(T entity) {
        baseDao.remove(entity);
    }

    @Override
    public void delete(ID[] ids) {
        baseDao.remove(ids);
    }

    @Override
    public void flush() {
        baseDao.flush();
    }

}
