import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import static io.appium.java_client.touch.offset.ElementOption.element;
import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static java.time.Duration.ofSeconds;


public class swipeTest extends base {

	public static void main(String[] args) throws MalformedURLException {
		AndroidDriver<AndroidElement> driver = Capabilities();
		driver.findElementByAndroidUIAutomator("text(\"Continue\")").click();
		driver.findElementByXPath("//android.widget.Button[@text='OK']").click();
		driver.findElementByAndroidUIAutomator("text(\"Views\")").click();
		driver.findElementByXPath("//android.widget.TextView[@text='Date Widgets']").click();
		driver.findElement(By.xpath("//android.widget.TextView[@text='2. Inline']")).click();
		
		// Another locator for 9 in clock can be, driver.findElementByAndroidUIAutomator("content-desc(\"9\")")
		driver.findElementByXPath("//*[@content-desc='9']").click();
		
		// Swipe action now: LongPress for a sec or 2 and then move to desired element, leave it
		WebElement initialHold = driver.findElementByXPath("//*[@content-desc='15']");
		WebElement FinalPosition = driver.findElementByXPath("//*[@content-desc='0']");
		
		TouchAction ta = new TouchAction(driver);
		ta.longPress(longPressOptions().withElement(element(initialHold)).withDuration(ofSeconds(2))).moveTo(element(FinalPosition)).release().perform();

	}

}
