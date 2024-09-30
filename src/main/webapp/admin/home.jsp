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
     <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css" type="text/css">
     
     
     
     
     
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


 <div id="container3">
        <div id="sidebar">
            <h2>Admin Dashboard</h2>
            <div class="menu-item" onclick="showSection('profile')">Profile</div>
            <div class="menu-item" onclick="showSection('sales')">Sales</div>
            <div class="menu-item" onclick="showSection('approveRequests')">Approve Users Requests</div>
            <div class="menu-item" onclick="showSection('feedback')">User Feedback</div>
        </div>
        <div id="content">
            <div id="profile" class="section active">
                <h2>Profile</h2>
                <form id="personalDetailsForm" onsubmit="updatePersonalDetails(event)">
                    <h3>Edit Personal Details</h3>
                    <div style="display: flex; align-items: center;">
                        <img id="profilePic" src="default_profile_pic.jpg" alt="Profile Picture" style="width: 50px; height: 50px; border-radius: 50%; margin-right: 10px;">
                        <input type="file" id="profilePictureInput" onchange="previewProfilePicture(event)" accept="image/*">
                    </div>
                    <input type="text" id="firstName" value="Admin" readonly required>
                    <input type="text" id="lastName" value="User" readonly required>
                    <textarea id="address" placeholder="Address" readonly required>123 Main St</textarea>
                    <input type="text" id="state" value="California" readonly required>
                    <input type="text" id="country" value="USA" readonly required>
                    <input type="text" id="zipCode" value="90001" readonly required>
                    <button type="button" class="button" id="updatePersonalButton" onclick="togglePersonalEdit()">UPDATE</button>
                </form>
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

            <div id="approveRequests" class="section">
                <h2>Approve User Requests</h2>
                <h3>Pending Approvals</h3>
                <table>
                    <tr>
                        <th>Username</th>
                        <th>Email</th>
                        <th>Action</th>
                    </tr>
                    <tr>
                        <td>user1</td>
                        <td>user1@example.com</td>
                        <td>
                            <button class="button">Approve</button>
                            <button class="button">Reject</button>
                        </td>
                    </tr>
                    <tr>
                        <td>user2</td>
                        <td>user2@example.com</td>
                        <td>
                            <button class="button">Approve</button>
                            <button class="button">Reject</button>
                        </td>
                    </tr>
                </table>

                <h3>Approved Users</h3>
                <table>
                    <tr>
                        <th>Username</th>
                        <th>Email</th>
                        <th>Status</th>
                    </tr>
                    <tr>
                        <td>user3</td>
                        <td>user3@example.com</td>
                        <td>Approved</td>
                    </tr>
                    <tr>
                        <td>user4</td>
                        <td>user4@example.com</td>
                        <td>Approved</td>
                    </tr>
                </table>

                <h3>Rejected Users</h3>
                <table>
                    <tr>
                        <th>Username</th>
                        <th>Email</th>
                        <th>Status</th>
                        <th>Action</th>
                    </tr>
                    <tr>
                        <td>user5</td>
                        <td>user5@example.com</td>
                        <td>Rejected</td>
                        <td><button class="button">Reapprove</button></td>
                    </tr>
                    <tr>
                        <td>user6</td>
                        <td>user6@example.com</td>
                        <td>Rejected</td>
                        <td><button class="button">Reapprove</button></td>
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
               <script src="${pageContext.request.contextPath}/js/admin.js"></script>

 
</html>