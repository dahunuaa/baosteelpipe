package com.bs.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bs.dao.AlterpswDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bs.system.DBUtils;
import com.bs.dao.UserDao;

public class Alterpsw extends HttpServlet{

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
		String id = (String) session.getAttribute("id");
		String psw = (String) session.getAttribute("psw");
		String oldpsw = req.getParameter("oldpsw");
		String newpsw = req.getParameter("newpsw");
		Connection conn = DBUtils.getConnection();
		
		String str ="";	
		if(psw.equals(oldpsw)){
			AlterpswDao dao = new AlterpswDao(id,newpsw);
			String alterpsw = dao.alterpsw();
			session.setAttribute("psw", alterpsw);
		     str = "ok";
		}else{
		     str = "fail";
		}
		 PrintWriter pw = null;
		
		 try {
			 pw = resp.getWriter(); 
			 pw.print(str);
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
    
//}
