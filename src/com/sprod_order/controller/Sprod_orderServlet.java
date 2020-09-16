package com.sprod_order.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.omg.CORBA.INTERNAL;

import com.mem.model.MemService;
import com.mem.model.MemVO;
import com.shop_product.model.Shop_productService;
import com.sprod_detail.model.Sprod_detailService;
import com.sprod_detail.model.Sprod_detailVO;
import com.sprod_order.model.Sprod_orderService;
import com.sprod_order.model.Sprod_orderVO;

import idv.david.websocketchat.controller.Robot;



public class Sprod_orderServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException{
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException{
		

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
//		=======會員查詢所有訂單===========
		if ("getOne_For_DisplayMem".equals(action)) {     

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String str = req.getParameter("orderNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入訂單編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/shop/memListAllSprod_order.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String orderNo = str;

				/***************************2.開始查詢資料****************************************/
				Sprod_orderService sprodOrderSvc = new Sprod_orderService();
				Sprod_orderVO sprodOrderVO = sprodOrderSvc.getOneSprodOrderVO(orderNo);

				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("sprodOrderVO", sprodOrderVO);        

				String url = "/front-end/shop/memListOneSprod_order.jsp";

				RequestDispatcher successView = req.getRequestDispatcher(url);
			
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {

				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/shop/memListAllSprod_order.jsp");
				failureView.forward(req, res);
			}
		}
			
//		====================================會員顯示更新前的資料=========================================		
		if ("getOne_For_UpdateMem".equals(action)) { 
	
			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數****************************************/
				String orderNo = req.getParameter("orderNo");
			
				/***************************2.開始查詢資料****************************************/
				Sprod_orderService sprodOrderSvc = new Sprod_orderService();
				Sprod_orderVO sprodOrderVO = sprodOrderSvc.getOneSprodOrderVO(orderNo);

				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("sprodOrderVO", sprodOrderVO);        
				String url = "/front-end/shop/memUpdate_sprod_order_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);

				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/shop/memListAllSprod_order.jsp");
				failureView.forward(req, res);
			}
		}
		
//		====================================會員更新收貨狀態資料=========================================
		if ("updateMem".equals(action)) {       //來自/shop_product/update_shop_product_input.jsp		
			List<String> errorMsgs = new LinkedList<String>();		
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {

				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//				訂單編號
				String orderNo = req.getParameter("orderNo");
//				會員編號
				String memNo = req.getParameter("memNo");	
//				寄送方式	
				Integer tranMethod = null;
				try {
					tranMethod = new Integer(req.getParameter("tranMethod").trim());
				}catch(NumberFormatException e){
					tranMethod = 0;
					errorMsgs.add("輸入格式錯誤，請輸入數字");				
				}
//				收件人姓名
				String addresseeName = req.getParameter("addresseeName");
				String addresseeNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (addresseeName == null || addresseeName.trim().length() == 0) {
					errorMsgs.add("收件人姓名請勿空白");
				}else if(!addresseeName.trim().matches(addresseeNameReg)){
					errorMsgs.add("收件人姓名內容: 只能是中、英文字母、數字和_ , 且中文名長度必需在2到10之間");
				}
//				地址
				String tranAdd = req.getParameter("tranAdd");
				String tranAddReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,30}$";
				if (tranAdd == null || tranAdd.trim().length() == 0) {
					errorMsgs.add("地址請勿空白");
				}else if(!tranAdd.trim().matches(tranAddReg)) {
					errorMsgs.add("地址: 只能是中、英文字母、數字和_ , 且長度必需在2到30之間");
				}
//				電子信箱
				String addresseeMail = req.getParameter("addresseeMail");
				if (addresseeMail == null || addresseeMail.trim().length() == 0) {
					errorMsgs.add("電子信箱請勿空白");
				}
//				訂單總金額
				Integer orderTotal = new Integer(req.getParameter("orderTotal"));
//				訂單狀態(預設為未出貨)
				Integer orderStatus = new Integer(req.getParameter("orderStatus"));
//				適用的優惠編號(可為空值)
				String spNo = req.getParameter("spNo");		
				
				Sprod_orderVO sprodOrderVO = new Sprod_orderVO();
				sprodOrderVO.setOrderNo(orderNo);
				sprodOrderVO.setMemNo(memNo);
				sprodOrderVO.setTranMethod(tranMethod);
				sprodOrderVO.setTranAdd(tranAdd);
				sprodOrderVO.setAddresseeName(addresseeName);
				sprodOrderVO.setAddresseeMail(addresseeMail);
				sprodOrderVO.setOrderTotal(orderTotal);
				sprodOrderVO.setOrderStatus(orderStatus);
				sprodOrderVO.setSpNo(spNo);
				
				if (!errorMsgs.isEmpty()) {
		
					req.setAttribute("sprodOrderVO", sprodOrderVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/shop/memUpdate_sprod_order_input.jsp");
					failureView.forward(req, res);
					return;
				}					
				/***************************2.開始修改資料*****************************************/
				Sprod_orderService sprodOrderSvc = new Sprod_orderService();
				sprodOrderVO = sprodOrderSvc.updateSprodOrder(memNo, tranMethod, tranAdd, addresseeName, addresseeMail, orderTotal, orderStatus, spNo, orderNo);
	
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("sprodOrderVO", sprodOrderVO);
				String url = "/front-end/shop/memListOneSprod_order.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);  //修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);
				
			}catch (Exception e) {

				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/shop/memUpdate_sprod_order_input.jsp");
				failureView.forward(req, res);
			}
		}		
		
		
////		===========================================會員新增訂單==================================
		if ("insert".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/			
//				會員編號
				String memNo = req.getParameter("memNo");	
//				寄送方式	
				Integer tranMethod = null;
				try {
					tranMethod = new Integer(req.getParameter("tranMethod").trim());
				}catch(NumberFormatException e){
					tranMethod = 0;
					errorMsgs.add("輸入格式錯誤，請輸入數字");				
				}
//				收件人姓名
				String addresseeName = req.getParameter("addresseeName");
				String addresseeNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (addresseeName == null || addresseeName.trim().length() == 0) {
					errorMsgs.add("收件人姓名請勿空白");
				}else if(!addresseeName.trim().matches(addresseeNameReg)){
					errorMsgs.add("收件人姓名內容: 只能是中、英文字母、數字和_ , 且中文名長度必需在2到10之間");
				}
//				地址
				String country = req.getParameter("country");
				String district = req.getParameter("district");
				String tranAdd0 = req.getParameter("tranAdd");//詳細地址
				String tranAddReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,30}$";
				if (tranAdd0 == null || tranAdd0.trim().length() == 0) {
					errorMsgs.add("地址請勿空白");
				}else if(!tranAdd0.trim().matches(tranAddReg)) {
					errorMsgs.add("地址: 只能是中、英文字母、數字和_ , 且長度必需在2到30之間");
				}
				String tranAdd = country + district + tranAdd0;

//				電子信箱
				String addresseeMail = req.getParameter("addresseeMail");
				if (addresseeMail == null || addresseeMail.trim().length() == 0) {
					errorMsgs.add("電子信箱請勿空白");
				}
//				訂單總金額
				Integer orderTotal = new Integer(req.getParameter("orderTotal"));
				if ( orderTotal < 0 ) {
					errorMsgs.add("請確認商品數量");
				}
//				訂單狀態(預設為未出貨)
				Integer orderStatus = new Integer(req.getParameter("orderStatus"));
//				適用的優惠編號(可為空值)
				String spNo = req.getParameter("spNo");
														
				Sprod_orderVO sprodOrderVO = new Sprod_orderVO();
				sprodOrderVO.setMemNo(memNo);
				sprodOrderVO.setTranMethod(tranMethod);
				sprodOrderVO.setTranAdd(tranAdd);
				sprodOrderVO.setAddresseeName(addresseeName);
				sprodOrderVO.setAddresseeMail(addresseeMail);
				sprodOrderVO.setOrderTotal(orderTotal);
				sprodOrderVO.setOrderStatus(orderStatus);
				sprodOrderVO.setSpNo(spNo);
//				取得訂單明細
				HttpSession session = req.getSession();		
				List<Sprod_detailVO> sprodDetailVOList = (List<Sprod_detailVO>)session.getAttribute("sprodDetail");
				Shop_productService shopProductSvc = new Shop_productService();  //為取得商品名稱
//				檢查購物車商品數量
				for(Sprod_detailVO lists: sprodDetailVOList) {							
					String prodNo = lists.getProdNo();
					Integer quantity = lists.getQuantity();
					if (quantity < 0) {
						errorMsgs.add("您的" + shopProductSvc.getOneShopProductVO(prodNo).getProdName() + "數量異常");
					}
				}

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("sprodOrderVO", sprodOrderVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/shop/Checkout.jsp");
					failureView.forward(req, res);
					return;
				}

				/***************************2.開始新增資料***************************************/
synchronized (this) {			
//				=========================新增訂單=====================================
					Sprod_orderService sprodOrderSvc = new Sprod_orderService();
					sprodOrderVO = sprodOrderSvc.addSprodOrder(memNo, tranMethod, tranAdd, addresseeName, addresseeMail, orderTotal, orderStatus, spNo);		
//				=========================新增訂單明細================================
//				取得剛新增的訂單編號
					
					List<Sprod_orderVO> Sprod_orderVOList = sprodOrderSvc.getAll();
					String orderNo = (Sprod_orderVOList.get(Sprod_orderVOList.size()-1)).getOrderNo();																		
						
						
					for(Sprod_detailVO lists: sprodDetailVOList) {		
					
						String prodNo = lists.getProdNo();
						Integer quantity = lists.getQuantity();
						Integer evaStar = null;
						String evaCont = null;
						
						Sprod_detailVO sprodDetailVO = new Sprod_detailVO();
//						取得剛新增的訂單編號					
						sprodDetailVO.setOrderNo(orderNo);
//						傳入購物車商品編號及數量
						sprodDetailVO.setProdNo(prodNo);
						sprodDetailVO.setQuantity(quantity);
//						評價分數和內容先傳空值
						sprodDetailVO.setEvaStar(evaStar);
						sprodDetailVO.setEvaCont(evaCont);
									
						Sprod_detailService sprodDetailSvc = new Sprod_detailService();
						sprodDetailSvc.addSprodDetail(orderNo, prodNo, quantity, evaStar, evaCont);
						
					}
					sprodOrderVO.setOrderNo(orderNo); //將剛新增的訂單編號存入VO轉交給checkOutEnd
					session.removeAttribute("sprodDetail");
					session.removeAttribute("shoppingcart");
}					
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				req.setAttribute("sprodOrderVO", sprodOrderVO);
				String url = "/front-end/shop/checkOutEnd.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);		
			}catch(Exception e){
				/***************************其他可能的錯誤處理**********************************/
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/shop/Checkout.jsp");
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
				String orderNo = new String(req.getParameter("orderNo"));
				
				/***************************2.開始刪除資料***************************************/
				Sprod_orderService sprodOrderSvc = new Sprod_orderService();
				sprodOrderSvc.deleteSprodOrder(orderNo);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/front-end/shop/memListAllSprod_order.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/shop/memListAllSprod_order.jsp");
				failureView.forward(req, res);
			}
		}
		
