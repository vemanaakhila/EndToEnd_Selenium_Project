package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
	public static WebDriver driver;
	By email = By.id("user_email");
	By password = By.name("user[password]");
	By signInBtn = By.cssSelector("input[value='Log In']");
	
	public LoginPage(WebDriver driver) {
		LoginPage.driver = driver;
	}
	
	public WebElement getEmail() {
		return driver.findElement(email);
	}
	
	public WebElement getPassword() {
		return driver.findElement(password);
	}
	
	public WebElement signBtn() {
		return driver.findElement(signInBtn);
	}
}
