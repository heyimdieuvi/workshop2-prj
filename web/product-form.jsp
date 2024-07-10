<%-- 
    Document   : product-form
    Created on : 23 Jun 2024, 17:35:34
    Author     : ADMIN
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Product Form</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>

<%@include file="menu-bar.jsp" %>
<div class="container col-md-5 card">
    <div class="container">
        <h2 class="text-center" style="margin-top: 10px">
            <c:if test="${product != null}">
                Edit Product
            </c:if>
            <c:if test="${product == null}">
                Add New Product
            </c:if>
        </h2>
        <c:if test="${product != null}">
            <form action="product-management" method="post">
                <input type="hidden" value="${product.productId}" name="productId">
                <div class="form-group">
                    <label class="h4">Product Name: </label>
                    <input type="text" class="form-control" value="<c:out value='${product.productName}'/>" name="productName">
                </div>
                <div class="form-group">
                    <label class="h4">Product Image: </label>
                    <input type="text" class="form-control" value="<c:out value='${product.productImage}'/>" name="productImage">
                </div>
                <div class="form-group">
                    <label class="h4">Brief: </label>
                    <input type="text" class="form-control" value="<c:out value='${product.brief}'/>" name="brief">
                </div>
                <div class="form-group">
                    <label class="h4">Posted Date: </label>
                    <input type="date" class="form-control" value="<c:out value='${product.postedDate}'/>" name="postedDate">
                </div>
                <div class="form-group">
                    <label class="h4">Category: </label>
                    <input type="text" class="form-control" value="<c:out value='${product.type.typeId}'/>" name="typeId">
                </div>
                <div class="form-group">
                    <label class="h4">Account: </label>
                    <input type="text" class="form-control" value="<c:out value='${product.account.account}'/>" name="account">
                </div>
                <div class="form-group">
                    <label class="h4">Unit: </label>
                    <input type="text" class="form-control" value="<c:out value='${product.unit}'/>" name="unit">
                </div>
                <div class="form-group">
                    <label class="h4">Price: </label>
                    <input type="number" class="form-control" value="<c:out value='${product.price}'/>" name="price">
                </div>
                <div class="form-group">
                    <label class="h4">Discount: </label>
                    <input type="number" class="form-control" value="<c:out value='${product.discount}'/>" name="discount">
                </div>
                <button type="submit" class="btn btn-success" name="action" value="update">Save</button>
            </form>
        </c:if>
        <c:if test="${product == null}">
            <form action="product-management" method="post">
                <div class="form-group">
                    <label class="h4">Product ID: </label>
                    <input type="text" class="form-control" name="productId">
                </div>
                <div class="form-group">
                    <label class="h4">Product Name: </label>
                    <input type="text" class="form-control" name="productName">
                </div>
                <div class="form-group">
                    <label class="h4">Product Image: </label>
                    <input type="text" class="form-control" name="productImage">
                </div>
                <div class="form-group">
                    <label class="h4">Brief: </label>
                    <input type="text" class="form-control" name="brief">
                </div>
                <div class="form-group">
                    <label class="h4">Posted Date: </label>
                    <input type="date" class="form-control" name="postedDate">
                </div>
                <div class="form-group">
                    <label class="h4">Category: </label>
                    <input type="text" class="form-control" name="typeId">
                </div>
                <div class="form-group">
                    <label class="h4">Account: </label>
                    <input type="text" class="form-control" value="<c:out value="${sessionScope.account.account}"/>" name="account">
                </div>
                <div class="form-group">
                    <label class="h4">Unit: </label>
                    <input type="text" class="form-control" name="unit">
                </div>
                <div class="form-group">
                    <label class="h4">Price: </label>
                    <input type="number" class="form-control" name="price">
                </div>
                <div class="form-group">
                    <label class="h4">Discount: </label>
                    <input type="number" class="form-control" name="discount">
                </div>
                <button type="submit" class="btn btn-success" name="action" value="insert">Save</button>
            </form>
        </c:if>
    </div>
</div>

</body>
</html>
