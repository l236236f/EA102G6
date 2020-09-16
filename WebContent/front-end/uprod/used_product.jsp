<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 設定時間 -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

<%@ page import="java.util.*"%>
<%@ page import="com.uprod.model.*"%>

<!DOCTYPE html>
<html lang="zxx">
<head>
    <meta charset="UTF-8">
    <meta name="description" content="Fashi Template">
    <meta name="keywords" content="Fashi, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>二手市集 - ${uprodVO.prodName}</title>
</head>

<body>
	<%@ include file="/front-end/Header/header.jsp" %>
	
	<%
		UprodVO uprodVO = (UprodVO) request.getAttribute("uprodVO");
		UprodService uprodSvc = new UprodService();
		pageContext.setAttribute("UprodVO", uprodVO);
	%>
	
	<jsp:useBean id="memberSvc" scope="page" class="com.mem.model.MemService" />
	
<!-- 	    Breadcrumb Section Begin -->
<!--     <div class="breacrumb-section" -->
<%--     	 style="height: 300px;  --%>
<%--     			background-image: url(<%=request.getContextPath()%>/front-end/uprod/image/banner/uprod_banner.jpg); --%>
<!--     			background-position: center; -->
<!--     			background-size: unset; -->
<%--     			background-repeat: no-repeat;"> --%>
<!--     </div> -->
<!--     Breadcrumb Section Begin -->

    <!-- Product Shop Section Begin -->
	<section class="product-shop spad page-details">
		<input type="hidden" id="ProdNo" value="${uprodVO.prodNo}">
		<div class="container">
			<div class="row">
				<div class="col-lg-1"></div>
				<div class="col-lg-9">
					<div class="row">
						<div class="col-lg-6">
							<div id="carouselExampleControls" class="carousel slide"
								data-ride="carousel">
								<div class="carousel-inner">
									<!-- 商品圖片 -->
									<img src="<%=request.getContextPath()%>/front-end/uprod/showpic.do?ProdNo=${uprodVO.getProdNo()}">
								</div>
							</div>
						</div>
						<div class="col-lg-6">
							<div class="product-details">
								<div class="pd-title">
									<!--商品名稱-->	
									<h3>${uprodVO.prodName}</h3>
								</div>
								<div class="pd-desc" style="margin-top:20px;">
									<h4>價格: ${uprodVO.price} 元</h4>
								</div>
								
								<FORM METHOD="post" ACTION="uprod.do" name="form1" >
								<div class="quantity" style="margin-top:50px;">
									<input type="hidden" name="ProdNo"  value="${uprodVO.getProdNo()}">
									<input type="hidden" name="update1" value="1">
									<input type="hidden" name="action"	value="getOne_For_Update">
									<button type="submit" class="primary-btn pd-cart">購買商品</button>										
								</div>
								</FORM>
								
<%-- 								<c:if test="${memVO!=null}"> --%>
								<!-- 追蹤 -->
<!-- 								<div class="icon"> -->
<!-- 									<div> -->
<%-- 										<%boolean exist = false; %> --%>
<%-- 											<c:forEach var="fpVO" items="${favProductSvc.all}"> --%>
<%-- 												<c:if test="${fpVO.prod_no.equals(productVO.prod_no)&&fpVO.mem_no.equals(memVO.mem_no)}"> --%>
<%-- 													<%exist = true; %> --%>
<%-- 												</c:if> --%>
<%-- 											</c:forEach> --%>
<%-- 											<c:if test="<%=exist!=true%>"> --%>
<!-- 											<button class="addToFav" title="收藏"  -->
<!-- 											style="background-color:transparent;border:none;"> -->
<!-- 												<i class="icon_heart_alt" style="font-size:1.5rem"></i><span>加入收藏</span> -->
<!-- 											</button> -->
<!-- 											<input type="hidden" class="action" name="action" value="add_to_fav"> -->
<%-- 											</c:if> --%>
<%-- 											<c:if test="<%=exist==true%>"> --%>
<!-- 											<button class="addToFav" title="取消收藏"  -->
<!-- 											style="background-color:transparent;border:none;"> -->
<!-- 												<i class="icon_heart" style="font-size:1.5rem"></i><span>取消收藏</span> -->
<!-- 											</button> -->
<!-- 											<input type="hidden" class="action" name="action" value="remove_from_fav"> -->
<%-- 											</c:if> --%>
											
