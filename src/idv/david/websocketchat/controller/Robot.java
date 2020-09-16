package idv.david.websocketchat.controller;
import java.net.URI;
import java.util.Date;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import com.google.gson.Gson;
import idv.david.websocketchat.model.ChatMessage;

public class Robot {

	public static void chatRobot(String robotSay, Set<String> receivers) {

		WebSocketClient mWs = null;
		Gson gson = new Gson();
		try {
			String url = "wss://localhost:8081/EA102G6/FriendWS/Robot";
			URI uri = new URI(url);

			mWs = new WebSocketClient(uri) {
				@Override
				public void onOpen(ServerHandshake serverHandshake) {

				}

				@Override
				public void onMessage(String s) {

				}

				@Override
				public void onClose(int i, String s, boolean b) {
					System.out.println("已離線");
				}

				@Override
				public void onError(Exception e) {

				}

			};
			
			mWs.connect();
			boolean a=true;
			while (a) {
				if (mWs.isOpen()) {
					System.out.println("進入");
					for (String receiver : receivers) {
						String message = "<span style='color:red;'>推播機器人說:</span><br>" + robotSay;
						String messagetime = Long.toString(new Date().getTime());
						ChatMessage CM = new ChatMessage("chat", "Robot", receiver, message, messagetime, "unRead");
						mWs.send(gson.toJson(CM));
					}
				
					a=false;
					System.out.println("出去");
				}
				Thread.sleep(1000);
			}
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
