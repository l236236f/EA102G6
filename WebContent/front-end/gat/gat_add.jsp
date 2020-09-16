<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.gat.model.*"%>

<%
	GatVO gatVO = (GatVO) request.getAttribute("gatVO");
	pageContext.setAttribute("gatVO", gatVO);
%>

<!DOCTYPE html>
<html>
<%@ include file="/front-end/Header/header.jsp"%>
<head>
<title>建立揪團</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/gat/style/style_gat_add.css" />
</head>
<body>
	<!-- bradcam_area_start -->
	<div id="benner_bg" style="background-image: url(<%=request.getContextPath()%>/front-end/gat/images/banner_dog.png);">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div class="bradcam_text text-center">
						<div class="gat_div">
							<div class="head_icon">
								<i class="fa fa-plus-circle" aria-hidden="true"></i>
							</div>
							<h3 class="gat_header">建立揪團</h3>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- bradcam_area_end -->

	<section class="add_section-padding">
		<form METHOD="post" ACTION="gat.do" name="form1"
			enctype="multipart/form-data">
			<!-- 			----------------------------------------------------------------------------- -->
			<div class="container">
				<div class="add_row">

					<div class="col-lg-6 col-md-6">

						<div class="input-group-icon mt-10">
							<div class="icon">
								<i class="fa fa-paw" aria-hidden="true"></i>
							</div>
							<input type="text" name="gatName" placeholder="請輸入揪團名稱"
								onfocus="this.placeholder = ''"
								onblur="this.placeholder = '請輸入揪團名稱'" required
								class="single-input" id="gatName"
								value="<%=(gatVO == null) ? "" : gatVO.getGatName()%>">
							<div class="errorMsgs">
							${errorMsgs.gatName}
							</div>
						</div>
						
					<div class="gattime_wrapper" style="border-bottom: 2px solid #f1f1f1; ">
						<div class="input-group-icon mt-10" style="display: inline-block; height: 88px; width: 53%;">
							<div class="icon">
								<i class="fa fa-clock-o" aria-hidden="true"></i>
							</div>
							<input type="text" name="gatTime" id="f_date1"
								placeholder="請輸入活動時間" class="single-input" style="width: 95%; margin-right: 46px;">
								<div class="errorMsgs">
							${errorMsgs.gatTime}
							</div>
						</div>
						
						<div class="input-group-icon mt-10" style="display: inline-block;">
							<div class="icon">
								<i class="fa fa-tag" aria-hidden="true"></i>
							</div>
							<select id="gatType" name="gatType" class="single-input type_select" style="height: 60px;width: 165%;">
								<option value="貓咪喵喵喵">貓咪喵喵喵</option>
								<option value="狗狗汪汪汪">狗狗汪汪汪</option>
								<option value="蛇蛇嘶嘶嘶">蛇蛇嘶嘶嘶</option>
								<option value="魚魚啵啵啵">魚魚啵啵啵</option>
								<option value="鳥鳥啾啾啾">鳥鳥啾啾啾</option>
								<option value="兔兔跳跳跳">兔兔跳跳跳</option>
								<option value="其他她牠它">其他她牠它</option>
							</select>
						</div>
					</div>
						<div class="input-group-icon mt-10" style="display: inline-block;">
							<div class="icon">
								<i class="fa fa-calendar-check-o" aria-hidden="true"></i>
							</div>
							<input type="text" name="gatStarttime" id="f_date2"
								placeholder="請輸入報名時間" class="single-input" style="width: 95%; margin-right: 46px;">
								<div class="errorMsgs" style="right: 13px;">
							${errorMsgs.gatStarttime}
							</div>
						</div>

						<div class="input-group-icon mt-10" style="display: inline-block;">
							<div class="icon">
								<i class="fa fa-calendar-times-o" aria-hidden="true"></i>
							</div>
							<input type="text" name="gatEndtime" id="f_date3"
								placeholder="請輸入截止時間" class="single-input">
								<div class="errorMsgs">
							${errorMsgs.gatEndtime}
							</div>
						</div>

						<div class="input-group-icon mt-10" style="display: inline-block; width: 51%; margin-right: 16px;">
							<div class="icon">
								<i class="fa fa-plus-square" aria-hidden="true"></i>
							</div>
							<input type="number" name="gatMax" min="1" max="1000"
								placeholder="最高人數上限" onfocus="this.placeholder = ''"
								onblur="this.placeholder = '最高人數上限'" required
								class="single-input" id="gatMax"
								value="<%=(gatVO == null) ? "" : gatVO.getGatMax()%>">
							<div class="errorMsgs">
							${errorMsgs.gatMax}
							</div>
						</div>

						<div class="input-group-icon mt-10" style=" width: 45%; display: inline-block;">
							<div class="icon">
								<i class="fa fa-minus-square" aria-hidden="true"></i>
							</div>
							<input type="number" name="gatMin" min="1" max="1000"
								placeholder="最低人數限制" onfocus="this.placeholder = ''"
								onblur="this.placeholder = '最低人數限制'" required
								class="single-input" id="gatMin" value="<%=(gatVO == null) ? "" : gatVO.getGatMin()%>">
							<div class="errorMsgs">
							${errorMsgs.gatMin}
							</div>
						</div>

						<div class="mt-10">
							<textarea name="gatIntro" class="single-textarea"
								placeholder="請輸入揪團簡介" onfocus="this.placeholder = ''" id="gatIntro"
								onblur="this.placeholder = '請輸入揪團簡介'" required><%=(gatVO == null) ? "" : gatVO.getGatIntro()%></textarea>
							<div class="errorMsgs" style="right: 17px;">
							${errorMsgs.gatIntro}
							</div>
						</div>

						<div class="gat_add_btn">
							<input type="hidden" name="memNo" value="<%=memVO.getMemNo()%>">
							<input type="hidden" name="action" value="insert"> <input
								type="submit" value="送出新增" class="submit_btn">
						</div>
						<div class="magicbtn-wrapper">
							<div id="magicbtn"><i class="fa fa-magic"></i></div>
						</div>

					</div>

					<div class="col-lg-6 col-md-6">
						
						<div class="input-group-icon mt-10">
							<div class="icon">
								<i class="fa fa-map-marker" aria-hidden="true"></i>
							</div>
							<input type="text" id="gatLoc" name="gatLoc"
								placeholder="請輸入揪團地點" onfocus="this.placeholder = ''"
								onblur="this.placeholder = '請輸入揪團地點'" required
								class="single-input" id="gatLoc"
								value="<%=(gatVO == null) ? "" : gatVO.getGatLoc()%>" style="margin-bottom: 30px;">
							<input type="hidden" id="gatLat" name="gatLat" value="<%=(gatVO == null) ? "" : gatVO.getGatLat()%>">
							<input type="hidden" id="gatLng" name="gatLng" value="<%=(gatVO == null) ? "" : gatVO.getGatLng()%>">
							<div class="errorMsgs" style=" top: 60px;">
							${errorMsgs.gatLoc}
							</div>
						</div>

						<div>

							<div class="input-group-icon gatMap">
								<div id="panel">
									<input type="text" id="keyword" class="panel-input">
								</div>
								<div id="map"></div>
							</div>


							<div class="input-group-icon gatPhoto">
								<label class="btn btn-info"> <input id="gatPhoto"
									style="display: none;" type="file" name="gatPhoto"
									class="gatPhoto_btn"> <i class="fa fa-photo"></i> 上傳圖片
								</label>
								<div class="preview_row">
									<div id="preview"></div>
								</div>
							</div>
						</div>
						<!-- 					------------------------------------------------------------------------------------- -->
					</div>
				</div>
				<!-- 			--------------------------container end------------------------------------------- -->
		</form>
	</section>

	<%@ include file="/front-end/Footer/footer.jsp"%>
