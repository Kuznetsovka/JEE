package org.example.repository;

import org.example.persist.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class ProductRepository extends Repository<Product> {

    public ProductRepository() {
        super(Product.class);
    }

    private static final Logger logger = LoggerFactory.getLogger(ProductRepository.class);

    public List<Product> productsByCategory(Long id) {
        return em.createNamedQuery("productsByCategory", Product.class)
                .setParameter("id", id)
                .getResultList();
    }

    public Product productByName(String name) {
        return em.createQuery("select p from Product p where p.name = '" + name + "'",Product.class).getSingleResult();
    }
}
