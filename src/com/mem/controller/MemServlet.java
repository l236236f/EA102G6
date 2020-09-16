package com.mem.controller;

import java.io.*;
import java.sql.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.mem.model.MemService;
import com.mem.model.MemVO;
import com.tools.GetFosmLatLng;

import idv.david.websocketchat.controller.Robot;

import com.fosmon.model.FosmService;
import com.fosmon.model.FosmVO;
import com.mem.model.*;

@MultipartConfig(fileSizeThreshold = 1920 * 1080, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class MemServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");

		String action = req.getParameter("action");

		if("firstTime".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			String memNo = req.getParameter("memNo");
			
			MemService memSvc = new MemService();
			memSvc.updateMemStatus(memNo);			//���|�����A(M1)
			MemVO memVO = memSvc.getOneMem(memNo);  //������VO�A�n�J
			
			HttpSession session = req.getSession();
			session.setAttribute("LoginMem",memVO);
			
			String url = "/front-end/mem/homeMem.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			return;
		}
		
		if ("onlyPhoto".equals(action)) {

			String memNo = req.getParameter("memNo").trim();
			Part part = req.getPart("memPhoto");
			InputStream is = part.getInputStream();
			byte[] buf = new byte[is.available()];
			is.read(buf);
			byte[] memPhoto = buf;
			is.close();

			MemService memSvc = new MemService();
			memSvc.updatePhoto(memNo, memPhoto);

			return;
		}
		
		if("logout".equals(action)) {
			HttpSession session = req.getSession();
			session.setAttribute("LoginMem",null);
			session.setAttribute("whereUFrom",null);
			
			String url = "/front-end/index.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			return;
		}
		
		if ("login".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			HttpSession session = req.getSession();
			String url = (String)session.getAttribute("whereUFrom");
			System.out.println("whereUGo = " + session.getAttribute("whereUFrom"));
			
			String memAcc = req.getParameter("memAcc").trim();
			String memPw = req.getParameter("memPw").trim();

			MemService memSvc = new MemService();
			MemVO memVO = memSvc.checkAcc(memAcc);

			if (memVO == null) {
				errorMsgs.add("�L���b��");
			}else {
				if (memPw.equals(memVO.getMemPw())) {//�b���K�X�ҥ��T
					if(memVO.getMemStatus().equals("M1")) {
						FosmService fmSvc = new FosmService();
						FosmVO f = fmSvc.getOneFosm(memVO.getMemNo());
						session.setAttribute("LoginMem",memVO);
						if(f!=null) {
							session.setAttribute("Loginfosm", f);
						}
					      if(url != null && !url.equals(req.getContextPath() + "/front-end/index.jsp")) {
					       res.sendRedirect(url);
					      }else{
					       res.sendRedirect(req.getContextPath()+"/front-end/mem/homeMem.jsp");
					      }
					      return;
					}else {
						errorMsgs.add("�|������,�Цܹq�l�H�c�i������");
					}
					
				} else {
					errorMsgs.add("�K�X���~");
				}
			}
			//�n�J����
			RequestDispatcher failureView = req.getRequestDispatcher("/front-end/mem/loginMem.jsp");
			failureView.forward(req, res);

		}
		if("applyForMom".equals(action)) {
			   System.out.println("QAQ");
			   String memNo = req.getParameter("memNo");
			   MemService memSvc = new MemService();
			   memSvc.updateMom(memNo);   //���|�����A(M1)
			   MemVO memVO = memSvc.getOneMem(memNo);  //������VO�A�ssession
			   
			   HttpSession session = req.getSession();
			   session.setAttribute("LoginMem",memVO);
			   
			   
			   return;
		}

		if ("insert".equals(action)) { // �Ӧ�addEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z *************************/
				String memName = req.getParameter("memName").trim();
				String memNameReg = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9)]{1,20}$";
				int count = memName.length();
				int bytes = memName.getBytes().length;
				if (memName == null || memName.length() == 0) {
					errorMsgs.add("�|���W��: �ФŪť�");
				} else if (!memName.matches(memNameReg) || (2 * bytes - count) > 20) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�|���W��: �ȱ������B�^��r���B�Ʀr , �B����r����6�Ӧr��");
				}

				String memAcc = req.getParameter("memAcc").trim();
				String memAccReg = "^[(a-zA-Z0-9_)]{4,10}$";
				MemService memSvc = new MemService();
				MemVO memcheck = memSvc.checkAcc(memAcc); //���b����VO
				if (memAcc == null || memAcc.length() == 0) {
					errorMsgs.add("�|���b��: �ФŪť�");
				} else if (!memAcc.matches(memAccReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�|���b��: �ȱ����^��r���M�Ʀr , �B���ץ��ݦb4��10����");
				} 
				else if(memcheck != null) {
					errorMsgs.add("�|���b��: �b���w�Q���U");
				}

				String memPw = req.getParameter("memPw").trim();
				String memPwReg = "^[(a-zA-Z0-9_)]{4,10}$";
				if (memPw == null || memPw.length() == 0) {
					errorMsgs.add("�|���K�X: �ФŪť�");
				} else if (!memPw.matches(memPwReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�|���K�X: �ȱ����^��r���M�Ʀr , �B���ץ��ݦb4��10����");
				}

				java.sql.Date memBirth = null;
				try {
					memBirth = java.sql.Date.valueOf(req.getParameter("memBirth").trim());
				} catch (IllegalArgumentException e) {
					memBirth = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("�п�J���!");
				}

				String memID = req.getParameter("memID").trim();
				String memIdReg = "^[a-zA-Z][0-9]{9}$"; // �u���榡�ˬd
				if (memID == null || memID.length() == 0) {
					errorMsgs.add("�����Ҧr��: �ФŪť�");
				} else if (!memID.matches(memIdReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�����Ҧr��: �п�J���T�榡, ���r(�^��)�����j�p�g");
				}

				String memTel = req.getParameter("memTel").trim();
				String memTelReg = "^[0-9]{1,10}$";
				if (memTel == null || memTel.length() == 0) {
					errorMsgs.add("�s���q��: �ФŪť�");
				} else if (!memTel.matches(memTelReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�s���q��: �ȱ����Ʀr, ������\"-\"�B�ť���Ψ�L");
				}

				String memGender = req.getParameter("memGender").trim();
				
				String zipcode = req.getParameter("zipcode");
				String country = req.getParameter("country");
				String district = req.getParameter("district");
				String address = req.getParameter("address").trim();
				
				String memAddr =zipcode + country + district + address;
				if (memAddr == null || memAddr.length() == 0) {
					errorMsgs.add("�a�}: �ФŪť�");
				}

				String memEmail = req.getParameter("memEmail").trim();
				String memEmailReg = "^([a-zA-Z0-9_]+@[a-zA-Z0-9.]+.[a-zA-Z]{2,4})*$";
				if (memEmail == null || memEmail.length() == 0) {
					errorMsgs.add("�q�l�l��: �ФŪť�");
				} else if (!memEmail.matches(memEmailReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�q�l�l��: �п�J���T�榡");
				}

				String memMoney = req.getParameter("memMoney").trim();
				if (memMoney == null || memMoney.length() == 0) {
					errorMsgs.add("�b��:�ФŪť�");
				}

				Part part = req.getPart("memPhoto");
				InputStream is = part.getInputStream();
				byte[] buf = new byte[is.available()];
				is.read(buf);
				byte[] memPhoto = buf;
				is.close();

				String memIntro = req.getParameter("memIntro").trim();

				MemVO memVO = new MemVO();

				memVO.setMemAcc(memAcc);
				memVO.setMemPw(memPw);
				memVO.setMemName(memName);
				memVO.setMemBirth(memBirth);
				memVO.setMemID(memID);
				memVO.setMemTel(memTel);
				memVO.setMemGender(memGender);
				memVO.setMemAddr(memAddr);
				memVO.setMemEmail(memEmail);
				memVO.setMemMoney(memMoney);
				memVO.setMemPhoto(memPhoto);
				memVO.setMemIntro(memIntro);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("XmemVO", memVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/mem/registerMem.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.�}�l�s�W��� ***************************************/
				memVO = memSvc.addMem(memAcc, memPw, memName, memBirth, memID, memTel, memGender, memAddr, memEmail,
						memMoney, memPhoto, memIntro);
				
				/*************************** 3.�s�W����,�ǳ����(Send the Success view) ***********/
				
				String subject = "GGYY�|���q���H";
			    String button = req.getScheme() + "://" + req.getServerName()+ ":" + req.getServerPort()+ req.getContextPath()
			         + "/front-end/mem/mem.do?action=firstTime&memNo=" + memVO.getMemNo();
			    MailThread mailThread = new MailThread(memEmail, subject, memName, button);
			    mailThread.start();
			      
				String url = "/front-end/index.jsp";
				req.setAttribute("success", "mem");
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				return;
					
				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/mem/registerMem.jsp");
				failureView.forward(req, res);
			}
		}
		if ("update".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				String memNo = req.getParameter("memNo").trim();
				
				String memName = req.getParameter("memName").trim();
				String memNameReg = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9)]{1,20}$";
				int count = memName.length();
				int bytes = memName.getBytes().length;
				if (memName == null || memName.length() == 0) {
					errorMsgs.add("�|���W��: �ФŪť�");
				} else if (!memName.matches(memNameReg) || (2 * bytes - count) > 20) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�|���W��: �ȱ������B�^��r���B�Ʀr , �B����r����6�Ӧr��");
				}

				String memPw = req.getParameter("memPw").trim();
				String memPwReg = "^[(a-zA-Z0-9_)]{4,10}$";
				if (memPw == null || memPw.length() == 0) {
					errorMsgs.add("�|���K�X: �ФŪť�");
				} else if (!memPw.matches(memPwReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�|���K�X: �ȱ����^��r���M�Ʀr , �B���ץ��ݦb4��10����");
				}

				java.sql.Date memBirth = null;
				try {
					memBirth = java.sql.Date.valueOf(req.getParameter("memBirth").trim());
				} catch (IllegalArgumentException e) {
					memBirth = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("�п�J���!");
				}

				String memID = req.getParameter("memID").trim();
				String memIDReg = "^[a-zA-Z][0-9]{9}$"; // �u���榡�ˬd
				if (memID == null || memID.length() == 0) {
					errorMsgs.add("�����Ҧr��: �ФŪť�");
				} else if (!memID.matches(memIDReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�����Ҧr��: �п�J���T�榡, ���r(�^��)�����j�p�g");
				}

				String memTel = req.getParameter("memTel").trim();
				String memTelReg = "^[0-9]{1,10}$";
				if (memTel == null || memTel.length() == 0) {
					errorMsgs.add("�s���q��: �ФŪť�");
				} else if (!memTel.matches(memTelReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�s���q��: �ȱ����Ʀr, ������\"-\"�B�ť���Ψ�L");
				}

				String memGender = req.getParameter("memGender").trim();

				String zipcode = req.getParameter("zipcode");
				String country = req.getParameter("country");
				String district = req.getParameter("district");
				String address = req.getParameter("address").trim();
				
				String memAddr =zipcode + country + district + address;
				if (memAddr == null || memAddr.length() == 0) {
					errorMsgs.add("�a�}: �ФŪť�");
				}

				String memEmail = req.getParameter("memEmail").trim();
				String memEmailReg = "^([a-zA-Z0-9_]+@[a-zA-Z0-9.]+.[a-zA-Z]{2,4})*$";
				if (memEmail == null || memEmail.length() == 0) {
					errorMsgs.add("�s���q��: �ФŪť�");
				} else if (!memEmail.matches(memEmailReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�s���q��: �ȱ����Ʀr, ������\"-\"�B�ť���Ψ�L");
				}

				String memMoney = req.getParameter("memMoney").trim();
				if (memMoney == null || memMoney.length() == 0) {
					errorMsgs.add("�b��ФŪť�");
				}
				
				String memIntro = req.getParameter("memIntro").trim();

				MemVO memVO = new MemVO();

				memVO.setMemNo(memNo);
				memVO.setMemPw(memPw);
				memVO.setMemName(memName);
				memVO.setMemBirth(memBirth);
				memVO.setMemID(memID);
				memVO.setMemTel(memTel);
				memVO.setMemGender(memGender);
				memVO.setMemAddr(memAddr);
				memVO.setMemEmail(memEmail);
				memVO.setMemMoney(memMoney);
				memVO.setMemIntro(memIntro);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("XmemVO", memVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/mem/modifyMem.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.�}�l�ק��� *****************************************/
				MemService memSvc = new MemService();
				memSvc.updateMem(memNo, memPw, memName, memBirth, memID, memTel, memGender, memAddr,
						memEmail, memMoney, memIntro);

				/*************************** 3.�ק粒��,�ǳ����(Send the Success view) *************/
				memVO = memSvc.getOneMem(memNo);
				HttpSession session = req.getSession();
				session.setAttribute("LoginMem",memVO);// ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
				String url = "/front-end/mem/homeMem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/mem/modifyMem.jsp");
				failureView.forward(req, res);
			}
		}
		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� ****************************************/
				String memNo = req.getParameter("memNo").trim();

				/*************************** 2.�}�l�d�߸�� ****************************************/
				MemService memSvc = new MemService();
				MemVO memVO = memSvc.getOneMem(memNo);

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) ************/
				req.setAttribute("memVO", memVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/front-end/mem/update_mem_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/mem/listAllMem.jsp");
				failureView.forward(req, res);
			}
		}
		if ("delete".equals(action)) { // �Ӧ�listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� ***************************************/
				String memNo = req.getParameter("memNo");

				/*************************** 2.�}�l�R����� ***************************************/
				MemService memSvc = new MemService();
				memSvc.deleteMem(memNo);

				/*************************** 3.�R������,�ǳ����(Send the Success view) ***********/
				String url = "/front-end/mem/listAllMem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/mem/listAllMem.jsp");
				failureView.forward(req, res);
			}
		}
		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				String memNo = req.getParameter("memNo").trim();
				if (memNo == null || memNo.length() == 0) {
					errorMsgs.add("�п�J�|���s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/mem/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 2.�}�l�d�߸�� *****************************************/
				MemService memSvc = new MemService();
				MemVO memVO = memSvc.getOneMem(memNo);
				if (memVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/mem/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) *************/
				req.setAttribute("memVO", memVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/front-end/mem/listOneMem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/mem/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		if ("updateByemp".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				String memNo = req.getParameter("memno").trim();
				String memPw = req.getParameter("mempw").trim();
				String memEmail = req.getParameter("mememail").trim();
				String memMoney = req.getParameter("memmoney").trim();
				String memStatus = req.getParameter("memstatus").trim();
				String mom = req.getParameter("mom").trim();
				int bonus = new Integer(req.getParameter("membonus").trim());
				MemVO memVO = new MemVO();

				memVO.setMemNo(memNo);
				memVO.setMemPw(memPw);
				memVO.setMemEmail(memEmail);
				memVO.setMemMoney(memMoney);
				memVO.setMemStatus(memStatus);
				memVO.setMom(mom);
				memVO.setBonus(bonus);
				/*************************** �}�l�ק��� *****************************************/
				MemService memSvc = new MemService();
				//���쥻���O�����A
				MemVO momvoO =memSvc.getOneMem(memNo);
				if("M1".equals(momvoO.getMom())){
					String str="";
					Set<String> set = new HashSet<String>();
					set.add(memNo);
					if("M2".equals(mom)) {
						str=momvoO.getMemAcc()+" �z���O���f�֤w�q�L";
						Robot.chatRobot(str,set);
						GetFosmLatLng gll = new GetFosmLatLng();
						FosmService fmSvc = new FosmService();
						FosmVO fosmVO = gll.getLat(memNo, momvoO.getMemAddr());
						fmSvc.addFosMon(fosmVO);
						
					}else if("M3".equals(mom)) {
						str=momvoO.getMemAcc()+" �z���O���f�֧P�w���q�L";
						Robot.chatRobot(str,set);
					}
				}
				//�}�l����
				memSvc.updateMemByEmp(memNo, memPw,memEmail, memMoney,memStatus,mom,bonus);
				//�ק���
				/*************************** 3.�ק粒��,�ǳ����(Send the Success view) *************/
				String url = "/back-end/emp/memmanage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/memmanage.jsp");
				failureView.forward(req, res);
			}
		}
	}

}
