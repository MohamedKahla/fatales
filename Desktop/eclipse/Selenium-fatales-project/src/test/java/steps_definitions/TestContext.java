package steps_definitions;

import pageObject.HomePage;
import pageObject.LoginPage;

public class TestContext {

		/*
- La classe TestContext est utilisée pour centraliser les instances des pages et des composants qui doivent être partagées entre 
  plusieurs classes de step definitions.	
  
- la modification apportée sur les variables public static 'login' et 'home' effectué dans les autres classes va donner des nouvelles valeurs à ces variables 
  dans cette classe (TestContext).
  
- et puis quand on appele ces variables une nouvelle fois dans d'autres classes seront appelés avec les nouvelles valeurs  
		*/
	
	
	 public static LoginPage login;
	 public static  HomePage home ;	
	
	
	
}
