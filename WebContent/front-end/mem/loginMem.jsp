<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/front-end/Header/header.jsp" %>

<html>
<head>
<meta charset="BIG5">
<title>會員登入</title>
<style type="text/css">

a:hover, a:focus {
	text-decoration: none;
	outline: none;
	color: blue;
}

#bgImage{ 
	width:100%;
	height:100%;
	background-image:url(<%= request.getContextPath()%>/front-end/mem/images/loginMem.jpeg);
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
#memAcc, #memPw {
  width: 100%;
  padding: 15px;
  margin: 5px 0 22px 0;
  border: none;
  border-radius: 5px;
  background: #f1f1f1;
}

#memAcc:focus, #memPw:focus {
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
				<font style="color: red">請修正以下錯誤:</font>
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color: red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>
			<FORM id='loginForm' METHOD="post"
				ACTION="<%=request.getContextPath()%>/front-end/mem/mem.do"
				name="form1" enctype="multipart/form-data">


				<input type="TEXT" id='memAcc' name="memAcc" placeholder='輸入帳號' size="40"><br> 
				<input type="password" id='memPw' name="memPw" placeholder='輸入密碼' size="40"><br> 
				
				<input type="hidden" name="action" value="login"> 
				<input id='loginButton' type="submit" value="登入">
				<hr>
				<div align='center'>
					<a href='#'>忘記密碼&nbsp;&nbsp;</a><b class="text-black-50">/</b> <a href='<%=request.getContextPath()%>/front-end/mem/registerMem.jsp'>&nbsp;建立新帳號</a>
				</div>
			</FORM>

		</div>
		<input id='NEW' type="button" class="btn btn-secondary" value="NEW">
		<input id='M013' type="button" class="btn btn-secondary" value="M013">
	</div>

	<%@ include file="/front-end/Footer/footer.jsp"%>
	<c:if test="${LoginMem!=null}">
		<%@ include file="/chatRoom/chatRoom.jsp"%>
	</c:if>
</body>
<script>
	$("#NEW").click(function(){
		$("#memAcc").val("EA102G6");
		$("#memPw").val("EA102G6");
	}); 
	$("#M013").click(function(){
		$("#memAcc").val("M013");
		$("#memPw").val("1234");
	}); 
</script>
</html>