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
    private List<Category> categories;

    public void preloadData() {
        categories = categoryRepository.findAll();
    }
    public List<Category> getAllCategories() {
        return categories;
    }

    private Category category;
}

