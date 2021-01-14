package E2E;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Resources.BaseClass;
import pageObjects.LandingPage;

public class ValidateNavbar extends BaseClass {
	public static Logger log = LogManager.getLogger(ValidateNavbar.class.getName());
	@BeforeTest
	public void initialize() throws IOException {
		// as return type is webDriver
				driver = initializeDriver();
				driver.get(prop.getProperty("url"));
	}
	@Test
	public void homePageNavigation() {
	
		// either create objects for class or use inheritance
		LandingPage l = new LandingPage(driver);

		// assertion to validate if navbar is present in the website page or not
		Assert.assertTrue(l.getNavbar().isDisplayed());
		log.info("Nav bar is present in the home page..");
	}
	@AfterTest
	public void tearDown() {
		driver.close();
	}

}
