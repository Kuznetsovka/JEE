package org.example.repository;

import org.example.persist.Entities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;


public abstract class Repository<T extends Entities> {
    private final Class<T> thisClass;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @PersistenceContext(unitName = "ds")
    protected EntityManager em;
    private final String nameClass;

    protected Repository(Class<T> thisClass) {
        this.thisClass = thisClass;
        this.nameClass = thisClass.getSimpleName();
    }

    public List<T> findAll() {
        return em.createQuery("from " + nameClass,thisClass).getResultList();
    }

    @Transactional
    public T findById(Long id) {
        return em.find(thisClass, id);
    }
    @Transactional
    public List<T> findAllById(List<Long> ids) {
        List<T> list = new ArrayList<>();
        for (Long id : ids) {
            list.add(findById(id));
        }
        return list;
    }

    public Long countAll() {
        logger.info("Count");
        return em.createQuery("select count(*) from " + nameClass,Long.class).getSingleResult();
    }

    @Transactional
    public void saveOrUpdate(T entity) {
        logger.info("save");
        if (entity.getId() == null) {
            em.persist(entity);
        }
        em.merge(entity);
    }

    @Transactional
    public T delete(T entity) {
        if (em.contains(entity)) {
            em.remove(entity);
        } else {
            em.remove(em.merge(entity));
        }
        return entity;
    }
    @Transactional
    public void deleteById(Long id) {
        em.createQuery(String.format("delete from %s where id = %d", nameClass, id)).executeUpdate();
    }

}
