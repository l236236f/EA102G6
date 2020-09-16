package com.vendor.model;

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

import com.mem.model.MemVO;

public class VendorDAO implements VendorDAO_interface{

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
			"INSERT INTO VENDOR (VEN_NO,VEN_ACC,VEN_PW,VEN_NAME,VEN_TEL,VEN_ID,VEN_MONEY,VEN_ADDR,VEN_EMAIL,VEN_PHOTO,VEN_INTRO) VALUES('V'||LPAD(TO_CHAR(VEN_NO_SEQ.NEXTVAL), 3, '0'),?,?,?,?,?,?,?,?,?,?)";
	private static final String UPDATE =
			"UPDATE VENDOR SET VEN_ACC=?,VEN_PW=?,VEN_NAME=?,VEN_TEL=?,VEN_ID=?,VEN_MONEY=?,VEN_ADDR=?,VEN_EMAIL=?,VEN_PHOTO=?,VEN_INTRO=? WHERE VEN_NO=?";
	private static final String DELETE =
			"DELETE FROM VENDOR WHERE VEN_NO = ?";
	private static final String GET_ONE_STMT =
			"SELECT * FROM VENDOR WHERE VEN_NO = ?";
	private static final String GET_ALL_STMT =
			"SELECT * FROM VENDOR ORDER BY VEN_NO";
	private static final String CHECKACC = 
			"SELECT * FROM VENDOR WHERE VEN_ACC = ?";
	private static final String UPDATEBYEMP =
			"UPDATE VENDOR SET VEN_NAME=?,VEN_PW=?,VEN_TEL=?,VEN_ID=?,VEN_MONEY=?,VEN_ADDR=?,VEN_EMAIL=?,VEN_STATUS=? WHERE VEN_NO=?";
	
	
	@Override
	public void insert(VendorVO vendorVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, vendorVO.getVenAcc());
			pstmt.setString(2, vendorVO.getVenPw());
			pstmt.setString(3, vendorVO.getVenName());
			pstmt.setString(4, vendorVO.getVenTel());
			pstmt.setString(5, vendorVO.getVenID());
			pstmt.setString(6, vendorVO.getVenMoney());
			pstmt.setString(7, vendorVO.getVenAddr());
			pstmt.setString(8, vendorVO.getVenEmail());
			pstmt.setBytes(9, vendorVO.getVenPhoto());
			pstmt.setString(10, vendorVO.getVenIntro());
			
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
	public void update(VendorVO vendorVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
		    
			pstmt.setString(11, vendorVO.getVenNo());
			pstmt.setString(1, vendorVO.getVenAcc());
			pstmt.setString(2, vendorVO.getVenPw());
			pstmt.setString(3, vendorVO.getVenName());
			pstmt.setString(4, vendorVO.getVenTel());
			pstmt.setString(5, vendorVO.getVenID());
			pstmt.setString(6, vendorVO.getVenMoney());
			pstmt.setString(7, vendorVO.getVenAddr());
			pstmt.setString(8, vendorVO.getVenEmail());
			pstmt.setBytes(9, vendorVO.getVenPhoto());
			pstmt.setString(10, vendorVO.getVenIntro());
			
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
	public void delete(String venNo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, venNo);

			pstmt.executeUpdate();

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
	public VendorVO findByPrimaryKey(String venNo) {
		
		VendorVO vendorVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, venNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				vendorVO = new VendorVO();
				
				vendorVO.setVenNo(rs.getString("VEN_NO"));
				vendorVO.setVenAcc(rs.getString("VEN_ACC"));
				vendorVO.setVenPw(rs.getString("VEN_PW"));
				vendorVO.setVenName(rs.getString("VEN_NAME"));
				vendorVO.setVenTel(rs.getString("VEN_TEL"));
				vendorVO.setVenID(rs.getString("VEN_ID"));
				vendorVO.setVenMoney(rs.getString("VEN_MONEY"));
				vendorVO.setVenAddr(rs.getString("VEN_ADDR"));
				vendorVO.setVenEmail(rs.getString("VEN_EMAIL"));
				vendorVO.setVenPhoto(rs.getBytes("VEN_PHOTO"));
				vendorVO.setVenIntro(rs.getString("VEN_INTRO"));
				vendorVO.setRegTime(rs.getDate("REG_TIME"));
				vendorVO.setVenStatus(rs.getString("VEN_STATUS"));

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
		return vendorVO;
	}

	@Override
	public List<VendorVO> getAll() {
		List<VendorVO> list = new ArrayList<VendorVO>();
		VendorVO vendorVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				vendorVO = new VendorVO();
				vendorVO.setVenNo(rs.getString("VEN_NO"));
				vendorVO.setVenAcc(rs.getString("VEN_ACC"));
				vendorVO.setVenPw(rs.getString("VEN_PW"));
				vendorVO.setVenName(rs.getString("VEN_NAME"));
				vendorVO.setVenTel(rs.getString("VEN_TEL"));
				vendorVO.setVenID(rs.getString("VEN_ID"));
				vendorVO.setVenMoney(rs.getString("VEN_MONEY"));
				vendorVO.setVenAddr(rs.getString("VEN_ADDR"));
				vendorVO.setVenEmail(rs.getString("VEN_EMAIL"));
				vendorVO.setVenPhoto(rs.getBytes("VEN_PHOTO"));
				vendorVO.setVenIntro(rs.getString("VEN_INTRO"));
				vendorVO.setRegTime(rs.getDate("REG_TIME"));
				vendorVO.setVenStatus(rs.getString("VEN_STATUS"));
				
				list.add(vendorVO);
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
	public VendorVO checkAcc(String venAcc) {
		
		VendorVO vendorVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(CHECKACC);
			pstmt.setString(1, venAcc);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO ¤]ºÙ¬° Domain objects
				vendorVO = new VendorVO();
				vendorVO.setVenNo(rs.getString("VEN_NO"));
				vendorVO.setVenAcc(rs.getString("VEN_ACC"));
				vendorVO.setVenPw(rs.getString("VEN_PW"));
				vendorVO.setVenName(rs.getString("VEN_NAME"));
				vendorVO.setVenTel(rs.getString("VEN_TEL"));
				vendorVO.setVenID(rs.getString("VEN_ID"));
				vendorVO.setVenMoney(rs.getString("VEN_MONEY"));
				vendorVO.setVenAddr(rs.getString("VEN_ADDR"));
				vendorVO.setVenEmail(rs.getString("VEN_EMAIL"));
				vendorVO.setVenPhoto(rs.getBytes("VEN_PHOTO"));
				vendorVO.setVenIntro(rs.getString("VEN_INTRO"));
				vendorVO.setRegTime(rs.getDate("REG_TIME"));
				vendorVO.setVenStatus(rs.getString("VEN_STATUS"));
				
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
		return vendorVO;
	}

	@Override
	public void updateByEmp(VendorVO vendorVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATEBYEMP);

			pstmt.setString(9, vendorVO.getVenNo());
			pstmt.setString(1, vendorVO.getVenName());
			pstmt.setString(2, vendorVO.getVenPw());
			pstmt.setString(3, vendorVO.getVenTel());
			pstmt.setString(4, vendorVO.getVenID());
			pstmt.setString(5, vendorVO.getVenMoney());
			pstmt.setString(6, vendorVO.getVenAddr());
			pstmt.setString(7, vendorVO.getVenEmail());
			pstmt.setString(8, vendorVO.getVenStatus());
			
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
}

