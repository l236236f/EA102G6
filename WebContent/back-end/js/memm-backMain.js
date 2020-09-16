$(function() {

	for (let i = 0; i < $("#dataTable td[name='memstatus']").length; i++) {

		if ($($(".card-body td[name='memstatus']")[i]).attr("value") === 'M0') {
			$($(".card-body td[name='memstatus']")[i]).text("未驗證");
		}
		if ($($(".card-body td[name='memstatus']")[i]).attr("value") === 'M1') {
			$($(".card-body td[name='memstatus']")[i]).text("正常");
		}
		if ($($(".card-body td[name='memstatus']")[i]).attr("value") === 'M2') {
			$($(".card-body td[name='memstatus']")[i]).text("停權");
		}
	}

	for (let i = 0; i < $("#dataTable td[name='mom']").length; i++) {

		if ($($(".card-body td[name='mom']")[i]).attr("value") === 'M0') {
			$($(".card-body td[name='mom']")[i]).prepend("未申請");
		}
		if ($($(".card-body td[name='mom']")[i]).attr("value") === 'M1') {
			$($(".card-body td[name='mom']")[i]).prepend("申請中");

		}
		if ($($(".card-body td[name='mom']")[i]).attr("value") === 'M2') {
			$($(".card-body td[name='mom']")[i]).prepend("通過");

		}
		if ($($(".card-body td[name='mom']")[i]).attr("value") === 'M3') {
			$($(".card-body td[name='mom']")[i]).prepend("不符合資格");

		}

	}

	$("input[name='change']").click(
			function() {
				var rowName = $(this).parent().parent().attr('name');
				var tr = $("#dataTable tr[name='" + rowName + "']");
				var memacc = tr.find("td[name='memacc']").text();
				var mempw = tr.find("td[name='mempw']").text();
				var mememail = tr.find("td[name='mememail']").text();
				var memmoney = tr.find("td[name='memmoney']").text();
				var membonus = tr.find("td[name='membonus']").text();
				var memstatus = tr.find("td[name='memstatus']").attr("value");
				var mom = tr.find("td[name='mom']").attr("value");
				var memintro = tr.find("td[name='memintro']").text();
				var uprodevas = tr.find("td[name='uprodevas']").text();
				var uprodevacount = tr.find("td[name='uprodevacount']").text();
				var gatevas = tr.find("td[name='gatevas']").text();
				var gatevacount = tr.find("td[name='gatevacount']").text();
				
				$("#changeForm input[name='memno']").val(rowName)
				$("#changeForm span[name='memacc']").text(memacc)
				$("#changeForm input[name='mempw']").val(mempw)
				$("#changeForm input[name='mememail']").val(mememail)
				$("#changeForm input[name='memmoney']").val(memmoney)
				$("#changeForm input[name='membonus']").val(membonus)
				$(
						"#changeForm select[name='memstatus'] option[value='"
								+ memstatus + "']")
						.attr("selected", "selected")
				$("#changeForm select[name='mom'] option[value='" + mom + "']")
						.attr("selected", "selected")
				$("#changeForm td[name='memintro']").text(memintro)
				$("#changeForm td[name='uprodevas']").text(uprodevas)
				$("#changeForm td[name='uprodevacount']").text(uprodevacount)
				$("#changeForm td[name='gatevas']").text(gatevas)
				$("#changeForm td[name='gatevacount']").text(gatevacount)
			});
	
	
	$("#changeSubmit").click(function() {
		if ($("#changeForm input[name='mempw']").val().trim() === "") {
			$("#changeForm .errorMsg[name='mempw']").text("請勿空格")
		}
		if ($("#changeForm input[name='mememail']").val().trim() === "" ) {
			$("#changeForm .errorMsg[name='mememail']").text("請勿空格")
		}else{
			
			var emailRule = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z]+$/;
			if ($("#changeForm input[name='mememail']").val().search(emailRule) === -1) {
				$("#changeForm .errorMsg[name='mememail']").text("請填寫正確格式")
			}
			
		}

		if ($("#changeForm input[name='memmoney']").val().trim() === "") {
			$("#changeForm .errorMsg[name='memmoney']").text("請勿空格")
		}
		if ($("#changeForm input[name='membonus']").val().trim() === "") {
			$("#changeForm .errorMsg[name='membonus']").text("請勿空格")
		}
	
		
		if(checkSubmit($("#changeSubmit"))){
			$("#changeForm")[0].submit();
		}

	});

	
	$('#dataTable').dataTable();
});