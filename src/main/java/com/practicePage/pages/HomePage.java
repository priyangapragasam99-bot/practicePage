package com.practicePage.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.practicePage.ActionDriver.ActionDriver;

public class HomePage {
	
	private ActionDriver actionDriver;
	private By logoName =By.cssSelector(".app_logo");
	private By addToCart = By.cssSelector("#add-to-cart-sauce-labs-backpack");
	private By openCart=By.cssSelector(".shopping_cart_link");

	
	public HomePage(WebDriver driver)
	{
		this.actionDriver = new ActionDriver(driver);
	}
	
	public String logoValidation()
	{
		String logoText = actionDriver.getText(logoName);
		return logoText;
		
	}
	
	public void addToCart()
	{
		actionDriver.click(addToCart);
	}
	
	public void openCart()
	{
		actionDriver.click(openCart);
	}

	

}
