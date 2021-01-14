package MobileTesting.AppiumE2EFW;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import pageObjects.ApiHomePage;

public class apiDemosTest extends eCommerceBase{
	
	// TestData class has to be static here because we created data provider method in different class other than in same class or base class.
	@Test(dataProvider="userNameInput", dataProviderClass = TestData.class)
	public void testApiDemos(String userNameField) throws IOException, InterruptedException {
		service = startServer();
		AndroidDriver<AndroidElement> driver = eCapabilities("apiDemos");
		
//		 1.API Demos App --> Preferences --> Preference Dependencies --> Enable WiFi --> WiFi Settings --> Name --> OK
//		xpath, id, class, androidUIautomator
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		System.out.println("Opened API Demos App");
		
		driver.findElementByAndroidUIAutomator("text(\"Continue\")").click();
		driver.findElementByXPath("//android.widget.Button[@text='OK']").click();
		
		ApiHomePage hp = new ApiHomePage(driver);
//		hp.continueButton.click();
//		hp.okButton.click();
		hp.preferences.click();
		
		driver.findElement(By.xpath("//android.widget.TextView[@text='3. Preference dependencies']")).click();
		driver.findElementById("android:id/checkbox").click();
		driver.findElementByXPath("(//android.widget.RelativeLayout)[2]").click();
		driver.findElementById("android:id/edit").sendKeys(userNameField);
		
		// Using finElements to identify OK button
		driver.findElementsByClassName("android.widget.Button").get(1).click();
		System.out.println("...Successfully set WiFi name...");
		service.stop();
	}
}