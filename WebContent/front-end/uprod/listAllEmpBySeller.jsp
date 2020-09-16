<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.uprod.model.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>




<html>
<head>
<%@ include file="/front-end/Header/header.jsp" %>
<%
    UprodService uprodSvc = new UprodService();
    List<UprodVO> list = uprodSvc.getAllBySeller(memVO.getMemNo());
    pageContext.setAttribute("list",list);
%>
<style>
  table#table-1 {
	background-color:orange;
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
	width: 800px;
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

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	<tr>
		<th>�G��ӫ~�s��</th>
		<th>��a�s��</th>
<!-- 		<th>�R�a�s��</th> -->
		<th>�ӫ~�W��</th>
		<th>�ӫ~����</th>
		<th>�ӫ~���</th>
		<th>�W�[�ɶ�</th>
		<th>�G��ӫ~���A</th>
<!-- 		<th>�q��B�z���A</th> -->
<!-- 		<th>�T�{�⦬�ӫ~</th> -->
<!-- 		<th>�q�榨�߮ɶ�</th> -->
<!-- 		<th>�H�e�覡</th> -->
<!-- 		<th>�H�e�a�}</th> -->
		<th>�ӫ~����</th>
		<th>�ӫ~�Ϥ�</th>
		
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="uprodVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${uprodVO.prodNo}</td>
			<td>${uprodVO.sellerNo}</td>
<%-- 			<td>${uprodVO.custNo}</td> --%>
		    <td>${uprodVO.prodName}</td>
		    <td>${uprodVO.prodIntro}</td>
		    <td>${uprodVO.price}</td>
		    <td><fmt:formatDate value="${uprodVO.increaseTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		  
		    <td>�W�[��</td>
		    
<%-- 		    <td>${uprodVO.orderStatus}</td> --%>
<%-- 		    <td>${uprodVO.receiveStatus}</td> --%>
<%-- 		    <td><fmt:formatDate value="${uprodVO.orderTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td> --%>
<%--             <td>${uprodVO.tranMethod}</td> --%>
<%-- 		    <td>${uprodVO.tranAddr}</td> --%>
		 
		    
		    
		    <c:if test="${uprodVO.prodType=='A'}">
		    <td><h8>��ĥ뭹</h8></td>
		    </c:if>
		    
		     <c:if test="${uprodVO.prodType=='B'}">
				    <td><h8>��`�Ϋ~</h8></td>
		    </c:if>
		    
		     <c:if test="${uprodVO.prodType=='C'}">
				    <td><h8>�M��Ϋ~</h8></td>
		    </c:if>
		    
		    
		    
		    <td>
			<img class="pics" src="<%=request.getContextPath()%>/front-end/uprod/showpic.do?ProdNo=${uprodVO.getProdNo()}" />
            </td>		
		    <td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/uprod/uprod.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="ProdNo"  value="${uprodVO.getProdNo()}">
			     <input type="hidden" name="update1"  value="2">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>
<%@ include file="/front-end/Footer/footer.jsp" %>
</body>
</html>