package krawler.erp.CompanySignUP;

import java.awt.AWTException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import krawler.erp.page.Utilities;

public class CompanySignUp {
	public WebDriver Intialdriver(String url) throws InterruptedException, AWTException, IOException {

		// Initial Setup
		String driverPath = System.getProperty("user.dir") + "\\Drivers\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", driverPath);
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		// Check for company creation on which environment we want to creation
		String UrlEnvironment = null;
		if (url.equalsIgnoreCase("http://apps.deskera.com/peruserplan/3")) {
			UrlEnvironment = url;
		}
		if (url.equalsIgnoreCase("http://192.168.0.21:8080/ondemand/peruserplan/100")) {
			UrlEnvironment = url;
		}

		if (url.equalsIgnoreCase("http://192.168.0.208:8080/ondemand/peruserplan/100")) {
			UrlEnvironment = url;
		}

		// Hitting url
		driver.get(UrlEnvironment);

		return driver;
	}

	public static void CreateCompany(WebDriver driver, String url, String subdomain)
			throws InterruptedException, AWTException, IOException {
		try {

			// Fill the details
			WebDriverWait wait = new WebDriverWait(driver, 30);
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='fname']")));
			driver.findElement(By.xpath("//input[@id='fname']")).sendKeys("Rahul");

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='lname']")));
			driver.findElement(By.xpath("//input[@id='lname']")).sendKeys("Kaushik");

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='email']")));
			driver.findElement(By.xpath("//input[@id='email']")).sendKeys("rahul.kaushik@krawlernetworks.com");

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='newuserid']")));
			driver.findElement(By.xpath("//input[@id='newuserid']")).sendKeys("admin");

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='newpassword']")));
			driver.findElement(By.xpath("//input[@id='newpassword']")).sendKeys("1234");

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='newpassword2']")));
			driver.findElement(By.xpath("//input[@id='newpassword2']")).sendKeys("1234");

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='company-name']")));
			driver.findElement(By.xpath("//input[@id='company-name']")).sendKeys(subdomain);

			driver.findElement(By.xpath("//input[@id='couponcode']")).click();
			Thread.sleep(1000);

			// Check whether subdomain is already exist or not
			String expected = "Deskera Address Availability";
			String actual = driver.findElement(By.xpath("//span[text()='Deskera Address Availability']")).getText();
			if (actual.equalsIgnoreCase(expected)) {

				System.out.println("Given Subdomain is already exist. Please provide unique subdomain");
				Thread.sleep(3000);

			}

			else {
				new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='checkbox']")));
				WebElement checkbox = driver.findElement(By.xpath("//input[@id='checkbox']"));
				if (checkbox.isEnabled()) {
					driver.findElement(By.xpath("//input[@id='checkbox']")).click();
					Thread.sleep(2000);
				}

				Utilities.justHover("//input[@id='button']", driver);
				Thread.sleep(1000);
				new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='button']")));
				driver.findElement(By.xpath("//input[@id='button']")).click();
				Thread.sleep(25000);

				System.out.println("Company is created successfully: " + subdomain);

			}
		}

		catch (Exception EX) {

			Utilities.handleError(EX, driver);
		}
	}

	public void Starting_Getting_Wizard(String username, String pwd, WebDriver driver, String subdomain,
			String SelectCountry, String SelectCurrency, String url)
			throws InterruptedException, AWTException, IOException {

		try {
			// Matching url and then hitting matched one
			if (url.equalsIgnoreCase("http://apps.deskera.com/peruserplan/3")) {
				driver.get("http://accounting.deskera.com/a/" + subdomain + "/");
				Thread.sleep(2000);

			}
			if (url.equalsIgnoreCase("http://192.168.0.21:8080/ondemand/peruserplan/100")) {

				driver.get("http://192.168.0.21:8080/stagingaccounting/a/" + subdomain + "/");
				Thread.sleep(2000);
			}

			if (url.equalsIgnoreCase("http://192.168.0.208:8080/ondemand/peruserplan/100")) {

				driver.get("http://192.168.0.208:8080/stagingaccounting/a/" + subdomain + "/");
				Thread.sleep(2000);
			}

			// Login Subdomain
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='UserName']")));
			WebElement Usr = driver.findElement(By.xpath("//input[@id='UserName']"));
			Usr.clear();
			Usr.sendKeys(username);
			Thread.sleep(1000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='Password']")));
			WebElement Pass = driver.findElement(By.xpath("//input[@id='Password']"));
			Pass.clear();
			Pass.sendKeys(pwd);
			Thread.sleep(1000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='LoginButton']")));
			driver.findElement(By.xpath("//input[@id='LoginButton']")).click();
			Thread.sleep(4000);

			// Checking getting wizard is run or not
			String expected = "Getting Started Wizard";
			try {
				String actual = driver.findElement(By.xpath("//span[text()='Getting Started Wizard']")).getText();
				if (expected.equalsIgnoreCase(actual)) {

					// Run getting wizard by clicking on required button
					new WebDriverWait(driver, 30)
							.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Continue']")));
					driver.findElement(By.xpath("//button[text()='Continue']")).click();
					Thread.sleep(2000);

					new WebDriverWait(driver, 30)
							.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='countryreccombo']")));
					WebElement country = driver.findElement(By.xpath("//input[@id='countryreccombo']"));
					country.click();
					Thread.sleep(1000);

					// Provide Country as per expectation
					String CountryType = null;
					if (SelectCountry.equalsIgnoreCase("SINGAPORE")) {
						CountryType = "SINGAPORE";
					}
					if (SelectCountry.equalsIgnoreCase("UNITED STATES")) {
						CountryType = "UNITED STATES";
					}

					if (SelectCountry.equalsIgnoreCase("MALAYSIA")) {
						CountryType = "MALAYSIA";
					}

					if (SelectCountry.equalsIgnoreCase("INDIA")) {
						CountryType = "INDIA";
					}

					country.sendKeys(CountryType);
					Thread.sleep(1000);
					driver.findElement(
							By.xpath("//div[@class='x-layer x-combo-list ' and contains(@style,'visible')]/div/div"))
							.click();
					Thread.sleep(2000);

					new WebDriverWait(driver, 30)
							.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='currencyrecid']")));
					WebElement currency = driver.findElement(By.xpath("//input[@id='currencyrecid']"));
					currency.click();
					Thread.sleep(1000);

					// Provide Currency as per expectation
					String CurencyType = null;
					if (SelectCurrency.equalsIgnoreCase("SGD")) {
						CurencyType = "SG Dollar (SGD)";
					}
					if (SelectCurrency.equalsIgnoreCase("USD")) {
						CurencyType = "US Dollars (USD)";
					}

					if (SelectCurrency.equalsIgnoreCase("MYR")) {
						CurencyType = "Malaysian Ringgit (MYR)";
					}

					if (SelectCurrency.equalsIgnoreCase("INR")) {
						CurencyType = "Rupees (INR)";
					}

					currency.sendKeys(CurencyType);
					Thread.sleep(1000);
					driver.findElement(
							By.xpath("//div[@class='x-layer x-combo-list ' and contains(@style,'visible')]/div/div"))
							.click();
					Thread.sleep(2000);

					driver.findElement(By.xpath("//button[text()='Next >>']")).click();
					Thread.sleep(1000);

					driver.findElement(By.xpath("//button[text()='Next >>']")).click();
					Thread.sleep(1000);

					driver.findElement(By.xpath("//button[text()='Next >>']")).click();
					Thread.sleep(1000);

					driver.findElement(By.xpath("//button[text()='Next >>']")).click();
					Thread.sleep(2000);

					new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(
							By.xpath("//a[text()='Download Exchange rates to enable multiple currency accounting']")));
					driver.findElement(
							By.xpath("//a[text()='Download Exchange rates to enable multiple currency accounting']"))
							.click();
					Thread.sleep(2000);

					driver.findElement(By.xpath("//button[text()='Next >>']")).click();
					Thread.sleep(1000);

					driver.findElement(By.xpath("//button[text()='Next >>']")).click();
					Thread.sleep(1000);

					driver.findElement(By.xpath("//button[text()='Next >>']")).click();
					Thread.sleep(1000);

					driver.findElement(By.xpath("//button[text()='Next >>']")).click();
					Thread.sleep(2000);

					driver.findElement(By.xpath("//button[text()='Save']")).click();
					Thread.sleep(3000);

					Utilities.clickButton("Yes", 2000, driver);
					Thread.sleep(4000);

					Utilities.clickButton("Finish", 1000, driver);

					System.out.println(
							"Company Started Wizard is run successfully. Now we can perform transaction on this subdomain");
				}
			}

			catch (Exception EX) {

				System.out.println(
						"Company Started Wizard is already done for this subdomain. So please provide those subdomain which is not yet to be done getting wizard.");

			}
		} catch (Exception EX) {

			Utilities.handleError(EX, driver);
		}

	}
}
