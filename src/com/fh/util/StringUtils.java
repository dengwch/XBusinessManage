package com.fh.util;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

public class StringUtils {
	/**
	 * 构造日期字符串
	 * @author GM
	 * @param formatStr
	 * @return
	 */
	public static String getDateString(String formatStr){
		SimpleDateFormat sdf = new SimpleDateFormat(formatStr);  
        return sdf.format(new Date());  
	}
	
	/**
	 * 获取webRoot路径
	 * @author GM
	 * @param request
	 * @return
	 */
	public static String getWebRoot(HttpServletRequest request){
//		return request.getSession().getServletContext().getRealPath("/");
//		System.out.println("======"+request.getSession().getServletContext().getRealPath("/"));
		String classPath = StringUtils.class.getClassLoader().getResource("/").getPath();
		String rootPath = "";
		// windows下
		if ("\\".equals(File.separator)) {
			rootPath = classPath.substring(1, classPath.indexOf("/WEB-INF/classes"));
			rootPath = rootPath.replace("/", "\\");
		}
		// linux下
		if ("/".equals(File.separator)) {
			rootPath = classPath.substring(0, classPath.indexOf("/WEB-INF/classes"));
			rootPath = rootPath.replace("\\", "/");
		}
		return rootPath;
	}
	
	/**
	 * 利用用户名和yyyyMMdd格式日期来组装保存路径字符串
	 * @author GM
	 * @param request
	 * @param username
	 * @return
	 */
	public static String getSavePath(HttpServletRequest request,String username){
		return StringUtils.getWebRoot(request)+"\\upload\\"+StringUtils.getDBSavePath(request, username);
	}
	
	
	/**
	 * 获取DB的保存路径，不含saveFileName
	 * @author GM
	 * @param request
	 * @param username
	 * @return
	 */
	public static String getDBSavePath(HttpServletRequest request,String username){
		return username+"\\"+StringUtils.getDateString("yyyyMMdd")+"\\";
	}
	
	
	
	
	
	/**
	 * 判断是否是纯数字
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str){ 
	    Pattern pattern = Pattern.compile("[0-9]*"); 
	    return pattern.matcher(str).matches();    
	 } 
	
	/**
     * 字符串转换到时间格式
     * @param dateStr 需要转换的字符串
     * @param formatStr 需要格式的目标字符串  举例 yyyy-MM-dd
     * @return Date 返回转换后的时间
     * @throws ParseException 转换异常
     */
    public static Date StringToDate(String dateStr,String formatStr){
        DateFormat sdf=new SimpleDateFormat(formatStr);
        Date date=null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
    /**
     * 判断字符串是否为空
     * @param string
     * @return
     */
    public static boolean isNullOrEmpty(String string) {
		if (string == null || string.isEmpty()) {
			return true;
		}
		return false;
	}
    
    
    public static String join(Object[] array, char separator) {
		if (array == null) {
			return null;
		}
		int arraySize = array.length;
		int bufSize = (arraySize == 0 ? 0 : ((array[0] == null ? 16 : array[0]
				.toString().length()) + 1) * arraySize);
		StringBuffer buf = new StringBuffer(bufSize);

		for (int i = 0; i < arraySize; i++) {
			if(i==0){
				buf.append("'");
			}
			if (i > 0) {
				buf.append("'"+separator+"'");
			}
			if (array[i] != null) {
				buf.append(array[i]);
			}
		}
		if(buf!=null&&buf.length()>0){
			buf.append("'");
		}
		return buf.toString();
	}
}