<%-- 
    Document   : admin
    Created on : Jun 30, 2022, 10:32:12 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                            <li class="nav-item">
                                <a class="nav-link">Hello ${userLogin.name}</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link">logout</a>
                            </li>
                        </form>
                        
                    </div>
                </div>


            </nav>
        </div>
        <div class="container">
            <br>
            <a href="products/add"  class="btn btn-primary">Add Product</a>

            <div class = "card mt-3">
                <div class="card-body">
                    <table class="table caption-top">
                        <caption>List of Product</caption>
                        <thead>
                            <tr>
                                <th scope="col">ID</th>
                                <th scope="col">Name</th>
                                <th scope="col">Price</th>
                                <th scope="col">Description</th>                                
                                <th scope="col">Detail</th>

                                <th scope="col">Amount</th>
                                <th scope="col">Discount</th>
                                <th scope="col">Color</th>
                                <th scope="col">Size</th>
                                <th scope="col">Image</th>
                                <th scope="col">Company</th>
                                <th scope="col" >Action</th>

                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="item" items="${productList}">
                            <!-- set up a link for each student -->
                            <c:url var="tempLink" value="StudentController">
                                <c:param name="command" value="LOAD"></c:param>
                                <c:param name="studentId" value="${item.id}"></c:param>

                            </c:url>
                            <!-- set up a link to delete a student -->
                            <c:url var="deleteLink" value="StudentController">
                                <c:param name="command" value="DELETE"
                                         ></c:param>
                                <c:param name="studentId" value="${item.id}"
                                         ></c:param>

                            </c:url>

                            <tr>
                                <td> ${item.name}</td>
                                <td> ${item.price}</td>
                                <td> ${item.description}</td>                                
                                <td> ${item.detail}</td>

                                <td> ${item.detail}</td>
                                <td> ${item.amount}</td>
                                <td> ${item.discount}</td>
                                <td> ${item.color}</td>
                                <td> ${item.size}</td>
                                <td> ${item.image}</td>                                
                                <td> ${item.company}</td>


                                <td> <a class="btn btn-info" href="${tempLink}">Update</a>
                                    <a  class="btn btn-danger"href="${deleteLink}" onClick="if (!(confirm('sure?')))return false">Delete</a>

                            </tr>

                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>


            <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>



    </body>
</html>
