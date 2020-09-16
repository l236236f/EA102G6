<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.rep_article.model.*"%>
<%@ page import="com.rep_response.model.*"%>

<%
	RepArticleService raSvc = new RepArticleService();
	List<RepArticleVO> list = raSvc.getAll();
	pageContext.setAttribute("list", list);
	
	RepResponseService rrsSvc = new RepResponseService();
	List<RepResponseVO> list2 = rrsSvc.getAll();
	pageContext.setAttribute("list2", list2);
%>
<jsp:useBean id="articleSvc" scope="page" class="com.article.model.ArticleService" />
<jsp:useBean id="arSvc" scope="page" class="com.art_response.model.ArtResponseService" />
<jsp:useBean id="memberSvc" scope="page" class="com.mem.model.MemService" />

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" 	 content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" 	 content="">
<title>���|�޲z - �峹</title>

<!-- Custom fonts for this template -->
<link href="<%=request.getContextPath()%>/back-end/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="<%=request.getContextPath()%>/back-end/css/sb-admin-2.min.css" rel="stylesheet">
<!-- Custom styles for this page -->
<link href="<%=request.getContextPath()%>/back-end/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/back-end/css/back-main.css" rel="stylesheet">

<style>
th {
	font-weight: bold;
	font-size: 20px;
}
</style>
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
	</div>
	<!-- Page Wrapper -->
	<div id="wrapper">
		
		<%@ include file="sidebar.file" %>
		
		<!-- Begin Page Content -->
        <div class="container-fluid">

		<!-- Page Heading -->
        <h1 class="h3 mb-2 text-gray-800" style="margin-top: 35px; font-weight:bold;">�峹���|�f��</h1>

		<!-- DataTales Example -->
        <div class="card shadow mb-4">
        <div class="content" style="margin-top:20px;">
				<div class="animated fadeIn" style="height:700px;">
					<ul class="nav nav-tabs" id="myTab" role="tablist">
						<li class="nav-item">
							<a class="nav-link active" id="home-tab"
							   data-toggle="tab" href="#rep_article" role="tab"
							   aria-controls="home" aria-selected="true">
							�f�֤峹���|</a></li>
						<li class="nav-item">
							<a class="nav-link" id="contact-tab"
							   data-toggle="tab" href="#rep_response" role="tab"
							   aria-controls="contact" aria-selected="false">
							�f�֦^�����|</a></li>
					</ul>

					<div class="tab-content" id="myTabContent">

						<div class="tab-pane fade show active" id="rep_article"
							 role="tabpanel" aria-labelledby="home-tab">
							<div class="row" style="margin-top: 20px;">
								<div class="col-md-12">
									<div class="card">
										<div class="card-body">
										<table id="bootstrap-data-table" class="table table-striped table-bordered">
                                    		<thead>
                                       			<tr>
								                    <th>���|�s��</th>
								                    <th>�峹���D</th>
								                    <th>�o���</th>
								                    <th>�o�G�ɶ�</th>
<!-- 								                    <th>���|��</th> -->
								                    <th>���|��]</th>
								                    <th>���|���A</th>
								                    <th>���|�B�z</th>
							                    </tr>
                                    		</thead>
		                                    <tbody>
							                  	<c:forEach var="reparticleVO" items="${list}">
							                  	<c:if test="${reparticleVO.repstatus.equals('R0')}">
							                    <tr>
							                      <td><h6>${reparticleVO.repno}</h6></td>
							                      <td>
							                      	<c:forEach var="articleVO" items="${articleSvc.all}">
							                    		<c:if test="${reparticleVO.artno==articleVO.artno}">
								                    		<h6>${articleVO.arttitle}</h6>
							                    		</c:if>
							                		</c:forEach>
							                      </td>
							                      <td>
							                      	<c:forEach var="articleVO" items="${articleSvc.all}">
							                    		<c:if test="${reparticleVO.artno==articleVO.artno}">
									                      	<c:forEach var="memVO" items="${memberSvc.all}">
									                    		<c:if test="${articleVO.memno==memVO.memNo}">
										                    		<h6>${memVO.memName}</h6>
									                    		</c:if>
									                		</c:forEach>
							                			</c:if>
							                		</c:forEach>
							                	  </td>
							                      <td>
							                      	<fmt:formatDate value="${reparticleVO.reptime}" pattern="yyyy-MM-dd"/><br>
										    		<fmt:formatDate value="${reparticleVO.reptime}" pattern="HH:mm:ss"/>
							                      </td>
