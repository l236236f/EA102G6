package com.gat.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.json.JSONArray;
import org.json.JSONObject;

import com.gat.model.GatService;
import com.gat.model.GatVO;

/**
 * Servlet implementation class GatServlet
 */
@WebServlet("/gat/gatServlet") // 跟xml名稱不能同名，他是老大
@MultipartConfig
public class GatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_UpdateList".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsg", errorMsgs);
			try {
				// 錯誤處理-未輸入
				String str = req.getParameter("memNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請先登入");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/gat/gat_index.jsp");
					failureView.forward(req, res);
					return;
				}
				// 錯誤處理-格式錯誤
				String memNo = null;
				try {
					memNo = new String(str);
				} catch (Exception e) {
					errorMsgs.add("會員編號格式不正確");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/gat/gat_index.jsp");
					failureView.forward(req, res);
					return;
				}
								
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/gat/gat_index.jsp");
					failureView.forward(req, res);
					return;
				}
				// 查詢成功
				req.setAttribute("memNo", memNo);
				String url = "/front-end/gat/gat_updateList.jsp";
				RequestDispatcher successfulView = req.getRequestDispatcher(url);
				successfulView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("無法取得資料: " + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/gat/gat_index.jsp");
				failureView.forward(req, res);
			}

		}
		
//		if ("getOne_For_Add".equals(action)) {
//			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsg", errorMsgs);
//			try {
//				// 錯誤處理-未輸入
//				String str = req.getParameter("memNo");
//				if (str == null || (str.trim()).length() == 0) {
//					errorMsgs.add("請先登入");
//				}
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/gat/gat_index.jsp");
//					failureView.forward(req, res);
//					return;
//				}
//				// 錯誤處理-格式錯誤
//				String memNo = null;
//				try {
//					memNo = new String(str);
//				} catch (Exception e) {
//					errorMsgs.add("會員編號格式不正確");
//				}
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/gat/gat_index.jsp");
//					failureView.forward(req, res);
//					return;
//				}
//								
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/gat/gat_index.jsp");
//					failureView.forward(req, res);
//					return;
//				}
//				// 轉交至新增
//				req.setAttribute("memNo", memNo);
//				String url = "/front-end/gat/gat_add.jsp";
//				RequestDispatcher successfulView = req.getRequestDispatcher(url);
//				successfulView.forward(req, res);
//			} catch (Exception e) {
//				errorMsgs.add("無法取得資料: " + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/gat/gat_index.jsp");
//				failureView.forward(req, res);
//			}
//
//		}

		if ("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			try {
				String gatNo = req.getParameter("gatNo");
				GatService gatSvc = new GatService();
				GatVO gatVO = gatSvc.getOneGat(gatNo);
				String photo = req.getParameter("photo");
				req.setAttribute("photo", photo);

				req.setAttribute("gatVO", gatVO);
				String url = "/front-end/gat/gat_update.jsp";
				RequestDispatcher successfulView = req.getRequestDispatcher(url);
				successfulView.forward(req, res);
			}  catch (Exception e) {
				errorMsgs.add("修改資料取出時失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");

			try {

				// 拿到揪團編號
				String gatNo = req.getParameter("gatNo").trim();
				String memNo = req.getParameter("memNo").trim();
//				System.out.println("memNo:" + memNo);

				// 揪團名稱
				String gatName = req.getParameter("gatName").trim();
				String gatNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_！～)]{2,30}$";
				if (gatName == null || gatName.length() == 0) {
					errorMsgs.add("揪團名稱請勿空白");
				} else if (!gatName.matches(gatNameReg)) {
					errorMsgs.add("揪團名稱只能是中、英文字母、數字和_ , 且長度必需在2到30之間");
				}

				// 揪團時間
				java.sql.Date gatTime = null;
				try {
					gatTime = Date.valueOf(req.getParameter("gatTime").trim());
				} catch (IllegalArgumentException e) {
					gatTime = new Date(System.currentTimeMillis());
					errorMsgs.add("請輸入揪團日期");
				}

				// 地點（到時候想接GOOGLE API）
				String gatLoc = req.getParameter("gatLoc").trim();
				if (gatLoc == null || gatLoc.length() == 0) {
					errorMsgs.add("揪團地點請勿空白");
				}

				// 簡介
				String gatIntro = req.getParameter("gatIntro").trim();
				if (gatIntro == null || gatIntro.length() == 0) {
					errorMsgs.add("揪團簡介請勿空白");
				}

				// 報名開始時間
				Date gatStarttime = null;
				try {
					gatStarttime = Date.valueOf(req.getParameter("gatStarttime").trim());
				} catch (IllegalArgumentException e) {
					gatStarttime = new Date(System.currentTimeMillis());
					errorMsgs.add("請輸入報名開始日期");
				}

				// 報名截止時間
				Date gatEndtime = null;
				try {
					gatEndtime = Date.valueOf(req.getParameter("gatEndtime").trim());
				} catch (IllegalArgumentException e) {
					gatEndtime = new Date(System.currentTimeMillis());
					errorMsgs.add("請輸入報名截止日期");
				}

				// 報名最高上限
				Integer gatMax = null;
				try {
					gatMax = new Integer(req.getParameter("gatMax").trim());
				} catch (NumberFormatException e) {
					gatMax = 0;
					errorMsgs.add("請輸入整數");
				}

				// 報名最低上限
				Integer gatMin = null;
				try {
					gatMin = new Integer(req.getParameter("gatMin").trim());
				} catch (NumberFormatException e) {
					gatMin = 0;
					errorMsgs.add("請輸入整數");
				}

				// 揪團狀態
