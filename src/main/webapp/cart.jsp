
<%@page import="com.yash.vijayvegmart.model.VegetablesDetails"%>
<%@page import="com.yash.vijayvegmart.serviceImpl.VegetablesServiceImpl"%>
<%@page import="com.yash.vijayvegmart.model.Carts"%>
<%@page import="java.util.List"%>
<%@page import="com.yash.vijayvegmart.serviceImpl.CartsServiceImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Vijay Veg Mart</title>
    <%@include file="/components/links.jsp" %>
    <script src="${pageContext.request.contextPath}/js/main.js"></script>
    <script src="${pageContext.request.contextPath}/js/cart.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/cart.css" type="text/css">
</head>

<body>
<%@include file="../components/navbar.jsp" %>
<br><br><br><br>

<div class="container3 mt-5">
    <h1 class="text-center">Your Vegetable Cart</h1>
    <div id="cart-items">
        <!-- Dynamically rendered items -->
        <%
        Users user = (Users) session.getAttribute("user");
        CartsServiceImpl cartServiceImpl = new CartsServiceImpl();
        VegetablesServiceImpl vegServiceImpl = new VegetablesServiceImpl();
        List<Carts> list = cartServiceImpl.fetchAllCartsByUserID(user.getId());
					double cart_total = 0;
        for (Carts cartItem : list) {
            VegetablesDetails vegItem = vegServiceImpl.fetchVegetableById(cartItem.getVeg_id());
            cart_total =  cart_total + cartItem.getTotal_Price();
        %>
        <div class="card mb-3" data-price="<%=vegItem.getNet_price()%>" id="item-<%=cartItem.getCart_Id()%>">
            <div class="card-body">
                <div class="row align-items-center">
                    <div class="col-md-6">
                        <h5 class="card-title"><%= vegItem.getVegName() %></h5>
                        <p class="card-text">Unit Price: <%=vegItem.getNet_price()%></p>
                        <p class="card-text">Total Price: $<span id="total_price_item-<%=cartItem.getCart_Id()%>"><%=cartItem.getTotal_Price()%></span></p>
                    </div>
                    <div class="col-md-6">
                        <div class="d-flex align-items-center justify-content-end">
                            <button class="btn btn-sm btn-secondary me-2" onclick="updateQuantity('item-<%=cartItem.getCart_Id()%>', -1)">-</button>
                            <input type="number" class="form-control quantity-input" id="quantity-item-<%=cartItem.getCart_Id()%>" value="<%=cartItem.getQuantity_added()%>" readonly>
                            <button class="btn btn-sm btn-secondary ms-2" onclick="updateQuantity('item-<%=cartItem.getCart_Id()%>', 1)">+</button>
                            <button class="btn btn-sm btn-danger ms-3" onclick="removeItem('item-<%=cartItem.getCart_Id()%>')">Remove</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <% } %>
    </div>

    <!-- Total price section -->
    <div class="mt-4 text-end">
        <h4>Total: $<span id="total-price"><%=cart_total %></span></h4>
    </div>

    <!-- Buttons section -->
    <div class="mt-3 d-flex justify-content-end">
        <button class="btn btn-success btn-custom">Continue Shopping</button>
        <button class="btn btn-primary btn-custom">Add More Items</button>
    </div>
</div>

<br><br>
<%@include file="../components/footer.jsp" %>
<%@include file="../components/jslibraries.jsp" %>
</body>
</html>