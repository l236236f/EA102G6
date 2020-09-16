package com.art_response.model;

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

public class ArtResponseDAO implements ArtResponseDAO_interface{
	
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
		"INSERT INTO ART_RESPONSE (RES_NO, ART_NO, MEM_NO, RES_CONTENT, RES_TIME, RES_STATUS) "
		           + "VALUES ('ARE'||LPAD(TO_CHAR(ART_RESPONSE_SEQ.NEXTVAL),3 ,'0'), ?, ?, ?, SYSDATE, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT * FROM ART_RESPONSE ORDER BY RES_NO";
	private static final String GET_ONE_STMT = 
		"SELECT * FROM ART_RESPONSE WHERE RES_NO=?";
	private static final String DELETE = 
		"DELETE FROM ART_RESPONSE WHERE RES_NO=?";
	private static final String UPDATE = 
		"UPDATE ART_RESPONSE SET ART_NO=?, MEM_NO=?, RES_CONTENT=?, RES_TIME=SYSDATE WHERE RES_NO=?";
	private static final String GET_RESPONSE_BY_ART_NO =
		"SELECT * FROM ART_RESPONSE WHERE ART_NO=?";
	
	@Override
	public void insert(ArtResponseVO artResponseVO) {
		
		Connection  con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, artResponseVO.getArtno());
			pstmt.setString(2, artResponseVO.getMemno());
			pstmt.setString(3, artResponseVO.getRescontent());
			pstmt.setString(4, artResponseVO.getResstatus());
			
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
	public void update(ArtResponseVO artResponseVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, artResponseVO.getArtno());
			pstmt.setString(2, artResponseVO.getMemno());
			pstmt.setString(3, artResponseVO.getRescontent());
			pstmt.setString(4, artResponseVO.getResstatus());
			pstmt.setString(5, artResponseVO.getResno());
			
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
	public void delete(String resno) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, resno);

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
	public ArtResponseVO findByPrimaryKey(String resno) {
		
		ArtResponseVO artResponseVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, resno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				artResponseVO = new ArtResponseVO();
				artResponseVO.setResno(rs.getString("res_no"));
				artResponseVO.setArtno(rs.getString("art_no"));
				artResponseVO.setMemno(rs.getString("mem_no"));
				artResponseVO.setRescontent(rs.getString("res_content"));
				artResponseVO.setRestime(rs.getTimestamp("res_time"));
				artResponseVO.setResstatus(rs.getString("res_status"));
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
		return artResponseVO;
	}

	
	@Override
	public List<ArtResponseVO> getAll() {
		
		List<ArtResponseVO> list = new ArrayList<ArtResponseVO>();
		ArtResponseVO artResponseVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				artResponseVO = new ArtResponseVO();
				artResponseVO.setResno(rs.getString("res_no"));
				artResponseVO.setArtno(rs.getString("art_no"));
				artResponseVO.setMemno(rs.getString("mem_no"));
				artResponseVO.setRescontent(rs.getString("res_content"));
				artResponseVO.setRestime(rs.getTimestamp("res_time"));
				artResponseVO.setResstatus(rs.getString("res_status"));
				list.add(artResponseVO);
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
	public List<ArtResponseVO> getResByArticle(String artno) {
		
		List<ArtResponseVO> list = new ArrayList<ArtResponseVO>();
		ArtResponseVO artResponseVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_RESPONSE_BY_ART_NO);
			
			pstmt.setString(1, artno);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				artResponseVO = new ArtResponseVO();
				artResponseVO.setResno(rs.getString("res_no"));
				artResponseVO.setArtno(rs.getString("art_no"));
				artResponseVO.setMemno(rs.getString("mem_no"));
				artResponseVO.setRescontent(rs.getString("res_content"));
				artResponseVO.setRestime(rs.getTimestamp("res_time"));
				artResponseVO.setResstatus(rs.getString("res_status"));
				list.add(artResponseVO);
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
	public void deleteResponse(String artno) {
		
	}

	@Override
	public void updateResponse(String resno) {
		
	}

}
