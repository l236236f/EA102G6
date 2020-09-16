<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.uprod.model.*"%>

<html>
<head>
<%@ include file="/front-end/Header/header.jsp" %>
<%
  UprodVO uprodVO = (UprodVO) request.getAttribute("uprodVO"); //EmpServlet.java (Concroller) �s�Jreq��empVO���� (�]�A�������X��empVO, �]�]�A��J��ƿ��~�ɪ�empVO����)
%>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>�ӫ~��ƭק� - update_uprod_input.jsp</title>

<style>
  table#table-1 {
	background-color: orange;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr>
			<td>
				
				<h4>
					<a href="usedProduct.jsp">��^�G�⥫��</a>
				</h4>
			</td>
		</tr>
</table>

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

<FORM METHOD="post" ACTION="uprod.do" name="form1" enctype="multipart/form-data" >
<table>
	<tr>
		<td>�ӫ~�s��:<font color=red><b>*</b></font></td>
		<td><%=uprodVO.getProdNo()%></td>
	</tr>
	<tr>
		<td>�ӫ~�W��:</td>
		<td><input type="TEXT" name="ProdName" size="45" value="<%=uprodVO.getProdName()%>" /></td>
	</tr>
	
	<tr>
<!-- 		<td>�ӫ~���e:</td> -->
<%-- 		<td><input type="TEXT" name="ProdIntro" size="45" value="<%=uprodVO.getProdIntro()%>" /></td> --%>
	<td>�ӫ~���e:</td>
				
			    <td><textarea name="ProdIntro" rows="5" cols="30" wrap="off"
				 >${uprodVO.prodIntro} </textarea>
				</td>
	
	</tr>
	<tr>
		<td>����:</td>
		<td><input type="TEXT" name="Price" size="45"	value="<%=uprodVO.getPrice()%>" /></td>
	</tr>
	
	<tr>
		<td>�ӫ~����:</td>
<%-- 		<td><input type="TEXT" name="ProdType" size="45"	value="<%=uprodVO.getProdType()%>" /></td> --%>
	
	  <td>
		           <select name="ProdType" size="1">
		           <option value=A selected>��ĥ뭹</option>
		           <option value=B >��`�Ϋ~ </option>
		           <option value=C>�M��Ϋ~ </option>
		           </select>
		           </td>
	</tr>
	<tr>
			    <td>�ӫ~�Ӥ�</td>
			    <td><input type='file' name="Photo" /> </td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="ProdNo" value="<%=uprodVO.getProdNo()%>" />
<input type="submit" value="�e�X�ק�">
</FORM>
<%@ include file="/front-end/Footer/footer.jsp" %>
</body>
</html>