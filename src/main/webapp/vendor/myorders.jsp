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
    <title>Vendor Inventory</title>
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
    <div id="content">
        <div id="inventory" class="section active">
            <h2>Orders</h2>
            
            <!-- In Stock Table -->
            <h3>Pending</h3>
            <table>
                <tr>
                    <th>Vegetable</th>
                    <th>Stock (kg)</th>
                    <th>Last Updated</th>
                    <th>Action</th>
                </tr>
                
               
                    <tr>
                        <td>POTATO%></td>
                        <td> 32 </td>
                        <td> DATE</td>
                        <td>
                            <button class="button"> Approve</button>
                             <button class="button"> Reject</button>
                        </td>
                    </tr>
               
            </table>
        
            <!-- ApprovedTable -->
            <h3>Approved</h3>
            <table>
                <tr>
                    <th>Vegetable</th>
                    <th>Stock (kg)</th>
                    <th>Last Updated</th>
                    <th>Action</th>
                </tr>
               
                    <tr>
                        <td>lady finger</td>
                        <td>23</td>
                        <td>date : </td>
                        <td>
                          Approved 
                        </td>
                    </tr>
                
            </table>
            
            
             <!-- ApprovedTable -->
            <h3>Rejected </h3>
            <table>
                <tr>
                    <th>Vegetable</th>
                    <th>Stock (kg)</th>
                    <th>Last Updated</th>
                    <th>Action</th>
                </tr>
               
                    <tr>
                        <td>lady finger</td>
                        <td>23</td>
                        <td>date : </td>
                        <td>
                        Rejected
                        <button class="button"> ReApprove</button>
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
    </div>
</div>

<%@include file="../components/footer.jsp" %>

</body>
<script src="${pageContext.request.contextPath}/js/vendor.js"></script>
</html>
