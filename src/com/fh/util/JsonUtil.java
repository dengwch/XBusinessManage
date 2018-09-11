package com.fh.util;

import java.util.HashMap;

import com.google.gson.Gson;
   
public class JsonUtil {
	public static String ToJson(Object o) {
		Gson gson = new Gson();
		String json = gson.toJson(o);
        System.out.println(json);
        return json;
	}
	
	public static void main(String[] arg0s){
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("name", "liuyu");
		map.put("age", "25");
		HashMap<String, String> map2 = new HashMap<String, String>();
		map2.put("name", "liuyu");
		map2.put("age", "25");
		map.put("map2", map2);
		ToJson(map);
	}
}
