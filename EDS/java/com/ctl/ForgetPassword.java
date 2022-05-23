package com.ctl;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.UserBean;
import com.exception.ApplicationException;
import com.exception.RecordNotFoundException;
import com.model.UserModel;


@WebServlet(urlPatterns="/ForgetPasswordCtl")
public class ForgetPassword extends HttpServlet {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		UserModel model=new UserModel();
		UserBean bean=null;
		
	//	System.out.println("out of if");
		
		if( req.getParameter("operation")!=null &&   req.getParameter("operation").equals("confirm")){
			
			//System.out.println("inside of if");
			try {
				bean=model.findByLogin(req.getParameter("email"));
			} catch (ApplicationException e) {
				
				e.printStackTrace();
			}
			
			if(bean!=null){
				
				String otp = null;
				try {
					//model.forgetPassword(bean.getLogin());
					 otp=model.generateOTP(bean.getEmail());
				} catch (ApplicationException e) {
				
					e.printStackTrace();
				} catch (RecordNotFoundException e) {
				
					e.printStackTrace();
				}
				
				req.setAttribute("OTP",otp );
				req.setAttribute("Login", bean.getEmail());
				req.getRequestDispatcher("forgetPassword.jsp").forward(req, resp);
				}
			
			else{
			    req.setAttribute("msg", "Email ID does not exist..!");
				req.getRequestDispatcher("forgetPassword.jsp").forward(req, resp);
				}
			
			}
		
		System.out.println("outside forget if");
		
		if(req.getParameter("operation")!=null && req.getParameter("operation").equals("Submit")){
			
			System.out.println("inside forget if");
			
			try {
				
				bean=model.findByLogin(req.getParameter("email"));
				bean.setPassword(req.getParameter("newpassword"));
				System.out.println(bean.getFirstName()+" "+bean.getEmail()+" "+bean.getPassword());
				model.resetPassword(bean);
				//model.forgetPassword(req.getParameter("email"));
			} catch (ApplicationException e) {
				
				e.printStackTrace();
			}
			
			req.setAttribute("msg", "Plz Login again with recovered Password...!");
			req.getRequestDispatcher("login.jsp").forward(req, resp);
			}
		}
}
