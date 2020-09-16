package com.tools;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mem.model.*;
import com.pet.model.*;
import com.dailyPhoto.model.*;
import com.foscare.model.FosterService;
import com.foscare.model.FosterVO;
import com.fosmphoto.model.FosmPhoService;
import com.gat.model.GatService;
import com.vendor.model.*;

@WebServlet("/ShowPhotos")
public class ShowPhotos extends HttpServlet {

	Connection con;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();

		
		try {
			//初始化
			byte[] imgBuf = null;
			//取出照片PK(照片編號、會員編號等等...)
			String photo_no = req.getParameter("photo_no");
			//功能分流
			String type= req.getParameter("type");
			//寄養單自己的參數
			String AB = req.getParameter("AB");
			
			
			switch(type) {
				case "mem"://<%=request.getContextPath()%>/front-end/ShowPhotos?type=mem&photo_no=
					MemService memSvc = new MemService();
					if(memSvc.getOneMem(photo_no).getMemPhoto() != null) {
						imgBuf = memSvc.getOneMem(photo_no).getMemPhoto();
					}else{
						InputStream in = getServletContext().getResourceAsStream("front-end/mem/images/noUser.png");
						byte[] b = new byte[in.available()];
						in.read(b);
						out.write(b);
						in.close(); 
						return;
					}		
					break;
				case "pet"://<%=request.getContextPath()%>/front-end/ShowPhotos?type=pet&photo_no=
					PetService petSvc = new PetService();
					if(petSvc.getOnePet(photo_no).getPetPhoto() != null) {
						imgBuf = petSvc.getOnePet(photo_no).getPetPhoto();
					}else{
						InputStream in = getServletContext().getResourceAsStream("front-end/pet/images/null.jpg");
						byte[] b = new byte[in.available()];
						in.read(b);
						out.write(b);
						in.close(); 
						return;
					}
					break;
				case "vendor"://<%=request.getContextPath()%>/front-end/ShowPhotos?type=vendor&photo_no=
					VendorService vendorSvc = new VendorService();
					if(vendorSvc.getOneVendor(photo_no).getVenPhoto() != null) {
						imgBuf = vendorSvc.getOneVendor(photo_no).getVenPhoto();
					}else{
						InputStream in = getServletContext().getResourceAsStream("front-end/mem/images/noUser.png");
						byte[] b = new byte[in.available()];
						in.read(b);
						out.write(b);
						in.close(); 
						return;
					}		
					break;
				case "dailyPhoto"://<%=request.getContextPath()%>/front-end/ShowPhotos?type=dailyPhoto&photo_no=
					DailyPhotoService DailyPhotoSvc = new DailyPhotoService();
					imgBuf = DailyPhotoSvc.getOneDailyPhoto(photo_no).getPhoto();
					break;
				case "fosmPho":
					FosmPhoService fmpSvc = new FosmPhoService();
					imgBuf = fmpSvc.getPhoto(photo_no).getPhoCon();
					break;
				case "fossign":
					FosterService fosSvc = new FosterService();
					FosterVO fosVO = fosSvc.getOneFoscare(photo_no);
					imgBuf = (AB.equals("A"))?fosVO.getFosSignA():fosVO.getFosSignB();
					break;
				case "gatPhoto":
					GatService gatSvc = new GatService();
					imgBuf = gatSvc.getOneGat(photo_no).getGatPhoto();
					break;
				
			}
			
			out.write(imgBuf);
	
		}catch(Exception e) {
			//無圖時顯示的部分
			InputStream in = getServletContext().getResourceAsStream("/img/none2.jpg");
			byte[] buf = new byte[in.available()];
			in.read(buf);
			out.write(buf);
			in.close();
		}
		
	}

}
