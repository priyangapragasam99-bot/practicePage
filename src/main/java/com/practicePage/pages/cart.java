package com.practicePage.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.practicePage.ActionDriver.ActionDriver;

public class cart {
	
	private ActionDriver actionDriver;
	private By checkOutButton = By.cssSelector("#checkout");
	private By continueShoppingButton= By.cssSelector("#continue-shopping");
	private By removeButton = By.cssSelector("#remove-sauce-labs-backpack");
	
	public cart(WebDriver driver)
	{
		this.actionDriver = new ActionDriver(driver);
	}
	
	public void checkOutButton()
	{
		actionDriver.click(checkOutButton);
	}
	
	public void continueShoppingButton()
	{
		actionDriver.click(continueShoppingButton);
	}
	
	public void removeButton()
	{
		actionDriver.click(removeButton);
	}

}
