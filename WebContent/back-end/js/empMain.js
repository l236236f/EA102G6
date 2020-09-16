
$(function() {
		
	$("#fakeButton").click(function(){
	
		$("#addForm input[name='empname']").val("大衛海鮮");

		$("#addForm input[name='emptel']").val("0910234587");
		$("#addForm input[name='empemail']").val("cky771234@gmail.com");
		$("#addForm input[name='empid']").val('david');
		$("#addForm input[name='empposition']").val('工程師');
		$("#addForm input[name='empchangeman']").val($("#LoginEmpno").text().trim());
		$("#addForm textarea[name='empnotes']").val('是個帥哥');
	
		
		
	});
	
	
	var empidvalue;

	for (let i = 0; i < $(".card-body td[name=empstatus]").length; i++) {
		let html = '';
		if ($($(".card-body td[name=empstatus]")[i]).attr("value") == 'E0') {
			html = '<select name="empstatus"><option value="E1">在職</option><option value="E0" selected="selected">停權</option></select>';
		}
		if ($($(".card-body td[name=empstatus]")[i]).attr("value") == 'E1') {
			html = '<select name="empstatus" ><option value="E1" selected="selected">在職</option><option value="E0" >停權</option></select>';
		}
		$($(".card-body td[name=empstatus]")[i]).html(html);
	}

	$(".card-body select[name='empstatus']")
			.change(
					function(e) {
						
						$("#changeForm .errorMsg").html('');

						let rowName = $(e.target).parent().parent()
								.attr("name");
						let empname = $(
								`#dataTable>tbody>tr[name='` + rowName
										+ `']>td[name='empname']`).text()
								.trim();
						let emptel = $(
								`#dataTable>tbody>tr[name='` + rowName
										+ `']>td[name='emptel']`).text().trim();
						let empemail = $(
								`#dataTable>tbody>tr[name='` + rowName
										+ `']>td[name='empemail']`).text()
								.trim();
						let empid = $(
								`#dataTable>tbody>tr[name='` + rowName
										+ `']>td[name='empid']`).text().trim();
						let emppsw = $(
								`#dataTable>tbody>tr[name='` + rowName
										+ `']>td[name='emppsw']`).text().trim();
						let empposition = $(
								`#dataTable>tbody>tr[name='` + rowName
										+ `']>td[name='empposition']`).text()
								.trim();
						let emphiredate = $(
								`#dataTable>tbody>tr[name='` + rowName
										+ `']>td[name='emphiredate']`).text()
								.trim();
						let empchangedate = Date().now;
						let empchangeman = $("#LoginEmpno").text().trim();
						let empstatus = $(
								`#dataTable>tbody>tr[name='` + rowName
										+ `'] select`).val();
						
						let empnotes = $('.notesButton[name=' + rowName + ']')
								.attr('value');
						let html = `<img src="/EA102G6/back-end/emp/Img?empno=`
								+ rowName
								+ `" style="max-width:100%;max-height:100%;" onerror="this.style.display='none'"></img>`;
						empidvalue = empid;

						$("#changeForm input[name='empno']").val(rowName);
						$("#changeForm input[name='empname']").val(empname);
						$("#changeForm input[name='emptel']").val(emptel);
						$("#changeForm input[name='empemail']").val(empemail);
						$("#changeForm input[name='empid']").val(empid);
						$("#changeForm input[name='emppsw']").val(emppsw);
						$("#changeForm input[name='empposition']").val(
								empposition);
						$("#changeForm input[name='empchangeman']").val(
								empchangeman);
						$("#changeForm textarea[name='empnotes']")
								.val(empnotes);
						
						if (empstatus === "E1") {
							
							$("#changeForm select[name='empstatus']")[0].selectedIndex = 0;
						} else if (empstatus === "E0") {
							$("#changeForm select[name='empstatus']")[0].selectedIndex = 1;
						}
						
						$('#ChangeDate').datetimepicker({
							theme : '',
							timepicker : false,
							step : 1,
							format : 'Y-m-d',
							value : emphiredate,
							maxDate : Date.now()
						});
						$("#changeForm")[0].submit();
					});

	$("input[name='photo']").change(function(e) {
		var id =$(e.target).parents('FORM').attr("id");
		var fileList = this.files;
		var reader = new FileReader();
		reader.readAsDataURL(fileList[0]);
		reader.onload = function() {
			$(".photo").html('');
			var img = document.createElement("img");
			img.setAttribute("class", "inputImg");
			img.setAttribute("style", `max-width:100%;`);
			img.src = reader.result;
			$("#"+id+" .photo").append(img);
		};
	});

	$("input[name='change']")
			.click(
					function(e) {
						$("#changeForm .errorMsg").html('');

						let rowName = $(e.target).parent().parent()
								.attr("name");
						beDark();
						$(".changeText[name='changeBox']").css('visibility',
								'visible');
						letDivCenter($(".changeText[name='changeBox']"));

						let empname = $(
								`#dataTable>tbody>tr[name='` + rowName
										+ `']>td[name='empname']`).text()
								.trim();
						let emptel = $(
								`#dataTable>tbody>tr[name='` + rowName
										+ `']>td[name='emptel']`).text().trim();
						let empemail = $(
								`#dataTable>tbody>tr[name='` + rowName
										+ `']>td[name='empemail']`).text()
								.trim();
						let empid = $(
								`#dataTable>tbody>tr[name='` + rowName
										+ `']>td[name='empid']`).text().trim();
						let emppsw = $(
								`#dataTable>tbody>tr[name='` + rowName
										+ `']>td[name='emppsw']`).text().trim();
						let empposition = $(
								`#dataTable>tbody>tr[name='` + rowName
										+ `']>td[name='empposition']`).text()
								.trim();
						let emphiredate = $(
								`#dataTable>tbody>tr[name='` + rowName
										+ `']>td[name='emphiredate']`).text()
								.trim();
						let empchangedate = Date().now;
						let empchangeman = $("#LoginEmpno").text().trim();
						let empnotes = $('.notesButton[name=' + rowName + ']')
								.attr('value');
						let html = `<img src="/EA102G6/back-end/emp/Img?empno=`
								+ rowName
								+ `" style="max-width:100%;max-height:100%;" onerror="this.style.display='none'"></img>`;
						empidvalue = empid;
						let empstatus = $("#dataTable tr[name='" + rowName	+ "']>td[name='empstatus']").attr("value");
						$("#changeForm input[name='empno']").val(rowName);
						$("#changeForm input[name='empname']").val(empname);
				
						$("#changeForm input[name='emptel']").val(emptel);
						$("#changeForm input[name='empemail']").val(empemail);
						$("#changeForm input[name='empid']").val(empid);
						$("#changeForm input[name='emppsw']").val(emppsw);
						$("#changeForm input[name='empposition']").val(
								empposition);
						$("#changeForm input[name='empchangeman']").val(
								empchangeman);
						$("#changeForm textarea[name='empnotes']")
								.val(empnotes);
						$("#changeForm .photo").html(html);
						
						if(empstatus==="E1"){
							$("#changeForm select[name='emptatus']").val("E1");
						}else if(empstatus==="E0"){
							$("#changeForm select[name='empstatus']").val("E0");
						}
						
						
						
						
						$('#ChangeDate').datetimepicker({
							theme : '', // theme: 'dark',
							timepicker : false, // timepicker:true,
							step : 1, // step: 60 (這是timepicker的預設間隔60分鐘)
							format : 'Y-m-d',
							value : emphiredate,// format:'Y-m-d H:i:s',
							// disabledDates:
							// ['2017/06/08','2017/06/09','2017/06/10'], //
							// 去除特定不含
							// startDate: '2017/07/10', // 起始日
							// minDate: '-1970-01-01', // 去除今日(不含)之前
							maxDate : Date.now()
						// 去除今日(不含)之後
						});
					});
	$("input[name='cancel']").click(function() {
		beBright();
		$(".changeText[name='changeBox']").css('visibility', 'hidden');
		$(".changeText[name='addBox']").css('visibility', 'hidden');
		$(".showBox").css('visibility', 'hidden');
	});
	$(".addButton").click(function() {
		$("#addSubmit").attr('disable', false);
		$("#addForm .errorMsg").html('');
		beDark();
		$(".changeText[name='addBox']").css('visibility', 'visible');
		letDivCenter($(".changeText[name='addBox']"));
		let empchangeman = $("#LoginEmpno").text().trim();
		$("#addForm input[name='empchangeman']").val(empchangeman);
	});

	$(".notesButton").click(function() {
		beDark();
		$(".showBox").css('visibility', 'visible');
		letDivCenter($(".showBox"));
		let html = $(this).attr('value');
		$(".showBox >p").html(html)
	});

	$(".imgButton")
			.click(
					function() {
						beDark();
						$(".showBox").css('visibility', 'visible');
						
						let html = `<img src="/EA102G6/back-end/emp/Img?empno=`
								+ $(this).attr('name')
								+ `" width="100%" onerror="this.style.display='none'"></img>`;
						$(".showBox >p").html(html);
						letDivCenter($(".showBox"));
					});

	$("#changeSubmit").click(
			function() {
				let obj = this;
				let id = $(this).parent().parent()[0].id;
				let data = {};
				data.action = 'checkid';
				data.empid = $("#" + id + " input[name='empid']").val();
				str = $("#" + id + " input[name='empid']").val().trim();
				checkReg($("#changeSubmit"));
				$
						.ajax({
							url : "emp.do",
							type : 'POST',
							data : data,
							success : function(res) {
								if (res == 'true') {
									$("#" + id + " td[name='inputErrorV4']")
											.html("帳號重複");
								}

								if ($("#" + id + " input[name='empid']").val()
										.trim() == empidvalue) {
									$("#" + id + " td[name='inputErrorV4']")
											.html('');
								}
								if (checkSubmit($("#changeSubmit"))) {
									$(obj).parent().parent()[0].submit();
								
								}
							}
						});
			});
	$("#addSubmit").click(function() {
		
		let obj = this;
		let id = $(this).parent().parent()[0].id;
		let data = {};
		data.action = 'checkid';
		data.empid = $("#" + id + " input[name='empid']").val();
		checkReg($("#addSubmit"));
		$.ajax({
			url : "emp.do",
			type : 'POST',
			data : data,
			success : function(res) {
				if (res == 'true') {
					$("#" + id + " td[name='inputErrorV4']").html('帳號重複');	
				}
				if (checkSubmit($("#addSubmit"))) {
					$("#addSubmit").attr('disable', true);
					$(obj).parent().parent()[0].submit();	
				}
			}
		});
	});
	$('#dataTable').DataTable();
});// $f

