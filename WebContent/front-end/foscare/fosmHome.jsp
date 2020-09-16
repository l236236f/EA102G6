<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ page import="com.foscare.model.*"%>
<%@ page import="com.fosmon.model.*"%>
<%@ page import="com.fosmphoto.model.*" %>
<!DOCTYPE html>
<html lang="zxx">
<head>
    <meta charset="UTF-8">
    <meta name="description" content="Fashi Template">
    <meta name="keywords" content="Fashi, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>GGYY</title>
    <style>
		#fosmcontain #changepage img{
			width:24px;
			height:24px;
		}
		
		#fosmcontain #fmpho img.showBig{
			display: inline;
		    position: fixed;
		    width: 1000px;
		    height: 700px;
		    top: 10%;
		    left: 30%;
		    display: inline;
		    z-index: 1;
		}
		#Eva{
			background-color: #f4f4f4;
			padding: 60px 60px 60px 40px;
			border-radius: 0px 0px 8px 8px;
		}
		#fosmcontain #Eva img{
			width:12px;
			height:12px;
		}
		#Eva .toright input{
			width:50%;			
		}
		.toright{
			text-align: right;
		}
		#trash>img{
			position:absolute;
			right: 5%;
			top: 60%;
			width:100px;
			height:100px;
		}
		#opengar{
			display:none;
		}
	</style>   
</head>
<body>
	<%@ include file="/front-end/Header/header.jsp" %>
		
	<% 
		FosmService fmSvc = new FosmService();
		pageContext.setAttribute("fmSvc",fmSvc);
		FosmVO fosmVO = null;
		if(request.getAttribute("fmVO")!=null){
			fosmVO = (FosmVO)request.getAttribute("fmVO");
		}else{
			fosmVO =  fmSvc.getOneFosm(memVO.getMemNo());
		}
		String fosmNo = fosmVO.getFosmNo();
		pageContext.setAttribute("fosmNo",fosmNo);
		session.setAttribute("LoginFosm", fosmVO);
		FosmPhoService fmpSvc = new FosmPhoService();
		List<String> list = fmpSvc.getAllByFosm(fosmNo);
		pageContext.setAttribute("list",list);
	%>
	<input type="hidden" value="<%= list.toString() %>">
	<input type="hidden" id="isSuccess" value="${isSuccess}">
	<input type="hidden" id="successVal" value="${successVal}">
    <section class="blog-section spad padding-top-section-style">
        <div class="container">
            <div class="row" id="foshomepho">
                <div class="col-2 ">
                    <div class="blog-sidebar">
                        <div class="blog-catagory">
                       		<div>
								<h4>�O���M��</h4>
							<ul>
                                <li><a href="fosmHome.jsp">�޲z�ڪ����Э�</a></li>
                                <li><a href="mreadfos.jsp">�޲z�ڪ��H�i��</a></li>
                            </ul>
							</div>
							<div class="sub-catagory">
								<a href="foscareHome.jsp">�H�i�M��</a>
							</div>
                        </div>                       
                    </div>
                </div>
                <div class="col-10">
                    <div class="row">
                        <div class="col-12 ">
                            <form action="fosm.do" class="checkout-form" method="post" enctype="multipart/form-data">
                                <div class="row" id="fosmcontain">
                                	<div class="col-12">
                                		<h4>�O���s��- ${LoginFosm.fosmNo}</h4>
                                		<input type="hidden" name="fosmNo" value="${LoginFosm.fosmNo}">
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
                                    <div class="col-6 ">
                                        <textarea  placeholder="�ۧڤ���" disabled name="fosmContain">${LoginFosm.fosmContain}</textarea>
                                    </div>
                                    <div class="col-6">
                                        <div class="row">
                                           <div class="col-12"  style="font-weight: bold;">
                                           		<%  String[] type ={""};
                                           			if(fosmVO.getFosmPetType() != null){
                                           			type = fosmVO.getFosmPetType().split(",");} 
                                           			
                                           			%>
                                                <span>�i�����d�������G</span> 
                                                <input type="checkbox" name="petType" class="formcheck" 
                                                value="��" disabled <%= (Arrays.asList(type).contains("��"))?"checked":"" %>>��
                                                <input type="checkbox" name="petType" class="formcheck" 
                                                value="��" disabled <%= (Arrays.asList(type).contains("��"))?"checked":"" %>>��
                                                <input type="checkbox" name="petType" class="formcheck" 
                                                value="��L" disabled <%= (Arrays.asList(type).contains("��L"))?"checked":"" %>>��L
                                           </div>
                                           <div class="col-12"  style="font-weight: bold;">
                                           		<%  String[] size ={""};
                                           			if(fosmVO.getFosmPetSize() != null){
                                           			size = fosmVO.getFosmPetSize().split(",");} %>
                                                <span>�i�����d���髬�G</span> 
                                                <input type="checkbox" name="petSize" class="formcheck" 
                                                value="�j" disabled <%= (Arrays.asList(size).contains("�j"))?"checked":"" %>>�j
                                                <input type="checkbox" name="petSize" class="formcheck" 
                                                value="��" disabled <%= (Arrays.asList(size).contains("��"))?"checked":"" %>>��
                                                <input type="checkbox" name="petSize" class="formcheck" 
                                                value="�p" disabled <%= (Arrays.asList(size).contains("�p"))?"checked":"" %>>�p
                                           </div>
                                           <div class="col-12" id="onlyone"  style="font-weight: bold;">
                                    
                                                <span>���ѥN���d���G</span> 
                                                <input type="checkbox" name="petnrun" class="formcheck" 
                                                value="�O" disabled <%= ("�O".equals(fosmVO.getFosmnrun()))?"checked":"" %>>�O
                                                <input type="checkbox" name="petnrun" class="formcheck" 
                                                value="�_" disabled <%= ("�_".equals(fosmVO.getFosmnrun()))?"checked":"" %>>�_                                    
                                           </div>
                                           <div class="col-12"  style="font-weight: bold;">
                                            	���ҷӤ��G <input type="file" name="fosmPho" class	="fosmPho" value="�W�ǹϤ�" disabled  multiple></div>
                                            
                                            <div class="col-12" id="fmpho">
                                            	<c:forEach var="phoNo" items="${list}" > 	
	                                                <img class="maxsix"  data-toggle="modal" data-target="#showBigPhoto" data-photo_no="${phoNo}"  draggable="true" ondragstart="event.dataTransfer.setData('photoNo','${phoNo}')" src="<%= request.getContextPath() %>/front-end/ShowPhotos?type=fosmPho&photo_no=${phoNo}"> 
                                            	</c:forEach>
                                            </div>
                                            <div class="col-12" id="changepage">
	                                            <img id="back" src="img/previous.png" class="img-controler"  draggable="false">
	                                            <img id="next" src="img/next.png" class="img-controler"  draggable="false">                                            	
                                            </div>	
                                          
                                           <div class="col-6">
                                           	   <span class="sta"><button  class="site-btn" id="shyogai">�ק�</button></span>
                                           </div>
                                           <div class="col-6">
                                           	   <span class="sta"><button type="submit" class="site-btn" id="send">�e�X</button></span>
                                               <input type="hidden" name="action" value="update"> 
                                           </div>
                                        </div>
                                    </div>    
                                </div>
                            </form>
                        </div>
                    </div>
