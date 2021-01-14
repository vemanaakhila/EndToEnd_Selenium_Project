package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ApiHomePage {
	public ApiHomePage(AndroidDriver<AndroidElement> d) {
		PageFactory.initElements(new AppiumFieldDecorator(d), this);
	}
	
//	@AndroidFindBy(uiAutomator="new UiSelector().text(\"Continue\")")
//	public AndroidElement continueButton;
//	
//	@AndroidFindBy(xpath="//android.widget.Button[@text='OK']")
//	public WebElement okButton;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Preference']")
	public WebElement preferences;
}