//		======廠商用訂單編號查詢一個訂單=========
		if ("getOne_For_DisplayVen".equals(action)) {     

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String str = req.getParameter("orderNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入訂單編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("vendorSelectSprodOrderHome.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String orderNo = str;

				/***************************2.開始查詢資料****************************************/
				Sprod_orderService sprodOrderSvc = new Sprod_orderService();
				Sprod_orderVO sprodOrderVO = sprodOrderSvc.getOneSprodOrderVO(orderNo);

				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("sprodOrderVO", sprodOrderVO);        
				String url = "vendorListOneSprod_order.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
			
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {

				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("vendorSelectSprodOrderHome.jsp");
				failureView.forward(req, res);
			}
		}
			
//		====================================廠商顯示訂單更新前的資料=========================================		
		if ("getOne_For_UpdateVen".equals(action)) { 
	
			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數****************************************/
				String orderNo = req.getParameter("orderNo");
			
				/***************************2.開始查詢資料****************************************/
				Sprod_orderService sprodOrderSvc = new Sprod_orderService();
				Sprod_orderVO sprodOrderVO = sprodOrderSvc.getOneSprodOrderVO(orderNo);

				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("sprodOrderVO", sprodOrderVO);        
				String url = "vendorUpdate_sprod_order_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);

				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("vendorListAllSprod_order.jsp");
				failureView.forward(req, res);
			}
		}
		
