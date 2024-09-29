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

import com.yash.vijayvegmart.service.CartsService;
import com.yash.vijayvegmart.serviceImpl.CartsServiceImpl;



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
		
		        String fullName = request.getParameter("fullName");
		        String address = request.getParameter("address");
		        String city = request.getParameter("city");
		        String state = request.getParameter("state");
		        String zip = request.getParameter("zip");
		        String cardName = request.getParameter("cardName");
		        String cardNumber = request.getParameter("cardNumber");
		        String expDate = request.getParameter("expDate");
		        String cvv = request.getParameter("cvv");
		       double  total_order_cost = Double.parseDouble(request.getParameter("total"));
		        // Process the order (e.g., save to database, send confirmation, etc.)

		        // Redirect or forward to a confirmation page
		     //   response.sendRedirect("confirmation.jsp");
	
System.out.println("---------------");

System.out.println(fullName);
System.out.println(address);
System.out.println(city);
System.out.println(state);
System.out.println(zip);
System.out.println(cardName);
System.out.println(cardNumber);
System.out.println(expDate);
System.out.println(cvv);
System.out.println(total_order_cost);













       // response.sendRedirect("payment.jsp");
	
	}
		
	}


