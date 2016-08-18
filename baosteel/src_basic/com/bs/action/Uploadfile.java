package com.bs.action;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class Uploadfile extends HttpServlet {

    private static final long serialVersionUID = 5827821285414610443L;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // �ϴ��ļ�Ŀ¼
        String uploadDir = "E:/files"; // this.getServletContext().getRealPath("/files");Ĭ�ϵ���tomcat����webapp�����Ŀ¼��
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // �����ڴ������С4KB
        factory.setSizeThreshold(4 * 1024);
        // �����ݴ����������ϴ��ļ��������õ��ڴ���Сʱ�����ݴ���������ת
//        factory.setRepository(new File(this.getServletContext().getRealPath(
//                "/temp")));
        factory.setRepository(new File("E:/temp"));
        ServletFileUpload fileUpload = new ServletFileUpload(factory);
        fileUpload.setSizeMax(1024 * 1024 * 100);//���ֵ��100M
        List<FileItem> fileItemList = null;

        try {
            fileItemList = fileUpload.parseRequest(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Iterator<FileItem> fileItemIterator = fileItemList.iterator();
        FileItem fileItem = null;
        while (fileItemIterator.hasNext()) {
            fileItem = fileItemIterator.next();
            // ��ͨ�ļ����ϴ�
            if (fileItem.isFormField()) {
                String filedName = fileItem.getFieldName();
                String filedValue = fileItem.getString("utf-8");// �����ʽ
                System.out.println(filedName);// �ļ�������
                System.out.println(filedValue);// �ļ���ֵ
            } else {
                String filedName = fileItem.getFieldName();// �ļ��ϴ��������
                // ��ȡ�ļ��ϴ����ļ���
                String OriginalFileName = takeOutFileName(fileItem.getName());
                System.out.println("ԭʼ�ļ�����"+OriginalFileName);
                if (!"".equals(OriginalFileName)) {
                    // �����ϴ����ļ�����������
                    String newFileName = getNewFileName(OriginalFileName);
                    System.out.println("��������"+newFileName);
                    File writeToFile = new File(uploadDir + File.separator
                            + newFileName);
                    try {
                        fileItem.write(writeToFile);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private String takeOutFileName(String filePath) {
        String fileName = filePath;
        if (null != filePath && !"".equals(filePath)) {
            int port = filePath.lastIndexOf("\\");
            if(port != -1){
                fileName = filePath.substring(port+1);
            }
        }
        return fileName;
    }

    private String getNewFileName(String originalFileName) {
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
            newFileName.append(fileName);
            newFileName.append(suffix);
            newFileName.append(".");
            newFileName.append(type);
        }
        return newFileName.toString();
    }

}