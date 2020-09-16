<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.shop_product.model.*"%>
<%@ page import="java.util.Date" %>

<%
  Shop_productVO shopProductVO = (Shop_productVO) request.getAttribute("shopProductVO");
%>

<html>
<head>
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
  <!--   bootstrap -v 4.5.2 -->
  <!-- CSS only -->
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
  
<title>�ӫ~��Ʒs�W - addShop_product.jsp</title>

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
<!-- ==================================================�������e�}�l======================================================== -->
<div class="page-header">
	<h1>�ӫ~��Ʒs�W
		<small><button type="button" class="btn btn-info"><a href="vendorSelectProductHome.jsp">�^����</a></button></small>	
	</h1>
</div>
<p>�H�H�i�i���z�ͷN����</p>


<h3>�ӫ~�s�W:</h3>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>


<FORM METHOD="post" ACTION="shop_product.do" name="form1" enctype="multipart/form-data">

 	<label for="exampleFormControlInput1">�ӫ~�W��</label>
    	<input type="TEXT" class="form-control" id="prodName" placeholder="�п�J�z���ӫ~�W��" name="prodName" value="<%= (shopProductVO==null)? "" : shopProductVO.getProdName()%>">

 	<div class="form-group">
	    <label for="exampleFormControlSelect1">�ӫ~�����W��</label>
	    <select class="form-control" id="className" style="width:120px;" name="className">
	      	<option value="�d�����~">�d�����~</option>
			<option value="�d���Ϋ~">�d���Ϋ~</option>
	    </select>
  	</div>

  	<div class="form-group">
	    <label for="exampleFormControlTextarea1">�ӫ~����</label>
	    <textarea class="form-control" id="prodIntro" rows="3" name="prodIntro" value="<%= (shopProductVO==null)? "" : shopProductVO.getProdIntro()%>"></textarea>
  	</div>

	<label for="exampleFormControlInput1">�ӫ~���</label>
    	<input type="TEXT" class="form-control" id="price" placeholder="�п�J�z���ӫ~����" name="price" value="<%= (shopProductVO==null)? "" : shopProductVO.getPrice()%>">

	<div class="form-group">
    	<label for="exampleFormControlFile1">�п�ܹϤ�</label>
    	<input type="file" class="form-control-file" name="photo" size="45" id="myFile">
  </div>
  <div id="preview"></div>
<br>

<input type="hidden" name="action" value="insert">
<input type="hidden" name="venNo" value="<%= vendorVO.getVenNo()%>">
<input type="hidden" name="evCount" value=0>
<input type="hidden" name="evTotal" value=0>
<input type="hidden" name="sprodStatus" value=0>
<button type="submit" class="btn btn-primary" style="width: 58px; height: 29px;"><h6>�W�[</h6></button>
</FORM>
<button id="123">���_�p���s</button>



<!-- ==================================================�������e����======================================================== -->
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
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>


<!-- �H�U���w���Ϥ� -->
 <script type="text/javascript">

      function init() {                 
         document.getElementById("myFile").addEventListener('change', function(e){
          if(this.files && this.files[0]){
              var reader = new FileReader();
              reader.onload = function (e) { // �o�̦����ɮת�onload�ƥ�
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
	  
      $(function(){
    	  $("#123").click(function(){
				$("#prodName").val("�n�Y���d�����~");
				$("#prodIntro").val("�W�n�Y�A���R�|�ᮬ�@");
				$("#price").val("990");
				
			})
      })
 </script>
