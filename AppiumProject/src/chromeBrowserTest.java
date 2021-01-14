import java.net.MalformedURLException;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class chromeBrowserTest extends chromeBase {

	public static void main(String[] args) throws MalformedURLException {
		// TODO Auto-generated method stub
		AndroidDriver<AndroidElement> driver = chromeCapabilities();
		
		// Command to start appium server: appium --chromedriver-executable /path/to/my/chromedriver
		driver.get("https://m.facebook.com/");
		driver.findElementByXPath("//input[@name='email']").sendKeys("akhilavemana@gmail.com");
		driver.findElementByXPath("//input[@name='pass']").sendKeys("Akhi@6279f");
		driver.findElementByXPath("//button[@value='Log In']").click();
	}
}
