<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>



<!DOCTYPE html>
<html>
<head>
<title>Examples</title>
<link
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css"
	rel="stylesheet">
<link href="/EA102G6/chatRoom/lib/css/nanoscroller.css" rel="stylesheet">
<link href="/EA102G6/chatRoom/lib/css/emoji.css" rel="stylesheet">
<link href="/EA102G6/chatRoom/lib/css/chatRoom.css" rel="stylesheet">


</head>


<body>
	<input type=text id='LoginMem'>
<button id = 'click'>111111</button>

	<div id="chatrbutton" class="chatrbutton">
		<div class='message'></div>
		<div class="chatrbutton" name='chatrbutton'>
			<i class="far fa-comment-dots"></i>
		</div>
	</div>


	<div class="box" id="odiv" style='height: 500px;'>
		<div class="checkStream">
			<!-- 			<span class="checkStreamText">aaaaa 向你發起視訊請求 是否接受</span> -->
			<!-- 			<div class="receptStream"> -->
			<!-- 				<i class="far fa-check-square"></i> -->
			<!-- 			</div> -->
			<!-- 			<div class="rejectStream"> -->
			<!-- 				<i class="fas fa-ban"></i> -->
			<!-- 			</div> -->
		</div>
		<div class='videoBox'>
			<video id="remote-video"></video>
			<div class='closeVideo'>
				<i class="far fa-times-circle"></i>
			</div>
			<div class='minusChildVideo'>
				<i class="fas fa-minus"></i>
			</div>
			<div class='localVideo'>
				<video id='local-video'></video>
			</div>
		</div>
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
				<div name="leftbutton" class='leftbutton'>
					<i class="fas fa-bars"></i>
				</div>
			</div>
			<div class="title">
				<div class='photo'></div>

				<p></p>
				<div class="video">
					<i class="fas fa-video"></i>
				</div>
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
  <script src="<%=request.getContextPath()%>/front-end/js/jquery-3.3.1.min.js"></script>
<script src="<%=request.getContextPath()%>/front-end/js/jquery-ui.min.js"></script>


<script src="https://kit.fontawesome.com/dd027c1f63.js"
	crossorigin="anonymous"></script>
<!-- <script src="/EA102G6/chatRoom/js/jquery-2.1.1.min.js"></script> -->

