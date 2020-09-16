<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 設定時間 -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

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
    <title>二手市集 - 購買完成頁</title>
</head>

<body>
	<%@ include file="/front-end/Header/header.jsp" %>
	
	<jsp:useBean id="memberSvc" scope="page" class="com.mem.model.MemService" />
	
	<!-- Breadcrumb Section Begin -->
	<section class="blog-details spad" style="height:100px;">
            <div class="row">
                <div class="col-lg-12">
                	<div class="blog-details-inner">
                        <div class="blog-detail-title">
                            <h2>商品購買完成</h2>
                            <br>
                            	目前您已完成購買流程，透過訂單查詢，即可確認詳細訂單資訊，謝謝。
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
                <div class="col-lg-12" style="position: relative; left:38.5%;">
                    <img src="<%=request.getContextPath()%>/front-end/uprod/image/thank.png" alt="">
                </div>
                <div class="col-lg-4 offset-lg-4">
                	<div class="proceed-checkout">
                		<a href="<%=request.getContextPath()%>/front-end/uprod/used_shop.jsp" class="proceed-btn" 
                   			style="background-color: #E7AB3C; position:relative;  margin-top:50px;">
							 返回二手市集
                		</a>
                	</div>
                </div>
            </div>
        </div>
    </section>
    <!-- Shopping Cart Section End -->
    
    <%@ include file="/front-end/Footer/footer.jsp" %>
    
</body>
</html>