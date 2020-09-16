<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>

<!DOCTYPE html>
<html>
<head>
<title>Chat Room</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet" href="css/styles.css" type="text/css" />
</head>

<body>
	<div class="container"><h2>會員訂單詢問區</h2></div>
	<div class="container"><h6 id="statusOutput" class="statusOutput"></h6></div>
	<textarea id="messagesArea" class="panel message-area" readonly style="width: 300px; height: 300px; background-image: linear-gradient(120deg, #a1c4fd 0%, #c2e9fb 100%);"></textarea>
	<div class="panel input-area" style="width: 300px;">
		<input type="hidden" id="userName" class="text-field" type="text" value = "廠商" /> 
		<input id="message" class="text-field" type="text" placeholder="請輸入回覆" onkeydown="if (event.keyCode == 13) sendMessage();" style="width: 240px;"/> 
		<input type="submit" id="sendMessage" class="button" value="發送" onclick="sendMessage();" style="background-image: linear-gradient(120deg, #a1c4fd 0%, #c2e9fb 100%);"/> 
<!-- 		<input type="button" id="connect" class="button" value="Connect" onclick="connect();" />  -->
<!-- 		<input type="button" id="disconnect" class="button" value="Disconnect" onclick="disconnect();" /> -->
	</div>
</body>

<script>
	var MyPointV = "/Shop/TogetherWS/james";
	var hostV = window.location.host;
	var pathV = window.location.pathname;
	var webCtxV = pathV.substring(0, pathV.indexOf('/', 1));
	var endPointURLV = "ws://" + window.location.host + webCtxV + MyPointV;

	var statusOutput = document.getElementById("statusOutput");
	var webSocketV;

	function connectV() {
		// create a websocket
		webSocketV = new WebSocket(endPointURLV);

		webSocketV.onopen = function(event) {
			console.log("Connect Success!");
		};

		webSocketV.onmessage = function(event) {
			var messagesArea = document.getElementById("messagesArea");
			var jsonObj = JSON.parse(event.data);
			var message = jsonObj.userName + ": " + jsonObj.message + "\r\n";
			messagesArea.value = messagesArea.value + message;
			messagesArea.scrollTop = messagesArea.scrollHeight;
		};

		webSocketV.onclose = function(event) {
			updateStatus("WebSocket Disconnected");
		};
	}

	var inputUserName = document.getElementById("userName");
	inputUserName.focus();

	function sendMessage() {
		var userName = inputUserName.value.trim();
		if (userName === "") {
			alert("Input a user name");
			inputUserName.focus();
			return;
		}

		var inputMessage = document.getElementById("message");
		var message = inputMessage.value.trim();

		if (message === "") {                                                               
			alert("Input a message");
			inputMessage.focus();
		} else {
			var jsonObj = {
				"userName" : userName,
				"message" : message
			};
			webSocketV.send(JSON.stringify(jsonObj));
			inputMessage.value = "";
			inputMessage.focus();
		}
	}

	$(function() {
		//廠商socket
		connectV();
		//聊天室socket
		ChatConnect();
		
	});// $f
	
</script>
</html>
