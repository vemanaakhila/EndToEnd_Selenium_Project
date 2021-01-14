package resources;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import MobileTesting.AppiumE2EFW.eCommerceBase;

public class Listeners implements ITestListener{
	
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub

	}
	

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub

	}
	
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		
		//to know in which method it failed
		result.getName();
		
		//Taking screenshot on failure
		try {
			eCommerceBase.takeScreenshot(result.getName());
	}catch( IOException e) {
			e.printStackTrace();
		}
	}
	

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

	}
	

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}
	

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}
	

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub

	}
}
