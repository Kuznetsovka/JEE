package org.example.repository;

import org.example.persist.Product;
import org.example.service.ProductDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class ProductRepository {

    private static final Logger logger = LoggerFactory.getLogger(ProductRepository.class);

    private final Class thisClass = Product.class;
    @PersistenceContext(unitName = "ds")
    protected EntityManager em;
    private final String nameClass = thisClass.getSimpleName();

    public List<Product> findAll() {
        return em.createQuery("from " + nameClass,thisClass).getResultList();
    }


    public Product findById(Long id) {
        return (Product) em.find(thisClass, id);
    }

    public List<Product> findAllById(List<Long> ids) {
        List<Product> list = new ArrayList<>();
        for (Long id : ids) {
            list.add(findById(id));
        }
        return list;
    }

    public Long countAll() {
        logger.info("Count");
        return em.createQuery("select count(*) from " + nameClass,Long.class).getSingleResult();
    }

    public Product getReference(Long id) {
        return (Product) em.getReference(thisClass, id);
    }

    public void saveOrUpdate(Product entity) {
        logger.info("save");
        if (entity.getId() == null) {
            em.persist(entity);
        }
        em.merge(entity);
    }

    public Product delete(Product entity) {
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

    public List<Product> productsByCategory(Long id) {
        return em.createNamedQuery("productsByCategory", Product.class)
                .setParameter("id",id)
                .getResultList();
    }

}
