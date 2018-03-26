package com.oc.dao;

import com.oc.entity.BaseEntity;
import com.oc.system.filter.Filter;
import com.oc.system.page.Page;
import com.oc.system.page.Pageable;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public interface BaseDao<T extends BaseEntity, ID extends Serializable> {

    /**
     * 根据id查找对象
     */
    T findList(ID id);

    /**
     * 查找所有实体
     */
    List<T> findAll();

    /**
     * 保存
     */
    void persist(T entity);

    /**
     * 更新
     */
    void merge(T entity);

    /**
     * 删除
     */
    void remove(ID id);

    /**
     * 删除
     */
    void remove(T entity);

    /**
     * 批量删除
     */
    void remove(ID[] ids);

    /**
     * flush
     */
    void flush();

    /**
     * 根据实体获取id
     */
    ID getIdentifier(T entity);

    /**
     * 过滤查找
     */
    List<T> findList(Filter filter);

    /**
     * 过滤查找
     */
    List<T> findList(Collection<Filter> filters);

    /**
     * 过滤count
     */
    long count(Filter filter);

    /**
     * 分页
     */
    Page<T> findPage(Pageable<T> pageable);

    /**
     * 分页原生sql
     *
     * @param selection   查找的内容
     * @param tableName   查找的表命
     * @param restriction 限定条件
     */
    Page<T> findPageNative(String selection, String[] tableName, String restriction,Pageable<T> pageable);

}
