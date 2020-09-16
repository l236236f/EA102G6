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
    <title>二手市集 - 訂單管理</title>
    
    <style>
    .proceed-checkout .proceed-btn {
	    font-size: 14px;
	    font-weight: 700;
	    color: #ffffff;
	    border-color: #E7AB3C;
	    border-style: solid;
	    text-transform: uppercase;
	    padding: 15px 25px 14px 25px;
	    display: block;
	    text-align: center;
	    width: 100%;
	}
    </style>
</head>

<body>
	<%@ include file="/front-end/Header/header.jsp" %>
	
	<%
	    UprodService uprodSvc = new UprodService();
	    List<UprodVO> list = uprodSvc.getAllBySellerDetail(memVO.getMemNo());
	    pageContext.setAttribute("list",list);
	%>
	<jsp:useBean id="memberSvc" scope="page" class="com.mem.model.MemService" />
	
	<!-- Breadcrumb Section Begin -->
	<section class="blog-details spad" style="height:100px;">
            <div class="row">
                <div class="col-lg-12">
                	<div class="blog-details-inner">
                        <div class="blog-detail-title">
                            <h2>訂單管理</h2>
                        </div>
                    </div>
                </div>
            </div>
    </section>
    <!-- Breadcrumb Section Begin -->
    
    <!-- Shopping Cart Section Begin -->
    <section class="roduct-shop spad">
        <div class="container">
            <div class="row">
            	
				<%@ include file="uprod_sidebar.file" %>
            	
            	<div class="col-lg-10 order-1 order-lg-2">
                <div class="col-lg-12">
                	<%@ include file="page1.file" %>
                    <div class="cart-table" style="margin-top: 20px; width: 1100px;">
                        <table>
                            <thead> 
                                <tr>
                                    <th>商品名稱</th>
                                    <th>商品種類</th>
                                    <th>商品單價</th>
                                    <th>買家名稱</th>
                                    <th>處理狀態</th>
                                    <th>領收狀態</th>
                                    <th>訂單成立時間</th>
                                    <th>寄送方式</th>
									<th>寄送地址</th>
                                </tr>
                            </thead>
                            
							<c:forEach var="uprodVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
                            
                            <tbody>
                                <tr>
                                    <td class="cart-title first-row" style="width:60px; text-align: center;">
                                        ${uprodVO.prodName}
                                    </td>
                                    <td class="qua-col first-row" style="width:120px; text-align: center;">
                                    	<c:if test="${uprodVO.prodType=='A'}">毛孩伙食</c:if>
                                    	<c:if test="${uprodVO.prodType=='B'}">日常用品</c:if>
                                    	<c:if test="${uprodVO.prodType=='C'}">清潔用品</c:if>
									</td>
                                    <td class="qua-col first-row" style="width:70px; text-align: center;">
                                    	${uprodVO.price}
                                    </td>
                                    <td class="qua-col first-row">
                                    	<c:forEach var="memVO" items="${memberSvc.all}">
		                    				<c:if test="${uprodVO.custNo==memVO.memNo}">
			                    				<h5>${memVO.memName}</h5>
		                    				</c:if>
		                				</c:forEach>
                                    </td>
                                    <td class="qua-col first-row" style="width:80px; text-align: center;">
                                    	<c:if test="${uprodVO.orderStatus=='OS0'}">處理中</c:if>
                                    	<c:if test="${uprodVO.orderStatus=='OS1'}">已出貨</c:if>
									</td>
									<td class="qua-col first-row" style="width:80px; text-align: center;">
                                    	<c:if test="${uprodVO.receiveStatus=='RS0'}">尚未收貨</c:if>
                                    	<c:if test="${uprodVO.receiveStatus=='RS1'}">已到貨</c:if>
									</td>
									<td class="qua-col first-row" style="width:120px; text-align: center;">
										<fmt:formatDate value="${uprodVO.orderTime}" pattern="yyyy-MM-dd"/><br>
										<fmt:formatDate value="${uprodVO.orderTime}" pattern="HH:mm:ss"/>
									</td>
									<td class="qua-col first-row" style="width:70px; text-align: center;">
                                    	<c:if test="${uprodVO.tranMethod=='TM0'}">超商取貨</c:if>
                                    	<c:if test="${uprodVO.tranMethod=='TM1'}">宅配</c:if>
                                    	<c:if test="${uprodVO.tranMethod=='TM2'}">面交</c:if>
									</td>
									<td class="qua-col first-row" style="width:70px; text-align: center;">${uprodVO.tranAddr}</td>
                                    <td class="qua-col first-row" style="text-align: center;">
                                    <c:if test="${uprodVO.orderStatus=='OS0'}">
	                                    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/uprod/uprod.do" style="margin-bottom: 0px;">
											<input type="hidden" name="ProdNo"  value="${uprodVO.getProdNo()}">
											<input type="hidden" name="action"	value="shipment">
											<input type="submit" value="確認出貨">
				     					</FORM>
				     				</c:if>
				     				<c:if test="${uprodVO.orderStatus=='OS1'}">
				     					完成出貨
				     				</c:if>
                                    </td>
                                    
                                </tr>
                            </tbody>
                            
                            </c:forEach>
                        </table>
                        <div style="text-align: center; margin-top: 20px;">
                        	<c:if test="${list.size()==0}">您目前沒有任何訂單</c:if>
                        </div>
                    </div>
                    <%@ include file="page2.file" %>
                    
                    <div class="row">
                        <div class="col-lg-4">
                        	<div class="cancel">
	                        	<button type="button" class="site-btn" data-dismiss="modal" 
	                        				style="background-color:black; border: 1px solid black; width: 180px; height: 54px;"
	                        				onclick="location.href='<%=request.getContextPath()%>/front-end/uprod/used_shop.jsp'">
	                        				返回二手市集</button>
                        	</div>
                        </div>
                    </div>
                </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Shopping Cart Section End -->
    
    <%@ include file="/front-end/Footer/footer.jsp" %>
    
</body>
</html>