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
		
		
//		�d�߳浧���
		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String str = req.getParameter("artno");
				String artno = new String(str);
				
				/***************************2.�}�l�d�߸��*****************************************/
				ArticleService artSvc = new ArticleService();
				ArticleVO articleVO = artSvc.getOneArticle(artno);
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("articleVO", articleVO);
				String url = "/front-end/article/listOneArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/article/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
//		�d�߳浧�峹
		if ("getOneArticle_For_Display".equals(action)) { // �Ӧ�article.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String str = req.getParameter("artno");
				String artno = new String(str);
				
				/***************************2.�}�l�d�߸��*****************************************/
				ArticleService artSvc = new ArticleService();
				ArticleVO articleVO = artSvc.getOneArticle(artno);
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("articleVO", articleVO); // ��Ʈw���X��articleVO����,�s�Jreq
				String url = "/front-end/article/article_details.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� article_details.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/article/article.jsp");
				failureView.forward(req, res);
			}
		}
		
//		�ק�浧���
		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllArticle.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.�����ШD�Ѽ�****************************************/
				String artno = new String(req.getParameter("artno"));
				
				/***************************2.�}�l�d�߸��****************************************/
				ArticleService artSvc = new ArticleService();
				ArticleVO articleVO = artSvc.getOneArticle(artno);
								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("articleVO", articleVO);         // ��Ʈw���X��articleVO����,�s�Jreq
				String url = "/front-end/article/update_article.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_article_input.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/article/article.jsp");
				failureView.forward(req, res);
			}
		}
		
		
//		�ק���
		if ("update".equals(action)) { // �Ӧ�update_article.jsp���ШD
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String artno = new String(req.getParameter("artno").trim());
				
				String memno = new String(req.getParameter("memno").trim());
				
				String arttitle = req.getParameter("arttitle");
				String arttitleReg = "^[(\u4e00-\u9fa5)(\\x00-\\x7F)( )]{2,20}$";
				if (arttitle == null || arttitle.trim().length() == 0) {
					errorMsgs.add("�峹���D: �ФŪť�");
				} else if(!arttitle.trim().matches(arttitleReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�峹���D: �榡���~, �B���ץ��ݦb2��20����");
	            }
				
				String artcontent = req.getParameter("artcontent").trim();
				if (artcontent == null || artcontent.trim().length() == 0) {
					errorMsgs.add("�峹���e: �ФŪť�");
				}	

				ArticleVO articleVO = new ArticleVO();
				articleVO.setArtno(artno);
				articleVO.setMemno(memno);
				articleVO.setArttitle(arttitle);
				articleVO.setArtcontent(artcontent);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("articleVO", articleVO); // �t����J�榡���~��articleVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/article/update_article.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				
				/***************************2.�}�l�ק���*****************************************/
				ArticleService artSvc = new ArticleService();
				articleVO = artSvc.updateArticle(artno, memno, arttitle, artcontent);
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("articleVO", articleVO); // ��Ʈwupdate���\��,���T����articleVO����,�s�Jreq
				String url = "/front-end/article/article_details.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneArticle.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/article/update_article.jsp");
				failureView.forward(req, res);
			}
		}

		
//		�s�W���
        if ("insert".equals(action)) { // �Ӧ�addArticle.jsp���ШD  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
				String memno = new String(req.getParameter("memno").trim());
				
				String arttitle = req.getParameter("arttitle");
				String arttitleReg = "^[(\u4e00-\u9fa5)(\\x00-\\x7F)( )]{2,20}$";
				if (arttitle == null || arttitle.trim().length() == 0) {
					errorMsgs.add("�峹���D: �ФŪť�");
				} else if(!arttitle.trim().matches(arttitleReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�峹���D: �榡���~, �B���ץ��ݦb2��20����");
	            }
				
				String artcontent = req.getParameter("artcontent").trim();
				if (artcontent == null || artcontent.trim().length() == 0) {
					errorMsgs.add("�峹���e: �ФŪť�");
				}
				
				ArticleVO articleVO = new ArticleVO();
				articleVO.setMemno(memno);
				articleVO.setArttitle(arttitle);
				articleVO.setArtcontent(artcontent);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("articleVO", articleVO); // �t����J�榡���~��articleVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/article/addNewArticle.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�s�W���***************************************/
				ArticleService artSvc = new ArticleService();
				articleVO = artSvc.addArticle(memno, arttitle, artcontent);
				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				req.setAttribute("articleVO", articleVO);
				
				String url = "/front-end/article/article_details.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllArticle.jsp
				successView.forward(req, res);				
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/article/addNewArticle.jsp");
				failureView.forward(req, res);
			}
		}
		
		
//      �R�����
		if ("delete".equals(action)) { // �Ӧ�listAllArticle.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				String artno = new String(req.getParameter("artno"));
				
				/***************************2.�}�l�R�����***************************************/
				ArticleService artSvc = new ArticleService();
				artSvc.deleteArticle(artno);
				
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
		
	}
}
