/**
 * 
 */


$(function() {
	

	
	
	
	$('#dataTable').DataTable();	
});//f


$("Button[name=changeAutho]").click(function(e){
	$("#changeAUTHO input[type='checkbox']").prop("checked",false);
	
	var rowName = $(e.target).parents("tr").find("td[name='empno']").text();
	for(let i=1;i<7;i++){
		if($("."+rowName+" .checkbox"+[i]).is(":checked")){
		
			$("#changeAUTHO .checkbox"+[i]).prop("checked",true);
		}
	}
	console.log(rowName);
	$("#changeAUTHO input[name='empno']").val(rowName);
	
	$("#changeAUTHO")[0].submit();
	
});