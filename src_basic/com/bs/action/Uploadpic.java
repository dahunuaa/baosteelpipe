package com.bs.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bs.dao.UploadPicDao;

public class Uploadpic extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		String path_1 = req.getParameter("path");
		String pic = req.getParameter("file");
		System.out.println(pic);
		pic = pic.substring(22,pic.length());
		UploadPicDao dao = new UploadPicDao(path_1, pic);
		boolean res = dao.uploadpic();
         PrintWriter pw = null;
         try {
				pw = resp.getWriter(); 
				 pw.print("111");
				 pw.flush();
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				if(pw!=null){
					pw.close();
				}
			}
	}

	
}
