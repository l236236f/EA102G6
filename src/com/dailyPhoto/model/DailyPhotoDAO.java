package com.dailyPhoto.model;

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

public class DailyPhotoDAO implements DailyPhotoDAO_interface{

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
			"INSERT INTO DAILY_PHOTO(DPH_NO,PD_NO,PHOTO) VALUES('DPH'||LPAD(TO_CHAR(DPH_NO_SEQ.NEXTVAL), 3, '0'),?,?)";
	private static final String UPDATE =
			"UPDATE DAILY_PHOTO SET PD_NO=?,PHOTO=? WHERE DPH_NO=?";
	private static final String DELETE =
			"DELETE FROM DAILY_PHOTO WHERE DPH_NO=?";
	private static final String DELETE_BY_DAILY =
			"DELETE FROM DAILY_PHOTO WHERE PD_NO=?";
	private static final String GET_ONE_STMT =
			"SELECT * FROM DAILY_PHOTO WHERE DPH_NO=?";
	private static final String GET_ALL_STMT =
			"SELECT * FROM DAILY_PHOTO ORDER BY DPH_NO";
	private static final String GET_BY_DAILY_STMT =
			"SELECT * FROM DAILY_PHOTO WHERE PD_NO=? ORDER BY DPH_NO";
	
	
	@Override
	public void insert(DailyPhotoVO dailyPhotoVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, dailyPhotoVO.getPdNo());
			pstmt.setBytes(2, dailyPhotoVO.getPhoto());
			
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
	public void update(DailyPhotoVO dailyPhotoVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
		    
			pstmt.setString(3, dailyPhotoVO.getDphNo());
			pstmt.setString(1, dailyPhotoVO.getPdNo());
			pstmt.setBytes(2, dailyPhotoVO.getPhoto());
			
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
	public void delete(String memNo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, memNo);

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
	public void deleteByPDNo(String pdNo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_BY_DAILY);

			pstmt.setString(1, pdNo);

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
	public DailyPhotoVO findByPrimaryKey(String dphNo) {
		
		DailyPhotoVO dailyPhotoVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, dphNo);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				dailyPhotoVO = new DailyPhotoVO();
				
				dailyPhotoVO.setDphNo(rs.getString("DPH_NO"));
				dailyPhotoVO.setPdNo(rs.getString("PD_NO"));
				dailyPhotoVO.setPhoto(rs.getBytes("PHOTO"));
				
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
		return dailyPhotoVO;
	}

	@Override
	public List<DailyPhotoVO> getAll() {
		List<DailyPhotoVO> list = new ArrayList<DailyPhotoVO>();
		DailyPhotoVO dailyPhotoVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				dailyPhotoVO = new DailyPhotoVO();
				dailyPhotoVO.setDphNo(rs.getString("DPH_NO"));
				dailyPhotoVO.setPdNo(rs.getString("PD_NO"));
				dailyPhotoVO.setPhoto(rs.getBytes("PHOTO"));
				
				list.add(dailyPhotoVO); // Store the row in the list
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
		return list;
	}
	@Override
	public List<DailyPhotoVO> findByPDNo(String pdNo) {
		List<DailyPhotoVO> list = new ArrayList<DailyPhotoVO>();
		DailyPhotoVO dailyPhotoVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BY_DAILY_STMT);
			pstmt.setString(1, pdNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				dailyPhotoVO = new DailyPhotoVO();
				dailyPhotoVO.setDphNo(rs.getString("DPH_NO"));
				dailyPhotoVO.setPdNo(rs.getString("PD_NO"));
				dailyPhotoVO.setPhoto(rs.getBytes("PHOTO"));
				
				list.add(dailyPhotoVO); // Store the row in the list
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

