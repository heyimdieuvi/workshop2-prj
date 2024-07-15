<%-- 
    Document   : product-list
    Created on : 23 Jun 2024, 17:35:09
    Author     : ADMIN
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags/" prefix="myLib" %>
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
                            <th>No</th>
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
                    <myLib:myDataGrid dataSource="DBCon"
                                      sql="SELECT * FROM products"
                                      action1="product-management?action=edit&productId="
                                      action2="product-management?action=delete&productId="/>
                </table>
            </div>
        </div>
    </body>
</html>

