package com.yash.vijayvegmart.dao;

import java.util.Optional;

import com.yash.vijayvegmart.model.VegetablesDetails;

public interface VegetablesDao {

    Optional<VegetablesDetails> checkVegetableExistsByVendor (int vendor_id , String veg_name); // check if vegetbale is already uploded by vendor or not ?
    
    void saveVegetable(VegetablesDetails details);
}
