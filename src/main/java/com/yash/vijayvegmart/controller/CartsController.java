package com.yash.vijayvegmart.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yash.vijayvegmart.model.VegetablesDetails;
import com.yash.vijayvegmart.service.CartsService;
import com.yash.vijayvegmart.service.VegetablesService;
import com.yash.vijayvegmart.serviceImpl.CartsServiceImpl;
import com.yash.vijayvegmart.serviceImpl.VegetablesServiceImpl;

@WebServlet("/CartsController")
public class CartsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private VegetablesService veg_service;
    private CartsService carts_service;
    public void init() {
        veg_service = new VegetablesServiceImpl();
        carts_service = new CartsServiceImpl();
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		try {
		
		 
		 String action = request.getParameter("action");

		 
		 if(action!=null)
		 {
		 if(action.equals("delete"))
		 {
			 int veg_id_Delete =  Integer.parseInt(request.getParameter("veg_id_Delete"));
			 int user_id_Delete = Integer.parseInt(request.getParameter("user_id_Delete")); 
			 
			 // we will delete the pending item from cart 
			 
			 carts_service.deletePendingCart(veg_id_Delete, user_id_Delete);
			 
			 System.out.println("VEG ID " + veg_id_Delete );
			 System.out.println("user_id_Delete " +user_id_Delete );
			 response.sendRedirect(request.getContextPath() + "/cart.jsp");
			 
			 
		 }
		 }
		 else
		 {
			 int veg_id =  Integer.parseInt(request.getParameter("vegetable_id"));
			 int user_id = Integer.parseInt(request.getParameter("user_id")); 
			 double quantity_added = Double.parseDouble(request.getParameter("quantity_added"));
		  
		System.out.println("veg ID : "+veg_id );
		System.out.println("user id " + user_id);
		System.out.println("quantity_added " + quantity_added);
	
			VegetablesDetails veg_details = veg_service.fetchVegetableById(veg_id);
			System.out.println("Price======= " + veg_details.getNet_price());
			 double total_price  = veg_details.getNet_price() * quantity_added;
			System.out.println("Total cost "+ total_price) ;
			
			//logic=> 1  check stock is it avialable to add  ?
			//if yes then see if user has alreadyy added same vegetbale So just update quantity 
			//other wise just insert in tbale 
			
			int stock = veg_details.getQuantity();
			
			System.out.println("Stock "+stock);
			if(quantity_added < stock)  //************* && check from cart upadted quantity also 
			{
				carts_service.saveOrUpdateCart(veg_id, user_id, quantity_added, total_price);
				response.sendRedirect(request.getContextPath() + "/home.jsp");
				
			}
			else
			{
				//TRY TO ADD LESEER QANTITY CART ECCEPTION 
			}
			
			
		//	WE WILL FIEST FETCH THE VEGETBALE DETAILS BY VEG ID AND THEN FILL OTAL PRICE 
		 }	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
