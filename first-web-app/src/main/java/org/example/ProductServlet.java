package org.example;

import org.example.persist.Category;
import org.example.persist.Product;
import org.example.persist.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet(urlPatterns = {"/product/*"})
public class ProductServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(ProductServlet.class);

    private Repository<Product> productRepository;
    private Repository<Category> categoryRepository;

    @Override
    public void init() throws ServletException {
        this.productRepository = (Repository) getServletContext().getAttribute("productRepository");
        this.categoryRepository = (Repository) getServletContext().getAttribute("categoryRepository");
        if (productRepository == null) {
            throw new ServletException("ProductRepository not initialized");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info(req.getPathInfo());
        long id;
        req.setAttribute("pageHeader", "Продукты");
        if (req.getPathInfo() == null || req.getPathInfo().equals("/")) {
            req.setAttribute("products", productRepository.findAll());
            req.setAttribute("categories", categoryRepository.findAll());
            getServletContext().getRequestDispatcher("/WEB-INF/product.jsp").forward(req, resp);
        }
        switch (req.getPathInfo()) {
            case ("/edit"): {
                getEditProduct(req, resp);
                break;
            }
            case ("/create"): {
                getCreateProduct(req, resp);
                break;
            }
            case ("/delete"): {
                getDeleteProduct(req, resp);
                break;
            }
        }
    }

    private void getDeleteProduct(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        long id;
        Product product = checkById(req, resp);
        if (product == null) return;
        req.setAttribute("product", product);
        id = Long.parseLong(req.getParameter("id"));
        productRepository.deleteById(id);
        resp.sendRedirect(getServletContext().getContextPath() + "/product");
    }

    private void getCreateProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Product product = new Product(productRepository.lastId()+1);
        redirectProductForm(req, resp, product);
    }

    private void redirectProductForm(HttpServletRequest req, HttpServletResponse resp, Product product) throws ServletException, IOException {
        req.setAttribute("product", product);
        req.setAttribute("categories", categoryRepository.findAll());
        getServletContext().getRequestDispatcher("/WEB-INF/product_form.jsp").forward(req, resp);
    }

    private void getEditProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Product product = checkById(req, resp);
        if (product == null) return;
        redirectProductForm(req, resp, product);
    }

    private Product checkById(HttpServletRequest req, HttpServletResponse resp) {
        long id;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException ex) {
            resp.setStatus(400);
            return null;
        }
        Product product = productRepository.findById(id);
        if (product == null) {
            resp.setStatus(404);
            return null;
        }
        return product;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        long id;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException ex) {
            resp.setStatus(400);
            return;
        }
        BigDecimal price;
        try {
            price = new BigDecimal(req.getParameter("price"));
        } catch (NumberFormatException ex) {
            resp.setStatus(400);
            return;
        }
        Category category = categoryRepository.findById(Long.valueOf(req.getParameter("categoryId")));
        Product product = new Product(id, req.getParameter("name"), req.getParameter("description"), category,price);
        productRepository.saveOrUpdate(product);
        resp.sendRedirect(getServletContext().getContextPath() + "/product");
    }

}
