package org.example.controller;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.example.dto.UserDto;
import org.example.service.UserService;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@Data
@Getter
@Setter
@SessionScoped
public class UserController implements Serializable {
    @EJB
    private UserService userService;
    private UserDto user;
    private UserDto selectedUser;
    private List<UserDto> users;

    public void preloadData(ComponentSystemEvent componentSystemEvent) {
        users = userService.findAll();
    }

    public String createUser() {
        this.user = new UserDto();
        return "/user_form.xhtml?faces-redirect-true";
    }

    public List<UserDto> getAllUsers() {
        return users;
    }

    public String editUser(UserDto user) {
        this.user = user;
        return "/user_form.xhtml?faces-redirect-true";
    }

    public void selectUser(UserDto user) {
        this.user = user;
    }

    public void deleteUser(UserDto user) {
        userService.deleteById(user.getId());
    }

    public String saveUser() {
        userService.saveOrUpdate(user);
        return "/user.xhtml?faces-redirect-true";
    }

    public void listenerChangeUser(ValueChangeEvent valueChangeEvent) {
        user = (UserDto) valueChangeEvent.getNewValue();
    }

//    public String addToCart(Product product) {
//        // Определение юзера
//        UserDto user = userService.findById(1L);
//        Cart cart = userService.addToUserCart(product, user);
//        this.cart = new CartDto(cart);
//        return "/cart.xhtml?faces-redirect=true";
//    }
}
