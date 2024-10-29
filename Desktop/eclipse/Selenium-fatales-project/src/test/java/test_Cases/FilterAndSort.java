package test_Cases;

import static org.testng.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.Test;

import Fatales.Selenium_fatales_project_Test_Component.BaseTest;
import pageObject.HomePage;
import pageObject.ProductPage;

public class FilterAndSort extends BaseTest {

	
@Test	
public void SortingByNameFromZToA () throws InterruptedException {
	
	HomePage home =	login.Login("kahlouchmamado@gmail.com", "MEDKHL@ssw0rd");
	ProductPage productPage = home.SelectProductBrachneByName("PARFUM");
	Thread.sleep(1000);	
	productPage.SortingProdBy("NameZToA");
}
	
	
@Test	
public void SortingByAscendingPrice () throws InterruptedException {
	
	HomePage home =	login.Login("kahlouchmamado@gmail.com", "MEDKHL@ssw0rd");
	ProductPage productPage = home.SelectProductBrachneByName("PARFUM");
	Thread.sleep(1000);	
	productPage.SortingProdBy("AscendingPrice");
}	



@Test	
public void FliterCheckBoxes () throws InterruptedException {
	
	HomePage home =	login.Login("kahlouchmamado@gmail.com", "MEDKHL@ssw0rd");
	ProductPage productPage = home.SelectProductBrachneByName("PARFUM");
	List<String> expectedList =	productPage.FilterByCheckingItem(Arrays.asList("FEMME","HOMME","Eau de Parfum", "100 ML", "CLINIQUE","AZZARO","RABANNE" ) );		
	List<String> obtainedList =	productPage.GetListOfCheckBoxesSelected();
	assertTrue(obtainedList.containsAll(expectedList), "Error on selecting checkboxes");
}	


@Test	
public void VerifyItemsInActivFilter () throws InterruptedException {
	
	HomePage home =	login.Login("kahlouchmamado@gmail.com", "MEDKHL@ssw0rd");
	ProductPage productPage = home.SelectProductBrachneByName("PARFUM");
	List<String> expectedList =	productPage.FilterByCheckingItem(Arrays.asList("FEMME","HOMME","Eau de Parfum", "100 ML", "CLINIQUE","AZZARO","RABANNE" ) );		
	List<String> FiltredItems =	productPage.Get_Liste_Of_Items_In_Active_Filter_Section();
	assertTrue(FiltredItems.containsAll(expectedList), "Error on filtring Items");
}


@Test	
public void FilterByPrice () throws InterruptedException {
	
	HomePage home =	login.Login("kahlouchmamado@gmail.com", "MEDKHL@ssw0rd");
	ProductPage productPage = home.SelectProductBrachneByName("PARFUM");
	String[] SortedList =	productPage.FilterByPrice("70,000 TND", "1 080,000 TND");
	assertTrue(SortedList[0].contains("70,000 TND") && SortedList[1].contains("1 089,000 TND")   );
}




	
}
