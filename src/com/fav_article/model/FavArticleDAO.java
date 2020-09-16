package com.fav_article.model;

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

public class FavArticleDAO implements FavArticleDAO_interface{
	
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
		"INSERT INTO FAV_ARTICLE (MEM_NO, ART_NO, FAV_TIME) "
					   + "VALUES (?, ?, SYSDATE)";
	private static final String GET_ALL_STMT = 
		"SELECT * FROM FAV_ARTICLE ORDER BY MEM_NO";
	private static final String GET_ONE_STMT = 
		"SELECT * FROM FAV_ARTICLE WHERE MEM_NO=?";
	private static final String DELETE = 
		"DELETE FROM FAV_ARTICLE WHERE MEM_NO=? AND ART_NO=?";
	private static final String GET_RESPONSE_BY_ART_NO =
		"SELECT * FROM FAV_ARTICLE WHERE ART_NO=?";
	
	@Override
	public void insert(FavArticleVO favArticleVO) {
		
		Connection  con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, favArticleVO.getMemno());
			pstmt.setString(2, favArticleVO.getArtno());
			
			pstmt.executeUpdate();
			System.out.println("新增成功");
			
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
	public void delete(String memno, String artno) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, memno);
			pstmt.setString(2, artno);

			pstmt.executeUpdate();
			con.commit();
			con.setAutoCommit(true);
			
			System.out.println("刪除成功");

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
	public List<FavArticleVO> findByPrimaryKey(String memno) {
		
		List<FavArticleVO> list = new ArrayList<FavArticleVO>();
		FavArticleVO favArticleVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, memno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				favArticleVO = new FavArticleVO();
				favArticleVO.setMemno(rs.getString("mem_no"));
				favArticleVO.setArtno(rs.getString("art_no"));
				favArticleVO.setFavtime(rs.getTimestamp("fav_time"));
				list.add(favArticleVO);
			}
			System.out.println("查詢成功");

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
	public List<FavArticleVO> getAll() {
		
		List<FavArticleVO> list = new ArrayList<FavArticleVO>();
		FavArticleVO favArticleVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				favArticleVO = new FavArticleVO();
				favArticleVO.setMemno(rs.getString("mem_no"));
				favArticleVO.setArtno(rs.getString("art_no"));
				favArticleVO.setFavtime(rs.getTimestamp("fav_time"));
				list.add(favArticleVO);
			}
			System.out.println("查詢成功");

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
	public List<FavArticleVO> getFavByArticle(String artno) {
		
		List<FavArticleVO> list = new ArrayList<FavArticleVO>();
		FavArticleVO favArticleVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_RESPONSE_BY_ART_NO);
			
			pstmt.setString(1, artno);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				favArticleVO = new FavArticleVO();
				favArticleVO.setMemno(rs.getString("mem_no"));
				favArticleVO.setArtno(rs.getString("art_no"));
				favArticleVO.setFavtime(rs.getTimestamp("fav_time"));
				list.add(favArticleVO);
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
