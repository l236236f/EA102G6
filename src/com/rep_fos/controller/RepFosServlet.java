package com.rep_fos.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.foscare.model.FosterService;
import com.rep_fos.model.RepFosService;

public class RepFosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	public void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)	
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
//		檢舉回覆
		RepFosService rfSvc = new RepFosService();
		if ("repfos".equals(action)) {
			try {
				String memNo = req.getParameter("memNo");
				String resNo = req.getParameter("resNo");
				String repCont = req.getParameter("repCont");

				rfSvc.addFosRep(memNo, resNo, repCont);
				
			} catch (Exception e) {}
		}else if ("rep_approved".equals(action)) {
			try {
				String repNo = req.getParameter("repNo");
				
				rfSvc.update_approved(repNo);
				
//				FosterService fosSvc = new FosterService();
//				String str = rfSvc.getOneRep(repNo).getFosNo();
//				
//				fosSvc.update_report(str);
			} catch (Exception e) {
				System.out.println("Ra:失敗");
			}
		}else if ("rep_notapproved".equals(action)) {
			try {
				String repNo = req.getParameter("repNo");
				
				rfSvc.update_notapproved(repNo);
				System.out.println("Ran:成功");
			} catch (Exception e) {
				System.out.println("Ran:失敗");
			}
		}
		
	}
	
}
