<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>訂單管理首頁</title>

<style>
h4 {
	color: blue;
	display: inline;
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
					<!-- ==================================================網頁內容開始======================================================== -->
					<div class="container">
						<div class="page-header">
							<h1>廠商訂單管理首頁</h1>
						</div>
					</div>
					<br>
					<br>

					<div class="container">
						<div class="row">
							<div class="col">

								<h3>資料查詢</h3>
								<br>
								<!-- 錯誤列表 -->
								<c:if test="${not empty errorMsgs}">
									<font style="color: red">請修正以下錯誤</font>
									<ul>
										<c:forEach var="message" items="${errorMsgs}">
											<li style="color: red">${message}</li>
										</c:forEach>
									</ul>
								</c:if>

								<ul>
									<li><a href="vendorListAllSprodOrder.jsp">全部訂單清單</a></li>
									<br>

									<jsp:useBean id="sprodOrderSvc" scope="page"
										class="com.sprod_order.model.Sprod_orderService" />

									<li>
										<FORM METHOD="post" ACTION="sprod_order.do">
											<b>選擇訂單編號:</b> <select size="1" name="orderNo">
												<c:forEach var="sprodOrderVO" items="${sprodOrderSvc.all}">
													<option value="${sprodOrderVO.orderNo}">${sprodOrderVO.orderNo}
												</c:forEach>
											</select> <input type="hidden" name="action"
												value="getOne_For_DisplayVen">
											<button type="submit" class="btn btn-primary"
												style="width: 58px; height: 29px;">
												<h6>送出</h6>
											</button>
										</FORM>
									</li>
									<br>

								</ul>
							</div>
							<div class="col">
								<%@ include file="vendorShopWebsocket.jsp"%>
							</div>
						</div>
					</div>


					</table>

					<!-- ==================================================網頁內容結束======================================================== -->
				</div>
				<!-- /.container-fluid -->
			</div>
			<!-- End of Main Content -->
			<!-- Footer -->
			<%@ include file="footer.jsp"%>
<%-- 			<%@ include file="/chatRoom/chatRoom.jsp"%> --%>
		</div>
		<!-- End of Content Wrapper -->
	</div>
	<!-- End of Page Wrapper -->

	<!-- Scroll to Top Button-->
	<a class="scroll-to-top rounded" href="#page-top"> <i
		class="fas fa-angle-up"></i>
	</a>

</body>

</html>