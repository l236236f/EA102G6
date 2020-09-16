<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- �]�w�ɶ� -->
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
    <title>�G�⥫�� - �q��޲z</title>
    
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
                            <h2>�q��޲z</h2>
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
                                    <th>�ӫ~�W��</th>
                                    <th>�ӫ~����</th>
                                    <th>�ӫ~���</th>
                                    <th>�R�a�W��</th>
                                    <th>�B�z���A</th>
                                    <th>�⦬���A</th>
                                    <th>�q�榨�߮ɶ�</th>
                                    <th>�H�e�覡</th>
									<th>�H�e�a�}</th>
                                </tr>
                            </thead>
                            
							<c:forEach var="uprodVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
                            
                            <tbody>
                                <tr>
                                    <td class="cart-title first-row" style="width:60px; text-align: center;">
                                        ${uprodVO.prodName}
                                    </td>
                                    <td class="qua-col first-row" style="width:120px; text-align: center;">
                                    	<c:if test="${uprodVO.prodType=='A'}">��ĥ뭹</c:if>
                                    	<c:if test="${uprodVO.prodType=='B'}">��`�Ϋ~</c:if>
                                    	<c:if test="${uprodVO.prodType=='C'}">�M��Ϋ~</c:if>
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
                                    	<c:if test="${uprodVO.orderStatus=='OS0'}">�B�z��</c:if>
                                    	<c:if test="${uprodVO.orderStatus=='OS1'}">�w�X�f</c:if>
									</td>
									<td class="qua-col first-row" style="width:80px; text-align: center;">
                                    	<c:if test="${uprodVO.receiveStatus=='RS0'}">�|�����f</c:if>
                                    	<c:if test="${uprodVO.receiveStatus=='RS1'}">�w��f</c:if>
									</td>
									<td class="qua-col first-row" style="width:120px; text-align: center;">
										<fmt:formatDate value="${uprodVO.orderTime}" pattern="yyyy-MM-dd"/><br>
										<fmt:formatDate value="${uprodVO.orderTime}" pattern="HH:mm:ss"/>
									</td>
									<td class="qua-col first-row" style="width:70px; text-align: center;">
                                    	<c:if test="${uprodVO.tranMethod=='TM0'}">�W�Ө��f</c:if>
                                    	<c:if test="${uprodVO.tranMethod=='TM1'}">�v�t</c:if>
                                    	<c:if test="${uprodVO.tranMethod=='TM2'}">����</c:if>
									</td>
									<td class="qua-col first-row" style="width:70px; text-align: center;">${uprodVO.tranAddr}</td>
                                    <td class="qua-col first-row" style="text-align: center;">
                                    <c:if test="${uprodVO.orderStatus=='OS0'}">
	                                    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/uprod/uprod.do" style="margin-bottom: 0px;">
											<input type="hidden" name="ProdNo"  value="${uprodVO.getProdNo()}">
											<input type="hidden" name="action"	value="shipment">
											<input type="submit" value="�T�{�X�f">
				     					</FORM>
				     				</c:if>
				     				<c:if test="${uprodVO.orderStatus=='OS1'}">
				     					�����X�f
				     				</c:if>
                                    </td>
                                    
                                </tr>
                            </tbody>
                            
                            </c:forEach>
                        </table>
                        <div style="text-align: center; margin-top: 20px;">
                        	<c:if test="${list.size()==0}">�z�ثe�S������q��</c:if>
                        </div>
                    </div>
                    <%@ include file="page2.file" %>
                    
                    <div class="row">
                        <div class="col-lg-4">
                        	<div class="cancel">
	                        	<button type="button" class="site-btn" data-dismiss="modal" 
	                        				style="background-color:black; border: 1px solid black; width: 180px; height: 54px;"
	                        				onclick="location.href='<%=request.getContextPath()%>/front-end/uprod/used_shop.jsp'">
	                        				��^�G�⥫��</button>
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