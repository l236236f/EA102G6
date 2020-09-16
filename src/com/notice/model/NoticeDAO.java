package com.notice.model;

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

public class NoticeDAO implements NoticeDAO_interface{

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/EA102G6");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
//	private static final String INSERT_STMT = 
//			"INSERT INTO MEMBER_INFO(MEM_NO,MEM_ACC,MEM_PW,MEM_NAME,MEM_BIRTH,MEM_ID,MEM_TEL,MEM_GENDER,MEM_ADDR,MEM_EMAIL,MEM_MONEY,MEM_PHOTO,MEM_INTRO,BONUS,REG_TIME,MEM_STATUS,MOM,UPROD_EVAS,UPROD_EVACOUNT,GAT_EVAS,GAT_EVACOUNT) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	private static final String INSERT_STMT = 
			"INSERT INTO NOTICE (NOT_NO,MEM_NO,NOT_CLASS,NOT_CONT) VALUES ('N'||LPAD(TO_CHAR(NOT_NO_SEQ.NEXTVAL), 3, '0'),?,?,?)";
	private static final String UPDATE =
			"UPDATE NOTICE SET MEM_NO=?,NOT_CLASS=?,NOT_CONT=? WHERE NOT_NO=?";
	private static final String DELETE =
			"DELETE FROM NOTICE WHERE NOT_NO = ?";
	private static final String GET_ONE_STMT =
			"SELECT * FROM NOTICE WHERE NOT_NO = ?";
	private static final String GET_ALL_STMT =
			"SELECT * FROM NOTICE ORDER BY NOT_NO";
	
	
	@Override
	public void insert(NoticeVO noticeVo) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, noticeVo.getMemNo());
			pstmt.setString(2, noticeVo.getNotClass());
			pstmt.setString(3, noticeVo.getNotCont());
			
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
	public void update(NoticeVO noticeVo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
		    
			pstmt.setString(4, noticeVo.getNotNo());
			pstmt.setString(1, noticeVo.getMemNo());
			pstmt.setString(2, noticeVo.getNotClass());
			pstmt.setString(3, noticeVo.getNotCont());
			
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
	public void delete(String notNo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, notNo);

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
	public NoticeVO findByPrimaryKey(String notNo) {
		
		NoticeVO noticeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, notNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				
				noticeVO = new NoticeVO();
				
				noticeVO.setNotNo(rs.getString("NOT_NO"));
				noticeVO.setMemNo(rs.getString("MEM_NO"));
				noticeVO.setNotClass(rs.getString("NOT_CLASS"));
				noticeVO.setNotCont(rs.getString("NOT_CONT"));
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
		return noticeVO;
	}

	@Override
	public List<NoticeVO> getAll() {
		List<NoticeVO> list = new ArrayList<NoticeVO>();
		NoticeVO noticeVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				noticeVO = new NoticeVO();
				
				noticeVO.setNotNo(rs.getString("NOT_NO"));
				noticeVO.setMemNo(rs.getString("MEM_NO"));
				noticeVO.setNotClass(rs.getString("NOT_CLASS"));
				noticeVO.setNotCont(rs.getString("NOT_CONT"));
				
				list.add(noticeVO); // Store the row in the list
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

}
