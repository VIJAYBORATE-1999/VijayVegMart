package com.yash.vijayvegmart.serviceImpl;


import java.io.File;
import java.util.Optional;

import javax.servlet.http.Part;

import com.yash.vijayvegmart.dao.VegetablesDao;
import com.yash.vijayvegmart.daoImpl.VegetablesDaoImpl;
import com.yash.vijayvegmart.exception.UsersException;
import com.yash.vijayvegmart.exception.VegetablesException;
import com.yash.vijayvegmart.model.VegetablesDetails;
import com.yash.vijayvegmart.service.VegetablesService;

public class VegetablesServiceImpl implements VegetablesService {
    
    private VegetablesDao vegDao;

    public VegetablesServiceImpl() {
        this.vegDao = new VegetablesDaoImpl();
    }

    @Override
    public void addVegetable(VegetablesDetails details, Part vegPicPart, String contextPath) throws Exception {
        
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
            String path = contextPath + "img";
            File imgDir = new File(path);
            if (!imgDir.exists()) {
                boolean created = imgDir.mkdirs();
                if (created) {
                    System.out.println("Directory created: " + imgDir.getAbsolutePath());
                } else {
                    throw new VegetablesException("Failed to create directory.");
                }
            }

            String fileName = details.getVegPicName();
            vegPicPart.write(path + File.separator + fileName);
            System.out.println("Image saved at: " + path + File.separator + fileName);
        } else {
            throw new VegetablesException("No file uploaded.");
        }
    }
}

