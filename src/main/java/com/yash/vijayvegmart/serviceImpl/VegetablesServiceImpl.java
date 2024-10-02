package com.yash.vijayvegmart.serviceImpl;


import java.io.File;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.Part;

import com.yash.vijayvegmart.dao.VegetablesDao;
import com.yash.vijayvegmart.daoImpl.VegetablesDaoImpl;
import com.yash.vijayvegmart.exception.VegetablesException;
import com.yash.vijayvegmart.model.VegetablesDetails;
import com.yash.vijayvegmart.service.VegetablesService;

public class VegetablesServiceImpl implements VegetablesService {
    
    private VegetablesDao vegDao;

    public VegetablesServiceImpl() {
        this.vegDao = new VegetablesDaoImpl();
    }

    @Override
   
    
/* --------------------------------- SAVE VEGETBALE INTO DB  ------------------------------------*/ 
    
    
    public void addVegetable(VegetablesDetails details, Part vegPicPart, String Path) throws Exception {
        
    	//bUSINESS LOGIC -> fIRST Check begetable is there or not ?
    	
    	
    	System.out.println("addVegetable CALLED ");
    	// Check if the vegetable already exists
        Optional<VegetablesDetails> existingVegetable = vegDao.checkVegetableExistsByVendor(details.getVendorId(), details.getVegName());
        if (existingVegetable.isPresent()) {
            throw new VegetablesException("Vegetable already exists.");
        }

        // Save vegetable details
        vegDao.saveVegetable(details);

        // Ensure the img directory exists and handle file upload
        if (vegPicPart != null && vegPicPart.getSize() > 0) {
            String path_final = Path + "\\img";
            
          System.out.println("HEYYYYY PATH ISSSSSS:::::::::::::"+path_final);
            File imgDir = new File(path_final);
            if (!imgDir.exists()) {
                boolean created = imgDir.mkdirs();
                if (created) {
                    System.out.println("Directory created: " + imgDir.getAbsolutePath());
                } else {
                    throw new VegetablesException("Failed to create directory.");
                }
            }

            String fileName = details.getVegPicName();
            vegPicPart.write(path_final + File.separator + fileName);
            System.out.println("Image saved at: " + path_final + File.separator + fileName);
        } else {
            throw new VegetablesException("No file uploaded.");
        }
    }
    
    
    
 /* --------------------------------- FTECH ALL VEGETBALE FROM  DB  ------------------------------------*/ 
    
    
    // we will fetch all vegtables for home page 
    
    public List<VegetablesDetails> fetchAllVegetablesBycategory(String Category) {
    	
    	return vegDao.getAllVegetablesByCategory(Category);
    }
    
    
    
   
/* --------------------------------- FTECH ALL VEGETBALE FROM  DB VIA SPECIFIC VENDOR   ------------------------------------*/   
    
    // we will fetch all veegtbales uploded by partticular vendor 
    @Override
    public List<VegetablesDetails> fetchAllVegetablesByVendorId(int vendor_id) throws Exception {
    	return vegDao.getAllVegetablesByVendorId(vendor_id);
    }
    
    
/* --------------------------------- FTECH IN STOCK VEGETBALE FROM  DB VIA SPECIFIC VENDOR   ------------------------------------*/   
    
    @Override
    public List<VegetablesDetails> fetchAllVegetablesInStockByVendorId(int vendor_id) throws Exception {
   
    	
    	
    	return vegDao.getAllInStockVegetablesByVendorId(vendor_id);
    }
    
 /* --------------------------------- FTECH OUT OF  STOCK VEGETBALE FROM  DB VIA SPECIFIC VENDOR   ------------------------------------*/   
    
    @Override
    public List<VegetablesDetails> fetchAllVegetablesOutOfStockByVendorId(int vendor_id) throws Exception {
    	
    	
    	return vegDao.getAllOutOfStockVegetablesByVendorId(vendor_id);
    }
    
    
    /* ----------------------------------------FETCH A VEGETBALES DETAIL BASED ON VEG ID -----------------------------*/
    // WE NEED THIS FUNCTION IN CART MODULE 
    
  @Override
public VegetablesDetails fetchVegetableById(int veg_id) throws Exception {
	// TODO Auto-generated method stub
	  
	  Optional<VegetablesDetails>  veg_details = vegDao.getVegetableById(veg_id);
		  return veg_details.get();
	  
}
    
  
  /*--------------FETCH ALL VENDORSas we need it on home page for sort by option ------------------------*/
    
    public List<String> fetchAllVendorsNames() throws Exception
    {
    	
    	return vegDao.getAllVendorsNames() ;
    	
    }  
  
  
    /*----------------------------FETCH VENDOR NAME BASED ON VEG ID-----------------*/
    
    @Override
    public String getVendorUsernameByVegId(int vegId) throws Exception {
    	// TODO Auto-generated method stub
    	return vegDao.getUsernameByVegId(vegId);
    }

    
    
}