<script src="/EA102G6/chatRoom/lib/js/nanoscroller.min.js"></script>
<script src="/EA102G6/chatRoom/lib/js/tether.min.js"></script>
<script src="/EA102G6/chatRoom/lib/js/config.js"></script>
<script src="/EA102G6/chatRoom/lib/js/util.js"></script>
<script src="/EA102G6/chatRoom/lib/js/jquery.emojiarea.js"></script>
<script src="/EA102G6/chatRoom/lib/js/emoji-picker.js"></script>
<script src='/EA102G6/chatRoom/lib/js/chatRoomArea.js'></script>
<script src='/EA102G6/chatRoom/lib/js/reconnecting-websocket.min.js'></script>
<script>
	$(function() {
// 		ChatConnect();
	});// $f

	
	var PeerConnection;
	var v = $('#LoginVendor').val();
	console.log(v)
	var m = $('#LoginMem').val();
	console.log(m)
	var e =$("#LoginEmpno").text();
	console.log(e)
		if(v){
		var username = v;
		}else if(m){
		var username = m;
		}else if(e){
		var username = e;
		}
		console.log('---------')
		console.log(username)
	var peer;
	var webSocket;
	
	$("#click").click(function(){
		
		ChatConnect();	
	})
	
	function ChatConnect() {
		console.log('開始連線')
		var username =$('#LoginMem').val();
		var MyPoint = "/FriendWS/" + username;
		var host = window.location.host;
		var path = window.location.pathname;
		var webCtx = path.substring(0, path.indexOf('/', 1));
		
		var endPointURL = "wss://" + window.location.host + webCtx + MyPoint;
		
        console.log(endPointURL)
		var statusOutput = $("#odiv div[name=header] div.title>p");

		var messagesArea = $("#odiv div[name=inner]");
		var self = username;
		
		
		var localstream;
		var AllNoToId = new Map();
		var CusSevList = new Array();
		var AllLastHistoryByUser =new Map();
		var AllUnReadByUser =new Map();
		webSocket = new ReconnectingWebSocket(endPointURL);
		var localVideo = $("#local-video");
		var remoteVideo = $("#remote-video");
		
		$("#odiv div[name=submit]").click(function() {
			sendMessage();
		});

		
			$("#odiv .closeVideo").click(function(){
			if(peer!=null){
			peer.close();}
			if(localstream!=null){
			localstream.getTracks().forEach(function (track) {
	            track.stop();});
			}
			
			
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
// 					var timer=setTimeout(function(){messagesArea.scrollTop(messagesArea.prop('scrollHeight'))} , 100);
// 					messagesArea.find('img').load(function(){
// 						messagesArea.scrollTop(messagesArea.prop('scrollHeight'))
						
// 					});
// 					($("#odiv div[name='inner']").children("div:last-child")[0]).scrollIntoView();
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
					console.log(typeof name)
					if (self === name ){continue;}
					var div = document.createElement('div');
					div.className = 'leftListButton';
					div.setAttribute("name", name);
					
					
					var img = urlByStatus(name);
					var number = AllUnReadByUser[key];
					var lastmessage = htmlEscape(JSON
							.parse(AllLastHistoryByUser[key]).message);
					
					
					id=AllNoToId[name];
		
					
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
								$("#odiv .video").show();
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
 	 						+JudgeStatus(jsonObj.receiver)
							+ "</h1><p>"
							+ "</p></div><div name='"
							+ obj.receiver+"' class='messageNumber'>"
							+ "</div>";

					$(div).html(html);
					$("#odiv .leftList").prepend(div);
					$(div).click(
							function(e) {
								$("#odiv .video").show();
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
				console.log("obj.message", obj.message)

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
					
			}else if ("answerSdp" === jsonObj.type) {
				
			
				var a = JSON.parse(jsonObj.message);

				var g=new RTCSessionDescription();
				g.type=a.type;
				g.sdp= a.sdp;
				peer.setRemoteDescription(g);
				
				
				
	
			}else if ("ICE" === jsonObj.type) {
			
			
			a = JSON.parse(jsonObj.message);

			var candidate = new RTCIceCandidate({
                        sdpMLineIndex: a.sdpMLineIndex,
                        candidate: a.candidate,
                        sdpMid:a.sdpMid
                    });

			
			
			console.log(candidate)
			if(jsonObj.sender==$("#odiv div[name=header] div.title>p").attr("name")){
			peer.addIceCandidate(candidate);}
				
				
				
			}else if ("sendSdp" === jsonObj.type) {
				
				
				if($("#odiv div[name=header] div.title>p").attr("name") === jsonObj.sender){
						
					var a = JSON.parse(jsonObj.message)
					var type = a.type;
					var sdp  = a.sdp;
					startLive(new RTCSessionDescription({ type, sdp }));
		
				}

				}else if ("askStream" === jsonObj.type) {
				
				if(self===jsonObj.sender){
					$("#odiv .checkStreamText").text("對方不在線上");
				}else if(self===jsonObj.receiver){		
				shake($('#odiv'), 2500, 10, 5);
				shake($('#chatrbutton'), 2500, 10, 5);
				$("#odiv .checkStream").show();
				var html="<span class='checkStreamText'>"+AllNoToId[jsonObj.sender]+JudgeStatus(jsonObj.sender)
				+"向你發送視訊請求中</span>";
				html+="<div class='receptStream'><i class='far fa-check-square'></i></div>";
				html+="<div class='rejectStream'><i class='fas fa-ban'></i></div>";
				$("#odiv .checkStream").html(html);

				//註冊事件
				$("#odiv .checkStream .receptStream").click(function(){
					
					
// 					PeerConnection = window.RTCPeerConnection || window.mozRTCPeerConnection || window.webkitRTCPeerConnection;
// 					!PeerConnection && console.log('不支援RTC');

 


					peer = new RTCPeerConnection(
							 {iceServers: [
					            {
					                'urls': [
					                    'turn:webrtcweb.com:7788', // coTURN 7788+8877
					                    'turn:webrtcweb.com:4455?transport=udp', // restund udp

					                    'turn:webrtcweb.com:8877?transport=udp', // coTURN udp
					                    'turn:webrtcweb.com:8877?transport=tcp', // coTURN tcp
					                ],
					                'username': 'muazkh',
					                'credential': 'muazkh'
					            },
					            {
					                'urls': [
					                    'stun:stun.l.google.com:19302',
					                    'stun:stun1.l.google.com:19302',
					                    'stun:stun2.l.google.com:19302',
					                    'stun:stun.l.google.com:19302?transport=udp',
					                ]
					            },
					            {
					            	url: 'turn:numb.viagenie.ca',
					            	    credential: 'muazkh',
					            	    username: 'webrtc@live.com'
					            	},{
					            		  'urls': 'turn:numb.viagenie.ca',
					            		  'credential': 'websitebeaver',
					            		  'username': 'websitebeaver@email.com'
					            		},
					            		
					            		
					            		{
					            		    url: 'turn:numb.viagenie.ca',
					            		    credential: 'muazkh',
					            		    username: 'webrtc@live.com'
					            		},
					            		{
					            		    url: 'turn:192.158.29.39:3478?transport=udp',
					            		    credential: 'JZEOEt2V3Qb0y27GRntt2u2PAYA=',
					            		    username: '28224511:1379330808'
					            		},
					            		{
					            		    url: 'turn:192.158.29.39:3478?transport=tcp',
					            		    credential: 'JZEOEt2V3Qb0y27GRntt2u2PAYA=',
					            		    username: '28224511:1379330808'
					            		},
					            		{
					            		    url: 'turn:turn.bistri.com:80',
					            		    credential: 'homeo',
					            		    username: 'homeo'
					            		 },
					            		 {
					            		    url: 'turn:turn.anyfirewall.com:443?transport=tcp',
					            		    credential: 'webrtc',
					            		    username: 'webrtc'
					            		}
					        ]}
							);

					
				
					
					peer.ontrack = e => {
						if (e && e.streams) {
							console.log('收到對方視訊');
							
							console.log(e.streams[0])
							remoteVideo[0].srcObject = e.streams[0];
							$("#odiv .videoBox").show();
						}
					};
					
					peer.oniceconnectionstatechange = () =>{
					  console.log('PC2 ice state ' + peer.iceConnectionState);
					}

					peer.onicecandidate = e => {
						if (e.candidate) {
							console.log('蒐集並發送候選人');
							
							var req = {
									"type" : "ICE",
									"sender" : $("#no").val(),
									"receiver" : $("#odiv div[name=header] div.title>p").attr("name"),
									"message" : JSON.stringify(e.candidate),
									"messagetime" : "",
									"mesgStatus" : ""
								};
							
							webSocket.send(JSON.stringify(req));
						
						} else {
							console.log('候選人收集完成！');
						}
					};

					$("#odiv div[name=header] div.title>p").attr("name",jsonObj.sender);
					$("#odiv div[name=header] div.title>p").text(AllNoToId[jsonObj.sender]);
					$("#odiv .title .photo").css("visibility",
					"visible");
					$("#odiv .title .photo").html("<img src='"+urlByStatus(jsonObj.sender)+"'>");
					
					var req = {
							"type" : "resStreamAsk",
							"sender" : jsonObj.receiver,
							"receiver" : jsonObj.sender,
							"message" : "accept",
							"messagetime" :"",
							"mesgStatus" : ""
						};
						webSocket.send(JSON.stringify(req));
						$("#odiv .videoBox").show();
						$("#odiv .checkStream").hide();
						$("#odiv .checkStream").html("");
					
				});
				$("#odiv .checkStream .rejectStream").click(function(){
					
					var req = {
							"type" : "resStreamAsk",
							"sender" : jsonObj.receiver,
							"receiver" : jsonObj.sender,
							"message" : "reject",
							"messagetime" :"",
							"mesgStatus" : ""
						};
						webSocket.send(JSON.stringify(req));
						$("#odiv .checkStream").hide();
						$("#odiv .checkStream").html("");
					
				});
				}	
				}else if ("resStreamAsk" === jsonObj.type) {

					if(jsonObj.message==='reject'){
						
						var html="<span class='checkStreamText'>"+AllNoToId[jsonObj.sender]
						+"已拒絕</span>";
						html+="<div class='rejectStream'><i class='fas fa-ban'></i></div>";
						$("#odiv .checkStream").html(html);
						$("#odiv .checkStream .rejectStream").click(function(){
							$("#odiv .checkStream").hide();
							$("#odiv .checkStream").html("");
							$("#odiv .videoBox").hide();
							$("#odiv .video").show();
						});
					
					}else if(jsonObj.message==='accept' && $("#odiv div[name=header] div.title>p").attr("name")===jsonObj.sender){
						$("#odiv .checkStream").hide();
						$("#odiv .checkStream").html("");
						startLive();
					}
					
					
					
					
					
				}else if ("close" === jsonObj.type) {

			}

		};

		webSocket.onclose = function(event) {
			
			console.log("Disconnected!");
		
		};
		$("#odiv .upfilebutton")
				.change(
						function(e) {
							if($("#odiv .upfilebutton").prop('files').length>0){
							var imgNo = generateUUID();
							var file_data = $("#odiv .upfilebutton").prop(
									'files')[0];
							var form_data = new FormData(); //建構new FormData()
							form_data.append('chatimg', file_data); //吧物件加到file後面
							form_data.append('chatimgno', imgNo);
							form_data.append('action', 'insert');
							$
									.ajax({
										url : '/EA102G6/chatRoom/chatimg.do',
										cache : false,
										contentType : false,
										processData : false,
										data : form_data, //data只能指定單一物件                 
										type : 'post',
										success : function(data) {

											var html = "<img src='/EA102G6/chatRoom/chatImg?chatimgno="
													+ imgNo + "'>";

											sendImg(html);
											$("#odiv .upfilebutton").val("");
										}
									});
							}
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
				var img = "/EA102G6/chatRoom/lib/img/clientSvc.jpg";
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
							$("#odiv .video").show();
							var rowName = $(e.target).parents(".leftListButton").attr("name");
							if($(e.target).parents(".leftListButton").length===0){
								rowName =$(e.target).attr("name");
							}
							$("#odiv .title .photo").css("visibility",
									"visible");
							$("#odiv .title .photo").html("<img src=/EA102G6/chatRoom/lib/img/clientSvc.jpg>");
							
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
								$("#odiv .video").show();
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
		
		

		localVideo[0].onloadeddata = () => {
			localVideo[0].play();
		}
		remoteVideo[0].onloadeddata = () => {
			remoteVideo[0].play();
		}
		
		$("#odiv .title .video").click(function(){
				
			if($("#odiv div[name=header] div.title>p").attr("name")!='Robot'){
			$("#odiv .checkStream").show();
			
			html="<span class='checkStreamText'>你向"
			+$("#odiv div[name=header] div.title>p").text()
			+"發送視訊請求中</span>";
			$("#odiv .checkStream").html(html);


			var reqO = {
					"type" : "askStream",
					"sender" : username,
					"receiver" : $("#odiv div[name=header] div.title>p").attr("name").trim(),
					"message" : "",
					"messagetime" : "",
					"mesgStatus" : ""
				};
				webSocket.send(JSON.stringify(reqO));
		
				
				PeerConnection = window.RTCPeerConnection || window.mozRTCPeerConnection || window.webkitRTCPeerConnection;
				!PeerConnection && console.log('不支援RTC');
				peer = new RTCPeerConnection(
						 {iceServers: [
							 {
				                'urls': [
				             
				                    'turn:webrtcweb.com:8877?transport=udp', // coTURN udp
				                    'turn:webrtcweb.com:8877?transport=tcp', // coTURN tcp
				                ],
				                'username': 'muazkh',
				                'credential': 'muazkh'
				            },
				            {
				                'urls': [
				                    'stun:stun.l.google.com:19302',
				                    'stun:stun1.l.google.com:19302',
				                    'stun:stun2.l.google.com:19302',
				                    'stun:stun.l.google.com:19302?transport=udp',
				                ]
				            },
				            {
				            	url: 'turn:numb.viagenie.ca',
				            	    credential: 'muazkh',
				            	    username: 'webrtc@live.com'
				            	},
				            	{
					            	url: 'turn:numb.viagenie.ca',
					            	    username: 'l236236f@yahoo.com.tw',
					            	    credential:'lfj20372'
					            },{
					                url: 'turn:numb.viagenie.ca',
					                credential: 'muazkh',
					                username: 'webrtc@live.com'
					            },
					            {
					                url: 'turn:192.158.29.39:3478?transport=udp',
					                credential: 'JZEOEt2V3Qb0y27GRntt2u2PAYA=',
					                username: '28224511:1379330808'
					            },
					            {
					                url: 'turn:192.158.29.39:3478?transport=tcp',
					                credential: 'JZEOEt2V3Qb0y27GRntt2u2PAYA=',
					                username: '28224511:1379330808'
					            },
					            {
					                url: 'turn:turn.bistri.com:80',
					                credential: 'homeo',
					                username: 'homeo'
					             },
					             {
					                url: 'turn:turn.anyfirewall.com:443?transport=tcp',
					                credential: 'webrtc',
					                username: 'webrtc'
					            }
				            
				        ]}
						);

			
				peer.ontrack = e => {
					if (e && e.streams) {
						console.log('收到對方視訊');
						remoteVideo[0].srcObject = e.streams[0];
						$("#odiv .videoBox").show();
					}
				};
				peer.oniceconnectionstatechange = () =>{
					  console.log('PC1 ice state ' + peer.iceConnectionState);
					}
				peer.onicecandidate = e => {
					if (e.candidate) {
						console.log('蒐集並發送候選人');
						
						var req = {
								"type" : "ICE",
								"sender" : $("#no").val(),
								"receiver" : $("#odiv div[name=header] div.title>p").attr("name"),
								"message" : JSON.stringify(e.candidate),
								"messagetime" : "",
								"mesgStatus" : ""
							};
						
					
						webSocket.send(JSON.stringify(req));
					
					} else {
						console.log('候選人收集完成！');
					}
				};
				
				
				
			}
			
			
// 				
		});
		
		
		async function startLive(offerSdp){
			
			
			var videoReceiver=$("#odiv div[name=header] div.title>p").attr("name");
			if(videoReceiver != ''){
			
			
			try {
				console.log("TRY LOCAL前")
				localstream = await navigator.mediaDevices.getUserMedia({ video: true, audio: true });
				
				console.log("開啟視訊要求有沒有近來LOCAL")
				$("#local-video")[0].srcObject = await localstream;
				
			}catch {
				console.log('本地視訊頭獲取要求失敗');
				return;
			}
			
			localstream.getTracks().forEach(track => {
				peer.addTrack(track, localstream);
			});
			

			if (!offerSdp) {
				console.log('創建本地端SDP');
				const offer = peer.createOffer();
				await peer.setLocalDescription(await offer);
				console.log('傳輸本地SDP');
				var str =JSON.stringify(await offer);
				var reqSendSdp = {
						"type" : "sendSdp",
						"sender" : 	$("#no").val(),
						"receiver" : $("#odiv div[name=header] div.title>p").attr("name"),
						"message" : str,
						"messagetime" : "",
						"mesgStatus" : ""
					};
				
				webSocket.send(JSON.stringify(reqSendSdp));
				
				
			}
				 else {
					console.log('接收發送方SDP');
    			
					peer.setRemoteDescription(offerSdp).then(async function(){
						
						
						var answer =  peer.createAnswer(); 
							console.log('建立接收方應答SDP');
							console.log(await answer)
							
							
							var req = {
									"type" : "answerSdp",
									"sender" : $("#odiv div[name=header] div.title>p").attr("name"),
									"receiver" : $("#no").val(),
									"message" : JSON.stringify(await answer),
									"messagetime" : "",
									"mesgStatus" : ""
								};
							webSocket.send(JSON.stringify(req));
							peer.setLocalDescription(await answer);
							
					
					

					
					});

					console.log('建立接收方應答SDP');

					

					
				}
			
		
			
			
			
			}
		}
		
		
		
	}///CONCHAT END
	
	
	function urlByStatus(str) {
		
		if(str === 'Robot'){
		return "/EA102G6/chatRoom/lib/img/BOT.jpeg";
		}
		var First = str.slice(0, 1);
		if (First === 'E') {
		return "/EA102G6/back-end/emp/Img?empno="+str;
		} else if (First === 'M') {
		return "/EA102G6/front-end/ShowPhotos?type=mem&memNo="+str;
		} else if (First === 'V') {
		return	"/EA102G6/front-end/ShowPhotos?type=vendor&photo_no="+str;	
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