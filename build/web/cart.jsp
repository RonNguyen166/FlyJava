<%-- 
    Document   : cart
    Created on : Jun 30, 2022, 11:36:11 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/font-awesome.min.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
        <link rel="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>
        <link type="text/css" rel="stylesheet" href="css/bootstrap.min.css"/>

        <!-- Slick -->
        <link type="text/css" rel="stylesheet" href="css/slick.css"/>
        <link type="text/css" rel="stylesheet" href="css/slick-theme.css"/>

        <!-- nouislider -->
        <link type="text/css" rel="stylesheet" href="css/nouislider.min.css"/>

        <!-- Font Awesome Icon -->


        <!-- Custom stlylesheet -->
        <link type="text/css" rel="stylesheet" href="css/style.css"/>
        <style>

            .cart .title{
                margin-bottom: 5vh;
            }
            .cart .card{
                margin: auto;
                max-width: 950px;
                width: 90%;
                box-shadow: 0 6px 20px 0 rgba(0, 0, 0, 0.19);
                border-radius: 1rem;
                border: transparent;
            }

            .cart .cart{
                background-color: #fff;
                padding: 4vh 5vh;
                border-bottom-left-radius: 1rem;
                border-top-left-radius: 1rem;
            }

            .cart .summary{
                background-color: #ddd;
                border-top-right-radius: 1rem;
                border-bottom-right-radius: 1rem;
                padding: 4vh;
                color: rgb(65, 65, 65);
            }

            .cart .summary .col-2{
                padding: 0;
            }
            .cart .summary .col-10
            {
                padding: 0;
            }
            .cart .row{
                margin: 0;
            }
            .cart .title b{
                font-size: 1.5rem;
            }
            .cart .main{
                margin: 0;
                padding: 2vh 0;
                width: 100%;
            }
            .cart .col-2, .cart .col{
                padding: 0 1vh;
            }
            .cart a{
                padding: 0 1vh;
            }
            .cart .close{
                margin-left: auto;
                font-size: 1.7rem;
            }
            .cart img{
                width: 7rem;
            }
            .cart .back-to-shop{
                margin-top: 4.5rem;
            }
            .cart h5{
                margin-top: 4vh;
            }
            .cart hr{
                margin-top: 1.25rem;
            }
            .cart form{
                padding: 2vh 0;
            }
            .cart select{
                border: 1px solid rgba(0, 0, 0, 0.137);
                padding: 1.5vh 1vh;
                margin-bottom: 4vh;
                outline: none;
                width: 100%;
                background-color: rgb(247, 247, 247);
            }
            .cart input{
                border: 1px solid rgba(0, 0, 0, 0.137);
                padding: 1vh;
                margin-bottom: 4vh;
                outline: none;
                width: 100%;
                background-color: rgb(247, 247, 247);
            }
            .cart input:focus::-webkit-input-placeholder
            {
                color:transparent;
            }
            .cart .btn{
                background-color: #000;
                border-color: #000;
                color: white;
                width: 100%;
                font-size: 0.7rem;
                margin-top: 4vh;
                padding: 1vh;
                border-radius: 0;
            }
            .cart    .btn:focus{
                box-shadow: none;
                outline: none;
                box-shadow: none;
                color: white;
                -webkit-box-shadow: none;
                -webkit-user-select: none;
                transition: none;
            }
            .cart .btn:hover{
                color: white;
            }
            .cart a{
                color: black;
            }
            .cart a:hover{
                color: black;
                text-decoration: none;
            }
            .cart #code{
                background-image: linear-gradient(to left, rgba(255, 255, 255, 0.253) , rgba(255, 255, 255, 0.185)), url("https://img.icons8.com/small/16/000000/long-arrow-right.png");
                background-repeat: no-repeat;
                background-position-x: 95%;
                background-position-y: center;
            }


        </style>
    </head>
    <body>
        <%@include file="./includes/header.jsp" %>
        <div class="cart mt-5">
            <div class="card">
                <div class="row">
                    <div class="col-md-8 cart">
                        <div class="title">
                            <div class="row">
                                <div class="col"><h4><b>Shopping Cart</b></h4></div>
                                <div class="col align-self-center text-right text-muted">${cartList.size()} items</div>
                            </div>
                        </div>    
                        <c:forEach var="item" items="${cartList}">
                            <div class="row border-top border-bottom">
                                <div class="row main align-items-center">
                                    <div class="col-2"><img class="img-fluid" src="${item.product.image}"></div>
                                    <div class="col">
                                        <div class="row text-muted">${item.product.company.name}</div>
                                        <div class="row">${item.product.name}</div>
                                    </div>
                                    <div class="col">
                                        <a href="#">-</a><a href="#" class="border">1</a><a href="#">+</a>
                                    </div>
                                    <c:url var="deleteLink" value="${request.getContextPath}/cart/delete">

                                        <c:param name="cartId" value="${item.id}"
                                                 ></c:param>

                                    </c:url>
                                    <div class="col">${item.product.price} VND<span class="close"><a href="${deleteLink}">&#10005;</span></a></div>
                                </div>
                            </div>
                        </c:forEach>

                        <div class="back-to-shop"><a href="#">&leftarrow;</a><span class="text-muted">Back to shop</span></div>
                    </div>
                    <div class="col-md-4 summary">
                        <form action="<%= request.getContextPath()%>/cart/checkout" method="POST">
                            <div><h5><b>Summary</b></h5></div>
                            <hr>
                            <div class="row">
                                <div class="col" style="padding-left:0;">ITEMS 3</div>
                                <div class="col text-right">${total} VND</div>
                            </div>

                            <p>Hinh Thuc thanh toan</p>
                            <select name="paymentId">
                                <c:forEach var="item" items="${payList}">
                                    <option class="text-muted" value="${item.paymentId}">${item.paymentName}</option>
                                </c:forEach>

                            </select>
                            <p>GIVE CODE</p>
                            <input id="code" placeholder="Enter your code">


                            <div class="row" style="border-top: 1px solid rgba(0,0,0,.1); padding: 2vh 0;">
                                <div class="col">TOTAL PRICE</div>
                                <div class="col text-right">${total} VND</div>
                            </div>
                            <button type="submit" class="btn">CHECKOUT</button>
                        </form>
                    </div>
                </div>

            </div>
        </div>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
        <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/slick.min.js"></script>
        <script src="js/nouislider.min.js"></script>
        <script src="js/jquery.zoom.min.js"></script>
        <script src="js/main.js"></script>

    </body>
</html>
