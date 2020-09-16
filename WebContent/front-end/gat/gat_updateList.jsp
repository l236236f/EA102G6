<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.gat.model.*"%>
<%@ page import="com.gat_detail.model.*"%>
<%@ page import="com.gat_eva.model.*"%>

<%

	GatService gatSvc = new GatService();
	pageContext.setAttribute("gatSvc", gatSvc);
	List<GatVO> list = gatSvc.getAllForUpdateList();
	pageContext.setAttribute("list", list);
	
	GatDetailService gatDetailSvc = new GatDetailService();
	List<GatDetailVO> listDetail = gatDetailSvc.getAll();
	pageContext.setAttribute("listDetail", listDetail);
	
	GatEvaService gatEvaSvc = new GatEvaService();
	pageContext.setAttribute("gatEvaSvc", gatEvaSvc);
	List<GatEvaVO> listEva = gatEvaSvc.getAll();
	pageContext.setAttribute("listEva", listEva);

%>
<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService" />

<!DOCTYPE html>
<html>
<%@ include file="/front-end/Header/header.jsp"%>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.3/sweetalert2.css" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.3/sweetalert2.js" type="text/javascript"></script>

<head>
<title>管理列表</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/gat/style/style_gat_updateList.css" />
 <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
</head>
<body>
	<!-- bradcam_area_start -->
	<div id="benner_bg">
		<div id="gat_wrapper">
			<video autoplay muted loop id="myVideo">
				<source src="images/gat_updatelist.mp4" type="video/mp4">
			</video>
		</div>
		<div class="gat_container">
			<div class="gat_row">
				<div class="col-lg-12">
					<div class="bradcam_text gat_text">
						<h3>揪團列表管理</h3>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- bradcam_area_end -->

	<div class="main-content-wrapper gat-section-padding-100">
		<div class="container">
			<!-- 	-------------------------------------------------------------------------------- -->
			<div class="row justify-content-center">
				<!-- ============= Post Content Area Start ============= -->
				<div class="col-12 col-lg-12">
					<div class="post-content-area mb-50">
						<!-- Catagory Area -->
						<div class="world-catagory-area">
							<ul class="nav nav-tabs-gat" id="myTab" role="tablist">
