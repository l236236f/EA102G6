package com.mem.model;

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

public class MemDAO implements MemDAO_interface{

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
			"INSERT INTO MEMBER_INFO(MEM_NO,MEM_ACC,MEM_PW,MEM_NAME,MEM_BIRTH,MEM_ID,MEM_TEL,MEM_GENDER,MEM_ADDR,MEM_EMAIL,MEM_MONEY,MEM_PHOTO,MEM_INTRO) VALUES('M'||LPAD(TO_CHAR(MEM_NO_SEQ.NEXTVAL), 3, '0'),?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String UPDATE =
			"UPDATE MEMBER_INFO SET MEM_PW=?,MEM_NAME=?,MEM_BIRTH=?,MEM_ID=?,MEM_TEL=?,MEM_GENDER=?,MEM_ADDR=?,MEM_EMAIL=?,MEM_MONEY=?,MEM_INTRO=? WHERE MEM_NO=?";
	private static final String UPDATE_PHOTO =
			"UPDATE MEMBER_INFO SET MEM_PHOTO=? WHERE MEM_NO=?";
	private static final String UPDATE_MEM_STATUS =
			"UPDATE MEMBER_INFO SET MEM_STATUS='M1' WHERE MEM_NO=?";
	private static final String UPDATE_MOM =
			"UPDATE MEMBER_INFO SET MOM='M1' WHERE MEM_NO=?";
	private static final String DELETE =
			"DELETE FROM MEMBER_INFO WHERE MEM_NO = ?";
	private static final String GET_ONE_STMT =
			"SELECT MEM_NO,MEM_ACC,MEM_PW,MEM_NAME,MEM_BIRTH,MEM_ID,MEM_TEL,MEM_GENDER,MEM_ADDR,MEM_EMAIL,MEM_MONEY,MEM_PHOTO,MEM_INTRO,BONUS,REG_TIME,MEM_STATUS,MOM,UPROD_EVAS,UPROD_EVACOUNT,GAT_EVAS,GAT_EVACOUNT FROM MEMBER_INFO WHERE MEM_NO = ?";
	private static final String GET_ALL_STMT =
			"SELECT MEM_NO,MEM_ACC,MEM_PW,MEM_NAME,MEM_BIRTH,MEM_ID,MEM_TEL,MEM_GENDER,MEM_ADDR,MEM_EMAIL,MEM_MONEY,MEM_PHOTO,MEM_INTRO,BONUS,REG_TIME,MEM_STATUS,MOM,UPROD_EVAS,UPROD_EVACOUNT,GAT_EVAS,GAT_EVACOUNT FROM MEMBER_INFO ORDER BY MEM_NO";
	private static final String CHECKACC = 
			"SELECT * FROM MEMBER_INFO WHERE MEM_ACC = ?";
	private static final String UPDATEBYEMP =
			"UPDATE  MEMBER_INFO SET MEM_PW=?,MEM_EMAIL=?,MEM_MONEY=?,MEM_STATUS=?,MOM=?,BONUS=? WHERE MEM_NO=?";
	
