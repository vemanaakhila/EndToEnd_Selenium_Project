package MobileTesting.AppiumE2EFW;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class eCommerceBase {

	public static AppiumDriverLocalService service;
	public static AndroidDriver<AndroidElement> driver;

	public AppiumDriverLocalService startServer() {
		boolean serverRunning = checkIfServerIsRunning(4723);
		if (!serverRunning) {
			service = AppiumDriverLocalService.buildDefaultService();
			service.start();
		}
		// As service is not initialized it might throw NullPointerException, so it is
		// better to kill the processes before starting the tests
		return service;
	}

	public static void startEmulator() throws IOException, InterruptedException {
		Runtime.getRuntime().exec(System.getProperty("user.dir") + "\\src\\main\\java\\resources\\startEmulator.bat");
		Thread.sleep(60000);
	}

	// https://discuss.appium.io/t/how-to-stop-appium-server-programmatically-stop-appiumdriverlocalservice/23105
	// - Method to check if server is running on test failure
	public boolean checkIfServerIsRunning(int port) {

		boolean isServerRunning = false;
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(port);
			serverSocket.close();

		} catch (IOException e) {
			// If control comes here, then it means that the port is in use
			isServerRunning = true;

		} finally {
			serverSocket = null;
		}
		return isServerRunning;
	}

	public static AndroidDriver<AndroidElement> eCapabilities(String appName) throws IOException, InterruptedException {
		// To get the path of apk file dynamically

//		System.getProperty("user.dir"); - this gets the local project path and then looks for src folder which would be common to all users machine path.
//		FileInputStream fis = new FileInputStream("C:\\Users\\15136\\eclipse-workspace\\AppiumE2EFW\\src"); 

		String localDir = System.getProperty("user.dir");
		System.out.println("Local path is before src is : " + localDir);
		FileInputStream fis = new FileInputStream(
				localDir + "\\src\\main\\java\\MobileTesting\\AppiumE2EFW\\global.properties");
		System.out.println("Full path : " + fis);
		Properties prop = new Properties();
		prop.load(fis);

		File appDir = new File("src");
		File app = new File(appDir, (String) prop.get(appName));

		// Getting virtual device name and telling Appium client/server which app to use
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
		dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");

//		if (appName.contains("Api")) {
//			dc.setCapability("appPackage", "io.appium.android.apis");
//			dc.setCapability("appActivity", "io.appium.android.apis.ApiDemos");
//		}
		// Getting device from global properties file
		String device = (String) prop.get("deviceName");

		// Driving through command prompt to get deviceName directly and not using
		// global.properties file.
//		String device = System.getProperty("deviceName");

		if (device.contains("Pixel")) {
			startEmulator();
		}
		dc.setCapability(MobileCapabilityType.DEVICE_NAME, device);
		dc.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 14);

		driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), dc);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}

	// Screenshot method for Listeners class
	public static void takeScreenshot(String result) throws IOException {
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcFile, new File(System.getProperty("user.dir") + "\\" + result + ".png"));
	}

	@AfterTest
	public void stopServer() {
		service.stop();
	}
}