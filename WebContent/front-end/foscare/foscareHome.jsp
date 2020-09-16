<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ page import="com.foscare.model.*"%>
<!DOCTYPE html>
<html lang="zxx">

<head>
    <meta charset="UTF-8">
    <meta name="description" content="Fashi Template">
    <meta name="keywords" content="Fashi, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>GGYY</title>
 
</head>
<body>
	<%@ include file="/front-end/Header/header.jsp" %>
   	 <input type="hidden" id="isMom" value="${isFosm}">
     <section class="blog-section spad" id="foshometop">
        <div class="container">
            <div class="row" id="foshomepho">
                <div class="col-2">
					<div class="blog-sidebar">

						<div class="blog-catagory">
							<div>
								<h4>寄養專區</h4>
								<ul>
									<li><a href="addfos.jsp">填寫寄養單</a></li>
									<li><a href="readfos.jsp">查看寄養單</a></li>
									<li><a href="serchfosm.jsp">搜尋保母</a></li>
								</ul>
							</div>
							<div class="sub-catagory">
								<a href="fosmHome.jsp">保母專區</a>
							</div>
						</div>
					</div>
				</div>
                <div class="col-lg-10 order-1 order-lg-2">
                    <div class="row">
                        <div class="col-lg-6 col-sm-6">
                            <img src="img/cat1.jpeg" alt="">
                        </div>
                        <div class="col-lg-6 col-sm-6">
                            <div class="jihome">
	                            <h4><b>我有寄養需求</b></h4>
	                            <br><br><br>
	                            <div>什麼樣的人適合?
		                            <ul style="list-style:none;">
		                                <li>　-工作需要出差</li>
		                                <li>　-國內外旅遊者</li>
		                            </ul>
	                            </div>
	                            <a href="addfos.jsp"><button type="submit" class="site-btn">申請登記需求</button></a>
                         	</div>
                        </div>
                        <div class="col-lg-6 col-sm-6">
                            <div class="jihome">
	                            <h4><b>我想成為寵物保母</b></h4>
	                            <br><br><br>
	                            <div>什麼樣的人適合?
		                            <ul style="list-style:none;">
		                                <li>　-熱愛與動物想處</li>
		                                <li>　-經常在家工作者</li>
		                            </ul>
	                            </div>
	                            <button type="submit" class="site-btn">申請登記需求</button>
                         	</div>
                        </div>
                        <div class="col-lg-6 col-sm-6">
                            <img src="img/cat3.jpeg" alt="">
                        </div>                                          
                    </div>
                </div>
            </div>
        </div>
    </section>

	<%@ include file="/front-end/Footer/footer.jsp" %>
	<c:if test="${LoginMem!=null}">
		<%@ include file="/chatRoom/chatRoom.jsp"%>
	</c:if>
	<script>
	$(document).ready(function(){
		let isMom= $("#isMom").val();
		
		if(isMom != ""){
			swal('你還沒成為保母！', '請點選下方申請按鈕去申請！', 'error');
		}
	})	
	</script>
	</body>
</html>
<% session.removeAttribute("isFosm"); %>