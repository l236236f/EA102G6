<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.gat.model.*"%>

<%
	GatService gatSvc = new GatService();
	List<GatVO> list = gatSvc.getAll();
	pageContext.setAttribute("list", list);
	
	String memNo = request.getParameter("memNo");
	String gatType = request.getParameter("gatType");
	
	MemService memSvc = new MemService();
	pageContext.setAttribute("memSvc", memSvc);

	MemVO memVO2 = memSvc.getOneMem(memNo);
	
	switch(gatType){
	case "Katze":
		gatType = "¿ß«}ØpØpØp";
		break;
	case "Hund":
		gatType = "ª¯ª¯¨L¨L¨L";
		break;
	case "Schlange":
		gatType = "³D³D¼R¼R¼R";
		break;
	case "Fisch":
		gatType = "³½³½ÔqÔqÔq";
		break;
	case "Vogel":
		gatType = "³¾³¾³î³î³î";
		break;
	case "Hase":
		gatType = "¨ß¨ß¸õ¸õ¸õ";
		break;
	case "Andere":
		gatType = "¨ä¥L¦o¨e¥¦";
		break;	
	}
	pageContext.setAttribute("gatType", gatType);
	pageContext.setAttribute("memNo", memNo);
	System.out.println(gatType);
	System.out.println(memNo);
%>

<!DOCTYPE html>
<html>
<%@ include file="/front-end/Header/header.jsp"%>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<head>
<title>´ª¹Î¦Cªí</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/gat/style/style_gat_listAllMem.css" />
</head>
<body>
	<!-- bradcam_area_start -->
	<div id="benner_bg">
		<div id="gat_wrapper">
			<video autoplay muted loop id="myVideo">
				<source src="images/gat_listall.mp4" type="video/mp4">
			</video>
		</div>
		<div class="gat_container">
			<div class="gat_row">
				<div class="col-lg-12">
					<div class="bradcam_text text-center">
						<h3>¨Ó»E»E§a¡I</h3>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- bradcam_area_end -->
	<!--================Blog Area =================-->
	<section class="blog_area gat_section-padding">
		<div class="container">
			<div class="row">
				<div class="col-lg-8 mb-5 mb-lg-0">
					
					<div class="blog_left_sidebar" id="default_gatalllist">
							<c:forEach var="gatVO" items="${list}">
								<c:if test="${gatVO.gatType.equals(gatType)&&gatVO.memNo.equals(memNo)}">
									<article class="blog_item" id="${gatVO.gatStatus}">
										<input type="hidden" value="${gatVO.gatStatus}">
										<div class="gatlist-wrapper">
											<div class="blog_item_img"
												style="background-image: url(<%=request.getContextPath()%>/front-end/ShowPhotos?type=gatPhoto&photo_no=${gatVO.gatNo});">
											</div>
											<div class="blog_item_date">
												<h3>
													<fmt:setLocale value="en_US" />
													<fmt:formatDate value="${gatVO.gatTime}"
														pattern="MMM. d, yyyy" />
												</h3>
												<p>
													<fmt:setLocale value="en_US" />
													<fmt:formatDate value="${gatVO.gatTime}" pattern="EEEE" />
												</p>
											</div>
										</div>
										<div class="blog_details">
											<a class="d-inline-block"
												href="gat_listOne.jsp?gatNo=${gatVO.gatNo}">
												<h2>${gatVO.gatName}</h2>
											</a>
											<p>${gatVO.gatIntro}</p>
											<ul class="blog-info-link">
												<li><i class="fa fa-map-marker"></i>${gatVO.gatLoc}</li>
												<li><i class="fa fa-tags"></i>${gatVO.gatType}</li>
											</ul>
										</div>
									</article>
								</c:if>
							</c:forEach>
							
					</div>
					<div id="search_gatalllist" style="display: none;">
					</div>
				</div>
				<div class="col-lg-4">
					<div class="blog_right_sidebar">

						<aside class="single_sidebar_widget search_widget">
								<div class="author-img"
									style="background-image: url(<%=request.getContextPath()%>/front-end/mem/memPicture.do?memNo=<%=memNo%>);">
								</div>
								<a href="gat_listAllMem.jsp?memNo=<%=memVO2.getMemNo()%>">
								<h5 style="text-align: center;"><%=memVO2.getMemName()%>ªº¤å³¹¦Cªí</h5>
								</a>
						</aside>



						<aside class="single_sidebar_widget post_category_widget">
							<h4 class="widget_title"><%=memVO2.getMemName()%>ªº¤å³¹¤ÀÃþ</h4>
														<ul class="list cat-list">
								<li><a href="gat_listMemType.jsp?gatType=Katze&memNo=<%=memNo%>" class="d-flex">
										<div class="animal-icon" style="background-image: url(<%=request.getContextPath()%>/front-end/gat/images/icon_cat.svg);">
										</div>
										<p>-- ¿ß«}ØpØpØp --</p>
								</a></li>
								<li><a href="gat_listMemType.jsp?gatType=Hund&memNo=<%=memNo%>" class="d-flex">
										<div class="animal-icon" style="background-image: url(<%=request.getContextPath()%>/front-end/gat/images/icon_dog.svg);">
										</div>
										<p>-- ª¯ª¯¨L¨L¨L --</p>
								</a></li>
								<li><a href="gat_listMemType.jsp?gatType=Schlange&memNo=<%=memNo%>" class="d-flex">
										<div class="animal-icon" style="background-image: url(<%=request.getContextPath()%>/front-end/gat/images/icon_snake.svg);">
										</div>
										<p>-- ³D³D¼R¼R¼R --</p>
								</a></li>
								<li><a href="gat_listMemType.jsp?gatType=Fisch&memNo=<%=memNo%>" class="d-flex">	
										<div class="animal-icon" style="background-image: url(<%=request.getContextPath()%>/front-end/gat/images/icon_fish.svg);">
										</div>
										<p>-- ³½³½ÔqÔqÔq --</p>
								</a></li>
								<li><a href="gat_listMemType.jsp?gatType=Vogel&memNo=<%=memNo%>" class="d-flex">
										<div class="animal-icon" style="background-image: url(<%=request.getContextPath()%>/front-end/gat/images/icon_bird.svg);">
										</div>
										<p>-- ³¾³¾³î³î³î --</p>
								</a></li>
								<li><a href="gat_listMemType.jsp?gatType=Hase&memNo=<%=memNo%>" class="d-flex">
										<div class="animal-icon" style="background-image: url(<%=request.getContextPath()%>/front-end/gat/images/icon_bird.svg);">
										</div>
										<p>-- ¨ß¨ß¸õ¸õ¸õ --</p>
								</a></li>
								<li><a href="gat_listMemType.jsp?gatType=Andere&memNo=<%=memNo%>" class="d-flex">
										<div class="animal-icon" style="background-image: url(<%=request.getContextPath()%>/front-end/gat/images/icon_paw.svg);">
										</div>
										<p>-- ¨ä¥L¦o¨e¥¦ --</p>
								</a></li>
							</ul>
						</aside>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!--================Blog Area =================-->
	<%@ include file="/front-end/Footer/footer.jsp"%>
