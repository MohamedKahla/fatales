package steps_definitions;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.WebDriver;

import Fatales.Selenium_fatales_project_Test_Component.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObject.CardPage;
import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.ProductPage;

public class Step_Deifinition_Add_Products_To_Card extends BaseTest {


	
	  CardPage card ;	
	  ProductPage productPage;
//    LoginPage login;
//	  HomePage home ;	
	
	
	
@When("^User click on the \"([^\"]*)\" category$")	
public void userClicksOnCategory(String category) {
	 productPage = TestContext.home.SelectProductBrachneByName(category);
}	
	
	
@When("^User add \"([^\"]*)\" with (.+) and \"([^\"]*)\"$")
public void userAddsProductsWithQuantitiesAndSizes(String productNames, String quantities, String contenance) throws InterruptedException {
   
	List<String> ProductList = Arrays.asList(productNames.split(",\\s*"));
	List<Integer> quantitiesList =	Arrays.stream(quantities.split(",")).map(Integer::parseInt).collect(Collectors.toList());
	List<String> contenanceList = Arrays.asList(contenance.split(",\\s*"));
	
	card =  productPage.OpenProductThenAddToCard(ProductList, quantitiesList, contenanceList);	
}	
	
	
@Then("^Products are added to card$")
public void productsAreAddedToCart() {
	
	// attention ce code System.out.println est provisoire -- à valider la fonctionnalité avec les méthodes concernées
    System.out.println("Product Added to card");
}	
	
	
	
	
	
}
