<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.article.model.*"%>

<!DOCTYPE html>
<html lang="zxx">
<head>
    <meta charset="UTF-8">
    <meta name="description" content="Fashi Template">
    <meta name="keywords" content="Fashi, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>修改文章</title>

<style>
	.blog-details-inner .blog-detail-title p:after,
	.blog-details-inner .blog-detail-title p:before {
		width: 0;
	}
	table {
		width: 1200px;
		background-color: white;
		margin-top: 5px;
		margin-bottom: 5px;
	}
</style>
</head>

<body>
	<%@ include file="/front-end/Header/header.jsp" %>
    <%
  		ArticleVO articleVO = (ArticleVO) request.getAttribute("articleVO");
    	pageContext.setAttribute("articleVO", articleVO);
	%>

    <!-- Blog Details Section Begin -->
    <section class="blog-details spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="blog-details-inner">
                        <div class="blog-detail-title">
                            <h2>修改文章</h2>
                            
                            <%-- 錯誤表列 --%>
							<c:if test="${not empty errorMsgs}">
								<ul>
									<c:forEach var="message" items="${errorMsgs}">
										<li style="color:red">${message}</li>
									</c:forEach>
								</ul>
							</c:if>
<!--                             	<p>文章分類 -->
<%--                             		<span>- <fmt:formatDate value="${articleVO.arttime}" pattern="yyyy-MM-dd HH:mm:ss"/></span> --%>
<!--                         		</p> -->
                        </div>
                        <FORM METHOD="post" ACTION="article.do" name="form1">
                        <div class="blog-detail-desc">
                        	<table>
								<tr>
									<td>文章標題:</td>
									<td><input type="TEXT" name="arttitle" size="45" 
										 value="<%=articleVO.getArttitle()%>" />
									</td>
								</tr>
								<tr>
									<td>文章內容:</td>
								</tr>
								<tr>
									<td colspan="2">
										<textarea name="artcontent">${articleVO.artcontent}</textarea>
									</td>
								</tr>
							</table>
                        </div>
                        
                        <div class="leave-comment">
                        	<div class="row">
                        		<div class="col-lg-12">
	                        		<button type="button" class="site-btn" data-dismiss="modal"
	                        				style="background-color:black; border: 1px solid black;"
	                        				onclick="location.href='<%=request.getContextPath()%>/front-end/article/article.do?artno=${articleVO.artno}&action=getOneArticle_For_Display'">
	                        				取消</button>
	
									<input type="hidden" name="artno" value="<%=articleVO.getArtno()%>">
									<input type="hidden" name="memno" value="<%=articleVO.getMemno()%>">
	                                <input type="hidden" name="action" value="update">
	                                <button type="submit" class="site-btn">確認修改文章</button>
                                </div>
                           </div>
                        </div>
                        </FORM>
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

	<!-- CK Editor -->
    <script src="<%=request.getContextPath()%>/ckeditor/ckeditor.js"></script> 
	<script> 
		CKEDITOR.replace('artcontent', {height:['500px']});
	</script> 

</body>
</html>