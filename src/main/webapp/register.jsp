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
           
            
                <form action="${pageContext.request.contextPath}/users" class="" method="post">
                    <input type="text" name="username" required="required" placeholder="Enter Your User Name" value="${sessionScope.username}" class="w-100  border-0 py-3 mb-4 ${not empty sessionScope.error && sessionScope.error=='Username already exists.' ? 'error':''}">
                                  
                    <input type="email" class="w-100  border-0 py-3 mb-4" placeholder="Enter Your EmailId" name="email" required="required" >
                    <input type="password" id="password" class="w-100  border-0 py-3 mb-4" placeholder="Enter Your Password" name="password" required="required" >
                    <input type="password" id="confirm-password" onkeyup="validatePasswordMatch()" class="w-100  border-0 py-3 mb-4" placeholder="Confirm Your Password" name="confrmpassword" required="required" >
					<select name="usertype" class="w-100  border-0 py-3 mb-4" >
					  <option value="customer" selected>Customer</option>
             		 <option value="admin">Admin</option>
             		 <option value="vendor">Vendor</option>
          			</select>
					<input type="checkbox" id="check" name="check" required="required">  <label for="check"  > Agree to terms and Conditions </label>
                 
                    <input type="hidden" value="register" name="action" />
                 <br><br><br>
                    <button class="w-100 btn  border-secondary py-3 bg-white text-primary" type="submit">Register</button>
                                <br><br><br>
                </form>
                
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