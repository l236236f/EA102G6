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
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String petNo = req.getParameter("petNo").trim();
				
				String pdClass = req.getParameter("pdClass").trim();
				
				String pdCont = req.getParameter("pdCont").trim();
				
				PetDailyVO petDailyVO = new PetDailyVO();

				petDailyVO.setPetNo(petNo);
				petDailyVO.setPdClass(pdClass);
				petDailyVO.setPdCont(pdCont);
				
				/*************************** 2.開始新增資料 ***************************************/
				PetDailyService petDailySvc = new PetDailyService();
				petDailyVO = petDailySvc.addPetDaily(petNo,pdClass,pdCont);
				/*************************** 3.開始新增圖片 ***************************************/
				
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

				/*************************** 2.開始新增資料 ***************************************/
							DailyPhotoService dailyPhotoSvc = new DailyPhotoService();
							dailyPhotoVO = dailyPhotoSvc.addDailyPhoto(pdNo, photo);
						}
					}
				}
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/front-end/petDaily/thePetDaily.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/petDaily/addPetDaily.jsp");
				failureView.forward(req, res);
			}
		}
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
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
					req.setAttribute("petDailyVO", petDailyVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/petDaily/update_petDaily_input.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始修改資料 *****************************************/
				PetDailyService petDailySvc = new PetDailyService();
				petDailyVO = petDailySvc.updatePetDaily(pdNo, petNo, pdClass, pdCont);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("petDailyVO", petDailyVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/front-end/petDaily/listOnePetDaily.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/petDaily/update_petDaily_input.jsp");
				failureView.forward(req, res);
			}
		}
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String pdNo = req.getParameter("pdNo").trim();

				/*************************** 2.開始查詢資料 ****************************************/
				PetDailyService petDailySvc = new PetDailyService();
				PetDailyVO petDailyVO = petDailySvc.getOnePetDaily(pdNo);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("petDailyVO", petDailyVO); // 資料庫取出的empVO物件,存入req
				String url = "/front-end/petDaily/update_petDaily_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/petDaily/listAllPetDaily.jsp");
				failureView.forward(req, res);
			}
		}
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String pdNo = req.getParameter("pdNo");
				
				/***************************2.開始刪除資料***************************************/
				//關聯照片優先刪除
				DailyPhotoService dailyPhotoService = new DailyPhotoService();
				dailyPhotoService.deleteByPDNo(pdNo);
				
				PetDailyService petDailySvc = new PetDailyService();
				petDailySvc.deletePetDaily(pdNo);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/petDaily/thePetDaily.jsp");
				failureView.forward(req, res);
			}
		}
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String pdNo = req.getParameter("pdNo").trim();
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/petDaily/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				PetDailyService petDailySvc = new PetDailyService();
				PetDailyVO petDailyVO = petDailySvc.getOnePetDaily(pdNo);
				if (petDailyVO == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/petDaily/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("petDailyVO", petDailyVO); // 資料庫取出的empVO物件,存入req
				String url = "/front-end/petDaily/listOnePetDaily.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/petDaily/select_page.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
