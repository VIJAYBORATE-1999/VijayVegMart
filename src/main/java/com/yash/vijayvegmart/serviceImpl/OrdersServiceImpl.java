package com.yash.vijayvegmart.serviceImpl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import com.yash.vijayvegmart.dao.OrdersDao;
import com.yash.vijayvegmart.daoImpl.OrdersDaoImpl;
import com.yash.vijayvegmart.daoImpl.UsersDAOImpl;
import com.yash.vijayvegmart.model.Orders;
import com.yash.vijayvegmart.model.OrdersDetails;
import com.yash.vijayvegmart.model.Revenues;
import com.yash.vijayvegmart.service.OrdersService;

public class OrdersServiceImpl implements OrdersService {

	private OrdersDao order_dao;

	public OrdersServiceImpl() {
        this.order_dao = new OrdersDaoImpl();             
    }

	
	

	//Logic : As user click on PLACE ORDER BUTTON  , WE DO FOUR THINGS 

		//1) Table =>"Orders "  update cart status =>payment_status_by_user "payment done"

		//2) Table => Order_details  
		
		//3) Table => carts    intially the order_status "Incomplete" as user add item in cart 
		// so after final order set      order_status "Compelted"
		// we will fetch only those carts in UI whose   order_status "Incomplete"

		//4) Table => Revenue 
	
	
	
	

	
	
@Override
public void placeOrder(String fullName, String address, String city, String state, int zip,
		String cardName, String cardNumber, String expDate, int cvv, double total_order_cost , String [] cart_ids , int user_id) {

	
	// Logic for order_id generation :
	String order_id=null;
	String order_id_generated = UUID.randomUUID().toString();
	DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
	String currentDateTime = LocalDateTime.now().format(f);
	order_id = order_id_generated + currentDateTime;
	System.out.println(order_id);
	
	
	/* 1) Table =>"Orders "  update cart status =>payment_status_by_user "payment done"   */

	for(int i=0 ; i<cart_ids.length; i++)
	{	
		int cart_id = Integer.parseInt(cart_ids[i]);		
	Orders order = new Orders(order_id, user_id,  cart_id, "paid", "pending");
	order_dao.insertOrder(order);
	}
	
	
	
	//2) Table => Order_details  

	
	OrdersDetails order_details = new OrdersDetails(order_id, fullName, address, city, state, zip, cardName, cardNumber, expDate, cvv, user_id, total_order_cost);
	order_dao.insertOrderDetails(order_details);
	
	//3) Table => carts  Update order_status to "Completed"
	
	for(int i=0 ; i<cart_ids.length; i++)
	{	
		int cart_id = Integer.parseInt(cart_ids[i]);	
	order_dao.updateCartOrder_status(cart_id ,user_id, "paid");
	
	}
	
	
	// 4) Shop revenue Update 
	double tax = 56;
	Revenues revenues = new Revenues(order_id_generated, total_order_cost,tax);
	
	order_dao.insertRevenues(revenues);
	
	
}
	
	
	
}
