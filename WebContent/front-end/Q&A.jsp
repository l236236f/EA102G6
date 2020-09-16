<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>

<!DOCTYPE html>
<html>
<%@ include file="/front-end/Header/header.jsp"%>
<link href="<%= request.getContextPath() %>/front-end/css/front-qa.css" rel="stylesheet">
<head>
<title>寄寄養養</title>
</head>
<body>
	<div id="qacontainer">
		<h1><img src ='<%= request.getContextPath() %>/front-end/img/qaicon.jpg' width="200px" style="float:left;"> <p>請使用Q&A列表</p></h1>
		<div class='optionBox'>
			<ul class="kind1">
			</ul>
			<span class="after-sign"><i class="fas fa-angle-double-right"></i></span>
			<ul class="kind2">
			</ul>
			<span class="after-sign"><i class="fas fa-angle-double-right"></i></span>
			<ul class="kind3">
			</ul>
		</div>
		<div class='textBox'>
			<div class ="qatext"></div>

		</div>

	</div>
	<!-- Banner Section Begin -->
	<div class="banner-section spad">
		<div class="container-fluid"></div>
	</div>

	<%@ include file="/front-end/Footer/footer.jsp"%>
<%-- 	<%@ include file="selfProject/footer.jsp"%> --%>
	<c:if test="${LoginMem!=null}">
		<%@ include file="/chatRoom/chatRoom.jsp"%>
	</c:if>
</body>
<script src="<%= request.getContextPath() %>/front-end/js/front-qa.js"></script>
<script src="https://kit.fontawesome.com/dd027c1f63.js"
		crossorigin="anonymous"></script>
</html>