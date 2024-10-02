<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Order_Confirm</title>

    <script src="${pageContext.request.contextPath}/js/main.js"></script>
      <%@include file="/components/links.jsp" %>
          <link rel="stylesheet" href="${pageContext.request.contextPath}/css/order_confirmation.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">

</head>
<body>

<%@include file="../components/navbar.jsp" %>
<br><br><br><br>

    <div class="container3">
        <h1 class="text-center mb-4">Order Confirmation</h1>
        <div class="alert alert-success no-print" role="alert">
            Thank you for your order! Your fresh vegetables are on their way.
        </div>
        <div class="order-details">
        <%
    String order_id = (String) session.getAttribute("order_id");
    
%>
     <h2> Order Id : <%= order_id %> </h2>
           
            <p>Order Date: 09/28/2024</p>
            <p class="estimated-delivery">Estimated Delivery: 09/30/2024</p>
        </div>
        <h2>Order Summary</h2>
        <table class="table">
            <thead>
                <tr>
                    <th>Item</th>
                    <th>Quantity</th>
                    <th>Price</th>
                    <th>Total</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>Carrots</td>
                    <td>2</td>
                    <td>$1.99</td>
                    <td>$3.98</td>
                </tr>
                <tr>
                    <td>Tomatoes</td>
                    <td>1</td>
                    <td>$2.49</td>
                    <td>$2.49</td>
                </tr>
                <tr>
                    <td>Broccoli</td>
                    <td>3</td>
                    <td>$1.79</td>
                    <td>$5.37</td>
                </tr>
            </tbody>
            <tfoot>
                <tr>
                    <td colspan="3" class="text-end"><strong>Total:</strong></td>
                    <td><strong>$11.84</strong></td>
                </tr>
            </tfoot>
        </table>
        <h2>Shipping Information</h2>
        <div id="shipping-info">
            <p>John Doe</p>
            <p>123 Veggie Lane</p>
            <p>Greenville, VG 12345</p>
        </div>
        <div class="text-center mt-4 no-print">
        
            <a href="${pageContext.request.contextPath}/home.jsp" class="btn btn-success me-2">Return to Shop</a>
          <br>
            <button onclick="window.print()" class="btn btn-primary">Download Bill</button>
        </div>
    </div>
    
    <br><br>
<%@include file="../components/footer.jsp" %>
</body>
</html>