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
	
	
	// When user vists My carts Page 
	@Override
	public List<Carts> fetchAllCartsByUserID(int user_id) {
	return cart_dao.getAllCartsByUserID(user_id);
	}
	 

	//When user clicks on Continue shopping 	
	@Override
	public void updateCartItem(int veg_id, int user_id, int cart_id, double quantity_added, double total_price) {
	 cart_dao.updateCartItem(veg_id, user_id, cart_id, quantity_added, total_price);
		
	}
}
