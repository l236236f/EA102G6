package com.article.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.MultipartConfig;

import com.article.model.*;

@MultipartConfig
public class ArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)	
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
//		查詢單筆資料
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("artno");
				String artno = new String(str);
				
				/***************************2.開始查詢資料*****************************************/
				ArticleService artSvc = new ArticleService();
				ArticleVO articleVO = artSvc.getOneArticle(artno);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("articleVO", articleVO);
				String url = "/front-end/article/listOneArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/article/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
//		查詢單筆文章
		if ("getOneArticle_For_Display".equals(action)) { // 來自article.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("artno");
				String artno = new String(str);
				
				/***************************2.開始查詢資料*****************************************/
				ArticleService artSvc = new ArticleService();
				ArticleVO articleVO = artSvc.getOneArticle(artno);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("articleVO", articleVO); // 資料庫取出的articleVO物件,存入req
				String url = "/front-end/article/article_details.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 article_details.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/article/article.jsp");
				failureView.forward(req, res);
			}
		}
		
//		修改單筆資料
		if ("getOne_For_Update".equals(action)) { // 來自listAllArticle.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String artno = new String(req.getParameter("artno"));
				
				/***************************2.開始查詢資料****************************************/
				ArticleService artSvc = new ArticleService();
				ArticleVO articleVO = artSvc.getOneArticle(artno);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("articleVO", articleVO);         // 資料庫取出的articleVO物件,存入req
				String url = "/front-end/article/update_article.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_article_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/article/article.jsp");
				failureView.forward(req, res);
			}
		}
		
		
//		修改資料
		if ("update".equals(action)) { // 來自update_article.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String artno = new String(req.getParameter("artno").trim());
				
				String memno = new String(req.getParameter("memno").trim());
				
				String arttitle = req.getParameter("arttitle");
				String arttitleReg = "^[(\u4e00-\u9fa5)(\\x00-\\x7F)( )]{2,20}$";
				if (arttitle == null || arttitle.trim().length() == 0) {
					errorMsgs.add("文章標題: 請勿空白");
				} else if(!arttitle.trim().matches(arttitleReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("文章標題: 格式錯誤, 且長度必需在2到20之間");
	            }
				
				String artcontent = req.getParameter("artcontent").trim();
				if (artcontent == null || artcontent.trim().length() == 0) {
					errorMsgs.add("文章內容: 請勿空白");
				}	

				ArticleVO articleVO = new ArticleVO();
				articleVO.setArtno(artno);
				articleVO.setMemno(memno);
				articleVO.setArttitle(arttitle);
				articleVO.setArtcontent(artcontent);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("articleVO", articleVO); // 含有輸入格式錯誤的articleVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/article/update_article.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				ArticleService artSvc = new ArticleService();
				articleVO = artSvc.updateArticle(artno, memno, arttitle, artcontent);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("articleVO", articleVO); // 資料庫update成功後,正確的的articleVO物件,存入req
				String url = "/front-end/article/article_details.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneArticle.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/article/update_article.jsp");
				failureView.forward(req, res);
			}
		}

		
//		新增資料
        if ("insert".equals(action)) { // 來自addArticle.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String memno = new String(req.getParameter("memno").trim());
				
				String arttitle = req.getParameter("arttitle");
				String arttitleReg = "^[(\u4e00-\u9fa5)(\\x00-\\x7F)( )]{2,20}$";
				if (arttitle == null || arttitle.trim().length() == 0) {
					errorMsgs.add("文章標題: 請勿空白");
				} else if(!arttitle.trim().matches(arttitleReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("文章標題: 格式錯誤, 且長度必需在2到20之間");
	            }
				
				String artcontent = req.getParameter("artcontent").trim();
				if (artcontent == null || artcontent.trim().length() == 0) {
					errorMsgs.add("文章內容: 請勿空白");
				}
				
				ArticleVO articleVO = new ArticleVO();
				articleVO.setMemno(memno);
				articleVO.setArttitle(arttitle);
				articleVO.setArtcontent(artcontent);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("articleVO", articleVO); // 含有輸入格式錯誤的articleVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/article/addNewArticle.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				ArticleService artSvc = new ArticleService();
				articleVO = artSvc.addArticle(memno, arttitle, artcontent);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				req.setAttribute("articleVO", articleVO);
				
				String url = "/front-end/article/article_details.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllArticle.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/article/addNewArticle.jsp");
				failureView.forward(req, res);
			}
		}
		
		
//      刪除資料
		if ("delete".equals(action)) { // 來自listAllArticle.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String artno = new String(req.getParameter("artno"));
				
				/***************************2.開始刪除資料***************************************/
				ArticleService artSvc = new ArticleService();
				artSvc.deleteArticle(artno);
				
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
		
	}
}
