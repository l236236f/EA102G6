package com.vendor.model;

import java.util.List;

public interface VendorDAO_interface {
	public void insert(VendorVO vendorVo);
	public void update (VendorVO vendorVo);
	public void delete(String venNo);
	public VendorVO findByPrimaryKey(String venNo);
	public List<VendorVO> getAll();
	public VendorVO checkAcc(String venAcc);
	public void updateByEmp (VendorVO vendorVo);
}