<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.sprod_order.model.*"%>
<%@ page import="com.sprod_detail.model.*" %>
<%@ page import="com.shop_product.model.*"%>
<%@ page import="com.rep_sprod.model.*" %>
 
<!-- ���o��U�ɶ� -->
<jsp:useBean id="now" class="java.util.Date" scope="page"/>
<%	
	Sprod_orderVO sprodOrderVO = (Sprod_orderVO) request.getAttribute("sprodOrderVO");
	Sprod_detailJNDIDAO dao = new Sprod_detailJNDIDAO();
	Shop_productJNDIDAO shopProductDao = new Shop_productJNDIDAO();
	
	/**�έq��s���h�d�Ҧ��q�����**/
	
	List<Sprod_detailVO> list = dao.findOneOrderDetail(sprodOrderVO.getOrderNo());
	pageContext.setAttribute("list", list);


	
	/**�����X�Ҧ��ӫ~VO�ǳƥΰӫ~�s���h��W��**/
	List<Shop_productVO> shopProductList = shopProductDao.getAll();
%>




<html>
<%@ include file="/front-end/Header/header.jsp" %>
<head>
<meta charset="BIG5">
<title>�|���q����</title>

<!-- bootstrap4.5.2 -->
<!-- CSS only -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">



<style>
  table#table-1 {
    text-align: center;
  }

  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>

<body>
<div style="background-image: linear-gradient(to top, #fff1eb 0%, #ace0f9 100%);">
	<table id="table-1" style="width: 100%">
		<tr><td>
			 <h3>�|���q����</h3>
		</td></tr>
	</table>
