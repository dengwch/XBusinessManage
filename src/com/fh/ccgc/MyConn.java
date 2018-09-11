package com.fh.ccgc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

/** 
*获取数据库连接的类 
*/
public class MyConn {  
    
    private static final String DRIVER = "com.mysql.jdbc.Driver";  
    private static final String URL = "jdbc:mysql://localhost/xchxu?useUnicode=true&characterEncoding=UTF-8";  
    private static final String USER = "root";  
    private static final String PASSWORD ="";  
  
    static {  
        try {  
            Class.forName(DRIVER);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
  
    /** 
     * 获取连接 
     *  
     * @return 
     * @throws Exception 
     */  
    public Connection getConnection() throws Exception {  
        return DriverManager.getConnection(URL, USER, PASSWORD);  
    }  
  
    /** 
     * 释放资源 
     *  
     * @param rs 
     * @param stmt 
     * @param conn 
     */  
    public void close(ResultSet rs, CallableStatement stmt, Connection conn) {  
        try{  
            if (rs != null) {  
                rs.close();  
            }  
            if (stmt != null) {  
                stmt.close();  
            }  
            if (conn != null) {  
                conn.close();  
            }  
        }catch(Exception e){  
            e.printStackTrace();  
        }  
    }  
  
}  