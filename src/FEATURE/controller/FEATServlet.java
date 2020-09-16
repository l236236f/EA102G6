package FEATURE.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import FEATURE.model.*;

public class FEATServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 272154666713775929L;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

        if ("insert".equals(action)) { // 來自addFEAT.jsp的請求  
			
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/


				String featname = req.getParameter("featname").trim();
				if (featname == null || featname.trim().length() == 0) {
					errorMsgs.put("featname","功能名請勿空白");
				}
				
				
	

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/FEAT/addFEAT.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				FEATService featSvc = new FEATService();
				featSvc.addFEAT(featname);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/FEAT/listAllFEAT.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllFEAT.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.put("Exception",e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/FEAT/addFEAT.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
