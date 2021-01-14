package E2E;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Resources.BaseClass;
import pageObjects.LandingPage;
import pageObjects.LoginPage;

public class HomePageTestCase extends BaseClass {
	public static Logger log = LogManager.getLogger(HomePageTestCase.class.getName());
	@BeforeTest
	public void initialize() throws IOException {
		// as return type is webDriver
				driver = initializeDriver();
				log.info("Driver is initialized...");
				
	}
	@Test(dataProvider="getData")
	public void homePageNavigation(String userName, String password, String text) {
		//url is invoked here in this case bcz we are using data provider and test has to be executed for all the data we provide. If we do not include it here,  
		driver.get(prop.getProperty("url"));
		
		// either create objects for class or use inheritance
		LandingPage l = new LandingPage(driver);
		l.getLogin().click();
		LoginPage lp = new LoginPage(driver);
		lp.getEmail().sendKeys(userName);
		lp.getPassword().sendKeys(password);
		lp.signBtn().click();
		log.info(text);
	}

	@DataProvider
	public Object[][] getData() {
		Object[][] data = new Object[2][3];
		data[0][0] = "akhilavema@yhs.com";
		data[0][1] = "ksldhajk";
		data[0][2] = "Restricted User";
		data[1][0] = "mdaan@hmai.com";
		data[1][1] = "kzjdbqiuwhydiu";
		data[1][2] = "Allowed User";

		return data;
	}
	@AfterTest
	public void tearDown() {
		driver.close();
	}

}
