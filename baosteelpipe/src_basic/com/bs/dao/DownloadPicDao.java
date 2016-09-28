package com.bs.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import sun.misc.BASE64Encoder;

public class DownloadPicDao {
   private String imgFile;
   
   public DownloadPicDao(String imgFile){
	   this.imgFile = imgFile;
   }
   
//		 ͼƬת����base64�ַ���
	 public String downloadpic() throws Exception{
		 InputStream in = null;
		 byte[] data = null;
		 
		 try {
//				 ��ȡͼƬ�ֽ�����
			 in = new FileInputStream(imgFile);
			 data = new byte[in.available()];
			 in.read(data);
			 in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
//			 ���ֽ�����base64����
		 BASE64Encoder encoder = new BASE64Encoder();
		 return encoder.encode(data);
	 }
}
