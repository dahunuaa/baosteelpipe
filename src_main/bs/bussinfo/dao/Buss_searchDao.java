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

public class Buss_searchDao {
	private String bussmen_name;
	private String buss_accounts;
	private String buss_place;
	private String buss_reason;
	private String buss_begintime;
	private String buss_endtime;
	private int count;
	
	 public Buss_searchDao(String bussmen_name,String buss_accounts,String buss_place,String buss_reason,String buss_begintime,String buss_endtime,int count){
		this.bussmen_name = bussmen_name;
		this.buss_accounts = buss_accounts;
		this.buss_place = buss_place;
		this.buss_reason = buss_reason;
		this.buss_begintime = buss_begintime;
		this.buss_endtime = buss_endtime;
		this.count = 5*count;
		
	}
	 public List Buss_searchRecord(){
         Connection conn =null;
         PreparedStatement pstmt =null;
         List infos = null;
         try {
			 conn=DBUtils.getConnection();
			 if(conn==null) return null;
			 StringBuffer sb = new StringBuffer();
			 if (buss_endtime == "") {
				 buss_endtime = "2050-12-12";
			 }
			 sb.append("SELECT * ");
			 sb.append(" FROM baosteelpipe_db.bussinessrecords");
			 sb.append(" WHERE CONCAT(bussmen_name) LIKE ? AND CONCAT(accounts) LIKE ? AND CONCAT(buss_place) LIKE ? AND CONCAT(buss_reason) LIKE ? AND buss_begintime >=? AND buss_endtime <=? ");
			 sb.append(" ORDER BY buss_id DESC LIMIT ?,5");
			 pstmt = conn.prepareStatement(sb.toString());
			 pstmt.setObject(1, "%"+this.bussmen_name+"%");
			 pstmt.setObject(2, "%"+this.buss_accounts+"%");
			 pstmt.setObject(3, "%"+this.buss_place+"%");
			 pstmt.setObject(4, "%"+this.buss_reason+"%");
			 pstmt.setObject(5, this.buss_begintime);
			 pstmt.setObject(6, this.buss_endtime);
			 pstmt.setObject(7, this.count);
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
