package com.tools;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;

import com.fosmon.model.FosmService;
import com.fosmon.model.FosmVO;


@WebServlet("/JSONfosm")
public class JSONfosm extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//從session取出所有保母的物件
		HttpSession se = req.getSession();
		List<FosmVO> list = (List<FosmVO>)se.getAttribute("AllfosMon");
		
		//接收前端傳來的3個參數
		String type = req.getParameter("type");
		String size = req.getParameter("size");
		String nrun = req.getParameter("nrun");
		
		//用參數過濾保母集合放到list2裡
		List<FosmVO> list2 =  list.stream()
				.filter(f -> f.getFosmPetType().indexOf(type)!=-1 || type.equals("請選擇"))
				.filter(f -> f.getFosmPetSize().indexOf(size)!=-1 || size.equals("請選擇"))
				.filter(f -> nrun.equals("請選擇") || nrun.equals("否") || (nrun.equals("是")&& f.getFosmnrun().equals("是")))
				.collect(Collectors.toList());
				
		res.setContentType("text/html; charset=BIG5");
		PrintWriter out =res.getWriter();
		
		//將集合放到JSONArray裡送回前端
		JSONArray jsonArray = new JSONArray();
		jsonArray.put(list2);
		out.print(jsonArray.toString());

	}

}
