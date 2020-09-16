package com.rep_article.model;

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

public class RepArticleDAO implements RepArticleDAO_interface{
	
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
		"INSERT INTO REP_ARTICLE (REP_NO, MEM_NO, ART_NO, REP_REASON) "
				       + "VALUES ('RA'||LPAD(TO_CHAR(REP_ARTICLE_SEQ.NEXTVAL),3 ,'0'), ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT * FROM REP_ARTICLE ORDER BY REP_NO";
	private static final String GET_ONE_STMT = 
		"SELECT * FROM REP_ARTICLE WHERE REP_NO=?";
	private static final String DELETE = 
		"DELETE FROM REP_ARTICLE WHERE REP_NO=?";
	private static final String UPDATE = 
		"UPDATE REP_ARTICLE SET MEM_NO=?, ART_NO=?, REP_TIME=SYSDATE, REP_REASON=?, REP_STATUS=? WHERE REP_NO=?";

	private static final String UPDATE_APPROVED =
			"UPDATE REP_ARTICLE SET REP_STATUS='R1' WHERE REP_NO=?";
	private static final String UPDATE_NOTAPPROVED =
			"UPDATE REP_ARTICLE SET REP_STATUS='R2' WHERE REP_NO=?";
	
	@Override
	public void insert(RepArticleVO repArticleVO) {
		
		Connection  con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, repArticleVO.getMemno());
			pstmt.setString(2, repArticleVO.getArtno());
			pstmt.setString(3, repArticleVO.getRepreason());
			
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
	public void update(RepArticleVO repArticleVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, repArticleVO.getMemno());
			pstmt.setString(2, repArticleVO.getArtno());
			pstmt.setString(3, repArticleVO.getRepreason());
			pstmt.setString(4, repArticleVO.getRepstatus());
			pstmt.setString(5, repArticleVO.getRepno());
			
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
	public RepArticleVO findByPrimaryKey(String repno) {
		
		RepArticleVO repArticleVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, repno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				repArticleVO = new RepArticleVO();
				repArticleVO.setRepno(rs.getString("rep_no"));
				repArticleVO.setMemno(rs.getString("mem_no"));
				repArticleVO.setArtno(rs.getString("art_no"));
				repArticleVO.setReptime(rs.getTimestamp("rep_time"));
				repArticleVO.setRepreason(rs.getString("rep_reason"));
				repArticleVO.setRepstatus(rs.getString("rep_status"));
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
		return repArticleVO;
	}

	
	@Override
	public List<RepArticleVO> getAll() {
		
		List<RepArticleVO> list = new ArrayList<RepArticleVO>();
		RepArticleVO repArticleVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				repArticleVO = new RepArticleVO();
				repArticleVO.setRepno(rs.getString("rep_no"));
				repArticleVO.setMemno(rs.getString("mem_no"));
				repArticleVO.setArtno(rs.getString("art_no"));
				repArticleVO.setReptime(rs.getTimestamp("rep_time"));
				repArticleVO.setRepreason(rs.getString("rep_reason"));
				repArticleVO.setRepstatus(rs.getString("rep_status"));
				list.add(repArticleVO);
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
