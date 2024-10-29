package pageObject;import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import abstractComponent.AbstractComponent;

public class CardPage extends AbstractComponent {

WebDriver driver;
	
public 	CardPage (WebDriver driver) {
	super(driver);
	this.driver = driver;
	PageFactory.initElements(driver, this);								
}
	
	
@FindBy (xpath = "//div[@class='card-block']/h1" )
WebElement Header;

@FindBy (xpath = "//div[@class='product-line-info']/a"  )
List<WebElement>  ListOfProductsAddedToCard;

@FindBy ( xpath  = "//div[@class='product-line-grid-body col-md-4 col-xs-8']/div/div/span"  )
List<WebElement>  ListOfAddedPrices;

String  Price = ".//div[@class='product-line-grid-body col-md-4 col-xs-8']/div/div/span";

@FindBy ( xpath  = "//div[@class='product-line-grid-right product-line-actions col-md-5 col-xs-12']/div/div/div/div/span/strong"  )
List<WebElement>  ListOfAddedTotalAmount;

String  Amount = ".//div[@class='product-line-grid-right product-line-actions col-md-5 col-xs-12']/div/div/div/div/span/strong";

String  Qauantity = ".//div[@class='product-line-grid-right product-line-actions col-md-5 col-xs-12']/div/div/div/div/div/div/input";

@FindBy ( xpath  = "//li[@class='cart-item']/div"  )
List<WebElement>  ProductItems;

@FindBy ( css  = "div[class='cart-summary-line cart-total'] span[class='value']"  )
WebElement  TotalCard;

public void verifyAccessToCardPage () {

assertTrue(Header.isDisplayed());	
assertEquals(Header.getText() , "Panier");
}
	

public List<String> GetProductNameAddedToCard () {
		
		List<String> Names =	ListOfProductsAddedToCard.stream().map(P -> P.getText().toUpperCase()).collect(Collectors.toList())  ;
		return Names;
}
	


	
public List<Double> GetListOfAddedPrices () {
		
		List<Double> Prices =	ListOfAddedPrices.stream().map(P -> P.getText().replaceAll("[^0-9,]", "").replace(",", ".") ).map(Double::parseDouble).collect(Collectors.toList());
		return Prices;
}
	



public List<Double> CheckAndGetAddedTotalAmount () throws InterruptedException {
	
List<Double> GettedTotalAmount = new ArrayList<Double>();
	
		for (WebElement ProductItem : ProductItems ) {
		
		String P  = 	ProductItem.findElement(By.xpath(Price)).getText().replaceAll("[^0-9,]", "").replace(",", ".") ;
		double GettedPrice = Double.parseDouble(P);	
		System.out.println("Price is : "  + GettedPrice );
	
		
		String Q = 	ProductItem.findElement(By.xpath(Qauantity)).getAttribute("value").replaceAll("[^0-9]", "").trim();
		int GettedQuantity = Integer.parseInt(Q);
		System.out.println("quantity is : "  + GettedQuantity );
	
		
		String T = 	ProductItem.findElement(By.xpath(Amount)).getText().replaceAll("[^0-9,]", "").replace(",", ".");
		double GettedAmount = Double.parseDouble(T);
		System.out.println("Amount is : "  + GettedAmount );
	
		
		double ExpectedAmount = GettedPrice * GettedQuantity;
			
			if ( ExpectedAmount ==   GettedAmount  )	{
				
				GettedTotalAmount.add(GettedAmount);
			}	
			
			else {
				
				System.out.println("Getted Amount does not match with Expected Amout");
				System.out.println("Getted Amount is : "  + GettedAmount );
				System.out.println("Expected Amount is : " + ExpectedAmount);
			}
			
			Thread.sleep(1000);
		}
			
	return 	GettedTotalAmount;	
}




public double CheckAndGetSumOfAddedItems () {
	
	String Textcard =	TotalCard.getText().replaceAll("[^0-9,]", "").replace(",", ".");
	double AmountInCard = Double.parseDouble(Textcard);

	double AddedAmount = 0;
	
	for (WebElement TotalAmount : ListOfAddedTotalAmount ) {
		
	String TxtAmount = TotalAmount.getText().replaceAll("[^0-9,]", "").replace(",", ".");
	double MT = Double.parseDouble(TxtAmount);
	
	AddedAmount = AddedAmount +  MT;
	
	}
	
assertTrue(AddedAmount == AmountInCard );
	 
	 if (AddedAmount != AmountInCard ) {
		 
			System.out.println("Getted Total card does not match with Expected Total card");
			System.out.println("Getted Total card is : "  + AmountInCard );
			System.out.println("Expected Getted Total card is : " + AddedAmount);
	 }
	 
	return AmountInCard;
	
	
}


	
	
	
}
