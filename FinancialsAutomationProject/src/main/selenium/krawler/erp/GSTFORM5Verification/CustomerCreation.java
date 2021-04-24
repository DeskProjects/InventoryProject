package krawler.erp.GSTFORM5Verification;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import krawler.erp.page.Utilities;

public class CustomerCreation {

	public static void create_Customer(String customerId, WebDriver driver)
			throws InterruptedException, IOException, AWTException {
		try {

			Properties pro = Utilities.fetchProValue("OR_CustomerMaster.properties");

			String customerName = customerId + "Name";
			// Wait until component appears
			WebDriverWait wait = new WebDriverWait(driver, 100);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("CreateCustomerIcon"))));

			// Open customer master
			driver.findElement(By.xpath(pro.getProperty("CreateCustomerIcon"))).click();
			Thread.sleep(2000);// pro

			// Click on select NA of customer ID
			WebElement NA = driver.findElement(By.xpath(pro.getProperty("sequenceformat")));
			NA.clear();
			NA.sendKeys("NA");
			Thread.sleep(1000);// pro
			NA.sendKeys(Keys.ENTER);

			Thread.sleep(2000);// pro
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath(pro.getProperty("customerId"))));
			driver.findElement(By.xpath(pro.getProperty("customerId"))).sendKeys(customerId);

			Thread.sleep(2000);// pro
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath(pro.getProperty("customerName"))));
			driver.findElement(By.xpath(pro.getProperty("customerName"))).sendKeys(customerName);

			// click on Term
			WebElement term = driver.findElement(By.xpath(pro.getProperty("term")));
			term.sendKeys("NET 45");
			Thread.sleep(1000);// pro
			term.sendKeys(Keys.ENTER);

			// credit term
			driver.findElement(By.xpath(pro.getProperty("CreditTerm"))).sendKeys("999999999");// adding
																								// new
																								// vendor
																								// ID

			// to disable Sales Person
			WebElement salespersonagentCheckbox = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(
					driver.findElement(By.xpath("//input[@name='customervenodoravailabletosalespersonagent']"))));
			if (salespersonagentCheckbox.isSelected()) {
				salespersonagentCheckbox.click();
			}

			// first save
			new WebDriverWait(driver, 30).until(ExpectedConditions
					.presenceOfElementLocated(By.xpath(pro.getProperty("customerPersonalDetailsaveButton"))));
			driver.findElement(By.xpath(pro.getProperty("customerPersonalDetailsaveButton"))).click();
			// Click on yes
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath(pro.getProperty("yesButton"))));

			driver.findElement(By.xpath(pro.getProperty("yesButton"))).click();
			Thread.sleep(2000);// pro
			// Click on OK
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath(pro.getProperty("okButton"))));

			driver.findElement(By.xpath(pro.getProperty("okButton"))).click();
			Thread.sleep(2000);// pro

			// close
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(pro.getProperty("dashBoardtab"))));
			driver.findElement(By.xpath(pro.getProperty("dashBoardtab"))).click();
			Thread.sleep(2000);// pro

			// close customer panel
			Thread.sleep(2000);// pro
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("closeCustomerTab"))));
			driver.findElement(By.xpath(pro.getProperty("closeCustomerTab"))).click();
			Thread.sleep(1000);// pro
			System.out.println("Customer created successfully :" + customerName);
		}

		catch (Exception EX) {
			System.out.println("Customer is not created");
			Thread.sleep(1000);// pro
			Utilities.handleError(EX, driver);
		}

	}
}
