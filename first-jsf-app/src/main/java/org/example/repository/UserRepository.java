package org.example.repository;

import org.example.persist.Cart;
import org.example.persist.Product;
import org.example.persist.Role;
import org.example.persist.User;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Collections;

@Named
@ApplicationScoped
public class UserRepository extends Repository<User> {
    @Inject
    private CartRepository cartRepository;

    public UserRepository() {
        super(User.class);
    }

    @PostConstruct
    public void init() {
        this.saveOrUpdate(new User(null,"User 1","123456",
                "user1@mail.ru", Role.CLIENT));
        this.saveOrUpdate(new User(null,"User 2","123456",
                "user2@mail.ru",Role.CLIENT));
        this.saveOrUpdate(new User(null,"User 3","123456",
                "user3@mail.ru",Role.CLIENT));
    }

    public Cart addToUserCart(Product product, User user) {
        if(user == null){
            throw new RuntimeException("User not found.");
        }
        Cart cart = user.getCart();
        if(cart == null){
            Cart newCart = cartRepository.createCart(user, Collections.singletonList(product.getId()));
            user.setCart(newCart);
            this.saveOrUpdate(user);
            cart = newCart;
        } else {
            cartRepository.addProducts(cart, Collections.singletonList(product.getId()));
        }
        return cart;
    }
}
