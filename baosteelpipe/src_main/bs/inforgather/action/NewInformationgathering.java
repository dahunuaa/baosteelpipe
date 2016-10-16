package bs.inforgather.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bs.inforgather.dao.NewInformationgatheringDao;

import com.bs.action.Uploadfile;
import com.bs.dao.UserNameDao;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NewInformationgathering extends HttpServlet{

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
		String gather_editor =(String) session.getAttribute("id");
		String gather_title = req.getParameter("gather_title");
		String gather_address = req.getParameter("gather_address");
		String gather_area = req.getParameter("gather_area");
		String gather_text = req.getParameter("gather_text");
		String gather_oil_field = req.getParameter("gather_oil_field");
		UserNameDao namedao = new UserNameDao(gather_editor);
		String gather_editor_name = namedao.getName();
		SimpleDateFormat sm=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = sm.format(new Date());
		SimpleDateFormat pm=new SimpleDateFormat("yyyyMMddHHmmss");
		String origin_file_name = req.getParameter("origin_file_name");
		String new_file_name = req.getParameter("new_file_name");		
		
		NewInformationgatheringDao dao = new NewInformationgatheringDao(gather_editor,gather_editor_name,gather_title,gather_address,gather_area,gather_text,gather_oil_field,time,origin_file_name,new_file_name);
		dao.Newinformationgathering();

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
