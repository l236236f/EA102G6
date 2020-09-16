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
		
	
		         // =======================================推播========================================
		//推播新商品
		Set<String> memSet = new HashSet<String>();       //宣告推播需要的型別
		
		MemService memService = new MemService();     
		List<MemVO>  allMemList = memService.getAll() ;     //取得所有會員資料
		
//加入所有會員編號給memSetet
		for(MemVO memVO: allMemList) {
			memSet.add(memVO.getMemNo());				
		}
		
		String Info = shopProductVO.getProdName() + "上架囉";  //傳送的訊息
		Robot.chatRobot(Info, memSet);
		
			// ============================================推播=========================================
	
		
		return shopProductVO;						
	}
	
	public Shop_productVO updateShopProduct(String prodNo, String venNo, String className, String prodName,String prodIntro, 
			java.sql.Date increaseTime, Integer price, Integer sprodStatus, Integer evCount, Integer evTotal, byte[] photo) {
		
		/**
//		============================================推播內容開始===============================
		//取得舊資料(用來比對那些資料有修改)
		Shop_productService oldShopProductSvc = new Shop_productService();
		Shop_productVO oldShopProductVO = oldShopProductSvc.getOneShopProductVO(prodNo);
		
		//創建告知商品修改內容的字串內容
		String classNameString = null;
		String prodNameString = null;
		String prodIntroString = null;
		String priceString = null;
		String sprodStatusString = null;
		String photoString = null;
		
		//判斷哪些資料有修改
//			商品分類
		if (oldShopProductVO.getClassName() != className) {
			classNameString = "商品類別已更改為" + className + "\n";
		}
//			商品名稱
		if (oldShopProductVO.getProdName() != prodName) {
			prodNameString = "商品名稱已更改為" + prodName + "\n";
		}
//			商品介紹
		if (oldShopProductVO.getProdIntro() != prodIntro) {
			prodIntroString = "商品介紹已更改為" + prodIntro + "\n";
		}
//			商品價格
		if (oldShopProductVO.getPrice() != price) {
			priceString = "商品價格以更改為" + price + "\n";
		}
//			商品狀態
		if (oldShopProductVO.getSprodStatus() != sprodStatus) {
			String sprodStatusInfo = null;
			switch(sprodStatus) {
				case 0:
					sprodStatusInfo = "上架";
					break;
				case 1 :
					sprodStatusInfo = "未上架";
					break;
				case 2 :
					sprodStatusInfo = "被檢舉下架";
					break;
			}
			sprodStatusString = "商品狀態已變更為" + sprodStatusInfo + "\n";
		}
//			商品照片
		if (oldShopProductVO.getPhoto() != photo) {
			photoString = "商品照片已變更" + "\n";
		}
		//要推播給廠商的所有內容
		String infoToVendor = classNameString + prodNameString + prodIntroString + priceString + sprodStatusString +photoString;
		//推播方法
		robotChat(infoToVendor, venNo);
		
//		===========================================推播內容結束=======================================
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
	//爬蟲資料新增(少圖片)
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