//				String gatStatus = req.getParameter("gatStatus").trim();
//				String gatStatusReg = "^[G0-1]{2}$";
//				if (gatStatus == null || gatStatus.length() == 0) {
//					errorMsgs.add("揪團狀態請勿空白");
//				} else if (!gatStatus.matches(gatStatusReg)) {
//					errorMsgs.add("揪團狀態只能是G0或G1");
//				}
				GatService gatSvc = new GatService();
				// 揪團照片
				byte[] gatPhoto = null;
				Part part = req.getPart("gatPhoto");
				InputStream ips = part.getInputStream();
				if (ips.available() != 0) {
					gatPhoto = new byte[ips.available()];
					ips.read(gatPhoto);
					ips.close();
				} else {
					gatPhoto = gatSvc.getOneGat(gatNo).getGatPhoto();
				}
				
				//揪團類別
				String gatType = req.getParameter("gatType").trim();
				if (gatType == null || gatType.length() == 0) {
					errorMsgs.add("揪團類別請勿空白");
				}
				
				//地點緯度
				Double gatLat = null;
				try {
					gatLat = new Double(req.getParameter("gatLat").trim());
				} catch (NumberFormatException e) {
					gatLat = 0.0;
					errorMsgs.add("請輸入緯度");
				}
				
				//地點經度
				Double gatLng = null;
				try {
					gatLng = new Double(req.getParameter("gatLng").trim());
				} catch (NumberFormatException e) {
					gatLng = 0.0;
					errorMsgs.add("請輸入經度");
				}

				GatVO gatVO = new GatVO();
				gatVO.setMemNo(memNo);
				gatVO.setGatNo(gatNo);
				gatVO.setGatName(gatName);
				gatVO.setGatTime(gatTime);
				gatVO.setGatLoc(gatLoc);
				gatVO.setGatIntro(gatIntro);
				gatVO.setGatStarttime(gatStarttime);
				gatVO.setGatEndtime(gatEndtime);
				gatVO.setGatMax(gatMax);
				gatVO.setGatMin(gatMin);
