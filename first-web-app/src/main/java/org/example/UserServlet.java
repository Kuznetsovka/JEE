package org.example;

import org.example.persist.Product;
import org.example.persist.Repository;
import org.example.persist.Role;
import org.example.persist.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/user/*"})
public class UserServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(UserServlet.class);

    private Repository<User> userRepository;

    @Override
    public void init() throws ServletException {
        this.userRepository = (Repository) getServletContext().getAttribute("userRepository");
        if (userRepository == null) {
            throw new ServletException("UserRepository not initialized");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println(userRepository);
        logger.info(req.getPathInfo());
        long id;
        switch (req.getPathInfo()){
            case ("/edit"): {
                getEditProduct(req, resp);
                break;
            }
            case("/create"): {
                getCreateProduct(req, resp);
                break;
            }
            case("/delete"):{
                getDeleteProduct(req, resp);
                break;
            }
            default: {
                req.setAttribute("users", userRepository.findAll());
                getServletContext().getRequestDispatcher("/WEB-INF/user.jsp").forward(req, resp);
            }
        }
    }

    private void getDeleteProduct(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        long id;
        User user = checkById(req, resp);
        if (user == null) return;
        req.setAttribute("user", user);
        id = Long.parseLong(req.getParameter("id"));
        userRepository.deleteById(id);
        resp.sendRedirect(getServletContext().getContextPath() + "/user");
    }

    private void getCreateProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        userRepository.saveOrUpdate(user);
        req.setAttribute("user", user);
        getServletContext().getRequestDispatcher("/WEB-INF/user_form.jsp").forward(req, resp);
    }

    private void getEditProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = checkById(req, resp);
        if (user == null) return;
        req.setAttribute("user", user);
        getServletContext().getRequestDispatcher("/WEB-INF/user_form.jsp").forward(req, resp);
    }

    private User checkById(HttpServletRequest req, HttpServletResponse resp) {
        long id;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException ex) {
            resp.setStatus(400);
            return null;
        }
        User user = userRepository.findById(id);
        if (user == null) {
            resp.setStatus(404);
            return null;
        }
        return user;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException ex) {
            resp.setStatus(400);
            return;
        }
        User user = new User(id, req.getParameter("name"), req.getParameter("password"), req.getParameter("email"),Role.CLIENT);
        userRepository.saveOrUpdate(user);
        resp.sendRedirect(getServletContext().getContextPath() + "/user");
    }

}
