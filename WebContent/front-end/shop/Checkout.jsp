<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ page import="java.util.* , shopping.Sprod" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.sprod_order.model.*"%>
<%@ page import="com.sprod_detail.model.*"%>
<%@ page import="java.util.Date" %>

<%
  Sprod_orderVO sprodOrderVO = (Sprod_orderVO) request.getAttribute("sprodOrderVO");
%>


<!DOCTYPE html>
<html>
<head>
<!-- bookstrap4.5.2 -->
<!-- CSS only -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">

 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>確認結帳</title>

<%@ include file="/front-end/Header/header.jsp" %>
</head>
<style>#success_message{ display: none;}
body {
  font-family: "Open Sans", -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen-Sans, Ubuntu, Cantarell, "Helvetica Neue", Helvetica, Arial, sans-serif; 
}
</style>



<body style="background-image: linear-gradient(120deg, #d4fc79 0%, #96e6a1 100%);">
<div style="background-image: url('img/bg-img/bgPicture.jpg'); background-size: 2000px 1100px;">
<font size="+3">寄寄養養寵物商城 - 結帳 </font>
<hr><p><center>


	
	<table class="table table-striped">
    <tr>
      <th scope="col">產品名稱</th>
      <th scope="col">廠商編號</th>
      <th scope="col">價格</th>
      <th scope="col">數量</th>
    </tr>
	<form name="myForm" class="well form-horizontal" onsubmit="return validateForm()" action="<%=request.getContextPath()%>/front-end/shop/sprod_order.do" method="post"  id="contact_form">
	<% 
		Vector<Sprod> buylist = (Vector<Sprod>) session.getAttribute("shoppingcart");
		List<Sprod_detailVO> spordDetailVOList = new ArrayList<Sprod_detailVO>();
		//宣告總金額變數
		int amount = 0;
		for (int index = 0; index < buylist.size(); index++) {
			
			//取各項的值
			Sprod order = buylist.get(index);
			String name = order.getName();
			String venNo = order.getVenNo();
			int price = order.getPrice();
			int quantity = order.getQuantity();
			String prodNo = order.getProdNo();
			
			Sprod_detailVO sprodDetailVO = new Sprod_detailVO();
			sprodDetailVO.setProdNo(prodNo);
			sprodDetailVO.setQuantity(quantity);
			spordDetailVOList.add(sprodDetailVO);
			
	%>
	

  <tbody >
    <tr style="background-image: linear-gradient(to right, #92fe9d 0%, #00c9ff 100%);">
      <th scope="row"><%=name%></th>
      <td><%=venNo%></td>
      <td><%=price%></td>
      <td>
<!--       更改商品數量 -->
				<table id="shoptabtabtab" >
				<tr>
				<td>
					<span id="price" class="price" style="display:none" ><%=price%></span>
					<input class="shopminminmin" name="quantity" type="button" data-pro="<%= prodNo %>" value="-" style="background-image: linear-gradient(to right, #f83600 0%, #f9d423 100%);"/>
					<input class="shopProductText_box" readonly id="shopProductText_box" name="quantity" type="text" size="5px" value="<%=quantity %>" />												
					<input class="shopaddaddadd" name="quantity" type="button" value="+" data-pro="<%= prodNo %>" style="background-image: linear-gradient(to right, #f83600 0%, #f9d423 100%);"/>
					<span class="smallTotal<%=name%>"></span>
				</td>
				</tr>				
				</table>
      </td>      
    </tr>
	
<% }%>
<% session.setAttribute("sprodDetail", spordDetailVOList); %>
	<tr>
		<td></td>
		<td></td>
		<td><div align="center"><font color="red"><b>總金額：</b></font></div></td>
		<td><div align="center"><font color="red"><b><label id="shoptotaltotaltotal"></label>元</b></font></div></td>
	</tr>
</table>

<table border="1" width="720">
<!-- 錯誤列表 -->
	<c:if test="${not empty errorMsgs}">
		<font style="color:red">請修正以下錯誤</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color:red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
</table>

<div class="container">

    
<fieldset>
<input type="hidden" name="spordDetailVOList" value="Sprod_detailVO">
<input type="hidden" name="action" value="insert">
<input type="hidden" name="memNo" value="<%=memVO.getMemNo()%>">
<input type="hidden" name="orderTotal" id="totalMoney" value="<%= amount %>">
<input type="hidden" name="orderStatus" value=0>

<!-- Form Name -->
<legend>~請輸入您的基本資料~</legend>

<!-- 收件人姓名-->

<div class="form-group">
  <label class="col-md-4 control-label">收件人姓名</label>  
  <div class="col-md-4 inputGroupContainer">
  <div class="input-group">
  <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
  <input  id="addresseeName" name="addresseeName" placeholder="請輸入收件人姓名" class="form-control"  type="text" style="background-image: linear-gradient(-225deg, #5D9FFF 0%, #B8DCFF 48%, #6BBBFF 100%);">
    </div>
  </div>
</div>


