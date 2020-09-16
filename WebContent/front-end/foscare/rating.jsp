<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<style type="text/css">
.rating {display: flex;flex-direction: row-reverse;justify-content: center}
.rating>input {display: none}
.rating>label {position: relative;width: 1em;font-size: 6vw;color: #FFD600;cursor: pointer}
.rating>label::before {content: "\2605";position: absolute;opacity: 0}
.rating>label:hover:before, .rating>label:hover ~label:before {opacity: 1 !important}
.rating>input:checked ~label:before {opacity: 1}
.rating:hover>input:checked ~label:before {	opacity: 0.4}
</style>
<div class="modal fade" id="exampleModalLong1" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">    
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLongTitle">給個評價吧，chuchu你~填完會送罐罐點數哦~</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body" id="evatext">
				<div class="rating"> 
				<input type="radio" name="rating" value="5" id="5"><label for="5">☆</label> 
				<input type="radio" name="rating" value="4" id="4"><label for="4">☆</label> 
				<input type="radio" name="rating" value="3" id="3"><label for="3">☆</label>
				<input type="radio" name="rating" value="2" id="2"><label for="2">☆</label>
				<input type="radio" name="rating" value="1" id="1"><label for="1">☆</label>
		  </div>
				<textarea placeholder="填填評論內容吧" name="repContain" id="evacon"></textarea>
			</div>
			      <div class="modal-footer">
			        <button type="button" class="btn btn-secondary" data-dismiss="modal" id="closeme">Close</button> 
	        	<input type="hidden" id="fosNo2" name="fosNo" value="">
	        	<input type="hidden" id="fosmNo2" name="fosmNo" value="">
	        	<button type="submit" class="btn btn-primary" id="sendeva">送出評論</button>     
	      		</div>
	    </div>
	</div>
</div>
<script>
	$(document).ready(function(){
		let data = {};
		$('.rating label').click(function(){
			data.star = $(this).attr('for');
			$('.rating input').prop('checked', false);
			$('.rating input[value="'+$(this).attr('for')+'"]').prop('checked', true);
		});
		$("#sendeva").click(function(){
			data.action = "addEva";
			data.fosmNo = $("#fosmNo2").val();
			data.fosNo = $("#fosNo2").val();
			data.fosmEvacon = $("#evacon").val();
			
			$.ajax({
				url: "fos.do",
				type:"POST",
				data: data,
				success:function(){
					$('#closeme').click();
					swal('評價成功！', '潮爽的!', 'success');
					$('#'+data.fosNo+' .addeva .site-btn').hide();
				}
			});
		});
	});

</script>

	
	
	
	
	
	