package org.example.repository;

import org.example.persist.Cart;
import org.example.persist.Category;
import org.example.persist.Product;
import org.example.persist.User;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Named
@ApplicationScoped
public class ProductRepository extends Repository<Product> {

    public ProductRepository() {
        super();
    }

    @PostConstruct
    public void init() {
        Category cat1 = new Category(null,"Bean");
        Category cat2 = new Category(null,"Good");
        Category cat3 = new Category(null, "Milk");
        Category cat4 = new Category(null, "Meat");
        this.saveOrUpdate(new Product(null, "Product  1",
                "Description of product 1", cat1, new BigDecimal(100)));
        this.saveOrUpdate(new Product(null, "Product  2",
                "Description of product 2", cat2,new BigDecimal(200)));
        this.saveOrUpdate(new Product(null, "Product  3",
                "Description of product 3", cat3,new BigDecimal(200)));
    }
}
