package pockerSocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
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

import org.json.JSONArray;
import org.json.JSONObject;

import com.game.model.Seat;
import com.google.gson.Gson;


@ServerEndpoint("/JoinWS/{userName}/{action}")
public class JoinWS {
//	private static Map<String, Session> sessionsMap = new ConcurrentHashMap<>();
	private static final Set<Session> connectedSessions = Collections.synchronizedSet(new HashSet<>());
	private static Map<String, Seat> rooms = new ConcurrentHashMap<>();
	Gson gson = new Gson();
	
	@OnOpen
	public void onOpen(@PathParam("userName") String userName,@PathParam("action") String action, Session userSession) throws IOException {
		connectedSessions.add(userSession);
		if("inLobby".equals(action)) {
			System.out.println(userName+"進入大廳囉!");
		}
		if("returnLobby".equals(action)) {
			System.out.println(userName+"回到大廳囉!");
		}
		
		Collection<Seat> roomSet = rooms.values();
		for(Session session:connectedSessions) {
			if(session.isOpen()) {
				JSONArray jsonArr  = new JSONArray(roomSet);
				session.getAsyncRemote().sendText(jsonArr.toString());
			}
		}
	}

	@OnMessage
	public void onMessage(Session userSession, String message) {
		Seat seat = gson.fromJson(message, Seat.class);
		Collection<Seat> roomSet = rooms.values();
		if("inroom".equals(seat.getType())){
			if(!rooms.containsKey(seat.getRoomNo())) {
				seat.setCount(1);
				rooms.put(seat.getRoomNo(), seat);
				for(Session session:connectedSessions) {
					if(session.isOpen()) {
						JSONArray jsonArr  = new JSONArray(roomSet);
						session.getAsyncRemote().sendText(jsonArr.toString());
					}
	    		}  
			}else {
				Seat roomSeat = rooms.get(seat.getRoomNo());
				roomSeat.setCount(roomSeat.getCount()+1);
				for(Session session:connectedSessions) {	
					if(session.isOpen()) {
						JSONArray jsonArr  = new JSONArray(roomSet);
						session.getAsyncRemote().sendText(jsonArr.toString());
					}
	    		}  
				
			}
		}
		if("leaveRoom".equals(seat.getType())){
			Seat roomSeat = rooms.get(seat.getRoomNo());
			roomSeat.setCount(roomSeat.getCount()-1);
			if(roomSeat.getCount()==0) {
				rooms.remove(seat.getRoomNo());
			}
			for(Session session:connectedSessions) {	
				if(session.isOpen()) {
					JSONArray jsonArr  = new JSONArray(roomSet);
					session.getAsyncRemote().sendText(jsonArr.toString());
				}
    		}
		}
	}

	@OnError
	public void onError(Session userSession, Throwable e) {
//		System.out.println("Error2: " + e.toString());
	}

	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
		connectedSessions.remove(userSession);
	}
}
