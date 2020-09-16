<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 設定時間 -->
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
    <title>討論區文章</title>
 
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
                    
					<!-- 文章標題 -->
                        <div class="blog-detail-title">
                            <h2>[版規]發表文章前，請先詳細閱讀版規</h2>
                            	<p>置頂公告
                            		<span>- 2020-09-01 00:00:00</span>
                        		</p>
                        </div>
                        
                    <!-- 會員資訊 -->
                        <div class="posted-by">
                            <div class="pb-pic">
                            	<img src="images/Teacher_Wu.jpg"
										 alt="" style="width:120px;">
                            </div>
                            <div class="pb-text">
                                <a><h5>管理員</h5></a>
                                <p>總是不厭其煩舉例說明，總是為同學提綱挈領，總是超越時數限制地傳授，總是把困難的東西講得讓您懂，總是帶領大家通過專題製作艱辛的考驗，總是不輕易說累或放棄，總是獲得用人廠商的信賴，總是把每一位學員當成自己的孩子！
								       每一個「總是」，都是「神」等級！不要只在PTT上聽聞吳老師了， 歡迎「與神同行」！</p>
                            </div>
                        </div>
                        
                    <!-- 文章內容 -->
                        <div class="blog-detail" style="text-align:center; margin-top: 20px;">
                            <p><h4>厚~死囡仔</h4></p>
                            <p><h4>小孩...不~錯吧</h4></p>
                            <p><h4>服務很好吧</h4></p>
                            <p><h4>Java是簡單的</h4></p>
                            <p><h4>Servlet也是簡單的</h4></p>
                            <p><h4>對...是啦</h4></p>
                            <p><h4>你們班真的很可愛</h4></p>
                            <p><h4>滷肉飯..我..沒拌</h4></p>
                            <p><h4>老師~我真的真的好想你</h4></p>
                            <p><h4>我有一個簡單的Java問題想問你</h4></p>
                            <p><h4>我現在腦力很強</h4></p>
                            <p><h4>我是不可能會錯的</h4></p>
                            <p><h4>這裡不會，哪裡出來的? ○○○○</h4></p>
                            <p><h4>你們是會SQL指令還是不會的...</h4></p>
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