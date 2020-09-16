
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;



public class InsertUPphoto {
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "EA102G6";
	private static final String PASSWORD = "123456";
	private static final String SQL = "UPDATE USED_PRODUCT SET PHOTO=? WHERE PROD_NO = ?";

	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pstmt = null;
        for(int i=0;i<30;i++) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(SQL);
			String ProdNo=null;
			if(i<9) ProdNo="U00"+(i+1);
			else ProdNo="U0"+(i+1);
			pstmt.setString(2, ProdNo);
			String PicName="UP"+(i+1)+".jpg";
			// 2. setBytes (重要)
			byte[] pic = getPictureByteArray("WebContent/front-end/img/uprod/"+PicName);
			pstmt.setBytes(1, pic);
			pstmt.executeUpdate();

			// 清空裡面參數，重覆使用已取得的PreparedStatement物件
			pstmt.clearParameters();
     
			System.out.println("新增成功"+(i+1));

		} catch (ClassNotFoundException ce) {
			System.out.println(ce);
		} catch (SQLException se) {
			System.out.println(se);
		} catch (IOException ie) {
			System.out.println(ie);
		} finally {
			// 依建立順序關閉資源 (越晚建立越早關閉)
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					System.out.println(se);
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					System.out.println(se);
				}
			}
		}
		}
	}

	
	public static byte[] getPictureByteArray(String path) throws IOException {
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();//輸出資料至接管(ByteArray)，自帶byte[];
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

