package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

public class LoginPage {
	
	WebDriver driver;
	
	public LoginPage (WebDriver driver ) {
	this.driver = driver;	
	PageFactory.initElements(driver, this);	
	}

	@FindBy(xpath = "//span[@class='login']")
	WebElement Connexion;
	
	@FindBy(xpath = "//div/label[@for='email']/following-sibling::input")
	WebElement EmailField;
	
	@FindBy(xpath = "//div/label[@for='passwd']/following-sibling::input")
	WebElement passwordField;
	
	@FindBy(id = "SubmitLoginheader_nav")
	WebElement SubmitButton;
	
	
	public void GoToUrl () 
	{	
		driver.get("https://www.fatales.tn/");
	}
	
	
	public HomePage Login (String email , String password)
	{
		Connexion.click();
		EmailField.sendKeys(email);
		passwordField.sendKeys(password);
		SubmitButton.click();
		
		HomePage home =  new HomePage(driver);
		return home;		
	}

	
// xpath parfum	(//a[@href='https://www.fatales.tn/428-fragrance']/span[@class='menu-title'])[1]
	


}
