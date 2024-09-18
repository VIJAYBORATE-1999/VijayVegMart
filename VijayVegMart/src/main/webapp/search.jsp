<%@page import="com.vijay.model.Users"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
       
      <div class="container-fluid bg-light py-3 mt-5" style="margin-top: 3rem !important;">
     
          <form class="d-flex" role="search">
            <input
              class="form-control me-2 "
              type="search"
              placeholder="Search for products..."
              aria-label="Search"
            />
            <button class="btn btn-success search-hover" type="submit">Search</button>
          </form>
       
      </div>
       
    </div>
    <!-- Content Section End -->

<%@include file="components/footer.jsp" %>

 </body>

</html>