package org.example.controller;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.example.persist.User;
import org.example.repository.UserRepository;

import javax.enterprise.context.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@Data
@Getter
@Setter
@SessionScoped
public class UserController implements Serializable {

    @Inject
    private UserRepository userRepository;
    @Inject
    private CartController cartController;
    private User user;
    private User selectedUser;

    public String createUser() {
        this.user = new User();
        return "/user_form.xhtml?faces-redirect-true";
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public String editUser(User user) {
        this.user = user;
        return "/user_form.xhtml?faces-redirect-true";
    }

    public void selectUser(User user) {
        this.user = user;
    }

    public void deleteUser(User user) {
        userRepository.deleteById(user.getId());
    }

    public String saveUser() {
        userRepository.saveOrUpdate(user);
        return "/user.xhtml?faces-redirect-true";
    }

    public void listenerChangeUser(ValueChangeEvent valueChangeEvent) {
        user = (User) valueChangeEvent.getNewValue();
    }
}