//				gatVO.setGatStatus(gatStatus);
				gatVO.setGatPhoto(gatPhoto);
				gatVO.setGatType(gatType);
				gatVO.setGatLat(gatLat);
				gatVO.setGatLng(gatLng);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("gatVO", gatVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/gat/gat_update.jsp");
					failureView.forward(req, res);
					return;
				}

				gatVO = gatSvc.updateGat(gatNo, memNo, gatName, gatTime, gatLoc, gatIntro, gatStarttime, gatEndtime,
						gatMax, gatMin, gatPhoto, gatType, gatLat, gatLng);

				req.setAttribute("gatVO", gatVO);
				req.setAttribute("memNo", memNo);
				String url = requestURL;
				RequestDispatcher successfulView = req.getRequestDispatcher(url);
				successfulView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("修改資料失敗: " + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/gat/gat_update.jsp");
				failureView.forward(req, res);
			}

		}
		if ("insert".equals(action)) {
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				// 抓會員編號
				String memNo = req.getParameter("memNo");

				// 揪團名稱
				String gatName = req.getParameter("gatName").trim();
				String gatNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_！～)]{2,30}$";
				if (gatName == null || gatName.length() == 0) {
					errorMsgs.put("gatName", "揪團名稱請勿空白");
				} else if (!gatName.matches(gatNameReg)) {
					errorMsgs.put("gatName", "揪團名稱只能是中、英文字母、數字和_ , 且長度必需在2到30之間");
				}

				// 揪團時間
				java.sql.Date gatTime = null;
				try {
					gatTime = Date.valueOf(req.getParameter("gatTime").trim());
				} catch (IllegalArgumentException e) {
					gatTime = new Date(System.currentTimeMillis());
					errorMsgs.put("gatTime", "請輸入揪團日期");
				}

				// 地點（到時候想接GOOGLE API）
				String gatLoc = req.getParameter("gatLoc").trim();
				if (gatLoc == null || gatLoc.length() == 0) {
					errorMsgs.put("gatLoc", "揪團地點請勿空白");
				}

				// 簡介
				String gatIntro = req.getParameter("gatIntro").trim();
				if (gatIntro == null || gatIntro.length() == 0) {
					errorMsgs.put("gatIntro","揪團簡介請勿空白");
				}

				// 報名開始時間
				Date gatStarttime = null;
				try {
					gatStarttime = Date.valueOf(req.getParameter("gatStarttime").trim());
				} catch (IllegalArgumentException e) {
					gatStarttime = new Date(System.currentTimeMillis());
					errorMsgs.put("gatStarttime","請輸入報名開始日期");
				}

				// 報名截止時間
				Date gatEndtime = null;
				try {
					gatEndtime = Date.valueOf(req.getParameter("gatEndtime").trim());
				} catch (IllegalArgumentException e) {
					gatEndtime = new Date(System.currentTimeMillis());
					errorMsgs.put("gatEndtime", "請輸入報名截止日期");
				}

				// 報名最高人數上限
				Integer gatMax = null;
				try {
					gatMax = new Integer(req.getParameter("gatMax").trim());
				} catch (NumberFormatException e) {
					gatMax = 0;
					errorMsgs.put("gatMax", "報名最高人數上限請輸入整數");
				}

				// 報名最低人數限制
				Integer gatMin = null;
				try {
					gatMin = new Integer(req.getParameter("gatMin").trim());
				} catch (NumberFormatException e) {
					gatMin = 0;
					errorMsgs.put("gatMin", "報名最低人數限制請輸入整數");
				}

				// 揪團照片
				byte[] gatPhoto = null;
				Part part = req.getPart("gatPhoto");
				InputStream ips = part.getInputStream();
				gatPhoto = new byte[ips.available()];
				ips.read(gatPhoto);
				ips.close();
				
				//揪團類別
				String gatType = req.getParameter("gatType").trim();
				if (gatType == null || gatType.length() == 0) {
					errorMsgs.put("gatType", "揪團類別請勿空白");
				}
				
				//地點緯度
				Double gatLat = null;
				try {
					gatLat = new Double(req.getParameter("gatLat").trim());
				} catch (NumberFormatException e) {
					gatLat = 0.0;
					errorMsgs.put("gatLat", "請輸入緯度");
				}
				
				//地點經度
				Double gatLng = null;
				try {
					gatLng = new Double(req.getParameter("gatLng").trim());
				} catch (NumberFormatException e) {
					gatLng = 0.0;
					errorMsgs.put("gatLng", "請輸入經度");
				}

				GatVO gatVO = new GatVO();
				gatVO.setMemNo(memNo);
				gatVO.setGatName(gatName);
				gatVO.setGatTime(gatTime);
				gatVO.setGatLoc(gatLoc);
				gatVO.setGatIntro(gatIntro);
				gatVO.setGatStarttime(gatStarttime);
				gatVO.setGatEndtime(gatEndtime);
				gatVO.setGatMax(gatMax);
				gatVO.setGatMin(gatMin);
				gatVO.setGatPhoto(gatPhoto);
				gatVO.setGatType(gatType);
				gatVO.setGatLat(gatLat);
				gatVO.setGatLng(gatLng);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("gatVO", gatVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/gat/gat_add.jsp");
					failureView.forward(req, res);
					return;
				}

				GatService gatSvc = new GatService();
				gatVO = gatSvc.addGat(memNo, gatName, gatTime, gatLoc, gatIntro, gatStarttime, gatEndtime, gatMax,
						gatMin, gatPhoto, gatType, gatLat, gatLng);
				req.setAttribute("gatVO", gatVO);

