<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/front-end/Header/header.jsp"%>

<html>
<head>
<meta charset="BIG5">
<title>�t�ӵn�J</title>
<style type="text/css">

a:hover, a:focus {
	text-decoration: none;
	outline: none;
	color: blue;
}

#bgImage{ 
	width:100%;
	height:100%;
	background-image:url(<%= request.getContextPath()%>/front-end/vendor/images/loginVendor.jpeg);
	background-repeat:no-repeat;
	background-size: cover;
} 

/* Add styles to the form container */
#loginDiv {
  position: absolute;
  margin-top:13%;
  margin-left:11%;
  max-width: 400px;
  padding: 16px;
  border-radius: 8px;
  background-color: white;
  opacity: 0.95;
}

#loginDiv:hover {
  opacity: 1;
}

/* Full-width input fields */
#venAcc, #venPw {
  width: 100%;
  padding: 15px;
  margin: 5px 0 22px 0;
  border: none;
  border-radius: 5px;
  background: #f1f1f1;
}

#venAcc:focus, #venPw:focus {
  background-color: #ddd;
  outline: none;
}

/* Set a style for the submit button */
#loginButton {
  background-color: #4CAF50;
  color: white;
  padding: 16px 20px;
  border: none;
  cursor: pointer;
  width: 100%;
  border-radius: 5px;
  opacity: 0.9;
}

#loginButton:hover {
  opacity: 1;
}

</style>
</head>
<body>
	<div id='bgImage'>
		<div id='loginDiv' class='shadow p-3 mb-5 bg-white rounded'>
			<c:if test="${not empty errorMsgs}">
				<font style="color: red">�Эץ��H�U���~:</font>
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color: red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>
			<FORM id='loginForm' METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/vendor/vendor.do" name="form1" enctype="multipart/form-data">

				<input type="TEXT" id='venAcc' name="venAcc" placeholder='��J�b��' size="40"><br> 
				
				<input type="password" id='venPw' name="venPw" placeholder='��J�K�X' size="40"><br>
				
				<input type="hidden" name="action" value="login"> 
				<input id='loginButton' type="submit" value="�n�J">
				<hr>
				<div align='center'>
					<a href='#'>�ѰO�K�X&nbsp;&nbsp;</a><b class="text-black-50">/</b> 
					<a href='<%=request.getContextPath()%>/front-end/vendor/registerVendor.jsp'>&nbsp;�إ߷s�b��</a>
				</div>
			</FORM>
			
		</div>
		<input id='NEW' type="button" class="btn btn-secondary" value="NEW">
	</div>

	<%@ include file="/front-end/Footer/footer.jsp"%>
	
</body>

<script>
	$("#NEW").click(function(){
		$("#venAcc").val("EA102GGYY");
		$("#venPw").val("EA102GGYY");
	}); 
	
	$("document").ready(function(){
		var success = "<%=(String)request.getAttribute("success")%>";
		if(success === "vendor"){
			swal("���U���\,���Ժ޲z���f�ֳq�L!!!","","success");
		}
	});
</script>
</html>