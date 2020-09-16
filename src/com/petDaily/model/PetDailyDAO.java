package com.petDaily.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class PetDailyDAO implements PetDailyDAO_interface{
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
			"INSERT INTO PET_DAILY (PD_NO, PET_NO, PD_CLASS, PD_CONT) VALUES ('PD'||LPAD(TO_CHAR(PD_NO_SEQ.NEXTVAL),3,'0'),?,?,?)";
	private static final String UPDATE =
			"UPDATE PET_DAILY SET PET_NO=?,PD_CLASS=?,PD_CONT=? WHERE PD_NO=?";
	private static final String DELETE =
			"DELETE FROM PET_DAILY WHERE PD_NO = ?";
	private static final String GET_ONE_STMT =
			"SELECT * FROM PET_DAILY WHERE PD_NO = ?";
	private static final String GET_ALL_STMT =
			"SELECT * FROM PET_DAILY ORDER BY PD_NO";
	private static final String GET_BY_PET_STMT = 
			"SELECT * FROM PET_DAILY WHERE PET_NO = ? ORDER BY PD_NO DESC";

	
	@Override
	public PetDailyVO insert(PetDailyVO petDailyVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			
			String cols[] = {"PD_NO"}; // ©Î int cols[] = {1};
			pstmt = con.prepareStatement(INSERT_STMT, cols);
			
			pstmt.setString(1, petDailyVO.getPetNo());
			pstmt.setString(2, petDailyVO.getPdClass());
			pstmt.setString(3, petDailyVO.getPdCont());
			
			pstmt.executeUpdate();
			
			ResultSet rs = pstmt.getGeneratedKeys();
			String pdNo = null;
			if (rs.next()) {
						pdNo = rs.getString(1);	
			} else {
				System.out.println("NO KEYS WERE GENERATED.");
			}
			petDailyVO.setPdNo(pdNo);
			return petDailyVO;
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
	public void update(PetDailyVO petDailyVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(4, petDailyVO.getPdNo());
			pstmt.setString(1, petDailyVO.getPetNo());
			pstmt.setString(2, petDailyVO.getPdClass());
			pstmt.setString(3, petDailyVO.getPdCont());
			
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
	public void delete(String pdNo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

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
	public PetDailyVO findByPrimaryKey(String pdNo) {
		
		PetDailyVO petDailyVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, pdNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				petDailyVO = new PetDailyVO();
				
				petDailyVO.setPdNo(rs.getString("PD_NO"));
				petDailyVO.setPetNo(rs.getString("PET_NO"));
				petDailyVO.setPdClass(rs.getString("PD_CLASS"));
				petDailyVO.setPdCont(rs.getString("PD_CONT"));
				petDailyVO.setEditTime(rs.getDate("EDIT_TIME"));
				
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
		return petDailyVO;
	}

	@Override
	public List<PetDailyVO> getAll() {
		List<PetDailyVO> list = new ArrayList<PetDailyVO>();
		PetDailyVO petDailyVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				petDailyVO = new PetDailyVO();
				petDailyVO.setPdNo(rs.getString("PD_NO"));
				petDailyVO.setPetNo(rs.getString("PET_NO"));
				petDailyVO.setPdClass(rs.getString("PD_CLASS"));
				petDailyVO.setPdCont(rs.getString("PD_CONT"));
				petDailyVO.setEditTime(rs.getDate("EDIT_TIME"));
				
				list.add(petDailyVO);
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
	public List<PetDailyVO> findByPetNo(String petNo) {
		List<PetDailyVO> list = new ArrayList<PetDailyVO>();
		PetDailyVO petDailyVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BY_PET_STMT);
			pstmt.setString(1, petNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				petDailyVO = new PetDailyVO();
				petDailyVO.setPdNo(rs.getString("PD_NO"));
				petDailyVO.setPetNo(rs.getString("PET_NO"));
				petDailyVO.setPdClass(rs.getString("PD_CLASS"));
				petDailyVO.setPdCont(rs.getString("PD_CONT"));
				petDailyVO.setEditTime(rs.getDate("EDIT_TIME"));
				
				list.add(petDailyVO);
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
