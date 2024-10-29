package test_Cases;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import Fatales.Selenium_fatales_project_Test_Component.BaseTest;
import pageObject.CardPage;
import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.ProductPage;

public class TestCase extends BaseTest {

	
	@Test	
	public void ConnectToSite ( ) throws InterruptedException {
		
		
	HomePage home =	login.Login("kahlouchmamado@gmail.com", "MEDKHL@ssw0rd");
	
	home.SearchProductByAutoSuggetion("Yves saint laurent" , "YSL Y MEN Eau de Parfum");	

	ProductPage productPage = home.SelectProductBrachneByName("PARFUM");
	Thread.sleep(1000);	
		
	productPage.SortingProdBy("NameZToA");
	productPage.SortingProdBy("AscendingPrice");
	
	System.out.println("test 1 Done");
	System.out.println("*************************");
	 home.SelectProductBrachneByName("PARFUM");
	Thread.sleep(1000);	
	
	List<String> expectedList =	productPage.FilterByCheckingItem(Arrays.asList("FEMME","HOMME","Eau de Parfum", "100 ML", "CLINIQUE","AZZARO","RABANNE" ) );		
	List<String> obtainedList =	productPage.GetListOfCheckBoxesSelected();
	assertTrue(obtainedList.containsAll(expectedList), "Error on selecting checkboxes");

	System.out.println("test 2 Done");
	System.out.println("*************************");
	
	List<String> FiltredItems =	productPage.Get_Liste_Of_Items_In_Active_Filter_Section();
	assertTrue(FiltredItems.containsAll(expectedList), "Error on filtring Items");

	System.out.println("test 3 Done");
	System.out.println("*************************");
			
	String[] SortedList =	productPage.FilterByPrice("250,000 TND", "350,000 TND");
	assertTrue(SortedList[0].contains("250,000 TND") && SortedList[1].contains("350,000 TND")   );
			
		/*
	System.out.println("test 4 Done");
	System.out.println("*************************");
	
	home.SelectProductBrachneByName("PARFUM");
	
	CardPage card = productPage.OpenProductThenAddToCard(Arrays.asList("ARMANI CODE COFFRET PARFUM", "ARMANI CODE EAU DE TOILETTE"   ),
									 Arrays.asList(4,3),
								 Arrays.asList("75 ML", "75 ML") );
	
	productPage.GoToCardpage();	
	
	List<String> productAddedList =	card.GetProductNameAddedToCard();
	assertEquals( Arrays.asList("ARMANI CODE COFFRET PARFUM", "ARMANI CODE EAU DE TOILETTE") , productAddedList);
	
	
	System.out.println("test 5 Done");
	System.out.println("*************************");	
	
	List<Double> ExpectedPrices = 	productPage.PricesOfProductsAddedToCard;
 	List<Double> ObtainedPrices =	card.GetListOfAddedPrices();				
 	assertTrue(ExpectedPrices.containsAll(ObtainedPrices));	
 	
 	System.out.println("test 6 Done");
	System.out.println("*************************");	
 	
	List<Double> ExpectedTotal = new ArrayList<Double>();
	for ( int i=0; i< ExpectedPrices.size(); i++  ) {
		double Total =  ExpectedPrices.get(i) * Arrays.asList(4,3).get(i) ;
		ExpectedTotal.add(Total);
	}
 	
	List<Double> ObtainedTotal = 	card.CheckAndGetAddedTotalAmount();
	
	assertTrue(ExpectedTotal.equals(ObtainedTotal));

	System.out.println("test 7 Done");
	System.out.println("*************************");	
	
	double ExpectedTotalCard = 0;
	for ( double total : ExpectedTotal  ) {
		ExpectedTotalCard = ExpectedTotalCard +  total;
	}
		
	double	ObtainedTotalCard =  card.CheckAndGetSumOfAddedItems();
	
	assertTrue(ExpectedTotalCard == ObtainedTotalCard);

	System.out.println("test 8 Done");
	System.out.println("*************************");	
		*/	 
	
//	productPage.getPriceByName(Arrays.asList("ARMANI CODE COFFRET PARFUM","ARMANI CODE EAU DE TOILETTE", "ARMANI CODE LE PARFUM" , "ARMANI SI EAU DE PARFUM"));
	
//	productPage.GoToCardpage();
//	List<String> productAddedList =	card.GetProductNameAddedToCard();
//	assertEquals( Arrays.asList("ARMANI CODE COFFRET PARFUM","ARMANI CODE EAU DE TOILETTE", "ARMANI CODE LE PARFUM" , "ARMANI SI EAU DE PARFUM") , productAddedList);
	
	
//	productPage.getPriceByName("ARMANI CODE EAU DE TOILETTE");
	
	/*

	
	System.out.println("Expected list : " + expectedList);
	System.out.println("Obtained List : " + obtainedList );
	 
	System.out.println("Filtred Items list : " + FiltredItems );
	 */
	
		/*
	productPage.AddProductsToCard(Arrays.asList("ARMANI CODE COFFRET PARFUM","ARMANI CODE EAU DE TOILETTE", "ARMANI CODE LE PARFUM" , "ARMANI SI EAU DE PARFUM"), 
			Arrays.asList(null,"125 ML","50 ML", "100 ML"));
		*/
	}
	
	
	
	
	
	
	
}
