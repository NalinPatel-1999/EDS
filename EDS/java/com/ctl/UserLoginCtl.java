package com.ctl;

import java.io.IOException;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.UserBean;
import com.exception.ApplicationException;
import com.model.UserModel;



@WebServlet(urlPatterns="/userLoginCtl")
public class UserLoginCtl extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		    UserModel model=new UserModel();
		    UserBean bean=null;
		
		if(req.getParameter("operation")!=null && req.getParameter("operation").equals("LogIn")){
		
			try {
				bean=model.authenticate(req.getParameter("email"), req.getParameter("password"));
				} catch (ApplicationException e1) {
				e1.printStackTrace();
				}
			if(bean!=null){
				
					HttpSession session=req.getSession();
					//System.out.println("hii I'm first session  "+session);
				    session.setAttribute("bean", bean);
                    req.getRequestDispatcher("welcome.jsp").forward(req, resp);
				}
			
			else {
				req.setAttribute("msg", "Login ID or Password is Incorrect");
				
				req.getRequestDispatcher("login.jsp").forward(req, resp);
				
			}
		} 
         }
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.sendRedirect("index.jsp");
	}



}