function checkSubmit(obj) {
	let d = true;
	var id = $(obj).parent().parent()[0].id;
	for (var i = $("#" + id + " .errorMsg").length - 1; i >= 0; i--) {
		if ($("#" + id + " .errorMsg")[i].innerHTML != '') {
			d = false;
		}
	}
	return d;
};

function checkReg(obj) {
	var id = $(obj).parent().parent()[0].id;
	cantBeBlank($("#" + id + " input[name='empname']"), $("#" + id
			+ " td[name='inputErrorV1']"));
	cantBeBlank($("#" + id + " input[name='emptel']"), $("#" + id
			+ " td[name='inputErrorV2']"));
	if($("#" + id + " input[name='emptel']").val().length>10){
		$("#" + id + " td[name='inputErrorV2']").text("請勿超過10位數字");
	}
	cantBeBlank($("#" + id + " input[name='empid']"), $("#" + id
			+ " td[name='inputErrorV4']"));
	cantBeBlank($("#" + id + " input[name='emppsw']"), $("#" + id
			+ " td[name='inputErrorV5']"));
	cantBeBlank($("#" + id + " input[name='empposition']"), $("#" + id
			+ " td[name='inputErrorV6']"));
	if ($("#" + id + " input[name='empemail']").val().trim() == "") {
		$("#" + id + " td[name='inputErrorV3']").html('請勿空白');
	} else {
		$("#" + id + " td[name='inputErrorV3']").html('');
		emailRule = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z]+$/;
		if ($("#" + id + " input[name='empemail']").val().search(emailRule) != -1) {
			$("#" + id + " td[name='inputErrorV3']").html('');
		} else {
			$("#" + id + " td[name='inputErrorV3']").html('請填寫為EMAIL格式');
		}
	}
}
function cantBeBlank(obj, obj2) {
	var vname = obj.val() == undefined ? '' : obj.val().trim();
	vname = vname.replace(/ /g, '%20');
	if (vname == "") {
		obj2.html('請勿空白');
	} else {
		obj2.html('');
	}
}

