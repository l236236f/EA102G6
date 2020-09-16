package com.fosm.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.fosmon.model.FosmService;
import com.fosmon.model.FosmVO;
import com.fosmphoto.model.FosmPhoService;

import com.tools.PhotoToByte;

@MultipartConfig
@WebServlet("/FosmServlet")
public class FosmServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				String fosmNo = req.getParameter("fosmNo");
				String fosmContain = req.getParameter("fosmContain");
				
				String[] type = req.getParameterValues("petType");
				StringBuilder fosmPetType = new StringBuilder();
				if(type==null) {
					errorMsgs.add("�п�ܥi�H�������d������");
				}else {
					for(int i = 0;i<type.length;i++) {
						if(fosmPetType.length()!=0) {
							fosmPetType.append(",");
							fosmPetType.append(type[i]);
						}else {
							fosmPetType.append(type[i]);
						}		
					}
				}	
				req.setAttribute("type", type);
				String[] size = req.getParameterValues("petSize");
				StringBuilder fosmPetSize = new StringBuilder();
				if(size==null) {
					errorMsgs.add("�п�ܥi�H�������d���髬");
				}else {
					for(int i = 0;i<size.length;i++) {
						if(fosmPetSize.length()!=0) {
							fosmPetSize.append(",");
							fosmPetSize.append(size[i]);
						}else {
							fosmPetSize.append(size[i]);
						}		
					}
				}
				req.setAttribute("size", size);
				
				String fosmnrun = req.getParameter("petnrun");
				if(fosmnrun==null) {
					errorMsgs.add("�п�ܬO�_�i�H�N���d��");
				}
				
				FosmVO fosmVO = new FosmVO();
				fosmVO.setFosmNo(fosmNo);
				fosmVO.setFosmContain(fosmContain);
				fosmVO.setFosmPetSize(fosmPetSize.toString());
				fosmVO.setFosmPetType(fosmPetType.toString());
				fosmVO.setFosmnrun(fosmnrun);
				HttpSession se = req.getSession();
				
				if(!errorMsgs.isEmpty()) {
					se.setAttribute("LoginFosm", fosmVO);
					RequestDispatcher failureView = 
							req.getRequestDispatcher("/front-end/foscare/fosmHome.jsp");
					failureView.forward(req, res);
					return;
				}
				
				//���~���ҧ�---�}�l�W��
				FosmService fmSvc = new FosmService();
				fmSvc.updateFosMon(fosmVO);
				
				FosmPhoService fmpSvc = new FosmPhoService();
				PhotoToByte ptob = new PhotoToByte();
				String imageReg = "^(image/)[a-z]*$";
				Collection<Part> parts = req.getParts();
				for(Part part: parts) {
					String photype = part.getContentType();
					byte[] photo = ptob.photoToByte(part);
					if(photo==null) {
						continue;
					}else if(!photype.matches(imageReg)) {
						errorMsgs.add("�ФW�ǹϤ���!");
						break;
					}else {
						fmpSvc.addPho(fosmNo, photo);
					}
				}

				se.setAttribute("isSuccess", "Yes");
				se.setAttribute("successVal", "�ק令�\!");
				String url = req.getContextPath()+"/front-end/foscare/fosmHome.jsp";
				res.sendRedirect(url);
//				RequestDispatcher successView = req.getRequestDispatcher("/front-end/foscare/fosmHome.jsp"); // �s�W���\�����listAllEmp.jsp
//				successView.forward(req, res);
				
				
	
			}	catch(Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = 
						req.getRequestDispatcher("/front-end/foscare/fosmHome.jsp");
				failureView.forward(req, res);	
			}
		}
		if("delete".equals(action)) {
			FosmPhoService fmPho = new FosmPhoService();
			String photoNo = req.getParameter("photoNo");
			fmPho.delPho(photoNo);
			
		}
	}

}
