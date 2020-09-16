<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/front-end/Header/header.jsp"%>

<%
	MemVO XmemVO = (MemVO) request.getAttribute("XmemVO");
%><!-- �ק�����VO -->

<html>
<head>
<meta charset="BIG5">
<title>�|�����Uregister</title>
<style type="text/css">
#bgImage {
	width: 100%;
	height: 140%;
	background-image: url(<%=request.getContextPath()%>/front-end/mem/images/registerMem.jpg);
	background-repeat: no-repeat;
	background-size: cover;
}

#registerDiv {
	position: absolute;
	margin-top: 8%;
	margin-left: 50%;
	width: 35%;
	padding: 16px;
	border-radius: 8px;
	background-color: white;
	opacity: 0.95;
}

/* @media screen and (min-width:1194px) and (max-width:1699px) { */
/* 	#registerDiv { */
/* 		width: 50%; */
/* 		margin-left: 15%; */
/* 	} */
/* } */

/* @media screen and (max-width: 1193px) { */
/* 	#registerDiv { */
/* 		width: 80%; */
/* 		margin-left: 5%; */
/* 	} */
/* 	#bgImage { */
/* 		height: 150%; */
/* 	} */
/* } */

#registerDiv:hover { 
	opacity: 1; 
} 

#loadingImage {
	text-align: center;
	margin-bottom: 20px;
	height: 225px;
}

#memPhoto {
	display: none;
}

#memPhoto+img {
	height: 225px;
	border: 2px solid #AAA;
	paddind: 2px;
}

#memPhoto+img:hover {
	border: 2px solid #0AF;
}

.form-group {
	margin-bottom: 5px;
}

select.country, select.district,select.zipcode{
	display: inline;
    width: 100%;
    height: calc(1.5em + .75rem + 2px);
    padding: .375rem .75rem;
    font-size: 1rem;
    font-weight: 400;
    line-height: 1.5;
    color: #495057;
    background-color: #fff;
    background-clip: padding-box;
    border: 1px solid #ced4da;
    border-radius: .25rem;
    transition: border-color .15s ease-in-out,box-shadow .15s ease-in-out;
    margin-bottom:5px;
}
ul{
list-style-type:none;
padding-inline-start:0px;

}
</style>
</head>
<body>

	<div id='bgImage'>
		<div id='registerDiv' class='shadow p-3 mb-5 bg-white rounded '>
			<%-- ���~��C --%>
			<c:if test="${not empty errorMsgs}">
				<font style="color: red">�Эץ��H�U���~:</font>
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color: red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>

			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/front-end/mem/mem.do"
				name="form1" enctype="multipart/form-data">


				<div id='loadingImage'>
					<label for="memPhoto"> <input type="file" id='memPhoto'
						name="memPhoto" /> <img
						src='<%=request.getContextPath()%>/front-end/mem/images/noUser.png'>
					</label>
				</div>

				<div class="form-group row">
					<label for="memName" class="col-sm-2 col-form-label">�|���m�W:</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" id='memName'
							name="memName" placeholder="USER NAME" maxLength="20"
							value="<%=(XmemVO == null) ? "" : XmemVO.getMemName()%>" required>
					</div>
				</div>

				<div class="form-group row">
					<label for="memAcc" class="col-sm-2 col-form-label">�|���b��:</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" id='memAcc' name="memAcc"
							placeholder="LOGIN ACCOUNT"
							value="<%=(XmemVO == null) ? "" : XmemVO.getMemAcc()%>" required>
					</div>
				</div>

				<div class="form-group row">
					<label for="memPw" class="col-sm-2 col-form-label">�|���K�X:</label>
					<div class="col-sm-9">
						<input type="password" class="form-control" id='memPw'
							name="memPw" placeholder="LOGIN PASSWORD"
							value="<%=(XmemVO == null) ? "" : XmemVO.getMemPw()%>" required>
					</div>
				</div>

				<div class="form-group row">
					<label for="memBirth" class="col-sm-2 col-form-label">�ͤ� :</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" id='memBirth'
							name="memBirth">
					</div>
				</div>

				<div class="form-group row">
					<label for="memID" class="col-sm-2 col-form-label">�����Ҹ�:</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" id='memID' name="memID"
							placeholder="USER ID"
							value="<%=(XmemVO == null) ? "" : XmemVO.getMemID()%>" required>
					</div>
				</div>

				<div class="form-group row">
					<label for="memTel" class="col-sm-2 col-form-label">�p���q��:</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" id='memTel' name="memTel"
							placeholder="TELEPHONE"
							value="<%=(XmemVO == null) ? "" : XmemVO.getMemTel()%>" required>
					</div>
				</div>

				<div class="form-group row">
					<div class="col-form-label col-sm-2 pt-0">�ʧO:</div>
					<div class="col-sm-9 custom-control-inline">
						<div class="form-check">
							<input class="form-check-input" type="radio" name="memGender"
								id="radioM" value="�k��"> <label class="form-check-label"
								for="radioM"> �k�� </label>
						</div>
						&nbsp;&nbsp;
						<div class="form-check">
							<input class="form-check-input" type="radio" name="memGender"
								id="radioW" value="�k��"> <label class="form-check-label"
								for="radioW"> �k�� </label>
						</div>
					</div>
				</div>
