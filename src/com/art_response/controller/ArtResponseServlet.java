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
		
		
		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String str = req.getParameter("resno");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J���|�s��");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/emp/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				String resno = null;
				try {
					resno = new String(str);
				} catch (Exception e) {
					errorMsgs.add("���|�s���榡�����T");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/emp/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				ArtResponseService artresSvc = new ArtResponseService();
				ArtResponseVO artresponseVO = artresSvc.getOneArtResponse(resno);
				if (artresponseVO == null) {
					errorMsgs.add("�d�L���");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher(" ");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("artresponseVO", artresponseVO); // ��Ʈw���X��artresponseVO����,�s�Jreq
				String url = " ";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneArtResponse.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(" ");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllArtResponse.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.�����ШD�Ѽ�****************************************/
				String resno = new String(req.getParameter("resno"));
				
				/***************************2.�}�l�d�߸��****************************************/
				ArtResponseService artresSvc = new ArtResponseService();
				ArtResponseVO artresponseVO = artresSvc.getOneArtResponse(resno);
								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("artresponseVO", artresponseVO);         // ��Ʈw���X��artresponseVO����,�s�Jreq
				String url = " ";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(" ");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String resno = new String(req.getParameter("resno").trim());
				
				String artno = new String(req.getParameter("artno").trim());
				
				String memno = new String(req.getParameter("memno").trim());
				
				String rescontent = req.getParameter("rescontent");
				String rescontentReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (rescontent == null || rescontent.trim().length() == 0) {
					errorMsgs.add("���|���e: �ФŪť�");
				} else if(!rescontent.trim().matches(rescontentReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("���|���e: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
	            }
				
				java.sql.Timestamp restime = null;
				
				String resstatus = req.getParameter("resstatus").trim();
				if (resstatus == null || resstatus.trim().length() == 0) {
					errorMsgs.add("�п�ܤ峹���A");
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
					req.setAttribute("artresponseVO", artresponseVO); // �t����J�榡���~��artresponseVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher(" ");
					failureView.forward(req, res);
					return; //�{�����_
				}
				
				/***************************2.�}�l�ק���*****************************************/
				ArtResponseService artresSvc = new ArtResponseService();
				artresponseVO = artresSvc.updateArtResponse(resno, artno, memno, rescontent, restime, resstatus);
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("artresponseVO", artresponseVO); // ��Ʈwupdate���\��,���T����artresponseVO����,�s�Jreq
				String url = " ";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneArtResponse.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(" ");
				failureView.forward(req, res);
			}
		}
		

        if ("insert".equals(action)) {
        	
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
				String artno = new String(req.getParameter("artno").trim());
				
				String memno = new String(req.getParameter("memno").trim());
				
				String rescontent = req.getParameter("rescontent");
				String rescontentReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,50}$";
				if (rescontent == null || rescontent.trim().length() == 0) {
					errorMsgs.add("�^�Ф��e  �ФŪť�");
				} else if(!rescontent.trim().matches(rescontentReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�^�Ф��e  �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb1��50����");
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
					req.setAttribute("artresponseVO", artresponseVO); // �t����J�榡���~��artresponseVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/article/article_details.jsp");
					failureView.forward(req, res);
					return;
				}				
				
				/***************************2.�}�l�s�W���***************************************/
				ArtResponseService artresSvc = new ArtResponseService();
				artresponseVO = artresSvc.addArtResponse(artno, memno, rescontent, restime, resstatus);
				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				ArticleService artSvc = new ArticleService();
				ArticleVO articleVO = artSvc.getOneArticle(artno);
				req.setAttribute("articleVO", articleVO);
				
				String url = "/front-end/article/article_details.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllArtResponse.jsp
				successView.forward(req, res);				
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/article/article_details.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // �Ӧ�listAllArtResponse.jsp

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				String resno = new String(req.getParameter("resno"));
				
				/***************************2.�}�l�R�����***************************************/
				ArtResponseService artresSvc = new ArtResponseService();
				artresSvc.deleteArtResponse(resno);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				String url = "/emp/listAllArtResponse.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/emp/listAllArtResponse.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
