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
<title>Insert title here</title>
<style type="text/css">
#memPhoto {
	display: none;
}

#memPhoto+img {
	/*  	height:225px; */
	border: 2px solid #AAA;
	paddind: 2px;
}

#memPhoto+img:hover {
	border: 2px solid #0AF;
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
	<section class="blog-section spad">
		<div class="container">
			<div class="row">
				<%@ include file="/front-end/mem/LHSBar.jsp"%>
				<div class="col-8">
					<!--------------------------------------------------------------------------------->


					<div class="card shadow p-3 mb-5 bg-white rounded"
						style="margin-left: 1%; width: 98%; align: center">

						<div align="center">
							<label for='memPhoto'> <input type="file" id='memPhoto'
								name="memPhoto" accept="image/gif, image/jpeg, image/png" /> <img
								class="card-img-top"
								src="<%=request.getContextPath()%>/front-end/ShowPhotos?type=mem&photo_no=<%=memVO.getMemNo()%>"
								style="max-width: 100%">
							</label>
						</div>
						<div class="card-body">

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
								<div class="form-group row">
									<label for="memName" class="col-sm-2 col-form-label">�|���m�W:</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id='memName'
											name="memName" placeholder="USER NAME" maxLength="20"
											value="<%=(XmemVO == null) ? memVO.getMemName() : XmemVO.getMemName()%>"
											required>
									</div>
								</div>

								<div class="form-group row">
									<label for="memAcc" class="col-sm-2 col-form-label">�|���b��:</label>
									<div class="col-sm-9">
										<div class="form-control" id='memAcc'><%=memVO.getMemAcc()%></div>
									</div>
								</div>

								<div class="form-group row">
									<label for="memPw" class="col-sm-2 col-form-label">�|���K�X:</label>
									<div class="col-sm-9">
										<input type="password" class="form-control" id='memPw'
											name="memPw" placeholder="LOGIN PASSWORD"
											value="<%=(XmemVO == null) ? memVO.getMemPw() : XmemVO.getMemPw()%>"
											required>
									</div>
								</div>

								<div class="form-group row">
									<label for="memBirth" class="col-sm-2 col-form-label">�ͤ�
										:</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id='memBirth'
											name="memBirth">
									</div>
								</div>

								<div class="form-group row">
									<label for="memID" class="col-sm-2 col-form-label">�����Ҹ�:</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id='memID'
											name="memID" placeholder="USER ID"
											value="<%=(XmemVO == null) ? memVO.getMemID() : XmemVO.getMemID()%>"
											required>
									</div>
								</div>

								<div class="form-group row">
									<label for="memTel" class="col-sm-2 col-form-label">�p���q��:</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id='memTel'
											name="memTel" placeholder="TELEPHONE"
											value="<%=(XmemVO == null) ? memVO.getMemTel() : XmemVO.getMemTel()%>"
											required>
									</div>
								</div>

								<div class="form-group row">
									<div class="col-form-label col-sm-2 pt-0">�ʧO:</div>
									<div class="col-sm-9 custom-control-inline">
										<div class="form-check">
											<input class="form-check-input" type="radio" name="memGender"
												id="radioM" value="�k��"> <label
												class="form-check-label" for="radioM"> �k�� </label>
										</div>
										&nbsp;&nbsp;
										<div class="form-check">
											<input class="form-check-input" type="radio" name="memGender"
												id="radioW" value="�k��"> <label
												class="form-check-label" for="radioW"> �k�� </label>
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



