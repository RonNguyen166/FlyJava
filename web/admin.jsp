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
        <%@include file="./includes/adminHeader.jsp" %>
        <div class="container-fluid px-5">
            <br>
            <a href="<%= request.getContextPath()%>/admin/products/add"  class="btn btn-primary">Add Product</a>

            <div class = "card mt-3">
                <div class="card-body">
                    <c:if test="${productList!=null}">
                        <table class="table caption-top">
                            <caption>List of Product</caption>
                            <thead>
                                <tr>
                                    <th scope="col">ID</th>
                                    <th scope="col">Company</th>

                                    <th scope="col">Name</th>
                                    <th scope="col">Price</th>
                                    <th scope="col">Description</th>                                
                                    <th scope="col">Detail</th>

                                    <th scope="col">Amount</th>
                                    <th scope="col">Discount</th>
                                    <th scope="col">Color</th>
                                    <th scope="col">Size</th>
                                    <th scope="col">Image</th>
                                    <th scope="col" >Action</th>

                                </tr>
                            </thead>
                            <tbody>

                                <c:forEach var="item" items="${productList}">
                                    <!-- set up a link for each student -->
                                    <c:url var="tempLink" value="${request.getContextPath}/admin/products/edit">

                                        <c:param name="productId" value="${item.id}"></c:param>

                                    </c:url>
                                    <!-- set up a link to delete a student -->
                                    <c:url var="deleteLink" value="${request.getContextPath}/products/delete">

                                        <c:param name="productId" value="${item.id}"
                                                 ></c:param>

                                    </c:url>

                                    <tr>

                                        <td> ${item.id}</td>
                                        <td><img src="${item.image}" width="80"/></td>  
                                        <td> ${item.name}</td>
                                        <td> ${item.price}</td>
                                        <td> ${item.description}</td>                                
                                        <td> ${item.detail}</td>
                                        <td> ${item.amount}</td>
                                        <td> ${item.discount}</td>
                                        <td> ${item.color}</td>
                                        <td> ${item.size}</td>

                                        <td> ${item.company.name}</td>


                                        <td>
                                            <div class="d-flex">
                                                <a class="btn btn-info" href="${tempLink}">Update</a>
                                                <p class="mx-1"></p>
                                                <a class="btn btn-danger "href="${deleteLink}" onClick="if (!(confirm('sure?')))
                                                            return false">Delete</a> 
                                            </div>
                                        </td>

                                    </tr>

                                </c:forEach>


                            </tbody>
                        </table>
                    </c:if>
                    <c:if test="${userList!=null}">
                        <table class="table caption-top">
                            <caption>List of Users</caption>
                            <thead>
                                <tr>
                                    <th scope="col">ID</th>
                                    <th scope="col">Photo</th>

                                    <th scope="col">Name</th>
                                    <th scope="col">Email</th>
                                    <th scope="col">Dob</th>                                
                                    <th scope="col">Address</th>

                                    <th scope="col">Phone</th>
                                    <th scope="col">Gender</th>
                                    <th scope="col">Role</th>
                                    <th scope="col" >Action</th>

                                </tr>
                            </thead>
                            <tbody>

                                <c:forEach var="item" items="${userList}">
                                    <!-- set up a link for each student -->
                                    <c:url var="tempLink" value="${request.getContextPath}/admin/users/edit">

                                        <c:param name="userId" value="${item.id}"></c:param>

                                    </c:url>
                                    <!-- set up a link to delete a student -->
                                    <c:url var="deleteLink" value="${request.getContextPath}/users/delete">

                                        <c:param name="userId" value="${item.id}"
                                                 ></c:param>

                                    </c:url>

                                    <tr>

                                        <td> ${item.id}</td>
                                        <td><img src="${item.photo}" width="60"/></td>  
                                        <td> ${item.name}</td>
                                        <td> ${item.email}</td>
                                        <td> ${item.dob}</td>                                
                                        <td> ${item.address}</td>
                                        <td> ${item.phone}</td>
                                        <td> ${item.gender}</td>
                                        <td> ${item.role ? "admin": "user"}</td>

                                        <td>
                                            <div class="d-flex">
                                                <a class="btn btn-info" href="${tempLink}">Update</a>
                                                <p class="mx-1"></p>
                                                <a class="btn btn-danger "href="${deleteLink}" onClick="if (!(confirm('sure?')))
                                                            return false">Delete</a> 
                                            </div>
                                        </td>

                                    </tr>

                                </c:forEach>


                            </tbody>
                        </table>
                    </c:if>
                    <c:if test="${companyList!=null}">


                        <button type="button" class="btn btn-info" data-bs-toggle="modal" data-bs-target="#exampleModal" data-bs-whatever="@getbootstrap">New Company</button>

                        <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <form action="<%=request.getContextPath()%>/admin/company/add" method="POST">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="exampleModalLabel">New Company</h5>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                        </div>
                                        <div class="modal-body">

                                            <div class="mb-3">
                                                <label for="recipient-name" class="col-form-label">Name Company</label>
                                                <input type="text" class="form-control" id="recipient-name" name="name">
                                            </div>

                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                            <button type="submit" class="btn btn-primary">Submit</button>
                                        </div>
                                    </form>

                                </div>
                            </div>
                        </div>


                        <table class="table caption-top">
                            <caption>List of Users</caption>
                            <thead>
                                <tr>
                                    <th scope="col">ID</th>
                                    <th scope="col">Name</th>



                                </tr>
                            </thead>
                            <tbody>

                                <c:forEach var="item" items="${companyList}">
                                    <!-- set up a link for each student -->
                                    <c:url var="tempLink" value="${request.getContextPath}/admin/company/edit">

                                        <c:param name="companyId" value="${item.id}"></c:param>

                                    </c:url>
                                    <!-- set up a link to delete a student -->
                                    <c:url var="deleteLink" value="${request.getContextPath}/admin/company/delete">

                                        <c:param name="companyId" value="${item.id}"
                                                 ></c:param>

                                    </c:url>

                                    <tr>

                                        <td> ${item.id}</td>
                                        <td> ${item.name}</td>

                                        <td>
                                            <div class="d-flex">
                                                <a class="btn btn-info" href="${tempLink}">Update</a>
                                                <p class="mx-1"></p>
                                                <a class="btn btn-danger "href="${deleteLink}" onClick="if (!(confirm('sure?')))
                                                            return false">Delete</a> 
                                            </div>
                                        </td>

                                    </tr>

                                </c:forEach>


                            </tbody>
                        </table>
                    </c:if>
                </div>
            </div>


            <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>



    </body>
</html>
