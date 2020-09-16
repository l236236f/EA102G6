package com.sale_detail.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Sale_detailJDBCDAO implements Sale_detailDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "EA102G6";
	String passwd = "123456";
	
	private static final String INSERT_STMT = 
			"INSERT INTO SALE_DETAIL (SP_NO,PROD_NO) VALUES (?,?)";
		private static final String GET_ALL_STMT = 
			"SELECT * FROM SALE_DETAIL ORDER BY SP_NO";
		private static final String GET_ONE_STMT = 
			"SELECT * FROM SALE_DETAIL WHERE SP_NO = ? AND PROD_NO = ?";
		private static final String DELETE = 
			"DELETE FROM SALE_DETAIL WHERE SP_NO = ? AND PROD_NO = ?";
		private static final String UPDATE = 
			"UPDATE SALE_DETAIL SET SP_NO= ?, PROD_NO= ? WHERE SP_NO = ? AND PROD_NO = ?";
		@Override
		public void insert(Sale_detailVO saleDetailVO) {
			
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {
				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(INSERT_STMT);
			
				pstmt.setString(1, saleDetailVO.getSpNo());
				pstmt.setString(2, saleDetailVO.getProdNo());
			
				pstmt.executeUpdate();
	
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				// Handle any SQL errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
				// Clean up JDBC resources
			} finally {
				if (pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if (con != null) {
					try {
						con.close();
					} catch (Exception e) {
						e.printStackTrace(System.err);
					}
				}
			}
		}
		
		@Override
		public void delete(String spNo, String prodNo) {
			
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(DELETE);

				pstmt.setString(1, spNo);
				pstmt.setString(2, prodNo);

				pstmt.executeUpdate();

				// Handle any driver errors
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				// Handle any SQL errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
				// Clean up JDBC resources
			} finally {
				if (pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if (con != null) {
					try {
						con.close();
					} catch (Exception e) {
						e.printStackTrace(System.err);
					}
				}
			}

			
		}
		@Override
		public Sale_detailVO findByPrimaryKey(String spNo, String prodNo) {
			
			Sale_detailVO saleDetailVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_ONE_STMT);

				pstmt.setString(1, spNo);
				pstmt.setString(2, prodNo);
				
				rs = pstmt.executeQuery();

				while (rs.next()) {
					saleDetailVO = new Sale_detailVO();
					saleDetailVO.setSpNo(rs.getString("SP_NO"));   
					saleDetailVO.setProdNo(rs.getString("PROD_NO"));   		
				}
				
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				// Handle any SQL errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
				// Clean up JDBC resources
			} finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if (pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if (con != null) {
					try {
						con.close();
					} catch (Exception e) {
						e.printStackTrace(System.err);
					}
				}
			}
			return saleDetailVO;
		}
		@Override
		public List<Sale_detailVO> getAll() {
			List<Sale_detailVO> list = new ArrayList<Sale_detailVO>();
			Sale_detailVO saleDetailVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					saleDetailVO = new Sale_detailVO();
					saleDetailVO.setSpNo(rs.getString("SP_NO"));   
					saleDetailVO.setProdNo(rs.getString("PROD_NO"));   	
					list.add(saleDetailVO);
				}
				
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				// Handle any SQL errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
				// Clean up JDBC resources
			} finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if (pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if (con != null) {
					try {
						con.close();
					} catch (Exception e) {
						e.printStackTrace(System.err);
					}
				}
			}
			return list;
		}
		
//		public static void main(String[] args) {
//		
//				Sale_detailJDBCDAO dao = new Sale_detailJDBCDAO();
		
				// 新增
//				Sale_detailVO saleDetailVO1 = new Sale_detailVO();		
//		
//				saleDetailVO1.setSpNo("SP002");
//				saleDetailVO1.setProdNo("S003");
//				
//				dao.insert(saleDetailVO1);
//				System.out.println("新增成功");
//				
//				// 刪除
//				dao.delete("S013");
//				System.out.println("刪除成功");
//				// 查詢
//				Sale_detailVO saleDetailVO3 = dao.findByPrimaryKey("SP002","S003");
//				System.out.print(saleDetailVO3.getSpNo() + ",");
//				System.out.print(saleDetailVO3.getProdNo());
//
//				// 查詢
//				List<Sale_detailVO> list = dao.getAll();
//				for (Sale_detailVO asaleDetail : list) {
//					System.out.print(asaleDetail.getSpNo() + ",");
//					System.out.print(asaleDetail.getProdNo());
//					System.out.println();
//				}
//			}
		
}
