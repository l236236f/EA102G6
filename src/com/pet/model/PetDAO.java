package com.pet.model;

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

public class PetDAO implements PetDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/EA102G6");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO PET(PET_NO,MEM_NO,PET_NAME,PET_PHOTO,PET_KIND,PET_VARIETY,PET_BIRTH,PET_GENDER,PET_ID,PET_INTRO) VALUES ('P'||LPAD(TO_CHAR(PET_NO_SEQ.NEXTVAL),3,'0'),?,?,?,?,?,?,?,?,?)";
	private static final String UPDATE = "UPDATE PET SET MEM_NO=?,PET_NAME=?,PET_PHOTO=?,PET_KIND=?,PET_VARIETY=?,PET_BIRTH=?,PET_GENDER=?,PET_INTRO=? WHERE PET_NO=?";
	private static final String DELETE = "DELETE FROM PET WHERE PET_NO = ?";
	private static final String GET_ONE_STMT = "SELECT PET_NO,MEM_NO,PET_NAME,PET_PHOTO,PET_KIND,PET_VARIETY,PET_BIRTH,PET_GENDER,PET_ID,PET_INTRO,REG_TIME,PET_STATUS FROM PET WHERE PET_NO = ?";
	private static final String GET_ALL_STMT = "SELECT PET_NO,MEM_NO,PET_NAME,PET_PHOTO,PET_KIND,PET_VARIETY,PET_BIRTH,PET_GENDER,PET_ID,PET_INTRO,REG_TIME,PET_STATUS FROM PET ORDER BY PET_NO";
	private static final String GET_BY_MEM_STMT = "SELECT PET_NO,MEM_NO,PET_NAME,PET_PHOTO,PET_KIND,PET_VARIETY,PET_BIRTH,PET_GENDER,PET_ID,PET_INTRO,REG_TIME,PET_STATUS FROM PET WHERE MEM_NO = ? ORDER BY PET_NO";

	@Override
	public List<PetVO> findByMemNo(String memNo){
		List<PetVO> list = new ArrayList<PetVO>();
		PetVO petVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BY_MEM_STMT);
			pstmt.setString(1, memNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO ¤]ºÙ¬° Domain objects
				petVO = new PetVO();
				petVO.setPetNo(rs.getString("PET_NO"));
				petVO.setMemNo(rs.getString("MEM_NO"));
				petVO.setPetName(rs.getString("PET_NAME"));
				petVO.setPetPhoto(rs.getBytes("PET_PHOTO"));
				petVO.setPetKind(rs.getString("PET_KIND"));
				petVO.setPetVariety(rs.getString("PET_VARIETY"));
				petVO.setPetBirth(rs.getDate("PET_BIRTH"));
				petVO.setPetGender(rs.getString("PET_GENDER"));
				petVO.setPetID(rs.getString("PET_ID"));
				petVO.setPetIntro(rs.getString("PET_INTRO"));
				petVO.setRegTime(rs.getDate("REG_TIME"));
				petVO.setPetPhoto(rs.getBytes("PET_STATUS"));
				
				list.add(petVO); // Store the row in the list
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
	@Override
	public void insert(PetVO petVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, petVO.getMemNo());
			pstmt.setString(2, petVO.getPetName());
			pstmt.setBytes(3, petVO.getPetPhoto());
			pstmt.setString(4, petVO.getPetKind());
			pstmt.setString(5, petVO.getPetVariety());
			pstmt.setDate(6, petVO.getPetBirth());
			pstmt.setString(7, petVO.getPetGender());
			pstmt.setString(8, petVO.getPetID());
			pstmt.setString(9, petVO.getPetIntro());

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

	@Override
	public void update(PetVO petVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(9, petVO.getPetNo());
			pstmt.setString(1, petVO.getMemNo());
			pstmt.setString(2, petVO.getPetName());
			pstmt.setBytes(3, petVO.getPetPhoto());
			pstmt.setString(4, petVO.getPetKind());
			pstmt.setString(5, petVO.getPetVariety());
			pstmt.setDate(6, petVO.getPetBirth());
			pstmt.setString(7, petVO.getPetGender());
			pstmt.setString(8, petVO.getPetIntro());

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

	@Override
	public void delete(String petNo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, petNo);

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

	@Override
	public PetVO findByPrimaryKey(String petNo) {

		PetVO petVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, petNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				petVO = new PetVO();
				petVO.setPetNo(rs.getString("PET_NO"));
				petVO.setMemNo(rs.getString("MEM_NO"));
				petVO.setPetName(rs.getString("PET_NAME"));
				petVO.setPetPhoto(rs.getBytes("PET_PHOTO"));
				petVO.setPetKind(rs.getString("PET_KIND"));
				petVO.setPetVariety(rs.getString("PET_VARIETY"));
				petVO.setPetBirth(rs.getDate("PET_BIRTH"));
				petVO.setPetGender(rs.getString("PET_GENDER"));
				petVO.setPetID(rs.getString("PET_ID"));
				petVO.setPetIntro(rs.getString("PET_INTRO"));
				petVO.setRegTime(rs.getDate("REG_TIME"));
				petVO.setPetStatus(rs.getString("PET_STATUS"));
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
		return petVO;
	}

	@Override
	public List<PetVO> getAll() {
		List<PetVO> list = new ArrayList<PetVO>();
		PetVO petVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				petVO = new PetVO();
				petVO.setPetNo(rs.getString("PET_NO"));
				petVO.setMemNo(rs.getString("MEM_NO"));
				petVO.setPetName(rs.getString("PET_NAME"));
				petVO.setPetPhoto(rs.getBytes("PET_PHOTO"));
				petVO.setPetKind(rs.getString("PET_KIND"));
				petVO.setPetVariety(rs.getString("PET_VARIETY"));
				petVO.setPetBirth(rs.getDate("PET_BIRTH"));
				petVO.setPetGender(rs.getString("PET_GENDER"));
				petVO.setPetID(rs.getString("PET_ID"));
				petVO.setPetIntro(rs.getString("PET_INTRO"));
				petVO.setRegTime(rs.getDate("REG_TIME"));
				petVO.setPetStatus(rs.getString("PET_STATUS"));
				
				list.add(petVO);
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
