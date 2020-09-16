<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.uprod.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>




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
					<a href="usedProduct.jsp">返回二手市集</a>
				</h4>
			</td>
		</tr>
</table>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	<tr>
		<th>二手商品編號</th>
		<th>賣家編號</th>
<!-- 		<th>買家編號</th> -->
		<th>商品名稱</th>
		<th>商品介紹</th>
		<th>商品單價</th>
		<th>上架時間</th>
		<th>二手商品狀態</th>
<!-- 		<th>訂單處理狀態</th> -->
<!-- 		<th>確認領收商品</th> -->
<!-- 		<th>訂單成立時間</th> -->
<!-- 		<th>寄送方式</th> -->
<!-- 		<th>寄送地址</th> -->
		<th>商品種類</th>
		<th>商品圖片</th>
		
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
		  
		    <td>上架中</td>
		    
<%-- 		    <td>${uprodVO.orderStatus}</td> --%>
<%-- 		    <td>${uprodVO.receiveStatus}</td> --%>
<%-- 		    <td><fmt:formatDate value="${uprodVO.orderTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td> --%>
<%--             <td>${uprodVO.tranMethod}</td> --%>
<%-- 		    <td>${uprodVO.tranAddr}</td> --%>
		 
		    
		    
		    <c:if test="${uprodVO.prodType=='A'}">
		    <td><h8>毛孩伙食</h8></td>
		    </c:if>
		    
		     <c:if test="${uprodVO.prodType=='B'}">
				    <td><h8>日常用品</h8></td>
		    </c:if>
		    
		     <c:if test="${uprodVO.prodType=='C'}">
				    <td><h8>清潔用品</h8></td>
		    </c:if>
		    
		    
		    
		    <td>
			<img class="pics" src="<%=request.getContextPath()%>/front-end/uprod/showpic.do?ProdNo=${uprodVO.getProdNo()}" />
            </td>		
		    <td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/uprod/uprod.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
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