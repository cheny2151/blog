package com.oc.service;

import com.oc.entity.BaseEntity;

import java.io.Serializable;
import java.util.List;

/**
 * service基类
 *
 * @param <T>  entity
 * @param <ID> id
 */
public interface BaseService<T extends BaseEntity, ID extends Serializable> {

    /**
     * 根据id查找对象
     */
    T find(ID id);

    /**
     * 查找所有实体
     */
    List<T> findAll();

    /**
     * 保存
     */
    void save(T entity);

    /**
     * 更新
     */
    void update(T entity);

    /**
     * 删除
     */
    void delete(ID id);

    /**
     * 删除
     */
    void delete(T entity);

    /**
     * 批量删除
     */
    void delete(ID[] ids);

    /**
     * flush
     */
    void flush();

}