<%-- 											<input type="hidden" class="mem_no" name="mem_no" value="${memVO.mem_no}">  --%>
<%-- 											<input type="hidden" class="prod_no" name="prod_no" value="${productVO.getProd_no()}"> --%>
<!-- 									</div> -->
									
								<!-- 檢舉 -->
<!--     								<button style="padding:5px; margin-right:10px; background-color:transparent; border:none;" -->
<%--     								data-toggle="modal" data-target="#<%=productVO.getProd_no()%>"> --%>
<!--     								<i class="icon_error-circle" style="font-size:1.5rem"></i><span>檢舉</span> -->
<!--     								</button> -->
<%--     								<div class="modal fade" id="<%=productVO.getProd_no()%>" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true"> --%>
<!-- 									  <div class="modal-dialog modal-dialog-centered" role="document"> -->
<!-- 									    <div class="modal-content"> -->
<!-- 									      <div class="modal-header"> -->
<!-- 									        <h5 class="modal-title" id="exampleModalCenterTitle">檢舉商品</h5> -->
<!-- 									        <button type="button" class="close" data-dismiss="modal" aria-label="Close"> -->
<!-- 									          <span aria-hidden="true">&times;</span> -->
<!-- 									        </button> -->
<!-- 									      </div> -->
<!-- 										      <div class="modal-body" style="text-align:left; font-size:1.15rem"> -->
<!-- 										      	<div class="form-group" style="text-align:left"> -->
<!-- 										            <label for="recipient-name" class="col-form-label">商品名稱:</label> -->
<!-- 										            <input type="text" class="form-control" disabled  -->
<%-- 										            style="background-color:none; border:none;" value="<%=productVO.getProd_name()%>"> --%>
<!-- 										        </div> -->
<!-- 										        <div class="form-group"> -->
<!-- 										            <label for="message-text" class="col-form-label">檢舉原因:</label> -->
<!-- 										            <textarea class="form-control rep_reason" name="rep_reason" placeholder="此為必填欄位"></textarea> -->
<!-- 										        </div> -->
<%-- 										        <input type="hidden" class="prod_no" name="prod_no" value="<%=productVO.getProd_no()%>"> --%>
<%-- 												<input type="hidden" class="mem_no" name="mem_no" value="${memVO.mem_no}"> --%>
<!-- 									     		<input type="hidden" name="action" value="report_prod"> -->
<!-- 										      </div> -->
<!-- 										      <div class="modal-footer"> -->
<!-- 										        <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button> -->
<!-- 										        <button type="submit" class="btn btn-primary rep_submit" data-dismiss="modal" style="background-color:#7f0000; border:#7f0000;">確認</button> -->
<!-- 										      </div> -->
<!-- 									    </div> -->
<!-- 									  </div> -->
<!-- 									</div> -->

