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
               <style>
    @keyframes fadeOut {
        0% { opacity: 1; } /* Fully visible */
        100% { opacity: 0; } /* Fully invisible */
    }
</style>  
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
    } else {
        session.setAttribute("failureMessage", "Please Login");
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;  // Ensure no further content is sent after redirect
    }
%>
<br>
<div id="container2">
    <div id="sidebar">
        <h2>Vendor Dashboard</h2>
        <div class="menu-item" onclick="showSection('sales')">Sales</div>
        <div class="menu-item" onclick="showSection('addUpdate')">Add/Update Vegetables</div>         
        <div class="menu-item" onclick="showSection('feedback')">User Feedback</div>
    </div>
    <div id="content">
    
    <c:if test="${not empty sucessmessage}">
       <h4 class="text-success" style="color: green; font-weight: bold; animation: fadeOut 1s ease-out forwards;">${sucessmessage}</h4>
        <c:remove var="sucessmessage" scope="session"/> 
    </c:if>

    <c:if test="${not empty errormessage}">
        <p class="text-danger">${errormessage}</p>
        <c:remove var="errormessage" scope="session"/> 
    </c:if>

    <div id="addUpdate" class="section active">
        <h2>Add/Update Vegetables</h2>
        <form action="${pageContext.request.contextPath}/Vegetable" method="post" id="vegetableForm" enctype="multipart/form-data">
            <input type="hidden" id="formAction" name="action_type" value="add">
            <input type="hidden" id="veg_id" name="veg_id" value="">
            <input type="text" name="veg_name" id="vegetableName" placeholder="Vegetable Name" required>

            <select id="vegetableCategory" name="veg_category" required>
                <option value="" disabled selected>Select Category</option>
                <option value="Leafy Green">Leafy Green</option>
                <option value="Cruciferous">Cruciferous </option>
                <option value="Root">Root</option>
                <option value="Seasonal">Seasonal</option>
            </select>

            <input type="number" id="vegetableStock" name="quantity" placeholder="Quantity (Stock in Pieces)" required>
            <textarea id="vegetableDescription" name="description" placeholder="Description"></textarea>
            <input type="number" name="price_per_piece" id="vegetablePrice" placeholder="Price (Per Piece)" required>
            <input type="number" name="discount_per_piece" id="vegetableDiscount" placeholder="Discount (Per Piece)" required oninput="calculateNetPrice()">
            <input type="number" name="net_price" id="vegetableNetPrice" placeholder="Net Price (After Discount)" readonly>
            <input type="file" name="veg_pic_name" id="vegetableImage" required>

            <button type="button" class="button" onclick="resetForm()">Reset</button>
            <button type="submit" class="button" id="submitButton">Add Vegetables</button>
        </form>
        
        <div class="table-responsive">
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
                    VegetablesServiceImpl vserviceImpl = new VegetablesServiceImpl();
                    List<VegetablesDetails> list = vserviceImpl.fetchAllVegetablesByVendorId(user.getId());
                    for (VegetablesDetails veg_item : list) {       
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
                        <td>
                            <button class="button" onclick="populateForm(<%=veg_item.getVegId()%>, '<%=veg_item.getVegName() %>', '<%=veg_item.getVegCategory() %>', <%=veg_item.getQuantity() %>, '<%=veg_item.getDescription() %>', <%=veg_item.getPricePerPiece() %>, <%=veg_item.getDiscount_per_piece() %>)">Update</button>
                            <form style="display: contents;" action="${pageContext.request.contextPath}/Vegetable" method="post">
                                <input type="hidden" id="vegetable_id" name="vegetable_id" value="<%=veg_item.getVegId()%>">
                                <input type="submit" name="action_type2" value="delete" style="max-width: 100%; background-color: #28a745; margin-left: 12% !important; padding: 4px 10px; margin: 2px;color: white; border: none; border-radius: 4px;">
                            </form> 
                        </td>
                    </tr>
                <% } %>    
                
                
                
                                <% 
                
                    List<VegetablesDetails> list2 = vserviceImpl.fetchAllDeletedVegetablesByVendorId(user.getId());
                    for (VegetablesDetails veg_item : list2) {       
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
                        <td>
                            <form style="display: contents;" action="${pageContext.request.contextPath}/Vegetable" method="post">
                                <input type="hidden" id="vegetable_id" name="vegetable_id" value="<%=veg_item.getVegId()%>">
                                <input type="submit" name="restore_action" value="restore" style="max-width: 100%; background-color: #FFA07A; margin-left: 12% !important; padding: 4px 10px; margin: 2px;color: white; border: none; border-radius: 4px;">
                            </form> 
                        </td>
                    </tr>
                <% } %>  
                
                   
            </table>
        </div>
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

<script src="${pageContext.request.contextPath}/js/vendor.js"></script>
</body>
</html>
