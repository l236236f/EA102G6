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
    List<UprodVO> list = uprodSvc.getAllBySellerDetail(memVO.getMemNo());
    pageContext.setAttribute("list",list);
%>
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

<h4>�����m�߱ĥ� EL ���g�k����:</h4>
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
		<th>�R�a�s��</th>
		<th>�ӫ~�W��</th>
		<th>�ӫ~���</th>
		<th>�q��B�z���A</th>
		<th>�T�{�⦬�ӫ~</th>
		<th>�q�榨�߮ɶ�</th>
		<th>�H�e�覡</th>
		<th>�H�e�a�}</th>
		
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="uprodVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${uprodVO.prodNo}</td>
			<td>${uprodVO.sellerNo}</td>
			<td>${uprodVO.custNo}</td>
		    <td>${uprodVO.prodName}</td>
		    <td>${uprodVO.price}</td>
		    
		    <c:if test="${uprodVO.orderStatus=='OS0'}">
		    <td>�q��B�z��</td>
		    </c:if>
		    
		    <c:if test="${uprodVO.orderStatus=='OS1'}">
		    <td>�w�X�f</td>
		    </c:if>
		    
            <c:if test="${uprodVO.receiveStatus=='RS0'}">
		    <td>�|������f</td>
		    </c:if>
		    
		    <c:if test="${uprodVO.receiveStatus=='RS1'}">
		    <td>�w�⦬�ӫ~</td>
		    </c:if>

		    <td><fmt:formatDate value="${uprodVO.orderTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            
            <c:if test="${uprodVO.tranMethod=='TM0'}">
		    <td>�W�Ө��f</td>
		    </c:if>
		    
		    <c:if test="${uprodVO.tranMethod=='TM1'}">
		    <td>�v�t</td>
		    </c:if>
		    
            <c:if test="${uprodVO.tranMethod=='TM2'}">
		    <td>����</td>
		    </c:if>
		  
<%--             <td>${uprodVO.tranMethod}</td> --%>
		    <td>${uprodVO.tranAddr}</td>

		    <td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/uprod/uprod.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�T�{�X�f">
			     <input type="hidden" name="ProdNo"  value="${uprodVO.getProdNo()}">
			     <input type="hidden" name="action"	value="shipment"></FORM>
			</td>
			
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>
<%@ include file="/front-end/Footer/footer.jsp" %>
</body>
</html>