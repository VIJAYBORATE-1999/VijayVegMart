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
        String checkQuery = "SELECT * FROM carts WHERE veg_id = ? AND user_id = ? AND order_status = ?";
        String insertQuery = "INSERT INTO carts (user_id, veg_id, quantity_added, total_price, created_at, updated_at , order_status ) VALUES (?, ?, ?, ?, NOW(), NOW(),?)";
        String updateQuery = "UPDATE carts SET quantity_added = quantity_added + ?, total_price = total_price + ?, updated_at = NOW() WHERE veg_id = ? AND user_id = ?";

        
       
        try (PreparedStatement checkStmt = connection.prepareStatement(checkQuery)) {
            checkStmt.setInt(1, vegId);
            checkStmt.setInt(2, userId);
            checkStmt.setString(3, "pending");
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
                	 System.out.print("HIIIIIIIIIIIIcartttttt");
                    insertStmt.setInt(1, userId);
                    insertStmt.setInt(2, vegId);
                    insertStmt.setDouble(3, quantityAdded);
                    insertStmt.setDouble(4, totalPrice);
                    String s ="pending";
                    insertStmt.setString(5,s);
                    insertStmt.executeUpdate();
                }
                catch (SQLException e) {
                    e.printStackTrace();
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
	    String query = "SELECT * FROM carts WHERE user_id = ? and order_status='pending'";

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

	
	
	
	/* deleet item from cart as user remove item from cart   */
	
	public void removePendingCart(int vegId, int userId)
	{
		 String query = "DELETE FROM carts WHERE veg_id = ? AND user_id = ? AND order_status = 'pending'";

	        try (PreparedStatement ps = connection.prepareStatement(query)) {
	            ps.setInt(1, vegId);  // Set veg_id parameter
	            ps.setInt(2, userId);  // Set user_id parameter

	            // Execute the delete operation
	            int rowsDeleted = ps.executeUpdate();

	            if (rowsDeleted > 0) {
	                System.out.println("Cart record deleted successfully.");
	            } else {
	                System.out.println("No pending cart found for the given veg_id and user_id.");
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

		
	
	/*  GET CART DETAIL BY CART_ID */
	
	
	 public Carts getCartDetailsByCartId(int cartId) {
	        String query = "SELECT cart_id, created_at, updated_at, user_id, veg_id, quantity_added, total_price, order_status "
	                     + "FROM carts WHERE cart_id = ?";

	        Carts cart = null;

	        try (PreparedStatement ps = connection.prepareStatement(query)) {
	            ps.setInt(1, cartId);  // Set cart_id as a parameter

	            try (ResultSet rs = ps.executeQuery()) {
	                if (rs.next()) {
	                    // Create Carts object and populate it with data from ResultSet
	                    cart = new Carts();
	                    cart.setCart_Id(rs.getInt("cart_id"));
	                    cart.setCreatedAt(rs.getTimestamp("created_at"));
	                    cart.setUpdatedAt(rs.getTimestamp("updated_at"));
	                    cart.setUser_Id(rs.getInt("user_id"));
	                    cart.setVeg_id(rs.getInt("veg_id"));
	                    cart.setQuantity_added(rs.getDouble("quantity_added"));
	                    cart.setTotal_Price(rs.getDouble("total_price"));
	                    cart.setOrder_status(rs.getString("order_status"));
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return cart;  // Return the Carts object
	    }
		
	
	

}
