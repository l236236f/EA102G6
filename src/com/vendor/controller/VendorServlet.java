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
				errorMsgs.add("無此帳號");
			}else {
				if (venPw.equals(vendorVO.getVenPw())) {
					if("V0".equals(vendorVO.getVenStatus())) {
						errorMsgs.add("管理員審核中...請稍後再試 : (");
					}else if("V1".equals(vendorVO.getVenStatus())) {
						HttpSession session = req.getSession();
						session.setAttribute("LoginVendor",vendorVO);
						String url = "/front-end/vendor/homeVendor.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url);
						successView.forward(req, res);
						return;
					}else {
						System.out.println(vendorVO.getVenStatus());
						errorMsgs.add("帳號已停權  : )");
					}
				} else {
					errorMsgs.add("密碼錯誤");
				}
			}
			RequestDispatcher failureView = req.getRequestDispatcher("/front-end/vendor/loginVendor.jsp");
			failureView.forward(req, res);
		}
		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String venName = req.getParameter("venName").trim();
				String venNameReg = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9)]{1,20}$";
				int count = venName.length();
				int bytes = venName.getBytes().length;
				if (venName == null || venName.length() == 0) {
					errorMsgs.add("廠商名稱: 請勿空白");
				} else if (!venName.matches(venNameReg) || (2 * bytes - count) > 20 ) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("廠商名稱: 僅接受中、英文字母、數字 , 且中文字必須6個字內");
				}
				
				String venAcc = req.getParameter("venAcc").trim();
				String venAccReg = "^[(a-zA-Z0-9_)]{4,10}$";
				VendorService vendorSvc = new VendorService();
				VendorVO vencheck = vendorSvc.checkAcc(venAcc); //此帳號的VO
				if (venAcc == null || venAcc.length() == 0) {
					errorMsgs.add("廠商帳號: 請勿空白");
				} else if (!venAcc.matches(venAccReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("廠商帳號: 僅接受英文字母和數字 , 且長度必需在4到10之間");
				} 
				else if(vencheck != null) {
					errorMsgs.add("廠商帳號: 帳號已被註冊");
				}

				String venPw = req.getParameter("venPw").trim();
				String venPwReg = "^[(a-zA-Z0-9_)]{4,10}$";
				if (venPw == null || venPw.length() == 0) {
					errorMsgs.add("廠商密碼: 請勿空白");
				} else if (!venPw.matches(venPwReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("廠商密碼: 僅接受英文字母和數字 , 且長度必需在4到10之間");
				}

				String venTel = req.getParameter("venTel").trim();
				String venTelReg = "^[0-9]{1,10}$";
				if (venTel == null || venTel.length() == 0) {
					errorMsgs.add("連絡電話: 請勿空白");
				} else if(!venTel.matches(venTelReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("連絡電話: 僅接受數字, 不接受\"-\"、空白鍵及其他");
	            }

				String venID = req.getParameter("venID").trim();
				String venIDReg = "^[0-9]{8}$"; //只有格式檢查
				if (venID == null || venID.length() == 0) {
					errorMsgs.add("統一編號: 請勿空白");
				} else if(!venID.matches(venIDReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("統一編號: 請輸入正確格式 ");
	            }
				
				String venMoney = req.getParameter("venMoney").trim();
				if (venMoney == null || venMoney.length() == 0) {
					errorMsgs.add("地址: 請勿空白");
				} 
					
				String zipcode = req.getParameter("zipcode");
				String country = req.getParameter("country");
				String district = req.getParameter("district");
				String address = req.getParameter("address").trim();
				
				String venAddr =zipcode + country + district + address;
				if (venAddr == null || venAddr.length() == 0) {
					errorMsgs.add("地址: 請勿空白");
				}
				
				String venEmail = req.getParameter("venEmail").trim();
				String venEmailReg = "^([a-zA-Z0-9_]+@[a-zA-Z0-9.]+.[a-zA-Z]{2,4})*$";
				if (venEmail == null || venEmail.length() == 0) {
					errorMsgs.add("電子郵件: 請勿空白");
				} else if(!venEmail.matches(venEmailReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("電子郵件: 請輸入正確格式");
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
					req.setAttribute("XvendorVO", vendorVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/vendor/registerVendor.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				vendorVO = vendorSvc.addVendor(venAcc, venPw, venName,
						 venTel, venID, venMoney, venAddr,venEmail, venPhoto, venIntro);
				
			    
			    //發送給所有員工
			    // =======================================推播========================================
				//推播新商品
				Set<String> empSet = new HashSet<String>();       //宣告推播需要的型別
				
				EmpService empService = new EmpService();     
				List<EmpVO>  allEmpList = empService.getAll() ;     //取得所有會員資料
				
					//加入所有會員編號給memSetet
				for(EmpVO empVO: allEmpList) {
					empSet.add(empVO.getEmpno());				
				}
				
				String Info = vendorVO.getVenName() + "已申請,請審查";  //傳送的訊息
				Robot.chatRobot(Info, empSet);
				
				// ============================================推播=========================================

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				
				String url = "/front-end/vendor/loginVendor.jsp";
				req.setAttribute("success", "vendor");
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				return;
					
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/vendor/registerVendor.jsp");
				failureView.forward(req, res);
			}
		}
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String venNo = req.getParameter("venNo").trim();

				String venName = req.getParameter("venName").trim();
				String venNameReg = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9)]{1,20}$";
				int count = venName.length();
				int bytes = venName.getBytes().length;
				if (venName == null || venName.length() == 0) {
					errorMsgs.add("廠商名稱: 請勿空白");
				} else if (!venName.matches(venNameReg) || (2 * bytes - count) > 20 ) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("廠商名稱: 僅接受中、英文字母、數字 , 且中文字必須6個字內");
				}

				String venAcc = req.getParameter("venAcc").trim();
				String venAccReg = "^[(a-zA-Z0-9_)]{4,10}$";
				if (venAcc == null || venAcc.length() == 0) {
					errorMsgs.add("廠商帳號: 請勿空白");
				} else if (!venAcc.matches(venAccReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("廠商帳號: 僅接受英文字母和數字 , 且長度必需在4到10之間");
				}

				String venPw = req.getParameter("venPw").trim();
				String venPwReg = "^[(a-zA-Z0-9_)]{4,10}$";
				if (venPw == null || venPw.length() == 0) {
					errorMsgs.add("廠商密碼: 請勿空白");
				} else if (!venPw.matches(venPwReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("廠商密碼: 僅接受英文字母和數字 , 且長度必需在4到10之間");
				}

				String venTel = req.getParameter("venTel").trim();
				String venTelReg = "^[0-9]{1,10}$";
				if (venTel == null || venTel.length() == 0) {
					errorMsgs.add("連絡電話: 請勿空白");
				} else if(!venTel.matches(venTelReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("連絡電話: 僅接受數字, 不接受\"-\"、空白鍵及其他");
	            }

				String venID = req.getParameter("venID").trim();
				String venIDReg = "^[0-9]{8}$"; //只有格式檢查
				if (venID == null || venID.length() == 0) {
					errorMsgs.add("統一編號: 請勿空白");
				} else if(!venID.matches(venIDReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("統一編號: 請輸入正確格式 ");
	            }
				
				String venMoney = req.getParameter("venMoney").trim();
				if (venMoney == null || venMoney.length() == 0) {
					errorMsgs.add("地址: 請勿空白");
				} 
					
				String venAddr = req.getParameter("venAddr").trim();
				if (venAddr == null || venAddr.length() == 0) {
					errorMsgs.add("地址: 請勿空白");
				} 
				
				String venEmail = req.getParameter("venEmail").trim();
				String venEmailReg = "^([a-zA-Z0-9_]+@[a-zA-Z0-9.]+.[a-zA-Z]{2,4})*$";
				if (venEmail == null || venEmail.length() == 0) {
					errorMsgs.add("電子郵件: 請勿空白");
				} else if(!venEmail.matches(venEmailReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("電子郵件: 請輸入正確格式");
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
					req.setAttribute("vendorVO", vendorVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/vendor/homeVendor.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************************** 2.開始修改資料 *****************************************/
				vendorVO = vendorSvc.updateVendor(venNo,venAcc,venPw,venName,
						venTel,venID,venMoney,venAddr,venEmail,venPhoto,venIntro);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("vendorVO", vendorVO); // 資料庫update成功後,正確的的empVO物件,存入req&session
				HttpSession session = req.getSession();
				session.setAttribute("LoginVendor", vendorVO);
				String url = "/front-end/vendor/homeVendor.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/vendor/homeVendor.jsp");
				failureView.forward(req, res);
			}
		}
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String venNo = req.getParameter("venNo").trim();

				/*************************** 2.開始查詢資料 ****************************************/
				VendorService vendorSvc = new VendorService();
				VendorVO vendorVO = vendorSvc.getOneVendor(venNo);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("vendorVO", vendorVO); // 資料庫取出的empVO物件,存入req
				String url = "/front-end/vendor/update_vendor_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/vendor/listAllVendor.jsp");
				failureView.forward(req, res);
			}
		}
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String venNo = req.getParameter("venNo");
				
				/***************************2.開始刪除資料***************************************/
				VendorService vendorSvc = new VendorService();
				vendorSvc.deleteVendor(venNo);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/front-end/vendor/listAllVendor.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/mem/listAllVendor.jsp");
				failureView.forward(req, res);
			}
		}
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String venNo = req.getParameter("venNo").trim();
				if (venNo == null || venNo.length() == 0) {
					errorMsgs.add("請輸入員工編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/vendor/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				VendorService vendorSvc = new VendorService();
				VendorVO vendorVO = vendorSvc.getOneVendor(venNo);
				if (venNo == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/vendor/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("vendorVO", vendorVO); // 資料庫取出的empVO物件,存入req
				String url = "/front-end/vendor/listOneVendor.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/vendor/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		if ("updateByEmp".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
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
					req.setAttribute("vendorVO", vendorVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/venmanage.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始修改資料 *****************************************/
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
						str=vendorVOO.getVenAcc()+" 您的廠商身分驗證已通過";
						Robot.chatRobot(str,set);
					} else if ("V2".equals(venstatus)) {
						str=vendorVOO.getVenAcc()+" 您的廠商身分目前已被停權";
						Robot.chatRobot(str,set);
					}
				}

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("vendorVO", vendorVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/emp/venmanage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/venmanage.jsp");
				failureView.forward(req, res);
			}
		}
	}

}

