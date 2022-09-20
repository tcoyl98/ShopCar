<%-- 
    Document   : update
    Created on : Jul 15, 2022, 8:52:27 PM
    Author     : looby
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order Page</title>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <div><a class="btn btn-primary" role="button" href="./">Back to main</a></div>
        <div class="alert alert-success alert-dismissible fade show">
            <strong>Success!</strong> Your order has been sent successfully. We will contact you soon.
        </div>
    </body>
</html>
