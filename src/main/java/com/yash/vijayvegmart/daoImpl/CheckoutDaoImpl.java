package com.yash.vijayvegmart.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.yash.vijayvegmart.dao.CheckoutDao;
import com.yash.vijayvegmart.util.DBUtil;

public class CheckoutDaoImpl  implements CheckoutDao{
	private Connection connection;

	public CheckoutDaoImpl() {
		this.connection = DBUtil.getConnection();;
	}
	
	
	/*   When user clciks on contuniue shopping we will update cart again   */
	
	
	
	@Override
	public void updateCartItem(int veg_id, int user_id, int cart_id, double quantity_added, double total_price) {

		 String sql = "UPDATE carts SET quantity_added = ?, total_price = ? WHERE veg_id = ? AND user_id = ? AND cart_id = ?";
	    try (PreparedStatement preparedStatement  = connection.prepareStatement(sql)){
	        
	        // Set the parameters for the prepared statement
	        preparedStatement.setDouble(1, quantity_added);
	        preparedStatement.setDouble(2, total_price);
	        preparedStatement.setInt(3, veg_id);
	        preparedStatement.setInt(4, user_id);
	        preparedStatement.setInt(5, cart_id);
	        
	        // Execute the update
	        int rowsUpdated = preparedStatement.executeUpdate();
	        
	        if (rowsUpdated > 0) {
	            System.out.println("Cart item updated successfully.");
	        } else {
	            System.out.println("No cart item found for the given parameters.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } 
		
	}
	

}
