<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<title>Insert title here</title>
<style>
	.irikuji{
		width:450px;
		height:200px;
		background-color:orange;
		margin: 100px auto;
		text-align:center;
	}

</style>
<%
     response.setHeader("Pragma","No-cache"); 
     response.setHeader("Cache-Control","no-cache"); 
     response.setDateHeader("Expires", 0); 
     response.flushBuffer();
 %>
</head>
<body>
<%@ include file="header.jsp" %>  
		<input type="hidden" id="suc" value="${isSuccess}">
		<input type="hidden" id="valc" value="${val}">
		<div class="irikuji">
			<div><h3>EA102�n���C���ϤW�u��</h3></div>
			<div>�п�J�z���j�W</div>
			<form action="Drow.do" method="post">
				<input type="hidden" name="action" value="inLobby">
				<input type="text" name="username">
				<button type="submit">�ڭn�i�h�o!</button>
			</form>
		</div>
		
<%@ include file="footer.jsp" %>
</body>
<script>
	$(document).ready(function(){
		var suc = $("#suc").val();
		var valc = $("#valc").val();
		if(suc){
			swal(valc,'','error');
		}
	});
	
</script>

</html>