<%-- 							${LoginMem.memName} --%>
								<li class="title"><i class="fa fa-paw" style="margin-right: 10px;"></i>揪團管理</li>
								<li class="nav-item-gat"><a class="nav-link active"
									id="tab10" data-toggle="tab" href="#world-tab-1" role="tab"
									aria-controls="world-tab-10" aria-selected="true">已建立的揪團</a></li>

								<li class="nav-item-gat"><a class="nav-link" id="tab11"
									data-toggle="tab" href="#world-tab-2" role="tab"
									aria-controls="world-tab-11" aria-selected="false">已報名的揪團</a></li>

							</ul>

							<div class="tab-content" id="myTabContent">

								<div class="tab-pane fade show active" id="world-tab-1"
									role="tabpanel" aria-labelledby="tab1">
									<div class="row">
										<div class="col-12 col-md-12">

											<c:forEach var="gatVO" items="${list}" varStatus="gatIndex">
												<c:if test='${gatVO.memNo.equals(LoginMem.memNo)}'>
													<div class="single-blog-post">
														<div class="gat-post-thumbnail"
															style="background-image: url(<%=request.getContextPath()%>/front-end/ShowPhotos?type=gatPhoto&photo_no=${gatVO.gatNo});">
														</div>
														<div class="post-content">
															<a href="gat_listOne.jsp?gatNo=${gatVO.gatNo}"
																class="headline">
																<h5>${gatVO.gatName}</h5>
															</a>
															<ul class="gat-info">
																<li><i class="fa fa-clock-o"></i> <fmt:setLocale
																		value="en_US" /> <fmt:formatDate
																		value="${gatVO.gatTime}" pattern="EEEE MMM. d, yyyy" /></li>
																<li><i class="fa fa-map-marker" aria-hidden="true"></i>${gatVO.gatLoc}</li>
																<li><i class="fa fa-calendar-check-o"
																	aria-hidden="true"></i> <fmt:setLocale value="en_US" />
																	<fmt:formatDate value="${gatVO.gatStarttime}"
																		pattern="MMM. d, yyyy" /></li>
																<li><i class="fa fa-calendar-times-o"
																	aria-hidden="true"></i> <fmt:setLocale value="en_US" />
																	<fmt:formatDate value="${gatVO.gatEndtime}"
																		pattern="MMM. d, yyyy" /></li>
																<%-- 																<li class="gatStatus_li">${gatVO.gatStatus}</li> --%>
															</ul>

															<c:if test='${gatVO.gatStatus.equals("G0") || gatVO.gatStatus.equals("G1")}'>
																<FORM METHOD="post" ACTION="gat.do"
																	style="margin-bottom: 0px;">
																	<input type="hidden" name="photo"
																		value="<%=request.getContextPath()%>/front-end/ShowPhotos?type=gatPhoto&photo_no=${gatVO.gatNo}">
																	<div class="gatbtn">
																		<label class="btn gatbtn-info"
																			style="top: -25px; right: 118px;"> <input
																			style="display: none;" type="button"
																			class="memNo_btn" data-toggle="modal"
																			data-target="#exampleModalCenter_${gatVO.gatNo}">
																			<i class="fa fa-cog"></i> 管理團員名單
																		</label>
																	</div>
																	<div class="gatbtn">
																		<label class="btn gatbtn-info"> <input
																			style="display: none;" type="submit"
																			class="memNo_btn"> <i class="fa fa-cog"></i>
																			修改內容
																		</label>
																	</div>
																	<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>">
																	<input type="hidden" name="gatNo"
																		value="${gatVO.gatNo}"> <input type="hidden"
																		name="action" value="getOne_For_Update">
																</FORM>
															</c:if>
															<!-- 狀態按鈕 -->
															<div class="gatbtn">
																<c:if test='${gatVO.gatStatus.equals("G0") || gatVO.gatStatus.equals("G1")}'>
																	<div class="statuscontent">
																		<select id="gatStatus_select_${gatIndex.index}" name="gatStatus">
																			<option value="G0"
																				<c:if test='${gatVO.gatStatus.equals("G0")}'> selected </c:if>>開放報名中</option>
																			<option value="G1"
																				<c:if test='${gatVO.gatStatus.equals("G1")}'> selected </c:if>>因故取消</option>
																		</select> <label class="btn btn-status"> <i
																			class="fa fa-exclamation-circle"></i> <input
																			id="statusbtn_${gatIndex.index}" style="display: none;"
																			type="submit"> <input type="hidden"
																			id="statusbtn_gatNo_${gatIndex.index}" name="gatNo"
																			value="${gatVO.gatNo}"> <input type="hidden"
																			id="statusbtn_memNo_${gatIndex.index}" name="memNo"
																			value="${LoginMem.memNo}">
																		</label>
																	</div>
																</c:if>
																<c:if test='${gatVO.gatStatus.equals("G2")}'>
																	<div class="offline">
																		<div>已下架</div>
																	</div>
																</c:if>
																<c:if test='${gatVO.gatStatus.equals("G3")}'>
																	<div class="offline">
																		<div>活動已結束</div>
																	</div>
																</c:if>
															</div>
															<!-- 狀態按鈕 -->
														</div>
													</div>

												</c:if>
											</c:forEach>
										</div>
									</div>
								</div>
								<!-- MODAL -->
								<c:forEach var="gatVO" items="${list}">
									<c:if test='${gatVO.memNo.equals(LoginMem.memNo)}'>
										<div class="modal fade" id="exampleModalCenter_${gatVO.gatNo}"
											tabindex="-1" role="dialog"
											aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
											<div class="modal-dialog modal-dialog-centered"
												role="document">
												<div class="modal-content">
													<div class="modal-header">
														<h5 class="modal-title" id="exampleModalLongTitle"><b>${gatVO.gatName}</b></h5>
														<button type="button" class="close" data-dismiss="modal"
															aria-label="Close">
															<span aria-hidden="true">&times;</span>
														</button>
													</div>
													<div class="modal-body">
														<div class="leader-info">
															<i class="fa fa-star"></i> ${memSvc.getOneMem(gatVO.memNo).memName}
														</div>
														<c:forEach var="gatDetailVO" items="${listDetail}">
															<c:if test='${gatDetailVO.gatNo.equals(gatVO.gatNo)}'>
																<div class="member-info">
																	<i class="fa fa-user" style="margin-right: 3px;"></i> ${memSvc.getOneMem(gatDetailVO.memNo).memName}
																<div class="member-infoedit">
																	<input type="hidden" class="remove_memNo" name="memNo" value="${gatDetailVO.memNo}">
																	<input type="hidden" class="remove_gatNo" name="gatNo" value="${gatVO.gatNo}">
																	<button type="button" class="btn member-infobtn removemember">剔除</button>
																</div>
																</div>
															</c:if>
														</c:forEach>
													</div>
												</div>
											</div>
										</div>
									</c:if>
								</c:forEach>
								<!-- MODAL -->

								<!-- 已報名的揪團頁面 -->
								<div class="tab-pane fade" id="world-tab-2" role="tabpanel"
									aria-labelledby="tab1">
									<div class="row">
										<div class="col-12 col-md-12">
											<c:forEach var="gatDetailVO" items="${listDetail}">
												<c:if test='${gatDetailVO.memNo.equals(LoginMem.memNo)}'>
													<c:forEach var="gatVO" items="${list}" varStatus="joinlist">
														<c:if test='${gatVO.gatNo.equals(gatDetailVO.gatNo)}'>
															<div class="single-blog-post">
																<div class="gat-post-thumbnail"
																	style="background-image: url(<%=request.getContextPath()%>/front-end/ShowPhotos?type=gatPhoto&photo_no=${gatVO.gatNo});">
																</div>
																<div class="post-content">
																	<a href="gat_listOne.jsp?gatNo=${gatVO.gatNo}"
																		class="headline">
																		<h5>${gatVO.gatName}</h5>
																	</a>
																	<ul class="gat-info">
																		<li><i class="fa fa-clock-o"></i> <fmt:setLocale
																				value="en_US" /> <fmt:formatDate
																				value="${gatVO.gatTime}" pattern="EEEE MMM. d, yyyy" /></li>
																		<li><i class="fa fa-map-marker"
																			aria-hidden="true"></i>${gatVO.gatLoc}</li>
																		<li><i class="fa fa-calendar-check-o"
																			aria-hidden="true"></i> <fmt:setLocale value="en_US" />
																			<fmt:formatDate value="${gatVO.gatStarttime}"
																				pattern="MMM. d, yyyy" /></li>
																		<li><i class="fa fa-calendar-times-o"
																			aria-hidden="true"></i> <fmt:setLocale value="en_US" />
																			<fmt:formatDate value="${gatVO.gatEndtime}"
																				pattern="MMM. d, yyyy" /></li>
																	</ul>

																	<c:if test='${gatVO.gatStatus.equals("G0") || gatVO.gatStatus.equals("G1")}'>
																		<input type="hidden" name="photo"
																			value="<%=request.getContextPath()%>/front-end/ShowPhotos?type=gatPhoto&photo_no=${gatVO.gatNo}">
																		<div class="gatbtn">
																			<label class="btn gatbtn-info"> <i
																				class="fa fa-times-circle"></i> 取消報名 <input
																				id="cancelbtn_${joinlist.index}" style="display: none;"
																				type="submit"> <input
																				id="cancelbtn_gatNo_${joinlist.index}" type="hidden" name="gatNo"
																				value="${gatVO.gatNo}"> <input
																				id="cancelbtn_memNo_${joinlist.index}" type="hidden" name="memNo"
																				value="${LoginMem.memNo}">
																			</label>
																		</div>
																	</c:if>
																	<c:if test='${gatVO.gatStatus.equals("G2")}'>
																	<div class="offline">
																		<div>已下架</div>
																	</div>
																	</c:if>
																	<c:if test='${gatVO.gatStatus.equals("G3") && gatEvaSvc.getOneGat(LoginMem.memNo, gatVO.gatNo).gatEva == null}'>
																		<input type="hidden" name="photo"
																			value="<%=request.getContextPath()%>/front-end/ShowPhotos?type=gatPhoto&photo_no=${gatVO.gatNo}">
																		<div class="join_offline">
																			<div>活動已結束</div>
																		</div>
																		<div class="gatbtn" id="gatbtn_${gatVO.gatNo}">
																			<label class="btn gatbtn-info gatbtn-eva"> <input
																				style="display: none;" type="button"
																				class="memNo_btn" data-toggle="modal"
																				data-target="#exampleModalCenter_join_${gatVO.gatNo}">
																				<i class="fa fa-star"></i> 評價揪團
																			</label>
																		</div>
																		<div id="eva_star_${gatVO.gatNo}" style="color: #ffc107; position: absolute; right: 35px; font-size: 30px; bottom: 15px;">
																		</div>
																	</c:if>
																	<c:if test='${gatVO.gatStatus.equals("G3") && gatEvaSvc.getOneGat(LoginMem.memNo, gatVO.gatNo).gatEva != null}'>
																		<div class="join_offline">
																			<div>活動已結束</div>
																		</div>
																		<div class="eva_star" style="color: #ffc107; position: absolute; right: 35px; font-size: 30px; bottom: 15px;">
																			<c:forEach var="gatEvaVO" items="${listEva}">
																				<c:if test="${gatEvaVO.gatNo.equals(gatVO.gatNo) && gatEvaVO.memNo.equals(LoginMem.memNo)}">
																					<c:if test="${gatEvaVO.gatEva == 1.0}">
																						<i class="fa fa-star"></i>
																						<i class="fa fa-star-o"></i>
																						<i class="fa fa-star-o"></i>
																						<i class="fa fa-star-o"></i>
																						<i class="fa fa-star-o"></i>
																					</c:if>
																					<c:if test="${gatEvaVO.gatEva == 2.0}">
																						<i class="fa fa-star"></i>
																						<i class="fa fa-star"></i>
																						<i class="fa fa-star-o"></i>
																						<i class="fa fa-star-o"></i>
																						<i class="fa fa-star-o"></i>
																					</c:if>
																					<c:if test="${gatEvaVO.gatEva == 3.0}">
																						<i class="fa fa-star"></i>
																						<i class="fa fa-star"></i>
																						<i class="fa fa-star"></i>
																						<i class="fa fa-star-o"></i>
																						<i class="fa fa-star-o"></i>
																					</c:if>
																					<c:if test="${gatEvaVO.gatEva == 4.0}">
																						<i class="fa fa-star"></i>
																						<i class="fa fa-star"></i>
																						<i class="fa fa-star"></i>
																						<i class="fa fa-star"></i>
																						<i class="fa fa-star-o"></i>
																					</c:if>
																					<c:if test="${gatEvaVO.gatEva == 5.0}">
																						<i class="fa fa-star"></i>
																						<i class="fa fa-star"></i>
																						<i class="fa fa-star"></i>
																						<i class="fa fa-star"></i>
																						<i class="fa fa-star"></i>
																					</c:if>
																				</c:if>
																			</c:forEach>	
																		</div>
																	</c:if>
																</div>
															</div>
														</c:if>
													</c:forEach>
												</c:if>
											</c:forEach>
										</div>
									</div>
								</div>
								<!-- MODAL -->
								<c:forEach var="gatDetailVO" items="${listDetail}" varStatus="gatEva">
									<c:if test='${gatDetailVO.memNo.equals(LoginMem.memNo)}'>
										<div class="modal fade" id="exampleModalCenter_join_${gatDetailVO.gatNo}"
											tabindex="-1" role="dialog"
											aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
											<div class="modal-dialog modal-dialog-centered"
												role="document">
												<div class="modal-content">
													<div class="modal-header" style="display: block;">
														<div class="gatEva_header"　style="font-size: 20px;">
															<b>${gatSvc.getOneGat(gatDetailVO.gatNo).gatName}</b>
														</div>
													</div>
													<div class="modal-body">
															<div class="rating-stars block">
														        <div class="rating-stars-container">
														            <div class="rating-star">
														                <i class="fa fa-star"></i>
														            </div>
														            <div class="rating-star">
														                <i class="fa fa-star"></i>
														            </div>
														            <div class="rating-star">
														                <i class="fa fa-star"></i>
														            </div>
														            <div class="rating-star">
														                <i class="fa fa-star"></i>
														            </div>
														            <div class="rating-star">
														                <i class="fa fa-star"></i>
														            </div>
														        </div>
														    </div>
													</div>
													<div class="modal-footer">
														 <div class="gatEva_btn">
															<input type="hidden" id="eva_memNo_${gatEva.index}" name="eva_memNo" value="${LoginMem.memNo}">
															<input type="hidden" id="eva_gatNo_${gatEva.index}" name="eva_gatNo" value="${gatDetailVO.gatNo}">
															<label class="btn gatevabtn">
																<input style="display: none;" type="button" id="eva_btn_${gatEva.index}" data-dismiss="modal">
																<i class="fa fa-star"></i> 確認評價
															</label>
													      </div>
													</div>
												</div>
											</div>
										</div>
									</c:if>
								</c:forEach>
								<!-- MODAL -->
							</div>
						</div>

					</div>
				</div>
			</div>
		</div>

		<%@ include file="/front-end/Footer/footer.jsp"%>
