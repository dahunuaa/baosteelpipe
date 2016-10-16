package bs.bussinfo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bs.system.DBUtils;

public class Buss_deleteDao {
	private String buss_id;

	 public Buss_deleteDao(String buss_id){
		 this.buss_id=buss_id;
	}
	 
	 public void deleteTask(){
		 int i =0;
		 Connection conn = DBUtils.getConnection();
		 if(conn == null)return;
		 StringBuffer sb = new StringBuffer();
         sb.append("DELETE FROM bussinessrecords WHERE bussinessrecords.buss_id=?");
	     PreparedStatement pstmt = null;

	     try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setObject(1,buss_id);
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
