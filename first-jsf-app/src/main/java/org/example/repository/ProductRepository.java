package org.example.repository;

import org.example.persist.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.transaction.UserTransaction;
import java.math.BigDecimal;

@Named
@ApplicationScoped
public class ProductRepository extends Repository<Product> {

    public ProductRepository() {
        super(Product.class);
    }

    private static final Logger logger = LoggerFactory.getLogger(ProductRepository.class);
    @Resource
    private UserTransaction ut;
    @PostConstruct
    public void init() throws Exception {
        if (countAll() == 0) {
            try {
                ut.begin();
                this.saveOrUpdate(new Product(null, "Product  1",
                        "Description of product 1", new BigDecimal(100)));
                this.saveOrUpdate(new Product(null, "Product  2",
                        "Description of product 2", new BigDecimal(200)));
                this.saveOrUpdate(new Product(null, "Product  3",
                        "Description of product 3", new BigDecimal(200)));

                ut.commit();
            } catch (Exception ex) {
                logger.error("ERROR", ex);
                ut.rollback();
            }
        }
    }
}
