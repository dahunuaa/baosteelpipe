package bs.notice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bs.system.DBUtils;

public class New_noticeDao {
	private String publisher;
	private String notice;
	private String notice_title;
	private String name;
	private String time;

	
	 public New_noticeDao(String publisher,String notice,String notice_title,String name,String time){
		this.publisher = publisher;
		this.notice = notice;
		this.notice_title = notice_title;	
		this.name = name;
		this.time = time;

	}	 
	 public void new_notice(){
		 int i =0;
		 Connection conn = DBUtils.getConnection();
		 if(conn == null)return;
		 StringBuffer sb = new StringBuffer();
         sb.append("INSERT INTO `baosteelpipe_db`.`notice`(`publisher`, `notice`,`notice_title`,`name`,`time`)");
         sb.append(" VALUES (?,?,?,?,?)");
	     PreparedStatement pstmt = null;
	     try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setObject(1,publisher);
			pstmt.setObject(2,notice);
			pstmt.setObject(3,notice_title);
			pstmt.setObject(4,name);
			pstmt.setObject(5,time);
//			System.out.println(notice);
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
