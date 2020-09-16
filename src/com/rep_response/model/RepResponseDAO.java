package com.rep_response.model;

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

public class RepResponseDAO implements RepResponseDAO_interface{
	
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
		"INSERT INTO REP_RESPONSE (REP_NO, MEM_NO, RES_NO, REP_REASON) "
			          	+ "VALUES ('RRE'||LPAD(TO_CHAR(REP_RESPONSE_SEQ.NEXTVAL),3 ,'0'), ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT * FROM REP_RESPONSE ORDER BY REP_NO";
	private static final String GET_ONE_STMT = 
		"SELECT * FROM REP_RESPONSE WHERE REP_NO=?";
	private static final String DELETE = 
		"DELETE FROM REP_RESPONSE WHERE REP_NO=?";
	private static final String UPDATE = 
		"UPDATE REP_RESPONSE SET MEM_NO=?, RES_NO=?, REP_TIME=SYSDATE, REP_REASON=?, REP_STATUS=? WHERE REP_NO=?";
	
	private static final String UPDATE_APPROVED =
			"UPDATE REP_RESPONSE SET REP_STATUS='R1' WHERE REP_NO=?";
	private static final String UPDATE_NOTAPPROVED =
			"UPDATE REP_RESPONSE SET REP_STATUS='R2' WHERE REP_NO=?";

	@Override
	public void insert(RepResponseVO repResponseVO) {
		
		Connection  con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, repResponseVO.getMemno());
			pstmt.setString(2, repResponseVO.getResno());
			pstmt.setString(3, repResponseVO.getRepreason());
			
			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void update(RepResponseVO repResponseVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, repResponseVO.getMemno());
			pstmt.setString(2, repResponseVO.getResno());
			pstmt.setString(3, repResponseVO.getRepreason());
			pstmt.setString(4, repResponseVO.getRepstatus());
			pstmt.setString(5, repResponseVO.getRepno());
			
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
	public void delete(String repno) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, repno);

			pstmt.executeUpdate();
			con.commit();
			con.setAutoCommit(true);
			
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
	public RepResponseVO findByPrimaryKey(String repno) {

		RepResponseVO repResponseVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, repno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				repResponseVO = new RepResponseVO();
				repResponseVO.setRepno(rs.getString("rep_no"));
				repResponseVO.setMemno(rs.getString("mem_no"));
				repResponseVO.setResno(rs.getString("res_no"));
				repResponseVO.setReptime(rs.getTimestamp("rep_time"));
				repResponseVO.setRepreason(rs.getString("rep_reason"));
				repResponseVO.setRepstatus(rs.getString("rep_status"));
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
		return repResponseVO;
	}
	

	@Override
	public List<RepResponseVO> getAll() {
		
		List<RepResponseVO> list = new ArrayList<RepResponseVO>();
		RepResponseVO repResponseVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				repResponseVO = new RepResponseVO();
				repResponseVO.setRepno(rs.getString("rep_no"));
				repResponseVO.setMemno(rs.getString("mem_no"));
				repResponseVO.setResno(rs.getString("res_no"));
				repResponseVO.setReptime(rs.getTimestamp("rep_time"));
				repResponseVO.setRepreason(rs.getString("rep_reason"));
				repResponseVO.setRepstatus(rs.getString("rep_status"));
				list.add(repResponseVO);
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
	
	public void update_approved(String repno) {

		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_APPROVED);
			
			pstmt.setString(1, repno);
			
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
	
	
	public void update_notapproved(String repno) {

		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_NOTAPPROVED);
			
			pstmt.setString(1, repno);
			
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

}
