package com.yash.vijayvegmart.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import com.yash.vijayvegmart.dao.VegetablesDao;
import com.yash.vijayvegmart.model.VegetablesDetails;
import com.yash.vijayvegmart.util.DBUtil;

public class VegetablesDaoImpl implements VegetablesDao{
	
private Connection connection;
public VegetablesDaoImpl() {
		this.connection = DBUtil.getConnection();
	}




/*------------------------ FIND VEGETABLE BY VENDOR ID AND VEGETBALE NAME --------------------------------*/

@Override
public Optional<VegetablesDetails> checkVegetableExistsByVendor(int vendor_id, String veg_name) {
	// TODO Auto-generated method stub
	
	String query = "SELECT * FROM vegetables_details WHERE vendor_id = ? AND veg_name = ?";
	
	try (PreparedStatement ps = connection.prepareStatement(query)) {
		ps.setInt(1,vendor_id);
		ps.setString(2, veg_name);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			VegetablesDetails details = new VegetablesDetails();
			details.setVegId(rs.getInt("veg_id"));
			details.setVendorId(rs.getInt("vendor_id"));
			details.setVegCategory(rs.getString("veg_category"));
			details.setVegPicName(rs.getString("veg_pic_name"));
			details.setVegName(rs.getString("veg_name"));
			details.setCreatedAt(rs.getTimestamp("created_at"));
			details.setDescription(rs.getString("description"));
			details.setPricePerPiece(rs.getDouble("price_per_piece"));
			details.setQuantity(rs.getInt("quantity"));
			details.setUpdatedAt(rs.getTimestamp("updated_at"));
				return Optional.of(details);
		}
		
	} catch (SQLException e) {
        e.printStackTrace();
    }
    return Optional.empty();
}



/*----------------------------INSERT(ADD) VEGETBALE INTO DATABASE --------------------------------------*/

@Override
public void saveVegetable(VegetablesDetails details) {
	
	
	System.out.print("The data here is ");
	
	  String query = "INSERT INTO vegetables_details (vendor_id, veg_name , quantity , description , price_per_piece , veg_pic_name, veg_category) VALUES (?, ?, ?, ?, ?, ?, ?)";
      try (PreparedStatement ps = connection.prepareStatement(query)) {
          ps.setInt(1, details.getVendorId());
          ps.setString(2, details.getVegName());
          ps.setInt(3, details.getQuantity());
          ps.setString(4,details.getDescription()); 
          ps.setDouble(5,details.getPricePerPiece() );
          ps.setString(6, details.getVegPicName());
          ps.setString(7, details.getVegCategory());
          ps.executeUpdate();
      } catch (SQLException e) {
          e.printStackTrace();
      }

	
}



}
