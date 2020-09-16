<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="EMPLOYEE.model.*"%>

<%
	EmpVO empVO1 = (EmpVO) session.getAttribute("EmpVO");
%>

<!DOCTYPE html>
<html>
<head>
<title>Examples</title>
<link
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css"
	rel="stylesheet">
<link href="<%= request.getContextPath() %>/chatRoom/lib/css/nanoscroller.css" rel="stylesheet">
<link href="<%= request.getContextPath() %>/chatRoom/lib/css/emoji.css" rel="stylesheet">
<link href="<%= request.getContextPath() %>/chatRoom/lib/css/chatRoom.css" rel="stylesheet">

</head>


<body>
	<input type='text' id='no' value='E001' >
	<input type="button" id="connect" class="button" value="Connect"
		onclick='ChatConnect();' />
	<div id="chatrbutton" class="chatrbutton">
		<div class='message'></div>
		<div class="chatrbutton" name='chatrbutton'>
			<i class="far fa-comment-dots"></i>
		</div>
	</div>

	<div class="box" id="odiv" style='height: 500px;'>
		<div name="inner" class='inner' style="bottom: 60px;"></div>
		<span class="r"></span> <span class="l"></span> <span class="t"></span>
		<span class="b"></span> <span class="br"></span> <span class="bl"></span>
		<span class="tr"></span> <span class="tl"></span>
		<div class='leftlColumn' name='leftlColumn'>
			<div name='leftlColumn' class='clientBox'></div>
			<div class='search' name='leftlColumn'>
				<div name='leftlColumn' class='icon'>
					<i class="fas fa-search"></i>
				</div>
				<div name='leftlColumn' class='client'>客服</div>
				<form name='leftlColumn' onsubmit="return false;">
					<input type="" class="searchinput" name='leftlColumn'
						autocomplete="off">
				</form>

			</div>
			<div name='leftlColumn' class='leftList'></div>


		</div>

		<div name="header">
			<div id="leftbuttonout" name='leftbuttonout' class='leftbutton'>
				<div name="leftbutton" class='leftbutton'><i class="fas fa-bars"></i></div>
			</div>
			<div class="title">
				<div class='photo'></div>

				<p></p>
			</div>
			<div class="close" name='close'>
				<div name='close'></div>
			</div>
		</div>
		<div name="footer" class='footer' style="height: 60px">

			<a class="t"></a>
			<div name="uploadfile">
				<input title='' type="file" class="upfilebutton" accept="image/*">
				<a class='iconClick'><i class="fas fa-plus hoverIcon"></i></a>
			</div>

			<div>
				<textarea name="textarea" class="textarea" data-emojiable="true"></textarea>
			</div>

			<div name="submit">
				<i class="fas fa-paper-plane hoverIcon"></i>
			</div>

		</div>
	</div>

</body>


<script src="https://kit.fontawesome.com/dd027c1f63.js"
	crossorigin="anonymous"></script>
<script src="<%= request.getContextPath() %>/chatRoom/js/jquery-2.1.1.min.js"></script>
<script src="<%= request.getContextPath() %>/chatRoom/lib/js/nanoscroller.min.js"></script>
<script src="<%= request.getContextPath() %>/chatRoom/lib/js/tether.min.js"></script>
<script src="<%= request.getContextPath() %>/chatRoom/lib/js/config.js"></script>
<script src="<%= request.getContextPath() %>/chatRoom/lib/js/util.js"></script>
<script src="<%= request.getContextPath() %>/chatRoom/lib/js/jquery.emojiarea.js"></script>
<script src="<%= request.getContextPath() %>/chatRoom/lib/js/emoji-picker.js"></script>
<script src='<%= request.getContextPath() %>/chatRoom/lib/js/chatRoomArea.js'></script>

