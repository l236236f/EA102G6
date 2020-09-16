package com.sale_detail.model;

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

public class Sale_detailJNDIDAO implements Sale_detailDAO_interface{
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
			"INSERT INTO SALE_DETAIL (SP_NO,PROD_NO) VALUES (?,?)";
		private static final String GET_ALL_STMT = 
			"SELECT * FROM SALE_DETAIL ORDER BY SP_NO";
		private static final String GET_ONE_STMT = 
			"SELECT * FROM SALE_DETAIL WHERE SP_NO = ? AND PROD_NO = ?";
		private static final String DELETE = 
			"DELETE FROM SALE_DETAIL WHERE SP_NO = ? AND PROD_NO = ?";
		private static final String UPDATE = 
			"UPDATE SALE_DETAIL SET SP_NO= ?, PROD_NO= ? WHERE SP_NO = ? AND PROD_NO = ?";
		@Override
		public void insert(Sale_detailVO saleDetailVO) {
			
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);
		
				pstmt.setString(1, saleDetailVO.getSpNo());
				pstmt.setString(2, saleDetailVO.getProdNo());
			
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
		public void delete(String spNo, String prodNo) {
			
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(DELETE);

				pstmt.setString(1, spNo);
				pstmt.setString(2, prodNo);

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
		public Sale_detailVO findByPrimaryKey(String spNo, String prodNo) {
			
			Sale_detailVO saleDetailVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_STMT);

				pstmt.setString(1, spNo);
				pstmt.setString(2, prodNo);
				
				rs = pstmt.executeQuery();

				while (rs.next()) {
					saleDetailVO = new Sale_detailVO();
					saleDetailVO.setSpNo(rs.getString("SP_NO"));   
					saleDetailVO.setProdNo(rs.getString("PROD_NO"));   		
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
			return saleDetailVO;
		}
		@Override
		public List<Sale_detailVO> getAll() {
			
			List<Sale_detailVO> list = new ArrayList<Sale_detailVO>();
			Sale_detailVO saleDetailVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {

				con = ds.getConnection();
				
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					saleDetailVO = new Sale_detailVO();
					saleDetailVO.setSpNo(rs.getString("SP_NO"));   
					saleDetailVO.setProdNo(rs.getString("PROD_NO"));   	
					list.add(saleDetailVO);
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
