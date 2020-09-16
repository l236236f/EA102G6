<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.gat.model.*"%>

<%
	GatService gatSvc = new GatService();
	List<GatVO> list = gatSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html>
<%@ include file="/front-end/Header/header.jsp"%>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<head>
<title>´ª¹Î¦Cªí</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/gat/style/style_gat_listAll.css" />
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
						<%@ include file="page1.file"%>
							<c:forEach var="gatVO" items="${list}" begin="<%=pageIndex%>"
								end="<%=pageIndex+rowsPerPage-1%>">
								<c:if test='${gatVO.gatStatus.equals("G0")}'>
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
						<nav class="blog-pagination justify-content-center d-flex">
							<ul class="pagination">
								<li class="page-item">
									<%
										if (rowsPerPage < rowNumber) {
									%>
									<% 
									
										if (pageIndex >= rowsPerPage) {
										
 									%> <a href="<%=request.getRequestURI()%>?whichPage=<%=whichPage - 1%>"
									class="page-link" aria-label="Previous"> <i
										class="ti-angle-left"></i>
								</a>
								</li>
								<%
									}
								%>

								<%
									for (int i = 1; i <= pageNumber; i++) {
								%>
								<li class="page-item"><a
									href="<%=request.getRequestURI()%>?whichPage=<%=whichPage = i%>"
									class="page-link" id="<%=whichPage%>"><%=i%></a></li>
								<%
									}
								%>

								<%
									if (pageIndex < pageIndexArray[pageNumber - 1]) {
								%>
								<li class="page-item"><a
									href="<%=request.getRequestURI()%>?whichPage=<%=whichPage + 1%>"
									class="page-link" aria-label="Next"><i
										class="ti-angle-right"></i> </a></li>
								<%
									}
								%>
								<%
									}
								%>
							</ul>
						</nav>
					</div>
					<div id="search_gatalllist" style="display: none;">
					</div>
				</div>
				<div class="col-lg-4">
					<div class="blog_right_sidebar">

						<aside class="single_sidebar_widget search_widget">
							<form action="#">
								<div class="form-group" style="margin-bottom: 0!important;">
									<div class="input-group mb-3" style="margin-bottom: 0!important;">
										<input type="text" class="form-control" id="search_keyword"
											placeholder='¤å³¹¼ÐÃD·j´M' onfocus="this.placeholder = ''"
											onblur="this.placeholder = 'Search Keyword'">
									</div>
								</div>
							</form>
						</aside>



						<aside class="single_sidebar_widget post_category_widget">
							<h4 class="widget_title">¤å³¹¤ÀÃþ</h4>
							<ul class="list cat-list">
								<li><a href="gat_listAllType.jsp?gatType=Katze" class="d-flex">
										<div class="animal-icon" style="background-image: url(<%=request.getContextPath()%>/front-end/gat/images/icon_cat.svg);">
										</div>
										<p>-- ¿ß«}ØpØpØp --</p>
								</a></li>
								<li><a href="gat_listAllType.jsp?gatType=Hund" class="d-flex">
										<div class="animal-icon" style="background-image: url(<%=request.getContextPath()%>/front-end/gat/images/icon_dog.svg);">
										</div>
										<p>-- ª¯ª¯¨L¨L¨L --</p>
								</a></li>
								<li><a href="gat_listAllType.jsp?gatType=Schlange" class="d-flex">
										<div class="animal-icon" style="background-image: url(<%=request.getContextPath()%>/front-end/gat/images/icon_snake.svg);">
										</div>
										<p>-- ³D³D¼R¼R¼R --</p>
								</a></li>
								<li><a href="gat_listAllType.jsp?gatType=Fisch" class="d-flex">	
										<div class="animal-icon" style="background-image: url(<%=request.getContextPath()%>/front-end/gat/images/icon_fish.svg);">
										</div>
										<p>-- ³½³½ÔqÔqÔq --</p>
								</a></li>
								<li><a href="gat_listAllType.jsp?gatType=Vogel" class="d-flex">
										<div class="animal-icon" style="background-image: url(<%=request.getContextPath()%>/front-end/gat/images/icon_bird.svg);">
										</div>
										<p>-- ³¾³¾³î³î³î --</p>
								</a></li>
								<li><a href="gat_listAllType.jsp?gatType=Hase" class="d-flex">
										<div class="animal-icon" style="background-image: url(<%=request.getContextPath()%>/front-end/gat/images/icon_bird.svg);">
										</div>
										<p>-- ¨ß¨ß¸õ¸õ¸õ --</p>
								</a></li>
								<li><a href="gat_listAllType.jsp?gatType=Andere" class="d-flex">
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