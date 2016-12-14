package bs.bussinfor.comment.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.bs.system.DBUtils;

public class Add_comment_Dao{
	private String type;
	private String buss_id;
	private String comment_user_id;
	private String comment_text;
	private String name;
	private String time;

	
	public Add_comment_Dao(String type,String buss_id,String comment_user_id,String comment_text,String name,String time ){
		this.type=type;
		this.buss_id = buss_id;
		this.comment_user_id = comment_user_id;
		this.comment_text = comment_text;
		this.name = name;
		this.time = time;
	}
	
	public void add_comment(){
		int i =0;
		 Connection conn = DBUtils.getConnection();
		 if(conn == null)return;
		 StringBuffer sb = new StringBuffer();
		 if(type.equals("buss")){
			 sb.append("INSERT INTO `baosteelpipe_db`.`buss_comment`(`buss_id`,`user_id`, `text`,`name`,`time`)");
	         sb.append(" VALUES (?,?,?,?,?)");
		 }else if (type.equals("guide")) {
			 sb.append("INSERT INTO `baosteelpipe_db`.`guide_comment`(`buss_id`,`user_id`, `text`,`name`,`time`)");
	         sb.append(" VALUES (?,?,?,?,?)");
		}else if (type.equals("gather")) {
			 sb.append("INSERT INTO `baosteelpipe_db`.`gather_comment`(`buss_id`,`user_id`, `text`,`name`,`time`)");
	         sb.append(" VALUES (?,?,?,?,?)");
		}
         
	     PreparedStatement pstmt = null;
	     try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setObject(1,buss_id);
			pstmt.setObject(2,comment_user_id);
			pstmt.setObject(3,comment_text);
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
