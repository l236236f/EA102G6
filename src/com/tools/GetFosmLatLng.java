
package com.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fosmon.model.FosmService;
import com.fosmon.model.FosmVO;

public class GetFosmLatLng {
	private static final String GOOGLE_URL = 
			"https://maps.googleapis.com/maps/api/geocode/json?"
			+ "key=AIzaSyAftybVRd3QKMfCi7ke-w5YIEfaoNuBAD0&"
			+ "address="/*+memVO.getMemAddr*/;

	public FosmVO getLat(String memNo,String memAddr) throws IOException, JSONException {
		//將傳進來的地址轉成UTF-8編碼->GOOGLE API要用
		String str = java.net.URLEncoder.encode(memAddr, "UTF-8");
		StringBuilder sb = new StringBuilder();
		String selfUrl = GOOGLE_URL + str;
		URL url = new URL(selfUrl);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		con.setUseCaches(false);
		
		FosmVO fosmVO = new FosmVO();
		fosmVO.setMemNo(memNo);	
//		int statusCode = con.getResponseCode();		
		InputStream is = con.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		
		String data;
		while ((data = br.readLine()) != null) {
			sb.append(data);
		}
		JSONObject jsonObj = new JSONObject(sb.toString());
		
		br.close();
		isr.close();
		is.close();
		
		JSONArray jsonArray_Addrs = jsonObj.getJSONArray("results");
		for (int i = 0; i < jsonArray_Addrs.length(); i++) {
			JSONObject jsonObj1 = jsonArray_Addrs.getJSONObject(i);
			JSONObject jsonObj2 = jsonObj1.getJSONObject("geometry");
			JSONObject jsonObj3 = jsonObj2.getJSONObject("location");
			fosmVO.setFosmLat(jsonObj3.getDouble("lat"));
			fosmVO.setFosmLng(jsonObj3.getDouble("lng"));
		}	
		return fosmVO;
	}
}
