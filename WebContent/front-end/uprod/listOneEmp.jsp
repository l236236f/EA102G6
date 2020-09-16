<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.uprod.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>



<html>
<head>
<%@ include file="/front-end/Header/header.jsp" %>
<%
	UprodVO uprodVO = (UprodVO) request.getAttribute("uprodVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>
<style>
table#table-1 {
	background-color:orange;
	border: 2px ;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}
</style>

<style>
table {
	width: 600px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 5px;
	text-align: center;
}
</style>

</head>
<body bgcolor='white'>

	
	<table id="table-1">
		<tr>
			<td>
				
				<h4>
					<a href="usedProduct.jsp">返回二手市集</a>
				</h4>
			</td>
		</tr>
	
	</table>
    

    <div>
    <table>
    <tr>
    <td><h6>二手商品編號:${uprodVO.prodNo}</h6></td>
    </tr>
    <tr>
    <td><h6>賣家編號:${uprodVO.sellerNo}</h6></td>
    </tr>	
    <tr>
    <td><h6>商品名稱:${uprodVO.prodName}</h6></td>
    </tr>	
    <tr>
    <c:if test="${uprodVO.prodType=='A'}">
		    <td><h6>商品種類:毛孩伙食</h6></td>
    </c:if>
    
     <c:if test="${uprodVO.prodType=='B'}">
		    <td><h6>商品種類:日常用品</h6></td>
    </c:if>
    
     <c:if test="${uprodVO.prodType=='C'}">
		    <td><h6>商品種類:清潔用品</h6></td>
    </c:if>
    </tr>	
    <tr>
    <td><h6>商品介紹:${uprodVO.prodIntro}</h6></td>
    </tr>	
    <tr>
    <td><h6>商品單價:${uprodVO.price}</h6></td>
    </tr>	
    <tr>
    <td><h6>上架時間:<fmt:formatDate value="${uprodVO.increaseTime}" pattern="yyyy-MM-dd HH:mm:ss" /></h6></td>
    </tr>		
    <tr>
    <td><img class="pics" src="<%=request.getContextPath()%>/front-end/uprod/showpic.do?ProdNo=${uprodVO.getProdNo()}" /></td>
    </tr>
    </table>

<!-- 	<table> -->
<!-- 		<tr> -->
<!-- 		<th>二手商品編號</th> -->
<!-- 		<th>賣家編號</th> -->
<!-- 		<th>買家編號</th> -->
<!-- 		<th>商品名稱</th> -->
<!-- 		<th>商品介紹</th> -->
<!-- 		<th>商品單價</th> -->
<!-- 		<th>上架時間</th> -->
<!-- 		<th>二手商品狀態</th> -->
<!-- 		<th>訂單處理狀態</th> -->
<!-- 		<th>確認領收商品</th> -->
<!-- 		<th>訂單成立時間</th> -->
<!-- 		<th>寄送方式</th> -->
<!-- 		<th>寄送地址</th> -->
<!-- 		<th>商品種類</th> -->
<!-- 		<th>商品評價顆星數</th> -->
<!-- 		<th>商品圖片</th> -->
<!-- 		</tr> -->
<!-- 		<tr> -->
<%-- 			<td>${uprodVO.prodNo}</td> --%>
<%-- 			<td>${uprodVO.sellerNo}</td> --%>
<%-- 			<td>${uprodVO.custNo}</td> --%>
<%-- 			<td>${uprodVO.prodName}</td> --%>
<%-- 			<td>${uprodVO.prodIntro}</td> --%>
<%-- 			<td>${uprodVO.price}</td> --%>
<%-- 			<td><fmt:formatDate value="${uprodVO.increaseTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td> --%>
<%-- 			<td>${uprodVO.prodStatus}</td> --%>
<%-- 			<td>${uprodVO.orderStatus}</td> --%>
<%-- 			<td>${uprodVO.receiveStatus}</td> --%>
<%-- 			<td><fmt:formatDate value="${uprodVO.orderTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td> --%>
<%-- 			<td>${uprodVO.tranMethod}</td> --%>
<%-- 			<td>${uprodVO.tranAddr}</td> --%>
<%-- 			<td>${uprodVO.prodType}</td> --%>
<%-- 			<td>${uprodVO.evaStar}</td> --%>
<!-- 			<td> -->
<%-- 			<img class="pics" src="<%=request.getContextPath()%>/front-end/uprod/showpic.do?ProdNo=${uprodVO.getProdNo()}" /> --%>
<!--             </td> -->
<!-- 		</tr> -->
<!-- 	</table> -->
<%@ include file="/front-end/Footer/footer.jsp" %>
</body>
</html>