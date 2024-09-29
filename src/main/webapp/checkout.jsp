
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

  
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/checkout.css" type="text/css">
     <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
 
</head>


<body>
<%@include file="../components/navbar.jsp" %>
<br><br><br><br>

<div class="container3">
        <h1 class="text-center mb-4">Checkout</h1>
        <form id="checkout-form" action="${pageContext.request.contextPath}/Orders" method="POST">
            <div class="row">
                <div class="col-md-8">
                    <h2 class="mb-3">Shipping Information</h2>
                    <div class="mb-3">
                        <label for="fullName" class="form-label">Full Name</label>
                        <input type="text" class="form-control" id="fullName" name="fullName" required>
                    </div>
                    <div class="mb-3">
                        <label for="address" class="form-label">Address</label>
                        <input type="text" class="form-control" id="address" name="address" required>
                    </div>
                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label for="city" class="form-label">City</label>
                            <input type="text" class="form-control" id="city" name="city" required>
                        </div>
                        <div class="col-md-4 mb-3">
                            <label for="state" class="form-label">State</label>
                            <input type="text" class="form-control" id="state" name="state" required>
                        </div>
                        <div class="col-md-2 mb-3">
                            <label for="zip" class="form-label">ZIP</label>
                            <input type="text" class="form-control" id="zip" name="zip" required>
                        </div>
                    </div>

                    <h2 class="mb-3 mt-4">Payment Information</h2>
                    <div class="mb-3">
                        <label for="cardName" class="form-label">Name on Card</label>
                        <input type="text" class="form-control" id="cardName" name="cardName" required>
                    </div>
                    <div class="mb-3">
                        <label for="cardNumber" class="form-label">Card Number</label>
                        <input type="text" class="form-control" id="cardNumber" name="cardNumber" required>
                    </div>
                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label for="expDate" class="form-label">Expiration Date</label>
                            <input type="text" class="form-control" id="expDate" name="expDate" placeholder="MM/YY" required>
                        </div>
                        <div class="col-md-6 mb-3">
                            <label for="cvv" class="form-label">CVV</label>
                            <input type="text" class="form-control" id="cvv" name="cvv" required>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="order-summary">
                        <h2 class="mb-3">Order Summary</h2>
                        <div id="order-items">
                        
                         <% 
                         Users user = (Users) session.getAttribute("user");
             VegetablesServiceImpl vserviceImpl = new  VegetablesServiceImpl();
      
            CartsServiceImpl cserviceImpl = new CartsServiceImpl();
                             		
        List<Carts> list = cserviceImpl.fetchAllCartsByUserID(user.getId());
        
        for(Carts cart_item : list)
        {       
        	  VegetablesDetails veg_details= vserviceImpl.fetchVegetableById(cart_item.getVeg_id()) ;
        %>
                      
                            <div class="d-flex justify-content-between mb-2">
                                <span><%=veg_details.getVegName() %> (<%=cart_item.getQuantity_added() %>)</span>
                                <span>$<span class="item-price"><%=cart_item.getTotal_Price() %></span></span>
                            </div>
                            
                    <%} %>        
                            
                            
                        </div>
                        <hr>
                        <div class="d-flex justify-content-between">
                            <strong>Total:</strong>
                            <strong id="total-price">$<span id="total"></span></strong>
                           
                        </div>
                    </div>
                </div>
            </div>
          <input type="hidden" id="totalInput" name="total">
            <button type="submit" class="btn btn-primary btn-lg mt-4">Place Order</button>
        </form>
    </div>

<br><br>
<%@include file="../components/footer.jsp" %>
<%@include file="../components/jslibraries.jsp" %>
   
</body>
 <script src="${pageContext.request.contextPath}/js/checkout.js"></script>
</html>