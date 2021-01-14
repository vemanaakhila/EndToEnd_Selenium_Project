import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.junit.Assert;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class eCommerceTestTask4 extends eCommerceBase {

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
		// Getting prices of individual items in cart and then summing up
		String amountItem1 = driver.findElementsById("com.androidsample.generalstore:id/productPrice").get(0).getText();
		String amountItem2 = driver.findElementsById("com.androidsample.generalstore:id/productPrice").get(1).getText();
		amountItem1 = amountItem1.substring(1);
		amountItem2 = amountItem2.substring(1);
		
		// Total value 
		String totalAmount = driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
		totalAmount = totalAmount.substring(1);
		
		// Converting string amount values to double
		double amt1Value = Double.parseDouble(amountItem1);
		double amt2Value = Double.parseDouble(amountItem2);
		double totalValueAct = Double.parseDouble(totalAmount);
		double totalSumOfProdsExp = amt1Value + amt2Value;
		
		System.out.println("Expected sum: "+ totalSumOfProdsExp);
		System.out.println("Actual sum: "+ totalValueAct);
		
		//https://stackoverflow.com/questions/5686755/meaning-of-delta-or-epsilon-argument-of-assertequals-for-double-values
		Assert.assertEquals(Double.doubleToLongBits(totalSumOfProdsExp), Double.doubleToLongBits(totalValueAct));
	}
}