</body>
<script type="text/javascript">
	
	var input = document.getElementById('search_keyword');
	input.addEventListener('input', function(){
		$("#search_gatalllist").empty();
		var keyword = $("#search_keyword").val();
		$.ajax({
			url: "<%=request.getContextPath()%>/front-end/gat/gat.do",
			type : "POST",
			data : {
					"action" : "gatlist_search",
					"keyword" : keyword
					},
			dataType: "json",
			success : function(data){
					$("#default_gatalllist").hide();
					$("#search_gatalllist").css("display", "block");
					$(data).each(function(i, item){
						var str = '<article class="blog_item"><div class="gatlist-wrapper"><div class="blog_item_img" id="search_img" style="background-image: url(/EA102G6/front-end/ShowPhotos?type=gatPhoto&photo_no='+item.gatNo+');"></div><div class="blog_item_date"></div></div><div class="blog_details" id="search_detail">'
						+'<a class="d-inline-block" id="search_name" href="gat_listOne.jsp?gatNo='+item.gatNo+'"><h2>'+item.gatName+'</h2></a><p>'+item.gatIntro+'</p><ul class="blog-info-link"><li id="search_loc"><i class="fa fa-map-marker"></i>'+item.gatLoc+'</li><li><i class="fa fa-tags"></i>'+item.gatType+'</li></ul></div></article>';
						$("#search_gatalllist").append(str);
					});
				},
			error : function(){alert("ajax¥X¿ùÅo")}
		});		
	});

</script>

</html>