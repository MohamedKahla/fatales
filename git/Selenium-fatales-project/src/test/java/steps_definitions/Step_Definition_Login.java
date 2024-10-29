package steps_definitions;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import Fatales.Selenium_fatales_project_Test_Component.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObject.HomePage;
import pageObject.LoginPage;

public class Step_Definition_Login extends BaseTest   {


	
	//  LoginPage login;
	//  HomePage home ;	
	
@Given("^User landed on Autentification pages$")	
public void Go_to_Autentification_page () throws IOException {
	TestContext.login = 	LunchApplication(); 
}
	  		
@Given("^User connected with valid (.+) and (.+)$")
public void Connect_with_valid_credentials (String email , String password  ) throws IOException {
	TestContext.home =  TestContext.login.Login(email, password);
}		 
		
 @When("^User click on My Account button$")
 public void user_clicks_on_my_account_button() { 
	 TestContext.home.MyAccount.click();	 
 }
	
 @Then("^The Message \"([^\"]*)\" displayed$")
 public void the_message_displayed(String expectedMessage) {
	 
	String ObtainedMessage = TestContext.home.ConfirmationLogin.getText();
	assertTrue(expectedMessage.equals(ObtainedMessage));
 }
	
	
	
	
	
}
