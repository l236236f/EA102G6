package com.dailyPhoto.controller;

import java.io.*;
import java.sql.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.dailyPhoto.model.*;

@MultipartConfig(fileSizeThreshold = 1920 * 1080, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class DailyPhotoServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("insert".equals(action)) { // �Ӧ�addEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z *************************/
				Collection<Part> parts = req.getParts();
				for (Part part : parts) {
					if (part.getContentType() != null) {

						String pdNo = req.getParameter("pdNo").trim();

						InputStream is = part.getInputStream();
						ByteArrayOutputStream baos = new ByteArrayOutputStream();
						byte[] buffer = new byte[8192];
						int i;
						while ((i = is.read(buffer)) != -1)
							baos.write(buffer, 0, i);
						byte[] photo = baos.toByteArray();
						baos.close();
						is.close();

						DailyPhotoVO dailyPhotoVO = new DailyPhotoVO();

						dailyPhotoVO.setPdNo(pdNo);
						dailyPhotoVO.setPhoto(photo);

						// Send the use back to the form, if there were errors
						if (!errorMsgs.isEmpty()) {
							req.setAttribute("dailyPhotoVO", dailyPhotoVO); // �t����J�榡���~��empVO����,�]�s�Jreq
							RequestDispatcher failureView = req
									.getRequestDispatcher("/front-end/dailyPhoto/addDailyPhoto.jsp");
							failureView.forward(req, res);
							return;
						}

						/*************************** 2.�}�l�s�W��� ***************************************/
						DailyPhotoService dailyPhotoSvc = new DailyPhotoService();
						dailyPhotoVO = dailyPhotoSvc.addDailyPhoto(pdNo, photo);
					}
				}
				/*************************** 3.�s�W����,�ǳ����(Send the Success view) ***********/
				String url = "/front-end/dailyPhoto/listAllDailyPhoto.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/dailyPhoto/addDailyPhoto.jsp");
				failureView.forward(req, res);
			}
		}
		if ("update".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			List<DailyPhotoVO> dailyPhotoVOs = new LinkedList<DailyPhotoVO>();
			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				
				String dphNo = req.getParameter("dphNo").trim();

				String pdNo = req.getParameter("pdNo").trim();
				Part part = req.getPart("photo");
				InputStream is = part.getInputStream();
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				byte[] buffer = new byte[8192];
				int i;
				while ((i = is.read(buffer)) != -1)
					baos.write(buffer, 0, i);
				byte[] photo = baos.toByteArray();
				baos.close();
				is.close();

				DailyPhotoVO dailyPhotoVO = new DailyPhotoVO();
						
				dailyPhotoVO.setDphNo(dphNo);
				dailyPhotoVO.setPdNo(pdNo);
				dailyPhotoVO.setPhoto(photo);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("dailyPhotoVO", dailyPhotoVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/dailyPhoto/update_DailyPhoto_input.jsp");
					failureView.forward(req, res);
					return;
				}

						/*************************** 2.�}�l�s�W��� ***************************************/
				DailyPhotoService dailyPhotoSvc = new DailyPhotoService();
				dailyPhotoVO = dailyPhotoSvc.updateDailyPhoto(dphNo, pdNo, photo);
				dailyPhotoVOs.add(dailyPhotoVO);
			
				/*************************** 3.�ק粒��,�ǳ����(Send the Success view) *************/
				req.setAttribute("dailyPhotoVO", dailyPhotoVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
				String url = "/front-end/dailyPhoto/listOneDailyPhoto.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/mem/update_mem_input.jsp");
				failureView.forward(req, res);
			}
		}
		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� ****************************************/
				String dphNo = req.getParameter("dphNo").trim();

				/*************************** 2.�}�l�d�߸�� ****************************************/
				DailyPhotoService dailyPhotoSvc = new DailyPhotoService();
				DailyPhotoVO dailyPhotoVO = dailyPhotoSvc.getOneDailyPhoto(dphNo);

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) ************/
				req.setAttribute("dailyPhotoVO", dailyPhotoVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/front-end/dailyPhoto/update_dailyPhoto_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/dailyPhoto/listAllDailyPhoto.jsp");
				failureView.forward(req, res);
			}
		}
		if ("delete".equals(action)) { // �Ӧ�listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				String dphNo = req.getParameter("dphNo");
				
				/***************************2.�}�l�R�����***************************************/
				DailyPhotoService dailyPhotoSvc = new DailyPhotoService();
				dailyPhotoSvc.deleteDailyPhoto(dphNo);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				String url = "/front-end/dailyPhoto/listAllDailyPhoto.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/dailyPhoto/listAllDailyPhoto.jsp");
				failureView.forward(req, res);
			}
		}
		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String dphNo = req.getParameter("dphNo").trim();
				if (dphNo == null || dphNo.length() == 0) {
					errorMsgs.add("�п�J���u�s��");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/dailyPhoto/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				DailyPhotoService dailyPhotoSvc = new DailyPhotoService();
				DailyPhotoVO dailyPhotoVO = dailyPhotoSvc.getOneDailyPhoto(dphNo);
				if (dailyPhotoVO == null) {
					errorMsgs.add("�d�L���");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/dailyPhoto/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("dailyPhotoVO", dailyPhotoVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/front-end/dailyPhoto/listOneDailyPhoto.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/dailyPhoto/select_page.jsp");
				failureView.forward(req, res);
			}
		}
	}

}
