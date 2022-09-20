<%-- 
    Document   : update
    Created on : Jul 15, 2022, 8:52:27 PM
    Author     : looby
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order Page</title>
    </head>
    <body>
       <jsp:include page="header.jsp"/>
       <div style=" height: 700px" id="content">
            <a class="btn btn-primary" role="button" href="./">Back to main</a>
            <div style="width: 100%" >
                <div>
                    <h1>Your order:</h1>
                    Car name: ${car.name}<br/>
                    Car color: ${car.color}<br/>
                    Brand: ${car.brand}<br/>
                    Price: ${car.price}
                </div>
                <form action="MainController?op=submitOrder" method="post">
                    <input type="hidden" name="carid" value="${car.id}"/>
                    <div class="form-group">
                       
                        <label for="exampleFormControlInput1">Your Name:</label>
                        <input type="text" name="name" value="${name}" class="form-control" id="exampleFormControlInput1" placeholder="Your name">
                        <a style="color: red"> ${errorname}</a>
                    </div>
                    <div class="form-group">
                        <label for="exampleFormControlInput1">Your Phone</label>
                        <input type="text" name="phone" value="${phone}" class="form-control" id="exampleFormControlInput1" placeholder="Phone number">
                        <a style="color: red"> ${errorphone}</a>
                    </div>
                    <div class="form-group">
                        <label for="exampleFormControlInput1">Your address:</label>
                        <input type="text" name="address" value="${address}" class="form-control" id="exampleFormControlInput1" placeholder="Address">
                        <a style="color: red"> ${erroraddress}</a>
                    </div>
                    <button type="submit" class="btn btn-primary mb-2">Order</button>
                </form>
            </div>
        </div>
    </body>
</html>
