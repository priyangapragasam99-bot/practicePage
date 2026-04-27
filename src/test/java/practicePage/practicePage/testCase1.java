package practicePage.practicePage;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.internal.annotations.IListeners;

import com.practicePage.base.BaseClass;
import com.practicePage.pages.loginPage;
import com.practicePage.pages.HomePage;
import com.practicePage.pages.menu;
import com.practicePage.pages.cart;

public class testCase1 extends BaseClass {
	
	private loginPage loginPages;
	private HomePage homePages;
	private menu menus;
	private cart carts;
	
	@BeforeMethod
	public void setUpPage()
	{
		loginPages = new loginPage(getDriver());
		homePages = new HomePage(getDriver());
		menus = new menu(getDriver());
		carts = new cart(getDriver());
	}
	
	@Test
	public void verifyValidLogin()
	{
		
		loginPages.login("standard_user","secret_sauce");
		Assert.assertEquals(homePages.logoValidation(),"Swag Labs");
		homePages.addToCart();
		homePages.openCart();
	}
	
	

}