</body>
<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<%
	java.sql.Date gatTime = null;
	java.sql.Date gatStarttime = null;
	java.sql.Date gatEndtime = null;
	try {
		gatTime = gatVO.getGatTime();
		gatStarttime = gatVO.getGatStarttime();
		gatEndtime = gatVO.getGatEndtime();
	} catch (Exception e) {
		gatTime = new java.sql.Date(System.currentTimeMillis());
		gatStarttime = new java.sql.Date(System.currentTimeMillis());
		gatEndtime = new java.sql.Date(System.currentTimeMillis());
	}
%>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>

<script>
        $.datetimepicker.setLocale('zh');
        
        $('#f_date1').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,        //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',   //format:'Y-m-d H:i:s',
		   value: '<%=gatTime%>'   // value:   new Date()
			  
		//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
		//startDate:	            '2017/07/10',  // 起始日
		//minDate:               '-new Date()', // 去除今日(不含)之前
		//maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });

        $('#f_date2').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,        //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',   //format:'Y-m-d H:i:s',
		   value: '<%=gatStarttime%>',
		   maxDate:'<%=gatStarttime%>'
        });

	     $('#f_date3').datetimepicker({
		    theme: '',              //theme: 'dark',
		    timepicker:false,        //timepicker:true,
		    step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
		    format:'Y-m-d',   //format:'Y-m-d H:i:s',
			value: '<%=gatEndtime%>',
			maxDate:'<%=gatEndtime%>',
			minDate: '<%=gatEndtime%>'
	});
	
</script>
<script>
$.datetimepicker.setLocale('zh'); // kr ko ja en
$(function(){
	  $('#f_date2').datetimepicker({
	   format:'Y-m-d',
	   onShow:function(){
	    this.setOptions({
	     maxDate:$('#f_date1').val()?$('#f_date1').val():false
	    })
	   },
	   timepicker:false,
	   value: '<%=gatStarttime%>'
	     });
	  });



