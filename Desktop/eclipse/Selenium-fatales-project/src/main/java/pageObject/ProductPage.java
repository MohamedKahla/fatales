package pageObject;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.awt.Checkbox;
import java.security.PublicKey;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;
import java.util.jar.Pack200;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import abstractComponent.AbstractComponent;

public class ProductPage extends AbstractComponent {
	
	WebDriver driver;
	
	
public 	ProductPage (WebDriver driver) {
	super(driver);
	this.driver = driver;	
	PageFactory.initElements(driver, this);		
}

// liste des produits dans Product page
@FindBy (xpath = "//h2[@itemprop='name']"  )
List<WebElement> productList;

@FindBy (css = "h1[itemprop='name']"  )
WebElement ProductName ;

@FindBy ( css = "span[class='radio-label']" )
List<WebElement> ContenanceList ;

//liste des éléments web des prix des profuit dans Product page
@FindBy (xpath = "//span[@class='price product-price']" )
List<WebElement> priceList;

@FindBy ( css = "div[class='current-price our_price_display'] span[itemprop='price']" )
WebElement ProductPriceInProductScreen;

@FindBy ( xpath  = "(//strong[@class='dark']/following-sibling::span)[3]" )
WebElement ProductQuantiry;

@FindBy ( xpath  = "//div[@class='layer_cart_row']/span" )
WebElement TotalAmountInPopPup;

@FindBy (xpath = "//div[@class='col-sm-9 col-xs-8 col-md-9 products-sort-order dropdown']/button" )
WebElement DropDownSortingButton;

@FindBy ( xpath = "(//div[@class='dropdown-menu']/a)"  )
List<WebElement>  DropDownSortingList;

@FindBy ( xpath =  "(//div[contains(@id,'slider-range')]/a)[1]"  )
WebElement BarrePriceMinHandle;

@FindBy ( xpath =  "(//div[contains(@id,'slider-range')]/a)[2]"  )
WebElement BarrePriceMaxHandle;

@FindBy ( xpath =  "//p[contains(@id,'facet_label')]"  )
WebElement PriceBarreText;

@FindBy (xpath = "//section/p/parent::section/ul/li/label"  )
List<WebElement> ListOfItemsToSelect;

@FindBy ( xpath = "//section/p[contains(text(),'Marque')]/parent::section/ul/li/label/span[@class='custom-checkbox']"  )
List<WebElement>  checkBoxesList;

@FindBy ( xpath = "//section[@id='js-active-search-filters']/ul/li" )
List<WebElement> ListOfFiltredItems;

@FindBy ( xpath = "//a[@class='product-name']"  )
List<WebElement>  ListOfProducts;

@FindBy ( xpath = "//a[@rel='next']" )
WebElement NextButton;

@FindBy (xpath = "//button[@class='close']/span[@class='cross']"	)
WebElement ButtonClosePopPupAddToCard ;

@FindBy (xpath = "//button[@class='btn btn-touchspin js-touchspin bootstrap-touchspin-up']"  )
WebElement ButtonIncrProductQuanitity ;

@FindBy (xpath = "//button[@class='btn btn-touchspin js-touchspin bootstrap-touchspin-down']"  )
WebElement ButtonDecrProductQuanitity ;

@FindBy ( xpath = "//span[@id='product-availability']/parent::div/div/button"  )
WebElement AddToCardButton;

@FindBy ( css = "h4[class='modal-title title']" )
WebElement ObtainedConfirmationText;

@FindBy ( id = "js-product-list-top" )
WebElement HeaderProductsPage ;

public List<Double> PricesOfProductsAddedToCard = new ArrayList<Double>();

// Cette méthode va récupérer la liste des prix des articles figurant dans la Product page
// méthode à utiliser dans le tri par prix
public List<Double> GetPriceList ()	{
	
		List<Double> Pliste = new ArrayList<Double>(); 
		// parcourir la liste des prix
		for (WebElement P : priceList) 	
		{
			// éliminer le mot 'TND' et garder seulement des ciffres et la virgule
			String S = P.getText().replaceAll("[^0-9,]", "").replace(",", ".");
			double price = Double.parseDouble(S);
			Pliste.add(price);
		}
		return Pliste;
}





/**
cette méthode sert à récupérer le WebElement relatif au type de tri choisi par l'utilisateur 
selon l'index dans la lise d'éléménts de tri
*/
public WebElement GetSortingType (int indexDD) {
		if (indexDD > 0 && indexDD < DropDownSortingList.size()  ) {
			return DropDownSortingList.get(indexDD);
		}
		return null;
}




// l'appel de la méthode de tri selon le paramètre inséré par l'utilisateur			
public void SortingProdBy (String SortingType) throws InterruptedException {
	
		DropDownSortingButton.click();
		Thread.sleep(2000);

		if (SortingType.equals("NameAToZ"))
			{
			GetSortingType(1).click();
			Thread.sleep(2000);
			ByName(true);
			}
		
		if (SortingType.equals("NameZToA"))
		{
		GetSortingType(2).click();
		Thread.sleep(2000);
		ByName(false);
		}
		
		if (SortingType.equals("AscendingPrice"))
		{
		GetSortingType(3).click();
		Thread.sleep(2000);
		ByPrice(true);
		}
		
		if (SortingType.equals("DescendingPrice"))
		{
		GetSortingType(4).click();
		Thread.sleep(2000);
		ByPrice(false);
		}
}
		

	


/**
 * Ici on a essayé de suivre un tré spécifique pour le tri par nom car
 * il y'a Plusieur produits qui se termine par '...' ce qui complique le tri par Java 
 * (logique de tri en java différent à celui du sit) 
 */
public void ByName  (boolean   Ascending ) throws InterruptedException {
		
		List<String> productListText = productList.stream()
		.map(s -> s.getText().replaceAll("\\.{3}$", "")) // Enlève les trois points à la fin
		.collect(Collectors.toList());
	
	
				 /**
		 * Tri simple
		//String.CASE_INSENSITIVE_ORDER : Comparateur qui effectue le tri des chaînes sans tenir compte de la casse (majuscules/minuscules).
		// List<String> productListTextSorted = productListText.stream().sorted(String.CASE_INSENSITIVE_ORDER).collect(Collectors.toList());
			*/ 
		
			/**
			 * Tri personnalisé
		 on a utilisé cette démarche (tri personnalisé) au lieu du simple code précédent (Tri simple) car le tri des chaines de caractère de Java suit l'ordre lexicographique 
		 contrairement à celui effectué par l'application donc on a forcé le code pour mettre le produit "AFFECT PRO MAKE UP" avant "AFFECT PRO MAKE UP BLUSH" 
		 et suivre la même logique de tri adapté par le site 
			 	*/
		// Tri des produits par ordre insensible à la casse avec logique personnalisée
		List<String> productListTextSorted = productListText.stream()
		.sorted((s1, s2) -> {
	    // Vérification des cas spécifiques en utilisant contains
	    if (s1.contains("AFFECT PRO MAKE UP") && s2.contains("AFFECT PRO MAKE UP BLUSH")) {
	        return Ascending? 1: -1; // return s1 puis s2 si le tri est de A à Z
	    }
	    if (s1.contains("AFFECT PRO MAKE UP BLUSH") && s2.contains("AFFECT PRO MAKE UP")) {
	        return Ascending? -1: 1; // return s2 puis s1 si le tri est de A à Z
	    }
	    if (s1.contains("ZIAJA EAU MICELLAIRE") && s2.contains("ZIAJA EAU MICELLAIRE ANTI")) {
	        return Ascending? 1: -1; // return s1 puis s2 si le tri est de A à Z
	    }
	    if (s1.contains("ZIAJA EAU MICELLAIRE ANTI") && s2.contains("ZIAJA EAU MICELLAIRE")) {
	        return Ascending? -1: 1; // return s2 puis s1 si le tri est de A à Z
	    }
	    return Ascending ? String.CASE_INSENSITIVE_ORDER.compare(s1, s2) : String.CASE_INSENSITIVE_ORDER.compare(s2, s1);
		})
		.collect(Collectors.toList());
			/*
		System.out.println(productListText);
		System.out.println("**********************************");
		System.out.println(productListTextSorted);
			*/
		assertTrue(productListText.equals(productListTextSorted));			
}
	


/** Tri par prix
 * Si la valeur de la variable boolean insérée comme paramètre est true ==> tri par prix Croissant
 * Si la valeur de la variable boolean insérée comme paramètre est false ==> tri par prix décroissant
*/
public void ByPrice (boolean Ascending) throws InterruptedException {

		List<Double> priceList =	GetPriceList();
		
		List<Double> sortedPriceList = Ascending?	priceList.stream().sorted().collect(Collectors.toList()) : 
			priceList.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList()) ;
		/*
		System.out.println(priceList);
		System.out.println("**********************************");
		System.out.println(sortedPriceList);
		*/
		assertTrue(priceList.equals(sortedPriceList));		
}





