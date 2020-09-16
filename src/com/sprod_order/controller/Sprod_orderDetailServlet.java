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



//用於訂單明細評價商品
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
//			====================================更新商品評價資料=========================================
			if ("update".equals(action)) {       //來自memListOneSprod.jsp		
				List<String> errorMsgs = new LinkedList<String>();		
				req.setAttribute("errorMsgs", errorMsgs);
		
				try {

					/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
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
					
					/***************************2.開始修改資料*****************************************/
					Sprod_detailService sprodDetailSvc = new Sprod_detailService();
					sprodDetailVO = sprodDetailSvc.updateSprodDetail(evaStar, evaCont, orderNo, prodNo);
					
					/***************************3.修改完成,準備轉交(Send the Success view)*************/
					req.setAttribute("sprodDetailVO", sprodDetailVO);
					String url = "memListAllSprod_order.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);  //修改成功後,轉交listOneEmp.jsp
					successView.forward(req, res);
					
				}catch (Exception e) {

					errorMsgs.add("修改資料失敗:"+e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/shop/memListOneSprod_order.jsp");
					failureView.forward(req, res);
				}
			}	
System.out.println(action);
			if ("updateajax".equals(action)) {       //來自memListOneSprod.jsp		
				List<String> errorMsgs = new LinkedList<String>();		
				req.setAttribute("errorMsgs", errorMsgs);
		System.out.println("進入");
				try {

					/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
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
					
					/***************************2.開始修改資料*****************************************/
					Sprod_detailService sprodDetailSvc = new Sprod_detailService();
					sprodDetailVO = sprodDetailSvc.updateSprodDetail(evaStar, evaCont, orderNo, prodNo);
					
					/***************************3.修改完成,準備轉交(Send the Success view)*************/
					out.print(gson.toJson(sprodDetailVO));
					
					
					
					
				}catch (Exception e) {

					errorMsgs.add("修改資料失敗:"+e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/shop/memListOneSprod_order.jsp");
					failureView.forward(req, res);
				}
			}	
			
		}
	}
}
