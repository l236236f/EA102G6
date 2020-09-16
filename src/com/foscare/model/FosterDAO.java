package com.foscare.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.pet.model.PetVO;


public class FosterDAO implements FosterDAOI{
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
			"INSERT INTO FOSTER_CARE(FOS_NO,MEM_NO,PET_NO,FOS_STARTTIME,FOS_ENDTIME,FOS_NRUN,FOS_SIZE,FOS_TYPE,FOS_REMARK,FOS_MONEY,FOS_STATUS,FOS_TIME,FOSM_EVAS)values('F'||LPAD(to_char(FOS_NO_ID.NEXTVAL), 3, '0'),?,?,?,?,?,?,?,?,?,'F0',sysdate,'-1')";
	private static final String UPDATE_PSTMT = 
			"UPDATE FOSTER_CARE SET PET_NO=?,FOSM_NO=?,FOS_STARTTIME=?,FOS_ENDTIME=?, FOS_NRUN=?, FOS_SIZE=?, FOS_TYPE=?, FOS_REMARK=?, FOS_MONEY=? WHERE FOS_NO=?";
	private static final String GET_ONE_PSTMT = 
			"SELECT * FROM FOSTER_CARE WHERE FOS_NO=?";
	private static final String GET_ALL_PSTMT = 
			"SELECT * FROM FOSTER_CARE";
	private static final String UPDATE_SIGNA = 
			"UPDATE FOSTER_CARE SET FOS_SIGNA=? WHERE FOS_NO=?";
	private static final String UPDATE_SIGNB = 
			"UPDATE FOSTER_CARE SET FOS_SIGNB=? WHERE FOS_NO=?";
	private static final String UPDATE_FOSSTATUS = 
			"UPDATE FOSTER_CARE SET FOS_STATUS=? WHERE FOS_NO=?";
	private static final String FOS_EVALUATION = 
			"UPDATE FOSTER_CARE SET FOSM_EVAS=?, FOSM_EVACON=? WHERE FOS_NO=?";
	private static final String GET_ALLBYMEM_PSTMT = 
			"SELECT * FROM FOSTER_CARE WHERE MEM_NO=? ORDER BY FOS_TIME DESC";
	private static final String GET_ALLBYFOSM_PSTMT = 
			"SELECT * FROM FOSTER_CARE WHERE FOSM_NO=? ORDER BY FOS_TIME DESC";
	private static final String UPDATE_EVARES = 
			"UPDATE FOSTER_CARE SET FOSM_EVARES=? WHERE FOS_NO=?";
	private static final String GET_PET = 
			"SELECT PET_NO,PET_NAME FROM PET WHERE MEM_NO=?";
	private static final String GET_TIMEOUT =
			"SELECT FOS_NO FROM FOSTER_CARE WHERE FOS_ENDTIME < SYSDATE + 1";
	
	@Override
	public void insert(FosterVO fosterVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_PSTMT);

			pstmt.setString(1, fosterVO.getMemNo());
			pstmt.setString(2, fosterVO.getPetNo());
			pstmt.setDate(3, fosterVO.getFosStartTime());
			pstmt.setDate(4, fosterVO.getFosEndTime());
			pstmt.setString(5, fosterVO.getFosnrun());
			pstmt.setString(6, fosterVO.getFosSize());
			pstmt.setString(7, fosterVO.getFosType());
			pstmt.setString(8, fosterVO.getFosRemark());
			pstmt.setInt(9, fosterVO.getFosMoney());

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
	public void update(FosterVO fosterVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_PSTMT);
			String UPDATE_PSTMT2 = 
					"UPDATE FOSTER_CARE SET PET_NO=?,SET FOSM_NO=?,FOS_STARTTIME=?,FOS_ENDTIME=?, FOS_NRUN=?, FOS_SIZE=?, FOS_TYPE=?, FOS_REMARK=?, FOS_MONEY=? WHERE FOS_NO=?";
			pstmt.setString(1, fosterVO.getPetNo());
			pstmt.setString(2, fosterVO.getFosmNo());
			pstmt.setDate(3, fosterVO.getFosStartTime());
			pstmt.setDate(4, fosterVO.getFosEndTime());
			pstmt.setString(5, fosterVO.getFosnrun());
			pstmt.setString(6, fosterVO.getFosSize());
			pstmt.setString(7, fosterVO.getFosType());
			pstmt.setString(8, fosterVO.getFosRemark());
			pstmt.setInt(9, fosterVO.getFosMoney());
			pstmt.setString(10, fosterVO.getFosNo());

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
	public FosterVO findByPrimaryKey(String fosNo) {
		FosterVO fosterVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_PSTMT);

