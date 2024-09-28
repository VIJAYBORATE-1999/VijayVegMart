package com.yash.vijayvegmart.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CartsController")
public class CartsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public CartsController() {
// constructor     
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		 int veg_id =  Integer.parseInt(request.getParameter("vegetable_id"));
		 int user_id = Integer.parseInt(request.getParameter("user_id")); 
		 double quantity_added = Double.parseDouble(request.getParameter("quantity_added"));
		 double total_price ;
		  
		System.out.println("veg ID : "+veg_id );
		System.out.println("user id " + user_id);
		System.out.println("quantity_added " + quantity_added);
		
		
		
	}

}
