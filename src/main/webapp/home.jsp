<%@page import="com.yash.vijayvegmart.serviceImpl.VegetablesServiceImpl"%>
<%@page import="java.util.List"%>
<%@page import="com.yash.vijayvegmart.model.VegetablesDetails"%>
<%@page import="com.yash.vijayvegmart.model.Users"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <title>Vijay Veg Mart</title>
         <%@include file="/components/links.jsp" %>
        <script src="${pageContext.request.contextPath}/js/main.js"></script>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
    </head>
    <body>
<%@include file="../components/navbar.jsp" %>
<br><br><br><br>


    <div class="container2">
    
    <%
    Users user = (Users) session.getAttribute("user");
    if (user != null) {
%>
        Welcome, Customer <%= user.getUsername() %>!<br>
        Customer Email: <%= user.getEmail() %>
<%
    } 
%>
      <!-- Sidebar Navigation -->
      <div class="sidebar">
        <ul id="category-list">
          <li data-category="Seasonal" class="active">Seasonal</li>
          <li data-category="Leafy Green">Leafy Green</li>
          <li data-category="Root">Root</li>
          <li data-category="Cruciferous">Cruciferous</li>
        </ul>
      </div>

      <!-- Product Grid -->
      <div class="product-grid">
        <!-- Sort By Section -->
        <div class="sort-section">
          <label for="sort-vendor">Sort by Vendor: </label>
          <select id="sort-vendor">
            <option value="all">All Vendors</option>
         <% 
        
        VegetablesServiceImpl vserviceImpl = new  VegetablesServiceImpl();
         List<String> list1 = vserviceImpl.fetchAllVendorsNames();
         
         
         for(String vendor_name : list1)
         {
        	 
         %>
            
            <option value="<%=vendor_name%>"><%=vendor_name%></option>
          <!--    <option value="vendor2">Vendor 2</option> -->
          
 <%}

%>
</select>
          <label for="sort-price">Sort by Price: </label>
          <select id="sort-price">
            <option value="none">Select</option>
            <option value="low-to-high">Low to High</option>
            <option value="high-to-low">High to Low</option>
          </select>
        </div>

        <h2 id="category-title">Buy Fresh Vegetables Online</h2>

        <!-- Seasonal  Vegetables  Start -->
        <div class="grid" id="Seasonal">
        
        <% 
        
  
        List<VegetablesDetails> list = vserviceImpl.fetchAllVegetablesBycategory("Seasonal");
        
        for(VegetablesDetails veg_item : list)
        {       
        	String vendor_name = vserviceImpl.getVendorUsernameByVegId(veg_item.getVegId());
        %>  
          <div class="product-card" data-vendor="<%=vendor_name %>" data-price="<%=veg_item.getNet_price()%>">
            <span class="discount-tag"><%=veg_item.getDiscount_per_piece()%>Rs OFF</span>
            <img src="${pageContext.request.contextPath}/img/<%=veg_item.getVegPicName()%>" alt="${pageContext.request.contextPath}/img/<%=veg_item.getVegPicName()%>" style="width:50px ; height:50px;">
            <h3><%=veg_item.getVegName()%></h3>
             <p class=""><%=veg_item.getDescription()%></p>
            <p class="price">
              <strong><%=veg_item.getNet_price()%></strong> <span class="old-price"><%=veg_item.getPricePerPiece() %></span>
            </p>
   <%   if (user != null) {
%>
                 <form style="display: contents;" action="${pageContext.request.contextPath}/CartsController" method="post">
  <input type="hidden" id="vegetable_id" name="vegetable_id" value="<%=veg_item.getVegId()%>">
   <input type="hidden" id="user_id" name="user_id" value="<%=user.getId()%>"> 
               <select name="quantity_added">
              <option value="1">1kg</option>
              <option value="0.5">0.5kg</option>
              <option value="2">2kg</option>
                <option value="5">5kg</option>
            </select>
   
  <input type="submit"  name="add_to_cart" value="Add to Cart" style="max-width: 100%; background-color: #28a745; margin-left: 12% !important; padding: 4px 10px; margin: 2px;color: white; border: none; border-radius: 4px;">
</form> 
<%
    } else { 
%>    	
    	
    	<select name="quantity_added">
              <option value="1">1kg</option>
              <option value="0.5">0.5kg</option>
              <option value="2">2kg</option>
                <option value="5">5kg</option>
            </select>
            <button> Add to Cart </button>
<%     	
    }
%>           

          </div>

      <%}

