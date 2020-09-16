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
	#blog-detail p {
		line-height:22px;
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
                            <h2>[���W]�o��峹�e�A�Х��ԲӾ\Ū���W</h2>
                            	<p>�m�����i
                            		<span>- 2020-09-01 00:00:00</span>
                        		</p>
                        </div>
                        
                    <!-- �|����T -->
                        <div class="posted-by">
                            <div class="pb-pic">
                            	<img src="images/Teacher_Wu.jpg"
										 alt="" style="width:120px;">
                            </div>
                            <div class="pb-text">
                                <a><h5>�޲z��</h5></a>
                                <p>�`�O��������|�һ����A�`�O���P�Ǵ�������A�`�O�W�V�ɼƭ���a�Ǳ¡A�`�O��x�����F�����o���z���A�`�O�a��j�a�q�L�M�D�s�@�}��������A�`�O���������֩Ω��A�`�O��o�ΤH�t�Ӫ��H��A�`�O��C�@��ǭ����ۤv���Ĥl�I
								       �C�@�ӡu�`�O�v�A���O�u���v���šI���n�u�bPTT�Wť�D�d�Ѯv�F�A �w��u�P���P��v�I</p>
                            </div>
                        </div>
                        
                    <!-- �峹���e -->
                        <div class="blog-detail" style="text-align:center; margin-top: 20px;">
                            <p><h4>�p~���ƥJ</h4></p>
                            <p><h4>�p��...��~���a</h4></p>
                            <p><h4>�A�ȫܦn�a</h4></p>
                            <p><h4>Java�O²�檺</h4></p>
                            <p><h4>Servlet�]�O²�檺</h4></p>
                            <p><h4>��...�O��</h4></p>
                            <p><h4>�A�̯Z�u���ܥi�R</h4></p>
                            <p><h4>���׶�..��..�S��</h4></p>
                            <p><h4>�Ѯv~�گu���u���n�Q�A</h4></p>
                            <p><h4>�ڦ��@��²�檺Java���D�Q�ݧA</h4></p>
                            <p><h4>�ڲ{�b���O�ܱj</h4></p>
                            <p><h4>�ڬO���i��|����</h4></p>
                            <p><h4>�o�̤��|�A���̥X�Ӫ�? ��������</h4></p>
                            <p><h4>�A�̬O�|SQL���O�٬O���|��...</h4></p>
                        </div>
                        

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
  
</body>
</html>