package com.gat_detail.controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gat.model.GatService;
import com.gat.model.GatVO;
import com.gat_detail.model.GatDetailService;
import com.gat_detail.model.GatDetailVO;
import com.gat_eva.model.GatEvaService;
import com.gat_eva.model.GatEvaVO;
import com.gat_res.model.GatResService;
import com.gat_res.model.GatResVO;
import com.mem.model.MemService;

import idv.david.websocketchat.controller.Robot;

/**
 * Servlet implementation class GatDetailServlet
 */
@WebServlet("/gat/gatDetailServlet")
public class GatDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("gat_join".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			String memNo = req.getParameter("memNo");
			String gatNo = req.getParameter("gatNo");
			System.out.println("join_memNo: " + memNo + ", join_gatNo:" + gatNo);

			try {

				GatDetailVO gatDetailVO = new GatDetailVO();
				gatDetailVO.setGatNo(gatNo);
				gatDetailVO.setMemNo(memNo);
				GatDetailService gatDetailSvc = new GatDetailService();
				gatDetailVO = gatDetailSvc.addDetail(gatNo, memNo);
				
				//WS robot.java
//				GatService gatSvc = new GatService();
//				String gatName = gatSvc.getOneGat(gatNo).getGatName(); //����ѥ[�̪��W�r
//				String gatMemNo = gatSvc.getOneGat(gatNo).getMemNo(); //����ΥD���s��
//				
//				Set<String> gatMemNoSet = new HashSet<String>();
//				gatMemNoSet.add(gatMemNo);	//��ΥD�s����iSet
//				
//				MemService memSvc = new MemService();
//				String memName = memSvc.getOneMem(memNo).getMemName(); //����ѥ[�̪��W�r
//				
//				String info = "�w��s�B�͡G"  + memName +"�ѥ[�u"+ gatName + "�v";//�����Hinfo���Ѽ�
//				Robot.chatRobot(info, gatMemNoSet);
				
				System.out.println("join success");
			} catch (Exception e) {
				errorMsgs.add("�X���աI�]��CATCH�F�G" + e.getMessage());
			}
		}
		if ("updateStatus".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String gatStatus = req.getParameter("gatStatus");
				String gatNo = req.getParameter("gatNo");
				String memNo = req.getParameter("memNo");
				System.out.println("gatStatus: " + gatStatus + " ,gatNo: " + gatNo + " ,memNo: " + memNo);
				GatService gatSvc = new GatService();
				gatSvc.updateStatus(gatStatus, gatNo);
				System.out.println("���A�ק令�\");
			} catch (Exception e) {
				errorMsgs.add("�ק窱�A����: " + e.getMessage());
				errorMsgs.add(e.getMessage());
			}
		}
		if ("gat_cancel".equals(action) || "mem_cancel".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			String memNo = req.getParameter("memNo");
			String gatNo = req.getParameter("gatNo");
			
			System.out.println("rmv_memNo: " + memNo + ", rmv_gatNo:" + gatNo);

			try {

				GatDetailVO gatDetailVO = new GatDetailVO();
				gatDetailVO.setGatNo(gatNo);
				gatDetailVO.setMemNo(memNo);
				GatDetailService gatDetailSvc = new GatDetailService();
				gatDetailVO = gatDetailSvc.deleteDetail(gatNo, memNo);
				
				//WS robot.java
//				GatService gatSvc = new GatService();
//				String gatName = gatSvc.getOneGat(gatNo).getGatName(); //����ѥ[�̪��W�r
//				String gatMemNo = gatSvc.getOneGat(gatNo).getMemNo(); //����ΥD���s��
//				
//				Set<String> gatMemNoSet = new HashSet<String>();
//				gatMemNoSet.add(gatMemNo);	//��ΥD�s����iSet
//				
//				MemService memSvc = new MemService();
//				String memName = memSvc.getOneMem(memNo).getMemName(); //����ѥ[�̪��W�r
//				
//				String info = memName +"�M�w���ѥ[�u"+ gatName + "�v�FQQ";//�����Hinfo���Ѽ�
//				Robot.chatRobot(info, gatMemNoSet);
				
				System.out.println("remove success");

			} catch (Exception e) {
				System.out.println("�X���աI�]��CATCH�F�G" + e.getMessage());
			}
		}
		if ("res_add".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			String gatNo = req.getParameter("gatNo");
			String memNo = req.getParameter("memNo");
			String resCont = req.getParameter("resCont");
			System.out.println("res_memNo: " + memNo + ", res_gatNo:" + resCont);

			try {
				GatResVO gatResVO = new GatResVO();
				gatResVO.setMemNo(memNo);
				gatResVO.setResCont(resCont);
				gatResVO.setGatNo(gatNo);
				
				GatResService gatResSvc = new GatResService();
				gatResSvc.addRes(gatNo, memNo, resCont);
				
				//WS robot.java
//				GatService gatSvc = new GatService();
//				String gatName = gatSvc.getOneGat(gatNo).getGatName(); //����ѥ[�̪��W�r
//				String gatMemNo = gatSvc.getOneGat(gatNo).getMemNo(); //����ΥD���s��
//				
//				Set<String> gatMemNoSet = new HashSet<String>();
//				gatMemNoSet.add(gatMemNo);	//��ΥD�s����iSet
//				
//				MemService memSvc = new MemService();
//				String memName = memSvc.getOneMem(memNo).getMemName(); //����ѥ[�̪��W�r
//				
//				String info = memName +"�b�A�����Ρu"+ gatName + "�v�d�F�@�h�T���I";//�����Hinfo���Ѽ�
//				Robot.chatRobot(info, gatMemNoSet);
				
				System.out.println("resadd success");

			} catch (Exception e) {
				errorMsgs.add("�X���աI�]��CATCH�F�G" + e.getMessage());
			}
		}
		if ("res_update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			String resNo = req.getParameter("resNo");
			String replyCont = req.getParameter("replyCont");
			System.out.println("resNo: " + resNo + ", replyCont:" + replyCont);

			try {
				GatResVO gatResVO = new GatResVO();
				gatResVO.setResNo(resNo);
				gatResVO.setResReply(replyCont);
				
				GatResService gatResSvc = new GatResService();
				gatResSvc.replyRes(resNo, replyCont);
				
				System.out.println("updateRes success");

			} catch (Exception e) {
				errorMsgs.add("�X���աI�]��CATCH�F�G" + e.getMessage());
			}
		}
		if ("eva_add".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			String gatNo = req.getParameter("gatNo");
			String memNo = req.getParameter("memNo");
			Double gatEva = Double.parseDouble(req.getParameter("gatEva"));
			System.out.println("memNo: " + memNo + ", gatEva:" + gatEva);

			try {
				GatEvaVO gatEvaVO = new GatEvaVO();
				gatEvaVO.setGatNo(gatNo);
				gatEvaVO.setMemNo(memNo);
				gatEvaVO.setGatEva(gatEva);
				
				GatEvaService gatEvaSvc = new GatEvaService();
				gatEvaSvc.addEva(memNo, gatNo, gatEva);
				
				//WS robot.java
//				GatService gatSvc = new GatService();
//				String gatName = gatSvc.getOneGat(gatNo).getGatName(); //����ѥ[�̪��W�r
//				String gatMemNo = gatSvc.getOneGat(gatNo).getMemNo(); //����ΥD���s��
//				
//				Set<String> gatMemNoSet = new HashSet<String>();
//				gatMemNoSet.add(gatMemNo);	//��ΥD�s����iSet
//				
//				MemService memSvc = new MemService();
//				String memName = memSvc.getOneMem(memNo).getMemName(); //����ѥ[�̪��W�r
//				
//				String info = memName +"�b�A�����Ρu"+ gatName + "�v�d�F�@�h�����I";//�����Hinfo���Ѽ�
//				Robot.chatRobot(info, gatMemNoSet);
				
				System.out.println("evaadd success");

			} catch (Exception e) {
				errorMsgs.add("�X���աI�]��CATCH�F�G" + e.getMessage());
			}
		}
	}

}
