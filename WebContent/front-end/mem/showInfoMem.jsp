<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="java.util.stream.Collectors"%>
<%@ page import="com.gat.model.*"%>
<%@ page import="com.gat_eva.model.*"%>

<%@ include file="/front-end/Header/header.jsp"%>

<%
	MemService memSvc = new MemService();
	MemVO showMemVO = memSvc.getOneMem(request.getParameter("showMemNo"));
	pageContext.setAttribute("showMemVo", showMemVO);
	
	GatService gatSvc = new GatService();
	List<GatVO> listGat = gatSvc.getAllForUpdateList();

	GatEvaService gatEvaSvc = new GatEvaService();
	List<GatEvaVO> listEva = gatEvaSvc.getAll();

	List<GatVO> newListGat = new ArrayList<GatVO>();
	newListGat = listGat.stream()
			.filter(gat -> gat.getMemNo().equals(memVO.getMemNo()))
			.collect(Collectors.toList()); 

	double GatEvacount = 0;	
	int GatEvas = 0;
	for(GatVO gatVo:newListGat) {
		GatEvacount += listEva.stream()
					.filter(eva -> eva.getGatNo().equals(gatVo.getGatNo()))
					.mapToDouble(GatEvaVO::getGatEva)
					.sum();
		GatEvas += listEva.stream()
				.filter(eva -> eva.getGatNo().equals(gatVo.getGatNo()))
				.count();
	}

%>

<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
<style type="text/css">

#memPhoto{
/*   	height:225px;  */
 	border:2px solid #AAA;
 	paddind:2px;
}

.ratings {
	position: relative;
	vertical-align: middle;
	display: inline-block;
	color: #ddd; /*背景星星顏色*/
	overflow: hidden;
	font-size: 20px; /*調整字體大小可放大縮小星星*/
	text-shadow: 0px 1px 0 #999;
}

.full_star {
	width: 90%; /*調整寬度可變更星等*/
	position: absolute;
	left: 0;
	top: 0;
	white-space: nowrap;
	overflow: hidden;
	color: #FFF333; /*前景星星顏色*/
}

</style>
</head>
<body>
	<section class="blog-section spad">
		<div class="container">
			<div class="row">
				<%@ include file="/front-end/mem/LHSBar.jsp"%>
				<div class="col-8">
<!--------------------------------------------------------------------------------->
					
					<div class="card shadow p-3 mb-5 bg-white rounded"
						style="margin-left: 1%; width: 98%; align: center">

						<div align="center">
						<label for='memPhoto'>
							<img id='memPhoto' class="card-img-top" src="<%=request.getContextPath()%>/front-end/ShowPhotos?type=mem&photo_no=<%=showMemVO.getMemNo()%>" style="max-width: 100%">
						</label>
						</div>
						<div class="card-body">
   				 			
   <div class="form-group row">
    <div class="col-sm-2 col-form-label">會員名稱</div>
    <div class="col-sm-9">
      <div class="form-control"><%=showMemVO.getMemName()%></div>
    </div>
  </div>
  
  <div class="form-group row">
    <div class="col-sm-2 col-form-label">會員帳號</div>
    <div class="col-sm-9">
      <div class="form-control"><%=showMemVO.getMemAcc()%></div>
    </div>
  </div>
  
  <div class="form-group row">
    <div class="col-sm-2 col-form-label">生日</div>
    <div class="col-sm-9">
      <div class="form-control"><%=showMemVO.getMemBirth()%></div>
    </div>
  </div>
  
  <div class="form-group row">
    <div class="col-sm-2 col-form-label">電話</div>
    <div class="col-sm-9">
      <div class="form-control"><%=showMemVO.getMemTel()%></div>
    </div>
  </div>	
  
  <div class="form-group row">
    <div class="col-sm-2 col-form-label">性別</div>
    <div class="col-sm-9">
      <div class="form-control"><%=showMemVO.getMemGender()%></div>
    </div>
  </div>
  
  <div class="form-group row">
    <div class="col-sm-2 col-form-label">地址</div>
    <div class="col-sm-9">
      <div class="form-control"><%=showMemVO.getMemAddr()%></div>
    </div>
  </div>
  
  <div class="form-group row">
    <div class="col-sm-2 col-form-label">電子郵件</div>
    <div class="col-sm-9">
      <div class="form-control"><%=showMemVO.getMemEmail()%></div>
    </div>
  </div>
  
  <div class="form-group row">
    <div class="col-sm-2 col-form-label">保母資格</div>
    <div class="col-sm-9">
    <%if("M0".equals(memVO.getMom())){ %>
      <div class="form-control" id="applymom">未申請<span id="apply" style='position:absolute;right:5%'>馬上申請</span></div>
    <%}else if("M1".equals(memVO.getMom())){ %>
      <div class="form-control" id="applymom">審核中</div>
    <%}else if("M2".equals(memVO.getMom())){ %>
      <div class="form-control" id="applymom">符合資格</div>
    <%}else { %>
      <div class="form-control" id="applymom">未符合資格</div>
    <%} %>
    </div>
  </div>
  
  <div class="form-group row">
    <div class="col-sm-2 col-form-label">簡介</div>
    <div class="col-sm-9">
      <div class="form-control" style='height:125px'><%=showMemVO.getMemIntro()%></div>
    </div>
  </div>
  
   <div class="form-group row">
    <div class="col-sm-3 col-form-label">揪團評價</div>
    <div class="col-sm-9">
      <div class="ratings">
			<div class="empty_star">★★★★★</div>
			<div class="full_star" id='gatEva'>★★★★★</div>
		</div>
    </div>
  </div>			
  						</div>
					</div>			
<!--------------------------------------------------------------------------------->
				</div>
			</div>
		</div>
	</section>
	<%@ include file="/front-end/Footer/footer.jsp"%>
	<c:if test="${LoginMem!=null}">
		<%@ include file="/chatRoom/chatRoom.jsp"%>
	</c:if>
	<script> 
	//計算評價
		var gatEva =<%=GatEvacount%>/<%=GatEvas%>*20;
			$("#gatEva").css("width", gatEva + "%");
	</script>
</body>
</html>