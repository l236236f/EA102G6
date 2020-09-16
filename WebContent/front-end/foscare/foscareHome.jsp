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
								<h4>�H�i�M��</h4>
								<ul>
									<li><a href="addfos.jsp">��g�H�i��</a></li>
									<li><a href="readfos.jsp">�d�ݱH�i��</a></li>
									<li><a href="serchfosm.jsp">�j�M�O��</a></li>
								</ul>
							</div>
							<div class="sub-catagory">
								<a href="fosmHome.jsp">�O���M��</a>
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
	                            <h4><b>�ڦ��H�i�ݨD</b></h4>
	                            <br><br><br>
	                            <div>����˪��H�A�X?
		                            <ul style="list-style:none;">
		                                <li>�@-�u�@�ݭn�X�t</li>
		                                <li>�@-�ꤺ�~�ȹC��</li>
		                            </ul>
	                            </div>
	                            <a href="addfos.jsp"><button type="submit" class="site-btn">�ӽеn�O�ݨD</button></a>
                         	</div>
                        </div>
                        <div class="col-lg-6 col-sm-6">
                            <div class="jihome">
	                            <h4><b>�ڷQ�����d���O��</b></h4>
	                            <br><br><br>
	                            <div>����˪��H�A�X?
		                            <ul style="list-style:none;">
		                                <li>�@-���R�P�ʪ��Q�B</li>
		                                <li>�@-�g�`�b�a�u�@��</li>
		                            </ul>
	                            </div>
	                            <button type="submit" class="site-btn">�ӽеn�O�ݨD</button>
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
			swal('�A�٨S�����O���I', '���I��U��ӽЫ��s�h�ӽСI', 'error');
		}
	})	
	</script>
	</body>
</html>
<% session.removeAttribute("isFosm"); %>