//				String gatNo = gatVO.getGatNo(); // DAO已經寫好自增主鍵值在生成時，就能抓取的方法
//				GatPhVO gatPhVO = new GatPhVO();
//				gatPhVO.setPhCont(gatPhoto);
//				gatPhVO.setGatNo(gatNo);
//				GatPhService gatPhSvc = new GatPhService();
//				gatPhVO = gatPhSvc.addPh(gatNo, gatPhoto);
//				req.setAttribute("gatPhVO", gatPhVO);

				String url = "/front-end/gat/gat_updateList.jsp";
				RequestDispatcher successfulView = req.getRequestDispatcher(url);
				successfulView.forward(req, res);

			} catch (Exception e) {
//				GatVO gatVO = new GatVO();
//				req.setAttribute("gatVO", gatVO);
				errorMsgs.put("Exception", "出錯啦！跑到CATCH了：" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/gat/gat_add.jsp");
				failureView.forward(req, res);
			}

		}
		if ("delete".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String gatNo = req.getParameter("gatNo");

				GatService gatSvc = new GatService();
				gatSvc.deleteGat(gatNo);

				String url = "/front-end/gat/listAllGat.jsp";
				RequestDispatcher successfulView = req.getRequestDispatcher(url);
				successfulView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗: " + e.getMessage());
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/gat/listAllGat.jsp");
				failureView.forward(req, res);
			}
		}
		if ("gatlist_search".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String keyword = req.getParameter("keyword");
				GatService gatSvc = new GatService();
				List<GatVO> list = gatSvc.getAllLike(keyword);
		
				JSONArray jsonarray = new JSONArray();
				
				for(GatVO result : list) {
					JSONObject jsonobject = new JSONObject();
					jsonobject.put("gatNo", result.getGatNo());
					jsonobject.put("gatName", result.getGatName());
					jsonobject.put("gatLoc", result.getGatLoc());
					jsonobject.put("gatTime", result.getGatTime());
					jsonobject.put("gatIntro", result.getGatIntro());
					jsonobject.put("gatType", result.getGatType());
					jsonobject.put("gatLat", result.getGatLat());
					jsonobject.put("gatLng", result.getGatLng());
					jsonarray.put(jsonobject);
				}
				
//				System.out.println("模糊查詢的VO設定完成");
				
				res.setContentType("text/html");
				res.setCharacterEncoding("UTF-8");
				PrintWriter out = res.getWriter();
				out.write(jsonarray.toString());
				out.flush();
				out.close();
				
//				System.out.println("模糊查詢的out已經close");
//				String url = "/front-end/gat/gat_update.jsp";
//				RequestDispatcher successfulView = req.getRequestDispatcher(url);
//				successfulView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料: " + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/gat/gat_updateList.jsp");
//				failureView.forward(req, res);
			}
		}
	}

}
