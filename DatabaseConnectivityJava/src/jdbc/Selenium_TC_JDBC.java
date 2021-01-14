package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Selenium_TC_JDBC {

	public static void main(String[] args) throws SQLException {
		String host = "localhost";
		String port = "3306";
		Connection jdbcConnection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/QaDB", "root",
				"Root_12345");

		// A path
		Statement s = jdbcConnection.createStatement();

		// Executing query
		ResultSet result = s.executeQuery("select * from LoginDetails");

		// Ideally result is stored in 1st index rather than base index i.e. headers
		while (result.next()) {
			// Invoking chrome driver
			System.setProperty("webdriver.chrome.driver", "C:\\Selenium_drivers\\chromedriver.exe");
			WebDriver driver = new ChromeDriver();
			driver.get("https://www.facebook.com/");
			driver.findElement(By.xpath("//input[@name='email']")).sendKeys(result.getString("name"));
			driver.findElement(By.xpath("//input[@name='pass']")).sendKeys(result.getString("password"));
			driver.findElement(By.xpath("//button[@name='login']")).click();
			driver.close();
		}
	}
}
