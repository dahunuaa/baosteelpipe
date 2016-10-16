package bs.bussinfo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bs.system.DBUtils;

public class CreatNewTaskDao {
	private String buss_editor;
	private String buss_editor_name;
	private String bussmen_name;
	private String buss_accounts;
	private String buss_place;
	private String buss_reason;
	private String buss_begintime;
	private String buss_endtime;
	private String time;

	 public CreatNewTaskDao(String buss_editor,String buss_editor_name,String bussmen_name,String buss_accounts,String buss_place,String buss_reason,String buss_begintime,String buss_endtime,String time){
		this.buss_editor = buss_editor;
		this.buss_editor_name = buss_editor_name;
		this.bussmen_name = bussmen_name;
		this.buss_accounts = buss_accounts;
		this.buss_place = buss_place;
		this.buss_reason = buss_reason;
		this.buss_begintime = buss_begintime;
		this.buss_endtime = buss_endtime;
		this.time = time;
	}
	 
	 public void creatNewTask(){
		 int i =0;
		 Connection conn = DBUtils.getConnection();
		 if(conn == null)return;
		 StringBuffer sb = new StringBuffer();
         sb.append("INSERT INTO `baosteelpipe_db`.`bussinessrecords`(`editor`, `editor_name`,`bussmen_name`,`accounts`,`buss_place`,`buss_reason`,`buss_begintime`,`buss_endtime`,`time`)");
         sb.append(" VALUES (?,?,?,?,?,?,?,?,?)");
	     PreparedStatement pstmt = null;
	     try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setObject(1,buss_editor);
			pstmt.setObject(2,buss_editor_name);
			pstmt.setObject(3,bussmen_name);
			pstmt.setObject(4,buss_accounts);
			pstmt.setObject(5,buss_place);
			pstmt.setObject(6,buss_reason);
			pstmt.setObject(7,buss_begintime);
			pstmt.setObject(8,buss_endtime);
			pstmt.setObject(9,time);
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
