package Resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.core.util.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseClass {
	public static WebDriver driver;

	// prop object initializes here
	public Properties prop;

	public WebDriver initializeDriver() throws IOException {

		// prop object is active here
		prop = new Properties();
		FileInputStream fis = new FileInputStream(
				"C:\\Users\\15136\\E2Eproject\\src\\main\\java\\Resources\\data.properties");
		prop.load(fis);
		String browserName = prop.getProperty("browser");
		System.out.println(browserName);

		if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "C:\\Selenium_drivers\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browserName.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", "C:\\Selenium_drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		return driver;
	}

	public void takeScreenshot(String result) throws IOException{
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		// copy this src file into local machine
		FileUtils.copyFile(src, new File("C://Users//15136//Selenium_screenshots"+result+" screenshot.png"));

	}

}
