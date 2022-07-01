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
        <%@include file="./includes/adminHeader.jsp" %>
        <div class="container">
            <br>


            <form action="<%= request.getContextPath()%>/products/updateProduct" method="POST">
                <div class="row">
                    <div class="col-md-6">
                        <input type="hidden" value="${productEdit.id}" name="productId">
                        <div class="mb-3">
                            <label class="form-label">Name</label>
                            <input type="text" class="form-control" required name="name" value="${productEdit.name}">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Price</label>
                            <input type="number" class="form-control" required name="price" value="${productEdit.price}">
                        </div>
                        <div class="mb-3">
                            <label class="form-label"> Description</label>
                            <textarea class="form-control" required cols="10" rows="4" name="description" >${productEdit.description}</textarea>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Detail</label>
                            <textarea class="form-control" required cols="10" rows="5" name="detail">${productEdit.detail}</textarea>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="mb-3">
                            <label class="form-label">Amount</label>
                            <input type="number" class="form-control" required name="amount" value="${productEdit.amount}">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Discount</label>
                            <input type="number" class="form-control" required name="discount" value="${productEdit.discount}" min="0" max="100"> 
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Color</label>
                            <input type="text" class="form-control" required name="color" value="${productEdit.color}">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Size</label>
                            <input type="number" class="form-control" required name="size" value="${productEdit.size}">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Image</label>
                            <input type="text" class="form-control" required name="image" value="${productEdit.image}">
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Company</label>
                            <select class="form-control" name="company">
                                <c:forEach var="op" items="${companyList}">
                                  
                                    <c:if test="${productEdit.company.id == op.id}">
                                        <option value="${op.id}" selected>${op.name}</option>
                                    </c:if>
                                    <c:if test="${productEdit.company.id != op.id}">
                                        <option value="${op.id}">${op.name}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>

                <div class="text-center mt-2"><button type="submit" class="btn btn-primary px-5 py-2">Submit</button></div>
                 <div><a href="<%=request.getContextPath() %>/admin" class="nav-link text-secondary h6"><--  back</a></div>
            </form>


            <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>



    </body>
</html>
