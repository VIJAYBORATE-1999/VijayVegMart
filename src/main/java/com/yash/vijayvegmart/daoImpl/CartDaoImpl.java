package com.yash.vijayvegmart.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yash.vijayvegmart.dao.CartsDao;
import com.yash.vijayvegmart.model.Carts;
import com.yash.vijayvegmart.util.DBUtil;

public class CartDaoImpl implements CartsDao {
	private Connection connection;

	public CartDaoImpl() {
		this.connection = DBUtil.getConnection();
	}
	


	/**************When user Clicks Add to cart Button *********///////////////
	
	public void saveOrUpdateCart(int vegId, int userId, double quantityAdded, double totalPrice) {
        String checkQuery = "SELECT * FROM carts WHERE veg_id = ? AND user_id = ?";
        String insertQuery = "INSERT INTO carts (user_id, veg_id, quantity_added, total_price, created_at, updated_at) VALUES (?, ?, ?, ?, NOW(), NOW())";
        String updateQuery = "UPDATE carts SET quantity_added = quantity_added + ?, total_price = total_price + ?, updated_at = NOW() WHERE veg_id = ? AND user_id = ?";

        try (PreparedStatement checkStmt = connection.prepareStatement(checkQuery)) {
            checkStmt.setInt(1, vegId);
            checkStmt.setInt(2, userId);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                // Update existing record
                try (PreparedStatement updateStmt = connection.prepareStatement(updateQuery)) {
                    updateStmt.setDouble(1, quantityAdded);
                    updateStmt.setDouble(2, totalPrice);
                    updateStmt.setInt(3, vegId);
                    updateStmt.setInt(4, userId);
                    updateStmt.executeUpdate();
                }
            } else {
                // Insert new record
                try (PreparedStatement insertStmt = connection.prepareStatement(insertQuery)) {
                    insertStmt.setInt(1, userId);
                    insertStmt.setInt(2, vegId);
                    insertStmt.setDouble(3, quantityAdded);
                    insertStmt.setDouble(4, totalPrice);
                    insertStmt.executeUpdate();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	
	
	/*  GET CART INFO AS USER LOGINS  */
	
	@Override
	public List<Carts> getAllCartsByUserID(int user_id) {
	    List<Carts> cartsList = new ArrayList<Carts>();
	    String query = "SELECT * FROM carts WHERE user_id = ?";

	    try (PreparedStatement ps = connection.prepareStatement(query)) {
	        ps.setInt(1, user_id);
	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	            Carts carts = new Carts();
	            carts.setCart_Id(rs.getInt("cart_id"));
	            carts.setCreatedAt(rs.getTimestamp("created_at"));
	            carts.setUpdatedAt(rs.getTimestamp("updated_at"));
	            carts.setUser_Id(rs.getInt("user_id"));
	            carts.setVeg_id(rs.getInt("veg_id"));
	            carts.setQuantity_added(rs.getDouble("quantity_added"));
	            carts.setTotal_Price(rs.getDouble("total_price"));

	            cartsList.add(carts); // Add cart details to the list
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return cartsList;  // Return the list of cart details
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
