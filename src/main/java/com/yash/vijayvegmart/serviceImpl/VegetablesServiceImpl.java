package com.yash.vijayvegmart.serviceImpl;


import java.util.Optional;

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





	public void addVegetbale(VegetablesDetails details) throws Exception
	{
		//logic here => Same vendor cannot upload same vegetable again 
		// 1) first check is the  vegetable already uploaded bY vendor or not by using vendor id and veg name 
		 Optional<VegetablesDetails> existingVegetable = vegDao.checkVegetableExistsByVendor(details.getVendorId() , details.getVegName());
		
		 if (existingVegetable.isPresent()) {
	            throw new VegetablesException("Vegetable already exists.");
	        }
		 vegDao.saveVegetable(details);
		
	}

}
