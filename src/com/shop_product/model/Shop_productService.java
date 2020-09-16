package com.shop_product.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.mem.model.*;
import com.mem.controller.*;
import idv.david.websocketchat.controller.Robot;

public class Shop_productService {
	private Shop_productDAO_interface dao;
	
	public Shop_productService() {
		dao = new Shop_productJNDIDAO();

	}
	
	public Shop_productVO addShopProduct(String venNo, String className, String prodName,String prodIntro, 
			 Integer price, Integer evCount, Integer evTotal, Integer sprodStatus, byte[] photo) {

		Shop_productVO shopProductVO = new Shop_productVO();	
		shopProductVO.setVenNo(venNo);
		shopProductVO.setClassName(className);
		shopProductVO.setProdName(prodName);
		shopProductVO.setProdIntro(prodIntro);
		shopProductVO.setPrice(price);
		shopProductVO.setEvCount(evCount);
		shopProductVO.setEvTotal(evTotal);
		shopProductVO.setSprodStatus(sprodStatus);
		shopProductVO.setPhoto(photo);
		dao.insert(shopProductVO);	
		
	
		         // =======================================����========================================
		//�����s�ӫ~
		Set<String> memSet = new HashSet<String>();       //�ŧi�����ݭn�����O
		
		MemService memService = new MemService();     
		List<MemVO>  allMemList = memService.getAll() ;     //���o�Ҧ��|�����
		
//�[�J�Ҧ��|���s����memSetet
		for(MemVO memVO: allMemList) {
			memSet.add(memVO.getMemNo());				
		}
		
		String Info = shopProductVO.getProdName() + "�W�[�o";  //�ǰe���T��
		Robot.chatRobot(Info, memSet);
		
			// ============================================����=========================================
	
		
		return shopProductVO;						
	}
	
	public Shop_productVO updateShopProduct(String prodNo, String venNo, String className, String prodName,String prodIntro, 
			java.sql.Date increaseTime, Integer price, Integer sprodStatus, Integer evCount, Integer evTotal, byte[] photo) {
		
		/**
//		============================================�������e�}�l===============================
		//���o�¸��(�ΨӤ�墨�Ǹ�Ʀ��ק�)
		Shop_productService oldShopProductSvc = new Shop_productService();
		Shop_productVO oldShopProductVO = oldShopProductSvc.getOneShopProductVO(prodNo);
		
		//�Ыاi���ӫ~�ק鷺�e���r�ꤺ�e
		String classNameString = null;
		String prodNameString = null;
		String prodIntroString = null;
		String priceString = null;
		String sprodStatusString = null;
		String photoString = null;
		
		//�P�_���Ǹ�Ʀ��ק�
//			�ӫ~����
		if (oldShopProductVO.getClassName() != className) {
			classNameString = "�ӫ~���O�w��אּ" + className + "\n";
		}
//			�ӫ~�W��
		if (oldShopProductVO.getProdName() != prodName) {
			prodNameString = "�ӫ~�W�٤w��אּ" + prodName + "\n";
		}
//			�ӫ~����
		if (oldShopProductVO.getProdIntro() != prodIntro) {
			prodIntroString = "�ӫ~���Фw��אּ" + prodIntro + "\n";
		}
//			�ӫ~����
		if (oldShopProductVO.getPrice() != price) {
			priceString = "�ӫ~����H��אּ" + price + "\n";
		}
//			�ӫ~���A
		if (oldShopProductVO.getSprodStatus() != sprodStatus) {
			String sprodStatusInfo = null;
			switch(sprodStatus) {
				case 0:
					sprodStatusInfo = "�W�[";
					break;
				case 1 :
					sprodStatusInfo = "���W�[";
					break;
				case 2 :
					sprodStatusInfo = "�Q���|�U�[";
					break;
			}
			sprodStatusString = "�ӫ~���A�w�ܧ�" + sprodStatusInfo + "\n";
		}
//			�ӫ~�Ӥ�
		if (oldShopProductVO.getPhoto() != photo) {
			photoString = "�ӫ~�Ӥ��w�ܧ�" + "\n";
		}
		//�n�������t�Ӫ��Ҧ����e
		String infoToVendor = classNameString + prodNameString + prodIntroString + priceString + sprodStatusString +photoString;
		//������k
		robotChat(infoToVendor, venNo);
		
//		===========================================�������e����=======================================
		**/
		
		Shop_productVO shopProductVO = new Shop_productVO();
		
		shopProductVO.setProdNo(prodNo);
		shopProductVO.setVenNo(venNo);
		shopProductVO.setClassName(className);
		shopProductVO.setProdName(prodName);
		shopProductVO.setProdIntro(prodIntro);
		shopProductVO.setIncreaseTime(increaseTime);
		shopProductVO.setPrice(price);
		shopProductVO.setSprodStatus(sprodStatus);
		shopProductVO.setEvCount(evCount);
		shopProductVO.setEvTotal(evTotal);
		shopProductVO.setPhoto(photo);
		dao.update(shopProductVO);
		
		return shopProductVO;
	}
	
	public void deleteShopProduct(String prodNo) {
		dao.delete(prodNo); 
	}
	
	public Shop_productVO getOneShopProductVO(String prodNo) {
		return dao.findByPrimaryKey(prodNo);
	}
	
	public List<Shop_productVO> getAll(){
		return dao.getAll();	
	}
	
	public List<Shop_productVO> selectShophomeProduct(Integer sprodStatus){
		return dao.selectShophomeProduct(sprodStatus);
	}
	
	public String selectShopProductName(String sprodNo) {
		return dao.selectShopProductName(sprodNo);
	}
	//���θ�Ʒs�W(�ֹϤ�)
	public Shop_productVO addCrawlerShopProduct(String venNo, String className, String prodName,String prodIntro, 
			 Integer price, Integer evCount, Integer evTotal, Integer sprodStatus) {

		Shop_productVO shopProductVO = new Shop_productVO();	
		shopProductVO.setVenNo(venNo);
		shopProductVO.setClassName(className);
		shopProductVO.setProdName(prodName);
		shopProductVO.setProdIntro(prodIntro);
		shopProductVO.setPrice(price);
		shopProductVO.setEvCount(evCount);
		shopProductVO.setEvTotal(evTotal);
		shopProductVO.setSprodStatus(sprodStatus);
		dao.shopCrawlerInsert(shopProductVO);	
		return shopProductVO;
	}
	
}
