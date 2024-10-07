package com.yash.vijayvegmart.serviceImpl;

import com.yash.vijayvegmart.dao.CartsDao;
import com.yash.vijayvegmart.dao.CheckoutDao;
import com.yash.vijayvegmart.daoImpl.CartDaoImpl;
import com.yash.vijayvegmart.daoImpl.CheckoutDaoImpl;
import com.yash.vijayvegmart.service.CheckoutService;

public class CheckoutServiceImpl  implements CheckoutService{
	
	private CheckoutDao cart_check_out_dao;

	public CheckoutServiceImpl() {
		cart_check_out_dao= new CheckoutDaoImpl();
		
		
	}  
	
	
	/* -------CLOSE  DB   CONNECTION -----------   */
	
	@Override
	public void closeConnection() {
		// TODO Auto-generated method stub
	cart_check_out_dao.closeConnection();
	}
	
	
	
	//When user clicks on Continue shopping 	
		@Override
		public void updateCartItem(int veg_id, int user_id, int cart_id, double quantity_added, double total_price) {
			cart_check_out_dao.updateCartItem(veg_id, user_id, cart_id, quantity_added, total_price);
			
		}

}
