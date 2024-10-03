package com.yash.vijayvegmart.service;

import java.util.List;

import com.yash.vijayvegmart.model.VO.VendorOrdersVO;

public interface VendorService {
	
	public List<VendorOrdersVO> getVendorOrders(int vendor_id, String vendorActionStatus);

}
