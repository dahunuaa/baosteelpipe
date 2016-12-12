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
	public CommentListDao(){
		
	}
	public List comment_list(){
        Connection conn =null;
        PreparedStatement pstmt =null;
        List infos = null;
        try {
       	
			 conn=DBUtils.getConnection();
			 if(conn==null) return null;
			 StringBuffer sb = new StringBuffer();
			 sb.append("SELECT buss_comment.`buss_id`,`user_id`,`text`,`name`,`time`");
			 sb.append(" FROM notice");
			 pstmt = conn.prepareStatement(sb.toString());
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
