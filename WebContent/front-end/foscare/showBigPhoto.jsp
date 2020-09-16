<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<!DOCTYPE html>
<style>
	#bigPhoto{
		margin:5px auto;
	}
	#bigPhoto>img{
		width:800px;
	}
</style>
<div class="modal fade" id="showBigPhoto" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">    
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLongTitle"></h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body" id="bigPhoto">
				
		  </div>
		  <div class="modal-footer">
		  <button type="button" class="btn btn-secondary" data-dismiss="modal" id="closeme">Close</button> 
	      </div>
	    </div>
	</div>
</div>
<script>
	$(document).ready(function(){
		$(".maxsix").click(function(){
			var photoNo = $(this).data("photo_no");
			$("#bigPhoto").html('<img src="<%= request.getContextPath() %>/front-end/ShowPhotos?type=fosmPho&photo_no='+photoNo+'">');
		});
	});
</script>