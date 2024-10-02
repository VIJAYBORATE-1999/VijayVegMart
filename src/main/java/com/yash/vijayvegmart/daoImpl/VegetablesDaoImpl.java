package com.yash.vijayvegmart.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.yash.vijayvegmart.dao.VegetablesDao;
import com.yash.vijayvegmart.model.VegetablesDetails;
import com.yash.vijayvegmart.util.DBUtil;

public class VegetablesDaoImpl implements VegetablesDao{
	
private Connection connection;
public VegetablesDaoImpl() {
		this.connection = DBUtil.getConnection();
	}



//To upload a vegetable we will do => checkVegetableExistsByVendor()  & then saveVegetable()
/*-PART 1----------------------- FIND VEGETABLE BY VENDOR ID AND VEGETBALE NAME --------------------------------*/

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



/*-PART 2 ---------------------------INSERT(ADD) VEGETBALE INTO DATABASE --------------------------------------*/

@Override
public void saveVegetable(VegetablesDetails details) {
	
	
	
	
	  String query = "INSERT INTO vegetables_details (vendor_id, veg_name , quantity , description , price_per_piece , veg_pic_name, veg_category , discount_per_piece , net_price ) VALUES (?, ?, ?, ?, ?, ?, ?,?,?)";
      try (PreparedStatement ps = connection.prepareStatement(query)) {
          ps.setInt(1, details.getVendorId());
          ps.setString(2, details.getVegName());
          ps.setInt(3, details.getQuantity());
          ps.setString(4,details.getDescription()); 
          ps.setDouble(5,details.getPricePerPiece() );
          ps.setString(6, details.getVegPicName());
          ps.setString(7, details.getVegCategory());
          ps.setDouble(8, details.getDiscount_per_piece());
          ps.setDouble(9, details.getNet_price());
         //net_price
          ps.executeUpdate();
          System.out.print("SAVED VEGETABLE IN DATABASE ");
      } catch (SQLException e) {
          e.printStackTrace();
      }

	
}



/*----------------------------FETCH ALL  VEGETBALE FROM  DATABASE --------------------------------------*/
 
@Override
	public List<VegetablesDetails> getAllVegetablesByCategory(String Category) {
	
	 List<VegetablesDetails> vegetablesList = new ArrayList<VegetablesDetails>();
	 VegetablesDetails details  =  null;
     String query = "SELECT * FROM vegetables_details WHERE veg_category= ?";  // SQL query to fetch all vegetables
     
     try (PreparedStatement ps = connection.prepareStatement(query)) {
    	 ps.setString(1, Category);
         ResultSet rs = ps.executeQuery();
         while (rs.next()) {
        	 details = new VegetablesDetails();
             details.setVegId(rs.getInt(1));
             details.setVendorId(rs.getInt(4));
             details.setVegName(rs.getString(5));
             details.setQuantity(rs.getInt(6));
             details.setDescription(rs.getString(7));
             details.setPricePerPiece(rs.getDouble(8));
             details.setVegPicName(rs.getString(9));
             details.setVegCategory(rs.getString(10));
             details.setDiscount_per_piece(rs.getDouble(11));
             details.setNet_price(rs.getDouble(12));
             details.setCreatedAt(rs.getTimestamp(2));
             details.setUpdatedAt(rs.getTimestamp(3));

             vegetablesList.add(details);  // Add the details to the list
         }
     } catch (SQLException e) {
         e.printStackTrace();
     }

     return vegetablesList;  // Return the list of vegetables
 }



/*----------------------------FETCH ALL VEGETBALE FOR A PARTICULAR  VENDOR FROM DATABASE --------------------------------------*/

//WE WILL FETCH VEEGTABLES UPLOADED BY PARTICULAR VENDOR 
@Override
public List<VegetablesDetails> getAllVegetablesByVendorId(int vendor_id) {
	
	 List<VegetablesDetails> vegetablesList = new ArrayList<VegetablesDetails>();
	 VegetablesDetails details  =  null;
    String query = "SELECT * FROM vegetables_details WHERE vendor_id = ?";  // SQL query to fetch all vegetables by a vendor id
    
    try (PreparedStatement ps = connection.prepareStatement(query)) {
    	ps.setInt(1,vendor_id);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
       	 details = new VegetablesDetails();
            details.setVegId(rs.getInt(1));
            details.setVendorId(rs.getInt(4));
            details.setVegName(rs.getString(5));
            details.setQuantity(rs.getInt(6));
            details.setDescription(rs.getString(7));
            details.setPricePerPiece(rs.getDouble(8));
            details.setVegPicName(rs.getString(9));
            details.setVegCategory(rs.getString(10));
            details.setDiscount_per_piece(rs.getDouble(11));
            details.setNet_price(rs.getDouble(12));
            details.setCreatedAt(rs.getTimestamp(2));
            details.setUpdatedAt(rs.getTimestamp(3));

            vegetablesList.add(details);  // Add the details to the list
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return vegetablesList;  // Return the list of vegetables
}



/*----------------------------FETCH IN STOCK  VEGETBALE FOR A PARTICULAR  VENDOR FROM DATABASE --------------------------------------*/


@Override
	public List<VegetablesDetails> getAllInStockVegetablesByVendorId(int vendor_id) {
	 List<VegetablesDetails> vegetablesList = new ArrayList<VegetablesDetails>();
	 VegetablesDetails details  =  null;
    String query = "SELECT * FROM vegetables_details WHERE vendor_id = ? AND quantity > 0 ";  // SQL query to fetch all vegetables by a vendor id
    
    try (PreparedStatement ps = connection.prepareStatement(query)) {
    	ps.setInt(1,vendor_id);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
       	 details = new VegetablesDetails();
            details.setVegId(rs.getInt(1));
            details.setVendorId(rs.getInt(4));
            details.setVegName(rs.getString(5));
            details.setQuantity(rs.getInt(6));
            details.setDescription(rs.getString(7));
            details.setPricePerPiece(rs.getDouble(8));
            details.setVegPicName(rs.getString(9));
            details.setVegCategory(rs.getString(10));
            details.setDiscount_per_piece(rs.getDouble(11));
            details.setNet_price(rs.getDouble(12));
            details.setCreatedAt(rs.getTimestamp(2));
            details.setUpdatedAt(rs.getTimestamp(3));

            vegetablesList.add(details);  // Add the details to the list
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return vegetablesList;  // Return the list of vegetables
	}



/*----------------------------FETCH OUT OF STOCK  VEGETBALE FOR A PARTICULAR  VENDOR FROM DATABASE --------------------------------------*/

@Override
	public List<VegetablesDetails> getAllOutOfStockVegetablesByVendorId(int vendor_id) {
	 List<VegetablesDetails> vegetablesList = new ArrayList<VegetablesDetails>();
	 VegetablesDetails details  =  null;
    String query = "SELECT * FROM vegetables_details WHERE vendor_id = ? AND quantity =0";  // SQL query to fetch all vegetables by a vendor id
    
    try (PreparedStatement ps = connection.prepareStatement(query)) {
    	ps.setInt(1,vendor_id);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
       	 details = new VegetablesDetails();
            details.setVegId(rs.getInt(1));
            details.setVendorId(rs.getInt(4));
            details.setVegName(rs.getString(5));
            details.setQuantity(rs.getInt(6));
            details.setDescription(rs.getString(7));
            details.setPricePerPiece(rs.getDouble(8));
            details.setVegPicName(rs.getString(9));
            details.setVegCategory(rs.getString(10));
            details.setDiscount_per_piece(rs.getDouble(11));
            details.setNet_price(rs.getDouble(12));
            details.setCreatedAt(rs.getTimestamp(2));
            details.setUpdatedAt(rs.getTimestamp(3));

            vegetablesList.add(details);  // Add the details to the list
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return vegetablesList;  // Return the list of vegetables
	}
	


/* ----------------------------FTECH A VEGETBALE INFO BY VEG ID--------------------------- */


@Override
public Optional<VegetablesDetails> getVegetableById(int vegId) {
    String query = "SELECT * FROM vegetables_details WHERE veg_id = ?";
    
    try (PreparedStatement ps = connection.prepareStatement(query)) {
        ps.setInt(1, vegId);
        ResultSet rs = ps.executeQuery();
        
        if (rs.next()) {
            VegetablesDetails details = new VegetablesDetails();
            details.setVegId(rs.getInt("veg_id"));
            details.setVendorId(rs.getInt("vendor_id"));
            details.setVegName(rs.getString("veg_name"));
            details.setQuantity(rs.getInt("quantity"));
            details.setDescription(rs.getString("description"));
            details.setPricePerPiece(rs.getDouble("price_per_piece"));
            details.setVegPicName(rs.getString("veg_pic_name"));
            details.setVegCategory(rs.getString("veg_category"));
            details.setDiscount_per_piece(rs.getDouble("discount_per_piece"));
            details.setNet_price(rs.getDouble("net_price"));
            details.setCreatedAt(rs.getTimestamp("created_at"));
            details.setUpdatedAt(rs.getTimestamp("updated_at"));
            
            return Optional.of(details);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return Optional.empty();
}




/*------------------------------------GET ALL VENDOR NAMES -------------------------------*/


@Override
	public List<String> getAllVendorsNames() {
	 List<String> usernames = new ArrayList<>();
     String query = "SELECT DISTINCT u.username FROM users u JOIN vegetables_details v ON u.id = v.vendor_id";

     try (PreparedStatement ps = connection.prepareStatement(query)) {
         ResultSet rs = ps.executeQuery();

         // Iterate through the result set and add usernames to the list
         while (rs.next()) {
             usernames.add(rs.getString("username"));
         }
     } catch (SQLException e) {
         e.printStackTrace();
     }

     return usernames;  // Return the list of usernames
	
	
	
	}




/*----------GET Vendor Name Based on VEg id to know which vendor is selling which vegtable */

public String getUsernameByVegId(int vegId) {
    String username = null;
    String query = "SELECT u.username FROM users u JOIN vegetables_details v ON u.id = v.vendor_id WHERE v.veg_id = ?";

    try (PreparedStatement ps = connection.prepareStatement(query)) {
        ps.setInt(1, vegId);  // Set the veg_id parameter in the query
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            // Retrieve the username from the result set
            username = rs.getString("username");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return username;  // Return the username or null if not found
}


}
