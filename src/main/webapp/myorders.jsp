<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>My Orders</title>

        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
     <link rel="stylesheet" href="${pageContext.request.contextPath}/css/myorders.css" type="text/css">   
       <%@include file="/components/links.jsp" %>     
    </head>
<body>

<%@include file="../components/navbar.jsp" %>
<br><br><br>
    <div class="container3">
        <h1 class="text-center">My Orders</h1>
        <div id="orders-container3">
          
          
          
            <!-- Order #VEG-123456 -->
            <div class="order-card">
                <div class="order-header d-flex justify-content-between align-items-center">
                    <h5 class="mb-0">Order #VEG-123456</h5>
                    <span class="badge bg-success">Delivered</span>
                </div>
                <div class="order-body">
                    <p><strong>Date:</strong> 2023-09-15</p>
                    <p><strong>Total:</strong> $15.25</p>
                </div>
                <div class="order-footer text-end">
                    <button class="btn btn-outline-success" data-bs-toggle="modal" data-bs-target="#orderDetailsModal1">View Details</button>
                </div>






                <!-- Modallllllllll-->

                <div class="modal fade" id="orderDetailsModal1" tabindex="-1" aria-labelledby="orderDetailsModalLabel1" aria-hidden="true">
                    <div class="modal-dialog modal-lg">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="orderDetailsModalLabel1">Order Details for #VEG-123456</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <p><strong>Order Date:</strong> 2023-09-15</p>
                                <p><strong>Status:</strong> Delivered</p>
                                <p><strong>Total:</strong> $15.25</p>
                                <h6>Items:</h6>
                                <ul>
                                    <li>Carrots - Quantity: 2, Price: $1.99</li>
                                    <li>Tomatoes - Quantity: 1, Price: $2.49</li>
                                    <li>Broccoli - Quantity: 3, Price: $1.79</li>
                                </ul>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                            </div>
                        </div>
                    </div>
                </div>



<!-- Modallllllllll-->





            </div>









            <!-- Order #VEG-123457 -->
            <div class="order-card">
                <div class="order-header d-flex justify-content-between align-items-center">
                    <h5 class="mb-0">Order #VEG-123457</h5>
                    <span class="badge bg-primary">In Transit</span>
                </div>
                <div class="order-body">
                    <p><strong>Date:</strong> 2023-09-20</p>
                    <p><strong>Total:</strong> $22.97</p>
                </div>
                <div class="order-footer text-end">
                    <button class="btn btn-outline-success" data-bs-toggle="modal" data-bs-target="#orderDetailsModal2">View Details</button>
                </div>


    <!-- Static Modal for Order #VEG-123457 -->
    <div class="modal fade" id="orderDetailsModal2" tabindex="-1" aria-labelledby="orderDetailsModalLabel2" aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="orderDetailsModalLabel2">Order Details for #VEG-123457</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <p><strong>Order Date:</strong> 2023-09-20</p>
                    <p><strong>Status:</strong> In Transit</p>
                    <p><strong>Total:</strong> $22.97</p>
                    <h6>Items:</h6>
                    <ul>
                        <li>Spinach - Quantity: 1, Price: $3.99</li>
                        <li>Bell Peppers - Quantity: 2, Price: $2.99</li>
                        <li>Cucumbers - Quantity: 3, Price: $1.49</li>
                    </ul>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>

    <!-- Static Modal for Order #VEG-123457 -->



            </div>


        </div>
    </div>



    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
<br>
<%@include file="../components/footer.jsp" %>
 <%@include file="../components/jslibraries.jsp" %>

</body>
  <script src="${pageContext.request.contextPath}/js/myorders.js"></script>
  
</html>