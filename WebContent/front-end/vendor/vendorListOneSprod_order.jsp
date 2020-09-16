<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.sprod_order.model.*"%>
<%@ page import="com.sprod_detail.model.*" %>
<%@ page import="com.shop_product.model.*" %>

<%
	Sprod_orderVO sprodOrderVO = (Sprod_orderVO) request.getAttribute("sprodOrderVO");
	Sprod_detailJNDIDAO dao = new Sprod_detailJNDIDAO();
	Shop_productJNDIDAO shopProductdao = new Shop_productJNDIDAO();

	
	/**用訂單編號去查所有訂單明細**/
	List<Sprod_detailVO> list = dao.findOneOrderDetail(sprodOrderVO.getOrderNo());
	pageContext.setAttribute("list", list);
	
%>


<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
<title>廠商訂單資料</title>

</head>

<body id="page-top">

  <!-- Page Wrapper -->
  <div id="wrapper">

    <!-- Sidebar -->
    <%@ include file="LHSBar.jsp" %>
    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

      <!-- Main Content -->
      <div id="content">

        <!-- Topbar -->
        <%@ include file="topBar.jsp" %>

        <!-- Begin Page Content -->
        <div class="container-fluid" style="background-image: linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%);">
<!-- ==================================================網頁內容開始======================================================== -->
<div class="page-header">
	<h1>廠商訂單資料
		<small><a href="vendorSelectSprodOrderHome.jsp" style="background-image: linear-gradient(to right, #4facfe 0%, #00f2fe 100%);">回上頁</a></small>	
	</h1>
</div>

<table class="table table-striped">
  <thead>
    <tr>
     	<th scope="col"><div style="width: 70px;"><p class="text-center">訂單編號</p></div></th>
		<th scope="col"><div style="width: 70px;"><p class="text-center">會員編號</p></div></th>
		<th scope="col"><p class="text-center">寄送方式</p></th>
		<th scope="col"><div style="width: 70px;"><p class="text-center">收件人姓名</p></div></th>
		<th scope="col"><div style="width: 70px;"><p class="text-center">收件人地址</p></div></th>
		<th scope="col"><p class="text-center">收件人電子信箱</p></th>
		<th scope="col"><div style="width: 70px;"><p class="text-center">總金額</p></div></th>
		<th scope="col"><div style="width: 70px;"><p class="text-center">訂單狀態</p></div></th>
    </tr>
  </thead>
  <tbody>
    <tr>
      	<th scope="row"><div style="width: 70px;"><p class="text-center">${sprodOrderVO.orderNo}</p></div></th>
		<td><div style="width: 70px;"><p class="text-center">${sprodOrderVO.memNo}</p></div></td>
		<td><p class="text-center">
			<c:choose>
				<c:when test="${sprodOrderVO.tranMethod == '1'}">
					<p>超商取貨</p>
				</c:when>
				<c:when test="${sprodOrderVO.tranMethod == '2'}">
					<p>宅配</p>
				</c:when>
			</c:choose>	
		</p></td>
		<td><div style="width: 70px;"><p class="text-center">${sprodOrderVO.addresseeName}</p></div></td>
		<td><div style="width: 70px;"><p class="text-center">${sprodOrderVO.tranAdd}</p></div></td>
		<td><p class="text-center">${sprodOrderVO.addresseeMail}</p></td>
		<td><div style="width: 70px;"><p class="text-center">${sprodOrderVO.orderTotal}</p></div></td>
		<td><div style="width: 70px;"><p class="text-center">
			<c:choose>
					<c:when test="${sprodOrderVO.orderStatus == '0'}">
						<p>未出貨</p>
					</c:when>
					<c:when test="${sprodOrderVO.orderStatus == '1'}">
						<p>已出貨</p>
					</c:when>
					<c:when test="${sprodOrderVO.orderStatus == '2'}">
						<p>完成(已到貨)</p>
					</c:when>
					<c:when test="${sprodOrderVO.orderStatus == '3'}">
						<p>取消</p>
					</c:when>
			</c:choose>
		</p></div></td>
    </tr>
  </tbody>
</table>
	
	<table>
		<tr>
			<th>產品編號</th>
			<th>購買數量</th>
			<th>評價分數</th>
			<th>評價內容</th>
		</tr>	
		<c:forEach var="Sprod_detailVO" items="${list}">
		<tr>
			<td>${Sprod_detailVO.prodNo}</td>
			<td>${Sprod_detailVO.quantity}</td>
			<td>
					<c:choose>
						<c:when test="${Sprod_detailVO.evaStar == '1'}">
							<p>1分</p>
						</c:when>
						<c:when test="${Sprod_detailVO.evaStar == '2'}">
							<p>2分</p>
						</c:when>
						<c:when test="${Sprod_detailVO.evaStar == '3'}">
							<p>3分</p>
						</c:when>
						<c:when test="${Sprod_detailVO.evaStar == '4'}">
							<p>4分</p>
						</c:when>
						<c:when test="${Sprod_detailVO.evaStar == '5'}">
							<p>5分</p>
						</c:when>
					</c:choose>	
			</td>
			<td>${Sprod_detailVO.evaCont}</td>
		</tr>
	</c:forEach>
	</table>
<style>
  table#table-1 {
	background-color: #CCCCFF;
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

<!-- ==================================================網頁內容結束======================================================== -->
        </div>
        <!-- /.container-fluid -->
      </div>
      <!-- End of Main Content -->
      <!-- Footer -->
      <%@ include file="footer.jsp" %>
      <%@ include file="/chatRoom/chatRoom.jsp"%>
    </div>
    <!-- End of Content Wrapper -->
  </div>
  <!-- End of Page Wrapper -->

  <!-- Scroll to Top Button-->
  <a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
  </a>

</body>
</html>