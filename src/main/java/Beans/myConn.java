package Beans;

import java.sql.Connection;
import java.sql.DriverManager;

public class myConn {
	private Connection con;
	public Connection getConn() {
		try {
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3308/liveproject","root","1234");
		}
		catch (Exception e) {
			e.printStackTrace();
			}
		return con;
	}
}
