package Fatales.Selenium_fatales_project_Test_Component;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import ressources.ExtentReporty;

public class Listeners extends BaseTest  implements ITestListener {

	
	ExtentTest test;
	
	ExtentReports extent =	ExtentReporty.GetReportObject();
	
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	
	
public void onTestStart(ITestResult result) {

			test =	extent.createTest(result.getMethod().getMethodName());
			
			extentTest.set(test);
}
	



	
public void onTestSuccess(ITestResult result) {
	extentTest.get().log(Status.PASS, "Test successfuly passed");
}	
	
	


public void onTestFailure(ITestResult result) {

	extentTest.get().fail(result.getThrowable());
	
	try {
		driver = (WebDriver ) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
	} catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} 
	
	String path = null;
	try {
		 path =	GetScreenShot(result.getMethod().getMethodName(), driver);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	extentTest.get().addScreenCaptureFromPath(path, result.getMethod().getMethodName());
}
	
	
	


public void onFinish(ITestContext context) {
	extent.flush();
  }
	
	
	
}
