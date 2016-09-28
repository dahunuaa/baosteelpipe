package bs.inforguide.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import bs.bussinfo.dao.myBusinessRecordDao;
import bs.inforguide.dao.Inforguide_searchDao;

import com.bs.dao.UserDao;
import com.bs.system.Constant;
public class Inforguide_search extends HttpServlet {
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
			String title = req.getParameter("inforguide_title");
			String category = req.getParameter("inforguide_category");
			String content = req.getParameter("inforguide_content");
			String p_count = req.getParameter("pull_count");
			int count = Integer.parseInt(p_count);
			
            Inforguide_searchDao searchDao = new Inforguide_searchDao(title,category,content,count);
            List<Map<String,Object>> infos = searchDao.Inforguide_searchrecord();
		    JSONArray busslist = JSONArray.fromObject(infos);
			PrintWriter pw = null;
			try {
				pw = resp.getWriter(); 
				 pw.print(busslist);
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
