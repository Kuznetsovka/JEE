package org.example.service;

import org.example.dto.UserDto;
import org.example.persist.User;
import org.example.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import java.util.List;
import java.util.stream.Collectors;
@Stateful
@LocalBean
public class UserService implements Service<UserDto> {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @EJB
    private UserRepository userRepository;

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
//        Cart cart = null;
//        if (user.getCartId() != null) {
//            cart = cartRepository.getReference(user.getCartId());
//        }
        userRepository.saveOrUpdate(new User(user));
    }

    @TransactionAttribute
    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
