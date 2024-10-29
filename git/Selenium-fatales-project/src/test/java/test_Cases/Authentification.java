package test_Cases;

import org.testng.annotations.Test;

import Fatales.Selenium_fatales_project_Test_Component.BaseTest;
import pageObject.HomePage;

public class Authentification extends BaseTest {

	
	@Test	
	public void ConnectToSite ( ) throws InterruptedException {
		
	HomePage home =	login.Login("kahlouchmamado@gmail.com", "MEDKHL@ssw0rd");
	
	}
	
}
