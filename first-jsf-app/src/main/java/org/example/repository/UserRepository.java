package org.example.repository;

import org.example.persist.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;
import java.util.Collections;

@Named
@ApplicationScoped
public class UserRepository extends Repository<User> {
    @Inject
    private CartRepository cartRepository;

    public UserRepository() {
        super(User.class);
    }
    private static final Logger logger = LoggerFactory.getLogger(ProductRepository.class);
    @Resource
    private UserTransaction ut;
    @PostConstruct
    public void init() throws Exception {
        if (countAll() == 0) {
            try {
                ut.begin();
                this.saveOrUpdate(new User(null,"User 1","123456",
                        "user1@mail.ru", Role.CLIENT));
                this.saveOrUpdate(new User(null,"User 2","123456",
                        "user2@mail.ru",Role.CLIENT));
                this.saveOrUpdate(new User(null,"User 3","123456",
                        "user3@mail.ru",Role.CLIENT));
                ut.commit();
            } catch (Exception ex) {
                logger.error("ERROR", ex);
                ut.rollback();
            }
        }
    }
    @Transactional
    public Cart addToUserCart(Product product, User user) {
        if(user == null){
            throw new RuntimeException("User not found.");
        }
        Cart cart = user.getCart();
        if(cart == null){
            Cart newCart = cartRepository.createCart(Collections.singletonList(product.getId()));
            user.setCart(newCart);
            this.saveOrUpdate(user);
            cart = newCart;
        } else {
            cartRepository.addProducts(cart, Collections.singletonList(product.getId()));
        }
        return cart;
    }
}
