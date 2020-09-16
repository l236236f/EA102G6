package com.vendor.controller;

import java.io.*;
import java.sql.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.mem.model.MemService;
import com.mem.model.MemVO;
import com.pet.model.PetService;
import com.tools.MailService;
import com.vendor.model.*;

import EMPLOYEE.model.*;
import idv.david.websocketchat.controller.Robot;

@MultipartConfig(fileSizeThreshold = 1920 * 1080, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class VendorServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("logout".equals(action)) {
			HttpSession session = req.getSession();
			session.setAttribute("LoginVendor",null);
			session.setAttribute("whereUFrom",null);
			
			String url = "/front-end/index.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			return;
		}
		
		if ("login".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String venAcc = req.getParameter("venAcc").trim();
			String venPw = req.getParameter("venPw").trim();
			VendorService vendorSvc = new VendorService();
			VendorVO vendorVO = vendorSvc.checkAcc(venAcc);
			if (vendorVO == null) {
				errorMsgs.add("�L���b��");
			}else {
				if (venPw.equals(vendorVO.getVenPw())) {
					if("V0".equals(vendorVO.getVenStatus())) {
						errorMsgs.add("�޲z���f�֤�...�еy��A�� : (");
					}else if("V1".equals(vendorVO.getVenStatus())) {
						HttpSession session = req.getSession();
						session.setAttribute("LoginVendor",vendorVO);
						String url = "/front-end/vendor/homeVendor.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url);
						successView.forward(req, res);
						return;
					}else {
						System.out.println(vendorVO.getVenStatus());
						errorMsgs.add("�b���w���v  : )");
					}
				} else {
					errorMsgs.add("�K�X���~");
				}
			}
			RequestDispatcher failureView = req.getRequestDispatcher("/front-end/vendor/loginVendor.jsp");
			failureView.forward(req, res);
		}
		if ("insert".equals(action)) { // �Ӧ�addEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z *************************/
				String venName = req.getParameter("venName").trim();
				String venNameReg = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9)]{1,20}$";
				int count = venName.length();
				int bytes = venName.getBytes().length;
				if (venName == null || venName.length() == 0) {
					errorMsgs.add("�t�ӦW��: �ФŪť�");
				} else if (!venName.matches(venNameReg) || (2 * bytes - count) > 20 ) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�t�ӦW��: �ȱ������B�^��r���B�Ʀr , �B����r����6�Ӧr��");
				}
				
				String venAcc = req.getParameter("venAcc").trim();
				String venAccReg = "^[(a-zA-Z0-9_)]{4,10}$";
				VendorService vendorSvc = new VendorService();
				VendorVO vencheck = vendorSvc.checkAcc(venAcc); //���b����VO
				if (venAcc == null || venAcc.length() == 0) {
					errorMsgs.add("�t�ӱb��: �ФŪť�");
				} else if (!venAcc.matches(venAccReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�t�ӱb��: �ȱ����^��r���M�Ʀr , �B���ץ��ݦb4��10����");
				} 
				else if(vencheck != null) {
					errorMsgs.add("�t�ӱb��: �b���w�Q���U");
				}

				String venPw = req.getParameter("venPw").trim();
				String venPwReg = "^[(a-zA-Z0-9_)]{4,10}$";
				if (venPw == null || venPw.length() == 0) {
					errorMsgs.add("�t�ӱK�X: �ФŪť�");
				} else if (!venPw.matches(venPwReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�t�ӱK�X: �ȱ����^��r���M�Ʀr , �B���ץ��ݦb4��10����");
				}

				String venTel = req.getParameter("venTel").trim();
				String venTelReg = "^[0-9]{1,10}$";
				if (venTel == null || venTel.length() == 0) {
					errorMsgs.add("�s���q��: �ФŪť�");
				} else if(!venTel.matches(venTelReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�s���q��: �ȱ����Ʀr, ������\"-\"�B�ť���Ψ�L");
	            }

				String venID = req.getParameter("venID").trim();
				String venIDReg = "^[0-9]{8}$"; //�u���榡�ˬd
				if (venID == null || venID.length() == 0) {
					errorMsgs.add("�Τ@�s��: �ФŪť�");
				} else if(!venID.matches(venIDReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�Τ@�s��: �п�J���T�榡 ");
	            }
				
				String venMoney = req.getParameter("venMoney").trim();
				if (venMoney == null || venMoney.length() == 0) {
					errorMsgs.add("�a�}: �ФŪť�");
				} 
					
				String zipcode = req.getParameter("zipcode");
				String country = req.getParameter("country");
				String district = req.getParameter("district");
				String address = req.getParameter("address").trim();
				
				String venAddr =zipcode + country + district + address;
				if (venAddr == null || venAddr.length() == 0) {
					errorMsgs.add("�a�}: �ФŪť�");
				}
				
				String venEmail = req.getParameter("venEmail").trim();
				String venEmailReg = "^([a-zA-Z0-9_]+@[a-zA-Z0-9.]+.[a-zA-Z]{2,4})*$";
				if (venEmail == null || venEmail.length() == 0) {
					errorMsgs.add("�q�l�l��: �ФŪť�");
				} else if(!venEmail.matches(venEmailReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�q�l�l��: �п�J���T�榡");
	            }

				Part part = req.getPart("venPhoto");
				InputStream is = part.getInputStream();
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				byte[] buffer = new byte[8192];
				int i;
				while ((i = is.read(buffer)) != -1)
					baos.write(buffer, 0, i);
				byte[] venPhoto = baos.toByteArray();
				baos.close();
				is.close();

				String venIntro = req.getParameter("venIntro").trim();
			
				VendorVO vendorVO = new VendorVO();

				vendorVO.setVenAcc(venAcc);
				vendorVO.setVenPw(venPw);
				vendorVO.setVenName(venName);
				vendorVO.setVenTel(venTel);
				vendorVO.setVenID(venID);
				vendorVO.setVenMoney(venMoney);
				vendorVO.setVenAddr(venAddr);
				vendorVO.setVenEmail(venEmail);
				vendorVO.setVenPhoto(venPhoto);
				vendorVO.setVenIntro(venIntro);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("XvendorVO", vendorVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/vendor/registerVendor.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.�}�l�s�W��� ***************************************/
				vendorVO = vendorSvc.addVendor(venAcc, venPw, venName,
						 venTel, venID, venMoney, venAddr,venEmail, venPhoto, venIntro);
				
			    
			    //�o�e���Ҧ����u
			    // =======================================����========================================
				//�����s�ӫ~
				Set<String> empSet = new HashSet<String>();       //�ŧi�����ݭn�����O
				
				EmpService empService = new EmpService();     
				List<EmpVO>  allEmpList = empService.getAll() ;     //���o�Ҧ��|�����
				
					//�[�J�Ҧ��|���s����memSetet
				for(EmpVO empVO: allEmpList) {
					empSet.add(empVO.getEmpno());				
				}
				
				String Info = vendorVO.getVenName() + "�w�ӽ�,�мf�d";  //�ǰe���T��
				Robot.chatRobot(Info, empSet);
				
				// ============================================����=========================================

				/*************************** 3.�s�W����,�ǳ����(Send the Success view) ***********/
				
				String url = "/front-end/vendor/loginVendor.jsp";
				req.setAttribute("success", "vendor");
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				return;
					
				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/vendor/registerVendor.jsp");
				failureView.forward(req, res);
			}
		}
		if ("update".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				String venNo = req.getParameter("venNo").trim();

				String venName = req.getParameter("venName").trim();
				String venNameReg = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9)]{1,20}$";
				int count = venName.length();
				int bytes = venName.getBytes().length;
				if (venName == null || venName.length() == 0) {
					errorMsgs.add("�t�ӦW��: �ФŪť�");
				} else if (!venName.matches(venNameReg) || (2 * bytes - count) > 20 ) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�t�ӦW��: �ȱ������B�^��r���B�Ʀr , �B����r����6�Ӧr��");
				}

				String venAcc = req.getParameter("venAcc").trim();
				String venAccReg = "^[(a-zA-Z0-9_)]{4,10}$";
				if (venAcc == null || venAcc.length() == 0) {
					errorMsgs.add("�t�ӱb��: �ФŪť�");
				} else if (!venAcc.matches(venAccReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�t�ӱb��: �ȱ����^��r���M�Ʀr , �B���ץ��ݦb4��10����");
				}

				String venPw = req.getParameter("venPw").trim();
				String venPwReg = "^[(a-zA-Z0-9_)]{4,10}$";
				if (venPw == null || venPw.length() == 0) {
					errorMsgs.add("�t�ӱK�X: �ФŪť�");
				} else if (!venPw.matches(venPwReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�t�ӱK�X: �ȱ����^��r���M�Ʀr , �B���ץ��ݦb4��10����");
				}

				String venTel = req.getParameter("venTel").trim();
				String venTelReg = "^[0-9]{1,10}$";
				if (venTel == null || venTel.length() == 0) {
					errorMsgs.add("�s���q��: �ФŪť�");
				} else if(!venTel.matches(venTelReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�s���q��: �ȱ����Ʀr, ������\"-\"�B�ť���Ψ�L");
	            }

				String venID = req.getParameter("venID").trim();
				String venIDReg = "^[0-9]{8}$"; //�u���榡�ˬd
				if (venID == null || venID.length() == 0) {
					errorMsgs.add("�Τ@�s��: �ФŪť�");
				} else if(!venID.matches(venIDReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�Τ@�s��: �п�J���T�榡 ");
	            }
				
				String venMoney = req.getParameter("venMoney").trim();
				if (venMoney == null || venMoney.length() == 0) {
					errorMsgs.add("�a�}: �ФŪť�");
				} 
					
				String venAddr = req.getParameter("venAddr").trim();
				if (venAddr == null || venAddr.length() == 0) {
					errorMsgs.add("�a�}: �ФŪť�");
				} 
				
				String venEmail = req.getParameter("venEmail").trim();
				String venEmailReg = "^([a-zA-Z0-9_]+@[a-zA-Z0-9.]+.[a-zA-Z]{2,4})*$";
				if (venEmail == null || venEmail.length() == 0) {
					errorMsgs.add("�q�l�l��: �ФŪť�");
				} else if(!venEmail.matches(venEmailReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�q�l�l��: �п�J���T�榡");
	            }
				
				VendorService vendorSvc = new VendorService();
				Part part = req.getPart("venPhoto");
				InputStream is = part.getInputStream();
				byte[] venPhoto = null;
				if(is.available() != 0) {
					byte[] buf = new byte[is.available()];
					is.read(buf);
					venPhoto = buf;
					is.close();
				}else {
					venPhoto = vendorSvc.getOneVendor(venNo).getVenPhoto();
				}

				String venIntro = req.getParameter("venIntro").trim();
				
				VendorVO vendorVO = new VendorVO();

				vendorVO.setVenNo(venNo);
				vendorVO.setVenAcc(venAcc);
				vendorVO.setVenPw(venPw);
				vendorVO.setVenName(venName);
				vendorVO.setVenTel(venTel);
				vendorVO.setVenID(venID);
				vendorVO.setVenMoney(venMoney);
				vendorVO.setVenAddr(venAddr);
				vendorVO.setVenEmail(venEmail);
				vendorVO.setVenPhoto(venPhoto);
				vendorVO.setVenIntro(venIntro);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("vendorVO", vendorVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/vendor/homeVendor.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************************** 2.�}�l�ק��� *****************************************/
				vendorVO = vendorSvc.updateVendor(venNo,venAcc,venPw,venName,
						venTel,venID,venMoney,venAddr,venEmail,venPhoto,venIntro);

				/*************************** 3.�ק粒��,�ǳ����(Send the Success view) *************/
				req.setAttribute("vendorVO", vendorVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq&session
				HttpSession session = req.getSession();
				session.setAttribute("LoginVendor", vendorVO);
				String url = "/front-end/vendor/homeVendor.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/vendor/homeVendor.jsp");
				failureView.forward(req, res);
			}
		}
		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� ****************************************/
				String venNo = req.getParameter("venNo").trim();

				/*************************** 2.�}�l�d�߸�� ****************************************/
				VendorService vendorSvc = new VendorService();
				VendorVO vendorVO = vendorSvc.getOneVendor(venNo);

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) ************/
				req.setAttribute("vendorVO", vendorVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/front-end/vendor/update_vendor_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/vendor/listAllVendor.jsp");
				failureView.forward(req, res);
			}
		}
		if ("delete".equals(action)) { // �Ӧ�listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				String venNo = req.getParameter("venNo");
				
				/***************************2.�}�l�R�����***************************************/
				VendorService vendorSvc = new VendorService();
				vendorSvc.deleteVendor(venNo);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				String url = "/front-end/vendor/listAllVendor.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/mem/listAllVendor.jsp");
				failureView.forward(req, res);
			}
		}
		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String venNo = req.getParameter("venNo").trim();
				if (venNo == null || venNo.length() == 0) {
					errorMsgs.add("�п�J���u�s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/vendor/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				VendorService vendorSvc = new VendorService();
				VendorVO vendorVO = vendorSvc.getOneVendor(venNo);
				if (venNo == null) {
					errorMsgs.add("�d�L���");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/vendor/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("vendorVO", vendorVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/front-end/vendor/listOneVendor.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/vendor/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		if ("updateByEmp".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				String venno = req.getParameter("venno").trim();
				String venname = req.getParameter("venname").trim();
				String venpw = req.getParameter("venpw").trim();
				String ventel = req.getParameter("ventel").trim();
				String venid = req.getParameter("venid").trim();
				String venmoney = req.getParameter("venmoney").trim();
				String venaddr = req.getParameter("venaddr").trim();
				String venemail = req.getParameter("venemail").trim();
				String venstatus = req.getParameter("venstatus").trim();

				VendorVO vendorVO = new VendorVO();

				vendorVO.setVenNo(venno);
				vendorVO.setVenName(venname);
				vendorVO.setVenPw(venpw);
				vendorVO.setVenTel(ventel);
				vendorVO.setVenID(venid);
				vendorVO.setVenMoney(venmoney);
				vendorVO.setVenAddr(venaddr);
				vendorVO.setVenEmail(venemail);
				vendorVO.setVenStatus(venstatus);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("vendorVO", vendorVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/venmanage.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.�}�l�ק��� *****************************************/
				VendorService vendorSvc = new VendorService();
				VendorVO vendorVOO =vendorSvc.getOneVendor(venno);
				String venStatusO = vendorVOO.getVenStatus();
				vendorVO = vendorSvc.updateVendorByEmp(venno, venpw, venname, ventel, venid, venmoney, venaddr,
						venemail, venstatus);

				if (!venStatusO.equals(venstatus)) {
					String str="";
					Set<String> set = new HashSet<String>();
					set.add(venno);
					if ("V1".equals(venstatus)) {
						str=vendorVOO.getVenAcc()+" �z���t�Ө������Ҥw�q�L";
						Robot.chatRobot(str,set);
					} else if ("V2".equals(venstatus)) {
						str=vendorVOO.getVenAcc()+" �z���t�Ө����ثe�w�Q���v";
						Robot.chatRobot(str,set);
					}
				}

				/*************************** 3.�ק粒��,�ǳ����(Send the Success view) *************/
				req.setAttribute("vendorVO", vendorVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
				String url = "/back-end/emp/venmanage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/venmanage.jsp");
				failureView.forward(req, res);
			}
		}
	}

}

