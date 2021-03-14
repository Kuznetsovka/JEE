package org.example.controller;

import lombok.Data;
import org.example.ButtonView;
import org.example.persist.Product;
import org.example.service.ProductDto;
import org.example.service.ProductService;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@Data
@SessionScoped
public class ProductController implements Serializable {
    @EJB
    private ProductService productService;
    @Inject
    private ButtonView buttonView;
    private ProductDto product;
    private List<ProductDto> products;
    private boolean filter;

    public void preloadData(ComponentSystemEvent componentSystemEvent) {
        if (!filter)
            products = productService.findAll();
        filter = false;
    }

    public String createProduct() {
        this.product = new ProductDto();
        return "/product_form.xhtml?faces-redirect=true";
    }

    public List<ProductDto> getAllProducts() {
        return products;
    }

    public String getProductsByCategory(Long id) {
        products = productService.productsByCategory(id);
        filter = true;
        return "/product.xhtml?faces-redirect=true";
    }

    public String editProduct(ProductDto product) {
        this.product = product;
        buttonView.update();
        return "/product_form.xhtml?faces-redirect=true";
    }

    public void deleteProduct(ProductDto product) {
        buttonView.delete();
        productService.deleteById(product.getId());
    }

    public String saveProduct() {
        productService.saveOrUpdate(product);
        return "/product.xhtml?faces-redirect=true";
    }
}
