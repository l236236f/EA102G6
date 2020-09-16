package com.fosmphoto.model;

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

public class FosmPhotoDAO implements FosmPhotoDAOI{
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
			"INSERT INTO FOSM_PHOTO(PHO_NO,FOSM_NO,PHO_CON,PHO_TIME)VALUES('FPH'||LPAD(to_char(FOSM_PHOTO_NO.NEXTVAL), 3, '0'),?,?,sysdate)";
	private static final String DELETE_PSTMT =
			"DELETE FOSM_PHOTO WHERE PHO_NO=?";
	private static final String GETALL_PSTMT =
			"SELECT PHO_NO FROM FOSM_PHOTO WHERE FOSM_NO=? ORDER BY PHO_TIME DESC";
	private static final String GET_ONEPHOTO =
			"SELECT * FROM FOSM_PHOTO WHERE PHO_NO=?";
	
	@Override
	public void insert(String fosmNo, byte[] phoCon) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt =con.prepareStatement(INSERT_PSTMT);
			
			pstmt.setString(1, fosmNo);
			pstmt.setBytes(2, phoCon);
			
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
	public void delete(String phoNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt =con.prepareStatement(DELETE_PSTMT);
			
			pstmt.setString(1, phoNo);
			
			
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
	public List<String> getAll(String fosmNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<String> list = new ArrayList<String>();
		try {
			con = ds.getConnection();
			pstmt =con.prepareStatement(GETALL_PSTMT);
			
			pstmt.setString(1, fosmNo);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(rs.getString("PHO_NO"));
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
	public FosmPhotoVO getPhoto(String phoNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		FosmPhotoVO fosmPhoVO = null; 
		try {
			con = ds.getConnection();
			pstmt =con.prepareStatement(GET_ONEPHOTO);
			fosmPhoVO = new FosmPhotoVO();
			pstmt.setString(1, phoNo);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				fosmPhoVO.setFosmNo(rs.getString("FOSM_NO"));
				fosmPhoVO.setPhoCon(rs.getBytes("PHO_CON"));
				fosmPhoVO.setPhoNo(rs.getString("PHO_NO"));
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
		return fosmPhoVO;
	}

	
}
