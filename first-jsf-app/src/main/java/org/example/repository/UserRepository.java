package org.example.repository;

import org.example.persist.Product;
import org.example.persist.Role;
import org.example.persist.User;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.math.BigDecimal;

@Named
@ApplicationScoped
public class UserRepository extends Repository<User> {

    public UserRepository() {
        super();
    }

    @PostConstruct
    public void init() {
        this.saveOrUpdate(new User(null,"User 1","123456",
                "user1@mail.ru", Role.CLIENT));
        this.saveOrUpdate(new User(null,"User 2","123456",
                "user2@mail.ru",Role.CLIENT));
        this.saveOrUpdate(new User(null,"User 3","123456",
                "user3@mail.ru",Role.CLIENT));
    }
}
