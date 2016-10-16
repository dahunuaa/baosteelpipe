package bs.bussinfo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bs.system.DBUtils;

public class myBusinessRecordDao {
	private String buss_editor;
	private int count;
	
	 public myBusinessRecordDao(String buss_editor,int count){
		this.buss_editor = buss_editor;
		this.count = 5*count;
		
	}
	 public List myBusinessRecord(){
         Connection conn =null;
         PreparedStatement pstmt =null;
         List infos = null;
         try {
        	
			 conn=DBUtils.getConnection();
			 if(conn==null) return null;
			 StringBuffer sb = new StringBuffer();
			 sb.append("SELECT bussinessrecords.`buss_id`, bussinessrecords.`editor`,`editor_name`,`bussmen_name`,`accounts`,`buss_place`,`buss_reason`,`buss_begintime`,`buss_endtime`,`time`");
			 sb.append(" FROM bussinessrecords");
			 sb.append(" WHERE bussinessrecords.`editor`=?");
			 sb.append(" ORDER BY buss_id DESC LIMIT ?,5");
			 pstmt = conn.prepareStatement(sb.toString());
			 pstmt.setObject(1, this.buss_editor);
			 pstmt.setObject(2, this.count);
			 ResultSet rs = pstmt.executeQuery();
			 infos = new ArrayList<Map<String,Object>>();
			 ResultSetMetaData rsmd = rs.getMetaData();//得到表的结构信息，比如字段名，字段数
			 while(rs.next()){//遍历列数
				 Map<String,Object> item = new HashMap<String, Object>();
				 int nCount = rsmd.getColumnCount();//得到列的数量  getrowcount是获取行的数量
				 for(int i = 0; i <nCount;++i){
					 item.put(rsmd.getColumnLabel(i+1), rs.getString(i+1));//将获取的信息存入到map中
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
