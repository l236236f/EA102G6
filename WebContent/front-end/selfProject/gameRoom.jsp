<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>Insert title here</title>
</head>
<style>
	.gameRoom{
		width:100%;
		height:800px;
		background:orange;
		position:relative;
	}
	#gSelf{
		position: absolute;
		bottom:5%;
		left:15%;
		width:31%;
		height:28%;
		background:white;	
	}
	#gSelfR{
		position: absolute;
		bottom:5%;
		left:47%;
		width:31%;
		height:28%;
		background:white;	
	}
		
	#canv{
		position:absolute;
		right:2%;
		top:2%;
		width:65%;
		height:60%;
		background:white;
	}
	#rating{
		position:absolute;
		left:5%;
		top:5%;
		width:20%;
		height:40%;
		overflow: auto;
		background:white;
	}
	#ques{
		position:absolute;
		left:5%;
		top:48%;
		width:20%;
		height:10%;
		background:white;
		text-align:center;
		font-size: 25px;
	}
	
	.myText{
		width:100%;
		border:1px solid;
		bottom: 0px;
    	position: absolute;
    	right: 0;
		
	}
	#demo{
 		width:100%; 
 		height:92%; 
		margin-top:0;
		border: 3px solid brown;
	}
	.panel .region {
		display: inline-block;
		margin: 5px;
	}
	.message-area {
		height: 76%;
		margin-top: 6%;
		width:100%;
		resize: none;
		box-sizing: border-box;
		overflow: auto;
	}
	.answer-area {
		height: 76%;
		margin-top: 6%;
		width:100%;
		box-sizing: border-box;
		overflow: auto;
	}
	#gameStr{
		position:absolute;
		bottom:10%;
		right:10%;
		width:90px;
		height:57px;
	}
	#leaveRoom{
		position:absolute;
		bottom:5%;
		left:5%;
		width:100px;
		height:57px;
	}
	.rating{
		width:100%;
		text-align:center;
		font-size:30px;
		border-bottom: 1px solid;
	}
	.point{
		color: red;
		font-weight: 600;
	}
	.tital{
		position:absolute;
		width:100%;
		height:10%;
		top:0%;
		background-color:gray;
		text-align:center;
	}
	

</style>
<body>
<%@ include file="header.jsp" %> 
	<section class="blog-section spad">
		<div class="container">
			<div class="gameRoom">
				<div id="canv">
					<div class="panel">
					<div class="region">
						<button id="pen">�e��</button>
						<button id="eraser">�����</button>
					</div>
					<div class="region">
						<span>�e���ʲ� (��� px)</span>
						<input type="range" id="size" min="5" max="100" step="5" value="5">
					</div>
					<div class="region">
						<span>�e���C��</span>
						<input type="color" name="" id="color" value="#000">
					</div>
					</div>
					<canvas id="demo"  width="735" height="435">
						�z���s�������䴩Canvas�A�Фɯűz���s�����Ψϥ�Chrome�C  
					</canvas>
				</div>
				<div id="rating">
					
				</div>
				<div id="ques"></div>
			
				<button id="gameStr">�}�l�C��</button>
				<button id="leaveRoom">���}�ж�</button>
				<div id="gSelf">
					<div class="tital">���װ�</div>
					<div id="messagesArea" class="panel answer-area"></div>
					<input type="text" class="myText" id="myText">
				</div>
				<div id="gSelfR">
					<div class="tital">��Ѱ�</div>
					<textarea id="chatArea" class="panel message-area" readonly></textarea>
					<input type="text" class="myText" id="myChat">
				</div>
			</div>
		</div>
	</section>

