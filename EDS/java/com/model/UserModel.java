package com.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.HashMap;

import com.bean.UserBean;
import com.exception.ApplicationException;
import com.exception.DatabaseException;
import com.exception.DuplicateRecordException;
import com.exception.RecordNotFoundException;
import com.util.EmailBuilder;
import com.util.EmailMessage;
import com.util.EmailUtility;
import com.util.JDBCDataSource;

public class UserModel extends BaseModel {

	Connection conn = null;
	PreparedStatement ps = null;

	public String getKey() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getTableName() {
		// TODO Auto-generated method stub
		return "user";
	}

	/******** adding user *******************/
	public long add(UserBean bean) throws ApplicationException {
		// log.debug("Model add Started");

		long pk = 0;

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPk();
			// Get auto-generated next primary key

			// conn.setAutoCommit(false); // Begin transaction
			conn.setAutoCommit(false);

			ps = conn.prepareStatement("INSERT INTO " + getTableName() + " VALUES(?,?,?,?,?,?)");

			ps.setLong(1, pk);
			// ps.setLong(1, 1);
			ps.setString(2, bean.getFirstName());
			ps.setString(3, bean.getLastName());
			ps.setString(4, bean.getEmail());
			ps.setString(5, bean.getPassword());
			ps.setString(6, bean.getRecentMsg());

			ps.executeUpdate();
			conn.commit(); // End transaction
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
			// log.error("Database Exception..", e);
			try {
				// System.out.println("catch me h ");
				conn.rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in add User");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		// log.debug("Model add End");
		return pk;
	}

	/*************** Updating user ******************/

	public void update(UserBean bean) throws ApplicationException, DuplicateRecordException {
		// log.debug("Model update Started");
		/*
		 * UserModel beanExist = findByLogin(bean.getLogin()); // Check if
		 * updated LoginId already exist if (beanExist != null &&
		 * !(beanExist.getId() == bean.getId())) { throw new
		 * DuplicateRecordException("LoginId is already exist"); }
		 */
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			System.out.println(getTableName());
			ps = conn.prepareStatement(
					"UPDATE " + getTableName() + " SET FirstName=?,LastName=?,Password=? WHERE Email=?");
			ps.setString(1, bean.getFirstName());
			ps.setString(2, bean.getLastName());
		
			ps.setString(3, bean.getPassword());
			ps.setString(4, bean.getEmail());

			// ps.setLong(18, bean.getId());
			ps.executeUpdate();

			// updateModifiedInfo(bean);
			conn.commit(); // End transaction
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
			// log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception in updating User ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		// log.debug("Model update End");
	}
/*****************Registering user*********************/
	

	public long registerUser(UserBean bean) throws ApplicationException,
			DuplicateRecordException {

		//log.debug("Model add Started");

		long pk = add(bean);

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("login", bean.getEmail());
		map.put("password", bean.getPassword());

		String message = EmailBuilder.getUserRegistrationMessage(map);

		EmailMessage msg = new EmailMessage();

		msg.setTo(bean.getEmail());
		msg.setSubject("Registration is successful for EDS");
		msg.setMessage(message);
		msg.setMessageType(EmailMessage.HTML_MSG);

		EmailUtility.sendMail(msg);
		return pk;
	}
	/************** Authenticating ****************/

	public UserBean authenticate(String login, String password) throws ApplicationException {
		// log.debug("Model authenticate Started");

		StringBuffer sql = new StringBuffer("SELECT * FROM " + getTableName() + " WHERE Email = ? AND Password = ?");

		// log.info("SQL : " + sql);

		UserBean bean = null;

		try {
			conn = JDBCDataSource.getConnection();
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, login);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				bean = new UserBean();
				// bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setEmail(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setRecentMsg(rs.getString(6));

			}
		} catch (Exception e) {
			e.printStackTrace();
			// log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in get roles");

		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		// log.debug("Model authenticate End");
		return bean;
	}

