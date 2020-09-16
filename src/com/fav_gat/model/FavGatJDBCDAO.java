package com.fav_gat.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FavGatJDBCDAO implements FavGatDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "EA102G6";
	String pwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO FAV_GAT (GAT_NO, MEM_NO) VALUES (?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM FAV_GAT ORDER BY MEM_NO";
	private static final String GET_ONE_STMT = "SELECT * FROM FAV_GAT WHERE MEM_NO = ?";
	private static final String DELETE = "DELETE FROM FAV_GAT WHERE GAT_NO = ? AND MEM_NO = ?";

	public void insert(FavGatVO favGatVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, pwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, favGatVO.getGatNo());
			pstmt.setString(2, favGatVO.getMemNo());
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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

	public void delete(FavGatVO favGatVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, pwd);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, favGatVO.getGatNo());
			pstmt.setString(2, favGatVO.getMemNo());
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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

	public FavGatVO findByPrimaryKey(String memNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		FavGatVO favGatVO = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, pwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, memNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				favGatVO = new FavGatVO();
				favGatVO.setGatNo(rs.getString("GAT_NO"));
				favGatVO.setMemNo(rs.getString("MEM_NO"));
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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
		return favGatVO;
	}

	public List<FavGatVO> getAll() {
		List<FavGatVO> list = new ArrayList<FavGatVO>();
		FavGatVO favGatVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, pwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				favGatVO = new FavGatVO();
				favGatVO.setGatNo(rs.getString("GAT_NO"));
				favGatVO.setMemNo(rs.getString("MEM_NO"));
				list.add(favGatVO);
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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

	public static void main(String[] args) {
		FavGatJDBCDAO dao = new FavGatJDBCDAO();
		
		//insert
//		FavGatVO favGatVO1 = new FavGatVO();
//		favGatVO1.setGatNo("G001");
//		favGatVO1.setMemNo("M002");
//		dao.insert(favGatVO1);
//		System.out.println("新增成功");
		
		//delete
//		FavGatVO favGatVO2 = new FavGatVO();
//		favGatVO2.setGatNo("G001");
//		favGatVO2.setMemNo("M002");
//		dao.delete(favGatVO2);
//		System.out.println("刪除成功");
		
		//select
//		FavGatVO favGatVO3 = dao.findByPrimaryKey("M001");
//		System.out.println(favGatVO3.getGatNo());
		
		List<FavGatVO> list = dao.getAll();
		System.out.println("查詢全部成功");
		for(FavGatVO result : list) {
			System.out.println(result.getMemNo() + ": " + result.getGatNo());
		}
	}
}
