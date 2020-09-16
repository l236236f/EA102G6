<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.sprod_order.model.*"%>
<%@ page import="java.util.*" %>

<%
  Sprod_orderVO sprodOrderVO = (Sprod_orderVO) request.getAttribute("sprodOrderVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>



<html>
<%@ include file="/front-end/Header/header.jsp" %>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>收貨確認</title>

<!-- bootstrap4.5.2 -->
<!-- CSS only -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">

</head>
<body>
<div style="background-image: linear-gradient(to top, #fff1eb 0%, #ace0f9 100%);">

<h1>會員訂單收貨確認</h1>

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

<FORM METHOD="post" ACTION="sprod_order.do" name="form1">

<table class="table table-striped">
  <thead>
    <tr>
      <th scope="col">訂單編號</th>
      <th scope="col">會員編號</th>
      <th scope="col">訂單成立時間</th>
      <th scope="col">寄送方式</th>
      <th scope="col">寄送地址</th>
      <th scope="col">收件人姓名</th>
      <th scope="col">收件人信箱</th>
      <th scope="col">總金額</th>
      <th scope="col">確認收貨</th>
      <th scope="col">確認</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      	<th scope="row"><%=sprodOrderVO.getOrderNo()%></th>
      	<td><%=sprodOrderVO.getMemNo()%></td>
      	<td><%=sprodOrderVO.getOrderTime()%></td>
      	<td>
      		<% 
				String str=null;
				int a = new Integer(sprodOrderVO.getTranMethod());
				if (a == 1){
					str = "超商取貨";
				}else{
					str = "宅配";
				}
			%>
			<%=str %>
		</td>
      	<td><%=sprodOrderVO.getTranAdd()%></td>
      	<td><%=sprodOrderVO.getAddresseeName()%></td>
      	<td><%=sprodOrderVO.getAddresseeMail()%></td>
      	<td><%=sprodOrderVO.getOrderTotal()%></td>
      	<% if(sprodOrderVO.getOrderStatus()==1){ %>
      	<td>
      			<select size="1" name="orderStatus">
					<option value ="1">已出貨(未到貨)</option>
					<option value ="2">完成(已到貨)</option>
					<option value ="3">取消</option>
				</select>
		</td>
		<td><input class="btn btn-primary" type="submit" value="確認"></td>
		<%} %>
    </tr>

<br>
<input type="hidden" name="orderNo" value="<%=sprodOrderVO.getOrderNo()%>">
<input type="hidden" name="memNo" value="<%=sprodOrderVO.getMemNo()%>">
<input type="hidden" name="orderTime" value="<%=sprodOrderVO.getOrderTime()%>">
<input type="hidden" name="tranMethod" value="<%=sprodOrderVO.getTranMethod()%>">
<input type="hidden" name="tranAdd" value="<%=sprodOrderVO.getTranAdd()%>">
<input type="hidden" name="addresseeName" value="<%=sprodOrderVO.getAddresseeName()%>">
<input type="hidden" name="addresseeMail" value="<%=sprodOrderVO.getAddresseeMail()%>">
<input type="hidden" name="orderTotal" value="<%=sprodOrderVO.getOrderTotal()%>">

<input type="hidden" name="action" value="updateMem">

</tbody>
</table>
</FORM>

<%@ include file="/front-end/Footer/footer.jsp" %>
</div>
</body>
<%@ include file="/chatRoom/chatRoom.jsp"%>
<!-- JS, Popper.js, and jQuery -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
</html>