import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

public class chromeBase {
	public static AndroidDriver<AndroidElement> chromeCapabilities() throws MalformedURLException {
// Getting virtual device name and telling Appium client/server which app to use
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Device");
		dc.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
//		dc.setCapability(MobileCapabilityType.BROWSER_VERSION, "74.0.3729");
//		dc.setCapability(AndroidMobileCapabilityType.CHROMEDRIVER_EXECUTABLE_DIR, "/path/to/my/chromedriver");
//		dc.setCapability("chromedriverExecutable", "C:\\Users\\15136\\AppData\\Roaming\\npm\\node_modules\\appium\\node_modules\\appium-chromedriver\\lib");
		
		AndroidDriver<AndroidElement> driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), dc);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
}