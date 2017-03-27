package com.object.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;

/**
 * Created by jack on 2017/2/27.
 */
@Component
public abstract class AbstractJpaDao<T, ID extends Serializable> {
    private Class<T> entityClass;

    @PersistenceContext
    private EntityManager em;

    @SuppressWarnings( "unchecked" )
    AbstractJpaDao() {
        entityClass = null;
        Type type = getClass().getGenericSuperclass();
        if(type instanceof  ParameterizedType){
            Type[] paramList = ((ParameterizedType) type).getActualTypeArguments();
            entityClass = (Class<T> )paramList[0];
        }else{
            entityClass = (Class<T>) Object.class;
        }
    }

    /**
     * find by id
     *
     * @param id id
     * @return record
     */
    public T find(ID id) {
        return em.find(entityClass, id);
    }

    /**
     * find all entities
     *
     * @return all entities
     */
    public List<T> findAll() {
        return em.createQuery("from" + entityClass.getName(), entityClass).getResultList();
    }

    /**
     * save entity
     *
     * @param t entity
     */
    @Transactional
    public void save(T t) {
        em.persist(t);
    }

    /**
     * update entity t
     *
     * @param t entity
     */
    @Transactional
    public void update(T t) {
        em.merge(t);
    }

    /**
     * delete T t
     *
     * @param t entity
     */
    @Transactional
    public void delete(T t) {
        em.remove(t);
    }

    /**
     * delete by id
     *
     * @param id identity
     */
    public void delete(ID id) {
        T t = find(id);
        if (!Objects.equals(t, null)) {
            delete(t);
        }
    }

}
