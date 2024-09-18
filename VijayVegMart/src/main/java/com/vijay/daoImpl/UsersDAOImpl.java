package com.vijay.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import com.vijay.dao.UsersDao;
import com.vijay.model.Users;
import com.vijay.util.DBUtil;

public class UsersDAOImpl implements UsersDao {

	////Why ???
	// private Connection connection= DBUtil.getConnection();
	private Connection connection;
    public UsersDAOImpl() {
    	
    	System.out.println("Vijayyyy");
        this.connection = DBUtil.getConnection();
    }

    @Override
    public void saveUser(Users user) {
        String query = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Users> getUserByUsername(String username) {
    	
    	
    	System.out.println( "YYYYY" );
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
                return Optional.of(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

}
