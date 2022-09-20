<%-- 
    Document   : header
    Created on : Jul 14, 2022, 10:47:26 PM
    Author     : looby
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style><%@include file="/WEB-INF/css/main.css"%></style>
<!DOCTYPE html>
<html>

    <body>
        <div class="header">
            <div class="logo"><a href="./"><img src="images/logo.png"/></a></div>
            <div class="right-logo">
                <c:if test="${not empty user}"><a>Hi, ${user.name} </a><a class="btn btn-primary"  role="button" href="MainController?op=Logout">Logout</a></c:if>
                <c:if test="${empty user}" var="loginUser"><a class="btn btn-primary" role="button" href="MainController?op=adminLogin">Login</a></c:if>
            </div>

        </div>
    </body>
</html>
