<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 設定時間 -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

<%@ page import="java.util.*"%>
<%@ page import="com.article.model.*"%>

<!DOCTYPE html>
<html lang="zxx">
<head>
    <meta charset="UTF-8">
    <meta name="description" content="Fashi Template">
    <meta name="keywords" content="Fashi, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>討論區</title>

<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script>
// 	$(document).ready(function() {
// 		$("tr:even").css("background-color", "white");
// 		$("tr:odd").css("background-color", "#d5d5dc");
// 		$("tr:odd").css("background-color", "#f4f4f4");
// 	});
	
	$(document).ready(function() {
		$("tr").hover(function() {
			oldColor = $(this).css("background-color");
			$(this).css("background-color", "#f5f5dc");
		}, function() {
			$(this).css("background-color", oldColor);
		});
	});
</script>

<style>
	table {
		width: 100%;
		background-color: white;
		margin-top: 5px;
		margin-bottom: 5px;
	}
	table, th, td {
    	border: 0px solid white;
    	height: 50px;
  	}
  	th {
  		background-color: #E7AB3C;
  	}
  	tr, th, td {
    	padding: 5px;
    	text-align: center;
  	}
  	tr:nth-child(2) th {
    	background: rgba(231, 172, 60, 0.65);
	}
	tr {
    	border-bottom: 1px solid #e7ac3c;
	}
</style>
</head>

<body>
	<%@ include file="/front-end/Header/header.jsp" %>
	<%
		ArticleService artSvc = new ArticleService();
    	List<ArticleVO> list = artSvc.getAll();
    	pageContext.setAttribute("list",list);
	%>
	
	<jsp:useBean id="memberSvc" scope="page" class="com.mem.model.MemService" />
	<jsp:useBean id="artresSvc" scope="page" class="com.art_response.model.ArtResponseService" />
	<jsp:useBean id="favarticleSvc" scope="page" class="com.fav_article.model.FavArticleService" />

    <!-- Breadcrumb Section Begin -->
    <div class="breacrumb-section" 
    	 style="height: 500px; 
    			background-image: url(<%=request.getContextPath()%>/front-end/article/images/banner/article_banner.jpg);
    			background-position: center;
    			background-size: cover;
    			background-repeat: no-repeat;">
<%--     	<img alt="" src="<%=request.getContextPath()%>/front-end/article/images/banner/article_banner.jpg"> --%>
    </div>
<!--     Breadcrumb Section Begin -->

<!--     Blog Section Begin -->
    <section class="blog-section spad">
        <div class="container">
            <div class="row" style="margin: 0;">
<!--                 <div class="col-lg-3 col-md-6 col-sm-8 order-2 order-lg-1"> -->
			<%@ include file="page1.file" %>
<!-- 			   換頁 -->
			    <nav class="blog-pagination justify-content-center d-flex"
			    	 style="position=relative; left=50%; min-width: 122.88px; min-height: 47px;">
					<ul class="pagination">
						<li class="page-item">
							<%	if (rowsPerPage < rowNumber) {	%>
								<% 	if (pageIndex >= rowsPerPage) { %> 
								<a href="<%=request.getRequestURI()%>?whichPage=<%=whichPage - 1%>"
									class="page-link" aria-label="Previous"> 
									<i class="ti-angle-left"></i>
								</a>
						</li>
							<% } %>

								<% for (int i = 1; i <= pageNumber; i++) { %>
							<li class="page-item">
								<a href="<%=request.getRequestURI()%>?whichPage=<%=whichPage = i%>"
									class="page-link" id="<%=whichPage%>">
									<%=i%>
								</a>
							</li>
								<% } %>

								<% if (pageIndex < pageIndexArray[pageNumber - 1]) { %>
							<li class="page-item">
								<a href="<%=request.getRequestURI()%>?whichPage=<%=whichPage + 1%>"
								  	class="page-link" aria-label="Next">
									<i class="ti-angle-right"></i> 
								</a>
							</li>
								<% } %>
							<% } %>
						</ul>
					</nav>			    	
				<c:if test="${LoginMem!=null}">
				<div class="addArticle" style="text-align:right">
					<button type="button" class="site-btn" onclick="location.href='addNewArticle.jsp'"
							style="position: relative; left: 870px;">發表文章</button>
				</div>
			    </c:if>	
					<table class="art-list">
			    		<tr class="art-list_head">
			    			<th>文章編號</th>
