package Beans;

public class jdbc {

	private String driver = "com.mysql.jdbc.Driver" ;
	
	private String database_url = "jdbc:mysql://localhost:3308/liveproject";
	
	private String root = "root";
	
	private String pass = "1234";

	public String getDriver() {
		return driver;
	}

	public String getDatabase_url() {
		return database_url;
	}

	public String getRoot() {
		return root;
	}

	public String getPass() {
		return pass;
	}
	
	
	
}
