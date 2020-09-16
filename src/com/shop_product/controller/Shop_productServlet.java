package com.shop_product.controller;

import java.io.*;
import java.sql.Date;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.shop_product.model.Shop_productVO;
import com.tools.PhotoToByte;
import com.shop_product.model.Shop_productService;

@MultipartConfig(fileSizeThreshold = 1920 * 1080, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class Shop_productServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException{
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException{
		

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) {     

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String str = req.getParameter("prodNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入商品編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("vendorSelectProductHome.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String prodNo = str;
				
				/***************************2.開始查詢資料****************************************/
				Shop_productService shopProductSvc = new Shop_productService();
				Shop_productVO shopProductVO = shopProductSvc.getOneShopProductVO(prodNo);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("shopProductVO", shopProductVO);         // 資料庫取出物件,存入req
				String url = "vendorListOneShopProduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("vendorSelectProductHome.jsp");
				failureView.forward(req, res);
			}
		}
			
//		====================================顯示更新前的資料=========================================		
		if ("getOne_For_Update".equals(action)) { // 來自listAll.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String prodNo = req.getParameter("prodNo");
				
				/***************************2.開始查詢資料****************************************/
				Shop_productService shopProductSvc = new Shop_productService();
				Shop_productVO shopProductVO = shopProductSvc.getOneShopProductVO(prodNo);
					
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("shopProductVO", shopProductVO);         // 資料庫取出的物件,存入req
				String url = "vendorUpdateShopProductInput.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("vendorListAllShopProduct.jsp");
				failureView.forward(req, res);
			}
		}
		
//		====================================更新資料=========================================
		if ("update".equals(action)) {       		
			List<String> errorMsgs = new LinkedList<String>();		
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				Shop_productService shopProductSvc = new Shop_productService();
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String prodNo = req.getParameter("prodNo");

				String venNo  = req.getParameter("venNo");
				String className  = req.getParameter("className");
//				商品名稱
				String prodName = req.getParameter("prodName");
				String prodNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,30}$";
				if (prodName== null || (prodName.trim()).length() == 0) {
					errorMsgs.add("商品名稱:請勿空白");
				} else if(!prodName.trim().matches(prodNameReg)){
					errorMsgs.add("商品名稱: 只能是中、英文字母、數字和_ , 且長度必需在1到30之間");
				}
			
//				商品介紹
				String prodIntro = req.getParameter("prodIntro");
				String prodIntroReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,300}$";
				if (prodIntro== null || (prodIntro.trim()).length() == 0) {
					errorMsgs.add("商品介紹:請勿空白");
				} else if(!prodIntro.trim().matches(prodIntroReg)){
					errorMsgs.add("商品介紹: 只能是中、英文字母、數字和_ , 且長度必需在1到100之間");
				}

//				上架時間(不可更動)

				java.sql.Date increaseTime = java.sql.Date.valueOf(req.getParameter("increaseTime").trim());

//				商品單價
				Integer price = null;
				try {
					price = new Integer(req.getParameter("price"));
				}catch(NumberFormatException e) {
					price = 0;
					errorMsgs.add("商品單價請填數字");
				}
				if (price < 1) {
					errorMsgs.add("商品價錢: 請確認輸入的價格");
				}
//				評價人數
				Integer evCount = new Integer(req.getParameter("evCount"));
//				評價總分
				Integer evTotal = new Integer(req.getParameter("evTotal"));
//				上下架狀態
				Integer sprodStatus = null;
				try {
					sprodStatus = new Integer(req.getParameter("sprodStatus"));
				} catch(NumberFormatException e) {
					sprodStatus = null;
					errorMsgs.add("商品狀態請填數字");
				}
			
//				圖片上傳					 				
			    Part part = req.getPart("photo");
			    PhotoToByte ptb = new PhotoToByte();
			    byte[] photo = ptb.photoToByte(part);
			    if(photo == null) {
			    	photo = shopProductSvc.getOneShopProductVO(prodNo).getPhoto();
			    }
			    
				Shop_productVO shopProductVO = new Shop_productVO();
				shopProductVO.setProdNo(prodNo);
				shopProductVO.setVenNo(venNo);;
				shopProductVO.setClassName(className);
				shopProductVO.setProdName(prodName);
				shopProductVO.setProdIntro(prodIntro);
				shopProductVO.setIncreaseTime(increaseTime);
				shopProductVO.setPrice(price);
				shopProductVO.setEvCount(evCount);
				shopProductVO.setEvTotal(evTotal);
				shopProductVO.setSprodStatus(sprodStatus);
				shopProductVO.setPhoto(photo);
				
				if (!errorMsgs.isEmpty()) {
		
					req.setAttribute("shopProductVO", shopProductVO);
					RequestDispatcher failureView = req.getRequestDispatcher("vendorUpdateShopProductInput.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始修改資料*****************************************/

				
				shopProductVO = shopProductSvc.updateShopProduct(prodNo, venNo, className, prodName, prodIntro, increaseTime, price, sprodStatus, evCount, evTotal, photo);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("shopProductVO", shopProductVO);
				String url = "vendorListOneShopProduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);  //修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);
				
			}catch (Exception e) {

				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("vendorUpdateShopProductInput.jsp");
				failureView.forward(req, res);
			}
		}		
		
		
