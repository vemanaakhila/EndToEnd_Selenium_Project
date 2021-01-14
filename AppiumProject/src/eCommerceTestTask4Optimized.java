import java.net.MalformedURLException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.junit.Assert;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static java.time.Duration.ofSeconds;

import static io.appium.java_client.touch.TapOptions.tapOptions;

public class eCommerceTestTask4Optimized extends eCommerceBase {

	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		AndroidDriver<AndroidElement> driver = eCapabilities();
		
		driver.findElementByClassName("android.widget.Spinner").click();
		
		// Scrolling until Bhutan is visible and then clicking the element
	//   driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textMatches(\"" + containedText + "\").instance(0))"));  
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
		
		// Iterating over the product price
		double sum = 0;
		List<AndroidElement> prodPrice = driver.findElementsById("com.androidsample.generalstore:id/productPrice");
		int prodSize = prodPrice.size();
		for(int i=0; i<prodSize; i++) {
			String prodPriceText = prodPrice.get(i).getText();
			double expAmount = getAmount(prodPriceText);
			sum = sum + expAmount;
		}

		String totalAmount = driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
		double totalValueAct = getAmount(totalAmount);
		
		System.out.println("Expected sum: "+ sum);
		System.out.println("Actual sum: "+ totalValueAct);
		
		//https://stackoverflow.com/questions/5686755/meaning-of-delta-or-epsilon-argument-of-assertequals-for-double-values
		Assert.assertEquals(Double.doubleToLongBits(sum), Double.doubleToLongBits(totalValueAct));
		
		// Mobile gestures
		WebElement checkbox = driver.findElementByClassName("android.widget.CheckBox");
		WebElement termsText = driver.findElementById("com.androidsample.generalstore:id/termsButton");
		TouchAction t = new TouchAction(driver);
		t.tap(tapOptions().withElement(element(checkbox))).perform();
		
		t.longPress(longPressOptions().withElement(element(termsText)).withDuration(ofSeconds(2))).release().perform();
		driver.findElementById("android:id/button1").click();
		
		// Clicking web view link to navigate to browser
		driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();
	}
	
	// A method to getAmount, getting string without a $ sign, convert string to double 
	public static double getAmount(String value) {
		value = value.substring(1);
		double valueDouble = Double.parseDouble(value);
		return valueDouble;
	}
}
