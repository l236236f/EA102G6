package com.sprod_order.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.omg.CORBA.INTERNAL;

import com.mem.model.MemService;
import com.mem.model.MemVO;
import com.shop_product.model.Shop_productService;
import com.sprod_detail.model.Sprod_detailService;
import com.sprod_detail.model.Sprod_detailVO;
import com.sprod_order.model.Sprod_orderService;
import com.sprod_order.model.Sprod_orderVO;

import idv.david.websocketchat.controller.Robot;



public class Sprod_orderServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException{
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException{
		

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
//		=======�|���d�ߩҦ��q��===========
		if ("getOne_For_DisplayMem".equals(action)) {     

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.�����ШD�Ѽ�****************************************/
				String str = req.getParameter("orderNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J�q��s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/shop/memListAllSprod_order.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				String orderNo = str;

				/***************************2.�}�l�d�߸��****************************************/
				Sprod_orderService sprodOrderSvc = new Sprod_orderService();
				Sprod_orderVO sprodOrderVO = sprodOrderSvc.getOneSprodOrderVO(orderNo);

				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("sprodOrderVO", sprodOrderVO);        

				String url = "/front-end/shop/memListOneSprod_order.jsp";

				RequestDispatcher successView = req.getRequestDispatcher(url);
			
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {

				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/shop/memListAllSprod_order.jsp");
				failureView.forward(req, res);
			}
		}
			
//		====================================�|����ܧ�s�e�����=========================================		
		if ("getOne_For_UpdateMem".equals(action)) { 
	
			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.�����ШD�Ѽ�****************************************/
				String orderNo = req.getParameter("orderNo");
			
				/***************************2.�}�l�d�߸��****************************************/
				Sprod_orderService sprodOrderSvc = new Sprod_orderService();
				Sprod_orderVO sprodOrderVO = sprodOrderSvc.getOneSprodOrderVO(orderNo);

				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("sprodOrderVO", sprodOrderVO);        
				String url = "/front-end/shop/memUpdate_sprod_order_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);

				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/shop/memListAllSprod_order.jsp");
				failureView.forward(req, res);
			}
		}
		
//		====================================�|����s���f���A���=========================================
		if ("updateMem".equals(action)) {       //�Ӧ�/shop_product/update_shop_product_input.jsp		
			List<String> errorMsgs = new LinkedList<String>();		
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {

				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
//				�q��s��
				String orderNo = req.getParameter("orderNo");
//				�|���s��
				String memNo = req.getParameter("memNo");	
//				�H�e�覡	
				Integer tranMethod = null;
				try {
					tranMethod = new Integer(req.getParameter("tranMethod").trim());
				}catch(NumberFormatException e){
					tranMethod = 0;
					errorMsgs.add("��J�榡���~�A�п�J�Ʀr");				
				}
//				����H�m�W
				String addresseeName = req.getParameter("addresseeName");
				String addresseeNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (addresseeName == null || addresseeName.trim().length() == 0) {
					errorMsgs.add("����H�m�W�ФŪť�");
				}else if(!addresseeName.trim().matches(addresseeNameReg)){
					errorMsgs.add("����H�m�W���e: �u��O���B�^��r���B�Ʀr�M_ , �B����W���ץ��ݦb2��10����");
				}
//				�a�}
				String tranAdd = req.getParameter("tranAdd");
				String tranAddReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,30}$";
				if (tranAdd == null || tranAdd.trim().length() == 0) {
					errorMsgs.add("�a�}�ФŪť�");
				}else if(!tranAdd.trim().matches(tranAddReg)) {
					errorMsgs.add("�a�}: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��30����");
				}
//				�q�l�H�c
				String addresseeMail = req.getParameter("addresseeMail");
				if (addresseeMail == null || addresseeMail.trim().length() == 0) {
					errorMsgs.add("�q�l�H�c�ФŪť�");
				}
//				�q���`���B
				Integer orderTotal = new Integer(req.getParameter("orderTotal"));
//				�q�檬�A(�w�]�����X�f)
				Integer orderStatus = new Integer(req.getParameter("orderStatus"));
//				�A�Ϊ��u�f�s��(�i���ŭ�)
				String spNo = req.getParameter("spNo");		
				
				Sprod_orderVO sprodOrderVO = new Sprod_orderVO();
				sprodOrderVO.setOrderNo(orderNo);
				sprodOrderVO.setMemNo(memNo);
				sprodOrderVO.setTranMethod(tranMethod);
				sprodOrderVO.setTranAdd(tranAdd);
				sprodOrderVO.setAddresseeName(addresseeName);
				sprodOrderVO.setAddresseeMail(addresseeMail);
				sprodOrderVO.setOrderTotal(orderTotal);
				sprodOrderVO.setOrderStatus(orderStatus);
				sprodOrderVO.setSpNo(spNo);
				
				if (!errorMsgs.isEmpty()) {
		
					req.setAttribute("sprodOrderVO", sprodOrderVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/shop/memUpdate_sprod_order_input.jsp");
					failureView.forward(req, res);
					return;
				}					
				/***************************2.�}�l�ק���*****************************************/
				Sprod_orderService sprodOrderSvc = new Sprod_orderService();
				sprodOrderVO = sprodOrderSvc.updateSprodOrder(memNo, tranMethod, tranAdd, addresseeName, addresseeMail, orderTotal, orderStatus, spNo, orderNo);
	
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("sprodOrderVO", sprodOrderVO);
				String url = "/front-end/shop/memListOneSprod_order.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);  //�ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);
				
			}catch (Exception e) {

				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/shop/memUpdate_sprod_order_input.jsp");
				failureView.forward(req, res);
			}
		}		
		
		
////		===========================================�|���s�W�q��==================================
		if ("insert".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/			
//				�|���s��
				String memNo = req.getParameter("memNo");	
//				�H�e�覡	
				Integer tranMethod = null;
				try {
					tranMethod = new Integer(req.getParameter("tranMethod").trim());
				}catch(NumberFormatException e){
					tranMethod = 0;
					errorMsgs.add("��J�榡���~�A�п�J�Ʀr");				
				}
//				����H�m�W
				String addresseeName = req.getParameter("addresseeName");
				String addresseeNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (addresseeName == null || addresseeName.trim().length() == 0) {
					errorMsgs.add("����H�m�W�ФŪť�");
				}else if(!addresseeName.trim().matches(addresseeNameReg)){
					errorMsgs.add("����H�m�W���e: �u��O���B�^��r���B�Ʀr�M_ , �B����W���ץ��ݦb2��10����");
				}
//				�a�}
				String country = req.getParameter("country");
				String district = req.getParameter("district");
				String tranAdd0 = req.getParameter("tranAdd");//�ԲӦa�}
				String tranAddReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,30}$";
				if (tranAdd0 == null || tranAdd0.trim().length() == 0) {
					errorMsgs.add("�a�}�ФŪť�");
				}else if(!tranAdd0.trim().matches(tranAddReg)) {
					errorMsgs.add("�a�}: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��30����");
				}
				String tranAdd = country + district + tranAdd0;

//				�q�l�H�c
				String addresseeMail = req.getParameter("addresseeMail");
				if (addresseeMail == null || addresseeMail.trim().length() == 0) {
					errorMsgs.add("�q�l�H�c�ФŪť�");
				}
//				�q���`���B
				Integer orderTotal = new Integer(req.getParameter("orderTotal"));
				if ( orderTotal < 0 ) {
					errorMsgs.add("�нT�{�ӫ~�ƶq");
				}
//				�q�檬�A(�w�]�����X�f)
				Integer orderStatus = new Integer(req.getParameter("orderStatus"));
//				�A�Ϊ��u�f�s��(�i���ŭ�)
				String spNo = req.getParameter("spNo");
														
				Sprod_orderVO sprodOrderVO = new Sprod_orderVO();
				sprodOrderVO.setMemNo(memNo);
				sprodOrderVO.setTranMethod(tranMethod);
				sprodOrderVO.setTranAdd(tranAdd);
				sprodOrderVO.setAddresseeName(addresseeName);
				sprodOrderVO.setAddresseeMail(addresseeMail);
				sprodOrderVO.setOrderTotal(orderTotal);
				sprodOrderVO.setOrderStatus(orderStatus);
				sprodOrderVO.setSpNo(spNo);
//				���o�q�����
				HttpSession session = req.getSession();		
				List<Sprod_detailVO> sprodDetailVOList = (List<Sprod_detailVO>)session.getAttribute("sprodDetail");
				Shop_productService shopProductSvc = new Shop_productService();  //�����o�ӫ~�W��
//				�ˬd�ʪ����ӫ~�ƶq
				for(Sprod_detailVO lists: sprodDetailVOList) {							
					String prodNo = lists.getProdNo();
					Integer quantity = lists.getQuantity();
					if (quantity < 0) {
						errorMsgs.add("�z��" + shopProductSvc.getOneShopProductVO(prodNo).getProdName() + "�ƶq���`");
					}
				}

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("sprodOrderVO", sprodOrderVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/shop/Checkout.jsp");
					failureView.forward(req, res);
					return;
				}

				/***************************2.�}�l�s�W���***************************************/
synchronized (this) {			
//				=========================�s�W�q��=====================================
					Sprod_orderService sprodOrderSvc = new Sprod_orderService();
					sprodOrderVO = sprodOrderSvc.addSprodOrder(memNo, tranMethod, tranAdd, addresseeName, addresseeMail, orderTotal, orderStatus, spNo);		
//				=========================�s�W�q�����================================
//				���o��s�W���q��s��
					
					List<Sprod_orderVO> Sprod_orderVOList = sprodOrderSvc.getAll();
					String orderNo = (Sprod_orderVOList.get(Sprod_orderVOList.size()-1)).getOrderNo();																		
						
						
					for(Sprod_detailVO lists: sprodDetailVOList) {		
					
						String prodNo = lists.getProdNo();
						Integer quantity = lists.getQuantity();
						Integer evaStar = null;
						String evaCont = null;
						
						Sprod_detailVO sprodDetailVO = new Sprod_detailVO();
//						���o��s�W���q��s��					
						sprodDetailVO.setOrderNo(orderNo);
//						�ǤJ�ʪ����ӫ~�s���μƶq
						sprodDetailVO.setProdNo(prodNo);
						sprodDetailVO.setQuantity(quantity);
//						�������ƩM���e���Ǫŭ�
						sprodDetailVO.setEvaStar(evaStar);
						sprodDetailVO.setEvaCont(evaCont);
									
						Sprod_detailService sprodDetailSvc = new Sprod_detailService();
						sprodDetailSvc.addSprodDetail(orderNo, prodNo, quantity, evaStar, evaCont);
						
					}
					sprodOrderVO.setOrderNo(orderNo); //�N��s�W���q��s���s�JVO��浹checkOutEnd
					session.removeAttribute("sprodDetail");
					session.removeAttribute("shoppingcart");
}					
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				req.setAttribute("sprodOrderVO", sprodOrderVO);
				String url = "/front-end/shop/checkOutEnd.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);		
			}catch(Exception e){
				/***************************��L�i�઺���~�B�z**********************************/
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/shop/Checkout.jsp");
				failureView.forward(req, res);
			}
		}
			
