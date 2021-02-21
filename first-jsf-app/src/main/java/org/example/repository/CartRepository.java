package org.example.repository;

import org.example.persist.Cart;
import org.example.persist.Product;
import org.example.persist.Role;
import org.example.persist.User;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Named
@ApplicationScoped
public class CartRepository extends Repository<Cart> {
    @Inject
    private ProductRepository productRepository;

    public CartRepository() {
        super();
    }

    public Cart createCart(User user, List<Long> productIds) {
        Cart cart = new Cart();
        List<Product> products= getCollectRefProductsByIds(productIds);
        cart.setProducts(products);
        this.saveOrUpdate(cart);
        return cart;
    }


    private List<Product> getCollectRefProductsByIds(List<Long> productIds) {
        return productIds.stream()
                .map(productRepository::findById)
                .collect(Collectors.toList());
    }

    public void addProducts(Cart cart, List<Long> productIds) {
        List<Product> products = cart.getProducts();
        List<Product> newProductsList = products == null ? new ArrayList<> () : new ArrayList<> (products);
        newProductsList.addAll(getCollectRefProductsByIds(productIds));
        cart.setProducts(newProductsList);
        this.saveOrUpdate(cart);
    }

    public void deleteProductFromCart(Product product) {
        //Газлушка определения cart
        Cart cart = this.findById(1L);
        if(cart == null || cart.getProducts().isEmpty()){
            return;
        }
        cart.getProducts ().remove (product);
        this.saveOrUpdate(cart);
    }
}
