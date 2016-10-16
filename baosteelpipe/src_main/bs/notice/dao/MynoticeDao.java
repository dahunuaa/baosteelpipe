package bs.notice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bs.system.DBUtils;

public class MynoticeDao {
	private String publisher;
	private int count;
	
	 public MynoticeDao(String publisher,int count){
		this.publisher = publisher;
		this.count=5*count;
		
	}
	 public List mynotice(){
         Connection conn =null;
         PreparedStatement pstmt =null;
         List infos = null;
         try {
        	
			 conn=DBUtils.getConnection();
			 if(conn==null) return null;
			 StringBuffer sb = new StringBuffer();
			 sb.append("SELECT notice.`notice_id`, notice.`publisher`, notice.`notice_title`,`notice`,`time`");
			 sb.append(" FROM notice");
			 sb.append(" WHERE notice.`publisher`=?");
			 sb.append(" ORDER BY notice_id DESC LIMIT ?,5");
			 pstmt = conn.prepareStatement(sb.toString());
			 pstmt.setObject(1, this.publisher);
			 pstmt.setObject(2, this.count);
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
