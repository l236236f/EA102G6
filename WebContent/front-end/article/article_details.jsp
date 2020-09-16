<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- �]�w�ɶ� -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

<%@ page import="java.util.*"%>
<%@ page import="com.article.model.*"%>
<%@ page import="com.art_response.model.*"%>
<%-- <%@ page import="com.fav_article.model.*"%> --%>
<%-- <%@ page import="com.rep_article.model.*"%> --%>
<%-- <%@ page import="com.rep_response.model.*"%> --%>

<!DOCTYPE html>
<html lang="zxx">
<head>
    <meta charset="UTF-8">
    <meta name="description" content="Fashi Template">
    <meta name="keywords" content="Fashi, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>�Q�װϤ峹</title>
 
<style>
	.blog-details-inner .blog-detail-title p:after,
	.blog-details-inner .blog-detail-title p:before {
		width: 0;
	}
	body {
  	font-family: "Open Sans",
  				  -apple-system, 
  				  BlinkMacSystemFont, 
  				  "Segoe UI", 
  				  Roboto, 
  				  Oxygen-Sans, 
  				  Ubuntu, 
  				  Cantarell, 
  				  "Helvetica Neue", 
  				  Helvetica, Arial, sans-serif; 
	}
</style>
</head>

<body>
	<%@ include file="/front-end/Header/header.jsp" %>
	<%
  		ArticleVO articleVO  = (ArticleVO) request.getAttribute("articleVO");
		ArtResponseVO artresponseVO = (ArtResponseVO) request.getAttribute("artresponseVO");

		MemService memSvc = new MemService();
		MemVO showMemVO = memSvc.getOneMem(request.getParameter("showMemNo"));
		pageContext.setAttribute("showMemVo", showMemVO);
	%>

	<jsp:useBean id="favarticleSvc" scope="page" class="com.fav_article.model.FavArticleService" />
	<jsp:useBean id="artresSvc" scope="page" class="com.art_response.model.ArtResponseService" />
	<jsp:useBean id="memberSvc" scope="page" class="com.mem.model.MemService" />
    
    <!-- Blog Details Section Begin -->
    <section class="blog-details spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="blog-details-inner">
                    
					<!-- �峹���D -->
                        <div class="blog-detail-title">
                            <h2>${articleVO.arttitle}</h2>
                            	<p>�峹����
                            		<span>- <fmt:formatDate value="${articleVO.arttime}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
                        		</p>
                        </div>
                        
                    <!-- �|����T -->
                        <div class="posted-by">
                            <div class="pb-pic">
                            	<img src="<%=request.getContextPath()%>/front-end/ShowPhotos?type=mem&memNo=${articleVO.memno}"
										 alt="" style="width:60px;">
                            </div>
                            <div class="pb-text">
                                <a href="#">
                                    <c:forEach var="memVO" items="${memberSvc.all}">
                    					<c:if test="${articleVO.memno==memVO.memNo}">
	                    					<h5>${memVO.memName}</h5>
                    					</c:if>
                					</c:forEach>
                                </a>
                                <c:forEach var="memVO" items="${memberSvc.all}">
                                	<c:if test="${articleVO.memno==memVO.memNo}">
                                		<p>${memVO.memIntro}</p>
                                	</c:if>
                                </c:forEach>
                            </div>
                        </div>
                        
                    <!-- �峹���e -->
                        <div class="blog-detail-desc">
                            <p>
                            	${articleVO.artcontent}                           	
                            </p>
                        </div>
                        
                    <!-- �峹���� -->
                        <div class="tag-share">
                            <div class="details-tag">
                            
							<!-- ���|�峹�}�l -->
                            	<c:if test="${LoginMem!=null and LoginMem.memNo != articleVO.memno}">
								<button	style="padding: 5px; margin-right: 10px; background-color: transparent; border: none;"
									    data-toggle="modal" data-target="#<%=articleVO.getArtno()%>">
									<i class="icon_error-circle" style="font-size: 20px;"></i><span> ���|</span>
								</button>
								<div class="modal fade" id="<%=articleVO.getArtno()%>" tabindex="-1"
									 role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
									<div class="modal-dialog modal-dialog-centered" role="document">
										<div class="modal-content">
											<div class="modal-header">
												<strong class="modal-title" id="exampleModalCenterTitle">���|�峹</strong>
												<button type="button" class="close" data-dismiss="modal" aria-label="Close">
													<span aria-hidden="true">&times;</span>
												</button>
											</div>
											<div class="modal-body"
												style="text-align: left; font-size: 1.15rem">
												<div class="form-group" style="text-align: left">
													<label for="recipient-name" class="col-form-label">�峹���D:</label>
													<input type="text" class="form-control" disabled
														   style="background-color: none; border: none;"
														   value="<%=articleVO.getArttitle()%>">
												</div>
												<div class="form-group">
													<label for="message-text" class="col-form-label">���|��]:</label>
													<textarea class="form-control repreason" id="repreason"
															  name="repreason" placeholder="�����������" required></textarea>
												</div>
												<input type="hidden" id="artno" name="artno" value="<%=articleVO.getArtno()%>"> 
												<input type="hidden" id="memno" name="memno" value="M001"> 
