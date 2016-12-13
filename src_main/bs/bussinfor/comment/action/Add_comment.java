package bs.bussinfor.comment.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bs.bussinfor.comment.dao.Add_comment_Dao;
import bs.notice.dao.New_noticeDao;

import com.bs.dao.UserNameDao;

public class Add_comment extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		req.setCharacterEncoding("utf-8");
		String comment_user_id = (String) session.getAttribute("id");
		String comment_text = req.getParameter("comment_text");
		String buss_id = req.getParameter("buss_id");
		
		UserNameDao namedao = new UserNameDao(comment_user_id);
		String name = namedao.getName();
//		System.out.print(comment_user_id+":"+buss_id);
		SimpleDateFormat sm=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = sm.format(new Date());
		
		Add_comment_Dao dao = new Add_comment_Dao(buss_id,comment_user_id,comment_text,name,time);
		dao.add_comment();

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
