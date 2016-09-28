package bs.inforguide.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bs.system.DBUtils;

public class Inforguide_editDao {
	private String guide_id;
	private String guide_title;
	private String guide_category;
	private String guide_text;
	private String time;
	private String pic_path;
	private String pic;
	

	 public Inforguide_editDao(String guide_id,String guide_title,String guide_category,String guide_text,String time,String path,String pic){
		this.guide_id=guide_id;
		this.guide_title = guide_title;
		this.guide_category = guide_category;
		this.guide_text = guide_text;
		this.time = time;
		this.pic_path = path;
		this.pic =pic;
	
	}
	 
	 public void Inforguide_edit(){
		 int i =0;
		 Connection conn = DBUtils.getConnection();
		 if(conn == null)return;
		 StringBuffer sb = new StringBuffer();
		 try {
			 if(this.pic==""||this.pic.equals("null")){
				 pic_path ="0";
			 }
	         sb.append("UPDATE informationguiderecord SET guide_title=?,guide_category=?,guide_text=?,time=?,pic_path=?");
	         sb.append(" WHERE informationguiderecord.`guide_id`=?");
		     PreparedStatement pstmt = null;

			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setObject(1,guide_title);
			pstmt.setObject(2,guide_category);
			pstmt.setObject(3,guide_text);
			pstmt.setObject(4,time);
			pstmt.setObject(5,pic_path);
			pstmt.setObject(6,guide_id);
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
