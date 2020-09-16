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
	//�C���ж�(�C�����a�B���aSession)
	private static Map<String, Map<String,Session>> rooms = new ConcurrentHashMap<>();
	//�C���ж�(��T�W�١B��V���e)--²��T��
	private static Map<String, Map<String,String>> GameMsg = new ConcurrentHashMap<>();
	//�C���ж�(��T�W�١B��V���e)--���X�T��
	private static Map<String, Map<String,Set<String>>> GameMsgC = new ConcurrentHashMap<>();
	//�C���ж�(�C���ж��B�p�ɾ�)	
	private static Map<String, GameTimer> GameTimers = new ConcurrentHashMap<>();
	//�C���ж�(�C���ж��B�p�ɾ�)	
	private static Map<String, NoticeTimer> NoticeTimers = new ConcurrentHashMap<>();
	Random ran = new Random();
	//���ε��׸sDAO
	AnswerDAO dao = new AnswerDAO();
	Gson gson = new Gson();

	@OnOpen
	public void onOpen(@PathParam("roomNo") String roomNo,@PathParam("userName") String userName,@PathParam("maxCount") String maxCount, Session userSession) throws IOException, InterruptedException {
		//����ж��S���N�}�@�ӷs�ж�
        if (!rooms.containsKey(roomNo)) {
        	//�Ыةж���T�A��J���aID�PSession
            Map<String,Session> room = new HashMap<String,Session>();
            userSession.setMaxTextMessageBufferSize(500 * 1024);
            room.put(userName,userSession);
            //��J�ж��Ӧ�����T
            Map<String,String> thisRoomMsg = new ConcurrentHashMap<>();
            thisRoomMsg.put("roomHost",userName);
            thisRoomMsg.put("maxCount",maxCount);
            GameMsg.put(roomNo, thisRoomMsg);
            Map<String,Set<String>> m = new HashMap<String,Set<String>>();
            //��J�ж��p�ɾ�
            GameTimer gt = new GameTimer();
            NoticeTimer nt = new NoticeTimer();
            GameTimers.put(roomNo, gt);
            NoticeTimers.put(roomNo, nt);
            //��i�Ҧ����ר�ж���
    		Set<String> AllAns = dao.getAll();
    		m.put("AllAns", AllAns);
    		//�ж���T���s�W�@���ťժ�����
    		thisRoomMsg.put("Ans","");
    		thisRoomMsg.put("status", "0");
    		//�N�i�Ъ̩�i�ж���T(�᭱�O����)
    		thisRoomMsg.put(userName,"0");
    		//�N�ж���T��J�ж��s
    		GameMsgC.put(roomNo, m);
    		rooms.put(roomNo, room);
            GameMessage msg = new GameMessage("roomHost","�A�O�ХD�@~");
            JSONObject jsonObj = new JSONObject(msg);
            userSession.getAsyncRemote().sendText(jsonObj.toString());
        } else {
        	//���ж��NuserName�BuserSession��i�ж�
        	Map<String,Session> roomMap =  rooms.get(roomNo);
        	roomMap.put(userName, userSession);
        	//����ж�����session(�ǻ������T����)
        	Collection<Session> sesset = roomMap.values();
        	//���o�өж����T��MAP����
        	Map<String,String> thisRoomMsg = GameMsg.get(roomNo);
        	Set<String> RoomMemSet = roomMap.keySet();
        	//�N�i�Ъ̩�i�ж���T(�᭱�O����)
        	thisRoomMsg.put(userName,"0");
    		Map<String,Integer> pointMap = new HashMap<String,Integer>();
    		for(String username:RoomMemSet) {
    			pointMap.put(username,new Integer(thisRoomMsg.get(username)));
    			
    		}
    		GameMessage msg = new GameMessage("getPoint","�i�J�ж��o");
    		msg.setPointMap(pointMap);
    		
    		for(Session session:sesset) {	
    			JSONObject jsonObj = new JSONObject(msg);
    			session.getAsyncRemote().sendText(jsonObj.toString());
    		}  
        }
	}	

	@OnMessage
	public void onMessage(Session userSession, String message) throws InterruptedException {
		//���o�Ƕi�Ӫ��T���A��gson�ন�ۭq�qGameMessage����
		GameMessage Gmsg = gson.fromJson(message, GameMessage.class);

		//���o�ж��W��
		String roomNo = Gmsg.getRoomNo();
		//���o�j�U�̪��ж�����
		Map<String,Session> roomMap = rooms.get(roomNo);
		//�q�ж�������o�Ҧ��H���W�ٸ�session
		Set<String> roomMemSet = roomMap.keySet();
		Collection<Session> sesset = roomMap.values();
		//���o�M�ݩж��T��
		Map<String,String> thisRoomMsg = GameMsg.get(roomNo);
		Map<String,Set<String>> RoomMsgList = GameMsgC.get(roomNo);
		//�T�{�Ƕi�Ӫ���T����
		String type = Gmsg.getType();
		//�p�ɾ�����
		GameTimer gt = GameTimers.get(roomNo);
		NoticeTimer nt = NoticeTimers.get(roomNo);
		//---------------�C���}�l�϶�(�ХD���U�}�l),��l�ж��T��----------------------------
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
			
			//��l�Ƨ����H�ưѼ�
			thisRoomMsg.put("complete", "0");
			//���X���׶��X�A���@�ӭԧR����^
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
			
				//��Ҧ��H�o�C���n�}�l�T��
				GameMessage msg = new GameMessage("GameNotice","�C��5���}�l�A�U�@��e�⬰:"+whoDrow);
				
				for(Session session:sesset) {	
	    			JSONObject jsonObj = new JSONObject(msg);
	    			session.getAsyncRemote().sendText(jsonObj.toString());
				}
				//�˼�5���ǵ��ר쥻�^�X�e��
				GameMessage oneMsg = new GameMessage("GameQusetion",thisAns);
				Session targetSession = roomMap.get(whoDrow);
				nt = new NoticeTimer(targetSession,oneMsg,5000);
				nt.start();
				//�p�ɾ��}�l
				GameMessage OverMsg = new GameMessage("gameOver",thisAns);
				gt = new GameTimer(sesset,OverMsg);
				gt.start();
				GameTimers.put(roomNo, gt);
				RoomMsgList.put("DrowPlayers",drowPlayers);
		} 
		//���}�ж��ƥ�------------------
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
			//�q���Ҧ��H�A�Y�H�w�g���}�F
			GameMessage msg = new GameMessage("GameNotice",sender+"���}�ж��o!");
			for(Session session:allses) {
    			JSONObject jsonObj = new JSONObject(msg);
    			session.getAsyncRemote().sendText(jsonObj.toString());
    		}
			
		}
		//------------�o�̳B�z�ǰe�e�Ϯy�Ъ������@�@�@�@�@�@
		if("drowNow".equals(type)) {
			String sender = Gmsg.getSender();
			//��X���Fsender�H�~���ϥΪ̶ǥX�T��
			for(String name :roomMemSet) {
				if(!name.equals(sender)) {
					Session session = roomMap.get(name);
						session.getAsyncRemote().sendText(message);
				}
			}
			//��s�o����
			Map<String,Integer> pointMap = new ConcurrentHashMap<String,Integer>();
//			roomMsg
			for(String name: roomMemSet) {
				Integer point = new Integer(thisRoomMsg.get(name));
				pointMap.put(name, point);
			}
			GameMessage Pointmsg = new GameMessage("getPoint","��s���ư�!");
			Pointmsg.setPointMap(pointMap);
			
			for(Session session:sesset) {
    			JSONObject jsonObj2 = new JSONObject(Pointmsg);
    			session.getAsyncRemote().sendText(jsonObj2.toString());
    		}
		}
		if("sendAns".equals(type)) {
			String sender = Gmsg.getSender();
			String senderMsg = Gmsg.getMessage();
			//���X����
			String ans = thisRoomMsg.get("Ans");
			int maxCount = new Integer(thisRoomMsg.get("maxCount"));
			int nowCount = new Integer(thisRoomMsg.get("nowCount"));

			if(ans.equals(senderMsg)) {
				//�����J���T���a������欰
				GameMessage StopMsg = new GameMessage("StopSend",sender);
				roomMap.get(sender).getAsyncRemote().sendText(new JSONObject(StopMsg).toString());
				//���X�w�����H�ơA�ñN��+1��^
				Integer complete = new Integer(thisRoomMsg.get("complete"));
				complete++;
				thisRoomMsg.put("complete", complete.toString());
				//�q���Ҧ��H�A�Y�H�w�g����F
				GameMessage msg = new GameMessage("GameNotice",sender+"�w�g�����o!");
				for(Session session:sesset) {
	    			JSONObject jsonObj = new JSONObject(msg);
	    			session.getAsyncRemote().sendText(jsonObj.toString());
	    		}
				//��s�o����
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
				GameMessage Pointmsg = new GameMessage("getPoint","��s���ư�!");
				Pointmsg.setPointMap(pointMap);
				
				for(Session session:sesset) {
	    			JSONObject jsonObj2 = new JSONObject(Pointmsg);
	    			session.getAsyncRemote().sendText(jsonObj2.toString());
	    		}
				GameMessage OverMsg = new GameMessage("gameOver",ans);
				//�Y�Ҧ��H�������A�h�N�C�������ǵ��Ҧ��H
				if(complete==nowCount-1) {
					gt.stop();
					//�N�����C���T���ǵ��Ҧ��H
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
				//��J���~�϶�
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
		System.out.println("�ڳQ�u�J�F!");
	}
	
}
