package com.yash.vijayvegmart.dao;

import java.util.List;

import com.yash.vijayvegmart.model.Carts;

public interface CartsDao {
	
	public void saveOrUpdateCart(int vegId, int userId, double quantityAdded, double totalPrice);

	public List<Carts> getAllCartsByUserID(int user_id);
	
	
	public void removePendingCart(int vegId, int userId);
	
	 public Carts getCartDetailsByCartId(int cartId) ;
	 
	 public List<Carts> getCartsByOrderId(String orderId);
}
