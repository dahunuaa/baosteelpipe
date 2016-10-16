package com.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

public class CheckFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		   arg2.doFilter(arg0, arg1);//此处意思是通行滤掉的所有东西；可以用来设置权限；过滤器通常都是过滤掉所有的图片css样式表，JS脚本，音频，视频
	  
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
	

}
