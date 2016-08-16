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
//		 ��base64�ַ�ת����ͼƬ
		 if (pic == null)
		 return false;
		 BASE64Decoder decoder = new BASE64Decoder();
		 try {
			//base64 ����
			 byte[] b = decoder.decodeBuffer(pic);
//			 for(int i =0;i<b.length;i++){
//				 if(b[i]<0){
////				 �����쳣����
//					 b[i]+=256;
//				 }
//			 }
//		 ����pngͼƬ
			 String imgFilePath = "d://111.png";//�����µ�ͼƬ
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
