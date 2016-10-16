package bs.inforguide.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bs.system.DBUtils;

public class Inforguide_searchDao {
	private String title;
	private String category;
	private String content;
	private int count;
	
	 public Inforguide_searchDao(String title,String category,String content,int count ){
		this.title = title;
		this.category = category;
		this.content = content;
		this.count = 5*count;
		
	}
	 public List Inforguide_searchrecord(){
         Connection conn =null;
         PreparedStatement pstmt =null;
         List infos = null;
         try {
        	
			 conn=DBUtils.getConnection();
			 if(conn==null) return null;
			 StringBuffer sb = new StringBuffer();
			 sb.append("SELECT *");
			 sb.append(" FROM informationguiderecord");
			 sb.append(" WHERE CONCAT(guide_title) LIKE ? AND CONCAT(guide_category) LIKE ? AND CONCAT(guide_text) LIKE ? ");
			 sb.append(" ORDER BY guide_id DESC LIMIT ?,5");
			 pstmt = conn.prepareStatement(sb.toString());
			 pstmt.setObject(1, "%"+this.title+"%");
			 pstmt.setObject(2, "%"+this.category+"%");
			 pstmt.setObject(3, "%"+this.content+"%");
			 pstmt.setObject(4, this.count);
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
         }catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtils.close(conn);
			return infos;
		}
	     
	}	
}
