package bs.bussinfo.action;

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
import bs.bussinfo.dao.Buss_searchDao;
import bs.bussinfo.dao.myBusinessRecordDao;

import com.bs.dao.UserDao;
import com.bs.system.Constant;
public class Buss_search extends HttpServlet {
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
			String bussmen_name = req.getParameter("bussmen_name");
			String buss_accounts = req.getParameter("buss_accounts");
			String buss_place = req.getParameter("buss_place");
			String buss_reason = req.getParameter("buss_reason");
			String buss_begintime = req.getParameter("buss_begintime");
			String buss_endtime = req.getParameter("buss_endtime");
			String p_count = req.getParameter("pull_count");
			int count = Integer.parseInt(p_count);
            Buss_searchDao searchDao = new Buss_searchDao(bussmen_name,buss_accounts,buss_place,buss_reason,buss_begintime,buss_endtime,count);
            List<Map<String,Object>> infos = searchDao.Buss_searchRecord();
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
