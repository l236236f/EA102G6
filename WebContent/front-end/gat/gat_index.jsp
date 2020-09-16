<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="java.util.*"%>
<%@ page import="com.gat.model.*"%>
<%@ page import="com.gat_weather.model.*"%>
<%@ page import="com.mem.model.*"%>

<%
	GatService gatSvc = new GatService();
	List<GatVO> list = gatSvc.getAll();
	pageContext.setAttribute("list", list);
	
	List<GatVO> listEndtime = gatSvc.getAllByEndtime();
	pageContext.setAttribute("listEndtime", listEndtime);

	WeatherData weatherData = new WeatherData();
	Map<String, List<WeatherVO>> weatherMap = weatherData.getWeatherDate();
	pageContext.setAttribute("weatherMap", weatherMap);
	
	MemService memSvc = new MemService();
%>

<!DOCTYPE html>
<html>
<%@ include file="/front-end/Header/header.jsp"%>
<head>
<title>揪團首頁</title>
 <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/gat/style/style_gat_index.css" />
</head>
<body>
	<!-- bradcam_area_start -->
	<div id="benner_bg">
		<div id="gat_wrapper">
			<video autoplay muted loop id="myVideo">
				<source src="images/gat_index.mp4" type="video/mp4">
			</video>
		</div>
		<div class="gat_container">
			<div class="gat_row">
				<div class="col-lg-12">
					<div class="bradcam_text gat_text">
						<h3>嗨！${LoginMem.memName}，找時間聚聚吧！</h3>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- bradcam_area_end -->

	<div class="main-content-wrapper gat-section-padding-100">
		<div class="container">
			<!-- 	-------------------------------------------------------------------------------- -->
			<div class="row justify-content-center">
				<!-- ============= Post Content Area Start ============= -->
				<div class="col-12 col-lg-8">
					<div class="post-content-area mb-50">

						<div class="world-catagory-area">
							<ul class="nav nav-tabs" id="myTab" role="tablist">
								<li class="title" style="font-family:microsoft jhenghei;"><i class="fa fa-paw"></i> 最近的揪團</li>
							</ul>

							<div class="tab-content" id="myTabContent">
								
								<div id="carouselExampleIndicators" class="carousel slide"
									data-ride="carousel">
									<ol class="carousel-indicators">
										<li data-target="#carouselExampleIndicators" data-slide-to="0"
											class="active"></li>
										<li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
										<li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
										<li data-target="#carouselExampleIndicators" data-slide-to="3"></li>
									</ol>
									<div class="carousel-inner">
										<c:forEach var="gatVO" items="${list}" begin="0" end="0">
												<div class="carousel-item active">
													 <div class="gatIndex-image" style="background-image: url(<%=request.getContextPath()%>/front-end/ShowPhotos?type=gatPhoto&photo_no=${gatVO.gatNo});"></div>
													 <div class="gatIndex-text carousel-caption d-none d-md-block">
													    <a href="gat_listOne.jsp?gatNo=${gatVO.gatNo}">
													    <h5>${gatVO.gatName}</h5>
													    </a>
													    <p><i class="fa fa-clock-o"></i> <fmt:setLocale value="en_US" /><fmt:formatDate value="${gatVO.gatTime}" pattern="MMM. d, yyyy"/></p>
		  											</div>
												</div>
											</c:forEach>
											<c:forEach var="gatVO" items="${list}" begin="1" end="3">
												<div class="carousel-item">
												<div class="gatIndex-image" style="background-image: url(<%=request.getContextPath()%>/front-end/ShowPhotos?type=gatPhoto&photo_no=${gatVO.gatNo});"></div>
													<div class="gatIndex-text carousel-caption d-none d-md-block">
													   <a href="gat_listOne.jsp?gatNo=${gatVO.gatNo}">
													   <h5>${gatVO.gatName}</h5>
													   </a>
													   <p><i class="fa fa-clock-o"></i> <fmt:setLocale value="en_US" /><fmt:formatDate value="${gatVO.gatTime}" pattern="MMM. d, yyyy"/></p>
		  											</div>
												</div>
										</c:forEach>
									</div>
									<a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
									<span class="carousel-control-prev-icon" aria-hidden="true"></span>
										<span class="sr-only">Previous</span>
									</a>
									<a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
									<span class="carousel-control-next-icon" aria-hidden="true"></span>
										<span class="sr-only">Next</span>
									</a>
								</div>
								
							</div>
						</div>
					</div>
				</div>

				<!-- ========== Sidebar Area ========== -->
				<div class="col-12 col-md-8 col-lg-4">
					<div class="post-sidebar-area">
						<!-- Widget Area -->
						<div class="sidebar-widget-area">
							<h5 class="title"><i class="fa fa-cloud"></i> 天氣狀況</h5>
							<div class="widget-content weather_content">
							<div class="weather_wrapper">
								<div class="weather_info">
								<c:set var="LocDate" value="${weatherMap.get('桃園市').get(0).starttime}"/>
								<c:set var="LocMonth" value="${fn:substring(LocDate, 5, 7)}" />
								<c:set var="LocDay" value="${fn:substring(LocDate, 8, 10)}" />
								<div style="display: display: inline-block;">
								<h5 style="font-size: 25px; font-weight: bolder;">${LocMonth}月${LocDay}日${weatherMap.get('桃園市').get(0).county}</h5>
								
								</div>
								<div style="display: inline-block;">
								<p style="margin-bottom: 0; color: #b2b2b2;">${weatherMap.get('桃園市').get(0).type}</p>
								<p style="margin-bottom: 0; color: #b2b2b2;">${weatherMap.get('桃園市').get(0).totalDescription}</p>
								</div>
								<div class="tem_wrapper">
								<div style="font-size: 50px;color: #636363b5;font-weight: bold;">${weatherMap.get('桃園市').get(0).temperature}</div>
								<span>&deg;C</span>
								</div>
								</div>
								<div class="weather_img">
								<img style="width: 90%; filter: drop-shadow(9px 8px 10px grey);" src="https://www.cwb.gov.tw/V8/assets/img/weather_icons/weathers/svg_icon/day/${weatherMap.get('桃園市').get(0).getTypeCode()}.svg">
								</div>
								</div>
							</div>
						</div>
						<!-- Widget Area -->
						<div class="sidebar-widget-area" >
							<h5 class="title"><i class="fa fa-exclamation-circle"></i> 即將截止報名</h5>
							<div class="widget-content">
					
							<c:forEach var="gatVO" items="${listEndtime}" begin="0" end="2">
								<div class="single-blog-post post-style-2 d-flex align-items-center widget-post">
									<div class="endtime-image post-thumbnail" style="background-image: url(<%=request.getContextPath()%>/front-end/ShowPhotos?type=gatPhoto&photo_no=${gatVO.gatNo});">
									</div>
									<div class="endtime-text post-content" style="font-family:microsoft jhenghei;">
										<a href="gat_listOne.jsp?gatNo=${gatVO.gatNo}" class="headline">
											<h5 class="mb-0">${gatVO.gatName}</h5>
											<p><i class="fa fa-clock-o"></i> <fmt:setLocale value="en_US" /><fmt:formatDate value="${gatVO.gatEndtime}" pattern="MMM. d, yyyy"/></p>
										</a>
									</div>
								</div>
							</c:forEach>

							</div>
						</div>


					</div>
				</div>
			</div>
			<!-- 	------------------------------------------------------------------------------------------------------------------------------------ -->

			<div class="row justify-content-center">

				<div class="col-12 col-md-4 col-lg-4">
					<div class="single-blog-post post-style-3 mt-50">
						<div class="post-thumbnail">
							<a href="<%= request.getContextPath()%>/front-end/gat/gat_add.jsp">
							<div class="gatadd" style="background-image: url(<%=request.getContextPath()%>/front-end/gat/images/gatadd-index.jpg);">
								<div class="gatadd-text submitbtn">開始建立揪團！</div>
							</div></a>
						</div>
					</div>
				</div>
				<!-- ========== Single Blog Post ========== -->
				<div class="col-12 col-md-4 col-lg-4">
					<div class="single-blog-post post-style-3 mt-50">
						<div class="post-thumbnail">
							<a href="<%= request.getContextPath()%>/front-end/gat/gat_listAll.jsp">
							<div class="gatlist" style="background-image: url(<%=request.getContextPath()%>/front-end/gat/images/gatlist-index.jpg);">
								<div class="gatadd-text submitbtn">看看最近有甚麼活動</div>
							</div></a>
						</div>
					</div>
				</div>
				<!-- ========== Single Blog Post ========== -->
				<div class="col-12 col-md-4 col-lg-4">
					<div class="single-blog-post post-style-3 mt-50">
						<div class="post-thumbnail">
						<a href="<%= request.getContextPath()%>/front-end/gat/gat_updateList.jsp">
							<div class="gatfav" style="background-image: url(<%=request.getContextPath()%>/front-end/gat/images/gatfav-index.jpg);">
								<div class="gatadd-text submitbtn">揪團管理列表</div>
							</div></a>
						</div>
					</div>
				</div>


			</div>
			<!-- 	------------------------------------------------------------------------------------------- -->
		</div>
	</div>
	<%@ include file="/front-end/Footer/footer.jsp"%>
</body>


</html>