</body>
<script>
<%for(int a = 0; a < list.size(); a++){ %>
$("#statusbtn_" + <%= a%>).click(function(){
	var memNo = $('#statusbtn_memNo_' + <%= a%> + '[name="memNo"]').val();
	var gatNo = $('#statusbtn_gatNo_' + <%= a%> + '[name="gatNo"]').val();
	var gatStatus = $('#gatStatus_select_' + <%= a%> + '[name="gatStatus"]').val();
	console.log(memNo);
	console.log(gatNo);
	console.log(gatStatus);
	
	$.ajax({
		url: "<%=request.getContextPath()%>/front-end/gat/gatdetail.do",
			type : "POST",
			data : {
				"action" : "updateStatus",
				"memNo" : memNo,
				"gatNo" : gatNo,
				"gatStatus" : gatStatus,
			}
		});
	swal('狀態修改成功', '還敢修改狀態阿', 'success');
	});
<%}%>
<%for(int b = 0; b < list.size(); b++){ %>
$("#cancelbtn_" + <%= b%>).click(function(){
	var memNo = $('#cancelbtn_memNo_' + <%= b%> + '[name="memNo"]').val();
	var gatNo = $('#cancelbtn_gatNo_' + <%= b%> + '[name="gatNo"]').val();
	console.log(memNo);
	console.log(gatNo);
	
	$.ajax({
		url: "<%=request.getContextPath()%>/front-end/gat/gatdetail.do",
			type : "POST",
			data : {
				"action" : "gat_cancel",
				"memNo" : memNo,
				"gatNo" : gatNo
			}
		});
		swal('取消成功', '還敢取消阿', 'success');
		$(this).parents(".single-blog-post").remove();
	});
<%}%>
$(".removemember").click(function(){
	var memNo = $(this).siblings('.remove_memNo[name="memNo"]').val();
	var gatNo = $(this).siblings('.remove_gatNo[name="gatNo"]').val();
	console.log('剔除' + memNo);
	console.log('剔除' + gatNo);
	
	$.ajax({
		url: "<%=request.getContextPath()%>/front-end/gat/gatdetail.do",
			type : "POST",
			data : {
				"action" : "mem_cancel",
				"memNo" : memNo,
				"gatNo" : gatNo
			}
		});
		swal('剔除成功', '踢得好', 'success');
		$(this).parents(".member-info").remove();
	});
	
