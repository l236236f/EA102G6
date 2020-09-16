package com.rep_gat.model;

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

public class RepGatJDBCDAO implements RepGatDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/EA102G6");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO REP_GAT (REP_NO, GAT_NO, MEM_NO, REP_CONT) "
			+ "VALUES ('R' || LPAD(TO_CHAR(REP_NO_SEQ.NEXTVAL), 3, '0'), ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM REP_GAT ORDER BY REP_NO";
	private static final String GET_ONE_STMT = "SELECT * FROM REP_GAT WHERE REP_NO = ?";
	private static final String DELETE = "DELETE FROM REP_GAT WHERE REP_NO = ?";
	private static final String UPDATE = "UPDATE REP_GAT SET REP_CONT = ? WHERE REP_NO = ?";
	
	private static final String UPDATE_APPROVED =
			"UPDATE REP_GAT SET REP_STATUS='R1' WHERE REP_NO=?";
	private static final String UPDATE_NOTAPPROVED =
			"UPDATE REP_GAT SET REP_STATUS='R2' WHERE REP_NO=?";

	public void insert(RepGatVO repGatVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, repGatVO.getGatNo());
			pstmt.setString(2, repGatVO.getMemNo());
			pstmt.setString(3, repGatVO.getRepCont());

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

	public void update(RepGatVO repGatVO) {		
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, repGatVO.getRepCont());
			pstmt.setString(2, repGatVO.getRepNo());
			
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

	public void delete(String repNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();

			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, repNo);
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

	public RepGatVO findByPrimaryKey(String repNo) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		RepGatVO repGatVO = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, repNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				repGatVO = new RepGatVO();
				repGatVO.setRepNo(rs.getString("REP_NO"));
				repGatVO.setGatNo(rs.getString("GAT_NO"));
				repGatVO.setMemNo(rs.getString("MEM_NO"));
				repGatVO.setRepCont(rs.getString("REP_CONT"));
				repGatVO.setRepTime(rs.getTimestamp("REP_TIME"));
				repGatVO.setRepStatus(rs.getString("REP_STATUS"));
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return repGatVO;
	}

	public List<RepGatVO> getAll() {
		
		List<RepGatVO> list = new ArrayList<RepGatVO>();
		RepGatVO repGatVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				repGatVO = new RepGatVO();
				repGatVO.setRepNo(rs.getString("REP_NO"));
				repGatVO.setGatNo(rs.getString("GAT_NO"));
				repGatVO.setMemNo(rs.getString("MEM_NO"));
				repGatVO.setRepCont(rs.getString("REP_CONT"));
				repGatVO.setRepTime(rs.getTimestamp("REP_TIME"));
				repGatVO.setRepStatus(rs.getString("REP_STATUS"));
				list.add(repGatVO);
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	
	public void update_approved(String repNo) {

		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_APPROVED);
			
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
	
	
	public void update_notapproved(String repNo) {

		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_NOTAPPROVED);
			
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
	
}
