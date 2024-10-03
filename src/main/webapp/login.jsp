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
    </head>
    <body>

<%@include file="components/navbar.jsp" %>

<br><br>

  <!-- Content Section Start -->
    <div class="container mt-5 pt-5 content">
    <c:if test="${not empty sucessmessage}">
<p class="text-success">${sucessmessage}</p>
<c:remove var="sucessmessage" scope="session"/>
</c:if>
<c:if test="${not empty failureMessage}">
<p class="text-danger">${failureMessage}</p>
<c:remove var="failureMessage" scope="session"/>
</c:if>
        <h2 class="text-center mb-4">Login</h2>
        <div class="row">
            <div class="offset-2 col-8 offset-2">

<div class="w-50 w-md-50 w-sm-75 w-100 mx-auto" style="max-width: 400px;">
    <form action="${pageContext.request.contextPath}/users" method="post">
        <div class="mb-4 d-flex justify-content-between">
            <label for="username" class="me-2 w-25 text-start">User Name:</label>
            <input type="text" id="username" class="form-control form-control-sm w-75 border-0 py-2" placeholder="Enter Your User Name" name="username" required="required">
        </div>
        <div class="mb-4 d-flex justify-content-between">
            <label for="password" class="me-2 w-25 text-start">Password:</label>
            <input type="password" id="password" class="form-control form-control-sm w-75 border-0 py-2" placeholder="Enter Your Password" name="password" required="required">
        </div>
        <input type="hidden" value="login" name="action" />
        <button class="w-100 btn border-secondary py-2 bg-white text-primary" type="submit">Login</button>
    </form>
</div>

            </div>
        

        </div>
        <br>
    </div>
    <!-- Content Section End -->

<%@include file="components/footer.jsp" %>

 </body>

</html>