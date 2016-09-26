package bs.inforguide.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bs.dao.UploadPicDao;

import bs.inforguide.dao.Inforguide_editDao;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Inforguide_edit extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		HttpSession session = req.getSession();
		String editor =(String) session.getAttribute("id");
		String guide_id = req.getParameter("guide_id");
		String guide_title = req.getParameter("guide_title");
		String guide_category = req.getParameter("guide_category");
		String guide_text = req.getParameter("guide_text");
		SimpleDateFormat sm=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat pm=new SimpleDateFormat("yyyyMMddHHmmss");
		String time = sm.format(new Date());
		String pic = req.getParameter("pic");
		String pic_path = editor+"_"+pm.format(new Date())+".png";
		if(pic.equals("1")){
			pic_path = req.getParameter("pic_path");
		}
		
        
		Inforguide_editDao dao = new Inforguide_editDao(guide_id,guide_title,guide_category,guide_text,time,pic_path,pic);
		dao.Inforguide_edit();
		if (pic==null||pic.equals("null")||pic.equals("1")){
			
		}else{
			pic = pic.substring(22,pic.length());
			UploadPicDao picdao = new UploadPicDao(pic_path, pic);
			boolean res = picdao.uploadpic();
		}

		 String str = "ok";
		 PrintWriter pw = null;
		
		 try {
			pw = resp.getWriter(); 
			 pw.print(str);//²»ÄÜÐ´³Éprintln
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
    
}
