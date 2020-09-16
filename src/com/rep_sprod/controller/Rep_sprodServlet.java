package com.rep_sprod.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rep_sprod.model.*;
import com.rep_sprod.model.Rep_sprodService;

public class Rep_sprodServlet extends HttpServlet{

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException{
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException{
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
//		============================商品檢舉單項查詢======================
		if("getOne_For_Display".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String str = req.getParameter("repNo");
				
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入檢舉編號");
				}
			
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/rep_sprod/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				String repNo = str;
						
				Rep_sprodService repSprodSvc = new Rep_sprodService();
				Rep_sprodVO repSprodVO = repSprodSvc.getOneRepSprodVO(repNo);
				req.setAttribute("repSprodVO", repSprodVO);    
				String url = "/rep_sprod/listOneRep_sprod.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/rep_sprod/select_page.jsp");
				failureView.forward(req, res);
			}
			
		}
			
		
		
//		=========================抓取單項檢舉修改前狀態=====================
		if("getOne_For_Update".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				String repNo = req.getParameter("repNo");
				Rep_sprodService repSprodSvc = new Rep_sprodService();
				Rep_sprodVO repSprodVO = repSprodSvc.getOneRepSprodVO(repNo);
				
				req.setAttribute("repSprodVO", repSprodVO);
				String url = "/rep_sprod/update_rep_sprod_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
							
			}catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/rep_sprod/listAllRep_sprod.jsp");
				failureView.forward(req, res);						
			}
		
		}
//		==============================更新單項檢舉狀態======================
		if ("update".equals(action)) { 
			List<String> errorMsgs = new LinkedList<String>();		
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {			
				
				String repNo = req.getParameter("repNo");
				Integer repStatus = new Integer(req.getParameter("repStatus"));
				Rep_sprodVO repSprodVO = new Rep_sprodVO();
				repSprodVO.setRepNo(repNo);
				repSprodVO.setRepStatus(repStatus);
				
//				錯誤訊息
				if (!errorMsgs.isEmpty()) {			
					req.setAttribute("repSprodVO", repSprodVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/rep_sprod/update_rep_sprod_input.jsp");
					failureView.forward(req, res);
					return;
				}
				
				Rep_sprodService repSprodSvc = new Rep_sprodService();
				repSprodVO = repSprodSvc.updateRepSpord(repNo, repStatus);
				
				req.setAttribute("repSprodVO", repSprodVO);
				String url = "/rep_sprod/listAllRep_sprod.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
						
			}catch (Exception e) {

				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/rep_sprod/update_rep_sprod_input.jsp");
				failureView.forward(req, res);
			}
		}
		
		
//		================================新增商品檢舉========================
		if ("insert".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String memNo = req.getParameter("memNo");
				String prodNo = req.getParameter("prodNo");
				String repReason = req.getParameter("repReason");
//				檢舉文字內容條件
				String reqReasonReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,100}$";
				if (repReason == null || repReason.trim().length() == 0) {
					errorMsgs.add("檢舉內容請勿空白");
				}else if(!repReason.trim().matches(reqReasonReg)){
					errorMsgs.add("檢舉內容請輸入中、英文字母、數字 , 且長度必需在2到100之間");
				}
				
				Integer repStatus = new Integer(req.getParameter("repStatus"));
				
				Rep_sprodVO repSprodVO = new Rep_sprodVO();
				repSprodVO.setMemNo(memNo);
				repSprodVO.setProdNo(prodNo);
				repSprodVO.setRepReason(repReason);
				repSprodVO.setRepStatus(repStatus);
				
//				檢查有無錯誤訊息
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("repSprodVO", repSprodVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/rep_sprod/addRep_sprod.jsp");
					failureView.forward(req, res);
					return;
				}
				
				Rep_sprodService repSprodSvc = new Rep_sprodService();
				repSprodVO = repSprodSvc.addRepSprod(memNo, prodNo, repReason, repStatus);
				
				String url = "/rep_sprod/listAllRep_sprod.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}catch(Exception e){
			errorMsgs.add(e.getMessage());
			RequestDispatcher failureView = req.getRequestDispatcher("/rep_sprod/addRep_sprod.jsp");
			failureView.forward(req, res);
			}
		}
		
//		===========================刪除檢舉=======================
		if ("delete".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
		
				String repNo = new String(req.getParameter("repNo"));
				
				Rep_sprodService repSprodSvc = new Rep_sprodService();
				repSprodSvc.deleteRepSprod(repNo);
				
				String url = "/rep_sprod/listAllRep_sprod.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
		
			}catch(Exception e) {
				errorMsgs.add("刪除資料失敗"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/rep_sprod/listAllRep_sprod.jsp");
				failureView.forward(req, res);
			}			
		}
		
	}
}
