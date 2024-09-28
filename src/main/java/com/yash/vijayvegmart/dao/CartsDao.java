package com.yash.vijayvegmart.dao;

public interface CartsDao {
	
	public void saveOrUpdateCart(int vegId, int userId, double quantityAdded, double totalPrice);

}
