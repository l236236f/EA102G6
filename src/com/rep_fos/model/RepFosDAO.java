package com.rep_fos.model;

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

import com.fosmon.model.FosmVO;

public class RepFosDAO implements RepFosDAOI{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/EA102G6");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_PSTMT = 
			"INSERT INTO REP_FOS(FOS_NO,MEM_NO,REP_CONTAIN,REP_STATUS,REP_TIME)VALUES(?,?,?,'FR0',sysdate)";
	private static final String UPDATE_PSTMT = 
			"UPDATE REP_FOS SET REP_CONTAIN=?,REP_STATUS=? WHERE REP_NO=?";
	private static final String GETBYMEMNO_PSTMT = 
			"SELECT * FROM REP_FOS WHERE MEM_NO=?";
	private static final String GETALL_PSTMT = 
			"SELECT * FROM REP_FOS";
	
	private static final String GET_ONE_STMT = 
			"SELECT * FROM REP_FOS WHERE REP_NO=?";
	
	private static final String UPDATE_APPROVED =
			"UPDATE REP_FOS SET REP_STATUS='RF1' WHERE REP_NO=?";
	private static final String UPDATE_NOTAPPROVED =
			"UPDATE REP_FOS SET REP_STATUS='RF2' WHERE REP_NO=?";
	
	@Override
	public void insert(RepFosVO repfosVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_PSTMT);
			
			pstmt.setString(1, repfosVO.getFosNo());
			pstmt.setString(2, repfosVO.getMemNo());
			pstmt.setString(3, repfosVO.getRepCont());
			
			pstmt.executeQuery();
			
		}catch(SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally {
			if(pstmt!=null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(con!=null) {
				try {
					con.close();
				}catch(Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}

	@Override
	public void update(RepFosVO repfosVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_PSTMT);
			
			pstmt.setString(1,repfosVO.getRepCont());
			pstmt.setString(2,repfosVO.getRepStatus());
			pstmt.setString(3,repfosVO.getRepNo());
			
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
	public List<RepFosVO> findBymemNo(String memNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<RepFosVO> list = new ArrayList<RepFosVO>();
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GETBYMEMNO_PSTMT);
			
			pstmt.setString(1, memNo);
			rs = pstmt.executeQuery();
			RepFosVO repFosVO = null;
			while (rs.next()) {
				repFosVO = new RepFosVO();
				repFosVO.setMemNo(rs.getString("MEM_NO"));
				repFosVO.setFosNo(rs.getString("FOS_NO"));
				repFosVO.setRepCont(rs.getString("REP_CONTAIN"));
				repFosVO.setRepStatus(rs.getString("REP_STATUS"));
				repFosVO.setRepTime(rs.getTimestamp("REP_TIME"));
				list.add(repFosVO);
				
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

	@Override
	public List<RepFosVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<RepFosVO> list = new ArrayList<RepFosVO>();
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GETALL_PSTMT);
			rs = pstmt.executeQuery();
			
			RepFosVO repFosVO = null;
			while (rs.next()) {
				repFosVO = new RepFosVO();
				repFosVO.setMemNo(rs.getString("MEM_NO"));
				repFosVO.setFosNo(rs.getString("FOS_NO"));
				repFosVO.setRepCont(rs.getString("REP_CONTAIN"));
				repFosVO.setRepStatus(rs.getString("REP_STATUS"));
				repFosVO.setRepTime(rs.getTimestamp("REP_TIME"));
				list.add(repFosVO);	
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
	
	@Override
	public RepFosVO findByPrimaryKey(String repNo) {
		
		RepFosVO repfosVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, repNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				repfosVO = new RepFosVO();
				repfosVO.setRepNo(rs.getString("rep_no"));
				repfosVO.setMemNo(rs.getString("mem_no"));
				repfosVO.setFosNo(rs.getString("fos_no"));
				repfosVO.setRepTime(rs.getTimestamp("rep_time"));
				repfosVO.setRepCont(rs.getString("rep_cont"));
				repfosVO.setRepStatus(rs.getString("rep_status"));
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
		return repfosVO;
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

	public static void main(String[] args) {
		
	}
}
