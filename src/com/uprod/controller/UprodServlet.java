package com.uprod.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.uprod.model.*;

@MultipartConfig(fileSizeThreshold = 1920 * 1080, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class UprodServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/

				String ProdNo = req.getParameter("ProdNo");
				String ProdNoReg = "^[U]\\d{3}$";
				if (ProdNo == null || (ProdNo.trim()).length() == 0) {
					errorMsgs.add("�п�ӫ~�s��");
				}
				else if (!ProdNo.trim().matches(ProdNoReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�ӫ~�W�ٮ榡�����T");
				}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/uprod/used_shop.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 2.�}�l�d�߸�� *****************************************/
				UprodService uprodSvc = new UprodService();
				UprodVO uprodVO = uprodSvc.getOneUprod(ProdNo);
				if (uprodVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/uprod/used_shop.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) *************/
				req.setAttribute("uprodVO", uprodVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/front-end/uprod/used_product.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/uprod/used_shop.jsp");
				failureView.forward(req, res);
			}
		}
		

		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
				/*************************** 1.�����ШD�Ѽ� ****************************************/
				String ProdNo = new String(req.getParameter("ProdNo"));

				/*************************** 2.�}�l�d�߸�� ****************************************/
				UprodService uprodSvc = new UprodService();
				UprodVO uprodVO = uprodSvc.getOneUprod(ProdNo);

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) ************/
				req.setAttribute("uprodVO", uprodVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url1 ="/front-end/uprod/uprod_shopping_cart.jsp"; //�ʶR�ӫ~
				String url2 ="/front-end/uprod/uprod_update.jsp";        //�ק�ӫ~
				
				String url=null;
				String update1=req.getParameter("update1");
				
				switch(update1) {
				    case "2":
				    	url=url2;
				        break;
				    case "1":
				    	url=url1;
				        break;
				    default:
				    	break;
			     }
				
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
				successView.forward(req, res);
			    
				/*************************** ��L�i�઺���~�B�z **********************************/
//			} catch (Exception e) {
//				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/uprod/used_shop.jsp");
//				failureView.forward(req, res);
//			}
		}
		

		if ("update".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {

				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				String ProdNo = new String(req.getParameter("ProdNo").trim());

				String ProdName = req.getParameter("ProdName");
				String ProdNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{3,20}$";
				if (ProdName == null || ProdName.trim().length() == 0) {
					errorMsgs.add("�ӫ~�W��: �ФŪť�");
				} else if (!ProdName.trim().matches(ProdNameReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�ӫ~�W��: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb3��10����");
				}
				String ProdIntro = req.getParameter("ProdIntro");
				
				if (ProdIntro == null || ProdIntro.trim().length() == 0) {
					errorMsgs.add("�ӫ~����: �ФŪť�");
				} else if (!(ProdIntro.trim().length()<300)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�ӫ~����:���Ъ��ץ��ݦb300�Ӧr�H�U");
				}
			
				Integer Price=null;
				try {
					Price = new Integer(req.getParameter("Price").trim());
				} catch (NumberFormatException e) {
					Price = 0;
					errorMsgs.add("����ж�Ʀr.");
				}
				
				java.sql.Timestamp IncreaseTime = null;
			
				IncreaseTime = new java.sql.Timestamp(System.currentTimeMillis());
				
				
				String ProdType = new String(req.getParameter("ProdType").trim());
				
				if (ProdType == null || ProdType.trim().length() == 0) {
					errorMsgs.add("�ӫ~����: �ФŪť�");
				} 
				
				String ProdStatus = new String(req.getParameter("ProdStatus").trim());
				
				Part part = req.getPart("Photo");
				byte[] Photo = getPicByteArr(ProdNo,part);
			    
				UprodVO uprodVO = new UprodVO();
				uprodVO.setProdName(ProdName);
				uprodVO.setProdIntro(ProdIntro);
				uprodVO.setPrice(Price);
				uprodVO.setIncreaseTime(IncreaseTime);
				uprodVO.setProdType(ProdType);
				uprodVO.setProdStatus(ProdStatus);
				uprodVO.setPhoto(Photo);
				uprodVO.setProdNo(ProdNo);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("uprodVO", uprodVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/uprod/uprod_update.jsp");
					failureView.forward(req, res);
					return; // �{�����_
				}

				/*************************** 2.�}�l�ק��� *****************************************/
				UprodService uprodSvc = new UprodService();
				uprodVO = uprodSvc.updateUprod(ProdName, ProdIntro, Price, ProdType, ProdStatus, IncreaseTime, Photo, ProdNo);

				/*************************** 3.�ק粒��,�ǳ����(Send the Success view) *************/
				req.setAttribute("uprodVO", uprodVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
				String url = "/front-end/uprod/uprod_manage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/uprod/uprod_update.jsp");
				failureView.forward(req, res);
			}
		}

		
		if ("buy".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {

				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				String ProdNo = new String(req.getParameter("ProdNo").trim());
                
				String CustNo = req.getParameter("CustNo");
				String CustNoReg = "^[M]\\d{3}$";// Regular Expression
				if (CustNo == null || CustNo.trim().length() == 0) {
					errorMsgs.add("�R�a�s��: �ФŪť�");
				} else if (!CustNo.trim().matches(CustNoReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�R�a�s��: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
				}
				
				String ProdStatus="PS2";
				String OrderStatus="OS0";
				String ReceiveStatus="RS0";
				java.sql.Timestamp OrderTime = null;
			
				OrderTime = new java.sql.Timestamp(System.currentTimeMillis());
				
				
				String TranMethod = new String(req.getParameter("TranMethod").trim());
				
				String TranAddr1 = req.getParameter("county");
				String TranAddr2 = req.getParameter("district");
				String TranAddr3 = req.getParameter("TranAddr3");
				String TranAddr = TranAddr1+TranAddr2+TranAddr3;
				if (TranAddr == null || TranAddr.trim().length() == 0) {
					errorMsgs.add("�H�e�a�} �ФŪť�");
				} else if (!(TranAddr.trim().length()<3000)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�H�e�a�} �a�}���ݦb300�Ӧr�H�U");
				}
				
				UprodVO uprodVO = new UprodVO();
				uprodVO.setCustNo(CustNo);
				uprodVO.setProdStatus(ProdStatus);
				uprodVO.setOrderStatus(OrderStatus);
				uprodVO.setReceiveStatus(ReceiveStatus);
				uprodVO.setOrderTime(OrderTime);
				uprodVO.setTranMethod(TranMethod);
				uprodVO.setTranAddr(TranAddr);
				uprodVO.setProdNo(ProdNo);
			
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("uprodVO", uprodVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/uprod/uprod_shopping_cart.jsp");
					failureView.forward(req, res);
					return; // �{�����_
				}

				/*************************** 2.�}�l�ק��� *****************************************/
				UprodService uprodSvc = new UprodService();
				uprodVO = uprodSvc.buyUprod(CustNo, ProdStatus,OrderStatus,ReceiveStatus, OrderTime,TranMethod,TranAddr,ProdNo);

				/*************************** 3.�ק粒��,�ǳ����(Send the Success view) *************/
				req.setAttribute("uprodVO", uprodVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
				String url = "/front-end/uprod/uprod_shopping_end.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/uprod/uprod_shopping_cart.jsp.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("insert".equals(action)) { // �Ӧ�addEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z *************************/
				String SellerNo = req.getParameter("SellerNo");
				String SellerNoReg = "^[M]\\d{3}$";// Regular Expression
				if (SellerNo == null || SellerNo.trim().length() == 0) {
					errorMsgs.add("��a�s��: �ФŪť�");
				} else if (!SellerNo.trim().matches(SellerNoReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("��a�s��: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
				}
				
				String ProdName = req.getParameter("ProdName");
				String ProdNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{3,20}$";
				if (ProdName == null || ProdName.trim().length() == 0) {
					errorMsgs.add("�ӫ~�W��: �ФŪť�");
				} else if (!ProdName.trim().matches(ProdNameReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�ӫ~�W��: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb3��10����");
				}
				String ProdIntro = req.getParameter("ProdIntro");
				
				if (ProdIntro == null || ProdIntro.trim().length() == 0) {
					errorMsgs.add("�ӫ~����: �ФŪť�");
				} else if (!(ProdIntro.trim().length()<300)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�ӫ~����:���Ъ��ץ��ݦb300�Ӧr�H�U");
				}
				
				Integer Price=null;
				try {
					Price = new Integer(req.getParameter("Price").trim());
				} catch (NumberFormatException e) {
					Price = 0;
					errorMsgs.add("����ж�Ʀr.");
				}
				
			    String ProdStatus="PS0";
				
				String ProdType = new String(req.getParameter("ProdType").trim());
				
				if (ProdType == null || ProdType.trim().length() == 0) {
					errorMsgs.add("�ӫ~����: �ФŪť�");
				} 
				
				Part part = req.getPart("Photo");
				byte[] Photo = getPicByteArr(null,part);
				
				if(Photo==null) errorMsgs.add("�ФW�ǰӫ~�Ӥ�(����)");
				
				UprodVO uprodVO = new UprodVO();
				uprodVO.setSellerNo(SellerNo);
				uprodVO.setProdName(ProdName);
				uprodVO.setProdIntro(ProdIntro);
				uprodVO.setPrice(Price);
				uprodVO.setProdType(ProdType);
				uprodVO.setProdStatus(ProdStatus);
				uprodVO.setPhoto(Photo);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("uprodVO", uprodVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/uprod/uprod_add.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.�}�l�s�W��� ***************************************/
				UprodService uprodSvc = new UprodService();
				uprodVO = uprodSvc.addUprod(SellerNo, ProdName, ProdIntro, Price, ProdStatus, ProdType,Photo);

				/*************************** 3.�s�W����,�ǳ����(Send the Success view) ***********/
				String url = "/front-end/uprod/uprod_manage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/uprod/uprod_add.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("shipment".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {

				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				String ProdNo = new String(req.getParameter("ProdNo").trim());
			    
				UprodVO uprodVO = new UprodVO();
				uprodVO.setProdNo(ProdNo);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("uprodVO", uprodVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/uprod/uprod_seller_detail.jsp");
					failureView.forward(req, res);
					return; // �{�����_
				}

				/*************************** 2.�}�l�ק��� *****************************************/
				UprodService uprodSvc = new UprodService();
				uprodVO = uprodSvc.shipment(ProdNo);

				/*************************** 3.�ק粒��,�ǳ����(Send the Success view) *************/
				req.setAttribute("uprodVO", uprodVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
				String url = "/front-end/uprod/uprod_seller_detail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/uprod/uprod_seller_detail.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("receive".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {

				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				String ProdNo = new String(req.getParameter("ProdNo").trim());
               
				UprodVO uprodVO = new UprodVO();
				uprodVO.setProdNo(ProdNo);
			
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("uprodVO", uprodVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/uprod/uprod_customer.jsp");
					failureView.forward(req, res);
					return; // �{�����_
				}

				/*************************** 2.�}�l�ק��� *****************************************/
				UprodService uprodSvc = new UprodService();
				uprodVO = uprodSvc.receive(ProdNo);

				/*************************** 3.�ק粒��,�ǳ����(Send the Success view) *************/
				req.setAttribute("uprodVO", uprodVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
				String url = "/front-end/uprod/uprod_customer.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/uprod/uprod_customer.jsp");
				failureView.forward(req, res);
			}
		}
	}
	
	
	public static byte[] getPicByteArr(String ProdNo,Part part) throws IOException, ServletException {
		UprodService uprodSvc = new UprodService();
		byte[] picArr = null;
		
		InputStream in = part.getInputStream();		
		if(in.available()!=0) {
			picArr = new byte[in.available()];
			in.read(picArr);
			in.close();
		}
		else {
			picArr = ProdNo== null? null: uprodSvc.getOneUprod(ProdNo).getPhoto();
		}
		return picArr;
			
	}
}
