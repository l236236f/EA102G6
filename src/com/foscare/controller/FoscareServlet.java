package com.foscare.controller;

import java.io.IOException;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.foscare.model.*;
import com.fosmon.model.FosmService;
import com.fosmon.model.FosmVO;


import com.tools.PhotoToByte;

@MultipartConfig
@WebServlet("/FoscareServlet")
public class FoscareServlet extends HttpServlet {
	

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
		
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		HttpSession se = req.getSession();
		
		if("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			try {
				
				String memNo = req.getParameter("memNo");
				String memNOReg = "^[M]{1}[0-9]{3}$";
				if (memNo == null || memNo.trim().length() == 0) {
					errorMsgs.add("會員編號: 請勿空白");
				} else if(!memNo.trim().matches(memNOReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員編號: 只能是M開頭加上3個0-9的數字");
	            }
				
				String petNo = req.getParameter("petNo");
				String petNOReg = "^[P]{1}[0-9]{3}$";
				if (petNo == null || petNo.trim().length() == 0) {
					errorMsgs.add("寵物編號: 請勿空白");
				} else if(!petNo.trim().matches(petNOReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("寵物編號: 只能是P開頭加上3個0-9的數字");
	            }
				
				java.sql.Date startTime = null;
				java.sql.Date endTime = null;
				
				try {
					startTime = java.sql.Date.valueOf(req.getParameter("startTime").trim());
				} catch (IllegalArgumentException e) {
					startTime=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入開始日期!");
				}
				try {
					endTime = java.sql.Date.valueOf(req.getParameter("endTime").trim());
				} catch (IllegalArgumentException e) {
					endTime=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入結束日期!");
				}
				Integer fosMoney=null;
				try {
					fosMoney = Integer.valueOf(req.getParameter("fosMoney").trim());
				}catch(NumberFormatException e) {
					fosMoney=0;
					errorMsgs.add("金額請填入數字");
				}
				String fosType = null;
				try {
					fosType = req.getParameter("fosType").trim();
				}catch(NullPointerException e) {
					errorMsgs.add("請選擇寵物種類");
				}
				
				String fosSize = null;
				try {
					fosSize = req.getParameter("fosSize").trim();
				}catch(NullPointerException e) {
					errorMsgs.add("請選擇寵物大小");
				}
				
				String fosnrun = null;
				try {
					fosnrun = req.getParameter("fosnrun").trim();
				}catch(NullPointerException e) {
					errorMsgs.add("請選擇寵物是否需要遛");
				}
				String fosRemark = null;
				fosRemark = req.getParameter("fosRemark");
				
				FosterVO fosterVO = new FosterVO();
				fosterVO.setMemNo(memNo);
				fosterVO.setPetNo(petNo);
				fosterVO.setFosStartTime(startTime);
				fosterVO.setFosEndTime(endTime);
				fosterVO.setFosMoney(fosMoney);
				fosterVO.setFosType(fosType);
				fosterVO.setFosSize(fosSize);
				fosterVO.setFosnrun(fosnrun);
				fosterVO.setFosRemark(fosRemark);		
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("fosterVO", fosterVO); // 含有輸入格式錯誤的empVO物件,也存入req
					se.setAttribute("isSuccess", "No");
					se.setAttribute("successVal", "修改失敗!");
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/foscare/addfos.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				/***************************2.開始新增資料***************************************/
				FosterService fosS = new FosterService();
				fosterVO = fosS.addFoscare(memNo, petNo, startTime, endTime, fosnrun, fosSize, fosType, fosRemark, fosMoney);
				se.setAttribute("isSuccess", "Yes");
				se.setAttribute("successVal", "新增成功!");
				String  url=req.getParameter("url");  
				res.sendRedirect(url);  

			}catch(Exception e) {
				errorMsgs.add(e.getMessage());
				se.setAttribute("isSuccess", "No");
				se.setAttribute("successVal", "修改失敗!");
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/foscare/addfos.jsp");
				failureView.forward(req, res);
			}
		}
		if("getOne_For_Update".equals(action)) {

			try {
			String fosNo = req.getParameter("fosNo");
			FosterService fosSvc = new FosterService();
			FosterVO fosVO = fosSvc.getOneFoscare(fosNo);
			req.setAttribute("fosterVO", fosVO);
			String url = "/front-end/foscare/updatefos.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 刪除成功後轉交listAllEmp.jsp
			successView.forward(req, res);
			}catch (Exception e) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/foscare/readfos.jsp");
				failureView.forward(req, res);
			}
		}
		if("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			FosterVO fosterVO= null;
//			try {
				String fosNo = req.getParameter("fosNo");
				String memNo = req.getParameter("memNo");
				String petNo = req.getParameter("petNo");
				String fosmNOReg = "^(FM)[0-9]{3}$";
				String fosmNo = req.getParameter("fosmNo");
				FosmService fmSvc = new FosmService();
				if(fosmNo.length()!=0&&(!fosmNo.trim().matches(fosmNOReg))) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("保母編號: 只能是FM開頭加上3個0-9的數字");
	            }else if(fosmNo.length()!=0&&fmSvc.getOneByFosmNo(fosmNo)==null) {
					errorMsgs.add("無此保母編號，請不要鬧事!");
				}else if(fosmNo.length()!=0&&memNo.equals(fmSvc.getOneByFosmNo(fosmNo).getMemNo())) {
					errorMsgs.add("不能自己當自己的保母，請不要鬧事!");
				}
				java.sql.Date startTime = null;
				java.sql.Date endTime = null;
				try {
					startTime = java.sql.Date.valueOf(req.getParameter("startTime").trim());
				} catch (IllegalArgumentException e) {
					startTime=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入開始日期!");
				}
				try {
					endTime = java.sql.Date.valueOf(req.getParameter("endTime").trim());
				} catch (IllegalArgumentException e) {
					endTime=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入結束日期!");
				}
				Integer fosMoney=null;
				try {
					fosMoney = Integer.valueOf(req.getParameter("fosMoney").trim());
				}catch(NumberFormatException e) {
					fosMoney=0;
					errorMsgs.add("金額請填入數字");
				}
				String fosType = req.getParameter("fosType");
				String fosSize = req.getParameter("fosSize");
				String fosnrun = req.getParameter("fosnrun");
				String fosRemark = req.getParameter("fosRemark");
				String fosStatus = req.getParameter("fosStatus");
				
