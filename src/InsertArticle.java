import java.io.*;
import java.sql.*;

import com.article.model.ArticleVO;
import com.article.controller.WriteTextToDB;

public class InsertArticle {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String userid = "EA102G6";
	String userpsw = "123456";
//	SQL
	private static final String SQL = 
			"UPDATE ARTICLE SET ART_CONTENT=? WHERE ART_NO=?";

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
//	新增文章內容
	public void insertArticleText(ArticleVO articleVO) {
		try {			
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, articleVO.getArtcontent());
			pstmt.setString(2, articleVO.getArtno());
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}
	}
	
	public static void main(String[] args) throws SQLException {
		
		InsertArticle InsertText = new InsertArticle();
		WriteTextToDB writeTextToDB = new WriteTextToDB();
		String path; //匯入的路徑
//		=======================新增文章內容=============================		
		InsertText.openDB();
		ArticleVO articleVO = new ArticleVO();
		
		for (int i = 1; i <= 10; i++) {
			String artno = (i < 10)? "A00" : "A0";
			articleVO.setArtno(artno + i);
			path = "WebContent/front-end/img/article/ggyy" + i + ".txt";
			try {
				articleVO.setArtcontent(WriteTextToDB.getLongString(path));
			} catch (IOException e) {
				throw new RuntimeException(e.getMessage());
			}
			InsertText.insertArticleText(articleVO);
		}
		System.out.println("文章內容新增成功");
		InsertText.closeDB();
	}
}