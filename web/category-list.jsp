<%-- 
    Document   : category-list
    Created on : 23 Jun 2024, 18:19:27
    Author     : ADMIN
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags/" prefix="myLib" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Category List</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <%@include file="menu-bar.jsp" %>
        <div class="container">
            <div>
                <h3 class="text-center" style="margin-top: 20px ">                            
                    List of Categories
                </h3> 
                <hr><!-- a line to break content -->
                <a class="btn btn-success" href="<%=request.getContextPath()%>/category-management?action=new">
                    Add New Category
                </a>
            </div>
            <br>        
            <div>        
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>No</th>
                            <th>Type ID</th>
                            <th>Category Name</th>
                            <th>Memo</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <myLib:myDataGrid dataSource="DBCon"
                                      sql="SELECT * FROM categories"
                                      action1="category-management?action=edit&typeId="
                                      action2="category-management?action=delete&typeId="/>

                </table>
            </div>
        </div>
    </body>
</html>

