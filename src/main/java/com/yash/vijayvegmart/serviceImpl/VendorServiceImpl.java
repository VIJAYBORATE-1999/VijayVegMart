package com.yash.vijayvegmart.serviceImpl;

import java.util.List;

import com.yash.vijayvegmart.dao.VendorDao;
import com.yash.vijayvegmart.daoImpl.VendorDaoImpl;
import com.yash.vijayvegmart.model.VO.VendorOrdersVO;
import com.yash.vijayvegmart.service.VegetablesService;
import com.yash.vijayvegmart.service.VendorService;

public class VendorServiceImpl implements VendorService{
	
	private VendorDao dao;

	public VendorServiceImpl() {

		this.dao = new VendorDaoImpl();
	}
	
	
	
	public List<VendorOrdersVO> getVendorOrders(int vendor_id, String vendorActionStatus)
	{
		
		return dao.getVendorOrders(vendor_id, vendorActionStatus);
		
	}

	
	 public boolean updateVendorActionStatus(String orderId, int userId, int cartId, String newVendorActionStatus)
	 {
		 
		 return dao.updateVendorActionStatus(orderId, userId, cartId, newVendorActionStatus);
	 }
	
	 
	 
	 @Override
	public boolean updateQuantity(int vegId, String vegName, int vendorId, int newQuantity) {
		// TODO Auto-generated method stub
		return dao.updateQuantity(vegId, vegName, vendorId, newQuantity);
	}
}
