package com.bs.action;

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

import com.bs.dao.InforgatherpieDao;

public class Inforgatherpie extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req,HttpServletResponse resp) 
	          throws ServletException,IOException{
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req,HttpServletResponse resp) 
	         throws ServletException,IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		
		InforgatherpieDao pieDao = new InforgatherpieDao();
		List<Map<String,Object>> infos=pieDao.Inforgatherpie();
		JSONArray infoslist = JSONArray.fromObject(infos);

		PrintWriter pw = null;
		try{
			pw = resp.getWriter();
			pw.print(infoslist);
			pw.flush();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(pw!=null){
				pw.close();
			}
		}
		
		
	}

}
