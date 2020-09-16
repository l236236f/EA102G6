<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ include file="/front-end/Header/header.jsp" %>
<%@ page import="ANNOUNCEMENT.model.*"%>

<!DOCTYPE html>
<html>
<head>
<title>寄寄養養</title>
<style>
	.col-lg-4{
	height:300px;
	overflow:hidden;
	}
	.col-lg-4 img{
	height:100%;
	width:100%;
	}
</style>
</head>
<body>
<% 
	session.removeAttribute("sprodDetail");
	session.removeAttribute("shoppingcart");
	//公告
	ANNService annSvc = new ANNService();
	List<ANNVO> list = annSvc.getAll();
	pageContext.setAttribute("list", list);
	List<ANNVO> annlist = new ArrayList<ANNVO>();
	
%>

<section class="hero-section">
        <div class="hero-items owl-carousel">
            <div class="single-hero-items set-bg" data-setbg="<%= request.getContextPath()%>/front-end/img/cat.png">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-5">
                            <span>Hey!奴才</span>
                            <h1>快給我罐罐</h1>
                            <p>我才是生物鏈的頂點，懂?</p>
                            <a href="#" class="primary-btn">Shop Now</a>
                        </div>
                    </div>  
                </div>
            </div>
            <div class="single-hero-items set-bg" data-setbg="<%= request.getContextPath()%>/front-end/img/dog.png">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-5">
                            <span>Hey!主人!</span>
                            <h1>帶我去散步</h1>
                            <p>不然我就拉屎在家裡，懂?</p>
                            <a href="#" class="primary-btn">Shop Now</a>
                        </div>
                    </div>
                    
                </div>
            </div>
        </div>
    </section>
    <!-- Hero Section End -->

    <!-- Banner Section Begin -->
    <div class="banner-section spad">
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-4">
                    <h3>宣告</h3>
                    <div class="single-banner">
                    	<img src="<%= request.getContextPath()%>/back-end/emp/ANNImg?annno=
						<c:forEach var="annVO" items="${list}">
							<c:if test="${annVO.annstatus == 'ANN1'}">${annVO.annno}</c:if>
						</c:forEach>">
					</div>
                </div>
                
                <div class="col-lg-4">
                    <h3>取值</h3>
                    <div class="single-banner">
                    	<img src="<%= request.getContextPath()%>/back-end/emp/ANNImg?annno=
						<c:forEach var="annVO" items="${list}">
							<c:if test="${annVO.annstatus == 'ANN2'}">${annVO.annno}</c:if>
						</c:forEach>">
					</div>
                </div>
                <div class="col-lg-4">
                    <h3>修但幾勒</h3>
                    <div class="single-banner">
                        <img src="<%= request.getContextPath()%>/back-end/emp/ANNImg?annno=
						<c:forEach var="annVO" items="${list}">
							<c:if test="${annVO.annstatus == 'ANN3'}">${annVO.annno}</c:if>
						</c:forEach>">
					</div>
                </div>
            </div>
        </div>
    </div>
   

<%@ include file="/front-end/Footer/footer.jsp" %>
<c:if test="${LoginMem!=null}">
	<%@ include file="/chatRoom/chatRoom.jsp"%>
</c:if>
</body>
<script type="text/javascript">
	$("document").ready(function(){
		var success = "<%=(String)request.getAttribute("success")%>";
		if(success === "mem"){
			swal("註冊成功,請至電子信箱進行驗證!!!","","success");
		}
	});
</script>
</html>