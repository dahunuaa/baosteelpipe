package com.bs.action;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class Uploadfile extends HttpServlet {

    public static String new_file_name = "";
    public static String origin_file_name = "";
    public static String user_id="";

    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        this.doPost(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    	
    	req.setCharacterEncoding("utf-8");
    	resp.setCharacterEncoding("utf-8");
    	HttpSession session = req.getSession();
    	user_id=(String) session.getAttribute("id");
        // 上传文件目录
        String uploadDir = "C:/websoft/tomcat/apache-tomcat-7.0.56/webapps/uploadfiles"; 
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 设置内存区块大小4KB
        factory.setSizeThreshold(4 * 1024);
        // 设置暂存容器，当上传文件大于设置的内存块大小时，用暂存容器做中转
        factory.setRepository(new File("C:/websoft/tomcat/apache-tomcat-7.0.56/webapps/temp"));
        ServletFileUpload fileUpload = new ServletFileUpload(factory);
        fileUpload.setSizeMax(1024 * 1024 * 100);//最大值是100M
        List<FileItem> fileItemList = null;

        try {
            fileItemList = fileUpload.parseRequest(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Iterator<FileItem> fileItemIterator = fileItemList.iterator();
        FileItem fileItem = null;
        while (fileItemIterator.hasNext()) {
            fileItem = fileItemIterator.next();
            // 普通文件框上传
            if (fileItem.isFormField()) {
                String filedName = fileItem.getFieldName();
                String filedValue = fileItem.getString("utf-8");
            } else {
                String filedName = fileItem.getFieldName();// 文件上传框的名称
                // 获取文件上传的文件名
                String OriginalFileName = takeOutFileName(fileItem.getName());
                origin_file_name =OriginalFileName; 
                if (!"".equals(OriginalFileName)) {
                    // 根据上传的文件名重新命名
                    String newFileName = getNewFileName(OriginalFileName);
//                    System.out.println(newFileName);
                    new_file_name = newFileName;
                    File writeToFile = new File(uploadDir + File.separator
                            + newFileName);
                    try {
                        fileItem.write(writeToFile);
                    } catch (Exception e) {
                        e.printStackTrace();                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               
                    }
                }else{
                	new_file_name="";
                }
            }
        }
		 PrintWriter pw = null;
		
		 try {
			pw = resp.getWriter(); 
			 pw.print(new_file_name);
			 pw.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(pw!=null){
				pw.close();
			}
		}
    	
    }


    public String takeOutFileName(String filePath) {
        String fileName = filePath;
        if (null != filePath && !"".equals(filePath)) {
            int port = filePath.lastIndexOf("\\");
            if(port != -1){
                fileName = filePath.substring(port+1);
            }
        }
        return fileName;
    }

    public String getNewFileName(String originalFileName) {
        StringBuffer newFileName = new StringBuffer();
        if (null != originalFileName && !"".equals(originalFileName)) {
            int port = originalFileName.lastIndexOf(".");
            String type = "";
            String fileName = "";
            if (port != -1) {
                type = originalFileName.substring(port + 1);
                fileName = originalFileName.substring(0, port);
            } else {
                fileName = originalFileName;
            }
            StringBuffer suffix = new StringBuffer("_");
            suffix.append(Calendar.getInstance().getTimeInMillis());
            suffix.append("_");
            suffix.append(new Random().nextInt(100));
//            newFileName.append(fileName);//服务器中的文件名不能含有汉字
            newFileName.append(user_id);
            newFileName.append(suffix);
            newFileName.append(".");
            newFileName.append(type);
        }
        return newFileName.toString();
    }

}