package com.shop_product.controller;

import java.io.*;
import java.sql.Date;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.shop_product.model.Shop_productVO;
import com.tools.PhotoToByte;
import com.shop_product.model.Shop_productService;

@MultipartConfig(fileSizeThreshold = 1920 * 1080, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class Shop_productServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException{
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException{
		

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) {     

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.�����ШD�Ѽ�****************************************/
				String str = req.getParameter("prodNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J�ӫ~�s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("vendorSelectProductHome.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				String prodNo = str;
				
				/***************************2.�}�l�d�߸��****************************************/
				Shop_productService shopProductSvc = new Shop_productService();
				Shop_productVO shopProductVO = shopProductSvc.getOneShopProductVO(prodNo);
								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("shopProductVO", shopProductVO);         // ��Ʈw���X����,�s�Jreq
				String url = "vendorListOneShopProduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� 
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("vendorSelectProductHome.jsp");
				failureView.forward(req, res);
			}
		}
			
//		====================================��ܧ�s�e�����=========================================		
		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAll.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.�����ШD�Ѽ�****************************************/
				String prodNo = req.getParameter("prodNo");
				
				/***************************2.�}�l�d�߸��****************************************/
				Shop_productService shopProductSvc = new Shop_productService();
				Shop_productVO shopProductVO = shopProductSvc.getOneShopProductVO(prodNo);
					
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("shopProductVO", shopProductVO);         // ��Ʈw���X������,�s�Jreq
				String url = "vendorUpdateShopProductInput.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� 
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("vendorListAllShopProduct.jsp");
				failureView.forward(req, res);
			}
		}
		
//		====================================��s���=========================================
		if ("update".equals(action)) {       		
			List<String> errorMsgs = new LinkedList<String>();		
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				Shop_productService shopProductSvc = new Shop_productService();
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String prodNo = req.getParameter("prodNo");

				String venNo  = req.getParameter("venNo");
				String className  = req.getParameter("className");
//				�ӫ~�W��
				String prodName = req.getParameter("prodName");
				String prodNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,30}$";
				if (prodName== null || (prodName.trim()).length() == 0) {
					errorMsgs.add("�ӫ~�W��:�ФŪť�");
				} else if(!prodName.trim().matches(prodNameReg)){
					errorMsgs.add("�ӫ~�W��: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb1��30����");
				}
			
//				�ӫ~����
				String prodIntro = req.getParameter("prodIntro");
				String prodIntroReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,300}$";
				if (prodIntro== null || (prodIntro.trim()).length() == 0) {
					errorMsgs.add("�ӫ~����:�ФŪť�");
				} else if(!prodIntro.trim().matches(prodIntroReg)){
					errorMsgs.add("�ӫ~����: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb1��100����");
				}

//				�W�[�ɶ�(���i���)

				java.sql.Date increaseTime = java.sql.Date.valueOf(req.getParameter("increaseTime").trim());

//				�ӫ~���
				Integer price = null;
				try {
					price = new Integer(req.getParameter("price"));
				}catch(NumberFormatException e) {
					price = 0;
					errorMsgs.add("�ӫ~����ж�Ʀr");
				}
				if (price < 1) {
					errorMsgs.add("�ӫ~����: �нT�{��J������");
				}
//				�����H��
				Integer evCount = new Integer(req.getParameter("evCount"));
//				�����`��
				Integer evTotal = new Integer(req.getParameter("evTotal"));
//				�W�U�[���A
				Integer sprodStatus = null;
				try {
					sprodStatus = new Integer(req.getParameter("sprodStatus"));
				} catch(NumberFormatException e) {
					sprodStatus = null;
					errorMsgs.add("�ӫ~���A�ж�Ʀr");
				}
			
//				�Ϥ��W��					 				
			    Part part = req.getPart("photo");
			    PhotoToByte ptb = new PhotoToByte();
			    byte[] photo = ptb.photoToByte(part);
			    if(photo == null) {
			    	photo = shopProductSvc.getOneShopProductVO(prodNo).getPhoto();
			    }
			    
				Shop_productVO shopProductVO = new Shop_productVO();
				shopProductVO.setProdNo(prodNo);
				shopProductVO.setVenNo(venNo);;
				shopProductVO.setClassName(className);
				shopProductVO.setProdName(prodName);
				shopProductVO.setProdIntro(prodIntro);
				shopProductVO.setIncreaseTime(increaseTime);
				shopProductVO.setPrice(price);
				shopProductVO.setEvCount(evCount);
				shopProductVO.setEvTotal(evTotal);
				shopProductVO.setSprodStatus(sprodStatus);
				shopProductVO.setPhoto(photo);
				
				if (!errorMsgs.isEmpty()) {
		
					req.setAttribute("shopProductVO", shopProductVO);
					RequestDispatcher failureView = req.getRequestDispatcher("vendorUpdateShopProductInput.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�ק���*****************************************/

				
				shopProductVO = shopProductSvc.updateShopProduct(prodNo, venNo, className, prodName, prodIntro, increaseTime, price, sprodStatus, evCount, evTotal, photo);
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("shopProductVO", shopProductVO);
				String url = "vendorListOneShopProduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);  //�ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);
				
			}catch (Exception e) {

				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("vendorUpdateShopProductInput.jsp");
				failureView.forward(req, res);
			}
		}		
		
		