$(function(){
  $('#f_date3').datetimepicker({
   format:'Y-m-d',
   onShow:function(){
    	this.setOptions({
    		maxDate:$('#f_date1').val()?$('#f_date1').val():false
   		 });
    	this.setOptions({
    	 minDate:$('#f_date2').val()?$('#f_date2').val():false
    	})
    
   },
   timepicker:false,
   value: '<%=gatEndtime%>'
     });
  });
 </script>


<script type="text/javascript">
	function initphoto() {
		var gatPhoto = document.getElementById("gatPhoto");
		// 				var filename = document.getElementById("filename");
		var preview = document.getElementById("preview");

		gatPhoto.addEventListener('change', function(e) {
			preview.innerHTML='';
			var files = e.srcElement.files;
			if (files) {
				for (var i = 0; i < files.length; i++) {
					var file = files[i];
					if (file.type.indexOf('image') > -1) {
						// 								filename.value = file.name;
						var reader = new FileReader();
						reader.addEventListener('load', function(e) {
							var result = e.srcElement.result;
							var img = document.createElement('img');
							img.setAttribute("class", "foto");
							img.src = result;
							img.style.width = '100%';
							preview.append(img);
						});
						reader.readAsDataURL(file);
					} else {
						alert('請上傳圖片');
					}
				}
			}
		});
	}
	initphoto(window.onload);
</script>
<script>
        var map;

        function initmap() {
            var keyword = document.getElementById('keyword');
            var options = {
                componentRestrictions: { country: 'tw' } // 限制在台灣範圍
            };
            var autocomplete = new google.maps.places.Autocomplete(keyword, options);

            // 地址的輸入框，值有變動時執行
            autocomplete.addListener('place_changed', function() {
                var place = autocomplete.getPlace(); // 地點資料存進place
                console.log(place);
                // debugger;
                // 確認回來的資料有經緯度
                if (place.geometry) {
                    // 改變map的中心點
                    var searchCenter = place.geometry.location;
                    // panTo是平滑移動、setCenter是直接改變地圖中心
                    map.panTo(searchCenter);
                    // 在搜尋結果的地點上放置標記
                    var marker = new google.maps.Marker({
                        position: searchCenter,
                        map: map
                    });
                    // info window
                    var infowindow = new google.maps.InfoWindow({
                        content: 
                        	`<div class="info_title">` + place.name + 
                        	`</div><div><span class="info_head">地址: </span>` + place.formatted_address + 
                        	`</div><div><span class="info_head">經緯度: </span>(` + place.geometry.location.lat() + `, ` + place.geometry.location.lng() + 
                        	`)</div><div><img class="info_img" src="` + place.photos[0].getUrl() + 
                        	`"></div>`
                    });
                    infowindow.open(map, marker);
                    console.log('lat: ' + place.geometry.location.lat());
                    console.log('lng: ' + place.geometry.location.lng());
                    console.log(place.formatted_address);
                    console.log(document.getElementById('gatLoc'));
                    $('#gatLoc').val(place.formatted_address);
                    $('#gatLat').val(place.geometry.location.lat());
                    $('#gatLng').val(place.geometry.location.lng());
                }

            });
        }

        function initMap() {
            map = new google.maps.Map(document.getElementById('map'), {
                center: { lat: 24.978391, lng: 121.268641 },
                zoom: 12,
            });
        }
        window.onload = initmap;
    </script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAUjdRO41BwDZqvCuvsOd5FO-amf4gndBA&libraries=places&callback=initMap" async defer></script>
<script type="text/javascript">
	$("#magicbtn").click(function(){
		$("#gatName").val("大家一起來環島囉！");
		$("#f_date1").val("2020-09-17");
		$("#f_date2").val("2020-04-08");
		$("#f_date3").val("2020-08-31");
		$("#gatLoc").val("320台灣桃園市中壢區中大路300號");
		$("#gatIntro").val("既然，深入的探討環島，是釐清一切的關鍵。泰格奈爾說過一句著名的話，知心朋友相隔千山萬水，也似近在咫尺。這啟發了我。索福克勒斯曾經提到過，一句短短的諺語往往蘊含著豐富的智慧。這段話可說是震撼了我。我們都有個共識，若問題很困難，那就勢必不好解決。領悟其中的道理也不是那麼的困難。由於，我們要從本質思考，從根本解決問題。總結來說，莊子深信，有人之形，無人之情。有人之形，故群於人; 無人之情，故是非不得於身。請諸位將這段話在心中默念三遍。既然如此，若沒有環島的存在，那麼後果可想而知。老子曾說過，大巧若拙。這讓我對於看待這個問題的方法有了巨大的改變。環島，到底應該如何實現。帶著這些問題，我們一起來審視環島。一般來說，毛澤東講過一段深奧的話，黨的政策是黨的生命。這句話語雖然很短，但令我浮想聯翩。");
		$("#gatMax").val("42");
		$("#gatMin").val("3");
		$("#gatType").val("魚魚啵啵啵");
		$("#gatLat").val("24.9679966");
		$("#gatLng").val("121.1922029");
// 		$("#preview").append("<img class='foto' src=''>");
	});
</script>

</html>