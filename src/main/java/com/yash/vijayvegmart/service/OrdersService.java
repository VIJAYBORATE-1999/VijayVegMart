package com.yash.vijayvegmart.service;

import java.sql.SQLException;
import java.util.List;

import com.yash.vijayvegmart.model.Orders;
import com.yash.vijayvegmart.model.OrdersDetails;
import com.yash.vijayvegmart.model.Revenues;

public interface OrdersService {

	String placeOrder(String fullName, String address, String city, String state, int zip,
	
	String cardName, String cardNumber, String expDate, int cvv, double total_order_cost, String[] cart_ids, int user_id );

	public List<Orders> fetchOrdersByIdAndUserId(String orderId, int userId) throws SQLException;
	
	public OrdersDetails fetchOrderDetailsByIdAndUserId(String orderId, int userId) throws SQLException;
	
	public Revenues fetchRevenuesByIdAndUserId(String orderId, int userId) throws SQLException;
	
	public String getOrderDateByOrderId(String orderId) ;
	
	public List<Orders> fetchAllOrdersByUserId(int userId) ;
	
	public Orders getOrderDetailsByOrderIdAndCartId(String orderId, int cartId);
	public boolean checkIfOrderPaymentAlredyDoneByUserForCart(int cart_id);
	
}
