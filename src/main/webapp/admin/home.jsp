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
        <title>Vijay Veg Mart</title>

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
        Welcome, ADMIN	 <%= user.getUsername() %>!<br>
         ADMIN Email : <%= user.getEmail() %>
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
            <h2>Admin Dashboard</h2>
            <div class="menu-item" onclick="showSection('addUpdate')">Add/Update Vegetables</div>
            <div class="menu-item" onclick="showSection('sales')">Sales</div>
            <div class="menu-item" onclick="showSection('profile')">Profile</div>
            <div class="menu-item" onclick="showSection('inventory')">Inventory</div>
            <div class="menu-item" onclick="showSection('feedback')">User Feedback</div>
        </div>
        <div id="content">
            <div id="addUpdate" class="section active">
                <h2>Add/Update Vegetables</h2>
                
 <c:if test="${not empty sucessmessage}">
<p class="text-success">${sucessmessage}</p>
 <c:remove var="sucessmessage" scope="session"/> 
</c:if>

 <c:if test="${not empty errormessage}">
<p class="text-danger">${errormessage}</p>
 <c:remove var="errormessage" scope="session"/> 
</c:if>
                          <form action="${pageContext.request.contextPath}/Vegetable" method="post" id="vegetableForm" enctype="multipart/form-data" >

  <input type="text"  name="veg_name" placeholder="Vegetable Name" required>
  <input type="text"  name="description"  placeholder="Description">
  <select name="veg_category">
                    <option selected>--Select Category--</option>
                    <option value="Leafy Green">Leafy Green</option>
                    <option value="Cruciferous">Cruciferous </option>
                    <option value="Root">Root</option>
                    <option value="Seasonal">Seasonal</option>
                  </select>
  <input type="number"  name="quantity" placeholder="Quantity" >
  <input type="number"  name="price_per_piece" placeholder="Price Per Piece" >
  <input type="file" name="veg_pic_name" />
  <input type="submit" value="Submit">

</form>
                <table>
                    <tr>
                        <th>Vegetable</th>
                        <th>Stock (kg)</th>
                        <th>Price per kg</th>
                        <th>Action</th>
                    </tr>
                     <% 
        
        VegetablesServiceImpl vserviceImpl = new  VegetablesServiceImpl();
        List<VegetablesDetails> list = vserviceImpl.fetchAllVegetablesByVendorId(user.getId());
        
        for(VegetablesDetails veg_item : list)
        {       
        %>
                    <tr>
                 		 <td><%=veg_item.getVegName() %></td>
                        <td><%=veg_item.getQuantity() %></td>
                        <td><%=veg_item.getDescription() %></td>
                        <td><button class="button">Update</button></td>
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
            <div id="profile" class="section">
                <h2>Profile</h2>
                <form id="profileForm">
                    <input type="text" id="vendorName" placeholder="Vendor Name" required>
                    <input type="email" id="vendorEmail" placeholder="Email" required>
                    <input type="tel" id="vendorPhone" placeholder="Phone" required>
                    <textarea id="vendorAddress" placeholder="Address" required></textarea>
                    <button type="submit" class="button">Update Profile</button>
                </form>
            </div>
            <div id="inventory" class="section">
                <h2>Inventory</h2>
                <table>
                    <tr>
                        <th>Vegetable</th>
                        <th>Stock (kg)</th>
                        <th>Last Updated</th>
                    </tr>
                    <tr>
                        <td>Tomatoes</td>
                        <td>100</td>
                        <td>2024-09-19</td>
                    </tr>
                    <tr>
                        <td>Potatoes</td>
                        <td>150</td>
                        <td>2024-09-19</td>
                    </tr>
                    <tr>
                        <td>Carrots</td>
                        <td>80</td>
                        <td>2024-09-19</td>
                    </tr>
                </table>
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
        </div>
    </div>



<%@include file="../components/footer.jsp" %>


 </body>
               <script src="${pageContext.request.contextPath}/js/vendor.js"></script>

 
</html>