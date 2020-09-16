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
<title>�إߴ���</title>
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
							<h3 class="gat_header">�إߴ���</h3>
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
								placeholder="�п�J���ʮɶ�" class="single-input" style="width: 95%; margin-right: 46px;">
								<div class="errorMsgs">
							${errorMsgs.gatTime}
							</div>
						</div>
						
						<div class="input-group-icon mt-10" style="display: inline-block;">
							<div class="icon">
								<i class="fa fa-tag" aria-hidden="true"></i>
							</div>
							<select id="gatType" name="gatType" class="single-input type_select" style="height: 60px;width: 165%;">
								<option value="�߫}�p�p�p">�߫}�p�p�p</option>
								<option value="�����L�L�L">�����L�L�L</option>
								<option value="�D�D�R�R�R">�D�D�R�R�R</option>
								<option value="�����q�q�q">�����q�q�q</option>
								<option value="��������">��������</option>
								<option value="�ߨ߸�����">�ߨ߸�����</option>
								<option value="��L�o�e��">��L�o�e��</option>
							</select>
						</div>
					</div>
						<div class="input-group-icon mt-10" style="display: inline-block;">
							<div class="icon">
								<i class="fa fa-calendar-check-o" aria-hidden="true"></i>
							</div>
							<input type="text" name="gatStarttime" id="f_date2"
								placeholder="�п�J���W�ɶ�" class="single-input" style="width: 95%; margin-right: 46px;">
								<div class="errorMsgs" style="right: 13px;">
							${errorMsgs.gatStarttime}
							</div>
						</div>

						<div class="input-group-icon mt-10" style="display: inline-block;">
							<div class="icon">
								<i class="fa fa-calendar-times-o" aria-hidden="true"></i>
							</div>
							<input type="text" name="gatEndtime" id="f_date3"
								placeholder="�п�J�I��ɶ�" class="single-input">
								<div class="errorMsgs">
							${errorMsgs.gatEndtime}
							</div>
						</div>

						<div class="input-group-icon mt-10" style="display: inline-block; width: 51%; margin-right: 16px;">
							<div class="icon">
								<i class="fa fa-plus-square" aria-hidden="true"></i>
							</div>
							<input type="number" name="gatMax" min="1" max="1000"
								placeholder="�̰��H�ƤW��" onfocus="this.placeholder = ''"
								onblur="this.placeholder = '�̰��H�ƤW��'" required
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
								placeholder="�̧C�H�ƭ���" onfocus="this.placeholder = ''"
								onblur="this.placeholder = '�̧C�H�ƭ���'" required
								class="single-input" id="gatMin" value="<%=(gatVO == null) ? "" : gatVO.getGatMin()%>">
							<div class="errorMsgs">
							${errorMsgs.gatMin}
							</div>
						</div>

						<div class="mt-10">
							<textarea name="gatIntro" class="single-textarea"
								placeholder="�п�J����²��" onfocus="this.placeholder = ''" id="gatIntro"
								onblur="this.placeholder = '�п�J����²��'" required><%=(gatVO == null) ? "" : gatVO.getGatIntro()%></textarea>
							<div class="errorMsgs" style="right: 17px;">
							${errorMsgs.gatIntro}
							</div>
						</div>

						<div class="gat_add_btn">
							<input type="hidden" name="memNo" value="<%=memVO.getMemNo()%>">
							<input type="hidden" name="action" value="insert"> <input
								type="submit" value="�e�X�s�W" class="submit_btn">
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
								placeholder="�п�J���Φa�I" onfocus="this.placeholder = ''"
								onblur="this.placeholder = '�п�J���Φa�I'" required
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
									class="gatPhoto_btn"> <i class="fa fa-photo"></i> �W�ǹϤ�
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
                        content: 
                        	`<div class="info_title">` + place.name + 
                        	`</div><div><span class="info_head">�a�}: </span>` + place.formatted_address + 
                        	`</div><div><span class="info_head">�g�n��: </span>(` + place.geometry.location.lat() + `, ` + place.geometry.location.lng() + 
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
		$("#gatName").val("�j�a�@�_�����q�o�I");
		$("#f_date1").val("2020-09-17");
		$("#f_date2").val("2020-04-08");
		$("#f_date3").val("2020-08-31");
		$("#gatLoc").val("320�x�W��饫���c�Ϥ��j��300��");
		$("#gatIntro").val("�J�M�A�`�J�����Q���q�A�O��M�@��������C����`�����L�@�y�ۦW���ܡA���ߪB�ͬ۹j�d�s�U���A�]����b���ءC�o�ҵo�F�ڡC���֧J�Ǵ����g����L�A�@�y�u�u���λy����ĭ�t���״I�����z�C�o�q�ܥi���O�_�٤F�ڡC�ڭ̳����Ӧ@�ѡA�Y���D�ܧx���A���N�ե����n�ѨM�C�⮩�䤤���D�z�]���O���򪺧x���C�ѩ�A�ڭ̭n�q�����ҡA�q�ڥ��ѨM���D�C�`���ӻ��A���l�`�H�A���H���ΡA�L�H�����C���H���ΡA�G�s��H; �L�H�����A�G�O�D���o�󨭡C�нѦ�N�o�q�ܦb�ߤ��q���T�M�C�J�M�p���A�Y�S�����q���s�b�A�����G�i�Q�Ӫ��C�Ѥl�����L�A�j���Y��C�o���ڹ��ݫݳo�Ӱ��D����k���F���j�����ܡC���q�A�쩳���Ӧp���{�C�a�۳o�ǰ��D�A�ڭ̤@�_�Ӽf�����q�C�@��ӻ��A��A�F���L�@�q�`�����ܡA�Ҫ��F���O�Ҫ��ͩR�C�o�y�ܻy���M�ܵu�A���O�گB�Q�p���C");
		$("#gatMax").val("42");
		$("#gatMin").val("3");
		$("#gatType").val("�����q�q�q");
		$("#gatLat").val("24.9679966");
		$("#gatLng").val("121.1922029");
// 		$("#preview").append("<img class='foto' src=''>");
	});
</script>

</html>