//		============================================�R�����====================================================
		if ("delete".equals(action)) { // �Ӧ�listAllShop_product.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				String orderNo = new String(req.getParameter("orderNo"));
				
				/***************************2.�}�l�R�����***************************************/
				Sprod_orderService sprodOrderSvc = new Sprod_orderService();
				sprodOrderSvc.deleteSprodOrder(orderNo);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				String url = "/front-end/shop/memListAllSprod_order.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/shop/memListAllSprod_order.jsp");
				failureView.forward(req, res);
			}
		}
		
//		======�t�ӥέq��s���d�ߤ@�ӭq��=========
		if ("getOne_For_DisplayVen".equals(action)) {     

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.�����ШD�Ѽ�****************************************/
				String str = req.getParameter("orderNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J�q��s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("vendorSelectSprodOrderHome.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				String orderNo = str;

				/***************************2.�}�l�d�߸��****************************************/
				Sprod_orderService sprodOrderSvc = new Sprod_orderService();
				Sprod_orderVO sprodOrderVO = sprodOrderSvc.getOneSprodOrderVO(orderNo);

				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("sprodOrderVO", sprodOrderVO);        
				String url = "vendorListOneSprod_order.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
			
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {

				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("vendorSelectSprodOrderHome.jsp");
				failureView.forward(req, res);
			}
		}
			
//		====================================�t����ܭq���s�e�����=========================================		
		if ("getOne_For_UpdateVen".equals(action)) { 
	
			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.�����ШD�Ѽ�****************************************/
				String orderNo = req.getParameter("orderNo");
			
				/***************************2.�}�l�d�߸��****************************************/
				Sprod_orderService sprodOrderSvc = new Sprod_orderService();
				Sprod_orderVO sprodOrderVO = sprodOrderSvc.getOneSprodOrderVO(orderNo);

				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("sprodOrderVO", sprodOrderVO);        
				String url = "vendorUpdate_sprod_order_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);

				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("vendorListAllSprod_order.jsp");
				failureView.forward(req, res);
			}
		}
		
