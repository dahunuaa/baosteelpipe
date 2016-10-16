package bs.inforgather.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bs.system.DBUtils;

public class NewInformationgatheringDao {
	private String gather_editor;
	private String gather_editor_name;
	private String gather_title;
	private String gather_address;
	private String gather_area;
	private String gather_text;
	private String gather_oil_field;
	private String time;
	private String origin_file_name;
	private String new_file_name;
	
	

	 public NewInformationgatheringDao(String gather_editor,String gather_editor_name,String gather_title,String gather_address,String gather_area,String gather_text,String gather_oil_field,String time,String origin_file_name,String new_file_name){
		this.gather_editor = gather_editor;
		this.gather_editor_name = gather_editor_name;
		this.gather_title = gather_title;
		this.gather_address = gather_address;
		this.gather_area = gather_area;
		this.gather_text = gather_text;
		this.gather_oil_field = gather_oil_field;
		this.time = time;
		this.origin_file_name = origin_file_name;
		this.new_file_name = new_file_name ;
	}
	 
	 public void Newinformationgathering(){
		 int i =0;
		 Connection conn = DBUtils.getConnection();
		 if(conn == null)return;
		 StringBuffer sb = new StringBuffer();
         sb.append("INSERT INTO `baosteelpipe_db`.`informationgatheringrecord`(`gather_editor`, `gather_editor_name`,`gather_title`,`area`,`address`,`gathering_text`,`gather_oil_field`,`time`,`origin_file_name`,`new_file_name`)");
         sb.append(" VALUES (?,?,?,?,?,?,?,?,?,?)");
	     PreparedStatement pstmt = null;
	     try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setObject(1,gather_editor);
			pstmt.setObject(2,gather_editor_name);
			pstmt.setObject(3,gather_title);
			pstmt.setObject(4,gather_area);
			pstmt.setObject(5,gather_address);
			pstmt.setObject(6,gather_text);
			pstmt.setObject(7,gather_oil_field);
			pstmt.setObject(8,time);
			pstmt.setObject(9,origin_file_name);
			pstmt.setObject(10,new_file_name);
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
