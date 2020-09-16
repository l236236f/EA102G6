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
<title>�T�{���b</title>

<%@ include file="/front-end/Header/header.jsp" %>
</head>
<style>#success_message{ display: none;}
body {
  font-family: "Open Sans", -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen-Sans, Ubuntu, Cantarell, "Helvetica Neue", Helvetica, Arial, sans-serif; 
}
</style>



<body style="background-image: linear-gradient(120deg, #d4fc79 0%, #96e6a1 100%);">
<div style="background-image: url('img/bg-img/bgPicture.jpg'); background-size: 2000px 1100px;">
<font size="+3">�H�H�i�i�d���ӫ� - ���b </font>
<hr><p><center>


	
	<table class="table table-striped">
    <tr>
      <th scope="col">���~�W��</th>
      <th scope="col">�t�ӽs��</th>
      <th scope="col">����</th>
      <th scope="col">�ƶq</th>
    </tr>
	<form name="myForm" class="well form-horizontal" onsubmit="return validateForm()" action="<%=request.getContextPath()%>/front-end/shop/sprod_order.do" method="post"  id="contact_form">
	<% 
		Vector<Sprod> buylist = (Vector<Sprod>) session.getAttribute("shoppingcart");
		List<Sprod_detailVO> spordDetailVOList = new ArrayList<Sprod_detailVO>();
		//�ŧi�`���B�ܼ�
		int amount = 0;
		for (int index = 0; index < buylist.size(); index++) {
			
			//���U������
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
<!--       ���ӫ~�ƶq -->
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
		<td><div align="center"><font color="red"><b>�`���B�G</b></font></div></td>
		<td><div align="center"><font color="red"><b><label id="shoptotaltotaltotal"></label>��</b></font></div></td>
	</tr>
</table>

<table border="1" width="720">
<!-- ���~�C�� -->
	<c:if test="${not empty errorMsgs}">
		<font style="color:red">�Эץ��H�U���~</font>
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
<legend>~�п�J�z���򥻸��~</legend>

<!-- ����H�m�W-->

<div class="form-group">
  <label class="col-md-4 control-label">����H�m�W</label>  
  <div class="col-md-4 inputGroupContainer">
  <div class="input-group">
  <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
  <input  id="addresseeName" name="addresseeName" placeholder="�п�J����H�m�W" class="form-control"  type="text" style="background-image: linear-gradient(-225deg, #5D9FFF 0%, #B8DCFF 48%, #6BBBFF 100%);">
    </div>
  </div>
</div>


<!-- �q�l�H�c-->
       <div class="form-group">
  <label class="col-md-4 control-label">�q�l�H�c</label>  
    <div class="col-md-4 inputGroupContainer">
    <div class="input-group">
        <span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>
  <input id="addresseeMail" name="addresseeMail" placeholder="�п�J�q�l�H�c" class="form-control"  type="text" style="background-image: linear-gradient(-225deg, #5D9FFF 0%, #B8DCFF 48%, #6BBBFF 100%);">
    </div>
  </div>
</div>


<!-- ����a�}-->
      
<div class="form-group">
  <label class="col-md-4 control-label">����a�}</label>  
    <div class="col-md-4 inputGroupContainer">
    <div class="input-group" style="width:380px">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.0/jquery.min.js"></script>
<script src="dk-tw-citySelector.js"></script>
<script src="init-address.js"></script>
    <ul class="row" id="memberForm" style="width:100%;">           
<!--           ���� -->
          <select id="country" name="country"></select>
<!--           �ϰ� -->
          <select id="district" name="district"></select>
<!--           �ԲӦa�} -->
          <input
            type="text"
            id="tranAdd"
            name="tranAdd"
            value=""
            placeholder="�п�J�ԲӦa�}..."
            class="form-control"
            style="width:100% ;background-image: linear-gradient(-225deg, #5D9FFF 0%, #B8DCFF 48%, #6BBBFF 100%);"
          />

    </ul>
<script>
      let memberForm = $('#memberForm');
      let address = '313�s�˿����y�۶m';
      initAddress(memberForm, address);
</script>
    </div>
  </div>
</div>


<!-- �H�e�覡 -->
 <div class="form-group">
                        <label class="col-md-4 control-label">�H�e�覡</label>
                        <div class="col-md-4">
                            <div class="radio">
                                <label>
                                    <input type="radio" name="tranMethod" value="1" checked="checked">�W�Ө��f
                                </label>
                            </div>
                            <div class="radio">
                                <label>
                                    <input type="radio" name="tranMethod" value="2">�v�t
                                </label>
                            </div>
                        </div>
 </div>
 
<p><input name="sumit" type="submit" value="�T�{" style="background-image: linear-gradient(to top, #4481eb 0%, #04befe 100%);"></p>

</fieldset>
</form>

<button id="123">���_�p���s</button>
</center>
</div>
<%@ include file="/front-end/Footer/footer.jsp" %>


<!-- ================================================================================�ӫ~�[��}�l=============================== -->
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
			
// 			���_�p���s
			$("#123").click(function(){
				$("#addresseeName").val("���i��");
				$("#addresseeMail").val("EA102GG66@gmail.com");
				$("#tranAdd").val("�H�q�����q7��36��");
				
			})
	}) 
 
<!-- ================================================================================�ӫ~�[���=============================== -->

		
// 	�e�x����
function validateForm() {
	var name = document.forms["myForm"]["addresseeName"].value;	           //����̩m�W
  	var total = document.getElementById("shoptotaltotaltotal").innerHTML;  //�ʪ������~�`��
	var tranAdd = document.forms["myForm"]["tranAdd"].value;				//�H�e�a�}
  	var mail = document.forms["myForm"]["addresseeMail"].value;				//�q�l�l��
  	
  	var totalMoney = $("#shoptotaltotaltotal").text();
  	$("#totalMoney").val(totalMoney);
  	
//  ����P�_
	if (name == ""){
		swal("�п�J����̩m�W","","error");
		return false;
	}

  	if (total == 0){
  		swal("�нT�{��J���~���ƶq","","error");
  		return false;
  	}
  
  	if (mail == "") {
  		swal("�п�J�q�l�H�c","","error");
    	return false;
  	}
	
  	if (tranAdd == ""){
  		swal("�п�J�H�e�a�}","","error");
  		return false;
  	}
  	
  	if(!confirm("�O�_�T�w�q��")){
  		return false;
  	};
  	
}

		
</script>
<!-- JS, Popper.js, and jQuery -->
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>

</html>