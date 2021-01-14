import java.net.MalformedURLException;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

import static io.appium.java_client.touch.offset.ElementOption.element;
import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static java.time.Duration.ofSeconds;

import static io.appium.java_client.touch.TapOptions.tapOptions;

public class eCommerceContextSwitchTest extends eCommerceBase {

	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		AndroidDriver<AndroidElement> driver = eCapabilities();
		
		driver.findElementByClassName("android.widget.Spinner").click();
		
		// Scrolling until Bhutan is visible and then clicking the element
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Bhutan\"));");
		driver.findElement(By.xpath("//*[@text='Bhutan']")).click();
		driver.findElementByClassName("android.widget.EditText").sendKeys("arsu");
		
		// Keyboard actions to hide keyboard on entering name 
		driver.hideKeyboard();
		driver.findElementByXPath("(//android.widget.RadioButton)[2]").click();
		driver.findElementById("com.androidsample.generalstore:id/btnLetsShop").click();
		System.out.println("Clicked Let's Shop button...");
		
		System.out.println("Clicking ADD TO CART for first 2 products displayed");
		driver.findElementsByXPath("//*[@text='ADD TO CART']").get(0).click();
		driver.findElementsByXPath("//*[@text='ADD TO CART']").get(0).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
		
		// Add Thread.sleep bcz the same object is being referenced in products page and cart page for amounts.
		Thread.sleep(4000);
		
		// Mobile gestures
		WebElement checkbox = driver.findElementByClassName("android.widget.CheckBox");
		WebElement termsText = driver.findElementById("com.androidsample.generalstore:id/termsButton");
		TouchAction t = new TouchAction(driver);
		t.tap(tapOptions().withElement(element(checkbox))).perform();
		
		t.longPress(longPressOptions().withElement(element(termsText)).withDuration(ofSeconds(2))).release().perform();
		driver.findElementById("android:id/button1").click();
		
		// Clicking web view link to navigate to browser
		driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();
		Thread.sleep(7000);
		
		// Switching context
		Set<String> context = driver.getContextHandles();
		for(String contextNames : context) {
			// Enhanced for loop of java
			System.out.println(contextNames);
		}
		driver.context("WEBVIEW_com.androidsample.generalstore");
		WebElement searchText = driver.findElement(By.name("q"));
		searchText.sendKeys("eCommerce site web view");
		searchText.sendKeys(Keys.ENTER);
		
		// Gng back to NATIVE_APP
		driver.pressKey(new KeyEvent(AndroidKey.BACK));
	}
}
