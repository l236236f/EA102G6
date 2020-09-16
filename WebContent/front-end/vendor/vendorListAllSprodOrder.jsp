<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.sprod_order.model.*"%>

<%
	Sprod_orderJNDIDAO dao = new Sprod_orderJNDIDAO();
	List<Sprod_orderVO> list = dao.getAll();
	pageContext.setAttribute("list", list);
%>


<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
<!--   bootstrap -v 4.5.2 -->
  <!-- CSS only -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">

<title>廠商您好，以下是您的訂單資料 - listAllSprod_order.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
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
	width: 800px;
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
<body id="page-top">

  <!-- Page Wrapper -->
  <div id="wrapper">

    <!-- Sidebar -->
    <%@ include file="LHSBar.jsp" %>
    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

      <!-- Main Content -->
      <div id="content">

        <!-- Topbar -->
        <%@ include file="topBar.jsp" %>

        <!-- Begin Page Content -->
        <div class="container-fluid" style="background-image: linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%);">
<!-- ==================================================網頁內容開始======================================================== -->
<div class="page-header">
	<h1>廠商訂單資料
		<small><button type="button" class="btn btn-info"><a href="vendorSelectSprodOrderHome.jsp">回首頁</a></button></small>	
	</h1>
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
		<th scope="col">出貨</th>
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
	
		<td>
<!-- 		只讓未出貨狀態能出貨 -->
			<c:choose>
				<c:when test="${Sprod_orderVO.orderStatus == '0'}">
					<FORM METHOD="post" ACTION="sprod_order.do" style="margin-bottom: 0px">
						<input type="submit" value="出貨">
						<input type="hidden" name="orderNo" value="${Sprod_orderVO.orderNo}">
						<input type="hidden" name="action" value="getOne_For_UpdateVen">
					</FORM>
				</c:when>
			</c:choose>
			
		</td>		
	</tr>
  </tbody>
</c:forEach>

</table>
<%@ include file="page2.file" %>

<!-- ==================================================網頁內容結束======================================================== -->
        </div>
        <!-- /.container-fluid -->
      </div>
      <!-- End of Main Content -->
      <!-- Footer -->
      <%@ include file="footer.jsp" %>
      <%@ include file="/chatRoom/chatRoom.jsp"%>
    </div>
    <!-- End of Content Wrapper -->
  </div>
  <!-- End of Page Wrapper -->

  <!-- Scroll to Top Button-->
  <a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
  </a>

</body>

<!-- JS, Popper.js, and jQuery -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
</html>