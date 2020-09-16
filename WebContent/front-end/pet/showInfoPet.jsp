<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/front-end/Header/header.jsp"%>
<%@ page import="java.util.*"%>
<%@ page import="com.pet.model.*"%>
<%@ page import="com.petDaily.model.*"%>
<%@ page import="com.dailyPhoto.model.*"%>

<%
	PetService petSvc = new PetService();
	PetVO showPetVO = petSvc.getOnePet(request.getParameter("showPetNo"));
	pageContext.setAttribute("showPetVO", showPetVO);
%>
<%
	PetDailyService petDailySvc = new PetDailyService();
	List<PetDailyVO> listPD = petDailySvc.findByPetNo(showPetVO.getPetNo());
	pageContext.setAttribute("listPD", listPD);
%>
<%
	DailyPhotoService dailyPhotoSvc = new DailyPhotoService();
	List<DailyPhotoVO> listDPH = dailyPhotoSvc.getAll();
	pageContext.setAttribute("listDPH", listDPH);
%>

<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
<style>
.petHeadPhoto2 {
	width: 55px;
	height: 55px;
	border-radius: 50%;
	background-image: url(<%=request.getContextPath()%>/front-end/ShowPhotos?type=pet&photo_no=${showPetVO.petNo
}
);
background-repeat


:no-repeat


;
background-size


:

 

cover


;
}
</style>
</head>
<body>

	<section class="blog-section spad">
		<div class="container">
			<div class="row">
				<%@ include file="/front-end/mem/LHSBar.jsp"%>
				<div class="col-8">
					<!--------------------------------------------------------------------------------->
					<div class="container">
						<div class="mb-4" id="accordion" role="tablist"
							aria-multiselectable="true">
							<div class="card">
								<div id='showInfo' class="card-header" role="tab" id="heading">
									<div class="collapsed" data-toggle="collapse"
										data-parent="#accordion" aria-expanded="true"
										aria-controls="collapse">
										<h4 style='display: inline'>
											<i class="fa fa-paw"></i><span id='headPetName'>${showPetVO.petName}</span>
										</h4>
									</div>
								</div>

								<div id="collapse" class="collapse" role="tabpanel"
									aria-labelledby="heading" style='display: flex'>
									<div id='information' class="row no-gutters information"
										style="width: 100%;">
										<div class="col-md-5">
											<img id='showImg' class="card-img"
												src="<%=request.getContextPath()%>/front-end/ShowPhotos?type=pet&photo_no=${showPetVO.petNo}">
										</div>
										<div class="col-md-7">
											<div id='infoCardBody' class="card-body">
												<form>

													<div>
														<div class="form-group row">
															<div class="col-sm-2 col-form-label">名稱</div>
															<div class="col-sm-9">
																<div class="form-control" id='showPetName'>${showPetVO.petName}</div>
															</div>
														</div>

														<div class="form-group row">
															<div class="col-sm-2 col-form-label">類別</div>
															<div class="col-sm-9">
																<div class="form-control" id='showPetKind'>${showPetVO.petKind}</div>
															</div>
														</div>

														<div class="form-group row">
															<div class="col-sm-2 col-form-label">品種</div>
															<div class="col-sm-9">
																<div class="form-control" id='showPetVariety'>${showPetVO.petVariety}</div>
															</div>
														</div>

														<div class="form-group row">
															<div class="col-sm-2 col-form-label">生日</div>
															<div class="col-sm-9">
																<div class="form-control" id='showPetBirth'>${showPetVO.petBirth}</div>
															</div>
														</div>

														<div class="form-group row">
															<div class="col-sm-2 col-form-label">性別</div>
															<div class="col-sm-9">
																<div class="form-control" id='showPetGender'>${showPetVO.petGender}</div>
															</div>
														</div>

														<div class="form-group row">
															<div class="col-sm-2 col-form-label">寵物介紹</div>
															<div class="col-sm-9">
																<textarea class="form-control" id='showPetIntro'>${showPetVO.petIntro}</textarea>
															</div>
														</div>

													</div>
												</form>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<hr>
					<!------------------日誌內文----------------------------------------------------------->
					<c:forEach var="petDailyVO" varStatus="listPDstatus"
						items="${listPD}">
						<div id='daily${listPDstatus.count}'>
							<div class="card shadow p-3 mb-4 bg-white rounded">
								<div class="card-body" style='padding: 8px;'>
									<div class="row" style='margin-bottom: 5px;'>
										<div class="col-sm-1">
											<div class='petHeadPhoto2' style='display: inline-block'></div>
										</div>
										<div class="col-sm-11">
											<h5 style='margin-top: 8px'><%=showPetVO.getPetName()%></h5>
											<h5 class="card-title">
												<c:if test="${petDailyVO.pdClass == 'A'}">
										居家生活
									</c:if>
												<c:if test="${petDailyVO.pdClass == 'B'}">
										戶外生活
									</c:if>
												<c:if test="${petDailyVO.pdClass == 'C'}">
										病例&療程
									</c:if>
												<c:if test="${petDailyVO.pdClass == 'O'}">
										其他
									</c:if>
												&nbsp;&nbsp;${petDailyVO.editTime}
											</h5>
										</div>

									</div>
									<h4 class="card-text">${petDailyVO.pdCont}</h4>
								</div>
								<!-----------------日誌圖片---------------------------------------------------------------->
								<div id="carouselExampleControls${listPDstatus.count}"
									class="carousel slide" data-ride="carousel">
									<div class="carousel-inner">
										<c:forEach var="dailyPhotoVO" varStatus="listDPHstatus"
											items="${listDPH}">
											<c:if test="${dailyPhotoVO.pdNo == petDailyVO.pdNo}">
												<div class="carousel-item">
													<img class="d-block w-100"
														src="<%=request.getContextPath()%>/front-end/ShowPhotos?type=dailyPhoto&photo_no=${dailyPhotoVO.dphNo}">
												</div>
											</c:if>
										</c:forEach>
										<a class="carousel-control-prev"
											href="#carouselExampleControls${listPDstatus.count}"
											role="button" data-slide="prev"> <span
											class="carousel-control-prev-icon" aria-hidden="true"></span>
											<span class="sr-only">Previous</span>
										</a> <a class="carousel-control-next"
											href="#carouselExampleControls${listPDstatus.count}"
											role="button" data-slide="next"> <span
											class="carousel-control-next-icon" aria-hidden="true"></span>
											<span class="sr-only">Next</span>
										</a>
									</div>
								</div>
							</div>
						</div>
					</c:forEach>
					<!--------------------------------------------------------------------------------->
				</div>
			</div>
		</div>
	</section>
	<%@ include file="/front-end/Footer/footer.jsp"%>
	<c:if test="${LoginMem!=null}">
		<%@ include file="/chatRoom/chatRoom.jsp"%>
	</c:if>
	<script>
		$("document").ready(function() {
			$(".carousel-inner > div:first-child").attr("class","carousel-item active");
		});
	</script>


</body>
</html>