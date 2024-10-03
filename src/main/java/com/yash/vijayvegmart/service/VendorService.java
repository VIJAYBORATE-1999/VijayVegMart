package com.yash.vijayvegmart.service;

import java.util.List;

import com.yash.vijayvegmart.model.VO.VendorOrdersVO;

public interface VendorService {
	
	public List<VendorOrdersVO> getVendorOrders(int vendor_id, String vendorActionStatus);
	
	 public boolean updateVendorActionStatus(String orderId, int userId, int cartId, String newVendorActionStatus);
	 public boolean updateQuantity(int vegId, String vegName, int vendorId, int newQuantity);

}
