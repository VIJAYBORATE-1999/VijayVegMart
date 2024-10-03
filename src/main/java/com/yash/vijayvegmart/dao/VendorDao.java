package com.yash.vijayvegmart.dao;

import java.util.List;

import com.yash.vijayvegmart.model.VO.VendorOrdersVO;

public interface VendorDao {
	
	public List<VendorOrdersVO> getVendorOrders(int vendor_id, String vendorActionStatus);

}