	public UserBean findByLogin(String login) throws ApplicationException {
		// log.debug("Model findByLogin Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM " + getTableName() + " WHERE Email=?");
		UserBean bean = null;

		System.out.println("sql " + sql);

		try {
			conn = JDBCDataSource.getConnection();
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, login);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				bean = new UserBean();
				// bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setEmail(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setRecentMsg(rs.getString(6));

			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			// log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting User by login");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		// log.debug("Model findByLogin End");
		return bean;
	}

	/**************** Pass forget *************/

	public boolean resetPassword(UserBean bean) throws ApplicationException {

		//String str = String.valueOf(new Date().getTime()).substring(0, 4);

		// String newPassword = str.substring(str.length()-4);
		// UserBean userData = findByPK(bean.getId());
		// userData.setPassword(newPassword);

		System.out.println(bean.getPassword() + "reset ,e");

		try {
			update(bean);
			// updateModifiedInfo(bean);
		} catch (DuplicateRecordException e) {
			return false;
		}

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("login", bean.getEmail());
		map.put("password", bean.getPassword());
		map.put("firstName", bean.getFirstName());
		map.put("lastName", bean.getLastName());

		String message = EmailBuilder.getForgetPasswordMessage(map);

		EmailMessage msg = new EmailMessage();

		msg.setTo(bean.getEmail());
		msg.setSubject("Password has been reset");
		msg.setMessage(message);
		msg.setMessageType(EmailMessage.HTML_MSG);

		EmailUtility.sendMail(msg);

		return true;
	}

	public boolean forgetPassword(String login) throws ApplicationException, RecordNotFoundException {
		UserBean userData = findByLogin(login);
		boolean flag = false;

		if (userData == null) {
			throw new RecordNotFoundException("Email ID does not exists !");

		}

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("login", userData.getEmail());
		map.put("password", userData.getPassword());
		map.put("firstName", userData.getFirstName());
		map.put("lastName", userData.getLastName());
		String message = EmailBuilder.getForgetPasswordMessage(map);
		EmailMessage msg = new EmailMessage();
		msg.setTo(login);
		msg.setSubject("EDS Password Reset Successfully");
		msg.setMessage(message);
		msg.setMessageType(EmailMessage.HTML_MSG);
		EmailUtility.sendMail(msg);
		flag = true;

		return flag;
	}
	
	public boolean msgForward(String login, String text, String key) throws ApplicationException, RecordNotFoundException {
		UserBean userData = findByLogin(login);
		boolean flag = false;

		if (userData == null) {
			throw new RecordNotFoundException("Email ID does not exists !");

		}

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("login", userData.getEmail());
		map.put("password", userData.getPassword());
		map.put("text", text);
		map.put("key", key);
		String message = EmailBuilder.getMessage(map);
		EmailMessage msg = new EmailMessage();
		msg.setTo(login);
		msg.setSubject("EDS Encyption/Decryption Successfull");
		msg.setMessage(message);
		msg.setMessageType(EmailMessage.HTML_MSG);
		EmailUtility.sendMail(msg);
		flag = true;

		return flag;
	}
	
	
	public String generateOTP(String login) throws RecordNotFoundException, ApplicationException{
		
		String str=String.valueOf(new Date().getTime());
		String otp = str.substring(str.length()-4);
		
		UserBean userData = findByLogin(login);
		boolean flag = false;
		
		if (userData == null) {
			throw new RecordNotFoundException("Email ID does not exists !");
		
		}
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("login", userData.getEmail());
		map.put("OTP",otp );
		map.put("firstName", userData.getFirstName());
		map.put("lastName", userData.getLastName());
		String message = EmailBuilder.getOTPMessage(map);
		EmailMessage msg = new EmailMessage();
		msg.setTo(login);
		msg.setSubject("EDS sends you OTP..");
		msg.setMessage(message);
		msg.setMessageType(EmailMessage.HTML_MSG);
		EmailUtility.sendMail(msg);
		flag = true;
		
		return otp;
		
		}

}
