<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.gat.model.*"%>
<%@ page import="com.gat_detail.model.*"%>
<%@ page import="com.gat_res.model.*"%>

<%
	String gatNo = request.getParameter("gatNo");

	GatService gatSvc = new GatService();
	GatVO gatVO_One = gatSvc.getOneGat(gatNo);
	pageContext.setAttribute("gatVO_One", gatVO_One);

	GatDetailService gatDetailSvc = new GatDetailService();
	List<GatDetailVO> listDetail = gatDetailSvc.getAll();
	pageContext.setAttribute("listDetail", listDetail);

	String memNo = gatVO_One.getMemNo();
	MemService memSvc = new MemService();
	pageContext.setAttribute("memSvc", memSvc);

	MemVO memVO2 = memSvc.getOneMem(memNo);

	GatResService gatResSvc = new GatResService();
	pageContext.setAttribute("gatResSvc", gatResSvc);
	List<GatResVO> listRes = gatResSvc.getAll();
	pageContext.setAttribute("listRes", listRes);
	
%>
<!DOCTYPE html>
<html>
<%@ include file="/front-end/Header/header.jsp"%>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.3/sweetalert2.css" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.3/sweetalert2.js"
	type="text/javascript"></script>
<head>
<title>${gatVO_One.gatName}</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/gat/style/style_gat_listOne.css" />
</head>
<body>
	<!-- bradcam_area_start -->
	<div id="benner_bg" style="background-image: url(<%=request.getContextPath()%>/front-end/gat/images/banner_dog.png);">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div class="bradcam_text text-center">
						<h3>${gatVO_One.gatName}</h3>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- bradcam_area_end -->
	<!--================Blog Area =================-->
	<section class="blog_area gat_section-padding">
		<div class="container">
			<div class="row">
				<div class="col-lg-8 mb-5 mb-lg-0">

					<div class="blog_left_sidebar" id="default_gatalllist">

						<article class="blog_item">
							<div class="gatlist-wrapper">
