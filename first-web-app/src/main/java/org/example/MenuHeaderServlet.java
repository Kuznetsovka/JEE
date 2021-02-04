package org.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/page_menu")
public class MenuHeaderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("    <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css\">\n" +
                        "    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js\"></script>\n" +
                        "    <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js\"></script>" +
                        "<table id=\"nav-menu\" width=\"100%\" class=\"table table-hover\">\n" +
                        "  <td><a href=\"/first-web-app/true-shop/main\" class=\"active\">Main</a></td>\n" +
                        "  <td><a href=\"/first-web-app/true-shop/catalog\">Catalog</a></td>\n" +
                        "  <td><a href=\"/first-web-app/true-shop/product\">Product</a></td>\n" +
                        "  <td><a href=\"/first-web-app/true-shop/order\">Order</a></td>\n" +
                        "  <td><a href=\"/first-web-app/true-shop/card\">Card</a></td>\n" +
                        "</table>");
        String header = (String) req.getAttribute("pageHeader");
        resp.getWriter().println("<h1>" + header +"</h1>");
    }
}
