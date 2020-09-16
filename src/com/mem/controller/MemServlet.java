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
			memSvc.updateMemStatus(memNo);			//更改會員狀態(M1)
			MemVO memVO = memSvc.getOneMem(memNo);  //拿完整VO再登入
			
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
				errorMsgs.add("無此帳號");
			}else {
				if (memPw.equals(memVO.getMemPw())) {//帳號密碼皆正確
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
						errorMsgs.add("尚未驗證,請至電子信箱進行驗證");
					}
					
				} else {
					errorMsgs.add("密碼錯誤");
				}
			}
			//登入失敗
			RequestDispatcher failureView = req.getRequestDispatcher("/front-end/mem/loginMem.jsp");
			failureView.forward(req, res);

		}
		if("applyForMom".equals(action)) {
			   System.out.println("QAQ");
			   String memNo = req.getParameter("memNo");
			   MemService memSvc = new MemService();
			   memSvc.updateMom(memNo);   //更改會員狀態(M1)
			   MemVO memVO = memSvc.getOneMem(memNo);  //拿完整VO再存session
			   
			   HttpSession session = req.getSession();
			   session.setAttribute("LoginMem",memVO);
			   
			   
			   return;
		}

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String memName = req.getParameter("memName").trim();
				String memNameReg = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9)]{1,20}$";
				int count = memName.length();
				int bytes = memName.getBytes().length;
				if (memName == null || memName.length() == 0) {
					errorMsgs.add("會員名稱: 請勿空白");
				} else if (!memName.matches(memNameReg) || (2 * bytes - count) > 20) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員名稱: 僅接受中、英文字母、數字 , 且中文字必須6個字內");
				}

				String memAcc = req.getParameter("memAcc").trim();
				String memAccReg = "^[(a-zA-Z0-9_)]{4,10}$";
				MemService memSvc = new MemService();
				MemVO memcheck = memSvc.checkAcc(memAcc); //此帳號的VO
				if (memAcc == null || memAcc.length() == 0) {
					errorMsgs.add("會員帳號: 請勿空白");
				} else if (!memAcc.matches(memAccReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員帳號: 僅接受英文字母和數字 , 且長度必需在4到10之間");
				} 
				else if(memcheck != null) {
					errorMsgs.add("會員帳號: 帳號已被註冊");
				}

				String memPw = req.getParameter("memPw").trim();
				String memPwReg = "^[(a-zA-Z0-9_)]{4,10}$";
				if (memPw == null || memPw.length() == 0) {
					errorMsgs.add("會員密碼: 請勿空白");
				} else if (!memPw.matches(memPwReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員密碼: 僅接受英文字母和數字 , 且長度必需在4到10之間");
				}

				java.sql.Date memBirth = null;
				try {
					memBirth = java.sql.Date.valueOf(req.getParameter("memBirth").trim());
				} catch (IllegalArgumentException e) {
					memBirth = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				String memID = req.getParameter("memID").trim();
				String memIdReg = "^[a-zA-Z][0-9]{9}$"; // 只有格式檢查
				if (memID == null || memID.length() == 0) {
					errorMsgs.add("身分證字號: 請勿空白");
				} else if (!memID.matches(memIdReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("身分證字號: 請輸入正確格式, 首字(英文)不分大小寫");
				}

				String memTel = req.getParameter("memTel").trim();
				String memTelReg = "^[0-9]{1,10}$";
				if (memTel == null || memTel.length() == 0) {
					errorMsgs.add("連絡電話: 請勿空白");
				} else if (!memTel.matches(memTelReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("連絡電話: 僅接受數字, 不接受\"-\"、空白鍵及其他");
				}

				String memGender = req.getParameter("memGender").trim();
				
				String zipcode = req.getParameter("zipcode");
				String country = req.getParameter("country");
				String district = req.getParameter("district");
				String address = req.getParameter("address").trim();
				
				String memAddr =zipcode + country + district + address;
				if (memAddr == null || memAddr.length() == 0) {
					errorMsgs.add("地址: 請勿空白");
				}

				String memEmail = req.getParameter("memEmail").trim();
				String memEmailReg = "^([a-zA-Z0-9_]+@[a-zA-Z0-9.]+.[a-zA-Z]{2,4})*$";
				if (memEmail == null || memEmail.length() == 0) {
					errorMsgs.add("電子郵件: 請勿空白");
				} else if (!memEmail.matches(memEmailReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("電子郵件: 請輸入正確格式");
				}

				String memMoney = req.getParameter("memMoney").trim();
				if (memMoney == null || memMoney.length() == 0) {
					errorMsgs.add("帳戶:請勿空白");
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
					req.setAttribute("XmemVO", memVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/mem/registerMem.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				memVO = memSvc.addMem(memAcc, memPw, memName, memBirth, memID, memTel, memGender, memAddr, memEmail,
						memMoney, memPhoto, memIntro);
				
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				
				String subject = "GGYY會員通知信";
			    String button = req.getScheme() + "://" + req.getServerName()+ ":" + req.getServerPort()+ req.getContextPath()
			         + "/front-end/mem/mem.do?action=firstTime&memNo=" + memVO.getMemNo();
			    MailThread mailThread = new MailThread(memEmail, subject, memName, button);
			    mailThread.start();
			      
				String url = "/front-end/index.jsp";
				req.setAttribute("success", "mem");
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				return;
					
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/mem/registerMem.jsp");
				failureView.forward(req, res);
			}
		}
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String memNo = req.getParameter("memNo").trim();
				
				String memName = req.getParameter("memName").trim();
				String memNameReg = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9)]{1,20}$";
				int count = memName.length();
				int bytes = memName.getBytes().length;
				if (memName == null || memName.length() == 0) {
					errorMsgs.add("會員名稱: 請勿空白");
				} else if (!memName.matches(memNameReg) || (2 * bytes - count) > 20) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員名稱: 僅接受中、英文字母、數字 , 且中文字必須6個字內");
				}

				String memPw = req.getParameter("memPw").trim();
				String memPwReg = "^[(a-zA-Z0-9_)]{4,10}$";
				if (memPw == null || memPw.length() == 0) {
					errorMsgs.add("會員密碼: 請勿空白");
				} else if (!memPw.matches(memPwReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員密碼: 僅接受英文字母和數字 , 且長度必需在4到10之間");
				}

				java.sql.Date memBirth = null;
				try {
					memBirth = java.sql.Date.valueOf(req.getParameter("memBirth").trim());
				} catch (IllegalArgumentException e) {
					memBirth = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				String memID = req.getParameter("memID").trim();
				String memIDReg = "^[a-zA-Z][0-9]{9}$"; // 只有格式檢查
				if (memID == null || memID.length() == 0) {
					errorMsgs.add("身分證字號: 請勿空白");
				} else if (!memID.matches(memIDReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("身分證字號: 請輸入正確格式, 首字(英文)不分大小寫");
				}

				String memTel = req.getParameter("memTel").trim();
				String memTelReg = "^[0-9]{1,10}$";
				if (memTel == null || memTel.length() == 0) {
					errorMsgs.add("連絡電話: 請勿空白");
				} else if (!memTel.matches(memTelReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("連絡電話: 僅接受數字, 不接受\"-\"、空白鍵及其他");
				}

				String memGender = req.getParameter("memGender").trim();

				String zipcode = req.getParameter("zipcode");
				String country = req.getParameter("country");
				String district = req.getParameter("district");
				String address = req.getParameter("address").trim();
				
				String memAddr =zipcode + country + district + address;
				if (memAddr == null || memAddr.length() == 0) {
					errorMsgs.add("地址: 請勿空白");
				}

				String memEmail = req.getParameter("memEmail").trim();
				String memEmailReg = "^([a-zA-Z0-9_]+@[a-zA-Z0-9.]+.[a-zA-Z]{2,4})*$";
				if (memEmail == null || memEmail.length() == 0) {
					errorMsgs.add("連絡電話: 請勿空白");
				} else if (!memEmail.matches(memEmailReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("連絡電話: 僅接受數字, 不接受\"-\"、空白鍵及其他");
				}

				String memMoney = req.getParameter("memMoney").trim();
				if (memMoney == null || memMoney.length() == 0) {
					errorMsgs.add("帳戶請勿空白");
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
					req.setAttribute("XmemVO", memVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/mem/modifyMem.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始修改資料 *****************************************/
				MemService memSvc = new MemService();
				memSvc.updateMem(memNo, memPw, memName, memBirth, memID, memTel, memGender, memAddr,
						memEmail, memMoney, memIntro);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				memVO = memSvc.getOneMem(memNo);
				HttpSession session = req.getSession();
				session.setAttribute("LoginMem",memVO);// 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/front-end/mem/homeMem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/mem/modifyMem.jsp");
				failureView.forward(req, res);
			}
		}
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String memNo = req.getParameter("memNo").trim();

				/*************************** 2.開始查詢資料 ****************************************/
				MemService memSvc = new MemService();
				MemVO memVO = memSvc.getOneMem(memNo);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("memVO", memVO); // 資料庫取出的empVO物件,存入req
				String url = "/front-end/mem/update_mem_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/mem/listAllMem.jsp");
				failureView.forward(req, res);
			}
		}
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String memNo = req.getParameter("memNo");

				/*************************** 2.開始刪除資料 ***************************************/
				MemService memSvc = new MemService();
				memSvc.deleteMem(memNo);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/front-end/mem/listAllMem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/mem/listAllMem.jsp");
				failureView.forward(req, res);
			}
		}
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String memNo = req.getParameter("memNo").trim();
				if (memNo == null || memNo.length() == 0) {
					errorMsgs.add("請輸入會員編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/mem/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				MemService memSvc = new MemService();
				MemVO memVO = memSvc.getOneMem(memNo);
				if (memVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/mem/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("memVO", memVO); // 資料庫取出的empVO物件,存入req
				String url = "/front-end/mem/listOneMem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/mem/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		if ("updateByemp".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
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
				/*************************** 開始修改資料 *****************************************/
				MemService memSvc = new MemService();
				//拿原本的保母狀態
				MemVO momvoO =memSvc.getOneMem(memNo);
				if("M1".equals(momvoO.getMom())){
					String str="";
					Set<String> set = new HashSet<String>();
					set.add(memNo);
					if("M2".equals(mom)) {
						str=momvoO.getMemAcc()+" 您的保母審核已通過";
						Robot.chatRobot(str,set);
						GetFosmLatLng gll = new GetFosmLatLng();
						FosmService fmSvc = new FosmService();
						FosmVO fosmVO = gll.getLat(memNo, momvoO.getMemAddr());
						fmSvc.addFosMon(fosmVO);
						
					}else if("M3".equals(mom)) {
						str=momvoO.getMemAcc()+" 您的保母審核判定未通過";
						Robot.chatRobot(str,set);
					}
				}
				//開始推播
				memSvc.updateMemByEmp(memNo, memPw,memEmail, memMoney,memStatus,mom,bonus);
				//修改資料
				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				String url = "/back-end/emp/memmanage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/memmanage.jsp");
				failureView.forward(req, res);
			}
		}
	}

}
