package org.example.repository;

import org.example.persist.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class CategoryRepository {

    private static final Logger logger = LoggerFactory.getLogger(CategoryRepository.class);
    private final Class thisClass = Category.class;
    @PersistenceContext(unitName = "ds")
    protected EntityManager em;
    private final String nameClass = thisClass.getSimpleName();

    public List<Category> findAll() {
        return em.createQuery("from " + nameClass,thisClass).getResultList();
    }


    public Category findById(Long id) {
        return (Category) em.find(thisClass, id);
    }


    public List<Category> findAllById(List<Long> ids) {
        List<Category> list = new ArrayList<>();
        for (Long id : ids) {
            list.add(findById(id));
        }
        return list;
    }

    public Long countAll() {
        logger.info("Count");
        return em.createQuery("select count(*) from " + nameClass,Long.class).getSingleResult();
    }

    public Category getReference(Long id) {
        return (Category) em.getReference(thisClass, id);
    }

    public void saveOrUpdate(Category entity) {
        logger.info("save");
        if (entity.getId() == null) {
            em.persist(entity);
        }
        em.merge(entity);
    }

    public Category delete(Category entity) {
        if (em.contains(entity)) {
            em.remove(entity);
        } else {
            em.remove(em.merge(entity));
        }
        return entity;
    }

    public void deleteById(Long id) {
        em.createQuery(String.format("delete from %s where id = %d", nameClass, id)).executeUpdate();
    }

}