//		===========================================�s�W�ӫ~==================================
		if ("insert".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
				String venNo = req.getParameter("venNo");

				String className = req.getParameter("className");

				String prodName = req.getParameter("prodName");
	
				String prodNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,30}$";
				if (prodName == null || prodName.trim().length() == 0) {
					errorMsgs.add("�ӫ~�W�ٽФŪť�");
				}else if(!prodName.trim().matches(prodNameReg)){
					errorMsgs.add("�ӫ~�W��: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��30����");
				}

				
				String prodIntro = req.getParameter("prodIntro");
				String prodIntroReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,300}$";
				if (prodIntro == null || prodIntro.trim().length() == 0) {
					errorMsgs.add("�ӫ~���нФŪť�");
				}else if(!prodName.trim().matches(prodIntroReg)) {
					errorMsgs.add("�ӫ~����: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��300����");
				}
					
				Integer price = null;
				try {
					price = new Integer(req.getParameter("price").trim());
				}catch(NumberFormatException e){
					price = 0;
					errorMsgs.add("��J�榡���~�A�п�J�Ʀr");				
				}
				if (price < 1) {
					errorMsgs.add("�ӫ~����: �нT�{��J������");
				}
			
				Integer evCount = new Integer(req.getParameter("evCount"));

				Integer evTotal = new Integer(req.getParameter("evTotal"));
			
				Integer sprodStatus = new Integer(req.getParameter("sprodStatus"));
				
//				�Ϥ��W��		
			    byte[] photo = null;
			    Part part = req.getPart("photo");
			    InputStream is = part.getInputStream();
			    ByteArrayOutputStream baos = new ByteArrayOutputStream();
			    byte[] buffer = new byte[8192];
			    int i;
			    while ((i = is.read(buffer)) != -1)
			    baos.write(buffer, 0, i);
			    photo = baos.toByteArray();
			    baos.close();
			    is.close();
				
				Shop_productVO shopProductVO = new Shop_productVO();
				shopProductVO.setVenNo(venNo);
				shopProductVO.setClassName(className);
				shopProductVO.setProdName(prodName);
				shopProductVO.setProdIntro(prodIntro);
				shopProductVO.setPrice(price);
				shopProductVO.setEvCount(evCount);
				shopProductVO.setEvTotal(evTotal);
				shopProductVO.setSprodStatus(sprodStatus);
				shopProductVO.setPhoto(photo);
				
				if (!errorMsgs.isEmpty()) {

					req.setAttribute("shopProductVO", shopProductVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("vendorAddShopProduct.jsp");
					failureView.forward(req, res);
					return;
				}

				/***************************2.�}�l�s�W���***************************************/
				
				Shop_productService shopProductSvc = new Shop_productService();
				shopProductVO = shopProductSvc.addShopProduct(venNo, className, prodName, prodIntro, price, evCount, evTotal, sprodStatus,photo);

				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				String url = "vendorListAllShopProduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);		
			}catch(Exception e){
				/***************************��L�i�઺���~�B�z**********************************/
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("vendorAddShopProduct.jsp");
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
				String prodNo = new String(req.getParameter("prodNo"));
				
				/***************************2.�}�l�R�����***************************************/
				Shop_productService shopProductSvc = new Shop_productService();
				shopProductSvc.deleteShopProduct(prodNo);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				String url = "vendorListAllShopProduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("vendorListAllShopProduct.jsp");
				failureView.forward(req, res);
			}
		}
		
		
//		=====================================================����(pchome)�s�W�ӫ~���==================================================
//		===========================================�s�W�ӫ~==================================
		if ("insertPcHomeProd".equals(action)) {
		
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
				String venNo = "V008";
				String prodName = req.getParameter("prodName");			
				String className = req.getParameter("className");						
				String prodIntro = req.getParameter("prodIntro");					
				Integer price = new Integer(req.getParameter("price").trim());						
				Integer evCount = 0;
				Integer evTotal = 0;			
				Integer sprodStatus = 0;
					
				Shop_productVO shopProductVO = new Shop_productVO();
				shopProductVO.setVenNo(venNo);
				shopProductVO.setClassName(className);
				shopProductVO.setProdName(prodName);
				shopProductVO.setProdIntro(prodIntro);
				shopProductVO.setPrice(price);
				shopProductVO.setEvCount(evCount);
				shopProductVO.setEvTotal(evTotal);
				shopProductVO.setSprodStatus(sprodStatus);				
				
				/***************************2.�}�l�s�W���***************************************/
				
				Shop_productService shopProductSvc = new Shop_productService();
				shopProductVO = shopProductSvc.addCrawlerShopProduct(venNo, className, prodName, prodIntro, price, evCount, evTotal, sprodStatus);
	
			}catch(Exception e){
				/***************************��L�i�઺���~�B�z**********************************/
				System.out.println("error");

			}
		}
										
	}
}
