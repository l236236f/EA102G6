<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ page import="com.foscare.model.*"%>
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
	  String memNo = memVO.getMemNo();
	  FosterVO fosterVO = (FosterVO)request.getAttribute("fosterVO");
		String fosType = "1";
		String fosSize = "1";
		String fosnrun = "1";
	  if(fosterVO != null){
			fosType = (fosterVO.getFosType()==null)?"1":fosterVO.getFosType();
			fosSize = (fosterVO.getFosSize()==null)?"1":fosterVO.getFosSize();
			fosnrun = (fosterVO.getFosnrun()==null)?"1":fosterVO.getFosnrun();
		}
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
				<div class="col-lg-10 order-1 order-lg-2">
					<div class="row">
						<div class="col-lg-12 col-sm-6">
							<form action="fos.do" class="checkout-form" method="post">
								<input  type="hidden"  name="url" value="<%= request.getContextPath()%>/front-end/foscare/readfos.jsp">
								<div class="row">
									<div class="col-lg-12" id="addfos">
										<h4>��g�H�i��</h4>
										<div class="row">
											<div class="col-lg-5 col-sm-11">
												<div class="col-lg-1"></div>
												<div class="col-lg-11">
													<%-- ���~��C --%>
													<c:if test="${not empty errorMsgs}">
														<font style="color:red">�Эץ��H�U���~:</font>
														<ul>
															<c:forEach var="message" items="${errorMsgs}">
																<li style="color:red">${message}</li>
															</c:forEach>
														</ul>
													</c:if>
												</div>
											</div>
											<div class="col-lg-7  col-sm-1"></div>
											<div class="col-lg-4">
												<label for="fir">�|���m�W<span> *</span></label>
												<input type="text" id="fir" value="${LoginMem.memName}" disabled> 
												<input type="hidden"  name="memNo" value="${LoginMem.memNo}">
											</div>
											
											<div class="col-lg-1"></div>
											<div class="col-lg-4 ">
												<label for="fir">�s���q��<span> *</span></label> <input
													type="text" value="${LoginMem.memTel}" disabled>
											</div>
											<div class="col-lg-3 "></div>
											
											
											<div class="col-lg-9">
												<label for="last">�a�}�ϰ�<span> *</span></label> <input
													type="text" value="${LoginMem.memAddr}"  disabled>
											</div>
											<div class="col-lg-3"></div>
											
											
											<div class="col-lg-3 margin-bottom-normal">
												<div class="label-title">�d���W�r<span class="red-must-type-selector"> *</span></div>
												<div class="pet-name-selector-full">
													<select class="sorting" name="petNo">
														<c:forEach var="petVO" items="<%= fosSvc.getPetNames(memNo) %>">
		    												<option value="${petVO.petNo}" ${(petVO.petNo==fosterVO.petNo)?'selected':''}>${petVO.petName}
		    											</c:forEach>
													</select>
												</div>
											</div>
											<div class="col-lg-2 margin-bottom-normal">
												<div class="label-title">�d������<span class="red-must-type-selector"> *</span></div>
												<div class="pet-name-selector-half">
													<select class="sorting" name="fosType">
														<!--�h��-->
														<option value="��" <%= (fosType.equals("��"))?"selected":""%>>��</option>
														<option value="��" <%= (fosType.equals("��"))?"selected":""%>>��</option>
														<option value="��L" <%= (fosType.equals("��L"))?"selected":""%>>��L</option>
													</select>
												</div>
											</div>
											<div class="col-lg-2 margin-bottom-normal">
												<div class="label-title">�d���髬�j�p<span class="red-must-type-selector"> *</span></div>
												<div class="pet-name-selector-half">
													<select class="sorting" name="fosSize">
														<!--�h��-->
														<option value="�p��" <%= (fosSize.equals("�p��"))?"selected":""%>>�p��</option>
														<option value="����" <%= (fosSize.equals("����"))?"selected":""%>>����</option>
														<option value="�j��" <%= (fosSize.equals("�j��"))?"selected":""%>>�j��</option>
													</select>
												</div>
											</div>
											<div class="col-lg-2 margin-bottom-normal">
												<div class="label-title">�O�_�n�N��<span class="red-must-type-selector"> *</span></div>
												<div class="pet-name-selector-half">
													<select class="sorting" name="fosnrun">
														<!--�h��-->
														<option value="�O" <%= (fosnrun.equals("�O"))?"selected":""%>>�O</option>
														<option value="�_" <%= (fosnrun.equals("�_"))?"selected":""%>>�_</option>
													</select>
												</div>
											</div>
											<div class="col-lg-3"></div>
											
											
											<div class="col-lg-4">
												<label for="fir">�}�l�ɶ�<span> *</span></label> <input
													type="text" id="startTime" name="startTime">
											</div>
											<div class="col-lg-4">
												<label for="last">�����ɶ�<span> *</span></label> <input
													type="text" id="endTime" name="endTime">
											</div>
											<div class="col-lg-4"></div>


											<div class="col-lg-4">
												<label for="money">����<span> *</span></label> <input
													type="text" id="money" name="fosMoney">
											</div>
											<div class="col-lg-8"></div>
											
											<div class="col-lg-11">
												<textarea placeholder="�Ƶ�" id="remark" name="fosRemark"></textarea>
												<button type="submit" class="site-btn">�e�X�H�i��</button>
												<input type="hidden" name="action" value="insert">
											</div>
											<div class="col-lg-1">
