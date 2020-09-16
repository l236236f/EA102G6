package AUTHORITY.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import AUTHORITY.model.*;

public class AUTHOServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8320059241791561719L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("change".equals(action)) { // 來自addAUTHO.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

				String empno = req.getParameter("empno");

				String feat0001 = req.getParameter("FEAT0001");
				String feat0002 = req.getParameter("FEAT0002");
				String feat0003 = req.getParameter("FEAT0003");
				String feat0004 = req.getParameter("FEAT0004");
				String feat0005 = req.getParameter("FEAT0005");
				String feat0006 = req.getParameter("FEAT0006");
				/***************************
				 * 2.開始刪除全部權限 然後新增資料
				 ***************************************/

				AUTHOService authoSecA = new AUTHOService();

				authoSecA.deleteAUTHOByEmpno(empno);

				if ("true".equals(feat0001)) {
					authoSecA.addAUTHO("FEAT0001", empno);
				}

				if ("true".equals(feat0002)) {
					authoSecA.addAUTHO("FEAT0002", empno);
				}
				if ("true".equals(feat0003)) {
					authoSecA.addAUTHO("FEAT0003", empno);
				}
				if ("true".equals(feat0004)) {
					authoSecA.addAUTHO("FEAT0004", empno);
				}
				if ("true".equals(feat0005)) {
					authoSecA.addAUTHO("FEAT0005", empno);
				}
				if ("true".equals(feat0006)) {
					authoSecA.addAUTHO("FEAT0006", empno);
				}
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/emp/authoritymanage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllAUTHO.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.put("Exception", e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/authoritymanage.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
