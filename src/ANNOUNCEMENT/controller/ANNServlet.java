package ANNOUNCEMENT.controller;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import ANNOUNCEMENT.model.*;


@MultipartConfig(fileSizeThreshold = 1920 * 1080, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class ANNServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1106738520437600349L;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

        if ("insert".equals(action)) { // 來自addANN.jsp的請求  
//			System.out.println("insert");
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String annchangeman = req.getParameter("annchangeman");
//				System.out.println(annchangeman);
				String anntext = req.getParameter("anntext");
				Timestamp annchangedate = new Timestamp(System.currentTimeMillis());
				String annstatus = "";
				
				String anntitle = req.getParameter("anntitle");
				if (anntitle == null || anntitle.trim().length() == 0) {
					errorMsgs.put("anntitle","title請勿空白");
				}
				byte[] annimg = null;
				Part part = req.getPart("photo");
				InputStream in = part.getInputStream();
				if (in.available() != 0) {
					byte[] buf = new byte[in.available()];
					in.read(buf);
					annimg = buf;
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/emp/annmanage.jsp");
					failureView.forward(req, res);
					return;
				}
			
			
				/***************************2.開始新增資料***************************************/
				ANNService annSvc = new ANNService();
				annSvc.addANN(annchangeman, anntext, annchangedate, annstatus,annimg,anntitle);
	
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back-end/emp/annmanage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllANN.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.put("Exception",e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/emp/annmanage.jsp");
				failureView.forward(req, res);
			}
		}
        
        
        
if ("update".equals(action)) {  
//			System.out.println("update");
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String annno =req.getParameter("annno");
				if (annno == null || annno.trim().length() == 0) {
					errorMsgs.put("annno","annno請勿空白");
				}
				String annchangeman = req.getParameter("annchangeman");
				if (annchangeman == null || annchangeman.trim().length() == 0) {
					errorMsgs.put("annchangeman","annchangeman請勿空白");
				}
				String anntitle = req.getParameter("anntitle");
				if (anntitle == null || anntitle.trim().length() == 0) {
					errorMsgs.put("anntitle","title請勿空白");
				}
		
				String anntext = req.getParameter("anntext");
				
				Timestamp annchangedate = new Timestamp(System.currentTimeMillis());		
				String annstatus = req.getParameter("annstatus");
		
				byte[] annimg = null;
				Part part = req.getPart("photo");
				InputStream in = part.getInputStream();
				if (in.available() != 0) {
					byte[] buf = new byte[in.available()];
					in.read(buf);
					annimg = buf;
				}else {
					annimg = new ANNService().getOneANN(annno).getAnnimg();
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/emp/annmanage.jsp");
					failureView.forward(req, res);
					return;
				}
				
			
				
				
				/***************************2.開始新增資料***************************************/
				ANNService annSvc = new ANNService();
				annSvc.updateANN(annno,annchangeman, anntext, annchangedate, annstatus, annimg,anntitle);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back-end/emp/annmanage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.put("Exception",e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/emp/annmanage.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
