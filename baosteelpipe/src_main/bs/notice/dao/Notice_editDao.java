package bs.notice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bs.system.DBUtils;

public class Notice_editDao {
	private String notice_id;
	private String notice;
	private String notice_title;
	private String time;
	
	

	 public Notice_editDao(String notice_id,String notice,String notice_title,String time){
		this.notice_id=notice_id;
		this.notice = notice;
		this.notice_title = notice_title;
		this.time = time;
		
	}
	 
	 public void Notice_edit(){
		 int i =0;
		 Connection conn = DBUtils.getConnection();
		 if(conn == null)return;
		 StringBuffer sb = new StringBuffer();
         sb.append("UPDATE notice SET notice=?,notice_title=?,time=?");
         sb.append(" WHERE notice.`notice_id`=?");
	     PreparedStatement pstmt = null;

	     try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setObject(1,notice);
			pstmt.setObject(2,notice_title);
			pstmt.setObject(3,time);
			pstmt.setObject(4,notice_id);
			
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
