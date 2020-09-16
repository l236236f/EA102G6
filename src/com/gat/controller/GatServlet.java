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
@WebServlet("/gat/gatServlet") // ��xml�W�٤���P�W�A�L�O�Ѥj
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
				// ���~�B�z-����J
				String str = req.getParameter("memNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�Х��n�J");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/gat/gat_index.jsp");
					failureView.forward(req, res);
					return;
				}
				// ���~�B�z-�榡���~
				String memNo = null;
				try {
					memNo = new String(str);
				} catch (Exception e) {
					errorMsgs.add("�|���s���榡�����T");
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
				// �d�ߦ��\
				req.setAttribute("memNo", memNo);
				String url = "/front-end/gat/gat_updateList.jsp";
				RequestDispatcher successfulView = req.getRequestDispatcher(url);
				successfulView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���: " + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/gat/gat_index.jsp");
				failureView.forward(req, res);
			}

		}
		
//		if ("getOne_For_Add".equals(action)) {
//			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsg", errorMsgs);
//			try {
//				// ���~�B�z-����J
//				String str = req.getParameter("memNo");
//				if (str == null || (str.trim()).length() == 0) {
//					errorMsgs.add("�Х��n�J");
//				}
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/gat/gat_index.jsp");
//					failureView.forward(req, res);
//					return;
//				}
//				// ���~�B�z-�榡���~
//				String memNo = null;
//				try {
//					memNo = new String(str);
//				} catch (Exception e) {
//					errorMsgs.add("�|���s���榡�����T");
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
//				// ���ܷs�W
//				req.setAttribute("memNo", memNo);
//				String url = "/front-end/gat/gat_add.jsp";
//				RequestDispatcher successfulView = req.getRequestDispatcher(url);
//				successfulView.forward(req, res);
//			} catch (Exception e) {
//				errorMsgs.add("�L�k���o���: " + e.getMessage());
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
				errorMsgs.add("�ק��ƨ��X�ɥ���:"+e.getMessage());
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

				// ���촪�νs��
				String gatNo = req.getParameter("gatNo").trim();
				String memNo = req.getParameter("memNo").trim();
//				System.out.println("memNo:" + memNo);

				// ���ΦW��
				String gatName = req.getParameter("gatName").trim();
				String gatNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_�I��)]{2,30}$";
				if (gatName == null || gatName.length() == 0) {
					errorMsgs.add("���ΦW�ٽФŪť�");
				} else if (!gatName.matches(gatNameReg)) {
					errorMsgs.add("���ΦW�٥u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��30����");
				}

				// ���ήɶ�
				java.sql.Date gatTime = null;
				try {
					gatTime = Date.valueOf(req.getParameter("gatTime").trim());
				} catch (IllegalArgumentException e) {
					gatTime = new Date(System.currentTimeMillis());
					errorMsgs.add("�п�J���Τ��");
				}

				// �a�I�]��ɭԷQ��GOOGLE API�^
				String gatLoc = req.getParameter("gatLoc").trim();
				if (gatLoc == null || gatLoc.length() == 0) {
					errorMsgs.add("���Φa�I�ФŪť�");
				}

				// ²��
				String gatIntro = req.getParameter("gatIntro").trim();
				if (gatIntro == null || gatIntro.length() == 0) {
					errorMsgs.add("����²���ФŪť�");
				}

				// ���W�}�l�ɶ�
				Date gatStarttime = null;
				try {
					gatStarttime = Date.valueOf(req.getParameter("gatStarttime").trim());
				} catch (IllegalArgumentException e) {
					gatStarttime = new Date(System.currentTimeMillis());
					errorMsgs.add("�п�J���W�}�l���");
				}

				// ���W�I��ɶ�
				Date gatEndtime = null;
				try {
					gatEndtime = Date.valueOf(req.getParameter("gatEndtime").trim());
				} catch (IllegalArgumentException e) {
					gatEndtime = new Date(System.currentTimeMillis());
					errorMsgs.add("�п�J���W�I����");
				}

				// ���W�̰��W��
				Integer gatMax = null;
				try {
					gatMax = new Integer(req.getParameter("gatMax").trim());
				} catch (NumberFormatException e) {
					gatMax = 0;
					errorMsgs.add("�п�J���");
				}

				// ���W�̧C�W��
				Integer gatMin = null;
				try {
					gatMin = new Integer(req.getParameter("gatMin").trim());
				} catch (NumberFormatException e) {
					gatMin = 0;
					errorMsgs.add("�п�J���");
				}

				// ���Ϊ��A
