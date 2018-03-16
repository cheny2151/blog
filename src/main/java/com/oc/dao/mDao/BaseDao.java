package com.oc.dao.mDao;

import java.io.Serializable;

public interface BaseDao<T, ID extends Serializable> {

    /**
     * 根据id查找对象
     */
    T find(Long id);

    /**
     * 保存
     */
    void insert(T object);

    /**
     * 更新
     */
    void update(T object);

    /**
     * 删除
     */
    void delete(Long id);

    /**
     * 分页
     */

    /**
     * 模糊匹配
     */
}
