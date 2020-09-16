package EMPLOYEE.model;
import java.util.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.sql.*;

public class EmpJDBCDAO implements EmpDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "EA102G6";
	String passwd = "123456";

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

	@Override
	public void insert(EmpVO empVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	public void update(EmpVO empVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	public void delete(String empno) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

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
	public EmpVO findByPrimaryKey(String empno) {

		EmpVO empVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

	public static void main(String[] args) throws IOException {

		EmpJDBCDAO dao = new EmpJDBCDAO();

		// 新增
		EmpVO empVO1 = new EmpVO();
		
	
		empVO1.setEmpname("吳永志");
		empVO1.setEmptel("4771234");
		empVO1.setEmpemail("wu@wu.wu.com");
		empVO1.setEmpid("wu1234");
		empVO1.setEmppsw("wu2234");
		byte[] a=imgToByte("C:\\Users\\USER\\Pictures\\1.jpg");
		empVO1.setEmpphoto(a);
		empVO1.setEmpposition("老師");
		empVO1.setEmphiredate(java.sql.Timestamp.valueOf("2009-1-01 10:50:0"));
		empVO1.setEmpchangedate(new Timestamp(System.currentTimeMillis()));
		empVO1.setEmpchangeman("E004");
		empVO1.setEmpstatus("E0");
		empVO1.setEmpnotes("帥");
		dao.insert(empVO1);

//		 修改
		EmpVO empVO2 = new EmpVO();
		empVO2.setEmpname("4dddd4");
		empVO2.setEmptel("wu2234");
		empVO2.setEmpemail("wu2234");
		empVO2.setEmpid("wu2234");
		empVO2.setEmppsw("wu2234");
		empVO2.setEmpphoto(a);
		empVO2.setEmpposition("老師");
		empVO2.setEmphiredate(java.sql.Timestamp.valueOf("2009-1-01 10:50:0"));
		empVO2.setEmpchangedate(new Timestamp(System.currentTimeMillis()));
		empVO2.setEmpchangeman("E004");
		empVO2.setEmpstatus("E0");
		empVO2.setEmpnotes("率");
		empVO2.setEmpno("E021");
		dao.update(empVO2);

		// 刪除
//		dao.delete("E006");

		// 查詢
		EmpVO empVO3 = dao.findByPrimaryKey("E004");
		
		System.out.print(empVO3.getEmpno()+",");	
		System.out.print(empVO3.getEmpname()+",");	
		System.out.print(empVO3.getEmptel()+",");	
		System.out.print(empVO3.getEmpemail()+",");	
		System.out.print(empVO3.getEmpid()+",");	
		System.out.print(empVO3.getEmppsw()+",");	
		System.out.print(empVO3.getEmpphoto()+",");	
		System.out.print(empVO3.getEmpposition()+",");	
		System.out.print(empVO3.getEmphiredate()+",");	
		System.out.print(empVO3.getEmpchangedate()+",");	
		System.out.print(empVO3.getEmpchangeman()+",");	
		System.out.print(empVO3.getEmpstatus()+",");	
		System.out.print(empVO3.getEmpnotes()+",");	
		System.out.println("---------------------");

		// 查詢
		List<EmpVO> list = dao.getAll();
		for (EmpVO aEmp : list) {
			System.out.print(aEmp.getEmpno() + ",");
			System.out.print(aEmp.getEmpname() + ",");
			System.out.print(aEmp.getEmptel() + ",");
			System.out.print(aEmp.getEmpemail() + ",");
			System.out.print(aEmp.getEmpid() + ",");
			System.out.print(aEmp.getEmppsw() + ",");
			System.out.print(aEmp.getEmpphoto()+ ",");
			System.out.print(aEmp.getEmpposition() + ",");
			System.out.print(aEmp.getEmphiredate() + ",");
			System.out.print(aEmp.getEmpchangedate()+",");	
			System.out.print(aEmp.getEmpchangeman()+",");	
			System.out.print(aEmp.getEmpstatus()+",");	
			System.out.print(aEmp.getEmpnotes()+",");	
			System.out.println();
		}
		
	}

	public static byte[] imgToByte(String str) throws IOException {
	
	File originalImgFile = new File(str);
	BufferedImage bufferedImage = ImageIO.read(originalImgFile);
	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	ImageIO.write(bufferedImage, "jpg", baos);
	baos.flush();
	byte[] originalImgByte = baos.toByteArray();
	baos.close();
	return originalImgByte;
	}

	@Override
	public EmpVO findByPrimaryKeyLogin(String empid) {
		EmpVO empVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(CHECKID);

			pstmt.setString(1, empid);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				a = true;
			}
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		// TODO Auto-generated method stub
		return null;
	}
	
	
}