%>
          
          
        </div>

<!-- Seasonal  Vegetables  END  -->

        <!-- Leafy green Start  -->
        
               <div class="grid" id="Leafy Green">
        
        <% 
        
  
        List<VegetablesDetails> list2 = vserviceImpl.fetchAllVegetablesBycategory("Leafy Green");
        
        for(VegetablesDetails veg_item : list2)
        {       
        	String vendor_name = vserviceImpl.getVendorUsernameByVegId(veg_item.getVegId());
        %>  
          <div class="product-card" data-vendor="<%=vendor_name %>" data-price="<%=veg_item.getNet_price()%>">
            <span class="discount-tag"><%=veg_item.getDiscount_per_piece()%>Rs OFF</span>
            <img src="${pageContext.request.contextPath}/img/<%=veg_item.getVegPicName()%>" alt="${pageContext.request.contextPath}/img/<%=veg_item.getVegPicName()%>" style="width:50px ; height:50px;">
            <h3><%=veg_item.getVegName()%></h3>
             <p class=""><%=veg_item.getDescription()%></p>
            <p class="price">
              <strong><%=veg_item.getNet_price()%></strong> <span class="old-price"><%=veg_item.getPricePerPiece() %></span>
            </p>
   <%   if (user != null) {
%>
                 <form style="display: contents;" action="${pageContext.request.contextPath}/CartsController" method="post">
  <input type="hidden" id="vegetable_id" name="vegetable_id" value="<%=veg_item.getVegId()%>">
   <input type="hidden" id="user_id" name="user_id" value="<%=user.getId()%>"> 
               <select name="quantity_added">
              <option value="1">1kg</option>
              <option value="0.5">0.5kg</option>
              <option value="2">2kg</option>
                <option value="5">5kg</option>
            </select>
   
  <input type="submit"  name="add_to_cart" value="Add to Cart" style="max-width: 100%; background-color: #28a745; margin-left: 12% !important; padding: 4px 10px; margin: 2px;color: white; border: none; border-radius: 4px;">
</form> 
<%
    } else { 
%>    	
    	
    	<select name="quantity_added">
              <option value="1">1kg</option>
              <option value="0.5">0.5kg</option>
              <option value="2">2kg</option>
                <option value="5">5kg</option>
            </select>
            <button> Add to Cart </button>
<%     	
    }
%>           

          </div>

      <%}

