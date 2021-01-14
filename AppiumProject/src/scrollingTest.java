import java.net.MalformedURLException;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class scrollingTest extends base {

	public static void main(String[] args) throws MalformedURLException {
		AndroidDriver<AndroidElement> driver = Capabilities();
		driver.findElementByAndroidUIAutomator("text(\"Continue\")").click();
		driver.findElementByXPath("//android.widget.Button[@text='OK']").click();
		driver.findElementByAndroidUIAutomator("text(\"Views\")").click();
		
		// Scrolling
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"WebView\"));");
		System.out.println("Identified WebView on scrolling down in Views");
		
//driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Radio Group\"));"); - deos not scroll till end, stops scrolling on locating Radio Group
	}

}
