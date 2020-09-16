<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.sprod_order.model.*"%>
<%@ page import="com.sprod_detail.model.*" %>
<%@ page import="com.shop_product.model.*"%>
<%@ page import="com.rep_sprod.model.*" %>
 
<!-- 取得當下時間 -->
<jsp:useBean id="now" class="java.util.Date" scope="page"/>
<%	
	Sprod_orderVO sprodOrderVO = (Sprod_orderVO) request.getAttribute("sprodOrderVO");
	Sprod_detailJNDIDAO dao = new Sprod_detailJNDIDAO();
	Shop_productJNDIDAO shopProductDao = new Shop_productJNDIDAO();
	
	/**用訂單編號去查所有訂單明細**/
	
	List<Sprod_detailVO> list = dao.findOneOrderDetail(sprodOrderVO.getOrderNo());
	pageContext.setAttribute("list", list);


	
	/**先取出所有商品VO準備用商品編號去抓名稱**/
	List<Shop_productVO> shopProductList = shopProductDao.getAll();
%>




<html>
<%@ include file="/front-end/Header/header.jsp" %>
<head>
<meta charset="BIG5">
<title>會員訂單資料</title>

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
			 <h3>會員訂單資料</h3>
		</td></tr>
	</table>
<center>	
	<table class="table table-striped" style="width:80%">
	  <thead>
	    <tr>
	      <th scope="col">訂單編號</th>
	      <th scope="col">會員編號</th>
	      <th scope="col">訂單成立時間</th>
	      <th scope="col">寄送方式</th>
	      <th scope="col">寄送地址</th>
	      <th scope="col">收件人姓名</th>
	      <th scope="col">收件人電子信箱</th>
	      <th scope="col">總金額</th>
	      <th scope="col">訂單狀態</th>
	    </tr>
	  </thead>
	  <tbody>
	    <tr>
	      <th scope="row">${sprodOrderVO.orderNo}</th>
	      <td>${sprodOrderVO.memNo}</td>
<!-- 	    取得當下時間 -->
	      <td><fmt:formatDate value="${now}" pattern="yyyy年MM月dd日" /></td>
	      <td>
	      		<c:choose>
					<c:when test="${sprodOrderVO.tranMethod == '1'}">
						<p>超商取貨</p>
					</c:when>
					<c:when test="${sprodOrderVO.tranMethod == '2'}">
						<p>宅配</p>
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
			</td>
	    </tr>
	  </tbody>
	</table>
	
<!-- 商品現況開始 -->

		<table name='detail' style="width:80%">
				<tr>
					<th>產品名稱</th>
					<th style="white-space:nowrap;">購買數量</th>
					<th style="white-space:nowrap;">你給予這項產品的分數:</th>
					<th style="white-space:nowrap;">評價內容</th>
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
<!-- 商品現況結束 -->
<table style="width:80%">
		<tr>
			<th style="white-space:nowrap;">產品名稱</th>
			<th style="white-space:nowrap;">購買數量</th>
			<th style="white-space:nowrap;">請選擇你給予這項產品的分數:</th>
			<th style="white-space:nowrap;">評價內容</th>
			<th style="white-space:nowrap;">送出評價</th>
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
						<button name='submitCom' style="background-image: linear-gradient(to top, #9890e3 0%, #b1f4cf 100%);">送出</button>
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
		form_data.evaCont= text; //把物件加到file後面
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

