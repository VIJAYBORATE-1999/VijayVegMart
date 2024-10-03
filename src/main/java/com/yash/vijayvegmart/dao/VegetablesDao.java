package com.yash.vijayvegmart.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.yash.vijayvegmart.model.VegetablesDetails;

public interface VegetablesDao {

    Optional<VegetablesDetails> checkVegetableExistsByVendor (int vendor_id , String veg_name); // check if vegetbale is already uploded by vendor or not ?
    
    void saveVegetable(VegetablesDetails details);
    
    List<VegetablesDetails> getAllVegetablesByCategory(String Category);  // method to get all vegetables for home page of customer 
    
    List<VegetablesDetails> getAllVegetablesByVendorId(int vendor_id); // get all vegetables for particlar vendor  

    List<VegetablesDetails> getAllInStockVegetablesByVendorId(int vendor_id);  // get all in stock  vegetables for particlar vendor  

    List<VegetablesDetails> getAllOutOfStockVegetablesByVendorId(int vendor_id); // get all out of  vegetables for particlar vendor  
  
    Optional<VegetablesDetails> getVegetableById(int veg_id) ;
    
    
    
    
    public List<String> getAllVendorsNames();
    
    public String getUsernameByVegId(int vegId);
    
    
    public VegetablesDetails getVegetableDetailsByCartId(int cartId);
    
    public List<VegetablesDetails> getVegetablesDetailsByOrderId(String orderId) ;
    
    
    public void updateVegetable(VegetablesDetails details) ;
    
    public boolean deleteVegetableById(int vegId);
    
    public VegetablesDetails get_A_VegetableById(int vegId) throws SQLException ;
}
