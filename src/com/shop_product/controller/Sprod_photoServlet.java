package com.shop_product.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.sprod_photo.model.*;

@MultipartConfig(fileSizeThreshold = 1920 * 1080, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)


public class Sprod_photoServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException{
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		
if ("insert".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
				String prodNo = req.getParameter("prodNo");
				
				byte[] photoContent = null;
				Part part = req.getPart("upfile1");
				InputStream in = part.getInputStream();
	if(in.available()!=0) {
					byte[] buf = new byte[in.available()];
					in.read(buf);
					photoContent = buf;
					in.close();
	}else {
		photoContent = null;
	}
													
				Sprod_photoVO sprodPhotoVO = new Sprod_photoVO();
				sprodPhotoVO.setProdNo(prodNo);
				sprodPhotoVO.setPhotoContent(photoContent);
	
				if (!errorMsgs.isEmpty()) {

					req.setAttribute("sprodPhotoVO", sprodPhotoVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/shop/vendorAddShopProduct.jsp");
					failureView.forward(req, res);
					return;
				}

				/***************************2.�}�l�s�W���***************************************/
				
				Sprod_photoService sprodPhotoSvc = new Sprod_photoService();
				sprodPhotoVO = sprodPhotoSvc.addSprodPhoto(prodNo, photoContent);

				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				String url = "/front-end/shop/vendorListAllShopProduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);		
			}catch(Exception e){
				/***************************��L�i�઺���~�B�z**********************************/
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/shop/vendorAddShopProduct.jsp");
				failureView.forward(req, res);
			}
		}
	}

	


	
}