</script>
<script src="rating/jquery.rating-stars.min.js"></script>
<script>
        var ratingOptions = {
            selectors: {
                starsSelector: '.rating-stars',
                starSelector: '.rating-star',
                starActiveClass: 'is--active',
                starHoverClass: 'is--hover',
                starNoHoverClass: 'is--no-hover',
                targetFormElementSelector: '.rating-value'
            }
        };
		
        var gatEva;
        
        $(".rating-stars").ratingStars(ratingOptions);

        $(".rating-stars").on("ratingChanged", function (ev, data) {
            $("#ratingChanged").html(data.ratingValue);
            console.log("test = "+data.ratingValue);
            gatEva = data.ratingValue;
        });

        $(".rating-stars").on("ratingOnEnter", function (ev, data) {
            $("#ratingOnEnter").html(data.ratingValue);
        });

        $(".rating-stars").on("ratingOnLeave", function (ev, data) {
            $("#ratingOnLeave").html(data.ratingValue);
        });
        
       <% for(int i = 0; i < listDetail.size(); i++){ %>
        $("#eva_btn_" + <%= i %>).click(function(){
        	if (gatEva == null) {
                alert('評價最低為一顆星!');
                return;
            }
        	var memNo = $('#eva_memNo_' + <%= i %> + '[name="eva_memNo"]').val();
        	var gatNo = $('#eva_gatNo_' + <%= i %> + '[name="eva_gatNo"]').val();
        	console.log("eva_memNo: " + memNo);
        	console.log("eva_gatNo: " + gatNo);
        	console.log("gatEva: " + gatEva);
        	$.ajax({
        		url: "<%=request.getContextPath()%>/front-end/gat/gatdetail.do",
				type : "POST",
				data : {
						"action" : "eva_add",
						"memNo" : memNo,
						"gatNo" : gatNo,
						"gatEva" : gatEva
						},
				success: function(data){
					swal('評價成功', '其實評價不影響揪團', 'success');
					$("#gatbtn_" + gatNo).hide();
					var stern = '';
					var etoile = '';
					for(var x = 0; x < gatEva; x++){
						stern += '<i class="fa fa-star" style="margin: 0 3px;"></i>';
					}
					for(var y = 0; y < (5-gatEva); y++){
						etoile += '<i class="fa fa-star-o" style="margin: 0 3px;"></i>';
					}
					$("#eva_star_" + gatNo).append(stern).append(etoile);
				}
        	});
        	
        });
       <% } %>
</script>
</html>