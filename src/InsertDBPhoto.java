import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.shop_product.model.*;
import com.tools.WritePhotoToDB;
import com.vendor.model.VendorVO;

import ANNOUNCEMENT.model.ANNVO;
import EMPLOYEE.model.EmpVO;

import com.dailyPhoto.model.DailyPhotoVO;
import com.fosmphoto.model.*;
import com.gat.model.GatVO;
import com.mem.model.MemVO;
import com.pet.model.PetVO;


public class InsertDBPhoto {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "EA102G6";
	String userpsw = "123456";
//	SQL
	private static final String INSERT_SHOP_PRODUCT_PHOTO = //	商城照片SQL
			"UPDATE SHOP_PRODUCT SET PHOTO = ? WHERE PROD_NO = ?";
	private static final String INSERT_PSTMT_FOSM_PHOTO =	//	保母照片SQL
			"INSERT INTO FOSM_PHOTO(PHO_NO,FOSM_NO,PHO_CON,PHO_TIME)VALUES('FPH'||LPAD(to_char(FOSM_PHOTO_NO.NEXTVAL), 3, '0'),?,?,sysdate)";
	private static final String INSERT_GAT_PHOTO = //	揪團照片SQL
			"UPDATE GATHERING SET GAT_PHOTO = ? WHERE GAT_NO = ?";
	private static final String INSERT_EMP_PHOTO = //	員工照片SQL
			"UPDATE EMPLOYEE SET EMP_PHOTO = ? WHERE EMP_NO = ?";
	private static final String INSERT_ANN_PHOTO = //	公告照片SQL
			"UPDATE ANNOUNCEMENT SET ANN_IMG = ? WHERE ANN_NO = ?";
	private static final String INSERT_MEM_PHOTO = //	會員照片SQL
			"UPDATE MEMBER_INFO SET MEM_PHOTO = ? WHERE MEM_NO = ?";
	private static final String INSERT_PET_PHOTO = //	寵物照片SQL
			"UPDATE PET SET PET_PHOTO = ? WHERE PET_NO = ?";
	private static final String INSERT_DAILY_PHOTO = //	寵物日誌照片SQL
			"UPDATE DAILY_PHOTO SET PHOTO = ? WHERE DPH_NO = ?";
	private static final String INSERT_VENDOR_PHOTO = // 廠商照片SQL
			"UPDATE VENDOR SET VEN_PHOTO = ? WHERE VEN_NO = ?";
	Connection con = null;
	PreparedStatement pstmt = null;
//	開啟連線
	public void openDB() {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, userpsw);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}
	}
//	清空pstmt
	public void clearPstmt() {
		pstmt = null;
	}