<!-- 												<img src="img/cat5.jpg"> -->
											</div>
										</div>
									</div>
									<div class="col-lg-6">
										<div class="checkout-content"></div>
										<div class="place-order">
										</div>
									</div>
								</div>
							</form>
							<button id="magic">���_�p���s</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<% 
  	java.sql.Date startTime = null;
  	java.sql.Date endTime = null;
  	try {
	    startTime = fosterVO.getFosStartTime();
	    endTime  = fosterVO.getFosEndTime();
   	} catch (Exception e) {
	   startTime = new java.sql.Date(System.currentTimeMillis());
	   endTime = new java.sql.Date(System.currentTimeMillis());
   	}
 	%> 

	<%@ include file="/front-end/Footer/footer.jsp" %>
	<c:if test="${LoginMem!=null}">
		<%@ include file="/chatRoom/chatRoom.jsp"%>
	</c:if>
<%-- 	<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script> --%>
	<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>
	
	<script type="text/javascript">
		$(document).ready(function(){
			let success= $("#isSuccess").val();
			let successVal = $("#successVal").val();
			if(success == "No"){
				swal(successVal, '���_�J�I�ٴ��ÿ�J��!', 'error');
			}
			
			$("#magic").click(function(){
				$("#money").val("123");
				$("#remark").text('dgk;sjdg;dlsgljd;lsgl;d;lasgn;ldp;lgnndsngdskgvdsnv,bnkscvbjbdklsf;dfl');
			});	
			
		});
	</script>
	
	<script>
		$.datetimepicker.setLocale('zh'); // kr ko ja en
		$(function(){
			 $('#startTime').datetimepicker({
			  format:'Y-m-d',
			  onShow:function(){
			   this.setOptions({
			    maxDate:$('#endTime').val()?$('#endTime').val():false
			   })
			  },
			  timepicker:false,
			  value: '<%= startTime %>'
			 });
			 
			 $('#endTime').datetimepicker({
			  format:'Y-m-d',
			  onShow:function(){
			   this.setOptions({
			    minDate:$('#startTime').val()?$('#startTime').val():false
			   })
			  },
			  timepicker:false,
			  value: '<%= endTime %>'
			 });
		});
	</script>
</body>
<% session.removeAttribute("isSuccess"); 
   session.removeAttribute("successVal");
%>
</html>