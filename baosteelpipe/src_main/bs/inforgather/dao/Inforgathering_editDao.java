package bs.inforgather.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bs.system.DBUtils;

public class Inforgathering_editDao {
	private String gather_id;
	private String gather_title;
	private String gather_address;
	private String gather_area;
	private String gather_text;
	private String gather_oil_field;
	private String time;
	private String origin_file_name;
	private String new_file_name;
	

	 public Inforgathering_editDao(String gather_id,String gather_title,String gather_address,String gather_area,String gather_text,String gather_oil_field,String time,String origin_file_name,String new_file_name){
		this.gather_id=gather_id;
		this.gather_title = gather_title;
		this.gather_address = gather_address;
		this.gather_area = gather_area;
		this.gather_text = gather_text;
		this.gather_oil_field = gather_oil_field;
		this.time = time;
		this.origin_file_name = origin_file_name;
		this.new_file_name = new_file_name ;
	
	}
	 
	 public void Inforgathering_edit(){
		 int i =0;
		 Connection conn = DBUtils.getConnection();
		 if(conn == null)return;
		 StringBuffer sb = new StringBuffer();
         sb.append("UPDATE informationgatheringrecord SET gather_title=?,address=?,area=?,gathering_text=?,gather_oil_field=?,time=?,origin_file_name=?,new_file_name=?");
         sb.append(" WHERE informationgatheringrecord.`gather_id`=?");
	     PreparedStatement pstmt = null;

	     try {
			pstmt = conn.prepareStatement(sb.toString());
			
			pstmt.setObject(1,gather_title);
			pstmt.setObject(2,gather_address);
			pstmt.setObject(3,gather_area);
			pstmt.setObject(4,gather_text);
			pstmt.setObject(5,gather_oil_field);
			pstmt.setObject(6,time);
			pstmt.setObject(7,origin_file_name);
			pstmt.setObject(8,new_file_name);
			pstmt.setObject(9,gather_id);
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
