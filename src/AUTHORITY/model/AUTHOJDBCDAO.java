package AUTHORITY.model;

import java.util.*;
import java.sql.*;

public class AUTHOJDBCDAO implements AUTHODAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "EA102G6";
	String passwd = "123456";

	private static final String INSERT_STMT = 
			"INSERT INTO AUTHORITY (FEAT_NO,EMP_NO) VALUES (?,?)";
		private static final String GET_ALL_STMT = 
			"SELECT EMP_NO,FEAT_NO FROM AUTHORITY order by EMP_NO,FEAT_NO ";
		private static final String GET_STMT_PK1 = 
			"SELECT EMP_NO FROM AUTHORITY where FEAT_NO = ?  order by FEAT_NO";
		private static final String GET_STMT_PK2 = 
			"SELECT EMP_NO,FEAT_NO FROM AUTHORITY where EMP_NO = ? order by EMP_NO";
		private static final String DELETE = 
			"DELETE FROM AUTHORITY where FEAT_NO = ? AND EMP_NO= ?";
		private static final String DELETE_PK = 
			"DELETE FROM AUTHORITY where EMP_NO = ?";
		
		@Override
		public void insert(AUTHOVO authoVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {
				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(INSERT_STMT);

				pstmt.setString(1, authoVO.getFeatno());
				pstmt.setString(2, authoVO.getEmpno());
				
				

				pstmt.executeUpdate();

				// Handle any SQL errors
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
		public void delete(String featno,String empno) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(DELETE);

				pstmt.setString(1, featno);
				pstmt.setString(2, empno);
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
		public void deleteByEmpno(String empno) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(DELETE_PK);

				pstmt.setString(1, empno);
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
		public List<AUTHOVO> findByFeatno(String featno)  {
			List<AUTHOVO> list = new ArrayList<AUTHOVO>();
			AUTHOVO authoVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_STMT_PK1);
				pstmt.setString(1, featno);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// qaVO 也稱為 Domain objects
					authoVO = new AUTHOVO();
					authoVO.setFeatno(rs.getString("FEAT_NO"));
					authoVO.setEmpno(rs.getString("EMP_NO"));
					list.add(authoVO); // Store the row in the list
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
		
		@Override
		public List<AUTHOVO> findByEmpno(String empno) {
			List<AUTHOVO> list = new ArrayList<AUTHOVO>();
			AUTHOVO authoVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_STMT_PK2);
				pstmt.setString(1, empno);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// qaVO 也稱為 Domain objects
					authoVO = new AUTHOVO();
					authoVO.setFeatno(rs.getString("FEAT_NO"));
					authoVO.setEmpno(rs.getString("EMP_NO"));
					list.add(authoVO); // Store the row in the list
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
		@Override
		public List<AUTHOVO> getAll() {
			List<AUTHOVO> list = new ArrayList<AUTHOVO>();
			AUTHOVO authoVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// qaVO 也稱為 Domain objects
					authoVO = new AUTHOVO();
					authoVO.setFeatno(rs.getString("FEAT_NO"));
					authoVO.setEmpno(rs.getString("EMP_NO"));
					list.add(authoVO); // Store the row in the list
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

//		AUTHOJDBCDAO dao = new AUTHOJDBCDAO();

//		// 新增
//		AUTHOVO authoVO1 = new AUTHOVO();
//		authoVO1.setFeatno("FEAT0001");
//		authoVO1.setEmpno("E003");
//		dao.insert(authoVO1);


//
		// 刪除 
//		dao.delete("FEAT0001","E003");
//		// 刪除 PK2
//		dao.deleteByEmpno("E003");
//
//		// 查詢 PK1
//		List<AUTHOVO> list = dao.findByFeatno("FEAT0002");
//		for (AUTHOVO aAUTHO : list) {
//			System.out.print(aAUTHO.getFeatno() + ",");
//			System.out.print(aAUTHO.getEmpno() + ",");
//			System.out.println();
//		}
		// 查詢 PK2
//		List<AUTHOVO> list2 = dao.findByEmpno("E005");
//		for (AUTHOVO aAUTHO : list2) {
//			System.out.print(aAUTHO.getFeatno() + ",");
//			System.out.print(aAUTHO.getEmpno() + ",");
//			System.out.println();
//		}

////		 查詢
//		List<AUTHOVO> list3 = dao.getAll();
//		for (AUTHOVO aAUTHO : list3) {
//			System.out.print(aAUTHO.getEmpno() + ",");
//			System.out.print(aAUTHO.getFeatno() + ",");
//			System.out.println();
//		}
	}
}