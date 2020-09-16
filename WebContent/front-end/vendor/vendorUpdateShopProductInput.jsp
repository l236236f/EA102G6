<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.shop_product.model.*"%>

<%
  Shop_productVO shopProductVO = (Shop_productVO) request.getAttribute("shopProductVO"); //EmpServlet.java (Concroller) �s�Jreq��empVO���� (�]�A�������X��empVO, �]�]�A��J��ƿ��~�ɪ�empVO����)
%>

<html>
<head>

  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

<title>�ӫ~��ƭק�</title>

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
<!-- ==================================================�������e�}�l======================================================== -->
<div class="page-header">
	<h1>�ӫ~��s
		<small><button type="button" class="btn btn-info"><a href="vendorSelectProductHome.jsp">�^�W��</a></button></small>	
	</h1>
</div>

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

<FORM METHOD="post" ACTION="shop_product.do" name="form1" enctype="multipart/form-data">

<table>
	<tr>
		<td>�ӫ~�s��:<font color=red><b>*</b></font></td>
		<td><%=shopProductVO.getProdNo()%></td>
	</tr>
	<tr>
		<td>�t�ӽs��:</td>
		<td><%=shopProductVO.getVenNo()%></td>
	</tr>
	<tr>
		<td>�ӫ~�����W��:</td>
		<td><%=shopProductVO.getClassName()%></td>
	</tr>
	<tr>
		<td>�W�[�ɶ�:</td>
		<td><%=shopProductVO.getIncreaseTime()%></td>
	</tr>
	<tr>
		<td>�ӫ~�����H��:</td>
		<td><%=shopProductVO.getEvCount()%></td>
	</tr>
	<tr>
		<td>�ӫ~�`��:</td>
		<td><%=shopProductVO.getEvTotal()%></td>
	</tr>
</table>
 	<label for="exampleFormControlInput1">�ӫ~�W��</label>
    	<input type="TEXT" class="form-control" id="exampleFormControlInput1" placeholder="�п�J�z���ӫ~�W��" name="prodName" value="<%=shopProductVO.getProdName()%>">

 	<div class="form-group">
	    <label for="exampleFormControlSelect1">�ӫ~�����W��</label>
	    <select class="form-control" id="exampleFormControlSelect1" style="width:120px;" name="className">
	      	<option value="�d�����~">�d�����~</option>
			<option value="�d���Ϋ~">�d���Ϋ~</option>
	    </select>
  	</div>

  	<div class="form-group">
	    <label for="exampleFormControlTextarea1">�ӫ~����</label>
	    <textarea class="form-control" id="exampleFormControlTextarea1" rows="3" name="prodIntro" value="<%=shopProductVO.getProdIntro()%>"><%=shopProductVO.getProdIntro()%></textarea>
  	</div>

	<label for="exampleFormControlInput1">�ӫ~���</label>
    	<input type="TEXT" class="form-control" id="exampleFormControlInput1" placeholder="�п�J�z���ӫ~����" name="price" value="<%= (shopProductVO==null)? "" : shopProductVO.getPrice()%>">
	
	<div class="form-group">
	    <label for="exampleFormControlSelect1">�W�[���A</label>
	    <select class="form-control" id="exampleFormControlSelect1" style="width:120px;" name="sprodStatus">
	      	<option value="0">�W�[</option>
			<option value="1">�U�[</option>
	    </select>
  	</div>
  	
  	<div>
		<p>�{�b���Ϥ�</p>
			<p>
				<img src="<%=request.getContextPath()%>/GetPhotos?prodNo=<%=shopProductVO.getProdNo()%>" class="d-block" alt="...">
			</p>
	</div>
	<div>
		<p>�п�ܧ�s���Ϥ�</p>
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
<button type="submit" class="btn btn-primary" style="width: 58px; height: 29px;"><h6>�W�[</h6></button>
</FORM>

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
 </script>