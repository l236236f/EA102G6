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
//		System.out.println("�i�JSERV");
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
					iderror = "ID�ФŬ���";
				} else if (empVO == null) {
					iderror = "ID���s�b";
				}
				if (iderror != null) {
					req.setAttribute("iderror", iderror);
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/loginEmp.jsp");
					failureView.forward(req, res);
					return;

				}
				if (pswstr == null || pswstr.trim().length() == 0) {
					pswerror = "�K�X�ФŬ���";
				}
				if (!empVO.getEmppsw().equals(pswstr)) {
					pswerror = "�K�X�����T";
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

		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				String str = req.getParameter("empno");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J���u�s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/empmanage.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				String empno = null;
				try {
					empno = new String(str);
				} catch (Exception e) {
					errorMsgs.add("���u�s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/empmanage.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 2.�}�l�d�߸�� *****************************************/
				EmpService empSvc = new EmpService();
				EmpVO empVO = empSvc.getOneEmp(empno);
				if (empVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) *************/
				req.setAttribute("empVO", empVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/back-end/emp/empmanage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/empmanage.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� ****************************************/
				String empno = new String(req.getParameter("empno"));

				/*************************** 2.�}�l�d�߸�� ****************************************/
				EmpService empSvc = new EmpService();
				EmpVO empVO = empSvc.getOneEmp(empno);

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) ************/
				req.setAttribute("empVO", empVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/back-end/emp/empmanage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/empmanage.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD
//			System.out.println("�i�JUPDATE");
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/

				String empno = req.getParameter("empno").trim();
				String empname = req.getParameter("empname").trim();

				if (empname == null || empname.trim().length() == 0) {
					errorMsgs.add("���u�m�W: �ФŪť�");
				}
				String emptel = req.getParameter("emptel").trim();
				String empemail = req.getParameter("empemail").trim();
				String empid = req.getParameter("empid").trim();
				if (empid == null || empid.trim().length() == 0) {
					errorMsgs.add("ID�ФŪť�");
				}
				String emppsw = req.getParameter("emppsw").trim();
				if (empid == null || empid.trim().length() == 0) {
					errorMsgs.add("�K�X�ФŪť�");
				}
				String empposition = req.getParameter("empposition").trim();
				if (empposition == null || empposition.trim().length() == 0) {
					errorMsgs.add("¾��ФŪť�");
				}

				java.sql.Timestamp emphiredate = null;
				try {
					emphiredate = java.sql.Timestamp.valueOf(req.getParameter("emphiredate") + " 00:00:00");
				} catch (IllegalArgumentException e) {
					emphiredate = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("�п�J�ɶ�!");
				}

				Timestamp empchangedate = new Timestamp(System.currentTimeMillis());
				String empchangeman = req.getParameter("empchangeman");
				String empstatus = req.getParameter("empstatus");
				if (empstatus == null || empstatus.trim().length() == 0) {
					errorMsgs.add("���A�ФŪť�");
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
					req.setAttribute("empVO", empVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/empmanage.jsp");
					failureView.forward(req, res);
					return; // �{�����_
				}

				/*************************** 2.�}�l�ק��� *****************************************/
				EmpService empSvc = new EmpService();
				empVO = empSvc.updateEmp(empno, empname, emptel, empemail, empid, emppsw, empphoto, empposition,
						emphiredate, empchangedate, empchangeman, empstatus, empnotes);
				/*************************** �ק��v�� *************/

				if ("E0".equals(empstatus)) {
					AUTHOService authoSvc = new AUTHOService();
					authoSvc.deleteAUTHOByEmpno(empno);
				}

				/*************************** 3.�ק粒��,�ǳ����(Send the Success view) *************/
				req.setAttribute("empVO", empVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
				String url = "/back-end/emp/empmanage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/empmanage.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // �Ӧ�addEmp.jsp���ШD
//System.out.println("�i�JINSERT");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/

				String empname = req.getParameter("empname");

				if (empname == null || empname.trim().length() == 0) {
					errorMsgs.add("���u�m�W: �ФŪť�");
				}

				String emptel = req.getParameter("emptel").trim();
				String empemail = req.getParameter("empemail").trim();
				String empid = req.getParameter("empid").trim();
				if (empid == null || empid.trim().length() == 0) {
					errorMsgs.add("ID�ФŪť�");
				}
				String emppsw = getRandomPassword();
				String empposition = req.getParameter("empposition").trim();
				if (empposition == null || empposition.trim().length() == 0) {
					errorMsgs.add("¾��ФŪť�");
				}

				java.sql.Timestamp emphiredate = null;
				try {
					emphiredate = java.sql.Timestamp.valueOf(req.getParameter("emphiredate") + " 00:00:00");
				} catch (IllegalArgumentException e) {
					emphiredate = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("�п�J�ɶ�!");
				}

				Timestamp empchangedate = new Timestamp(System.currentTimeMillis());

				String empchangeman = req.getParameter("empchangeman");
				String empstatus = req.getParameter("empstatus");
				if (empstatus == null || empstatus.trim().length() == 0) {
					errorMsgs.add("���A�ФŪť�");
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
					req.setAttribute("empVO", empVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/addEmp.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.�}�l�s�W��� ***************************************/
				EmpService empSvc = new EmpService();

				empVO = empSvc.addEmp(empname, emptel, empemail, empid, emppsw, empphoto, empposition, emphiredate,
						empchangedate, empchangeman, empstatus, empnotes);

				/*************************** EMAIL ***************************************/

				String to = empemail;
				String subject = "�H�H�i�i��x���u�K�X�q��";
				String ch_name = empid;
				String passRandom = emppsw;
				String messageText = "�A�n! " + ch_name + " ���԰O���K�X: " + passRandom + "\n" + " (�w�g�ҥ�)";

				MailService mailService = new MailService();
				mailService.sendMail(to, subject, messageText);

				/*************************** 3.�s�W����,�ǳ����(Send the Success view) ***********/
				String url = "/back-end/emp/empmanage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/empmanage.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) { // �Ӧ�listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� ***************************************/
				String empno = new String(req.getParameter("empno"));

				/*************************** 2.�}�l�R����� ***************************************/
				EmpService empSvc = new EmpService();
				empSvc.deleteEmp(empno);

				/*************************** 3.�R������,�ǳ����(Send the Success view) ***********/
				String url = "/back-end/emp/listAllEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:" + e.getMessage());
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

		if ("updateByEmp".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD
//			System.out.println("�i�JUPDATE");
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/

				String empno = req.getParameter("empno").trim();
				EmpVO empVO1 = new EmpService().getOneEmp(empno);

				String empname = empVO1.getEmpname();

				if (empname == null || empname.trim().length() == 0) {
					errorMsgs.add("���u�m�W: �ФŪť�");
				}
				String emptel = req.getParameter("emptel").trim();
				String empemail = req.getParameter("empemail").trim();
				String empid = req.getParameter("empid").trim();
				if (empid == null || empid.trim().length() == 0) {
					errorMsgs.add("ID�ФŪť�");
				}
				String emppsw = req.getParameter("emppsw").trim();
				if (empid == null || empid.trim().length() == 0) {
					errorMsgs.add("�K�X�ФŪť�");
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
					req.setAttribute("empVO", empVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/empmanage.jsp");
					failureView.forward(req, res);
					return; // �{�����_
				}

				/*************************** 2.�}�l�ק��� *****************************************/
				EmpService empSvc = new EmpService();
				empVO = empSvc.updateEmp(empno, empname, emptel, empemail, empid, emppsw, empphoto, empposition,
						emphiredate, empchangedate, empchangeman, empstatus, empnotes);

//				System.out.println("�ק令�\");
				/*************************** 3.�ק粒��,�ǳ����(Send the Success view) *************/
				req.setAttribute("empVO", empVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
				String url = "/back-end/emp/emppofile.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:" + e.getMessage());
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

			if (z == 1) { // ��Ʀr
				sb.append((int) ((Math.random() * 10) + 48));
			} else if (z == 2) { // ��j�g�^��
				sb.append((char) (((Math.random() * 26) + 65)));
			} else {// ��p�g�^��
				sb.append(((char) ((Math.random() * 26) + 97)));
			}
		}
		return sb.toString();
	}
}
