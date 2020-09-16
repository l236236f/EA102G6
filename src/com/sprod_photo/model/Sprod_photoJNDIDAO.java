package com.sprod_photo.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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

public class Sprod_photoJNDIDAO implements Sprod_photoDAO_interface{
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
			"INSERT INTO SPROD_PHOTO(PHOTO_NO, PROD_NO) VALUES('SPH'||LPAD(TO_CHAR(SPH_SEQ.NEXTVAL),3,'0'),?)";
		private static final String GET_ALL_STMT = 
			"SELECT * FROM SPROD_PHOTO ORDER BY PHOTO_NO";
		private static final String GET_ONE_STMT = 
			"SELECT * FROM SPROD_PHOTO WHERE PHOTO_NO = ?";
		private static final String DELETE = 
			"DELETE FROM SPROD_PHOTO WHERE PHOTO_NO = ?";
		private static final String UPDATE = 
			"UPDATE SPROD_PHOTO SET PHOTO_CONTENT = ? WHERE PHOTO_NO = ?";
		private static final String GET_PHOTO = 
			"SELECT PHOTO_CONTENT, PHOTO_NO FROM SPROD_PHOTO WHERE PROD_NO = ?";
		private static final String FIND_IMGS_BY_PROD_NO =
			"SELECT PROD_NO, PHOTO_CONTENT FROM PROD_PHOTO WHERE PROD_NO =? AND ROWNUM=1";
		private static final String GET_ALL_BY_PRODNO = 
			"SELECT * FROM SPROD_PHOTO WHERE PROD_NO = ?";
		private static final String GET_NOS = 
			"SELECT PHOTO_NO FROM SPROD_PHOTO WHERE PROD_NO = ?";
		@Override
		public void insert(Sprod_photoVO sprodPhotoVO) {
			
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);
				
				pstmt.setString(1, sprodPhotoVO.getProdNo());
				
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
		public void delete(String photoNo) {
			
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(DELETE);
				
				pstmt.setString(1, photoNo);

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
		public Sprod_photoVO findByPrimaryKey(String photoNo) {
			
			Sprod_photoVO sprodPhotoVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_STMT);
				
				pstmt.setString(1, photoNo);

				rs = pstmt.executeQuery();

				while (rs.next()) {
								
					// empVo 也稱為 Domain objects
					sprodPhotoVO = new Sprod_photoVO();
					sprodPhotoVO.setPhotoNo(rs.getString("PHOTO_NO"));  
					sprodPhotoVO.setProdNo(rs.getString("PROD_NO"));   
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
			return sprodPhotoVO;
		}
		@Override
		public List<Sprod_photoVO> getAll() {
			
			List<Sprod_photoVO> list = new ArrayList<Sprod_photoVO>();
			Sprod_photoVO sprodPhotoVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {

				con = ds.getConnection();
				
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					// empVO 也稱為 Domain objects
					sprodPhotoVO = new Sprod_photoVO();
					sprodPhotoVO.setPhotoNo(rs.getString("PHOTO_NO"));
					sprodPhotoVO.setProdNo(rs.getString("PROD_NO"));     
					list.add(sprodPhotoVO);
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
		public List<byte[]> getPhotos(String prodNo) {
			List<byte[]> list = new ArrayList<byte[]>();
			
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				con = ds.getConnection();
				
				pstmt = con.prepareStatement(GET_PHOTO);
				
				pstmt.setString(1, prodNo);
				
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					list.add(rs.getBytes("PHOTO_CONTENT"));
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
		@Override
		public List<byte[]> getImages(String prodNo) {
			List<byte[]> picList=null;
			byte[] picture = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				
				pstmt = con.prepareStatement(FIND_IMGS_BY_PROD_NO);
				
				pstmt.setString(1, prodNo);
				
				rs = pstmt.executeQuery();
					picList = new ArrayList<byte[]>();
					if(rs.next()) {
						if(prodNo.equals(rs.getString(1))) {
						picture = rs.getBytes(2);
						picList.add(picture);
						}else{picList.add(null);}
				}
				
				// Handle any driver errors
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
			return picList;
		}
		@Override
		public void update(Sprod_photoVO sprodPhotoVO) {
			
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setBytes(1, sprodPhotoVO.getPhotoContent());
			pstmt.setString(2, sprodPhotoVO.getPhotoNo());
			
			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
	}
		@Override
		public List<Sprod_photoVO> getAll_by_prod_no(String prodNo) {
			List<Sprod_photoVO> list = new ArrayList<Sprod_photoVO>();
			Sprod_photoVO sprodPhotoVO = null;
			
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_BY_PRODNO);

				pstmt.setString(1, prodNo);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					sprodPhotoVO = new Sprod_photoVO();
					sprodPhotoVO.setPhotoNo(rs.getString("PHOTO_NO"));
					sprodPhotoVO.setProdNo(rs.getString("PROD_NO"));
					sprodPhotoVO.setPhotoContent(rs.getBytes("PHOTO_CONTENT"));
					list.add(sprodPhotoVO);
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
		@Override
		public List<String> getPhotoNos(String prodNo) {
			List<String> list = new ArrayList<String>();
			
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_NOS);

				pstmt.setString(1, prodNo);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					list.add(rs.getString("PHOTO_NO"));
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
}
