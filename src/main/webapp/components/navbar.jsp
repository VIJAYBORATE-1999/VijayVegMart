<%@page import="com.yash.vijayvegmart.model.Users"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- Navbar start -->
<div class="container-fluid fixed-top">
    <div class="px-0">
        <nav class="navbar navbar-light bg-white navbar-expand-xl">
            <a href="home.jsp" class="navbar-brand">
                <img src="${pageContext.request.contextPath}/img/V_MartLogoNew2.png" alt="${pageContext.request.contextPath}/img/V_MartLogoNew2.png">
            </a>
            <button class="navbar-toggler py-2 px-3" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
                <span class="fa fa-bars" style="color: black !important;"></span>
            </button>
            <div class="collapse navbar-collapse bg-white" id="navbarCollapse">
                <div class="navbar-nav mx-auto">
                    <a href="home.jsp" class="nav-item nav-link active" aria-current="page"><i class="fa fa-home fa-2x fa-icon-colour"></i></a>

                    <!-- Login AND REGISTER  VISIBLE ONLY WHEN USER IS NOT LOGGED IN   -->
                    <%
                        Users user_1 = (Users) session.getAttribute("user");
                        if (user_1 == null) {
                    %>
                    <a href="login.jsp" class="nav-item nav-link fa-icon-colour">Login</a>
                    <a href="register.jsp" class="nav-item nav-link fa-icon-colour">Register</a>
                    <%
                        } else if (user_1.getUsertype().equals("customer")) {
                    %>  
                    <a href="cart.jsp" class="position-relative me-4 my-auto" aria-label="Shopping Cart">
                        <i class="fa fa-shopping-cart fa-2x fa-icon-colour"></i>
                    </a>
                    <a href="myorders.jsp" class="nav-item nav-link fa-icon-colour">My Orders</a>
                    
                    <!-- Profile Icon with Hover Effect -->
                    <div class="nav-item dropdown">
                        <a href="#" class="nav-link position-relative me-4 my-auto" id="profileDropdown" aria-label="Profile" role="button" data-bs-toggle="dropdown">
                            <i class="fas fa-user-circle fa-2x fa-icon-colour"></i>
                        </a>
                        <div class="dropdown-menu dropdown-menu-end p-3" aria-labelledby="profileDropdown" style="min-width: 200px;">
                            <p><strong>Username :</strong><%= user_1.getUsername()  %></p>
                            <p><strong>Email :</strong> <%= user_1.getEmail() %></p>
                        </div>
                    </div>
                    <%
                        }
                    %>

                    <a href="about.jsp" class="nav-item nav-link fa-icon-colour">About</a>
                </div>
                <div class="d-flex m-3 me-0">
                    <a href="search.jsp" class="position-relative me-4 my-auto" aria-label="Shopping Cart">
                        <i class="fas fa-search fa-icon-colour fa-2x"></i>
                    </a>
                    <a href="${pageContext.request.contextPath}/logout" class="my-auto" aria-label="Log Out">
                        <i class="fas fa-power-off fa-2x danger-colour"></i>
                    </a>
                </div>
            </div>
        </nav>
    </div>
</div>
<!-- Navbar End -->
