package com.petDaily.controller;

import java.io.*;
import java.sql.*;
import java.sql.Date;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.petDaily.model.*;
import com.dailyPhoto.model.*;

@MultipartConfig(fileSizeThreshold = 1920 * 1080, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class PetDailyServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("insert".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z *************************/
				String petNo = req.getParameter("petNo").trim();
				
				String pdClass = req.getParameter("pdClass").trim();
				
				String pdCont = req.getParameter("pdCont").trim();
				
				PetDailyVO petDailyVO = new PetDailyVO();

				petDailyVO.setPetNo(petNo);
				petDailyVO.setPdClass(pdClass);
				petDailyVO.setPdCont(pdCont);
				
				/*************************** 2.�}�l�s�W��� ***************************************/
				PetDailyService petDailySvc = new PetDailyService();
				petDailyVO = petDailySvc.addPetDaily(petNo,pdClass,pdCont);
				/*************************** 3.�}�l�s�W�Ϥ� ***************************************/
				
				String pdNo = petDailyVO.getPdNo();

				Collection<Part> parts = req.getParts();
				for (Part part : parts) {
					if (part.getContentType() != null) {
						
						InputStream is = part.getInputStream();
						if(is.available() != 0) {
							byte[] buf = new byte[is.available()];
							is.read(buf);
							byte[] photo = buf;
							is.close();

							DailyPhotoVO dailyPhotoVO = new DailyPhotoVO();

							dailyPhotoVO.setPdNo(pdNo);
							dailyPhotoVO.setPhoto(photo);

				/*************************** 2.�}�l�s�W��� ***************************************/
							DailyPhotoService dailyPhotoSvc = new DailyPhotoService();
							dailyPhotoVO = dailyPhotoSvc.addDailyPhoto(pdNo, photo);
						}
					}
				}
				/*************************** 3.�s�W����,�ǳ����(Send the Success view) ***********/
				String url = "/front-end/petDaily/thePetDaily.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/petDaily/addPetDaily.jsp");
				failureView.forward(req, res);
			}
		}
		if ("update".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				String pdNo = req.getParameter("pdNo").trim();
				
				String petNo = req.getParameter("petNo").trim();
				
				String pdClass = req.getParameter("pdClass").trim();
				
				String pdCont = req.getParameter("pdCont").trim();
				
				PetDailyVO petDailyVO = new PetDailyVO();

				petDailyVO.setPdNo(pdNo);
				petDailyVO.setPetNo(petNo);
				petDailyVO.setPdClass(pdClass);
				petDailyVO.setPdCont(pdCont);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("petDailyVO", petDailyVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/petDaily/update_petDaily_input.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.�}�l�ק��� *****************************************/
				PetDailyService petDailySvc = new PetDailyService();
				petDailyVO = petDailySvc.updatePetDaily(pdNo, petNo, pdClass, pdCont);

				/*************************** 3.�ק粒��,�ǳ����(Send the Success view) *************/
				req.setAttribute("petDailyVO", petDailyVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
				String url = "/front-end/petDaily/listOnePetDaily.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/petDaily/update_petDaily_input.jsp");
				failureView.forward(req, res);
			}
		}
		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� ****************************************/
				String pdNo = req.getParameter("pdNo").trim();

				/*************************** 2.�}�l�d�߸�� ****************************************/
				PetDailyService petDailySvc = new PetDailyService();
				PetDailyVO petDailyVO = petDailySvc.getOnePetDaily(pdNo);

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) ************/
				req.setAttribute("petDailyVO", petDailyVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/front-end/petDaily/update_petDaily_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/petDaily/listAllPetDaily.jsp");
				failureView.forward(req, res);
			}
		}
		if ("delete".equals(action)) { // �Ӧ�listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				String pdNo = req.getParameter("pdNo");
				
				/***************************2.�}�l�R�����***************************************/
				//���p�Ӥ��u���R��
				DailyPhotoService dailyPhotoService = new DailyPhotoService();
				dailyPhotoService.deleteByPDNo(pdNo);
				
				PetDailyService petDailySvc = new PetDailyService();
				petDailySvc.deletePetDaily(pdNo);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/petDaily/thePetDaily.jsp");
				failureView.forward(req, res);
			}
		}
		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String pdNo = req.getParameter("pdNo").trim();
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/petDaily/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				PetDailyService petDailySvc = new PetDailyService();
				PetDailyVO petDailyVO = petDailySvc.getOnePetDaily(pdNo);
				if (petDailyVO == null) {
					errorMsgs.add("�d�L���");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/petDaily/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("petDailyVO", petDailyVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/front-end/petDaily/listOnePetDaily.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/petDaily/select_page.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