<%-- 												<input type="hidden" id="mem_no" name="mem_no" value="${memVO.mem_no}">  --%>
												<input type="hidden" name="action" value="reparticle">
											</div>
											<div class="modal-footer">
												<button type="button" class="btn btn-secondary"	data-dismiss="modal">����</button>
												<button type="submit" id="reparticle" class="btn btn-primary reparticle" data-dismiss="modal"
														style="background-color: #e7ab3c; border: #e7ab3c;">�T�{</button>
											</div>
										</div>
									</div>
								</div>
								</c:if>
							<!-- ���|�峹���� -->
								
                            </div>
                            
                            <div class="blog-share">
                            
                            <!-- �ק�峹 -->
                    		<c:if test="${LoginMem!=null and LoginMem.memNo==articleVO.memno}">
                            	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/article/article.do" style="margin-bottom: 0px;">
									<button type="submit" class="site-btn" style="position: relative; left: 0px;">�ק�峹</button>
						     		<input type="hidden" name="artno"  value="${articleVO.artno}">
						     		<input type="hidden" name="action"	value="getOne_For_Update">
						     	</FORM>
			     			</c:if>			
			     	
                            </div>
                        </div>
                        
					<!-- �峹�^�� -->
					<c:forEach var="artresponseVO" items="${artresSvc.all}">
						<c:if test="${articleVO.artno == artresponseVO.artno}">
							<div class="posted-by">
								<div class="pb-pic">
									<img src="<%=request.getContextPath()%>/front-end/ShowPhotos?type=mem&memNo=${articleVO.memno}"
										 alt="" style="width:60px;">
								</div>
								<div class="pb-text">
									<a href="#">
										<c:forEach var="memVO" items="${memberSvc.all}">
                    						<c:if test="${artresponseVO.memno==memVO.memNo}">
	                    						<h5>${memVO.memName}</h5>
                    						</c:if>
                						</c:forEach>
									</a>
									<p>${artresponseVO.rescontent}</p>
								</div>

									<!-- ���|�d���}�l  -->
									<c:if test="${LoginMem!=null}">
									<div style="position: absolute; right: 22px;">
										<button	style="padding: 5px; margin-right: 10px; background-color: transparent; border: none;"
												data-toggle="modal" data-target=#${artresponseVO.resno}>
											<i class="icon_error-circle" style="font-size: 20px;"></i><span>���|</span>
										</button>
										<div class="modal fade" id="${artresponseVO.resno}" tabindex="-1"
											 role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
											<div class="modal-dialog modal-dialog-centered"	role="document">
												<div class="modal-content">
													<div class="modal-header">
														<h5 class="modal-title" id="exampleModalCenterTitle">���|�d��</h5>
														<button type="button" class="close" data-dismiss="modal" aria-label="Close">
															<span aria-hidden="true">&times;</span>
														</button>
													</div>
													<div class="modal-body"	style="text-align: left; font-size: 1.15rem">
														<div class="form-group">
															<label for="recipient-name" class="col-form-label">�d����:</label>
															<c:forEach var="memVO" items="${memberSvc.all}">
	                    										<c:if test="${artresponseVO.memno==memVO.memNo}">
		                    										${memVO.memName}
	                    										</c:if>
	                										</c:forEach>
														<div class="form-group" style="text-align: left">
															<label for="recipient-name" class="col-form-label">�d�����e:</label>
															<input type="text" class="form-control" disabled
																   style="background-color: none; border: none;" value="${artresponseVO.rescontent}">
														</div>
														<div class="form-group">
															<label for="message-text" class="col-form-label" >���|��]:</label>
															<textarea class="form-control repreason"
																	  name="repreason" placeholder="�����������" required></textarea>
														</div>
														<input type="hidden" class="resno" name="resno" value="${artresponseVO.resno}"> 
														<input type="hidden" class="memno" name="memno" value="${artresponseVO.memno}">
														<input type="hidden" class="artno" name="artno" value="${artresponseVO.artno}"> 
														<input type="hidden" name="action" value="insertcomment">
													</div>
														<div class="modal-footer">
															<button type="button" class="btn btn-secondary"	id="cancel" data-dismiss="modal">����</button>
															<button type="submit" class="btn btn-primary rep_submit" data-dismiss="modal"
																	style="background-color: #e7ab3c; border: #e7ab3c;">�T�{</button>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
									</c:if>
								<!-- ���|�d������  -->

							</div>
						</c:if>
					</c:forEach>
					
                        
                    <!-- �^�Ф峹 -->
                    <c:if test="${LoginMem!=null and LoginMem.memNo!=articleVO.memno}">
                        <div class="leave-comment">
                        
							<c:if test="${not empty errorMsgs}">
								<font style="color:red">�Эץ��H�U���~:</font>
									<c:forEach var="message" items="${errorMsgs}">
										<a style="color:red">${message}</a>
									</c:forEach>
							</c:if>
							
                            <h4>�^�Ф峹</h4>
                            <form method="post" action="<%=request.getContextPath()%>/front-end/article/art_response.do" class="comment-form">
                                <div class="row">
                                    <div class="col-lg-12">
                                        <textarea placeholder="�п�J�^��..." name="rescontent"></textarea>
                                        <input type="hidden" name="artno" value="${articleVO.artno}">
										<input type="hidden" name="memno" value="M002">
<%-- 										<input type="hidden" name="memno" value="${memVO.memno}"> --%>
										<input type="hidden" name="resstatus" value="AC1">
										<input type="hidden" name="action" value="insert">
                                        <button type="submit" class="site-btn">�e�X�^��</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </c:if>

                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Blog Details Section End -->
	
	<%@ include file="/front-end/Footer/footer.jsp" %>
	<c:if test="${LoginMem!=null}">
		<%@ include file="/chatRoom/chatRoom.jsp"%>
	</c:if>
	<script src='https://cdnjs.cloudflare.com/ajax/libs/imask/3.4.0/imask.min.js'></script>
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
  
</body>

<script>
$(".reparticle").click(function(){
	var artno = $(this).parent().prev().find(".artno").val();
	var memno = $(this).parent().prev().find(".memno").val();
	var repreason = $(this).parent().prev().find(".repreason").val();
	
    $.ajax({
    url: "<%=request.getContextPath()%>/front-end/article/rep_article.do",
    type: "POST",
     data:{
     "action" : "reparticle",
     "artno" : $("#artno"),
     "memno" : $("#memno"),
     "repreason" : $("#repreason")
   	}
 	});
 });
 
$(".rep_submit").click(function(){
	var resno = $(this).parent().prev().find(".resno").val();
	var memno = $(this).parent().prev().find(".memno").val();
	var repreason = $(this).parent().prev().find(".repreason").val();
	
    $.ajax({
    url: "<%=request.getContextPath()%>/front-end/article/rep_response.do",
    type: "POST",
     data:{
     "action" : "represponse",
     "resno" : resno,
     "memno" : memno,
     "repreason" : repreason
   	}
 	});
 });
 
$(function () {
    $(".rep_submit").click(function () {
    	Swal.fire({
	  		icon: 'success',
	  		title: '�P�±z�����|',
	  		text: '�ڭ̷|�ɧֳB�z',
		})
    });
});

</script>
</html>