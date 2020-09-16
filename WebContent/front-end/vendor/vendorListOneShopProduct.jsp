<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ page import="com.shop_product.model.*"%>

<%
  Shop_productVO shopProductVO = (Shop_productVO) request.getAttribute("shopProductVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>


<html>
<head>
<!-- CSS only -->
<!-- bootstrap -v 4.5.2 -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">

  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
<title>商品資料</title>

<style>
  h4 {
    color: blue;
    display: inline;
  }
</style>

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
	<h1>商品資料
		<small><a href="vendorSelectProductHome.jsp" style="background-image: linear-gradient(to right, #4facfe 0%, #00f2fe 100%);">回首頁</a></small>	
	</h1>
</div>

<table class="table table-striped">
  <thead>
    <tr>
     	<th scope="col"><div style="width: 70px;"><p class="text-center">商品編號</p></div></th>
		<th scope="col"><div style="width: 70px;"><p class="text-center">商品分類</p></div></th>
		<th scope="col"><div style="width: 70px;"><p class="text-center">產品名稱</p></div></th>
		<th scope="col"><p class="text-center">產品介紹</p></th>
		<th scope="col"><div style="width: 70px;"><p class="text-center">上架時間</p></div></th>
		<th scope="col"><div style="width: 70px;"><p class="text-center">商品單價</p></div></th>
		<th scope="col"><div style="width: 70px;"><p class="text-center">評價次數</p></div></th>
		<th scope="col"><div style="width: 70px;"><p class="text-center">評價總分</p></div></th>
		<th scope="col"><div style="width: 70px;"><p class="text-center">商品狀態</p></div></th>
    </tr>
  </thead>
  <tbody>
    <tr>
      	<th scope="row"><div style="width: 70px;"><p class="text-center"><%=shopProductVO.getProdNo()%></p></div></th>
		<td><div style="width: 70px;"><p class="text-center"><%=shopProductVO.getClassName()%></p></div></td>
		<td><div style="width: 70px;"><p class="text-center"><%=shopProductVO.getProdName()%></p></div></td>
		<td><p class="text-center"><%=shopProductVO.getProdIntro()%></p></td>
		<td><div style="width: 70px;"><p class="text-center"><%=shopProductVO.getIncreaseTime()%></p></div></td>
		<td><div style="width: 70px;"><p class="text-center"><%=shopProductVO.getPrice()%></p></div></td>
		<td><div style="width: 70px;"><p class="text-center"><%=shopProductVO.getEvCount()%></p></div></td>
		<td><div style="width: 70px;"><p class="text-center"><%=shopProductVO.getEvTotal()%></p></div></td>
		<td><div style="width: 70px;"><p class="text-center">
			<% 
		 	String status = null;		
			Integer S = new Integer(shopProductVO.getSprodStatus());
			int statusnum = S.intValue();
			if(statusnum == 0){
				status = "上架";
			}else if(statusnum == 1){
				status = "下架";
			}else{
				status = "被檢舉下架";
			}
			%>
			<%=status%>
		</p></div></td>
    </tr>
  </tbody>
</table>

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
<!-- JS, Popper.js, and jQuery -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>

</html>