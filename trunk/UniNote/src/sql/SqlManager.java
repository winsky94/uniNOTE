package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlManager {
	public static Connection getConnection() throws SQLException,
			java.lang.ClassNotFoundException {
	
		String driverName = "com.mysql.jdbc.Driver";
		String userName = "root";
		String userPasswd = "12345a";
		String dbName = "uninote";
		String url = "jdbc:mysql://localhost/" + dbName + "?user=" + userName
				+ "&password=" + userPasswd
				+ "&useUnicode=true&characterEncoding=UTF-8";

		try {
			Class.forName(driverName).newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		Connection c= (Connection) DriverManager.getConnection(url);

		return c;
	}

}
