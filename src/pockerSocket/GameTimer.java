package pockerSocket;

import java.util.Collection;

import javax.websocket.Session;

import org.json.JSONObject;

import com.game.model.GameMessage;

public class GameTimer extends Thread{
	Collection<Session> sesset;
	GameMessage OverMsg;
	
	public GameTimer() {
	}
	public GameTimer(Collection<Session> sesset,GameMessage OverMsg) {
		this.sesset = sesset;
		this.OverMsg = OverMsg;
	}
	//�p��1����
	public void run() {
		try {
			sleep(4000);
			GameMessage notice0 = new GameMessage("GameAction","");
			JSONObject jsonObj0 = new JSONObject(notice0);
			for(Session session: sesset) {
				synchronized (session) {
					session.getAsyncRemote().sendText(jsonObj0.toString());
				}
			}
			sleep(30000);
			GameMessage notice1 = new GameMessage("GameNotice","�ٳ�30��A�Х[�o");
			JSONObject jsonObj1 = new JSONObject(notice1);
			for(Session session: sesset) {
				synchronized (session) {
					session.getAsyncRemote().sendText(jsonObj1.toString());
				}
			}
			sleep(20000);
			GameMessage notice2 = new GameMessage("GameNotice","�ٳ�10��A�Х[�o");
			JSONObject jsonObj2 = new JSONObject(notice2);
			for(Session session: sesset) {
				synchronized (session) {
					session.getAsyncRemote().sendText(jsonObj2.toString());
				}
			}
			sleep(5000);
			GameMessage notice3 = new GameMessage("GameNotice","�ٳ�5��A�Х[�o");
			JSONObject jsonObj3 = new JSONObject(notice3);
			for(Session session: sesset) {
				synchronized (session) {
					session.getAsyncRemote().sendText(jsonObj3.toString());
				}
			}
			for(int i = 4 ; i > 0 ; i--) {
				sleep(1000);
				for(Session session: sesset) {
					GameMessage notice4 = new GameMessage("GameNotice","�ٳ�"+i+"��A�Х[�o");
					JSONObject jsonObj = new JSONObject(notice4);
					synchronized (session) {
						session.getAsyncRemote().sendText(jsonObj.toString());
					}
				}
			}
			sleep(1000);
			for(Session session:sesset) {	
    			JSONObject jsonObj = new JSONObject(OverMsg);
    			synchronized (session) {
					session.getAsyncRemote().sendText(jsonObj.toString());
				}
    		}
			for(Session session:sesset) {
				GameMessage reStartMsg = new GameMessage("reStart","");
    			JSONObject jsonObj = new JSONObject(reStartMsg);
    			session.getAsyncRemote().sendText(jsonObj.toString());
    			break;
    		}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
