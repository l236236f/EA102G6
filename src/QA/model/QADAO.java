package QA.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class QADAO implements QADAO_interface {

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
		"INSERT INTO QA (QA_NO, QA_CHANGEMAN, QA_TEXT, QA_CHANGEDATE, QA_STATUS, QA_KIND, QA_KIND2, QA_KIND3) VALUES "
				+ "('Q'||LPAD(TO_CHAR(QA_SEQ.NEXTVAL),3,'0'),?,?,?,?,?,?,?) ";
	private static final String GET_ALL_STMT = 
		"SELECT QA_NO, QA_CHANGEMAN, QA_TEXT, QA_CHANGEDATE, QA_STATUS, QA_KIND, QA_KIND2, QA_KIND3 FROM QA order by QA_NO";
	private static final String GET_ONE_STMT = 
		"SELECT QA_NO, QA_CHANGEMAN, QA_TEXT, QA_CHANGEDATE, QA_STATUS, QA_KIND, QA_KIND2, QA_KIND3 FROM QA where  QA_NO = ?";
	private static final String DELETE = 
		"DELETE FROM QA where QA_NO = ?";
	private static final String UPDATE = 
		"UPDATE QA set QA_CHANGEMAN=?, QA_TEXT=?, QA_CHANGEDATE=?, QA_STATUS=?, QA_KIND=? , QA_KIND2=? , QA_KIND3=? where QA_NO = ?";

	@Override
	public void insert(QAVO qaVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, qaVO.getQachangeman() );
			pstmt.setString(2, qaVO.getQatext());
			pstmt.setTimestamp(3, qaVO.getQachangedate());
			pstmt.setString(4, qaVO.getQastatus());
			pstmt.setString(5, qaVO.getQakind());
			pstmt.setString(6, qaVO.getQakind2());
			pstmt.setString(7, qaVO.getQakind3());
			

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
	public void update(QAVO qaVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, qaVO.getQachangeman() );
			pstmt.setString(2, qaVO.getQatext());
			pstmt.setTimestamp(3, qaVO.getQachangedate());
			pstmt.setString(4, qaVO.getQastatus());
			pstmt.setString(5, qaVO.getQakind());
			pstmt.setString(6, qaVO.getQakind2());
			pstmt.setString(7, qaVO.getQakind3());
			pstmt.setString(8, qaVO.getQano());

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
	public void delete(String qano) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, qano);
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
	public QAVO findByPrimaryKey(String qano) {

		QAVO qaVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, qano);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				
				qaVO = new QAVO();
				qaVO.setQano(rs.getString("QA_NO"));
				qaVO.setQachangeman(rs.getString("QA_CHANGEMAN"));
				qaVO.setQatext(rs.getString("QA_TEXT"));
				qaVO.setQachangedate(rs.getTimestamp("QA_CHANGEDATE"));
				qaVO.setQastatus(rs.getString("QA_STATUS"));
				qaVO.setQakind(rs.getString("QA_KIND"));
				qaVO.setQakind2(rs.getString("QA_KIND2"));
				qaVO.setQakind3(rs.getString("QA_KIND3"));
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
		return qaVO;
	}

	@Override
	public List<QAVO> getAll() {
		List<QAVO> list = new ArrayList<QAVO>();
		QAVO qaVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				// qaVO 也稱為 Domain objects
				qaVO = new QAVO();
				qaVO.setQano(rs.getString("QA_NO"));
				qaVO.setQachangeman(rs.getString("QA_CHANGEMAN"));
				qaVO.setQatext(rs.getString("QA_TEXT"));
				qaVO.setQachangedate(rs.getTimestamp("QA_CHANGEDATE"));
				qaVO.setQastatus(rs.getString("QA_STATUS"));
				qaVO.setQakind(rs.getString("QA_KIND"));
				qaVO.setQakind2(rs.getString("QA_KIND2"));
				qaVO.setQakind3(rs.getString("QA_KIND3"));
				list.add(qaVO); // Store the row in the list
				
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