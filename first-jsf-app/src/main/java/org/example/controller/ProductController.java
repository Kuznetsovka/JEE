package org.example.controller;

import lombok.Data;
import org.example.ButtonView;
import org.example.persist.Product;
import org.example.repository.ProductRepository;
import org.example.repository.UserRepository;

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
    @Inject
    ButtonView buttonView;
    private Product product;
    @Inject
    private UserRepository userRepository;

    public String createProduct() {
        this.product = new Product();
        return "/product_form.xhtml?faces-redirect=true";
    }

    public String getProductByIdCategory(Long id) {
        this.product = new Product();
        return "/product_form.xhtml?faces-redirect=true";
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public String editProduct(Product product) {
        this.product = product;
        buttonView.update();
        return "/product_form.xhtml?faces-redirect=true";
    }

    public void deleteProduct(Product product) {
        buttonView.delete();
        productRepository.deleteById(product.getId());
    }

    public String saveProduct() {
        productRepository.saveOrUpdate(product);
        return "/product.xhtml?faces-redirect=true";
    }
}
