package com.fav_sprod.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shop_product.model.Shop_productVO;

public class Fav_sprodJDBCDAO implements Fav_sprodDAO_interface{
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "EA102G6";
	String passwd = "123456";
	
	private static final String INSERT_STMT = 
			"INSERT INTO FAV_SPROD (MEM_NO,PROD_NO,FAV_TIME) VALUES (?,?,SYSDATE)";
		private static final String GET_ALL_STMT = 
			"SELECT * FROM FAV_SPROD ORDER BY FAV_TIME";
		private static final String GET_ONE_STMT = 
			"SELECT * FROM FAV_SPROD WHERE MEM_NO = ? AND PROD_NO = ?";
		private static final String DELETE = 
			"DELETE FROM FAV_SPROD WHERE MEM_NO = ? AND PROD_NO = ?";
		
		@Override
		public void insert(Fav_sprodVO favSprodVO) {
			
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(INSERT_STMT);

			
				pstmt.setString(1, favSprodVO.getMemNo());
				pstmt.setString(2, favSprodVO.getProdNo());

				pstmt.executeUpdate();

			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
			
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
		public void delete(String memNo, String prodNo) {
			
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(DELETE);

				pstmt.setString(1, memNo);
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
		public Fav_sprodVO findByPrimaryKey(String memNo, String prodNo) {
			
			Fav_sprodVO favSprodVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_ONE_STMT);

				pstmt.setString(1, memNo);
				pstmt.setString(2, prodNo);

				rs = pstmt.executeQuery();

				while (rs.next()) {
								
					// empVo 也稱為 Domain objects
					favSprodVO = new Fav_sprodVO();
					favSprodVO.setMemNo(rs.getString("MEM_NO"));   
					favSprodVO.setProdNo(rs.getString("PROD_NO"));   
					
				}

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
			return favSprodVO;
		}
		@Override
		public List<Fav_sprodVO> getAll() {
			
			List<Fav_sprodVO> list = new ArrayList<Fav_sprodVO>();
			Fav_sprodVO favSprodVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVO 也稱為 Domain objects
					favSprodVO = new Fav_sprodVO();
					favSprodVO.setMemNo(rs.getString("MEM_NO"));   
					favSprodVO.setProdNo(rs.getString("PROD_NO"));   
					favSprodVO.setFavTime(rs.getDate("FAV_TIME"));
					
					list.add(favSprodVO); // Store the row in the list
				}

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

//		public static void main(String args[]) {
//			Fav_sprodJDBCDAO dao = new Fav_sprodJDBCDAO();
//			
////				新增
//				Fav_sprodVO favSprodVO1 = new Fav_sprodVO();
//										
//				favSprodVO1.setMemNo("M004");
//				favSprodVO1.setProdNo("S002");
//				
//				dao.insert(favSprodVO1);
//				System.out.println("新增成功");
//			
////				刪除
//				dao.delete("M003", "S003");
//				System.out.println("刪除成功");
////				單項查詢
//				Fav_sprodVO favSprodVO2 = dao.findByPrimaryKey("M001", "S001");
//				System.out.print(favSprodVO2.getMemNo() + ",");
//				System.out.print(favSprodVO2.getProdNo() + ",");
//				System.out.print(favSprodVO2.getFavTime());
//				
//				System.out.println("-------------以下為List<Fav_sprodVO>-------");
//
////				查詢
//				List<Fav_sprodVO> list = dao.getAll();
//				for (Fav_sprodVO afavSprodVO : list) {
//					System.out.print(afavSprodVO.getMemNo() + ",");
//					System.out.print(afavSprodVO.getProdNo() + ",");
//					System.out.print(afavSprodVO.getFavTime());
//				}
//		}
		
}
