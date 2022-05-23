package com.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.exception.DatabaseException;
import com.util.JDBCDataSource;

public abstract class BaseModel {
	
/***********get table name*********/

	
	public abstract String getTableName();
	
	
	
	public long nextPk() throws DatabaseException{
		
		
		//log.debug("Model nextPK Started");
		Connection conn = null;
		long pk = 0;
		try {
			
			System.out.println(getTableName()+"apna");
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select max(ID) from "+getTableName());
			//"SELECT MAX(ID) FROM"+ getTableName()
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getLong(1);
			}
			rs.close();

		} catch (Exception e) {
			e.printStackTrace();
			//log.error("Database Exception..", e);
			throw new DatabaseException("Exception : Exception in getting PK");
		} finally {
			
			JDBCDataSource.closeConnection(conn);
			
			
		}
		//log.debug("Model nextPK End");
		return pk + 1;
	}


}
