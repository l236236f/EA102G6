<%@page import="java.awt.Container"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.sprod_order.model.*"%>
<%@ page import="java.util.*" %>

<%
  Sprod_orderVO sprodOrderVO = (Sprod_orderVO) request.getAttribute("sprodOrderVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>



<html>
<head>

  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>  
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
  
<title>出貨確認修改</title>

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
	<h1>廠商訂單出貨確認
		<small><button type="button" class="btn btn-info"><a href="vendorSelectSprodOrderHome.jsp">回首頁</a></button></small>	
	</h1>
</div>

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
<table>
	<tr>
		<td>訂單編號:</td>
		<td><%=sprodOrderVO.getOrderNo()%></td>
	</tr>
	<tr>
		<td>會員編號:</td>
		<td><%=sprodOrderVO.getMemNo()%></td>
	</tr>
	<tr>
		<td>訂單成立時間:</td>
		<td><%=sprodOrderVO.getOrderTime()%></td>
	</tr>
	<tr>
		<td>寄送方式:</td>
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
	</tr>
	<tr>
		<td>寄送地址:</td>
		<td><%=sprodOrderVO.getTranAdd()%></td>
	</tr>
	<tr>
		<td>收件人姓名:</td>
		<td><%=sprodOrderVO.getAddresseeName()%></td>
	</tr>
	<tr>
		<td>收件人信箱:</td>
		<td><%=sprodOrderVO.getAddresseeMail()%></td>
	</tr>
	<tr>
		<td>總金額:</td>
		<td><%=sprodOrderVO.getOrderTotal()%></td>
	</tr>
	<tr>
		<td>訂單狀態:<font color=red><b>*</b></font></td>
		<td>
			<%  //用java判斷狀態
				String statusText = null;
				int status = Integer.valueOf(sprodOrderVO.getOrderStatus()); 
				switch(status){
					case 0:
						statusText = "未出貨";
						break;
					case 1:
						statusText = "已出貨";
						break;
					case 2:
				   		statusText = "完成(已到貨)";
				   		break;
					case 3:
						statusText = "取消";
						break;
				}
			%>
			<%=statusText %>
		</td>
	</tr>




</table>
<br>
<input type="hidden" name="orderStatus" value="1">
<input type="hidden" name="orderNo" value="<%=sprodOrderVO.getOrderNo()%>">
<input type="hidden" name="memNo" value="<%=sprodOrderVO.getMemNo()%>">
<input type="hidden" name="orderTime" value="<%=sprodOrderVO.getOrderTime()%>">
<input type="hidden" name="tranMethod" value="<%=sprodOrderVO.getTranMethod()%>">
<input type="hidden" name="tranAdd" value="<%=sprodOrderVO.getTranAdd()%>">
<input type="hidden" name="addresseeName" value="<%=sprodOrderVO.getAddresseeName()%>">
<input type="hidden" name="addresseeMail" value="<%=sprodOrderVO.getAddresseeMail()%>">
<input type="hidden" name="orderTotal" value="<%=sprodOrderVO.getOrderTotal()%>">

<input type="hidden" name="action" value="updateVen">

<%if(status==0){ %>
<button type="submit" class="btn btn-primary" style="width: 58px; height: 29px;"><h6>出貨</h6></button>
<%} %>
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