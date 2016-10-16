package com.bs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bs.system.DBUtils;

public class UserDao {
	private String name = null;
	
	
	public UserDao(String name) {
		super();
		this.name = name;
	}
   

	public String getPassWord(){
	      Connection conn = DBUtils.getConnection();
	      if(conn == null)return null;
	      String sql ="SELECT psw FROM usersinfor WHERE id ="+"'"+this.name+"'";
	      String psw = "";
	      try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();
				
				while(rs.next()){
					psw=rs.getString(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				DBUtils.close(conn);
				return psw;
			
			}
	
	   }
	
	

}

