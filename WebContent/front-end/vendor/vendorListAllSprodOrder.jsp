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

<title>�t�ӱz�n�A�H�U�O�z���q���� - listAllSprod_order.jsp</title>

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
<!-- ==================================================�������e�}�l======================================================== -->
<div class="page-header">
	<h1>�t�ӭq����
		<small><button type="button" class="btn btn-info"><a href="vendorSelectSprodOrderHome.jsp">�^����</a></button></small>	
	</h1>
</div>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table class="table table-striped">
  <thead>
    <tr>
      	<th scope="col">�q��s��</th>
		<th scope="col">�|���s��</th>
		<th scope="col">�q�榨�߮ɶ�</th>
		<th scope="col">�H�e�覡</th>
		<th scope="col">�H�e�a�}</th>
		<th scope="col">����H�m�W</th>
		<th scope="col">����H�q�l�H�c</th>
		<th scope="col">�`���B</th>
		<th scope="col">�q�檬�A</th>
		<th scope="col">�X�f</th>
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
					<p>�W�Ө��f</p>
				</c:when>					
				<c:when test="${Sprod_orderVO.tranMethod == '2'}">
					<p>�v�t</p>
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
					<p>���X�f</p>
				</c:when>
				<c:when test="${Sprod_orderVO.orderStatus == '1'}">
					<p>�w�X�f</p>
				</c:when>
				<c:when test="${Sprod_orderVO.orderStatus == '2'}">
					<p>����(�w��f)</p>
				</c:when>
				<c:when test="${Sprod_orderVO.orderStatus == '3'}">
					<p>����</p>
				</c:when>
			</c:choose>	
		</td>
	
		<td>
<!-- 		�u�����X�f���A��X�f -->
			<c:choose>
				<c:when test="${Sprod_orderVO.orderStatus == '0'}">
					<FORM METHOD="post" ACTION="sprod_order.do" style="margin-bottom: 0px">
						<input type="submit" value="�X�f">
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

<!-- ==================================================�������e����======================================================== -->
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