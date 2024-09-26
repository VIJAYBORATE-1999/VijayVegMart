package com.yash.vijayvegmart.service;
import javax.servlet.http.Part;

import com.yash.vijayvegmart.model.VegetablesDetails;

public interface VegetablesService {
	public void addVegetable(VegetablesDetails details, Part vegPicPart, String contextPath)throws Exception;
	

}
