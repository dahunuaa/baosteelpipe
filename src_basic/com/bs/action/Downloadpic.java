package com.bs.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bs.dao.DownloadPicDao;;

public class Downloadpic extends HttpServlet{
	
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
		String imgfile=req.getParameter("imgfile");
		System.out.println(imgfile);
		DownloadPicDao dao = new DownloadPicDao(imgfile);
		try {
			String base64 = dao.downloadpic();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
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
