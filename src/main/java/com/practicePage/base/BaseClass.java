package com.practicePage.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class BaseClass {

	private static WebDriver driver;
	private static Properties prop;
	private static ChromeOptions chromeOptions;
	private static DesiredCapabilities desiredCapabilities;

	@BeforeSuite
	public void loadConfig() throws IOException {
		prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/resources/config.properties");
		prop.load(fis);
	}

	@BeforeMethod
	public void setUp() throws IOException {

		System.out.println("SettingUp WebDriver:" + this.getClass().getSimpleName());
		launchBrowser();
		configureBrowser();
		chromeOptions();
		desiredCapabilities();
		staticWait(2);

	}

	// initialize the webdriver based on browser defined in the confg.properities
	private void launchBrowser() throws IOException {
		String browser = prop.getProperty("browser");

		if (browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();

		} else if (browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}

		else {
			throw new IllegalArgumentException("Browser not supported: " + browser);
		}
	}

	// implicit wait, maximize browser, navigate to URL
	private void configureBrowser() throws IOException {
		// implicit wait
		int implicitWait = Integer.parseInt(prop.getProperty("implicitWait"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));

		// maximize the browser
		driver.manage().window().maximize();

		// navigate to URL
		try {
			driver.get(prop.getProperty("url"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Failed to get the URL:" + e.getMessage());
		}
	}

	private void chromeOptions() {
		chromeOptions = new ChromeOptions();
		try {
			chromeOptions.addArguments("--headless", "--start-maximized", "--incognito", "--disable-notifications");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Failed to get argument:" + e.getMessage());
		}
	}

	private void desiredCapabilities() {
		desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities.setCapability("browserName", "chrome");
		desiredCapabilities.setCapability("platformName", "Win10");
	}

	// Static wait
	public void staticWait(int seconds) {
		LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(seconds));
	}

	// getter method for prop
	public static Properties getProp() {
		return prop;
	}

	// driver getter method
	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver() {
		this.driver = driver;
	}

	@AfterMethod
	public void tearDown() {
		if (driver != null) {
			try {
				driver.quit();
			} catch (Exception e) {
				System.out.println("Unable to quit the browser:" + e.getMessage());
			}
		}
	}

}
