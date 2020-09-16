package com.shop_product.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/GetPhotos")
public class GetPhotos extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/EA102G6");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		Connection con = null;
		
		
		
		req.setCharacterEncoding("UTF-8");
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();

		try {
			con = ds.getConnection();
			Statement stmt = con.createStatement();
			String prodNo = req.getParameter("prodNo").trim();

			ResultSet rs = stmt.executeQuery(
				"SELECT * FROM SHOP_PRODUCT WHERE PROD_NO='"+prodNo+"'");

			if (rs.next()) {

				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("PHOTO"));
				byte[] buf = new byte[4*1024]; // 4K buffer
				int len;

				while ((len = in.read(buf)) != -1) {
					out.write(buf, 0, len);

				}
				in.close();
			} else {

				//res.sendError(HttpServletResponse.SC_NOT_FOUND);
				//空值
				InputStream in = getServletContext().getResourceAsStream("/img/NoImage.jpg");
				byte[] buf = new byte[in.available()];
				in.read(buf);
				out.write(buf);
				in.close();
			}
			rs.close();
			stmt.close();			
			con.close();
		} catch (Exception e) {
			//System.out.println(e);
			//找不到圖
			InputStream in = getServletContext().getResourceAsStream("/img/none2.jpg");
			byte[] buf = new byte[in.available()];
			in.read(buf);
			out.write(buf);
			in.close();
		}
	}
}