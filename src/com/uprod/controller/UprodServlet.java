package com.uprod.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.uprod.model.*;

@MultipartConfig(fileSizeThreshold = 1920 * 1080, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class UprodServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

				String ProdNo = req.getParameter("ProdNo");
				String ProdNoReg = "^[U]\\d{3}$";
				if (ProdNo == null || (ProdNo.trim()).length() == 0) {
					errorMsgs.add("請輸商品編號");
				}
				else if (!ProdNo.trim().matches(ProdNoReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("商品名稱格式不正確");
				}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/uprod/used_shop.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				UprodService uprodSvc = new UprodService();
				UprodVO uprodVO = uprodSvc.getOneUprod(ProdNo);
				if (uprodVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/uprod/used_shop.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("uprodVO", uprodVO); // 資料庫取出的empVO物件,存入req
				String url = "/front-end/uprod/used_product.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/uprod/used_shop.jsp");
				failureView.forward(req, res);
			}
		}
		

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
				/*************************** 1.接收請求參數 ****************************************/
				String ProdNo = new String(req.getParameter("ProdNo"));

				/*************************** 2.開始查詢資料 ****************************************/
				UprodService uprodSvc = new UprodService();
				UprodVO uprodVO = uprodSvc.getOneUprod(ProdNo);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("uprodVO", uprodVO); // 資料庫取出的empVO物件,存入req
				String url1 ="/front-end/uprod/uprod_shopping_cart.jsp"; //購買商品
				String url2 ="/front-end/uprod/uprod_update.jsp";        //修改商品
				
				String url=null;
				String update1=req.getParameter("update1");
				
				switch(update1) {
				    case "2":
				    	url=url2;
				        break;
				    case "1":
				    	url=url1;
				        break;
				    default:
				    	break;
			     }
				
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);
			    
				/*************************** 其他可能的錯誤處理 **********************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/uprod/used_shop.jsp");
//				failureView.forward(req, res);
//			}
		}
		

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {

				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String ProdNo = new String(req.getParameter("ProdNo").trim());

				String ProdName = req.getParameter("ProdName");
				String ProdNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{3,20}$";
				if (ProdName == null || ProdName.trim().length() == 0) {
					errorMsgs.add("商品名稱: 請勿空白");
				} else if (!ProdName.trim().matches(ProdNameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("商品名稱: 只能是中、英文字母、數字和_ , 且長度必需在3到10之間");
				}
				String ProdIntro = req.getParameter("ProdIntro");
				
				if (ProdIntro == null || ProdIntro.trim().length() == 0) {
					errorMsgs.add("商品介紹: 請勿空白");
				} else if (!(ProdIntro.trim().length()<300)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("商品介紹:介紹長度必需在300個字以下");
				}
			
				Integer Price=null;
				try {
					Price = new Integer(req.getParameter("Price").trim());
				} catch (NumberFormatException e) {
					Price = 0;
					errorMsgs.add("價格請填數字.");
				}
				
				java.sql.Timestamp IncreaseTime = null;
			
				IncreaseTime = new java.sql.Timestamp(System.currentTimeMillis());
				
				
				String ProdType = new String(req.getParameter("ProdType").trim());
				
				if (ProdType == null || ProdType.trim().length() == 0) {
					errorMsgs.add("商品種類: 請勿空白");
				} 
				
				String ProdStatus = new String(req.getParameter("ProdStatus").trim());
				
				Part part = req.getPart("Photo");
				byte[] Photo = getPicByteArr(ProdNo,part);
			    
				UprodVO uprodVO = new UprodVO();
				uprodVO.setProdName(ProdName);
				uprodVO.setProdIntro(ProdIntro);
				uprodVO.setPrice(Price);
				uprodVO.setIncreaseTime(IncreaseTime);
				uprodVO.setProdType(ProdType);
				uprodVO.setProdStatus(ProdStatus);
				uprodVO.setPhoto(Photo);
				uprodVO.setProdNo(ProdNo);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("uprodVO", uprodVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/uprod/uprod_update.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				UprodService uprodSvc = new UprodService();
				uprodVO = uprodSvc.updateUprod(ProdName, ProdIntro, Price, ProdType, ProdStatus, IncreaseTime, Photo, ProdNo);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("uprodVO", uprodVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/front-end/uprod/uprod_manage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/uprod/uprod_update.jsp");
				failureView.forward(req, res);
			}
		}

		
		if ("buy".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {

				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String ProdNo = new String(req.getParameter("ProdNo").trim());
                
				String CustNo = req.getParameter("CustNo");
				String CustNoReg = "^[M]\\d{3}$";// Regular Expression
				if (CustNo == null || CustNo.trim().length() == 0) {
					errorMsgs.add("買家編號: 請勿空白");
				} else if (!CustNo.trim().matches(CustNoReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("買家編號: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}
				
				String ProdStatus="PS2";
				String OrderStatus="OS0";
				String ReceiveStatus="RS0";
				java.sql.Timestamp OrderTime = null;
			
				OrderTime = new java.sql.Timestamp(System.currentTimeMillis());
				
				
				String TranMethod = new String(req.getParameter("TranMethod").trim());
				
				String TranAddr1 = req.getParameter("county");
				String TranAddr2 = req.getParameter("district");
				String TranAddr3 = req.getParameter("TranAddr3");
				String TranAddr = TranAddr1+TranAddr2+TranAddr3;
				if (TranAddr == null || TranAddr.trim().length() == 0) {
					errorMsgs.add("寄送地址 請勿空白");
				} else if (!(TranAddr.trim().length()<3000)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("寄送地址 地址必需在300個字以下");
				}
				
				UprodVO uprodVO = new UprodVO();
				uprodVO.setCustNo(CustNo);
				uprodVO.setProdStatus(ProdStatus);
				uprodVO.setOrderStatus(OrderStatus);
				uprodVO.setReceiveStatus(ReceiveStatus);
				uprodVO.setOrderTime(OrderTime);
				uprodVO.setTranMethod(TranMethod);
				uprodVO.setTranAddr(TranAddr);
				uprodVO.setProdNo(ProdNo);
			
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("uprodVO", uprodVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/uprod/uprod_shopping_cart.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				UprodService uprodSvc = new UprodService();
				uprodVO = uprodSvc.buyUprod(CustNo, ProdStatus,OrderStatus,ReceiveStatus, OrderTime,TranMethod,TranAddr,ProdNo);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("uprodVO", uprodVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/front-end/uprod/uprod_shopping_end.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/uprod/uprod_shopping_cart.jsp.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String SellerNo = req.getParameter("SellerNo");
				String SellerNoReg = "^[M]\\d{3}$";// Regular Expression
				if (SellerNo == null || SellerNo.trim().length() == 0) {
					errorMsgs.add("賣家編號: 請勿空白");
				} else if (!SellerNo.trim().matches(SellerNoReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("賣家編號: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}
				
				String ProdName = req.getParameter("ProdName");
				String ProdNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{3,20}$";
				if (ProdName == null || ProdName.trim().length() == 0) {
					errorMsgs.add("商品名稱: 請勿空白");
				} else if (!ProdName.trim().matches(ProdNameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("商品名稱: 只能是中、英文字母、數字和_ , 且長度必需在3到10之間");
				}
				String ProdIntro = req.getParameter("ProdIntro");
				
				if (ProdIntro == null || ProdIntro.trim().length() == 0) {
					errorMsgs.add("商品介紹: 請勿空白");
				} else if (!(ProdIntro.trim().length()<300)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("商品介紹:介紹長度必需在300個字以下");
				}
				
				Integer Price=null;
				try {
					Price = new Integer(req.getParameter("Price").trim());
				} catch (NumberFormatException e) {
					Price = 0;
					errorMsgs.add("價格請填數字.");
				}
				
			    String ProdStatus="PS0";
				
				String ProdType = new String(req.getParameter("ProdType").trim());
				
				if (ProdType == null || ProdType.trim().length() == 0) {
					errorMsgs.add("商品種類: 請勿空白");
				} 
				
				Part part = req.getPart("Photo");
				byte[] Photo = getPicByteArr(null,part);
				
				if(Photo==null) errorMsgs.add("請上傳商品照片(必填)");
				
				UprodVO uprodVO = new UprodVO();
				uprodVO.setSellerNo(SellerNo);
				uprodVO.setProdName(ProdName);
				uprodVO.setProdIntro(ProdIntro);
				uprodVO.setPrice(Price);
				uprodVO.setProdType(ProdType);
				uprodVO.setProdStatus(ProdStatus);
				uprodVO.setPhoto(Photo);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("uprodVO", uprodVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/uprod/uprod_add.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				UprodService uprodSvc = new UprodService();
				uprodVO = uprodSvc.addUprod(SellerNo, ProdName, ProdIntro, Price, ProdStatus, ProdType,Photo);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/front-end/uprod/uprod_manage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/uprod/uprod_add.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("shipment".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {

				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String ProdNo = new String(req.getParameter("ProdNo").trim());
			    
				UprodVO uprodVO = new UprodVO();
				uprodVO.setProdNo(ProdNo);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("uprodVO", uprodVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/uprod/uprod_seller_detail.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				UprodService uprodSvc = new UprodService();
				uprodVO = uprodSvc.shipment(ProdNo);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("uprodVO", uprodVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/front-end/uprod/uprod_seller_detail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/uprod/uprod_seller_detail.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("receive".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {

				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String ProdNo = new String(req.getParameter("ProdNo").trim());
               
				UprodVO uprodVO = new UprodVO();
				uprodVO.setProdNo(ProdNo);
			
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("uprodVO", uprodVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/uprod/uprod_customer.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				UprodService uprodSvc = new UprodService();
				uprodVO = uprodSvc.receive(ProdNo);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("uprodVO", uprodVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/front-end/uprod/uprod_customer.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/uprod/uprod_customer.jsp");
				failureView.forward(req, res);
			}
		}
	}
	
	
	public static byte[] getPicByteArr(String ProdNo,Part part) throws IOException, ServletException {
		UprodService uprodSvc = new UprodService();
		byte[] picArr = null;
		
		InputStream in = part.getInputStream();		
		if(in.available()!=0) {
			picArr = new byte[in.available()];
			in.read(picArr);
			in.close();
		}
		else {
			picArr = ProdNo== null? null: uprodSvc.getOneUprod(ProdNo).getPhoto();
		}
		return picArr;
			
	}
}
