package com.oc.dao.impl;

import com.oc.dao.BaseDao;
import com.oc.entity.BaseEntity;
import com.oc.system.filter.Filter;
import com.oc.system.filter.FilterHandler;
import com.oc.system.page.Page;
import com.oc.system.page.Pageable;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


/**
 * dao基类
 *
 * @param <T>  entity
 * @param <ID> id
 */
public class BaseDaoImpl<T extends BaseEntity, ID extends Serializable> implements BaseDao<T, ID> {

    @PersistenceContext
    protected EntityManager entityManager;

    private Class<T> entityType;

    @SuppressWarnings("unchecked")
    public BaseDaoImpl() {
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        this.entityType = (Class<T>) pt.getActualTypeArguments()[0];
    }

    @Override
    public T findList(ID id) {
        return id == null ? null : entityManager.find(entityType, id);
    }

    @Override
    public List<T> findAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = cb.createQuery(entityType);
        Root<T> root = query.from(entityType);
        query.select(root);
        return entityManager.createQuery(query).setFlushMode(FlushModeType.COMMIT).getResultList();
    }

    @Override
    public void persist(T entity) {
        Assert.notNull(entity, "not null entity");
        entityManager.persist(entity);
    }

    @Override
    public void merge(T entity) {
        Assert.notNull(entity, "not null entity");
        entityManager.merge(entity);
    }

    @Override
    public void remove(ID ID) {
        T entity;
        if ((entity = findList(ID)) != null) {
            entityManager.remove(entity);
        }
    }

    @Override
    public void remove(T entity) {
        Assert.notNull(entity, "not null entity");
        entityManager.remove(entity);
    }

    @Override
    public void remove(ID[] ids) {
        if (ids == null || ids.length == 0) {
            return;
        }
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaDelete<T> delete = cb.createCriteriaDelete(entityType);
        Root<T> root = delete.from(entityType);
        delete.where(root.get("id").in(Arrays.asList(ids)));
        entityManager.createQuery(delete).setFlushMode(FlushModeType.COMMIT).executeUpdate();
    }

    @Override
    public void flush() {
        entityManager.flush();
    }

    @SuppressWarnings("unchecked")
    @Override
    public ID getIdentifier(T entity) {
        Assert.notNull(entity, "not null entity");
        return (ID) entityManager.getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(entity);
    }

    @Override
    @SuppressWarnings("Duplicates")
    public List<T> findList(Filter filter) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityType);
        Root<T> root = criteriaQuery.from(entityType);
        FilterHandler.filterQuery(criteriaQuery, root, filter);
        return entityManager.createQuery(criteriaQuery).setFlushMode(FlushModeType.COMMIT).getResultList();
    }

    @Override
    @SuppressWarnings("Duplicates")
    public List<T> findList(Collection<Filter> filters) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityType);
        Root<T> root = criteriaQuery.from(entityType);
        FilterHandler.filterQuery(criteriaQuery, root, filters);
        return entityManager.createQuery(criteriaQuery).setFlushMode(FlushModeType.COMMIT).getResultList();
    }

    @Override
    public long count(Filter filter) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<T> root = criteriaQuery.from(entityType);
        criteriaQuery.select(criteriaBuilder.count(root));
        FilterHandler.filterQuery(criteriaQuery, root, filter);
        return entityManager.createQuery(criteriaQuery).setFlushMode(FlushModeType.COMMIT).getSingleResult();
    }

    @Override
    public Page<T> findPage(Pageable<T> pageable) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityType);
        Root<T> root = criteriaQuery.from(entityType);
        return findPageBase(criteriaBuilder, criteriaQuery, root, pageable);
    }

    /**
     * 处理分页基类方法
     * 只对dao包可见
     */
    protected Page<T> findPageBase(CriteriaBuilder criteriaBuilder, CriteriaQuery<T> criteriaQuery, Root<T> from, Pageable<T> pageable) {
        FilterHandler.filterQuery(criteriaQuery, from, pageable.getFilter());
        Predicate restriction = criteriaQuery.getRestriction();
        List<T> content = entityManager.createQuery(criteriaQuery).setFirstResult(pageable.getStartSize()).setMaxResults(pageable.getPageSize()).getResultList();
        //以同样的条件查找count
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<T> root = countQuery.from(entityType);
        countQuery.select(criteriaBuilder.count(root));
        countQuery.where(restriction);
        Long count = entityManager.createQuery(countQuery).setFlushMode(FlushModeType.COMMIT).getSingleResult();
        pageable.setContent(content);
        pageable.setEntityTotal(count);
        pageable.setPageTotal((int) ((count + pageable.getPageSize() - 1) / pageable.getPageSize()));
        return new Page<>(pageable);
    }

}
