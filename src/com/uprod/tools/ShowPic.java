package com.uprod.tools;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.uprod.model.*;


public class ShowPic extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();

		UprodService uprodSvc = new UprodService();
		UprodVO uprodVO = uprodSvc.getOneUprod(req.getParameter("ProdNo"));
		
		try {	
			byte[] picArr = null;

		    picArr = uprodVO.getPhoto();
		
			out.write(picArr);


		} catch (Exception e) {
			e.printStackTrace();
//			InputStream in = getServletContext().getResourceAsStream("/front-end/img/uprod/example.jpg");
//			byte[] buf = new byte[in.available()];
//			in.read(buf);
//			out.write(buf);
//			in.close();
		}
	}

}