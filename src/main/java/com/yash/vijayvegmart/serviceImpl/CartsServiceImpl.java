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


	@Override
	public void saveOrUpdateCart(int vegId, int userId, double quantityAdded, double totalPrice) {
		// TODO Auto-generated method stub
		
		try {
			cart_dao.saveOrUpdateCart(vegId, userId, quantityAdded, totalPrice);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	
	@Override
	public List<Carts> fetchAllCartsByUserID(int user_id) {
	return cart_dao.getAllCartsByUserID(user_id);
	}
	 

}
