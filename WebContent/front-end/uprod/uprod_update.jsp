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
    <title>�G�⥫�� - �ӫ~�޲z</title>
    
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
		UprodVO uprodVO = (UprodVO) request.getAttribute("uprodVO");
	%>
	<jsp:useBean id="memberSvc" scope="page" class="com.mem.model.MemService" />
	
	<!-- Breadcrumb Section Begin -->
	<section class="blog-details spad" style="height:100px;">
            <div class="row">
                <div class="col-lg-12">
                	<div class="blog-details-inner">
                        <div class="blog-detail-title">
                            <h2>�ק�ӫ~</h2>
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
            	
            	<FORM METHOD="post" ACTION="uprod.do" name="form1" enctype="multipart/form-data">
            	<%-- ���~��C --%>
				<c:if test="${not empty errorMsgs}">
					<font style="color: red">�Эץ��H�U���~:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>
				
                <div class="col-lg-12">
                    <div class="cart-table" style="margin-top: 20px;">
                        <table>
	                        <thead>
	                        	<tr>
	                            	<td>�ӫ~�W�١G</td>
									<td style="text-align:left">
										<input type="TEXT" name="ProdName" size="45" value="<%=uprodVO.getProdName()%>" />
									</td>
								</tr>
								<tr>
									<td>�ӫ~���e�G</td>
									<td style="text-align:left">
									    <textarea name="ProdIntro" rows="5" cols="45" 
									    		  wrap="off" style="resize:none;">${uprodVO.prodIntro}</textarea>
									</td>
								</tr>	
								<tr>
									<td>����G</td>
									<td style="text-align:left">
										<input type="TEXT" name="Price" size="45"
											   value="<%=uprodVO.getPrice()%>" />
									</td>
	                            </tr>
	                            <tr>
									<td>�ӫ~�����G</td>	
									<td style="text-align:left">
							           	<select name="ProdType" size="1">
								           	<option value="A" ${(uprodVO.prodType=='A')? 'selected':'' }>��ĥ뭹</option>
								           	<option value="B" ${(uprodVO.prodType=='B')? 'selected':'' }>��`�Ϋ~ </option>
								           	<option value="C" ${(uprodVO.prodType=='C')? 'selected':'' }>�M��Ϋ~ </option>
							           	</select>
						            </td>
								</tr>
	                            <tr>
									<td>�ӫ~���A�G</td>	
									<td style="text-align:left">
										<input type="RADIO" name="ProdStatus" value="PS0"  ${(uprodVO.prodStatus=='PS0')? 'checked':'' } />�W�[�@
										<input type="RADIO" name="ProdStatus" value="PS1"  ${(uprodVO.prodStatus=='PS1')? 'checked':'' } />�U�[
						            </td>
								</tr>
								<tr>
								    <td>�ӫ~�Ӥ��G</td>
								    <td style="text-align:left">
								  		<input type='file' name="Photo" accept="image/*" onchange="PreviewImage(this)"/>
								    </td>
								</tr>
								<tr>
									<td></td>
									<td style="text-align:left">
										<div>
								  			<img id="imgPreview" src="<%=request.getContextPath()%>/front-end/uprod/showpic.do?ProdNo=${uprodVO.getProdNo()}" 
								  				 height="200px" width="200px">
								  		</div>
									</td>
								</tr>
							</thead>	
                        </table>
                    </div>
                    
                    <div class="row">
                        <div class="col-lg-4">
                        	<div class="cancel">
	                        	<button type="button" class="site-btn" data-dismiss="modal" 
	                        				style="background-color:black; border: 1px solid black; width: 180px; height: 54px;"
	                        				onclick="location.href='<%=request.getContextPath()%>/front-end/uprod/uprod_manage.jsp'">
	                        				����</button>
                        	</div>
                        </div>
                        <div class="col-lg-4 offset-lg-4">
                            <div class="proceed-checkout">
                            	<input type="hidden" name="SellerNo" value="<%=memVO.getMemNo()%>">
                            	<input type="hidden" name="ProdNo"  value="${uprodVO.prodNo}">
                            	<input type="hidden" name="action" value="update">
                            	<button type="submit" class="proceed-btn buy_prod" style="background-color: #E7AB3C;">
                              		 �T�{�ק�
                                </button>
                            </div>
                        </div>
                    </div>
                    
                </div>
                </FORM>
                
                </div>
            </div>
        </div>
    </section>
    <!-- Shopping Cart Section End -->
    
    <%@ include file="/front-end/Footer/footer.jsp" %>
    
<script>
	function PreviewImage(imgFile) {
	var pattern = /(\.*.jpg$)|(\.*.png$)|(\.*.jpeg$)|(\.*.gif$)|(\.*.bmp$)/;
	if (!pattern.test(imgFile.value)) {
			alert("�u�䴩jpg/jpeg/png/gif/bmp���榡�ɮ�");
			imgFile.focus();
		} else {
			 var imgPreview = document.getElementById('imgPreview');
			 imgPreview.src = URL.createObjectURL(event.target.files[0]);
		};
	}
</script>
	
</body>
</html>