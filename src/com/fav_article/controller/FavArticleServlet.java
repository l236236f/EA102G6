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
		
//		�d�߳浧���
		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String str = req.getParameter("memno");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J�|���s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/article/select_page.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				
				String memno = null;
				try {
					memno = new String(str);
				} catch (Exception e) {
					errorMsgs.add("�峹�s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/article/select_page.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				FavArticleService favarticleSvc = new FavArticleService();
				FavArticleVO favarticleVO = (FavArticleVO) favarticleSvc.getOneFavArticle(memno);
				if (favarticleVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/article/select_page.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("favarticleVO", favarticleVO); // ��Ʈw���X��articleVO����,�s�Jreq
				String url = "/front-end/article/listOneArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneArticle.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/article/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
//		�s�W���
        if ("insert".equals(action)) { // �Ӧ�addArticle.jsp���ШD  
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
				String memno = new String(req.getParameter("memno").trim());
				
				String artno = new String(req.getParameter("artno").trim());
				
				java.sql.Timestamp favtime = null;
				
				FavArticleVO favArticleVO = new FavArticleVO();
				favArticleVO.setMemno(memno);
				favArticleVO.setArtno(artno);
				favArticleVO.setFavtime(favtime);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("favArticleVO", favArticleVO); // �t����J�榡���~��articleVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/article/addArticle.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�s�W���***************************************/
				FavArticleService favarticleSvc = new FavArticleService();
				favArticleVO = favarticleSvc.addFavArticle(memno, artno, favtime);
				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				String url = "/front-end/article/listAllArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllArticle.jsp
				successView.forward(req, res);				
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/article/addArticle.jsp");
				failureView.forward(req, res);
			}
		}
		
//      �R�����
		if ("delete".equals(action)) { // �Ӧ�listAllArticle.jsp

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				String memno = new String(req.getParameter("memno"));
				String artno = new String(req.getParameter("artno"));
				
				/***************************2.�}�l�R�����***************************************/
				FavArticleService artSvc = new FavArticleService();
				artSvc.deleteFavArticle(memno, artno);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				String url = "/front-end/article/listAllArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/article/listAllArticle.jsp");
				failureView.forward(req, res);
			}
		}
	
//  ------------�[�J����----------
//		if("addfavorite".equals(action)) {
//			try {
//				String memno = req.getParameter("memno");
//				String artno = req.getParameter("artno");
//			
//				Fav_Courses_Service favoritecoursesSvc = new Fav_Courses_Service();
//				favoritecoursesSvc.addFavoriteCourses(memno, artno);
//				
//				System.out.println("�l�ܦ��\");
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

//  ------------��������----------
		
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
