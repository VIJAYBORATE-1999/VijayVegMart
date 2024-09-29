package com.yash.vijayvegmart.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yash.vijayvegmart.model.Carts;
import com.yash.vijayvegmart.model.Users;
import com.yash.vijayvegmart.service.CartsService;
import com.yash.vijayvegmart.service.VegetablesService;
import com.yash.vijayvegmart.serviceImpl.CartsServiceImpl;
import com.yash.vijayvegmart.serviceImpl.VegetablesServiceImpl;

/**
 * Servlet implementation class Orders
 */
@WebServlet("/Orders")
public class OrdersController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	    private CartsService carts_service;
	    
	    
 @Override
	public void init() throws ServletException {
     carts_service = new CartsServiceImpl();
	};
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.print("ORDER IS CALLEEDDDDDDD");
        HttpSession session = request.getSession(false);
        Users user = (Users) session.getAttribute("user");

        // Retrieve updated cart data from the form submission
        Enumeration<String> parameterNames = request.getParameterNames();
        List<Carts> updatedCart = new ArrayList<Carts>();

        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();

            // Check for cart item data (quantities, veg_ids, etc.)
            if (paramName.startsWith("quantity-")) {
                int cartId = Integer.parseInt(paramName.substring(9));
                double quantity = Double.parseDouble(request.getParameter(paramName));
                int vegId = Integer.parseInt(request.getParameter("veg_id-" + cartId));
                double unit_price_veg = Double.parseDouble(request.getParameter("unit_price-" + cartId));
                double total_price =  unit_price_veg * quantity;
      
                
                // double  total_price = quantity *  
                System.out.println("CART ID => "+ cartId);
                System.out.println("QUANTITY => "+ quantity);
                System.out.println("Vegtable id "+ vegId);
                System.out.println("UNI PRICE "+ unit_price_veg);
                // Update or add cart items 
                Carts cartItem = new Carts();
                cartItem.setVeg_id(vegId);
                cartItem.setQuantity_added(quantity);
                cartItem.setUser_Id(user.getId());
                cartItem.setTotal_Price(total_price);
                
                
                 carts_service.updateCartItem(vegId, user.getId(), cartId, quantity, total_price);
                // Add other necessary fields...
                updatedCart.add(cartItem);

                
                System.out.print("");
                
                // Update cart in the database
                //cartsService.updateCart(cartItem);
            }
        }

        // Add updated cart to the session or forward it to the next page
//        session.setAttribute("updatedCart", updatedCart);
//        response.sendRedirect(request.getContextPath() + "/orderSummary.jsp");  // Redirect to an order summary page
    }
		
	}


