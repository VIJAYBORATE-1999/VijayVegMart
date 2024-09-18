<%@page import="com.vijay.model.Users"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <title>Vijay Veg Mart</title>
         <%@include file="components/links.jsp" %>
        <%@include file="components/jslibraries.jsp" %>
        <link href="css/style.css" rel="stylesheet">
    </head>
    <body>

<%@include file="components/navbar.jsp" %>



  <!-- Content Section Start -->
    <div class="container mt-5 pt-5 content">
    
    <%
    Users user = (Users) session.getAttribute("user");
    if (user != null) {
%>
        Welcome, <%= user.getUsername() %>!<br>
        Your email: <%= user.getEmail() %>
<%
    } else {
%>
        Please <a href="login.jsp">login</a> first.
<%
    }
%>
    
        <h2 class="text-center mb-4">Vegetables List</h2>
        <div class="row">
            <!-- Product 1 -->
            <div class="col-md-4 col-12 mb-4">
                <div class="card">
                    <img src="img/Tomato-red.jpg" class="card-img-top" alt="Image of Product 1">
                    <div class="card-body">
                        <h5 class="card-title">Tomato</h5>
                        <p class="card-text">Fresh Tomato</p>
                        <div class="row col-12">
                        <a href="#" class="offset-1 btn btn-primary btn-sm col-3 ">View Details</a>
                        <a href="#" class="btn btn-primary btn-sm col-3  offset-1 ml-1"><i class="fa fa-shopping-cart fa-1x"></i> Add Cart</a>
						<a href="#" class="btn btn-primary btn-sm col-3 offset-1 ">Price $</a>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Product 2 -->
            <div class="col-md-4 col-12 mb-4">
                <div class="card">
                    <img src="img/Broccoli.jpg" class="card-img-top" alt="Image of Product 2">
                    <div class="card-body">
                        <h5 class="card-title">Broccoli</h5>
                        <p class="card-text">Best Quality Broccoli</p>
                        <div class="row col-12">
                        <a href="#" class="offset-1 btn btn-primary btn-sm col-3 ">View Details</a>
                        <a href="#" class="btn btn-primary btn-sm col-3  offset-1 ml-1"><i class="fa fa-shopping-cart fa-1x"></i> Add Cart</a>
						<a href="#" class="btn btn-primary btn-sm col-3 offset-1 ">Price $</a>
                        </div>                    
                       
                       
                       
                    </div>
                </div>
            </div>
            <!-- Product 3 -->
            <div class="col-md-4 col-12 mb-4">
                <div class="card">
                    <img src="img/capsicum-red.jpg" class="card-img-top" alt="Image of Product 3">
                    <div class="card-body">
                        <h5 class="card-title">Capsicum(Red)</h5>
                        <p class="card-text">Best Quality Capsicum</p>
                        <div class="row col-12">
                        <a href="#" class="offset-1 btn btn-primary btn-sm col-3 ">View Details</a>
                        <a href="#" class="btn btn-primary btn-sm col-3  offset-1 ml-1"><i class="fa fa-shopping-cart fa-1x"></i> Add Cart</a>
						<a href="#" class="btn btn-primary btn-sm col-3 offset-1 ">Price $</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Content Section End -->

<%@include file="components/footer.jsp" %>

 </body>

</html>