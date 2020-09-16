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
                <div class="col-lg-2 order-1 order-lg-2">
                   <div class="fosmSerch">
                     <div class="col-lg-12" >
                        <div class="fosmSerchtype" id="petType">
                            <div class="label-title">�����d������</div>                        
                            <select class="sorting" name="petType">
                            	<option value="�п��">�п��</option> 
                                <option value="��">��</option>
                                <option value="��">��</option>
                                <option value="��L">��L</option>
                            </select>
                        </div>
                    </div>
                    <div class="col-lg-12">
                        <div class="fosmSerchtype" id="petSize">
                            <div class="label-title">�����d���髬�j�p</div>
                            <select class="sorting" name="petSize">
                            	<option value="�п��">�п��</option>  
                                <option value="�j">�j</option>
                                <option value="��">��</option>
                                <option value="�p">�p</option>
                            </select>
                        </div>
                    </div>
                    <div class="col-lg-12">
                        <div class="fosmSerchtype" id="petnrun">
                            <div class="label-title">�i���ѥN��</div>                       
                            <select class="sorting" name=""> 
                            	<option value="�п��">�п��</option>
                                <option value="�O">�O</option>
                                <option value="�_">�_</option>
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
                generateMarkers(choose[i]); // // �ϥ� function �N var �ŧi�X�Ӫ��ܼ���b local scope
            }
        }
        
        var infowindow

        function generateMarkers(datum) {
        	var star = datum.fosStar;
        	var count = datum.fosmEvacount;
        	var avgstar = star/count;
        	avgstar = avgstar.toFixed(1);
        	
            // ���� marker ����
            var marker = new google.maps.Marker({
                position: { lat: datum.fosmLat, lng: datum.fosmLng },
                map: map,
                icon:'<%= request.getContextPath() %>/front-end/foscare/img/cats1.png',
                animation: google.maps.Animation.DROP, // DROP���U�ӡBBOUNCE�@���u��
                draggable: false, // true�Bfalse�i�_���
                fosmNo: datum.fosmNo,
                petType:datum.fosmPetType,
                
            });
            // ���� infowindow ����
            
            // �w�]���} info window
            // infowindow.open(map, marker);
            // �� marker �Q�I����Ĳ�o
            marker.addListener('click', function() {
            	if(infowindow) infowindow.close();
            	infowindow = new google.maps.InfoWindow({
                    content: '<div class="fosMon"><div>�O���s��<span>:</span>'+datum.fosmNo+'</div><div>�����P��<span>:</span>'+avgstar+'</div><div>�i��������<span>:</span>'+datum.fosmPetType+
                    		 '</div><div>�i�����j�p<span>:</span>'+datum.fosmPetSize+'</div><div>�i�����N��<span>:</span>'+datum.fosmnrun+'</div><div>²�u����<span>:</span></div><div class="detailf">'
                    		 +datum.fosmContain+'</div><div><a href="readOnefosm.jsp?oneMom='+datum.fosmNo+'">�ݧ�Բ�..</a></div>'
                });
                infowindow.open(map, marker);
            });
        }
        
        var data1 = {
    			type: '�п��',
    			size: '�п��',
    			nrun: '�п��'
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
                strokeColor: '#f1c40f', // �u���C��
                strokeOpacity: 1, // �u���z����
                strokeWeight: 1, // �u���ʫ�
                fillColor: '#f1c40f', // ��θ̶񺡪��C��
                fillOpacity: 0.35, // ��θ̡A���C�⪺�z����
                map: map,
                center: { lat: 24.978391, lng: 121.268641 }, // �����I
                radius: 500 // �b�|
            });
        }   
    </script>
</body>
</html>