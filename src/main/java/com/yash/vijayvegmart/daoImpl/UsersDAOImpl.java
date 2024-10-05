package com.yash.vijayvegmart.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.yash.vijayvegmart.dao.UsersDao;
import com.yash.vijayvegmart.model.Users;
import com.yash.vijayvegmart.util.DBUtil;

public class UsersDAOImpl implements UsersDao {

	private Connection connection;
    public UsersDAOImpl() {
    	
   
        this.connection = DBUtil.getConnection();
    }

    
  /*-------------------------REGISTRATION DAO starts -----------------------------------------------*/
    public List<String> getAllUsernames() {
        String query = "SELECT username FROM users";
        List<String> usernames = new ArrayList<String> ();
        
        try (PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                usernames.add(rs.getString("username"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return usernames;
    }




    public List<String> getAllPasswords() {
        String query = "SELECT password FROM users";
        List<String> passwords = new ArrayList<>();
        
        try (PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                passwords.add(rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return passwords;
    }





    public List<String> getAllEmails() {
        String query = "SELECT email FROM users";
        List<String> emails = new ArrayList<>();
        
        try (PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                emails.add(rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return emails;
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

    
    /*-------------------------REGISTRATION DAO ENDS ----------------------------------------------------*/
    
    
    
    /*----------------------LOGIN MODULE STARTS --------------------------------------------------*/
    
    @Override
    public List<Users> fetchAllUsers() {
        List<Users> userList = new ArrayList<>();
        String query = "SELECT * FROM users";
        
        try (PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Users user = new Users();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setIsapproved(rs.getString("isapproved"));
                user.setIsactive(rs.getString("isactive"));
                user.setUsertype(rs.getString("usertype"));
                user.setMobileno(rs.getString("mobileno"));
                user.setAddress(rs.getString("address"));
                user.setState(rs.getString("state"));
                user.setCity(rs.getString("city"));
                user.setPincode(rs.getInt("pincode"));
                
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return userList;
    }

    
    
    /*----------------------LOGIN MODULE ENDS --------------------------------------------------*/
    
    
    
    /* ------------------ CHECK ADMIN EXISTS OR NOT ----------------------/ 
     * 
     * 
     */
    
    @Override
    public boolean doesAdminUserExist() {
    	 String query = "SELECT COUNT(*) FROM users WHERE usertype = ?";
    	    try (PreparedStatement ps = connection.prepareStatement(query)) {
    	        ps.setString(1, "admin");
    	        ResultSet rs = ps.executeQuery();
    	        if (rs.next()) {
    	            int count = rs.getInt(1);
    	            return count > 0; // Returns true if count is greater than 0
    	        }
    	    } catch (SQLException e) {
    	        e.printStackTrace();
    	    }
    	    return false; // Return false if no admin user is found or an exception occurs
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