//		====================================�t�ӧ�s�X�f���A���=========================================
		if ("updateVen".equals(action)) {       //�Ӧ�/shop_product/update_shop_product_input.jsp		
			List<String> errorMsgs = new LinkedList<String>();		
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {

				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
//				�q��s��
				String orderNo = req.getParameter("orderNo");
//				�|���s��
				String memNo = req.getParameter("memNo");	
//				�H�e�覡	
				Integer tranMethod = null;
				try {
					tranMethod = new Integer(req.getParameter("tranMethod").trim());
				}catch(NumberFormatException e){
					tranMethod = 0;
					errorMsgs.add("��J�榡���~�A�п�J�Ʀr");				
				}
//				����H�m�W
				String addresseeName = req.getParameter("addresseeName");
				String addresseeNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (addresseeName == null || addresseeName.trim().length() == 0) {
					errorMsgs.add("����H�m�W�ФŪť�");
				}else if(!addresseeName.trim().matches(addresseeNameReg)){
					errorMsgs.add("����H�m�W���e: �u��O���B�^��r���B�Ʀr�M_ , �B����W���ץ��ݦb2��10����");
				}
//				�a�}
				String tranAdd = req.getParameter("tranAdd");
				String tranAddReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,30}$";
				if (tranAdd == null || tranAdd.trim().length() == 0) {
					errorMsgs.add("�a�}�ФŪť�");
				}else if(!tranAdd.trim().matches(tranAddReg)) {
					errorMsgs.add("�a�}: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��30����");
				}
//				�q�l�H�c
				String addresseeMail = req.getParameter("addresseeMail");
				if (addresseeMail == null || addresseeMail.trim().length() == 0) {
					errorMsgs.add("�q�l�H�c�ФŪť�");
				}
//				�q���`���B
				Integer orderTotal = new Integer(req.getParameter("orderTotal"));
//				�q�檬�A(�w�]�����X�f)
				Integer orderStatus = new Integer(req.getParameter("orderStatus"));

//				�A�Ϊ��u�f�s��(�i���ŭ�)
				String spNo = req.getParameter("spNo");		
				
				Sprod_orderVO sprodOrderVO = new Sprod_orderVO();
				sprodOrderVO.setOrderNo(orderNo);
				sprodOrderVO.setMemNo(memNo);
				sprodOrderVO.setTranMethod(tranMethod);
				sprodOrderVO.setTranAdd(tranAdd);
				sprodOrderVO.setAddresseeName(addresseeName);
				sprodOrderVO.setAddresseeMail(addresseeMail);
				sprodOrderVO.setOrderTotal(orderTotal);
				sprodOrderVO.setOrderStatus(orderStatus);
				sprodOrderVO.setSpNo(spNo);
				
					
				/***************************2.�}�l�ק���*****************************************/
				Sprod_orderService sprodOrderSvc = new Sprod_orderService();
				sprodOrderVO = sprodOrderSvc.updateSprodOrder(memNo, tranMethod, tranAdd, addresseeName, addresseeMail, orderTotal, orderStatus, spNo, orderNo);
	
		         // =======================================����========================================
				//�����s�ӫ~
				Set<String> memSet = new HashSet<String>();       //�ŧi�����ݭn�����O     
				memSet.add(memNo);
								
				String Info = "�q��s��:" + orderNo + "�X�f�����o";  //�ǰe���T��
				Robot.chatRobot(Info, memSet);
				
					// ============================================����=========================================
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("sprodOrderVO", sprodOrderVO);
				String url = "vendorListOneSprod_order.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);  
				successView.forward(req, res);
				
			}catch (Exception e) {

				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("vendorUpdate_sprod_order_input.jsp");
				failureView.forward(req, res);
			}
		}	
		
	}
	
}
