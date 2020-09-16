package pockerSocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import org.json.JSONObject;
import com.game.model.AnswerDAO;
import com.game.model.GameMessage;
import com.google.gson.Gson;



@ServerEndpoint("/DrowWS/{roomNo}/{userName}/{maxCount}")
public class PockerWS{
	//遊戲房間(遊戲玩家、玩家Session)
	private static Map<String, Map<String,Session>> rooms = new ConcurrentHashMap<>();
	//遊戲房間(資訊名稱、資訓內容)--簡單訊息
	private static Map<String, Map<String,String>> GameMsg = new ConcurrentHashMap<>();
	//遊戲房間(資訊名稱、資訓內容)--集合訊息
	private static Map<String, Map<String,Set<String>>> GameMsgC = new ConcurrentHashMap<>();
	//遊戲房間(遊戲房間、計時器)	
	private static Map<String, GameTimer> GameTimers = new ConcurrentHashMap<>();
	//遊戲房間(遊戲房間、計時器)	
	private static Map<String, NoticeTimer> NoticeTimers = new ConcurrentHashMap<>();
	Random ran = new Random();
	//公用答案群DAO
	AnswerDAO dao = new AnswerDAO();
	Gson gson = new Gson();

	@OnOpen
	public void onOpen(@PathParam("roomNo") String roomNo,@PathParam("userName") String userName,@PathParam("maxCount") String maxCount, Session userSession) throws IOException, InterruptedException {
		//先找房間沒找到就開一個新房間
        if (!rooms.containsKey(roomNo)) {
        	//創建房間資訊，放入玩家ID與Session
            Map<String,Session> room = new HashMap<String,Session>();
            userSession.setMaxTextMessageBufferSize(500 * 1024);
            room.put(userName,userSession);
            //放入房間該有的資訊
            Map<String,String> thisRoomMsg = new ConcurrentHashMap<>();
            thisRoomMsg.put("roomHost",userName);
            thisRoomMsg.put("maxCount",maxCount);
            GameMsg.put(roomNo, thisRoomMsg);
            Map<String,Set<String>> m = new HashMap<String,Set<String>>();
            //放入房間計時器
            GameTimer gt = new GameTimer();
            NoticeTimer nt = new NoticeTimer();
            GameTimers.put(roomNo, gt);
            NoticeTimers.put(roomNo, nt);
            //放進所有答案到房間裡
    		Set<String> AllAns = dao.getAll();
    		m.put("AllAns", AllAns);
    		//房間資訊先新增一筆空白的答案
    		thisRoomMsg.put("Ans","");
    		thisRoomMsg.put("status", "0");
    		//將進房者放進房間資訊(後面是分數)
    		thisRoomMsg.put(userName,"0");
    		//將房間資訊放入房間群
    		GameMsgC.put(roomNo, m);
    		rooms.put(roomNo, room);
            GameMessage msg = new GameMessage("roomHost","你是房主哦~");
            JSONObject jsonObj = new JSONObject(msg);
            userSession.getAsyncRemote().sendText(jsonObj.toString());
        } else {
        	//找到房間將userName、userSession放進房間
        	Map<String,Session> roomMap =  rooms.get(roomNo);
        	roomMap.put(userName, userSession);
        	//抓取房間全部session(傳遞全部訊息用)
        	Collection<Session> sesset = roomMap.values();
        	//取得該房間的訊息MAP物件
        	Map<String,String> thisRoomMsg = GameMsg.get(roomNo);
        	Set<String> RoomMemSet = roomMap.keySet();
        	//將進房者放進房間資訊(後面是分數)
        	thisRoomMsg.put(userName,"0");
    		Map<String,Integer> pointMap = new HashMap<String,Integer>();
    		for(String username:RoomMemSet) {
    			pointMap.put(username,new Integer(thisRoomMsg.get(username)));
    			
    		}
    		GameMessage msg = new GameMessage("getPoint","進入房間囉");
    		msg.setPointMap(pointMap);
    		
    		for(Session session:sesset) {	
    			JSONObject jsonObj = new JSONObject(msg);
    			session.getAsyncRemote().sendText(jsonObj.toString());
    		}  
        }
	}	

