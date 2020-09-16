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
		
	// ���X�W�Ǫ��ɮצW�� (�]��API������method,�ҥH�����ۦ漶�g)
		public String getFileNameFromPart(Part part) {
			String header = part.getHeader("content-disposition");
//			System.out.println("header=" + header); // ���ե�
			String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
//			System.out.println("filename=" + filename); // ���ե�
			if (filename.length() == 0) {
				return null;
			}
			return filename;
		}
}
