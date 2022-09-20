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
        <title>Admin Page</title>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <div style=" height: 700px" id="content">
            <h1>All Orders</h1>
            <hr/>
             <a class="btn btn-primary" role="button" href="./">Back to main</a>
            <table border="1">
                <thead>
                    <tr>
                        <th style="width: 5%">ID</th>
                        <th style="width: 20%">CarID</th>
                        <th style="width: 35%">CustomerName</th>
                        <th style="width: 25%">CustomerPhone</th>
                        <th>CustomerAddress</th>
                    </tr>
                </thead>
                <c:forEach items="${listOrder}" var="item" varStatus="loop">
                    <tbody>
                    <tr>
                        <td>${item.id}</td>
                        <td>${item.carid}</td>
                        <td>${item.customerName}</td>
                        <td>${item.customerPhone}</td>
                        <td>${item.customerAddress}</td>
                    </tr>
                </tbody>
                </c:forEach>
            </table>

        </div>
    </body>
</html>
