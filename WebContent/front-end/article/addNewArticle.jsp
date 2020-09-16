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
    <title>新增文章</title>

<style>
	.blog-details-inner .blog-detail-title p:after,
	.blog-details-inner .blog-detail-title p:before {
		width: 0;
	}
	table {
		width: 100%;
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
	%>

    <!-- Blog Details Section Begin -->
    <section class="blog-details spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="blog-details-inner">
                        <div class="blog-detail-title">
                            <h2>新增文章</h2>
                            
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
									<td style="width:100px;">文章標題:</td>
									<td><input type="TEXT" name="arttitle" size="45" 
										 value="<%= (articleVO==null)? "寄養心得" : articleVO.getArttitle()%>" />
									</td>
								</tr>
								<tr>
									<td>文章內容:</td>
								</tr>
								<tr>
									<td colspan="2">
										<textarea name="artcontent"><%= (articleVO==null)? "" : articleVO.getArtcontent()%></textarea>
									</td>
								</tr>
							</table>
                        </div>
                        
                        <div class="leave-comment">
                        	<div class="row">
                        		<div class="col-lg-12">
                        		<button type="button" class="site-btn" data-dismiss="modal" 
                        				style="background-color:black; border: 1px solid black;"
                        				onclick="location.href='article.jsp'">取消</button>
								
								<input type="hidden" name="memno" value="M001">
<%-- 							<input type="hidden" name="memno" value="${memVO.mem_no}"> --%>

                                <input type="hidden" name="action" value="insert">
                                <button type="submit" class="site-btn">發表文章</button>
                                        
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