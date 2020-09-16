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
	<h1>訂單問題區</h1>
	<h6 id="statusOutput" class="statusOutput"></h6>
	<textarea id="messagesArea" class="panel message-area" readonly style="width: 200px; height: 300px;background-image:linear-gradient(to top, #fff1eb 0%, #ace0f9 100%);"></textarea>
	<div class="panel input-area" style="width: 200px;">
		<input id="userName" class="text-field" type="text" placeholder="請輸入訂單編號" style="width: 164px; background-image: linear-gradient(-225deg, #E3FDF5 0%, #FFE6FA 100%);" /> 
		<input id="message" class="text-field" type="text" placeholder="請輸入回覆" onkeydown="if (event.keyCode == 13) sendMessage();" style="width: 164px; background-image: linear-gradient(-225deg, #E3FDF5 0%, #FFE6FA 100%);"/> 
		<input type="submit" id="sendMessage" class="button" value="發送" onclick="sendMessage();" style="background-image: linear-gradient(-225deg, #7DE2FC 0%, #B9B6E5 100%);"/> 
	</div>
</body>

<script>
	var MyPointM = "/Shop/TogetherWS/james";
	var hostM = window.location.host;
	var pathM = window.location.pathname;
	var webCtxM = pathM.substring(0, pathM.indexOf('/', 1));
	var endPointURLM = "ws://" + window.location.host + webCtxM + MyPointM;

	var statusOutput = document.getElementById("statusOutput");
	var webSocketM;

	function connectM() {
		// create a websocket
		webSocketM = new WebSocket(endPointURLM);

		webSocketM.onopen = function(event) {

		};

		webSocketM.onmessage = function(event) {
			var messagesArea = document.getElementById("messagesArea");
			var jsonObj = JSON.parse(event.data);
			var message = jsonObj.userName + ": " + jsonObj.message + "\r\n";
			messagesArea.value = messagesArea.value + message;
			messagesArea.scrollTop = messagesArea.scrollHeight;
		};

		webSocketM.onclose = function(event) {
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
			
			webSocketM.send(JSON.stringify(jsonObj));
			inputMessage.value = "";
			inputMessage.focus();
		}
	}

</script>
</html>
