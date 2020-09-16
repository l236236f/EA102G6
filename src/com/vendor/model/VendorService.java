package com.vendor.model;

import java.sql.Date;
import java.util.List;

import com.mem.model.MemVO;

public class VendorService {

	private VendorDAO_interface dao;

	public VendorService() {
		dao = new VendorDAO();
	}

	public VendorVO addVendor(String venAcc,String venPw,String venName,
			String venTel,String venID,String venMoney,String venAddr,
			String venEmail,byte[] venPhoto,String venIntro) {

		VendorVO vendorVO = new VendorVO();

		vendorVO.setVenAcc(venAcc);
		vendorVO.setVenPw(venPw);
		vendorVO.setVenName(venName);
		vendorVO.setVenTel(venTel);
		vendorVO.setVenID(venID);
		vendorVO.setVenMoney(venMoney);
		vendorVO.setVenAddr(venAddr);
		vendorVO.setVenEmail(venEmail);
		vendorVO.setVenPhoto(venPhoto);
		vendorVO.setVenIntro(venIntro);
		
		dao.insert(vendorVO);

		return vendorVO;
	}
	

	public VendorVO updateVendor(String venNo,String venAcc,String venPw,String venName,
			String venTel,String venID,String venMoney,String venAddr,
			String venEmail,byte[] venPhoto,String venIntro) {

		VendorVO vendorVO = new VendorVO();

		vendorVO.setVenNo(venNo);
		vendorVO.setVenAcc(venAcc);
		vendorVO.setVenPw(venPw);
		vendorVO.setVenName(venName);
		vendorVO.setVenTel(venTel);
		vendorVO.setVenID(venID);
		vendorVO.setVenMoney(venMoney);
		vendorVO.setVenAddr(venAddr);
		vendorVO.setVenEmail(venEmail);
		vendorVO.setVenPhoto(venPhoto);
		vendorVO.setVenIntro(venIntro);
		
		dao.update(vendorVO);

		return vendorVO;
	}

	public void deleteVendor(String venNo) {
		dao.delete(venNo);
	}

	public VendorVO getOneVendor(String venNo) {
		return dao.findByPrimaryKey(venNo);
	}

	public List<VendorVO> getAll() {
		return dao.getAll();
	}
	
	public VendorVO checkAcc(String venAcc) {
		return dao.checkAcc(venAcc);
	}
	
	public VendorVO updateVendorByEmp(String venNo,String venPw,String venName,
			String venTel,String venID,String venMoney,String venAddr,
			String venEmail,String venStatus) {

		VendorVO vendorVO = new VendorVO();

		vendorVO.setVenNo(venNo);
	
		vendorVO.setVenPw(venPw);
		vendorVO.setVenName(venName);
		vendorVO.setVenTel(venTel);
		vendorVO.setVenID(venID);
		vendorVO.setVenMoney(venMoney);
		vendorVO.setVenAddr(venAddr);
		vendorVO.setVenEmail(venEmail);
		vendorVO.setVenStatus(venStatus);
	
		dao.updateByEmp(vendorVO);

		return vendorVO;
	}
}

