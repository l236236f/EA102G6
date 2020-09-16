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
	        <h5 class="modal-title" id="exampleModalLongTitle">合約書內容</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	      		1.紙上得來終覺淺，覺知此事必躬行<br>
	      		2.左邊超大型巨人、右邊巨人之恥<br>
	        	1.在本合約有效期間內，乙方應盡善良管理人注意義務，全心全力致力與甲方協同合作。<br>
	        	2.乙方在與甲方合作期間，應充份知悉並遵守適用之相關法令<br>
	        	3.Object的11個方法:clone、equals、finalize、getClass、hashCode、notify、notifyAll、toString、3個wait<br>
	        	4.記得去按大衛海鮮的粉專讚<br>
	        	5.EA102班犬:00dog 、班龍:YEEEEEEEE<br>
	        	6.在本合約有效期間內，乙方應充份瞭解並遵守甲方所頒布之一切政策、辦法及規章，該等政策、辦法及規章，包括但不限於甲方之工作規則、禁止工作場所性騷擾聲明、行為規範、清廉政策等<br>
	        	7.乙方明示同意甲方得為其業務及其他合法目的蒐集、使用、電腦處理及國內外傳輸乙方之個人資料。<br>
	        	8.本合約得隨時因雙方合意而終止。<br>
	        	9.甲方得因乙方重大違反本合約條款或甲方所頒布之工作規則、辦法規章，一切政策，包括但不限於本合約第三條第三項所示者；或乙方於合作上有重大過失而立即終止本合約。<br>
	        	10.本合約之成立、解釋與執行應以中華民國法律為準據法。任何因本合約而起或與其有關之爭議，皆應以臺灣南投地方法院為第一審管轄法院。<br>
	        	11.宣告、取值、拿來用<br>
	        	<div class="col-10" id="canv">
		        	請簽下您的大名:
		        	<div class="panel">
						<div class="region">
							<button id="pen">畫筆</button>
							<button id="eraser">橡皮擦</button>
						</div>
					</div>
		        	<canvas id="demo" width="500" height="300">
						您的瀏覽器不支援Canvas，請升級您的瀏覽器或使用Chrome。  
					</canvas>
				</div>
				<div class="col-6">飼主簽名:
					<div class="col-12" id="Asign"></div>
				</div>
				<div class="col-6">保母簽名:
					<div class="col-12" id="Bsign"></div>
				</div>
	        	
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" id="signClose" data-dismiss="modal">Close</button>
	        <div>
	        	<input type="hidden" name="signjpg" id="signjpg" value="">
	        	<input type="hidden" id="AandB" name="AandB" value="">
	        	<button type="submit" class="btn btn-primary" id="sendsign">送出</button>
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
		//滑鼠事件--------------------------------------
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
// 		//觸控事件--------------------------------------
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
					swal('簽名成功！', '喵喵喵!', 'success');
					$("#signClose").click();
					$("#"+a+"needsign").val('0');
					$("#"+a+"signimg").html('<img src="<%= request.getContextPath() %>/front-end/ShowPhotos?type=fossign&AB='+ab+'&photo_no='+a+'">');	
				}			
			});	
		});
	}
	</script>