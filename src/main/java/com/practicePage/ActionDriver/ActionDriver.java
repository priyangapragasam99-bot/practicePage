package com.practicePage.ActionDriver;

import java.awt.Desktop.Action;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ActionDriver {

	private WebDriver driver;
	private WebDriverWait wait;
	private Select select;
	private Actions action;
	private TakesScreenshot screenShot;

	public ActionDriver(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));

	}

	// Element click
	public void click(By by) {
		try {
			waitForElementToBeClickable(by);
			driver.findElement(by).click();
		} catch (Exception e) {
			System.out.println("Unable to Click: " + e.getMessage());
		}
	}

	// Enter Text
	public void enterText(By by, String value) {
		try {
			driver.findElement(by).sendKeys(value);
		} catch (Exception e) {
			System.out.println("Unable to enter text: " + e.getMessage());
		}
	}

	// getText
	public String getText(By by) {
		try {
			waitUntilVisibilityOfElement(by);
			String value = driver.findElement(by).getText();
			return value;
		} catch (Exception e) {
			System.out.println("Unable to get text: " + e.getMessage());
			return "";
		}

	}

	// waitUntilVisibilityOfElement
	private void waitUntilVisibilityOfElement(By by) {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		} catch (Exception e) {
			System.out.println("Element is not visiblet: " + e.getMessage());
		}

	}

	// compare two text
	public Boolean compareTwoText(String value1, String Value2) {
		try {
			Boolean value = value1.equals(Value2) ? true : false;
			return value;
		} catch (Exception e) {
			System.out.println("Unable to compare text: " + e.getMessage());
			return false;
		}
	}

	// element is displayed
	public boolean isDisplayed(By by) {
		try {
			return driver.findElement(by).isDisplayed() ? true : false;
		} catch (Exception e) {
			System.out.println("Element is not displayed: " + e.getMessage());
			return false;
		}
	}

	// Scroll to the element
	public void scrollToTheElement(By by) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement element = driver.findElement(by);
			js.executeScript("argument[0],scrollIntoView(true);", element);
		} catch (Exception e) {
			System.out.println("Unable to move to the element: " + e.getMessage());
		}
	}

	// wait for the page to load
	public void waitForTheElementToLoad(int timeOutInSec) {
		try {
			wait.withTimeout(Duration.ofSeconds(timeOutInSec)).until(WebDriver -> ((JavascriptExecutor) WebDriver)
					.executeScript("Return document.readyState").equals("Complete"));
			System.out.println("Page Loaded Successfully");
		} catch (Exception e) {
			System.out.println("Unable to load the element: " + e.getMessage());
		}
	}

	// wait for element to be clickable
	public void waitForElementToBeClickable(By by) {

		try {
			wait.until(ExpectedConditions.elementToBeClickable(by));
		} catch (Exception e) {
			System.out.println("Element is not clickable: " + e.getMessage());
		}

	}

	// Select from dropdown - selectByVisibleText
	public void selectByVisibleText(By by, String text) {

		select = new Select(driver.findElement(by));
		try {
			select.selectByVisibleText(text);
		} catch (Exception e) {
			System.out.println("text is not visible: " + e.getMessage());
		}
	}

	// Select from dropdown - deSelectAll
	public void deSelectAll(By by) {
		select = new Select(driver.findElement(by));
		try {
			select.deselectAll();
		} catch (Exception e) {
			System.out.println("deselect is not aplicable: " + e.getMessage());
		}
	}

	// Select from dropdown - selectByValue
	public void selectByValue(By by, String text) {
		select = new Select(driver.findElement(by));
		try {
			select.selectByValue(text);
		} catch (Exception e) {
			System.out.println("Value is not present: " + e.getMessage());
		}
	}

	// Select from dropdown - selectByIndex
	public void selectByIndex(By by, int index) {
		select = new Select(driver.findElement(by));
		try {
			select.selectByIndex(index);
		} catch (Exception e) {
			System.out.println("Index is not present: " + e.getMessage());
		}
	}

	// navigate forward
	public void navigateForward() {
		try {
			driver.navigate().forward();
		} catch (Exception e) {
			System.out.println("Unable to navigate forward: " + e.getMessage());
		}
	}

	// navigate backward
	public void navigateBackward() {
		try {
			driver.navigate().back();
		} catch (Exception e) {
			System.out.println("Unable to navigate back: " + e.getMessage());
		}
	}

	// navigate to url
	public void navigateTo(String url) {
		try {
			driver.navigate().to(url);
		} catch (Exception e) {
			System.out.println("Unable to navigate to url: " + e.getMessage());
		}
	}

	// refreshpage
	public void refreshPage() {
		try {
			driver.navigate().refresh();
		} catch (Exception e) {
			System.out.println("Unable to refresh page: " + e.getMessage());
		}
	}

	// action - drag and drop
	public void dragAndDrop(WebElement source, WebElement destination) {
		action = new Actions(driver);
		try {
			action.dragAndDrop(source, destination);
		} catch (Exception e) {
			System.out.println("Unable to drag and drop: " + e.getMessage());
		}
	}

	// action - move to the element
	public void moveToTheElement(WebElement element) {
		action = new Actions(driver);
		try {
			action.moveToElement(element);
		} catch (Exception e) {
			System.out.println("Unable to move to the element: " + e.getMessage());
		}
	}

	//Screenshot
	public void tackScreenshot()
	{
		screenShot = ((TakesScreenshot)driver);
		File src=screenShot.getScreenshotAs(OutputType.FILE);
		File dest=new File(System.getProperty("user.dir")+"practicePage\\resources\\screenshot.png");
		try {
			FileHandler.copy(src, dest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Unable to copy screenshot: " + e.getMessage());

		}
		
	}
	
	// close Current page
	public void closeCurrentPage() {
		try {
			driver.close();
		} catch (Exception e) {
			System.out.println("Unable to close the page: " + e.getMessage());
		}
	}

}
