<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/front-end/Header/header.jsp" %>

<% VendorVO XvendorVO = (VendorVO) request.getAttribute("XvendorVO"); %><!-- 錯的VO -->

<html>
<head>
<meta charset="BIG5">
<title>廠商註冊register</title>
<style type="text/css">

#bgImage{ 
	width:100%;
	height:120%;
	background-image:url(<%= request.getContextPath()%>/front-end/vendor/images/registerVendor.jpeg);
	background-repeat:no-repeat;
	background-size: cover;
} 

#registerDiv{
	position: absolute;
	margin-top:4%;
	margin-left: 20%;
	width: 35%;
  	padding: 16px;
  	border-radius: 8px;
  	background-color: white;
  	opacity: 0.95;
}
/* @media screen and (min-width:1194px) and (max-width:1699px){ */
/*   #registerDiv{ */
/*     width: 50%; */
/*     margin-left:15%; */
/*   } */
/* } */
/* @media screen and (max-width: 1193px){ */
/*   #registerDiv{ */
/*     width: 80%; */
/*     margin-left:5%; */
/*   } */
/*   #bgImage{  */
/* 	height:135%;   */
/*   } */
/* } */

#registerDiv:hover {
 	 opacity: 1;
}

#loadingImage{
	text-align:center;
	margin-bottom:20px;
	height:225px;
}
#venPhoto{
 	display:none;
}

#venPhoto + img{
 	height:225px;
 	border:2px solid #AAA;
 	paddind:2px;
}

#venPhoto + img:hover{
 	border:2px solid #0AF;
}

.form-group{
	margin-bottom:5px;
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
		<div id='registerDiv'>
			<%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">
				<font style="color: red">請修正以下錯誤:</font>
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color: red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>
			<FORM METHOD="post" ACTION="<%= request.getContextPath()%>/front-end/vendor/vendor.do" name="form1" enctype="multipart/form-data">

				<div id='loadingImage'>
					<label for="venPhoto"> 
					<input type="file" id='venPhoto' name="venPhoto" accept="image/gif, image/jpeg, image/png"/> 
					<img src='<%=request.getContextPath()%>/front-end/mem/images/noUser.png'>
					</label>
				</div>

				<div class="form-group row">
					<label for="venName" class="col-sm-2 col-form-label">廠商名稱:</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" id='venName'
							name="venName" placeholder="VENDOR NAME" maxLength="20"
							value="<%= (XvendorVO==null)? "" : XvendorVO.getVenName()%>">
					</div>
				</div>

				<div class="form-group row">
					<label for="venAcc" class="col-sm-2 col-form-label">廠商帳號:</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" id='venAcc' 
							name="venAcc" placeholder="LOGIN ACCOUNT" 
							value="<%= (XvendorVO==null)? "" : XvendorVO.getVenAcc()%>">
					</div>
				</div>
				
				<div class="form-group row">
					<label for="venPw" class="col-sm-2 col-form-label">廠商密碼:</label>
					<div class="col-sm-9">
						<input type="password" class="form-control" id='venPw' 
							name="venPw" placeholder="LOGIN PASSWORD" 
							value="<%= (XvendorVO==null)? "" : XvendorVO.getVenPw()%>">
					</div>
				</div>
			
				<div class="form-group row">
					<label for="venTel" class="col-sm-2 col-form-label">聯絡電話:</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" id='venTel' 
							name="venTel" placeholder="TELEPHONE" 
							value="<%= (XvendorVO==null)? "" : XvendorVO.getVenTel()%>">
					</div>
				</div>
				
				<div class="form-group row">
					<label for="venID" class="col-sm-2 col-form-label">統一編號:</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" id='venID' 
							name="venID" placeholder="VENDOR ID" 
							value="<%= (XvendorVO==null)? "" : XvendorVO.getVenID()%>">
					</div>
				</div>
				
				<div class="form-group row">
					<label for="venMoney" class="col-sm-2 col-form-label">匯款帳戶:</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" id='venMoney' 
							name="venMoney" placeholder="VENDOR ACCOUNT" 
							value="<%= (XvendorVO==null)? "" : XvendorVO.getVenMoney()%>">
					</div>
				</div>
				
<!-- 地址 -->
	<div class="form-group row text-left" style='display:inline-block;'>
        <label for="mem_addr" class="col-sm-2 col-form-label">地址:</label> 
        <div class="col-sm-9" style='display:inline-block;padding-left:10px;'>
        	<ul class="row vendorForm">
              <li class="col-sm-4">
                  <select class="country" name="country" required></select>
              </li>

              <li class="col-sm-4">
                  <select class="district" name="district" required></select>
              </li>

              <li class="col-sm-4">
                   <input type="text" class="zipcode form-control" name="zipcode" placeholder="郵遞區號" readonly />
              </li>

              <li class="col-sm-12">
                  <input type="text" class="form-control detail col-form-label" id="address" name="address" value="" required>
              </li>
        	</ul>
        </div>
    </div>				
				
				
				<div class="form-group row">
					<label for="venEmail" class="col-sm-2 col-form-label">電子郵件:</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" id='venEmail' 
							name="venEmail" placeholder="E-MAIL" 
							value="<%= (XvendorVO==null)? "" : XvendorVO.getVenEmail()%>">
					</div>
				</div>
					
				<div class="form-group row">
    				<label for="venIntro" class="col-sm-2 col-form-label">簡介:</label>
    				<div class="col-sm-9">
     				 	<textarea class="form-control" id='venIntro' name="venIntro" style="width:100%;height:100px;"><%= (XvendorVO==null)? "" : XvendorVO.getVenIntro()%></textarea>
    				</div>
  				</div>
				
				<hr> 
				<div align='center'>
					<input type="hidden" name="action" value="insert">
					<input type="submit"class="btn btn-primary" value="註冊" style='width:20%;height:20%'>
			</div></FORM>
		</div>
	</div>

	<%@ include file="/front-end/Footer/footer.jsp"%>
	<c:if test="${LoginMem!=null}">
		<%@ include file="/chatRoom/chatRoom.jsp"%>
	</c:if>
	<script src="<%=request.getContextPath()%>/front-end/js/dk-tw-citySelector.js"></script>
	<script src="<%=request.getContextPath()%>/front-end/js/init-address.js"></script>
</body>

<script>
window.onload = function(){

	  let vendorForm = $('.vendorForm');
	  initAddress(vendorForm, "<%=(XvendorVO == null)?"":XvendorVO.getVenAddr()%>");
	 };

	$(function() {
        $("#venPhoto").change(function() {
            var readFile = new FileReader();
            var venPhoto = $("#venPhoto")[0].files[0];  //注意這裡必須時$("#myfile")[0]，document.getElementById('file')等價與$("#myfile")[0]
            readFile.readAsDataURL(venPhoto);
            readFile.onload = function() {
                $("#venPhoto + img").attr("src", this.result);
            }
        });
    })
    
    $("input").keyup(function(){ 
    	  switch(event.keyCode) { 
   			case 113:
   				$("#venName").val("機械寵物協會");
        		$("#venAcc").val("EA102GGYY");
        		$("#venPw").val("EA102GGYY");
        		$("#venTel").val("0912345678");
        		$("#venID").val("87654321");
        		$("#venMoney").val("555555555555");
        		$("#venEmail").val("cky771234@gmail.com");
        		$("#venIntro").val("提供大量優質寵物產品,品質保證");
            	initAddress($('.vendorForm'),"110台北市信義區信義路五段7號36樓");
            return;
       	  };  
       });	
        
        	
        	
        	
</script>
</html>