package com.yash.vijayvegmart.dao;

public interface CheckoutDao {

	public void updateCartItem(int veg_id, int user_id, int cart_id, double quantity_added, double total_price);

}
