$(function() {
	var qakind3value;
	for (let i = 0; i < $(".card-body td[name=qastatus]").length; i++) {
		let html='';
		if ($($(".card-body td[name=qastatus]")[i]).attr("value") == 'QA0') {
			
			html = '<select name="qastatus"><option value="QA1">上架中</option><option value="QA0" selected="selected">下架中</option></select>';
		}
		if ($($(".card-body td[name=qastatus]")[i]).attr("value") == 'QA1') {
			
			html = '<select name="qastatus"><option value="QA1" selected="selected">上架中</option><option value="QA0" >下架中</option></select>';

		}
		$($(".card-body td[name=qastatus]")[i]).html(html);
	}
	
	$(".card-body select[name='qastatus']").change(function(e){
		let rowName = $(e.target).parent().parent()
		.attr("name");
		setChangeForm(e.target);
		let qastatus = $("#dataTable>tbody>tr[name='" + rowName + "'] select").val();
		
		if (qastatus === "QA1") {
			$("#changeForm select[name='qastatus']")[0].selectedIndex = 0;
		} else if (qastatus === "QA0") {
			$("#changeForm select[name='qastatus']")[0].selectedIndex = 1;
		}
		$("#changeForm")[0].submit();
	});
	
	
	
	
	
	$("input[name='change']").click(function(e){
		setChangeForm(e.target);
		let rowName = $(e.target).parent().parent().attr("name");
		let qakind3 = $("#dataTable tr[name='" + rowName	+ "']>td[name='qakind3']").text().trim();
		qakind3value=qakind3;
	});
	
	
	$("#changeSubmit").click(function() {
				let obj = this;
				let id = $(this).parent().parent()[0].id;
				let data = {};
				data.action = 'qaCheckKind3';
				data.kind3=$("#" + id + " input[name='qakind3']").val();
				checkReg($("#changeSubmit"));
				$.ajax({
					url : "qa.do",
					type : 'POST',
					data : data,
					success : function(res) {
						if (res == 'true') {
							$("#" + id + " td[name='qakind3']")
									.html("類別三重複");
						}
						if ($("#" + id + " input[name='qakind3']").val()
								.trim() == qakind3value) {
							$("#" + id + " td[name='qakind3']")
									.html('');
						}
						if (checkSubmit($("#changeSubmit"))) {
							$(obj).parent().parent()[0].submit();
						
						}
					}
				});
			});
	
	$("#fakeButton").click(function(){
		
		
		$("#addForm input[name='qachangeman']").val($("#LoginEmpno").text().trim());
		
		$("#addForm textarea[name='qatext']").val("請註冊好帳號後，即可利用聊天室聯絡客服!");
		$("#addForm input[name='qakind']").val("帳號");
		$("#addForm input[name='qakind2']").val("新手上路");
		$("#addForm input[name='qakind3']").val("聯絡客服");
	
		
		
	});
	
	
	
	
	$("#addSubmit").click(function() {
		let obj=this;
		let changeman = $("#LoginEmpno").text().trim();
		$("#addForm input[name='qachangeman']").val(changeman);
		let id = $(this).parent().parent()[0].id;		
		checkReg($("#addSubmit"));
		let data = {};
		data.action = 'qaCheckKind3';
		data.kind3=$("#" + id + " input[name='qakind3']").val();
		checkReg($("#addSubmit"));
		$.ajax({
			url : "qa.do",
			type : 'POST',
			data : data,
			success : function(res) {
				
				if (res == 'true') {
					$("#" + id + " td[name='qakind3']")
							.html("類別三重複");
				}
				if (checkSubmit($("#addSubmit"))) {
					$(obj).parent().parent()[0].submit();
				
				}
			}
		});
		
		
		
		
	});
	
	$('#dataTable').DataTable();
});


function checkReg(obj) {
	var id = $(obj).parent().parent()[0].id;
	cantBeBlank($("#" + id + " textarea[name='qatext']"), $("#" + id
			+ " td[name='qatext']"));
	cantBeBlank($("#" + id + " input[name='qakind']"), $("#" + id
			+ " td[name='qakind']"));
	cantBeBlank($("#" + id + " input[name='qakind2']"), $("#" + id
			+ " td[name='qakind2']"));
	cantBeBlank($("#" + id + " input[name='qakind3']"), $("#" + id
			+ " td[name='qakind3']"));
}

function setChangeForm(obj){
	$("#changeForm .errorMsg").html('');
	let rowName = $(obj).parent().parent().attr("name");
	let qatext = $("#dataTable tr[name='" + rowName	+ "']>td[name='qatext']").text().trim();
	let qakind = $("#dataTable tr[name='" + rowName	+ "']>td[name='qakind']").text().trim();
	let qakind2 = $("#dataTable tr[name='" + rowName	+ "']>td[name='qakind2']").text().trim();
	let qakind3 = $("#dataTable tr[name='" + rowName	+ "']>td[name='qakind3']").text().trim();
	let changeman = $("#LoginEmpno").text().trim();
	let qastatus = $("#dataTable tr[name='" + rowName	+ "']>td[name='qastatus']").attr("value");
	
	$("#changeForm input[name='qano']").val(rowName);
	$("#changeForm input[name='qachangeman']").val(changeman);
	
	$("#changeForm textarea[name='qatext']").val(qatext);
	$("#changeForm input[name='qakind']").val(qakind);
	$("#changeForm input[name='qakind2']").val(qakind2);
	$("#changeForm input[name='qakind3']").val(qakind3);
	
	if(qastatus==="QA1"){
		$("#changeForm select[name='qastatus']").val("QA1");
	}else if(qastatus==="QA0"){
		$("#changeForm select[name='qastatus']").val("QA0");
	}
	
}