function beBright() {
	$("head")
			.append(
					`<style type="text/css">#content-wrapper::before{visibility: hidden;}</style>`);
}
function beDark() {
	$("head")
			.append(
					`<style type="text/css">#content-wrapper::before{visibility: visible;}</style>`);
}
function letDivCenter(divName) {
	var top = ($(window).height() - $(divName).height()) / 2;

	top > 300 ? top = top - 80 : '';
	var left = ($(window).width() - $(divName).width()) / 2;
	var scrollTop = $(document).scrollTop();
	var scrollLeft = $(document).scrollLeft();
	$(divName).css({
		position : 'absolute',
		'top' : top + scrollTop,
		left : left + scrollLeft
	}).show();

}
$.datetimepicker.setLocale('zh');
$('#AddDate').datetimepicker({
	theme : '', // theme: 'dark',
	timepicker : false, // timepicker:true,
	step : 1, // step: 60 (這是timepicker的預設間隔60分鐘)
	format : 'Y-m-d', // format:'Y-m-d H:i:s',
	value : '<%=hiredate%>', // value: new Date(),
	// disabledDates: ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
	// startDate: '2017/07/10', // 起始日
	// minDate: '-1970-01-01', // 去除今日(不含)之前
	maxDate : Date.now()
// 去除今日(不含)之後
});
