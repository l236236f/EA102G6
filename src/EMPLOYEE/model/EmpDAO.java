package EMPLOYEE.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
public class EmpDAO implements EmpDAO_interface {

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
		"INSERT INTO EMPLOYEE (emp_no,emp_name,emp_tel,emp_email,emp_id,emp_psw,emp_photo,emp_position,emp_hiredate,emp_changedate,emp_changeman,emp_status,emp_notes) VALUES ('E'||LPAD(TO_CHAR(EMP_SEQ.NEXTVAL),3,'0'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT emp_no,emp_name,emp_tel,emp_email,emp_id,emp_psw,emp_photo,emp_position,emp_hiredate,emp_changedate,emp_changeman,emp_status,emp_notes FROM EMPLOYEE order by emp_no";
	private static final String GET_ONE_STMT = 
		"SELECT emp_no,emp_name,emp_tel,emp_email,emp_id,emp_psw,emp_photo,emp_position,emp_hiredate,emp_changedate,emp_changeman,emp_status,emp_notes FROM EMPLOYEE where emp_no = ?";
	private static final String DELETE = 
		"DELETE FROM EMPLOYEE where emp_no = ?";
	private static final String UPDATE = 
		"UPDATE EMPLOYEE set emp_name=?,emp_tel=?,emp_email=?,emp_id=?,emp_psw=?,emp_photo=?,emp_position=?,emp_hiredate=?,emp_changedate=?,emp_changeman=?,emp_status=?,emp_notes=? where emp_no = ?";
	private static final String GET_ONE_LOGIN = 
		"SELECT emp_no,emp_name,emp_tel,emp_email,emp_id,emp_psw,emp_photo,emp_position,emp_hiredate,emp_changedate,emp_changeman,emp_status,emp_notes FROM EMPLOYEE WHERE EMP_ID = ?";
	private static final String CHECKID = "SELECT EMP_ID FROM EMPLOYEE WHERE EMP_ID = ?";
	private static final String CHECKONTHEJOB ="SELECT EMP_NO,EMP_STATUS FROM EMPLOYEE WHERE EMP_ID = ?";
	
	
	@Override
	public void insert(EmpVO empVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, empVO.getEmpname());
			pstmt.setString(2, empVO.getEmptel());
			pstmt.setString(3, empVO.getEmpemail());
			pstmt.setString(4, empVO.getEmpid());
			pstmt.setString(5, empVO.getEmppsw());
			pstmt.setBytes(6, empVO.getEmpphoto());
			pstmt.setString(7, empVO.getEmpposition());
			pstmt.setTimestamp(8, empVO.getEmphiredate());
			pstmt.setTimestamp(9, empVO.getEmpchangedate());
			pstmt.setString(10, empVO.getEmpchangeman());
			pstmt.setString(11, empVO.getEmpstatus());
			pstmt.setString(12, empVO.getEmpnotes());
				
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
	public void update(EmpVO empVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, empVO.getEmpname());
			pstmt.setString(2, empVO.getEmptel());
			pstmt.setString(3, empVO.getEmpemail());
			pstmt.setString(4, empVO.getEmpid());
			pstmt.setString(5, empVO.getEmppsw());
			pstmt.setBytes(6, empVO.getEmpphoto());
			pstmt.setString(7, empVO.getEmpposition());
			pstmt.setTimestamp(8, empVO.getEmphiredate());
			pstmt.setTimestamp(9, empVO.getEmpchangedate());
			pstmt.setString(10, empVO.getEmpchangeman());
			pstmt.setString(11, empVO.getEmpstatus());
			pstmt.setString(12, empVO.getEmpnotes());
			pstmt.setString(13, empVO.getEmpno());
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
	public void delete(String empno) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, empno);

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
	public EmpVO findByPrimaryKey(String empno) {

		EmpVO empVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, empno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				empVO = new EmpVO();
				empVO.setEmpno(rs.getString("emp_no"));
				empVO.setEmpname(rs.getString("emp_name"));
				empVO.setEmptel(rs.getString("emp_tel"));
				empVO.setEmpemail(rs.getString("emp_email"));
				empVO.setEmpid(rs.getString("emp_id"));
				empVO.setEmppsw(rs.getString("emp_psw"));
				empVO.setEmpphoto(rs.getBytes("emp_photo"));
				empVO.setEmpposition(rs.getString("emp_position"));
				empVO.setEmphiredate(rs.getTimestamp("emp_hiredate"));
				empVO.setEmpchangedate(rs.getTimestamp("emp_changedate"));
				empVO.setEmpchangeman(rs.getString("emp_changeman"));
				empVO.setEmpstatus(rs.getString("emp_status"));
				empVO.setEmpnotes(rs.getString("emp_notes"));
				
				
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
		return empVO;
	}

	@Override
	public List<EmpVO> getAll() {
		List<EmpVO> list = new ArrayList<EmpVO>();
		EmpVO empVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				empVO = new EmpVO();
				empVO.setEmpno(rs.getString("emp_no"));
				empVO.setEmpname(rs.getString("emp_name"));
				empVO.setEmptel(rs.getString("emp_tel"));
				empVO.setEmpemail(rs.getString("emp_email"));
				empVO.setEmpid(rs.getString("emp_id"));
				empVO.setEmppsw(rs.getString("emp_psw"));
				empVO.setEmpphoto(rs.getBytes("emp_photo"));
				empVO.setEmpposition(rs.getString("emp_position"));
				empVO.setEmphiredate(rs.getTimestamp("emp_hiredate"));
				empVO.setEmpchangedate(rs.getTimestamp("emp_changedate"));
				empVO.setEmpchangeman(rs.getString("emp_changeman"));
				empVO.setEmpstatus(rs.getString("emp_status"));
				empVO.setEmpnotes(rs.getString("emp_notes"));
				list.add(empVO); // Store the row in the list
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
	public EmpVO findByPrimaryKeyLogin(String empid) {
		EmpVO empVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			con = ds.getConnection();
			
			pstmt = con.prepareStatement(GET_ONE_LOGIN);
			
			pstmt.setString(1, empid);

			rs = pstmt.executeQuery();
			
		
			while (rs.next()) {
				empVO = new EmpVO();
				empVO.setEmpno(rs.getString("emp_no"));
				empVO.setEmpname(rs.getString("emp_name"));
				empVO.setEmptel(rs.getString("emp_tel"));
				empVO.setEmpemail(rs.getString("emp_email"));
				empVO.setEmpid(rs.getString("emp_id"));
				empVO.setEmppsw(rs.getString("emp_psw"));
				empVO.setEmpphoto(rs.getBytes("emp_photo"));
				empVO.setEmpposition(rs.getString("emp_position"));
				empVO.setEmphiredate(rs.getTimestamp("emp_hiredate"));
				empVO.setEmpchangedate(rs.getTimestamp("emp_changedate"));
				empVO.setEmpchangeman(rs.getString("emp_changeman"));
				empVO.setEmpstatus(rs.getString("emp_status"));
				empVO.setEmpnotes(rs.getString("emp_notes"));
				
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());

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

		return empVO;
	}

	@Override
	public String checkid(String empid) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean a = false; 
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(CHECKID);

			pstmt.setString(1, empid);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				a = true;
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());

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
		if(a) {
			return "true";
		}else {
			return "false";
		}
		
	}

	@Override
	public EmpVO checkOnthejob(String empid) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		EmpVO empVO = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(CHECKONTHEJOB);
			pstmt.setString(1, empid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				empVO = new EmpVO();
				empVO.setEmpno(rs.getString("emp_no"));
				empVO.setEmpstatus(rs.getString("emp_status"));
			}
		}  catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());

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

		return empVO;
	}
}
