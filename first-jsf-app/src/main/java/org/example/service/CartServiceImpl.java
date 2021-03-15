package org.example.service;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Stateful
@LocalBean
public class CartServiceImpl implements CartService {

    private final Map<Long, ProductDto> productMap = new HashMap<>();

    @Override
    public void addToCart(ProductDto product) {
        productMap.put(product.getId(),product);
    }

    @Override
    public void removeFromCart(ProductDto product) {
        productMap.remove(product.getId());
    }

    @Override
    public ProductDto findById(Long id) {
        if (productMap.containsKey(id))
            return productMap.get(id);
        return null;
    }

    @Override
    public int countAll() {
        return productMap.size();
    }

    @Override
    public List<ProductDto> getAllProducts() {
        return new ArrayList<>(productMap.values());
    }
}
