package com.bs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bs.system.DBUtils;

public class UserNameDao {
	private String id = null;
	
	
	public UserNameDao(String id) {
		super();
		this.id = id;
	}
   

	public String getName(){
	      Connection conn = DBUtils.getConnection();
	      if(conn == null)return null;
	      String sql ="SELECT name FROM usersinfor WHERE id =?";
	      String name = "";
	
	      try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setObject(1, this.id);
				ResultSet rs = pstmt.executeQuery();
				
				while(rs.next()){
					name=rs.getString(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				DBUtils.close(conn);
				return name;
			
			}
	
	   }
	
	

}

