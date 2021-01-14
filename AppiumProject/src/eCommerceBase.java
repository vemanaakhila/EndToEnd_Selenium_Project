import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

public class eCommerceBase {
	public static AndroidDriver<AndroidElement> eCapabilities() throws MalformedURLException {
		
		File f = new File("src");
		File fs = new File(f, "General-Store.apk");
		
		// Getting virtual device name and telling Appium client/server which app to use
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability(MobileCapabilityType.APP, fs.getAbsolutePath());
		dc.setCapability(MobileCapabilityType.AUTOMATION_NAME,"uiautomator2");
		dc.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel_4_XL_Emulator");
		dc.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 14);
		
		AndroidDriver<AndroidElement> driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), dc);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
}