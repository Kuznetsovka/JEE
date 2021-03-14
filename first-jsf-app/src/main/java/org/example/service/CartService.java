package org.example.service;

import javax.ejb.Local;
import java.util.List;

@Local
public interface CartService {

    void addToCart(ProductDto product);
    void removeFromCart(ProductDto product);
    ProductDto findById(Long id);
    int countAll();
    List<ProductDto> getAllProducts();
}
