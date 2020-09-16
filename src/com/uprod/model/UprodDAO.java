package com.uprod.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.art_response.model.ArtResponseVO;

import java.sql.*;

public class UprodDAO implements UprodDAO_interface {
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
//	  "INSERT INTO USED_PRODUCT (PROD_NO,SELLER_NO,PROD_NAME,PROD_INTRO,PRICE,PROD_STATUS,PROD_TYPE)"
//		   + " VALUES " + "('U'||LPAD(UPROD_PROD_NO.NEXTVAL,3,'0'),?,?,?,?,?,?)";
	private static final String INSERT_STMT = 
			  "INSERT INTO USED_PRODUCT (PROD_NO,SELLER_NO,PROD_NAME,PROD_INTRO,PRICE,PROD_STATUS,PROD_TYPE,PHOTO)"
				   + " VALUES " + "('U'||LPAD(UPROD_PROD_NO.NEXTVAL,3,'0'),?,?,?,?,?,?,?)";
//	private static final String UPDATE = 
//		"UPDATE USED_PRODUCT set PROD_NAME=?, PROD_INTRO=?, PRICE=?, INCREASE_TIME=?, PROD_TYPE=? where PROD_NO = ?";
	private static final String UPDATE = 
			"UPDATE USED_PRODUCT set PROD_NAME=?, PROD_INTRO=?, PRICE=?, INCREASE_TIME=?, PROD_STATUS=?, PROD_TYPE=?, PHOTO=? where PROD_NO = ?";
	private static final String BUY =
			"UPDATE USED_PRODUCT set CUST_NO=?, PROD_STATUS=?,ORDER_STATUS=?,RECEIVE_STATUS=?, ORDER_TIME=?, TRAN_METHOD=?,TRAN_ADDR=? where PROD_NO = ?";
	private static final String GET_ALL_STMT = 
			"SELECT * FROM USED_PRODUCT ORDER BY PROD_NO";
	private static final String GET_ALL_ON_SHELF = 
			"SELECT * FROM USED_PRODUCT WHERE PROD_STATUS='PS0' ORDER BY PROD_NO";
	private static final String GET_ALL_STMT_SELLER = 
			"SELECT * FROM USED_PRODUCT WHERE SELLER_NO=? ORDER BY PROD_NO " ;
	private static final String GET_ALL_STMT_SELLER_DETAIL = 
			 "SELECT * FROM USED_PRODUCT WHERE SELLER_NO=? AND PROD_STATUS='PS2'  ORDER BY PROD_NO " ;
	private static final String SHIPMENT = 
		    "UPDATE USED_PRODUCT set ORDER_STATUS= 'OS1' where PROD_NO = ?";
	private static final String RECEIVE = 
		    "UPDATE USED_PRODUCT set RECEIVE_STATUS= 'RS1' where PROD_NO = ?";
	private static final String GET_ALL_STMT_CUSTOMER = 
			"SELECT * FROM USED_PRODUCT WHERE CUST_NO=? ";
//	 "SELECT PROD_NO,SELLER_NO,CUST_NO,PROD_NAME,PROD_INTRO,PRICE,INCREASE_TIME,PROD_STATUS,ORDER_STATUS,RECEIVE_STATUS,ORDER_TIME,"
//	       + "TRAN_METHOD,TRAN_ADDR,PROD_TYPE,EVA_STAR FROM USED_PRODUCT order by PROD_NO";
	private static final String GET_ONE_STMT = 
			"SELECT * FROM USED_PRODUCT WHERE PROD_NO=?";	
	
