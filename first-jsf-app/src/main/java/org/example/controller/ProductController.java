package org.example.controller;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.example.persist.Product;
import org.example.repository.ProductRepository;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@Data
@SessionScoped
public class ProductController implements Serializable {

    @Inject
    private ProductRepository productRepository;

    private Product product;

    public String createProduct() {
        this.product = new Product();
        return "/product_form.xhtml?faces-redirect-true";
    }

    public String getProductByIdCategory(Long id) {
        this.product = new Product();
        return "/product_form.xhtml?faces-redirect-true";
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public String editProduct(Product product) {
        this.product = product;
        return "/product_form.xhtml?faces-redirect-true";
    }

    public void deleteProduct(Product product) {
        productRepository.deleteById(product.getId());
    }

    public String saveProduct() {
        productRepository.saveOrUpdate(product);
        return "/product.xhtml?faces-redirect-true";
    }
}