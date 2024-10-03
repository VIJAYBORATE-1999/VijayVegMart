<%@page import="com.yash.vijayvegmart.model.Users"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false"%>

<!DOCTYPE html>
<html lang="en">

    <head>
        <title>Vijay Veg Mart</title>
         <%@include file="components/links.jsp" %>
        <%@include file="components/jslibraries.jsp" %>
        <link href="css/style.css" rel="stylesheet">
       <style> 
       .error{
       
       border : 2px solid red;
       background-color : #ffe6e6;
       }
       
     </style>
    </head>
    <body>

<%@include file="components/navbar.jsp" %>



  <!-- Content Section Start -->
        
    <div class="container mt-5 pt-5 content">
        <h2 class="text-center mb-4">Register</h2>
        <div class="row">
            <div class="offset-2 col-8 offset-2">
            

 
 
 <c:if test="${not empty checkmessage}">
<p class="text-danger">${checkmessage}</p>
<c:remove var="checkmessage" scope="request"/>
</c:if>     
 
 <c:if test="${not empty error}">
<p class="text-info">${error}</p>

</c:if>
  <div class="w-50 w-md-50 w-sm-75 w-100 mx-auto" style="max-width: 500px;">
    <form action="${pageContext.request.contextPath}/users" class="" method="post">
        <div class="mb-3 d-flex justify-content-between">
            <label for="username" class="me-2 w-25 text-start">User Name:</label>
            <input type="text" name="username" required="required" placeholder="Enter Your User Name" value="${sessionScope.username}" 
            class="form-control form-control-sm w-75 border-0 py-2 ${not empty sessionScope.error && sessionScope.error=='Username already exists.' ? 'error':''}">
        </div>

        <div class="mb-3 d-flex justify-content-between">
            <label for="email" class="me-2 w-25 text-start">Email:</label>
            <input type="email" class="form-control form-control-sm w-75 border-0 py-2" placeholder="Enter Your EmailId" name="email" required="required">
        </div>

        <div class="mb-3 d-flex justify-content-between">
            <label for="password" class="me-2 w-25 text-start">Password:</label>
            <input type="password" id="password" class="form-control form-control-sm w-75 border-0 py-2" placeholder="Enter Your Password" name="password" required="required">
        </div>

        <div class="mb-3 d-flex justify-content-between">
            <label for="confirm-password" class="me-2 w-25 text-start">Confirm Password:</label>
            <input type="password" id="confirm-password" onkeyup="validatePasswordMatch()" class="form-control form-control-sm w-75 border-0 py-2" placeholder="Confirm Your Password" name="confrmpassword" required="required">
        </div>

        <div class="mb-3 d-flex justify-content-between">
            <label for="usertype" class="me-2 w-25 text-start">User Type:</label>
            <select name="usertype" class="form-control form-control-sm w-75 border-0 py-2">
                <option value="customer" selected>Customer</option>
           
                <option value="vendor">Vendor</option>
            </select>
        </div>

        <div class="mb-3 d-flex align-items-center">
            <input type="checkbox" id="check" name="check" required="required" class="me-2">
            <label for="check" class="mb-0">Agree to terms and Conditions</label>
        </div>

        <input type="hidden" value="register" name="action" />
    
        <button class="w-100 btn border-secondary py-2 bg-white text-primary" type="submit">Register</button>
    </form>
</div>


                
<c:if test="${not empty password}">
 <c:remove var="error" scope="session"/> 
 <c:remove var="username" scope="session"/> 
</c:if>

            </div>
        </div>
    </div>
    <!-- Content Section End -->

<%@include file="components/footer.jsp" %>

 </body>

</html>