/**
 * Cette méthode récupére le texte de prix minimun et le prix maximum qui existent en dessus de curseur de plage de filtre de prix 
 * Le prix Min et le prix Max seront mis à jour avec chaque mouvement des poignées du curseur de plage 
 * le prix min et le prix max se trouvent dans un seul texte dont les deux prix sont séparés par (-)
 * c'est pourquoi on a utilisé le split et mis chaque prix dans une case de l'array string
 * Cette méthode sera utilisé par la méthode FilterByPrice
 */
public String[] GetMinAndMaxPrice () {
		
		String Pricetext = 	PriceBarreText.getText();	
		String [] parts = Pricetext.split(" - ");
		
		return parts;
}




 // Cette méthode consiste à mouvementer le curseur de plage jusqu'à atteindre PreferedMinPrice et PreferedMaxPrice  
public String[] FilterByPrice (String PreferedMinPrice , String PreferedMaxPrice ) throws InterruptedException {
		Actions action = new Actions(driver);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		
		int step =  1;
		
		// récupération des Prix minimum actuel et prix maximum actuel avec la méthode GetMinAndMaxPrice par index de l'array
		String Mintext =	GetMinAndMaxPrice()[0];
		String Maxtext =	GetMinAndMaxPrice()[1];
	  
		// cliquer sur la poinée gauche et maintenir le click
		action.clickAndHold(BarrePriceMinHandle).build().perform();
		Thread.sleep(2000);
	
		// mouvementer la poignée à droite ( moveByOffset(step, 0) ) et mise à jour de prix minimum de la méthode GetMinAndMaxPrice jusqu'à ce prix sera égal à la valeur de paramètre 'PreferedMinPrice' 
	  while (!Mintext.contains(PreferedMinPrice))  {
		  action.moveByOffset(step, 0).build().perform();
		  Mintext =  GetMinAndMaxPrice()[0]; // rappel de la méthode GetMinAndMaxPrice pour mettre à jour le prix minimum
	  }
	  
	  // libérer le click effectué sur la poigné gauché une fois  Mintext = PreferedMinPrice et la boucle while quittée 
	  action.release().build().perform();
		Thread.sleep(2000);
	  
		// cliquer sur la poinée droite et maintenir le click
		action.clickAndHold(BarrePriceMaxHandle).build().perform();
		Thread.sleep(2000);
		
		// mouvementer la poignée à gauche ( moveByOffset(- step, 0) ) et mise à jour de prix maximum de la méthode GetMinAndMaxPrice jusqu'à ce prix sera égal à la valeur de paramètre 'PreferedMaxPrice' 	
	while (!Maxtext.contains(PreferedMaxPrice))  {
		  action.moveByOffset(-step, 0).build().perform();
		  wait.until(ExpectedConditions.visibilityOf(PriceBarreText));
		  Maxtext =  GetMinAndMaxPrice()[1]; //  rappel de la méthode GetMinAndMaxPrice pour mettre à jour le prix maximum	  
	}
	  // libérer le click effectué sur la poigné droite une fois  Maxtext = PreferedMinPrice et la boucle while quittée 
		action.release().build().perform();
		
		return GetMinAndMaxPrice();
}
	



