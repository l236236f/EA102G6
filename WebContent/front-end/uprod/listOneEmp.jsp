<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.uprod.model.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>



<html>
<head>
<%@ include file="/front-end/Header/header.jsp" %>
<%
	UprodVO uprodVO = (UprodVO) request.getAttribute("uprodVO"); //EmpServlet.java(Concroller), �s�Jreq��empVO����
%>
<style>
table#table-1 {
	background-color:orange;
	border: 2px ;
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
	width: 600px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 5px;
	text-align: center;
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
    

    <div>
    <table>
    <tr>
    <td><h6>�G��ӫ~�s��:${uprodVO.prodNo}</h6></td>
    </tr>
    <tr>
    <td><h6>��a�s��:${uprodVO.sellerNo}</h6></td>
    </tr>	
    <tr>
    <td><h6>�ӫ~�W��:${uprodVO.prodName}</h6></td>
    </tr>	
    <tr>
    <c:if test="${uprodVO.prodType=='A'}">
		    <td><h6>�ӫ~����:��ĥ뭹</h6></td>
    </c:if>
    
     <c:if test="${uprodVO.prodType=='B'}">
		    <td><h6>�ӫ~����:��`�Ϋ~</h6></td>
    </c:if>
    
     <c:if test="${uprodVO.prodType=='C'}">
		    <td><h6>�ӫ~����:�M��Ϋ~</h6></td>
    </c:if>
    </tr>	
    <tr>
    <td><h6>�ӫ~����:${uprodVO.prodIntro}</h6></td>
    </tr>	
    <tr>
    <td><h6>�ӫ~���:${uprodVO.price}</h6></td>
    </tr>	
    <tr>
    <td><h6>�W�[�ɶ�:<fmt:formatDate value="${uprodVO.increaseTime}" pattern="yyyy-MM-dd HH:mm:ss" /></h6></td>
    </tr>		
    <tr>
    <td><img class="pics" src="<%=request.getContextPath()%>/front-end/uprod/showpic.do?ProdNo=${uprodVO.getProdNo()}" /></td>
    </tr>
    </table>

<!-- 	<table> -->
<!-- 		<tr> -->
<!-- 		<th>�G��ӫ~�s��</th> -->
<!-- 		<th>��a�s��</th> -->
<!-- 		<th>�R�a�s��</th> -->
<!-- 		<th>�ӫ~�W��</th> -->
<!-- 		<th>�ӫ~����</th> -->
<!-- 		<th>�ӫ~���</th> -->
<!-- 		<th>�W�[�ɶ�</th> -->
<!-- 		<th>�G��ӫ~���A</th> -->
<!-- 		<th>�q��B�z���A</th> -->
<!-- 		<th>�T�{�⦬�ӫ~</th> -->
<!-- 		<th>�q�榨�߮ɶ�</th> -->
<!-- 		<th>�H�e�覡</th> -->
<!-- 		<th>�H�e�a�}</th> -->
<!-- 		<th>�ӫ~����</th> -->
<!-- 		<th>�ӫ~�������P��</th> -->
<!-- 		<th>�ӫ~�Ϥ�</th> -->
<!-- 		</tr> -->
<!-- 		<tr> -->
<%-- 			<td>${uprodVO.prodNo}</td> --%>
<%-- 			<td>${uprodVO.sellerNo}</td> --%>
<%-- 			<td>${uprodVO.custNo}</td> --%>
<%-- 			<td>${uprodVO.prodName}</td> --%>
<%-- 			<td>${uprodVO.prodIntro}</td> --%>
<%-- 			<td>${uprodVO.price}</td> --%>
<%-- 			<td><fmt:formatDate value="${uprodVO.increaseTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td> --%>
<%-- 			<td>${uprodVO.prodStatus}</td> --%>
<%-- 			<td>${uprodVO.orderStatus}</td> --%>
<%-- 			<td>${uprodVO.receiveStatus}</td> --%>
<%-- 			<td><fmt:formatDate value="${uprodVO.orderTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td> --%>
<%-- 			<td>${uprodVO.tranMethod}</td> --%>
<%-- 			<td>${uprodVO.tranAddr}</td> --%>
<%-- 			<td>${uprodVO.prodType}</td> --%>
<%-- 			<td>${uprodVO.evaStar}</td> --%>
<!-- 			<td> -->
<%-- 			<img class="pics" src="<%=request.getContextPath()%>/front-end/uprod/showpic.do?ProdNo=${uprodVO.getProdNo()}" /> --%>
<!--             </td> -->
<!-- 		</tr> -->
<!-- 	</table> -->
<%@ include file="/front-end/Footer/footer.jsp" %>
</body>
</html>