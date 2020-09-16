$(function() {
	
	
	$("#fakeButton").click(function(){
	

		$("#addForm input[name='annchangeman']").val($("#LoginEmpno").text().trim());
		$("#addForm textarea[name='anntext']").val("解決寵物家長出游問題，忙碌時找不到人照顧毛小孩的困擾 愛毛小孩的您可利用閒暇時間接案；與寵物共享快樂時光");
	
		$("#addForm input[name='anntitle']").val("寵物保母招生中");
		
		
	});
	
	
	
	
	var ANN1 = '';
	var ANN2 = '';
	var ANN3 = '';
	for (let i = 0; i < $(".card-body td[name=annstatus]").length; i++) {
		let html = '';
		html = "<select name='annstatus'>" + "<option value=''>未上架</option>"
				+ "<option value='ANN1'>公告欄位1</option>"
				+ "<option value='ANN2'>公告欄位2</option>"
				+ "<option value='ANN3'>公告欄位3</option>";
		$(".card-body td[name=annstatus]").html(html);
	}

	for (let i = 0; i < $(".card-body td[name=annstatus]").length; i++) {
		if ($($(".card-body td[name=annstatus]")[i]).attr("value") == 'ANN1') {
			ANN1 = $($(".card-body td[name=annstatus]")[i]).parent().attr(
					'name');
			$($(".card-body td[name=annstatus]")[i]).find("select").find("option[value='ANN1']").attr("selected",true);
			for (let j = 0; j < $(".card-body td[name=annstatus]").length; j++) {
				if ($($(".card-body td[name=annstatus]")[j]).parent().attr('name') != ANN1){
					$($(".card-body td[name=annstatus]")[j]).find("option[value='ANN1']").remove();
				}
			}
		} else if ($($(".card-body td[name=annstatus]")[i]).attr("value") == 'ANN2') {
			ANN2 = $($(".card-body td[name=annstatus]")[i]).parent().attr('name');
			$($(".card-body td[name=annstatus]")[i]).find("select").find("option[value='ANN2']").attr("selected",true);
			for (let j = 0; j < $(".card-body td[name=annstatus]").length; j++) {
				if ($($(".card-body td[name=annstatus]")[j]).parent().attr('name') != ANN2){
					$($(".card-body td[name=annstatus]")[j]).find("option[value='ANN2']").remove();
				}
			}
		} else if ($($(".card-body td[name=annstatus]")[i]).attr("value") == 'ANN3') {
			ANN3 = $($(".card-body td[name=annstatus]")[i]).parent().attr(
					'name');
			$($(".card-body td[name=annstatus]")[i]).find("select").find("option[value='ANN3']").attr("selected",true);
			for (let j = 0; j < $(".card-body td[name=annstatus]").length; j++) {
				if ($($(".card-body td[name=annstatus]")[j]).parent().attr('name') != ANN3){
					$($(".card-body td[name=annstatus]")[j]).find("option[value='ANN3']").remove();
				}
			}
		}
	}


	
	
	$(".card-body select[name='annstatus']").change(function(e){

		let rowName = $(e.target).parent().parent()
		.attr("name");
		
		setChangeForm(e.target);
		let annstatus = $("#dataTable>tbody>tr[name='" + rowName + "'] select").val();
		
		if (annstatus === "ANN1") {
			$("#changeForm input[name='qastatus']").val("ANN1");
		}else if (annstatus === "ANN2") {
			$("#changeForm input[name='qastatus']").val("ANN2");
		}else if (annstatus === "ANN3") {
			$("#changeForm input[name='qastatus']").val("ANN3");
		}
		$("#changeForm")[0].submit();
	});

	
	
	
	
	

	$('#dataTable').DataTable();
});// $f








$(".addButton").click(function() {
	let annchangeman = $("#LoginEmpno").text().trim();
	$("#addForm input[name='annchangeman']").val(annchangeman);
});




$("input[name='change']").click(function(e){
	setChangeForm(e.target);
});

$(".imgButton")
.click(
		function() {
			beDark();
			$(".showBox").css('visibility', 'visible');
			
			let html = `<img src="/EA102G6/back-end/emp/ANNImg?annno=`
					+ $(this).attr('name')
					+ `"style="max-width:100%;max-height:100%;"></img>`;
			$(".showBox >p").html(html);
			letDivCenter($(".showBox"));
		});

function setChangeForm(obj){
	$("#changeForm .errorMsg").html('');
	let rowName = $(obj).parent().parent().attr("name");
	let anntext = $("#dataTable tr[name='" + rowName	+ "']>td[name='anntext']").text().trim();
	let anntitle = $("#dataTable tr[name='" + rowName	+ "']>td[name='anntitle']").text().trim();
	let changeman = $("#LoginEmpno").text().trim();
	let annstatustext = $("#dataTable tr[name='" + rowName	+ "'] select :selected").text();
	let html = `<img src="/EA102G6/back-end/emp/ANNImg?annno=`
		+ rowName
		+ `" style="max-width:100%;max-height:100%;"></img>`;
	let annstatusvalue = $("#dataTable tr[name='" + rowName	+ "'] select").val();
	
	$("#changeForm input[name='annno']").val(rowName);
	$("#changeForm input[name='annchangeman']").val(changeman);
	$("#changeForm textarea[name='anntext']").val(anntext);
	$("#changeForm .photo").html(html);
	$("#changeForm td[name='annstatus']").text(annstatustext);
	$("#changeForm input[name='annstatus']").val(annstatusvalue);
	$("#changeForm input[name='anntitle']").val(anntitle);
}