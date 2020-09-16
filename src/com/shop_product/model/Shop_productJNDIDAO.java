package com.shop_product.model;

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

import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;


public class Shop_productJNDIDAO implements Shop_productDAO_interface{
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
			"INSERT INTO SHOP_PRODUCT (PROD_NO,VEN_NO,CLASS_NAME,PROD_NAME,PROD_INTRO,INCREASE_TIME,PRICE,SPROD_STATUS,EV_COUNT,EV_TOTAL,PHOTO) VALUES (('S'||LPAD(TO_CHAR(SPROD_SEQ.NEXTVAL),3,'0')), ?, ?, ?, ?, SYSDATE, ?, ?, ?, ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT * FROM SHOP_PRODUCT ORDER BY PROD_NO";
		private static final String GET_ONE_STMT = 
			"SELECT * FROM SHOP_PRODUCT WHERE PROD_NO = ?";
		private static final String DELETE = 
			"DELETE FROM SHOP_PRODUCT WHERE PROD_NO = ?";
		private static final String UPDATE = 
			"UPDATE SHOP_PRODUCT SET VEN_NO=?, CLASS_NAME=?, PROD_NAME=?, PROD_INTRO=?, INCREASE_TIME=?, PRICE=?, SPROD_STATUS=?, EV_COUNT=?, EV_TOTAL=?, PHOTO=? WHERE PROD_NO = ?";
		private static final String INSERT_PHOTO =
			"UPDATE SHOP_PRODUCT SET PHOTO = ? WHERE PROD_NO = ?";
		private static final String GET_SHOPHOME_PRODUCT =
			"SELECT * FROM SHOP_PRODUCT WHERE SPROD_STATUS = ? ORDER BY PROD_NO DESC";
		private static final String GET_SHOP_PRODUCT_NAME =
			"SELECT PROD_NAME FROM SHOP_PRODUCT WHERE PROD_NO = ?";
		private static final String SHOP_CRAWLER_INSERT_STMT = 
			"INSERT INTO SHOP_PRODUCT (PROD_NO,VEN_NO,CLASS_NAME,PROD_NAME,PROD_INTRO,INCREASE_TIME,PRICE,SPROD_STATUS,EV_COUNT,EV_TOTAL) VALUES (('S'||LPAD(TO_CHAR(SPROD_SEQ.NEXTVAL),3,'0')), ?, ?, ?, ?, SYSDATE, ?, ?, ?, ?)";
	@Override
	public void insert(Shop_productVO shopProductVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

		
			pstmt.setString(1, shopProductVO.getVenNo());
			pstmt.setString(2, shopProductVO.getClassName());
			pstmt.setString(3, shopProductVO.getProdName());
			pstmt.setString(4, shopProductVO.getProdIntro());		
			pstmt.setInt(5, shopProductVO.getPrice());
			pstmt.setInt(6, shopProductVO.getSprodStatus());
			pstmt.setInt(7, shopProductVO.getEvCount());
			pstmt.setInt(8, shopProductVO.getEvTotal());
			pstmt.setBytes(9, shopProductVO.getPhoto());

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
	public void update(Shop_productVO shopProductVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);


			pstmt.setString(1, shopProductVO.getVenNo());
			pstmt.setString(2, shopProductVO.getClassName());
			pstmt.setString(3, shopProductVO.getProdName());
			pstmt.setString(4, shopProductVO.getProdIntro());
			pstmt.setDate(5, shopProductVO.getIncreaseTime());
			pstmt.setInt(6, shopProductVO.getPrice());
			pstmt.setInt(7, shopProductVO.getSprodStatus());
			pstmt.setInt(8, shopProductVO.getEvCount());
			pstmt.setInt(9, shopProductVO.getEvTotal());
			pstmt.setBytes(10, shopProductVO.getPhoto());
			pstmt.setString(11, shopProductVO.getProdNo());

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
	public void delete(String prodNo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, prodNo);

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
	public Shop_productVO findByPrimaryKey(String prodNo) {

		Shop_productVO shopProductVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, prodNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
							
				// empVo 也稱為 Domain objects
				shopProductVO = new Shop_productVO();
				shopProductVO.setProdNo(rs.getString("prod_no"));   
				shopProductVO.setVenNo(rs.getString("ven_no"));   
				shopProductVO.setClassName(rs.getString("class_name"));
				shopProductVO.setProdName(rs.getString("prod_name"));
				shopProductVO.setProdIntro(rs.getString("prod_intro"));
				shopProductVO.setIncreaseTime(rs.getDate("increase_time"));
				shopProductVO.setPrice(rs.getInt("price"));
				shopProductVO.setSprodStatus(rs.getInt("sprod_status"));
				shopProductVO.setEvCount(rs.getInt("ev_count"));
				shopProductVO.setEvTotal(rs.getInt("ev_total"));
				shopProductVO.setPhoto(rs.getBytes("PHOTO"));
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
		return shopProductVO;
	}

	@Override
	public List<Shop_productVO> getAll() {
		List<Shop_productVO> list = new ArrayList<Shop_productVO>();
		Shop_productVO shopProductVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				shopProductVO = new Shop_productVO();
				shopProductVO.setProdNo(rs.getString("prod_no"));  
				shopProductVO.setVenNo(rs.getString("ven_no"));   
				shopProductVO.setClassName(rs.getString("class_name"));
				shopProductVO.setProdName(rs.getString("prod_name"));
				shopProductVO.setProdIntro(rs.getString("prod_intro"));
				shopProductVO.setIncreaseTime(rs.getDate("Increase_time"));
				shopProductVO.setPrice(rs.getInt("price"));
				shopProductVO.setSprodStatus(rs.getInt("sprod_status"));
				shopProductVO.setEvCount(rs.getInt("ev_count"));
				shopProductVO.setEvTotal(rs.getInt("ev_total"));
				shopProductVO.setPhoto(rs.getBytes("PHOTO"));
				list.add(shopProductVO); // Store the row in the list
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
		return list;
	}
	@Override
	public byte[] writePhoto(String path) throws IOException {
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[8192];
		int i;
		while ((i = fis.read(buffer)) != -1) {
			baos.write(buffer, 0, i);
		}
		
		baos.close();
		fis.close();
		
		return baos.toByteArray();
	}
	@Override
	public void insertPhoto(Shop_productVO shopProductVO)  {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_PHOTO);

			pstmt.setBytes(1, shopProductVO.getPhoto());
			pstmt.setString(2, shopProductVO.getProdNo());

			pstmt.executeUpdate();
System.out.println("新增成功");
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
	public List<Shop_productVO> selectShophomeProduct(Integer sprodStatus) {
		List<Shop_productVO> list = new ArrayList<Shop_productVO>();
		Shop_productVO shopProductVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_SHOPHOME_PRODUCT);
			
			pstmt.setInt(1, sprodStatus);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				shopProductVO = new Shop_productVO();
				shopProductVO.setProdNo(rs.getString("PROD_NO"));   
				shopProductVO.setVenNo(rs.getString("VEN_NO"));   
				shopProductVO.setClassName(rs.getString("CLASS_NAME"));
				shopProductVO.setProdName(rs.getString("PROD_NAME"));
				shopProductVO.setProdIntro(rs.getString("PROD_INTRO"));
				shopProductVO.setIncreaseTime(rs.getDate("INCREASE_TIME"));
				shopProductVO.setPrice(new Integer(rs.getInt("PRICE")));
				shopProductVO.setSprodStatus(new Integer(rs.getInt("SPROD_STATUS")));
				shopProductVO.setEvCount(new Integer(rs.getInt("EV_COUNT")));
				shopProductVO.setEvTotal(new Integer(rs.getInt("EV_TOTAL")));	
				shopProductVO.setPhoto(rs.getBytes("PHOTO"));
				list.add(shopProductVO); // Store the row in the list
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
	
//	用編號查商品名稱
	@Override
	public String selectShopProductName(String sprodNo) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sprodName = null;
		
		try {

			con = ds.getConnection();
			
			pstmt = con.prepareStatement(GET_SHOP_PRODUCT_NAME);
			pstmt.setString(1, sprodNo);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				sprodName = rs.getString("PROD_NAME");
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
		return sprodName;
	}

@Override
public void shopCrawlerInsert(Shop_productVO shopProductVO) {
	
	Connection con = null;
	PreparedStatement pstmt = null;

	try {

		con = ds.getConnection();
		pstmt = con.prepareStatement(SHOP_CRAWLER_INSERT_STMT);

	
		pstmt.setString(1, shopProductVO.getVenNo());
		pstmt.setString(2, shopProductVO.getClassName());
		pstmt.setString(3, shopProductVO.getProdName());
		pstmt.setString(4, shopProductVO.getProdIntro());		
		pstmt.setInt(5, shopProductVO.getPrice());
		pstmt.setInt(6, shopProductVO.getSprodStatus());
		pstmt.setInt(7, shopProductVO.getEvCount());
		pstmt.setInt(8, shopProductVO.getEvTotal());

		pstmt.executeUpdate();

		// Handle any driver errors
	} catch (SQLException se) {
System.out.println("error");
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

//	public static void main(String[] args) {
//
//		Shop_productJDBCDAO dao = new Shop_productJDBCDAO();

		// 新增
//		Shop_productVO shop_productVO1 = new Shop_productVO();
//	
//
//		shop_productVO1.setVenNo("V003");
//		shop_productVO1.setClassName("海產");
//		shop_productVO1.setProdName("鮭魚");
//		shop_productVO1.setProdIntro("這商品買了會發大財");
//		shop_productVO1.setIncreaseTime(java.sql.Date.valueOf("2022-12-22")); 
//		shop_productVO1.setPrice(100);
//		shop_productVO1.setSprodStatus(0);
//		shop_productVO1.setEv_count(10);
//		shop_productVO1.setEv_total(48);
//
//		dao.insert(shop_productVO1);
//		System.out.println("新增成功");
		// 修改
//		Shop_productVO shop_productVO2 = new Shop_productVO();
//		
//		shop_productVO2.setProdNo("S012");
//		shop_productVO2.setVenNo("V003");
//		shop_productVO2.setClassName("海產");
//		shop_productVO2.setProdName("鮪魚");
//		shop_productVO2.setProdIntro("這商品買了會發小財");
//		shop_productVO2.setIncreaseTime(java.sql.Date.valueOf("2022-12-22")); 
//		shop_productVO2.setPrice(100);
//		shop_productVO2.setSprodStatus(0);
//		shop_productVO2.setEv_count(10);
//		shop_productVO2.setEv_total(48);
//		
//		dao.update(shop_productVO2);
//		System.out.println("修改成功");
		
		// 刪除
//		dao.delete("S012");
//		System.out.println("刪除成功");
		// 查詢
//		Shop_productVO shop_productVO3 = dao.findByPrimaryKey("S013");
//		System.out.print(shop_productVO3.getProdNo() + ",");
//		System.out.print(shop_productVO3.getVenNo() + ",");
//		System.out.print(shop_productVO3.getClassName() + ",");
//		System.out.print(shop_productVO3.getProdName() + ",");
//		System.out.print(shop_productVO3.getProdIntro() + ",");
//		System.out.print(shop_productVO3.getIncreaseTime() + ",");
//		System.out.print(shop_productVO3.getPrice() + ",");
//		System.out.print(shop_productVO3.getSprodStatus() + ",");
//		System.out.print(shop_productVO3.getEvCount() + ",");
//		System.out.print(shop_productVO3.getEvTotal());
//		System.out.println("---------------------");

		// 查詢
//		List<Shop_productVO> list = dao.getAll();
//		for (Shop_productVO aShop_product : list) {
//			System.out.print(aShop_product.getProdNo() + ",");
//			System.out.print(aShop_product.getVenNo() + ",");
//			System.out.print(aShop_product.getClassName() + ",");
//			System.out.print(aShop_product.getProdName() + ",");
//			System.out.print(aShop_product.getProdIntro() + ",");
//			System.out.print(aShop_product.getIncreaseTime() + ",");
//			System.out.print(aShop_product.getPrice() + ",");
//			System.out.print(aShop_product.getSprodStatus() + ",");
//			System.out.print(aShop_product.getEvCount() + ",");
//			System.out.print(aShop_product.getEvTotal());
//			System.out.print(aShop_product.getPhoto());
//			
//			System.out.println();
//		}
//	}

}
