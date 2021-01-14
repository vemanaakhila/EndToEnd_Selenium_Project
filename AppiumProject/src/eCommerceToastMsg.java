import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.junit.Assert;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class eCommerceToastMsg extends eCommerceBase {

	public static void main(String[] args) throws MalformedURLException {
		AndroidDriver<AndroidElement> driver = eCapabilities();
		
		driver.findElementByClassName("android.widget.Spinner").click();
		
		// Scrolling until Bhutan is visible and then clicking the element
	//   driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textMatches(\"" + containedText + "\").instance(0))"));  
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Bhutan\"));");
		driver.findElement(By.xpath("//*[@text='Bhutan']")).click();

		driver.findElementByXPath("(//android.widget.RadioButton)[2]").click();
		driver.findElementById("com.androidsample.generalstore:id/btnLetsShop").click();
		System.out.println("Clicked Lets Shop button...");
		
		//Handling toast message 
		String toastMsg = driver.findElementByXPath("//android.widget.Toast[1]").getAttribute("name");
		System.out.println("Toast message captured is: " + toastMsg);
		Assert.assertEquals("Please enter your name", toastMsg);
	}
}
