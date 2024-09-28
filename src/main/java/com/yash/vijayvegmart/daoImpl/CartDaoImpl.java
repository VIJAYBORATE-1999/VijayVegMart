package com.yash.vijayvegmart.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.yash.vijayvegmart.dao.CartsDao;
import com.yash.vijayvegmart.util.DBUtil;

public class CartDaoImpl implements CartsDao {
	private Connection connection;

	public CartDaoImpl() {
		this.connection = DBUtil.getConnection();
	}
	


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
	
	
	
	

}
