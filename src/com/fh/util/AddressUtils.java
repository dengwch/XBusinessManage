package com.fh.util;
import java.io.BufferedReader;  
import java.io.IOException;  
import java.io.InputStreamReader;  
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;  
import java.net.URLConnection;
import java.util.HashMap;  
import java.util.LinkedHashMap;
import java.util.Map;  
import java.net.URLEncoder;
import java.util.Map.Entry;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;  
import java.net.URLEncoder;  
import org.apache.commons.lang.StringUtils;  
public class AddressUtils {  
  
  
	private static String KEY_1 = "QsBdUKQTUViOEXubCy07Dh11Y6VwhWcS";  //百度
    private static double EARTH_RADIUS = 6378.137; 
    private static String KEY = "DXVBZ-DI4WG-IRJQF-ITJO5-2NTDK-VJFBL";  //腾讯
    private static double rad(double d) { 
        return d * Math.PI / 180.0; 
    }
    /**
     * 根据两个位置的经纬度，来计算两地的距离（单位为KM）
     * 参数为String类型
     * @param longitude1 用户经度
     * @param latitude1 用户纬度
     * @param longitude2 商家经度
     * @param latitude2 商家纬度
     * @return
     */
    public static String getDistance(String longitude1, String latitude1, String longitude2, String latitude2) {
        Double lat1 = Double.parseDouble(longitude1);
        Double lng1 = Double.parseDouble(latitude1);
        Double lat2 = Double.parseDouble(longitude2);
        Double lng2 = Double.parseDouble(latitude2);
         
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double difference = radLat1 - radLat2;
        double mdifference = rad(lng1) - rad(lng2);
        double distance = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(difference / 2), 2)
                + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.pow(Math.sin(mdifference / 2), 2)));
        distance = distance * EARTH_RADIUS;
        distance = Math.round(distance * 10000) / 10000;
        String distanceStr = distance+"";
        distanceStr = distanceStr.
            substring(0, distanceStr.indexOf("."));
         
        return distanceStr;
    }
     
    /**
     * 获取当前用户一定距离以内的经纬度值
     * 单位米 return minLat
     * 最小经度 minLng
     * 最小纬度 maxLat
     * 最大经度 maxLng
     * 最大纬度 minLat
     */
    public static Map getAround(String latStr, String lngStr, String raidus) {
        Map map = new HashMap();
         
        Double latitude = Double.parseDouble(latStr);// 传值给经度
        Double longitude = Double.parseDouble(lngStr);// 传值给纬度
 
        Double degree = (24901 * 1609) / 360.0; // 获取每度
        double raidusMile = Double.parseDouble(raidus);
         
        Double mpdLng = Double.parseDouble((degree * Math.cos(latitude * (Math.PI / 180))+"").replace("-", ""));
        Double dpmLng = 1 / mpdLng;
        Double radiusLng = dpmLng * raidusMile;
        //获取最小经度
        Double minLat = longitude - radiusLng;
        // 获取最大经度
        Double maxLat = longitude + radiusLng;
         
        Double dpmLat = 1 / degree;
        Double radiusLat = dpmLat * raidusMile;
        // 获取最小纬度
        Double minLng = latitude - radiusLat;
        // 获取最大纬度
        Double maxLng = latitude + radiusLat;
         
        map.put("minLat", minLat+"");
        map.put("maxLat", maxLat+"");
        map.put("minLng", minLng+"");
        map.put("maxLng", maxLng+"");
         
        return map;
    }
    /**
     * 获取经纬度百度
     * @param address
     * @return
     */
      public static Map<String,Double> getPoiLngAndLat(String address){
    	  	Map<String,Double> map=new HashMap<String, Double>();
    	  	try {
	    	  	String json1 = loadJSON("http://api.map.baidu.com/place/v2/search?query="+address+"&page_size=10&page_num=0&scope=1&region=全国&output=json&ak=QsBdUKQTUViOEXubCy07Dh11Y6VwhWcS");
	     	 	System.out.println(json1);
	     	 	JSONObject obj = JSONObject.fromObject(json1);
	     	 	System.out.println(obj);
	     	 	if(String.valueOf(obj.get("status")).equals("0")){
		           	JSONArray jsonArray = JSONArray.fromObject(obj.get("results"));
		       		JSONObject juliobj = JSONObject.fromObject(jsonArray.get(0));
		       		JSONObject juliobj1 = JSONObject.fromObject(juliobj.get("location"));
		       		map.put("longitude", getDecimal(juliobj1.getDouble("lng")));
	                map.put("latitude", getDecimal(juliobj1.getDouble("lat")));
		       		//System.out.println(getTwopointsDistance(""+117.10978, ""+39.067528, ""+juliobj1.get("lng"), ""+juliobj1.get("lat"))); 	
	           }else{
	               System.out.println("未找到相匹配的经纬度！");
	           }
    	  	}catch (Exception e){
            	System.out.println("未找到相匹配的经纬度！");
            }
          return map;
      }
  /**
   * 获取经纬度
   * @param address
   * @return
   */
    public static Map<String,Double> getLngAndLat(String address){
        Map<String,Double> map=new HashMap<String, Double>();
        String url = "http://api.map.baidu.com/geocoder/v2/?address="+address+"&output=json&ak=QsBdUKQTUViOEXubCy07Dh11Y6VwhWcS";
        try {
            String json = loadJSON(url);
            JSONObject obj = JSONObject.fromObject(json);
            if(obj.get("status").toString().equals("0")){
                double lng=obj.getJSONObject("result").getJSONObject("location").getDouble("lng");
                double lat=obj.getJSONObject("result").getJSONObject("location").getDouble("lat");
                map.put("longitude", getDecimal(lng));
                map.put("latitude", getDecimal(lat));
                //System.out.println("经度："+lng+"---纬度："+lat);
            }else{
                System.out.println("未找到相匹配的经纬度！");
            }
        }catch (Exception e){
        	System.out.println("未找到相匹配的经纬度！");
        }
        return map;
    }

    public static double getDecimal(double num) {
        if (Double.isNaN(num)) {
            return 0;
        }
        BigDecimal bd = new BigDecimal(num);
        num = bd.setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue();
        return num;
    }

    public static String loadJSON (String url) {
        StringBuilder json = new StringBuilder();
        try {
            URL oracle = new URL(url);
            URLConnection yc = oracle.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    yc.getInputStream(),"UTF-8"));
            String inputLine = null;
            while ( (inputLine = in.readLine()) != null) {
                json.append(inputLine);
            }
            in.close();
        } catch (MalformedURLException e) {
        } catch (IOException e) {
        }
        return json.toString();
    }
    /** 
     * 返回输入地址的经纬度坐标 
     * key lng(经度),lat(纬度) 
     */  
    public static Map<String,String> getGeocoderLatitude(String address){  
        BufferedReader in = null;  
        try {  
            //将地址转换成utf-8的16进制  
            address = URLEncoder.encode(address, "UTF-8");  
            URL tirc = new URL("http://api.map.baidu.com/geocoder?address="+ address +"&output=json&key="+ KEY_1);  
  
  
            in = new BufferedReader(new InputStreamReader(tirc.openStream(),"UTF-8"));  
            String res;  
            StringBuilder sb = new StringBuilder("");  
            while((res = in.readLine())!=null){  
                sb.append(res.trim());  
            }  
            String str = sb.toString();  
            Map<String,String> map = null;  
            if(StringUtils.isNotEmpty(str)){  
                int lngStart = str.indexOf("lng\":");  
                int lngEnd = str.indexOf(",\"lat");  
                int latEnd = str.indexOf("},\"precise");  
                if(lngStart > 0 && lngEnd > 0 && latEnd > 0){  
                    String longitude = str.substring(lngStart+5, lngEnd);  
                    String latitude = str.substring(lngEnd+7, latEnd);  
                    map = new HashMap<String,String>();  
                    map.put("longitude", longitude);  
                    map.put("latitude", latitude);  
                    return map;  
                }  
            }  
        }catch (Exception e) {  
            e.printStackTrace();  
        }finally{  
            try {  
                in.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
        return null;  
    }  
    /**
     * aliyun
     * 返回输入经纬度坐标的地址
     * @param log
     * @param lat
     * @return
     */
    public static String getAliYunAdd(String log, String lat ){  
        //lat 小  log  大  
        //参数解释: 纬度,经度 type 001 (100代表道路，010代表POI，001代表门址，111可以同时显示前三项) 
    	//String urlString = "http://api.map.baidu.com/geocoder/v2/?ak=QsBdUKQTUViOEXubCy07Dh11Y6VwhWcS&location=30.548397,104.04701&output=json&pois=1"; 
        String urlString = "http://gc.ditu.aliyun.com/regeocoding?l="+lat+","+log+"&type=010";  
        String res = "";     
        try {     
            URL url = new URL(urlString);    
            java.net.HttpURLConnection conn = (java.net.HttpURLConnection)url.openConnection();    
            conn.setDoOutput(true);    
            conn.setRequestMethod("POST");    
            java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(conn.getInputStream(),"UTF-8"));    
            String line;    
           while ((line = in.readLine()) != null) {    
               res += line+"\n";    
         }    
            in.close();    
        } catch (Exception e) {    
            System.out.println("error in wapaction,and e is " + e.getMessage());    
        }   
        System.out.println(res);  
        return res;    
    }  
    /**
     * 返回输入经纬度坐标的地址
     * @param log
     * @param lat
     * @return
     */
    public static String getBDAdd(String log, String lat ){  
        //lat 小  log  大  
        //参数解释: 纬度,经度 type 001 (100代表道路，010代表POI，001代表门址，111可以同时显示前三项) 
    	String urlString = "http://api.map.baidu.com/geocoder/v2/?ak="+ KEY_1+"&location="+lat+","+log+"&output=json&pois=1"; 
        //String urlString = "http://gc.ditu.aliyun.com/regeocoding?l="+lat+","+log+"&type=010";  
        String res = "";     
        try {     
            URL url = new URL(urlString);    
            java.net.HttpURLConnection conn = (java.net.HttpURLConnection)url.openConnection();    
            conn.setDoOutput(true);    
            conn.setRequestMethod("POST");    
            java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(conn.getInputStream(),"UTF-8"));    
            String line;    
           while ((line = in.readLine()) != null) {    
               res += line+"\n";    
         }    
            in.close();    
        } catch (Exception e) {    
            System.out.println("error in wapaction,and e is " + e.getMessage());    
        }   
        System.out.println(res);  
        return res;    
    }  
    
   
      
    /**
     * 获取经纬度腾讯api
     * @param address
     * @return
     */
      public static Map<String,Double> getTXLngAndLat(String address){
          Map<String,Double> map=new HashMap<String, Double>();
          String url ="http://apis.map.qq.com/ws/geocoder/v1/?address="+address+"&key="+KEY;
          //String url = "http://api.map.baidu.com/geocoder/v2/?address="+address+"&output=json&ak=QsBdUKQTUViOEXubCy07Dh11Y6VwhWcS";
          try {
              String json = loadJSON(url);
              JSONObject obj = JSONObject.fromObject(json);
              if(obj.get("status").toString().equals("0")){
                  double lng=obj.getJSONObject("result").getJSONObject("location").getDouble("lng");
                  double lat=obj.getJSONObject("result").getJSONObject("location").getDouble("lat");
                  map.put("longitude", getDecimal(lng));
                  map.put("latitude", getDecimal(lat));
                  //System.out.println("经度："+lng+"---纬度："+lat);
              }else{
                  System.out.println("未找到相匹配的经纬度！");
              }
          }catch (Exception e){
          	System.out.println("未找到相匹配的经纬度！");
          }
          return map;
      }
      /**
       * 获取距离腾讯api 一对一 或者多对多
       * @param longitude1经度
	   *    from	   是	起点坐标，格式：lat,lng;lat,lng... 
	   * 	（经度与纬度用英文逗号分隔，坐标间用英文分号分隔）	from=39.071510,117.190091
	   * 	to	是	终点坐标，格式：lat,lng;lat,lng... 
	   * 	（经度与纬度用英文逗号分隔，坐标间用英文分号分隔）
	   * 	注意：本服务支持单起点到多终点，或多起点到单终点，from和to参数仅可有一个为多坐标	to=39.071510,117.190091;40.007632,
	   * 	116.389160;39.840177,116.463318
       * @param latitude1纬度
       * @param longitude2
       * @param latitude2
       * @return
       */
        public static String getTXjuli(String longitude1, String latitude1, String longitude2, String latitude2){
            Map<String,Double> map=new HashMap<String, Double>();
            String url ="http://apis.map.qq.com/ws/distance/v1/?mode=driving&from="+latitude1+","+longitude1+"&to="+latitude2+","+longitude2+"&key="+KEY;
            String juliString="";
            try {
                String json = loadJSON(url);
                JSONObject obj = JSONObject.fromObject(json);
                
                if(obj.get("status").toString().equals("0")){
                	JSONObject resultobj = obj.getJSONObject("result");
                	JSONArray jsonArray = JSONArray.fromObject(resultobj.getString("elements"));
                	for (int i = 0; i < jsonArray.size(); i++) {
                		JSONObject juliobj = JSONObject.fromObject(jsonArray.get(i));
                		BigDecimal b1 = new BigDecimal(Double.valueOf(juliobj.getString("distance")));
                        BigDecimal b2 = new BigDecimal(Double.valueOf(1000));
                        juliString=String.valueOf(b1.divide(b2).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue()); 
                		
					}
                }else{
                    System.out.println("未找到相匹配的经纬度！");
                }
            }catch (Exception e){
            	System.out.println("未找到相匹配的经纬度！");
            }
            return juliString;
        }
        
        /**
         * 返回输入经纬度坐标的地址
         * @param log
         * @param lat
         * @return
         */
        public static String getTXAddress(String log, String lat ){  
            //lat 小  log  大  
            //参数解释: 纬度,经度 type 001 (100代表道路，010代表POI，001代表门址，111可以同时显示前三项) 
        	
        	String urlString = "http://apis.map.qq.com/ws/geocoder/v1/?location="+lat+","+log+"&key="+ KEY+"&get_poi=1"; 
            //String urlString = "http://gc.ditu.aliyun.com/regeocoding?l="+lat+","+log+"&type=010";  
            String res = "";     
            try {     
                URL url = new URL(urlString);    
                java.net.HttpURLConnection conn = (java.net.HttpURLConnection)url.openConnection();    
                conn.setDoOutput(true);    
                conn.setRequestMethod("GET");    
                java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(conn.getInputStream(),"UTF-8"));    
                String line;    
               while ((line = in.readLine()) != null) {    
                   res += line+"\n";    
             }    
                in.close();    
            } catch (Exception e) {    
                System.out.println("error in wapaction,and e is " + e.getMessage());    
            }   
            System.out.println(res);  
            return res;    
        }  
        
        /**
         * 对Map内所有value作utf8编码，拼接返回结果
         * @param data 参数的封装
         * @return 拼接的访问字符串的一部分如：address=%E7%99%BE%E5%BA%A6%E5%A4%A7%E5%8E%A6&output=json&ak=yourakyoursk
         * @throws UnsupportedEncodingException
         */
        public static String toQueryString(Map<?, ?> data) throws UnsupportedEncodingException {
            StringBuffer queryString = new StringBuffer();
            for (Entry<?, ?> pair : data.entrySet()) {
                queryString.append(pair.getKey() + "=");
                queryString.append(URLEncoder.encode((String) pair.getValue(), "UTF-8") + "&");
            }
            if (queryString.length() > 0) {
                queryString.deleteCharAt(queryString.length() - 1);
            }
            return queryString.toString();
        }

        /**
         * 来自stackoverflow的MD5计算方法，调用了MessageDigest库函数，并把byte数组结果转换成16进制
         * @param md5
         * @return
         */
        private static String MD5(String md5) {
            try {
                java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
                byte[] array = md.digest(md5.getBytes());
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < array.length; ++i) {
                    sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
                }
                return sb.toString();
            } catch (java.security.NoSuchAlgorithmException e) {
            }
            return null;
        }
        /**
         * 计算sn的值
         * @param paramsMap
         * @return
         * @throws UnsupportedEncodingException
         */
        public static String result(Map paramsMap) throws UnsupportedEncodingException {
            /**
             * 计算sn跟参数对出现顺序有关，get请求请使用LinkedHashMap保存
             * <key,value>，该方法根据key的插入顺序排序；post请使用TreeMap保存
             * <key,value>，该方法会自动将key按照字母a-z顺序排序。所以get请求可自定义参数顺序（sn参数必须在最后）发送请求，
             * 但是post请求必须按照字母a-z顺序填充body（sn参数必须在最后）。以get请求为例：http://api.map.baidu.
             * com/geocoder/v2/?address=百度大厦&output=json&ak=yourak，
             * paramsMap中先放入address，再放output，然后放ak，放入顺序必须跟get请求中对应参数的出现顺序保持一致。
             */
            // 调用下面的toQueryString方法，对LinkedHashMap内所有value作utf8编码，拼接返回结果address=%E7%99%BE%E5%BA%A6%E5%A4%A7%E5%8E%A6&output=json&ak=yourak
            String paramsStr = toQueryString(paramsMap);

            // 对paramsStr前面拼接上/geocoder/v2/?，后面直接拼接yoursk得到/geocoder/v2/?address=%E7%99%BE%E5%BA%A6%E5%A4%A7%E5%8E%A6&output=json&ak=yourakyoursk
            String wholeStr = new String("/geocoder/v2/?" + paramsStr + "FkytCBIZMi5kVhkfTBICZp02KGa1U5tk");

            // 对上面wholeStr再作utf8编码
            String tempStr = URLEncoder.encode(wholeStr, "UTF-8");

            // 调用下面的MD5方法得到最后的sn签名7de5a22212ffaa9e326444c75a58f9a0
            // System.out.println(MD5(tempStr));
            return MD5(tempStr);
        }
        
        /**
         * 返回输入地址的经纬度坐标 key lng(经度),lat(纬度)
         */
        public static Map<String, String> getGeocoderLatitude1(String address) {
            BufferedReader in = null;
            try {
                Map paramsMap = new LinkedHashMap<String, String>();
                paramsMap.put("address", address);
                paramsMap.put("output", "json");
                paramsMap.put("ak", "HOohirfjDdMOZR9BZLR2qHUXKc4IaWGj");
                String quest = toQueryString(paramsMap);
                URL tirc = new URL(
                        "http://api.map.baidu.com/geocoder/v2/?" + quest + "&sn=" + result(paramsMap));

                in = new BufferedReader(new InputStreamReader(tirc.openStream(), "UTF-8"));
                String res;
                StringBuilder sb = new StringBuilder("");
                while ((res = in.readLine()) != null) {
                    sb.append(res.trim());
                }
                String str = sb.toString();
                Map<String, String> map = null;
                if (StringUtils.isNotEmpty(str)) {
                    int lngStart = str.indexOf("lng\":");
                    int lngEnd = str.indexOf(",\"lat");
                    int latEnd = str.indexOf("},\"precise");
                    if (lngStart > 0 && lngEnd > 0 && latEnd > 0) {
                        String lng = str.substring(lngStart + 5, lngEnd);
                        String lat = str.substring(lngEnd + 7, latEnd);
                        map = new HashMap<String, String>();
                        map.put("lng", lng);
                        map.put("lat", lat);
                        return map;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
        
    public static void main(String args[]){  
        try {  
        	Map<String, String> json3 = getGeocoderLatitude1("天津市河西区乐园路5号");
            System.out.println("lng : " + json3.get("lng"));
            System.out.println("lat : " + json3.get("lat"));
            Map<String, Double> json = AddressUtils.getPoiLngAndLat("天津市河西区乐园路5号");  
        	//Map<String, Double> json = AddressUtils.getTXLngAndLat("天津市西青区大学城地铁站");  
        	//System.out.println(get(""+117.109726, ""+39.070843,""+json.get("longitude"), ""+json.get("latitude"))); 
        	//System.out.println(getTXAddress(""+117.10978, ""+39.067528));
        	 System.out.println("经度longitude : " + json.get("longitude"));  
            System.out.println("纬度latitude : " + json.get("latitude"));
        	// Map<String, String> json1 = AddressUtils.getGeocoderLatitude("天津市和平区"); 
            //System.out.println(json1);
           // System.out.println("经度longitude : " + json.get("longitude"));  
           // System.out.println("纬度latitude : " + json.get("latitude"));  
           // String add = getBDAdd(String.valueOf(json.get("longitude")), String.valueOf(json.get("latitude")));  
            //JSONObject jsonObject = JSONObject.fromObject(add);  
            //JSONArray jsonArray = JSONArray.fromObject(jsonObject.getString("addressComponent"));  
            //String jsonObject1=jsonObject.getString("result");
           // JSONObject j_21 = JSONObject.fromObject(jsonObject1);  
            //String jsonObject2=j_21.getString("addressComponent");
            ///JSONObject j_2 = JSONObject.fromObject(jsonObject2);  
          //  String allAdd = j_2.getString("admName");  
           //String allAdd1 = j_2.getString("addr"); 
          //  String arr[] = allAdd.split(",");  
            //System.out.println("省："+arr[0]+"\n市："+arr[1]+"\n区："+arr[2]);  
           // System.out.println(allAdd+allAdd1);  
           // System.out.println("地址:"+j_21.getString("formatted_address"));
          // System.out.println("国家:"+j_2.getString("country")+"\n省:"+j_2.getString("province")+"\n市:"+j_2.getString("city")+"\n区:"+j_2.getString("district")+"\n街道:"+j_2.getString("street"));
           
           
         /*  String add1 = getAliYunAdd(String.valueOf(json.get("longitude")), String.valueOf(json.get("latitude")));  
           JSONObject jsonObject3 = JSONObject.fromObject(add1);  
           JSONArray jsonArray = JSONArray.fromObject(jsonObject3.getString("addrList"));  
           JSONObject j_23 = JSONObject.fromObject(jsonArray.get(0));  
           String allAdd = j_23.getString("admName"); 
           String addr = j_23.getString("addr"); 
           String admCode = j_23.getString("admCode"); 
           String name = j_23.getString("name"); 
           String arr[] = allAdd.split(",");  
           System.out.println("省："+arr[0]+"\n市："+arr[1]+"\n区："+arr[2]);  
           if (null!=addr&&!"".equals(addr)) {
        	   System.out.println("地址:"+allAdd+addr);
        	   System.out.println("邮编:"+admCode);
           }else if (null!=name&&!"".equals(name)) {
        	   System.out.println("地址:"+allAdd+name);
        	   System.out.println("邮编:"+admCode);
           }
           //济南国际会展中心经纬度：117.11811  36.68484
           //趵突泉：117.00999000000002  36.66123
           System.out.println(getDistance("117.11811","36.68484","117.00999000000002","36.66123"));
           System.out.println(getAround("117.11811","36.68484","30000"));
           
          // System.out.println(getAround("117.11811", "36.68484", "13000"));
           //117.01028712333508(Double), 117.22593287666493(Double),
           //36.44829619896034(Double), 36.92138380103966(Double)
           String str="18322781189";//待处理字符串

           if(str.length()>=4){// 判断是否长度大于等于4
              String strsub=str.substring(str.length()- 4);//一个参数表示截取传递的序号之后的部分
              System.out.println(strsub);
               String strsub1=str.substring(str.length()- 4,str.length());//截取两个数字之间的部分
               System.out.println(strsub1);
           }
          
           
           System.out.println(str.replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2"));
           //152****4799
           String idCard="6218262652525252";//待处理字符串
           System.out.println(idCard.replaceAll("(\\d{4})\\d{10}(\\w{4})","$1*****$2"));*/
           
           //4304*****7733
        }catch (Exception e ){  
           e.printStackTrace();  
        }  
    }  
}