<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ page import="java.util.*" %>
<%@ page import="com.foscare.model.*" %>
<%@ page import="com.fosmon.model.*" %>
<%@ page import="com.mem.model.*" %>
<%@ page import="com.fosmphoto.model.*" %>
<!DOCTYPE html>
<html lang="zxx">
	<%@ include file="/front-end/Header/header.jsp" %>	
	<% 
		FosmService fmSvc = new FosmService();
		pageContext.setAttribute("fmSvc",fmSvc);
		String fosmNo =  request.getParameter("oneMom");
		FosmVO fosmVO =  fmSvc.getOneByFosmNo(fosmNo);
		pageContext.setAttribute("fosmNo",fosmNo);
		pageContext.setAttribute("memNo",fosmVO.getMemNo());
		pageContext.setAttribute("fosmVO",fosmVO);
		FosmPhoService fmpSvc = new FosmPhoService();
		List<String> list = fmpSvc.getAllByFosm(fosmNo);
		pageContext.setAttribute("list",list);
	%>

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
	</style>   
</head>
<body>

    <section class="blog-section spad">
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
                <div class="col-10">
                    <div class="row">
                        <div class="col-12 ">
                            <div class="checkout-form">
                                <div class="row" id="fosmcontain">
                                	<div class="col-12">
                                		<h3>保母編號- ${fosmNo}</h3>
                                	</div>
                                    <div class="col-6 ">
                                        <textarea disabled name="fosmContain">${fosmVO.fosmContain}</textarea>
                                    </div>
                                    <div class="col-6">
                                        <div class="row">
                                           <div class="col-12">
                                           		<%  String[] type ={""};
                                           			if(fosmVO.getFosmPetType() != null){
                                           			type = fosmVO.getFosmPetType().split(",");} %>
                                                <span>可接受寵物類型:</span> 
                                                <input type="checkbox" name="petType" class="formcheck" 
                                                value="貓" disabled <%= (Arrays.asList(type).contains("貓"))?"checked":"" %>>貓
                                                <input type="checkbox" name="petType" class="formcheck" 
                                                value="狗" disabled <%= (Arrays.asList(type).contains("狗"))?"checked":"" %>>狗
                                                <input type="checkbox" name="petType" class="formcheck" 
                                                value="其他" disabled <%= (Arrays.asList(type).contains("其他"))?"checked":"" %>>其他
                                           </div>
                                           <div class="col-12">
                                           		<%  String[] size ={""};
                                           			if(fosmVO.getFosmPetSize() != null){
                                           			size = fosmVO.getFosmPetSize().split(",");} %>
                                                <span>可接受寵物體型:</span> 
                                                <input type="checkbox" name="petSize" class="formcheck" 
                                                value="大" disabled <%= (Arrays.asList(size).contains("大"))?"checked":"" %>>大
                                                <input type="checkbox" name="petSize" class="formcheck" 
                                                value="中" disabled <%= (Arrays.asList(size).contains("中"))?"checked":"" %>>中
                                                <input type="checkbox" name="petSize" class="formcheck" 
                                                value="小" disabled <%= (Arrays.asList(size).contains("小"))?"checked":"" %>>小
                                           </div>
                                           <div class="col-12 " id="onlyone">
                                    
                                                <span>提供代遛寵物:</span> 
                                                <input type="checkbox" name="petnrun" class="formcheck" 
                                                value="是" disabled <%= ("是".equals(fosmVO.getFosmnrun()))?"checked":"" %>>是
                                                <input type="checkbox" name="petnrun" class="formcheck" 
                                                value="否" disabled <%= ("否".equals(fosmVO.getFosmnrun()))?"checked":"" %>>否                                    
                                           </div>
                                           <div class="col-12" >                                           
                                            <div class="col-12" id="fmpho">
                                            	<c:forEach var="phoNo" items="${list}" > 	
	                                                <img class="maxsix"  data-toggle="modal" data-target="#showBigPhoto" data-photo_no="${phoNo}" src="<%= request.getContextPath() %>/front-end/ShowPhotos?type=fosmPho&photo_no=${phoNo}"> 
                                            	</c:forEach>
                                            </div>
                                            <div class="col-12" id="changepage">
	                                            <img id="back" src="img/previous.png" class="img-controler">
	                                            <img id="next" src="img/next.png" class="img-controler">                                            	
                                            </div>		
                                          	
                                        </div>
                                        </div>
                                    </div>    
                                </div>
                            </div>
                        </div>
                    </div>
<!--                     評論區 -->
			<jsp:useBean id="fosSvc" class="com.foscare.model.FosterService"></jsp:useBean>
                     <div class="row checkout-form" id="Eva">
                    		
                    	<div class="col-12 row"  style="font-weight: bold;">
                    		<div class="col-1">評價</div>
                    		<div class="col-11">留言區</div>
                    	</div>
                    		
                    		<hr class="col-12">
                    		<c:forEach var="foseva" items="${fosSvc.getAllByFosmNo(fosmNo)}" >
                    			<c:if test="${foseva.fosmEvas!='-1'}">	
		                             <div class="rate-box col-12 row">
			                             <div class="col-1 rate-style">${foseva.fosmEvas}</div>
			                             	<div class="col-1">
			                             	<a href="<%= request.getContextPath() %>/front-end/mem/showInfoMem.jsp?showMemNo=${foseva.memNo}">
			                             	<img src="<%= request.getContextPath() %>/front-end/ShowPhotos?type=mem&photo_no=${foseva.memNo}">
			                             	</a></div>
			                             <div class="col-10">${foseva.fosmEvacon}</div>
		                             </div>
		                             
		                             <div class="col-3"></div>
		                             <div class="col-9 row reply-box">
			                             <div class="col-11 toright">                    
				                             	${foseva.fosmEvares}    
			                             </div>
			                             <div class="col-1">
			                             	<a href="<%= request.getContextPath() %>/front-end/mem/showInfoMem.jsp?showMemNo=${memNo}">
			                             	<img src="<%= request.getContextPath() %>/front-end/ShowPhotos?type=mem&photo_no=${memNo}"></a>
			                             </div> 
		                             </div>
	                             </c:if>           
                        	</c:forEach>
                        <hr class="col-12">
                    </div>                                                      
                </div>
            </div>
        </div>
    </section>
    <!-- Blog Section End -->

	<%@ include file="/front-end/Footer/footer.jsp" %>
	<%@ include file="/front-end/foscare/showBigPhoto.jsp" %>
	<c:if test="${LoginMem!=null}">
		<%@ include file="/chatRoom/chatRoom.jsp"%>
	</c:if>

    <script type="text/javascript">
        $(document).ready(function(){     
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
           	           	                  	
        });
    </script>
</body>

</html>