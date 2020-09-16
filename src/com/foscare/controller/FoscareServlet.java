package com.foscare.controller;

import java.io.IOException;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.foscare.model.*;
import com.fosmon.model.FosmService;
import com.fosmon.model.FosmVO;


import com.tools.PhotoToByte;

@MultipartConfig
@WebServlet("/FoscareServlet")
public class FoscareServlet extends HttpServlet {
	

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
		
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		HttpSession se = req.getSession();
		
		if("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
			try {
				
				String memNo = req.getParameter("memNo");
				String memNOReg = "^[M]{1}[0-9]{3}$";
				if (memNo == null || memNo.trim().length() == 0) {
					errorMsgs.add("�|���s��: �ФŪť�");
				} else if(!memNo.trim().matches(memNOReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�|���s��: �u��OM�}�Y�[�W3��0-9���Ʀr");
	            }
				
				String petNo = req.getParameter("petNo");
				String petNOReg = "^[P]{1}[0-9]{3}$";
				if (petNo == null || petNo.trim().length() == 0) {
					errorMsgs.add("�d���s��: �ФŪť�");
				} else if(!petNo.trim().matches(petNOReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�d���s��: �u��OP�}�Y�[�W3��0-9���Ʀr");
	            }
				
				java.sql.Date startTime = null;
				java.sql.Date endTime = null;
				
				try {
					startTime = java.sql.Date.valueOf(req.getParameter("startTime").trim());
				} catch (IllegalArgumentException e) {
					startTime=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("�п�J�}�l���!");
				}
				try {
					endTime = java.sql.Date.valueOf(req.getParameter("endTime").trim());
				} catch (IllegalArgumentException e) {
					endTime=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("�п�J�������!");
				}
				Integer fosMoney=null;
				try {
					fosMoney = Integer.valueOf(req.getParameter("fosMoney").trim());
				}catch(NumberFormatException e) {
					fosMoney=0;
					errorMsgs.add("���B�ж�J�Ʀr");
				}
				String fosType = null;
				try {
					fosType = req.getParameter("fosType").trim();
				}catch(NullPointerException e) {
					errorMsgs.add("�п���d������");
				}
				
				String fosSize = null;
				try {
					fosSize = req.getParameter("fosSize").trim();
				}catch(NullPointerException e) {
					errorMsgs.add("�п���d���j�p");
				}
				
				String fosnrun = null;
				try {
					fosnrun = req.getParameter("fosnrun").trim();
				}catch(NullPointerException e) {
					errorMsgs.add("�п���d���O�_�ݭn��");
				}
				String fosRemark = null;
				fosRemark = req.getParameter("fosRemark");
				
				FosterVO fosterVO = new FosterVO();
				fosterVO.setMemNo(memNo);
				fosterVO.setPetNo(petNo);
				fosterVO.setFosStartTime(startTime);
				fosterVO.setFosEndTime(endTime);
				fosterVO.setFosMoney(fosMoney);
				fosterVO.setFosType(fosType);
				fosterVO.setFosSize(fosSize);
				fosterVO.setFosnrun(fosnrun);
				fosterVO.setFosRemark(fosRemark);		
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("fosterVO", fosterVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					se.setAttribute("isSuccess", "No");
					se.setAttribute("successVal", "�ק異��!");
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/foscare/addfos.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				/***************************2.�}�l�s�W���***************************************/
				FosterService fosS = new FosterService();
				fosterVO = fosS.addFoscare(memNo, petNo, startTime, endTime, fosnrun, fosSize, fosType, fosRemark, fosMoney);
				se.setAttribute("isSuccess", "Yes");
				se.setAttribute("successVal", "�s�W���\!");
				String  url=req.getParameter("url");  
				res.sendRedirect(url);  

			}catch(Exception e) {
				errorMsgs.add(e.getMessage());
				se.setAttribute("isSuccess", "No");
				se.setAttribute("successVal", "�ק異��!");
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/foscare/addfos.jsp");
				failureView.forward(req, res);
			}
		}
		if("getOne_For_Update".equals(action)) {

			try {
			String fosNo = req.getParameter("fosNo");
			FosterService fosSvc = new FosterService();
			FosterVO fosVO = fosSvc.getOneFoscare(fosNo);
			req.setAttribute("fosterVO", fosVO);
			String url = "/front-end/foscare/updatefos.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // �R�����\�����listAllEmp.jsp
			successView.forward(req, res);
			}catch (Exception e) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/foscare/readfos.jsp");
				failureView.forward(req, res);
			}
		}
		if("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			FosterVO fosterVO= null;
//			try {
				String fosNo = req.getParameter("fosNo");
				String memNo = req.getParameter("memNo");
				String petNo = req.getParameter("petNo");
				String fosmNOReg = "^(FM)[0-9]{3}$";
				String fosmNo = req.getParameter("fosmNo");
				FosmService fmSvc = new FosmService();
				if(fosmNo.length()!=0&&(!fosmNo.trim().matches(fosmNOReg))) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�O���s��: �u��OFM�}�Y�[�W3��0-9���Ʀr");
	            }else if(fosmNo.length()!=0&&fmSvc.getOneByFosmNo(fosmNo)==null) {
					errorMsgs.add("�L���O���s���A�Ф��n�x��!");
				}else if(fosmNo.length()!=0&&memNo.equals(fmSvc.getOneByFosmNo(fosmNo).getMemNo())) {
					errorMsgs.add("����ۤv��ۤv���O���A�Ф��n�x��!");
				}
				java.sql.Date startTime = null;
				java.sql.Date endTime = null;
				try {
					startTime = java.sql.Date.valueOf(req.getParameter("startTime").trim());
				} catch (IllegalArgumentException e) {
					startTime=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("�п�J�}�l���!");
				}
				try {
					endTime = java.sql.Date.valueOf(req.getParameter("endTime").trim());
				} catch (IllegalArgumentException e) {
					endTime=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("�п�J�������!");
				}
				Integer fosMoney=null;
				try {
					fosMoney = Integer.valueOf(req.getParameter("fosMoney").trim());
				}catch(NumberFormatException e) {
					fosMoney=0;
					errorMsgs.add("���B�ж�J�Ʀr");
				}
				String fosType = req.getParameter("fosType");
				String fosSize = req.getParameter("fosSize");
				String fosnrun = req.getParameter("fosnrun");
				String fosRemark = req.getParameter("fosRemark");
				String fosStatus = req.getParameter("fosStatus");
				
				fosterVO = new FosterVO();
				fosterVO.setFosNo(fosNo);
				fosterVO.setMemNo(memNo);
				fosterVO.setPetNo(petNo);
				fosterVO.setFosmNo(fosmNo);
				fosterVO.setFosStartTime(startTime);
				fosterVO.setFosEndTime(endTime);
				fosterVO.setFosMoney(fosMoney);
				fosterVO.setFosType(fosType);
				fosterVO.setFosSize(fosSize);
				fosterVO.setFosnrun(fosnrun);
				fosterVO.setFosStatus(fosStatus);
				fosterVO.setFosRemark(fosRemark);
								
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("fosterVO", fosterVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					se.setAttribute("isSuccess", "No");
					se.setAttribute("successVal", "�ק異��!");
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/foscare/updatefos.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				/***************************2.�}�l�ק���***************************************/
				FosterService fosSvc = new FosterService();
				fosSvc.updateFoscare(fosterVO);
								
				if(fosmNo.length()!=0) {
					fosSvc.changeStatus(fosNo, "F1");
				}
				//�έ����קK���ƴ���
				se.setAttribute("isSuccess", "Yes");
				se.setAttribute("successVal", "�ק令�\!");
				String  url=req.getParameter("url");  
				res.sendRedirect(url);  
				
//			}catch(Exception e) {
//				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
//				se.setAttribute("isSuccess", "No");
//				se.setAttribute("successVal", "�ק異��!");
//				req.setAttribute("fosVO", fosterVO);
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/front-end/foscare/updatefos.jsp");
//				failureView.forward(req, res);
//			}
		}
		if("sign".equals(action)) {
			String AB = req.getParameter("AandB");
			String fosNo = req.getParameter("fosNo");	
			//�N���쪺Base64���ɦr���Ѩ��নBinary�r��
			String signbase = req.getParameter("signjpg");
			String base64Image = signbase.split(",")[1];
			//�NBinary�r���নbyte�}�C
			byte[] sign = javax.xml.bind.DatatypeConverter.parseBase64Binary(base64Image);
			//�I�sService�s�i��Ʈw
			FosterService fosSvc = new FosterService();
			fosSvc.addSign(sign, fosNo, AB);
			//�P�_��Ʈw��ƭק窱�A
			FosterVO fosVO = fosSvc.getOneFoscare(fosNo);
			if(fosVO.getFosSignA()!=null&&fosVO.getFosSignB()!=null) {
				fosSvc.changeStatus(fosNo, "F4");
			}else {
				fosSvc.changeStatus(fosNo, "F3");
			}
			//��AB�P�_�����ӷ�
			String url = (AB.equals("A"))?"/front-end/foscare/readfos.jsp":"/front-end/foscare/mreadfos.jsp";
			se.setAttribute("isSuccess", "Yes");
			se.setAttribute("successVal", "ñ�W���\!");
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			
		}
		if("addEva".equals(action)) {
			try {
			String fosNo = req.getParameter("fosNo");
			String fosmEvacon = req.getParameter("fosmEvacon");
			Integer star = new Integer(req.getParameter("star"));
			String fosmNo = req.getParameter("fosmNo");
			
			FosterService fosSvc = new FosterService();
			fosSvc.addevaluation(fosNo, star, fosmEvacon);
			fosSvc.changeStatus(fosNo, "F8");
			
			FosmService fmSvc = new FosmService();
			System.out.println(fosmNo);
			FosmVO fosmVO = fmSvc.getOneByFosmNo(fosmNo);
			System.out.println(fosmVO);
			fosmVO.setFosmEvacount(fosmVO.getFosmEvacount()+1);
			fosmVO.setFosStar(fosmVO.getFosStar()+star);
			fmSvc.changeStar(fosmVO);
					
			String url = "/front-end/foscare/readfos.jsp";
			se.setAttribute("isSuccess", "Yes");
			se.setAttribute("successVal", "�������\!");
			RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
			successView.forward(req, res);
			
			}catch(Exception e) {
				e.printStackTrace();
			}	
		}
		if("fosmres".equals(action)) {
			String fosmEvares = req.getParameter("fosmText");
			String fosNo = req.getParameter("fosNo");
			FosterService fosSvc = new FosterService();
			fosSvc.addFosmEvares(fosNo, fosmEvares);
		}
		if("changeSta".equals(action)) {
			String fosStatus = req.getParameter("fosStatus");
			String fosNo = req.getParameter("fosNo");
			String AB = req.getParameter("AB");
			FosterService fosSvc = new FosterService();
			fosSvc.changeStatus(fosNo, fosStatus);
			
			//��AB�P�_�����ӷ�
			String url = (AB.equals("A"))?"/front-end/foscare/readfos.jsp":"/front-end/foscare/mreadfos.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
			successView.forward(req, res);
			
 		}
	
	}
}
