package bs.inforguide.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bs.inforguide.dao.NewInformationGuideDao;
import com.bs.dao.UploadPicDao;
import com.bs.system.DateDemo;
import com.bs.dao.UserNameDao;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NewInformationGuide extends HttpServlet{

	private static final DateDemo New = null;

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
		HttpSession session = req.getSession();
		String editor =(String) session.getAttribute("id");
		String title = req.getParameter("guide_title");
		String category = req.getParameter("guide_category");
		String text = req.getParameter("guide_text");
		UserNameDao namedao = new UserNameDao(editor);
		String editor_name = namedao.getName();
		SimpleDateFormat sm=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat pm=new SimpleDateFormat("yyyyMMddHHmmss");
		String time = sm.format(new Date());
		String pic_path = editor+"_"+pm.format(new Date())+".png";
		String pic = req.getParameter("pic");
		
		NewInformationGuideDao dao = new NewInformationGuideDao(editor,editor_name,title,category,text,time,pic_path,pic);
		dao.NewInformationGuide();
		if (pic==null||pic.equals("null")){
		
		}else{
			pic = pic.substring(22,pic.length());
			UploadPicDao picdao = new UploadPicDao(pic_path, pic);
			boolean res = picdao.uploadpic();
		}
		
		 String str = "ok";
		 PrintWriter pw = null;
		 try {
			pw = resp.getWriter(); 
			 pw.print(str);
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
