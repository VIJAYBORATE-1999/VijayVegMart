package com.yash.vijayvegmart.dao;

import java.util.List;
import java.util.Optional;

import com.yash.vijayvegmart.model.VegetablesDetails;

public interface VegetablesDao {

    Optional<VegetablesDetails> checkVegetableExistsByVendor (int vendor_id , String veg_name); // check if vegetbale is already uploded by vendor or not ?
    
    void saveVegetable(VegetablesDetails details);
    
    List<VegetablesDetails> getAllVegetables();  // method to get all vegetables for home page of customer 
    
    List<VegetablesDetails> getAllVegetablesByVendorId(int vendor_id); // get all vegetables for particlar vendor  

    List<VegetablesDetails> getAllInStockVegetablesByVendorId(int vendor_id);  // get all in stock  vegetables for particlar vendor  

    List<VegetablesDetails> getAllOutOfStockVegetablesByVendorId(int vendor_id); // get all out of  vegetables for particlar vendor  

    
}
