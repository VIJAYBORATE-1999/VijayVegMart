package com.yash.vijayvegmart.dao;

import java.util.List;

import com.yash.vijayvegmart.model.Revenues;
import com.yash.vijayvegmart.model.Users;

public interface AdminDao {

	
	/*------------  CLOSE DB CONNECTION WHEN NEEDED ----------------  */
	public void closeConnection();
	
	  public List<Users> getAllApprovedUsers();
	  
	  public List<Users> getAllNotApprovedUsers();
	  
	  public List<Users> getAllRejectedUsers();
	  
	  public List<Users> getAllDeletedUsers();
	  
	  public List<Users> getAllActive() ;
	  
	  public void approveUser(int id);
	  
	  public void reapproveUser(int id);
	  
	  public void rejectUser(int id);
	  
	  public void reactivateAccount(int id);
	  
	  public void deactivateAccount(int id);
	  
	  Revenues getRevenueByOrderId(String orderId) ;
	  public List<Revenues> fetchAllRevenues();
	  
}
