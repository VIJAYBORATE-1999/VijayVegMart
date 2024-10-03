package com.yash.vijayvegmart.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import com.yash.vijayvegmart.dao.UsersDao;
import com.yash.vijayvegmart.model.Users;
import com.yash.vijayvegmart.util.DBUtil;

public class UsersDAOImpl implements UsersDao {

	private Connection connection;
    public UsersDAOImpl() {
    	
   
        this.connection = DBUtil.getConnection();
    }

    @Override
    public void saveUser(Users user) {
        String query = "INSERT INTO users (username, password, email ,isapproved, isactive , usertype) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getIsapproved());
            ps.setString(5, user.getIsactive());
            ps.setString(6, user.getUsertype());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Users> getUserByUsername(String username) {
    	
    	
  
        String query = "SELECT * FROM users WHERE username = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Users user = new Users();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setIsactive(rs.getString("isactive"));
                user.setIsapproved(rs.getString("isapproved"));
                user.setUsertype(rs.getString("usertype"));
                return Optional.of(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    
    @Override
    public Optional<Users> getUserByEmail(String email) {
        String query = "SELECT * FROM users WHERE email = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Users user = new Users();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setIsactive(rs.getString("isactive"));
                user.setIsapproved(rs.getString("isapproved"));
                user.setUsertype(rs.getString("usertype"));
                return Optional.of(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    
    
}
