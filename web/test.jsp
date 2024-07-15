<%-- 
    Document   : test
    Created on : 15-Jul-2024, 21:13:39
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags/" prefix="myLib" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <myLib:myDataGrid dataSource="DBCon"
                          sql="SELECT * FROM accounts"
                          action1="account-management?action=edit&accName="
                          action2="account-management?action=delete&accName="/>
    </body>
</html>
