import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class basics extends realDeviceBase{

	public static void main(String[] args) throws MalformedURLException {
		// TODO Auto-generated method stub
		AndroidDriver<AndroidElement> driver = Capabilities("real");
		
//		 1.API Demos App --> Preferences --> Preference Dependencies --> Enable WiFi --> WiFi Settings --> Name --> OK
//		xpath, id, class, amdroidUIautomator
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		System.out.println("Opened API Demos App");
		driver.findElementByAndroidUIAutomator("text(\"Continue\")").click();
//		driver.findElement(By.xpath("//android.widget.Button[@text='Continue']")).click();
//		driver.findElementByXPath("//android.widget.Button[@text='Continue']").click();
		driver.findElementByXPath("//android.widget.Button[@text='OK']").click();
		driver.findElementByXPath("//android.widget.TextView[@text='Preference']").click();
		driver.findElement(By.xpath("//android.widget.TextView[@text='3. Preference dependencies']")).click();
		driver.findElementById("android:id/checkbox").click();
		driver.findElementByXPath("(//android.widget.RelativeLayout)[2]").click();
		driver.findElementById("android:id/edit").sendKeys("Akma");
		
		// Using finElements to identify OK button
		driver.findElementsByClassName("android.widget.Button").get(1).click();
		System.out.println("...Successfully set WiFi name...");
	}

}