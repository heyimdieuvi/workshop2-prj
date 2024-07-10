<%-- 
    Document   : admin-home
    Created on : 23 Jun 2024, 20:31:00
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shoppie</title>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <!------ Include the above in your HEAD tag ---------->
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <link href="assets/css/style.css" rel="stylesheet" type="text/css"/>

    </head>
    <body>

        <%@include file="menu-bar.jsp" %>
        <section class="jumbotron text-center">
            <div class="container">
                <h1 class="jumbotron-heading">Welcome To Shoppie</h1>
                <p class="lead text-muted mb-0">Trust creates the brand with over 10 years of providing high-quality imported products</p>
            </div>
        </section>
        <!--end of menu-->
        <div class="container">
            <div class="row">
                <div class="col">
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a href="#">Home</a></li>
                            <li class="breadcrumb-item"><a href="#">Category</a></li>
                            <li class="breadcrumb-item active" aria-current="page">Sub-category</li>
                        </ol>
                    </nav>
                </div>
            </div>
        </div>
        <div class="container">
            <div class="row">
                <div class="col-sm-3">
                    <div class="card bg-light mb-3">
                        <div class="card-header bg-primary text-white text-uppercase"><i class="fa fa-list"></i> Categories</div>
                        <ul class="list-group category_block">
                            <c:forEach items="${listCategories}" var="category">
                                <li class="list-group-item"><a href="#">${category.categoryName}</a></li>
                                </c:forEach>
                        </ul>
                    </div>
                </div>

                <div class="col-sm-9">
                    <div class="row">
                        <c:forEach items="${listProducts}" var="product">
                            <div class="col-12 col-md-6 col-lg-4">
                                <div class="card">
                                    <img class="card-img-top" src="${product.productImage}" alt="Product image cap">
                                    <div class="card-body">
                                        <h4 class="card-title show_txt"><a href="#" title="View Product">${product.productName}</a></h4>
                                        <p class="card-text show_txt">${product.brief}</p>
                                        <c:choose>
                                            <c:when test="${product.discount == 0}">
                                                <div class="col-12">
                                                    <p class="text-center" style="font-weight: 500; font-size: 1.1rem">${product.price} $</p>
                                                </div>
                                            </c:when>
                                            <c:otherwise>
                                                <div class="row">
                                                    <div class="col">
                                                        <p style="font-weight: 500; font-size: 1.08rem">${product.price - product.discount/100} $</p>
                                                    </div>
                                                    <div class="col">
                                                        <p><s>${product.price} $</s></p>
                                                    </div>
                                                </div>
                                            </c:otherwise>
                                        </c:choose>

                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>

    </body><!-- comment -->

