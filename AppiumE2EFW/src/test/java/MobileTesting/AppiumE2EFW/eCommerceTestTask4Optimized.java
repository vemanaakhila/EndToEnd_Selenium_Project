package MobileTesting.AppiumE2EFW;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import pageObjects.CheckOutPage;
import pageObjects.FormPage;

import static io.appium.java_client.touch.offset.ElementOption.element;
import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static java.time.Duration.ofSeconds;

import java.io.IOException;

import static io.appium.java_client.touch.TapOptions.tapOptions;

public class eCommerceTestTask4Optimized extends eCommerceBase {
	
	// As service is not initialized it might throw NullPointerException, so it is better to kill the processes before starting the tests
	@BeforeTest
	public void killAllProcesses() throws IOException, InterruptedException {
		
		// exec takes string input in case there is only one command else use .bat file
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Thread.sleep(3000);
	}

	@Test
	public void testEcommerce() throws IOException, InterruptedException {
		service = startServer();
		AndroidDriver<AndroidElement> driver = eCapabilities("GeneralStoreApp");

		driver.findElementByClassName("android.widget.Spinner").click();

		// Scrolling until Bhutan is visible and then clicking the element
		// driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new
		// UiSelector().scrollable(true).instance(0)).scrollIntoView(new
		// UiSelector().textMatches(\"" + containedText + "\").instance(0))"));
		
		Helper h = new Helper(driver);
		h.scrollToText("Bhutan");
		driver.findElement(By.xpath("//*[@text='Bhutan']")).click();
		
		FormPage fp = new FormPage(driver);
		fp.getName().sendKeys("testUser");

		// Keyboard actions to hide keyboard on entering name
		driver.hideKeyboard();
		fp.femaleOption.click();
		fp.letsShopBtn.click();
		System.out.println("Clicked Let's Shop button...");

		System.out.println("Clicking ADD TO CART for first 2 products displayed");
		driver.findElementsByXPath("//*[@text='ADD TO CART']").get(0).click();
		driver.findElementsByXPath("//*[@text='ADD TO CART']").get(0).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();

		// Add Thread.sleep bcz the same object is being referenced in products page and
		// cart page for amounts.
		Thread.sleep(4000);
		
		CheckOutPage cp = new CheckOutPage(driver);
		// Iterating over the product price
		double sum = 0;
		List<WebElement> prodPrice = cp.priceOfProds;
		int prodSize = prodPrice.size();
		for (int i = 0; i < prodSize; i++) {
			String prodPriceText = prodPrice.get(i).getText();
			double expAmount = getAmount(prodPriceText);
			sum = sum + expAmount;
		}

		String totalAmount = ((WebElement) cp.totalPurchasePrice).getText();
		System.out.println("Total Amount Purchased: "+ totalAmount);
		double totalValueAct = getAmount(totalAmount);
		System.out.println("Total Amount: "+ totalValueAct);

		System.out.println("Expected sum: " + sum);
		System.out.println("Actual sum: " + totalValueAct);

		// https://stackoverflow.com/questions/5686755/meaning-of-delta-or-epsilon-argument-of-assertequals-for-double-values
//		Assert.assertEquals(Double.doubleToLongBits(sum), Double.doubleToLongBits(totalValueAct));

		Assert.assertEquals(sum, totalValueAct);
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

	// A method to getAmount, getting string without a $ sign, convert string to
	// double
	public static double getAmount(String value) {
		value = value.substring(1);
		double valueDouble = Double.parseDouble(value);
		return valueDouble;
	}
}
