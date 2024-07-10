<%-- 
    Document   : product-list
    Created on : 23 Jun 2024, 17:35:09
    Author     : ADMIN
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product List</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <style>
            img{
                width: 200px;
            }
            .brief{
                height: 50px;
                overflow: hidden;
                text-overflow: ellipsis;
            }
        </style>
    </head>
    <body>
        <%@include file="menu-bar.jsp" %>
        <div class="container">
            <div>
                <h3 class="text-center" style="margin-top: 20px ">                            
                    List of Products
                </h3> 
            </div>
            <br><!-- a line to break content -->
            <div class="row">
                <div class="col-md-3">
                    <a class="btn btn-success" href="<%=request.getContextPath()%>/product-management?action=new">
                        Add New Product
                    </a> 
                </div>
                <div class="col-md-6">
                    <form action="account-management">
                        <input type="text" name="txtSearchValue" value="" />
                        <button type="submit" name="action" value="Search">Search</button>
                    </form>
                </div>

            </div>


            <br>        
            <div>        
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>Product ID</th>
                            <th>Product Name</th>
                            <th>Product Image</th>
                            <th>Brief</th>
                            <th>Posted Date</th>
                            <th>Category</th>
                            <th>Account</th>
                            <th>Unit</th>
                            <th>Price</th>
                            <th>Discount</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="product" items="${requestScope.listProducts}"> 
                            <tr>
                                <td><c:out value="${product.productId}"/></td>
                                <td><c:out value="${product.productName}"/></td>
                                <td><img src="${product.productImage}" alt="img"/></td>
                                <td><p class="brief"><c:out value="${product.brief}"/></p></td>
                                <td><c:out value="${product.postedDate}"/></td>
                                <td><c:out value="${product.type.categoryName}"/></td>
                                <td><c:out value="${product.account.account}"/></td>
                                <td><c:out value="${product.unit}"/></td>
                                <td><c:out value="${product.price}"/></td>
                                <td><c:out value="${product.discount}"/></td>
                                <td>
                                    <a class="btn btn-info" href="product-management?action=edit&productId=<c:out value='${product.productId}' />">Edit</a>
                                    <a class="btn btn-danger" href="product-management?action=delete&productId=<c:out value='${product.productId}' />">Delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>

