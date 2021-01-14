import java.net.MalformedURLException;

import org.openqa.selenium.WebElement;

import io.appium.java_client.TouchAction;
import static io.appium.java_client.touch.TapOptions.tapOptions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static java.time.Duration.ofSeconds;

public class gestures1 extends base {

	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		// TODO Auto-generated method stub
		AndroidDriver<AndroidElement> driver = Capabilities();
		
		driver.findElementByAndroidUIAutomator("text(\"Continue\")").click();
		driver.findElementByXPath("//android.widget.Button[@text='OK']").click();
		driver.findElementByAndroidUIAutomator("text(\"Views\")").click();
		WebElement expLists = driver.findElementByXPath("//android.widget.TextView[@text='Expandable Lists']");
		
		// Tap
		TouchAction t = new TouchAction(driver);
		
		// import for element: import static io.appium.java_client.touch.offset.ElementOption.element;
		t.tap(tapOptions().withElement(element(expLists))).perform();
		System.out.println("Successfully tapped Exapandable Lists");
		driver.findElementByXPath("//android.widget.TextView[@text='1. Custom Adapter']").click();
		
		// LongPress
		WebElement peopleNames = driver.findElementByAndroidUIAutomator("text(\"People Names\")");
		t.longPress(longPressOptions().withElement(element(peopleNames)).withDuration(ofSeconds(2))).release().perform();
		Thread.sleep(2000);
		System.out.println(driver.findElementById("android:id/title").isDisplayed());
	}

}