	@OnMessage
	public void onMessage(Session userSession, String message) throws InterruptedException {
		//取得傳進來的訊息，用gson轉成自訂義GameMessage物件
		GameMessage Gmsg = gson.fromJson(message, GameMessage.class);

		//取得房間名稱
		String roomNo = Gmsg.getRoomNo();
		//取得大廳裡的房間物件
		Map<String,Session> roomMap = rooms.get(roomNo);
		//從房間物件取得所有人的名稱跟session
		Set<String> roomMemSet = roomMap.keySet();
		Collection<Session> sesset = roomMap.values();
		//取得專屬房間訊息
		Map<String,String> thisRoomMsg = GameMsg.get(roomNo);
		Map<String,Set<String>> RoomMsgList = GameMsgC.get(roomNo);
		//確認傳進來的資訊種類
		String type = Gmsg.getType();
		//計時器物件
		GameTimer gt = GameTimers.get(roomNo);
		NoticeTimer nt = NoticeTimers.get(roomNo);
		//---------------遊戲開始區塊(房主按下開始),初始房間訊息----------------------------
		if("gameStart".equals(type)) {
			String status = thisRoomMsg.get("status");
			
			Set<String> drowPlayers = null;
			if("0".equals(status)) {
				drowPlayers = new HashSet<String>();
				for(String str : roomMemSet) {
					drowPlayers.add(str);
				}
			}else {
				drowPlayers = RoomMsgList.get("DrowPlayers");
			}
			String nowCount = roomMemSet.size()+"";
			thisRoomMsg.put("nowCount", nowCount);
			
			//初始化完成人數參數
			thisRoomMsg.put("complete", "0");
			//取出答案集合，取一個候刪除放回
			Set<String> allAns = RoomMsgList.get("AllAns");
			Object[] ansArr = allAns.toArray();
			String thisAns = (String) ansArr[ran.nextInt(ansArr.length)];
			thisRoomMsg.put("Ans", thisAns);
			allAns.remove(thisAns);
			
			Object[] drowPlayersArr = drowPlayers.toArray();
			String whoDrow = (String) drowPlayersArr[ran.nextInt(drowPlayersArr.length)];
			thisRoomMsg.put("status", "1");
			drowPlayers.remove(whoDrow);
			if(drowPlayers.size()==0) {
				thisRoomMsg.put("status", "0");
			}
			
				//對所有人發遊戲要開始訊息
				GameMessage msg = new GameMessage("GameNotice","遊戲5秒後開始，下一位畫手為:"+whoDrow);
				
				for(Session session:sesset) {	
	    			JSONObject jsonObj = new JSONObject(msg);
	    			session.getAsyncRemote().sendText(jsonObj.toString());
				}
				//倒數5秒後傳答案到本回合畫手
				GameMessage oneMsg = new GameMessage("GameQusetion",thisAns);
				Session targetSession = roomMap.get(whoDrow);
				nt = new NoticeTimer(targetSession,oneMsg,5000);
				nt.start();
				//計時器開始
				GameMessage OverMsg = new GameMessage("gameOver",thisAns);
				gt = new GameTimer(sesset,OverMsg);
				gt.start();
				GameTimers.put(roomNo, gt);
				RoomMsgList.put("DrowPlayers",drowPlayers);
		} 
		//離開房間事件------------------
		if("leaveRoom".equals(type)) {
			String sender = Gmsg.getSender();
			roomMap.remove(sender);
			roomMemSet.remove(sender);
			thisRoomMsg.remove(sender);
			String status = thisRoomMsg.get("status");
			if("1".equals(status)) {
				Set<String> drowPlayers = RoomMsgList.get("DrowPlayers");
				if(drowPlayers.contains(sender)) {
					drowPlayers.remove(sender);
				}
				Integer nowCount = new Integer(thisRoomMsg.get("nowCount"));
				nowCount--;
				thisRoomMsg.put("nowCount", nowCount.toString());
			}
			Collection<Session> allses = roomMap.values();
			//通知所有人，某人已經離開了
			GameMessage msg = new GameMessage("GameNotice",sender+"離開房間囉!");
			for(Session session:allses) {
    			JSONObject jsonObj = new JSONObject(msg);
    			session.getAsyncRemote().sendText(jsonObj.toString());
    		}
			
		}
		//------------這裡處理傳送畫圖座標的部分哦哦哦哦哦哦
		if("drowNow".equals(type)) {
			String sender = Gmsg.getSender();
			//找出除了sender以外的使用者傳出訊息
			for(String name :roomMemSet) {
				if(!name.equals(sender)) {
					Session session = roomMap.get(name);
						session.getAsyncRemote().sendText(message);
				}
			}
			//更新得分表
			Map<String,Integer> pointMap = new ConcurrentHashMap<String,Integer>();
//			roomMsg
			for(String name: roomMemSet) {
				Integer point = new Integer(thisRoomMsg.get(name));
				pointMap.put(name, point);
			}
			GameMessage Pointmsg = new GameMessage("getPoint","更新分數啦!");
			Pointmsg.setPointMap(pointMap);
			
			for(Session session:sesset) {
    			JSONObject jsonObj2 = new JSONObject(Pointmsg);
    			session.getAsyncRemote().sendText(jsonObj2.toString());
    		}
		}
		if("sendAns".equals(type)) {
			String sender = Gmsg.getSender();
			String senderMsg = Gmsg.getMessage();
			//取出答案
			String ans = thisRoomMsg.get("Ans");
			int maxCount = new Integer(thisRoomMsg.get("maxCount"));
			int nowCount = new Integer(thisRoomMsg.get("nowCount"));

			if(ans.equals(senderMsg)) {
				//停止輸入正確玩家的後續行為
				GameMessage StopMsg = new GameMessage("StopSend",sender);
				roomMap.get(sender).getAsyncRemote().sendText(new JSONObject(StopMsg).toString());
				//取出已完成人數，並將其+1放回
				Integer complete = new Integer(thisRoomMsg.get("complete"));
				complete++;
				thisRoomMsg.put("complete", complete.toString());
				//通知所有人，某人已經答對了
				GameMessage msg = new GameMessage("GameNotice",sender+"已經答對囉!");
				for(Session session:sesset) {
	    			JSONObject jsonObj = new JSONObject(msg);
	    			session.getAsyncRemote().sendText(jsonObj.toString());
	    		}
				//更新得分表
				Map<String,Integer> pointMap = new ConcurrentHashMap<String,Integer>();
//				roomMsg
				for(String name: roomMemSet) {
					Integer point = new Integer(thisRoomMsg.get(name));
					if(name.equals(sender)) {
						point += ((nowCount-complete)*3);
						thisRoomMsg.put(name,point.toString());
					}
					pointMap.put(name, point);
				}
				GameMessage Pointmsg = new GameMessage("getPoint","更新分數啦!");
				Pointmsg.setPointMap(pointMap);
				
				for(Session session:sesset) {
	    			JSONObject jsonObj2 = new JSONObject(Pointmsg);
	    			session.getAsyncRemote().sendText(jsonObj2.toString());
	    		}
				GameMessage OverMsg = new GameMessage("gameOver",ans);
				//若所有人都完成，則將遊戲結束傳給所有人
				if(complete==nowCount-1) {
					gt.stop();
					//將結束遊戲訊息傳給所有人
					for(Session session:sesset) {	
		    			JSONObject jsonObj = new JSONObject(OverMsg);
		    			session.getAsyncRemote().sendText(jsonObj.toString());
		    		}
					for(Session session:sesset) {
						GameMessage reStartMsg = new GameMessage("reStart","");
		    			JSONObject jsonObj = new JSONObject(reStartMsg);
		    			session.getAsyncRemote().sendText(jsonObj.toString());
		    			break;
		    		}
				}	
			}else{
				//輸入錯誤區塊
				for(Session session:sesset) {	
					session.getAsyncRemote().sendText(message);
	    		}
			}
		}
		if("sendChat".equals(type)) {
			for(Session session:sesset) {	
				session.getAsyncRemote().sendText(message);
    		}
		}
	}

	@OnError
	public void onError(Session userSession, Throwable e) {
//		System.out.println("Error: " + e.toString());
	}

	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
		System.out.println("我被滾蛋了!");
	}
	
}
