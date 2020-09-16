package com.fosmon.model;

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

public class FosmDAO implements FosmDAOI{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/EA102G6");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_PSTMT = 
			"INSERT INTO FOSM_HOME(FOSM_NO,MEM_NO,FOSM_STAR,FOSM_EVACOUNT,FOSM_LAT,FOSM_LNG)VALUES('FM'||LPAD(to_char(FOSM_NO_ID.NEXTVAL), 3, '0'),?,0,0,?,?)";
	private static final String UPDATE_PSTMT = 
			"UPDATE FOSM_HOME SET FOSM_PETTYPE=?,FOSM_PETSIZE=?,FOSM_NRUN=?,FOSM_CONTAIN=? WHERE FOSM_NO=?";
	private static final String SELECT_ONEBYMEMNO = 
			"SELECT * FROM FOSM_HOME WHERE MEM_NO=?";
	private static final String SELECT_ONEBYFOSMNO = 
			"SELECT * FROM FOSM_HOME WHERE FOSM_NO=?";
	private static final String SELECT_ALL = 
			"SELECT * FROM FOSM_HOME";
	private static final String UPDATE_STAR=
			"UPDATE FOSM_HOME SET FOSM_STAR=?,FOSM_EVACOUNT=? WHERE FOSM_NO=?";
	@Override
 	public void insert(FosmVO fosmVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt =con.prepareStatement(INSERT_PSTMT);
			
			pstmt.setString(1, fosmVO.getMemNo());
			pstmt.setDouble(2, fosmVO.getFosmLat());
			pstmt.setDouble(3, fosmVO.getFosmLng());
			
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
	public void update(FosmVO fosmVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_PSTMT);
			pstmt.setString(1, fosmVO.getFosmPetType());
			pstmt.setString(2, fosmVO.getFosmPetSize());
			pstmt.setString(3, fosmVO.getFosmnrun());
			pstmt.setString(4, fosmVO.getFosmContain());
			pstmt.setString(5, fosmVO.getFosmNo());
			
			pstmt.executeUpdate();
			// Handle any driver errors
		}  catch (SQLException se) {
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
	public FosmVO findByMemNo(String memNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		FosmVO fosmVO = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(SELECT_ONEBYMEMNO);
			
			pstmt.setString(1, memNo);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				fosmVO = new FosmVO();
				fosmVO.setFosmNo(rs.getString("FOSM_NO"));
				fosmVO.setMemNo(rs.getString("MEM_NO"));
				fosmVO.setFosmnrun(rs.getString("FOSM_NRUN"));
				fosmVO.setFosmPetType(rs.getString("FOSM_PETTYPE"));
				fosmVO.setFosmPetSize(rs.getString("FOSM_PETSIZE"));
				fosmVO.setFosmContain(rs.getString("FOSM_CONTAIN"));
				fosmVO.setFosStar(rs.getInt("FOSM_STAR"));
				fosmVO.setFosmEvacount(rs.getInt("FOSM_EVACOUNT"));
				fosmVO.setFosmLat(rs.getDouble("FOSM_LAT"));
				fosmVO.setFosmLng(rs.getDouble("FOSM_LNG"));
				
			}
			
			// Handle any driver errors
		}  catch (SQLException se) {
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
		return fosmVO;
	}
	
	@Override
	public FosmVO findByFosmNo(String fosmNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		FosmVO fosmVO = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(SELECT_ONEBYFOSMNO);
			
			pstmt.setString(1, fosmNo);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				fosmVO = new FosmVO();
				fosmVO.setFosmNo(rs.getString("FOSM_NO"));
				fosmVO.setMemNo(rs.getString("MEM_NO"));
				fosmVO.setFosmnrun(rs.getString("FOSM_NRUN"));
				fosmVO.setFosmPetType(rs.getString("FOSM_PETTYPE"));
				fosmVO.setFosmPetSize(rs.getString("FOSM_PETSIZE"));
				fosmVO.setFosmContain(rs.getString("FOSM_CONTAIN"));
				fosmVO.setFosStar(rs.getInt("FOSM_STAR"));
				fosmVO.setFosmEvacount(rs.getInt("FOSM_EVACOUNT"));
				fosmVO.setFosmLat(rs.getDouble("FOSM_LAT"));
				fosmVO.setFosmLng(rs.getDouble("FOSM_LNG"));
				
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
		return fosmVO;
	}
	
	@Override
	public List<FosmVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<FosmVO> list = new ArrayList<FosmVO>();
		FosmVO fosmVO = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(SELECT_ALL);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				fosmVO = new FosmVO();
				fosmVO.setFosmNo(rs.getString("FOSM_NO"));
				fosmVO.setMemNo(rs.getString("MEM_NO"));
				fosmVO.setFosmnrun(rs.getString("FOSM_NRUN"));
				fosmVO.setFosmPetType(rs.getString("FOSM_PETTYPE"));
				fosmVO.setFosmPetSize(rs.getString("FOSM_PETSIZE"));
				fosmVO.setFosmContain(rs.getString("FOSM_CONTAIN"));
				fosmVO.setFosStar(rs.getInt("FOSM_STAR"));
				fosmVO.setFosmEvacount(rs.getInt("FOSM_EVACOUNT"));
				fosmVO.setFosmLat(rs.getDouble("FOSM_LAT"));
				fosmVO.setFosmLng(rs.getDouble("FOSM_LNG"));
				list.add(fosmVO);
			}
			// Handle any driver errors
		}  catch (SQLException se) {
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
	public void updateStar(FosmVO fosmVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STAR);
			pstmt.setInt(1, fosmVO.getFosStar());
			pstmt.setInt(2, fosmVO.getFosmEvacount());
			pstmt.setString(3, fosmVO.getFosmNo());
			
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


