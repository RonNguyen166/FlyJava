<%-- 
    Document   : adminHeader
    Created on : Jul 1, 2022, 2:22:43 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="header">

    <header class="p-3 py-4 bg-dark text-white">
        <div class="container">
            <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
                <a href="/" class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none h4">
                    FLYTEAM
                </a>

                <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                    <li class="nav-item">
                    <li><a href="<%= request.getContextPath()%>/" class="nav-link px-2 text-secondary">Home</a></li>
                    <li><a href="<%= request.getContextPath()%>/admin" class="nav-link px-2 text-white">Products</a></li>
                    <li><a href="<%= request.getContextPath()%>/admin/users" class="nav-link px-2 text-white">Users</a></li>
                    <li><a href="<%= request.getContextPath()%>/admin/orders" class="nav-link px-2 text-white">Orders</a></li>

                </ul>

                <form class="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3" role="search">
                    <input type="search" class="form-control form-control-dark text-white bg-dark" placeholder="Search..." aria-label="Search">
                </form>

                <div class="text-end">
                    <div class="dropdown text-end">
                        <a href="#" class="d-block text-white text-decoration-none dropdown-toggle" id="dropdownUser1" data-bs-toggle="dropdown" aria-expanded="false">


                            <img src="https://www.nicepng.com/png/full/136-1366211_group-of-10-guys-login-user-icon-png.png" alt="mdo" width="32" height="32" class="rounded-circle">
                        </a>
                        <ul class="dropdown-menu text-small" aria-labelledby="dropdownUser1">
                            <li><a class="dropdown-item" href="#">New project...</a></li>
                            <li><a class="dropdown-item" href="#">Settings</a></li>
                            <li><a class="dropdown-item" href="#">Profile</a></li>
                            <li><hr class="dropdown-divider"></li>
                            <li><a class="dropdown-item" href="<%= request.getContextPath()%>/users/logout">Sign out</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </header>
</div>