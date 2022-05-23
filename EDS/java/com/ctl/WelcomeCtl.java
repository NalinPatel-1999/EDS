package com.ctl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.CORBA.Request;

import com.sun.mail.iap.Response;


@WebServlet("/welcomeCtl")
public class WelcomeCtl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		if (request.getParameter("operation") != null && request.getParameter("operation").equals("Encrypt My Text")) {

			request.getRequestDispatcher("encrypt.jsp").forward(request, response);

		}

		if (request.getParameter("operation") != null && request.getParameter("operation").equals("Decrypt My Text")) {

			request.setAttribute("op", "Decrypt");

			request.getRequestDispatcher("decrypt.jsp").forward(request, response);

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