<%-- 									</c:if> --%>
								</div>
								
								<!--商品其他資訊-->
								<ul class="pd-tags" style="margin-left:25px;">
									<li style="margin-top:10px;"><span>賣家 :</span>
										<c:forEach var="memVO" items="${memberSvc.all}">
	                    					<c:if test="${uprodVO.sellerNo==memVO.memNo}">
		                    					<a>${memVO.memName}</a>
	                    					</c:if>
	                					</c:forEach>
									</li>
									<li style="margin-top:10px;"><span>商品種類 :</span>
										<a>
										<c:if test="${uprodVO.prodType=='A'}">毛孩伙食</c:if>
									    <c:if test="${uprodVO.prodType=='B'}">日常用品</c:if>
									    <c:if test="${uprodVO.prodType=='C'}">清潔用品</c:if>
										</a>
		    						</li>
									<li style="margin-top:10px;"><span>上架時間 :</span>
										<fmt:formatDate value="${uprodVO.increaseTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
									</li>
								</ul>
							</div>
						</div>
					</div>
					
					<div class="product-tab">
						<div class="tab-item">
							<ul class="nav" role="tablist">
								<li><a class="active" data-toggle="tab" href="#tab-1"
								    role="tab" style="width: 230px; text-align: center;">商品描述</a></li>
<!-- 								<li><a data-toggle="tab" href="#tab-2" role="tab" -->
<!-- 									style="width: 230px; text-align: center">商品規格</a></li> -->
<!-- 								<li><a data-toggle="tab" href="#tab-3" role="tab" -->
<!-- 									style="width: 230px; text-align: center">商品留言</a></li> -->
							</ul>
						</div>
						<div class="tab-item-content">
							<div class="tab-content">
							
								<div class="tab-pane fade-in active" id="tab-1" role="tabpanel">
									<div class="product-content">
										<div class="row">
											<div class="col-lg-10">
												<h5>商品介紹</h5>
												<p>${uprodVO.prodIntro}</p>
											</div>
										</div>
									</div>
								</div>
								
								<div class="tab-pane fade" id="tab-3" role="tabpanel">
<!-- 									<div class="customer-review-option"> -->
<%-- 										<h4><%=productVO.getVal_count()%>評價</h4> --%>
<!-- 										<div class="comment-option col-lg-12"> -->
<%-- 										<%for(int i=0; i<pelist.size(); i++){  --%>
<%-- 											int star_count = pelist.get(i).getEval_star();%> --%>
<!-- 											<div class="co-item"> -->
<!-- 												<div class="avatar-pic"> -->
<%-- 													<img src="<%=request.getContextPath()%>/front-end/GetPhotos?photo_no=<%=pelist.get(i).getMem_no()%>&type=member"> --%>
<!-- 												</div> -->
<!-- 												<div class="avatar-text"> -->
<!-- 													<div class="at-rating"> -->
<%-- 													<%for(int j=0; j<star_count; j++){%> --%>
<!-- 														<i class="fa fa-star"></i> -->
<%-- 													<%}  --%>
<%-- 											  		for(int j=0; j<5-star_count; j++){%>	 --%>
<!-- 											  			<i class="fa fa-star-o"></i> -->
<%-- 											  		<%} %> --%>
<!-- 													</div> -->
<!-- 													<h5> -->
<%-- 														<%mVO = memberSvc.getOneMemberInfo2(pelist.get(i).getMem_no()); %> --%>
<%-- 														<%=mVO.getMem_name()%>  --%>
<%-- 														<span><fmt:formatDate value="<%=pelist.get(i).getEval_time()%>" type="time" pattern="yyyy/MM/dd HH:mm"/></span> --%>
<!-- 													</h5> -->
<!-- 													<div class="at-reply"> -->
<!-- 														<h5> -->
<%-- 															<%if(pelist.get(i).getEval_content()!=null){ %> --%>
<%-- 																<%=pelist.get(i).getEval_content()%> --%>
<%-- 															<%} %> --%>
<!-- 														</h5> -->
<!-- 													</div> -->
<!-- 												</div> -->
<!-- 											</div> -->
<%-- 										<%} %> --%>
<!-- 										</div> -->
<!-- 									</div> -->
								</div>
							</div>
						</div>
						
					</div>
				</div>
			</div>
		</div>
	</section>
    <!-- Partner Logo Section End -->
    
    <%@ include file="/front-end/Footer/footer.jsp" %>

</body>
</html>