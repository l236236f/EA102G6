<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.uprod.model.*"%>

<%
	UprodVO uprodVO = (UprodVO) request.getAttribute("uprodVO");
%>
<html>
<head>
<%@ include file="/front-end/Header/header.jsp" %>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>員工資料新增 - addEmp.jsp</title>

<style>
table#table-1 {
	background-color: orange;
	border: 2px solid black;
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
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	border: 0px solid #CCCCFF;
}

th, td {
	padding: 1px;
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

	<h3>資料新增:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="uprod.do" name="form1" enctype="multipart/form-data">
		<table>
		    <tr>
				<td>賣家編號:</td>
				<td><%=memVO.getMemNo()%></td>
				<td><input type="hidden" name="SellerNo" size="45"
					value="<%=memVO.getMemNo()%>" /></td>
			</tr>
			<tr>
				<td>商品名稱:</td>
				<td><input type="TEXT" name="ProdName" size="45"
					value="<%= (uprodVO==null)? "" : uprodVO.getProdName()%>" /></td>
			</tr>

			<tr>
				<td>商品內容:</td>
				
			    <td><textarea name="ProdIntro" rows="5" cols="30" wrap="off"
					>${uprodVO.prodIntro}</textarea>
				</td>
			</tr>
			<tr>
				<td>價格:</td>
				<td><input type="TEXT" name="Price" size="45"
					value="<%= (uprodVO==null)? "" : uprodVO.getPrice()%>" /></td>
			</tr>

			<tr>
				<td>商品種類:</td>
<!-- 				<td><input type="TEXT" name="ProdType" size="45" -->
<%-- 					value="<%= (uprodVO==null)? "" : uprodVO.getProdType()%>" /></td> --%>
					
					
				  <td>
		           <select name="ProdType" size="1">
		           <option value=A selected>毛孩伙食</option>
		           <option value=B >日常用品 </option>
		           <option value=C>清潔用品 </option>
		           </select>
		           </td>
					
					
					
					
			</tr>
			<tr>
			    <td>商品照片</td>
			    <td><input type='file' name="Photo" /> </td>
			</tr>

		</table>
		<br> <input type="hidden" name="action" value="insert"> <input
			type="submit" value="送出新增">
	</FORM>
	<%@ include file="/front-end/Footer/footer.jsp" %>
</body>
</html>