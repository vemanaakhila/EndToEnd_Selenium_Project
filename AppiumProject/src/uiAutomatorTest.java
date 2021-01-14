import java.util.List;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class uiAutomatorTest extends base {
	public static void main(String[] args) throws MalformedURLException {
		AndroidDriver<AndroidElement> driver = Capabilities();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.findElementByAndroidUIAutomator("text(\"Continue\")").click();
		driver.findElementByXPath("//android.widget.Button[@text='OK']").click();
		
		// Syntax of UIAutomator: driver.findElementByAndroidUIAutomator("attribute(value)")
		driver.findElementByAndroidUIAutomator("text(\"Views\")").click();
		
		// Syntax to select element(s) using properties like clickable, enabled, etc
		List<AndroidElement> numOfClickableViews = driver.findElementsByAndroidUIAutomator("new UiSelector().clickable(true)");
		System.out.println("The number of clickbale options in Views are : " + numOfClickableViews.size()); 		
	}
}
