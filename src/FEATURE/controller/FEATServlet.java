package FEATURE.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import FEATURE.model.*;

public class FEATServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 272154666713775929L;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

        if ("insert".equals(action)) { // �Ӧ�addFEAT.jsp���ШD  
			
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/


				String featname = req.getParameter("featname").trim();
				if (featname == null || featname.trim().length() == 0) {
					errorMsgs.put("featname","�\��W�ФŪť�");
				}
				
				
	

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/FEAT/addFEAT.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�s�W���***************************************/
				FEATService featSvc = new FEATService();
				featSvc.addFEAT(featname);
				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				String url = "/FEAT/listAllFEAT.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllFEAT.jsp
				successView.forward(req, res);				
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.put("Exception",e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/FEAT/addFEAT.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
