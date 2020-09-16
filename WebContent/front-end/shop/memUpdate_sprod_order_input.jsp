<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.sprod_order.model.*"%>
<%@ page import="java.util.*" %>

<%
  Sprod_orderVO sprodOrderVO = (Sprod_orderVO) request.getAttribute("sprodOrderVO"); //EmpServlet.java (Concroller) �s�Jreq��empVO���� (�]�A�������X��empVO, �]�]�A��J��ƿ��~�ɪ�empVO����)
%>



<html>
<%@ include file="/front-end/Header/header.jsp" %>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>���f�T�{</title>

<!-- bootstrap4.5.2 -->
<!-- CSS only -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">

</head>
<body>
<div style="background-image: linear-gradient(to top, #fff1eb 0%, #ace0f9 100%);">

<h1>�|���q�榬�f�T�{</h1>

<h3>��ƭק�:</h3>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="sprod_order.do" name="form1">

<table class="table table-striped">
  <thead>
    <tr>
      <th scope="col">�q��s��</th>
      <th scope="col">�|���s��</th>
      <th scope="col">�q�榨�߮ɶ�</th>
      <th scope="col">�H�e�覡</th>
      <th scope="col">�H�e�a�}</th>
      <th scope="col">����H�m�W</th>
      <th scope="col">����H�H�c</th>
      <th scope="col">�`���B</th>
      <th scope="col">�T�{���f</th>
      <th scope="col">�T�{</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      	<th scope="row"><%=sprodOrderVO.getOrderNo()%></th>
      	<td><%=sprodOrderVO.getMemNo()%></td>
      	<td><%=sprodOrderVO.getOrderTime()%></td>
      	<td>
      		<% 
				String str=null;
				int a = new Integer(sprodOrderVO.getTranMethod());
				if (a == 1){
					str = "�W�Ө��f";
				}else{
					str = "�v�t";
				}
			%>
			<%=str %>
		</td>
      	<td><%=sprodOrderVO.getTranAdd()%></td>
      	<td><%=sprodOrderVO.getAddresseeName()%></td>
      	<td><%=sprodOrderVO.getAddresseeMail()%></td>
      	<td><%=sprodOrderVO.getOrderTotal()%></td>
      	<% if(sprodOrderVO.getOrderStatus()==1){ %>
      	<td>
      			<select size="1" name="orderStatus">
					<option value ="1">�w�X�f(����f)</option>
					<option value ="2">����(�w��f)</option>
					<option value ="3">����</option>
				</select>
		</td>
		<td><input class="btn btn-primary" type="submit" value="�T�{"></td>
		<%} %>
    </tr>

<br>
<input type="hidden" name="orderNo" value="<%=sprodOrderVO.getOrderNo()%>">
<input type="hidden" name="memNo" value="<%=sprodOrderVO.getMemNo()%>">
<input type="hidden" name="orderTime" value="<%=sprodOrderVO.getOrderTime()%>">
<input type="hidden" name="tranMethod" value="<%=sprodOrderVO.getTranMethod()%>">
<input type="hidden" name="tranAdd" value="<%=sprodOrderVO.getTranAdd()%>">
<input type="hidden" name="addresseeName" value="<%=sprodOrderVO.getAddresseeName()%>">
<input type="hidden" name="addresseeMail" value="<%=sprodOrderVO.getAddresseeMail()%>">
<input type="hidden" name="orderTotal" value="<%=sprodOrderVO.getOrderTotal()%>">

<input type="hidden" name="action" value="updateMem">

</tbody>
</table>
</FORM>

<%@ include file="/front-end/Footer/footer.jsp" %>
</div>
</body>
<%@ include file="/chatRoom/chatRoom.jsp"%>
<!-- JS, Popper.js, and jQuery -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
</html>