package com.ctl;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns="*.do")
public class DoFilter implements Filter{

	public void destroy() {
	
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)

			throws IOException, ServletException {
		
		HttpServletRequest req=(HttpServletRequest)request;
		HttpServletResponse resp=(HttpServletResponse)response;
		
		HttpSession session=req.getSession(false);
	
		if(session.getAttribute("bean")!=null){
			
			chain.doFilter(request, response);
			
			}else{
				
				req.setAttribute("msg", "Your Session has been expired. Please Login again with your credentials.");
				req.getRequestDispatcher("login.jsp").forward(req, resp);
			}
		
	}

	
	public void init(FilterConfig arg0) throws ServletException {
		
		
	}

}
