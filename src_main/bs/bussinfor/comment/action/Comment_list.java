package bs.bussinfor.comment.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import bs.bussinfor.comment.dao.CommentListDao;
import bs.notice.dao.NoticelistDao;

public class Comment_list extends HttpServlet {
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
			resp.setCharacterEncoding("utf-8");
			String buss_id = req.getParameter("buss_id");
			CommentListDao dao = new CommentListDao(buss_id);
		    List<Map<String,Object>> infos = dao.comment_list();
      
		    JSONArray infoslist = JSONArray.fromObject(infos);

			PrintWriter pw = null;
			try {
				pw = resp.getWriter(); 
				 pw.print(infoslist);
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
