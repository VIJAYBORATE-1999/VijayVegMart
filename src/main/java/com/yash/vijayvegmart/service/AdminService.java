package com.yash.vijayvegmart.service;

import java.util.List;

import com.yash.vijayvegmart.model.Revenues;
import com.yash.vijayvegmart.model.Users;

public interface AdminService {
	
	
	

	  public List<Users>fetchAllApprovedUsers();
	  
	  public List<Users>fetchAllNotApprovedUsers();
	  
	  public List<Users>fetchAllRejectedUsers();
	  
	  public List<Users>fetchAllDeletedUsers();
	  
	  public List<Users>fetchAllActive() ;
	  
	  public void approvalService(int id);
	  
	  public void reapproveService(int id);
	  
	  public void rejectService(int id);

	  public void reactivateService(int id);
	  
	  public Revenues fetchRevenueByOrderId(String orderId);
}
