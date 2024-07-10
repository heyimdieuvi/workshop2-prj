<%-- 
    Document   : login
    Created on : 18 Jun 2024, 23:32:27
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html lang="en">

    <head>
        <title>Login V1</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!--===============================================================================================-->	
        <link rel="icon" type="image/png" href="assets/images/icons/favicon.ico"/>
        <!--===============================================================================================-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

        <link rel="stylesheet" type="text/css" href="assets/css/util.css">
        <link rel="stylesheet" type="text/css" href="assets/css/main.css">
        <!--===============================================================================================-->
    </head>
    <body>

        <div class="limiter">
            <div class="container-login100">
                <div class="wrap-login100">
                    <div class="login100-pic js-tilt" data-tilt>
                        <img src="assets/images/img-01.png" alt="IMG"/>
                    </div>

                    <form class="login100-form validate-form" action="main" method="post">
                        <span class="login100-form-title">
                            Member Login
                        </span>
                        <div class="wrap-input100 validate-input" data-validate = "Valid email is required: ex@abc.xyz">
                            <input class="input100" type="text" name="account" placeholder="Account" required="">
                            <span class="focus-input100"></span>
                            <span class="symbol-input100">
                                <i class="fa fa-envelope" aria-hidden="true"></i>
                            </span>
                        </div>

                        <div class="wrap-input100 validate-input" data-validate = "Password is required">
                            <input class="input100" type="password" name="password" placeholder="Password" required="">
                            <span class="focus-input100"></span>
                            <span class="symbol-input100">
                                <i class="fa fa-lock" aria-hidden="true"></i>
                            </span>
                        </div>
                        <div class="text-center m-t-10 m-b-10">
                            <strong><c:out value="${message}"/></strong>

                        </div>
                        <div class="container-login100-form-btn">
                            <button type="submit" name="action" value="login" class="login100-form-btn">
                                Login
                            </button>
                        </div>

                        <div class="text-center p-t-12 p-b-140">
                            <input class="form-check-input" type="checkbox" value="" id="flexCheckChecked" checked>
                            <label class="form-check-label" for="flexCheckChecked">
                                Remember me
                            </label>
                        </div>

                    </form>
                </div>
            </div>
        </div>
                            
    </body>
</html>