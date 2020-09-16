package com.rep_response.controller;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.article.model.ArticleService;
import com.rep_response.model.*;

public class RepResponseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)	
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
//		���|�^��
		RepResponseService rrSvc = new RepResponseService();
		if ("represponse".equals(action)) {
			try {
				String memno = req.getParameter("memno");
				String resno = req.getParameter("resno");
				String repreason = req.getParameter("repreason");

				rrSvc.addRepResponse(memno, resno, repreason);
				
			} catch (Exception e) {}
		}else if ("rep_approved".equals(action)) {
			try {
				String repno = req.getParameter("repno");
				
				rrSvc.update_approved(repno);
				
				ArticleService articleSvc = new ArticleService();
				String str = rrSvc.getOneRepResponse(repno).getResno();
				
				articleSvc.update_report(str);
			} catch (Exception e) {
				System.out.println("Ra:����");
			}
		}else if ("rep_notapproved".equals(action)) {
			try {
				String repno = req.getParameter("repno");
				
				rrSvc.update_notapproved(repno);
				System.out.println("Ran:���\");
			} catch (Exception e) {
				System.out.println("Ran:����");
			}
		}
		
	}
	
}
