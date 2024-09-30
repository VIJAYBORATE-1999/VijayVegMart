package com.yash.vijayvegmart.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.yash.vijayvegmart.dao.OrdersDao;
import com.yash.vijayvegmart.model.Orders;
import com.yash.vijayvegmart.model.OrdersDetails;
import com.yash.vijayvegmart.model.Revenues;
import com.yash.vijayvegmart.util.DBUtil;

public class OrdersDaoImpl implements OrdersDao{
	private Connection connection;

	public OrdersDaoImpl() {
		this.connection = DBUtil.getConnection();
	}

	
	
	
	//1) 	Table =>"Orders "  update cart status =>payment_status_by_user "payment done"
	
	
	//2) Table => carts    intially the order_status "Incomplete" as user add item in cart 
	// so after final order set      order_status "Compelted"
	// we will fetch only those carts in UI whose   order_status "Incomplete"
	
	
	//3) Tbale 
	
	
	@Override
	public void insertOrder(Orders order) {
		// TODO Auto-generated method stub
	
		 String query = "INSERT INTO orders (order_id, user_id , cart_id, payment_status_by_user, vendor_action_status) VALUES (?, ?, ?, ?, ?)";

	        try (PreparedStatement ps = connection.prepareStatement(query)) {
	            
	            ps.setString(1, order.getOrderId());
	            ps.setInt(2, order.getUserId());
	            ps.setInt(3, order.getCartId());
	            ps.setString(4, order.getPaymentStatusByUser());
	            ps.setString(5, order.getVendorActionStatus());

	            ps.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

		
	
	
	
	
	@Override
	public void insertOrderDetails(OrdersDetails order_details) {
		
		 String query = "INSERT INTO order_details (order_id, full_name, address, city, state, zip, card_name, card_number, exp_date, cvv, user_id, total_order_cost) "
                 + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    try (PreparedStatement ps = connection.prepareStatement(query)) {
        
        // Set the parameters for the query
        ps.setString(1, order_details.getOrderId());
        ps.setString(2, order_details.getFullName());
        ps.setString(3, order_details.getAddress());
        ps.setString(4, order_details.getCity());
        ps.setString(5, order_details.getState());
        ps.setInt(6, order_details.getZip());
        ps.setString(7, order_details.getCardName());
        ps.setString(8, order_details.getCardNumber());
        ps.setString(9, order_details.getExpDate());
        ps.setInt(10, order_details.getCvv());
        ps.setInt(11, order_details.getUserId());
        ps.setDouble(12, order_details.getTotalOrderCost());
        ps.executeUpdate();
        
    } catch (SQLException e) {
        e.printStackTrace();
    }

		
	}
		
	

	
	@Override
	public void updateCartOrder_status(int cart_id, int user_id, String order_status) {
		
		
		String query = "UPDATE carts SET order_status = ? WHERE cart_id = ? AND user_id = ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {

            // Set the parameters
            ps.setString(1, order_status);
            ps.setInt(2,  cart_id);
            ps.setInt(3, user_id);

            // Execute the update
            int rowsAffected = ps.executeUpdate();
            
            if (rowsAffected > 0) {
                System.out.println("Order status updated successfully!");
            } else {
                System.out.println("No records found with the given cart_id and user_id.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
		// TODO Auto-generated method stub
		
	}
	
	
	

	@Override
	public void insertRevenues(Revenues revenues) {
		// TODO Auto-generated method stub
		
		String query = "INSERT INTO revenues (order_id, total_payment, tax) VALUES (?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(query)) {

            // Set the parameters for the query
            ps.setString(1, revenues.getOrderId());
            ps.setDouble(2, revenues.getTotalPayment());
            ps.setDouble(3, revenues.getTax());

            // Execute the insert operation
            ps.executeUpdate();

            System.out.println("Revenue inserted successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
		
		
		
		
	}
	
	
	
	
	
	
		
	}
	
	
	
