package com.sprod_order.model;

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

import com.shop_product.model.Shop_productVO;

public class Sprod_orderJNDIDAO implements Sprod_orderDAO_interface{
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
			"INSERT INTO SPROD_ORDER (ORDER_NO, MEM_NO, ORDER_TIME, TRAN_METHOD, TRAN_ADD, ADDRESSEE_NAME, ADDRESSEE_MAIL, ORDER_TOTAL, ORDER_STATUS, SP_NO) VALUES ('SO'||LPAD(TO_CHAR(SO_SEQ.NEXTVAL),3,'0'), ?,SYSDATE, ?, ?, ?, ?, ?, ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT * FROM SPROD_ORDER ORDER BY ORDER_NO";
		private static final String GET_ONE_STMT = 
			"SELECT * FROM SPROD_ORDER WHERE ORDER_NO = ?";
		private static final String DELETE = 
			"DELETE FROM SPROD_ORDER WHERE ORDER_NO = ?";
		private static final String UPDATE = 
			"UPDATE SPROD_ORDER SET MEM_NO=?, TRAN_METHOD=?, TRAN_ADD=?, ADDRESSEE_NAME=?, ADDRESSEE_MAIL=?, ORDER_TOTAL=?, ORDER_STATUS=?, SP_NO=? WHERE ORDER_NO = ?";
		private static final String GET_ONE_MEM = 
			"SELECT * FROM SPROD_ORDER WHERE MEM_NO = ?";
		@Override
		public void insert(Sprod_orderVO sprodOrderVO) {
			
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);
				
				pstmt.setString(1, sprodOrderVO.getMemNo());				
				pstmt.setInt(2, sprodOrderVO.getTranMethod());
				pstmt.setString(3, sprodOrderVO.getTranAdd());
				pstmt.setString(4, sprodOrderVO.getAddresseeName());			
				pstmt.setString(5, sprodOrderVO.getAddresseeMail());
				pstmt.setInt(6, sprodOrderVO.getOrderTotal());
				pstmt.setInt(7, sprodOrderVO.getOrderStatus());
				pstmt.setString(8, sprodOrderVO.getSpNo());
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
		public void update(Sprod_orderVO sprodOrderVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE);
				
				pstmt.setString(1, sprodOrderVO.getMemNo());
				pstmt.setInt(2, sprodOrderVO.getTranMethod());
				pstmt.setString(3, sprodOrderVO.getTranAdd());
				pstmt.setString(4, sprodOrderVO.getAddresseeName());
				pstmt.setString(5, sprodOrderVO.getAddresseeMail());
				pstmt.setInt(6, sprodOrderVO.getOrderTotal());
				pstmt.setInt(7, sprodOrderVO.getOrderStatus());
				pstmt.setString(8, sprodOrderVO.getSpNo());
				pstmt.setString(9, sprodOrderVO.getOrderNo());

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
		public void delete(String orderNo) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(DELETE);
				
				pstmt.setString(1, orderNo);

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
		public Sprod_orderVO findByPrimaryKey(String orderNo) {


			Sprod_orderVO sprodOrderVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
		
			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_STMT);
				
				pstmt.setString(1, orderNo);
	
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					
					sprodOrderVO = new Sprod_orderVO();
					sprodOrderVO.setOrderNo(rs.getString("ORDER_NO"));
					sprodOrderVO.setMemNo(rs.getString("MEM_NO"));
					sprodOrderVO.setOrderTime(rs.getDate("ORDER_TIME"));
					sprodOrderVO.setTranMethod(new Integer(rs.getInt("TRAN_METHOD")));
					sprodOrderVO.setTranAdd(rs.getString("TRAN_ADD"));
					sprodOrderVO.setAddresseeName(rs.getString("ADDRESSEE_NAME"));
					sprodOrderVO.setAddresseeMail(rs.getString("ADDRESSEE_MAIL"));
					sprodOrderVO.setOrderTotal(new Integer(rs.getInt("ORDER_TOTAL")));
					sprodOrderVO.setOrderStatus(new Integer(rs.getInt("ORDER_STATUS")));
					sprodOrderVO.setSpNo(rs.getString("SP_NO"));		
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
			return sprodOrderVO;
		}
		@Override
		public List<Sprod_orderVO> getAll() {

			List<Sprod_orderVO> list = new ArrayList<Sprod_orderVO>();
			Sprod_orderVO sprodOrderVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {

				con = ds.getConnection();
				
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					sprodOrderVO = new Sprod_orderVO();
					sprodOrderVO.setOrderNo(rs.getString("ORDER_NO"));
					sprodOrderVO.setMemNo(rs.getString("MEM_NO"));
					sprodOrderVO.setOrderTime(rs.getDate("ORDER_TIME"));
					sprodOrderVO.setTranMethod(new Integer(rs.getInt("TRAN_METHOD")));
					sprodOrderVO.setTranAdd(rs.getString("TRAN_ADD"));
					sprodOrderVO.setAddresseeName(rs.getString("ADDRESSEE_NAME"));
					sprodOrderVO.setAddresseeMail(rs.getString("ADDRESSEE_MAIL"));
					sprodOrderVO.setOrderTotal(new Integer(rs.getInt("ORDER_TOTAL")));
					sprodOrderVO.setOrderStatus(new Integer(rs.getInt("ORDER_STATUS")));
					sprodOrderVO.setSpNo(rs.getString("SP_NO"));		
					list.add(sprodOrderVO);
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
		public List<Sprod_orderVO> findByMemNo(String memNo) {
			List<Sprod_orderVO> list = new ArrayList<Sprod_orderVO>();
			Sprod_orderVO sprodOrderVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_MEM);

				pstmt.setString(1, memNo);
				
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					sprodOrderVO = new Sprod_orderVO();
					sprodOrderVO.setOrderNo(rs.getString("ORDER_NO"));
					sprodOrderVO.setMemNo(rs.getString("MEM_NO"));
					sprodOrderVO.setOrderTime(rs.getDate("ORDER_TIME"));
					sprodOrderVO.setTranMethod(new Integer(rs.getInt("TRAN_METHOD")));
					sprodOrderVO.setTranAdd(rs.getString("TRAN_ADD"));
					sprodOrderVO.setAddresseeName(rs.getString("ADDRESSEE_NAME"));
					sprodOrderVO.setAddresseeMail(rs.getString("ADDRESSEE_MAIL"));
					sprodOrderVO.setOrderTotal(new Integer(rs.getInt("ORDER_TOTAL")));
					sprodOrderVO.setOrderStatus(new Integer(rs.getInt("ORDER_STATUS")));
					sprodOrderVO.setSpNo(rs.getString("SP_NO"));		
					list.add(sprodOrderVO);
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
}
