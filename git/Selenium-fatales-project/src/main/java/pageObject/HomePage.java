package pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import net.bytebuddy.asm.Advice.Return;

public class HomePage {

	WebDriver driver;
	
public 	HomePage (WebDriver driver) {
		this.driver = driver;	
		PageFactory.initElements(driver, this);	
}
	


@FindBy (xpath =
"//div[@class='leo-top-menu collapse navbar-toggleable-md megamenu-off-canvas megamenu-off-canvas-1']/ul[@class='nav navbar-nav megamenu horizontal']/li")
List<WebElement> navbar;

@FindBy ( xpath = "//div[@class='text-search']/input")
WebElement SearchField;

@FindBy(css = "li[class='ui-menu-item'] a span"  )
List<WebElement> Suggetions;

@FindBy ( xpath = "//div/a[@class='login'] " )
public WebElement MyAccount;

@FindBy ( xpath = "//a[@class='login']/following-sibling::ul/li[@class='custmer_connect']")
public WebElement ConfirmationLogin;


public ProductPage SelectProductBrachneByName (String ProductBrancheName) {
	
	WebElement ProductBranche = navbar.stream().filter(P-> P.getText().equals(ProductBrancheName)).findFirst().orElseThrow(() -> new RuntimeException("Product Branche not found : " + ProductBrancheName));
	ProductBranche.click();

	ProductPage Prod = new ProductPage(driver) ;

	return Prod;
}



public void SearchProductByAutoSuggetion (String InsertedText, String  SuggettedText) throws InterruptedException {
	
	SearchField.sendKeys(InsertedText);
	Thread.sleep(5000);
	
	for (WebElement Suggetion : Suggetions) {
		
		String ProductText =	Suggetion.getText();
			if ( ProductText.equals(SuggettedText)  ) {
				Suggetion.click();
			}
	}
}






}