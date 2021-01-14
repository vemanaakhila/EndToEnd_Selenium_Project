import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.junit.Assert;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class cricBuzzTest extends chromeBase {

	public static void main(String[] args) throws MalformedURLException {
		// TODO Auto-generated method stub
		AndroidDriver<AndroidElement> driver = chromeCapabilities();
		
		// Make sure to have the same chromedriver versions in both appium driver and mobile device
		driver.get("https://cricbuzz.com/");
		driver.findElementByXPath("//a[@href='#menu']").click();
		driver.findElementByCssSelector("a[title='Cricbuzz Home']").click();
		System.out.println("The current URL after clicking Home is : " + driver.getCurrentUrl());
		
		// Scrolling in mobile chrome browser using js executor
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,480)", "");
		
		// Searching for Top stories header
		Assert.assertTrue(driver.findElement(By.xpath("//h4[text()='Top Stories']")).getAttribute("class").contains("header"));
		System.out.println("Grabbed Top Stories text header by scrolling..!");
	}
}
