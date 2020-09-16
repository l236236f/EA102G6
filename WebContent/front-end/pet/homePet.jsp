<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/front-end/Header/header.jsp"%>
<%@ page import="java.util.*"%>
<%@ page import="com.pet.model.*"%>

<%
	PetService petSvc = new PetService();
	List<PetVO> list = petSvc.findByMemNo(memVO.getMemNo());
	pageContext.setAttribute("list", list);
%>
<%
	PetVO updatePetVO = (PetVO) request.getAttribute("updatePetVO");//�s"�ק�"���~VO
	PetVO insertPetVO = (PetVO) request.getAttribute("insertPetVO");//�s"�s�W"���~VO
%>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
<style>
#ifUWantAddPet {
	display: none;
}

/* #showInfo{ */
/* 	background-color:lightgray;	 */
/* } */
.form-group {
	margin-bottom: 3px;
}

#petPhoto, .petPhoto {
	display: none;
}

#addPhoto {
	padding-top: 20px;
}

#modifyPetPhoto img {
	border: 2px solid #AAA;
	paddind: 2px;
}

#modifyPetPhoto img:hover {
	border: 2px solid #0AF;
}

#addPhoto img {
	opacity: 0.65;
	border: 2px solid #AAA;
	paddind: 2px;
}

#addPhoto img:hover {
	border: 2px solid #0AF;
}

.petDaily {
	color: black;
}

.petDaily:hover {
	color: none;
}

.card-header {
	border-bottom: 2px dashed rgba(0, 0, 0, .125);
}

.card {
	border: none;
}

