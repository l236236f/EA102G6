<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.sprod_order.model.*"%>

<%
	Sprod_orderVO sprodOrderVO = (Sprod_orderVO) request.getAttribute("sprodOrderVO");
%>
<%=sprodOrderVO == null%>--${sprodOrderVO==null}
<br>
--${sprodOrderVO.orderNo}--


<html>
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>�ӫ~��ƭק� - update_shop_product_input.jsp</title>

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
		<%@ include file="LHSBar.jsp"%>
		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">

			<!-- Main Content -->
			<div id="content">

				<!-- Topbar -->
				<%@ include file="topBar.jsp"%>

				<!-- Begin Page Content -->
				<div class="container-fluid"
					style="background-image: linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%);">
					<!-- ==================================================�������e�}�l======================================================== -->
					<table id="table-1">
						<tr>
							<td>
								<h3>�ӫ~��s - update_shop_product_input.jsp</h3>
								<h4>
									<a href="vendorSelectProductHome.jsp">�^����</a>
								</h4>
							</td>
						</tr>
					</table>

					<h3>��ƭק�:</h3>

					<%-- ���~��C --%>
					<c:if test="${not empty errorMsgs}">
						<font style="color: red">�Эץ��H�U���~:</font>
						<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<li style="color: red">${message}</li>
							</c:forEach>
						</ul>
					</c:if>

					<FORM METHOD="post" ACTION="sprod_order.do" name="form1">
						<table>
							<tr>
								<td>��f�覡:</td>
								<td>
									<%
										String method = null;
										int a = new Integer(sprodOrderVO.getTranMethod());
										if (a == 1) {
											method = "�W�Ө��f";
										} else {
											method = "�v�t";
										}
									%> <%=method%>
								</td>
							</tr>
							<tr>
								<td>�H�e�a�}:</td>
								<td><input type="TEXT" name="tranAdd" size="45"
									value="<%=(sprodOrderVO == null) ? "�п�J�A���H�e�a�}" : sprodOrderVO.getTranAdd()%>" /></td>
							</tr>
							<tr>
								<td>����H�m�W:</td>
								<td><input type="TEXT" name="addresseeMail" size="45"
									value="<%=(sprodOrderVO == null) ? "�M��J����H�m�W" : sprodOrderVO.getAddresseeMail()%>" /></td>
							</tr>
							<tr>
								<td>�q�l�H�c:</td>
								<td><input type="EMAIL" name="price" size="45"
									value="<%=(sprodOrderVO == null) ? "�п�J����H�q�l�H�c" : sprodOrderVO.getAddresseeMail()%>" /></td>
							</tr>
							<tr>
								<td>�X�p:</td>
								<td><input type="text" name="orderTotal" value="10000"
									readonly></td>
							</tr>
							<tr>
								<td>�q�檬�A:</td>
								<td><select size="1" name="orderStatus">
										<option value="0">���X�f</option>
										<option value="1">�w�X�f</option>
										<option value="2">����(�w��f)</option>
										<option value="3">����</option>
								</select></td>
							</tr>
							<tr>
								<td>�A���u�f�M��:</td>
								<td></td>
							</tr>
						</table>
						<br> <input type="hidden" name="action" value="insert">
						<input type="hidden" name="memNo" value="M001"> <input
							type="submit" value="��窱�A">

						<!-- ==================================================�������e����======================================================== -->
				</div>
				<!-- /.container-fluid -->
			</div>
			<!-- End of Main Content -->
			<!-- Footer -->
			<%@ include file="footer.jsp"%>
			<%@ include file="/chatRoom/chatRoom.jsp"%>
		</div>
		<!-- End of Content Wrapper -->
	</div>
	<!-- End of Page Wrapper -->

	<!-- Scroll to Top Button-->
	<a class="scroll-to-top rounded" href="#page-top"> <i
		class="fas fa-angle-up"></i>
	</a>

</body>