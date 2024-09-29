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

/**
 * Servlet implementation class Orders
 */
@WebServlet("/OrderServlet")
public class Orders extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Orders() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.print("ORDER IS CALLEEDDDDDDD");
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("user");

        // Retrieve updated cart data from the form submission
        Enumeration<String> parameterNames = request.getParameterNames();
        List<Carts> updatedCart = new ArrayList<Carts>();

        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();

            // Check for cart item data (quantities, veg_ids, etc.)
            if (paramName.startsWith("quantity-")) {
                int cartId = Integer.parseInt(paramName.substring(9));
                int quantity = Integer.parseInt(request.getParameter(paramName));
                int vegId = Integer.parseInt(request.getParameter("veg_id-" + cartId));

                System.out.println("CART ID => "+ cartId);
                System.out.println("QUANTITY => "+ quantity);
                System.out.println("Vegtable id "+ vegId);
                // Update or add cart items as needed (logic for updating the database)
                Carts cartItem = new Carts();
                cartItem.setVeg_id(vegId);
                cartItem.setQuantity_added(quantity);
                cartItem.setUser_Id(user.getId());
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


