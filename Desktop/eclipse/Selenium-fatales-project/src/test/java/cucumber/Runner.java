package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;



@CucumberOptions ( 
		features="C:\\Users\\MED\\Desktop\\eclipse\\Selenium-fatales-project\\src\\test\\java\\cucumber\\AddProductsToCard.feature" ,
		glue = "steps_definitions",
		monochrome=true ,
		tags = "@AddToCard" ,
		plugin = {"html:target\\cucumber.html"} )

public class Runner extends AbstractTestNGCucumberTests {

}
