<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ include file="/front-end/Header/header.jsp" %>
<%@ page import="ANNOUNCEMENT.model.*"%>

<!DOCTYPE html>
<html>
<head>
<title>�H�H�i�i</title>
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
	//���i
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
                            <span>Hey!���~</span>
                            <h1>�ֵ�������</h1>
                            <p>�ڤ~�O�ͪ��쪺���I�A��?</p>
                            <a href="#" class="primary-btn">Shop Now</a>
                        </div>
                    </div>  
                </div>
            </div>
            <div class="single-hero-items set-bg" data-setbg="<%= request.getContextPath()%>/front-end/img/dog.png">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-5">
                            <span>Hey!�D�H!</span>
                            <h1>�a�ڥh���B</h1>
                            <p>���M�ڴN�ԫ˦b�a�̡A��?</p>
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
                    <h3>�ŧi</h3>
                    <div class="single-banner">
                    	<img src="<%= request.getContextPath()%>/back-end/emp/ANNImg?annno=
						<c:forEach var="annVO" items="${list}">
							<c:if test="${annVO.annstatus == 'ANN1'}">${annVO.annno}</c:if>
						</c:forEach>">
					</div>
                </div>
                
                <div class="col-lg-4">
                    <h3>����</h3>
                    <div class="single-banner">
                    	<img src="<%= request.getContextPath()%>/back-end/emp/ANNImg?annno=
						<c:forEach var="annVO" items="${list}">
							<c:if test="${annVO.annstatus == 'ANN2'}">${annVO.annno}</c:if>
						</c:forEach>">
					</div>
                </div>
                <div class="col-lg-4">
                    <h3>�צ��X��</h3>
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
			swal("���U���\,�Цܹq�l�H�c�i������!!!","","success");
		}
	});
</script>
</html>