.information, .modify {
	border-bottom: 2px dashed rgba(0, 0, 0, .125);
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
					<!-- Page Content -->
					<div class="container">

						<!-- Page Heading/Breadcrumbs -->
						<h1 class="mt-4 mb-3">
							<small>�d���M��</small>
						</h1>
						<div class="mb-4" id="accordion" role="tablist"
							aria-multiselectable="true">
							<c:if test="${not empty errorMsgs}">
								<font style="color: red">�Эץ��H�U���~:</font>
								<ul>
									<c:forEach var="message" items="${errorMsgs}">
										<li style="color: red">${message}</li>
									</c:forEach>
								</ul>
							</c:if>
							<c:forEach var="petVO" varStatus="varStatusName" items="${list}">
								<div class="card">
									<div class="card-header" role="tab"
										id="heading${varStatusName.count}">
										<div id='showInfo${varStatusName.count}' class="collapsed" data-toggle="collapse"
											data-parent="#accordion"
											href="#collapse${varStatusName.count}" aria-expanded="true"
											aria-controls="collapse${varStatusName.count}">
											<h4 style='display: inline'>
												<a class='petDaily'
													href="<%=request.getContextPath()%>/front-end/petDaily/thePetDaily.jsp?petNo=${petVO.petNo}">
													<i class="fa fa-paw"></i><span
													id='headPetName${varStatusName.count}'>${petVO.petName}</span>
												</a>
											</h4>
										</div>
									</div>

									<div id="collapse${varStatusName.count}" class="collapse"
										role="tabpanel"
										aria-labelledby="heading${varStatusName.count}">
										<div id='information${varStatusName.count}'
											class="row no-gutters information" style="width: 100%;">
											<div class="col-md-5">
												<img id='showImg${varStatusName.count}' class="card-img"
													src="<%=request.getContextPath()%>/front-end/ShowPhotos?type=pet&photo_no=${petVO.petNo}">
											</div>
											<div class="col-md-7">
												<div id='infoCardBody' class="card-body">
													<form>

														<div>
															<div class="form-group row">
																<div class="col-sm-2 col-form-label">�W��</div>
																<div class="col-sm-9">
																	<div class="form-control"
																		id='showPetName${varStatusName.count}'>${petVO.petName}</div>
																</div>
															</div>

															<div class="form-group row">
																<div class="col-sm-2 col-form-label">���O</div>
																<div class="col-sm-9">
																	<div class="form-control"
																		id='showPetKind${varStatusName.count}'>${petVO.petKind}</div>
																</div>
															</div>

															<div class="form-group row">
																<div class="col-sm-2 col-form-label">�~��</div>
																<div class="col-sm-9">
																	<div class="form-control"
																		id='showPetVariety${varStatusName.count}'>${petVO.petVariety}</div>
																</div>
															</div>

															<div class="form-group row">
																<div class="col-sm-2 col-form-label">�ͤ�</div>
																<div class="col-sm-9">
																	<div class="form-control"
																		id='showPetBirth${varStatusName.count}'>${petVO.petBirth}</div>
																</div>
															</div>

															<div class="form-group row">
																<div class="col-sm-2 col-form-label">�ʧO</div>
																<div class="col-sm-9">
																	<div class="form-control"
																		id='showPetGender${varStatusName.count}'>${petVO.petGender}</div>
																</div>
															</div>

															<div class="form-group row">
																<div class="col-sm-2 col-form-label">�d������</div>
																<div class="col-sm-9">
																	<textarea class="form-control"
																		id='showPetIntro${varStatusName.count}'>${petVO.petIntro}</textarea>
																</div>
															</div>

															<input id='ifUWantModify${varStatusName.count}'
																type='button' class="btn btn-primary" value='�ק��d�����'>
														</div>
													</form>
												</div>
											</div>
										</div>
										<!--�ק��d����� -->
										<div id='modifyInformation${varStatusName.count}'
											class="row no-gutters modify"
											style="width: 100%; display: none;">
											<div id='modifyPetPhoto' class="col-md-5">
												<label for='petPhoto${varStatusName.count}' class="card-img"
													style='margin-bottom: 0'> <img
													id='modifyImg${varStatusName.count}' class="card-img"
													src="<%=request.getContextPath()%>/front-end/ShowPhotos?type=pet&photo_no=${petVO.petNo}">
												</label>
											</div>
											<div class="col-md-7">
												<div id='modifyCardBody' class="card-body">
													<div>
														<form method="post" action="pet.do" name="form1"
															enctype="multipart/form-data">

															<div class="form-group row">
																<label for="petName${varStatusName.count}"
																	class="col-sm-2 col-form-label">�W��</label>
																<div class="col-sm-9">
																	<input type="text" class="form-control"
																		id='petName${varStatusName.count}' name="petName"
																		placeholder="PET NAME" maxLength="20"
																		value="${petVO.petName}" required>
																</div>
															</div>

															<div class="form-group row">
																<label for="petKind${varStatusName.count}"
																	class="col-sm-2 col-form-label">���O</label>
																<div class="col-sm-9">
																	<select class="form-control"
																		id='petKind${varStatusName.count}' name='petKind'>
																		<option id='selectC${varStatusName.count}' value='�߬�'>�߬�</option>
																		<option id='selectD${varStatusName.count}' value='����'>����</option>
																		<option id='selectB${varStatusName.count}' value='����'>����</option>
																		<option id='selectO${varStatusName.count}' value='��L'>��L</option>
																	</select>
																</div>
															</div>

															<div class="form-group row">
																<label for="petVariety${varStatusName.count}"
																	class="col-sm-2 col-form-label">�~��</label>
																<div class="col-sm-9">
																	<input type="text" class="form-control"
																		id='petVariety${varStatusName.count}'
																		name="petVariety" placeholder="PET VARIETY"
																		value="${petVO.petVariety}">
																</div>
															</div>

															<div class="form-group row">
																<label for="petBirth${varStatusName.count}"
																	class="col-sm-2 col-form-label">�ͤ�</label>
																<div class="col-sm-9">
																	<input type="text" class="form-control"
																		id='petBirth${varStatusName.count}' name="petBirth">
																</div>
															</div>

															<div class="form-group row">
																<label for="petBirth" class="col-sm-2 col-form-label">�ʧO</label>
																<div class="col-sm-9" style='padding-top: 7px'>
																	<div
																		class="custom-control custom-radio custom-control-inline">
																		<input type="radio" id="radioM${varStatusName.count}"
																			name="petGender" class="custom-control-input"
																			value="����"> <label
																			class="custom-control-label"
																			for="radioM${varStatusName.count}">����</label>
																	</div>
																	<div
																		class="custom-control custom-radio custom-control-inline">
																		<input type="radio" id="radioW${varStatusName.count}"
																			name="petGender" class="custom-control-input"
																			value="�۩�"> <label
																			class="custom-control-label"
																			for="radioW${varStatusName.count}">�۩�</label>
																	</div>
																</div>
															</div>

															<div class="form-group row">
																<label for="petIntro${varStatusName.count}"
																	class="col-sm-2 col-form-label">�d������</label>
																<div class="col-sm-9">
																	<textarea class="form-control"
																		id='petIntro${varStatusName.count}' name="petIntro">${petVO.petIntro}</textarea>
																</div>
															</div>

															<input type="file" class='petPhoto'
																id='petPhoto${varStatusName.count}' name="petPhoto"
																accept="image/gif, image/jpeg, image/png"> <input
																type="hidden" name="action" value="update"> <input
																type="hidden" id='petNo${varStatusName.count}'
																name="petNo" value="${petVO.petNo}"> <input
																type="hidden" id='memNo${varStatusName.count}'
																name="memNo" value="${petVO.memNo}">
															<button type='button' id='goBack${varStatusName.count}'
																class="btn btn-secondary" style='display: inline'>�����ק�</button>
															<input type="button" id='send${varStatusName.count}'
																class="btn btn-primary" value="�e�X�ק�">
														</form>
													</div>
												</div>
											</div>
										</div>
										<!--�ק��d����� end -->
									</div>
								</div>
							</c:forEach>
							<hr>
							<!-- �s�W�d����� -->
							<div id='show' align='center'>
								<input id='showTable' type='button' class="btn btn-success"
									value='�Q�W�[�d����?'> <input id='hideTable' type='button'
									class="btn btn-danger" value='���Q' style='display: none'>
							</div>
							<div id="ifUWantAddPet" align='center'>
								<!--���~��C -->
								<c:if test="${not empty errorMsgs}">
									<font style="color: red">�Эץ��H�U���~:</font>
									<ul>
										<c:forEach var="message" items="${errorMsgs}">
											<li style="color: red">${message}</li>
										</c:forEach>
									</ul>
								</c:if>

								<div id='addPet' class="card" style="width: 70%;">
									<div id='addPhoto' style='align: center;'>
										<label for='petPhoto' class="card-img"
											style='margin-bottom: 0'> <img class="card-img-top"
											src="images/noPet.jpeg" style="width: 50%;">
										</label>
									</div>
									<div class="card-body">
										<div class="card-text">

											<form method="post" action="pet.do" name="form1"
												enctype="multipart/form-data">

												<div class="form-group row">
													<label for="petName" class="col-sm-2 col-form-label">�W��:</label>
													<div class="col-sm-9">
														<input type="text" class="form-control" id='petName'
															name="petName" placeholder="PET NAME" maxLength="20"
															value="<%=(insertPetVO == null) ? "" : insertPetVO.getPetName()%>"
															required>
													</div>
												</div>

												<div class="form-group row">
													<label for="petKind" class="col-sm-2 col-form-label">���O:</label>
													<div class="col-sm-9">
														<select class="form-control" id='petKind' name='petKind'>
															<option id='selectC' value='�߬�'>�߬�</option>
															<option id='selectD' value='����'>����</option>
															<option id='selectB' value='����'>����</option>
															<option id='selectO' value='��L'>��L</option>
														</select>
													</div>
												</div>

												<div class="form-group row">
													<label for="petVariety" class="col-sm-2 col-form-label">�~��:</label>
													<div class="col-sm-9">
														<input type="text" class="form-control" id='petVariety'
															name="petVariety" placeholder="PET VARIETY"
															value="<%=(insertPetVO == null) ? "" : insertPetVO.getPetVariety()%>"
															required>
													</div>
												</div>

												<div class="form-group row">
													<label for="petBirth" class="col-sm-2 col-form-label">�ͤ�:</label>
													<div class="col-sm-9">
														<input name="petBirth" class="form-control" id="petBirth"
															type="text" />
													</div>
												</div>

												<div class="form-group row">
													<label class="col-sm-2 col-form-label">�ʧO</label>
													<div class="col-sm-9" style='padding-top: 7px'>
														<div
															class="custom-control custom-radio custom-control-inline">
															<input type="radio" id="radioM" name="petGender"
																class="custom-control-input" value="����"> <label
																class="custom-control-label" for="radioM">����</label>
														</div>
														<div
															class="custom-control custom-radio custom-control-inline">
															<input type="radio" id="radioW" name="petGender"
																class="custom-control-input" value="�۩�"> <label
																class="custom-control-label" for="radioW">�۩�</label>
														</div>
													</div>
												</div>

												<div class="form-group row">
													<label for="petID" class="col-sm-2 col-form-label">�����X:</label>
													<div class="col-sm-9">
														<input class="form-control" type="TEXT" id='petID'
															name="petID" placeholder="PET ID"
															value="<%=(insertPetVO == null) ? "" : insertPetVO.getPetID()%>" />
													</div>
												</div>

												<div class="form-group row">
													<label for="petIntro" class="col-sm-2 col-form-label">�d������:</label>
													<div class="col-sm-9">
														<textarea class="form-control" id='petIntro'
															name="petIntro"><%=(insertPetVO == null) ? "" : insertPetVO.getPetIntro()%></textarea>
													</div>
												</div>

												<input type="file" id='petPhoto' name="petPhoto"
													accept="image/gif, image/jpeg, image/png" /> <br> <input
													type="hidden" name="memNo" value="<%=memVO.getMemNo()%>">
												<input type="submit" value="�s�W�d��" class="btn btn-primary">
												<input type="hidden" name="action" value="insert">
											</form>

										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
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
$('document').ready(function(){
	setTimeout(function(){
		$("#showInfo1").click();
	},200);
	setTimeout(function(){
		$('body').animate({
			scrollTop: 200
		}, 1500);
	},500);
});
<%if (insertPetVO != null) {%>
	$("#ifUWantAddPet").css('display','block');
<%}%>
$("input").keyup(function(){
	switch(event.keyCode) { 
		case 113:
			$("#addpet #petName").val("���i��");
			$('#addpet #selectC').prop("selected",true);
			$("#addpet #petVariety").val("�߿�");
			$("#addpet #petBirth").val("2018-08-24");
			$('#addpet #radioM').prop("checked",true);
			$("#addpet #petID").val("7700123456");
			$("#addpet #petIntro").val("�p�}�߿ߥs");
			return;

		case 115:
			$("#addpet #petName").val("�^��");
			$('#addpet #selectD').prop("selected",true);
			$("#addpet #petVariety").val("�x�p��");
			$("#addpet #petBirth").val("2019-08-24");
			$('#addpet #radioM').prop("checked",true);
			$("#addpet #petID").val("7700234567");
			$("#addpet #petIntro").val("�p�ª�");
			return;

		}
	});
	

$("#showTable").click(function(){
	$("#ifUWantAddPet").slideToggle(200);
	$("#showTable").css('display','none');
	$("#hideTable").css('display','block');
});
$("#hideTable").click(function(){
	$("#ifUWantAddPet").slideToggle(200);
	$("#showTable").css('display','block');
	$("#hideTable").css('display','none');
});


<%for (int i = 1; i <= list.size(); i++) {%>
$("#ifUWantModify"+"<%=i%>").click(function(){
	$("#information"+"<%=i%>").css('display','none');
	$("#modifyInformation"+"<%=i%>").css('display','flex');
});

$("#send"+"<%=i%>").click(function(){
	$("#information"+"<%=i%>").css('display','flex');
	$("#modifyInformation"+"<%=i%>").css('display','none');
});
<%}%>

<%for (int i = 1; i <= list.size(); i++) {%>
$("#goBack"+"<%=i%>").click(function(){
	$("#information"+"<%=i%>").css('display','flex');
	$("#modifyInformation"+"<%=i%>").css('display','none');
});
<%}%>

<%for (int i = 1; i <= list.size(); i++) {%>
$(function() {
    $("#petPhoto"+"<%=i%>").change(function() {
        var readFile = new FileReader();
        var petPhoto = $("#petPhoto"+"<%=i%>")[0].files[0]; 
        readFile.readAsDataURL(petPhoto);
        readFile.onload = function() {
            $("#modifyimg"+"<%=i%>").attr("src", this.result);
        }
    });
})
<%}%>

<%for (int i = 1; i <= list.size(); i++) {%>
		$("#send"+"<%=i%>").click(function(){
			var data = new FormData();
			data.append("action","update");
			data.append("petNo",$("#petNo"+"<%=i%>").val());
			data.append("memNo",$("#memNo"+"<%=i%>").val());
			data.append("petName",$("#petName"+"<%=i%>").val());
			data.append("petPhoto",$("#petPhoto"+"<%=i%>")[0].files[0]);	
			data.append("petKind",$("#petKind"+"<%=i%>").val());
			data.append("petVariety",$("#petVariety"+"<%=i%>").val());
			data.append("petBirth",$("#petBirth"+"<%=i%>").val());
			if($("#radioM"+"<%=i%>").prop("checked") === true){
				var petGender = "����";	
			}else{
				var petGender = "�۩�";
			}
			data.append("petGender",petGender);
			data.append("petIntro",$("#petIntro"+"<%=i%>").val());
			
			$.ajax({
				 type: "POST",
				 url: "<%=request.getContextPath()%>/front-end/pet/pet.do",
				 data: data,
				 processData:false,
  				 contentType :false,
   			 	 cache:false,
	
				 success: function (data){
					data = JSON.parse(data);
				
					$("#showPetName"+"<%=i%>").text(data.petName);
					$("#headPetName"+"<%=i%>").text(data.petName);
					$("#showPetKind"+"<%=i%>").text(data.petKind);
					$("#showPetVariety"+"<%=i%>").text(data.petVariety);
					$("#showPetBirth"+"<%=i%>").text(data.petBirth);
					$("#showPetGender"+"<%=i%>").text(data.petGender);
					$("#showPetIntro"+"<%=i%>").text(data.petIntro);
					
				},
				error : function() {
					alert("�ק異��");
				}
			})
	});	
<%}%>

<%for (int i = 1; i <= list.size(); i++) {%>
    $("#petPhoto"+"<%=i%>").change(function() {
        var readFile = new FileReader();
        var petPhoto = $("#petPhoto"+"<%=i%>")[0].files[0];  //�`�N�o�̥�����$("#myfile")[0]�Adocument.getElementById('file')�����P$("#myfile")[0]
        readFile.readAsDataURL(petPhoto);
        readFile.onload = function() {
        	$("#showImg"+"<%=i%>").attr("src", this.result);
            $("#modifyImg"+"<%=i%>").attr("src", this.result);
        }
    });
<%}%>

</script>
<%
	java.sql.Date petBirth = null;
	try {
		petBirth = insertPetVO.getPetBirth();
	} catch (Exception e) {
		petBirth = new java.sql.Date(System.currentTimeMillis());
	}

	String petGender = null;
	try {
		petGender = insertPetVO.getPetGender();
	} catch (Exception e) {
		petGender = "����";
	}

	String petKind = null;
	try {
		petKind = insertPetVO.getPetKind();
	} catch (Exception e) {
		petKind = "�߬�";
	}
%>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}
.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>

