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
		//�qsession���X�Ҧ��O��������
		HttpSession se = req.getSession();
		List<FosmVO> list = (List<FosmVO>)se.getAttribute("AllfosMon");
		
		//�����e�ݶǨӪ�3�ӰѼ�
		String type = req.getParameter("type");
		String size = req.getParameter("size");
		String nrun = req.getParameter("nrun");
		
		//�ΰѼƹL�o�O�����X���list2��
		List<FosmVO> list2 =  list.stream()
				.filter(f -> f.getFosmPetType().indexOf(type)!=-1 || type.equals("�п��"))
				.filter(f -> f.getFosmPetSize().indexOf(size)!=-1 || size.equals("�п��"))
				.filter(f -> nrun.equals("�п��") || nrun.equals("�_") || (nrun.equals("�O")&& f.getFosmnrun().equals("�O")))
				.collect(Collectors.toList());
				
		res.setContentType("text/html; charset=BIG5");
		PrintWriter out =res.getWriter();
		
		//�N���X���JSONArray�̰e�^�e��
		JSONArray jsonArray = new JSONArray();
		jsonArray.put(list2);
		out.print(jsonArray.toString());

	}

}