	@Override
	public MemVO insert(MemVO memVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			String cols[] = {"MEM_NO"}; // 或 int cols[] = {1};
			pstmt = con.prepareStatement(INSERT_STMT, cols);

			pstmt.setString(1, memVO.getMemAcc());
			pstmt.setString(2, memVO.getMemPw());
			pstmt.setString(3, memVO.getMemName());
			pstmt.setDate(4, memVO.getMemBirth());
			pstmt.setString(5, memVO.getMemID());
			pstmt.setString(6, memVO.getMemTel());
			pstmt.setString(7, memVO.getMemGender());
			pstmt.setString(8, memVO.getMemAddr());
			pstmt.setString(9, memVO.getMemEmail());
			pstmt.setString(10, memVO.getMemMoney());
			pstmt.setBytes(11, memVO.getMemPhoto());
			pstmt.setString(12, memVO.getMemIntro());
			
			pstmt.executeUpdate();

			ResultSet rs = pstmt.getGeneratedKeys();
			String memNo = null;
			if (rs.next()) {
						memNo = rs.getString(1);	
			} else {
				System.out.println("NO KEYS WERE GENERATED.");
			}
			memVO.setMemNo(memNo);
			return memVO;
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
	public void update(MemVO memVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
		    
			pstmt.setString(11, memVO.getMemNo());
			pstmt.setString(1, memVO.getMemPw());
			pstmt.setString(2, memVO.getMemName());
			pstmt.setDate(3, memVO.getMemBirth());
			pstmt.setString(4, memVO.getMemID());
			pstmt.setString(5, memVO.getMemTel());
			pstmt.setString(6, memVO.getMemGender());
			pstmt.setString(7, memVO.getMemAddr());
			pstmt.setString(8, memVO.getMemEmail());
			pstmt.setString(9, memVO.getMemMoney());
			pstmt.setString(10, memVO.getMemIntro());

			
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
	public void updatePhoto(MemVO memVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_PHOTO);
		    
			pstmt.setString(2, memVO.getMemNo());
			pstmt.setBytes(1, memVO.getMemPhoto());
				
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

	public void updateMemStatus (String memNo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_MEM_STATUS);
		    
			pstmt.setString(1, memNo);
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
	
	public void updateMom (String memNo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_MOM);
		    
			pstmt.setString(1, memNo);
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
	public void delete(String memNo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, memNo);

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
	public MemVO findByPrimaryKey(String memNo) {
		
		MemVO memVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, memNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				memVO = new MemVO();
				memVO.setMemNo(rs.getString("MEM_NO"));
				memVO.setMemAcc(rs.getString("MEM_ACC"));
				memVO.setMemPw(rs.getString("MEM_PW"));
				memVO.setMemName(rs.getString("MEM_NAME"));
				memVO.setMemBirth(rs.getDate("MEM_BIRTH"));
				memVO.setMemID(rs.getString("MEM_ID"));
				memVO.setMemTel(rs.getString("MEM_TEL"));
				memVO.setMemGender(rs.getString("MEM_GENDER"));
				memVO.setMemAddr(rs.getString("MEM_ADDR"));
				memVO.setMemEmail(rs.getString("MEM_EMAIL"));
				memVO.setMemMoney(rs.getString("MEM_MONEY"));
				memVO.setMemPhoto(rs.getBytes("MEM_PHOTO"));
				memVO.setMemIntro(rs.getString("MEM_INTRO"));
				memVO.setBonus(rs.getInt("BONUS"));
				memVO.setRegTime(rs.getDate("REG_TIME"));
				memVO.setMemStatus(rs.getString("MEM_STATUS"));
				memVO.setMom(rs.getString("MOM"));
				memVO.setUppodEvas(rs.getInt("UPROD_EVAS"));
				memVO.setUppodEvacount(rs.getInt("UPROD_EVACOUNT"));
				memVO.setGatEvas(rs.getInt("GAT_EVAS"));
				memVO.setGatEvacount(rs.getInt("GAT_EVACOUNT"));
				
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
		return memVO;
	}

	@Override
	public List<MemVO> getAll() {
		List<MemVO> list = new ArrayList<MemVO>();
		MemVO memVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				memVO = new MemVO();
				memVO.setMemNo(rs.getString("MEM_NO"));
				memVO.setMemAcc(rs.getString("MEM_ACC"));
				memVO.setMemPw(rs.getString("MEM_PW"));
				memVO.setMemName(rs.getString("MEM_NAME"));
				memVO.setMemBirth(rs.getDate("MEM_BIRTH"));
				memVO.setMemID(rs.getString("MEM_ID"));
				memVO.setMemTel(rs.getString("MEM_TEL"));
				memVO.setMemGender(rs.getString("MEM_GENDER"));
				memVO.setMemAddr(rs.getString("MEM_ADDR"));
				memVO.setMemEmail(rs.getString("MEM_EMAIL"));
				memVO.setMemMoney(rs.getString("MEM_MONEY"));
				memVO.setMemPhoto(rs.getBytes("MEM_PHOTO"));
				memVO.setMemIntro(rs.getString("MEM_INTRO"));
				memVO.setBonus(rs.getInt("BONUS"));
				memVO.setRegTime(rs.getDate("REG_TIME"));
				memVO.setMemStatus(rs.getString("MEM_STATUS"));
				memVO.setMom(rs.getString("MOM"));
				memVO.setUppodEvas(rs.getInt("UPROD_EVAS"));
				memVO.setUppodEvacount(rs.getInt("UPROD_EVACOUNT"));
				memVO.setGatEvas(rs.getInt("GAT_EVAS"));
				memVO.setGatEvacount(rs.getInt("GAT_EVACOUNT"));
				list.add(memVO); // Store the row in the list
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
	
	public MemVO checkAcc(String memAcc) {
	
		MemVO memVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(CHECKACC);
			pstmt.setString(1, memAcc);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				memVO = new MemVO();
				memVO.setMemNo(rs.getString("MEM_NO"));
				memVO.setMemAcc(rs.getString("MEM_ACC"));
				memVO.setMemPw(rs.getString("MEM_PW"));
				memVO.setMemName(rs.getString("MEM_NAME"));
				memVO.setMemBirth(rs.getDate("MEM_BIRTH"));
				memVO.setMemID(rs.getString("MEM_ID"));
				memVO.setMemTel(rs.getString("MEM_TEL"));
				memVO.setMemGender(rs.getString("MEM_GENDER"));
				memVO.setMemAddr(rs.getString("MEM_ADDR"));
				memVO.setMemEmail(rs.getString("MEM_EMAIL"));
				memVO.setMemMoney(rs.getString("MEM_MONEY"));
				memVO.setMemPhoto(rs.getBytes("MEM_PHOTO"));
				memVO.setMemIntro(rs.getString("MEM_INTRO"));
				memVO.setBonus(rs.getInt("BONUS"));
				memVO.setRegTime(rs.getDate("REG_TIME"));
				memVO.setMemStatus(rs.getString("MEM_STATUS"));
				memVO.setMom(rs.getString("MOM"));
				memVO.setUppodEvas(rs.getInt("UPROD_EVAS"));
				memVO.setUppodEvacount(rs.getInt("UPROD_EVACOUNT"));
				memVO.setGatEvas(rs.getInt("GAT_EVAS"));
				memVO.setGatEvacount(rs.getInt("GAT_EVACOUNT"));
				
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
		return memVO;
	}
	@Override
	public void updateByEmp(MemVO memVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
		
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATEBYEMP);
			pstmt.setString(1, memVO.getMemPw());
			pstmt.setString(2, memVO.getMemEmail());
			pstmt.setString(3, memVO.getMemMoney());
			pstmt.setString(4, memVO.getMemStatus());
			pstmt.setString(5, memVO.getMom());
			pstmt.setInt(6, memVO.getBonus());
			pstmt.setString(7, memVO.getMemNo());
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

}
