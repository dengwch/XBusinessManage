package com.fh.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry; 

import org.apache.commons.collections.map.ListOrderedMap;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
public class Utils {  
	public static List<Map<String, Object>> parseJSON2List(String jsonStr){
        JSONArray jsonArr = JSONArray.fromObject(jsonStr);
        List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
        Iterator<JSONObject> it = jsonArr.iterator();
        while(it.hasNext()){
            JSONObject json2 = it.next();
            list.add(parseJSON2Map(json2.toString()));
        }
        return list;
    } 
	   /**
	    * 
	   * json转换map.
	   * <br>详细说明
	   * @param jsonStr json字符串
	   * @return
	   * @return Map<String,Object> 集合
	   * @throws
	   * @author slj
	    */
	    public static Map<String, Object> parseJSON2Map(String jsonStr){
	        ListOrderedMap map = new ListOrderedMap();
	        //最外层解析
	        JSONObject json = JSONObject.fromObject(jsonStr);
	        for(Object k : json.keySet()){
	            Object v = json.get(k); 
	            //如果内层还是数组的话，继续解析
	            if(v instanceof JSONArray){
	                List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
	                Iterator<JSONObject> it = ((JSONArray)v).iterator();
	                while(it.hasNext()){
	                    JSONObject json2 = it.next();
	                    list.add(parseJSON2Map(json2.toString()));
	                }
	                map.put(k.toString(), list);
	            } else {
	                map.put(k.toString(), v);
	            }
	        }
	        return map;
	    }
}