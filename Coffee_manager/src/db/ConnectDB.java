package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
	public static Connection connection = null;
	private static ConnectDB instance = new ConnectDB();
	public static ConnectDB getInstance() {
		return instance;
	}
	public void connect() throws Exception{
		String URL = "jdbc:sqlserver://localhost:1433;databaseName=QLCF;";
        String user = "sa";
        String pass = "123";
        connection = DriverManager.getConnection(URL, user, pass);
        
	}
	public void disconnect() {
		if(connection!=null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static Connection getConnection() {
		return connection;
	}
	
}
