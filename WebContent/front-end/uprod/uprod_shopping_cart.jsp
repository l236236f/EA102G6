<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 設定時間 -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page import="java.util.*"%>
<%@ page import="com.uprod.model.*"%>
<%@ page import="com.mem.model.*"%>

<!DOCTYPE html>
<html lang="zxx">
<head>
<meta charset="UTF-8">
<meta name="description" content="Fashi Template">
<meta name="keywords" content="Fashi, unica, creative, html">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>二手市集 - 購買確認頁</title>

<style>
.proceed-checkout .proceed-btn {
	font-size: 14px;
	font-weight: 700;
	color: #ffffff;
	border-color: #E7AB3C;
	border-style: solid;
	text-transform: uppercase;
	padding: 15px 25px 14px 25px;
	display: block;
	text-align: center;
	width: 100%;
}

.box1 {
	display: inline-block;
}
</style>
</head>

<body>
	<%@ include file="/front-end/Header/header.jsp"%>

	<%
		UprodVO uprodVO = (UprodVO) request.getAttribute("uprodVO");
	%>

	<jsp:useBean id="memberSvc" scope="page"
		class="com.mem.model.MemService" />

	<!-- Breadcrumb Section Begin -->
	<section class="blog-details spad" style="height: 100px;">
		<div class="row">
			<div class="col-lg-12">
				<div class="blog-details-inner">
					<div class="blog-detail-title">
						<h2>確認購買商品</h2>
					</div>
				</div>
			</div>
		</div>
	</section>

	<!-- Breadcrumb Section Begin -->

	<!-- Shopping Cart Section Begin -->
	<section class="shopping-cart spad">
		<div class="container">
			<div class="row">

				<%-- 錯誤表列 --%>
				<c:if test="${not empty errorMsgs}">
					<font style="color: red">請修正以下錯誤：</font>
					<c:forEach var="message" items="${errorMsgs}">
						<a style="color: red">${message}</a>
					</c:forEach>
				</c:if>

				<div class="col-lg-12">
					<FORM METHOD="post" ACTION="uprod.do" name="form1">
						<div class="cart-table">
							<table>
								<thead>
									<tr>
										<th>商品照片</th>
										<th class="p-name">商品名稱</th>
										<th>寄送方式</th>
										<th>寄送地址</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td class="cart-pic first-row"><img
											src="<%=request.getContextPath()%>/front-end/uprod/showpic.do?ProdNo=${uprodVO.getProdNo()}"
											alt=""></td>
										<td class="cart-title first-row">
											<h5>${uprodVO.prodName}</h5>
										</td>
										<td class="p-price first-row"><select name="TranMethod"
											size="1">
												<option value=TM0>超商取貨</option>
												<option value=TM1 selected>宅配</option>
												<option value=TM2>面交</option>
										</select></td>
										<td>
											<div class="city-selector-set">

												<div class=box1>
													<!-- 郵遞區號欄位 (建議加入 readonly 屬性，防止修改) -->
													<input class="zipcode" type="text" size="3" readonly
														placeholder="">
												</div>

												<div class=box1>
													<!-- 縣市選單 -->
													<select class="county"></select>
												</div>

												<div class=box1>
													<!-- 區域選單 -->
													<select class="district"></select>
												</div>

												<div class=box1>
													<input type="TEXT" id="456" name="TranAddr3" size="45"
														value="<%=(uprodVO.getTranAddr() == null) ? "" : uprodVO.getTranAddr()%>" />
												</div>
											</div>
										</td>


										<!-- 										<td class="qua-col first-row"><input type="TEXT" id="456" -->
										<!-- 											name="TranAddr" size="45" -->
										<%-- 											value="<%=(uprodVO.getTranAddr() == null) ? "" : uprodVO.getTranAddr()%>" /> --%>
										<!-- 										</td> -->
									</tr>
								</tbody>
							</table>
						</div>

						<div class="row">
							<div class="col-lg-4">
								<div class="cancel">
									<button type="button" class="site-btn" data-dismiss="modal"
										style="background-color: black; border: 1px solid black; margin-top: 77px;"
										onclick="location.href='<%=request.getContextPath()%>/front-end/uprod/used_shop.jsp'">
										取消</button>
								</div>
							</div>

							<div>
								<button id="123">神奇小按鈕</button>
							</div>

							<div class="col-lg-4 offset-lg-4">
								<div class="proceed-checkout">
									<ul>
										<li class="cart-total">價格 <span style="color: red;">${uprodVO.price}
												<span style="color: black; padding-left: 3px;">元</span>
										</span>
										</li>
									</ul>
									<input type="hidden" name="action" value="buy"> <input
										type="hidden" name="ProdNo" value="<%=uprodVO.getProdNo()%>" />
									<input type="hidden" name="CustNo"
										value="<%=memVO.getMemNo()%>" />
									<button type="submit" class="proceed-btn buy_prod"
										style="background-color: #E7AB3C;">確認購買</button>
								</div>
							</div>
						</div>
					</FORM>
				</div>
			</div>
		</div>
	</section>
	<!-- Shopping Cart Section End -->

	<%@ include file="/front-end/Footer/footer.jsp"%>
	<script
		src="https://cdn.jsdelivr.net/npm/tw-city-selector@2.1.1/dist/tw-city-selector.min.js">
		
	</script>
	<script>
		$("#123").click(function() {
			$("#456").val("燈塔路90號12樓");
			event.preventDefault();
		})

		new TwCitySelector({
			el : '.city-selector-set',
			elCounty : '.county', // 在 el 裡查找 element
			elDistrict : '.district', // 在 el 裡查找 element
			elZipcode : '.zipcode' // 在 el 裡查找 element
		});
	</script>
</body>
</html>