<!-- 電子信箱-->
       <div class="form-group">
  <label class="col-md-4 control-label">電子信箱</label>  
    <div class="col-md-4 inputGroupContainer">
    <div class="input-group">
        <span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>
  <input id="addresseeMail" name="addresseeMail" placeholder="請輸入電子信箱" class="form-control"  type="text" style="background-image: linear-gradient(-225deg, #5D9FFF 0%, #B8DCFF 48%, #6BBBFF 100%);">
    </div>
  </div>
</div>


<!-- 收件地址-->
      
<div class="form-group">
  <label class="col-md-4 control-label">收件地址</label>  
    <div class="col-md-4 inputGroupContainer">
    <div class="input-group" style="width:380px">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.0/jquery.min.js"></script>
<script src="dk-tw-citySelector.js"></script>
<script src="init-address.js"></script>
    <ul class="row" id="memberForm" style="width:100%;">           
<!--           縣市 -->
          <select id="country" name="country"></select>
<!--           區域 -->
          <select id="district" name="district"></select>
<!--           詳細地址 -->
          <input
            type="text"
            id="tranAdd"
            name="tranAdd"
            value=""
            placeholder="請輸入詳細地址..."
            class="form-control"
            style="width:100% ;background-image: linear-gradient(-225deg, #5D9FFF 0%, #B8DCFF 48%, #6BBBFF 100%);"
          />

    </ul>
<script>
      let memberForm = $('#memberForm');
      let address = '313新竹縣市尖石鄉';
      initAddress(memberForm, address);
</script>
    </div>
  </div>
</div>


<!-- 寄送方式 -->
 <div class="form-group">
                        <label class="col-md-4 control-label">寄送方式</label>
                        <div class="col-md-4">
                            <div class="radio">
                                <label>
                                    <input type="radio" name="tranMethod" value="1" checked="checked">超商取貨
                                </label>
                            </div>
                            <div class="radio">
                                <label>
                                    <input type="radio" name="tranMethod" value="2">宅配
                                </label>
                            </div>
                        </div>
 </div>
 
<p><input name="sumit" type="submit" value="確認" style="background-image: linear-gradient(to top, #4481eb 0%, #04befe 100%);"></p>

</fieldset>
</form>

<button id="123">神奇小按鈕</button>
</center>
</div>
<%@ include file="/front-end/Footer/footer.jsp" %>


<!-- ================================================================================商品加減開始=============================== -->
<!-- <script type="text/javascript" src="http://code.jquery.com/jquery-1.6.1.min.js"></script> -->
    <script>
    
     $(function(){ 
			$(".shopaddaddadd").click(function(){ 
				var t=$(this).prev().val();
				var tt = parseInt(t)+1;
				$(this).prev().val(tt);
				setTotal();
				var productNo = $(this).data("pro");
				$.ajax({
					url:"Shopping.html",
					type:"POST",
					data:{
						"action":"change",
						"prodNo":productNo,
						"quen":tt
					},
					success:function(){
						console.log("success");
					}
				});
				
	
			}) 
			$(".shopminminmin").click(function(){
				var t=$(this).next(); 
				t.val(parseInt(t.val())-1);
				if(parseInt(t.val())<0){ 
					t.val(0); 
				} 
				setTotal();
				var productNo = $(this).data("pro");
				$.ajax({
					url:"Shopping.html",
					type:"POST",
					data:{
						"action":"change",
						"prodNo":productNo,
						"quen":t.val()
					},
					success:function(){
						console.log("success");
					}
				});
				
			}) 
			function setTotal(){ 
			var s=0; 
			$("#shoptabtabtab td").each(function(){ 
			s+=parseInt($(this).find('input[class*=shopProductText_box]').val())*parseFloat($(this).find('span[class*=price]').text());
			}); 
			$("#shoptotaltotaltotal").html(s.toFixed(0)); 
			} 
			setTotal(); 
			
// 			神奇小按鈕
			$("#123").click(function(){
				$("#addresseeName").val("蕭波塊");
				$("#addresseeMail").val("EA102GG66@gmail.com");
				$("#tranAdd").val("信義路五段7號36樓");
				
			})
	}) 
 
<!-- ================================================================================商品加減結束=============================== -->

		
// 	前台驗證
function validateForm() {
	var name = document.forms["myForm"]["addresseeName"].value;	           //收件者姓名
  	var total = document.getElementById("shoptotaltotaltotal").innerHTML;  //購物車產品總價
	var tranAdd = document.forms["myForm"]["tranAdd"].value;				//寄送地址
  	var mail = document.forms["myForm"]["addresseeMail"].value;				//電子郵件
  	
  	var totalMoney = $("#shoptotaltotaltotal").text();
  	$("#totalMoney").val(totalMoney);
  	
//  條件判斷
	if (name == ""){
		swal("請輸入收件者姓名","","error");
		return false;
	}

  	if (total == 0){
  		swal("請確認輸入產品的數量","","error");
  		return false;
  	}
  
  	if (mail == "") {
  		swal("請輸入電子信箱","","error");
    	return false;
  	}
	
  	if (tranAdd == ""){
  		swal("請輸入寄送地址","","error");
  		return false;
  	}
  	
  	if(!confirm("是否確定訂單")){
  		return false;
  	};
  	
}

		
</script>
<!-- JS, Popper.js, and jQuery -->
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>

</html>