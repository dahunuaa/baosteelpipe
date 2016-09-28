package com.bs.action;

import java.awt.List;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import com.bs.dao.UserDao;
import com.bs.dao.UserNameDao;

public class Action extends HttpServlet{
	
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
		String id = req.getParameter("name");
		String password = req.getParameter("pswd");

		UserDao dao = new UserDao(id);
         String psw = dao.getPassWord();
         HttpSession session=req.getSession();
         session.setAttribute("psw", psw);
         session.setAttribute("id", id);
         UserNameDao namedao = new UserNameDao(id);
         String user_name = namedao.getName();
         String str = "";
         Map<String, String> map = new HashMap<String,String>();
         
         PrintWriter pw = null;
         if(psw.equals(password)){
        	str = "ok";
        	map.put("str", str);
        	map.put("user_name",user_name);
         }else{
        	 str = "sorry";
        	 map.put("str",str);
         } 
         JSONArray infoslist = JSONArray.fromObject(map);
         try {
				pw = resp.getWriter(); 
				 pw.print(infoslist);
				 pw.flush();
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				if(pw!=null){
					pw.close();
				}
			}
	}

	
}
