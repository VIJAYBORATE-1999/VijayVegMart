<%@page import="com.yash.vijayvegmart.serviceImpl.VegetablesServiceImpl"%>
<%@page import="java.util.List"%>
<%@page import="com.yash.vijayvegmart.model.VegetablesDetails"%>
<%@page import="com.yash.vijayvegmart.model.Users"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- JSTL  -->
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@page isELIgnored="false"%>
 <!-- JSTL  -->
<!DOCTYPE html>
<html lang="en">

    <head>
        <title>Vendor DashBoard</title>

        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
     <link rel="stylesheet" href="${pageContext.request.contextPath}/css/vendor.css" type="text/css">   
       <%@include file="/components/links.jsp" %>     
    </head>
    <body>

<%@include file="../components/navbar.jsp" %>
<br><br><br><br><br>
    <%
    Users user = (Users) session.getAttribute("user");
    if (user != null) {
%>
        Welcome, Vendor <%= user.getUsername() %>!<br>
         Vendor Email : <%= user.getEmail() %>
<%
    }else {
    	session.setAttribute("failureMessage", "Please Login");
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;  // Ensure no further content is sent after redirect
    }
%>
<br>
<div id="container2">
    
        <div id="sidebar">
            <h2>Vendor Dashboard</h2>
            <div class="menu-item" onclick="showSection('profile')">Profile</div>
                    <div class="menu-item" onclick="showSection('sales')">Sales</div>
            <div class="menu-item" onclick="showSection('addUpdate')">Add/Update Vegetables</div>         
            <div class="menu-item" onclick="showSection('inventory')">Inventory</div>
            <div class="menu-item" onclick="showSection('orders')">Orders Status</div>
                      <div class="menu-item" onclick="showSection('feedback')">User Feedback</div>
        </div>
        <div id="content">
        
 <c:if test="${not empty sucessmessage}">
<p class="text-success">${sucessmessage}</p>
 <c:remove var="sucessmessage" scope="session"/> 
</c:if>

 <c:if test="${not empty errormessage}">
<p class="text-danger">${errormessage}</p>
 <c:remove var="errormessage" scope="session"/> 