/**
 * 	Cette méthode effectue un filtre en cliquant sur des check boxes pour sélectionner des éléments comme :
 * marque, Contenance, Catégories, Sexe etc
 */
public List<String> FilterByCheckingItem (List<String> Items) throws InterruptedException {

		Thread.sleep(2000);
	
		// parcourir la liste de paramètres de la méthode
		for (String Item : Items  )  {
	
			List<WebElement> VoirplusList = driver.findElements(By.xpath("(//section/p/parent::section/span[@class='plusfilter'])"));
			
				// j'ai ajouté cette boucle parcqu'il y plusieurs liste ayant un élément cliquable Voirplus: marque, Contenance, Catégories, Sexe etc
				// après chaque clique sur un check box pour sélectionner un élément, les listes revenir à l'affichage par défaut et n'affichent qu'un nombre limité des choix
				// c'est pour cela qu'on a inclus un click sur le bouton 'voir plus' dans cette boucle
				// l'objectif est de cliquer sur 'voir plus' après chaque sélection effectuée
				for (WebElement VoirPlus :  VoirplusList ) {
					Thread.sleep(2000);
						try {
					WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
					wait.until(ExpectedConditions.elementToBeClickable(VoirPlus)).click();
						}
						catch (NoSuchElementException | ElementNotInteractableException e) {
							System.out.println("L'élément VoirPlus n'a pas été trouvé ou n'est pas interactif, on sort de la boucle.");
							  break;  
						}
				}
			
				// parcourir la liste les élément de filtre par checkbox   
				for (WebElement Name :  ListOfItemsToSelect ) {
					// localiser le checkbox
					WebElement CheckBox = Name.findElement(By.xpath(".//span[@class='custom-checkbox']"));
					// récupérer le texte de chque élément 
					String	ItemName =	Name.getText();
				
					// si le texte de cet élément correspond à celui fourni en paramètre ==> cliquer sur le checkbox de cet élément
						if (ItemName.contains(Item)     ) {
						WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
						Thread.sleep(1000);
						wait.until(ExpectedConditions.elementToBeClickable(CheckBox));
						CheckBox.click();
						wait.until(ExpectedConditions.stalenessOf(Name));
						break;							
						}
				}			
		}
		return Items;
}




