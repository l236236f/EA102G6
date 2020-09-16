/**
 * 
 */

$(function() {
	let data = {};
	data.action = "getAllQajson";

	$.ajax({
		url : "/EA102G6/back-end/emp/qa.do",
		type : "POST",
		dataType : "json",
		data : data,
		success : function(res) {
			var kind = [];

			for (let i = 0; i < res.length; i++) {
				kind.push(res[i].kind);
			}
			for (let i = 0; i < uniqueList(kind).length; i++) {
				$("#qacontainer .kind1").append("<li class='li-kind1'></li>");
				$("#qacontainer .kind1").find("li").eq(i).text(
						uniqueList(kind)[i]);
			}
			$("#qacontainer .li-kind1").click(
					function(e) {
						$("#qacontainer .li-kind1").removeClass("onActive");
						$(e.target).addClass("onActive");
						var kind2 = [];
						$("#qacontainer .li-kind2").remove();
						$("#qacontainer .li-kind3").remove();
						for (let i = 0; i < res.length; i++) {
							if (res[i].kind == $(e.target).text()) {
								kind2.push(res[i].kind2);
							}
						}
						for (let i = 0; i < uniqueList(kind2).length; i++) {
							$("#qacontainer .kind2").append(
									`<li class='li-kind2'></li>`);
							$("#qacontainer .kind2").find("li.li-kind2").eq(i)
									.text(uniqueList(kind2)[i]);
						}//放入不重複的kind2

						$("#qacontainer .li-kind2").click(function(e2) {
							$("#qacontainer .li-kind2").removeClass("onActive");
							$(e2.target).addClass("onActive");
							$("#qacontainer .li-kind3").remove();
							var kind3=[];
							for (let i = 0; i < res.length; i++) {
								if (res[i].kind == $(e.target).text()) {
									if(res[i].kind2==$(e2.target).text()){
										kind3.push(res[i].kind3);
									}
								}
							}
							for (let j = 0; j < uniqueList(kind3).length; j++) {
								$("#qacontainer .kind3").append(`<li></li>`);
							    $("#qacontainer .kind3").find("li").addClass("li-kind3");
								$("#qacontainer .kind3").find("li.li-kind3").eq(j)
										.text(uniqueList(kind3)[j]);
							}//放入不重複的kind3
							$("#qacontainer .li-kind3").click(function(e3) {
								$(".textBox .qatext").attr("style","visibility:visible;");
								$("#qacontainer .li-kind3").removeClass("onActive");
								$(e3.target).addClass("onActive");
								for (let k = 0; k < res.length; k++) {
									if(res[k].kind3 == $(e3.target).text()){
									$(".textBox .qatext").text(res[k].text);	
									}
								}
							});
							
					
						});// KIND1點擊後生成KIND2並註冊KIND2事件

					});// KIND1點擊事件

		}
	});

});

function uniqueList(array) {
	var deduped = Array.from(new Set(array))
	return deduped.sort()
}