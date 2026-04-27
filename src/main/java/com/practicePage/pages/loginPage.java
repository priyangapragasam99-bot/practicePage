package com.practicePage.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.practicePage.ActionDriver.ActionDriver;

public class loginPage {

	private ActionDriver actionDriver;

	private By userName = By.cssSelector("#user-name");
	private By password = By.cssSelector("#password");
	private By loginButton = By.cssSelector("#login-button");

	public loginPage(WebDriver driver) {
		this.actionDriver = new ActionDriver(driver);
	}

	public void login(String name, String pass) {
		actionDriver.enterText(userName, name);
		actionDriver.enterText(password, pass);
		actionDriver.click(loginButton);
	}

}
