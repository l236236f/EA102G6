<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ page import="com.foscare.model.*"%>
<%@ page import="com.fosmon.model.*"%>
<%@ page import="com.tools.*"%>
<%@ page import="java.util.*"%>
<%@ page import="org.json.JSONObject" %>
<%@ page import="org.json.JSONArray" %>

<!DOCTYPE html>
<html lang="zxx">

<head>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAftybVRd3QKMfCi7ke-w5YIEfaoNuBAD0&callback=initMap" async defer></script>
<meta charset="UTF-8">
<meta name="description" content="Fashi Template">
<meta name="keywords" content="Fashi, unica, creative, html">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>GGYY</title>
<style type="text/css">
        .display {
            text-align: center;
        }
        #map {
            width: 800px;
            height: 500px;
            margin: 10px auto;
        }
        .info_title {
            font-size: 20px;
            font-weight: 800;
        }
        .info_img {
            height: 30px;
            width: auto;
        }
        .gm-style-iw.gm-style-iw-c{
        	max-width:300px;
        }
        .fosMon{
        	width:300px;
        	text-align:left;
        }
        .fosMon *,
        .fosMon {
        	font-weight:700;
        }
        .fosMon span{
        	padding:0 5px;
        }
        .detailf{
        	overflow: hidden;
		    text-overflow: ellipsis;
		    white-space: nowrap;
        }
    </style>
</head>
<body>
	<%@ include file="/front-end/Header/header.jsp" %>
	<%
		FosmService fosmSvc = new FosmService();
		List<FosmVO> list = fosmSvc.getAll();
		session.setAttribute("AllfosMon", list);
		JSONArray jsonArray = new JSONArray();		
		jsonArray.put(list);
		FosmVO fosmVO1 = null;
		if(memVO != null){
			GetFosmLatLng gfll = new GetFosmLatLng();
			fosmVO1 = gfll.getLat(memVO.getMemNo(), memVO.getMemAddr());
			
		}
	%>
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
                <div class="col-lg-2 order-1 order-lg-2">
                   <div class="fosmSerch">
                     <div class="col-lg-12" >
                        <div class="fosmSerchtype" id="petType">
                            <div class="label-title">接受寵物類型</div>                        
                            <select class="sorting" name="petType">
                            	<option value="請選擇">請選擇</option> 
                                <option value="貓">貓</option>
                                <option value="狗">狗</option>
                                <option value="其他">其他</option>
                            </select>
                        </div>
                    </div>
                    <div class="col-lg-12">
                        <div class="fosmSerchtype" id="petSize">
                            <div class="label-title">接受寵物體型大小</div>
                            <select class="sorting" name="petSize">
                            	<option value="請選擇">請選擇</option>  
                                <option value="大">大</option>
                                <option value="中">中</option>
                                <option value="小">小</option>
                            </select>
                        </div>
                    </div>
                    <div class="col-lg-12">
                        <div class="fosmSerchtype" id="petnrun">
                            <div class="label-title">可提供代遛</div>                       
                            <select class="sorting" name=""> 
                            	<option value="請選擇">請選擇</option>
                                <option value="是">是</option>
                                <option value="否">否</option>
                            </select>
                        </div>
                    </div> 

                </div>
            </div>
            <div class="col-lg-8 order-1 order-lg-2">
                <div class="row">
                    <div class="col-lg-12 col-sm-6">
                        <div class="display">
					        <div id="map"></div>
					    </div>
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

		let allfosm = '<%= jsonArray.toString() %>';
		allfosm = JSON.parse(allfosm)[0];
		console.log(allfosm);
		var map;
		var choose = allfosm;
		
        function init() {        	
            
            for (var i = 0; i < choose.length; i++) {
                generateMarkers(choose[i]); // // 使用 function 將 var 宣告出來的變數鎖在 local scope
            }
        }
        
        var infowindow

        function generateMarkers(datum) {
        	var star = datum.fosStar;
        	var count = datum.fosmEvacount;
        	var avgstar = star/count;
        	avgstar = avgstar.toFixed(1);
        	
            // 產生 marker 物件
            var marker = new google.maps.Marker({
                position: { lat: datum.fosmLat, lng: datum.fosmLng },
                map: map,
                icon:'<%= request.getContextPath() %>/front-end/foscare/img/cats1.png',
                animation: google.maps.Animation.DROP, // DROP掉下來、BOUNCE一直彈跳
                draggable: false, // true、false可否拖拉
                fosmNo: datum.fosmNo,
                petType:datum.fosmPetType,
                
            });
            // 產生 infowindow 物件
            
            // 預設打開 info window
            // infowindow.open(map, marker);
            // 當 marker 被點擊時觸發
            marker.addListener('click', function() {
            	if(infowindow) infowindow.close();
            	infowindow = new google.maps.InfoWindow({
                    content: '<div class="fosMon"><div>保母編號<span>:</span>'+datum.fosmNo+'</div><div>平均星等<span>:</span>'+avgstar+'</div><div>可接受類型<span>:</span>'+datum.fosmPetType+
                    		 '</div><div>可接受大小<span>:</span>'+datum.fosmPetSize+'</div><div>可接受代遛<span>:</span>'+datum.fosmnrun+'</div><div>簡短介紹<span>:</span></div><div class="detailf">'
                    		 +datum.fosmContain+'</div><div><a href="readOnefosm.jsp?oneMom='+datum.fosmNo+'">看更詳細..</a></div>'
                });
                infowindow.open(map, marker);
            });
        }
        
        var data1 = {
    			type: '請選擇',
    			size: '請選擇',
    			nrun: '請選擇'
    	};
        
        function changeSerch(){
        	
        	$.ajax({
        		url:"JSONfosm",
        		type:"POST",
        		data: data1,
				success:function(data){
					choose = JSON.parse(data)[0];
					console.log(choose);
					init();
				}	
        	})
        }
        $(document).ready(function(){        	
        	$("#petType li").click(function(){
        		data1.type = $(this).data("value");
        		initMap();
            	changeSerch();
            });
        	$("#petSize li").click(function(){
        		data1.size = $(this).data("value");
        		initMap();
            	changeSerch();
            });
        	$("#petnrun li").click(function(){
        		data1.nrun = $(this).data("value");
        		initMap();
            	changeSerch();
            });
            
        });
        

        function initMap() {
            map = new google.maps.Map(document.getElementById('map'), {
//                 center: { lat: 24.9527954, lng: 121.2258173 },
				center: { lat: <%= (fosmVO1==null)?24.9527954:fosmVO1.getFosmLat() %>, lng: <%= (fosmVO1==null)?121.2258173:fosmVO1.getFosmLng() %>},
                zoom: 15,
            });

            var cityCircle = new google.maps.Circle({
                strokeColor: '#f1c40f', // 線條顏色
                strokeOpacity: 1, // 線條透明度
                strokeWeight: 1, // 線條粗度
                fillColor: '#f1c40f', // 圓形裡填滿的顏色
                fillOpacity: 0.35, // 圓形裡，填滿顏色的透明度
                map: map,
                center: { lat: 24.978391, lng: 121.268641 }, // 中心點
                radius: 500 // 半徑
            });
        }   
    </script>
</body>
</html>