package org.example.dto;

import lombok.*;
import org.example.persist.Role;
import org.example.persist.User;

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

    public UserDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.role = user.getRole();
    }
}