<!-- 			    		<th>文章分類</th> -->
<!-- 			    		<th>封面圖片</th> -->
			    			<th>文章標題</th>
			    			<th>發文者</th>
			    			<th>回覆數</th>
			    			<th>讚數</th>
			    			<th>追蹤數</th>
			    			<th>發佈時間</th>
			    		</tr>
			    		
			    		<tr class="art-list_notice">
			    			<th>置頂公告</th>
<!-- 			    		<th>文章分類</th> -->
<!-- 			    		<th>封面圖片</th> -->
			    			<th>
			    				<a href="article_notice.jsp">[版規]發表文章前，請先詳細閱讀版規</a>
			    			</th>
			    			<th>管理員</th>
			    			<th>0</th>
			    			<th>0</th>
			    			<th>0</th>
			    			<th>2020-09-01<br>00:00:00</th>
			    		</tr>
			    		
						<c:forEach var="articleVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
			    		<tr class="art-list_body">
			    			<td>${articleVO.artno}</td>
			    			<td>
			    				<a href="<%=request.getContextPath()%>/front-end/article/article.do?artno=${articleVO.artno}&action=getOneArticle_For_Display">
			    					${articleVO.arttitle}
			    				</a>
			    			</td>
			    			<td>
								<c:forEach var="memVO" items="${memberSvc.all}">
                    				<c:if test="${articleVO.memno==memVO.memNo}">
	                    				<h5>${memVO.memName}</h5>
                    				</c:if>
                				</c:forEach>
							</td>
			    			<td>${artresSvc.getOneResByArtno(articleVO.artno).size()}</td>
			    			<td>${articleVO.gpcount}</td>
			    			<td>${favarticleSvc.getOneFavByArtno(articleVO.artno).size()}</td>
			    			<td>
			    				<fmt:formatDate value="${articleVO.arttime}" pattern="yyyy-MM-dd"/><br>
			    				<fmt:formatDate value="${articleVO.arttime}" pattern="HH:mm:ss"/>
			    			</td>
			    		</tr>
			    		</c:forEach>
			    	</table>
			    				    	
<!-- 			           換頁 -->
			    	<nav class="blog-pagination justify-content-center d-flex"
			    		 style="position=relative; left=50%; min-width: 122.88px; min-height: 47px; margin-top:10px">
							<ul class="pagination">
								<li class="page-item">
								<%	if (rowsPerPage < rowNumber) {	%>
									<% 	if (pageIndex >= rowsPerPage) { %> 
									<a href="<%=request.getRequestURI()%>?whichPage=<%=whichPage - 1%>"
									   class="page-link" aria-label="Previous"> 
									   <i class="ti-angle-left"></i>
									</a>
								</li>
									<% } %>

									<% for (int i = 1; i <= pageNumber; i++) { %>
								<li class="page-item">
									<a href="<%=request.getRequestURI()%>?whichPage=<%=whichPage = i%>"
									   class="page-link" id="<%=whichPage%>">
									   <%=i%>
									</a>
								</li>
									<% } %>

									<% if (pageIndex < pageIndexArray[pageNumber - 1]) { %>
								<li class="page-item">
									<a href="<%=request.getRequestURI()%>?whichPage=<%=whichPage + 1%>"
								  	   class="page-link" aria-label="Next">
								   		<i class="ti-angle-right"></i> 
								   	</a>
								</li>
									<% } %>
								<% } %>
							</ul>
						</nav>
			    	
            </div>
        </div>
    </section>
    <!-- Blog Section End -->
    
    <%@ include file="/front-end/Footer/footer.jsp" %>
    <c:if test="${LoginMem!=null}">
		<%@ include file="/chatRoom/chatRoom.jsp"%>
	</c:if>
    
</body>
</html>