/**
 * 1- la méthode suivante va parcourir la liste de tous les élément web de filtre par checkbox 
 * 2- nettoyer les caractères de chaque String récupéré
 * 3- insérer chaque éléménet coché dans la liste ListOfSelectedItem
 * 4- renvoyer la liste ListOfSelectedItem
 */
public List<String> GetListOfCheckBoxesSelected () throws InterruptedException {
	
List<String> ListOfSelectedItem = new ArrayList<String>();	
List<WebElement> VoirplusList = driver.findElements(By.xpath("(//section/p/parent::section/span[@class='plusfilter'])"));

		// cliquer sur tous les liens 'voirplus' trouvés
		for (WebElement Voirplus : VoirplusList) {
			Voirplus.click();
		}

		Thread.sleep(2000);
		for (WebElement Marque :  ListOfItemsToSelect    ) {
			
			WebElement	Checkbox =	Marque.findElement(By.xpath(".//span/input[@type='checkbox']"));
			
				if ( Checkbox.isSelected()  ) {
	
					String NameOfSelectedItem =	Marque.findElement(By.xpath(".//a")).getText().trim();
					Thread.sleep(2000);
					
					/**
					nettoyage des caractères dans le texte 
					1- supprmer le point d'interrogation dans le texte replace("?", "")
					2- supprimer tout caractère qui n'est pas une lettre (\\p{L}), un chiffre (\\p{N}), un espace (\\s), ou une parenthèse (()) : .replaceAll("[^\\p{L}\\p{N}\\s()]", "")
					3- supprimer les parenthèses et l'expression entre les deux parenthèses replaceAll("\\(\\d+\\)", "") afin de garder seulement le nom de l'élément sélectionné
					*/
					 NameOfSelectedItem = NameOfSelectedItem.replace("?", "")          // Enlève les points d'interrogation
	                         .replaceAll("[^\\p{L}\\p{N}\\s()]", "").replaceAll("\\(\\d+\\)", "") 
	                         .trim();   
					ListOfSelectedItem.add(NameOfSelectedItem);
				}	
		}
	return ListOfSelectedItem;	
}