<!-- 								<div class="gatTime_wrapper"> -->
<!-- 									<time datetime="2014-09-20" class="icon"> -->
<%-- 										<fmt:setLocale value="en_US"/> --%>
<%-- 										<em><fmt:formatDate value="${gatVO_One.gatTime}" pattern="EEEE"/></em> --%>
<%-- 									 	<strong><fmt:formatDate value="${gatVO_One.gatTime}" pattern="MMMM"/></strong> --%>
<%-- 									 	<span><fmt:formatDate value="${gatVO_One.gatTime}" pattern="d"/></span> --%>
<!-- 									</time> -->
<!-- 								</div> -->
								<div class="blog_item_img"
									style="background-image: url(<%=request.getContextPath()%>/front-end/ShowPhotos?type=gatPhoto&photo_no=${gatVO_One.gatNo});">
								</div>

							</div>

							<div class="blog_details">
								<h2>${gatVO_One.gatName}</h2>
								<ul class="blog-info-link">
									<li><i class="fa fa-map-marker" aria-hidden="true" style="margin-right: 7px; font-size: 18px;"></i>${gatVO_One.gatLoc}</li>
								</ul>
								<p>${gatVO_One.gatIntro}</p>
								<ul class="gat-info">
									<li><i class="fa fa-calendar-check-o" aria-hidden="true"></i>
										<fmt:setLocale value="en_US" /> <fmt:formatDate
											value="${gatVO_One.gatStarttime}" pattern="MMM. d, yyyy" /></li>
									<li><i class="fa fa-calendar-times-o" aria-hidden="true"></i>
										<fmt:setLocale value="en_US" /> <fmt:formatDate
											value="${gatVO_One.gatEndtime}" pattern="MMM. d, yyyy" /></li>
									<li><i class="fa fa-tags"></i>${gatVO_One.gatType}</li>
								</ul>
								<c:if test="${!gatVO_One.memNo.equals(LoginMem.memNo)}">
									<%
										boolean joined = false;
									%>
									<c:forEach var="gatDetailVO" items="${listDetail}">
										<c:if
											test="${gatDetailVO.gatNo.equals(gatVO_One.gatNo) && gatDetailVO.memNo.equals(LoginMem.memNo)}">
											<%
												joined = true;
											%>
										</c:if>
									</c:forEach>
									<c:if test="<%=joined != true%>">
										<div class="gatbtn">
											<button class="btn btn-info hiddenbtn" id="join_btn">
												<i class="fa fa-paw"></i> ���W���W
											</button>
											<input class="hidden_input" type="hidden" name="action"
												value="gat_join">

										</div>
									</c:if>
									<c:if test="<%=joined == true%>">
										<div class="gatbtn">
											<button class="btn btn-info hiddenbtn" id="cancel_btn"
												style="background-color: #9E9E9E; border-color: #9E9E9E;">
												<i class="fa fa-times-circle"></i> �������W
											</button>
											<input class="hidden_input" type="hidden" name="action"
												value="gat_cancel">

										</div>
									</c:if>
									<input type="hidden" class="gatbtn_memNo" name="memNo"
										value="${LoginMem.memNo}">
									<input type="hidden" class="gatbtn_gatNo" name="gatNo"
										value="<%=gatNo%>">
								</c:if>
								<c:if test="${gatVO_One.memNo.equals(LoginMem.memNo)}">
									<FORM METHOD="post" ACTION="gat.do" style="margin-bottom: 0px;">
										<input type="hidden" name="photo"
											value="<%=request.getContextPath()%>/front-end/ShowPhotos?type=gatPhoto&photo_no=${gatVO_One.gatNo}">
										<div class="gatbtn">
											<label class="btn gatbtn-info btn-info"
												style="right: 118px;"> <input
												style="display: none;" type="button" class="memNo_btn"
												data-toggle="modal"
												data-target="#exampleModalCenter_${gatVO_One.gatNo}">
												<i class="fa fa-cog"></i> �޲z�έ��W��
											</label>
										</div>
										<div class="gatbtn">
											<label class="btn gatbtn-info btn-info"> <input
												style="display: none;" type="submit" class="memNo_btn">
												<i class="fa fa-cog"></i> �ק鷺�e
											</label>
										</div>
										<input type="hidden" name="gatNo" value="${gatVO_One.gatNo}">
										<input type="hidden" name="requestURL"
											value="<%=request.getServletPath()%>"> <input
											type="hidden" name="action" value="getOne_For_Update">
									</FORM>
								</c:if>
							</div>
						</article>


						<!-- MODAL -->
						<c:if test='${gatVO_One.memNo.equals(LoginMem.memNo)}'>
							<div class="modal fade"
								id="exampleModalCenter_${gatVO_One.gatNo}" tabindex="-1"
								role="dialog" aria-labelledby="exampleModalCenterTitle"
								aria-hidden="true">
								<div class="modal-dialog modal-dialog-centered" role="document">
									<div class="modal-content">
										<div class="modal-header">
											<h5 class="modal-title" id="exampleModalLongTitle">
												<b>${gatVO_One.gatName}</b>
											</h5>
											<button type="button" class="close" data-dismiss="modal"
												aria-label="Close">
												<span aria-hidden="true">&times;</span>
											</button>
										</div>
										<div class="modal-body">
											<div class="leader-info">
												<i class="fa fa-star"></i>
												${memSvc.getOneMem(gatVO_One.memNo).memName}
											</div>
											<c:forEach var="gatDetailVO" items="${listDetail}">
												<c:if test='${gatDetailVO.gatNo.equals(gatVO_One.gatNo)}'>
													<div class="member-info">
														<i class="fa fa-user" style="margin-right: 3px;"></i>
														${memSvc.getOneMem(gatDetailVO.memNo).memName}
														<div class="member-infoedit">
															<input type="hidden" class="remove_memNo" name="memNo"
																value="${gatDetailVO.memNo}"> <input
																type="hidden" class="remove_gatNo" name="gatNo"
																value="${gatVO_One.gatNo}">
															<button type="button"
																class="btn member-infobtn removemember">�簣</button>
														</div>
													</div>
												</c:if>
											</c:forEach>
										</div>
									</div>
								</div>
							</div>
						</c:if>
						<!-- MODAL -->

						<div class="address-info">
							<div class="media align-items-center" style="width: 152%;">

								<div class="input-group-icon gatMap">
									<div id="gatLoc_google">
										<h5></h5>
									</div>
									<div class="display">
										<span id="panel"><input type="text" id="keyword" placeholder="�п�J�_�l�a�I�Τ��~��"></span>
										<div class="navtitle_wrapper">
											<div class="nav_wrapper">
												<h5><i class="fa fa-paper-plane" style="margin-right: 7px;"></i> �ɯ��${gatVO_One.gatLoc}</h5>
											</div>
											<div class="radio_wrapper">
												<label> <input
													type="radio" name="travel" value="DRIVING" checked  style="margin-right: 9px;"><i class="fa fa-car" title="�}���e���ت��a"></i>
												</label>
												<label> <input type="radio" name="travel"
													value="BICYCLING"> <i class="fa fa-bicycle" style="font-size: 17px;" title="�H�ۦ樮�e��"></i>
												</label>
												<label> <input type="radio" name="travel"
													value="TRANSIT"> <i class="fa fa-subway" title="�H���@�B��e��"></i>
												</label>
												<label> <input type="radio" name="travel"
													value="WALKING"> <i class="fa fa-male" style="font-size: 18px;" title="�{�B�e��"></i>
												</label>
											</div>
										</div>
										<button id="start">�}�l�ɯ�</button>
										<button id="clear">�M�����</button>
										<div id="map"></div>
									</div>
									
								</div>
								<div class="side_panel_wrapper">
								<div class="side_panel">
										
									<div class="site_list">
										</div>
										<div class="summary">
											<h5 class="result" id="summary_result"></h5>
											<div>
												<span id="time"></span>
											</div>
											<div>
												<span id="distance"></span>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>

						<div class="comments-area">
							<%
								int count = 0;
								for (GatResVO gatResVO : listRes) {
									if (gatResVO.getGatNo().equals(gatNo))
										count++;
								}
							%>
							<div class="count_div">
								<h4><%=(count > 1) ? count + " Comments" : count + " Comment"%></h4>
								<input type="hidden" class="comments_count"
									name="comments_count" value="<%=count%>">
							</div>
							<div class="comment-list">
								<div class="single-comment justify-content-between">
									<div class="datawrapper">
										<c:forEach var="gatResVO" items="${listRes}"
											varStatus="gatComments">
											<c:if test="${gatResVO.gatNo.equals(gatVO_One.gatNo)}">
												<!-- mem question -->
												<div class="mem_question">
													<div class="thumb" style="width: 70px;">
														<img src="images/husky-author.svg">
													</div>
													<div class="desc">
														<p class="comment res_comment">${gatResVO.resCont}</p>
														<div class="d-flex justify-content-between">
															<div class="d-flex align-items-center res_name">
																<h5>${memSvc.getOneMem(gatResVO.memNo).memName}</h5>
																<p class="date">
																	<fmt:setLocale value="en_US" />
																	<fmt:formatDate value="${gatResVO.resTime}"
																		pattern="MMM. d, yyyy" />
																</p>
															</div>
															<c:if test="${LoginMem.memNo.equals(gatVO_One.memNo)}">
																<div class="reply-btn">
																	<button class="btn-reply text-uppercase"
																		id="replyBtn_${gatComments.index}">�^��</button>
																</div>
															</c:if>
														</div>
													</div>
												</div>
												<!-- mem question -->
												<!-- defaultleader answer -->
												<div class="leader_answer"
													id="defaultReply_${gatComments.index}">
													<div class="thumb" style="width: 70px;">
														<img src="images/migru-author.svg">
													</div>
													<div class="desc">
														<c:if test="${gatResVO.resReply == null}">
															<p class="comment">�ΥD�|���^�Ю@�I</p>
															<div class="justify-content-between">
																<div class="align-items-center res_name">
																	<h5>�d���p���F</h5>
																</div>
															</div>
														</c:if>
														<c:if test="${gatResVO.resReply != null}">
															<p class="comment">${gatResVO.resReply}</p>
															<div class="justify-content-between">
																<div class="align-items-center res_name">
																	<h5>${memSvc.getOneMem(gatVO_One.memNo).memName}</h5>
																</div>
															</div>
														</c:if>
													</div>
												</div>
												<!-- defaultleader answer -->
												<!-- realleader answer -->
												<div class="leader_answer realReply"
													id="realReply_${gatComments.index}" style="display: none;">
													<div class="thumb" style="width: 70px;">
														<img src="images/migru-author.svg">
													</div>
													<div class="desc">
														<c:if test="${gatResVO.resReply == null}">
															<textarea id="replyCont_${gatComments.index}"
																name="replyCont" rows="1">�ΥD�|���^�Ю@�I</textarea>
															<input type="hidden" id="resNo_${gatComments.index}"
																name="resNo" value="${gatResVO.resNo}">
															<input type="hidden" id="resMemNo_${gatComments.index}"
																name="resMemNo" value="${gatResSvc.getOneGat(gatResVO.resNo).memNo}">
															<input type="hidden" id="replyName_${gatComments.index}"
																name="replyName"
																value="${memSvc.getOneMem(gatVO_One.memNo).memName}">
															<div class="justify-content-between">
																<div class="align-items-center res_name">
																	<button class="reply_confirm"
																		id="confirm_null_${gatComments.index}">�T�{�^��</button>
																</div>
															</div>
														</c:if>
														<c:if test="${gatResVO.resReply != null}">
															<textarea id="replyCont_${gatComments.index}"
																name="replyCont" rows="1">${gatResVO.resReply}</textarea>
															<input type="hidden" id="resNo_${gatComments.index}"
																name="resNo" value="${gatResVO.resNo}">
															<input type="hidden" id="resMemNo_${gatComments.index}"
																name="resMemNo" value="${gatResSvc.getOneGat(gatResVO.resNo).memNo}">	
															<input type="hidden" id="replyName_${gatComments.index}"
																name="replyName"
																value="${memSvc.getOneMem(gatVO_One.memNo).memName}">
															<div class="justify-content-between">
																<div class="align-items-center res_name">
																	<button class="reply_confirm"
																		id="confirm_${gatComments.index}">�T�{�^��</button>
																</div>
															</div>
														</c:if>
													</div>
												</div>
												<!-- realleader answer -->
											</c:if>
										</c:forEach>
									</div>
									<div class="newcommentwrapper"></div>
								</div>
							</div>


						<c:if test="${!LoginMem.memNo.equals(gatVO_One.memNo)}">
							<div class="comment-form">
								<h4>���Ѧr�ܡH�յۯd���ݰݹΥD�a</h4>
								<div class="row">
									<div class="col-12">
										<div class="form-group">
											<textarea class="form-control w-100 res_cont" name="res_cont"
												id="comment" cols="30" rows="9" placeholder="Write Comment"
												required></textarea>
										</div>
									</div>
								</div>
								<div class="form-group">
									<input type="hidden" class="res_memNo" name="res_memNo"
										value="${LoginMem.memNo}"> <input type="hidden"
										class="res_gatNo" name="res_gatNo" value="<%=gatNo%>">
									<button type="submit"
										class="button button-contactForm btn_1 boxed-btn res_btn">�e�X</button>
									<div class="magicbtn" style="margin-left: 10px;">
										<div id="magicbtn"><i class="fa fa-magic"></i></div>
									</div>
								</div>
							</div>
						</c:if>	
						</div>
					</div>
					<div id="search_gatalllist" style="display: none;"></div>
				</div>
				<div class="col-lg-4" style="height: fit-content;">
					<div class="blog_right_sidebar">
						
						<aside class="single_sidebar_widget tag_cloud_widget" style="background-color: #fff; padding:0;">
							<time datetime="2014-09-20" class="icon">
								<fmt:setLocale value="en_US"/>
								<em><fmt:formatDate value="${gatVO_One.gatTime}" pattern="EEEE"/></em>
							 	<strong><fmt:formatDate value="${gatVO_One.gatTime}" pattern="MMMM"/></strong>
							 	<span><fmt:formatDate value="${gatVO_One.gatTime}" pattern="d"/></span>
							</time>
						</aside>
						
						<aside class="single_sidebar_widget search_widget">
							<div class="blog-author">
								<div class="media align-items-center">
									<!-- 							images/husky-author.svg -->
									<div class="media-body">
										<a href="gat_listAllMem.jsp?memNo=<%=memNo%>">
											<h4><i class="fa fa-paw" style="margin-right: 7px;"></i><%=memVO2.getMemName()%></h4>
										</a>
										<div class="intro_wrapper">
											<div class="intro_icon_wrapper">
												<i class="fa fa-commenting-o" style="margin-left: 2.5px; margin-right: 2.5px;"></i>
											</div>
											<div class="intro_detail_wrapper">
												<%=memVO2.getMemIntro()%>
											</div>
										</div>
										<div class="email_wrapper" >
											<div class="email_icon_wrapper">
												<i class="fa fa-envelope-o" style="margin-right: 7px;"></i>
											</div>
											<div class="email_detail_wrapper">
												<%=memVO2.getMemEmail()%>
											</div>
										</div>
									</div>
									<div class="author-img" style="background-image: url(<%=request.getContextPath()%>/front-end/ShowPhotos?type=mem&photo_no=<%=memNo%>);">
									</div>
									
								</div>
							</div>
						</aside>

						<aside class="single_sidebar_widget post_category_widget">
							<h4 class="widget_title">�峹����</h4>
							<ul class="list cat-list">
								<li><a href="gat_listAllType.jsp?gatType=Katze"
									class="d-flex">
										<div class="animal-icon"
											style="background-image: url(<%=request.getContextPath()%>/front-end/gat/images/icon_cat.svg);">
										</div>
										<p>-- �߫}�p�p�p --</p>
								</a></li>
								<li><a href="gat_listAllType.jsp?gatType=Hund"
									class="d-flex">
										<div class="animal-icon"
											style="background-image: url(<%=request.getContextPath()%>/front-end/gat/images/icon_dog.svg);">
										</div>
										<p>-- �����L�L�L --</p>
								</a></li>
								<li><a href="gat_listAllType.jsp?gatType=Schlange"
									class="d-flex">
										<div class="animal-icon"
											style="background-image: url(<%=request.getContextPath()%>/front-end/gat/images/icon_snake.svg);">
										</div>
										<p>-- �D�D�R�R�R --</p>
								</a></li>
								<li><a href="gat_listAllType.jsp?gatType=Fisch"
									class="d-flex">
										<div class="animal-icon"
											style="background-image: url(<%=request.getContextPath()%>/front-end/gat/images/icon_fish.svg);">
										</div>
										<p>-- �����q�q�q --</p>
								</a></li>
								<li><a href="gat_listAllType.jsp?gatType=Vogel"
									class="d-flex">
										<div class="animal-icon"
											style="background-image: url(<%=request.getContextPath()%>/front-end/gat/images/icon_bird.svg);">
										</div>
										<p>-- �������� --</p>
								</a></li>
								<li><a href="gat_listAllType.jsp?gatType=Hase"
									class="d-flex">
										<div class="animal-icon"
											style="background-image: url(<%=request.getContextPath()%>/front-end/gat/images/icon_bird.svg);">
										</div>
										<p>-- �ߨ߸����� --</p>
								</a></li>
								<li><a href="gat_listAllType.jsp?gatType=Andere"
									class="d-flex">
										<div class="animal-icon"
											style="background-image: url(<%=request.getContextPath()%>/front-end/gat/images/icon_paw.svg);">
										</div>
										<p>-- ��L�o�e�� --</p>
								</a></li>
							</ul>
						</aside>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!--================Blog Area =================-->
	<%@ include file="/front-end/Footer/footer.jsp"%>
</body>
<script src="dist/sweetalert.min.js"></script>
<script>

$(".hiddenbtn").click(function(){
	var memNo = $('.gatbtn_memNo[name="memNo"]').val();
	var gatNo = $('.gatbtn_gatNo[name="gatNo"]').val();
	var action = $('.hidden_input[name="action"]').val();
	console.log(memNo);
	console.log(gatNo);
	console.log(action);
	
	$.ajax({
		url: "<%=request.getContextPath()%>/front-end/gat/gatdetail.do",
									type : "POST",
									data : {
										"action" : action,
										"memNo" : memNo,
										"gatNo" : gatNo
									}
			});
	
						if (action === "gat_cancel") {
							
							jsonObj = {
									"type" : "notice",
									"sender" : "${LoginMem.memNo}",
									"receiver" : "<%=memNo%>",
									"contain" : "<%=memVO.getMemName()%>" + "���Q�ѥ[�A�����ΰաI"
								};
							webSocketN.send(JSON.stringify(jsonObj));
							
							swal('�������\', '�ٴ�������', 'success');
							$('.hidden_input[name="action"]').val("gat_join");
							$(this).html('<i class="fa fa-paw"></i> ���W���W');
							$(this)
									.attr('style',
											'background-color: #17a2b8; border-color: #17a2b8;');
							$(this)
									.mousedown(
											function() {
												$(this).css("background-color",
														"#138496");
												$(this).css("border-color",
														"#117a8b");
												$(this)
														.css("box-shadow",
																"box-shadow: 0 0 0 0.2rem rgba(58,176,195,.5)");
											});
							$(this).mouseup(function() {
								$(this).css("background-color", "#17a2b8");
								$(this).css("border-color", "#17a2b8");
								$(this).css("box-shadow", "none");
							});
						} else {
							
							jsonObj = {
									"type" : "notice",
									"sender" : "${LoginMem.memNo}",
									"receiver" : "<%=memNo%>",
									"contain" : "<%=memVO.getMemName()%>" + "�W�Q�ѥ[�A�����ΡA�ҥH�N�ӰաI"
								};
							webSocketN.send(JSON.stringify(jsonObj));
							
							swal('���W���\', '�ٴ����W��', 'success');
							$('.hidden_input[name="action"]').val("gat_cancel");
							$(this).html(
									'<i class="fa fa-times-circle"></i> �������W');
							$(this)
									.attr('style',
											'background-color: #9E9E9E; border-color: #9E9E9E;');
							$(this)
									.mousedown(
											function() {
												$(this).css("background-color",
														"#8a8a8a");
												$(this).css("border-color",
														"#949494");
												$(this)
														.css("box-shadow",
																"box-shadow: 0 0 0 0.2rem rgb(171 171 171 / 25%)");
											});
							$(this).mouseup(function() {
								$(this).css("background-color", "9E9E9E");
								$(this).css("border-color", "#9E9E9E");
								$(this).css("box-shadow", "none");
							});
						}

					});
					
	var commentsCount = $('.comments_count[name="comments_count"]').val();
	var commentsCount_parseInt = parseInt(commentsCount);

	$('.res_btn').click(function() {
		var gatNo = $('.res_gatNo[name="res_gatNo"]').val();
		var memNo = $('.res_memNo[name="res_memNo"]').val();
		var resCont = $('.res_cont[name="res_cont"]').val();
		console.log(gatNo);
		console.log(memNo);
		console.log(resCont);
		$.ajax({
			url: "<%=request.getContextPath()%>/front-end/gat/gatdetail.do",
			type : "POST",
			data : {
					"action" : "res_add",
					"memNo" : memNo,
					"resCont" : resCont,
					"gatNo" : gatNo
					},
			success: function(){
				var resnewdiv = '<div class="mem_question"><div class="thumb" style="width: 70px;"><img src="images/husky-author.svg"></div><div class="desc"><p class="comment res_comment">' 
					+resCont+'</p><div class="d-flex justify-content-between"><div class="d-flex align-items-center res_name"><h5>${LoginMem.memName}</h5></div></div></div><div class="leader_answer"><div class="thumb" style="width: 70px;"><img src="images/migru-author.svg"></div><div class="desc"><p class="comment">�ΥD�����N�|�^�ФF��</p><div class="justify-content-between"><div class="align-items-center res_name"><h5>�d���p���F</h5></div></div></div></div>';
					
					$('.newcommentwrapper').append(resnewdiv);
					
					commentsCount_parseInt = commentsCount_parseInt + 1;
					$('.count_div').children('h4').text(commentsCount_parseInt + ' Comments');
					}
			});
			jsonObj = {
					"type" : "notice",
					"sender" : "${LoginMem.memNo}",
					"receiver" : "<%=memNo%>",
					"contain" : "<%=memVO.getMemName()%>" + "�ݤ������e�A�ҥH�d�����A�F�I"
				};
			webSocketN.send(JSON.stringify(jsonObj));
	
	});
	
	$('.single-comment .mem_question:first').css("border-top-color", "transparent");
	
	<%for (int i = 0; i < listRes.size(); i++) {%>
		
		$("#replyBtn_" + "<%=i%>").click(function(){
			$("#defaultReply_" + "<%=i%>").css("display","none");
			$("#realReply_" + "<%=i%>").css("display","block");
		});
		
		$("#confirm_null_" + "<%=i%>").click(function(){
			var resNo = $("#resNo_" + "<%=i%>" + "[name='resNo']").val();
			var replyCont = $("#replyCont_" + "<%=i%>" + "[name='replyCont']").val();
			var replyName = $("#replyName_" + "<%=i%>" + "[name='replyName']").val();
			var resMemNo = $("#resMemNo_" + "<%=i%>" + "[name='resMemNo']").val();
			console.log("resNo: " + resNo);
			console.log("replyCont: " + replyCont);
			console.log("replyName: " + replyName);
			console.log("resMemNo: " + resMemNo);
			$.ajax({
				url: "<%=request.getContextPath()%>/front-end/gat/gatdetail.do",
				type : "POST",
				data : {
						"action" : "res_update",
						"resNo" : resNo,
						"replyCont" : replyCont
						}				
				});
			jsonObj = {
					"type" : "notice",
					"sender" : "<%=memNo%>",
					"receiver" : resMemNo,
					"contain" : "�ΥD�^�ЧA���d���o�I"
				};
			webSocketN.send(JSON.stringify(jsonObj));
			$("#defaultReply_" + "<%=i%>").find("p").text(replyCont);
			$("#defaultReply_" + "<%=i%>").find("h5").text(replyName);
			$("#defaultReply_" + "<%=i%>").css("display","block");
			$("#realReply_" + "<%=i%>").css("display","none");
		});
		
		$("#confirm_" + "<%=i%>").click(function(){
			var resNo = $("#resNo_" + "<%=i%>" + "[name='resNo']").val();
			var replyCont = $("#replyCont_" + "<%=i%>" + "[name='replyCont']").val();
			var replyName = $("#replyName_" + "<%=i%>" + "[name='replyName']").val();
			var resMemNo = $("#resMemNo_" + "<%=i%>" + "[name='resMemNo']").val();
			console.log("resNo: " + resNo);
			console.log("replyCont: " + replyCont);
			console.log("replyName: " + replyName);
			console.log("resMemNo: " + resMemNo);
			$.ajax({
				url: "<%=request.getContextPath()%>/front-end/gat/gatdetail.do",
				type : "POST",
				data : {
						"action" : "res_update",
						"resNo" : resNo,
						"replyCont" : replyCont
						}				
				});
			jsonObj = {
					"type" : "notice",
					"sender" : "<%=memNo%>",
					"receiver" : resMemNo,
					"contain" : "�ΥD�ק�F�L���^�СI"
				};
			webSocketN.send(JSON.stringify(jsonObj));
			$("#defaultReply_" + "<%=i%>").find("p").text(replyCont);
			$("#defaultReply_" + "<%=i%>").find("h5").text(replyName);
			$("#defaultReply_" + "<%=i%>").css("display","block");
			$("#realReply_" + "<%=i%>").css("display", "none");
					});
<%}%>
$(".removemember").click(function(){
	var memNo = $(this).siblings('.remove_memNo[name="memNo"]').val();
	var gatNo = $(this).siblings('.remove_gatNo[name="gatNo"]').val();
	console.log('�簣' + memNo);
	console.log('�簣' + gatNo);
	
	$.ajax({
		url: "<%=request.getContextPath()%>/front-end/gat/gatdetail.do",
			type : "POST",
			data : {
				"action" : "mem_cancel",
				"memNo" : memNo,
				"gatNo" : gatNo
			}
		});
		swal('�簣���\', '��o�n', 'success');
		$(this).parents(".member-info").remove();
});
</script>
<script type="text/javascript">
	
	var input = document.getElementById('search_keyword');
	input.addEventListener('input', function(){
		$("#search_gatalllist").empty();
		var keyword = $("#search_keyword").val();
		$.ajax({
			url: "<%=request.getContextPath()%>/front-end/gat/gat.do",
			type : "POST",
			data : {
					"action" : "gatlist_search",
					"keyword" : keyword
					},
			dataType: "json",
			success : function(data){
					$("#default_gatalllist").hide();
					$("#search_gatalllist").css("display", "block");
					$(data).each(function(i, item){
						var str = '<article class="blog_item"><div class="gatlist-wrapper"><div class="blog_item_img" id="search_img" style="background-image: url(/EA102G6/front-end/ShowPhotos?type=gatPhoto&photo_no='+item.gatNo+');"></div><div class="blog_item_date"></div></div><div class="blog_details" id="search_detail">'
						+'<a class="d-inline-block" id="search_name" href="gat_listOne.jsp?gatNo='+item.gatNo+'"><h2>'+item.gatName+'</h2></a><p>'+item.gatIntro+'</p><ul class="blog-info-link"><li id="search_loc"><i class="fa fa-map-marker"></i>'+item.gatLoc+'</li><li><i class="fa fa-tags"></i> Travel, Lifestyle</li></ul></div></article>';
						$("#search_gatalllist").append(str);
					});
				},
			error : function(){alert("ajax�X���o")}
		});		
	});
</script>
<script>
        var map, directionsService, directionsDisplay;
        var markers = [];
        var loci = [];
        var keyword, site_list, start, clear;

        function init() {
            keyword = document.getElementById('keyword');
            site_list = document.getElementsByClassName('site_list')[0];
            start = document.getElementById('start');
            clear = document.getElementById('clear');
            time = document.getElementById('time');
            distance = document.getElementById('distance');

            var options = {
                componentRestrictions: { country: 'tw' } // ����b�x�W�d��
            };
            
            var marker = new google.maps.Marker({
                position: { lat: <%=gatVO_One.getGatLat()%>, lng: <%=gatVO_One.getGatLng()%> },
                map: map,
                animation: google.maps.Animation.BOUNCE
            });

            markers.push(marker);
            loci.push({ lat: <%=gatVO_One.getGatLat()%>, lng: <%=gatVO_One.getGatLng()%> });
            
            var autocomplete = new google.maps.places.Autocomplete(keyword, options);

            // �a�}����J�ءA�Ȧ��ܰʮɰ���
            autocomplete.addListener('place_changed', function() {
                var place = autocomplete.getPlace(); // �a�I��Ʀs�iplace
                // console.log(place);
                // debugger;
                // �T�{�^�Ӫ���Ʀ��g�n��
                if (place.geometry) {
                    // ����map�������I
                    var searchCenter = place.geometry.location;
                    // panTo�O���Ʋ��ʡBsetCenter�O�������ܦa�Ϥ���
                    map.panTo(searchCenter);
                    // �b�j�M���G���a�I�W��m�аO
                    marker = new google.maps.Marker({
                        position: searchCenter,
                        map: map,
                        animation: google.maps.Animation.BOUNCE
                    });

                    markers.push(marker);
                    loci.push({ lat: searchCenter.lat(), lng: searchCenter.lng() });
                    addSite(place.name, place.formatted_address, loci.length);
                }
                keyword.value = '';
            });

            start.addEventListener('click', function() {
                if (loci.length < 2) {
                    alert('�п�ܦܤ֤@�Ӧ��I�I');
                    return;
                }

                deleteMarkersAndRoutes();

                var travelMode = 'DRIVING';
                var travel = document.getElementsByName('travel');
                for (var i = 0; i < travel.length; i++) {
                    if (travel[i].checked) {
                        travelMode = travel[i].value;
                    }
                }

                // ���J���u�A�ȻP���u��ܹϼh
                directionsService = new google.maps.DirectionsService();
                directionsDisplay = new google.maps.DirectionsRenderer();

                // ��m���u�ϼh
                directionsDisplay.setMap(map);

                // ���u�����]�w
                var origin = {};
                var destination = {};
                var waypts = [];
                for (var i = 0; i < loci.length; i++) {
                    if (i === 0) {
                        origin = loci[i];
                    } else if (i === (loci.length - 1)) {
                        destination = loci[i];
                    } else {
                        waypts.push({
                            location: new google.maps.LatLng(loci[i].lat, loci[i].lng),
                            stopover: true
                        });
                    }
                }
                var request = {
                    origin: origin,
                    destination: destination,
                    waypoints: waypts,
                    travelMode: travelMode
                };

                // ø�s���u
                directionsService.route(request, function(result, status) {
                    if (status == google.maps.DirectionsStatus.OK) {
                        // �^�Ǹ��u�W�C�ӨB�J���Ӹ`
                        directionsDisplay.setDirections(result);
                        var route = result.routes[0];
                        var dist = 0;
                        var duration = 0;
                        for (var i = 0; i < route.legs.length; i++) {
                            dist += route.legs[i].distance.value; // ���G����
                            duration += route.legs[i].duration.value; // ���G��
                        }
                        $('.side_panel_wrapper').find('.summary').css('border-top', '1px solid #dee2e6');
                        document.getElementById('summary_result').innerHTML = '�ɯ誺���G<i class="fa fa-question-circle-o" style="margin-left: 7px; font-size: 20px;"></i>';
                        distance.innerText = (dist / 1000).toFixed(2) + ' ����';
                        time.innerText = (duration / 60 / 60).toFixed(2) + ' �p��';
                        
                    } else {
                        console.log(status);
                    }
                });
            });

            clear.addEventListener('click', function() {
                loci = [];
                site_list.innerHTML = '';
                time.innerText = '';
                distance.innerText = '';
                document.getElementById('summary_result').innerHTML = '';
                $('.side_panel_wrapper').find('.summary').css('border-top', 'none');
                deleteMarkersAndRoutes();

                loci.push({ lat: <%=gatVO_One.getGatLat()%>, lng: <%=gatVO_One.getGatLng()%> });
            });

        }

        function addSite(name, address, no) {
            var div = document.createElement('div');
            div.classList.add('site');
            div.innerHTML = `<div class="title"><i class="fa fa-flag" style="margin-right: 10px;"></i>` + name + `</div><div class="location">` + address + `</div>`;
            site_list.append(div);
        }

        function deleteMarkersAndRoutes() {
            // clear markers
            for (var i = 0; i < markers.length; i++) {
                markers[i].setMap(null);
            }
            markers = [];
            
            marker = new google.maps.Marker({
                position: { lat: <%=gatVO_One.getGatLat()%>, lng: <%=gatVO_One.getGatLng()%> },
                map: map,
                animation: google.maps.Animation.BOUNCE
            });
            markers.push(marker);
            

            // Clear past routes
            if (directionsDisplay != null) {
                directionsDisplay.setMap(null);
                directionsDisplay = null;
            }
        }

        function initMap() {
            map = new google.maps.Map(document.getElementById('map'), {
                center: { lat: <%=gatVO_One.getGatLat()%>, lng: <%=gatVO_One.getGatLng()%> },
                zoom: 12,
            });
        }

        window.onload = init;
    </script>
<script
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAUjdRO41BwDZqvCuvsOd5FO-amf4gndBA&libraries=places&callback=initMap"
	async defer></script>
<script>
	$("#magicbtn").click(function(){
		$("#comment").val("�аݴ��ήɶ��O�ƻ�ɭԡH");
	});
</script>
</html>