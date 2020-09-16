package com.gat.model;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class GatJDBCDAO implements GatDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/EA102G6");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO GATHERING (GAT_NO, MEM_NO, GAT_NAME, GAT_TIME, GAT_LOC, GAT_INTRO, GAT_STARTTIME, GAT_ENDTIME, GAT_MAX, GAT_MIN, GAT_PHOTO, GAT_TYPE, GAT_LAT, GAT_LNG) "
			+ "VALUES ('G' || LPAD(TO_CHAR(GAT_NO_SEQ.NEXTVAL), 3, '0'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM GATHERING WHERE GAT_TIME > CURRENT_DATE AND GAT_STATUS = 'G0' ORDER BY GAT_NO DESC";
	private static final String GET_ALL_BY_ENDTIME_STMT = "SELECT * FROM GATHERING WHERE GAT_ENDTIME > CURRENT_DATE AND GAT_STATUS = 'G0' ORDER BY GAT_ENDTIME";
	private static final String GET_ONE_STMT = "SELECT * FROM GATHERING WHERE GAT_NO = ?";
	private static final String GET_LIKE_STMT = "SELECT * FROM GATHERING WHERE GAT_NAME LIKE ? AND GAT_TIME > CURRENT_DATE AND GAT_STATUS = 'G0' ORDER BY GAT_TIME DESC";
	private static final String DELETE = "DELETE FROM GATHERING WHERE GAT_NO = ?";
	private static final String UPDATE = "UPDATE GATHERING SET GAT_NAME = ?,GAT_TIME = ?, GAT_LOC = ?, GAT_INTRO = ?, GAT_STARTTIME = ?, GAT_ENDTIME = ?, GAT_MAX = ?, GAT_MIN = ?, GAT_PHOTO = ?, GAT_TYPE = ?, GAT_LAT = ?, GAT_LNG = ? WHERE GAT_NO = ?";
	private static final String UPDATESTATUS = "UPDATE GATHERING SET GAT_STATUS = ? WHERE GAT_NO = ?";
	private static final String GET_TIMEOUT = "SELECT GAT_NO FROM GATHERING WHERE GAT_TIME < SYSDATE + 1";
	private static final String GET_ALL_FOR_UPATELIST = "SELECT * FROM GATHERING ORDER BY GAT_NO DESC";
	
	private static final String UPDATE_TO_REPORT = 
			"UPDATE GATHERING SET GAT_STATUS='G2' WHERE GAT_NO=?";
	
	public void insert(GatVO gatVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, gatVO.getMemNo());
			pstmt.setString(2, gatVO.getGatName());
			pstmt.setDate(3, gatVO.getGatTime());
			pstmt.setString(4, gatVO.getGatLoc());
			pstmt.setString(5, gatVO.getGatIntro());
			pstmt.setDate(6, gatVO.getGatStarttime());
			pstmt.setDate(7, gatVO.getGatEndtime());
			pstmt.setInt(8, gatVO.getGatMax());
			pstmt.setInt(9, gatVO.getGatMin());
			pstmt.setBytes(10, gatVO.getGatPhoto());
			pstmt.setString(11, gatVO.getGatType());	
			pstmt.setDouble(12, gatVO.getGatLat());
			pstmt.setDouble(13, gatVO.getGatLng());	
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
//		return key;
	}

	public void update(GatVO gatVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();;
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, gatVO.getGatName());
			pstmt.setDate(2, gatVO.getGatTime());
			pstmt.setString(3, gatVO.getGatLoc());
			pstmt.setString(4, gatVO.getGatIntro());
			pstmt.setDate(5, gatVO.getGatStarttime());
			pstmt.setDate(6, gatVO.getGatEndtime());
			pstmt.setInt(7, gatVO.getGatMax());
			pstmt.setInt(8, gatVO.getGatMin());
			pstmt.setBytes(9, gatVO.getGatPhoto());
			pstmt.setString(10, gatVO.getGatType());
			pstmt.setDouble(11, gatVO.getGatLat());
			pstmt.setDouble(12, gatVO.getGatLng());	
			pstmt.setString(13, gatVO.getGatNo());

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
	
	public void updateStatus(String gatStatus, String gatNo) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATESTATUS);

			pstmt.setString(1, gatStatus);
			pstmt.setString(2, gatNo);
			

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

	public void delete(String gatNo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, gatNo);
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

	public GatVO findByPrimaryKey(String gatNo) {

		GatVO gatVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, gatNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				gatVO = new GatVO();
				gatVO.setGatNo(rs.getString("GAT_NO"));
				gatVO.setMemNo(rs.getString("MEM_NO"));
				gatVO.setGatName(rs.getString("GAT_NAME"));
				gatVO.setGatTime(rs.getDate("GAT_TIME"));
				gatVO.setGatLoc(rs.getString("GAT_LOC"));
				gatVO.setGatIntro(rs.getString("GAT_INTRO"));
				gatVO.setGatStarttime(rs.getDate("GAT_STARTTIME"));
				gatVO.setGatEndtime(rs.getDate("GAT_ENDTIME"));
				gatVO.setGatMax(rs.getInt("GAT_MAX"));
				gatVO.setGatMin(rs.getInt("GAT_MIN"));
				gatVO.setGatAll(rs.getInt("GAT_ALL"));
				gatVO.setGatStatus(rs.getString("GAT_STATUS"));
				gatVO.setGatPhoto(rs.getBytes("GAT_PHOTO"));
				gatVO.setGatType(rs.getString("GAT_TYPE"));
				gatVO.setGatLat(rs.getDouble("GAT_LAT"));
				gatVO.setGatLng(rs.getDouble("GAT_LNG"));

			}

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
		return gatVO;

	}

	public List<GatVO> getAll() {

		List<GatVO> list = new ArrayList<GatVO>();
		GatVO gatVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				gatVO = new GatVO();
				gatVO.setGatNo(rs.getString("GAT_NO"));
				gatVO.setMemNo(rs.getString("MEM_NO"));
				gatVO.setGatName(rs.getString("GAT_NAME"));
				gatVO.setGatTime(rs.getDate("GAT_TIME"));
				gatVO.setGatLoc(rs.getString("GAT_LOC"));
				gatVO.setGatIntro(rs.getString("GAT_INTRO"));
				gatVO.setGatStarttime(rs.getDate("GAT_STARTTIME"));
				gatVO.setGatEndtime(rs.getDate("GAT_ENDTIME"));
				gatVO.setGatMax(rs.getInt("GAT_MAX"));
				gatVO.setGatMin(rs.getInt("GAT_MIN"));
				gatVO.setGatAll(rs.getInt("GAT_ALL"));
				gatVO.setGatStatus(rs.getString("GAT_STATUS"));
				gatVO.setGatPhoto(rs.getBytes("GAT_PHOTO"));
				gatVO.setGatType(rs.getString("GAT_TYPE"));
				gatVO.setGatLat(rs.getDouble("GAT_LAT"));
				gatVO.setGatLng(rs.getDouble("GAT_LNG"));
				list.add(gatVO);
			}

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

	public List<GatVO> getAllForUpdateList() {

		List<GatVO> list = new ArrayList<GatVO>();
		GatVO gatVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_FOR_UPATELIST);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				gatVO = new GatVO();
				gatVO.setGatNo(rs.getString("GAT_NO"));
				gatVO.setMemNo(rs.getString("MEM_NO"));
				gatVO.setGatName(rs.getString("GAT_NAME"));
				gatVO.setGatTime(rs.getDate("GAT_TIME"));
				gatVO.setGatLoc(rs.getString("GAT_LOC"));
				gatVO.setGatIntro(rs.getString("GAT_INTRO"));
				gatVO.setGatStarttime(rs.getDate("GAT_STARTTIME"));
				gatVO.setGatEndtime(rs.getDate("GAT_ENDTIME"));
				gatVO.setGatMax(rs.getInt("GAT_MAX"));
				gatVO.setGatMin(rs.getInt("GAT_MIN"));
				gatVO.setGatAll(rs.getInt("GAT_ALL"));
				gatVO.setGatStatus(rs.getString("GAT_STATUS"));
				gatVO.setGatPhoto(rs.getBytes("GAT_PHOTO"));
				gatVO.setGatType(rs.getString("GAT_TYPE"));
				gatVO.setGatLat(rs.getDouble("GAT_LAT"));
				gatVO.setGatLng(rs.getDouble("GAT_LNG"));
				list.add(gatVO);
			}

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
	
	public List<GatVO> getAllByEndtime() {

		List<GatVO> list = new ArrayList<GatVO>();
		GatVO gatVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_BY_ENDTIME_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				gatVO = new GatVO();
				gatVO.setGatNo(rs.getString("GAT_NO"));
				gatVO.setMemNo(rs.getString("MEM_NO"));
				gatVO.setGatName(rs.getString("GAT_NAME"));
				gatVO.setGatTime(rs.getDate("GAT_TIME"));
				gatVO.setGatLoc(rs.getString("GAT_LOC"));
				gatVO.setGatIntro(rs.getString("GAT_INTRO"));
				gatVO.setGatStarttime(rs.getDate("GAT_STARTTIME"));
				gatVO.setGatEndtime(rs.getDate("GAT_ENDTIME"));
				gatVO.setGatMax(rs.getInt("GAT_MAX"));
				gatVO.setGatMin(rs.getInt("GAT_MIN"));
				gatVO.setGatAll(rs.getInt("GAT_ALL"));
				gatVO.setGatStatus(rs.getString("GAT_STATUS"));
				gatVO.setGatPhoto(rs.getBytes("GAT_PHOTO"));
				gatVO.setGatType(rs.getString("GAT_TYPE"));
				gatVO.setGatLat(rs.getDouble("GAT_LAT"));
				gatVO.setGatLng(rs.getDouble("GAT_LNG"));
				list.add(gatVO);
			}

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
	
	public List<GatVO> getAllLike(String gatName) {

		List<GatVO> list = new ArrayList<GatVO>();
		GatVO gatVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_LIKE_STMT);
			pstmt.setString(1, "%"+gatName+"%");
			rs = pstmt.executeQuery();

			while (rs.next()) {

				gatVO = new GatVO();
				gatVO.setGatNo(rs.getString("GAT_NO"));
				gatVO.setMemNo(rs.getString("MEM_NO"));
				gatVO.setGatName(rs.getString("GAT_NAME"));
				gatVO.setGatTime(rs.getDate("GAT_TIME"));
				gatVO.setGatLoc(rs.getString("GAT_LOC"));
				gatVO.setGatIntro(rs.getString("GAT_INTRO"));
				gatVO.setGatStarttime(rs.getDate("GAT_STARTTIME"));
				gatVO.setGatEndtime(rs.getDate("GAT_ENDTIME"));
				gatVO.setGatMax(rs.getInt("GAT_MAX"));
				gatVO.setGatMin(rs.getInt("GAT_MIN"));
				gatVO.setGatAll(rs.getInt("GAT_ALL"));
				gatVO.setGatStatus(rs.getString("GAT_STATUS"));
				gatVO.setGatPhoto(rs.getBytes("GAT_PHOTO"));
				gatVO.setGatType(rs.getString("GAT_TYPE"));
				gatVO.setGatLat(rs.getDouble("GAT_LAT"));
				gatVO.setGatLng(rs.getDouble("GAT_LNG"));
				list.add(gatVO);
			}

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
	public void autoStatus(){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_TIMEOUT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				updateStatus("G3", rs.getString("GAT_NO"));
			}
		
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
		
	}
	
	//將圖片寫進DB
	public static byte[] getPhByteArray(String path) throws IOException {
		
		//接水管
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		//建bytes容器
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		//自訂緩衝
		byte[] buffer = new byte[8192];
		
		//用低階管讀byte array
		int i;
		while ((i = fis.read(buffer)) != -1) {
			//將資料讀進bytes容器
			baos.write(buffer, 0, i);
		}
		
		baos.close();
		fis.close();
		
		return baos.toByteArray();
	}
	
	public void update_report(String gatNo) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_TO_REPORT);

			pstmt.setString(1, gatNo);
			

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
	
}
