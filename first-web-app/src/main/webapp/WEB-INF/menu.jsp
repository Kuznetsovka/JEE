<%@ page import="org.example.persist.Category" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
<nav id="nav-menu" class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">TrueShop</a>

    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href=<%=getServletContext().getContextPath()%>/main>Main</a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href=<%=getServletContext().getContextPath()%>/user>User</a>
            </li>

            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href=<%= getServletContext().getContextPath()%>/category id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Catalog
                </a>

                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <c:forEach var="category" items="${requestScope.categories}">
                        <c:url value="/product/category/" var="categoryById">
                            <c:param name="id" value="${category.id}"/>
                        </c:url>
                        <a class="dropdown-item" href="${categoryById}"><c:out value="${category.title}"/></a>
                    </c:forEach>
                </div>

            </li>

            <li class="nav-item active">
                <a class="nav-link" href=<%= getServletContext().getContextPath()%>/product>Product</a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href=<%= getServletContext().getContextPath()%>/order>Order</a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href=<%=getServletContext().getContextPath()%>/card>Card</a>
            </li>
        </ul>
    </div>
</nav>
</html>