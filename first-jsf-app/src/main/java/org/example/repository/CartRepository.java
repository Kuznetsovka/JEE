package org.example.repository;

import org.example.persist.Cart;
import org.example.persist.Product;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Named
@ApplicationScoped
public class CartRepository extends Repository<Cart> {
    @Inject
    private ProductRepository productRepository;

    public CartRepository() {
        super(Cart.class);
    }

    public Cart createCart(List<Long> productIds) {
        Cart cart = new Cart();
        List<Product> products= productRepository.findAllById(productIds);
        cart.setProducts(products);
        this.saveOrUpdate(cart);
        return cart;
    }

    @Transactional
    public void addProducts(Cart cart, List<Long> productIds) {
        List<Product> products = cart.getProducts();
        List<Product> newProductsList = products == null ? new ArrayList<> () : new ArrayList<> (products);
        newProductsList.addAll(productRepository.findAllById(productIds));
        cart.setProducts(newProductsList);
        this.saveOrUpdate(cart);
    }
    @Transactional
    public void deleteProductFromCart(Cart cart, Product product) {
        if(cart == null || cart.getProducts().isEmpty()){
            return;
        }
        cart.getProducts ().remove (product);
        this.saveOrUpdate(cart);
    }
}
