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
    <title>二手市集 - 商品管理</title>
    
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
                            <h2>上架商品</h2>
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
            	<%-- 錯誤表列 --%>
				<c:if test="${not empty errorMsgs}">
					<font style="color: red">請修正以下錯誤:</font>
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
	                            	<td>商品名稱：</td>
									<td style="text-align:left">
										<input type="TEXT" id="1231" name="ProdName" size="45"
											   value="<%= (uprodVO==null)? "" : uprodVO.getProdName()%>" />
									</td>
								</tr>
								<tr>
									<td>商品內容：</td>
									<td style="text-align:left">
									    <textarea id="1232" name="ProdIntro" rows="5" cols="45" 
									    		  wrap="off" style="resize:none;">${uprodVO.prodIntro}</textarea>
									</td>
								</tr>	
								<tr>
									<td>價格：</td>
									<td style="text-align:left">
										<input type="TEXT" id="1233" name="Price" size="45"
											   value="<%= (uprodVO==null)? "" : uprodVO.getPrice()%>" />
									</td>
	                            </tr>
	                            <tr>
									<td>商品種類：</td>	
									<td style="text-align:left">
							           <select name="ProdType" size="1">
								           <option value=A selected>毛孩伙食</option>
								           <option value=B >日常用品 </option>
								           <option value=C>清潔用品 </option>
							           </select>
						            </td>
								</tr>
								<tr>
								    <td>商品照片：</td>
								    <td style="text-align:left">
								  		<input type='file' name="Photo" accept="image/*" onchange="PreviewImage(this)"/>
								    </td>
								</tr>
								<tr>
									<td></td>
									<td style="text-align:left">
										<div>
								  			<img id="imgPreview" src="<%=request.getContextPath()%>/img/none2.jpg" 
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
	                        				取消</button>
                        	</div>
                        </div>
                        <div class="col-lg-4 offset-lg-4">
                            <div class="proceed-checkout">
                            	<input type="hidden" name="SellerNo" value="<%=memVO.getMemNo()%>">
                            	<input type="hidden" name="action" value="insert">
                            	<button type="submit" class="proceed-btn buy_prod" style="background-color: #E7AB3C;">
                              		 確認上架
                                </button>
                            </div>
                        </div>
                    </div>
                    <div>
                        <button id="123">神奇小按鈕</button> 
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

    $("#123").click(function(){
	$("#1231").val("吉娃娃吐司");
	$("#1232").val("商品描述:<br><br>早晨快速補充能量<br>營養美味可口<br>廣受台灣人喜愛<br>迎接美好的一天<br><br>產地:台灣<br>成分:可愛吉娃娃");
	$("#1233").val(9999);
	event.preventDefault();
	})
	
	function PreviewImage(imgFile) {
	var pattern = /(\.*.jpg$)|(\.*.png$)|(\.*.jpeg$)|(\.*.gif$)|(\.*.bmp$)/;
	if (!pattern.test(imgFile.value)) {
			alert("只支援jpg/jpeg/png/gif/bmp之格式檔案");
			imgFile.focus();
		} else {
			 var imgPreview = document.getElementById('imgPreview');
			 imgPreview.src = URL.createObjectURL(event.target.files[0]);
		};
	}
</script>
	
</body>
</html>