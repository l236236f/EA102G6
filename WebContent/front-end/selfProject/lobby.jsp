<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<title>Insert title here</title>
<%
     response.setHeader("Pragma","No-cache"); 
     response.setHeader("Cache-Control","no-cache"); 
     response.setDateHeader("Expires", 0); 
     response.flushBuffer();
 %>
<style>
	#build{
		margin-left:30px;
		border-radius: 8px;
	}
	#allRoom{
		width:100%;
		height:600px;
	}
	.Rooms{
		margin:20px;
		width:200px;
		height:80px;
		text-align:center;
	}
	.peoplein{
		background:red;
		pointer-events: none;
	}
	
</style>
</head>
<body>
<%@ include file="header.jsp" %>
	<section class="blog-section spad">
		<div class="container">
			<div class="row">
				<input type="text" class="SerchRoom" placeholder="搜尋房間吧!">
				<button id="build"  class="site-btn margin-right-small" data-toggle="modal" data-target="#exampleModalLong">建立房間</button>
			</div>
			<div class="row" id="allRoom">
			</div>
		</div>
	</section>
	
	<div class="modal fade" id="exampleModalLong" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLongTitle">建立房間吧!</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	        	<span>房間名稱　：　</span><input type="text" class="roomNames" id="roomName" >
	        	<span>房間人數　：　</span><select class="count" id="count">
	        			<option value="10">10</option>
	        			<option value="15">15</option>
	        			<option value="20">20</option>
	        	</select>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" id="buildClose" data-dismiss="modal">Close</button>
	        <div>
	        	<button type="submit" class="btn btn-primary" id="buildRoom">建立房間</button>
	        </div>
	      </div>
	    </div>
	  </div>
	</div>

 

<%@ include file="footer.jsp" %>
	<script>
		
		$(document).ready(function(){
			var webSocket;
			var self = '${username}';
			var path = window.location.pathname;
			var MyPoint = "/JoinWS/${username}/${param.action}";
			var host = window.location.host;
			var webCtx = path.substring(0, path.indexOf('/', 1));
			var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
			
			var status = '${param.action}';
			var roomNo = '${param.roomNo}';
			
			function connect(endPointURL) {
				// create a websocket
				webSocket = new WebSocket(endPointURL);
		
				webSocket.onopen = function(event) {
					console.log("Connect Success!");
					if(status == 'returnLobby'){
						var jsonObj = {
								"type":"leaveRoom",
								"roomNo":roomNo
						}
						webSocket.send(JSON.stringify(jsonObj));
					}
				}
				
				webSocket.onmessage = function(event) {
					var jsonObj = JSON.parse(event.data);
					$("#allRoom").empty();
					for(var i = 0 ; i < jsonObj.length ; i++){
						if(jsonObj[i].count == jsonObj[i].maxCount){
							var roomCont = '<div class="Rooms peoplein" style="background-color:red" id="'+jsonObj[i].roomNo+'" data-room_name="'+jsonObj[i].roomName+'" data-max_count="'+jsonObj[i].count+'" data-room_no="'+jsonObj[i].roomNo+'" style="background-color:orange"><span class="roomName">'+jsonObj[i].roomName+'</span><span class="peopleCount">('+jsonObj[i].count+'/'+jsonObj[i].maxCount+')</span></div>';
						}else{
							var roomCont = '<div class="Rooms" id="'+jsonObj[i].roomNo+'" data-room_name="'+jsonObj[i].roomName+'" data-max_count="'+jsonObj[i].count+'" data-room_no="'+jsonObj[i].roomNo+'" style="background-color:orange"><span class="roomName">'+jsonObj[i].roomName+'</span><span class="peopleCount">('+jsonObj[i].count+'/'+jsonObj[i].maxCount+')</span></div>';
						}
						$("#allRoom").append(roomCont);
					}
				};
				webSocket.onclose = function(event) {
					
				};
			}
			connect(endPointURL);
						
			$("#buildRoom").click(function(){
				var roomName = $("#roomName").val();
				if(roomName.length===0||roomName.length>8){
					swal("創建失敗","房間名稱請介於1-8字以內!","error");
					return;
				}
				var length = $(".roomName").length;
				for(var i = 0 ; i < length ; i++){
					var names = $(".roomName").eq(i).val();
					if(names===roomName){
						swal("創建失敗","房間名稱重複!","error");
						return;
					}
				}
				var maxCount = $("#count").val();
				var roomNo = randomString(6);
				
				$("#buildClose").click();
				var jsonObj = {
						"type":"inroom",
						"roomNo":roomNo,
						"roomName":roomName,
						"maxCount":maxCount
				}
				webSocket.send(JSON.stringify(jsonObj));
				window.location.href="Drow.do?roomNo="+roomNo+"&username="+self+"&maxCount="+maxCount+"&action=inRoom";
				
			});
			
			$("#allRoom").on('click',".Rooms",function(){
				var roomNo=$(this).data("room_no");
				var roomName=$(this).data("room_name");
				var maxCount=$(this).data("max_count");
				var jsonObj = {
						"type":"inroom",
						"roomNo":roomNo,
						"roomName":roomName,
						"maxCount":maxCount
				}
				webSocket.send(JSON.stringify(jsonObj));
				
				window.location.href="Drow.do?roomNo="+roomNo+"&username="+self+"&maxCount="+maxCount+"&action=inRoom";
			})
			
			function randomString(len) {
				　　len = len || 32;
				　　var $chars = 'ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678';    /****預設去掉了容易混淆的字元oOLl,9gq,Vv,Uu,I1****/
				　　var maxPos = $chars.length;
				　　var pwd = '';
				　　for (i = 0; i < len; i++) {
				　　　　pwd += $chars.charAt(Math.floor(Math.random() * maxPos));
				　　}
				　　return pwd;
				}
		});	
	</script>



</body>
</html>