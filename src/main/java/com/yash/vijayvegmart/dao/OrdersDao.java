package com.yash.vijayvegmart.dao;

import java.sql.SQLException;
import java.util.List;

import com.yash.vijayvegmart.model.Orders;
import com.yash.vijayvegmart.model.OrdersDetails;
import com.yash.vijayvegmart.model.Revenues;

public interface OrdersDao {

	
	void insertOrder(Orders order);


	void insertOrderDetails(OrdersDetails order_details);


	void updateCartOrder_status(int cart_id, int user_id, String string);


	void insertRevenues(Revenues revenues);

	
	public List<Orders> getOrdersByIdAndUserId(String orderId, int userId) throws SQLException;
	
	public OrdersDetails getOrderDetailsByIdAndUserId(String orderId, int userId) throws SQLException;
	
	public Revenues getRevenuesByIdAndUserId(String orderId, int userId) throws SQLException;
	
	public String getOrderDateByOrderId(String orderId);
	
	public List<Orders> getAllOrdersByUserId(int userId) ;
	
	public Orders getOrderDetailsByOrderIdAndCartId(String orderId, int cartId) ;
	
}
