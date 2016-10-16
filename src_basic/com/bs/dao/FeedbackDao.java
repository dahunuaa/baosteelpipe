package com.bs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bs.system.DBUtils;

public class FeedbackDao {
	private String provider;
	private String feedback;
	private String contact;
	private String name;
	private String time;
	
	 public FeedbackDao(String provider,String feedback,String contact,String name,String time){
		this.provider = provider;
		this.feedback = feedback;
		this.contact = contact;
		this.name = name;
		this.time = time;
	}	 
	 public void feedback(){
		 int i =0;
		 Connection conn = DBUtils.getConnection();
		 if(conn == null)return;
		 StringBuffer sb = new StringBuffer();
         sb.append("INSERT INTO `baosteelpipe_db`.`feedback`(`provider`, `advice`,`contact`,`provider_name`,`time`)");
         sb.append(" VALUES (?,?,?,?,?)");
	     PreparedStatement pstmt = null;
	     try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setObject(1,provider);
			pstmt.setObject(2,feedback);
			pstmt.setObject(3,contact);
			pstmt.setObject(4,name);
			pstmt.setObject(5,time);
			
			i = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtils.close(conn);
			return;
		}
         
	 }
}