<%@ include file="footer.jsp" %>
<script>
		
		$(document).ready(function(){
			//-----------------Canvas�϶�----------------------------------------
			var lastX = 0, lastY = 0; // �ƹ��̫��I������m
			var isDrawing = false; // �O�_���b�e��?
			var isStartNow = 'no'; //�C���O�_�i�椤(�M�w�֯�e�e)
			var drowXY = [];     //�e�Ϯy��
			var drowData = {};   //�e�ϸ�T

			var demo = document.getElementById("demo");
			var size = document.getElementById("size");
			var eraser = document.getElementById("eraser");
			var pen = document.getElementById("pen");
			var color = document.getElementById("color");

			var ctx = demo.getContext('2d');

			ctx.lineWidth = parseInt(size.value);
			ctx.strokeStyle = color.value;
			ctx.lineCap = 'round'; //round���꩷�C

			function draw(startX, startY, endX, endY){
				ctx.beginPath();
				ctx.moveTo(startX,startY);
				ctx.lineTo(endX,endY);
				ctx.stroke();

			}
			
			demo.addEventListener('mousedown',function(e){
				if(isStartNow === 'yes'){
					lastX = e.offsetX , lastY = e.offsetY;
					isDrawing = true;
					//�]�w�Ƕi�h�����C���SIZE
					drowData.penSize = 	size.value;
				}
			});

			demo.addEventListener('mousemove',function(e){
				if(isStartNow === 'yes'){
					if(!isDrawing) return;
					var startX = lastX;
					var startY = lastY;
					var endX = e.offsetX;
					var endY = e.offsetY;
					drowXY.push({x:endX,y:endY});
					draw(startX,startY,endX,endY);
					lastX = endX;
					lastY = endY;
					//�s�W�L100�Ӯy���I�A�N�T���ǥX�ñN�}�C�M��
					if(drowXY.length === 100){
						drowData.drowMap = drowXY;
						console.log(drowData);
						console.log(JSON.stringify(drowData))
						webSocket.send(JSON.stringify(drowData));
						drowXY = [];
					}
				}
			});
				
			demo.addEventListener('mouseup',function(e){	
				isDrawing = false;
				if(isStartNow === 'yes'){
					if(drowXY.length < 100){
						drowData.drowMap = drowXY;
						webSocket.send(JSON.stringify(drowData));
						drowXY = [];
					}
				}
			});

			size.addEventListener('change', function(){
				ctx.lineWidth = parseInt(size.value);
			});

			color.addEventListener('change', function(){
				ctx.strokeStyle = color.value;
				drowData.penColor = ctx.strokeStyle;
			});

			pen.addEventListener('click', function(){
				ctx.strokeStyle = color.value;
			});

			eraser.addEventListener('click', function(){
				ctx.strokeStyle = '#ffffff';
				drowData.penColor = ctx.strokeStyle;
			});

		//----------------------------------------------------------------
		//-------------WebSocket�϶�---------------------------------------
			var webSocket;
			var username = '${param.username}';
			var roomNo = '${param.roomNo}';
			var maxCount = '${param.maxCount}';
			
			var MyPoint = "/DrowWS/"+roomNo+"/"+username+"/"+maxCount;
			var host = window.location.host;
			var path = window.location.pathname;
			var webCtx = path.substring(0, path.indexOf('/', 1));
			var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
			
			//��r������
			var messagesArea = document.getElementById("messagesArea");
			//��Ѥ�����
			var chatArea = document.getElementById("chatArea");
			//�ǳƫ��s�w�]���áA4�H���ɤ~�|���
			$("#gameStr").hide();
			$("#myText").prop("disabled",true);
			
			function connect(endPointURL) {
				// create a websocket
				webSocket = new WebSocket(endPointURL);
		
				webSocket.onopen = function(event) {
					console.log("Connect Success!");
				};
		
				webSocket.onmessage = function(event) {
					//���o�ǨӸ��
					var jsonObj = JSON.parse(event.data);
					//�C���_�l����A�H�躡4�H
					if(jsonObj.type === 'GameStart'){
						swal("�j�î��A�q��!",jsonObj.message,"success");
						
						
					}
					//�t�γq��
					if(jsonObj.type === "GameNotice"){
						var message = '<div style="color:red">�j�î��A :�@' + jsonObj.message + '</div>';
						$("#messagesArea").append(message);
						$('#messagesArea').animate({scrollTop:$('#messagesArea div:last-child').offset().top},1000);
					}
					//�ХD�q��(�i�H���}�l��)
					if(jsonObj.type === "roomHost"){
						$("#gameStr").show();	
					}
					//����C���}�l�T���A�Ȱ��e���\��A���}��J���ץ\��
					if(jsonObj.type === "GameAction"){
						
						$('#messagesArea').animate({scrollTop:$('#messagesArea div:last-child').offset().top},1000);
						$("#ques").text("");
						//�M�ŵe��
						clearCanvas();
						
						//��L�H�|��e�e�\���������,�N�^���a��}��
						isStartNow = 'no';
						$("#pen").prop("disabled",true);
						$("#eraser").prop("disabled",true);
						$("#color").prop("disabled",true);
						$("#myText").prop("disabled",false);
								
					}
					//�����D��
					if(jsonObj.type === 'GameQusetion'){
						var ques = jsonObj.message;
						//�N�e���ƥ󥴶}
						isStartNow = 'yes';
						$("#ques").text("�D�ء@:�@"+ques);
						//drowData�̦s�񵥵��n�Ǩ��L�˸m���T��
						drowData.type = "drowNow";
						drowData.sender = username;
						drowData.roomNo = roomNo;
						$("#myText").prop("disabled",true);
						$("#pen").prop("disabled",false);
						$("#eraser").prop("disabled",false);
						$("#color").prop("disabled",false);
					}
					//�^�����T�ᰱ���欰
					if(jsonObj.type === 'StopSend'){
						var cold = window.setTimeout(function(){
							$("#myText").prop("disabled",true);
	        			},550);
					}
					//������L�˸m�ǨӪ��y�аT���A����ܦb�۹�����
					if(jsonObj.type === 'drowNow'){
						var drowMap = jsonObj.drowMap;
						ctx.lineWidth = parseInt(jsonObj.penSize);
						ctx.strokeStyle = jsonObj.penColor;
						isDrawing = true;		
						for(var i=0 ; i<drowMap.length-1 ; i++){
							draw(drowMap[i].x,drowMap[i].y,drowMap[i+1].x,drowMap[i+1].y);
						}
						isDrawing = false;
					}
					if(jsonObj.type === 'getPoint'){
						var pointMap = jsonObj.pointMap;
						var everyOne = Object.keys(pointMap);
						$("#rating").empty();
						for(var i = 0 ; i < everyOne.length ; i++){
							if(everyOne[i]===username){
								$("#rating").append('<div class="rating" style="background-color:pink">'+everyOne[i]+' : <span style="color:red">'+pointMap[everyOne[i]]+'</span>��</div>');
							}else{
								$("#rating").append('<div class="rating">'+everyOne[i]+' : <span style="color:red">'+pointMap[everyOne[i]]+'</span>��</div>');
							}
						}
					}
					if(jsonObj.type === 'gameOver'){
						//�q�X����
						$("#ques").text("���ס@:�@"+jsonObj.message);
						$("#myText").prop("disabled",true);
						isDrawing = false;
						var message = '<div style="color:red">�j�î��A : ���ݤU�@�^�X----------</div>';
						$("#messagesArea").append(message);
						$('#messagesArea').animate({scrollTop:$('#messagesArea div:last-child').offset().top},1000);
						//���s�e��
						clearCanvas();
						$("#pen").prop("disabled",true);
						$("#eraser").prop("disabled",true);
						$("#color").prop("disabled",true);
					
					}
					if(jsonObj.type === 'sendAns'){
						var message =  "<div>"+jsonObj.sender+" : "+jsonObj.message + "</div>";
						$("#messagesArea").append(message);
						$('#messagesArea').animate({scrollTop:$('#messagesArea div:last-child').offset().top},1000);
						
					}
					if(jsonObj.type === 'sendChat'){						
						var message =  jsonObj.sender+" : "+jsonObj.message + "\r\n";
						chatArea.value = chatArea.value + message;
						chatArea.scrollTop = chatArea.scrollHeight;
						
					}
					if(jsonObj.type === 'reStart'){						
						var jsonObj={
								"type" : "gameStart",
								"message" : "OK",
								"sender" : username,
								"roomNo" : roomNo
							}
						webSocket.send(JSON.stringify(jsonObj));
						
					}
				}

				webSocket.onclose = function(event) {

				};
			}
			connect(endPointURL);
			
			
			//�}�l���s�ƥ�------------------------
			$("#gameStr").click(function(){
					var jsonObj={
						"type" : "gameStart",
						"message" : "OK",
						"sender" : username,
						"roomNo" : roomNo
					}
					webSocket.send(JSON.stringify(jsonObj));
					$(this).hide();
					
			});
			//---------------------------------
			//��J���רƥ�------------------------
			$("#myText").keypress(function(e){
				var textp = $(this);
				if(event.keyCode==13){
					let message = $(this).val();				
					if(message.trim().length == 0){
           				swal("�Ф��n��J�ť�!","","error");
           			}else{
           				var jsonObj = {
           						"type": "sendAns",
           						"roomNo": roomNo,
        						"sender" : username,
        						"message" : message
        					};
        					webSocket.send(JSON.stringify(jsonObj));
        					$(this).val("");
        					$(this).prop("disabled",true);
	        			var cold = window.setTimeout(function(){
	        				textp.prop("disabled",false);
	        				textp.focus();
	        			},500);
           			}
				}
			});
			//---------------------------------
			//��J��Ѩƥ�------------------------
			$("#myChat").keypress(function(e){
				var textp = $(this);
				if(event.keyCode==13){
					let message = $(this).val();				
					if(message.trim().length == 0){
           				swal("�Ф��n��J�ť�!","","error");
           			}else{
           				var jsonObj = {
           						"type": "sendChat",
           						"roomNo": roomNo,
        						"sender" : username,
        						"message" : message
        					};
        					webSocket.send(JSON.stringify(jsonObj));
        					$(this).val("");
        					$(this).prop("disabled",true);
	        			var cold = window.setTimeout(function(){
	        				textp.prop("disabled",false);
	        				textp.focus();
	        			},200);
           			}
				}
			});
			//���}�ж��ƥ�--------------------------
			$("#leaveRoom").click(function(){
				var jsonObj = {
   						"type": "leaveRoom",
   						"roomNo": roomNo,
						"sender" : username
					};
				webSocket.send(JSON.stringify(jsonObj));
				window.location.href="Drow.do?roomNo="+roomNo+"&username="+username+"&action=returnLobby";
		
			});
			
			//Canvas�M�ŵe��---------------------
			function clearCanvas()  
			{  
			    ctx.clearRect(0,0,demo.width,demo.height);  
			}  
			//---------------------------------
		});
				
	</script>	
	
</body>
</html>