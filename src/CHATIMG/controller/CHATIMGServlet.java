package CHATIMG.controller;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import CHATIMG.model.*;


@MultipartConfig(fileSizeThreshold = 1920 * 1080, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class CHATIMGServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1106538520437600349L;

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
			
				String chatimgno = req.getParameter("chatimgno");
				byte[] chatimg = null;
				Part part = req.getPart("chatimg");
				InputStream in = part.getInputStream();
				if (in.available() != 0) {
					byte[] buf = new byte[in.available()];
					in.read(buf);
					chatimg = buf;
				}
				/***************************2.開始新增資料***************************************/
				CHATIMGService chatimgSvc = new CHATIMGService();
				chatimgSvc.addCHATIMG(chatimgno,chatimg);
	
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