<script>
	$(function() {

	});// $f

	function ChatConnect() {
		console.log('開始連線')
		var username = $("#no").val();
		var MyPoint = "/FriendWS/" + username;
		var host = window.location.host;
		var path = window.location.pathname;
		var webCtx = path.substring(0, path.indexOf('/', 1));
		var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
        console.log(endPointURL)
		var statusOutput = $("#odiv div[name=header] div.title>p");

		var messagesArea = $("#odiv div[name=inner]");
		var self = username;
		var webSocket;

		var AllNoToId = new Map();
		var CusSevList = new Array();
		var AllLastHistoryByUser =new Map();
		var AllUnReadByUser =new Map();
		webSocket = new WebSocket(endPointURL);
		
		console.log(webSocket.readyState)
		$("#odiv div[name=submit]").click(function() {
			sendMessage();
		});

		// create a websocket

		webSocket.onopen = function(event) {
		};

		webSocket.onmessage = function(event) {

			var jsonObj = JSON.parse(event.data);

			if ("open" === jsonObj.type) {

			} else if ("history" === jsonObj.type) {

				if (jsonObj.message.length > 0) {
					messagesArea.html("");

					var messages = JSON.parse(jsonObj.message);

					for (var i = 0; i < messages.length; i++) {

						var historyData = JSON.parse(messages[i]);

						var showMsg = htmlEscape(historyData.message);
						var time = formatDateTime(new Date(
								parseInt(historyData.messagetime)));
						var status = '';
						if (historyData.mesgStatus === 'beRead') {
							status = "已讀";
						} else if (historyData.mesgStatus === 'unRead') {
							status = "未讀";
						}
						var div = document.createElement('div');
						// 根據發送者是自己還是對方來給予不同的class名, 以達到訊息左右區分
						historyData.sender === self ? div.className += "messageblockSelf"
								: div.className += "messageblockOther";
						div.innerHTML = "<div name ='"+historyData.sender
				+"' class='userphoto'><img src='"+urlByStatus(historyData.sender)+"'></div>"
								+ "<div class='messagetime'><a>"
								+ status
								+ "</a><p>"
								+ time
								+ "</p></div><div class='messageTextArea'>"
								+ showMsg + "</div>";
						messagesArea.append(div);
					}
					var timer=setTimeout(function(){messagesArea.scrollTop(messagesArea.prop('scrollHeight'))} , 100);
					sendAction("getOneLastHistoryAndUnReadNum",self, jsonObj.sender);
				}
			} else if ("chat" === jsonObj.type) {

				sendAction("getOneLastHistoryAndUnReadNum",self, jsonObj.sender);
				
				if ($("#odiv div[name=header] div.title>p").attr('name') === jsonObj.sender) {
					var req = {
						"type" : "checkRead",
						"sender" : jsonObj.receiver,
						"receiver" : jsonObj.sender, // 讓RECEIVER 執行自己的HISTORY
						"message" : "",
						"messagetime" : "1",
						"mesgStatus" : ""
					};
					webSocket.send(JSON.stringify(req));
					
				}
				if (self === jsonObj.receiver ) {
					
					
					shake($('#chatrbutton'), 2500, 10, 5);
					if ($("#odiv div[name=header] div.title>p").attr("name") != jsonObj.sender) {
						shake($('#odiv'), 2500, 10, 5);
					}
					if ($("#odiv div[name=header] div.title>p").attr("name") === jsonObj.sender) {
						sendAction("history",self, jsonObj.sender);
						sendAction("getOneLastHistoryAndUnReadNum",self, jsonObj.sender);
					}
				}

				if ($("#odiv div[name=header] div.title>p").attr("name") === jsonObj.receiver
						&& self === jsonObj.sender && $("#odiv div[name=header] div.title>p").attr("name")!='Robot') {

					var div = document.createElement('div');
					div.className += "messageblockSelf";
					var time = formatDateTime(new Date(jsonObj.messagetime));
					div.innerHTML = "<div name ='"+jsonObj.sender
	 				+"' class='userphoto'><img src='"+urlByStatus(jsonObj.sender)+"'></div>"
							+ "<div class='messagetime'><a>"
							+ "</a><p>"
							+ time
							+ "</p></div><div class='messageTextArea'>"
							+ htmlEscape(jsonObj.message) + "</div>";
					messagesArea.append(div);
					messagesArea.scrollTop(messagesArea.prop('scrollHeight'));
					
					sendAction("history",self, jsonObj.receiver);
				}

			} else if ("AllLastMesgAndUnReadNum" === jsonObj.type) {
				
				AllLastHistoryByUser = JSON.parse(jsonObj.message);//Map<String, String> AllLastHistoryByUser
				AllUnReadByUser = JSON.parse(jsonObj.receiver);//AllUnReadByUser Map<String, String>
				AllNoToId = JSON.parse(jsonObj.sender);
				CusSevList = JSON.parse(jsonObj.messagetime);
			
				$("#odiv .leftList").html('');
				
				for ( var key in AllLastHistoryByUser) {
					
					var name = key.replace(":"+self,"");
					if (self === name ){continue;}
					var div = document.createElement('div');
					div.className = 'leftListButton';
					div.setAttribute("name", name);
					
					
					var img = urlByStatus(name);
					var number = AllUnReadByUser[key];
					var lastmessage = htmlEscape(JSON
							.parse(AllLastHistoryByUser[key]).message);
					var id=AllNoToId[name];
					if(name==="Robot"){
						id='推播小人';
					}
					var html = "<div class='userphoto'><img src='"
 	 			+img+"'></div><div name='"+name
 	 						+"' class='messageButtun'><h1>"
							+ id
							+ JudgeStatus(name)
							+ "</h1><p>"
							+ lastmessage
							+ "</p></div><div name='"
							+ name+"' class='messageNumber'>"
							+ number + "</div>";

					$(div).html(html);
					$("#odiv .leftList").append(div);

					$(div).click(
							function(e) {
								var rowName = $(e.target).parents(
								".leftListButton").attr("name");
								$("#odiv .title .photo").css("visibility",
										"visible");
								$("#odiv .title .photo").html("<img src='"+urlByStatus(rowName)+"'>");
								
								$("#odiv div[name=header] div.title>p").attr(
										"name", rowName);
								$("#odiv div[name=header] div.title>p").text(
										$(this).find(".messageButtun h1")
												.text());
								sendAction("history",username, rowName);
								
								var reqO = {
									"type" : "checkRead",
									"sender" : username,
									"receiver" : rowName,
									"message" : "",
									"messagetime" : "1",
									"mesgStatus" : ""
								};
								webSocket.send(JSON.stringify(reqO));

								sendAction("getOneLastHistoryAndUnReadNum",username, rowName);
								

							});
					calTotalUnRead();
				}

				// 			console.log(AllLastHistoryByUser)
				//  			console.log(AllUnReadByUser)
			} else if ("getOneLastHistoryAndUnReadNum" === jsonObj.type) {
// 				var t=setTimeout(function(){
				var lastMsg = jsonObj.message;//lastMsg
				var unReadNum = jsonObj.messagetime;//unReadNum
				var obj = JSON.parse(lastMsg);
			
				if ($("#odiv .leftListButton[name='" + jsonObj.receiver + "']").length === 0 && self === jsonObj.sender) {
				
					var div = document.createElement('div');
					div.className = 'leftListButton';
					div.setAttribute("name", jsonObj.receiver);
					
					var id=AllNoToId[jsonObj.receiver];
					if(name==="Robot"){
						id='推播小人';
					}
					var img = urlByStatus(jsonObj.receiver);
					var html = "<div class='userphoto'><img src='"
 	 			+img+"'></div><div name='"+jsonObj.receiver
 	 						+"' class='messageButtun'><h1>"
							+ AllNoToId[jsonObj.receiver]
							+ id
							+ "</h1><p>"
							+ "</p></div><div name='"
							+ obj.receiver+"' class='messageNumber'>"
							+ "</div>";

					$(div).html(html);
					$("#odiv .leftList").prepend(div);
					$(div).click(
							function(e) {
								
								$("#odiv .title .photo").css("visibility",
										"visible");
								var rowName = $(e.target).parents(
										".leftListButton").attr("name")
								$("#odiv div[name=header] div.title>p").attr(
										'name', rowName);
								$("#odiv div[name=header] div.title>p").text(
										$(this).find(".messageButtun h1")
												.text());
								$("#odiv .title .photo").html("<img src='"+urlByStatus(rowName)+"'>");
								
								
								sendAction("history",username, jsonObj.receiver);
			
								var reqO = {
									"type" : "checkRead",
									"sender" : username,
									"receiver" : jsonObj.receiver,
									"message" : "",
									"messagetime" : "1",
									"mesgStatus" : ""
								};
								webSocket.send(JSON.stringify(reqO));

								sendAction("getOneLastHistoryAndUnReadNum",username, jsonObj.receiver);
								
								

							});
				}
				$("#odiv .leftList .leftListButton[name='" + jsonObj.receiver + "']").find("p")
						.html(obj.message);

				$("#odiv .leftList .leftListButton[name='" + jsonObj.receiver + "']").find(
						".messageNumber").text(unReadNum);

				calTotalUnRead()
// 				},5000)

			} else if ("checkRead" === jsonObj.type) {

				if (parseInt(jsonObj.messagetime) < 4) {
					var req = {
						"type" : "checkRead",
						"sender" : jsonObj.receiver,
						"receiver" : jsonObj.sender, // 讓RECEIVER 執行自己的HISTORY
						"message" : "",
						"messagetime" : (parseInt(jsonObj.messagetime) + 1)
								.toString(),
						"mesgStatus" : ""
					};
					webSocket.send(JSON.stringify(req));
				}
				if ($("#odiv div[name=header] div.title>p").attr("name") === jsonObj.sender)
					
					sendAction("history",self, jsonObj.sender);
					
			} else if ("close" === jsonObj.type) {

			}

		};

		webSocket.onclose = function(event) {
			console.log("Disconnected!");
		};
		$("#odiv .upfilebutton")
				.change(
						function(e) {
							var imgNo = generateUUID();
							var file_data = $("#odiv .upfilebutton").prop(
									'files')[0];
							var form_data = new FormData(); //建構new FormData()
							form_data.append('chatimg', file_data); //吧物件加到file後面
							form_data.append('chatimgno', imgNo);
							form_data.append('action', 'insert');
							$
									.ajax({
										url : '<%= request.getContextPath() %>/chatRoom/chatimg.do',
										cache : false,
										contentType : false,
										processData : false,
										data : form_data, //data只能指定單一物件                 
										type : 'post',
										success : function(data) {

											var html = "<img src='<%= request.getContextPath() %>/chatRoom/chatImg?chatimgno="
													+ imgNo + "'>";

											sendImg(html);

										}
									});
						})
		function sendImg(html) {
			var friend = $("#odiv div[name=header] div.title>p").attr("name")
					.trim();
			if (friend === 'Robot'){
				return;
			}
			var messagetime = new Date().getTime();
			if (friend.length > 0) {
				var jsonObj = {
					"type" : "chat",
					"sender" : self,
					"receiver" : friend,
					"message" : html,
					"messagetime" : messagetime,
					"mesgStatus" : "unRead"
				};

				webSocket.send(JSON.stringify(jsonObj));
			}
		}

		function sendMessage() {

			var inputMessage = $("#odiv div.textarea");
			var friend = $("#odiv div[name=header] div.title>p").attr("name")
					.trim();
			if (friend === 'Robot'){
				return;
			}
			var message = inputMessage.html();
			var messagetime = new Date().getTime();
			if (friend.length > 0) {
				if (message === "") {
					
				} else {
					var jsonObj = {
						"type" : "chat",
						"sender" : self,
						"receiver" : friend,
						"message" : message,
						"messagetime" : messagetime,
						"mesgStatus" : "unRead"
					};

					webSocket.send(JSON.stringify(jsonObj));
					inputMessage.html("");
					inputMessage.focus();
				}
			}
		}

		function disconnect() {
			webSocket.close();
			document.getElementById('sendMessage').disabled = true;
			document.getElementById('connect').disabled = false;
			document.getElementById('disconnect').disabled = true;
		}
		function formatDateTime(date) {
			var today = new Date();
			var tm = today.getMonth() + 1
			var td = today.getDate();

			var m = date.getMonth() + 1;
			var d = date.getDate();
			var h = date.getHours();
			var minute = date.getMinutes();
			minute = minute < 10 ? ('0' + minute) : minute;

			if (tm === m && td === d) {
				return h + ":" + minute;
			} else {
				mC = m < 10 ? ('0' + m) : m;
				dC = d < 10 ? ('0' + d) : d;
				return mC + '-' + dC + ' ' + h + ':' + minute;
			}
		}
		$("#odiv .client").click(function(){
			$("#odiv .clientBox").html('');
			var html='';
			var len = CusSevList.length;
			
			for(var i = 0; i<len ; i++){
				var name = CusSevList[i];
				if (self === name ){continue;}
				var div = document.createElement('div');
				div.className = 'leftListButton';
				div.setAttribute("name", name);
				var img = "<%= request.getContextPath() %>/chatRoom/lib/img/clientSvc.jpg";
				var html = "<div class='userphoto'><img src='"
	 	 			+img+"'></div><div name='"+name
	 	 						+"' class='messageButtun'><h1>"
								+ "客服人員00"+(i+1)
								+ "</h1><p>"
								+ "</p></div><div name='"
								+ name+"' class='messageNumber'>"
								+ "</div>";

						$(div).html(html);
						$("#odiv .clientBox").append(div);
				
				$(div).click(
						function(e) {
							var rowName = $(e.target).parents(".leftListButton").attr("name");
							if($(e.target).parents(".leftListButton").length===0){
								rowName =$(e.target).attr("name");
							}
							$("#odiv .title .photo").css("visibility",
									"visible");
							$("#odiv .title .photo").html("<img src=<%= request.getContextPath() %>/chatRoom/lib/img/clientSvc.jpg>");
							
							$("#odiv div[name=header] div.title>p").attr(
									"name", rowName);
							$("#odiv div[name=header] div.title>p").text(
									$(this).find(".messageButtun h1")
											.text());
							
							
							sendAction("history",username, rowName);
							
							

							var reqO = {
								"type" : "checkRead",
								"sender" : username,
								"receiver" : rowName,
								"message" : "",
								"messagetime" : "1",
								"mesgStatus" : ""
							};
							webSocket.send(JSON.stringify(reqO));

							
							sendAction("getOneLastHistoryAndUnReadNum",username, rowName);
						});
				calTotalUnRead();
			}
		});
		
		$("#odiv .icon")
				.click(
						function(e) {
							var a = $("form[name='leftlColumn']").find(
									"input[name='leftlColumn']").val().trim();

							if (a != self && a!="") {
								$("#odiv .title .photo").css("visibility",
										"visible");
								$("#odiv .title .photo").html("<img src='"+urlByStatus(a)+"'>");
								$("#odiv div[name=header] div.title>p").attr(
										"name", a);
								
								
								var id=AllNoToId[a];
								if(a==="Robot"){
									id='推播小人';
								}
								
								$("#odiv div[name=header] div.title>p").text(
										id+JudgeStatus(a)
								);
								
								
								
								sendAction("history",username,a);
								

								var reqO = {
									"type" : "checkRead",
									"sender" : username,
									"receiver" : a,
									"message" : "",
									"messagetime" : "1",
									"mesgStatus" : ""
								};
								webSocket.send(JSON.stringify(reqO));

								
								sendAction("getOneLastHistoryAndUnReadNum",username,a);
								
							}
						});
		
		
		
		function JudgeStatus(str) {
			var len =CusSevList.length;
			for(var i=0;i<len;i++){
				if(CusSevList[i]===str){
				return '-客服';
				}
			}
			if(str === 'Robot'){
			return '-BOT';
			}
			var First = str.slice(0, 1);
			if (First === 'E') {
				return '-員工';
			} else if (First === 'M') {
				return '-會員';
			} else if (First === 'V') {
				return '-廠商';
			}
			return "";
		}
		

		function sendAction(type,sender,receiver){
		var req = {
				"type" : type,
				"sender" : sender,
				"receiver" : receiver,
				"message" : "",
				"messagetime" : "",
				"mesgStatus" : ""
			};
		webSocket.send(JSON.stringify(req));
		}
		
	}///CONCHAT END
	
	
	function urlByStatus(str) {
		
		if(str === 'Robot'){
		return "<%= request.getContextPath() %>/chatRoom/lib/img/BOT.jpeg";
		}
		var First = str.slice(0, 1);
		if (First === 'E') {
		return "<%= request.getContextPath() %>/back-end/emp/Img?empno="+str;
		} else if (First === 'M') {

		} else if (First === 'V') {

		}
		return "";
	}

	function calTotalUnRead() {
		$("#chatrbutton .message").text('');
		$("#chatrbutton .message").css("visibility", "hidden");
		var sum = 0;
		var array = $(".leftList .messageNumber");

		for (let i = 0; i < array.length; i++) {
			sum += parseInt($(array[i]).text());
		}
		if (sum != 0) {
			$("#chatrbutton .message").css("visibility", "visible");
			$("#chatrbutton .message").text(sum);
		}
	}

	function htmlEscape(html) {
		var reg = /(&lt;)|(&gt;)|(&amp;)|(&quot;)/g;
		return html.replace(reg, function(match) {
			switch (match) {
			case "&lt;":
				return "<";
				case "&gt;":
				return ">"
			case "&amp;":
				return "&";
			case "&quot;":
				return "\""
			}
		});
	}
	function generateUUID() {
		var d = new Date().getTime();
		if (window.performance && typeof window.performance.now === "function") {
			d += performance.now(); //use high-precision timer if available
		}
		var uuid = 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g,
				function(c) {
					var r = (d + Math.random() * 16) % 16 | 0;
					d = Math.floor(d / 16);
					return (c == 'x' ? r : (r & 0x3 | 0x8)).toString(16);
				});
		return uuid;
	}
</script>
</html>