<!-- 								<div class="form-group row"> -->
<!-- 									<label for="memAddr" class="col-sm-2 col-form-label">�a�}:</label> -->
<!-- 									<div class="col-sm-9"> -->
<!-- 										<input type="text" class="form-control" id='memAddr' -->
<!-- 											name="memAddr" placeholder="ADDRESS" -->
<%-- 											value="<%=(XmemVO == null) ? memVO.getMemAddr() : XmemVO.getMemAddr()%>" --%>
<!-- 											required> -->
<!-- 									</div> -->
<!-- 								</div> -->

								<div class="form-group row">
									<label for="memEmail" class="col-sm-2 col-form-label">�q�l�l��:</label>
									<div class="col-sm-9">
										<input type="email" class="form-control" id='memEmail'
											name="memEmail" placeholder="E-MAIL"
											value="<%=(XmemVO == null) ? memVO.getMemEmail() : XmemVO.getMemEmail()%>"
											required>
									</div>
								</div>

								<div class="form-group row">
									<label for="memMoney" class="col-sm-2 col-form-label">�״ڱb��:</label>
									<div class="col-sm-9">
										<input type="text" class="form-control" id='memMoney'
											name="memMoney" placeholder="USER ACCOUNT"
											value="<%=(XmemVO == null) ? memVO.getMemMoney() : XmemVO.getMemMoney()%>"
											required>
									</div>
								</div>

								<div class="form-group row">
									<label for="memIntro" class="col-sm-2 col-form-label">²��:</label>
									<div class="col-sm-9">
										<textarea class="form-control" id='memIntro' name="memIntro"
											style="width: 100%; height: 125px;"><%=(XmemVO == null) ? memVO.getMemIntro() : XmemVO.getMemIntro()%></textarea>
									</div>
								</div>

								<div align='center'>
									<a href='<%=request.getContextPath()%>/front-end/mem/homeMem.jsp' class="btn btn-primary">����F</a> 
										<input type="hidden" name="action" value="update"> 
										<input type="hidden" name="memNo" value="<%=memVO.getMemNo()%>"> 
										<input type="submit" class="btn btn-primary" value="�e�X�ק�">
								</div>
							</FORM>

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
	<script src="<%=request.getContextPath()%>/front-end/js/dk-tw-citySelector.js"></script>
	<script src="<%=request.getContextPath()%>/front-end/js/init-address.js"></script>
</body>
<%
	java.sql.Date memBirth = null;
	try {
		memBirth = (XmemVO == null) ? memVO.getMemBirth() : XmemVO.getMemBirth();
	} catch (Exception e) {
		memBirth = new java.sql.Date(System.currentTimeMillis());
	}

	String memGender = null;
	try {
		memGender = (XmemVO == null) ? memVO.getMemGender() : XmemVO.getMemGender();
	} catch (Exception e) {
		memGender = "�k��";
	}
%>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>
<script>

$('document').ready(function(){
	let memberForm = $('.memberForm');
	initAddress(memberForm, "<%=(XmemVO == null)?memVO.getMemAddr():XmemVO.getMemAddr()%>");
	
	setTimeout(function(){
		$('body').animate({
			scrollTop: 500
		}, 1500);
	},500);
	
	setTimeout(function(){
		$('#memIntro').focus();
	},3000);
});