<script>
		var petGender = "<%=petGender%>";
		if(petGender === "����"){
			$('#addpet #radioM').prop("checked",true);
		}else{
			$('#addpet #radioW').prop("checked",true);
		}
		
		var petKind = "<%=petKind%>";
		if(petKind === "����"){
			$('#selectD').prop("selected",true);
		}else if(petKind === "����"){
			$('#selectB').prop("selected",true);
		}else if(petKind === "��L"){
			$('#selectO').prop("selected",true);
		}else{
			$('#selectC').prop("selected",true);
		}

        $.datetimepicker.setLocale('zh');
        $('#petBirth').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (�o�Otimepicker���w�]���j60����)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=petBirth%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // �h���S�w���t
           //startDate:	            '2017/07/10',  // �_�l��
           //minDate:               '-1970-01-01', // �h������(���t)���e
           //maxDate:               '+1970-01-01'  // �h������(���t)����
        });
        
        //��U���d���ͤ�
        <%int i = 0;; //�ϰ��ܼ�
			java.sql.Date thePetBirth = null;
			String thePetKind = null;
			String thePetGender = null;
			for (PetVO thePetVO : list) {
				i++;
				thePetKind = thePetVO.getPetKind();
				thePetBirth = thePetVO.getPetBirth();
				thePetGender = thePetVO.getPetGender();%>
        $('#petBirth'+"<%=i%>").datetimepicker({
 	       theme: '',              //theme: 'dark',
 	       timepicker:false,       //timepicker:true,
 	       step: 1,                //step: 60 (�o�Otimepicker���w�]���j60����)
 	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
 		   value: '<%=thePetBirth%>'      // value:   new Date(),
            //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // �h���S�w���t
            //startDate:	            '2017/07/10',  // �_�l��
            //minDate:               '-1970-01-01', // �h������(���t)���e
            //maxDate:               '+1970-01-01'  // �h������(���t)����
         });
        
		if("<%=thePetKind%>" === "����"){
			$('#selectD'+"<%=i%>").prop("selected",true);
		}else if("<%=thePetKind%>" === "����"){
			$('#selectB'+"<%=i%>").prop("selected",true);
		}else if("<%=thePetKind%>" === "��L"){
			$('#selectO'+"<%=i%>").prop("selected",true);
		}else{
			$('#selectC'+"<%=i%>").prop("selected",true);
		};
		
		if("<%=thePetGender%>" === "����"){
			$('#radioM'+"<%=i%>").prop("checked",true);
		}else{
			$('#radioW'+"<%=i%>").prop("checked", true);
	};
<%}%>
	$(function() {
		$("#addpet #petPhoto").change(function() {
			var readFile = new FileReader();
			var petPhoto = $("#addpet #petPhoto")[0].files[0]; //�`�N�o�̥�����$("#myfile")[0]�Adocument.getElementById('file')�����P$("#myfile")[0]
			readFile.readAsDataURL(petPhoto);
			readFile.onload = function() {
				$("#addpet img").attr("src", this.result);
			}
			$("#addpet img").css("opacity", 1);
		});
	})
</script>
</html>