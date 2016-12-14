package bs.bussinfor.comment.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.bs.system.DBUtils;

public class Comment_deleteDao {
	private String type;
	private String comment_id;
	
	public Comment_deleteDao(String type,String comment_id){
		this.type = type;
		this.comment_id = comment_id;
	}
	public void comment_delete(){
		 int i =0;
		 Connection conn = DBUtils.getConnection();
		 if(conn == null)return;
		 StringBuffer sb = new StringBuffer();
		 if(type.equals("buss")){
			sb.append("DELETE FROM buss_comment WHERE buss_comment.comment_id=?");
		 }else if (type.equals("guide")) {
			 sb.append("DELETE FROM guide_comment WHERE guide_comment.comment_id=?");
		 }else if (type.equals("gather")) {
			 sb.append("DELETE FROM gather_comment WHERE gather_comment.comment_id=?");
		}
         
	     PreparedStatement pstmt = null;

	     try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setObject(1,comment_id);
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
