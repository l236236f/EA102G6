<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.sprod_order.model.*"%>


<html>
<%@ include file="/front-end/Header/header.jsp" %>
<%
	Sprod_orderJNDIDAO dao = new Sprod_orderJNDIDAO();
	List<Sprod_orderVO> list = dao.findByMemNo(memVO.getMemNo());
	pageContext.setAttribute("list", list);
%>

<html>
<head>
<title>會員訂單資料</title>
<!-- bootstrap4.5.2 -->
<!-- CSS only -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">



</head>
<body>
<div style="background-image: linear-gradient(to top, #fff1eb 0%, #ace0f9 100%);">
<div class="page-header">
	<h1>您好，以下是您的訂單資料</h1>
</div>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table class="table table-striped">
  <thead>
    <tr>
      <th scope="col">訂單編號</th>
      <th scope="col">會員編號</th>
      <th scope="col">訂單成立時間</th>
      <th scope="col">寄送方式</th>
      <th scope="col">寄送地址</th>
      <th scope="col">收件人姓名</th>
      <th scope="col">收件人電子信箱</th>
      <th scope="col">總金額</th>
      <th scope="col">訂單狀態</th>
      <th scope="col">查看訂單</th>
    </tr>
  </thead>
  	<%@ include file="page1.file" %> 
	<c:forEach var="Sprod_orderVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
	  <tbody>
	    <tr>
	      <th scope="row">${Sprod_orderVO.orderNo}</th>
	      <td>${Sprod_orderVO.memNo}</td>
				<td>${Sprod_orderVO.orderTime}</td>
				<td>
					<c:choose>
						<c:when test="${Sprod_orderVO.tranMethod == '1'}">
							<p>超商取貨</p>
						</c:when>
						<c:when test="${Sprod_orderVO.tranMethod == '2'}">
							<p>宅配</p>
						</c:when>
					</c:choose>	
				</td>
				<td>${Sprod_orderVO.tranAdd}</td>
				<td>${Sprod_orderVO.addresseeName}</td>
				<td>${Sprod_orderVO.addresseeMail}</td>
				<td>${Sprod_orderVO.orderTotal}</td>
				<td>
					<c:choose>
						<c:when test="${Sprod_orderVO.orderStatus == '0'}">
							<p>未出貨</p>
						</c:when>
						<c:when test="${Sprod_orderVO.orderStatus == '1'}">
							<p>已出貨</p>
						</c:when>
						<c:when test="${Sprod_orderVO.orderStatus == '2'}">
							<p>完成(已到貨)</p>
						</c:when>
						<c:when test="${Sprod_orderVO.orderStatus == '3'}">
							<p>取消</p>
						</c:when>
					</c:choose>	
				</td>

<!-- 如果狀態為非已出貨，則不顯示 -->
				<td>												
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/shop/sprod_order.do" style="margin-bottom: 0px">
						<button type="submit" class="btn btn-primary"><font color=yellow>查看訂單</font></button>
						<input type="hidden" name="orderNo" value="${Sprod_orderVO.orderNo}">
						<input type="hidden" name="action" value="getOne_For_UpdateMem">
					</FORM>													
				</td>
	    </tr>	    
	  </tbody>
  </c:forEach>
</table>
<%@ include file="page2.file" %> 
<%@ include file="/front-end/Footer/footer.jsp" %>
</div>
</body>
<%@ include file="/chatRoom/chatRoom.jsp"%>
<!-- JS, Popper.js, and jQuery -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
</html>