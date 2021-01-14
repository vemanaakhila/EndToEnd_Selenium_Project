import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class base {

	public static AndroidDriver<AndroidElement> Capabilities() throws MalformedURLException {
		File f = new File("src");
		File fs = new File(f, "ApiDemos-debug.apk");
		
		// Getting virtual device name and telling Appium client/server which app to use
		DesiredCapabilities dc = new DesiredCapabilities();

		dc.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel_4_XL_Emulator");
		
		/* Handling files in a better way as local path may not be available in other's
		  systems */
		dc.setCapability(MobileCapabilityType.APP, fs.getAbsolutePath());
		dc.setCapability(MobileCapabilityType.AUTOMATION_NAME,"uiautomator2");
		dc.setCapability("appPackage", "io.appium.android.apis");
		dc.setCapability("appActivity", "io.appium.android.apis.ApiDemos");

		// Android driver class's object handles Android and IOSDriver for apple OS.
		// This step forms connection to server from client
		AndroidDriver<AndroidElement> driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), dc);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}

}
