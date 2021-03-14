package org.example.controller;

import org.example.service.ProductDto;
import org.example.service.CartService;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class CartController implements Serializable {

    @EJB
    private CartService cartService;


    public void addToCart(ProductDto product) {
        cartService.addToCart(product);
    }

    public void removeFromCart(ProductDto product) {
        cartService.removeFromCart(product);
    }

    public List<ProductDto> getAllProducts() {
        return cartService.getAllProducts();
    }
}