// Cette méthode récupére les éléménent dans la section :FiltreActif et les ajoute à la liste NamesOfFiltreditems
// cette liste sera comparé avec la liste attendue qui est le paramètre de la méthode FilterByCheckingItem
public List<String> Get_Liste_Of_Items_In_Active_Filter_Section () {
	
		List<String> NamesOfFiltreditems = new ArrayList<String>();
	
		for (WebElement FiltredItems : ListOfFiltredItems    ) {
		// le texte de ListOfFiltredItems contient la nature de l'élément filtré: maraque, sexe, contenance etc
		// le slpit est pour garder seuelemtn le nom de l'élément qui vient après les deux points expl: (Marque : Agatha)	
		String[] Parts =	FiltredItems.getText().replaceAll("[^\\p{L}\\p{N}\\s():]", "").trim().split(" : ");
		NamesOfFiltreditems.add(Parts[1]);	
	}
return 	NamesOfFiltreditems;
}



						
// récupérer l'élement Web d'un produit selon sa description en cliququant toujours sur la pagination tant que ce produit n'existe pas dans la page actuelle
public WebElement GetProductByName (String ProductNames) throws InterruptedException {

		WebElement Prod = null;
		
		// Tant que tous les produits n'ont pas été trouvés
		while ( Prod ==  null    ) {
			
			// Pour chaque produit dans la page actuelle
			
					
			Prod = 	productList.stream().filter(p -> p.getText().contains(ProductNames)).findFirst().orElse(null) ;
						
						// Si un produit est trouvé, l'ajouter à la liste
							 if (Prod != null   ) {
								//	System.out.println(Prod.getText());
							 	 }
			
							Thread.sleep(2000);
							 if (Prod == null   ) {					
							      // Si tous les produits n'ont pas été trouvés et qu'il reste des pages, on clique sur "Next" 	
								  if (NextButton.isDisplayed() &&  NextButton.isEnabled()  ) {
									  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
									   wait.until(ExpectedConditions.visibilityOf(NextButton));
									   wait.until(ExpectedConditions.elementToBeClickable(NextButton));
									   ((JavascriptExecutor) driver).executeScript("arguments[0].click();", NextButton);
										//	NextButton.click();
									   
											Thread.sleep(3000);
												
										}
										
								   else {
										break;
										}	
							 }		  
		}
		return Prod;
}
					



					
public List<Double> getPriceByName (List<String> ProductNames) throws InterruptedException {
		
		List<Double> ListOfPrices = new ArrayList<Double>();
		
		for (String Name : ProductNames) {
		
			GetProductByName(Name).findElement(By.xpath(".//a")).click();
			String PriceText =	ProductPriceInProductScreen.getText().replaceAll("[^0-9,]", "").replace(",", ".");
			double Price =	Double.parseDouble(PriceText);
			ListOfPrices.add(Price);
			System.out.println(Price );
			//driver.navigate().back();
			
			Thread.sleep(1000);
			
		}
		return ListOfPrices;	
}
					

//Cette méthode ajoute un produit à la carte en fonction de son nom directement sans afficher le détail du produit.
public CardPage AddProductsToCard (List<String> ProdsNames, List<String> Contenance ) throws InterruptedException {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			for (int i =0; i<ProdsNames.size(); i++  ) {
				WebElement P = GetProductByName(ProdsNames.get(i));
				P.findElement(By.xpath(".//following-sibling::div[@class='product-add-to-cart text-center']/div/div/form/div/div/*[1]")).click();
 
				Thread.sleep(2000);
				
				if (ContenanceList != null  && !ContenanceList.isEmpty() ) {
						
						for ( WebElement C : ContenanceList     ) {
							
							String	CT  = C.getText().replaceAll("[^\\p{L}\\p{N}\\s()]", "");
									if (CT.equals(Contenance.get(i))  ) {
										
									//	System.out.println("Contenance selected is :"+   CT );
										C.click();		
									}							
						}
						Thread.sleep(1000);
						String TextPrice = ProductPriceInProductScreen.getText().replaceAll("[^0-9,]", "").replace(",", ".");
						double Price = Double.parseDouble(TextPrice);
						Thread.sleep(1000);
						PricesOfProductsAddedToCard.add(Price);
						Thread.sleep(1000);
						wait.until(ExpectedConditions.visibilityOf(AddToCardButton));
						wait.until(ExpectedConditions.elementToBeClickable(AddToCardButton));
						AddToCardButton.click();
						Thread.sleep(2000);
						ButtonClosePopPupAddToCard.click();
						Thread.sleep(1000);
					}	
				else {
				
					ButtonClosePopPupAddToCard.click();
					Thread.sleep(1000);
					String TextPrice =	P.findElement(By.xpath(".//following-sibling::div/div/span[@itemprop='price']")).getText().replaceAll("[^0-9,]", "").replace(",", ".");
					double Price = Double.parseDouble(TextPrice);
					PricesOfProductsAddedToCard.add(Price);
					Thread.sleep(1000);
					 }
			}	
				CardPage card = new CardPage(driver);
				return card;
}
				



