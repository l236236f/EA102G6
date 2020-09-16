package com.article.model;

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

public class ArticleDAO implements ArticleDAO_interface{
	
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
		"INSERT INTO ARTICLE (ART_NO, MEM_NO, ART_TITLE, ART_CONTENT) "
		           + "VALUES ('A'||LPAD(TO_CHAR(ARTICLE_SEQ.NEXTVAL), 3, '0'), ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT * FROM ARTICLE WHERE ART_STATUS='A1' ORDER BY ART_NO DESC";
	private static final String GET_ONE_STMT = 
		"SELECT * FROM ARTICLE WHERE ART_NO=?";
	private static final String DELETE = 
		"DELETE   FROM ARTICLE WHERE ART_NO=?";
	private static final String UPDATE = 
		"UPDATE ARTICLE SET MEM_NO=?, ART_TITLE=?, ART_CONTENT=? WHERE ART_NO=?";
	private static final String UPDATE_TO_REPORT = 
		"UPDATE ARTICLE SET ART_STATUS='A0' WHERE ART_NO=?";
	private static final String GET_REP_ART = 
		"SELECT * FROM ARTICLE WHERE ART_STATUS='A0' ORDER BY ART_NO";

	
	@Override
	public void insert(ArticleVO articleVO) {
		
		Connection  con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, articleVO.getMemno());
			pstmt.setString(2, articleVO.getArttitle());
			pstmt.setString(3, articleVO.getArtcontent());
			
			pstmt.executeUpdate();
			
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void update(ArticleVO articleVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, articleVO.getMemno());
			pstmt.setString(2, articleVO.getArttitle());
			pstmt.setString(3, articleVO.getArtcontent());
			pstmt.setString(4, articleVO.getArtno());
			
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
	public void delete(String artno) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, artno);

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
	public ArticleVO findByPrimaryKey(String artno) {
		
		ArticleVO articleVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, artno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				articleVO = new ArticleVO();
				articleVO.setArtno(rs.getString("art_no"));
				articleVO.setMemno(rs.getString("mem_no"));
				articleVO.setArttitle(rs.getString("art_title"));
				articleVO.setArtcontent(rs.getString("art_content"));
				articleVO.setArttime(rs.getTimestamp("art_time"));
				articleVO.setGpcount(rs.getInt("gp_count"));
				articleVO.setFavcount(rs.getInt("fav_count"));
				articleVO.setArtstatus(rs.getString("art_status"));
				articleVO.setRescount(rs.getInt("res_count"));
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
		return articleVO;
	}

	
	@Override
	public List<ArticleVO> getAll() {
		
		List<ArticleVO> list = new ArrayList<ArticleVO>();
		ArticleVO articleVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				articleVO = new ArticleVO();
				articleVO.setArtno(rs.getString("art_no"));
				articleVO.setMemno(rs.getString("mem_no"));
				articleVO.setArttitle(rs.getString("art_title"));
				articleVO.setArtcontent(rs.getString("art_content"));
				articleVO.setArttime(rs.getTimestamp("art_time"));
				articleVO.setGpcount(rs.getInt("gp_count"));
				articleVO.setFavcount(rs.getInt("fav_count"));
				articleVO.setArtstatus(rs.getString("art_status"));
				articleVO.setRescount(rs.getInt("res_count"));
				list.add(articleVO);
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
	public void update_report(String artno) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_TO_REPORT);
			
			pstmt.setString(1, artno);
			
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
	public List<ArticleVO> getRepArt() {

		List<ArticleVO> list = new ArrayList<ArticleVO>();
		ArticleVO articleVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_REP_ART);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				articleVO = new ArticleVO();
				articleVO.setArtno(rs.getString("art_no"));
				list.add(articleVO);
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