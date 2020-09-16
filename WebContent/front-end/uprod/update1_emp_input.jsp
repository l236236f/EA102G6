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
<title>商品資料修改(購買) - update_uprod_input.jsp</title>

<style>
  table#table-1 {
	background-color: orange;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: black;
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
	<tr><td>
		 <h3>購買商品 - update_uprod_input.jsp</h3>
		
		 <h4><a href="usedProduct.jsp">返回二手市集</a></h4>
	</td></tr>
</table>

<h3>購買商品:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="uprod.do" name="form1" >
<table>
	<tr>
		<td>商品編號:<font color=red><b>*</b></font></td>
		<td><%=uprodVO.getProdNo()%></td>
	</tr>
	<tr>
		<td>買家編號:</td>
<%-- 		<td><input type="TEXT" name="CustNo" size="45" value="<%=(uprodVO.getCustNo()==null) ? "" :uprodVO.getCustNo()%>" /></td> --%>
	<td><%=memVO.getMemNo() %></td>
	<td><input type="hidden" name="CustNo" value="<%=memVO.getMemNo()%>" /></td>
	</tr>
	
	<tr>
		<td>寄送方法:</td>
		<td>
		<select name="TranMethod" size="1">
		<option value=TM0> 超商取貨 </option>
		<option value=TM1 selected> 宅配 </option>
		<option value=TM2> 面交 </option>
		</select>
		</td>
		
<%-- 		<td><input type="TEXT" name="TranMethod" size="45"	value="<%=(uprodVO.getTranMethod()==null) ? "" :uprodVO.getTranMethod()%>" /></td> --%>
	</tr>
	
	<tr>
		<td>寄送地址:</td>
		<td><input type="TEXT" name="TranAddr" size="45"	value="<%=(uprodVO.getTranAddr()==null) ? "" :uprodVO.getTranAddr()%>" /></td>
	</tr>
	
</table>
<br>
<input type="hidden" name="action" value="buy">
<input type="hidden" name="ProdNo" value="<%=uprodVO.getProdNo()%>" />
<input type="submit" value="確認購買">
</FORM>
<%@ include file="/front-end/Footer/footer.jsp" %>
</body>
</html>