package abstractComponent;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObject.CardPage;

public class AbstractComponent {

	WebDriver driver;
	
public AbstractComponent (WebDriver driver ) {
	this.driver = driver;
//	PageFactory.initElements(driver, this);
	
}
	
@FindBy ( css = "div div[class='shopping_cart']" )
WebElement cardButton;

@FindBy ( id = "button_order_cart"  )
WebElement ButtonVoirLePanier ;
	
public void waitForElementToAppear(WebElement waitedElement)

{
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	wait.until(ExpectedConditions.visibilityOf(waitedElement));
}


public CardPage GoToCardpage () throws InterruptedException {
	cardButton.click();
	Thread.sleep(1000);
	ButtonVoirLePanier.click();
	CardPage card = new CardPage(driver);
	return card;
}
	





}
