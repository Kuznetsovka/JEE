package org.example.persist;

import lombok.*;
import org.example.dto.CartDto;
import org.example.dto.UserDto;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@Entity
@Table(name="users")
public class User implements Entities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String password;
    @Column
    private String email;
    @Column
    private boolean archive;
    @Column
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToOne (cascade = CascadeType.ALL)
    private Cart cart;

    public User(Long id, String name, String password, String email, Role role) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public User(UserDto userDto, Cart cart) {
        this(userDto.getId(), userDto.getName(), userDto.getPassword(), userDto.getEmail(),userDto.getRole());
        this.cart = cart;
    }
    public User(Long id) {
        this.id = id;
    }
}
