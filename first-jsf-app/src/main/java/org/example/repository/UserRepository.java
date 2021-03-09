package org.example.repository;

import org.example.persist.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;

@Stateful
@LocalBean
public class UserRepository extends Repository<User> {

    public UserRepository() {
        super(User.class);
    }
    private static final Logger logger = LoggerFactory.getLogger(UserRepository.class);

}