<!-- 							                      <td> -->
<%-- 							                      	<c:forEach var="memVO" items="${memberSvc.all}"> --%>
<%-- 							                    		<c:if test="${reparticleVO.memno==memVO.memNo}"> --%>
<%-- 								                    		<h6>${memVO.memName}</h6> --%>
<%-- 							                    		</c:if> --%>
<%-- 							                		</c:forEach> --%>
<!-- 												  </td> -->
							                      <td><h6>${reparticleVO.repreason}</h6></td>
							                      <td>
							                      	<c:if test="${reparticleVO.repstatus=='R0'}"><h6>���B�z</h6></c:if>
							                      	<c:if test="${reparticleVO.repstatus=='R1'}"><h6>���|�q�L</h6></c:if>
							                      	<c:if test="${reparticleVO.repstatus=='R2'}"><h6>���|���q�L</h6></c:if>
							                      </td>
							                      <td>
							                      	<button type="button" class="btn btn-primary"
															data-toggle="modal"
															data-target="#${reparticleVO.repno}"
															style="background-color: #E7AB3C; border: #E7AB3C;">�f�����|</button>
													<!-- Modal -->
													<div class="modal fade" id="${reparticleVO.repno}"
														 tabindex="-1" role="dialog"
														 aria-labelledby="exampleModalCenterTitle"
														 aria-hidden="true">
														<div class="modal-dialog modal-dialog-centered"
															 role="document">
															<div class="modal-content">
																<div class="modal-header">
																	<h5 class="modal-title"	id="exampleModalCenterTitle"
																		style="font-weight:bold; font-size:26px;">�f�ֵ��G</h5>
																	<button type="button" class="close"
																			data-dismiss="modal" aria-label="Close">
																		<span aria-hidden="true">&times;</span>
																	</button>
																</div>
																<div class="modal-body" style="text-align: center;">
																	<button type="button" style="margin-right:20px; font-size:14px;"
																			class="btn btn-primary rep_submit" value="addEval"
																			data-dismiss="modal">���|���q�L</button>
																		<input type="hidden" class="action" value="rep_notapproved"> 
																		<input type="hidden" class="repno"  value="${reparticleVO.repno}">
																	<button type="button" style="background-color: red; border:red; 
																								 margin-left:20px; font-size:14px;"
																			class="btn btn-secondary rep_submit"
																			data-dismiss="modal">���|�q�L</button>
																		<input type="hidden" class="action" value="rep_approved">
																</div>
															</div>
														</div>
												  	</div>
							                      </td>
							                    </tr>
							                    </c:if>
							                    </c:forEach>
							            	</tbody>
                                		</table>
										</div>
									</div>
								</div>
							</div>
						</div>

						<div class="tab-pane fade" id="rep_response" role="tabpanel"
							 aria-labelledby="profile-tab">
							<div class="row" style="margin-top: 20px;">
								<div class="col-12">
                        			<div class="card">
                            			<div class="card-body">
                                		<table id="bootstrap-data-table" class="table table-striped table-bordered">
                                    		<thead>
                                       			<tr>
								                    <th>���|�s��</th>
								                    <th>�峹�^��</th>
								                    <th>�^�Ъ�</th>
								                    <th>�o�G�ɶ�</th>
<!-- 								                    <th>���|��</th> -->
								                    <th>���|��]</th>
								                    <th>���|���A</th>
								                    <th>���|�B�z</th>
							                    </tr>
                                    		</thead>
		                                    <tbody>
							                  	<c:forEach var="represponseVO" items="${list2}">
		                                    	<c:if test="${represponseVO.repstatus.equals('R0')}">
							                    <tr>
							                      <td><h6>${represponseVO.repno}</h6></td>
							                      <td>
								                  	<c:forEach var="artresponseVO" items="${arSvc.all}">
								                  		<c:if test="${represponseVO.resno==artresponseVO.resno}">
									              			<h6>${artresponseVO.rescontent}</h6>
								                  		</c:if>
								                  	</c:forEach>
							                      </td>
							                      <td>
							                      	<c:forEach var="artresponseVO" items="${arSvc.all}">
							                    		<c:if test="${represponseVO.resno==artresponseVO.resno}">
									                      	<c:forEach var="memVO" items="${memberSvc.all}">
									                    		<c:if test="${artresponseVO.memno==memVO.memNo}">
										                    		<h6>${memVO.memName}</h6>
									                    		</c:if>
									                		</c:forEach>
							                			</c:if>
							                		</c:forEach>
							                	  </td>
							                      <td>
							                      	<fmt:formatDate value="${represponseVO.reptime}" pattern="yyyy-MM-dd"/><br>
										    		<fmt:formatDate value="${represponseVO.reptime}" pattern="HH:mm:ss"/>
							                      </td>
