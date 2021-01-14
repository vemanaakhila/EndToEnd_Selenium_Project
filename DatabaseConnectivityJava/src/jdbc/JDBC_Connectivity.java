package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC_Connectivity {

	public static void main(String[] args) throws SQLException {

		// "jdbc:mysql://" + host + ":" + port + "/DatabaseName"
		String host = "localhost";
		String port = "3306";
		Connection jdbcConnection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/QaDB", "root",
				"Root_12345");

		// A path
		Statement s = jdbcConnection.createStatement();

		// Executing query
		ResultSet result = s.executeQuery("select * from EmployeeInfo where name='AkhilaVK'");

		// Ideally result is stored in 1st index rather than base index i.e. headers
		while (result.next()) {
			System.out.println("Name value of the query executed is : " + result.getString("name"));
		}
	}
}
