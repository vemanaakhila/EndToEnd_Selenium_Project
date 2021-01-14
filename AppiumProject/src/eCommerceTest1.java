import java.net.MalformedURLException;
import java.util.List;

import org.openqa.selenium.By;
import org.junit.Assert;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class eCommerceTest1 extends eCommerceBase {

	public static void main(String[] args) throws MalformedURLException {
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
		
		// Scrolling and selecting the desired product
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.androidsample.generalstore:id/rvProductList\")).scrollIntoView(new UiSelector().textMatches(\"Nike SFB Jungle\").instance(0));");
		
		List<AndroidElement> prodNamesOnScroll = driver.findElementsById("com.androidsample.generalstore:id/productName");
		int countOfProds = prodNamesOnScroll.size();
		
		for(int i=0; i<=countOfProds; i++) {
			String prodName = prodNamesOnScroll.get(i).getText();
			if(prodName.equalsIgnoreCase("Nike SFB Jungle")) {
				System.out.println("Product name selected is: " + prodName);
				driver.findElementsById("com.androidsample.generalstore:id/productAddCart").get(i).click();
				break;
			}
		}
		
		System.out.println("Clicking ADD TO CART");
		driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
		
		String prodNameInATC = driver.findElementById("com.androidsample.generalstore:id/productName").getText();
		System.out.println("Product in cart: " + prodNameInATC);
		Assert.assertEquals("Nike SFB Jungle", prodNameInATC);
	}
}
