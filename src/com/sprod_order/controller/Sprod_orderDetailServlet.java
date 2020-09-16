package com.sprod_order.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import com.google.gson.Gson;
import com.sprod_detail.model.*;



//�Ω�q����ӵ����ӫ~
public class Sprod_orderDetailServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException{
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException{
		

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		PrintWriter out = res.getWriter();
		Gson gson = new Gson();
		{
//			====================================��s�ӫ~�������=========================================
			if ("update".equals(action)) {       //�Ӧ�memListOneSprod.jsp		
				List<String> errorMsgs = new LinkedList<String>();		
				req.setAttribute("errorMsgs", errorMsgs);
		
				try {

					/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
					Integer evaStar = new Integer(req.getParameter("evaStar"));
					String evaCont  = req.getParameter("evaCont");
					String orderNo = req.getParameter("orderNo");
					String prodNo = req.getParameter("prodNo");

					Sprod_detailVO sprodDetailVO = new Sprod_detailVO();
					sprodDetailVO.setEvaStar(evaStar);
					sprodDetailVO.setEvaCont(evaCont);
					sprodDetailVO.setOrderNo(orderNo);
					sprodDetailVO.setProdNo(prodNo);
					
					if (!errorMsgs.isEmpty()) {
			
						req.setAttribute("sprodDetailVO", sprodDetailVO);
						RequestDispatcher failureView = req.getRequestDispatcher("/front-end/shop/memListOneSprod_order.jsp");
						failureView.forward(req, res);
						return;
					}
					
					/***************************2.�}�l�ק���*****************************************/
					Sprod_detailService sprodDetailSvc = new Sprod_detailService();
					sprodDetailVO = sprodDetailSvc.updateSprodDetail(evaStar, evaCont, orderNo, prodNo);
					
					/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
					req.setAttribute("sprodDetailVO", sprodDetailVO);
					String url = "memListAllSprod_order.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);  //�ק令�\��,���listOneEmp.jsp
					successView.forward(req, res);
					
				}catch (Exception e) {

					errorMsgs.add("�ק��ƥ���:"+e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/shop/memListOneSprod_order.jsp");
					failureView.forward(req, res);
				}
			}	
System.out.println(action);
			if ("updateajax".equals(action)) {       //�Ӧ�memListOneSprod.jsp		
				List<String> errorMsgs = new LinkedList<String>();		
				req.setAttribute("errorMsgs", errorMsgs);
		System.out.println("�i�J");
				try {

					/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
					Integer evaStar = new Integer(req.getParameter("evaStar"));
					String evaCont  = req.getParameter("evaCont");
					String orderNo = req.getParameter("orderNo");
					String prodNo = req.getParameter("prodNo");

					Sprod_detailVO sprodDetailVO = new Sprod_detailVO();
					sprodDetailVO.setEvaStar(evaStar);
					sprodDetailVO.setEvaCont(evaCont);
					sprodDetailVO.setOrderNo(orderNo);
					sprodDetailVO.setProdNo(prodNo);
					
					if (!errorMsgs.isEmpty()) {
			
						req.setAttribute("sprodDetailVO", sprodDetailVO);
						RequestDispatcher failureView = req.getRequestDispatcher("/front-end/shop/memListOneSprod_order.jsp");
						failureView.forward(req, res);
						return;
					}
					
					/***************************2.�}�l�ק���*****************************************/
					Sprod_detailService sprodDetailSvc = new Sprod_detailService();
					sprodDetailVO = sprodDetailSvc.updateSprodDetail(evaStar, evaCont, orderNo, prodNo);
					
					/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
					out.print(gson.toJson(sprodDetailVO));
					
					
					
					
				}catch (Exception e) {

					errorMsgs.add("�ק��ƥ���:"+e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/shop/memListOneSprod_order.jsp");
					failureView.forward(req, res);
				}
			}	
			
		}
	}
}