			pstmt.setString(1, fosNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]붙О Domain objects
				fosterVO = new FosterVO();
				fosterVO.setFosNo(fosNo);
				fosterVO.setMemNo(rs.getString("MEM_NO"));
				fosterVO.setPetNo(rs.getString("PET_NO"));
				fosterVO.setFosmNo(rs.getString("FOSM_NO"));
				fosterVO.setFosStartTime(rs.getDate("FOS_STARTTIME"));
				fosterVO.setFosEndTime(rs.getDate("FOS_ENDTIME"));
				fosterVO.setFosnrun(rs.getString("FOS_NRUN"));
				fosterVO.setFosSize(rs.getString("FOS_SIZE"));
				fosterVO.setFosType(rs.getString("FOS_TYPE"));
				fosterVO.setFosSignA(rs.getBytes("FOS_SIGNA"));				
				fosterVO.setFosSignB(rs.getBytes("FOS_SIGNB"));
				fosterVO.setFosMoney(rs.getInt("FOS_MONEY"));
//				Integer.parseInt(rs.getString("FOS_MONEY"))
				fosterVO.setFosRemark(rs.getString("FOS_REMARK"));
				fosterVO.setFosStatus(rs.getString("FOS_STATUS"));
				fosterVO.setFosTime(rs.getTimestamp("FOS_TIME"));
				fosterVO.setFosmEvas(Double.parseDouble(rs.getString("FOSM_EVAS")));
				fosterVO.setFosmEvacon(rs.getString("FOSM_EVACON"));
				
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
		return fosterVO;
	}

	@Override
	public List<FosterVO> getAll() {
		List<FosterVO> list = new ArrayList<FosterVO>();
		FosterVO fosterVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_PSTMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]붙О Domain objects
				fosterVO = new FosterVO();
				fosterVO.setFosNo(rs.getString("FOS_NO"));
				fosterVO.setMemNo(rs.getString("MEM_NO"));
				fosterVO.setPetNo(rs.getString("PET_NO"));
				fosterVO.setFosmNo(rs.getString("FOSM_NO"));
				fosterVO.setFosStartTime(rs.getDate("FOS_STARTTIME"));
				fosterVO.setFosEndTime(rs.getDate("FOS_ENDTIME"));
				fosterVO.setFosnrun(rs.getString("FOS_NRUN"));
				fosterVO.setFosSize(rs.getString("FOS_SIZE"));
				fosterVO.setFosType(rs.getString("FOS_TYPE"));
				fosterVO.setFosSignA(rs.getBytes("FOS_SIGNA"));
				fosterVO.setFosSignB(rs.getBytes("FOS_SIGNB"));
				fosterVO.setFosMoney(Integer.parseInt(rs.getString("FOS_MONEY")));
				fosterVO.setFosRemark(rs.getString("FOS_REMARK"));
				fosterVO.setFosStatus(rs.getString("FOS_STATUS"));
				fosterVO.setFosTime(rs.getTimestamp("FOS_TIME"));
				fosterVO.setFosmEvas(rs.getDouble("FOSM_EVAS"));
				fosterVO.setFosmEvacon(rs.getString("FOSM_EVACON"));
				fosterVO.setFosmEvares(rs.getString("FOSM_EVARES"));
				list.add(fosterVO);
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} catch(NumberFormatException ne){
			throw new RuntimeException("A database error occured. "
					+ ne.getMessage());
		}finally {
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
	public void updateSign(byte[] sign,String fosNo,String AorB) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = (AorB.equals("A"))?con.prepareStatement(UPDATE_SIGNA):con.prepareStatement(UPDATE_SIGNB);
			
			pstmt.setBytes(1, sign);
			pstmt.setString(2, fosNo);

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
	public void updateStatus(String fosNo, String fosStatus) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_FOSSTATUS);
			
			pstmt.setString(1, fosStatus);
			pstmt.setString(2, fosNo);

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
	public void updateFosmres(String fosNo, String fosmEvares) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_EVARES);
			
			pstmt.setString(1, fosmEvares);
			pstmt.setString(2, fosNo);

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
	
	public void evaluation(String fosNo,Integer i,String eva) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FOS_EVALUATION);
			
			pstmt.setInt(1, i);
			pstmt.setString(2, eva);
			pstmt.setString(3, fosNo);

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
	public List<PetVO> findPetByMemNo(String memNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		List<PetVO> petList = new ArrayList<PetVO>();
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_PET);
			
			pstmt.setString(1, memNo);

			pstmt.executeQuery();

			rs = pstmt.getResultSet();
			PetVO pet = null;
			while(rs.next()) {
				pet = new PetVO();
				pet.setPetNo(rs.getString("PET_NO"));
				pet.setPetName(rs.getString("PET_NAME"));
				petList.add(pet);
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
		return petList;
	}
	
	@Override
	public List<FosterVO> getAllByMemNo(String memNo) {
		List<FosterVO> list = new ArrayList<FosterVO>();
		FosterVO fosterVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALLBYMEM_PSTMT);
			pstmt.setString(1, memNo);
			pstmt.executeQuery();

			rs = pstmt.getResultSet();
			while (rs.next()) {
				// empVO �]붙О Domain objects
				fosterVO = new FosterVO();
				fosterVO.setFosNo(rs.getString("FOS_NO"));
				fosterVO.setMemNo(rs.getString("MEM_NO"));
				fosterVO.setPetNo(rs.getString("PET_NO"));
				fosterVO.setFosmNo(rs.getString("FOSM_NO"));
				fosterVO.setFosStartTime(rs.getDate("FOS_STARTTIME"));
				fosterVO.setFosEndTime(rs.getDate("FOS_ENDTIME"));
				fosterVO.setFosnrun(rs.getString("FOS_NRUN"));
				fosterVO.setFosSize(rs.getString("FOS_SIZE"));
				fosterVO.setFosType(rs.getString("FOS_TYPE"));
				fosterVO.setFosSignA(rs.getBytes("FOS_SIGNA"));
				fosterVO.setFosSignB(rs.getBytes("FOS_SIGNB"));
				fosterVO.setFosMoney(rs.getInt("FOS_MONEY"));
				fosterVO.setFosRemark(rs.getString("FOS_REMARK"));
				fosterVO.setFosStatus(rs.getString("FOS_STATUS"));
				fosterVO.setFosTime(rs.getTimestamp("FOS_TIME"));
				fosterVO.setFosmEvas(rs.getDouble("FOSM_EVAS"));
				fosterVO.setFosmEvacon(rs.getString("FOSM_EVACON"));
				fosterVO.setFosmEvares(rs.getString("FOSM_EVARES"));
				list.add(fosterVO);
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} catch(NumberFormatException ne){
			throw new RuntimeException("A database error occured. "
					+ ne.getMessage());
		}finally {
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
	public List<FosterVO> getAllByFosmNo(String fosmNo) {
		List<FosterVO> list = new ArrayList<FosterVO>();
		FosterVO fosterVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALLBYFOSM_PSTMT);
			pstmt.setString(1, fosmNo);
			pstmt.executeQuery();

			rs = pstmt.getResultSet();
			while (rs.next()) {
				// empVO �]붙О Domain objects
				fosterVO = new FosterVO();
				fosterVO.setFosNo(rs.getString("FOS_NO"));
				fosterVO.setMemNo(rs.getString("MEM_NO"));
				fosterVO.setPetNo(rs.getString("PET_NO"));
				fosterVO.setFosmNo(rs.getString("FOSM_NO"));
				fosterVO.setFosStartTime(rs.getDate("FOS_STARTTIME"));
				fosterVO.setFosEndTime(rs.getDate("FOS_ENDTIME"));
				fosterVO.setFosnrun(rs.getString("FOS_NRUN"));
				fosterVO.setFosSize(rs.getString("FOS_SIZE"));
				fosterVO.setFosType(rs.getString("FOS_TYPE"));
				fosterVO.setFosSignA(rs.getBytes("FOS_SIGNA"));
				fosterVO.setFosSignB(rs.getBytes("FOS_SIGNB"));
				fosterVO.setFosMoney(rs.getInt("FOS_MONEY"));
				fosterVO.setFosRemark(rs.getString("FOS_REMARK"));
				fosterVO.setFosStatus(rs.getString("FOS_STATUS"));
				fosterVO.setFosTime(rs.getTimestamp("FOS_TIME"));
				fosterVO.setFosmEvas(rs.getDouble("FOSM_EVAS"));
				fosterVO.setFosmEvacon(rs.getString("FOSM_EVACON"));
				fosterVO.setFosmEvares(rs.getString("FOSM_EVARES"));
				list.add(fosterVO);
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} catch(NumberFormatException ne){
			throw new RuntimeException("A database error occured. "
					+ ne.getMessage());
		}finally {
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

	public void getAlltimeUp(){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_TIMEOUT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				updateStatus(rs.getString("FOS_NO"),"F7");
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
	
}
