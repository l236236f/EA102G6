package com.gat_eva.model;

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

public class GatEvaJDBCDAO implements GatEvaDAO_interface{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/EA102G6");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO GAT_EVALUATION (GAT_NO, MEM_NO, GAT_EVA) VALUES (?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM GAT_EVALUATION ORDER BY MEM_NO";
	private static final String GET_ONE_STMT = "SELECT * FROM GAT_EVALUATION WHERE MEM_NO = ? AND GAT_NO = ?";
	private static final String UPDATE = "UPDATE GAT_EVALUATION SET GAT_EVA = ? WHERE GAT_NO = ? AND MEM_NO = ?";
	
	@Override
	public void insert(GatEvaVO gatEvaVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, gatEvaVO.getGatNo());
			pstmt.setString(2, gatEvaVO.getMemNo());
			pstmt.setDouble(3, gatEvaVO.getGatEva());
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
	public void update(GatEvaVO gatEvaVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setDouble(1, gatEvaVO.getGatEva());
			pstmt.setString(2, gatEvaVO.getGatNo());
			pstmt.setString(3, gatEvaVO.getMemNo());
			
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
	public GatEvaVO findByPrimaryKey(String memNo, String gatNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		GatEvaVO gatEvaVO = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, memNo);
			pstmt.setString(2, gatNo);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				gatEvaVO = new GatEvaVO();
				gatEvaVO.setGatNo(rs.getString("GAT_NO"));
				gatEvaVO.setMemNo(rs.getString("MEM_NO"));
				gatEvaVO.setGatEva(rs.getDouble("GAT_EVA"));

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
		return gatEvaVO;
	}
	
	@Override
	public List<GatEvaVO> getAll() {
		List<GatEvaVO> list = new ArrayList<GatEvaVO>();
		GatEvaVO gatEvaVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				gatEvaVO = new GatEvaVO();
				gatEvaVO.setGatNo(rs.getString("GAT_NO"));
				gatEvaVO.setMemNo(rs.getString("MEM_NO"));
				gatEvaVO.setGatEva(rs.getDouble("GAT_EVA"));
				list.add(gatEvaVO);
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
}
