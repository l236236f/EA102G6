<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<style type="text/css">
			#demo {
				border: 3px solid brown;
			}
			.panel .region {
				display: inline-block;
				margin: 5px;
			}
			canvas{
               background-color:black; 
               }
		</style>
<div class="modal fade" id="exampleModalLong" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLongTitle">�X���Ѥ��e</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	      		1.�ȤW�o�Ӳ�ı�L�Aı�����ƥ��`��<br>
	      		2.����W�j�����H�B�k�䥨�H����<br>
	        	1.�b���X�����Ĵ������A�A�����ɵ��}�޲z�H�`�N�q�ȡA���ߥ��O�P�O�P�Ҥ��P�X�@�C<br>
	        	2.�A��b�P�Ҥ�X�@�����A���R�����x�ÿ�u�A�Τ������k�O<br>
	        	3.Object��11�Ӥ�k:clone�Bequals�Bfinalize�BgetClass�BhashCode�Bnotify�BnotifyAll�BtoString�B3��wait<br>
	        	4.�O�o�h���j�î��A�����M�g<br>
	        	5.EA102�Z��:00dog �B�Z�s:YEEEEEEEE<br>
	        	6.�b���X�����Ĵ������A�A�����R���A�Ѩÿ�u�Ҥ�ҹ{�����@���F���B��k�γW���A�ӵ��F���B��k�γW���A�]�A��������Ҥ褧�u�@�W�h�B�T��u�@���ҩ����Z�n���B�欰�W�d�B�M�G�F����<br>
	        	7.�A����ܦP�N�Ҥ�o����~�ȤΨ�L�X�k�ت��`���B�ϥΡB�q���B�z�ΰꤺ�~�ǿ�A�褧�ӤH��ơC<br>
	        	8.���X���o�H�ɦ]����X�N�Ӳפ�C<br>
	        	9.�Ҥ�o�]�A�譫�j�H�ϥ��X�����کΥҤ�ҹ{�����u�@�W�h�B��k�W���A�@���F���A�]�A�������󥻦X���ĤT���ĤT���ҥܪ̡F�ΤA���X�@�W�����j�L���ӥߧY�פ�X���C<br>
	        	10.���X�������ߡB�����P�������H���إ���k�߬��Ǿڪk�C����]���X���Ӱ_�λP�䦳������ĳ�A�����H�O�W�n��a��k�|���Ĥ@�f���Ҫk�|�C<br>
	        	11.�ŧi�B���ȡB���ӥ�<br>
	        	<div class="col-10" id="canv">
		        	��ñ�U�z���j�W:
		        	<div class="panel">
						<div class="region">
							<button id="pen">�e��</button>
							<button id="eraser">�����</button>
						</div>
					</div>
		        	<canvas id="demo" width="500" height="300">
						�z���s�������䴩Canvas�A�Фɯűz���s�����Ψϥ�Chrome�C  
					</canvas>
				</div>
				<div class="col-6">�}�Dñ�W:
					<div class="col-12" id="Asign"></div>
				</div>
				<div class="col-6">�O��ñ�W:
					<div class="col-12" id="Bsign"></div>
				</div>
	        	
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" id="signClose" data-dismiss="modal">Close</button>
	        <div>
	        	<input type="hidden" name="signjpg" id="signjpg" value="">
	        	<input type="hidden" id="AandB" name="AandB" value="">
	        	<button type="submit" class="btn btn-primary" id="sendsign">�e�X</button>
	        	<input type="hidden" id="fosNo" name="fosNo" value="">
	        	
	        </div>
	      </div>
	    </div>
	  </div>
	</div>
	<script type="text/javascript">	
	function init(){

		var lastX = 0, lastY = 0; 
		var isDrawing = false; 
		var demo = document.getElementById("demo");
		
		var eraser = document.getElementById("eraser");
		var pen = document.getElementById("pen");
		
		var ctx = demo.getContext('2d');
		ctx.lineWidth = 5;
		ctx.strokeStyle = 'orange';
		ctx.lineCap = 'round'; 
		function draw(startX, startY, endX, endY){
			ctx.beginPath();
			ctx.moveTo(startX,startY);
			ctx.lineTo(endX,endY);
			ctx.stroke();
		}
		//�ƹ��ƥ�--------------------------------------
		demo.addEventListener('mousedown',function(e){
			lastX = e.offsetX , lastY = e.offsetY;
			isDrawing = true;
		});
		demo.addEventListener('mousemove',function(e){
			 if(!isDrawing) return;
			var startX = lastX;
			var startY = lastY;
			var endX = e.offsetX;
			var endY = e.offsetY;
			draw(startX,startY,endX,endY);
			lastX = endX;
			lastY = endY;
		});
		demo.addEventListener('mouseup',function(e){
			isDrawing = false;
		});
		//--------------------------------------------
// 		//Ĳ���ƥ�--------------------------------------
// 		demo.addEventListener("touchstart", function(e){
// 			lastX = e.clientX , lastY = e.clientY;
// 			isDrawing = true;
// 		});
// 		demo.addEventListener("touchmove", function(e){
// 			 if(!isDrawing) return;
// 				var startX = lastX;
// 				var startY = lastY;
// 				var endX = e.clientX;
// 				var endY = e.clientY;
// 				draw(startX,startY,endX,endY);
// 				lastX = endX;
// 				lastY = endY;
// 		});
// 		demo.addEventListener("touchend", function(e){
//   			isDrawing = false;
//   		});
// 		//--------------------------------------------
		pen.addEventListener('click', function(){
			ctx.strokeStyle = 'orange';
			ctx.lineWidth = 5;
		});
		eraser.addEventListener('click', function(){
			ctx.strokeStyle = 'black';
			ctx.lineWidth = 50;
		});
		$("#sendsign").click(function(){
			let data={};
			//var canvas = document.getElementById('demo');
			var dataURL=demo.toDataURL('image/jpeg');
// 			$("#signjpg").val(dataURL);
			data.signjpg = dataURL;
			data.action = 'sign';
			data.AandB = $(this).prev().val();
			data.fosNo = $(this).next().val();
			let a = $(this).next().val();
			let ab = data.AandB;
			
			$.ajax({
				url:"fos.do",
				type:"POST",
				data:data,
				success:function(data){
					swal('ñ�W���\�I', '�p�p�p!', 'success');
					$("#signClose").click();
					$("#"+a+"needsign").val('0');
					$("#"+a+"signimg").html('<img src="<%= request.getContextPath() %>/front-end/ShowPhotos?type=fossign&AB='+ab+'&photo_no='+a+'">');	
				}			
			});	
		});
	}
	</script>