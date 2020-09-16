package com.pocker.controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginPocker
 */
@WebServlet("/LoginPocker")
public class LoginPocker extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		RequestDispatcher failureView = 
				req.getRequestDispatcher("/front-end/selfProject/gameRoom.jsp");
		failureView.forward(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		ServletContext ctx =  getServletContext();
		HttpSession se = req.getSession();
		Set<String> loginSet = (Set<String>)ctx.getAttribute("loginSet");
		if(loginSet == null) {
			loginSet = new HashSet<String>();
			ctx.setAttribute("loginSet", loginSet);
		}
		String iriName = req.getParameter("username");
		System.out.println(iriName);
		if(loginSet.contains(iriName)) {
			req.setAttribute("isSuccess", "no");
			req.setAttribute("val", "名稱重複，請重新輸入");
			RequestDispatcher failureView = 
					req.getRequestDispatcher("/front-end/selfProject/irikuji.jsp");
			failureView.forward(req, res);
		}else {
			loginSet.add(iriName);
			se.setAttribute("username", iriName);
			String url = req.getContextPath()+"/front-end/selfProject/bridge.jsp";
			res.sendRedirect(url);
		}	
	}
}
