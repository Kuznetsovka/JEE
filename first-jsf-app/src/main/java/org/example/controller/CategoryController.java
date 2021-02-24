package org.example.controller;


import lombok.Data;
import org.example.persist.Category;
import org.example.repository.CategoryRepository;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@Data
@SessionScoped
public class CategoryController implements Serializable {

    @Inject
    private CategoryRepository categoryRepository;

    private Category category;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}

