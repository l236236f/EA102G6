package AUTHORITY.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import AUTHORITY.model.*;

public class AUTHOServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8320059241791561719L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("change".equals(action)) { // �Ӧ�addAUTHO.jsp���ШD

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z *************************/

				String empno = req.getParameter("empno");

				String feat0001 = req.getParameter("FEAT0001");
				String feat0002 = req.getParameter("FEAT0002");
				String feat0003 = req.getParameter("FEAT0003");
				String feat0004 = req.getParameter("FEAT0004");
				String feat0005 = req.getParameter("FEAT0005");
				String feat0006 = req.getParameter("FEAT0006");
				/***************************
				 * 2.�}�l�R�������v�� �M��s�W���
				 ***************************************/

				AUTHOService authoSecA = new AUTHOService();

				authoSecA.deleteAUTHOByEmpno(empno);

				if ("true".equals(feat0001)) {
					authoSecA.addAUTHO("FEAT0001", empno);
				}

				if ("true".equals(feat0002)) {
					authoSecA.addAUTHO("FEAT0002", empno);
				}
				if ("true".equals(feat0003)) {
					authoSecA.addAUTHO("FEAT0003", empno);
				}
				if ("true".equals(feat0004)) {
					authoSecA.addAUTHO("FEAT0004", empno);
				}
				if ("true".equals(feat0005)) {
					authoSecA.addAUTHO("FEAT0005", empno);
				}
				if ("true".equals(feat0006)) {
					authoSecA.addAUTHO("FEAT0006", empno);
				}
				/*************************** 3.�s�W����,�ǳ����(Send the Success view) ***********/
				String url = "/back-end/emp/authoritymanage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllAUTHO.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.put("Exception", e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/authoritymanage.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
