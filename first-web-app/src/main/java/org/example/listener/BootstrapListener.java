package org.example.listener;

import org.example.persist.*;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.math.BigDecimal;

@WebListener
public class BootstrapListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Repository userRepository = new Repository<User>();

        userRepository.saveOrUpdate(new User(null,"User 1","123456",
                "user1@mail.ru",Role.CLIENT));
        userRepository.saveOrUpdate(new User(null,"User 2","123456",
                "user2@mail.ru",Role.CLIENT));
        userRepository.saveOrUpdate(new User(null,"User 3","123456",
                "user3@mail.ru",Role.CLIENT));

        Repository categoryRepository = new Repository<Category>();
        Category cat1 = new Category(null,"Бакалея");
        Category cat2 = new Category(null,"Хозтовары");
        Category cat3 = new Category(null, "Мясо");
        Category cat4 = new Category(null, "Молочные продукты");
        categoryRepository.saveOrUpdate(cat1);
        categoryRepository.saveOrUpdate(cat2);
        categoryRepository.saveOrUpdate(cat3);
        categoryRepository.saveOrUpdate(cat4);

        Repository productRepository = new Repository<Product>();


        productRepository.saveOrUpdate(new Product(null, "Product  1",
                "Description of product 1",cat1,  new BigDecimal(100)));
        productRepository.saveOrUpdate(new Product(null, "Product  2",
                "Description of product 2",cat3, new BigDecimal(200)));
        productRepository.saveOrUpdate(new Product(null, "Product  3",
                "Description of product 3",cat1, new BigDecimal(200)));

        sce.getServletContext().setAttribute("categoryRepository", categoryRepository);
        sce.getServletContext().setAttribute("productRepository", productRepository);
        sce.getServletContext().setAttribute("userRepository", userRepository);
    }
}
