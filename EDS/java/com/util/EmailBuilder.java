package com.util;

import java.util.Date;
import java.util.HashMap;

/**
 * Class that build Application Email messages 
 */

 public class EmailBuilder {
	 /**
		 * Returns Successful User Registration Message
		 * 
		 * @param map
		 *            : Message parameters
		 * @return
		 */
	public static String getUserRegistrationMessage(HashMap<String, String> map) {
		StringBuilder msg = new StringBuilder();


		msg.append("<HTML><BODY>");
		msg.append("<H1>Hi! Greetings from Our EDS!</H1>");
		msg.append("Registration is successful for EDS Project of Tech Mahindra.");
		msg.append("<br><P>Congratulations for registering on Encryption Decryption System (EDS)! You can now access your account online - anywhere, anytime and enjoy the flexibility to secure your messaging and Details.</P>");
		String s = map.get("password");
		s=s.substring(0,2)+"*******"+s.substring(s.length()-2,s.length());
		msg.append("<P><B>Login Id : " + map.get("login") + "<BR>"
				+ " Password : " + s + "</B></p>");
		msg.append("<P> Please click this <a href='http://117.218.97.168:8085/OCFMS/LoginCtl?"+new Date().getTime() + "'>here</a> to confirm your mail id.</p>");
		msg.append("<P> As a security measure, we recommended that you change your password after you first log in.</p>");
		msg.append("<br><span style='color:red'>© Developed by Nalin Patel</span>");
		msg.append("</BODY></HTML>");

		return msg.toString();
	}
    
	/**
	 * Returns Email message of Forget Password
	 * 
	 * @param map
	 *            : params
	 * @return
	 */
	
	public static String getForgetPasswordMessage(HashMap<String, String> map) {
		StringBuilder msg = new StringBuilder();

		msg.append("<HTML><BODY>");
		msg.append("<H1>Your password is reccovered !! " + map.get("firstName")
				+ " " + map.get("lastName") + "</H1>");
		/*msg.append("<P>To access account user login ID : " + map.get("login")
				+ " and password " + map.get("password") + "</P>");*/
		msg.append("<P><B>To access account user Login Id : " + map.get("login") + "<BR>"
				+ " Password : " + map.get("password") + "</B></p>");
		msg.append("</BODY></HTML>");

		return msg.toString();
	}
	
	public static String getMessage(HashMap<String, String> map) {
		StringBuilder msg = new StringBuilder();

		msg.append("<HTML><BODY>");
		msg.append("<H1>Your Encrypted/Decryptest text is here !! <H1><BR>" +"<P><B> Text Is :- "+ map.get("text")
				+ " <BR>" +"Public key is :- "+ map.get("key") + "</B></p>");
		/*msg.append("<P>To access account user login ID : " + map.get("login")
				+ " and password " + map.get("password") + "</P>");*/
		msg.append("<P><B>To access account user Login Id : " + map.get("login") + "<BR>"
				+ " Password : " + map.get("password") + "</B></p>");
		msg.append("</BODY></HTML>");

		return msg.toString();
	}
	
	public static String getOTPMessage(HashMap<String, String> map) {
		StringBuilder msg = new StringBuilder();

		msg.append("<HTML><BODY>");
		msg.append("<H1>Your OTP for forget password is here !! " + map.get("firstName")
				+ " " + map.get("lastName") + "</H1>");
		/*msg.append("<P>To access account user login ID : " + map.get("login")
				+ " and password " + map.get("password") + "</P>");*/
		msg.append("<P><B>To access EDS account user Login Id : " + map.get("login") + "<BR>"
				+ " OTP : " + map.get("OTP") + "</B></p>");
		msg.append("</BODY></HTML>");

		return msg.toString();
	}
	
	/**
	 * Returns Email message of Change Password
	 * 
	 * @param map
	 * @return
	 */
	public static String getChangePasswordMessage(HashMap<String, String> map) {
		StringBuilder msg = new StringBuilder();

		msg.append("<HTML><BODY>");
		msg.append("<H1>Your Password has been changed Successfully !! " + map.get("firstName")
				+ " " + map.get("lastName") + "</H1>");
		/*msg.append("<P>To access account user login ID : " + map.get("login")
				+ " and password " + map.get("password") + "</P>");*/
		msg.append("<P><B>To access account user Login Id : " + map.get("login") + "<BR>"
				+ " Password : " + map.get("password") + "</B></p>");
		msg.append("</BODY></HTML>");

		return msg.toString();
	}

}
