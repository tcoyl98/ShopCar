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
        <title>Admin Page</title>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <div style=" height: 700px" id="content">
            <a class="btn btn-primary" role="button" href="./">Back to main</a>
            <hr/>
            <div style="width: 100%" >
                <form action="MainController?op=SubmitUpdate" method="post">
                    <div class="form-group">
                        <label for="exampleFormControlInput1">Car ID</label>
                        <input type="text" name="id" value="${car.id}" readonly="True" class="form-control" id="exampleFormControlInput1" placeholder="">
                    </div>
                    <div class="form-group">
                        <label for="exampleFormControlInput1">Car Name</label>
                        <input type="text" name="name" value="${car.name}" class="form-control" id="exampleFormControlInput1" placeholder="">

                    </div>
                    <div class="form-group">
                        <label for="exampleFormControlInput1">Car Price</label>
                        <input type="text" name="price" value="${car.price}" class="form-control" id="exampleFormControlInput1" placeholder="">

                    </div>
                    <div class="form-group">
                        <label for="exampleFormControlSelect1">Brand</label>
                        <select name="brand"  class="form-control" id="exampleFormControlSelect1">
                            <option ${car.brand=='BMW'?"selected":""}>BMW</option>
                            <option ${car.brand=='Missan'?"selected":""}>Nissan</option>
                            <option ${car.brand=='Audi'?"selected":""}>Audi</option>
                            <option ${car.brand=='Ford'?"selected":""}>Ford</option>
                        </select>
                    </div>
                     <div class="form-group">
                        <label for="exampleFormControlInput1">Car Color</label>
                        <input type="text" name="color" value="${car.color}" class="form-control" id="exampleFormControlInput1" placeholder="">

                    </div>
                    
                     <div class="form-group">
                        <label for="exampleFormControlInput1">Car Description</label>
                        <input type="text" name="description" value="${car.description}" class="form-control" id="exampleFormControlInput1" placeholder="">

                    </div>
                    <div class="form-group">
                        <label for="exampleFormControlInput1">Car Type</label>
                        <input type="text" name="type" value="${car.type}" class="form-control" id="exampleFormControlInput1" placeholder="">

                    </div>
                     <div class="form-group">
                        <label for="exampleFormControlInput1">Car ImagePath</label>
                        <input type="text" name="imagepath" value="${car.imagePath}" class="form-control" id="exampleFormControlInput1" placeholder="">

                    </div>
                    
                    <button type="submit" class="btn btn-primary mb-2">Update</button>
                </form>
            </div>
        </div>
    </body>
</html>
