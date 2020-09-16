<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ page import="java.util.* , shopping.Sprod" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.sprod_order.model.*"%>
<%@ page import="com.sprod_detail.model.*"%>
<%@ page import="java.util.Date" %>

<%
  Sprod_orderVO sprodOrderVO = (Sprod_orderVO) request.getAttribute("sprodOrderVO");
%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">



<title>付款成功</title>
<%@ include file="/front-end/Header/header.jsp" %>

</head>
<body>
<div style="background-image: url('img/bg-img/bgPicture.jpg'); background-size: 2000px 700px; height: 500px">

<script defer="defer">
swal("訂單已成立","","success");
</script>

<br><br><br><br>

<div class="container" style="text-align:center;">
	<div class="page-header">
		<h2>謝謝您的消費</h2>
	</div>
</div>

<br><br>

<table class="table table-bordered table-dark" style="width:30%;  text-align: center; margin: auto; ">
  <thead>
    <tr>
      <th scope="col">訂單編號</th>
      <th scope="col"><%=sprodOrderVO.getOrderNo()%></th>
      
    </tr>
  </thead>
  <tbody>
    <tr>
      <th scope="row">訂單金額</th>
      <td><%=sprodOrderVO.getOrderTotal()%></td>
    </tr>
  </tbody>
</table>
</div>
<%@ include file="/front-end/Footer/footer.jsp" %>
<%@ include file="/chatRoom/chatRoom.jsp"%>
</body>

<style>
<!-- JS, Popper.js, and jQuery -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
</style>

</html>