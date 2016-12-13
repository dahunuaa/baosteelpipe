package com.bs.system;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;;

public class DBUtils {
	private static final String driver = "com.mysql.jdbc.Driver";
	private static final String url = "jdbc:mysql://127.0.0.1:3306/baosteelpipe_db";
	
	//第一步 加载驱动
	static{
		 try {
				Class.forName(driver);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	public static void main(String[] args){
		try {
			System.out.println(DriverManager.getConnection(url,"root","Rfvmg3EaSCZ2").toString());
//			System.out.println(DriverManager.getConnection(url,"root","19920920").toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//第二步  连接数据库
	public static Connection getConnection(){	
		try {
			return DriverManager.getConnection(url,"root","Rfvmg3EaSCZ2");
//			return DriverManager.getConnection(url,"root","19920920");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
     //关闭connection
	public static void close(Connection connection){
		try {
			if(connection != null && !connection.isClosed()){
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(PreparedStatement pStatement) {
		try{
			if(pStatement == null || pStatement.isClosed())return;
			pStatement.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
