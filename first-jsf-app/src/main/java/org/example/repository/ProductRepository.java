package org.example.repository;

import org.example.persist.Category;
import org.example.persist.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.UserTransaction;
import java.math.BigDecimal;
import java.util.List;

@Named
@ApplicationScoped
public class ProductRepository extends Repository<Product> {

    public ProductRepository() {
        super(Product.class);
    }

    private static final Logger logger = LoggerFactory.getLogger(ProductRepository.class);
    @Resource
    private UserTransaction ut;
    @Inject
    CategoryRepository categoryRepository;
    @PostConstruct
    public void init() throws Exception {
        if (countAll() == 0) {
            try {
                ut.begin();
                Category cat1 = new Category(1L,"Bean");
                Category cat2 = new Category(2L,"Good");
                Category cat3 = new Category(3L, "Milk");
                this.saveOrUpdate(new Product(null, "Product  1",
                        "Description of product 1", cat1, new BigDecimal(100)));
                this.saveOrUpdate(new Product(null, "Product  2",
                        "Description of product 2", cat2, new BigDecimal(200)));
                this.saveOrUpdate(new Product(null, "Product  3",
                        "Description of product 3", cat3,new BigDecimal(200)));
                ut.commit();
            } catch (Exception ex) {
                logger.error("ERROR", ex);
                ut.rollback();
            }
        }
    }

    public List<Product> productsByCategory(Long id) {
        return em.createNamedQuery("productsByCategory", Product.class)
                .setParameter("id",id)
                .getResultList();
    }

}
