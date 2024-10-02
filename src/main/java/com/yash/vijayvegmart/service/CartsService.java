package com.yash.vijayvegmart.service;

import java.util.List;

import com.yash.vijayvegmart.model.Carts;

public interface CartsService {

	
	public void saveOrUpdateCart(int vegId, int userId, double quantityAdded, double totalPrice);

	public List<Carts> fetchAllCartsByUserID(int user_id);
	
	public void deletePendingCart(int vegId, int userId);
	
}