<center>	
	<table class="table table-striped" style="width:80%">
	  <thead>
	    <tr>
	      <th scope="col">�q��s��</th>
	      <th scope="col">�|���s��</th>
	      <th scope="col">�q�榨�߮ɶ�</th>
	      <th scope="col">�H�e�覡</th>
	      <th scope="col">�H�e�a�}</th>
	      <th scope="col">����H�m�W</th>
	      <th scope="col">����H�q�l�H�c</th>
	      <th scope="col">�`���B</th>
	      <th scope="col">�q�檬�A</th>
	    </tr>
	  </thead>
	  <tbody>
	    <tr>
	      <th scope="row">${sprodOrderVO.orderNo}</th>
	      <td>${sprodOrderVO.memNo}</td>
<!-- 	    ���o��U�ɶ� -->
	      <td><fmt:formatDate value="${now}" pattern="yyyy�~MM��dd��" /></td>
	      <td>
	      		<c:choose>
					<c:when test="${sprodOrderVO.tranMethod == '1'}">
						<p>�W�Ө��f</p>
					</c:when>
					<c:when test="${sprodOrderVO.tranMethod == '2'}">
						<p>�v�t</p>
					</c:when>
				</c:choose>
	      </td>
	      <td>${sprodOrderVO.tranAdd}</td>
		  <td>${sprodOrderVO.addresseeName}</td>
		  <td>${sprodOrderVO.addresseeMail}</td>
		  <td>${sprodOrderVO.orderTotal}</td>
		  <td>
				<c:choose>
					<c:when test="${sprodOrderVO.orderStatus == '0'}">
						<p>���X�f</p>
					</c:when>
					<c:when test="${sprodOrderVO.orderStatus == '1'}">
						<p>�w�X�f</p>
					</c:when>
					<c:when test="${sprodOrderVO.orderStatus == '2'}">
						<p>����(�w��f)</p>
					</c:when>
					<c:when test="${sprodOrderVO.orderStatus == '3'}">
						<p>����</p>
					</c:when>
				</c:choose>	
			</td>
	    </tr>
	  </tbody>
	</table>
	
<!-- �ӫ~�{�p�}�l -->

		<table name='detail' style="width:80%">
				<tr>
					<th>���~�W��</th>
					<th style="white-space:nowrap;">�ʶR�ƶq</th>
					<th style="white-space:nowrap;">�A�����o�����~������:</th>
					<th style="white-space:nowrap;">�������e</th>
				</tr>	
				<%
					for(Sprod_detailVO index: list){
				%>	
					<tr name='<%=index.getProdNo() %>'>	
						<td><%=shopProductDao.selectShopProductName(index.getProdNo())%></td>
						<td><%=index.getQuantity()%></td>
						<td name='evastar'><%=(index.getEvaStar()==null||index.getEvaStar()==0)? "":index.getEvaStar()%></td>
						<td name='evacont'><%=(index.getEvaCont()==null)? "":index.getEvaCont()%></td>
					</tr>		
				<% 
					}
				%>
	</table>
<!-- �ӫ~�{�p���� -->
<table style="width:80%">
		<tr>
			<th style="white-space:nowrap;">���~�W��</th>
			<th style="white-space:nowrap;">�ʶR�ƶq</th>
			<th style="white-space:nowrap;">�п�ܧA�����o�����~������:</th>
			<th style="white-space:nowrap;">�������e</th>
			<th style="white-space:nowrap;">�e�X����</th>
		</tr>	
		<%
			for(Sprod_detailVO index: list){
		%>
		
			<form METHOD="post" ACTION="sprod_orderDetail.do" onsubmit="return false">
				<tr name ='<%=index.getProdNo()%>'>	
					<td><%=shopProductDao.selectShopProductName(index.getProdNo())%></td>
					<td name='quality'><%=index.getQuantity()%></td>
					<td>
						<select size="1" name="evaStar" id="evaStar">
							<option value ="1">1</option>
							<option value ="2">2</option>
							<option value ="3">3</option>
							<option value ="4">4</option>
							<option value ="5">5</option>
						</select>
					</td>
					<td>
						<input type="text" name="evaCont" id="evaCont">				
					<td>
						<button name='submitCom' style="background-image: linear-gradient(to top, #9890e3 0%, #b1f4cf 100%);">�e�X</button>
					</td>
						<input type="hidden" name="orderNo" id="orderNo" value="<%=index.getOrderNo()%>">
						<input type="hidden" name="prodNo" id="prodNo" value="<%=index.getProdNo()%>">
					</tr>
				
						<input type="hidden" name="orderNo" id="orderNo" value="<%=index.getOrderNo()%>">
						<input type="hidden" name="prodNo" id="prodNo" value="<%=index.getProdNo()%>">
						<input type="hidden" name="action" id="action" value="update">
			</form>
		<% 
			}
		%>
	</table>
</center>

<!-- ========================================== -->

<%@ include file="/front-end/Footer/footer.jsp" %>
<%-- <%@ include file="/chatRoom/chatRoom.jsp"%> --%>
</div>
</body>

<!-- JS, Popper.js, and jQuery -->

<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>


<script>

$(function(){
	
	
	console.log($("button[name='submitCom']"))
	$("button[name='submitCom']").click(function(){
		
		$(this).hide();
		
		var tr =$(this).parent().parent();
		var text = tr.find("input[name='evaCont']").val();
		var star = tr.find("select[name='evaStar']").val();
		var ProdNo = tr.attr("name");
		var OrderNo= tr.find("input[name='orderNo']").val();
		var form_data = {}; 
		form_data.evaCont= text; //�⪫��[��file�᭱
		form_data.evaStar= star;
		form_data.orderNo = OrderNo;
		form_data.prodNo = ProdNo;
		form_data.action ='updateajax';

		
		$.ajax({
			url : 'sprod_orderDetail.do',
			data : form_data,
			type : 'post',
			success : function(data) {
				console.log(data);
				var obj = JSON.parse(data);
				console.log(obj);
				$('table[name="detail"]').find("tr[name='"+obj.prodNo+"']").find("td[name='evastar']").text(obj.evaStar);
				$('table[name="detail"]').find("tr[name='"+obj.prodNo+"']").find("td[name='evacont']").text(text);
			}
		});				
	});	
})


</script>
</html>

