package idv.david.websocketchat.jedis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import com.google.gson.Gson;

import idv.david.websocketchat.jedis.JedisHandleMessage;
import idv.david.websocketchat.model.ChatMessage;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisHandleMessage {
	// 此範例key的設計為(發送者名稱:接收者名稱)，實際應採用(發送者會員編號:接收者會員編號)

	private static JedisPool pool = JedisPoolUtil.getJedisPool();

	public static List<String> getHistoryMsg(String sender, String receiver) {
		Gson gson = new Gson();
		
		
		
		
		String key = new StringBuilder(sender).append(":").append(receiver).toString();
		String key2 = new StringBuilder(receiver).append(":").append(sender).toString();
		Jedis jedis = null;
		jedis = pool.getResource();
		jedis.auth("123456");
		List<String> Data = jedis.lrange(key2, 0, -1);
		List<String> array = new ArrayList<String>();
		for (String str : Data) {
			ChatMessage mesg = gson.fromJson(str, ChatMessage.class);
			mesg.setMesgStatus("beRead");
			array.add(gson.toJson(mesg));
		}
		
		if("Robot".equals(receiver)) {
			List<String> Data1 = jedis.lrange(key2, 0, -1);
			List<String> array1 = new ArrayList<String>();
			for (String str : Data1) {
				ChatMessage mesg1 = gson.fromJson(str, ChatMessage.class);
				mesg1.setMesgStatus("beRead");
				array1.add(gson.toJson(mesg1));
			}
			for (int i = 0; i < array1.size(); i++) {
				jedis.lset(key, i, array1.get(i));
			}
		}

		for (int i = 0; i < array.size(); i++) {
			jedis.lset(key2, i, array.get(i));
		}
		List<String> historyData = jedis.lrange(key, 0, -1);
		jedis.close();
		return historyData;
	}

	public static void saveChatMessage(String sender, String receiver, String message) {
		// 對雙方來說，都要各存著歷史聊天記錄

		String senderKey = new StringBuilder(sender).append(":").append(receiver).toString();
		String receiverKey = new StringBuilder(receiver).append(":").append(sender).toString();
		Jedis jedis = pool.getResource();
		jedis.auth("123456");
		jedis.rpush(senderKey, message);
		jedis.rpush(receiverKey, message);

		jedis.close();
	}

	public static HashMap<String, String> getLastHistoryMsg(String sender) {

		Jedis jedis = null;
		jedis = pool.getResource();
		jedis.auth("123456");
		String findkey = new StringBuilder("*:").append(sender).toString();
		Set<String> key = jedis.keys(findkey);

		HashMap<String, String> map = new HashMap<String, String>();
		for (String obj : key) {
			Long a = jedis.llen(obj);
			map.put(obj, jedis.lindex(obj, a - 1));
		}
		jedis.close();
		return map;
	}

	public static HashMap<String, Integer> getUnReadNum(String sender) {

		Gson gson = new Gson();
		Jedis jedis = null;
		jedis = pool.getResource();
		jedis.auth("123456");
		String findkey = new StringBuilder("*:").append(sender).toString();
		Set<String> key = jedis.keys(findkey);

		HashMap<String, Integer> map = new HashMap<String, Integer>();
		for (String obj : key) {
			int i = 0;
			List<String> Data = jedis.lrange(obj, 0, -1);
			
			for (String str : Data) {
				
				ChatMessage mesg = gson.fromJson(str, ChatMessage.class);
				if ("unRead".equals(mesg.getMesgStatus())) {
					i++;
				}
			}
			map.put(obj, i);
		}
		jedis.close();
		return map;
	}

	public static String getOneLastHistory(String sender, String receiver) {
		Jedis jedis = null;
		jedis = pool.getResource();
		jedis.auth("123456");
		String key = new StringBuilder(sender).append(":").append(receiver).toString();
		Long a = jedis.llen(key);
		String lastMsg = jedis.lindex(key, a - 1);

		jedis.close();
		return lastMsg;
	}

	public static String getOneUnReadNum(String sender, String receiver) {
		Gson gson = new Gson();
		Jedis jedis = null;
		jedis = pool.getResource();
		jedis.auth("123456");
		String key = new StringBuilder(receiver).append(":").append(sender).toString();
		
		List<String> Data = jedis.lrange(key, 0, -1);
		int i = 0;
		for (String str : Data) {
			ChatMessage mesg = gson.fromJson(str, ChatMessage.class);
			if ("unRead".equals(mesg.getMesgStatus())) {
				i++;
			}
		}
		jedis.close();
		return Integer.toString(i);
	}
}
