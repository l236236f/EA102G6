package FEATURE.model;

import java.util.*;
import java.sql.*;

public class FEATJDBCDAO implements FEATDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "EA102G6";
	String passwd = "123456";

		private static final String INSERT_STMT = 
			"INSERT INTO FEATURE (FEAT_NO,FEAT_NAME) VALUES ('FEAT'||LPAD(TO_CHAR(FEAT_SEQ.NEXTVAL),4,'0'),?)";
		private static final String GET_ALL_STMT = 
			"SELECT FEAT_NO,FEAT_NAME FROM FEATURE order by FEAT_NO ";
		private static final String GET_ONE_STMT = 
			"SELECT FEAT_NO,FEAT_NAME FROM FEATURE where FEAT_NO = ?";
		private static final String DELETE = 
			"DELETE FROM FEATURE where FEAT_NO = ?";
		private static final String UPDATE = 
			"UPDATE FEATURE set FEAT_NAME=? where FEAT_NO = ?";
	@Override
	public void insert(FEATVO featVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, featVO.getFeatname() );
		
		
			

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
	public void update(FEATVO featVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, featVO.getFeatname() );
			pstmt.setString(2, featVO.getFeatno());
		

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
	public void delete(String featno) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, featno);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
	public FEATVO findByPrimaryKey(String featno) {

		FEATVO featVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, featno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				featVO = new FEATVO();
				featVO.setFeatno(rs.getString("FEAT_NO"));
				featVO.setFeatname(rs.getString("FEAT_NAME"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
		return featVO;
	}

	@Override
	public List<FEATVO> getAll() {
		List<FEATVO> list = new ArrayList<FEATVO>();
		FEATVO featVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// featVO 也稱為 Domain objects
				featVO = new FEATVO();
				featVO.setFeatno(rs.getString("FEAT_NO"));
				featVO.setFeatname(rs.getString("FEAT_NAME"));
				list.add(featVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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

	public static void main(String[] args) {

//		FEATJDBCDAO dao = new FEATJDBCDAO();

//		// 新增
//		FEATVO featVO1 = new FEATVO();
//		featVO1.setFeatname("管理登入");
//		dao.insert(featVO1);

//		// 修改
//		FEATVO featVO2 = new FEATVO();
//		featVO2.setFeatno("FEAT001");
//		featVO2.setFeatname("管理討論區");
//		dao.update(featVO2);

//		// 刪除
//		dao.delete("FEAT0004");
//
//		// 查詢
//		FEATVO featVO3 = dao.findByPrimaryKey("FEAT0002");
//		System.out.print(featVO3.getFeatno() + ",");
//		System.out.print(featVO3.getFeatname() + ",");	
//		System.out.println("---------------------");

//		// 查詢
//		List<FEATVO> list = dao.getAll();
//		for (FEATVO aFeat : list) {
//			System.out.print(aFeat.getFeatno() + ",");
//			System.out.print(aFeat.getFeatname() + ",");	
//			
//			System.out.println();
//		}
	}
}