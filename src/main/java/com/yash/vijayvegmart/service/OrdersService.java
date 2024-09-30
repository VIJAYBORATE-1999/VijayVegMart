package com.yash.vijayvegmart.service;

public interface OrdersService {

	String placeOrder(String fullName, String address, String city, String state, int zip,
			String cardName, String cardNumber, String expDate, int cvv, double total_order_cost, String[] cart_ids, int user_id );

}
