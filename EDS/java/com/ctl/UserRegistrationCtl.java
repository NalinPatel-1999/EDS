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
import com.exception.DatabaseException;
import com.exception.DuplicateRecordException;
import com.model.UserModel;
import com.util.DataUtility;
import com.util.PropertyReader;


@WebServlet(urlPatterns="/UserRegistrationCtl")
public class UserRegistrationCtl extends HttpServlet {

	
	/**
	 * 
	 */
	
	

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		UserModel model=new UserModel();
		UserBean bean=new UserBean();
		UserBean bean1=null;
		
		try {
			bean1=model.findByLogin(req.getParameter("email"));
			System.out.println("try block");
		} catch (ApplicationException e2) {
		    e2.printStackTrace();
			}
		
		if(bean1==null){
			
		
		
		
		
		bean.setFirstName(req.getParameter("firstName"));
		bean.setLastName(req.getParameter("lastName"));
		bean.setPassword(req.getParameter("password"));
		bean.setEmail(req.getParameter("email"));
		//bean.setRecentMsg(req.getParameter("msg"));
		
		/*************************************************************/
		
		//To store data into database and mail activity starts here.....
		
		try {
			model.registerUser(bean);
		} catch (ApplicationException e) {
			e.printStackTrace();
		} catch (DuplicateRecordException e) {
			
			e.printStackTrace();
		}
		
		req.setAttribute("msg", "Registration is successful Please Login Once.");
		req.getRequestDispatcher("login.jsp").forward(req, resp);
		
		}else{
			
			System.out.println("hii im running");
			req.setAttribute("firstname", req.getParameter("firstName"));
			req.setAttribute("lastname", req.getParameter("lastName"));
			
			
			req.setAttribute("msg", "This Email Address Already registered with us. Please Login or Register with another email address.");
			req.getRequestDispatcher("login.jsp").forward(req, resp);
		}
		}
		
		
	
	}
