package com.wuxin.shop.dbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String DBDRIVER="com.mysql.jdbc.Driver";
    private static final String DBURL="jdbc:mysql://localhost:3306/mshop";
    private static final String DBUSER="root";
    private static final String DBPASSWORD="snows";
	private Connection conn;

	public DatabaseConnection() {
		try {
			Class.forName(DBDRIVER);
			this.conn = DriverManager.getConnection(DBURL, DBUSER, DBPASSWORD);
			System.out.println("***************数据库连接成功！************");
		} catch (Exception e) {
			System.out.println("***************数据库连接失败！************");
			e.printStackTrace();
		}
	}
	public Connection getConnection() {
		return this.conn ;
	}
	public void close() {
		if (this.conn != null) {
			try {
				this.conn.close() ;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
