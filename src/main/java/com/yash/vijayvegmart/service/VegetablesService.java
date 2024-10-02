package com.yash.vijayvegmart.service;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.Part;

import com.yash.vijayvegmart.model.VegetablesDetails;

public interface VegetablesService {
	public void addVegetable(VegetablesDetails details, Part vegPicPart, String contextPath)throws Exception;
	
	 public List<VegetablesDetails> fetchAllVegetablesBycategory(String Category) throws Exception ; 
	    
	 public List<VegetablesDetails> fetchAllVegetablesByVendorId(int vendor_id) throws Exception ; 

	 public List<VegetablesDetails> fetchAllVegetablesInStockByVendorId(int vendor_id) throws Exception ; 

	 public List<VegetablesDetails> fetchAllVegetablesOutOfStockByVendorId(int vendor_id) throws Exception ; 

     public  VegetablesDetails fetchVegetableById(int veg_id) throws Exception ; 

     public List<String> fetchAllVendorsNames() throws Exception ; 
     
     public String getVendorUsernameByVegId(int vegId) throws Exception;
     
     public VegetablesDetails fetchVegetableDetailsByCartId(int cartId) throws Exception ;
     
     public List<VegetablesDetails> getVegetablesDetailsByOrderId(String orderId) throws Exception;

}
