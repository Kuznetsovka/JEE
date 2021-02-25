package org.example.repository;

import org.example.persist.Category;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named
@ApplicationScoped
public class CategoryRepository extends Repository<Category> {

    public CategoryRepository() {
        super(Category.class);
    }

    @PostConstruct
    public void init() {
        this.saveOrUpdate(new Category(null,"Bean"));
        this.saveOrUpdate(new Category(null,"Good"));
        this.saveOrUpdate(new Category(null, "Milk"));
        this.saveOrUpdate(new Category(null, "Meat"));
    }

}
