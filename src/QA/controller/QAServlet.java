package QA.controller;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import org.json.JSONArray;
import org.json.JSONObject;

import QA.model.*;

@MultipartConfig(fileSizeThreshold = 1920 * 1080, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class QAServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8932219555453444415L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		String action = req.getParameter("action");
		PrintWriter out = res.getWriter();

		if ("insert".equals(action)) { // 來自addQA.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String qachangeman = req.getParameter("qachangeman");

				String qatext = req.getParameter("qatext");
				if (qatext == null || qatext.trim().length() == 0) {
					errorMsgs.put("qakind", "狀態請勿空白");
				}
				Timestamp qachangedate = new Timestamp(System.currentTimeMillis());

				String qastatus = req.getParameter("qastatus").trim();
				if (qastatus == null || qastatus.trim().length() == 0) {
					errorMsgs.put("qastatus", "狀態請勿空白");
				}

				String qakind = req.getParameter("qakind").trim();
				if (qakind == null || qakind.trim().length() == 0) {
					errorMsgs.put("qakind", "狀態請勿空白");
				}
				String qakind2 = req.getParameter("qakind2").trim();
				if (qakind2 == null || qakind2.trim().length() == 0) {
					errorMsgs.put("qakind2", "狀態請勿空白");
				}
				String qakind3 = req.getParameter("qakind3").trim();
				if (qakind3 == null || qakind3.trim().length() == 0) {
					errorMsgs.put("qakind3", "狀態請勿空白");
				}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/qamanage.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				QAService qaSvc = new QAService();
				qaSvc.addQA(qachangeman, qatext, qachangedate, qastatus, qakind, qakind2, qakind3);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/emp/qamanage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllQA.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.put("Exception", e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/qamanage.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自addQA.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String qano = req.getParameter("qano");
				String qachangeman = req.getParameter("qachangeman");
				String qatext = req.getParameter("qatext");

				Timestamp qachangedate = new Timestamp(System.currentTimeMillis());

				String qastatus = req.getParameter("qastatus").trim();

				String qakind = req.getParameter("qakind").trim();
				if (qakind == null || qakind.trim().length() == 0) {
					errorMsgs.put("qakind", "狀態請勿空白");
				}
				String qakind2 = req.getParameter("qakind2").trim();
				if (qakind2 == null || qakind2.trim().length() == 0) {
					errorMsgs.put("qakind2", "狀態請勿空白");
				}
				String qakind3 = req.getParameter("qakind3").trim();
				if (qakind3 == null || qakind3.trim().length() == 0) {
					errorMsgs.put("qakind3", "狀態請勿空白");
				}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/qamanage.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				QAService qaSvc = new QAService();
				qaSvc.updateQA(qano, qachangeman, qatext, qachangedate, qastatus, qakind, qakind2, qakind3);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/emp/qamanage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllQA.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.put("Exception", e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/qamanage.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getAllQajson".equals(action)) {
			QAService qaSvc = new QAService();
			List<QAVO> list = qaSvc.getAll();
			JSONArray jsa = new JSONArray();

			for (QAVO qavo : list) {
				if ("QA1".equals(qavo.getQastatus())) {
					JSONObject object = new JSONObject();
					object.put("kind", qavo.getQakind());
					object.put("kind2", qavo.getQakind2());
					object.put("kind3", qavo.getQakind3());
					object.put("text", qavo.getQatext());
					jsa.put(object);
				}
			}
			out.print(jsa);

		}
		
		if ("qaCheckKind3".equals(action)) {
			QAService qaSvc = new QAService();
			List<QAVO> list = qaSvc.getAll();
			String kind3 = req.getParameter("kind3").trim();
	
			for(QAVO qavo :list) {
				if(kind3.equals(qavo.getQakind3())) {
					out.print("true");
				}
			}
		}
	}
}
