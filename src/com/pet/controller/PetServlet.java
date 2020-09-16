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
		
		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String memNo = req.getParameter("memNo").trim();
				
				String petName = req.getParameter("petName").trim();
				String petNameReg = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9)]{1,20}$";
				if (petName == null || petName.length() == 0) {
					errorMsgs.add("寵物名稱: 請勿空白");
				} else if (!petName.matches(petNameReg) || petName.getBytes().length > 20 ) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("寵物名稱: 僅接受中、英文字母、數字 , 且中文字必須6個字內");
				}
				
				Part part = req.getPart("petPhoto");//小吳老師程式碼
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
					req.setAttribute("insertPetVO", petVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/pet/homePet.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				PetService petSvc = new PetService();
				petVO = petSvc.addPet(memNo, petName, petPhoto, petKind, petVariety, 
						petBirth, petGender, petID, petIntro);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/front-end/pet/homePet.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/pet/homePet.jsp");
				failureView.forward(req, res);
			}
		}
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String petNo = req.getParameter("petNo").trim();
				
				String memNo = req.getParameter("memNo").trim();
				
				String petName = req.getParameter("petName").trim();
				String petNameReg = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9)]{1,20}$";
				if (petName == null || petName.length() == 0) {
					errorMsgs.add("會員名稱: 請勿空白");
				} else if (!petName.matches(petNameReg) || petName.getBytes().length > 20 ) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員名稱: 僅接受中、英文字母、數字 , 且中文字必須10個字內");
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
					req.setAttribute("updatePetVO", petVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/pet/homePet.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/*************************** 2.開始修改資料 *****************************************/
				petVO = petSvc.updatePet(petNo, memNo, petName, petPhoto, petKind, petVariety, 
						petBirth, petGender, petIntro);
				
				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/	
				JSONObject json = new JSONObject();//Ajax以json傳到前端
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
				
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/pet/homePet.jsp");
				failureView.forward(req, res);
			}
		}
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*************************** 1.接收請求參數 ****************************************/
				String petNo = req.getParameter("petNo").trim();

				/*************************** 2.開始查詢資料 ****************************************/
				PetService petSvc = new PetService();
				PetVO petVO = petSvc.getOnePet(petNo);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("petVO", petVO); // 資料庫取出的empVO物件,存入req
				String url = "/front-end/pet/listOnePet.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/pet/homePet.jsp");
				failureView.forward(req, res);
			}
		}
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String petNo = req.getParameter("petNo");
				
				/***************************2.開始刪除資料***************************************/
				PetService petSvc = new PetService();
				petSvc.deletePet(petNo);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/front-end/pet/listAllPet.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/pet/listAllPet.jsp");
				failureView.forward(req, res);
			}
		}
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String petNo = req.getParameter("petNo").trim();
				if (petNo == null || petNo.length() == 0) {
					errorMsgs.add("請輸入員工編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/pet/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				PetService memSvc = new PetService();
				PetVO petVO = memSvc.getOnePet(petNo);
				if (petVO == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/pet/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("petVO", petVO); // 資料庫取出的empVO物件,存入req
				String url = "/front-end/pet/listOnePet.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/pet/select_page.jsp");
				failureView.forward(req, res);
			}
		}
	}

}