	private static final String GET_ALL_ON_SHELF_BY_A = 
			"SELECT * FROM USED_PRODUCT WHERE PROD_STATUS='PS0' AND PROD_TYPE='A' ORDER BY PROD_NO";
	private static final String GET_ALL_ON_SHELF_BY_B = 
			"SELECT * FROM USED_PRODUCT WHERE PROD_STATUS='PS0' AND PROD_TYPE='B' ORDER BY PROD_NO";
	private static final String GET_ALL_ON_SHELF_BY_C = 
			"SELECT * FROM USED_PRODUCT WHERE PROD_STATUS='PS0' AND PROD_TYPE='C' ORDER BY PROD_NO";
	
	
	@Override
	public void insert(UprodVO UprodVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, UprodVO.getSellerNo());
			pstmt.setString(2, UprodVO.getProdName());
			pstmt.setString(3, UprodVO.getProdIntro());
			pstmt.setInt(4, UprodVO.getPrice());
			pstmt.setString(5, UprodVO.getProdStatus());
			pstmt.setString(6, UprodVO.getProdType());
			pstmt.setBytes(7,UprodVO.getPhoto());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void update(UprodVO uprodVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
          
			pstmt.setString(1, uprodVO.getProdName());
			pstmt.setString(2, uprodVO.getProdIntro());
			pstmt.setInt(3, uprodVO.getPrice());
			pstmt.setTimestamp(4, uprodVO.getIncreaseTime());
			pstmt.setString(5, uprodVO.getProdStatus());
			pstmt.setString(6, uprodVO.getProdType());
			pstmt.setBytes(7,uprodVO.getPhoto());
			pstmt.setString(8,uprodVO.getProdNo());
			
   
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
	public void buy(UprodVO uprodVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(BUY);
			
			pstmt.setString(1, uprodVO.getCustNo());
			pstmt.setString(2, uprodVO.getProdStatus());
			pstmt.setString(3, uprodVO.getOrderStatus());
			pstmt.setString(4, uprodVO.getReceiveStatus());
			pstmt.setTimestamp(5, uprodVO.getOrderTime());
			pstmt.setString(6, uprodVO.getTranMethod());
			pstmt.setString(7, uprodVO.getTranAddr());
			pstmt.setString(8,uprodVO.getProdNo());
			
   
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
	public UprodVO findByPrimaryKey(String ProdNo) {

		UprodVO uprodVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, ProdNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				uprodVO = new UprodVO();
				uprodVO.setProdNo(rs.getString("PROD_NO"));
				uprodVO.setSellerNo(rs.getString("SELLER_NO"));
				uprodVO.setCustNo(rs.getString("CUST_NO"));
				uprodVO.setProdName(rs.getString("PROD_NAME"));
				uprodVO.setProdIntro(rs.getString("PROD_INTRO"));
				uprodVO.setPrice(rs.getInt("PRICE"));
				uprodVO.setIncreaseTime(rs.getTimestamp("INCREASE_TIME"));
				uprodVO.setProdStatus(rs.getString("PROD_STATUS"));
				uprodVO.setOrderStatus(rs.getString("ORDER_STATUS"));
				uprodVO.setReceiveStatus(rs.getString("RECEIVE_STATUS"));
				uprodVO.setOrderTime(rs.getTimestamp("ORDER_TIME"));
				uprodVO.setTranMethod(rs.getString("TRAN_METHOD"));
				uprodVO.setTranAddr(rs.getString("TRAN_ADDR"));
				uprodVO.setProdType(rs.getString("PROD_TYPE"));
				uprodVO.setEvaStar(rs.getInt("EVA_STAR"));
				uprodVO.setPhoto(rs.getBytes("PHOTO"));
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
		return uprodVO;
	}

	@Override
	public List<UprodVO> getAll() {
		List<UprodVO> list = new ArrayList<UprodVO>();
		UprodVO uprodVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				uprodVO = new UprodVO();
				uprodVO.setProdNo(rs.getString("PROD_NO"));
				uprodVO.setSellerNo(rs.getString("SELLER_NO"));
				uprodVO.setCustNo(rs.getString("CUST_NO"));
				uprodVO.setProdName(rs.getString("PROD_NAME"));
				uprodVO.setProdIntro(rs.getString("PROD_INTRO"));
				uprodVO.setPrice(rs.getInt("PRICE"));
				uprodVO.setIncreaseTime(rs.getTimestamp("INCREASE_TIME"));
				uprodVO.setProdStatus(rs.getString("PROD_STATUS"));
				uprodVO.setOrderStatus(rs.getString("ORDER_STATUS"));
				uprodVO.setReceiveStatus(rs.getString("RECEIVE_STATUS"));
				uprodVO.setOrderTime(rs.getTimestamp("ORDER_TIME"));
				uprodVO.setTranMethod(rs.getString("TRAN_METHOD"));
				uprodVO.setTranAddr(rs.getString("TRAN_ADDR"));
				uprodVO.setProdType(rs.getString("PROD_TYPE"));
				uprodVO.setEvaStar(rs.getInt("EVA_STAR"));
				uprodVO.setPhoto(rs.getBytes("PHOTO"));
				list.add(uprodVO);
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
		return list;
	}
	
	@Override
	public List<UprodVO> getAllOnShelf() {
		List<UprodVO> list = new ArrayList<UprodVO>();
		UprodVO uprodVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_ON_SHELF);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				uprodVO = new UprodVO();
				uprodVO.setProdNo(rs.getString("PROD_NO"));
				uprodVO.setSellerNo(rs.getString("SELLER_NO"));
				uprodVO.setCustNo(rs.getString("CUST_NO"));
				uprodVO.setProdName(rs.getString("PROD_NAME"));
				uprodVO.setProdIntro(rs.getString("PROD_INTRO"));
				uprodVO.setPrice(rs.getInt("PRICE"));
				uprodVO.setIncreaseTime(rs.getTimestamp("INCREASE_TIME"));
				uprodVO.setProdStatus(rs.getString("PROD_STATUS"));
				uprodVO.setOrderStatus(rs.getString("ORDER_STATUS"));
				uprodVO.setReceiveStatus(rs.getString("RECEIVE_STATUS"));
				uprodVO.setOrderTime(rs.getTimestamp("ORDER_TIME"));
				uprodVO.setTranMethod(rs.getString("TRAN_METHOD"));
				uprodVO.setTranAddr(rs.getString("TRAN_ADDR"));
				uprodVO.setProdType(rs.getString("PROD_TYPE"));
				uprodVO.setEvaStar(rs.getInt("EVA_STAR"));
				uprodVO.setPhoto(rs.getBytes("PHOTO"));
				list.add(uprodVO);
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
		return list;
	}
	 
	@Override
	public List<UprodVO> getAllBySeller(String SellerNo) {
		List<UprodVO> list = new ArrayList<UprodVO>();
		UprodVO uprodVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT_SELLER);
			pstmt.setString(1,SellerNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				uprodVO = new UprodVO();
				uprodVO.setProdNo(rs.getString("PROD_NO"));
				uprodVO.setSellerNo(rs.getString("SELLER_NO"));
				uprodVO.setCustNo(rs.getString("CUST_NO"));
				uprodVO.setProdName(rs.getString("PROD_NAME"));
				uprodVO.setProdIntro(rs.getString("PROD_INTRO"));
				uprodVO.setPrice(rs.getInt("PRICE"));
				uprodVO.setIncreaseTime(rs.getTimestamp("INCREASE_TIME"));
				uprodVO.setProdStatus(rs.getString("PROD_STATUS"));
				uprodVO.setOrderStatus(rs.getString("ORDER_STATUS"));
				uprodVO.setReceiveStatus(rs.getString("RECEIVE_STATUS"));
				uprodVO.setOrderTime(rs.getTimestamp("ORDER_TIME"));
				uprodVO.setTranMethod(rs.getString("TRAN_METHOD"));
				uprodVO.setTranAddr(rs.getString("TRAN_ADDR"));
				uprodVO.setProdType(rs.getString("PROD_TYPE"));
				uprodVO.setEvaStar(rs.getInt("EVA_STAR"));
				uprodVO.setPhoto(rs.getBytes("PHOTO"));
				list.add(uprodVO);
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
		return list;
		
	}
	
	@Override
	public List<UprodVO> getAllBySellerDetail(String SellerNo) {
		List<UprodVO> list = new ArrayList<UprodVO>();
		UprodVO uprodVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT_SELLER_DETAIL);
			pstmt.setString(1,SellerNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				uprodVO = new UprodVO();
				uprodVO.setProdNo(rs.getString("PROD_NO"));
				uprodVO.setSellerNo(rs.getString("SELLER_NO"));
				uprodVO.setCustNo(rs.getString("CUST_NO"));
				uprodVO.setProdName(rs.getString("PROD_NAME"));
				uprodVO.setProdIntro(rs.getString("PROD_INTRO"));
				uprodVO.setPrice(rs.getInt("PRICE"));
				uprodVO.setIncreaseTime(rs.getTimestamp("INCREASE_TIME"));
				uprodVO.setProdStatus(rs.getString("PROD_STATUS"));
				uprodVO.setOrderStatus(rs.getString("ORDER_STATUS"));
				uprodVO.setReceiveStatus(rs.getString("RECEIVE_STATUS"));
				uprodVO.setOrderTime(rs.getTimestamp("ORDER_TIME"));
				uprodVO.setTranMethod(rs.getString("TRAN_METHOD"));
				uprodVO.setTranAddr(rs.getString("TRAN_ADDR"));
				uprodVO.setProdType(rs.getString("PROD_TYPE"));
				uprodVO.setEvaStar(rs.getInt("EVA_STAR"));
				uprodVO.setPhoto(rs.getBytes("PHOTO"));
				list.add(uprodVO);
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
		return list;
	}
	
	@Override
	public void shipment(UprodVO uprodVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(SHIPMENT);
	
			pstmt.setString(1,uprodVO.getProdNo());
			
   
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
	public void receive(UprodVO uprodVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(RECEIVE);
	
			pstmt.setString(1,uprodVO.getProdNo());
   
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
	public List<UprodVO> getAllByCustomer(String CustNo) {
		List<UprodVO> list = new ArrayList<UprodVO>();
		UprodVO uprodVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT_CUSTOMER);
			pstmt.setString(1,CustNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				uprodVO = new UprodVO();
				uprodVO.setProdNo(rs.getString("PROD_NO"));
				uprodVO.setSellerNo(rs.getString("SELLER_NO"));
				uprodVO.setCustNo(rs.getString("CUST_NO"));
				uprodVO.setProdName(rs.getString("PROD_NAME"));
				uprodVO.setProdIntro(rs.getString("PROD_INTRO"));
				uprodVO.setPrice(rs.getInt("PRICE"));
				uprodVO.setIncreaseTime(rs.getTimestamp("INCREASE_TIME"));
				uprodVO.setProdStatus(rs.getString("PROD_STATUS"));
				uprodVO.setOrderStatus(rs.getString("ORDER_STATUS"));
				uprodVO.setReceiveStatus(rs.getString("RECEIVE_STATUS"));
				uprodVO.setOrderTime(rs.getTimestamp("ORDER_TIME"));
				uprodVO.setTranMethod(rs.getString("TRAN_METHOD"));
				uprodVO.setTranAddr(rs.getString("TRAN_ADDR"));
				uprodVO.setProdType(rs.getString("PROD_TYPE"));
				uprodVO.setEvaStar(rs.getInt("EVA_STAR"));
				uprodVO.setPhoto(rs.getBytes("PHOTO"));
				list.add(uprodVO);
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
		return list;
	}
	
	@Override
	public List<UprodVO> getAllOnShelfByA() {
		List<UprodVO> list = new ArrayList<UprodVO>();
		UprodVO uprodVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_ON_SHELF_BY_A);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				uprodVO = new UprodVO();
				uprodVO.setProdNo(rs.getString("PROD_NO"));
				uprodVO.setSellerNo(rs.getString("SELLER_NO"));
				uprodVO.setCustNo(rs.getString("CUST_NO"));
				uprodVO.setProdName(rs.getString("PROD_NAME"));
				uprodVO.setProdIntro(rs.getString("PROD_INTRO"));
				uprodVO.setPrice(rs.getInt("PRICE"));
				uprodVO.setIncreaseTime(rs.getTimestamp("INCREASE_TIME"));
				uprodVO.setProdStatus(rs.getString("PROD_STATUS"));
				uprodVO.setOrderStatus(rs.getString("ORDER_STATUS"));
				uprodVO.setReceiveStatus(rs.getString("RECEIVE_STATUS"));
				uprodVO.setOrderTime(rs.getTimestamp("ORDER_TIME"));
				uprodVO.setTranMethod(rs.getString("TRAN_METHOD"));
				uprodVO.setTranAddr(rs.getString("TRAN_ADDR"));
				uprodVO.setProdType(rs.getString("PROD_TYPE"));
				uprodVO.setEvaStar(rs.getInt("EVA_STAR"));
				uprodVO.setPhoto(rs.getBytes("PHOTO"));
				list.add(uprodVO);
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
		return list;
	}
	 
	public List<UprodVO> getAllOnShelfByB() {
		List<UprodVO> list = new ArrayList<UprodVO>();
		UprodVO uprodVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_ON_SHELF_BY_B);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				uprodVO = new UprodVO();
				uprodVO.setProdNo(rs.getString("PROD_NO"));
				uprodVO.setSellerNo(rs.getString("SELLER_NO"));
				uprodVO.setCustNo(rs.getString("CUST_NO"));
				uprodVO.setProdName(rs.getString("PROD_NAME"));
				uprodVO.setProdIntro(rs.getString("PROD_INTRO"));
				uprodVO.setPrice(rs.getInt("PRICE"));
				uprodVO.setIncreaseTime(rs.getTimestamp("INCREASE_TIME"));
				uprodVO.setProdStatus(rs.getString("PROD_STATUS"));
				uprodVO.setOrderStatus(rs.getString("ORDER_STATUS"));
				uprodVO.setReceiveStatus(rs.getString("RECEIVE_STATUS"));
				uprodVO.setOrderTime(rs.getTimestamp("ORDER_TIME"));
				uprodVO.setTranMethod(rs.getString("TRAN_METHOD"));
				uprodVO.setTranAddr(rs.getString("TRAN_ADDR"));
				uprodVO.setProdType(rs.getString("PROD_TYPE"));
				uprodVO.setEvaStar(rs.getInt("EVA_STAR"));
				uprodVO.setPhoto(rs.getBytes("PHOTO"));
				list.add(uprodVO);
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
		return list;
	}
	
	public List<UprodVO> getAllOnShelfByC() {
		List<UprodVO> list = new ArrayList<UprodVO>();
		UprodVO uprodVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_ON_SHELF_BY_C);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				uprodVO = new UprodVO();
				uprodVO.setProdNo(rs.getString("PROD_NO"));
				uprodVO.setSellerNo(rs.getString("SELLER_NO"));
				uprodVO.setCustNo(rs.getString("CUST_NO"));
				uprodVO.setProdName(rs.getString("PROD_NAME"));
				uprodVO.setProdIntro(rs.getString("PROD_INTRO"));
				uprodVO.setPrice(rs.getInt("PRICE"));
				uprodVO.setIncreaseTime(rs.getTimestamp("INCREASE_TIME"));
				uprodVO.setProdStatus(rs.getString("PROD_STATUS"));
				uprodVO.setOrderStatus(rs.getString("ORDER_STATUS"));
				uprodVO.setReceiveStatus(rs.getString("RECEIVE_STATUS"));
				uprodVO.setOrderTime(rs.getTimestamp("ORDER_TIME"));
				uprodVO.setTranMethod(rs.getString("TRAN_METHOD"));
				uprodVO.setTranAddr(rs.getString("TRAN_ADDR"));
				uprodVO.setProdType(rs.getString("PROD_TYPE"));
				uprodVO.setEvaStar(rs.getInt("EVA_STAR"));
				uprodVO.setPhoto(rs.getBytes("PHOTO"));
				list.add(uprodVO);
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
		return list;
	}

}