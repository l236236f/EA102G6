package pockerSocket;

import java.util.Collection;

import javax.websocket.Session;

import org.json.JSONObject;

import com.game.model.GameMessage;

public class NoticeTimer extends Thread {
	Collection<Session> sesset = null;
	Session session = null;
	GameMessage Msg;
	int second;
	
	public NoticeTimer() {
	}
	//傳全部房間
	public NoticeTimer(Collection<Session> sesset,GameMessage Msg,int second) {
		this.sesset = sesset;
		this.Msg = Msg;
		this.second = second;
	}
	//傳個人(答案
	public NoticeTimer(Session session,GameMessage Msg,int second) {
		this.session = session;
		this.Msg = Msg;
		this.second = second;
	}
	public void run() {
		
		try {
			sleep(second);
			if(sesset!= null) {
				for(Session session: sesset) {
					JSONObject jsonObj = new JSONObject(Msg);
					session.getAsyncRemote().sendText(jsonObj.toString());
				}
			}
			if(session != null) {
				JSONObject jsonObj = new JSONObject(Msg);
				session.getAsyncRemote().sendText(jsonObj.toString());
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
