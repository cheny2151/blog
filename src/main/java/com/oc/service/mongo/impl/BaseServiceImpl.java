package com.oc.service.mongo.impl;

import com.oc.dao.mongo.BaseMongo;
import com.oc.entity.mongo.MongoBaseEntity;
import com.oc.service.mongo.BaseService;
import com.oc.system.page.Page;
import com.oc.system.page.PageInfo;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.List;

/**
 * mongo service基类
 *
 * @param <T>
 */
public class BaseServiceImpl<T extends MongoBaseEntity> implements BaseService<T> {

    private BaseMongo<T> baseMongo;

    protected void setBaseMongo(BaseMongo<T> baseMongo) {
        this.baseMongo = baseMongo;
    }

    @Override
    public String save(T entity) {
        return baseMongo.save(entity);
    }

    @Override
    public void update(T entity) {
        baseMongo.update(entity);
    }

    @Override
    public void update(T entity, String[] properties) {
        baseMongo.update(entity, properties);
    }

    @Override
    public void remove(String id) {
        baseMongo.remove(id);
    }

    @Override
    public long count(Criteria criteria) {
        return baseMongo.count(criteria);
    }

    @Override
    public T findById(String id) {
        return baseMongo.findById(id);
    }

    @Override
    public List<T> findAll() {
        return baseMongo.findAll();
    }

    @Override
    public List<T> find(Criteria criteria) {
        return baseMongo.find(criteria);
    }

    @Override
    public Page<T> findPage(Criteria criteria, PageInfo pageInfo, Sort.Order... orders) {
        return baseMongo.findPage(criteria, pageInfo, orders);
    }

}
