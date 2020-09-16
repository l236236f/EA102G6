<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.foscare.model.*"%>
<%@ page import="com.fosmon.model.*"%>
<%@ page import="com.mem.model.*"%>
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
		FosmVO fosmVO = (FosmVO)session.getAttribute("LoginFosm");
		String fosmNo = fosmVO.getFosmNo();
		String memNo2 = fosmVO.getMemNo();	
	%>
	<input type="hidden" id="isSuccess" value="${isSuccess}">
	<input type="hidden" id="successVal" value="${successVal}">
	<section class="blog-section spad padding-top-section-style">
		<div class="container">
			<div class="row" id="foshomepho">
				<div class="col-2">
					<div class="blog-sidebar">

						<div class="blog-catagory">
							<h4>�O���M��</h4>
                            <ul>
                                <li><a href="fosmHome.jsp">�޲z�ڪ����Э�</a></li>
                                <li><a href="mreadfos.jsp">�޲z�ڪ��H�i��</a></li>
                                
                            </ul>
						</div>
					</div>
				</div>
				<div class="col-10">
					<div class="row">
					<jsp:useBean id="fosSvc" class="com.foscare.model.FosterService" />
						<div class="col-lg-12 col-sm-6">
							<c:forEach var="fosVO" items="<%= fosSvc.getAllByFosmNo(fosmNo) %>">
							<div  class="checkout-form">
								<div class="row">	
									<div class="col-lg-12" id="addfos">
										<%  %>
											<h4 class="sub-accent-color">
												�H�i��-${fosVO.fosNo}�@�@�@
												<input type="hidden" name="fosNo" value="${fosVO.fosNo}">
												�H�i�H�|���s��-<a href="<%= request.getContextPath() %>/front-end/mem/showInfoMem.jsp?showMemNo=${fosVO.memNo}">${fosVO.memNo}</a>
											</h4>
											<div class="row">
												<div class="col-lg-3">
													<span>�d���W�r�G
														<a href="<%= request.getContextPath() %>/front-end/pet/showInfoPet.jsp?showPetNo=${fosVO.petNo}"> 
															<c:forEach var="petVO" items="${fosSvc.getPetNames(fosVO.memNo)}">
			    												${(fosVO.petNo==petVO.petNo)?petVO.petName:''}
			    											</c:forEach>
														</a>
													</span>
												</div>
												<div class="col-lg-3">
													<span>�d�������G ${fosVO.fosType}</span>
												</div>
												<div class="col-lg-3">
													<span>�d���髬�j�p�G  ${fosVO.fosSize}</span>
												</div>
												<div class="col-lg-3">
													<span>�O�_�n�N���G  ${fosVO.fosnrun}</span>
												</div>

												<div class="col-lg-4">
													<br> <span>�}�l�ɶ��G �@<fmt:formatDate value="${fosVO.fosStartTime}" pattern="yyyy-MM-dd"/></span>
												</div>
												<div class="col-lg-4">
													<br> <span>�����ɶ��G �@<fmt:formatDate value="${fosVO.fosEndTime}" pattern="yyyy-MM-dd"/></span>
												</div>
												
												<div class="col-lg-8">
													<br> <span>����G ${fosVO.fosMoney}</span>
												</div>
												<div class="col-lg-4"></div>
												
												
												<div class="col-lg-7">
													<br>
													<textarea placeholder="�L�Ƶ�" disabled>${fosVO.fosRemark}</textarea>
												</div>
												<div class="col-lg-5" id="${fosVO.fosNo}signimg">
													<c:if test="${fosVO.fosSignB != null}">
														<img src="<%= request.getContextPath() %>/front-end/ShowPhotos?type=fossign&AB=B&photo_no=${fosVO.fosNo}">
													</c:if>
												</div>				
												<div class="col-lg-12">
													<div class="margin-bottom-normal">���A�G
														<span style="font-weight: bold; font-size: 18px" id="${fosVO.fosNo}status">${(fosVO.fosStatus == 'F1')?'�ݽT�{':''}
														${(fosVO.fosStatus == 'F2' || fosVO.fosStatus == 'F3')?'��ñ�W':''}
														${(fosVO.fosStatus == 'F4')?'�ݥI��':''}
														${(fosVO.fosStatus == 'F5')?'�ݦ���':''}
														${(fosVO.fosStatus == 'F6')?'�i�椤':''}
														${(fosVO.fosStatus == 'F7'|| fosVO.fosStatus == 'F8')?'�w����':''}</span>
													</div> 													
													<input type="hidden" name="action" value="getOne_For_Update">
													<input type="hidden" id="${fosVO.fosNo}needsign" value="${((fosVO.fosStatus=='F2'||fosVO.fosStatus=='F3')&&fosVO.fosSignB==null)?'1':'0'}">
													<span class="sign"><button  class="site-btn margin-right-small sub-accent-bg-color"  data-toggle="modal" data-target="#exampleModalLong" data-a_sign="${(fosVO.fosSignA==null)?'1':'0'}" data-b_sign="${(fosVO.fosSignB==null)?'1':'0'}">�d�ݦX����</button></span>															
													<input type="hidden" value="${fosVO.fosNo},B">													
													
													<c:if test="${fosVO.fosStatus=='F1'}">
														<span class="check"><button type="submit" class="site-btn sub-accent-bg-color" data-fos_no="${fosVO.fosNo}">�T�{�H�i��</button></span>
														<input type="hidden" value="fos.do?AB=B&action=changeSta&fosStatus=F2&fosNo=${fosVO.fosNo}">
													</c:if>
													<c:if test="${fosVO.fosStatus=='F5'}">
														<span class="money"><button  class="site-btn sub-accent-bg-color" data-fos_no="${fosVO.fosNo}" data-mem_no="${fosVO.memNo}">�T�{����</button></span>
														<input type="hidden" class="moneyto" value="fos.do?AB=B&action=changeSta&fosStatus=F6&fosNo=${fosVO.fosNo}">
													</c:if>																																										
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
	<c:if test="${LoginMem!=null}">
		<%@ include file="/chatRoom/chatRoom.jsp"%>
	</c:if>
	
	<script type="text/javascript">
		$(document).ready(function(){
		
			let success= $("#isSuccess").val();
			let successVal = $("#successVal").val();
			if(success == "Yes"){
				swal(successVal, '��n��!', 'success');
			}			
			$(".sign button").click(function(){
				var fosNo = $(this).parent().next().val();
				var para = fosNo.split(',');
				$("#fosNo").val(para[0]);
				$("#AandB").val(para[1]);
				var a = $(this).parent().prev().val();
				//�P�_�ۤv����O�_ñ�L�W
				var asign = $(this).data("a_sign");
				var bsign = $(this).data("b_sign");
				if(asign == 0){
					$("#Asign").html('<img src="<%= request.getContextPath() %>/front-end/ShowPhotos?type=fossign&AB=A&photo_no='+para[0]+'">');
				}
				if(bsign == 0){
					$("#Bsign").html('<img src="<%= request.getContextPath() %>/front-end/ShowPhotos?type=fossign&AB=B&photo_no='+para[0]+'">');
				}
				
				if(a=='0'){
					$("#sendsign").hide();
					$("#canv").hide();
				}else{
					$("#sendsign").show();
					$("#canv").show();
				}
			});	
			$(".check button").click(function(e){
				e.preventDefault();
				let changesta = $(this).parent().next().val();
				var thisbutton = $(this);
				var a = $(this).data("fos_no");
				$.ajax({
					url:changesta,
					type:"GET",
					success:function(data){
						swal('�T�{���\�I', '�p�p�p!', 'success');
						thisbutton.hide();
						$("#"+a+"status").text('��ñ�W');
						$("#"+a+"needsign").val('1');
					},
					error:function(){
						swal('�T�{���ѡI', '��aB!', 'error');
					}
				});
			});
			$(".money button").click(function(e){
				e.preventDefault();
				let changesta = $(this).parent().next().val();
				var thisbutton = $(this);
				var a = $(this).data("fos_no");
				var tomem = $(this).data("mem_no");
				let receiver = 
				$.ajax({
					url:changesta,
					type:"GET",
					success:function(data){
						swal('�T�{���\�I', '�p�p�p!', 'success');
						thisbutton.hide();
						$("#"+a+"status").text('�i�椤');
					},
					error:function(){
						swal('�T�{���ѡI', '��aB!', 'error');
					}
				});
				jsonObj = {
						"type" : "notice",
						"sender" : "${LoginMem.memNo}",
						"receiver" : tomem,
						"contain" : "�z���H�i��"+a+"�O���w����ڶ�!!"
							
					};
				webSocketN.send(JSON.stringify(jsonObj));
			});
		});
	</script>
</body>
<% session.removeAttribute("isSuccess"); 
   session.removeAttribute("successVal");
%>
</html>