package ressources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporty {

	
		
public static ExtentReports GetReportObject () {
	
	
	String path =	System.getProperty("user.dir")+"\\reports\\index.html";
	
	ExtentSparkReporter reporter = new ExtentSparkReporter(path);
	reporter.config().setReportName("Fatales Automation project");
	reporter.config().setDocumentTitle("Fatales project");
	ExtentReports extent = new  ExtentReports();
	extent.attachReporter(reporter);
	extent.setSystemInfo("Tester", "MAHLA");
	return extent;
}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
