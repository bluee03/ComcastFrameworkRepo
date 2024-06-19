package com.comcast.crm.generic_Databaseutility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class DataBaseUtility {

	Connection conn;

	public void getDbconnection(String url, String username, String password) throws Throwable {

		try {
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			conn = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
		                }
	}
	public void getDbconnectionwithoutargs() throws Throwable {
		
		try {
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila", "root", "Pankaj@123");
		} catch (Exception e) {
				System.out.println("Exception in DB Connection");
		                       }
	}

	public void closeDbconnection() throws SQLException {
		conn.close();
	}

	public ResultSet executeSelectQuery(String query) throws Throwable {
		ResultSet result=null;
		try{
			Statement stat = conn.createStatement();
		    result = stat.executeQuery(query);
		}catch (Exception e) {
			
		}
		return result;
}
	
	public int executeNonSelectQuery(String query) throws Throwable {
		int result=0;  
		try{
			Statement stat = conn.createStatement();
			result = stat.executeUpdate(query);
		}catch (Exception e) {
			
		}
		return result;
		}
}
