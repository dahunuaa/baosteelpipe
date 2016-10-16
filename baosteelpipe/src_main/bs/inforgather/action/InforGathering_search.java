package bs.inforgather.action;

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
import bs.inforgather.dao.InforGathering_searchDao;

import com.bs.dao.UserDao;
import com.bs.system.Constant;
public class InforGathering_search extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		    HttpSession session = req.getSession();
		//System.out.println(session.getAttribute(Constant.IS_LOGIN));
			req.setCharacterEncoding("utf-8");
			resp.setCharacterEncoding("utf-8");
			String title = req.getParameter("inforgather_title");
			String area = req.getParameter("inforgather_area");
			String address = req.getParameter("inforgather_address");
			String field = req.getParameter("inforgather_oil_field");
			String textarea = req.getParameter("inforgather_textarea");
			String pull_count = req.getParameter("pull_count");
			int count = Integer.parseInt(pull_count);
			
            InforGathering_searchDao searchDao = new InforGathering_searchDao(title,area,address,field,textarea,count);
            List<Map<String,Object>> infos = searchDao.inforgathering_searchrecord();

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
