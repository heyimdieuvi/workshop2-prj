<%-- 
    Document   : menu-bar
    Created on : 20 Jun 2024, 23:03:24
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
    </head>
    <body>
        <!--begin of menu-->
        <nav class="navbar navbar-expand-md navbar-dark bg-dark">
            <div class="container">
                <c:choose>
                    <c:when test="${not empty sessionScope.account and (sessionScope.role == 1 or sessionScope.role == 2)}">
                        <a class="navbar-brand font-weight-bold" href="admin">Shoppie</a>
                    </c:when>
                    <c:otherwise>
                        <a class="navbar-brand font-weight-bold" href="main">Shoppie</a>    
                    </c:otherwise>
                </c:choose>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse justify-content-end" id="navbarsExampleDefault">
                    <ul class="navbar-nav m-auto">
                        <c:choose>
                            <c:when test="${not empty sessionScope.account and sessionScope.role == 1}">
                                <li class="nav-item">
                                    <a class="nav-link" href="admin?action=account-management">Manager Account</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="admin?action=category-management">Manager Category</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="admin?action=product-management">Manager Product</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="#">Hello ${sessionScope.account.firstName}</a>
                                </li>
                                <li class="nav-item">
                                    <a class="btn btn-success btn-sm ml-3" href="main?action=logout">
                                        <i class="fa fa-sign-out"></i> Log Out
                                    </a>
                                </li>
                            </c:when>
                            <c:when test="${not empty sessionScope.account and sessionScope.role == 2}">
                                <li class="nav-item">
                                    <a class="nav-link" href="admin?action=category-management">Manager Category</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="admin?action=product-management">Manager Product</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="#">Hello ${sessionScope.account.firstName}</a>
                                </li>
                                <li class="nav-item">
                                    <a class="btn btn-success btn-sm ml-3" href="main?action=logout">
                                        <i class="fa fa-sign-out"></i> Log Out
                                    </a>
                                </li>
                            </c:when>
                                <c:when test="${not empty sessionScope.account and sessionScope.role == 3}">
                                <li class="nav-item">
                                    <a class="nav-link" href="#">Hello ${sessionScope.account.firstName}</a>
                                </li>
                                <li class="nav-item">
                                    <a class="btn btn-success btn-sm ml-3" href="main?action=logout">
                                        <i class="fa fa-sign-out"></i> Log Out
                                    </a>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li class="nav-item">
                                    <a class="btn btn-success btn-sm ml-3" href="main?action=login">
                                        <i class="fa fa-sign-in"></i> Log In
                                    </a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </ul>
                </div>
            </div>
        </nav>
    </body>
</html>
