<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"/>
    <title>EShop application</title>
</head>

<body>
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
            <li class="nav-item active">
                <a class="nav-link" href=<%= getServletContext().getContextPath()%>/category>Catalog</a>
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

<div class="container">
    <div class="row py-2">
        <div class="col-12">
            <table class="table table-bordered my-2">
                <thead>
                <tr>
                    <th scope="col">Title</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="category" items="${requestScope.categories}">
                <tr>
                    <th scope="row">

                        <c:url value="/category/get" var="categoryById">
                            <c:param name="id" value="${category.id}"/>
                        </c:url>
                        <a href="${categoryById}"><c:out value="${category.title}"/></a>
                    </th>
                </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
</body>
</html>