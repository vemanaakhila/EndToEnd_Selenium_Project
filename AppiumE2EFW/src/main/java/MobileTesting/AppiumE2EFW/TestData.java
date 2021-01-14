package MobileTesting.AppiumE2EFW;

import org.testng.annotations.DataProvider;

public class TestData {
	
	@DataProvider(name="userNameInput")
	public static Object[][] getDataOfUserName() {
		Object[][] obj = new Object[][] {
			{"Akma"}, {"#45th"}
		};
		return obj;
	}

}
