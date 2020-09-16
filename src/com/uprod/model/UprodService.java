package com.uprod.model;

import java.util.List;


public class UprodService {
	
	private UprodDAO_interface dao;


	public UprodService() {
	   dao = new UprodDAO();

	}

	public UprodVO addUprod(String SellerNo, String ProdName,String ProdIntro,Integer Price,String ProdStatus,String ProdType,byte[] Photo) {

		UprodVO uprodVO = new UprodVO();
		uprodVO.setSellerNo(SellerNo);
		uprodVO.setProdName(ProdName);
		uprodVO.setProdIntro(ProdIntro);
		uprodVO.setPrice(Price);
		uprodVO.setProdStatus(ProdStatus);
		uprodVO.setProdType(ProdType);
		uprodVO.setPhoto(Photo);
		
		dao.insert(uprodVO);

		return uprodVO;
	}

	public UprodVO updateUprod(String ProdName, String ProdIntro, Integer Price, String ProdType, 
							   String ProdStatus, java.sql.Timestamp IncreaseTime,byte[] Photo,String ProdNo ) {

		UprodVO uprodVO = new UprodVO();

		uprodVO.setProdName(ProdName);
		uprodVO.setProdIntro(ProdIntro);
		uprodVO.setPrice(Price);
		uprodVO.setIncreaseTime(IncreaseTime);
		uprodVO.setProdType(ProdType);
		uprodVO.setProdStatus(ProdStatus);
        uprodVO.setPhoto(Photo);
		uprodVO.setProdNo(ProdNo);
		
		dao.update(uprodVO);
		return dao.findByPrimaryKey(ProdNo);
//		return uprodVO;
	}
	
	public UprodVO buyUprod(String CustNo, String ProdStatus,String OrderStatus,String ReceiveStatus,java.sql.Timestamp OrderTime,String TranMethod,String TranAddr,String ProdNo ) {

		
		UprodVO uprodVO = new UprodVO();

		uprodVO.setCustNo(CustNo);
		uprodVO.setProdStatus(ProdStatus);
		uprodVO.setOrderStatus(OrderStatus);
		uprodVO.setReceiveStatus(ReceiveStatus);
		uprodVO.setOrderTime(OrderTime);
		uprodVO.setTranMethod(TranMethod);
		uprodVO.setTranAddr(TranAddr);
		uprodVO.setProdNo(ProdNo);
		
		dao.buy(uprodVO);
		return dao.findByPrimaryKey(ProdNo);
//		return uprodVO;
	}


	public UprodVO getOneUprod(String UprodNo) {
		return dao.findByPrimaryKey(UprodNo);
	}

	public List<UprodVO> getAll() {
		return dao.getAll();
	}
	
	public List<UprodVO> getAllOnShelf() {
		return dao.getAllOnShelf();
	}
	
	public List<UprodVO> getAllBySeller(String SellerNo){
		return dao.getAllBySeller(SellerNo);
	}
	
	public List<UprodVO> getAllByCustomer(String CustNo){
		return dao.getAllByCustomer(CustNo);
	}
	public List<UprodVO> getAllBySellerDetail(String SellerNo){
		return dao.getAllBySellerDetail(SellerNo);
	}
	
	public UprodVO shipment(String ProdNo ) {

		UprodVO uprodVO = new UprodVO();

		uprodVO.setProdNo(ProdNo);
		
		dao.shipment(uprodVO);
		return dao.findByPrimaryKey(ProdNo);
//		return uprodVO;
	}
	
	public UprodVO receive(String ProdNo ) {

		UprodVO uprodVO = new UprodVO();

		uprodVO.setProdNo(ProdNo);
		
		dao.receive(uprodVO);
		return dao.findByPrimaryKey(ProdNo);
//		return uprodVO;
	}
	
	public List<UprodVO> getAllOnShelfByA() {
		return dao.getAllOnShelfByA();
	}
	public List<UprodVO> getAllOnShelfByB() {
		return dao.getAllOnShelfByB();
	}
	public List<UprodVO> getAllOnShelfByC() {
		return dao.getAllOnShelfByC();
	}
}