//		====================================廠商更新出貨狀態資料=========================================
		if ("updateVen".equals(action)) {       //來自/shop_product/update_shop_product_input.jsp		
			List<String> errorMsgs = new LinkedList<String>();		
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {

				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//				訂單編號
				String orderNo = req.getParameter("orderNo");
//				會員編號
				String memNo = req.getParameter("memNo");	
//				寄送方式	
				Integer tranMethod = null;
				try {
					tranMethod = new Integer(req.getParameter("tranMethod").trim());
				}catch(NumberFormatException e){
					tranMethod = 0;
					errorMsgs.add("輸入格式錯誤，請輸入數字");				
				}
//				收件人姓名
				String addresseeName = req.getParameter("addresseeName");
				String addresseeNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (addresseeName == null || addresseeName.trim().length() == 0) {
					errorMsgs.add("收件人姓名請勿空白");
				}else if(!addresseeName.trim().matches(addresseeNameReg)){
					errorMsgs.add("收件人姓名內容: 只能是中、英文字母、數字和_ , 且中文名長度必需在2到10之間");
				}
//				地址
				String tranAdd = req.getParameter("tranAdd");
				String tranAddReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,30}$";
				if (tranAdd == null || tranAdd.trim().length() == 0) {
					errorMsgs.add("地址請勿空白");
				}else if(!tranAdd.trim().matches(tranAddReg)) {
					errorMsgs.add("地址: 只能是中、英文字母、數字和_ , 且長度必需在2到30之間");
				}
//				電子信箱
				String addresseeMail = req.getParameter("addresseeMail");
				if (addresseeMail == null || addresseeMail.trim().length() == 0) {
					errorMsgs.add("電子信箱請勿空白");
				}
//				訂單總金額
				Integer orderTotal = new Integer(req.getParameter("orderTotal"));
//				訂單狀態(預設為未出貨)
				Integer orderStatus = new Integer(req.getParameter("orderStatus"));

//				適用的優惠編號(可為空值)
				String spNo = req.getParameter("spNo");		
				
				Sprod_orderVO sprodOrderVO = new Sprod_orderVO();
				sprodOrderVO.setOrderNo(orderNo);
				sprodOrderVO.setMemNo(memNo);
				sprodOrderVO.setTranMethod(tranMethod);
				sprodOrderVO.setTranAdd(tranAdd);
				sprodOrderVO.setAddresseeName(addresseeName);
				sprodOrderVO.setAddresseeMail(addresseeMail);
				sprodOrderVO.setOrderTotal(orderTotal);
				sprodOrderVO.setOrderStatus(orderStatus);
				sprodOrderVO.setSpNo(spNo);
				
					
				/***************************2.開始修改資料*****************************************/
				Sprod_orderService sprodOrderSvc = new Sprod_orderService();
				sprodOrderVO = sprodOrderSvc.updateSprodOrder(memNo, tranMethod, tranAdd, addresseeName, addresseeMail, orderTotal, orderStatus, spNo, orderNo);
	
		         // =======================================推播========================================
				//推播新商品
				Set<String> memSet = new HashSet<String>();       //宣告推播需要的型別     
				memSet.add(memNo);
								
				String Info = "訂單編號:" + orderNo + "出貨完成囉";  //傳送的訊息
				Robot.chatRobot(Info, memSet);
				
					// ============================================推播=========================================
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("sprodOrderVO", sprodOrderVO);
				String url = "vendorListOneSprod_order.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);  
				successView.forward(req, res);
				
			}catch (Exception e) {

				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("vendorUpdate_sprod_order_input.jsp");
				failureView.forward(req, res);
			}
		}	
		
	}
	
}
