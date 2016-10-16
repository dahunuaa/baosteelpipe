package com.bs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bs.system.DBUtils;

public class InforgatherpieDao {
	
	 public InforgatherpieDao(){} 
	
	 public List Inforgatherpie(){
         Connection conn =null;
         PreparedStatement pstmt =null;
         List infos = null;
         try {
			 conn=DBUtils.getConnection();
			 if(conn==null) return null;
			 String sql ="SELECT AREA AS name, COUNT(AREA) AS value FROM informationgatheringrecord GROUP BY AREA";
			 pstmt = conn.prepareStatement(sql);
			 ResultSet rs = pstmt.executeQuery();
			 infos = new ArrayList<Map<String,Object>>();
			 ResultSetMetaData rsmd = rs.getMetaData();//�õ���Ľṹ��Ϣ�������ֶ������ֶ���
			 while(rs.next()){//��������
				 Map<String,Object> item = new HashMap<String, Object>();
				 int nCount = rsmd.getColumnCount();//�õ��е�����  getrowcount�ǻ�ȡ�е�����
				 for(int i = 0; i <nCount;++i){
					 item.put(rsmd.getColumnLabel(i+1), rs.getString(i+1));//����ȡ����Ϣ���뵽map��
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
