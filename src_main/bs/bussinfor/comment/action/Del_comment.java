package bs.bussinfor.comment.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bs.bussinfor.comment.dao.Comment_deleteDao;
import bs.notice.dao.Notice_deleteDao;

public class Del_comment extends HttpServlet {
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
		String comment_user_id = (String)session.getAttribute("id");
		String type = req.getParameter("type");
		String user_id = req.getParameter("user_id");
		String comment_id = req.getParameter("comment_id");
		String str= "";
        if(user_id.equals(comment_user_id)){
        	Comment_deleteDao dao = new Comment_deleteDao(type,comment_id);
    		dao.comment_delete();
    		str = "ok";
        }else {
        	str = "fail";
		}
		
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
