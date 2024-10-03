package com.yash.vijayvegmart.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yash.vijayvegmart.service.VendorService;
import com.yash.vijayvegmart.serviceImpl.VendorServiceImpl;

/**
 * Servlet implementation class VendorController
 */
@WebServlet("/VendorsController")
public class VendorController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private VendorService vendorService;
	
   @Override
	public void init() throws ServletException {
	   vendorService = new VendorServiceImpl();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String vendor_action_status = request.getParameter("vendor_action_status");
		
		
		if( vendor_action_status != null)
		{
			String order_id = request.getParameter("order_id");
			int  user_id = Integer.parseInt(request.getParameter("user_id"));
			int  cart_id = Integer.parseInt(request.getParameter("cart_id"));
			
			
			if(vendor_action_status.equals("approved"))
			{
				boolean b = vendorService.updateVendorActionStatus(order_id, user_id, cart_id, vendor_action_status);
				
			}
			else if(vendor_action_status.equals("rejected"))
			{
				boolean b = vendorService.updateVendorActionStatus(order_id, user_id, cart_id, vendor_action_status);
				
			}
		
			 response.sendRedirect("vendor/myorders.jsp");
		
		}

		
		
		
	}

}
