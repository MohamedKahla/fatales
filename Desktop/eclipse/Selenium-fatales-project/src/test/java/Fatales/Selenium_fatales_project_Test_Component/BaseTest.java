package Fatales.Selenium_fatales_project_Test_Component;

import java.io.File;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.BeforeMethod;

import pageObject.LoginPage;




public class BaseTest {

		
public WebDriver driver;	
public LoginPage login;	

public WebDriver initilizeDriver () throws IOException {
	
	// new Properties object to load configurations from a file.	
	Properties prop = new Properties();	
		
	//Creating a file input stream to read the properties file.
	//System.getProperty("user.dir") retrieves the current working directory path
	//This allows us to locate the properties file within the project structure
	FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\ressources\\GlobalData.properties");
	prop.load(file);	

	/**
	if System.getProperty("browser") not null so execute the instruction before ":" System.getProperty("browser") -- take the browser name entred from cmd commande
	else if System.getProperty("browser") null so execute the instruction after ":" prop.getProperty("browser") -- take browser name from GlobalData file
	System.getProperty("browser"): to use the "browser" value sent via cmd : -Dbrowser=chrome
	prop.getProperty("browser") : to Retrieving the desired browser's name from the properties JSON file: browser=Chrome
	*/
	String BrowserName = System.getProperty("browser") != null ? System.getProperty("browser") : prop.getProperty("browser");


	if ( BrowserName.contains("Chrome") ) {
		
	System.setProperty("webdriver.chrome.driver", "\\Users\\MED\\Documents\\chromedriver.exe");

	// Initialize the ChromeDriver with the specified options.	
	ChromeOptions Options = new ChromeOptions();	

		if ( BrowserName.contains("headless")  ) 
			{
			// run Chrome in headless mode.
			Options.addArguments("headless");
			}

	driver = new ChromeDriver(Options);
	driver.manage().window().maximize();
		
	}


	else if ( BrowserName.contains("Edge") ) {
		
	System.setProperty("webdriver.chrome.driver", "\\Users\\MED\\Documents\\msedgedriver.exe");


	driver = new EdgeDriver();
	driver.manage().window().maximize();
		
	}


	else if ( BrowserName.contains("Edge") ) {
		
	//implement firefoxdriver
		
	}

	return driver;	

}

@BeforeMethod
public LoginPage LunchApplication () throws IOException {
	
	driver = initilizeDriver ();
	login = new LoginPage(driver);
	login.GoToUrl();
	return login;
	
}



public String GetScreenShot (String TestCaseName , WebDriver driver) throws IOException {
	
	TakesScreenshot ts = (TakesScreenshot)driver;	
	File source = ts.getScreenshotAs(OutputType.FILE);
	File file = new File(System.getProperty("user.dir")+"\\reports\\" +  TestCaseName + ".png" );
	FileUtils.copyFile(source, file);
	return System.getProperty("user.dir")+"\\reports\\" +  TestCaseName + ".png";
}








}