//	關閉連線
	public void closeDB() {
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
//	新增商城商品照片
	public void insertShopPhoto(Shop_productVO shopProductVO)  {		
		try {			
			pstmt = con.prepareStatement(INSERT_SHOP_PRODUCT_PHOTO);
			pstmt.setBytes(1, shopProductVO.getPhoto());
			pstmt.setString(2, shopProductVO.getProdNo());
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}		
	}
//	新增保母照片
	public void insertFosmPhoto(String fosmNo, byte[] phoCon) {
		try {
			pstmt = con.prepareStatement(INSERT_PSTMT_FOSM_PHOTO);
			pstmt.setString(1, fosmNo);
			pstmt.setBytes(2, phoCon);			
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}
	}
//	新增揪團照片
	public void insertGatShopPhoto(GatVO gatVO)  {		
		try {			
			pstmt = con.prepareStatement(INSERT_GAT_PHOTO);
			pstmt.setBytes(1, gatVO.getGatPhoto());
			pstmt.setString(2, gatVO.getGatNo());
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}		
	}
//	新增員工照片	
	public void insertEmpPhoto(EmpVO empvo)  {		
		try {			
			pstmt = con.prepareStatement(INSERT_EMP_PHOTO);
			pstmt.setBytes(1, empvo.getEmpphoto());
			pstmt.setString(2, empvo.getEmpno());
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}		
	}
//	新增公告照片	
	public void insertANNPhoto(ANNVO annvo)  {		
		try {			
			pstmt = con.prepareStatement(INSERT_ANN_PHOTO);
			pstmt.setBytes(1, annvo.getAnnimg());
			pstmt.setString(2, annvo.getAnnno());
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}		
	}
//	新增會員照片
	public void insertMemPhoto(MemVO memVO)  {		
		try {			
			pstmt = con.prepareStatement(INSERT_MEM_PHOTO);
			pstmt.setBytes(1, memVO.getMemPhoto());
			pstmt.setString(2, memVO.getMemNo());
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}		
	}
//	新增寵物照片
	public void insertPetPhoto(PetVO petVO)  {		
		try {			
			pstmt = con.prepareStatement(INSERT_PET_PHOTO);
			pstmt.setBytes(1, petVO.getPetPhoto());
			pstmt.setString(2, petVO.getPetNo());
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}		
	}
//	新增寵物日誌照片
	public void insertDailyPhoto(DailyPhotoVO dailyPhotoVO)  {		
		try {			
			pstmt = con.prepareStatement(INSERT_DAILY_PHOTO);
			pstmt.setBytes(1, dailyPhotoVO.getPhoto());
			pstmt.setString(2, dailyPhotoVO.getDphNo());
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}		
	}
//	新增廠商照片
	public void insertVendorPhoto(VendorVO vendorVO)  {		
		try {			
			pstmt = con.prepareStatement(INSERT_VENDOR_PHOTO);
			pstmt.setBytes(1, vendorVO.getVenPhoto());
			pstmt.setString(2, vendorVO.getVenNo());
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}		
	}
	
	
	public static void main(String[] args) {		
		InsertDBPhoto InsertPhoto = new InsertDBPhoto();
		WritePhotoToDB writePhotoToDB = new WritePhotoToDB();
		String path; //匯入照片的路徑
//		=======================新增商品照片=============================		
		InsertPhoto.openDB();//開啟連線
		Shop_productVO shopProductVO = new Shop_productVO();				
		for (int i = 1; i < 19; i++) {
			String prodNo = (i < 10)? "S00" : "S0";
			shopProductVO.setProdNo(prodNo + i);
			path = "WebContent/front-end/img/shopProduct/" + prodNo + i +".jpg";
			try {
				shopProductVO.setPhoto(writePhotoToDB.writePhoto(path));
			} catch (IOException e) {
				throw new RuntimeException(e.getMessage());
			}
			InsertPhoto.insertShopPhoto(shopProductVO);
		}
		System.out.println("商品照片新增完成");
//		======================新增寵物保母照片===========================
		InsertPhoto.clearPstmt();  //清空SQL 
		for (int i = 1; i < 17; i++) {
			String fosmNo;
			String fosPath;
			int a;                           					 //照片數量變數
			fosmNo = (i < 10)? "FM00" + i: "FM0" + i;            //新增保母編號
			fosPath = (i < 10)? "WebContent/front-end/img/fosmPhoto/DPH00" + i + "_": "WebContent/front-end/img/fosmPhoto/DPH0" + i + "_";   //各保母照片路徑
			a = (i<7)? 10 : 4;        							 //照片新增9張or3張			
			for (int j = 1; j < a; j++) {    					 //新增照片
				path = fosPath + j + ".jpg";  					 //各照片路徑
				try {			      
					InsertPhoto.insertFosmPhoto(fosmNo, writePhotoToDB.writePhoto(path));    //新增至資料庫
				}catch (IOException e) {
					throw new RuntimeException(e.getMessage());	
				}
			}						
		}
		System.out.println("寵物保母照片新增完成");
//		=======================新增揪團照片=============================		
		InsertPhoto.clearPstmt();  //清空SQL 
		GatVO gatVO = new GatVO();			
		for (int i = 1; i < 17; i++) {
			String gatNo = (i < 10)? "G00" : "G0";
			gatVO.setGatNo(gatNo + i);
			path = "WebContent/front-end/img/gatPhoto/" + gatNo + i +".jpg";
			try {
				gatVO.setGatPhoto(writePhotoToDB.writePhoto(path));
			} catch (IOException e) {
				throw new RuntimeException(e.getMessage());
			}
			InsertPhoto.insertGatShopPhoto(gatVO);
		}
		System.out.println("揪團照片新增完成");
		
//		=======================新增員工照片=============================		
		InsertPhoto.clearPstmt();  //清空SQL 
		EmpVO empVO = new EmpVO();			
		for (int i = 1; i < 6; i++) {
			String empNo = (i < 10)? "E00" : "E0";
			empVO.setEmpno(empNo + i);
			path = "WebContent/front-end/img/empPhoto/" + empNo + i +".jpg";
			try {
				empVO.setEmpphoto(writePhotoToDB.writePhoto(path));
			} catch (IOException e) {
				throw new RuntimeException(e.getMessage());
			}
			InsertPhoto.insertEmpPhoto(empVO);
		}
		System.out.println("EMP照片新增完成");
//		=======================新增公告照片=============================				
		InsertPhoto.clearPstmt();  //清空SQL 
		ANNVO annVO = new ANNVO();			
		for (int i = 1; i < 6; i++) {
			String annNo = (i < 10)? "ANN00" : "ANN0";
			annVO.setAnnno(annNo + i);
			path = "WebContent/front-end/img/annPhoto/" + annNo + i +".jpg";
			try {
				annVO.setAnnimg(writePhotoToDB.writePhoto(path));
			} catch (IOException e) {
				throw new RuntimeException(e.getMessage());
			}
			InsertPhoto.insertANNPhoto(annVO);
		}
		System.out.println("ANN照片新增完成");
//		=======================新增會員照片=============================		
		InsertPhoto.clearPstmt();  //清空SQL 
		MemVO memVO = new MemVO();				
		for (int i = 1; i <= 48; i++) {
			String memNo = (i < 10)? "M00" : "M0";
			memVO.setMemNo(memNo + i);
			path = "WebContent/front-end/img/mem/" + memNo + i +".jpg";
			try {
				memVO.setMemPhoto(writePhotoToDB.writePhoto(path));
			} catch (IOException e) {
				throw new RuntimeException(e.getMessage());
			}
			InsertPhoto.insertMemPhoto(memVO);
		}
		System.out.println("會員照片新增完成");
//		=======================新增寵物照片=============================		
		InsertPhoto.clearPstmt();  //清空SQL 
		PetVO petVO = new PetVO();				
		for (int i = 1; i <= 26; i++) {
			String petNo = (i < 10)? "P00" : "P0";
			petVO.setPetNo(petNo + i);
			path = "WebContent/front-end/img/pet/" + petNo + i +".jpg";
			try {
				petVO.setPetPhoto(writePhotoToDB.writePhoto(path));
			} catch (IOException e) {
				throw new RuntimeException(e.getMessage());
			}
			InsertPhoto.insertPetPhoto(petVO);
		}
		System.out.println("寵物照片新增完成");
//		=======================新增寵物日誌照片=============================		
		InsertPhoto.clearPstmt();  //清空SQL 
		DailyPhotoVO dailyPhotoVO = new DailyPhotoVO();				
		for (int i = 1; i <= 8; i++) {
			String DPHNo = (i < 10)? "DPH00" : "DPH0";
			dailyPhotoVO.setDphNo(DPHNo + i);
			path = "WebContent/front-end/img/dailyPhoto/" + DPHNo + i +".jpg";
			try {
				dailyPhotoVO.setPhoto(writePhotoToDB.writePhoto(path));
			} catch (IOException e) {
				throw new RuntimeException(e.getMessage());
			}
			InsertPhoto.insertDailyPhoto(dailyPhotoVO);
		}
		System.out.println("寵物日誌照片新增完成");
//		=======================新增寵物日誌照片=============================		
		InsertPhoto.clearPstmt();  //清空SQL 
		VendorVO vendorVO = new VendorVO();				
		for (int i = 1; i <= 8; i++) {
			String VenNo = (i < 10)? "V00" : "V0";
			vendorVO.setVenNo(VenNo + i);
			path = "WebContent/front-end/img/vendor/" + VenNo + i +".jpg";
			try {
				vendorVO.setVenPhoto(writePhotoToDB.writePhoto(path));
			} catch (IOException e) {
				throw new RuntimeException(e.getMessage());
			}
			InsertPhoto.insertVendorPhoto(vendorVO);
		}
		System.out.println("廠商照片新增完成");

		InsertPhoto.closeDB();//關閉連線	
	}
}
