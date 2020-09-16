<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="EMPLOYEE.model.*"%>
<%
	EmpVO empVO = (EmpVO) request.getAttribute("empVO");
%>
<!DOCTYPE html>

<html>
<head>
<meta charset="BIG5">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>員工登入</title>
<meta name="description" content="Ela Admin - HTML5 Admin Template">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Custom fonts for this template-->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/back-end/assets/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<link rel="icon" href="data:;base64,iVBORw0KGgo=">
<!-- Custom styles for this template-->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/back-end/assets/css/sb-admin-2.min.css"
	rel="stylesheet">

</head>
<style>
.container{
top:500px;
}
</style>

<body class="bg-gradient-primary">

	<div class="container">
		
		<!-- Outer Row -->
		<div class="row justify-content-center">

			<div class="col-xl-10 col-lg-12 col-md-9">

				<div class="card o-hidden border-0 shadow-lg my-5">
					<div class="card-body p-0">
						<!-- Nested Row within Card Body -->
						<div class="row">
							<div class="col-lg-6 d-none d-lg-block bg-login-image"></div>
							<div class="col-lg-6">
								<div class="p-5">
									<div class="text-center">
										<h1 class="h4 text-gray-900 mb-4">平台員工系統登入</h1>
									</div>
									<form class="user"
										action="<%=request.getContextPath()%>/back-end/emp/emp.do"
										method="post">
										<div class="form-group">
											<c:if test="${not empty iderror}">
												<div style="color: red">${iderror}</div>
											</c:if>
											<input type="text" class="form-control form-control-user"
												id="InputAcount" placeholder="Enter Employee Id"
												value="${idstr}" name='empid'>
										</div>
										<div class="form-group">
											<c:if test="${not empty pswerror}">
												<div style="color: red">${pswerror}</div>
											</c:if>
											<input type="password" class="form-control form-control-user"
												id="InputPassword" placeholder="Password" value="${pswstr}"
												name='emppsw'>
										</div>
										<div class="form-group"></div>
										<button type="submit"
											class="btn btn-primary btn-user btn-block">登入</button>
										<input type="hidden" name="action" value="login">
									</form>
									<hr>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Bootstrap core JavaScript-->
	<script
		src="<%=request.getContextPath()%>/back-end/assets/vendor/jquery/jquery.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/back-end/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script
		src="<%=request.getContextPath()%>/back-end/assets/vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script
		src="<%=request.getContextPath()%>/back-end/assets/js/sb-admin-2.min.js"></script>
	<script>
	</script>
</body>

</html>