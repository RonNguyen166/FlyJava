<%-- 
    Document   : admin
    Created on : Jun 30, 2022, 10:32:12 PM
    Author     : DELL
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    </head>
    <body>
        <div class="header">

            <nav class="navbar navbar-expand-lg navbar-dark bg-dark">

                <div class="container py-3">
                    <a class="navbar-brand mb-0 h1" href="#">MANAGER</a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                            <li class="nav-item">
                                <a class="nav-link " href="home">Home</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link " href="#">Products</a>
                            </li>

                            <li class="nav-item">
                                <a class="nav-link">Users</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link">Orders</a>
                            </li>
                        </ul>
                        <form class="d-flex">
                            <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                            <button class="btn btn-outline-success" type="submit">Search</button>

                        </form>
                        <li class="nav-item">
                            <a class="nav-link">Hello ${userLogin.name}</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link">logout</a>
                        </li>
                    </div>
                </div>


            </nav>
        </div>
        <div class="container">
            <br>


            <form action="add" method="POST">
                <div class="row">
                    <div class="col-md-6">
                        <div class="mb-3">
                            <label class="form-label">Name</label>
                            <input type="text" class="form-control" required name="name">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Price</label>
                            <input type="number" class="form-control" required name="price">
                        </div>
                        <div class="mb-3">
                            <label class="form-label"> Description</label>
                            <textarea class="form-control" required cols="10" rows="4" name="description"></textarea>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Detail</label>
                            <textarea class="form-control" required cols="10" rows="5" name="detail"></textarea>
                        </div>
                    </div>
                    <div class="col-md-6">
                         <div class="mb-3">
                            <label class="form-label">Amount</label>
                            <input type="number" class="form-control" required name="amount">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Discount</label>
                            <input type="number" class="form-control" required name="discount"> 
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Color</label>
                            <input type="text" class="form-control" required name="color">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Size</label>
                            <input type="number" class="form-control" required name="size">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Image</label>
                            <input type="text" class="form-control" required name="image">
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Company</label>
                            <select class="form-control" name="company">
                                <c:forEach var="op" items="${companyList}">
                                    <option value="${op.id}">${op.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>
                
                <div class="text-center mt-2"><button type="submit" class="btn btn-primary px-5 py-2">Submit</button></div>
            </form>


            <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>



    </body>
</html>
