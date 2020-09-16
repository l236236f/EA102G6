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
    <title>二手市集</title>
    <style>
    .pi-pic {
	    min-width: 295px;
	    min-height: 295px;
	    background-size: contain;
	    background-repeat: no-repeat;
	    background-position: center;
	    background-color: rgba(0, 0, 0, 0.10);
	}
	</style>
</head>

<body>
	<%@ include file="/front-end/Header/header.jsp" %>
	
	<%
    	UprodService uprodSvc = new UprodService();
    	List<UprodVO> list = uprodSvc.getAllOnShelfByA();
    	pageContext.setAttribute("list",list);
	%>
	
	    <!-- Breadcrumb Section Begin -->
    <div class="breacrumb-section"
    	 style="height: 300px; 
    			background-image: url(<%=request.getContextPath()%>/front-end/uprod/image/banner/uprod_banner.jpg);
    			background-position: center;
    			background-size: unset;
    			background-repeat: no-repeat;">
    </div>
    <!-- Breadcrumb Section Begin -->

    <!-- Product Shop Section Begin -->
    <section class="product-shop spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-2 col-md-6 col-sm-8 order-2 order-lg-1 produts-sidebar-filter">
                    <div class="filter-widget">
                        <h4 class="fw-title">賣家功能</h4>
                        <ul class="filter-catagories">
                            <li><a href="uprod_manage.jsp">商品管理</a></li>
                            <li><a href="uprod_add.jsp">上架商品</a></li>
                            <li><a href="uprod_seller_detail.jsp">訂單管理</a></li>
                        </ul>
                    </div>
                    <div class="filter-widget">
                        <h4 class="fw-title">買家功能</h4>
                        <ul class="filter-catagories">
                            <li><a href="uprod_customer.jsp">訂單查詢</a></li>
                        </ul>
                    </div>
                    <div class="filter-widget">
                        <h4 class="fw-title">商品分類</h4>
                        <div class="fw-brand-check">
                            <div class="bc-item">
                                <label for="bc-calvin">
                                <a href="used_shopA.jsp"> 毛孩伙食</a>
                                                                                                     
<!--                                     <input type="checkbox" id="bc-calvin"> -->
<!--                                     <span class="checkmark"></span> -->
                                </label>
                            </div>
                            <div class="bc-item">
                                <label for="bc-diesel">
                                <a href="used_shopB.jsp"> 日常用品</a>
                                                                                        
<!--                                     <input type="checkbox" id="bc-diesel"> -->
<!--                                     <span class="checkmark"></span> -->
                                </label>
                            </div>
                            <div class="bc-item">
                                <label for="bc-polo">
                                 <a href="used_shopC.jsp"> 清潔用品</a>
                          
<!--                                     <input type="checkbox" id="bc-polo"> -->
<!--                                     <span class="checkmark"></span> -->
                                </label>
                            </div>
                        </div>
                    </div>
                </div>
                
                
                <div class="col-lg-10 order-1 order-lg-2">
                    <div class="product-list">
			            
			            <%@ include file="page1.file" %>
                        <div class="row">
                        
			                <c:forEach var="uprodVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
                            
                            <div class="col-lg-4 col-sm-6">
                                <div class="product-item">
                                    <div 
                                    	class="pi-pic" 
                                    	style="background-image: url('<%=request.getContextPath()%>/front-end/uprod/showpic.do?ProdNo=${uprodVO.getProdNo()}')"
                                   	>
<!--                                         <img class="pics"/>  -->
<!--                                     追蹤 -->
                                        <div class="icon">
                                            <i class="icon_heart_alt"></i>
                                        
                                        </div>
                                        <ul>
                                            <li class="quick-view">
                                            	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/uprod/uprod.do">
											    	<input type="hidden" name="ProdNo"  value="${uprodVO.getProdNo()}">
											     	<input type="hidden" name="action"	value="getOne_For_Update">
											     	<input type="hidden" name="update1"  value="1">
											     	<input type="submit" value="購買商品">
											  	</FORM>
                                            </li>
                                        </ul>
                                    </div>
                                    
                                    <div class="pi-text">
                                        <div class="catagory-name">
											<c:if test="${uprodVO.prodType=='A'}">毛孩伙食</c:if>
									    	<c:if test="${uprodVO.prodType=='B'}">日常用品</c:if>
									    	<c:if test="${uprodVO.prodType=='C'}">清潔用品</c:if>
                                        </div>
                                        <a href="<%=request.getContextPath()%>/front-end/uprod/uprod.do?ProdNo=${uprodVO.prodNo}&action=getOne_For_Display">
                                            <h5>${uprodVO.prodName}</h5>
                                        </a>
                                        <div class="product-price">
					                                            價格: ${uprodVO.price} 元
                                        </div>
                                    </div>
                                </div>
                            </div>
					    
					    </c:forEach>
					        
                        </div>
                    </div>
		        	<%@ include file="page2.file" %>
		        	
                </div>
            </div>
        </div>
        
    </section>
    <!-- Product Shop Section End -->

    <%@ include file="/front-end/Footer/footer.jsp" %>

</body>
</html>