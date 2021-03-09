package org.example.dto;

import lombok.*;
import org.example.persist.Cart;
import org.example.persist.Category;
import org.example.persist.Role;
import org.example.persist.User;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode

public class UserDto {
    private Long id;
    private Long cartId;
    private String name;
    private String email;
    private String password;
    private Role role;
    private Cart cart;

    public UserDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.role = user.getRole();
        Cart cart = user.getCart();
        cartId = cart != null ? cart.getId() : null;
    }
}
