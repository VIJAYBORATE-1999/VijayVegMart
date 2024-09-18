<%@page import="com.vijay.model.Users"%>
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
    </head>
    <body>

<%@include file="components/navbar.jsp" %>



  <!-- Content Section Start -->
        
    <div class="container mt-5 pt-5 content">
        <h2 class="text-center mb-4">Register</h2>
        <div class="row">
            <div class="offset-2 col-8 offset-2">
            
<c:if test="${not empty error}">
<p class="text-danger">${error}</p>
<c:remove var="error" scope="session"/>
</c:if>
 
 
 <c:if test="${not empty checkmessage}">
<p class="text-danger">${checkmessage}</p>
<c:remove var="checkmessage" scope="request"/>
</c:if>      
            
            
                <form action="${pageContext.request.contextPath}/users" class="" method="post">
                    <input type="text" class="w-100  border-0 py-3 mb-4" placeholder="Enter Your User Name" name="username" required="required">
                    <input type="email" class="w-100  border-0 py-3 mb-4" placeholder="Enter Your EmailId" name="email" required="required" >
                    <input type="password" id="password" class="w-100  border-0 py-3 mb-4" placeholder="Enter Your Password" name="password" required="required" >
                    <input type="password" id="confirm-password" onkeyup="validatePasswordMatch()" class="w-100  border-0 py-3 mb-4" placeholder="Confirm Your Password" name="confrmpassword" required="required" >
					<input type="checkbox" id="check" name="check" >  <label for="check" > Agree to terms and Conditions </label>
                    <input type="hidden" value="register" name="action" />
                 <br><br><br>
                    <button class="w-100 btn  border-secondary py-3 bg-white text-primary" type="submit">Register</button>
                </form>
            </div>
        </div>
    </div>
    <!-- Content Section End -->

<%@include file="components/footer.jsp" %>

 </body>

</html>