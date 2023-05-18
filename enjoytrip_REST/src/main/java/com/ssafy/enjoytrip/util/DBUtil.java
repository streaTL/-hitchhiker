package com.ssafy.enjoytrip.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import lombok.extern.apachecommons.CommonsLog;

@Component
public class DBUtil {
	
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/enjoytrips?serverTimezone=UTC";
	private static final String DB_ID = "ssafy";
	private static final String DB_PWD = "ssafy";
	
	private static DBUtil instance = new DBUtil();

	private DBUtil() {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static DBUtil getInstance() {
		return instance;
	}
	

	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, DB_ID, DB_PWD);
	}
	
	public void close(AutoCloseable... autoCloseables) {
		for(AutoCloseable ac : autoCloseables) {
			if(ac != null) {
				try {
					ac.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