<!--                     ���װ� -->
			<jsp:useBean id="fosSvc" class="com.foscare.model.FosterService"></jsp:useBean>
			
                    <div class="row checkout-form" id="Eva">
                    		
                    	<div class="col-12 row"  style="font-weight: bold;">
                    		<div class="col-1">����</div>
                    		<div class="col-11">�d����</div>
                    	</div>
                    		<hr class="col-12">
                    		<c:forEach var="foseva" items="${fosSvc.getAllByFosmNo(fosmNo)}" >
                    			<c:if test="${foseva.fosmEvas!='-1'}">	
		                             
		                             <div class="rate-box col-12 row">
			                             <div class="col-1 rate-style">${foseva.fosmEvas}</div>
			                             <div class="col-1"><img src="<%= request.getContextPath() %>/front-end/ShowPhotos?type=mem&photo_no=${foseva.memNo}"></div>
			                             <div class="col-10">${foseva.fosmEvacon}</div>
		                             </div>
		                             
		                             <div class="col-3"></div>
		                             <div class="col-9 row reply-box">
			                             <div class="col-11 toright">
				                             <c:if test="${foseva.fosmEvares!= null}">
				                             	${foseva.fosmEvares}
				                             </c:if>
				                             <c:if test="${foseva.fosmEvares == null}"> 
				                             	<input type="hidden" value="${foseva.fosNo}">
				                             	<input type="text" name="fosmres" class="respons">		                             	
				                             </c:if>
			                             </div>
			                             <div class="col-1"><img src="<%= request.getContextPath() %>/front-end/ShowPhotos?type=mem&photo_no=${LoginMem.memNo}"></div> 
		                             </div>  
	                             </c:if>           
                        	</c:forEach>
                        	<hr class="col-12">
                    </div>                                                       
                </div>
            </div>
        </div>
    </section>
        <div id="trash">
        	<img src="img/close.png" class="dropzone" id="closegar">
        	<img src="img/open.png" class="dropzone" id="opengar">
        </div>
    <!-- Blog Section End -->

	<%@ include file="/front-end/Footer/footer.jsp" %>
	<%@ include file="/front-end/foscare/showBigPhoto.jsp" %>
	<c:if test="${LoginMem!=null}">
		<%@ include file="/chatRoom/chatRoom.jsp"%>
	</c:if>
	
    <script type="text/javascript">
		    
    
    	//---------------------------
    
        $(document).ready(function(){
        	let success= $("#isSuccess").val();
			let successVal = $("#successVal").val();
			if(success == "Yes"){
				swal(successVal, '��n��!', 'success');
			}
        	$("#send").hide();
            $("#shyogai").click(function(e){
                e.preventDefault();
                $("#fosmcontain *").prop("disabled", false);
                $("#send").show();
            });
           
            $("#fmpho>img").hide();
            var len = $(".maxsix").length;
            var str=1;
            if($(".maxsix").length<7){
            	$("#fmpho>img").show();
            	$("#back").hide();
            	$("#next").hide();
            }else{
            	showPho(str);
            	$("#back").hide();
            }
            var deleteNo;
            //�R���Ӥ�
            $("#fmpho>img").mousedown(function(){
            	deleteNo = $(this).data("photo_no");
            	console.log("aaaa");
            });
            
            $("#closegar").hover(function(){
            	$("#closegar").hide();
            	$("#opengar").show();
            },function(){
            	$("#closegar").show();
            	$("#opengar").hide();
            });
            
          
            $("#next").click(function(){
            	$("#fmpho>img").hide();
            	str += 6;
            	showPho(str);
            	$("#back").show();
            	if(str+6 > len){
            		$("#next").hide();
            	}
            });
            
            $("#back").click(function(){
            	$("#fmpho>img").hide();
            	str -= 6;
            	showPho(str);
            	$("#next").show();
            	if(str<6){
            		$("#back").hide();
            	}
            });

            function showPho(str){
            	for(var i = str; i < str+6 ; i++){
            		$('.maxsix:nth-child('+i+')').show();	
            	}
            }

           	$("#onlyone>input").click(function(){
           		$(this).next().prop("checked",false);
           		$(this).prev().prop("checked",false);
   		
           	});
           	
           	$(".respons").keypress(function(){        
           		if(event.keyCode==13){
           			let fosmText = $(this).val();
           			if(fosmText.trim().length == 0){
           				alert("�п�J�^�Ф��e!")
           			}else{
           			data = {};
           			data.action = 'fosmres';
           			var inputtext = $(this).val();
           			data.fosmText = inputtext;
           			data.fosNo = $(this).prev().val();
           			$(this).hide();
           			$(this).parent().html(inputtext);
           			
           			$.ajax({
           				url:"fos.do",
           				type:"POST",
           				data:data,
           				success:function(data){
           					swal('�^�Ц��\�I', '�R�E�d�R�E�ԩԪi�i���R�����|�h!', 'success');
           				}
           			});
           				
           			}
           		}        		
           	});
          //---------------------------
        	var dragged;

    		/* events fired on the draggable target */
    		document.addEventListener("drag", function(event) {
    		
    		}, false);
    		
    		document.addEventListener("dragstart", function(event) {
    		  // store a ref. on the dragged elem
    		  dragged = event.target;
    		  // make it half transparent
    		  event.target.style.opacity = .5;
    		}, false);
    		
    		document.addEventListener("dragend", function(event) {
    		  // reset the transparency
    		  event.target.style.opacity = "";
    		  					
    		}, false);
    		
    		/* events fired on the drop targets */
    		document.addEventListener("dragover", function(event) {
    		  // prevent default to allow drop
    		  									
    		  event.preventDefault();
    		}, false);
    		
    		document.addEventListener("dragenter", function(event) {
    		  // highlight potential drop target when the draggable element enters it
    		  if (event.target.className == "dropzone") {
    		    event.target.style.background = "purple";
    		  }
    		
    		}, false);
    		
    		document.addEventListener("dragleave", function(event) {
    		  // reset background of potential drop target when the draggable element leaves it
    		  if (event.target.className == "dropzone") {
    		    event.target.style.background = "";
    		  }
    		
    		}, false);
    		
    		document.addEventListener("drop", function(event) {
    		  // prevent default action (open as link for some elements)
    		  event.preventDefault();
    		  var phoNo = event.dataTransfer.getData('photoNo');								
    		  // move dragged elem to the selected drop target
    		  if (event.target.className == "dropzone") {
    		    event.target.style.background = "";
    		    dragged.parentNode.removeChild( dragged );
    		    
    		    
    		    
    		    $.ajax({
       				url:"fosm.do",
       				type:"POST",
       				data:{
       					"action":"delete",
       					"photoNo":phoNo
       				},
       				success:function(data){
       					swal('�R�����\�I', '�A���٤����a~�p��', 'success');
       					showPho(str);
       				}
       			});
    		    
    		  }
    		}, false);
           	
           	
        });
    </script>
</body>
<% session.removeAttribute("isSuccess"); 
   session.removeAttribute("successVal");
%>
</html>