package org.example;

import org.example.persist.Category;
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
    private Repository<Category> categoryRepository;

    @Override
    public void init() throws ServletException {
        this.userRepository = (Repository) getServletContext().getAttribute("userRepository");
        this.categoryRepository = (Repository) getServletContext().getAttribute("categoryRepository");
        if (userRepository == null) {
            throw new ServletException("UserRepository not initialized");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println(userRepository);
        logger.info(req.getPathInfo());
        if (req.getPathInfo() == null || req.getPathInfo().equals("/")) {
            req.setAttribute("users", userRepository.findAll());
            req.setAttribute("categories", categoryRepository.findAll());
            getServletContext().getRequestDispatcher("/WEB-INF/user.jsp").forward(req, resp);
        }
        switch (req.getPathInfo()){
            case ("/edit"): {
                getEditUser(req, resp);
                break;
            }
            case("/create"): {
                getCreateUser(req, resp);
                break;
            }
            case("/delete"):{
                getDeleteUser(req, resp);
                break;
            }
        }
    }

    private void getDeleteUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        long id;
        User user = checkById(req, resp);
        if (user == null) return;
        req.setAttribute("user", user);
        id = Long.parseLong(req.getParameter("id"));
        userRepository.deleteById(id);
        resp.sendRedirect(getServletContext().getContextPath() + "/user");
    }

    private void getCreateUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        userRepository.saveOrUpdate(user);
        req.setAttribute("user", user);
        getServletContext().getRequestDispatcher("/WEB-INF/user_form.jsp").forward(req, resp);
    }

    private void getEditUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