//		===========================================新增商品==================================
		if ("insert".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String venNo = req.getParameter("venNo");

				String className = req.getParameter("className");

				String prodName = req.getParameter("prodName");
	
				String prodNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,30}$";
				if (prodName == null || prodName.trim().length() == 0) {
					errorMsgs.add("商品名稱請勿空白");
				}else if(!prodName.trim().matches(prodNameReg)){
					errorMsgs.add("商品名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到30之間");
				}

				
				String prodIntro = req.getParameter("prodIntro");
				String prodIntroReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,300}$";
				if (prodIntro == null || prodIntro.trim().length() == 0) {
					errorMsgs.add("商品介紹請勿空白");
				}else if(!prodName.trim().matches(prodIntroReg)) {
					errorMsgs.add("商品介紹: 只能是中、英文字母、數字和_ , 且長度必需在2到300之間");
				}
					
				Integer price = null;
				try {
					price = new Integer(req.getParameter("price").trim());
				}catch(NumberFormatException e){
					price = 0;
					errorMsgs.add("輸入格式錯誤，請輸入數字");				
				}
				if (price < 1) {
					errorMsgs.add("商品價錢: 請確認輸入的價格");
				}
			
				Integer evCount = new Integer(req.getParameter("evCount"));

				Integer evTotal = new Integer(req.getParameter("evTotal"));
			
				Integer sprodStatus = new Integer(req.getParameter("sprodStatus"));
				
//				圖片上傳		
			    byte[] photo = null;
			    Part part = req.getPart("photo");
			    InputStream is = part.getInputStream();
			    ByteArrayOutputStream baos = new ByteArrayOutputStream();
			    byte[] buffer = new byte[8192];
			    int i;
			    while ((i = is.read(buffer)) != -1)
			    baos.write(buffer, 0, i);
			    photo = baos.toByteArray();
			    baos.close();
			    is.close();
				
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
				
				if (!errorMsgs.isEmpty()) {

					req.setAttribute("shopProductVO", shopProductVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("vendorAddShopProduct.jsp");
					failureView.forward(req, res);
					return;
				}

				/***************************2.開始新增資料***************************************/
				
				Shop_productService shopProductSvc = new Shop_productService();
				shopProductVO = shopProductSvc.addShopProduct(venNo, className, prodName, prodIntro, price, evCount, evTotal, sprodStatus,photo);

				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "vendorListAllShopProduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);		
			}catch(Exception e){
				/***************************其他可能的錯誤處理**********************************/
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("vendorAddShopProduct.jsp");
				failureView.forward(req, res);
			}
		}
			
//		============================================刪除資料====================================================
		if ("delete".equals(action)) { // 來自listAllShop_product.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String prodNo = new String(req.getParameter("prodNo"));
				
				/***************************2.開始刪除資料***************************************/
				Shop_productService shopProductSvc = new Shop_productService();
				shopProductSvc.deleteShopProduct(prodNo);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "vendorListAllShopProduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("vendorListAllShopProduct.jsp");
				failureView.forward(req, res);
			}
		}
		
		
//		=====================================================爬蟲(pchome)新增商品資料==================================================
//		===========================================新增商品==================================
		if ("insertPcHomeProd".equals(action)) {
		
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String venNo = "V008";
				String prodName = req.getParameter("prodName");			
				String className = req.getParameter("className");						
				String prodIntro = req.getParameter("prodIntro");					
				Integer price = new Integer(req.getParameter("price").trim());						
				Integer evCount = 0;
				Integer evTotal = 0;			
				Integer sprodStatus = 0;
					
				Shop_productVO shopProductVO = new Shop_productVO();
				shopProductVO.setVenNo(venNo);
				shopProductVO.setClassName(className);
				shopProductVO.setProdName(prodName);
				shopProductVO.setProdIntro(prodIntro);
				shopProductVO.setPrice(price);
				shopProductVO.setEvCount(evCount);
				shopProductVO.setEvTotal(evTotal);
				shopProductVO.setSprodStatus(sprodStatus);				
				
				/***************************2.開始新增資料***************************************/
				
				Shop_productService shopProductSvc = new Shop_productService();
				shopProductVO = shopProductSvc.addCrawlerShopProduct(venNo, className, prodName, prodIntro, price, evCount, evTotal, sprodStatus);
	
			}catch(Exception e){
				/***************************其他可能的錯誤處理**********************************/
				System.out.println("error");

			}
		}
										
	}
}
