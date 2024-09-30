package com.yash.vijayvegmart.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yash.vijayvegmart.model.Orders;
import com.yash.vijayvegmart.service.CartsService;
import com.yash.vijayvegmart.service.OrdersService;
import com.yash.vijayvegmart.serviceImpl.CartsServiceImpl;
import com.yash.vijayvegmart.serviceImpl.OrdersServiceImpl;

@WebServlet("/Orders")
public class OrdersController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	    private OrdersService order_service;
	    
	    
 @Override
	public void init() throws ServletException {
	 order_service = new OrdersServiceImpl();
	};
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		        String fullName = request.getParameter("fullName");
		        String address = request.getParameter("address");
		        String city = request.getParameter("city");
		        String state = request.getParameter("state");
		        int zip = Integer.parseInt(request.getParameter("zip"));
		        String cardName = request.getParameter("cardName");
		        String cardNumber = request.getParameter("cardNumber");
		        String expDate = request.getParameter("expDate");
		        int cvv = Integer.parseInt(request.getParameter("cvv"));
		       double  total_order_cost = Double.parseDouble(request.getParameter("total"));
		        // Process the order (e.g., save to database, send confirmation, etc.)
                int user_id = Integer.parseInt(request.getParameter("user_id"));
               
                String all_carts_ids= request.getParameter("all_carts_ids");
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
System.out.println("USER ID :::"+user_id);

String[] carts_ids = all_carts_ids.split(",");


for(int i=0; i<carts_ids.length; i++)
System.out.println("carts_ids :::"+carts_ids[i]);

order_service.placeOrder(fullName, address , city ,state ,zip ,cardName ,cardNumber, expDate , cvv , total_order_cost, carts_ids, user_id  );
														
       // response.sendRedirect("payment.jsp");







	}
		
	}


