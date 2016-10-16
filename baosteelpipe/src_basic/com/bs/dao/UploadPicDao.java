package com.bs.dao;

import java.io.FileInputStream;  
import java.io.FileNotFoundException;
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.io.InputStream;  
import java.io.OutputStream;  

import com.sun.mail.util.BASE64DecoderStream;
import com.sun.mail.util.BASE64EncoderStream;

import sun.misc.BASE64Decoder;  
import sun.misc.BASE64Encoder;

public class UploadPicDao {
	 private String path;
	 private String pic;
	 
	 public UploadPicDao(String path,String pic){
		 this.path= path;
		 this.pic = pic;
	 }
	 
	 public boolean uploadpic(){
//		 将base64字符转化成图片
		 if (pic == null)
		 return false;
		 BASE64Decoder decoder = new BASE64Decoder();
		 try {
			//base64 解码
			 byte[] b = decoder.decodeBuffer(pic);
//			 for(int i =0;i<b.length;i++){
//				 if(b[i]<0){
////				 调整异常数据
//					 b[i]+=256;
//				 }
//			 }
//		 生成png图片
			 String imgFilePath = "C:/websoft/tomcat/apache-tomcat-7.0.56/webapps/uploadimage/"+path;//生成新的图片
			 OutputStream out = new FileOutputStream(imgFilePath);
			 out.write(b);
			 out.flush();
			 out.close();
			 return true;
		} catch (Exception e) {
			return false;
		} 
		 
	 }

}
