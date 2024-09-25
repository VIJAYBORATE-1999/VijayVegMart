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
         <%@include file="/components/links.jsp" %>
        <%@include file="../components/jslibraries.jsp" %>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
     <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css" type="text/css">
     <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.7.0/chart.min.js"></script>
    </head>
    <body>


<br><br><br><br><br>
<%@include file="../components/navbar.jsp" %>
    <%
    Users user = (Users) session.getAttribute("user");
    if (user != null) {
%>
        Welcome, Admin <%= user.getUsername() %>!<br>
          Admin Email : <%= user.getEmail() %>
<%
    } else {
    	session.setAttribute("failureMessage", "Please Login");
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;  // Ensure no further content is sent after redirect
    }
%>
<div style="display: flex;">
     <div id="sidebar" style="flex: 1; padding: 10px;">
        <h2>Admin Dashboard</h2>
        <div class="menu-item" onclick="showSection('users')">Users</div>
        <div class="menu-item" onclick="showSection('inventory')">Inventory</div>
        <div class="menu-item" onclick="showSection('approvals')">Approvals</div>
    </div>
    
    <div id="content" style="flex: 1; padding: 10px;">
        <div id="users" class="section">
            <h2>Users</h2>
            <table id="usersTable">
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Type</th>
                    <th>Action</th>
                </tr>
            </table>
        </div>
        <div id="inventory" class="section">
            <h2>Vegetable Inventory</h2>
            <canvas id="inventoryChart"></canvas>
            <table id="inventoryTable">
                <tr>
                    <th>Vegetable</th>
                    <th>Stock (kg)</th>
                    <th>Action</th>
                </tr>
            </table>
        </div>
        <div id="approvals" class="section">
            <h2>User Approvals</h2>
            <table id="approvalsTable">
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Type</th>
                    <th>Action</th>
                </tr>
            </table>
        </div>
    </div>
</div>
<%@include file="../components/footer.jsp" %>


 </body>
     <%@include file="../components/jslibraries.jsp" %>
              <script src="${pageContext.request.contextPath}/js/admin.js"></script>
      <script src="${pageContext.request.contextPath}/js/main.js"></script>
</html>