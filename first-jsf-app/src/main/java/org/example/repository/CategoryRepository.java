package org.example.repository;

import org.example.persist.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.transaction.UserTransaction;

@Named
@ApplicationScoped
public class CategoryRepository extends Repository<Category> {

    public CategoryRepository() {
        super(Category.class);
    }
    private static final Logger logger = LoggerFactory.getLogger(ProductRepository.class);
    @Resource
    private UserTransaction ut;
//    @PostConstruct
    public void init() throws Exception {
        if (countAll() == 0) {
            try {
                ut.begin();
                this.saveOrUpdate(new Category(1L,"Bean"));
                this.saveOrUpdate(new Category(2L,"Good"));
                this.saveOrUpdate(new Category(3L, "Milk"));
                this.saveOrUpdate(new Category(null, "Meat"));
                ut.commit();
            } catch (Exception ex) {
                logger.error("ERROR", ex);
                ut.rollback();
            }
        }
    }

}
