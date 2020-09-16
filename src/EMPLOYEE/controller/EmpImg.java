package EMPLOYEE.controller;

import java.io.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.DataSource;

public class EmpImg extends HttpServlet{
 
 /**
	 * 
	 */
	private static final long serialVersionUID = -3257941710181635584L;
private static final String GETONEPIC ="SELECT EMP_PHOTO FROM EMPLOYEE WHERE EMP_NO=?";
 Connection con;
 public void doGet(HttpServletRequest req, HttpServletResponse res)
   throws ServletException, IOException {

  res.setContentType("image/gif");
  ServletOutputStream out = res.getOutputStream();
  
  try {
   PreparedStatement pstmt = con.prepareStatement(GETONEPIC);
   String empno = req.getParameter("empno").trim();
   pstmt.setString(1, empno);
   ResultSet rs = pstmt.executeQuery();
   
   if (rs.next()) {
    BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("EMP_PHOTO"));
    byte[] buf = new byte[4 * 1024]; // 4K buffer
    int len;
    while ((len = in.read(buf)) != -1) {
     out.write(buf, 0, len);
    }
    in.close();
   } else {
//    res.sendError(HttpServletResponse.SC_NOT_FOUND);
    InputStream in = getServletContext().getResourceAsStream("/back-end/NoData/none2.jpg");
    byte[] b = new byte[in.available()];
    in.read(b);
    out.write(b);
    in.close();
    
   }
   rs.close();
   pstmt.close();
  } catch (Exception e) {
//   System.out.println(e);
   InputStream in = getServletContext().getResourceAsStream("/back-end/NoData/null2.jpg");
   byte[] b = new byte[in.available()];
   in.read(b);
   out.write(b);
   in.close();
  }
 }

 public void init() throws ServletException {
  try {
   Context ctx = new javax.naming.InitialContext();
   DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/EA102G6");
   con = ds.getConnection();
  } catch (NamingException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  } catch (SQLException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  }
 }

 public void destroy() {
  try {
   if (con != null) con.close();
  } catch (SQLException e) {
   System.out.println(e);
  }
 }
}