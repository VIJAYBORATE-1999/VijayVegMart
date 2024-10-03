package com.yash.vijayvegmart.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yash.vijayvegmart.dao.VendorDao;
import com.yash.vijayvegmart.model.VO.VendorOrdersVO;
import com.yash.vijayvegmart.util.DBUtil;

public class VendorDaoImpl implements VendorDao{

	private Connection connection;

	public VendorDaoImpl() {
		super();
		this.connection = DBUtil.getConnection();
	}
	
	
	
	 public List<VendorOrdersVO> getVendorOrders(int vendor_id, String vendorActionStatus) {
		 
	        List<VendorOrdersVO> vendorOrdersList = new ArrayList<VendorOrdersVO>();

	        // SQL query to retrieve orders for a given vendor_id and vendor_action_status
	        String query = "SELECT o.order_id, o.user_id, o.order_date, o.cart_id, c.quantity_added, c.veg_id, vd.veg_name, c.total_price " +
                    "FROM orders o " +
                    "JOIN carts c ON o.cart_id = c.cart_id AND o.user_id = c.user_id " +
                    "JOIN vegetables_details vd ON c.veg_id = vd.veg_id " +
                    "WHERE vd.vendor_id = ? AND o.vendor_action_status = ?";

	        // Assuming you have a method to get the database connection
	   
	      

	        try (PreparedStatement ps = connection.prepareStatement(query)){
	           
	          
	            ps.setInt(1, vendor_id); // Set vendor_id parameter
	            ps.setString(2, vendorActionStatus); // Set vendor_action_status parameter

	            ResultSet rs = ps.executeQuery();

	            while (rs.next()) {
	                VendorOrdersVO order = new VendorOrdersVO();
	                order.setOrder_id(rs.getString("order_id"));
	                order.setUser_id(rs.getInt("user_id"));
	                order.setOrder_date(rs.getTimestamp("order_date"));
	                order.setQuantity_added(rs.getDouble("quantity_added"));
	                order.setCart_id(rs.getInt("cart_id")); // Fetch cart_id
	                order.setVeg_id(rs.getInt("veg_id"));
	                order.setVeg_name(rs.getString("veg_name"));
	                order.setTotal_price(rs.getDouble("total_price"));

	                vendorOrdersList.add(order);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } 

	        return vendorOrdersList;
	    }
	
	
	 
	    public boolean updateVendorActionStatus(String orderId, int userId, int cartId, String newVendorActionStatus) {
	        // SQL query to update the vendor_action_status field
	        String query = "UPDATE orders " +
	                       "SET vendor_action_status = ? " +
	                       "WHERE order_id = ? AND user_id = ? AND cart_id = ?";

	        try (PreparedStatement ps = connection.prepareStatement(query)) {
	            // Set parameters for the query
	            ps.setString(1, newVendorActionStatus);  // Set new vendor_action_status
	            ps.setString(2, orderId);                // Set order_id
	            ps.setInt(3, userId);                    // Set user_id
	            ps.setInt(4, cartId);                    // Set cart_id

	            // Execute the update query
	            int rowsAffected = ps.executeUpdate();

	            // Return true if the update was successful (i.e., at least one row was updated)
	            return rowsAffected > 0;

	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;  // Return false if there was an exception or no rows were updated
	        }
	    }
	    
	    
}
