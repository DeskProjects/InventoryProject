package krawler.erp.OpeningDocuments;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import krawler.erp.page.Utilities;

public class OpeningSICreation {

	public void Create_OPSI_With_BaseCurrency(String documentid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			String documentid1 = documentid + "BaseSI";
			Properties pro = Utilities.fetchProValue("OR_SalesInvoice.properties");
			Thread.sleep(1000);
			// Click on customer check box
			WebDriverWait wait = new WebDriverWait(driver, 30);
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("CustomerChkbox"))))
					.click();

			// Click on "Opening Balance" button
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("OpeningBalBtn")))).click();
			Thread.sleep(1000);

			// Click on "Create New" button
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("CreatenewforOpen"))))
					.click();
			Thread.sleep(1000);

			// Fill the information for Opening SI document

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("TransactioNo"))))
					.sendKeys(documentid1);
			Thread.sleep(1000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("AmountforOpening"))))
					.sendKeys("100");
			Thread.sleep(1000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("PoNum"))))
					.sendKeys("PO123");
			Thread.sleep(1000);

			// Click on Save button
			driver.findElement(By.xpath(pro.getProperty("Save"))).click();
			Thread.sleep(1000);

			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);
			System.out.println("\"Opening Sales Invoice\" is created successfully with Base currency: " + documentid1);

		} catch (Exception EX) {
			System.out.println("Opening SI is not created");

			Utilities.handleError(EX, driver);
		}
		Thread.sleep(1000);

	}
	// ------------------------------------------- Opening SI with Foreign
	// Currency---------------------------------------------------------------------

	public void Create_OPSI_With_ForeignCurrency(String documentid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			String documentid1 = documentid + "ForeignSI";
			Properties pro = Utilities.fetchProValue("OR_SalesInvoice.properties");
			Thread.sleep(3000);

			// Click on "Create New" button
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("CreatenewforOpen"))))
					.click();
			Thread.sleep(2000);

			// Fill the information for Opening SI document

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("TransactioNo"))))
					.sendKeys(documentid1);
			Thread.sleep(1000);

			// Select the currency
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("CurrencyforOpening"))))
					.click();
			Thread.sleep(2000);
			driver.findElement(By.xpath(pro.getProperty("CurrencyforOpening"))).sendKeys("US Dollars (USD)");
			Thread.sleep(1000);
			driver.findElement(By.xpath(pro.getProperty("CurrencySelector"))).click();
			Thread.sleep(1000);

			// Putting Amount
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("AmountforOpening"))))
					.sendKeys("81");
			Thread.sleep(1000);

			// Putting additional info
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("PoNum"))))
					.sendKeys("PO123");
			Thread.sleep(2000);

			// Click on Save button
			driver.findElement(By.xpath(pro.getProperty("Save"))).click();
			Thread.sleep(1000);

			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);

			System.out
					.println("\"Opening Sales Invoice\" is created successfully with foreign currency: " + documentid1);

		} catch (Exception EX) {
			System.out.println("Opening SI is not created");

			Utilities.handleError(EX, driver);
		}
		Thread.sleep(1000);

	}
	// --------------------------------------------------------------Edit
	// Opening Document(Base Currency)--------------------------------------

	public void Edit_OPSI_With_BaseCurrency(String documentid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			String documentid1 = documentid + "BaseSI";
			Properties pro = Utilities.fetchProValue("OR_SalesInvoice.properties");
			Thread.sleep(1000);

			// Click on first row check box for selecting record
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("Firstrowchckbox"))))
					.click();
			Thread.sleep(1000);

			// Click on edit button
			driver.findElement(By.xpath(pro.getProperty("Editbtn"))).click();
			Thread.sleep(1000);

			// Put the memo information
			driver.findElement(By.xpath(pro.getProperty("OpeningMemo")))
					.sendKeys("Edit Opening SI by Automation Script");
			Thread.sleep(1000);

			// Change Amount for opening
			WebElement Amountchange = driver.findElement(By.xpath(pro.getProperty("AmountforOpening")));
			Amountchange.clear();
			Thread.sleep(1000);
			Amountchange.sendKeys("1000");
			Thread.sleep(2000);
			driver.findElement(By.xpath(pro.getProperty("OpeningMemo"))).click();
			Thread.sleep(1000);

			Utilities.clickButton("Save", 1000, driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);

			System.out.println("\"Opening Sales Invoice\" is edited successfully with base currency: " + documentid1);

		} catch (Exception EX) {
			System.out.println("Opening SI is not edited");
			Utilities.handleError(EX, driver);
		}
		Thread.sleep(1000);
	}

	// --------------------------------------------------------------Edit
	// Opening Document(Foreign Currency)--------------------------------------

	public void Edit_OPSI_With_ForeignCurrency(String documentid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			String documentid1 = documentid + "ForeignSI";
			Properties pro = Utilities.fetchProValue("OR_SalesInvoice.properties");
			Thread.sleep(1000);

			// Click on Second row check box for selecting record
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("Secondrowchckbox"))))
					.click();
			Thread.sleep(1000);

			// Click on edit button
			driver.findElement(By.xpath(pro.getProperty("Editbtn"))).click();
			Thread.sleep(1000);

			// Put the memo information
			driver.findElement(By.xpath(pro.getProperty("OpeningMemo")))
					.sendKeys("Edit Opening SI by Automation Script");
			Thread.sleep(1000);

			// Change Amount for opening
			WebElement Amountchange = driver.findElement(By.xpath(pro.getProperty("AmountforOpening")));
			Amountchange.clear();
			Thread.sleep(1000);
			Amountchange.sendKeys("810");
			Thread.sleep(2000);

			driver.findElement(By.xpath(pro.getProperty("OpeningMemo"))).click();
			Thread.sleep(1000);

			Utilities.clickButton("Save", 1000, driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);

			System.out
					.println("\"Opening Sales Invoice\" is edited successfully with foreign currency: " + documentid1);

		} catch (Exception EX) {
			System.out.println("Opening SI is not edited");
			Utilities.handleError(EX, driver);
		}
		Thread.sleep(1000);
	}

	// -------------------------------------------------------------Delete
	// Opening Sales
	// Documents---------------------------------------------------------

	public void Delete_OPSI_With_BasendForeignCurrency(String documentid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			String documentid1 = documentid + "BaseSI";
			Properties pro = Utilities.fetchProValue("OR_SalesInvoice.properties");
			Thread.sleep(1000);

			// Click on mass select row check box for selecting record
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("Massrow")))).click();
			Thread.sleep(1000);

			// Click on "Delete" button
			driver.findElement(By.xpath(pro.getProperty("DeleteBtn"))).click();
			Thread.sleep(3000);

			Utilities.clickButton("Yes", 3000, driver);
			Utilities.clickButton("OK", 3000, driver);

			System.out.println("Opening Sales Invoices is deleted successfully");

		} catch (Exception EX) {
			System.out.println("Opening SI is not deleted");
			Utilities.handleError(EX, driver);
		}
		Thread.sleep(1000);
	}

	// ----------------------------------------Again Create Opening SI with Base
	// and Foreign Currency-------------------------------------------

	public void Create_OPSI_With_BaseCurrency1(String documentid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			String documentid1 = documentid + "BaseSI";
			Properties pro = Utilities.fetchProValue("OR_SalesInvoice.properties");
			Thread.sleep(1000);

			// Click on "Create New" button
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("CreatenewforOpen"))))
					.click();
			Thread.sleep(1000);

			// Fill the information for Opening SI document

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("TransactioNo"))))
					.sendKeys(documentid1);
			Thread.sleep(1000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("AmountforOpening"))))
					.sendKeys("100");
			Thread.sleep(1000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("PoNum"))))
					.sendKeys("PO123");
			Thread.sleep(1000);

			// Click on Save button
			driver.findElement(By.xpath(pro.getProperty("Save"))).click();
			Thread.sleep(1000);

			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);

			System.out.println("\"Opening Sales Invoice\" is created successfully with Base currency: " + documentid1);

		} catch (Exception EX) {
			System.out.println("Opening SI is not created");

			Utilities.handleError(EX, driver);
		}
		Thread.sleep(1000);

	}
	// ------------------------------------------- Opening SI with Foreign
	// Currency---------------------------------------------------------------------

	public void Create_OPSI_With_ForeignCurrency1(String documentid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			String documentid1 = documentid + "ForeignSI";
			Properties pro = Utilities.fetchProValue("OR_SalesInvoice.properties");
			Thread.sleep(2000);

			// Click on "Create New" button
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("CreatenewforOpen"))))
					.click();
			Thread.sleep(2000);

			// Fill the information for Opening SI document

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("TransactioNo"))))
					.sendKeys(documentid1);
			Thread.sleep(1000);

			// Select the currency
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("CurrencyforOpening"))))
					.click();
			Thread.sleep(2000);
			driver.findElement(By.xpath(pro.getProperty("CurrencyforOpening"))).sendKeys("US Dollars (USD)");
			Thread.sleep(1000);
			driver.findElement(By.xpath(pro.getProperty("CurrencySelector"))).click();
			Thread.sleep(1000);

			// Putting Amount
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("AmountforOpening"))))
					.sendKeys("81");
			Thread.sleep(1000);

			// Putting additional info
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("PoNum"))))
					.sendKeys("PO123");
			Thread.sleep(2000);

			// Click on Save button
			driver.findElement(By.xpath(pro.getProperty("Save"))).click();
			Thread.sleep(1000);

			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);

			System.out
					.println("\"Opening Sales Invoice\" is created successfully with foreign currency: " + documentid1);

		} catch (Exception EX) {
			System.out.println("Opening SI is not created");

			Utilities.handleError(EX, driver);
		}
		Thread.sleep(1000);

	}

	// ---------------------------------------- Verify Opening SI document
	// through Purchase Invoice Report----------------------------------------

	public void OpeningSIDocument_Verifcation(WebDriver driver, String CustomerID)
			throws IOException, InterruptedException, AWTException {
		try {
			Properties pro = Utilities.fetchProValue("OR_SalesInvoice.properties");
			Thread.sleep(1000);

			// Open SI Report
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("SIReport")))).click();
			Thread.sleep(2000);

			// Choose "Opening document" from View drop down
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("Viewdropdown")))).click();
			Thread.sleep(1000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("Opendocusel1")))).click();
			Thread.sleep(2000);

			// Search Customer ID
			WebElement Search1 = new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("QuickSearch1"))));
			Search1.click();
			Search1.sendKeys(CustomerID + "Name");
			Thread.sleep(1000);
			Search1.sendKeys(Keys.ENTER);
			Thread.sleep(4000);

			// Check document is present or not
			String expected = "Displaying 1 - 2 of 2";
			String actual = driver.findElement(By.xpath(pro.getProperty("Bottomselct"))).getText();
			if (expected.equalsIgnoreCase(actual)) {
				System.out.println("Opening Sales Invoice records are shown successfully");
			}
		} catch (Exception EX) {
			System.out.println("No Opening Sales Invoice record is shown");

			Utilities.handleError(EX, driver);
		}
		Thread.sleep(1000);
	}

}
