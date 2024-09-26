<%@page import="java.util.List"%>
<%@page import="com.yash.vijayvegmart.model.VegetablesDetails"%>
<%@page import="com.yash.vijayvegmart.daoImpl.VegetablesDaoImpl"%>
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
          <li data-category="vegetables" class="active">Fresh Vegetables</li>
          <li data-category="fruits">Fresh Fruits</li>
          <li data-category="melons">Mangoes & Melons</li>
          <li data-category="seasonal">Seasonal</li>
          <li data-category="exotics">Exotics</li>
        </ul>
      </div>

      <!-- Product Grid -->
      <div class="product-grid">
        <!-- Sort By Section -->
        <div class="sort-section">
          <label for="sort-vendor">Sort by Vendor: </label>
          <select id="sort-vendor">
            <option value="all">All Vendors</option>
            <option value="vendor1">Vendor 1</option>
            <option value="vendor2">Vendor 2</option>
          </select>

          <label for="sort-price">Sort by Price: </label>
          <select id="sort-price">
            <option value="none">Select</option>
            <option value="low-to-high">Low to High</option>
            <option value="high-to-low">High to Low</option>
          </select>
        </div>

        <h2 id="category-title">Buy Fresh Vegetables Online</h2>

        <!-- Vegetables Products -->
        <div class="grid" id="vegetables">
        
        <% 
        
        VegetablesDaoImpl vdao = new VegetablesDaoImpl();
        List<VegetablesDetails> list = vdao.getAllVegetables();
        
        for(VegetablesDetails veg_item : list)
        {       
        %>
        
        
          <div class="product-card" data-vendor="vendor1" data-price="30">
            <span class="discount-tag">23% OFF</span>
            <img src="${pageContext.request.contextPath}/img/<%=veg_item.getVegPicName()%>" alt="${pageContext.request.contextPath}/img/<%=veg_item.getVegPicName()%>" style="width:50px ; height:50px;">
            <h3><%=veg_item.getVegName()%></h3>
             <p class=""><%=veg_item.getDescription()%></p>
            <select>
              <option value="500gg">500</option>
              <option value="1kg">1kg</option>
            </select>
            <p class="price">
              <strong><%=veg_item.getPricePerPiece() %></strong> <span class="old-price"><%=veg_item.getVegCategory() %></span>
            </p>
            <button>Add to Cart</button>
          </div>

      <%}

%>
          
          
        </div>

        <!-- Fruits Products -->
        <div class="grid" id="fruits" style="display: none">
          <div class="product-card" data-vendor="vendor2" data-price="100">
            <span class="discount-tag">15% OFF</span>
            <h3>Apple</h3>
            <p class="vendor-name">Vendor: Vendor 2</p>
            <select>
              <option value="1kg">1kg</option>
            </select>
            <p class="price">
              <strong>₹100</strong> <span class="old-price">₹120</span>
            </p>
            <button>Add to Cart</button>
          </div>

          <div class="product-card" data-vendor="vendor1" data-price="40">
            <span class="discount-tag">20% OFF</span>
            <h3>Banana</h3>
            <p class="vendor-name">Vendor: Vendor 1</p>
            <select>
              <option value="1 dozen">1 dozen</option>
            </select>
            <p class="price">
              <strong>₹40</strong> <span class="old-price">₹50</span>
            </p>
            <button>Add to Cart</button>
          </div>
        </div>
      </div>
    </div>


<%@include file="../components/footer.jsp" %>
 <%@include file="../components/jslibraries.jsp" %>
 </body>

</html>