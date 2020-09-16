package com.sale_project.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Sale_projectJDBCDAO implements Sale_projectDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "EA102G6";
	String passwd = "123456";
	
	private static final String INSERT_STMT = 
			"INSERT INTO SALE_PROJECT(SP_NO, VEN_NO, SP_NAME, SP_QUAN, SP_TOTPRICE, SP_TOTOFF, SP_STARTTIME, SP_ENDTIME, SP_STATUS)VALUES('SP'||LPAD(TO_CHAR(SP_SEQ.NEXTVAL),3,'0'),? , ?, ?, ?, ?, ?, ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT * FROM SALE_PROJECT ORDER BY SP_NO";
		private static final String GET_ONE_STMT = 
			"SELECT * FROM SALE_PROJECT WHERE SP_NO = ?";
		private static final String DELETE = 
			"DELETE FROM SALE_PROJECT WHERE SP_NO = ?";
		private static final String UPDATE = 
			"UPDATE SALE_PROJECT SET VEN_NO=?, SP_NAME=?, SP_QUAN=?, SP_TOTPRICE=?, SP_TOTOFF=?, SP_STARTTIME=?, SP_ENDTIME=?, SP_STATUS=? WHERE SP_NO = ?";
		@Override
		public void insert(Sale_projectVO saleProjectVO) {
			
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(INSERT_STMT);
				
				pstmt.setString(1, saleProjectVO.getVenNo());
				pstmt.setString(2, saleProjectVO.getSpName());
				pstmt.setInt(3, saleProjectVO.getSpQuan());
				pstmt.setInt(4, saleProjectVO.getSpTotPrice());
				pstmt.setInt(5, saleProjectVO.getSpTotOff());
				pstmt.setDate(6, saleProjectVO.getSpStartTime());
				pstmt.setDate(7, saleProjectVO.getSpEndTime());
				pstmt.setInt(8, saleProjectVO.getSpStatus());
				
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
		public void update(Sale_projectVO saleProjectVO) {
			
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(UPDATE);
				
				pstmt.setString(1, saleProjectVO.getVenNo());
				pstmt.setString(2, saleProjectVO.getSpName());
				pstmt.setInt(3, saleProjectVO.getSpQuan());
				pstmt.setInt(4, saleProjectVO.getSpTotPrice());
				pstmt.setInt(5, saleProjectVO.getSpTotOff());
				pstmt.setDate(6, saleProjectVO.getSpStartTime());
				pstmt.setDate(7, saleProjectVO.getSpEndTime());
				pstmt.setInt(8, saleProjectVO.getSpStatus());
				pstmt.setString(9, saleProjectVO.getSpNo());
				
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
		public void delete(String spNo) {
			
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(DELETE);

				pstmt.setString(1, spNo);

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
		public Sale_projectVO findByPrimaryKey(String spNo) {
			
			Sale_projectVO saleProjectVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_ONE_STMT);

				pstmt.setString(1, spNo);
				
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					saleProjectVO = new Sale_projectVO();
					saleProjectVO.setSpNo(rs.getString("SP_NO"));   
					saleProjectVO.setVenNo(rs.getString("VEN_NO"));   
					saleProjectVO.setSpName(rs.getString("SP_NAME"));
					saleProjectVO.setSpQuan(new Integer(rs.getInt("SP_QUAN")));
					saleProjectVO.setSpTotPrice(new Integer(rs.getInt("SP_TOTPRICE")));
					saleProjectVO.setSpTotOff(new Integer(rs.getInt("SP_TOTOFF")));
					saleProjectVO.setSpStartTime(rs.getDate("SP_STARTTIME"));
					saleProjectVO.setSpEndTime(rs.getDate("SP_ENDTIME"));
					saleProjectVO.setSpStatus(new Integer(rs.getInt("SP_STATUS")));			
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
			return saleProjectVO;
		}
		@Override
		public List<Sale_projectVO> getAll() {		
			List<Sale_projectVO> list = new ArrayList<Sale_projectVO>();
			Sale_projectVO saleProjectVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					saleProjectVO = new Sale_projectVO();
					saleProjectVO.setSpNo(rs.getString("SP_NO"));   
					saleProjectVO.setVenNo(rs.getString("VEN_NO"));   
					saleProjectVO.setSpName(rs.getString("SP_NAME"));
					saleProjectVO.setSpQuan(new Integer(rs.getInt("SP_QUAN")));
					saleProjectVO.setSpTotPrice(new Integer(rs.getInt("SP_TOTPRICE")));
					saleProjectVO.setSpTotOff(new Integer(rs.getInt("SP_TOTOFF")));
					saleProjectVO.setSpStartTime(rs.getDate("SP_STARTTIME"));
					saleProjectVO.setSpEndTime(rs.getDate("SP_ENDTIME"));
					saleProjectVO.setSpStatus(new Integer(rs.getInt("SP_STATUS")));			
					
					list.add(saleProjectVO);
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
//				Sale_projectJDBCDAO dao = new Sale_projectJDBCDAO();
		
				// 新增
//				Sale_projectVO saleProjectVO1 = new Sale_projectVO();
//			
//				saleProjectVO1.setVenNo("V003");
//				saleProjectVO1.setSpName("吃大餐囉");
//				saleProjectVO1.setSpQuan(1);
//				saleProjectVO1.setSpTotPrice(1000);
//				saleProjectVO1.setSpTotOff(95);
//				saleProjectVO1.setSpStartTime(java.sql.Date.valueOf("2019-12-22"));
//				saleProjectVO1.setSpEndTime(java.sql.Date.valueOf("2022-12-22"));
//				saleProjectVO1.setSpStatus(0);
//		
//				dao.insert(saleProjectVO1);
//				System.out.println("新增成功");
//				// 修改
//				Sale_projectVO saleProjectVO2 = new Sale_projectVO();
//				
//				saleProjectVO2.setVenNo("V003");
//				saleProjectVO2.setSpName("吃飯囉");;
//				saleProjectVO2.setSpQuan(1);
//				saleProjectVO2.setSpTotPrice(1000);
//				saleProjectVO2.setSpTotOff(95);
//				saleProjectVO2.setSpStartTime(java.sql.Date.valueOf("2019-12-22"));
//				saleProjectVO2.setSpEndTime(java.sql.Date.valueOf("2022-12-22"));
//				saleProjectVO2.setSpStatus(0);
//				saleProjectVO2.setSpNo("SP004");
//				
//				dao.update(saleProjectVO2);
//				System.out.println("修改成功");
//				
//				// 刪除
//				dao.delete("SP002");
//				System.out.println("刪除成功");
//				// 查詢
//				Sale_projectVO saleProjectVO3 = dao.findByPrimaryKey("SP001");
//				System.out.print(saleProjectVO3.getSpNo() + ",");
//				System.out.print(saleProjectVO3.getVenNo() + ",");
//				System.out.print(saleProjectVO3.getSpName() + ",");
//				System.out.print(saleProjectVO3.getSpQuan() + ",");
//				System.out.print(saleProjectVO3.getSpTotPrice() + ",");
//				System.out.print(saleProjectVO3.getSpTotOff() + ",");
//				System.out.print(saleProjectVO3.getSpStartTime() + ",");
//				System.out.print(saleProjectVO3.getSpEndTime() + ",");
//				System.out.print(saleProjectVO3.getSpStatus());
//				System.out.println("---------------------");

				// 查詢
//				List<Sale_projectVO> list = dao.getAll();			
//				for (Sale_projectVO aSaleProject : list) {
//					System.out.print(aSaleProject.getSpNo() + ",");
//					System.out.print(aSaleProject.getVenNo() + ",");
//					System.out.print(aSaleProject.getSpName() + ",");
//					System.out.print(aSaleProject.getSpQuan() + ",");
//					System.out.print(aSaleProject.getSpTotPrice() + ",");
//					System.out.print(aSaleProject.getSpTotOff() + ",");
//					System.out.print(aSaleProject.getSpStartTime() + ",");
//					System.out.print(aSaleProject.getSpEndTime() + ",");
//					System.out.print(aSaleProject.getSpStatus());					
//				}			
//			}
}
