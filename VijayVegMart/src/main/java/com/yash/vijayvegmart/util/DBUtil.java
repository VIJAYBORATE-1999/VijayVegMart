package com.yash.vijayvegmart.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	
	 private static final String URL = "jdbc:mysql://localhost:3306/vijayvegmartdb";
	    private static final String USER = "root";
	    private static final String PASSWORD = "root";

	    public static Connection getConnection() {
	        try {
	        	
	        	System.out.print("HIIIII  vijayyyy");
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            return DriverManager.getConnection(URL, USER, PASSWORD);
	        } catch (ClassNotFoundException | SQLException e) {
	            e.printStackTrace();
	            throw new RuntimeException("Database connection error");
	        }
	    }


}
