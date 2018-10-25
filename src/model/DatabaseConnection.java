package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	private static DatabaseConnection instance;
	private Connection connection;
	private String url = "jdbc:mysql://localhost:3306/bdd?serverTimezone=GMT";
	private String username = "root";
	private String password = "";
	static String driver = "com.mysql.cj.jdbc.Driver";

	private DatabaseConnection() throws SQLException {// private only instanced in the class

		//Class.forName(driver);
		this.connection = DriverManager.getConnection(url, username, password);

	}

	public Connection getConnection() {
		return connection;
	}

	public static DatabaseConnection getInstance() throws SQLException {
		if (instance == null) {
			instance = new DatabaseConnection();
		} else if (instance.getConnection().isClosed()) {
			instance = new DatabaseConnection();
		}

		return instance;
	}

	public void closeConnection() {
		if (instance != null) {
			try {
				instance.getConnection().close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
	}

}
