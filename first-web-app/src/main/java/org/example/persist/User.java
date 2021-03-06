package org.example.persist;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements Entities {

    private Long id;
    private String name;
    private String password;
    private String email;
    private boolean archive;
    private Role role;
    private Bucket bucket;

    public User(Long id, String name, String password, String email, Role role) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.role = role;
    }
    public User(Long id) {
        this.id = id;
    }
}
