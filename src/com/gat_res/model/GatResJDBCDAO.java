package com.gat_res.model;

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

public class GatResJDBCDAO implements GatResDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/EA102G6");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO GAT_RESPONSE (RES_NO, GAT_NO, MEM_NO, RES_CONT)"
			+ "VALUES ('RS' || LPAD(TO_CHAR(RES_NO_SEQ.NEXTVAL), 3, '0'), ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM GAT_RESPONSE ORDER BY RES_NO";
	private static final String GET_ONE_STMT = "SELECT * FROM GAT_RESPONSE WHERE RES_NO = ?";
	private static final String DELETE = "DELETE FROM GAT_RESPONSE WHERE RES_NO = ?";
	private static final String UPDATE = "UPDATE GAT_RESPONSE SET RES_CONT = ? WHERE RES_NO = ?";
	private static final String REPLY = "UPDATE GAT_RESPONSE SET RES_REPLY = ?, RES_STATUS = 'RS1' WHERE RES_NO = ?";

	public void insert(GatResVO gatResVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, gatResVO.getGatNo());
			pstmt.setString(2, gatResVO.getMemNo());
			pstmt.setString(3, gatResVO.getResCont());

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

	public void update(GatResVO gatResVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, gatResVO.getResCont());
			pstmt.setString(2, gatResVO.getResNo());

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

	public void reply(GatResVO gatResVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(REPLY);

			pstmt.setString(1, gatResVO.getResReply());
			pstmt.setString(2, gatResVO.getResNo());

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

	public void delete(String resNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, resNo);

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

	public GatResVO findByPrimaryKey(String resNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		GatResVO gatResVO = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, resNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				gatResVO = new GatResVO();
				gatResVO.setResNo(rs.getString("RES_NO"));
				gatResVO.setGatNo(rs.getString("GAT_NO"));
				gatResVO.setMemNo(rs.getString("MEM_NO"));
				gatResVO.setResCont(rs.getString("RES_CONT"));
				gatResVO.setResTime(rs.getTimestamp("RES_TIME"));
				gatResVO.setResReply(rs.getString("RES_REPLY"));
				gatResVO.setResStatus(rs.getString("RES_STATUS"));
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
		return gatResVO;
	}

	public List<GatResVO> getAll() {
		List<GatResVO> list = new ArrayList<GatResVO>();
		GatResVO gatResVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				gatResVO = new GatResVO();
				gatResVO.setResNo(rs.getString("RES_NO"));
				gatResVO.setGatNo(rs.getString("GAT_NO"));
				gatResVO.setMemNo(rs.getString("MEM_NO"));
				gatResVO.setResCont(rs.getString("RES_CONT"));
				gatResVO.setResTime(rs.getTimestamp("RES_TIME"));
				gatResVO.setResReply(rs.getString("RES_REPLY"));
				gatResVO.setResStatus(rs.getString("RES_STATUS"));
				list.add(gatResVO);
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
