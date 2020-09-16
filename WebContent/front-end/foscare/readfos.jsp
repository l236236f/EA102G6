<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.foscare.model.*"%>
<%@ page import="com.fosmon.model.*"%>
<%@ page import="com.pet.model.*"%>

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
	<%
		
		FosterService fosSvc = new FosterService();
		FosmService fmSvc = new FosmService();
		pageContext.setAttribute("fmSvc", fmSvc);
		String memNo = memVO.getMemNo();
		int count = 1;
	%>
	<input type="hidden" id="isSuccess" value="${isSuccess}">
	<input type="hidden" id="successVal" value="${successVal}">
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
				<div class="col-10 ">
					<div class="row">
						<div class="col-12">
							<c:forEach var="fosVO" items="<%=fosSvc.getAllByMemNo(memNo)%>">
							<div  class="checkout-form" id="${fosVO.fosNo}">
								<div class="row">	
									<div class="col-12" id="addfos">
										
											<h4 class="main-accent-color">
												�H�i��-${fosVO.fosNo}�@
												<input type="hidden" name="fosNo" value="${fosVO.fosNo}">
												�O���s��-<span class="showFosm" data-showfosm="<%= request.getContextPath() %>/front-end/foscare/readOnefosm.jsp?oneMom=${fosVO.fosmNo}">${(fosVO.fosmNo==null)?'���T�{':fosVO.fosmNo}</span>
											</h4>
											<div class="row">
												<div class="col-3">
													<span>�d���W�r�G
														<a href="<%= request.getContextPath() %>/front-end/pet/showInfoPet.jsp?showPetNo=${fosVO.petNo}">
															<c:forEach var="petVO" items="<%= fosSvc.getPetNames(memNo) %>">
			    												${(fosVO.petNo==petVO.petNo)?petVO.petName:''}
			    											</c:forEach>
														</a>
													</span>
												</div>
												<div class="col-3">
													<span>�d�������G${fosVO.fosType}</span>
												</div>
												<div class="col-3">
													<span>�d���髬�j�p�G${fosVO.fosSize}</span>
												</div>
												<div class="col-3">
													<span>�O�_�n�N���G ${fosVO.fosnrun}</span>
												</div>

												<div class="col-4">
													<br> <span>�}�l�ɶ��G<fmt:formatDate value="${fosVO.fosStartTime}" pattern="yyyy-MM-dd"/></span>
												</div>
												<div class="col-4">
													<br> <span>�����ɶ��G<fmt:formatDate value="${fosVO.fosEndTime}" pattern="yyyy-MM-dd"/></span>
												</div>
												
												<div class="col-8">
													<br> <span>����G ${fosVO.fosMoney}</span>
												</div>
												<div class="col-lg-4"></div>
												
												<div class="col-7">
													<br>
													<textarea placeholder="�L�Ƶ�" disabled>${fosVO.fosRemark}</textarea>
												</div>
												<div class="col-lg-5" id="${fosVO.fosNo}signimg">
													<c:if test="${fosVO.fosSignA != null}">
														<img src="<%= request.getContextPath() %>/front-end/ShowPhotos?type=fossign&AB=A&photo_no=${fosVO.fosNo}">
													</c:if>
												</div>
												
												<div class="col-lg-12">

													<div class="margin-bottom-normal">���A�G
													<span style="font-weight: bold; font-size: 18px" id="${fosVO.fosNo}status">${(fosVO.fosStatus=='F0')?'���T�{':''}
														${(fosVO.fosStatus == 'F1')?'�ݽT�{':''}
														${(fosVO.fosStatus == 'F3' || fosVO.fosStatus == 'F2')?'��ñ�W':''}
														${(fosVO.fosStatus == 'F4')?'�ݥI��':''}
														${(fosVO.fosStatus == 'F5')?'�ݦ���':''}
														${(fosVO.fosStatus == 'F6')?'�i�椤':''}
														${(fosVO.fosStatus == 'F7'||fosVO.fosStatus == 'F8')?'�w����':''}</span>
													</div>
													<input type="hidden" id="${fosVO.fosNo}needsign" value="${((fosVO.fosStatus=='F2'||fosVO.fosStatus=='F3')&&fosVO.fosSignA==null)?'1':'0'}">
													<span class="sign"><button  class="site-btn margin-right-small" data-toggle="modal" data-target="#exampleModalLong" data-a_sign="${(fosVO.fosSignA==null)?'1':'0'}" data-b_sign="${(fosVO.fosSignB==null)?'1':'0'}">�d�ݦX����</button></span>
													<input type="hidden" value="${fosVO.fosNo},A">
													<c:if test="${fosVO.fosStatus=='F0'||fosVO.fosStatus=='F1'}">
														<span class="update9"><button type="submit" class="site-btn">�ק�H�i��</button></span>
														<input type="hidden" name="action" value="fos.do?action=getOne_For_Update&fosNo=${fosVO.fosNo}">
													</c:if>
													<c:if test="${fosVO.fosStatus=='F4'}">
														<input type="hidden" value="">
														<span class="money">
															<button  class="site-btn" data-fos_no="${fosVO.fosNo}">�T�{�I��</button>
															<input type="hidden" value="${fmSvc.getOneByFosmNo(fosVO.fosmNo).memNo}">
														</span>
														<input type="hidden" class="moneyto" value="fos.do?AB=A&action=changeSta&fosStatus=F5&fosNo=${fosVO.fosNo}">
													</c:if>
													<c:if test="${fosVO.fosStatus=='F7'}">
														<input type="hidden" id="<%= count%>">
														<input type="hidden" value="${fosVO.fosmNo}">
														<span class="addeva" id="<%= count%>"><button  class="site-btn" data-toggle="modal" data-target="#exampleModalLong1">�����O��</button></span>
														<input type="hidden" value="${fosVO.fosNo}">
													</c:if>
													<% count++; %>																										
												</div>
											</div>										
										</div>		
									</div>
								</div>
							  <br><br><br>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	
	<%@ include file="/front-end/Footer/footer.jsp" %>
	<%@ include file="/front-end/foscare/contract.jsp" %>
	<%@ include file="/front-end/foscare/rating.jsp" %>
	<c:if test="${LoginMem!=null}">
		<%@ include file="/chatRoom/chatRoom.jsp"%>
	</c:if>
	<script>
		$(document).ready(function(){
	
			let success= $("#isSuccess").val();
			let successVal = $("#successVal").val();
			if(success == "Yes"){
				swal(successVal, '��n��!', 'success');
			}
			
			$(".sign button").click(function(){
				//�����u�����Ѽ�
				var fosNo = $(this).parent().next().val();
				var para = fosNo.split(',');
				$("#fosNo").val(para[0]);
				$("#AandB").val(para[1]);
				//�P�_�ۤv����O�_ñ�L�W
				var asign = $(this).data("a_sign");
				var bsign = $(this).data("b_sign");
				if(asign == 0){
					$("#Asign").html('<img src="<%= request.getContextPath() %>/front-end/ShowPhotos?type=fossign&AB=A&photo_no='+para[0]+'">');
				}
				if(bsign == 0){
					$("#Bsign").html('<img src="<%= request.getContextPath() %>/front-end/ShowPhotos?type=fossign&AB=B&photo_no='+para[0]+'">');
				}
				//�P�_�O�_�wñ�L�A�Yñ�L�h����ñ�W�P�e�X�����s
				var a = $(this).parent().prev().val();
				if(a=='0'){
					$("#sendsign").hide();
					$("#canv").hide();
				}else{
					$("#sendsign").show();
					$("#canv").show();
				}
			});
			//�����ɦV��s����
			$(".update9 button").click(function(e){
				e.preventDefault();
				let update = $(this).parent().next().val(); 
				window.location.href=update;
			});
			//���ܪ��A
			$(".money button").click(function(e){
				e.preventDefault();
				let money = $(this).parent().next().val();
				let receiver = $(this).next().val();
				var thisbutton = $(this);
				var a = $(this).data("fos_no");
				$.ajax({
					url:money,
					type:"GET",
					success:function(data){
						swal('�I�ڦ��\�I', '�p�p�p!', 'success');
						thisbutton.hide();
						$("#"+a+"status").text('�ݦ���');
					},
					error:function(){
						swal('�I�ڥ��ѡI', '��aB!', 'error');
					}
				});
				jsonObj = {
					"type" : "notice",
					"sender" : "${LoginMem.memNo}",
					"receiver" : receiver,
					"contain" : "�a�誺�H�i��w�I�ڡA�ݭn�z���ֽT�{!!"
						
				};
				webSocketN.send(JSON.stringify(jsonObj));
				
			});
			//��g����
			$(".addeva button").click(function(e){
				e.preventDefault();
				var fosNo = $(this).parent().next().val();
				var fosmNo = $(this).parent().prev().val();
				//�]�wrating.jsp�̪��������value
				$("#fosNo2").val(fosNo);
				$("#fosmNo2").val(fosmNo);
			});
			
			//�ݫO�����Э�
			$(".showFosm").click(function(){
				var showfosm = $(this).data("showfosm");
				var hasfosm = $(this).text();
				console.log(showfosm);
				if(hasfosm != '���T�{'){
					console.log('a');
					window.location.href = showfosm;
				}	
			});
		});
		
	</script>
</body>
<% session.removeAttribute("isSuccess"); 
   session.removeAttribute("successVal");
%>
</html>