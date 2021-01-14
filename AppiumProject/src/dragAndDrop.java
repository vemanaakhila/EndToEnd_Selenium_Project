import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static java.time.Duration.ofSeconds;

import java.net.MalformedURLException;

import org.openqa.selenium.WebElement;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class dragAndDrop extends base {

	public static void main(String[] args) throws MalformedURLException {
		AndroidDriver<AndroidElement> driver = Capabilities();
		driver.findElementByAndroidUIAutomator("text(\"Continue\")").click();
		driver.findElementByXPath("//android.widget.Button[@text='OK']").click();
		driver.findElementByAndroidUIAutomator("text(\"Views\")").click();
		System.out.println("Clicking Drag and Drop");
		driver.findElementByXPath("//android.widget.TextView[@text='Drag and Drop']").click();
		
		// LongPress on source object and release it at the destination object place.
		WebElement initialObject = driver.findElementsByClassName("android.view.View").get(0);
		WebElement destinationObject = driver.findElementsByClassName("android.view.View").get(1);
		
		TouchAction ta = new TouchAction(driver);
//		ta.longPress(longPressOptions().withElement(element(initialObject))).moveTo(element(destinationObject)).release().perform();
		
		ta.longPress(element(initialObject)).moveTo(element(destinationObject)).release().perform();
		System.out.println("Drag and drop is successful");
	}
}