//				String gatStatus = req.getParameter("gatStatus").trim();
//				String gatStatusReg = "^[G0-1]{2}$";
//				if (gatStatus == null || gatStatus.length() == 0) {
//					errorMsgs.add("���Ϊ��A�ФŪť�");
//				} else if (!gatStatus.matches(gatStatusReg)) {
//					errorMsgs.add("���Ϊ��A�u��OG0��G1");
//				}
				GatService gatSvc = new GatService();
				// ���ηӤ�
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
				
				//�������O
				String gatType = req.getParameter("gatType").trim();
				if (gatType == null || gatType.length() == 0) {
					errorMsgs.add("�������O�ФŪť�");
				}
				
				//�a�I�n��
				Double gatLat = null;
				try {
					gatLat = new Double(req.getParameter("gatLat").trim());
				} catch (NumberFormatException e) {
					gatLat = 0.0;
					errorMsgs.add("�п�J�n��");
				}
				
				//�a�I�g��
				Double gatLng = null;
				try {
					gatLng = new Double(req.getParameter("gatLng").trim());
				} catch (NumberFormatException e) {
					gatLng = 0.0;
					errorMsgs.add("�п�J�g��");
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
				errorMsgs.add("�ק��ƥ���: " + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/gat/gat_update.jsp");
				failureView.forward(req, res);
			}

		}
		if ("insert".equals(action)) {
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				// ��|���s��
				String memNo = req.getParameter("memNo");

				// ���ΦW��
				String gatName = req.getParameter("gatName").trim();
				String gatNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_�I��)]{2,30}$";
				if (gatName == null || gatName.length() == 0) {
					errorMsgs.put("gatName", "���ΦW�ٽФŪť�");
				} else if (!gatName.matches(gatNameReg)) {
					errorMsgs.put("gatName", "���ΦW�٥u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��30����");
				}

				// ���ήɶ�
				java.sql.Date gatTime = null;
				try {
					gatTime = Date.valueOf(req.getParameter("gatTime").trim());
				} catch (IllegalArgumentException e) {
					gatTime = new Date(System.currentTimeMillis());
					errorMsgs.put("gatTime", "�п�J���Τ��");
				}

				// �a�I�]��ɭԷQ��GOOGLE API�^
				String gatLoc = req.getParameter("gatLoc").trim();
				if (gatLoc == null || gatLoc.length() == 0) {
					errorMsgs.put("gatLoc", "���Φa�I�ФŪť�");
				}

				// ²��
				String gatIntro = req.getParameter("gatIntro").trim();
				if (gatIntro == null || gatIntro.length() == 0) {
					errorMsgs.put("gatIntro","����²���ФŪť�");
				}

				// ���W�}�l�ɶ�
				Date gatStarttime = null;
				try {
					gatStarttime = Date.valueOf(req.getParameter("gatStarttime").trim());
				} catch (IllegalArgumentException e) {
					gatStarttime = new Date(System.currentTimeMillis());
					errorMsgs.put("gatStarttime","�п�J���W�}�l���");
				}

				// ���W�I��ɶ�
				Date gatEndtime = null;
				try {
					gatEndtime = Date.valueOf(req.getParameter("gatEndtime").trim());
				} catch (IllegalArgumentException e) {
					gatEndtime = new Date(System.currentTimeMillis());
					errorMsgs.put("gatEndtime", "�п�J���W�I����");
				}

				// ���W�̰��H�ƤW��
				Integer gatMax = null;
				try {
					gatMax = new Integer(req.getParameter("gatMax").trim());
				} catch (NumberFormatException e) {
					gatMax = 0;
					errorMsgs.put("gatMax", "���W�̰��H�ƤW���п�J���");
				}

				// ���W�̧C�H�ƭ���
				Integer gatMin = null;
				try {
					gatMin = new Integer(req.getParameter("gatMin").trim());
				} catch (NumberFormatException e) {
					gatMin = 0;
					errorMsgs.put("gatMin", "���W�̧C�H�ƭ���п�J���");
				}

				// ���ηӤ�
				byte[] gatPhoto = null;
				Part part = req.getPart("gatPhoto");
				InputStream ips = part.getInputStream();
				gatPhoto = new byte[ips.available()];
				ips.read(gatPhoto);
				ips.close();
				
				//�������O
				String gatType = req.getParameter("gatType").trim();
				if (gatType == null || gatType.length() == 0) {
					errorMsgs.put("gatType", "�������O�ФŪť�");
				}
				
				//�a�I�n��
				Double gatLat = null;
				try {
					gatLat = new Double(req.getParameter("gatLat").trim());
				} catch (NumberFormatException e) {
					gatLat = 0.0;
					errorMsgs.put("gatLat", "�п�J�n��");
				}
				
				//�a�I�g��
				Double gatLng = null;
				try {
					gatLng = new Double(req.getParameter("gatLng").trim());
				} catch (NumberFormatException e) {
					gatLng = 0.0;
					errorMsgs.put("gatLng", "�п�J�g��");
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

//				String gatNo = gatVO.getGatNo(); // DAO�w�g�g�n�ۼW�D��Ȧb�ͦ��ɡA�N��������k
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
				errorMsgs.put("Exception", "�X���աI�]��CATCH�F�G" + e.getMessage());
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
				errorMsgs.add("�R����ƥ���: " + e.getMessage());
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
				
//				System.out.println("�ҽk�d�ߪ�VO�]�w����");
				
				res.setContentType("text/html");
				res.setCharacterEncoding("UTF-8");
				PrintWriter out = res.getWriter();
				out.write(jsonarray.toString());
				out.flush();
				out.close();
				
//				System.out.println("�ҽk�d�ߪ�out�w�gclose");
//				String url = "/front-end/gat/gat_update.jsp";
//				RequestDispatcher successfulView = req.getRequestDispatcher(url);
//				successfulView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���: " + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/gat/gat_updateList.jsp");
//				failureView.forward(req, res);
			}
		}
	}

}
