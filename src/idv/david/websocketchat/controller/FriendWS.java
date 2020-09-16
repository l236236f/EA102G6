package idv.david.websocketchat.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;
import com.mem.model.MemService;
import com.mem.model.MemVO;

import AUTHORITY.model.*;
import EMPLOYEE.model.*;
import com.vendor.*;
import com.vendor.model.VendorService;
import com.vendor.model.VendorVO;

import idv.david.websocketchat.jedis.JedisHandleMessage;
import idv.david.websocketchat.model.*;


@ServerEndpoint("/FriendWS/{userName}")
public class FriendWS {
	private static Map<String, Session> sessionsMap = new ConcurrentHashMap<>();
	Gson gson = new Gson();

	@OnOpen
	public void onOpen(@PathParam("userName") String userName, Session userSession) throws IOException {
		/* save the new user in the map */
		sessionsMap.put(userName, userSession);
		/* Sends all the connected users to the new user */
		Set<String> userNames = sessionsMap.keySet();
		State stateMessage = new State("open", userName, userNames);
		String stateMessageJson = gson.toJson(stateMessage);
		Collection<Session> sessions = sessionsMap.values();
		for (Session session : sessions) {
			if (session.isOpen()) {
				session.getBasicRemote().sendText(stateMessageJson);
			}
		}

		EmpService empSev = new EmpService();
		VendorService venSev = new VendorService();
		MemService memSev = new MemService();
		
		AUTHOService authoSev = new AUTHOService();

		List<EmpVO> empList = empSev.getAll();
		List<VendorVO> venList = venSev.getAll();
		List<MemVO> memList = memSev.getAll();
		
		List<AUTHOVO> authoList = authoSev.getAUTHOByFeatno("FEAT0006");

		List<String> CusSevList = new ArrayList<String>();
		Map<String, String> AllNoToId = new HashMap<String, String>();

		for (AUTHOVO authovo : authoList) {
			CusSevList.add(authovo.getEmpno());
		}
		for (EmpVO empvo : empList) {
			AllNoToId.put(empvo.getEmpno(), empvo.getEmpid());
		}
		// 員工的所有帳號
		for (MemVO memvo : memList) {
			AllNoToId.put(memvo.getMemNo(), memvo.getMemAcc());
		}

		// 會員的所有帳號
		for (VendorVO venvo : venList) {
			AllNoToId.put(venvo.getVenNo(), venvo.getVenAcc());
		}
		// 廠商的所有帳號

		AllNoToId.put("Robot","推播機器人");
		Map<String, String> AllLastHistoryByUser = JedisHandleMessage.getLastHistoryMsg(userName);
		HashMap<String, Integer> AllUnReadByUser = JedisHandleMessage.getUnReadNum(userName);

		ChatMessage CM = new ChatMessage("AllLastMesgAndUnReadNum", gson.toJson(AllNoToId),
				gson.toJson(AllUnReadByUser), gson.toJson(AllLastHistoryByUser), gson.toJson(CusSevList), "");
		// 拿RECEIVER>ALLUNREAD欄位來用
		// sender> all no to id
		// message>all last his
		// messagetime>客服人員列表
		synchronized (userSession) {
			userSession.getBasicRemote().sendText(gson.toJson(CM));
		}

//		 String text = String.format("Session ID = %s, connected; userName =%s%nusers: %s", userSession.getId(),
//				userName, userNames);
//		System.out.println(text);
	}

