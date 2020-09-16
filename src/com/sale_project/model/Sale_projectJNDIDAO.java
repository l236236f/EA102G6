package com.sale_project.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Sale_projectJNDIDAO implements Sale_projectDAO_interface{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/EA102G6");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
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

				con = ds.getConnection();
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

				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);
				
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

				con = ds.getConnection();
				pstmt = con.prepareStatement(DELETE);

				pstmt.setString(1, spNo);

				pstmt.executeUpdate();

				// Handle any driver errors
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

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_STMT);

				pstmt.setString(1, spNo);
				
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					saleProjectVO = new Sale_projectVO();
					saleProjectVO.setSpNo(rs.getString("SP_NO"));   
					saleProjectVO.setVenNo(rs.getString("VEN_NO"));   
					saleProjectVO.setSpName(rs.getString("SP_NAME"));
					saleProjectVO.setSpQuan(new Integer(("SP_QUAN")));
					saleProjectVO.setSpTotPrice(new Integer(("SP_TOTPRICE")));
					saleProjectVO.setSpTotOff(new Integer(("SP_TOTOFF")));
					saleProjectVO.setSpStartTime(rs.getDate("SP_STARTTIME"));
					saleProjectVO.setSpEndTime(rs.getDate("SP_ENDTIME"));
					saleProjectVO.setSpStatus(new Integer(rs.getInt("SP_STATUS")));			
				}
				
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

				con = ds.getConnection();
				
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					// empVO ¤]ºÙ¬° Domain objects
					saleProjectVO = new Sale_projectVO();
					saleProjectVO.setSpNo(rs.getString("SP_NO"));   
					saleProjectVO.setVenNo(rs.getString("VEN_NO"));   
					saleProjectVO.setSpName(rs.getString("SP_NAME"));
					saleProjectVO.setSpQuan(new Integer(("SP_QUAN")));
					saleProjectVO.setSpTotPrice(new Integer(("SP_TOTPRICE")));
					saleProjectVO.setSpTotOff(new Integer(("SP_TOTOFF")));
					saleProjectVO.setSpStartTime(rs.getDate("SP_STARTTIME"));
					saleProjectVO.setSpEndTime(rs.getDate("SP_ENDTIME"));
					saleProjectVO.setSpStatus(new Integer(rs.getInt("SP_STATUS")));			
					list.add(saleProjectVO); // Store the row in the list
				}

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
}
