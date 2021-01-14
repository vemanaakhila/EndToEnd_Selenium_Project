package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class FormPage {

	public FormPage(AndroidDriver<AndroidElement> d) {
		PageFactory.initElements(new AppiumFieldDecorator(d), this);
	}
	

	@AndroidFindBy(className="android.widget.EditText")
	private WebElement name;
	
	@AndroidFindBy(xpath="(//android.widget.RadioButton)[2]")
	public WebElement femaleOption;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/btnLetsShop")
	public WebElement letsShopBtn;
	
	public WebElement getName() {
		return name;
	}
}
