<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.Timestamp"%>
<%@ page import="EMPLOYEE.model.*"%>
<%@ page import="com.vendor.model.*"%>
<%
	EmpService empSvc = new EmpService();
	EmpVO empVO1 = (EmpVO) session.getAttribute("EmpVO");
	List<EmpVO> list = empSvc.getAll();
	pageContext.setAttribute("emplist", list);
	VendorService venSvc = new VendorService();
	List<VendorVO> list2 = venSvc.getAll();
	pageContext.setAttribute("venlist", list2);
%>



<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>�򥻸�ƺ޲z</title>
<!-- Custom fonts for this template -->
<link href="/EA102G6/back-end/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">
<!-- Custom styles for this template -->
<link href="/EA102G6/back-end/css/sb-admin-2.min.css" rel="stylesheet">
<!-- Custom styles for this page -->
<link
	href="/EA102G6/back-end/vendor/datatables/dataTables.bootstrap4.min.css"
	rel="stylesheet">
<link href="/EA102G6/back-end/css/back-main.css" rel="stylesheet">

</head>
<style>


.photo {
	display: flex;
	width: 350px;
	height: 350px;
	align-items: center;
}

.photo img {
	display: block;
	margin: auto;
	max-width: 340px;
	max-height: 340px;
}
</style>
<body id="page-top">

	<div class="showBox">
		<div class="showCancel">
			<input type="reset" name="cancel" value="X">
		</div>

		<p></p>

	</div>

	<div class="changeText" name="changeBox">
		<div class="showCancel">
			<input type="reset" name="cancel" value="X">
		</div>
		<div class="changeForm">
			<FORM METHOD="post" ACTION="/EA102G6/front-end/vendor/vendor.do" name="changeForm"
				enctype="multipart/form-data" id='changeForm' onsubmit="return false">
				<table>
					<h3 align="center">�ק�t�Ӹ��</h3>
					<br>
					<br>
					<tr style="visibility: hidden;">
						<td width="100px"></td>
						<td width="300px"></td>
						<td width="200px"></td>
					</tr>

					<tr style="display: none;">
						<td>�t�ӽs��</td>
						<td><input type="TEXT" name="venno" size="45"></td>
						<td class='errorMsg' name="venno"></td>
					</tr>
					<tr>
						<td>�t�ӱb��</td>
						<td><span name="venacc"></span></td>
						<td class='errorMsg' name="venacc"></td>
					</tr>
					<tr>
						<td>�t�ӱK�X</td>
						<td><input type="TEXT" name="venpw" size="45"></td>
						<td class='errorMsg' name="venpw"></td>
					</tr>
					<tr>
						<td>�t�ӦW��</td>
						<td><input type="TEXT" name="venname" size="45"></td>
						<td class='errorMsg' name='venname'></td>
					</tr>
						<tr>
						<td>�t�ӹq��</td>
						<td><input type="TEXT" name="ventel" size="45"></td>
						<td class='errorMsg' name='ventel'></td>
					</tr>
					<tr>
						<td>�Τ@�s��</td>
						<td><input type="TEXT" name="venid" size="45"></td>
						<td class='errorMsg' name='venid'></td>
					</tr>
					<tr>
						<td>�״ڱb��</td>
						<td><input type="TEXT" name="venmoney" size="45"></td>
						<td class='errorMsg' name='venmoney'></td>
					</tr>
					<tr>
						<td>�t�Ӧa�}</td>
						<td><input type="TEXT" name="venaddr" size="45"></td>
						<td class='errorMsg' name='venaddr'></td>
					</tr>
					<tr>
						<td>email</td>
						<td><input type="TEXT" name="venemail" size="45"></td>
						<td class='errorMsg' name='venemail'></td>
					</tr>
					<tr>
						<td>�t��²��</td>
						<td name='venintro'></td>
						<td></td>
					</tr>
					<tr>
						<td>���U���</td>
						<td><span name="regtime"></span></td>
						<td class='errorMsg' name="regtime"></td>
					</tr>
					<tr>
						<td>�t�Ӫ��A</td>
						<td><select name='venstatus'>
								<option value="V0">������</option>
								<option value="V1">�w����</option>
								<option value="V2">���v</option>
						</select></td>
					</tr>
				</table>
				<input type="hidden" name="action" value="updateByEmp">
				<center>
					<br> <input type="submit" value="�e�X�ק�" id='changeSubmit'>
					<br> <br>
				</center>
			</FORM>
		</div>
	</div>
	<div class="changeText" name="addBox">
		<div class="showCancel">
			<input type="reset" name="cancel" value="X">
		</div>
		<div class="changeForm"></div>
	</div>
	<!-- Page Wrapper -->
	<div id="wrapper">
		<!-- Sidebar -->
		<ul
			class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion"
			id="accordionSidebar">
			<!-- Sidebar - Brand -->
			<a
				class="sidebar-brand d-flex align-items-center justify-content-center"
				href="/EA102G6/back-end/emp/empbackindex.jsp">
				<div class="sidebar-brand-icon rotate-n-15">
					<i class="fas fa-laugh-wink"></i>
				</div>
				<div class="sidebar-brand-text mx-3">��x�޲z�t��</div>
			</a>
			<hr class="sidebar-divider my-0">
			<hr class="sidebar-divider">

			<!-- Heading -->
			<div class="sidebar-heading">���C��</div>
			<li class="nav-item"><a class="nav-link" href="/EA102G6/back-end/emp/empmanage.jsp">
					<i class="fas fa-fw fa-table"></i> <span>���u�޲z</span>
			</a></li>

			<li class="nav-item"><a class="nav-link"
				href="authoritymanage.jsp"> <i class="fas fa-fw fa-chart-area"></i>
					<span>�v���޲z</span>
			</a></li>
			<!-- Nav Item - Pages Collapse Menu -->
			<li class="nav-item"><a class="nav-link collapsed" href="#"
				data-toggle="collapse" data-target="#collapseOne"
				aria-expanded="true" aria-controls="collapseOne"> <i
					class="fas fa-fw fa-table"></i> <span>���|�f��</span>
			</a>
				<div id="collapseOne" class="collapse" aria-labelledby="headingOne"
					data-parent="#accordionSidebar">
					<div class="bg-white py-2 collapse-inner rounded">
						<h6 class="collapse-header">���|�f��</h6>
						<a class="collapse-item" href="/EA102G6/back-end/emp/memReport.jsp">�|�����|�f��</a> <a
							class="collapse-item" href="/EA102G6/back-end/emp/articleReport.jsp">�峹���|�f��</a> <a
							class="collapse-item" href="/EA102G6/back-end/emp/getherReport.jsp">�������|�f��</a> <a
							class="collapse-item" href="/EA102G6/back-end/emp/shopReport.jspl">�ӫ����|�f��</a> <a
							class="collapse-item" href="/EA102G6/back-end/emp/usedReport.jsp">�G�������|�f��</a>
					</div>
				</div></li>
			<li class="nav-item "><a class="nav-link collapsed" href="#"
				data-toggle="collapse" data-target="#collapseTwo"
				aria-expanded="true" aria-controls="collapseTwo"> <i
					class="fas fa-fw fa-table"></i> <span>�e�x�޲z</span>
			</a>
				<div id="collapseTwo" class="collapse" aria-labelledby="headingTwo"
					data-parent="#accordionSidebar">
					<div class="bg-white py-2 collapse-inner rounded">
						<h6 class="collapse-header">Q&A</h6>
						<a class="collapse-item" href="/EA102G6/back-end/emp/qamanage.jsp">Q&A</a>
						<h6 class="collapse-header">�̷s����</h6>
						<a class="collapse-item" href="/EA102G6/back-end/emp/annmanage.jsp">�������i</a>
					</div>
				</div></li>

			<li class="nav-item active"><a class="nav-link collapsed"
				href="#" data-toggle="collapse" data-target="#collapseThree"
				aria-expanded="true" aria-controls="collapseThree"> <i
					class="fas fa-fw fa-table"></i> <span>�򥻸�ƺ޲z</span>
			</a>
				<div id="collapseThree" class="collapse"
					aria-labelledby="headingThree" data-parent="#accordionSidebar">
					<div class="bg-white py-2 collapse-inner rounded">
						<h6 class="collapse-header">�|��</h6>
						<a class="collapse-item" href="/EA102G6/back-end/emp/memmanage.jsp">�|����ƭק�</a>
						<h6 class="collapse-header">�t��</h6>
						<a class="collapse-item" href="/EA102G6/back-end/emp/venmanage.jsp">�t�Ӹ�ƭק�</a>
					
					</div>
				</div></li>

			<hr class="sidebar-divider d-none d-md-block">

			<!-- Sidebar Toggler (Sidebar) -->
			<div class="text-center d-none d-md-inline">
				<button class="rounded-circle border-0" id="sidebarToggle"></button>
			</div>

		</ul>

		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">

			<!-- Main Content -->
			<div id="content">


				<nav
					class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

					<form class="form-inline">
						<button id="sidebarToggleTop"
							class="btn btn-link d-md-none rounded-circle mr-3">
							<i class="fa fa-bars"></i>
						</button>
					</form>
					<h1 class="h3 mb-2 text-gray-800">�t�Ӻ޲z</h1>

					<ul class="navbar-nav ml-auto">

						<!-- Nav Item - Search Dropdown (Visible Only XS) -->
						<li class="nav-item dropdown no-arrow d-sm-none"><a
							class="nav-link dropdown-toggle" href="#" id="searchDropdown"
							role="button" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false"> <i class="fas fa-search fa-fw"></i>
						</a> <!-- Dropdown - Messages -->
							<div
								class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in"
								aria-labelledby="searchDropdown">
								<form class="form-inline mr-auto w-100 navbar-search">
									<div class="input-group">
										<input type="text"
											class="form-control bg-light border-0 small"
											placeholder="Search for..." aria-label="Search"
											aria-describedby="basic-addon2">
										<div class="input-group-append">
											<button class="btn btn-primary" type="button">
												<i class="fas fa-search fa-sm"></i>
											</button>
										</div>
									</div>
								</form>
							</div></li>
						<!-- Nav Item - User Information -->
						<li class="nav-item dropdown no-arrow"><a
							class="nav-link dropdown-toggle" href="#" id="userDropdown"
							role="button" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false"><span style='display: none;'
								id='LoginEmpno'>${EmpVO.empno}</span> <span
								class="mr-2 d-none d-lg-inline text-gray-600 small"
								id='LoginEmp'>${EmpVO.empname}</span> <img
								class="img-profile rounded-circle"
								src="/EA102G6/back-end/emp/Img?empno=${EmpVO.empno}"> </a> <!-- Dropdown - User Information -->
							<div
								class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
								aria-labelledby="userDropdown">
								<a class="dropdown-item" href="emppofile.jsp"> <i
									class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i> �ӤH���
								</a>
								<div class="dropdown-divider"></div>
								<a class="dropdown-item" href="#" data-toggle="modal"
									data-target="#logoutModal"> <i
									class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
									�n�X
								</a>
							</div></li>

					</ul>

				</nav>
				<div class="container-fluid">
					<p class="mb-4">
						�p�����D���pô <a target="_blank" href="mailto:AA@BB.COM">�t�κ޲z��</a>.
					</p>
					<!-- DataTales Example -->
					<div class="card shadow mb-4">
						<div class="card-header py-3">
							<h6 class="m-0 font-weight-bold text-primary"
								style="display: inline;">�t�ӦC��</h6>
						</div>

						<div class="card-body">

							<div class="table-responsive">

								<table class="table table-bordered" id="dataTable" width="100%"
									cellspacing="0">
									<thead>
										<tr>
											<th style="display: none;"></th>
											<th>�t�ӱb��</th>
											<th>�t�ӱK�X</th>
											<th>�t�ӦW��</th>
											<th>�t�ӹq��</th>
											<th>�Τ@�s��</th>
											<th>email</th>
											<th>�״ڱb��</th>
											<th>�t�Ӫ��A</th>
										
											<th></th>
											<th style="display: none;"></th>
											<th style="display: none;"></th>
											<th style="display: none;"></th>
											
										</tr>
									</thead>
									<tbody>
										</tr>
										<c:forEach var="venVO" items="${venlist}">
											<tr name='${venVO.venNo}'>
												<td style="display: none;"></td>
												<td name="venacc">${venVO.venAcc}</td>
												<td name="venpw">${venVO.venPw}</td>
												<td name='venname'>${venVO.venName}</td>
												<td name='ventel'>${venVO.venTel}</td>
												<td name='venid'>${venVO.venID}</td>
												<td name="venemail">${venVO.venEmail}</td>
												<td name='venmoney'>${venVO.venMoney}</td>		
												<td name='venstatus' value='${venVO.venStatus}'></td>
												<td name='venintro' style="display: none;">${venVO.venIntro}</td>
												<td name='venaddr' style="display: none;">${venVO.venAddr}</td>
												<td name='regtime' style="display: none;">${venVO.regTime}</td>
												
												<td><input type="submit" name="change" value="�ק�">
												</td>
											</tr>
										</c:forEach>

									</tbody>
								</table>
							</div>
						</div>
					</div>

				</div>
				<!-- /.container-fluid -->

			</div>
			<!-- End of Main Content -->

			<!-- Footer -->
			<footer class="sticky-footer bg-white">
				<div class="container my-auto">
					<div class="copyright text-center my-auto">
						<span>Copyright &copy; EA102-G6 2020</span>
					</div>
				</div>
			</footer>
			<!-- End of Footer -->

		</div>
		<!-- End of Content Wrapper -->

	</div>
	<!-- End of Page Wrapper -->

	<!-- Scroll to Top Button-->
	<a class="scroll-to-top rounded" href="#page-top"> <i
		class="fas fa-angle-up"></i>
	</a>

	<!-- Logout Modal-->
	<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">�T�w�n�n�X��?</h5>
					<button class="close" type="button" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">��</span>
					</button>
				</div>
				<div class="modal-body">����n�X�ﶵ�T�{�n�X</div>
				<div class="modal-footer">
					<FORM METHOD="post" ACTION="emp.do" name="logout">
						<button class="btn btn-secondary" type="button"
							data-dismiss="modal">����</button>
						<input type="hidden" name="action" value="logout" style=""
							display=none;"> <input type="submit"
							class="btn btn-primary" value="�n�X" id='logout'>
					</FORM>
				</div>
			</div>
		</div>
	</div>

	<!-- Bootstrap core JavaScript-->
	<script src="/EA102G6/back-end/vendor/jquery/jquery.min.js"></script>
	<script
		src="/EA102G6/back-end/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script
		src="/EA102G6/back-end/vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script src="/EA102G6/back-end/js/sb-admin-2.min.js"></script>

	<!-- Page level plugins -->
	<script
		src="/EA102G6/back-end/vendor/datatables/jquery.dataTables.min.js"></script>
	<script
		src="/EA102G6/back-end/vendor/datatables/dataTables.bootstrap4.min.js"></script>

	<!-- Page level custom scripts -->
	<script src="/EA102G6/back-end/js/demo/datatables-demo.js"></script>
	<script src="https://kit.fontawesome.com/dd027c1f63.js"
		crossorigin="anonymous"></script>
	<script src="/EA102G6/back-end/js/demo/datatables-demo.js"></script>



</body>
<%
	Timestamp hiredate = null;
	hiredate = new Timestamp(System.currentTimeMillis());
%>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script
	src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>
<script src="/EA102G6/back-end/js/back-Main.js"></script>
<script src="/EA102G6/back-end/js/venm-backMain.js"></script>
	<%@ include file="/chatRoom/chatRoom.jsp"%>
</html>
