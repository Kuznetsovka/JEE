package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;

public class FirstServlet implements Servlet {
    private ServletConfig config;
    private static final Logger logger = LoggerFactory.getLogger (FirstServlet.class);
    public void init(ServletConfig config) throws ServletException {
        logger.info ("FirstServlet is initialised");
        this.config = config;
    }

    public ServletConfig getServletConfig() {
        return this.config;
    }

    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        logger.info ("New request to FirstServlet");
        res.getWriter ().println ("<h1>Hello world from servlet!!!</h1>");
    }

    public String getServletInfo() {
        return null;
    }

    public void destroy() {

    }
}
