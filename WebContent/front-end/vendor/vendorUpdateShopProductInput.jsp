<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.shop_product.model.*"%>

<%
  Shop_productVO shopProductVO = (Shop_productVO) request.getAttribute("shopProductVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>

<html>
<head>

  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

<title>商品資料修改</title>

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
	<h1>商品更新
		<small><button type="button" class="btn btn-info"><a href="vendorSelectProductHome.jsp">回上頁</a></button></small>	
	</h1>
</div>

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="shop_product.do" name="form1" enctype="multipart/form-data">

<table>
	<tr>
		<td>商品編號:<font color=red><b>*</b></font></td>
		<td><%=shopProductVO.getProdNo()%></td>
	</tr>
	<tr>
		<td>廠商編號:</td>
		<td><%=shopProductVO.getVenNo()%></td>
	</tr>
	<tr>
		<td>商品分類名稱:</td>
		<td><%=shopProductVO.getClassName()%></td>
	</tr>
	<tr>
		<td>上架時間:</td>
		<td><%=shopProductVO.getIncreaseTime()%></td>
	</tr>
	<tr>
		<td>商品評價人數:</td>
		<td><%=shopProductVO.getEvCount()%></td>
	</tr>
	<tr>
		<td>商品總分:</td>
		<td><%=shopProductVO.getEvTotal()%></td>
	</tr>
</table>
 	<label for="exampleFormControlInput1">商品名稱</label>
    	<input type="TEXT" class="form-control" id="exampleFormControlInput1" placeholder="請輸入您的商品名稱" name="prodName" value="<%=shopProductVO.getProdName()%>">

 	<div class="form-group">
	    <label for="exampleFormControlSelect1">商品分類名稱</label>
	    <select class="form-control" id="exampleFormControlSelect1" style="width:120px;" name="className">
	      	<option value="寵物食品">寵物食品</option>
			<option value="寵物用品">寵物用品</option>
	    </select>
  	</div>

  	<div class="form-group">
	    <label for="exampleFormControlTextarea1">商品介紹</label>
	    <textarea class="form-control" id="exampleFormControlTextarea1" rows="3" name="prodIntro" value="<%=shopProductVO.getProdIntro()%>"><%=shopProductVO.getProdIntro()%></textarea>
  	</div>

	<label for="exampleFormControlInput1">商品單價</label>
    	<input type="TEXT" class="form-control" id="exampleFormControlInput1" placeholder="請輸入您的商品價格" name="price" value="<%= (shopProductVO==null)? "" : shopProductVO.getPrice()%>">
	
	<div class="form-group">
	    <label for="exampleFormControlSelect1">上架狀態</label>
	    <select class="form-control" id="exampleFormControlSelect1" style="width:120px;" name="sprodStatus">
	      	<option value="0">上架</option>
			<option value="1">下架</option>
	    </select>
  	</div>
  	
  	<div>
		<p>現在的圖片</p>
			<p>
				<img src="<%=request.getContextPath()%>/GetPhotos?prodNo=<%=shopProductVO.getProdNo()%>" class="d-block" alt="...">
			</p>
	</div>
	<div>
		<p>請選擇更新的圖片</p>
		<p><input type="file" name="photo" size="45" id="myFile"></p>		
		<div id="preview"></div>
	</div>
  	
<br>

<input type="hidden" name="action" value="update">
<input type="hidden" name="prodNo" value="<%=shopProductVO.getProdNo()%>">
<input type="hidden" name="venNo" value="<%=shopProductVO.getVenNo()%>">
<input type="hidden" name="className" value="<%=shopProductVO.getClassName()%>">
<input type="hidden" name="increaseTime" value="<%=shopProductVO.getIncreaseTime()%>">
<input type="hidden" name="evCount" value="<%=shopProductVO.getEvCount()%>">
<input type="hidden" name="evTotal" value="<%=shopProductVO.getEvTotal()%>">
<button type="submit" class="btn btn-primary" style="width: 58px; height: 29px;"><h6>上架</h6></button>
</FORM>

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



<!-- 以下為預覽圖片 -->
 <script type="text/javascript">

      function init() {                 
         document.getElementById("myFile").addEventListener('change', function(e){
          if(this.files && this.files[0]){
              var reader = new FileReader();
              reader.onload = function (e) { // 這裡有個檔案的onload事件
                var img = document.createElement("img");
                img.setAttribute("src", e.target.result);
                img.style.height = '300px';
                  document.getElementById("preview").appendChild(img);
              }
              reader.readAsDataURL(this.files[0]); // trigger onload event
          }
        }, false);
      }
      window.addEventListener('load', init, false);  
 </script>