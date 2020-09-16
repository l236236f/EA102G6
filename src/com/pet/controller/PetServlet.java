package com.pet.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;


import com.pet.model.*;
import org.json.JSONObject;

@MultipartConfig(fileSizeThreshold = 1920 * 1080, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class PetServlet extends HttpServlet {
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
				String memNo = req.getParameter("memNo").trim();
				
				String petName = req.getParameter("petName").trim();
				String petNameReg = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9)]{1,20}$";
				if (petName == null || petName.length() == 0) {
					errorMsgs.add("�d���W��: �ФŪť�");
				} else if (!petName.matches(petNameReg) || petName.getBytes().length > 20 ) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�d���W��: �ȱ������B�^��r���B�Ʀr , �B����r����6�Ӧr��");
				}
				
				Part part = req.getPart("petPhoto");//�p�d�Ѯv�{���X
				InputStream is = part.getInputStream();
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				byte[] buffer = new byte[8192];
				int i;
				while ((i = is.read(buffer)) != -1)
					baos.write(buffer, 0, i);
				byte[] petPhoto = baos.toByteArray();
				baos.close();
				is.close();
				
				String petKind = req.getParameter("petKind").trim();
				
				String petVariety = req.getParameter("petVariety").trim();
				
				java.sql.Date petBirth = null;
				petBirth = java.sql.Date.valueOf(req.getParameter("petBirth").trim());
				
				String petGender = req.getParameter("petGender").trim();
				
				String petID = req.getParameter("petID").trim();
				
				String petIntro = req.getParameter("petIntro").trim();
				
				PetVO petVO = new PetVO();

				petVO.setMemNo(memNo);
				petVO.setPetName(petName);
				petVO.setPetPhoto(petPhoto);
				petVO.setPetKind(petKind);
				petVO.setPetVariety(petVariety);
				petVO.setPetBirth(petBirth);
				petVO.setPetGender(petGender);
				petVO.setPetID(petID);
				petVO.setPetIntro(petIntro);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("insertPetVO", petVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/pet/homePet.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.�}�l�s�W��� ***************************************/
				PetService petSvc = new PetService();
				petVO = petSvc.addPet(memNo, petName, petPhoto, petKind, petVariety, 
						petBirth, petGender, petID, petIntro);

				/*************************** 3.�s�W����,�ǳ����(Send the Success view) ***********/
				String url = "/front-end/pet/homePet.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/pet/homePet.jsp");
				failureView.forward(req, res);
			}
		}
		if ("update".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				String petNo = req.getParameter("petNo").trim();
				
				String memNo = req.getParameter("memNo").trim();
				
				String petName = req.getParameter("petName").trim();
				String petNameReg = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9)]{1,20}$";
				if (petName == null || petName.length() == 0) {
					errorMsgs.add("�|���W��: �ФŪť�");
				} else if (!petName.matches(petNameReg) || petName.getBytes().length > 20 ) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�|���W��: �ȱ������B�^��r���B�Ʀr , �B����r����10�Ӧr��");
				}
				
				PetService petSvc = new PetService();
				Part part = req.getPart("petPhoto");
				InputStream is = part.getInputStream();
				byte[] petPhoto = null;
				if(part.getContentType() != null) {
					byte[] buf = new byte[is.available()];
					is.read(buf);
					petPhoto = buf;
					is.close();
				}else {
					petPhoto = petSvc.getOnePet(petNo).getPetPhoto();
				}
				
				String petKind = req.getParameter("petKind").trim();
				
				String petVariety = req.getParameter("petVariety").trim();
				
				java.sql.Date petBirth = null;
				petBirth = java.sql.Date.valueOf(req.getParameter("petBirth").trim());
				
				String petGender = req.getParameter("petGender").trim();
				
				String petIntro = req.getParameter("petIntro").trim();
				
				PetVO petVO = new PetVO();
				
				petVO.setPetNo(petNo);
				petVO.setMemNo(memNo);
				petVO.setPetName(petName);
				petVO.setPetPhoto(petPhoto);
				petVO.setPetKind(petKind);
				petVO.setPetVariety(petVariety);
				petVO.setPetBirth(petBirth);
				petVO.setPetGender(petGender);
				petVO.setPetIntro(petIntro);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					System.out.println("X");
					req.setAttribute("updatePetVO", petVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/pet/homePet.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/*************************** 2.�}�l�ק��� *****************************************/
				petVO = petSvc.updatePet(petNo, memNo, petName, petPhoto, petKind, petVariety, 
						petBirth, petGender, petIntro);
				
				/*************************** 3.�ק粒��,�ǳ����(Send the Success view) *************/	
				JSONObject json = new JSONObject();//Ajax�Hjson�Ǩ�e��
				json.put("petNo",petNo);
				json.put("memNo",memNo);
				json.put("petName",petName);
				json.put("petPhoto",petPhoto);
				json.put("petKind",petKind);
				json.put("petVariety",petVariety);
				json.put("petBirth",petBirth);
				json.put("petGender",petGender);
				json.put("petIntro",petIntro);
			
				res.setContentType("text/plain");
				res.setCharacterEncoding("UTF-8");
				PrintWriter out = res.getWriter();
				out.write(json.toString());
				out.flush();
				out.close();
				
				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/pet/homePet.jsp");
				failureView.forward(req, res);
			}
		}
		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*************************** 1.�����ШD�Ѽ� ****************************************/
				String petNo = req.getParameter("petNo").trim();

				/*************************** 2.�}�l�d�߸�� ****************************************/
				PetService petSvc = new PetService();
				PetVO petVO = petSvc.getOnePet(petNo);

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) ************/
				req.setAttribute("petVO", petVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/front-end/pet/listOnePet.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/pet/homePet.jsp");
				failureView.forward(req, res);
			}
		}
		if ("delete".equals(action)) { // �Ӧ�listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				String petNo = req.getParameter("petNo");
				
				/***************************2.�}�l�R�����***************************************/
				PetService petSvc = new PetService();
				petSvc.deletePet(petNo);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				String url = "/front-end/pet/listAllPet.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/pet/listAllPet.jsp");
				failureView.forward(req, res);
			}
		}
		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String petNo = req.getParameter("petNo").trim();
				if (petNo == null || petNo.length() == 0) {
					errorMsgs.add("�п�J���u�s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/pet/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				PetService memSvc = new PetService();
				PetVO petVO = memSvc.getOnePet(petNo);
				if (petVO == null) {
					errorMsgs.add("�d�L���");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/pet/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("petVO", petVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/front-end/pet/listOnePet.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/pet/select_page.jsp");
				failureView.forward(req, res);
			}
		}
	}

}
