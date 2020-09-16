<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.gat.model.*"%>

<%
	GatVO gatVO = (GatVO) request.getAttribute("gatVO");
	// 	String memNo = request.getParameter("memNo");
%>

<!DOCTYPE html>
<html>
<%@ include file="/front-end/Header/header.jsp"%>
<head>
<title>�קﴪ�θ�T</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/gat/style/style_gat_update.css" />
</head>
<body>
	<!-- bradcam_area_start -->
	<div id="benner_bg" style="background-image: url(<%=request.getContextPath()%>/front-end/gat/images/banner_dog.png);">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div class="bradcam_text text-center">
						<div class="gat_div">
							<h3 class="gat_header"><%=gatVO.getGatName()%> <i class="fa fa-pencil-square"></i></h3>
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
							<input type="text" name="gatName" placeholder="�п�J���ΦW��"
								onfocus="this.placeholder = ''"
								onblur="this.placeholder = '�п�J���ΦW��'" required
								class="single-input"
								value="<%=gatVO.getGatName()%>">
						</div>

						<div class="gattime_wrapper" style="border-bottom: 2px solid #f1f1f1; ">
						<div class="input-group-icon mt-10" style="display: inline-block; height: 88px; width: 53%;">
							<div class="icon">
								<i class="fa fa-clock-o" aria-hidden="true"></i>
							</div>
							<input type="text" name="gatTime" id="f_date1"
								placeholder="�п�J���ʮɶ�" class="single-input" style="width: 95%; margin-right: 46px;">
						</div>
						
						<div class="input-group-icon mt-10" style="display: inline-block;">
							<div class="icon">
								<i class="fa fa-tag" aria-hidden="true"></i>
							</div>
							<select name="gatType" class="single-input type_select" style="height: 60px;width: 165%;">
								<option value="�߫}�p�p�p">�߫}�p�p�p
								<option value="�����L�L�L">�����L�L�L
								<option value="�D�D�R�R�R">�D�D�R�R�R
								<option value="�����q�q�q">�����q�q�q
								<option value="��������">��������
								<option value="�ߨ߸�����">�ߨ߸�����
								<option value="��L�o�e��">��L�o�e��
							</select>
						</div>
						</div>

						<div class="input-group-icon mt-10" style="display: inline-block;">
							<div class="icon">
								<i class="fa fa-calendar-check-o" aria-hidden="true"></i>
							</div>
							<input type="text" name="gatStarttime" id="f_date2"
								placeholder="�п�J���W�ɶ�" class="single-input" style="width: 95%; margin-right: 46px;">
						</div>

						<div class="input-group-icon mt-10" style="display: inline-block;">
							<div class="icon">
								<i class="fa fa-calendar-times-o" aria-hidden="true"></i>
							</div>
							<input type="text" name="gatEndtime" id="f_date3"
								placeholder="�п�J�I��ɶ�" class="single-input">
						</div>

						<div class="input-group-icon mt-10" style="display: inline-block; width: 51%; margin-right: 16px;">
							<div class="icon">
								<i class="fa fa-plus-square" aria-hidden="true"></i>
							</div>
							<input type="number" name="gatMax" min="1" max="1000"
								placeholder="�̰��H�ƤW��" onfocus="this.placeholder = ''"
								onblur="this.placeholder = '�̰��H�ƤW��'" required
								class="single-input"
								value="<%=gatVO.getGatMax()%>">
						</div>

						<div class="input-group-icon mt-10" style=" width: 45%; display: inline-block;">
							<div class="icon">
								<i class="fa fa-minus-square" aria-hidden="true"></i>
							</div>
							<input type="number" name="gatMin" min="1" max="1000"
								placeholder="�̧C�H�ƭ���" onfocus="this.placeholder = ''"
								onblur="this.placeholder = '�̧C�H�ƭ���'" required
								class="single-input"
								value="<%=gatVO.getGatMin()%>">
						</div>

						<div class="mt-10">
							<textarea name="gatIntro" class="single-textarea"
								placeholder="�п�J����²��" onfocus="this.placeholder = ''"
								onblur="this.placeholder = '�п�J����²��'" required><%=gatVO.getGatIntro()%></textarea>
						</div>

						<div class="gat_add_btn">
							<input type="hidden" name="memNo" value="<%=memVO.getMemNo()%>">
							<input type="hidden" name="gatNo" value="<%=gatVO.getGatNo()%>">
							<input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>">
							<input type="hidden" name="action" value="update"> <input
								type="submit" value="�T�{�ק�" class="submit_btn">
						</div>

					</div>

					<div class="col-lg-6 col-md-6">
						
						<div class="input-group-icon mt-10">
							<div class="icon">
								<i class="fa fa-map-marker" aria-hidden="true"></i>
							</div>
							<input type="text" id="gatLoc" name="gatLoc"
								placeholder="�п�J���Φa�I" onfocus="this.placeholder = ''"
								onblur="this.placeholder = '�п�J���Φa�I'" required
								class="single-input"
								value="<%=gatVO.getGatLoc()%>" style="margin-bottom: 30px;">
							<input type="hidden" id="gatLat" name="gatLat" value="<%=gatVO.getGatLat()%>">
							<input type="hidden" id="gatLng" name="gatLng" value="<%=gatVO.getGatLng()%>">
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
									class="gatPhoto_btn"> <i class="fa fa-photo"></i> �W�ǹϤ�
								</label>
								<div class="preview_row">
									<div id="preview"><img src="${photo}"></div>
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
<!-- =========================================�H�U�� datetimepicker �������]�w========================================== -->

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
	       step: 1,                //step: 60 (�o�Otimepicker���w�]���j60����)
	       format:'Y-m-d',   //format:'Y-m-d H:i:s',
		   value: '<%=gatTime%>'   // value:   new Date()
			  
		//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // �h���S�w���t
		//startDate:	            '2017/07/10',  // �_�l��
		//minDate:               '-new Date()', // �h������(���t)���e
		//maxDate:               '+1970-01-01'  // �h������(���t)����
        });

        $('#f_date2').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,        //timepicker:true,
	       step: 1,                //step: 60 (�o�Otimepicker���w�]���j60����)
	       format:'Y-m-d',   //format:'Y-m-d H:i:s',
		   value: '<%=gatStarttime%>',
		   maxDate:'<%=gatStarttime%>'
        });

	     $('#f_date3').datetimepicker({
		    theme: '',              //theme: 'dark',
		    timepicker:false,        //timepicker:true,
		    step: 1,                //step: 60 (�o�Otimepicker���w�]���j60����)
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
						alert('�ФW�ǹϤ�');
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
                componentRestrictions: { country: 'tw' } // ����b�x�W�d��
            };
            var autocomplete = new google.maps.places.Autocomplete(keyword, options);

            // �a�}����J�ءA�Ȧ��ܰʮɰ���
            autocomplete.addListener('place_changed', function() {
                var place = autocomplete.getPlace(); // �a�I��Ʀs�iplace
                console.log(place);
                // debugger;
                // �T�{�^�Ӫ���Ʀ��g�n��
                if (place.geometry) {
                    // ����map�������I
                    var searchCenter = place.geometry.location;
                    // panTo�O���Ʋ��ʡBsetCenter�O�������ܦa�Ϥ���
                    map.panTo(searchCenter);
                    // �b�j�M���G���a�I�W��m�аO
                    var marker = new google.maps.Marker({
                        position: searchCenter,
                        map: map
                    });
                    // info window
                    var infowindow = new google.maps.InfoWindow({
                        content: `
                            <div class="info_title">` + place.name + `</div>
                            <div><span class="info_head">�a�}: </span>` + place.formatted_address + `</div>
                            <div><span class="info_head">�g�n��: </span>(` + place.geometry.location.lat() + `, ` + place.geometry.location.lng() + `)</div> 
                            <div><img class="info_img" src="` + place.photos[0].getUrl() + `"></div>
                        `
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
<script
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAUjdRO41BwDZqvCuvsOd5FO-amf4gndBA&libraries=places&callback=initMap"
	async defer></script>

</html>