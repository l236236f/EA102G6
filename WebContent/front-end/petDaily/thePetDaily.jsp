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
	PetVO petVO = petSvc.getOnePet(request.getParameter("petNo"));
	pageContext.setAttribute("petVO", petVO);
%>
<%
	PetDailyService petDailySvc = new PetDailyService();
	List<PetDailyVO> listPD = petDailySvc.findByPetNo(request.getParameter("petNo"));
	pageContext.setAttribute("listPD", listPD);
%>
<%
	DailyPhotoService dailyPhotoSvc = new DailyPhotoService();
	List<DailyPhotoVO> listDPH = dailyPhotoSvc.getAll();
	pageContext.setAttribute("listDPH", listDPH);
%>
<%
	PetDailyVO petDailyVO = (PetDailyVO) request.getAttribute("petDailyVO"); //�s���~VO
%>
<html>
<head>
<meta charset="BIG5">
<title>�d����x</title>
<style>
#petHeadPhoto {
	width: 100px;
	height: 100px;
	border-radius: 50%;
	background-image: url(<%=request.getContextPath()%>/front-end/ShowPhotos?type=pet&photo_no=${petVO.petNo});
	background-repeat:no-repeat;
	background-size:cover;
}

.petHeadPhoto2 {
	width: 55px;
	height: 55px;
	border-radius: 50%;
	background-image: url(<%=request.getContextPath()%>/front-end/ShowPhotos?type=pet&photo_no=${petVO.petNo});
	background-repeat:no-repeat;
	background-size:cover;
}

#theBtn {
	background-color: white;
}

