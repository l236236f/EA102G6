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

        if ("insert".equals(action)) { // �Ӧ�addANN.jsp���ШD  
//			System.out.println("insert");
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
			
				String chatimgno = req.getParameter("chatimgno");
				byte[] chatimg = null;
				Part part = req.getPart("chatimg");
				InputStream in = part.getInputStream();
				if (in.available() != 0) {
					byte[] buf = new byte[in.available()];
					in.read(buf);
					chatimg = buf;
				}
				/***************************2.�}�l�s�W���***************************************/
				CHATIMGService chatimgSvc = new CHATIMGService();
				chatimgSvc.addCHATIMG(chatimgno,chatimg);
	
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.put("Exception",e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/emp/annmanage.jsp");
				failureView.forward(req, res);
			}
		}
        
        
        

	}
}
