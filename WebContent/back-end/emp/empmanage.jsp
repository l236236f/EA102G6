<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.Timestamp"%>
<%@ page import="EMPLOYEE.model.*"%>

<%
	EmpService empSvc = new EmpService();
	List<EmpVO> list = empSvc.getAll();
	pageContext.setAttribute("list", list);
	EmpVO empVO1 = (EmpVO) session.getAttribute("EmpVO");
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
<title>員工管理</title>
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
			<FORM METHOD="post" ACTION="emp.do" name="changeForm"
				enctype="multipart/form-data" id='changeForm'
				onsubmit="return false">
				<table>
					<h3 align="center">修改員工資料</h3>
					<br>
					<tr style="visibility: hidden;">
						<td width="200px"></td>
						<td width="300px"></td>
						<td width="200px"></td>
					</tr>
					<tr style="display: none;">
						<td>員工編號</td>
						<td><input type="TEXT" name="empno" size="45"
							readonly="readonly"></td>
						<td class='errorMsg' name="inputErrorH1"></td>
					</tr>
					<tr>
						<td>員工姓名:</td>
						<td><input type="TEXT" name="empname" size="45"></td>
						<td class='errorMsg' name="inputErrorV1"></td>
					</tr>
					<tr>
						<td>員工電話:</td>
						<td><input type="TEXT" name="emptel" size="45"></td>
						<td class='errorMsg' name="inputErrorV2"></td>
					</tr>
					<tr>
						<td>email:</td>
						<td><input type="TEXT" name="empemail" size="45"></td>
						<td class='errorMsg' name="inputErrorV3"></td>
					</tr>
					<tr>
						<td>員工ID:</td>
						<td><input type="TEXT" name="empid" size="45"></td>
						<td class='errorMsg' name="inputErrorV4"></td>
					</tr>
					<tr>
						<td>員工密碼:</td>
						<td><input type="TEXT" name="emppsw" size="45"></td>
						<td class='errorMsg' name="inputErrorV5"></td>
					</tr>
					<tr>
						<td>員工照片:</td>
						<td><div class='photo'></div> <input type="file"
							name="photo" size="45"></td>
					</tr>
					<tr>
						<td>職位:</td>
						<td><input type="TEXT" name="empposition"></td>
						<td class='errorMsg' name="inputErrorV6" size="45"></td>

					</tr>
					<tr>
						<td>雇用日期:</td>
						<td><input name="emphiredate" type="text" id="ChangeDate"
							autocomplete="off"></td>
					</tr>
					<tr style="display: none;">
						<td>修改人</td>
						<td><input name="empchangeman" type="text"></td>
					</tr>
					<tr>
						<td>狀態:</td>
						<td><select name="empstatus">
								<option value="E1" selected="selected">在職</option>
								<option value="E0">停權</option>
						</select></td>

					</tr>
					<tr>
						<td>備註:</td>
						<td><textarea name="empnotes" cols="50" rows="5"></textarea></td>
					</tr>

				</table>
				<input type="hidden" name="action" value="update">
				<center>
					<input type="submit" value="送出新增" id='changeSubmit'>
				</center>
			</FORM>
		</div>
	</div>
	<div class="changeText" name="addBox">
		<div class="showCancel">
			<input type="reset" name="cancel" value="X">
		</div>
		<div class="changeForm">
		<button id = 'fakeButton'>神奇小按鈕</button>
			<FORM METHOD="post" ACTION="emp.do" name="addForm"
				enctype="multipart/form-data" id='addForm' onsubmit="return false">
				<table>
					<h3 align="center">新增員工資料</h3>
					<br>
					<tr style="visibility: hidden;">
						<td width="200px"></td>
						<td width="300px"></td>
						<td width="200px"></td>
					</tr>
					<tr>
						<td>員工姓名:</td>
						<td><input type="TEXT" name="empname" size="45"></td>
						<td class='errorMsg' name="inputErrorV1"></td>
					</tr>
					<tr>
						<td>員工電話:</td>
						<td><input type="TEXT" name="emptel" size="45"></td>
						<td class='errorMsg' name="inputErrorV2"></td>
					</tr>
					<tr>
						<td>email:</td>
						<td><input type="TEXT" name="empemail" size="45"></td>
						<td class='errorMsg' name="inputErrorV3"></td>
					</tr>
					<tr>
						<td>員工ID:</td>
						<td><input type="TEXT" name="empid" size="45"></td>
						<td class='errorMsg' name="inputErrorV4"></td>
					</tr>
					<tr>
						<td>員工照片:</td>
						<td><div class='photo'></div> <input type="file"
							name="photo" size="45"></td>
					</tr>
					<tr>
						<td>職位:</td>
						<td><input type="TEXT" name="empposition" size="45"></td>
						<td class='errorMsg' name="inputErrorV6"></td>

					</tr>
					<tr>
						<td>雇用日期:</td>
						<td><input name="emphiredate" type="text" id="AddDate"
							autocomplete="off"></td>
					</tr>
					<tr style="display: none;">
						<td>修改人</td>
						<td><input name="empchangeman" type="text"></td>
					</tr>
					<tr>
						<td>狀態:</td>
						<td><select name="empstatus">
								<option value="E1" selected="selected">在職</option>
								<option value="E0">停權</option>
						</select></td>

					</tr>
					<tr>
						<td>備註:</td>
						<td><textarea name="empnotes" cols="50" rows="5"></textarea></td>
					</tr>

				</table>
				<input type="hidden" name="action" value="insert">
				<center>
					<input type="submit" value="送出新增" id='addSubmit'>
				</center>
			</FORM>
		</div>
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
				href="empbackindex.jsp">
				<div class="sidebar-brand-icon rotate-n-15">
					<i class="fas fa-laugh-wink"></i>
				</div>
				<div class="sidebar-brand-text mx-3">後台管理系統</div>
			</a>
			<hr class="sidebar-divider my-0">
			<hr class="sidebar-divider">

			<!-- Heading -->
			<div class="sidebar-heading">選單列表</div>
			<li class="nav-item active"><a class="nav-link"
				href="empmanage.jsp"> <i class="fas fa-fw fa-table"></i> <span>員工管理</span></a>
			</li>

			<li class="nav-item"><a class="nav-link"
				href="authoritymanage.jsp"> <i class="fas fa-fw fa-chart-area"></i>
					<span>權限管理</span>
			</a></li>
			<!-- Nav Item - Pages Collapse Menu -->
			<li class="nav-item"><a class="nav-link collapsed" href="#"
				data-toggle="collapse" data-target="#collapseOne"
				aria-expanded="true" aria-controls="collapseOne"> <i
					class="fas fa-fw fa-table"></i> <span>檢舉審核</span>
			</a>
				<div id="collapseOne" class="collapse" aria-labelledby="headingOne"
					data-parent="#accordionSidebar">
					<div class="bg-white py-2 collapse-inner rounded">
						<h6 class="collapse-header">檢舉審核</h6>
						<a class="collapse-item" href="memReport.jsp">會員檢舉審核</a> <a
							class="collapse-item" href="articleReport.jsp">文章檢舉審核</a> <a
							class="collapse-item" href="getherReport.jsp">揪團檢舉審核</a> <a
							class="collapse-item" href="shopReport.jspl">商城檢舉審核</a> <a
							class="collapse-item" href="usedReport.jsp">二手拍賣檢舉審核</a>
					</div>
				</div></li>
			<li class="nav-item "><a class="nav-link collapsed" href="#"
				data-toggle="collapse" data-target="#collapseTwo"
				aria-expanded="true" aria-controls="collapseTwo"> <i
					class="fas fa-fw fa-table"></i> <span>前台管理</span>
			</a>
				<div id="collapseTwo" class="collapse" aria-labelledby="headingTwo"
					data-parent="#accordionSidebar">
					<div class="bg-white py-2 collapse-inner rounded">
						<h6 class="collapse-header">Q&A</h6>
						<a class="collapse-item" href="qamanage.jsp">Q&A</a>
						<h6 class="collapse-header">最新消息</h6>
						<a class="collapse-item" href="annmanage.jsp">網站公告</a>
					</div>
				</div></li>

			<li class="nav-item"><a class="nav-link collapsed" href="#"
				data-toggle="collapse" data-target="#collapseThree"
				aria-expanded="true" aria-controls="collapseThree"> <i
					class="fas fa-fw fa-table"></i> <span>基本資料管理</span>
			</a>
				<div id="collapseThree" class="collapse"
					aria-labelledby="headingThree" data-parent="#accordionSidebar">
					<div class="bg-white py-2 collapse-inner rounded">
						<h6 class="collapse-header">會員</h6>
						<a class="collapse-item" href="memmanage.jsp">會員資料修改</a>
						<h6 class="collapse-header">廠商</h6>
						<a class="collapse-item" href="venmanage.jsp">廠商資料修改</a>
						
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
					<h1 class="h3 mb-2 text-gray-800">員工管理</h1>

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
									class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i> 個人資料
								</a>
								<div class="dropdown-divider"></div>
								<a class="dropdown-item" href="#" data-toggle="modal"
									data-target="#logoutModal"> <i
									class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
									登出
								</a>
							</div></li>

					</ul>

				</nav>
				<!-- End of Topbar -->

				<!-- Begin Page Content -->
				<div class="container-fluid">

					<!-- Page Heading -->

					<p class="mb-4">
						如有問題請聯繫 <a target="_blank" href="mailto:AA@BB.COM">系統管理員</a>.
					</p>

					<!-- DataTales Example -->


					<div class="card shadow mb-4">
						<div class="card-header py-3">
							<h6 class="m-0 font-weight-bold text-primary"
								style="display: inline;">員工列表</h6>
							<div name="add" class="addButton">新增員工</div>
						</div>

						<div class="card-body">

							<div class="table-responsive">

								<table class="table table-bordered" id="dataTable" width="100%"
									cellspacing="0">
									<thead>
										<tr>
											<th style="display: none;"></th>
											<th>姓名</th>
											<th>電話</th>
											<th>E-mail</th>
											<th>帳號</th>
											<th>密碼</th>
											<th>照片</th>
											<th>職位</th>
											<th>雇用日期</th>
											<th>修改日期</th>
											<th>修改人</th>
											<th>狀態</th>
											<th>備註</th>
											<td></td>
										</tr>
									</thead>
									<tbody>
										</tr>
										<c:forEach var="empVO" items="${list}">
											<tr name='${empVO.empno}'>
												<td name='empno' style="display: none;">${empVO.empno}</td>
												<td name='empname'>${empVO.empname}</td>
												<td name='emptel'>${empVO.emptel}</td>
												<td name='empemail'>${empVO.empemail}</td>
												<td name='empid'>${empVO.empid}</td>
												<td name='emppsw'>${empVO.emppsw}</td>
												<td name='empphoto'><div class='imgButton'
														name='${empVO.empno}'>
														<i class="fas fa-file-image"></i>
													</div></td>
												<td name='empposition'>${empVO.empposition}</td>
												<td name='emphiredate'><fmt:formatDate
														value="${empVO.emphiredate}" pattern="yyyy-MM-dd" /></td>
												<td name='empchangedate'><fmt:formatDate
														value="${empVO.empchangedate}" pattern="yyyy-MM-dd  HH:mm" /></td>
												<td name='empchangeman'><c:forEach var="empVO1"
														items="${list}">
														<c:if test="${empVO.empchangeman==empVO1.empno}">
	                  							  ${empVO1.empname}
                  								 </c:if>
													</c:forEach></td>
												<td name='empstatus' value='${empVO.empstatus}'></td>
												<td><div class='notesButton' name='${empVO.empno}'
														value='${empVO.empnotes}'>
														<i class="far fa-file-alt"></i>
													</div></td>
												<td><input type="submit" name="change" value="修改">
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
					<h5 class="modal-title" id="exampleModalLabel">確定要登出嗎?</h5>
					<button class="close" type="button" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
				</div>
				<div class="modal-body">選取登出選項確認登出</div>
				<div class="modal-footer">
					<FORM METHOD="post" ACTION="emp.do" name="logout">
						<button class="btn btn-secondary" type="button"
							data-dismiss="modal">取消</button>
						<input type="hidden" name="action" value="logout" style=""
							display=none;"> <input type="submit"
							class="btn btn-primary" value="登出" id='logout'>
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
<script src="/EA102G6/back-end/js/empMain.js"></script>
	<%@ include file="/chatRoom/chatRoom.jsp"%>
</html>

