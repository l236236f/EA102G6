<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<% VendorVO XvendorVO = (VendorVO) request.getAttribute("vendorVO");%> <!-- ���~VO -->

<head>

  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>�t�ӭ���</title>

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
  table {
 width: 100%;
 background-color: white;
 margin-top: 1px;
 margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
    width:5%
  }
  .firstCol{
    text-align:right;
  }
</style>
</head>

<body id="page-top">
  <div id="wrapper">
    <%@ include file="/front-end/vendor/LHSBar.jsp" %>
    <div id="content-wrapper" class="d-flex flex-column">
      <div id="content">
    <%@ include file="/front-end/vendor/topBar.jsp" %>
        <div class="container-fluid">
  <table id="table-1">
   <tr>
    <td>
     <h3 style='height:50px; margin-top:25px'>�t�ӭ���</h3>
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

<FORM METHOD="post" ACTION="vendor.do" name="form1" enctype="multipart/form-data">
<table>
<tr>
<td></td>
<td>
<label for='photo'>
<img src='<%=request.getContextPath()%>/front-end/ShowPhotos?type=vendor&photo_no=<%=vendorVO.getVenNo()%>'></label>
</td>
</tr>
 <tr>
  <td class='firstCol'>�t�ӦW��: </td>
  <td><input type="TEXT" name="venName" size="45" maxLength="20" 
    value="<%= (XvendorVO==null)? vendorVO.getVenName() : XvendorVO.getVenName()%>" /></td>
 </tr>
 <tr>
  <td class='firstCol'>�t�ӱb��: </td>
  <td><input type="TEXT" name="venAcc" size="45"
    value="<%= (XvendorVO==null)? vendorVO.getVenAcc() : XvendorVO.getVenAcc()%>" /></td>
 </tr>
 <tr>
  <td class='firstCol'>�t�ӱK�X: </td>
  <td><input type="TEXT" name="venPw" size="45"
    value="<%= (XvendorVO==null)? vendorVO.getVenPw() : XvendorVO.getVenPw()%>" /></td>
 </tr>
 <tr>
  <td class='firstCol'>�p���q��:</td>
  <td><input type="TEXT" name="venTel" size="45"
    value="<%= (XvendorVO==null)? vendorVO.getVenTel() : XvendorVO.getVenTel()%>" /></td>
 </tr>
 <tr>
  <td class='firstCol'>�Τ@�s��:</td>
  <td><input type="TEXT" name="venID" size="45"
    value="<%= (XvendorVO==null)? vendorVO.getVenID() : XvendorVO.getVenID()%>" /></td>
 </tr>
 <tr>
  <td class='firstCol'>�״ڱb��:</td>
  <td><input type="TEXT" name="venMoney" size="45"
    value="<%= (XvendorVO==null)? vendorVO.getVenMoney() : XvendorVO.getVenMoney()%>" /></td>
 </tr>
 <tr>
  <td class='firstCol'>�a�}:</td>
  <td><input type="TEXT" name="venAddr" size="45"
    value="<%= (XvendorVO==null)? vendorVO.getVenAddr() : XvendorVO.getVenAddr()%>" /></td>
 </tr>
 
 <tr>
  <td class='firstCol'>�q�l�l��:</td>
  <td><input type="TEXT" name="venEmail" size="45"
    value="<%= (XvendorVO==null)? vendorVO.getVenEmail() : XvendorVO.getVenEmail()%>" /></td>
 </tr>
 
 <tr>
  <td class='firstCol'>²��:</td>
  <td><input type="TEXT" name="venIntro" size="45"
    value="<%= (XvendorVO==null)? vendorVO.getVenIntro() : XvendorVO.getVenIntro()%>" /></td>
 </tr>
 
</table>
<br>
<input type="file" id='photo' name="venPhoto" style='display:none'>
<input type="hidden" name="action" value="update">
<input type="hidden" name="venNo" value="<%=vendorVO.getVenNo()%>">
<div align='center'><input type="submit" value="�e�X�ק�"></div>
</FORM>

        </div>
      </div>
      <%@ include file="/front-end/vendor/footer.jsp" %>
      <%@ include file="/chatRoom/chatRoom.jsp"%>
    </div>
  </div>
  <a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
  </a>

</body>

</html>