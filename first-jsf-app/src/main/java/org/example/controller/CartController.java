package org.example.controller;


import lombok.Data;
import org.example.persist.Cart;
import org.example.persist.Product;
import org.example.persist.User;
import org.example.repository.CartRepository;
import org.example.repository.ProductRepository;
import org.example.repository.UserRepository;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@Data
@SessionScoped
public class CartController implements Serializable {
    @Inject
    private UserRepository userRepository;
    @Inject
    private UserController userController;
    @Inject
    private ProductRepository productRepository;
    private Cart cart;
    @Inject
    private CartRepository cartRepository;
    public String deleteProductFromCart(Product product) {
        cartRepository.deleteProductFromCart(product);
        return "/cart.xhtml?faces-redirect=true";
    }
    //TODO CartRepository
    public String addToCart(Product product ) {
        // Определение юзера
        User user = userRepository.findById(1L);
        // Почему-то листнер не работает.
//      User user =  userController.getUser();
        Cart cart = userRepository.addToUserCart(product, user);
        return "/cart.xhtml?faces-redirect=true";
    }
}
