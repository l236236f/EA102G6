<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.uprod.model.*"%>

<html>
<head>
<%@ include file="/front-end/Header/header.jsp" %>
<%
  UprodVO uprodVO = (UprodVO) request.getAttribute("uprodVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>商品資料修改 - update_uprod_input.jsp</title>

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

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="uprod.do" name="form1" enctype="multipart/form-data" >
<table>
	<tr>
		<td>商品編號:<font color=red><b>*</b></font></td>
		<td><%=uprodVO.getProdNo()%></td>
	</tr>
	<tr>
		<td>商品名稱:</td>
		<td><input type="TEXT" name="ProdName" size="45" value="<%=uprodVO.getProdName()%>" /></td>
	</tr>
	
	<tr>
<!-- 		<td>商品內容:</td> -->
<%-- 		<td><input type="TEXT" name="ProdIntro" size="45" value="<%=uprodVO.getProdIntro()%>" /></td> --%>
	<td>商品內容:</td>
				
			    <td><textarea name="ProdIntro" rows="5" cols="30" wrap="off"
				 >${uprodVO.prodIntro} </textarea>
				</td>
	
	</tr>
	<tr>
		<td>價格:</td>
		<td><input type="TEXT" name="Price" size="45"	value="<%=uprodVO.getPrice()%>" /></td>
	</tr>
	
	<tr>
		<td>商品種類:</td>
<%-- 		<td><input type="TEXT" name="ProdType" size="45"	value="<%=uprodVO.getProdType()%>" /></td> --%>
	
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
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="ProdNo" value="<%=uprodVO.getProdNo()%>" />
<input type="submit" value="送出修改">
</FORM>
<%@ include file="/front-end/Footer/footer.jsp" %>
</body>
</html>