</c:if>

                    <div id="profile" class="section">
                <h2>Profile</h2>
            
                <!-- Edit Personal Details Form -->
                <form id="personalDetailsForm" onsubmit="updatePersonalDetails(event)">
                    <h3>Edit Personal Details</h3>
                    <div style="display: flex; align-items: center;">
                        <img id="profilePic" src="default_profile_pic.jpg" alt="Profile Picture" style="width: 50px; height: 50px; border-radius: 50%; margin-right: 10px;">
                        <input type="file" id="profilePictureInput" onchange="previewProfilePicture(event)" accept="image/*">
                    </div>
                    <input type="text" id="firstName" value="John" readonly required>
                    <input type="text" id="lastName" value="Doe" readonly required>
                    <textarea id="address" placeholder="Address" readonly required>123 Main St</textarea>
                    <input type="text" id="state" value="California" readonly required>
                    <input type="text" id="country" value="USA" readonly required>
                    <input type="text" id="zipCode" value="90001" readonly required>
                    <button type="button" class="button" id="updatePersonalButton" onclick="togglePersonalEdit()">UPDATE</button>
                </form>
            
                <!-- Edit Login Details Form -->
                <form id="loginDetailsForm" onsubmit="updateLoginDetails(event)">
                    <h3>Edit Login Details</h3>
                    <input type="text" id="username" value="johndoe" readonly required>
                    <button type="button" class="button" id="checkUsernameButton" disabled>Check Username</button>
                    <input type="password" id="password" value="password123" readonly required>
                    <input type="email" id="email" value="john.doe@example.com" readonly required>
                    <input type="text" id="userType" value="Admin" readonly required>
                    <input type="text" id="userStatus" value="Active" readonly required>
                    <button type="button" class="button" id="updateLoginButton" onclick="toggleLoginEdit()">UPDATE</button>
                </form>
            </div>
            
            <div id="addUpdate" class="section active">
                <h2>Add/Update Vegetables</h2>
                 <form action="${pageContext.request.contextPath}/Vegetable" method="post" id="vegetableForm" enctype="multipart/form-data" >
                    <input type="hidden" id="formAction" name="action_type" value="add"> <!-- Hidden input for action = "update" or "add" . Initially its value is ="add" but as User Clicks update Button value becomes="update" -->
                     <input type="hidden" id="veg_id" name="veg_id" value=""> <!-- Hidden input for Update action . intially it is empty"" But as user clicks update button it gets the vendor id -->
                    <input type="text" name="veg_name" id="vegetableName" placeholder="Vegetable Name" required>
                    
                    <select id="vegetableCategory" name="veg_category"  required>
                        <option value="" disabled selected>Select Category</option>
                        <option value="Leafy Green">Leafy Green</option>
                    <option value="Cruciferous">Cruciferous </option>
                    <option value="Root">Root</option>
                    <option value="Seasonal">Seasonal</option>
                    </select>

                    <input type="number" id="vegetableStock"  name="quantity"  placeholder="Quantity (Stock in Pieces)" required>
                    
                    <textarea id="vegetableDescription" name="description" placeholder="Description"></textarea>
                    
                    <input type="number" name="price_per_piece" id="vegetablePrice" placeholder="Price (Per Piece)" required>

                    <input type="number" name= "discount_per_piece" id="vegetableDiscount" placeholder="Discount (Per Piece)" required oninput="calculateNetPrice()">

                    <input type="number" name="net_price" id="vegetableNetPrice" placeholder="Net Price (After Discount)" readonly>

                    <input type="file" name="veg_pic_name"  id="vegetableImage" required>

                    <button type="button" class="button" onclick="resetForm()">Reset</button>

                    <button type="submit" class="button" id="submitButton">Add Vegetables</button> <!-- Button initially for adding -->
                </form>
                
                <table>
                    <tr>
                        <th>Vegetable</th>
                        <th>Category</th>
                        <th>Stock (kg)</th>
                        <th>Description</th>
                        <th>Price (Per Piece)</th>
                        <th>Discount (Per Piece)</th>
                        <th>Net Price</th>
                        <th>Image</th>
                        <th>Action</th>
                    </tr>
                    
                    <% 
        
        VegetablesServiceImpl vserviceImpl = new  VegetablesServiceImpl();
        List<VegetablesDetails> list = vserviceImpl.fetchAllVegetablesByVendorId(user.getId());
        
        for(VegetablesDetails veg_item : list)
        {       
        %>
                    
                    <tr>
                    <td style="display: none;"><%=veg_item.getVegId()%></td>
                        <td><%=veg_item.getVegName() %></td>
                        <td><%=veg_item.getVegCategory() %></td>
                        <td><%=veg_item.getQuantity() %></td>  
                        <td><%=veg_item.getDescription() %></td>
                        <td><%=veg_item.getPricePerPiece() %></td>
                        <td><%=veg_item.getDiscount_per_piece() %></td>
                        <td><%=veg_item.getNet_price() %></td>
                        <td><img src="${pageContext.request.contextPath}/img/<%=veg_item.getVegPicName()%>" alt="<%=veg_item.getVegPicName() %>" width="50"></td>
                        <td><button class="button" onclick="populateForm(<%=veg_item.getVegId()%>,'<%=veg_item.getVegName() %>', '<%=veg_item.getVegCategory() %>', <%=veg_item.getQuantity() %>, '<%=veg_item.getDescription() %>', <%=veg_item.getPricePerPiece() %> , <%=veg_item.getDiscount_per_piece() %>)">Update</button></td>
                    </tr>
                       <%}

