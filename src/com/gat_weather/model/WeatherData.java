package com.gat_weather.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class WeatherData {

	private static final String weather_url = "https://opendata.cwb.gov.tw/api/v1/rest/datastore/F-C0032-001?Authorization=CWB-02D0D1ED-78FF-4700-BEAD-F3ABE7C92EEF";

	public Map<String, List<WeatherVO>> getWeatherDate() throws IOException, JSONException {
		Map weatherTypeCode = new HashMap();
		try {
			// �q��J�y�Ы�Workbook
			Workbook workbook = Workbook.getWorkbook(new File(
					"C:\\EA102_WebApp\\eclipse_WTP_workspace1\\EA102G6\\WebContent\\front-end\\gat\\weather\\weatherTypeCode.xls"));
			// ��Workbook��getSheet(0)��k��ܲĤ@�Ӥu�@��]�q0�}�l�^
			Sheet sheet1 = workbook.getSheet(0);
			// ���oSheet���ҥ]�t���`row��
			int rows = sheet1.getRows();
			// ���oSheet���ҥ]�t���`column��
			int columns = sheet1.getColumns();

			for (int i = 0; i < rows; i++) {
				String key = null;
				String value = null;
				for (int j = 0; j < columns; j++) {
					// Sheet��k��getCell(j, i)��k��ܦ�m��(j, i)���椸��]��ӰѼƳ��q0�}�l�^
					Cell cell = sheet1.getCell(j, i);
					// Cell��getContents��k��椸�椤���H���H�r�Ū��Φ�Ū���X��
					String contents = cell.getContents();
					if (j == 0) {
						key = contents;
					} else {
						value = contents;
					}
					weatherTypeCode.put(key, value);
				}
			}
			workbook.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
//--------------------------------------------------------------------------------------------------------

		Map<String, List<WeatherVO>> weatherMap = new HashMap<String, List<WeatherVO>>();
		StringBuilder strb = new StringBuilder();

		URL url = new URL(weather_url);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		con.setUseCaches(false);
		System.out.println("ResponseCode: " + con.getResponseCode());

		InputStream is = con.getInputStream();
		InputStreamReader isr = new InputStreamReader(is, "UTF-8");
		BufferedReader br = new BufferedReader(isr);

		String data;
		while ((data = br.readLine()) != null) {
			strb.append(data);
		}

		String rawData = strb.toString();
		// parse json
		JSONObject json = new JSONObject(rawData);
		JSONObject records = (JSONObject) json.get("records");
		JSONArray location = (JSONArray) records.getJSONArray("location");

		// �H�U�}�l�O�U�m����ơA�C�Ӷm���ƬO�@��JSONObject
		int allLoc = location.length();
		for (int i = 0; i < allLoc; i++) {
			List<WeatherVO> list = new ArrayList<WeatherVO>();
			JSONObject county = (JSONObject) location.get(i); // �Ĥ@�Ӷm�� ���L��
			Object countyName = county.get("locationName");

			JSONArray weatherElement = (JSONArray) county.getJSONArray("weatherElement"); // �U�m��Ѯ�array
				JSONObject WeatherDescriptionArray_0 = (JSONObject) weatherElement.get(0); // �Ѯ�y�zarray
				JSONObject WeatherDescriptionArray_2 = (JSONObject) weatherElement.get(2);	//���
				JSONObject WeatherDescriptionArray_3 = (JSONObject) weatherElement.get(3);	//��P
				JSONArray WeatherDescription_0 = (JSONArray) WeatherDescriptionArray_0.getJSONArray("time"); // �Ѯ�y�z
				JSONArray WeatherDescription_2 = (JSONArray) WeatherDescriptionArray_2.getJSONArray("time");
				JSONArray WeatherDescription_3 = (JSONArray) WeatherDescriptionArray_3.getJSONArray("time");
				for (int j = 0; j < WeatherDescription_0.length(); j++) {
					WeatherVO weatherVO = new WeatherVO();
					weatherVO.setCounty(countyName.toString());
					JSONObject WeatherDescription_First = (JSONObject) WeatherDescription_0.get(j); // �Ĥ@�Ӯɶ� 08/22 06:00 
					JSONObject WeatherDescription_Second = (JSONObject) WeatherDescription_2.get(j);
					JSONObject WeatherDescription_Third = (JSONObject) WeatherDescription_3.get(j);
					Object starttime = WeatherDescription_First.get("startTime");
					Object endtime = WeatherDescription_First.get("endTime");
					
					JSONObject elementValue = (JSONObject) WeatherDescription_First.get("parameter");
					String type = (String) elementValue.get("parameterName"); // �Ѯ�y�z
						weatherVO.setStarttime(starttime.toString().substring(0, 10));
						weatherVO.setEndtime(endtime.toString());
						weatherVO.setTypeCode((String) weatherTypeCode.get(type));
						weatherVO.setType(type);
					JSONObject elementValue_sec = (JSONObject) WeatherDescription_Second.get("parameter");
					String type_sec = (String) elementValue_sec.get("parameterName");
						weatherVO.setTemperature(type_sec);
					JSONObject elementValue_thi = (JSONObject) WeatherDescription_Third.get("parameter");
					String type_thi = (String) elementValue_thi.get("parameterName");
						weatherVO.setTotalDescription(type_thi);
					list.add(weatherVO);
				}
			weatherMap.put(countyName.toString(), list);
		}
		br.close();
		isr.close();
		is.close();
		return weatherMap;
	}

	public static void main(String[] args) throws IOException, JSONException {
		WeatherData dao = new WeatherData();

		Map<String, List<WeatherVO>> map = dao.getWeatherDate();
		Set<String> keySet = map.keySet();
		System.out.println(keySet);
		List<WeatherVO> list = map.get("��饫");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getCounty());
			System.out.println(list.get(i).getType());
			System.out.println(list.get(i).getTemperature());
			System.out.println(list.get(i).getTotalDescription());
			System.out.println(list.get(i).getStarttime());
		}
	}
}
