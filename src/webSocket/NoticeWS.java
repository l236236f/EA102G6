package webSocket;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.fosmon.model.FosmService;
import com.google.gson.Gson;

@ServerEndpoint("/NoticeWS/{userName}")
public class NoticeWS {
	private static Map<String, Session> sessionsMap = new ConcurrentHashMap<>();
	Gson gson = new Gson();

	@OnOpen
	public void onOpen(@PathParam("userName") String userName, Session userSession) throws IOException {
		/* save the new user in the map */
		sessionsMap.put(userName, userSession);
		System.out.println(userName + "成功加入socket囉!");
	}

	@OnMessage
	public void onMessage(Session userSession, String message) {
		NoticeMessage call = gson.fromJson(message, NoticeMessage.class);

		String receiver = call.getReceiver();
		String fosmNOReg = "^(FM)[0-9]{3}$";
		if(call.getReceiver().matches(fosmNOReg)) {
			FosmService fmSvc = new FosmService();
			receiver = fmSvc.getOneByFosmNo(receiver).getMemNo();
		}
		Session receiverSession = sessionsMap.get(receiver);
		if (receiverSession != null && receiverSession.isOpen()) {
			receiverSession.getAsyncRemote().sendText(message);	
		}
		
	}

	@OnError
	public void onError(Session userSession, Throwable e) {
//		System.out.println("Error: " + e.toString());
	}

	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
//		String userNameClose = null;
//		Set<String> userNames = sessionsMap.keySet();
//		for (String userName : userNames) {
//			if (sessionsMap.get(userName).equals(userSession)) {
//				userNameClose = userName;
//				sessionsMap.remove(userName);
//				break;
//			}
//		}
	}
}
