package org.example.repository;


import org.example.persist.Cart;
import org.example.persist.Product;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

@Stateless
@LocalBean
public class CartRepository extends Repository<Cart> {
    @EJB
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

    public void addProducts(Cart cart, List<Long> productIds) {
        List<Product> products = cart.getProducts();
        List<Product> newProductsList = products == null ? new ArrayList<> () : new ArrayList<> (products);
        newProductsList.addAll(productRepository.findAllById(productIds));
        cart.setProducts(newProductsList);
        this.saveOrUpdate(cart);
    }
    public List<Product> getProducts(Long id){
        Cart cart = this.findById(id);
        return cart.getProducts();
    }
}
