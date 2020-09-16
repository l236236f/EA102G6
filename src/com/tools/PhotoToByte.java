package com.tools;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.Part;

public class PhotoToByte {

	
	public byte[] photoToByte(Part part) throws IOException {
		byte[] buf = null;
		String filename = getFileNameFromPart(part);
		if (filename!= null && part.getContentType()!=null) {
		InputStream is =part.getInputStream();
		buf = new byte[is.available()];
		is.read(buf);
		}
		return buf;
	}
		
	// 取出上傳的檔案名稱 (因為API未提供method,所以必須自行撰寫)
		public String getFileNameFromPart(Part part) {
			String header = part.getHeader("content-disposition");
//			System.out.println("header=" + header); // 測試用
			String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
//			System.out.println("filename=" + filename); // 測試用
			if (filename.length() == 0) {
				return null;
			}
			return filename;
		}
}
