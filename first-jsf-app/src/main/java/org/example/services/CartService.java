package org.example.services;

import org.example.dto.CartDto;
import org.example.persist.Cart;
import org.example.persist.Product;
import org.example.repository.CartRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Stateless
@LocalBean
public class CartService implements Service<CartDto> {
    private static final Logger logger = LoggerFactory.getLogger(CartService.class);
    @EJB
    private CartRepository cartRepository;

    @TransactionAttribute
    @Override
    public List<CartDto> findAll() {
        return cartRepository.findAll().stream()
                .map(CartDto::new)
                .collect(Collectors.toList());
    }
    @TransactionAttribute
    @Override
    public CartDto findById(Long id) {
        Cart cart = cartRepository.findById(id);
        if (cart != null) {
            return new CartDto(cart);
        }
        return null;
    }

    @Override
    public Long countAll() {
        return cartRepository.countAll();
    }

    @TransactionAttribute
    @Override
    public void saveOrUpdate(CartDto cart) {
        logger.info("Saving cart with id {}" , cart.getId());
        List<Product> products = new ArrayList<>();
        if (cart.getProducts() != null) {
            products = cartRepository.getProducts(cart.getId());
        }
        cartRepository.saveOrUpdate(new Cart(cart, products));
    }

    @TransactionAttribute
    @Override
    public void deleteById(Long id) {
        cartRepository.deleteById(id);
    }


}
