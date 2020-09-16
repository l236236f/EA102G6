<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- �]�w�ɶ� -->
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
<title>�G�⥫�� - �ʶR�T�{��</title>

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
						<h2>�T�{�ʶR�ӫ~</h2>
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

				<%-- ���~��C --%>
				<c:if test="${not empty errorMsgs}">
					<font style="color: red">�Эץ��H�U���~�G</font>
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
										<th>�ӫ~�Ӥ�</th>
										<th class="p-name">�ӫ~�W��</th>
										<th>�H�e�覡</th>
										<th>�H�e�a�}</th>
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
												<option value=TM0>�W�Ө��f</option>
												<option value=TM1 selected>�v�t</option>
												<option value=TM2>����</option>
										</select></td>
										<td>
											<div class="city-selector-set">

												<div class=box1>
													<!-- �l���ϸ���� (��ĳ�[�J readonly �ݩʡA����ק�) -->
													<input class="zipcode" type="text" size="3" readonly
														placeholder="">
												</div>

												<div class=box1>
													<!-- ������� -->
													<select class="county"></select>
												</div>

												<div class=box1>
													<!-- �ϰ��� -->
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
										����</button>
								</div>
							</div>

							<div>
								<button id="123">���_�p���s</button>
							</div>

							<div class="col-lg-4 offset-lg-4">
								<div class="proceed-checkout">
									<ul>
										<li class="cart-total">���� <span style="color: red;">${uprodVO.price}
												<span style="color: black; padding-left: 3px;">��</span>
										</span>
										</li>
									</ul>
									<input type="hidden" name="action" value="buy"> <input
										type="hidden" name="ProdNo" value="<%=uprodVO.getProdNo()%>" />
									<input type="hidden" name="CustNo"
										value="<%=memVO.getMemNo()%>" />
									<button type="submit" class="proceed-btn buy_prod"
										style="background-color: #E7AB3C;">�T�{�ʶR</button>
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
			$("#456").val("�O���90��12��");
			event.preventDefault();
		})

		new TwCitySelector({
			el : '.city-selector-set',
			elCounty : '.county', // �b el �̬d�� element
			elDistrict : '.district', // �b el �̬d�� element
			elZipcode : '.zipcode' // �b el �̬d�� element
		});
	</script>
</body>
</html>