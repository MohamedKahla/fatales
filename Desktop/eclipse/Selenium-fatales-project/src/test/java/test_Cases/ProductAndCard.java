package test_Cases;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.annotations.Test;

import Fatales.Selenium_fatales_project_Test_Component.BaseTest;
import pageObject.CardPage;
import pageObject.HomePage;
import pageObject.ProductPage;

public class ProductAndCard extends BaseTest {

	
	List<Double> ExpectedTotal = new ArrayList<Double>();


@Test	
public void AddProductsToCard () throws InterruptedException {
	
	HomePage home =	login.Login("kahlouchmamado@gmail.com", "MEDKHL@ssw0rd");
	ProductPage productPage = home.SelectProductBrachneByName("PARFUM");
	productPage.AddProductsToCard(Arrays.asList("ARMANI CODE COFFRET PARFUM","ARMANI CODE EAU DE TOILETTE", "ARMANI CODE LE PARFUM" , "ARMANI SI EAU DE PARFUM"), 
			Arrays.asList(null,"125 ML","50 ML", "100 ML"));	
}	


@Test	
public void OpenProductThenAddToCard () throws InterruptedException {
	
	HomePage home =	login.Login("kahlouchmamado@gmail.com", "MEDKHL@ssw0rd");
	ProductPage productPage = home.SelectProductBrachneByName("PARFUM");
	productPage.OpenProductThenAddToCard(Arrays.asList("ARMANI CODE COFFRET PARFUM", "ARMANI CODE EAU DE TOILETTE"   ),
			 Arrays.asList(4,3),Arrays.asList("75 ML", "75 ML") );	
}	


@Test	
public void VerifyProductAdedToPage () throws InterruptedException {

	HomePage home =	login.Login("kahlouchmamado@gmail.com", "MEDKHL@ssw0rd");
	ProductPage productPage = home.SelectProductBrachneByName("PARFUM");
	CardPage card =	productPage.GoToCardpage();	
	List<String> productAddedList =	card.GetProductNameAddedToCard();
	assertEquals( Arrays.asList("ARMANI CODE COFFRET PARFUM", "ARMANI CODE EAU DE TOILETTE") , productAddedList);
}	


@Test	
public void VerifyPricesAddedToCard () throws InterruptedException {

	HomePage home =	login.Login("kahlouchmamado@gmail.com", "MEDKHL@ssw0rd");
	ProductPage productPage = home.SelectProductBrachneByName("PARFUM");
	CardPage card =	productPage.GoToCardpage();	
	List<Double> ExpectedPrices = 	productPage.PricesOfProductsAddedToCard;
 	List<Double> ObtainedPrices =	card.GetListOfAddedPrices();				
 	assertTrue(ExpectedPrices.containsAll(ObtainedPrices));	
}	


@Test	
public void VerifyProductAmount () throws InterruptedException {

	HomePage home =	login.Login("kahlouchmamado@gmail.com", "MEDKHL@ssw0rd");
	ProductPage productPage = home.SelectProductBrachneByName("PARFUM");
	CardPage card =	productPage.GoToCardpage();	
	List<Double> ExpectedPrices = 	productPage.PricesOfProductsAddedToCard;
	
	for ( int i=0; i< ExpectedPrices.size(); i++  ) {
		double Total =  ExpectedPrices.get(i) * Arrays.asList(4,3).get(i) ;
		ExpectedTotal.add(Total);
	}
	
	List<Double> ObtainedTotal = 	card.CheckAndGetAddedTotalAmount();
	
	assertTrue(ExpectedTotal.equals(ObtainedTotal));
}	



@Test	
public void VerifyTotalCard () throws InterruptedException {

	HomePage home =	login.Login("kahlouchmamado@gmail.com", "MEDKHL@ssw0rd");
	ProductPage productPage = home.SelectProductBrachneByName("PARFUM");
	CardPage card =	productPage.GoToCardpage();	
	double ExpectedTotalCard = 0;
	for ( double total : ExpectedTotal  ) {
		ExpectedTotalCard = ExpectedTotalCard +  total;
	}
		
	double	ObtainedTotalCard =  card.CheckAndGetSumOfAddedItems();
	
	assertTrue(ExpectedTotalCard == ObtainedTotalCard);
}








	
	
	
}