				fosterVO = new FosterVO();
				fosterVO.setFosNo(fosNo);
				fosterVO.setMemNo(memNo);
				fosterVO.setPetNo(petNo);
				fosterVO.setFosmNo(fosmNo);
				fosterVO.setFosStartTime(startTime);
				fosterVO.setFosEndTime(endTime);
				fosterVO.setFosMoney(fosMoney);
				fosterVO.setFosType(fosType);
				fosterVO.setFosSize(fosSize);
				fosterVO.setFosnrun(fosnrun);
				fosterVO.setFosStatus(fosStatus);
				fosterVO.setFosRemark(fosRemark);
								
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("fosterVO", fosterVO); // 含有輸入格式錯誤的empVO物件,也存入req
					se.setAttribute("isSuccess", "No");
					se.setAttribute("successVal", "修改失敗!");
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/foscare/updatefos.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				/***************************2.開始修改資料***************************************/
				FosterService fosSvc = new FosterService();
				fosSvc.updateFoscare(fosterVO);
								
				if(fosmNo.length()!=0) {
					fosSvc.changeStatus(fosNo, "F1");
				}
				//用重導避免重複提交
				se.setAttribute("isSuccess", "Yes");
				se.setAttribute("successVal", "修改成功!");
				String  url=req.getParameter("url");  
				res.sendRedirect(url);  
				
//			}catch(Exception e) {
//				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
//				se.setAttribute("isSuccess", "No");
//				se.setAttribute("successVal", "修改失敗!");
//				req.setAttribute("fosVO", fosterVO);
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/front-end/foscare/updatefos.jsp");
//				failureView.forward(req, res);
//			}
		}
		if("sign".equals(action)) {
			String AB = req.getParameter("AandB");
			String fosNo = req.getParameter("fosNo");	
			//將收到的Base64圖檔字串拆解並轉成Binary字串
			String signbase = req.getParameter("signjpg");
			String base64Image = signbase.split(",")[1];
			//將Binary字串轉成byte陣列
			byte[] sign = javax.xml.bind.DatatypeConverter.parseBase64Binary(base64Image);
			//呼叫Service存進資料庫
			FosterService fosSvc = new FosterService();
			fosSvc.addSign(sign, fosNo, AB);
			//判斷資料庫資料修改狀態
			FosterVO fosVO = fosSvc.getOneFoscare(fosNo);
			if(fosVO.getFosSignA()!=null&&fosVO.getFosSignB()!=null) {
				fosSvc.changeStatus(fosNo, "F4");
			}else {
				fosSvc.changeStatus(fosNo, "F3");
			}
			//用AB判斷網頁來源
			String url = (AB.equals("A"))?"/front-end/foscare/readfos.jsp":"/front-end/foscare/mreadfos.jsp";
			se.setAttribute("isSuccess", "Yes");
			se.setAttribute("successVal", "簽名成功!");
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			
		}
		if("addEva".equals(action)) {
			try {
			String fosNo = req.getParameter("fosNo");
			String fosmEvacon = req.getParameter("fosmEvacon");
			Integer star = new Integer(req.getParameter("star"));
			String fosmNo = req.getParameter("fosmNo");
			
			FosterService fosSvc = new FosterService();
			fosSvc.addevaluation(fosNo, star, fosmEvacon);
			fosSvc.changeStatus(fosNo, "F8");
			
			FosmService fmSvc = new FosmService();
			System.out.println(fosmNo);
			FosmVO fosmVO = fmSvc.getOneByFosmNo(fosmNo);
			System.out.println(fosmVO);
			fosmVO.setFosmEvacount(fosmVO.getFosmEvacount()+1);
			fosmVO.setFosStar(fosmVO.getFosStar()+star);
			fmSvc.changeStar(fosmVO);
					
			String url = "/front-end/foscare/readfos.jsp";
			se.setAttribute("isSuccess", "Yes");
			se.setAttribute("successVal", "評價成功!");
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);
			
			}catch(Exception e) {
				e.printStackTrace();
			}	
		}
		if("fosmres".equals(action)) {
			String fosmEvares = req.getParameter("fosmText");
			String fosNo = req.getParameter("fosNo");
			FosterService fosSvc = new FosterService();
			fosSvc.addFosmEvares(fosNo, fosmEvares);
		}
		if("changeSta".equals(action)) {
			String fosStatus = req.getParameter("fosStatus");
			String fosNo = req.getParameter("fosNo");
			String AB = req.getParameter("AB");
			FosterService fosSvc = new FosterService();
			fosSvc.changeStatus(fosNo, fosStatus);
			
			//用AB判斷網頁來源
			String url = (AB.equals("A"))?"/front-end/foscare/readfos.jsp":"/front-end/foscare/mreadfos.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);
			
 		}
	
	}
}