%>       
                </table>
            </div>


            <div id="sales" class="section">
                <h2>Sales</h2>
                <table>
                    <tr>
                        <th>Date</th>
                        <th>Vegetable</th>
                        <th>Quantity Sold (kg)</th>
                        <th>Total Amount</th>
                    </tr>
                    <tr>
                        <td>2024-09-20</td>
                        <td>Tomatoes</td>
                        <td>20</td>
                        <td>$50.00</td>
                    </tr>
                    <tr>
                        <td>2024-09-21</td>
                        <td>Potatoes</td>
                        <td>30</td>
                        <td>$54.00</td>
                    </tr>
                    <tr>
                        <td>2024-09-22</td>
                        <td>Carrots</td>
                        <td>15</td>
                        <td>$22.50</td>
                    </tr>
                </table>
            </div>

            
            
            <div id="inventory" class="section">
                <h2>Inventory</h2>
                
                <!-- In Stock Table -->
                <h3>In Stock</h3>
                <table>
                    <tr>
                        <th>Vegetable</th>
                        <th>Stock (kg)</th>
                        <th>Last Updated</th>
                        <th>Action</th>
                    </tr>
                    <tr>
                        <td>Tomatoes</td>
                        <td>100</td>
                        <td>2024-09-19</td>
                        <td>
                            <button class="button" onclick="openDialog('update', 'Tomatoes', 100)">Update</button>
                        </td>
                    </tr>
                    <tr>
                        <td>Potatoes</td>
                        <td>150</td>
                        <td>2024-09-19</td>
                        <td>
                            <button class="button" onclick="openDialog('update', 'Potatoes', 150)">Update</button>
                        </td>
                    </tr>
                </table>
            
                <!-- Out of Stock Table -->
                <h3>Out of Stock</h3>
                <table>
                    <tr>
                        <th>Vegetable</th>
                        <th>Stock (kg)</th>
                        <th>Last Updated</th>
                        <th>Action</th>
                    </tr>
                    <tr>
                        <td>Carrots</td>
                        <td>0</td>
                        <td>2024-09-19</td>
                        <td>
                            <button class="button" onclick="openDialog('refill', 'Carrots', 0)">Refill</button>
                        </td>
                    </tr>
                </table>
            
                <!-- Dialog Box -->
                <div id="dialog" style="display: none;">
                    <div style="background-color: white; padding: 20px; border: 1px solid #ccc; width: 300px; position: fixed; top: 50%; left: 50%; transform: translate(-50%, -50%); z-index: 1000;">
                        <h3 id="dialogTitle"></h3>
                        <form id="stockForm" onsubmit="submitStockForm(event)">
                            <input type="hidden" id="vegetableName" required>
                            <input type="number" id="newStock" placeholder="Stock" required>
                            <button type="submit" class="button">Update/Refill Stock</button>
                            <button type="button" class="button" onclick="closeDialog()">Cancel</button>
                        </form>
                    </div>
                    <div style="background: rgba(0, 0, 0, 0.5); position: fixed; top: 0; left: 0; right: 0; bottom: 0; z-index: 999;"></div>
                </div>
            </div>

            <div id="feedback" class="section">
                <h2>User Feedback</h2>
                <table>
                    <tr>
                        <th>Date</th>
                        <th>User</th>
                        <th>Vegetable</th>
                        <th>Rating</th>
                        <th>Comment</th>
                    </tr>
                    <tr>
                        <td>2024-09-20</td>
                        <td>John Doe</td>
                        <td>Tomatoes</td>
                        <td>4</td>
                        <td>Great quality!</td>
                    </tr>
                    <tr>
                        <td>2024-09-21</td>
                        <td>Jane Smith</td>
                        <td>Potatoes</td>
                        <td>5</td>
                        <td>Very fresh</td>
                    </tr>
                    <tr>
                        <td>2024-09-22</td>
                        <td>Bob Johnson</td>
                        <td>Carrots</td>
                        <td>3</td>
                        <td>Good, but could be fresher</td>
                    </tr>
                </table>
            </div>

            


            <div id="orders" class="section">
                <h2>Orders</h2>
                
                <!-- Pending Orders Table -->
                <h3>Pending Orders</h3>
                <table>
                    <tr>
                        <th>Vegetable</th>
                        <th>Quantity Ordered</th>
                        <th>Order Status</th>
                        <th>Order Date</th>
                        <th>Transaction ID</th>
                        <th>Payment Status</th>
                        <th>Action</th>
                    </tr>
                    <tr>
                        <td>Tomatoes</td>
                        <td>50</td>
                        <td>Pending Approval</td>
                        <td>2024-09-25</td>
                        <td>TX123456</td>
                        <td>Pending</td>
                        <td>
                            <button class="button" onclick="approveOrder('Tomatoes', 'TX123456')">Approve</button>
                            <button class="button" onclick="rejectOrder('Tomatoes', 'TX123456')">Reject</button>
                        </td>
                    </tr>
                    <tr>
                        <td>Potatoes</td>
                        <td>30</td>
                        <td>Pending Approval</td>
                        <td>2024-09-26</td>
                        <td>TX123457</td>
                        <td>Pending</td>
                        <td>
                            <button class="button" onclick="approveOrder('Potatoes', 'TX123457')">Approve</button>
                            <button class="button" onclick="rejectOrder('Potatoes', 'TX123457')">Reject</button>
                        </td>
                    </tr>
                </table>
            
                <!-- Orders Approved Table -->
                <h3>Orders Approved</h3>
                <table>
                    <tr>
                        <th>Vegetable</th>
                        <th>Quantity Ordered</th>
                        <th>Order Status</th>
                        <th>Order Date</th>
                        <th>Transaction ID</th>
                        <th>Payment Status</th>
                    </tr>
                    <tr>
                        <td>Carrots</td>
                        <td>20</td>
                        <td>Approved</td>
                        <td>2024-09-24</td>
                        <td>TX123458</td>
                        <td>Completed</td>
                    </tr>
                </table>
            
                <!-- Orders Rejected Table -->
                <h3>Orders Rejected</h3>
                <table>
                    <tr>
                        <th>Vegetable</th>
                        <th>Quantity Ordered</th>
                        <th>Order Status</th>
                        <th>Order Date</th>
                        <th>Transaction ID</th>
                        <th>Payment Status</th>
                    </tr>
                    <tr>
                        <td>Spinach</td>
                        <td>15</td>
                        <td>Rejected</td>
                        <td>2024-09-23</td>
                        <td>TX123459</td>
                        <td>Refunded</td>
                    </tr>
                </table>
            </div>





        </div>


</div>



<%@include file="../components/footer.jsp" %>


 </body>
               <script src="${pageContext.request.contextPath}/js/vendor.js"></script>

 
</html>