<!-- 							                      <td> -->
<%-- 							                      	<c:forEach var="memVO" items="${memberSvc.all}"> --%>
<%-- 							                    		<c:if test="${represponseVO.memno==memVO.memNo}"> --%>
<%-- 								                    		<h6>${memVO.memName}</h6> --%>
<%-- 							                    		</c:if> --%>
<%-- 							                		</c:forEach> --%>
<!-- 												  </td> -->
							                      <td><h6>${represponseVO.repreason}</h6></td>
							                      <td>
							                      	<c:if test="${represponseVO.repstatus=='R0'}"><h6>���B�z</h6></c:if>
							                      	<c:if test="${represponseVO.repstatus=='R1'}"><h6>���|�q�L</h6></c:if>
							                      	<c:if test="${represponseVO.repstatus=='R2'}"><h6>���|���q�L</h6></c:if>
							                      </td>
							                      <td>
							                      	<button type="button" class="btn btn-primary"
															data-toggle="modal"
															data-target="#${represponseVO.repno}"
															style="background-color: #E7AB3C; border: #E7AB3C;">�f�����|</button>
													<!-- Modal -->
													<div class="modal fade" id="${represponseVO.repno}"
														 tabindex="-1" role="dialog"
														 aria-labelledby="exampleModalCenterTitle"
														 aria-hidden="true">
														<div class="modal-dialog modal-dialog-centered"
															 role="document">
															<div class="modal-content">
																<div class="modal-header">
																	<h5 class="modal-title"	id="exampleModalCenterTitle"
																		style="font-weight:bold; font-size:26px;">�f�ֵ��G</h5>
																	<button type="button" class="close"
																			data-dismiss="modal" aria-label="Close">
																		<span aria-hidden="true">&times;</span>
																	</button>
																</div>
																<div class="modal-body" style="text-align: center;">
																	<button type="button" style="margin-right:20px; font-size:14px;"
																			class="btn btn-primary rep_res_submit" value="addEval"
																			data-dismiss="modal">���|���q�L</button>
																		<input type="hidden" class="action" value="rep_notapproved"> 
																		<input type="hidden" class="repno"  value="${represponseVO.repno}">
																	<button type="button" style="background-color: red; border:red; 
																								 margin-left:20px; font-size:14px;"
																			class="btn btn-secondary rep_res_submit"
																			data-dismiss="modal">���|�q�L</button>
																		<input type="hidden" class="action" value="rep_approved">
																</div>
															</div>
														</div>
												  	</div>
							                      </td>
							                    </tr>
							                    </c:if>
							                    </c:forEach>
							            	</tbody>
                                		</table>
                            			</div>
                       				</div>
		                    	</div>
		                    </div>
		                </div>

					</div>
				</div>
			</div>
        </div>

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
	<a class="scroll-to-top rounded" href="#page-top"> 
		<i	class="fas fa-angle-up"></i>
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
					<input type="hidden" name="action" value="logout" style=""display=none;">
					<input type="submit" class="btn btn-primary" value="�n�X" id='logout'>
				</FORM>
				</div>
			</div>
		</div>
	</div>

	<!-- Bootstrap core JavaScript-->
	<script src="<%=request.getContextPath()%>/back-end/vendor/jquery/jquery.min.js"></script>
	<script	src="<%=request.getContextPath()%>/back-end/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script	src="<%=request.getContextPath()%>/back-end/vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script src="<%=request.getContextPath()%>/back-end/js/sb-admin-2.min.js"></script>

	<!-- Page level plugins -->
	<script	src="<%=request.getContextPath()%>/back-end/vendor/datatables/jquery.dataTables.min.js"></script>
	<script	src="<%=request.getContextPath()%>/back-end/vendor/datatables/dataTables.bootstrap4.min.js"></script>

	<!-- Page level custom scripts -->
	<script src="<%=request.getContextPath()%>/back-end/js/demo/datatables-demo.js"></script>
	<script src="https://kit.fontawesome.com/dd027c1f63.js"	crossorigin="anonymous"></script>
	<script src="<%=request.getContextPath()%>/back-end/js/demo/datatables-demo.js"></script>
	<script src="<%=request.getContextPath()%>/back-end/js/back-Main.js"></script>

<script>
	jQuery(document).ready(function($) {
		$(".rep_submit").click(function(){
			var action = $(this).next().val();
			var repno = $(this).siblings(".repno").val();
			
			$.ajax({
			    url: "<%= request.getContextPath()%>/back-end/report/rep_article.do",
			    type: "POST",
			     data:{
			     "action" : action,
			     "repno" : repno
			   	}
			 });
		});
	});
	
	jQuery(document).ready(function($) {
		$(".rep_res_submit").click(function(){
			var action = $(this).next().val();
			var repno = $(this).siblings(".repno").val();
			
			$.ajax({
			    url: "<%= request.getContextPath()%>/back-end/report/rep_response.do",
			    type: "POST",
			     data:{
			     "action" : action,
			     "repno" : repno
			   	}
			 });
		});
	});
</script>
</body>
</html>
