package at.edu.uas.fm.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLConnector {

	private static final String DATABASE_NAME = "fama";
	private static final String DATABASE_URL = "localhost:3306";
	private static final String DATABASE_USERNAME = "dbadmin";
	private static final String DATABASE_PASSWORD = "dbadmin123";

	private static Connection connection;

	private static Connection connectToDatabase(String dbName, String url,
			String username, String password) {

		Connection connection = null;
		String connectionURL = "jdbc:mysql://" + url + "/" + dbName + "?user="
				+ username + "&password=" + password;

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(connectionURL);
			System.out.println("Database connection established");
			return connection;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Connection getConnection() {
		if (connection == null)
			connection = connectToDatabase(DATABASE_NAME, DATABASE_URL,
					DATABASE_USERNAME, DATABASE_PASSWORD);
		return connection;

	}

}
