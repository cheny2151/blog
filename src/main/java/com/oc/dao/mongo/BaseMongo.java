package com.oc.dao.mongo;

import com.oc.entity.mongo.MongoBaseEntity;
import com.oc.system.page.Page;
import com.oc.system.page.PageInfo;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface BaseMongo<T extends MongoBaseEntity> {

    /**
     * 保存
     */
    String save(T entity);

    /**
     * 更新
     */
    void update(T entity);

    /**
     * 更新
     *
     * @param properties 更新的字段名
     */
    void update(T entity, String[] properties);

    /**
     * 删除
     */
    void remove(String id);

    /**
     * count
     */
    long count(Criteria criteria);

    /**
     * 通过id查找
     */
    T findById(String id);

    /**
     * 查找所有实体
     */
    List<T> findAll();

    /**
     * 根据条件查找
     *
     * @param criteria 查询条件
     */
    List<T> find(Criteria criteria);

    /**
     * 分页
     */
    Page<T> findPage(Criteria criteria, PageInfo pageInfo,Sort.Order... orders);

    Page<T> findPage(Query query, PageInfo pageInfo, Sort.Order... orders);

}
