package bs.bussinfo.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bs.bussinfo.dao.Buss_editDao;

import java.text.SimpleDateFormat;
import java.util.Date;
public class Buss_edit extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String bussmen = req.getParameter("bussmen_name");
		String accounts = req.getParameter("buss_accounts");
		String place = req.getParameter("buss_place");
		String reason = req.getParameter("buss_reason");
		String begintime = req.getParameter("buss_begintime");
		String endtime = req.getParameter("buss_endtime");
		String buss_id = req.getParameter("buss_id");
		SimpleDateFormat sm=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = sm.format(new Date());
		
        
		Buss_editDao dao = new Buss_editDao(buss_id,bussmen,accounts,place,reason,begintime,endtime,time);
		dao.buss_edit();
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
