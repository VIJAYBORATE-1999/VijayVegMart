package com.yash.vijayvegmart.serviceImpl;

import java.util.List;

import com.yash.vijayvegmart.dao.CartsDao;
import com.yash.vijayvegmart.dao.VegetablesDao;
import com.yash.vijayvegmart.daoImpl.CartDaoImpl;
import com.yash.vijayvegmart.model.Carts;
import com.yash.vijayvegmart.service.CartsService;

public class CartsServiceImpl  implements CartsService{
	
	private CartsDao cart_dao;
	
	
	public CartsServiceImpl() {
		this.cart_dao = new CartDaoImpl();
	}

	
	
	/* -------CLOSE  DB   CONNECTION -----------   */
	
	@Override
	public void closeConnection() {
		// TODO Auto-generated method stub
	cart_dao.closeConnection();	
	}
	/* -------CLOSE  DB   CONNECTION -----------   */

	///When Customer clicks on Add To Cart Button on Home page 
	@Override
	public void saveOrUpdateCart(int vegId, int userId, double quantityAdded, double totalPrice) {
		// TODO Auto-generated method stub
		
		try {
			cart_dao.saveOrUpdateCart(vegId, userId, quantityAdded, totalPrice);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	// When user vists My carts Page and checkout page 
	@Override
	public List<Carts> fetchAllCartsByUserID(int user_id) {
	return cart_dao.getAllCartsByUserID(user_id);
	}
	 

	
	// as user clciks on remove item in cart 
	
	@Override
	public void deletePendingCart(int vegId, int userId) {
		
		cart_dao.removePendingCart(vegId, userId);
	}
	
	
	
	
	@Override
	public Carts fetchCartDetailsByCartId(int cartId) {
		// TODO Auto-generated method stub
		return cart_dao.getCartDetailsByCartId(cartId);
	}
	
	
	
	 public List<Carts> getCartsByOrderId(String orderId)
	 {
		 
		 return cart_dao.getCartsByOrderId(orderId);
	 }
}