<!-- �a�} -->
	<div class="form-group row text-left" style='display:inline-block;'>
        <label for="mem_addr" class="col-sm-2 col-form-label">�a�}:</label> 
        <div class="col-sm-9" style='display:inline-block;padding-left:10px;'>
        	<ul class="row memberForm">
              <li class="col-sm-4">
                  <select class="country" name="country" required></select>
              </li>

              <li class="col-sm-4">
                  <select class="district" name="district" required></select>
              </li>

              <li class="col-sm-4">
                   <input type="text" class="zipcode form-control" name="zipcode" placeholder="�l���ϸ�" readonly />
              </li>

              <li class="col-sm-12">
                  <input type="text" class="form-control detail col-form-label" id="address" name="address" value="" required>
              </li>
        	</ul>
        </div>
    </div>

				<div class="form-group row">
					<label for="memEmail" class="col-sm-2 col-form-label">�q�l�l��:</label>
					<div class="col-sm-9">
						<input type="email" class="form-control" id='memEmail'
							name="memEmail" placeholder="E-MAIL"
							value="<%=(XmemVO == null) ? "" : XmemVO.getMemEmail()%>" required>
					</div>
				</div>

				<div class="form-group row">
					<label for="memMoney" class="col-sm-2 col-form-label">�״ڱb��:</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" id='memMoney'
							name="memMoney" placeholder="USER ACCOUNT"
							value="<%=(XmemVO == null) ? "" : XmemVO.getMemMoney()%>" required>
					</div>
				</div>

				<div class="form-group row">
					<label for="memIntro" class="col-sm-2 col-form-label">²��:</label>
					<div class="col-sm-9">
						<textarea class="form-control" id='memIntro' name="memIntro"
							style="width: 100%; height: 100px;"><%=(XmemVO == null) ? "" : XmemVO.getMemIntro()%></textarea>
					</div>
				</div>

				<hr>
				<div align='center'>
					<input type="hidden" name="action" value="insert"> <input
						type="submit" class="btn btn-primary" value="���U"
						style='width: 20%; height: 20%'>
				</div>
			</FORM>
		</div>
	</div>

	<%@ include file="/front-end/Footer/footer.jsp"%>
	<c:if test="${LoginMem!=null}">
		<%@ include file="/chatRoom/chatRoom.jsp"%>
	</c:if>
	<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>
	<script src="<%=request.getContextPath()%>/front-end/js/dk-tw-citySelector.js"></script>
	<script src="<%=request.getContextPath()%>/front-end/js/init-address.js"></script>
</body>

<%
	java.sql.Date memBirth = null;
	try {
		memBirth = XmemVO.getMemBirth();
	} catch (Exception e) {
		memBirth = new java.sql.Date(System.currentTimeMillis());
	}

	String memGender = null;
	try {
		memGender = XmemVO.getMemGender();
	} catch (Exception e) {
		memGender = "�k��";
	}
%>


<script>
	window.onload = function(){

	  let memberForm = $('.memberForm');
	  initAddress(memberForm, "<%=(XmemVO == null)?"":XmemVO.getMemAddr()%>");
	 };

		var memGender = "<%=memGender%>";
		if(memGender === "�k��"){
			$('#radioM').prop("checked",true);
		}else{
			$('#radioW').prop("checked",true);
		}

        $.datetimepicker.setLocale('zh');
        $('#memBirth').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (�o�Otimepicker���w�]���j60����)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=memBirth%>', // value:   new Date(),
        });

        $(function() {
            $("#memPhoto").change(function() {
                var readFile = new FileReader();
                var memPhoto = $("#memPhoto")[0].files[0];  //�`�N�o�̥�����$("#myfile")[0]�Adocument.getElementById('file')�����P$("#myfile")[0]
                readFile.readAsDataURL(memPhoto);
                readFile.onload = function() {
                    $("#memPhoto + img").attr("src", this.result);
                }
            });
        })
      $("input").keyup(function(){ 
    	  switch(event.keyCode) { 
   			case 113:
           		$("#memName").val("�Q�l");
            	$("#memAcc").val("EA102G6");
            	$("#memPw").val("EA102G6");
          		$("#memBirth").val("1996-08-24");
            	$("#memID").val("A123456789");
            	$("#memTel").val("0987654321");
            	$("#radioM").prop("checked",true);
            	$("#memEmail").val("cky771234@gmail.com");
          		$("#memMoney").val("12345671234567");
            	$("#memIntro").val("JAVA�f�Z�Ĥ@�y:JAVA���O²�檺");
            	initAddress($('.memberForm'),"320��鿤���c�������28��");
            return;
       	  };  
       });	
</script>
</html>