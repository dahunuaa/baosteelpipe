package bs.bussinfor.comment.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bs.system.DBUtils;

public class CommentListDao {
	private String type;
	private String buss_id;
	public CommentListDao(String type,String buss_id){
		this.type = type;
		this.buss_id = buss_id;
	}
	public List comment_list(){
        Connection conn =null;
        PreparedStatement pstmt =null;
        List infos = null;
        try {
       	
			 conn=DBUtils.getConnection();
			 if(conn==null) return null;
			 StringBuffer sb = new StringBuffer();
			 if(type.equals("buss")){
				 sb.append("SELECT `comment_id`,`user_id`,`text`,`name`,`time`");
				 sb.append(" FROM buss_comment");
				 sb.append(" WHERE buss_comment.`buss_id`=?");
			 }else if (type.equals("guide")) {
				 sb.append("SELECT `comment_id`,`user_id`,`text`,`name`,`time`");
				 sb.append(" FROM guide_comment");
				 sb.append(" WHERE guide_comment.`buss_id`=?");
			}else if (type.equals("gather")) {
				 sb.append("SELECT `comment_id`,`user_id`,`text`,`name`,`time`");
				 sb.append(" FROM gather_comment");
				 sb.append(" WHERE gather_comment.`buss_id`=?");
			}
			 
			 pstmt = conn.prepareStatement(sb.toString());
			 pstmt.setObject(1, this.buss_id);
			 ResultSet rs = pstmt.executeQuery();
	       
			 infos = new ArrayList<Map<String,Object>>();
			 ResultSetMetaData rsmd = rs.getMetaData();		 
			 while(rs.next()){
				 Map<String,Object> item = new HashMap<String, Object>();
				 int nCount = rsmd.getColumnCount();			 
				 for(int i = 0; i <nCount;++i){
			       item.put(rsmd.getColumnLabel(i+1), rs.getString(i+1));		 
			    }
				 infos.add(item);
				
			 }
	         
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtils.close(conn);
			return infos;
		}
	}
}
