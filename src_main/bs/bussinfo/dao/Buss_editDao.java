package bs.bussinfo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bs.system.DBUtils;

public class Buss_editDao {
	
	private String bussmen_name;
	private String buss_accounts;
	private String buss_place;
	private String buss_reason;
	private String buss_begintime;
	private String buss_endtime;
	private String buss_id;
	private String time;

	 public Buss_editDao(String buss_id,String bussmen_name,String buss_accounts,String buss_place,String buss_reason,String buss_begintime,String buss_endtime,String time){
		this.buss_id=buss_id;
		this.bussmen_name = bussmen_name;
		this.buss_accounts = buss_accounts;
		this.buss_place = buss_place;
		this.buss_reason = buss_reason;
		this.buss_begintime = buss_begintime;
		this.buss_endtime = buss_endtime;
		this.time = time;
	}
	 
	 public void buss_edit(){
		 int i =0;
		 Connection conn = DBUtils.getConnection();
		 if(conn == null)return;
		 StringBuffer sb = new StringBuffer();
         sb.append("UPDATE bussinessrecords SET bussmen_name=?,accounts=?,buss_place=?,buss_reason=?,buss_begintime=?,buss_endtime=?,time=?");
         sb.append(" WHERE bussinessrecords.`buss_id`=?");
	     PreparedStatement pstmt = null;

	     try {
			pstmt = conn.prepareStatement(sb.toString());
			
			pstmt.setObject(1,bussmen_name);
			pstmt.setObject(2,buss_accounts);
			pstmt.setObject(3,buss_place);
			pstmt.setObject(4,buss_reason);
			pstmt.setObject(5,buss_begintime);
			pstmt.setObject(6,buss_endtime);
			pstmt.setObject(7,time);
			pstmt.setObject(8,buss_id);
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
