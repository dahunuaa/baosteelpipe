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
import bs.inforguide.dao.MyInformationGuidingDao;

import com.bs.dao.UserDao;
import com.bs.system.Constant;
import bs.inforgather.dao.*;

public class InformationGather extends HttpServlet {
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
			String p_count = req.getParameter("pull_count");
			int count  = Integer.parseInt(p_count);
			InformationGatherDao Dao = new InformationGatherDao(count);
		    List<Map<String,Object>> infos = Dao.InformationGuidingRecord();

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
