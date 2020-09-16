package ANNOUNCEMENT.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ANNDAO implements ANNDAO_interface {

	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
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
		"INSERT INTO ANNOUNCEMENT (ANN_NO, ANN_CHANGEMAN, ANN_TEXT, ANN_CHANGEDATE, ANN_STATUS, ANN_IMG, ANN_TITLE) VALUES "
				+ "('ANN'||LPAD(TO_CHAR(ANN_SEQ.NEXTVAL),3,'0'),?,?,?,?,?,?) ";
	private static final String GET_ALL_STMT = 
		"SELECT ANN_NO, ANN_CHANGEMAN, ANN_TEXT, ANN_CHANGEDATE, ANN_STATUS, ANN_IMG,ANN_TITLE FROM ANNOUNCEMENT order by ANN_CHANGEDATE desc ";
	private static final String GET_ONE_STMT = 
		"SELECT ANN_NO, ANN_CHANGEMAN, ANN_TEXT, ANN_CHANGEDATE, ANN_STATUS, ANN_IMG,ANN_TITLE FROM ANNOUNCEMENT where  ANN_NO = ?";
	private static final String DELETE = 
		"DELETE FROM ANNOUNCEMENT where ANN_NO = ?";
	private static final String UPDATE = 
		"UPDATE ANNOUNCEMENT set ANN_CHANGEMAN=?, ANN_TEXT=?, ANN_CHANGEDATE=?, ANN_STATUS=?, ANN_IMG=?,ANN_TITLE=? where ANN_NO = ?";

	@Override
	public void insert(ANNVO annVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, annVO.getAnnchangeman());
			pstmt.setString(2, annVO.getAnntext());
			pstmt.setTimestamp(3, annVO.getAnnchangedate());
			pstmt.setString(4, annVO.getAnnstatus());
			pstmt.setBytes(5, annVO.getAnnimg());
			pstmt.setString(6, annVO.getAnntitle());
//			System.out.println(annVO.getAnnchangeman());
//			System.out.println(annVO.getAnntext());
//			System.out.println(annVO.getAnnchangedate());
		

			pstmt.executeUpdate();
		
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
	public void update(ANNVO annVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, annVO.getAnnchangeman() );
			pstmt.setString(2, annVO.getAnntext());
			pstmt.setTimestamp(3, annVO.getAnnchangedate());
			pstmt.setString(4, annVO.getAnnstatus());
			pstmt.setBytes(5, annVO.getAnnimg());
			pstmt.setString(6, annVO.getAnntitle());
			pstmt.setString(7, annVO.getAnnno());

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
	public void delete(String annno) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, annno);
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
	public ANNVO findByPrimaryKey(String annno) {

		ANNVO annVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, annno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				annVO = new ANNVO();
				annVO.setAnnno(rs.getString("ANN_NO"));
				annVO.setAnnchangeman(rs.getString("ANN_CHANGEMAN"));
				annVO.setAnntext(rs.getString("ANN_TEXT"));
				annVO.setAnnchangedate(rs.getTimestamp("ANN_CHANGEDATE"));
				annVO.setAnnstatus(rs.getString("ANN_STATUS"));
				annVO.setAnnimg(rs.getBytes("ANN_IMG"));
				annVO.setAnntitle(rs.getString("ANN_TITLE"));
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
		return annVO;
	}

	@Override
	public List<ANNVO> getAll() {
		List<ANNVO> list = new ArrayList<ANNVO>();
		ANNVO annVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// annVO 也稱為 Domain objects
				annVO = new ANNVO();
				annVO.setAnnno(rs.getString("ANN_NO"));
				annVO.setAnnchangeman(rs.getString("ANN_CHANGEMAN"));
				annVO.setAnntext(rs.getString("ANN_TEXT"));
				annVO.setAnnchangedate(rs.getTimestamp("ANN_CHANGEDATE"));
				annVO.setAnnstatus(rs.getString("ANN_STATUS"));
				annVO.setAnnimg(rs.getBytes("ANN_IMG"));
				annVO.setAnntitle(rs.getString("ANN_TITLE"));
				list.add(annVO); // Store the row in the list
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