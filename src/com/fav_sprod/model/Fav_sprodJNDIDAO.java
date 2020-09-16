package com.fav_sprod.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Fav_sprodJNDIDAO implements Fav_sprodDAO_interface{
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

				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);
		
				pstmt.setString(1, favSprodVO.getMemNo());
				pstmt.setString(2, favSprodVO.getProdNo());

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
		public void delete(String memNo, String prodNo) {
			
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(DELETE);

				pstmt.setString(1, memNo);
				pstmt.setString(2, prodNo);

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
		public Fav_sprodVO findByPrimaryKey(String memNo, String prodNo) {
			
			Fav_sprodVO favSprodVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_STMT);
				
				pstmt.setString(1, memNo);
				pstmt.setString(2, prodNo);

				rs = pstmt.executeQuery();

				while (rs.next()) {

					favSprodVO = new Fav_sprodVO();
					favSprodVO.setMemNo(rs.getString("MEM_NO"));   
					favSprodVO.setProdNo(rs.getString("PROD_NO"));   
					
				}

				
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
				
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

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVO ¤]ºÙ¬° Domain objects
					favSprodVO = new Fav_sprodVO();
					favSprodVO.setMemNo(rs.getString("MEM_NO"));   
					favSprodVO.setProdNo(rs.getString("PROD_NO"));   
					favSprodVO.setFavTime(rs.getDate("FAV_TIME"));
					
					list.add(favSprodVO); // Store the row in the list
				}

				// Handle any driver errors
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
