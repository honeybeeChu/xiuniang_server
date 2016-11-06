package com.xiuniang.xiuniang_server.servlet;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBTest {
	
	public static void main(String[] args) {
		//jdbc驱动
		String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
		//连接服务器和数据库shopping
		String dbURL="jdbc:sqlserver://58.210.143.138:8044;DatabaseName=bserp_rfx";
		String userName="sa";
		String userPwd="ruifuxiang";
		Connection connection;
		try {
			//加载驱动
			Class.forName(driverName);
			//获取连接
			connection=DriverManager.getConnection(dbURL, userName, userPwd);
			System.out.println("Connection SQLServer Successful!");
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
