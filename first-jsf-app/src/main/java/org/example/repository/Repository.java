package org.example.repository;

import org.example.persist.Entities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import java.util.List;


public abstract class Repository<T extends Entities> {
    private final Class<T> thisClass;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @PersistenceContext(unitName = "ds")
    private EntityManager em;

    protected Repository(Class<T> thisClass) {
        this.thisClass = thisClass;
    }

    public List<T> findAll() {
        return em.createQuery("select * from " + thisClass.getSimpleName()).getResultList();
    }

    public T findById(Long id) {
        return em.find(thisClass, id);
    }

    public Long countAll() {
        logger.info("Count");
        return em.createQuery("select count(*) from " + thisClass.getSimpleName(),Long.class).getSingleResult();
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
        em.createQuery("delete from "+ thisClass.getSimpleName() +" p where p.id = " + id);
    }

}
