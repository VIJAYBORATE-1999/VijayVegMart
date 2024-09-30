package com.yash.vijayvegmart.dao;

import com.yash.vijayvegmart.model.Orders;
import com.yash.vijayvegmart.model.OrdersDetails;
import com.yash.vijayvegmart.model.Revenues;

public interface OrdersDao {

	
	void insertOrder(Orders order);


	void insertOrderDetails(OrdersDetails order_details);


	void updateCartOrder_status(int cart_id, int user_id, String string);


	void insertRevenues(Revenues revenues);


	
}
