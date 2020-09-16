<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.shop_product.model.*"%>
<%@ page import="com.sprod_photo.model.*" %>>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%	
	Shop_productJNDIDAO dao = new Shop_productJNDIDAO();
    List<Shop_productVO> list = dao.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
<title>�Ҧ��ӫ~���</title>

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
	<h1>�Ҧ��ӫ~���
		<small><button type="button" class="btn btn-info"><a href="vendorSelectProductHome.jsp">�^����</a></button></small>	
	</h1>
</div>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table class="table table-striped">
  <thead>
    <tr>
     	<th scope="col"><div style="width: 70px;"><p class="text-center">�ӫ~�s��</p></div></th>
		<th scope="col"><div style="width: 70px;"><p class="text-center">�ӫ~����</p></div></th>
		<th scope="col"><div style="width: 70px;"><p class="text-center">���~�W��</p></div></th>
		<th scope="col"><p class="text-center">���~����</p></th>
		<th scope="col"><div style="width: 70px;"><p class="text-center">�W�[�ɶ�</p></div></th>
		<th scope="col"><div style="width: 70px;"><p class="text-center">�ӫ~���</p></div></th>
		<th scope="col"><div style="width: 70px;"><p class="text-center">�ӫ~���A</p></div></th>
		<th scope="col"><div style="width: 70px;"><p class="text-center">��������</p></div></th>
		<th scope="col"><div style="width: 70px;"><p class="text-center">�����`��</p></div></th>
		<th scope="col"><div style="width: 100px;"><p class="text-center">�ӫ~�Ӥ�</p></div></th>
		<th scope="col"><div style="width: 45px;"><p class="text-center">�ק�</p></div></th>
    </tr>
  </thead>
  <tbody>
  <%@ include file="page1.file" %> 
	<c:forEach var="shop_productVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
    <tr>
      	<th scope="row"><div style="width: 70px;"><p class="text-center">${shop_productVO.prodNo}</p></div></th>
		<td><div style="width: 70px;"><p class="text-center">${shop_productVO.className}</p></div></td>
		<td><div style="width: 70px;"><p class="text-center">${shop_productVO.prodName}</p></div></td>
		<td><p class="text-center">${shop_productVO.prodIntro}</p></td>
		<td><div style="width: 70px;"><p class="text-center">${shop_productVO.increaseTime}</p></div></td>
		<td><div style="width: 70px;"><p class="text-center">${shop_productVO.price}</p></div></td>
		<td><div style="width: 70px;"><p class="text-center">
				<c:choose>
					<c:when test="${shop_productVO.sprodStatus == '0'}">
						�W�[
					</c:when>
					<c:when test="${shop_productVO.sprodStatus == '1'}">
						�U�[
					</c:when>
					<c:when test="${shop_productVO.sprodStatus == '2'}">
						�Q���|�U�[
					</c:when>
				</c:choose>			
		</p></div></td>
		<td><div style="width: 70px;"><p class="text-center">${shop_productVO.evCount}</p></div></td>
		<td><div style="width: 70px;"><p class="text-center">${shop_productVO.evTotal}</p></div></td>
		<td><div style="width: 100px;"><p class="text-center">
			<img src="<%=request.getContextPath()%>/GetPhotos?prodNo=${shop_productVO.prodNo}"class="d-block" alt="..." style="width: 100px;">
		</p></div></td>
		<td><div style="width: 45px;"><p class="text-center">
				
				<FORM METHOD="post" ACTION="shop_product.do" style="margin-bottom: 0px">
					<button type="submit" class="btn btn-primary" style="width: 50px; height: 55px;"><h6>�ק�</h6></button>
		</p></div></td>
					<input type="hidden" name="prodNo" value="${shop_productVO.prodNo}">
					<input type="hidden" name="action" value="getOne_For_Update">
				</FORM>

    </tr>
    </c:forEach>
  </tbody>
</table>

<%@ include file="page2.file" %>
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

</html>