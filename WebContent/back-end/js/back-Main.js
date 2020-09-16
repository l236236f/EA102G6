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

$("input[name='change']").click(function(e) {
	$("#changeForm .errorMsg").html('');
	let rowName = $(e.target).parent().parent().attr("name");
	beDark();
	$(".changeText[name='changeBox']").css('visibility', 'visible');
	letDivCenter($(".changeText[name='changeBox']"));
});
$("input[name='cancel']").click(function() {
	beBright();
	$(".changeText[name='changeBox']").css('visibility', 'hidden');
	$(".changeText[name='addBox']").css('visibility', 'hidden');
	$(".showBox").css('visibility', 'hidden');
});

$(".addButton").click(function() {
	$("#addForm .errorMsg").html('');
	beDark();
	$(".changeText[name='addBox']").css('visibility', 'visible');
	letDivCenter($(".changeText[name='addBox']"));
});

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



