package org.example;

import org.example.persist.Category;
import org.example.persist.Product;
import org.example.persist.Repository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = {"/product/category/*"})
public class CategoryServlet extends HttpServlet {

    private Repository<Category> categoryRepository;
    private Repository<Product> productRepository;

    @Override
    public void init() throws ServletException {
        this.categoryRepository = (Repository) getServletContext().getAttribute("categoryRepository");
        this.productRepository = (Repository) getServletContext().getAttribute("productRepository");
        if (categoryRepository == null) {
            throw new ServletException("ProductRepository not initialized");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id;
        Category category = checkById(req, resp);
//        if (category == null && req.getPathInfo() == null || req.getPathInfo().equals("/")) {
//            req.setAttribute("categories", categoryRepository.findAll());
//            getServletContext().getRequestDispatcher("/WEB-INF/category.jsp").forward(req, resp);
//        }
        List<Product> products = productRepository.findAll().stream().filter(product -> product.getCategory()==category).collect(Collectors.toList());
        req.setAttribute("products", products);
        getServletContext().getRequestDispatcher("/WEB-INF/product.jsp").forward(req, resp);
    }

    private Category checkById(HttpServletRequest req, HttpServletResponse resp) {
        long id;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException ex) {
            resp.setStatus(400);
            return null;
        }
        Category category = categoryRepository.findById(id);
        if (category == null) {
            resp.setStatus(404);
            return null;
        }
        return category;
    }
}
