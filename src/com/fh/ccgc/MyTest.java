package com.fh.ccgc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;

public class MyTest {  
	  
    static MyConn c = new MyConn();  
      
    public static void main(String[] args) throws Exception { 
        test1(); 
        test2(); 
    } 
     
    public static void test1() throws Exception { 
    	 Connection connection = c.getConnection();  
        String sql = "{CALL pro_num_user(?,?)}"; //调用存储过程 
        CallableStatement cstm = connection.prepareCall(sql); //实例化对象cstm 
        cstm.setString(1, "myd"); //存储过程输入参数 
        //cstm.setInt(2, 2); // 存储过程输入参数 
        cstm.registerOutParameter(2, Types.INTEGER); // 设置返回值类型 即返回值 
        cstm.execute(); // 执行存储过程 
        System.out.println(cstm.getInt(2)); 
        cstm.close(); 
        connection.close(); 
    } 
     
    public static void test2() throws Exception { 
    	Connection connection = c.getConnection();
        String sql = "{CALL pro_number(?,?,?)}"; //调用存储过程 
        CallableStatement cstm = connection.prepareCall(sql); //实例化对象cstm 
        cstm.setInt(1, 2); // 存储过程输入参数 
        cstm.setInt(2, 2); // 存储过程输入参数 
        cstm.registerOutParameter(3, Types.INTEGER); // 设置返回值类型 即返回值 
        cstm.execute(); // 执行存储过程 
        System.out.println(cstm.getInt(3)); 
        cstm.close(); 
        connection.close(); 
         
    } 
  
}  