	@OnMessage
	public void onMessage(Session userSession, String message) {
		if (message.length() > 10) {
		
		
			ChatMessage chatMessage = gson.fromJson(message, ChatMessage.class);
			String sender = chatMessage.getSender();
			String receiver = chatMessage.getReceiver();
			String messagetime = chatMessage.getMessagetime();

			if ("history".equals(chatMessage.getType())) {
				List<String> historyData = JedisHandleMessage.getHistoryMsg(sender, receiver);

				if (historyData == null) {
					return;
				}

				String historyMsg = gson.toJson(historyData);
				ChatMessage cmHistory = new ChatMessage("history", sender, receiver, historyMsg, messagetime, "");
				if (userSession != null && userSession.isOpen()) {
					try {
						synchronized (userSession) {
							userSession.getBasicRemote().sendText(gson.toJson(cmHistory));
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
//				System.out.println("history = " + gson.toJson(cmHistory));
					return;
				}
			}

			if ("getOneLastHistoryAndUnReadNum".equals(chatMessage.getType())) {

				String lastMsg = JedisHandleMessage.getOneLastHistory(sender, receiver);
				String unReadNum = JedisHandleMessage.getOneUnReadNum(sender, receiver);
				ChatMessage CM = new ChatMessage("getOneLastHistoryAndUnReadNum", sender, receiver, lastMsg, unReadNum,
						"");
				// 拿時間欄位來用

				Session seSession = sessionsMap.get(sender);
				if (lastMsg == null) {
					return;
				}
				if (seSession != null && seSession.isOpen()) {
					try {
						synchronized (seSession) {
							seSession.getBasicRemote().sendText(gson.toJson(CM));
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return;
				}
			}

			if ("checkRead".equals(chatMessage.getType())) {
				Session Session = sessionsMap.get(receiver);
				ChatMessage CM = new ChatMessage("checkRead", sender, receiver, "", chatMessage.getMessagetime(), "");
				if (Session != null && Session.isOpen()) {
					try {
						synchronized (Session) {
							Session.getBasicRemote().sendText(gson.toJson(CM));
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return;
				}
			}

			if ("chat".equals(chatMessage.getType())) {
				Session receiverSession = sessionsMap.get(receiver);
				if (receiverSession != null && receiverSession.isOpen()) {
					try {
						synchronized (receiverSession) {
							receiverSession.getBasicRemote().sendText(message);
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				try {
					synchronized (userSession) {userSession.getBasicRemote().sendText(message);}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				JedisHandleMessage.saveChatMessage(sender, receiver, message);

			}

			if ("sendSdp".equals(chatMessage.getType())) {
				Session receiverSession = sessionsMap.get(receiver);
				
				if (receiverSession != null && receiverSession.isOpen()) {
				synchronized (receiverSession) {
					try {
						receiverSession.getBasicRemote().sendText(message);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}}}
				
			}
			if ("answerSdp".equals(chatMessage.getType())) {

			
				Session seceiverSession = sessionsMap.get(sender);
				
				synchronized (seceiverSession) {
					try {
						seceiverSession.getBasicRemote().sendText(message);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}}

			}
			if ("ICE".equals(chatMessage.getType())) {
		
	
				Session receiverSession = sessionsMap.get(receiver);
				synchronized (receiverSession) {
					try {
						receiverSession.getBasicRemote().sendText(message);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}}
			
			}
			if ("askStream".equals(chatMessage.getType())) {
				Session receiverSession = sessionsMap.get(receiver);
				if(receiverSession != null && receiverSession.isOpen()){
					synchronized (receiverSession) {
						try {
							receiverSession.getBasicRemote().sendText(message);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}}
				}else {
			
				Session seSession = sessionsMap.get(sender);	
				synchronized (seSession) {
					try {
						seSession.getBasicRemote().sendText(message);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}}
				}
				
				
			
			}
			if ("resStreamAsk".equals(chatMessage.getType())) {
				Session receiverSession = sessionsMap.get(receiver);
				synchronized (receiverSession) {
					try {
						receiverSession.getBasicRemote().sendText(message);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}}
				
			}
//		System.out.println("Message received: " + message);
		}
	}

	@OnError
	public void onError(Session userSession, Throwable e) {
		System.out.println("Error: " + e.toString());
		e.printStackTrace();
	}

	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
		String userNameClose = null;
		Set<String> userNames = sessionsMap.keySet();
		for (String userName : userNames) {
			if (sessionsMap.get(userName).equals(userSession)) {
				userNameClose = userName;
				sessionsMap.remove(userName);
				break;
			}
		}

		if (userNameClose != null) {
			State stateMessage = new State("close", userNameClose, userNames);
			String stateMessageJson = gson.toJson(stateMessage);
			Collection<Session> sessions = sessionsMap.values();
			for (Session session : sessions) {
				try {
					synchronized (session) {
						session.getBasicRemote().sendText(stateMessageJson);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

//		String text = String.format("session ID = %s, disconnected; close code = %d%nusers: %s", userSession.getId(),
//				reason.getCloseCode().getCode(), userNames);
////		System.out.println(text);
	}
}
