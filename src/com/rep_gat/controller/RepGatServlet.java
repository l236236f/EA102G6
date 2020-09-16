package com.rep_gat.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.gat.model.GatService;
import com.rep_gat.model.RepGatService;

public class RepGatServlet extends HttpServlet {
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
		RepGatService rgSvc = new RepGatService();
		if ("repgat".equals(action)) {
			try {
				String memNo = req.getParameter("memNo");
				String resNo = req.getParameter("resNo");
				String repCont = req.getParameter("repCont");

				rgSvc.addRep(memNo, resNo, repCont);
				
			} catch (Exception e) {}
		}else if ("rep_approved".equals(action)) {
			try {
				String repNo = req.getParameter("repNo");
				
				rgSvc.update_approved(repNo);
				
				GatService gatSvc = new GatService();
				String str = rgSvc.getOneGat(repNo).getGatNo();
				
				gatSvc.update_report(str);
			} catch (Exception e) {
				System.out.println("Ra:失敗");
			}
		}else if ("rep_notapproved".equals(action)) {
			try {
				String repNo = req.getParameter("repNo");
				
				rgSvc.update_notapproved(repNo);
				System.out.println("Ran:成功");
			} catch (Exception e) {
				System.out.println("Ran:失敗");
			}
		}
		
	}
	
}
