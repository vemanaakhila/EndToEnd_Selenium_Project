package E2E;

import java.io.IOException;

import org.apache.logging.log4j.*;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Resources.BaseClass;
import pageObjects.LandingPage;

public class ValidateTitleTestCase extends BaseClass {
	public static Logger log = LogManager.getLogger(ValidateTitleTestCase.class.getName());
	@BeforeTest
	public void initialize() throws IOException {
		// as return type is webDriver
				driver = initializeDriver();
				log.info("Driver is initialized...");
				driver.get(prop.getProperty("url"));
				log.info("URL is hit and naviagted to home page..");
	}
	@Test
	public void homePageNavigation() {
	
		// either create objects for class or use inheritance
		LandingPage l = new LandingPage(driver);

		// assertion to validate if actual and expected results are same
		Assert.assertEquals(l.getTitle().getText(), "FEATURED COURSES");
		log.info("Successfully grabbed the text..");

	}
	@AfterTest
	public void tearDown() {
		driver.close();
		
		//once execution is completed, close driver object for a particular test bcz it heaps up memory
		driver = null;
	}

}
