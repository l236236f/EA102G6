package com.art_response.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.art_response.model.*;
import com.article.model.*;

public class ArtResponseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("resno");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入檢舉編號");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/emp/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String resno = null;
				try {
					resno = new String(str);
				} catch (Exception e) {
					errorMsgs.add("檢舉編號格式不正確");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/emp/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				ArtResponseService artresSvc = new ArtResponseService();
				ArtResponseVO artresponseVO = artresSvc.getOneArtResponse(resno);
				if (artresponseVO == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher(" ");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("artresponseVO", artresponseVO); // 資料庫取出的artresponseVO物件,存入req
				String url = " ";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneArtResponse.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(" ");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllArtResponse.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String resno = new String(req.getParameter("resno"));
				
				/***************************2.開始查詢資料****************************************/
				ArtResponseService artresSvc = new ArtResponseService();
				ArtResponseVO artresponseVO = artresSvc.getOneArtResponse(resno);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("artresponseVO", artresponseVO);         // 資料庫取出的artresponseVO物件,存入req
				String url = " ";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(" ");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String resno = new String(req.getParameter("resno").trim());
				
				String artno = new String(req.getParameter("artno").trim());
				
				String memno = new String(req.getParameter("memno").trim());
				
				String rescontent = req.getParameter("rescontent");
				String rescontentReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (rescontent == null || rescontent.trim().length() == 0) {
					errorMsgs.add("檢舉內容: 請勿空白");
				} else if(!rescontent.trim().matches(rescontentReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("檢舉內容: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				java.sql.Timestamp restime = null;
				
				String resstatus = req.getParameter("resstatus").trim();
				if (resstatus == null || resstatus.trim().length() == 0) {
					errorMsgs.add("請選擇文章狀態");
				} 

				ArtResponseVO artresponseVO = new ArtResponseVO();
				artresponseVO.setResno(resno);
				artresponseVO.setArtno(artno);
				artresponseVO.setMemno(memno);
				artresponseVO.setRescontent(rescontent);
				artresponseVO.setRestime(restime);
				artresponseVO.setResstatus(resstatus);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("artresponseVO", artresponseVO); // 含有輸入格式錯誤的artresponseVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher(" ");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				ArtResponseService artresSvc = new ArtResponseService();
				artresponseVO = artresSvc.updateArtResponse(resno, artno, memno, rescontent, restime, resstatus);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("artresponseVO", artresponseVO); // 資料庫update成功後,正確的的artresponseVO物件,存入req
				String url = " ";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneArtResponse.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(" ");
				failureView.forward(req, res);
			}
		}
		

        if ("insert".equals(action)) {
        	
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String artno = new String(req.getParameter("artno").trim());
				
				String memno = new String(req.getParameter("memno").trim());
				
				String rescontent = req.getParameter("rescontent");
				String rescontentReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,50}$";
				if (rescontent == null || rescontent.trim().length() == 0) {
					errorMsgs.add("回覆內容  請勿空白");
				} else if(!rescontent.trim().matches(rescontentReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("回覆內容  只能是中、英文字母、數字和_ , 且長度必需在1到50之間");
	            }
				
				java.sql.Timestamp restime = null;
				
				String resstatus = req.getParameter("resstatus").trim();
				
				ArtResponseVO artresponseVO = new ArtResponseVO();
				artresponseVO.setArtno(artno);
				artresponseVO.setMemno(memno);
				artresponseVO.setRescontent(rescontent);
				artresponseVO.setRestime(restime);
				artresponseVO.setResstatus(resstatus);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					ArticleService artSvc = new ArticleService();
					ArticleVO articleVO = artSvc.getOneArticle(artno);
					req.setAttribute("articleVO", articleVO);
					req.setAttribute("artresponseVO", artresponseVO); // 含有輸入格式錯誤的artresponseVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/article/article_details.jsp");
					failureView.forward(req, res);
					return;
				}				
				
				/***************************2.開始新增資料***************************************/
				ArtResponseService artresSvc = new ArtResponseService();
				artresponseVO = artresSvc.addArtResponse(artno, memno, rescontent, restime, resstatus);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				ArticleService artSvc = new ArticleService();
				ArticleVO articleVO = artSvc.getOneArticle(artno);
				req.setAttribute("articleVO", articleVO);
				
				String url = "/front-end/article/article_details.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllArtResponse.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/article/article_details.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllArtResponse.jsp

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String resno = new String(req.getParameter("resno"));
				
				/***************************2.開始刪除資料***************************************/
				ArtResponseService artresSvc = new ArtResponseService();
				artresSvc.deleteArtResponse(resno);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/emp/listAllArtResponse.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/emp/listAllArtResponse.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