#theBtn:hover {
	background-color: lightgray;
}
</style>
</head>
<body>
	<section class="blog-section spad">
		<div class="container">
			<div class="row">
				<%@ include file="/front-end/mem/LHSBar.jsp"%>
				<div class="col-8">
					<!----------------------------- �s�W�d����x���----------------------------- -->
					<div id="ifUWantAddPetDaily" align='center'>
						<FORM METHOD="post" ACTION="petDaily.do" name="form1"
							enctype="multipart/form-data">
							<div class="card col-12 shadow p-3 bg-white rounded">

								<div class="row" style='margin-top: 15px'>
									<div class="col-sm-2">
										<div id='petHeadPhoto' style='display: inline-block'></div>
									</div>
									<div class="col-sm-10">
										<div class="input-group">
											<input type="TEXT" class="form-control alert alert-secondary"
												id='pdCont' name="pdCont"
												style="height: 100px; font-size: 24px"
												placeholder='<%=petVO.getPetName()%>�A�b�Q�Ǥ��� ?' required>
										</div>
									</div>
								</div>
								<hr>
								<div class="btn-group" role="group"
									aria-label="Button group with nested dropdown">
									<label for='photo' class="btn btn-light col-6" id="theBtn"
										style='margin-bottom: 0; padding: 0'> <input
										type="file" id='photo' name="photo" style='display: none'
										accept="image/gif, image/jpeg, image/png" multiple> <span
										style='line-height: 50px; font-size: 20px'>�W�ǹϤ�<sub
											id='photoCount'
											style='position: absolute; right: 0; line-height: 30px;'></sub></span>
									</label>

									<div class="form-group col-md-6"
										style='margin-bottom: 0; padding: 0'>
										<select id='pdClass' name='pdClass' class="form-control"
											style='height: 51.99px; border: none; text-align: center; font-size: 20px'>
											<option selected value='O'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�п���d����x���O</option>
											<option id='selectA' value='A'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�~�a�ͬ�</option>
											<option id='selectB' value='B'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��~�ͬ�</option>
											<option id='selectC' value='C'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�f�һP���{</option>
											<option id='selectO' value='O'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��L</option>
										</select>
									</div>
								</div>

								<input type="hidden" name="petNo"
									value="<%=request.getParameter("petNo")%>"> <input
									type="hidden" name="petDailyNo"
									value="<%=request.getParameter("petDailyNo")%>"> <input
									type="hidden" name="action" value="insert"> <input
									type="submit" value="�o�G" id='sent'
									class="alert alert-secondary" disabled="disabled"
									style='height: 47px; margin: 10px 0;'>
							</div>
						</FORM>
						<br>
					</div>

					<!---------------------��x����------------------------------------------------------------>

					<c:forEach var="petDailyVO" varStatus="listPDstatus"
						items="${listPD}">
						<div id='daily${listPDstatus.count}'>
							<div class="card shadow p-3 mb-4 bg-white rounded">
								<div class="card-body" style='padding: 8px;'>
									<div class="row" style='margin-bottom: 5px;'>
										<div class="col-sm-1">
											<div class='petHeadPhoto2' style='display: inline-block'></div>
										</div>
										<div class="col-sm-10">
											<h5 style='margin-top: 8px'><%=petVO.getPetName()%></h5>
											<h5 class="card-title">
												<c:if test="${petDailyVO.pdClass == 'A'}">�~�a�ͬ�</c:if>
												<c:if test="${petDailyVO.pdClass == 'B'}">��~�ͬ�</c:if>
												<c:if test="${petDailyVO.pdClass == 'C'}">�f��&���{</c:if>
												<c:if test="${petDailyVO.pdClass == 'O'}">��L</c:if>
												&nbsp;&nbsp;${petDailyVO.editTime}
											</h5>
										</div>
										<div class="col-sm-1" style='width: 3%; height: 3%;'>
											<label for='delete${listPDstatus.count}'
												style='opacity: 0.55; margin-bottom: 0; padding: 0'>X</label>
										</div>
										<input type="submit" id='delete${listPDstatus.count}'
											name="pdNo" style='display: none' value="${petDailyVO.pdNo}">

									</div>
									<h4 class="card-text">${petDailyVO.pdCont}</h4>
								</div>
								<!-----------------��x�Ϥ�---------------------------------------------------------------->
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
					<hr>
					<!--------------------------------------------------------------------------------->
				</div>
			</div>
		</div>
	</section>

	<%@ include file="/front-end/Footer/footer.jsp"%>
	<c:if test="${LoginMem!=null}">
		<%@ include file="/chatRoom/chatRoom.jsp"%>
	</c:if>
</body>
<script>
$("document").ready(function(){
	$(".carousel-inner > div:first-child").attr("class","carousel-item active");
	setTimeout(function(){
		$('body').animate({
			scrollTop: 200
		}, 1500);
	},500);
});

$("#pdCont").keyup(function(){
	switch(event.keyCode) { 
	case 113:
		var startTime = 500;
		var timeInterval = 60;
		setTimeout(function() {
			$('#pdCont').val("��")
			$("#sent").attr('class','btn btn-primary');
			$("#sent").attr('disabled',false);	
		}, startTime);
		setTimeout(function() {
			$('#pdCont').val("����")
		}, startTime + timeInterval * 1);
		setTimeout(function() {
			$('#pdCont').val("������")
		}, startTime + timeInterval * 2);
		setTimeout(function() {
			$('#pdCont').val("�p")
		}, startTime + timeInterval * 3);
		setTimeout(function() {
			$('#pdCont').val("�p�t")
		}, startTime + timeInterval * 4);
		setTimeout(function() {
			$('#pdCont').val("�p�t��")
		}, startTime + timeInterval * 5);
		setTimeout(function() {
			$('#pdCont').val("�p��")
		}, startTime + timeInterval * 6);
		setTimeout(function() {
			$('#pdCont').val("�p�£|")
		}, startTime + timeInterval * 7);
		setTimeout(function() {
			$('#pdCont').val("�p�£|��")
		}, startTime + timeInterval * 8);
		setTimeout(function() {
			$('#pdCont').val("�p�ª�")
		}, startTime + timeInterval * 9);
		setTimeout(function() {
			$('#pdCont').val("�p�ª��A")
		}, startTime + timeInterval * 10);
		setTimeout(function() {
			$('#pdCont').val("�p�ª��A�z")
		}, startTime + timeInterval * 11);
		setTimeout(function() {
			$('#pdCont').val("�p�ª��A�z��")
		}, startTime + timeInterval * 12);
		setTimeout(function() {
			$('#pdCont').val("�p�ª��A�A")
		}, startTime + timeInterval * 13);
		setTimeout(function() {
			$('#pdCont').val("�p�ª��A�A�~")
		}, startTime + timeInterval * 14);
		setTimeout(function() {
			$('#pdCont').val("�p�ª��A�A�~��")
		}, startTime + timeInterval * 15);
		setTimeout(function() {
			$('#pdCont').val("�p�ª��A�A�`")
		}, startTime + timeInterval * 16);
		setTimeout(function() {
			$('#pdCont').val("�p�ª��A�A�`��")
		}, startTime + timeInterval * 17);
		setTimeout(function() {
			$('#pdCont').val("�p�ª��A�A�`����")
		}, startTime + timeInterval * 18);
		setTimeout(function() {
			$('#pdCont').val("�p�ª��A�A�`��")
		}, startTime + timeInterval * 19);
		setTimeout(function() {
			$('#pdCont').val("�p�ª��A�A�`�ڣ~")
		}, startTime + timeInterval * 20);
		setTimeout(function() {
			$('#pdCont').val("�p�ª��A�A�`�ڣ~��")
		}, startTime + timeInterval * 21);
		setTimeout(function() {
			$('#pdCont').val("�p�ª��A�A�`�ڦn")
		}, startTime + timeInterval * 22);
		setTimeout(function() {
			$('#pdCont').val("�p�ª��A�A�`�ڦn�x")
		}, startTime + timeInterval * 23);
		setTimeout(function() {
			$('#pdCont').val("�p�ª��A�A�`�ڦn�x��")
		}, startTime + timeInterval * 24);
		setTimeout(function() {
			$('#pdCont').val("�p�ª��A�A�`�ڦn�x����")
		}, startTime + timeInterval * 25);
		setTimeout(function() {
			$('#pdCont').val("�p�ª��A�A�`�ڦn��")
		}, startTime + timeInterval * 26);
		setTimeout(function() {
			$('#pdCont').val("�p�ª��A�A�`�ڦn��{")
		}, startTime + timeInterval * 27);
		setTimeout(function() {
			$('#pdCont').val("�p�ª��A�A�`�ڦn��{��")
		}, startTime + timeInterval * 28);
		setTimeout(function() {
			$('#pdCont').val("�p�ª��A�A�`�ڦn��{����")
		}, startTime + timeInterval * 29);
		setTimeout(function() {
			$('#pdCont').val("�p�ª��A�A�`�ڦn���y")
		}, startTime + timeInterval * 30);
		setTimeout(function() {
			$('#pdCont').val("�p�ª��A�A�`�ڦn���y!")
		}, startTime + timeInterval * 31);
		return;
	
	case 115:
		var startTime = 500;
		var timeInterval = 60;
		setTimeout(function() {
			$('#pdCont').val("��")
			$("#sent").attr('class','btn btn-primary');
			$("#sent").attr('disabled',false);	
		}, startTime);
		setTimeout(function() {
			$('#pdCont').val("�v")
		}, startTime + timeInterval * 1);
		setTimeout(function() {
			$('#pdCont').val("�v��")
		}, startTime + timeInterval * 2);
		setTimeout(function() {
			$('#pdCont').val("�[")
		}, startTime + timeInterval * 3);
		setTimeout(function() {
			$('#pdCont').val("�[�{")
		}, startTime + timeInterval * 4);
		setTimeout(function() {
			$('#pdCont').val("�[�{��")
		}, startTime + timeInterval * 5);
		setTimeout(function() {
			$('#pdCont').val("�[��")
		}, startTime + timeInterval * 6);
		setTimeout(function() {
			$('#pdCont').val("�[����")
		}, startTime + timeInterval * 7);
		setTimeout(function() {
			$('#pdCont').val("�[����")
		}, startTime + timeInterval * 8);
		setTimeout(function() {
			$('#pdCont').val("�[����~")
		}, startTime + timeInterval * 9);
		setTimeout(function() {
			$('#pdCont').val("�[����~~")
		}, startTime + timeInterval * 10);
		setTimeout(function() {
			$('#pdCont').val("�[����~~~")
		}, startTime + timeInterval * 11);
		return;
}
	if($("#pdCont").val() !== ""){
		$("#sent").attr('class','btn btn-primary');
		$("#sent").attr('disabled',false);	
	}else{
		$("#sent").attr('class','alert alert-secondary');
		$("#sent").attr('disabled',true);
	}
});

$("#photo").change(function(){
	var files = $("#photo")[0].files;
	var count = $("#photo")[0].files.length;
	$("#photoCount").text("�w���" + count + "�Ӷ���");
	
});

<%for (int i = 1; i <= listPD.size(); i++) {%>
		$("#delete"+"<%=i%>").click(function(){
			var data = new FormData();
			data.append("action","delete");
			data.append("pdNo",$("#delete"+"<%=i%>").val());
			
			$.ajax({
				 type: "POST",
				 url: "<%=request.getContextPath()%>/front-end/petDaily/petDaily.do",
				 data: data,
				 processData:false,
  				 contentType:false,
   			 	 cache:false,
   			 	 
				 success: function (){	 
					$("#daily"+"<%=i%>").slideUp();
				 },
				 error : function() {
					alert("�R������");
				 }
			})
		});
<%}%>
	
</script>
</html>