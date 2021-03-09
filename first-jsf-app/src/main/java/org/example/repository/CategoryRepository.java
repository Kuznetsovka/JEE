package org.example.repository;

import org.example.persist.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@Stateless
@LocalBean
public class CategoryRepository extends Repository<Category> {

    public CategoryRepository() {
        super(Category.class);
    }
    private static final Logger logger = LoggerFactory.getLogger(CategoryRepository.class);


}
