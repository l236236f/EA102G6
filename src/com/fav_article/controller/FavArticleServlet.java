package com.fav_article.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.fav_article.model.*;

public class FavArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)	throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
//		查詢單筆資料
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("memno");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入會員編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/article/select_page.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				String memno = null;
				try {
					memno = new String(str);
				} catch (Exception e) {
					errorMsgs.add("文章編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/article/select_page.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				FavArticleService favarticleSvc = new FavArticleService();
				FavArticleVO favarticleVO = (FavArticleVO) favarticleSvc.getOneFavArticle(memno);
				if (favarticleVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/article/select_page.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("favarticleVO", favarticleVO); // 資料庫取出的articleVO物件,存入req
				String url = "/front-end/article/listOneArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneArticle.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/article/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
//		新增資料
        if ("insert".equals(action)) { // 來自addArticle.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String memno = new String(req.getParameter("memno").trim());
				
				String artno = new String(req.getParameter("artno").trim());
				
				java.sql.Timestamp favtime = null;
				
				FavArticleVO favArticleVO = new FavArticleVO();
				favArticleVO.setMemno(memno);
				favArticleVO.setArtno(artno);
				favArticleVO.setFavtime(favtime);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("favArticleVO", favArticleVO); // 含有輸入格式錯誤的articleVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/article/addArticle.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				FavArticleService favarticleSvc = new FavArticleService();
				favArticleVO = favarticleSvc.addFavArticle(memno, artno, favtime);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front-end/article/listAllArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllArticle.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/article/addArticle.jsp");
				failureView.forward(req, res);
			}
		}
		
//      刪除資料
		if ("delete".equals(action)) { // 來自listAllArticle.jsp

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String memno = new String(req.getParameter("memno"));
				String artno = new String(req.getParameter("artno"));
				
				/***************************2.開始刪除資料***************************************/
				FavArticleService artSvc = new FavArticleService();
				artSvc.deleteFavArticle(memno, artno);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/front-end/article/listAllArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/article/listAllArticle.jsp");
				failureView.forward(req, res);
			}
		}
	
//  ------------加入收藏----------
//		if("addfavorite".equals(action)) {
//			try {
//				String memno = req.getParameter("memno");
//				String artno = req.getParameter("artno");
//			
//				Fav_Courses_Service favoritecoursesSvc = new Fav_Courses_Service();
//				favoritecoursesSvc.addFavoriteCourses(memno, artno);
//				
//				System.out.println("追蹤成功");
//				
//				String url = req.getParameter("requestURL");
//				RequestDispatcher successView = req.getRequestDispatcher(url);
//				
//				successView.forward(req, res);
//			} catch (Exception e) {
//				String url = req.getParameter("requestURL");
//				RequestDispatcher failureView = 
//					req.getRequestDispatcher(url);
//				failureView.forward(req, res);
//			}

//  ------------移除收藏----------
		
//				String url = req.getParameter("requestURL");
//				RequestDispatcher successView = req.getRequestDispatcher(url);
//				successView.forward(req, res);
			
//			}catch (Exception e){
//				System.out.println(e.getMessage());
//				String url = req.getParameter("requestURL");
//				RequestDispatcher failureView = 
//					req.getRequestDispatcher(url);
//				failureView.forward(req, res);
//			}
//		}
		
	}
}
