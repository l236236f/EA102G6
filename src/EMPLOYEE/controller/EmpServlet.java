package EMPLOYEE.controller;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import AUTHORITY.model.AUTHOService;
import EMPLOYEE.model.*;

@MultipartConfig(fileSizeThreshold = 1920 * 1080, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class EmpServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3033076204001855194L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
		String action = req.getParameter("action");
//		System.out.println("進入SERV");
		if ("login".equals(action)) {

			EmpService empSvc = new EmpService();
			EmpVO empVO = new EmpVO();
			String iderror = null;
			String pswerror = null;
			String idstr = req.getParameter("empid");
			String pswstr = req.getParameter("emppsw");
			req.setAttribute("idstr", idstr);
			req.setAttribute("pswstr", pswstr);
			try {

				empVO = empSvc.getOneEmpLogin(idstr);
				if (idstr == null || idstr.trim().length() == 0) {
					iderror = "ID請勿為空";
				} else if (empVO == null) {
					iderror = "ID未存在";
				}
				if (iderror != null) {
					req.setAttribute("iderror", iderror);
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/loginEmp.jsp");
					failureView.forward(req, res);
					return;

				}
				if (pswstr == null || pswstr.trim().length() == 0) {
					pswerror = "密碼請勿為空";
				}
				if (!empVO.getEmppsw().equals(pswstr)) {
					pswerror = "密碼不正確";
				}
				if (pswerror != null) {
					req.setAttribute("pswerror", pswerror);
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/loginEmp.jsp");
					failureView.forward(req, res);
					return;
				}
				if ("E1".equals(empVO.getEmpstatus())) {
					HttpSession session = req.getSession();
					session.setAttribute("EmpVO", empVO);
					String location = (String) session.getAttribute("location");

					if (location != null) {
						session.removeAttribute(location);
						res.sendRedirect(location);
						session.removeAttribute("location");
						return;
					}
					res.sendRedirect(req.getContextPath() + "/back-end/emp/empbackindex.jsp");
				}else if("E0".equals(empVO.getEmpstatus())) {
					res.sendRedirect(req.getContextPath() + "/back-end/emp/loginEmp.jsp");
				}
				
			} catch (Exception e) {
				e.getStackTrace();
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/errorPage.html");
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
				String str = req.getParameter("empno");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入員工編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/empmanage.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String empno = null;
				try {
					empno = new String(str);
				} catch (Exception e) {
					errorMsgs.add("員工編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/empmanage.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				EmpService empSvc = new EmpService();
				EmpVO empVO = empSvc.getOneEmp(empno);
				if (empVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("empVO", empVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/emp/empmanage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/empmanage.jsp");
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
				String empno = new String(req.getParameter("empno"));

				/*************************** 2.開始查詢資料 ****************************************/
				EmpService empSvc = new EmpService();
				EmpVO empVO = empSvc.getOneEmp(empno);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("empVO", empVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/emp/empmanage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/empmanage.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
//			System.out.println("進入UPDATE");
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

				String empno = req.getParameter("empno").trim();
				String empname = req.getParameter("empname").trim();

				if (empname == null || empname.trim().length() == 0) {
					errorMsgs.add("員工姓名: 請勿空白");
				}
				String emptel = req.getParameter("emptel").trim();
				String empemail = req.getParameter("empemail").trim();
				String empid = req.getParameter("empid").trim();
				if (empid == null || empid.trim().length() == 0) {
					errorMsgs.add("ID請勿空白");
				}
				String emppsw = req.getParameter("emppsw").trim();
				if (empid == null || empid.trim().length() == 0) {
					errorMsgs.add("密碼請勿空白");
				}
				String empposition = req.getParameter("empposition").trim();
				if (empposition == null || empposition.trim().length() == 0) {
					errorMsgs.add("職位請勿空白");
				}

				java.sql.Timestamp emphiredate = null;
				try {
					emphiredate = java.sql.Timestamp.valueOf(req.getParameter("emphiredate") + " 00:00:00");
				} catch (IllegalArgumentException e) {
					emphiredate = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入時間!");
				}

				Timestamp empchangedate = new Timestamp(System.currentTimeMillis());
				String empchangeman = req.getParameter("empchangeman");
				String empstatus = req.getParameter("empstatus");
				if (empstatus == null || empstatus.trim().length() == 0) {
					errorMsgs.add("狀態請勿空白");
				}

				String empnotes = req.getParameter("empnotes");

				byte[] empphoto = null;

				Part part = req.getPart("photo");
				InputStream in = part.getInputStream();
				if (in.available() != 0) {
					byte[] buf = new byte[in.available()];
					in.read(buf);
					empphoto = buf;
				} else {
					empphoto = new EmpService().getOneEmp(empno).getEmpphoto();
				}

				EmpVO empVO = new EmpVO();
				empVO.setEmpno(empno);
				empVO.setEmpname(empname);
				empVO.setEmptel(emptel);
				empVO.setEmpemail(empemail);
				empVO.setEmpid(empid);
				empVO.setEmppsw(emppsw);
				empVO.setEmpphoto(empphoto);
				empVO.setEmpposition(empposition);
				empVO.setEmphiredate(emphiredate);
				empVO.setEmpchangedate(empchangedate);
				empVO.setEmpchangeman(empchangeman);
				empVO.setEmpstatus(empstatus);
				empVO.setEmpnotes(empnotes);

//				System.out.println(empno);
//				System.out.println(empname);
//				System.out.println(emptel);
//				System.out.println(empemail);
//				System.out.println(empid);
//				System.out.println(emppsw);
//				System.out.println(empposition);
//				System.out.println(emphiredate);
//				System.out.println(empchangedate);
//				System.out.println(empchangeman);
//				System.out.println(empstatus);
//				System.out.println(empnotes);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("empVO", empVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/empmanage.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				EmpService empSvc = new EmpService();
				empVO = empSvc.updateEmp(empno, empname, emptel, empemail, empid, emppsw, empphoto, empposition,
						emphiredate, empchangedate, empchangeman, empstatus, empnotes);
				/*************************** 修改權限 *************/

				if ("E0".equals(empstatus)) {
					AUTHOService authoSvc = new AUTHOService();
					authoSvc.deleteAUTHOByEmpno(empno);
				}

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("empVO", empVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/emp/empmanage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/empmanage.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求
//System.out.println("進入INSERT");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

				String empname = req.getParameter("empname");

				if (empname == null || empname.trim().length() == 0) {
					errorMsgs.add("員工姓名: 請勿空白");
				}

				String emptel = req.getParameter("emptel").trim();
				String empemail = req.getParameter("empemail").trim();
				String empid = req.getParameter("empid").trim();
				if (empid == null || empid.trim().length() == 0) {
					errorMsgs.add("ID請勿空白");
				}
				String emppsw = getRandomPassword();
				String empposition = req.getParameter("empposition").trim();
				if (empposition == null || empposition.trim().length() == 0) {
					errorMsgs.add("職位請勿空白");
				}

				java.sql.Timestamp emphiredate = null;
				try {
					emphiredate = java.sql.Timestamp.valueOf(req.getParameter("emphiredate") + " 00:00:00");
				} catch (IllegalArgumentException e) {
					emphiredate = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入時間!");
				}

				Timestamp empchangedate = new Timestamp(System.currentTimeMillis());

				String empchangeman = req.getParameter("empchangeman");
				String empstatus = req.getParameter("empstatus");
				if (empstatus == null || empstatus.trim().length() == 0) {
					errorMsgs.add("狀態請勿空白");
				}

				String empnotes = req.getParameter("empnotes");

				byte[] empphoto = null;
				Part part = req.getPart("photo");
				InputStream in = part.getInputStream();
				if (in.available() != 0) {
					byte[] buf = new byte[in.available()];
					in.read(buf);
					empphoto = buf;
				}

				EmpVO empVO = new EmpVO();

				empVO.setEmpname(empname);
				empVO.setEmptel(emptel);
				empVO.setEmpemail(empemail);
				empVO.setEmpid(empid);
				empVO.setEmppsw(emppsw);
				empVO.setEmpphoto(empphoto);
				empVO.setEmpposition(empposition);
				empVO.setEmphiredate(emphiredate);
				empVO.setEmpchangedate(empchangedate);
				empVO.setEmpchangeman(empchangeman);
				empVO.setEmpstatus(empstatus);
				empVO.setEmpnotes(empnotes);

//				System.out.println(empname);
//				System.out.println(emptel);
//				System.out.println(empemail);
//				System.out.println(empid);
//				System.out.println(emppsw);
//				System.out.println(empposition);
//				System.out.println(emphiredate);
//				System.out.println(empchangedate);
//				System.out.println(empchangeman);
//				System.out.println(empstatus);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("empVO", empVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/addEmp.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				EmpService empSvc = new EmpService();

				empVO = empSvc.addEmp(empname, emptel, empemail, empid, emppsw, empphoto, empposition, emphiredate,
						empchangedate, empchangeman, empstatus, empnotes);

				/*************************** EMAIL ***************************************/

				String to = empemail;
				String subject = "寄寄養養後台員工密碼通知";
				String ch_name = empid;
				String passRandom = emppsw;
				String messageText = "你好! " + ch_name + " 請謹記此密碼: " + passRandom + "\n" + " (已經啟用)";

				MailService mailService = new MailService();
				mailService.sendMail(to, subject, messageText);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/emp/empmanage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/empmanage.jsp");
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
				String empno = new String(req.getParameter("empno"));

				/*************************** 2.開始刪除資料 ***************************************/
				EmpService empSvc = new EmpService();
				empSvc.deleteEmp(empno);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/emp/listAllEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/listAllEmp.jsp");
				failureView.forward(req, res);
			}
		}

		if ("checkid".equals(action)) {
			String empid = req.getParameter("empid");
			EmpService empSvc = new EmpService();
			out.print(empSvc.checkEmpid(empid));
		}

		if ("logout".equals(action)) {
			try {
				HttpSession session = req.getSession();
				session.removeAttribute("EmpVO");
				RequestDispatcher sucessRegister = req.getRequestDispatcher("/back-end/emp/loginEmp.jsp");
				sucessRegister.forward(req, res);
			} catch (Exception e) {
				e.printStackTrace();
				RequestDispatcher failRegister = req.getRequestDispatcher("/front-end/errorPage.html");
				failRegister.forward(req, res);
			}
		}

		if ("updateByEmp".equals(action)) { // 來自update_emp_input.jsp的請求
//			System.out.println("進入UPDATE");
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

				String empno = req.getParameter("empno").trim();
				EmpVO empVO1 = new EmpService().getOneEmp(empno);

				String empname = empVO1.getEmpname();

				if (empname == null || empname.trim().length() == 0) {
					errorMsgs.add("員工姓名: 請勿空白");
				}
				String emptel = req.getParameter("emptel").trim();
				String empemail = req.getParameter("empemail").trim();
				String empid = req.getParameter("empid").trim();
				if (empid == null || empid.trim().length() == 0) {
					errorMsgs.add("ID請勿空白");
				}
				String emppsw = req.getParameter("emppsw").trim();
				if (empid == null || empid.trim().length() == 0) {
					errorMsgs.add("密碼請勿空白");
				}
				String empposition = empVO1.getEmpposition();
				java.sql.Timestamp emphiredate = empVO1.getEmphiredate();
				Timestamp empchangedate = new Timestamp(System.currentTimeMillis());
				String empchangeman = req.getParameter("empchangeman");

				String empstatus = empVO1.getEmpstatus();

				String empnotes = empVO1.getEmpnotes();

				byte[] empphoto = null;

				Part part = req.getPart("photo");
				InputStream in = part.getInputStream();
				if (in.available() != 0) {
					byte[] buf = new byte[in.available()];
					in.read(buf);
					empphoto = buf;
				} else {
					empphoto = new EmpService().getOneEmp(empno).getEmpphoto();
				}
				EmpVO empVO = new EmpVO();
				empVO.setEmpno(empno);
				empVO.setEmpname(empname);
				empVO.setEmptel(emptel);
				empVO.setEmpemail(empemail);
				empVO.setEmpid(empid);
				empVO.setEmppsw(emppsw);
				empVO.setEmpphoto(empphoto);
				empVO.setEmpposition(empposition);
				empVO.setEmphiredate(emphiredate);
				empVO.setEmpchangedate(empchangedate);
				empVO.setEmpchangeman(empchangeman);
				empVO.setEmpstatus(empstatus);
				empVO.setEmpnotes(empnotes);

//				System.out.println(empno);
//				System.out.println(empname);
//				System.out.println(emptel);
//				System.out.println(empemail);
//				System.out.println(empid);
//				System.out.println(emppsw);
//				System.out.println(empposition);
//				System.out.println(emphiredate);
//				System.out.println(empchangedate);
//				System.out.println(empchangeman);
//				System.out.println(empstatus);
//				System.out.println(empnotes);
//				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("empVO", empVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/empmanage.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				EmpService empSvc = new EmpService();
				empVO = empSvc.updateEmp(empno, empname, emptel, empemail, empid, emppsw, empphoto, empposition,
						emphiredate, empchangedate, empchangeman, empstatus, empnotes);

//				System.out.println("修改成功");
				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("empVO", empVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/emp/emppofile.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/emppofile.jsp");
				failureView.forward(req, res);
			}
		}

	}

	private String getRandomPassword() {
		int z;
		StringBuilder sb = new StringBuilder();
		int i;
		for (i = 0; i < 8; i++) {
			z = (int) ((Math.random() * 7) % 3);

			if (z == 1) { // 放數字
				sb.append((int) ((Math.random() * 10) + 48));
			} else if (z == 2) { // 放大寫英文
				sb.append((char) (((Math.random() * 26) + 65)));
			} else {// 放小寫英文
				sb.append(((char) ((Math.random() * 26) + 97)));
			}
		}
		return sb.toString();
	}
}
