package com.rep_article.controller;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.article.model.ArticleService;
import com.rep_article.model.*;

public class RepArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)	
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
//		檢舉文章
		RepArticleService raSvc = new RepArticleService();
		if("reparticle".equals(action)) {
			try {
				String memno = req.getParameter("memno");
				String artno = req.getParameter("artno");
				String repreason = req.getParameter("repreason");
				
				raSvc.addRepArticle(memno, artno, repreason);
				
			} catch (Exception e) {}
		}else if ("rep_approved".equals(action)) {
			try {
				String repno = req.getParameter("repno");
				
				raSvc.update_approved(repno);
				
				ArticleService articleSvc = new ArticleService();
				String str = raSvc.getOneRepArticle(repno).getArtno();
				
				articleSvc.update_report(str);
			} catch (Exception e) {
				System.out.println("Ra:失敗");
			}
		}else if ("rep_notapproved".equals(action)) {
			try {
				String repno = req.getParameter("repno");
				
				raSvc.update_notapproved(repno);
				System.out.println("Ran:成功");
			} catch (Exception e) {
				System.out.println("Ran:失敗");
			}
		}
		
	}
	
}