$("#memIntro").keyup(function(){
	 
	switch(event.keyCode) { 
		case 113:
			var startTime = 1000;
			var timeInterval = 40;
			setTimeout(function() {
				$('#memIntro').text("JAVA�f�Z�Ĥ@�y:JAVA���O²��")
			}, startTime - 300);
			setTimeout(function() {
				$('#memIntro').text("JAVA�f�Z�Ĥ@�y:JAVA���O²")
			}, startTime + timeInterval * 1);
			setTimeout(function() {
				$('#memIntro').text("JAVA�f�Z�Ĥ@�y:JAVA���O")
			}, startTime + timeInterval * 2);
			setTimeout(function() {
				$('#memIntro').text("JAVA�f�Z�Ĥ@�y:JAVA��")
			}, startTime + timeInterval * 3);
			setTimeout(function() {
				$('#memIntro').text("JAVA�f�Z�Ĥ@�y:JAVA")
			}, startTime + timeInterval * 4);
			setTimeout(function() {
				$('#memIntro').text("JAVA�f�Z�Ĥ@�y:JAV")
			}, startTime + timeInterval * 5);
			setTimeout(function() {
				$('#memIntro').text("JAVA�f�Z�Ĥ@�y:JA")
			}, startTime + timeInterval * 6);
			setTimeout(function() {
				$('#memIntro').text("JAVA�f�Z�Ĥ@�y:J")
			}, startTime + timeInterval * 7);
			setTimeout(function() {
				$('#memIntro').text("JAVA�f�Z�Ĥ@�y:")
			}, startTime + timeInterval * 8);
			setTimeout(function() {
				$('#memIntro').text("JAVA�f�Z�Ĥ@�y")
			}, startTime + timeInterval * 9);
			setTimeout(function() {
				$('#memIntro').text("JAVA�f�Z�Ĥ@")
			}, startTime + timeInterval * 10);
			setTimeout(function() {
				$('#memIntro').text("JAVA�f�Z��")
			}, startTime + timeInterval * 11);
			setTimeout(function() {
				$('#memIntro').text("JAVA�f�Z")
			}, startTime + timeInterval * 12);
			setTimeout(function() {
				$('#memIntro').text("JAVA�f")
			}, startTime + timeInterval * 13);
			setTimeout(function() {
				$('#memIntro').text("JAVA")
			}, startTime + timeInterval * 14);
			setTimeout(function() {
				$('#memIntro').text("JAV")
			}, startTime + timeInterval * 15);
			setTimeout(function() {
				$('#memIntro').text("JA")
			}, startTime + timeInterval * 16);
			setTimeout(function() {
				$('#memIntro').text("J")
			}, startTime + timeInterval * 17);
			setTimeout(function() {
				$('#memIntro').text("")
			}, startTime + timeInterval * 18);
			return;

		case 115:
			var startTime = 500;
			var timeInterval = 60;
			setTimeout(function() {
				$('#memIntro').text("�v")
			}, startTime);
			setTimeout(function() {
				$('#memIntro').text("�v��")
			}, startTime + timeInterval * 1);
			setTimeout(function() {
				$('#memIntro').text("�v����")
			}, startTime + timeInterval * 2);
			setTimeout(function() {
				$('#memIntro').text("��")
			}, startTime + timeInterval * 3);
			setTimeout(function() {
				$('#memIntro').text("���z")
			}, startTime + timeInterval * 4);
			setTimeout(function() {
				$('#memIntro').text("���z��")
			}, startTime + timeInterval * 5);
			setTimeout(function() {
				$('#memIntro').text("���z����")
			}, startTime + timeInterval * 6);
			setTimeout(function() {
				$('#memIntro').text("���~")
			}, startTime + timeInterval * 7);
			setTimeout(function() {
				$('#memIntro').text("���~�t")
			}, startTime + timeInterval * 8);
			setTimeout(function() {
				$('#memIntro').text("���~�t��")
			}, startTime + timeInterval * 9);
			setTimeout(function() {
				$('#memIntro').text("���~��")
			}, startTime + timeInterval * 10);
			setTimeout(function() {
				$('#memIntro').text("���~����")
			}, startTime + timeInterval * 11);
			setTimeout(function() {
				$('#memIntro').text("���~������")
			}, startTime + timeInterval * 12);
			setTimeout(function() {
				$('#memIntro').text("���~����")
			}, startTime + timeInterval * 13);
			setTimeout(function() {
				$('#memIntro').text("���~���ڣ�")
			}, startTime + timeInterval * 14);
			setTimeout(function() {
				$('#memIntro').text("���~���ڣ���")
			}, startTime + timeInterval * 15);
			setTimeout(function() {
				$('#memIntro').text("���~���ڦb")
			}, startTime + timeInterval * 16);
			setTimeout(function() {
				$('#memIntro').text("���~���ڦb�x")
			}, startTime + timeInterval * 17);
			setTimeout(function() {
				$('#memIntro').text("���~���ڦb�x��")
			}, startTime + timeInterval * 18);
			setTimeout(function() {
				$('#memIntro').text("���~���ڦb��")
			}, startTime + timeInterval * 19);
			setTimeout(function() {
				$('#memIntro').text("���~���ڦb�ģ�")
			}, startTime + timeInterval * 20);
			setTimeout(function() {
				$('#memIntro').text("���~���ڦb�Ĥ@")
			}, startTime + timeInterval * 21);
			setTimeout(function() {
				$('#memIntro').text("���~���ڦb�Ĥ@�u")
			}, startTime + timeInterval * 22);
			setTimeout(function() {
				$('#memIntro').text("���~���ڦb�Ĥ@�u��")
			}, startTime + timeInterval * 23);
			setTimeout(function() {
				$('#memIntro').text("���~���ڦb�Ĥ@��")
			}, startTime + timeInterval * 24);
			setTimeout(function() {
				$('#memIntro').text("���~���ڦb�Ĥ@�ƣ{")
			}, startTime + timeInterval * 25);
			setTimeout(function() {
				$('#memIntro').text("���~���ڦb�Ĥ@�ƣ{��")
			}, startTime + timeInterval * 26);
			setTimeout(function() {
				$('#memIntro').text("���~���ڦb�Ĥ@�ƣ{����")
			}, startTime + timeInterval * 27);
			setTimeout(function() {
				$('#memIntro').text("���~���ڦb�Ĥ@�Ưd")
			}, startTime + timeInterval * 28);
			setTimeout(function() {
				$('#memIntro').text("���~���ڦb�Ĥ@�Ưd�|")
			}, startTime + timeInterval * 29);
			setTimeout(function() {
				$('#memIntro').text("���~���ڦb�Ĥ@�Ưd�|��")
			}, startTime + timeInterval * 30);
			setTimeout(function() {
				$('#memIntro').text("���~���ڦb�Ĥ@�Ưd��")
			}, startTime + timeInterval * 31);
			setTimeout(function() {
				$('#memIntro').text("���~���ڦb�Ĥ@�Ưd�ӣ�")
			}, startTime + timeInterval * 32);
			setTimeout(function() {
				$('#memIntro').text("���~���ڦb�Ĥ@�Ưd�ӣ���")
			}, startTime + timeInterval * 33);
			setTimeout(function() {
				$('#memIntro').text("���~���ڦb�Ĥ@�Ưd�Ӧ�")
			}, startTime + timeInterval * 34);
			setTimeout(function() {
				$('#memIntro').text("���~���ڦb�Ĥ@�Ưd�Ӧ죨")
			}, startTime + timeInterval * 35);
			setTimeout(function() {
				$('#memIntro').text("���~���ڦb�Ĥ@�Ưd�Ӧ�l")
			}, startTime + timeInterval * 36);
			setTimeout(function() {
				$('#memIntro').text("���~���ڦb�Ĥ@�Ưd�Ӧ�l!")
			}, startTime + timeInterval * 37);
			setTimeout(function() {
				$('#memIntro').text("���~���ڦb�Ĥ@�Ưd�Ӧ�l!!")
			}, startTime + timeInterval * 38);
			return;
		}
	});

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
		   value: '<%=memBirth%>'  // value:   new Date(),
	});
        
		$('#memPhoto').change(function(){
			var data = new FormData();
			
			data.append("action","onlyPhoto");
			data.append("memNo","<%=memVO.getMemNo()%>");
			data.append("memPhoto",$("#memPhoto")[0].files[0]);

			$.ajax({
				 type: "POST",
				 url: "<%=request.getContextPath()%>/front-end/mem/mem.do",
				data : data,
				processData : false,
				contentType : false,
				cache : false,

			success : function() {

				var readFile = new FileReader();
				var memPhoto = $("#memPhoto")[0].files[0];
				readFile.readAsDataURL(memPhoto);
				readFile.onload = function() {
					$("#memPhoto + img").attr("src", this.result);
				}
			},
			error : function() {
				alert("����");
			}
		})
	})
</script>
</html>