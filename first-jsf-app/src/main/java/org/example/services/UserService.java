package org.example.services;

import org.example.dto.CartDto;
import org.example.dto.UserDto;
import org.example.persist.Cart;
import org.example.persist.Product;
import org.example.persist.User;
import org.example.repository.CartRepository;
import org.example.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
@Stateful
@LocalBean
public class UserService implements Service<UserDto> {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @EJB
    private UserRepository userRepository;
    @EJB
    private CartRepository cartRepository;
    @EJB
    private CartService cartService;

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream()
                .map(UserDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto findById(Long id) {
        User user = userRepository.findById(id);
        if (user != null) {
            return new UserDto(user);
        }
        return null;
    }

    @Override
    public Long countAll() {
        return userRepository.countAll();
    }

    @TransactionAttribute
    @Override
    public void saveOrUpdate(UserDto user) {
        logger.info("Saving user with id {}" , user.getId());
        Cart cart = null;
        if (user.getCartId() != null) {
            cart = cartRepository.getReference(user.getCartId());
        }
        userRepository.saveOrUpdate(new User(user,cart));
    }

    @TransactionAttribute
    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @TransactionAttribute
    public Cart addToUserCart(Product product, UserDto user) {
        if(user == null){
            throw new RuntimeException("User not found.");
        }
        Cart cart = user.getCart();
        if(user.getCartId() == null){
            Cart newCart = cartRepository.createCart(Collections.singletonList(product.getId()));
            user.setCart(newCart);
            this.saveOrUpdate(user);
        } else {
            cartRepository.addProducts(cart, Collections.singletonList(product.getId()));
        }
        return cart;
    }

    @TransactionAttribute
    public void deleteProductFromCart(CartDto cart, Product product) {
        if(cart == null || cart.getProducts().isEmpty()){
            return;
        }
        cart.getProducts ().remove (product);
        cartService.saveOrUpdate(cart);
    }
}
