package com.fh.util;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

public class PropertiesUtil {
	/**
	 * 获取资源文件属性值
	 * @param propertiesPath:文件路径
	 * @param key:key值
	 * @return：返回value值
	 */
	public static String getProperty(String propertiesPath, String key) {
		Properties prop = new Properties();
		InputStream in = PropertiesUtil.class.getClassLoader().getResourceAsStream(propertiesPath);
		try {
			prop.load(in);
		} catch (IOException e) {
			return "";
		}
		if (prop.containsKey(key)) {
			return prop.get(key).toString();
		} else {
			return "";
		}
	}
	
	/**
	 * 
	 * 获得配置文件对象
	 * 
	 * @return Properties
	 * @throws
	 */
	public static Properties getProFile() {
		InputStream in = PropertiesUtil.class.getClassLoader().getResourceAsStream("/integration.properties"); 
		Properties properties = new Properties();
		if (in != null) {
			try {
				properties.load(in);
			} catch (IOException e) {
				e.printStackTrace();
				
			}
		}
		return properties;
	}
	
	public static List<String> getAllPropertiesValue(){
		InputStream in = PropertiesUtil.class.getClassLoader().getResourceAsStream("country.properties"); 
		Properties prop = new Properties();
		List<String> lst = new ArrayList<String>();
		if (in != null) {
			try {
				prop.load(in);
				Set<Object> keys = prop.keySet();
				for(Object key:keys){
					String value = (String)prop.get(key);
					byte[] bytes = value.getBytes("ISO-8859-1");
					lst.add(new String(bytes, "UTF-8"));
	            }  
			} catch (IOException e) {
				e.printStackTrace();
				
			}
		}
		return lst;
	}
}