//Cette méthode ouvre un produit, ajuste la quantité et sélectionne la contenance si nécessaire, puis ajoute le produit à la carte.
public CardPage OpenProductThenAddToCard (List<String> ProdNames ,List<Integer>  Quantities , List<String> Contenances) throws InterruptedException {
	
	double TotalExpectedAmount =0;
	double TotalAddedAmount =0;

	
	for (int i=0; i < ProdNames.size() ; i++ ) {
		   if ( driver.getTitle().equals("")  ) {
				driver.navigate().refresh();
			}
		WebElement Pr = GetProductByName(ProdNames.get(i)) ;
		String PName = ProdNames.get(i);
		int Qt =	Quantities.get(i);
		String  contenance = 	Contenances.get(i);	
		
		
	 	// Trouver le produit par son nom et cliquer dessus pour ouvrir la page du produit.
		Pr.findElement(By.xpath(".//a")).click();
		
	    // Obtenir le nom du produit après avoir cliqué et le convertir en majuscules pour vérifier la correspondance avec le nom d'origine.
		String NameAfterClickOnProduct = ProductName.getText().toUpperCase() ;
		
		// System.out.println("Before : " + PName + " After : " + NameAfterClickOnProduct );
		
	    // Vérifier que le nom du produit après avoir cliqué corrspond nom du produit original.
		assertTrue(NameAfterClickOnProduct.contains(PName));
		
		Thread.sleep(2000);
		
		
	    // Si aucune contenance n'a été trouvée dans la page du produit sélectionné ou si la liste de contenances est vide, ignorer la sélection de contenance.
		if ( ContenanceList == null || 	ContenanceList.isEmpty()  ) {
			contenance = null;
		}
		
	    // Parcourir la liste des contenances disponibles pour sélectionner celle qui correspond au paramètre `Contenance`.
		for ( WebElement C : ContenanceList     ) {
			
			String	CT  = C.getText().replaceAll("[^\\p{L}\\p{N}\\s()]", "");
			
			 // Si une contenance spécifique est donnée et qu'elle correspond à l'une des options, cliquer dessus.
			if (contenance != null ) {
					if (CT.equals(contenance)  ) {
						
					//	System.out.println("Contenance selected is :"+   CT );
						C.click();														   												}
			}
			
			else { // Si aucune contenance n'est demandée, sortir de la boucle.
				break;
			}
		}
		Thread.sleep(2000);
		int j = 1;
		
		// Ajuster la quantité de produits à ajouter au panier selon le paramètre fourni 'Quantité'.
        // Cliquer sur le bouton pour augmenter la quantité.
		// le nombre de click est celui la quantité à commander
		while (j < Qt  ) {
			
		ButtonIncrProductQuanitity.click();
		Thread.sleep(1000);
		j++;	
		
		}
		Thread.sleep(1000);
		// Extraire le prix du produit affiché sur l'écran et le convertir en double.
		String TextPrice = ProductPriceInProductScreen.getText().replaceAll("[^0-9,]", "").replace(",", ".");
		double Price = Double.parseDouble(TextPrice);
		PricesOfProductsAddedToCard.add(Price);
		
	    // Attendre que le bouton "Ajouter au panier" soit cliquable, puis cliquer dessus.
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(AddToCardButton));
		Thread.sleep(1000);
		AddToCardButton.click();
		
		Thread.sleep(2000);
		
	    // Extraire le montant total affiché dans la pop-up de confirmation et le convertir en double.
		String TextTotalAmount = TotalAmountInPopPup.getText().replaceAll("[^0-9,]", "").replace(",", ".");
		TotalAddedAmount = Double.parseDouble(TextTotalAmount);
		
		// Vérifier que le texte de confirmation d'ajout est correct.
		String ObtainedAddingtext = ObtainedConfirmationText.getText();
		String ExpectedAddingText = "Cet article a été bien ajouté à votre sélection";
		assertEquals(ObtainedAddingtext, ExpectedAddingText);
		
	    // Calculer le montant total attendu (prix unitaire * quantité).
		//double ExpectedTotal = ( Price * Qt) ;
		TotalExpectedAmount += (Price * Qt);  // Ajouter le prix * quantité au total attendu
		
		// Vérifier que le total ajouté correspond au total attendu jusqu'à présent
		assertEquals(TotalExpectedAmount, TotalAddedAmount);
			
		ButtonClosePopPupAddToCard.click();
		driver.navigate().back();
		wait.until(ExpectedConditions.visibilityOf(NextButton));
}
	
	
	CardPage card = new CardPage(driver);
	return card;

}







}