%>
          
          
        </div>


        <!-- Root Vegetable Start  -->
        
               <div class="grid" id="Root">
        
        <% 
        
  
        List<VegetablesDetails> list3 = vserviceImpl.fetchAllVegetablesBycategory("Root");
        
        for(VegetablesDetails veg_item : list3)
        {       
        	String vendor_name = vserviceImpl.getVendorUsernameByVegId(veg_item.getVegId());
        %>  
          <div class="product-card" data-vendor="<%=vendor_name %>" data-price="<%=veg_item.getNet_price()%>">
            <span class="discount-tag"><%=veg_item.getDiscount_per_piece()%>Rs OFF</span>
            <img src="${pageContext.request.contextPath}/img/<%=veg_item.getVegPicName()%>" alt="${pageContext.request.contextPath}/img/<%=veg_item.getVegPicName()%>" style="width:50px ; height:50px;">
            <h3><%=veg_item.getVegName()%></h3>
             <p class=""><%=veg_item.getDescription()%></p>
            <p class="price">
              <strong><%=veg_item.getNet_price()%></strong> <span class="old-price"><%=veg_item.getPricePerPiece() %></span>
            </p>
   <%   if (user != null) {
%>
                 <form style="display: contents;" action="${pageContext.request.contextPath}/CartsController" method="post">
  <input type="hidden" id="vegetable_id" name="vegetable_id" value="<%=veg_item.getVegId()%>">
   <input type="hidden" id="user_id" name="user_id" value="<%=user.getId()%>"> 
               <select name="quantity_added">
              <option value="1">1kg</option>
              <option value="0.5">0.5kg</option>
              <option value="2">2kg</option>
                <option value="5">5kg</option>
            </select>
   
  <input type="submit"  name="add_to_cart" value="Add to Cart" style="max-width: 100%; background-color: #28a745; margin-left: 12% !important; padding: 4px 10px; margin: 2px;color: white; border: none; border-radius: 4px;">
</form> 
<%
    } else { 
%>    	
    	
    	<select name="quantity_added">
              <option value="1">1kg</option>
              <option value="0.5">0.5kg</option>
              <option value="2">2kg</option>
                <option value="5">5kg</option>
            </select>
            <button> Add to Cart </button>
<%     	
    }
%>           

          </div>

      <%}

%>
          
          
        </div>




        <!-- Cruciferous Start  -->
        
               <div class="grid" id="Cruciferous">
        
        <% 
        
  
        List<VegetablesDetails> list4 = vserviceImpl.fetchAllVegetablesBycategory("Cruciferous");
        
        for(VegetablesDetails veg_item : list4)
        {       
        	String vendor_name = vserviceImpl.getVendorUsernameByVegId(veg_item.getVegId());
        %>  
          <div class="product-card" data-vendor="<%=vendor_name %>" data-price="<%=veg_item.getNet_price()%>">
            <span class="discount-tag"><%=veg_item.getDiscount_per_piece()%>Rs OFF</span>
            <img src="${pageContext.request.contextPath}/img/<%=veg_item.getVegPicName()%>" alt="${pageContext.request.contextPath}/img/<%=veg_item.getVegPicName()%>" style="width:50px ; height:50px;">
            <h3><%=veg_item.getVegName()%></h3>
             <p class=""><%=veg_item.getDescription()%></p>
            <p class="price">
              <strong><%=veg_item.getNet_price()%></strong> <span class="old-price"><%=veg_item.getPricePerPiece() %></span>
            </p>
   <%   if (user != null) {
%>
                 <form style="display: contents;" action="${pageContext.request.contextPath}/CartsController" method="post">
  <input type="hidden" id="vegetable_id" name="vegetable_id" value="<%=veg_item.getVegId()%>">
   <input type="hidden" id="user_id" name="user_id" value="<%=user.getId()%>"> 
               <select name="quantity_added">
              <option value="1">1kg</option>
              <option value="0.5">0.5kg</option>
              <option value="2">2kg</option>
                <option value="5">5kg</option>
            </select>
   
  <input type="submit"  name="add_to_cart" value="Add to Cart" style="max-width: 100%; background-color: #28a745; margin-left: 12% !important; padding: 4px 10px; margin: 2px;color: white; border: none; border-radius: 4px;">
</form> 
<%
    } else { 
%>    	
    	
    	<select name="quantity_added">
              <option value="1">1kg</option>
              <option value="0.5">0.5kg</option>
              <option value="2">2kg</option>
                <option value="5">5kg</option>
            </select>
            <button> Add to Cart </button>
<%     	
    }
%>           

          </div>

      <%}

%>
          
          
        </div>

      </div>
    </div>


<%@include file="../components/footer.jsp" %>
 <%@include file="../components/jslibraries.jsp" %>
 </body>

</html>