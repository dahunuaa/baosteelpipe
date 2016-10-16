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
   
//		 图片转化成base64字符串
	 public String downloadpic() throws Exception{
		 InputStream in = null;
		 byte[] data = null;
		 
		 try {
//				 读取图片字节数组
			 in = new FileInputStream(imgFile);
			 data = new byte[in.available()];
			 in.read(data);
			 in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
//			 对字节数组base64编码
		 BASE64Encoder encoder = new BASE64Encoder();
		 return encoder.encode(data);
	 }
}
