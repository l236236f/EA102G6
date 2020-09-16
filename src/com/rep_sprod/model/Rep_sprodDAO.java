package com.rep_sprod.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import com.rep_sprod.model.Rep_sprodVO;

public class Rep_sprodDAO implements Rep_sprodDAO_interface{

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
			"INSERT INTO REP_SPROD (REP_NO,MEM_NO,PROD_NO,REP_TIME,REP_REASON,REP_STATUS) VALUES (('SR'||LPAD(TO_CHAR(SR_SEQ.NEXTVAL),3,'0')), ?, ?, SYSDATE, ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT * FROM REP_SPROD ORDER BY REP_NO";
		private static final String GET_ONE_STMT = 
			"SELECT * FROM REP_SPROD WHERE REP_NO = ?";
		private static final String DELETE = 
			"DELETE FROM REP_SPROD WHERE REP_NO = ?";
		private static final String UPDATE = 
			"UPDATE REP_SPROD SET REP_STATUS=? WHERE REP_NO = ?";
		@Override
		public void insert(Rep_sprodVO repSprodVO) {
			
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);
				
				pstmt.setString(1, repSprodVO.getMemNo());
				pstmt.setString(2, repSprodVO.getProdNo());
				pstmt.setString(3, repSprodVO.getRepReason());
				pstmt.setInt(4, repSprodVO.getRepStatus());
				
				pstmt.executeUpdate();
				
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
		public void update(Rep_sprodVO repSprodVO) {
			
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE);

				pstmt.setInt(1, repSprodVO.getRepStatus());			
				pstmt.setString(2, repSprodVO.getRepNo());
				

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
		public void delete(String repNo) {
			
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(DELETE);

				pstmt.setString(1, repNo);

				pstmt.executeUpdate();
				
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
		public Rep_sprodVO findByPrimaryKey(String repNo) {
			
			Rep_sprodVO repSprodVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_STMT);

				pstmt.setString(1, repNo);			
				rs = pstmt.executeQuery();
				while (rs.next()) {
					repSprodVO = new Rep_sprodVO();
					repSprodVO.setRepNo(rs.getString("REP_NO"));   
					repSprodVO.setMemNo(rs.getString("MEM_NO"));   
					repSprodVO.setProdNo(rs.getString("PROD_NO"));
					repSprodVO.setRepTime(rs.getDate("REP_TIME"));
					repSprodVO.setRepReason(rs.getString("REP_REASON"));
					repSprodVO.setRepStatus(rs.getInt("REP_STATUS"));
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
			return repSprodVO;
		}
		
		@Override
		public List<Rep_sprodVO> getAll() {
			
			List<Rep_sprodVO> list = new ArrayList<Rep_sprodVO>();
			Rep_sprodVO repSprodVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {

				con = ds.getConnection();
				
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					
					repSprodVO = new Rep_sprodVO();
					repSprodVO.setRepNo(rs.getString("REP_NO"));   
					repSprodVO.setMemNo(rs.getString("MEM_NO"));   
					repSprodVO.setProdNo(rs.getString("PROD_NO"));
					repSprodVO.setRepTime(rs.getDate("REP_TIME"));
					repSprodVO.setRepReason(rs.getString("REP_REASON"));
					repSprodVO.setRepStatus(rs.getInt("REP_STATUS"));	
					list.add(repSprodVO);
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
			return list;
			
		}

		
		
	
	
}
