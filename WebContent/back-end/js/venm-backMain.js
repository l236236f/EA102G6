$(function() {

	for (let i = 0; i < $("#dataTable td[name='venstatus']").length; i++) {

		if ($($(".card-body td[name='venstatus']")[i]).attr("value") === 'V0') {
			$($(".card-body td[name='venstatus']")[i]).text("未驗證");
		}
		if ($($(".card-body td[name='venstatus']")[i]).attr("value") === 'V1') {
			$($(".card-body td[name='venstatus']")[i]).text("已驗證");
		}
		if ($($(".card-body td[name='venstatus']")[i]).attr("value") === 'V2') {
			$($(".card-body td[name='venstatus']")[i]).text("停權中");
		}
	}







	$('#dataTable').dataTable();
});


$("input[name='change']").click(
		function() {
			var rowName = $(this).parent().parent().attr('name');
			var tr = $("#dataTable tr[name='" + rowName + "']");
			
			
			var venacc = tr.find("td[name='venacc']").text();
			var venpw = tr.find("td[name='venpw']").text();
			var venemail = tr.find("td[name='venemail']").text();
			var venmoney = tr.find("td[name='venmoney']").text();
			var venstatus = tr.find("td[name='venstatus']").attr("value");
			var venintro = tr.find("td[name='venintro']").text();
			var venname = tr.find("td[name='venname']").text();
			var venid = tr.find("td[name='venid']").text();
			var ventel = tr.find("td[name='ventel']").text();
			var venaddr = tr.find("td[name='venaddr']").text();
			var regtime = tr.find("td[name='regtime']").text();
			
			
			
			$("#changeForm input[name='venno']").val(rowName)
			$("#changeForm span[name='venacc']").text(venacc)
			$("#changeForm input[name='venpw']").val(venpw)
			$("#changeForm input[name='venname']").val(venname)
			$("#changeForm input[name='ventel']").val(ventel)
			$("#changeForm input[name='venmoney']").val(venmoney)
			$("#changeForm input[name='venaddr']").val(venaddr)
			$("#changeForm input[name='venemail']").val(venemail)
			$("#changeForm input[name='venid']").val(venid)
			$("#changeForm td[name='venintro']").text(venintro)
			$("#changeForm span[name='regtime']").text(regtime)
			$("#changeForm select[name='venstatus'] option[value='"
							+ venstatus + "']")
					.attr("selected", "selected")
			
		});

$("#changeSubmit").click(function() {
	$("#changeForm .errorMsg").text("");
	if ($("#changeForm input[name='venpw']").val().trim() === "") {
		$("#changeForm .errorMsg[name='venpw']").text("請勿空格")
	}
	if ($("#changeForm input[name='venemail']").val().trim() === "" ) {
		$("#changeForm .errorMsg[name='venemail']").text("請勿空格")
	}else{
		
		var emailRule = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z]+$/;
		if ($("#changeForm input[name='venemail']").val().search(emailRule) === -1) {
			$("#changeForm .errorMsg[name='venemail']").text("請填寫正確格式")
		}
		
	}

	if ($("#changeForm input[name='venmoney']").val().trim() === "") {
		$("#changeForm .errorMsg[name='venmoney']").text("請勿空格")
	}
	if ($("#changeForm input[name='venname']").val().trim() === "") {
		$("#changeForm .errorMsg[name='venname']").text("請勿空格")
	}
	if ($("#changeForm input[name='ventel']").val().trim() === "") {
		$("#changeForm .errorMsg[name='ventel']").text("請勿空格")
	}
	if ($("#changeForm input[name='venaddr']").val().trim() === "") {
		$("#changeForm .errorMsg[name='venaddr']").text("請勿空格")
	}
	
	if(checkSubmit($("#changeSubmit"))){
		$("#changeForm")[0].submit();
	}

});

		