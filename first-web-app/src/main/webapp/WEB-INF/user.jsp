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
    <title>${requestScope.pageHeader}</title>
</head>

<body>
<c:import url="menu.jsp" >
</c:import>

<div class="container">
    <div class="row py-2">
        <div class="col-12">
            <a class="btn btn-primary" href="user/create">Add User</a>
        </div>

        <div class="col-12">
            <table class="table table-bordered my-2">
                <thead>
                <tr>
                    <th scope="col">Id</th>
                    <th scope="col">Name</th>
                    <th scope="col">Password</th>
                    <th scope="col">Email</th>
                    <th scope="col">Actions</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="user" items="${requestScope.users}">
<%--                <% for (Product product : (List<Product>) request.getAttribute("products")) { %>--%>
                <tr>
                    <th scope="row">
                        <c:out value="${user.id}"/>
<%--                        <%= product.getId() %>--%>
                    </th>
                    <td>
                        <c:out value="${user.name}"/>
<%--                        <%= product.getName() %>--%>
                    </td>
                    <td>
                        <c:out value="${user.password}"/>
<%--                        <%= product.getDescription() %>--%>
                    </td>
                    <td>
                        <c:out value="${user.email}"/>
<%--                        <%= product.getPrice() %>--%>
                    </td>
                    <td>
                        <c:url value="/user/edit" var="userEditUrl">
                            <c:param name="id" value="${user.id}"/>
                        </c:url>
                        <a class="btn btn-success" href="${userEditUrl}"><i class="fas fa-edit"></i></a>
                        <c:url value="/user/delete" var="deleteEditUrl">
                            <c:param name="id" value="${user.id}"/>
                        </c:url>
                        <a class="btn btn-danger" href="${deleteEditUrl}"><i class="far fa-trash-alt"></i></a>
                    </td>
                </tr>
<%--                <% } %>--%>
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