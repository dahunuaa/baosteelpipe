package com.bs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bs.system.DBUtils;

public class AlterpswDao {
	private String id;
	private String newpsw;
	
	

	 public AlterpswDao(String id,String newpsw){
		this.id=id;
		this.newpsw = newpsw;
		
	
	}
	 
	 public String alterpsw(){
		 int i =0;
		 Connection conn = DBUtils.getConnection();
		 if(conn == null)return "";
		 		 
 		 StringBuffer sb = new StringBuffer();		 
         sb.append("UPDATE usersinfor SET usersinfor.`psw`=?");
         sb.append(" WHERE usersinfor.`id`=?");
	     PreparedStatement pstmt = null;

	     try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setObject(1,newpsw);
			pstmt.setObject(2,id);
		
			i = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtils.close(conn);
			return newpsw;
		}
         
	 }
}
