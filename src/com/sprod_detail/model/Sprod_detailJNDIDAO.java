package com.sprod_detail.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.shop_product.model.Shop_productJNDIDAO;

public class Sprod_detailJNDIDAO implements Sprod_detailDAO_interface{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/EA102G6");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_ORDER1_STMT = 
			"INSERT INTO SPROD_DETAIL (ORDER_NO,PROD_NO,QUANTITY,EVA_TIME) VALUES (?,?,?,SYSDATE)";
		private static final String GET_ALL_STMT = 
			"SELECT * FROM SPROD_DETAIL ORDER BY ORDER_NO";
		private static final String GET_ONE_STMT = 
			"SELECT * FROM SPROD_DETAIL WHERE ORDER_NO = ? AND PROD_NO = ?";
		private static final String DELETE = 
			"DELETE FROM SPROD_DETAIL WHERE ORDER_NO = ? AND PROD_NO = ?";
		private static final String FIND_ONE_ORDER_DETAIL =
			"SELECT * FROM SPROD_DETAIL WHERE ORDER_NO = ?";
		private static final String UPDATE = 
			"UPDATE SPROD_DETAIL SET EVA_STAR=?, EVA_CONT=? WHERE ORDER_NO = ? AND PROD_NO = ?";
		@Override
		public void insert(Sprod_detailVO sprodDetailVO) {
			
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_ORDER1_STMT);
				
				pstmt.setString(1, sprodDetailVO.getOrderNo());
				pstmt.setString(2, sprodDetailVO.getProdNo());
				pstmt.setInt(3, sprodDetailVO.getQuantity());			
				
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
		public void delete(String orderNo, String prodNo) {
			
			Connection con = null;
			PreparedStatement pstmt = null;			
			
			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(DELETE);

				pstmt.setString(1, orderNo);
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
		public Sprod_detailVO findByPrimaryKey(String orderNo, String prodNo) {

			Sprod_detailVO sprodDetailVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_STMT);
				
				pstmt.setString(1, orderNo);
				pstmt.setString(2, prodNo);
				
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					sprodDetailVO = new Sprod_detailVO();
					sprodDetailVO.setOrderNo(rs.getString("ORDER_NO"));   
					sprodDetailVO.setProdNo(rs.getString("PROD_NO")); 
					sprodDetailVO.setQuantity(rs.getInt("QUANTITY")); 
					sprodDetailVO.setEvaTime(rs.getDate("EVA_TIME")); 
					sprodDetailVO.setEvaStar(rs.getInt("EVA_STAR")); 
					sprodDetailVO.setEvaCont(rs.getString("EVA_CONT")); 
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
			return sprodDetailVO;
		}
		@Override
		public List<Sprod_detailVO> getAll() {
			
			List<Sprod_detailVO> list = new ArrayList<Sprod_detailVO>();
			Sprod_detailVO sprodDetailVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {

				con = ds.getConnection();
				
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					// empVO 也稱為 Domain objects
					sprodDetailVO = new Sprod_detailVO();
					sprodDetailVO.setOrderNo(rs.getString("ORDER_NO"));   
					sprodDetailVO.setProdNo(rs.getString("PROD_NO"));   
					sprodDetailVO.setQuantity(rs.getInt("QUANTITY"));
					sprodDetailVO.setEvaTime(rs.getDate("EVA_TIME"));
					sprodDetailVO.setEvaStar(rs.getInt("EVA_STAR"));
					sprodDetailVO.setEvaCont(rs.getString("EVA_CONT"));
					
					list.add(sprodDetailVO); 
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
//		取得訂單的所有明細資料
		public List<Sprod_detailVO> findOneOrderDetail(String orderNo) {
System.out.println(orderNo);
			List<Sprod_detailVO> list = new ArrayList<Sprod_detailVO>();
			Sprod_detailVO sprodDetailVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
	
			try {
				con = ds.getConnection();	
				
				pstmt = con.prepareStatement(FIND_ONE_ORDER_DETAIL);
				pstmt.setString(1, orderNo);
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					// empVO 也稱為 Domain objects
					sprodDetailVO = new Sprod_detailVO();
					sprodDetailVO.setOrderNo(rs.getString("ORDER_NO"));   
					sprodDetailVO.setProdNo(rs.getString("PROD_NO"));   
					sprodDetailVO.setQuantity(rs.getInt("QUANTITY"));
					sprodDetailVO.setEvaTime(rs.getDate("EVA_TIME"));
					sprodDetailVO.setEvaStar(rs.getInt("EVA_STAR"));
					sprodDetailVO.setEvaCont(rs.getString("EVA_CONT"));
					
					list.add(sprodDetailVO);
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
		public void update(Sprod_detailVO sprodDetailVO) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE);


				pstmt.setInt(1, sprodDetailVO.getEvaStar());
				pstmt.setString(2, sprodDetailVO.getEvaCont());
				pstmt.setString(3, sprodDetailVO.getOrderNo());
				pstmt.setString(4, sprodDetailVO.getProdNo());

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
		
		
		public static void main(String[] args) {
		
				Sprod_detailJNDIDAO dao = new Sprod_detailJNDIDAO();

//				 新增
//				Sprod_detailVO sprodDetailVO1 = new Sprod_detailVO();
//					
//				sprodDetailVO1.setOrderNo("SO099");
//				sprodDetailVO1.setProdNo("S099");
//				sprodDetailVO1.setQuantity(10);
//
//		
//				dao.insert(sprodDetailVO1);
//				System.out.println("新增成功");
//				// 修改
//				Sprod_detailVO sprodDetailVO2 = new Sprod_detailVO();
//				
//				sprodDetailVO2.setEvaStar(1);
//				sprodDetailVO2.setEvaCont("我好棒");
//				sprodDetailVO2.setOrderNo("SO001");
//				sprodDetailVO2.setProdNo("S001");
//
//				
//				dao.update(sprodDetailVO2);
//				System.out.println("修改成功");
//				
//				// 刪除
//				dao.delete("SO002","S001");
//				System.out.println("刪除成功");
				// 查詢
				Sprod_detailVO sprodDetailVO3 = dao.findByPrimaryKey("SO001", "S001");
				System.out.print(sprodDetailVO3.getEvaCont() + ",");
				System.out.print(sprodDetailVO3.getOrderNo() + ",");
				System.out.print(sprodDetailVO3.getProdNo() + ",");
				System.out.print(sprodDetailVO3.getEvaStar() + ",");
				System.out.print(sprodDetailVO3.getEvaTime() + ",");
				System.out.print(sprodDetailVO3.getQuantity());
				System.out.println("---------------------");

				// 查詢
				List<Sprod_detailVO> list = dao.getAll();
				for (Sprod_detailVO aSprod_detailVO : list) {
					System.out.print(aSprod_detailVO.getEvaCont() + ",");
					System.out.print(aSprod_detailVO.getOrderNo() + ",");
					System.out.print(aSprod_detailVO.getProdNo() + ",");
					System.out.print(aSprod_detailVO.getEvaStar() + ",");
					System.out.print(aSprod_detailVO.getEvaTime() + ",");
					System.out.print(aSprod_detailVO.getQuantity());
					
					System.out.println();
				}
			}

		
}
