<%-- 
    Document   : allproducts
    Created on : Jul 14, 2022, 10:52:46 PM
    Author     : looby
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="ct" uri="/WEB-INF/tlds/tag_library.tld" %>
<html>

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <head>
        <title>G6 Car</title>
    </head>
    <body>

        <jsp:include page="header.jsp"/>
        <div class="banner">
            <div class="intro">
                <a class="intro-text">You need us. We serve you.</a></div>
        </div>

        <div id="page">
            <div id="menu">
                <div class="list-group">

                    <a class="list-group-item list-group-item-primary">BRAND</a>
                    <a href="MainController?op=SearchByBrand&name=BMW" class="list-group-item list-group-item-action">BMW</a>
                    <a href="MainController?op=SearchByBrand&name=Audi" class="list-group-item list-group-item-action">Audi</a>
                    <a href="MainController?op=SearchByBrand&name=Ford" class="list-group-item list-group-item-action">Ford</a>
                    <a href="MainController?op=SearchByBrand&name=Nissan" class="list-group-item list-group-item-action">Nissan</a>
                    <a class="list-group-item list-group-item-primary">PRICE</a>
                    <a href="MainController?op=SearchByPrice&pricefrom=0&priceto=1000000000" class="list-group-item list-group-item-action ">Under 1.000.000.000 VND</a>
                    <a href="MainController?op=SearchByPrice&pricefrom=1000000000&priceto=2000000001" class="list-group-item list-group-item-action ">1.000.000.000 - 2.000.000.000 VND</a>
                    <a href="MainController?op=SearchByPrice&pricefrom=2000000001&priceto=100000000000" class="list-group-item list-group-item-action ">Over 2.000.000.000 VND</a>
                    <a class="list-group-item list-group-item-primary">TYPE</a>
                    <a href="MainController?op=SearchByType&type=Sedan" class="list-group-item list-group-item-action ">Sedan</a>
                    <a href="MainController?op=SearchByType&type=SUV" class="list-group-item list-group-item-action ">SUV</a>
                    <a href="MainController?op=SearchByType&type=other" class="list-group-item list-group-item-action ">Other</a>


                </div>
            </div>
            <div id="content">

                <form class="form-inline" action="MainController?op=SearchByName" method="post">
                    <label class="sr-only" for="inlineFormInputName2">Name</label>
                    <input type="text" name="name" value="${searchname}" class="form-control mb-2 mr-sm-2" id="inlineFormInputName2" placeholder="Input car name">
                    <button type="submit" class="btn btn-primary mb-2">Search</button>
                </form>
                <span>Number of Product: ${listAllProduct.size()}</span>
                <c:if test="${not empty user}">
                    <div style="text-align: left" id="admin-tool">
                        <a class="btn btn-primary" style="width: 150px"  role="button" href="MainController?op=Create">Add New Car</a>
                        <a class="btn btn-primary" style="width: 150px" role="button" href="MainController?op=ViewOrders">View Orders</a>
                    </div>
                </c:if>
                <c:forEach items="${list}" var="item">
                    <div id="item">
                        <div id="image"><img style="height: 250px;width: 100%" src="${item.imagePath}"/></div>
                        <div id="description">
                            <div><h1>${item.name}</h1></div>
                            <div>${item.description}</div>
                            <div><a style="background-color: #8cdaff" class="btn-primary">Brand: ${item.brand}</a>|<a class="btn-primary">Type: ${item.type}</a>|<a style="background-color: #8cdaff" class="btn-primary">Color: ${item.color}</a>|<a class="btn-primary">Price: ${item.price}</a></div>
                            <c:if test="${not empty user}">
                                <div><a class="btn btn-primary" style="width: 150px"  role="button" href="MainController?op=Update&id=${item.id}">Update</a></div>
                            </c:if>
                        </div>
                        <c:if test="${empty user}">
                            <div id="choice-button"><a href="MainController?op=Order&id=${item.id}" class="btn btn-primary" role="button" style="color: black;font-size: 15;padding-top: 150%;height: 250px; width: 70px"><i class="fa fa-shopping-cart" style="color: black"></i></a></div>
                                </c:if>
                                <c:if test="${not empty user}">
                            <div id="choice-button"><a class="btn btn-primary" href="MainController?op=Delete&id=${item.id}" role="button" style="color: black;font-size: 15;padding-top: 150%;height: 250px;">Delete</a></div>
                        </c:if>
                    </div>
                </c:forEach>
                <c:if test="${listAllProduct.size()>5}">
                <ct:PagerTagHandler pindex="${index}" productSize="${size}" page="${page}"/>
                </c:if>
            </div>
            <div class="footer">
                <a style="font-weight: 900">MEMBER LIST:</a><br/>
                Nguyễn Quốc Đạt | Đinh Hữu Khang | Nguyễn Thành Lộc | Lâm Tấn Lợi | Nguyễn Hữu Thọ 
            </div>
        </div>
    </body>
</html>