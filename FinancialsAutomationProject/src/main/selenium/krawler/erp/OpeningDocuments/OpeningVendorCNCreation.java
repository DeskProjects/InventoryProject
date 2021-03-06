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

public class OpeningVendorCNCreation {

	public void Create_OPVendorCN_With_BaseCurrency(String documentid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			String documentid1 = documentid + "BaseVendCN";
			Properties pro = Utilities.fetchProValue("OR_CreditNoteOtherwise.properties");
			Thread.sleep(2000);

			WebDriverWait wait = new WebDriverWait(driver, 30);
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("VendorChkbox2")))).click();

			// Click on "Opening Balance" button
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("OpeningBalBtn2"))))
					.click();
			Thread.sleep(2000);

			// Click on Credit Note Tab
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("CNTab2")))).click();

			// Click on "Create New" button
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("CreatenewforOpen2"))))
					.click();
			Thread.sleep(1000);

			// Fill the information for Opening Vendor CN document

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("TransactioNo2"))))
					.sendKeys(documentid1);
			Thread.sleep(1000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("AmountforOpening2"))))
					.sendKeys("100");
			Thread.sleep(1000);

			// Click on Save button
			driver.findElement(By.xpath(pro.getProperty("Save2"))).click();
			Thread.sleep(1000);

			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);
			System.out.println(
					"\"Opening Vendor Credit Note\" is created successfully with Base currency: " + documentid1);

		} catch (Exception EX) {
			System.out.println("Opening Vendor CN is not created");

			Utilities.handleError(EX, driver);
		}
		Thread.sleep(1000);

	}
	// ------------------------------------------- Opening Vendor CN with
	// Foreign
	// Currency---------------------------------------------------------------------

	public void Create_OPVendorCN_With_ForeignCurrency(String documentid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			String documentid1 = documentid + "ForeignVendCN";
			Properties pro = Utilities.fetchProValue("OR_CreditNoteOtherwise.properties");
			Thread.sleep(3000);

			// Click on "Create New" button
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("CreatenewforOpen2"))))
					.click();
			Thread.sleep(2000);

			// Fill the information for Opening Vendor CN document

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("TransactioNo2"))))
					.sendKeys(documentid1);
			Thread.sleep(1000);

			// Select the currency
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("CurrencyforOpening2"))))
					.click();
			Thread.sleep(2000);
			driver.findElement(By.xpath(pro.getProperty("CurrencyforOpening2"))).sendKeys("US Dollars (USD)");
			Thread.sleep(1000);
			driver.findElement(By.xpath(pro.getProperty("CurrencySelector2"))).click();
			Thread.sleep(1000);

			// Putting Amount
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("AmountforOpening2"))))
					.sendKeys("81");
			Thread.sleep(1000);

			// Click on Save button
			driver.findElement(By.xpath(pro.getProperty("Save2"))).click();
			Thread.sleep(1000);

			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);

			System.out.println(
					"\"Opening Vendor Credit Note\" is created successfully with foreign currency: " + documentid1);

		} catch (Exception EX) {
			System.out.println("Opening Vendor CN is not created");

			Utilities.handleError(EX, driver);
		}
		Thread.sleep(1000);

	}
	// --------------------------------------------------------------Edit
	// Opening Vendor CN Document(Base
	// Currency)--------------------------------------

	public void Edit_OPVendorCN_With_BaseCurrency(String documentid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			String documentid1 = documentid + "BaseVendCN";
			Properties pro = Utilities.fetchProValue("OR_CreditNoteOtherwise.properties");
			Thread.sleep(1000);

			// Click on first row check box for selecting record
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("Firstrowchckbox2"))))
					.click();
			Thread.sleep(1000);

			// Click on edit button
			driver.findElement(By.xpath(pro.getProperty("Editbtn2"))).click();
			Thread.sleep(1000);

			// Put the memo information
			driver.findElement(By.xpath(pro.getProperty("OpeningMemo2")))
					.sendKeys("Edit Opening Vendor CN by Automation Script");
			Thread.sleep(1000);

			// Change Amount for opening
			WebElement Amountchange = driver.findElement(By.xpath(pro.getProperty("AmountforOpening2")));
			Amountchange.clear();
			Thread.sleep(1000);
			Amountchange.sendKeys("1000");
			Thread.sleep(2000);
			driver.findElement(By.xpath(pro.getProperty("OpeningMemo2"))).click();
			Thread.sleep(1000);

			Utilities.clickButton("Save", 1000, driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);

			System.out.println(
					"\"Opening Vendor Credit Note\" is edited successfully with base currency: " + documentid1);

		} catch (Exception EX) {
			System.out.println("Opening Vendor CN is not edited");
			Utilities.handleError(EX, driver);
		}
		Thread.sleep(1000);
	}

	// --------------------------------------------------------------Edit
	// Opening Vendor CN Document(Foreign
	// Currency)--------------------------------------

	public void Edit_OPVendorCN_With_ForeignCurrency(String documentid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			String documentid1 = documentid + "ForeignVendCN";
			Properties pro = Utilities.fetchProValue("OR_CreditNoteOtherwise.properties");
			Thread.sleep(1000);

			// Click on Second row check box for selecting record
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("Secondrowchckbox2"))))
					.click();
			Thread.sleep(1000);

			// Click on edit button
			driver.findElement(By.xpath(pro.getProperty("Editbtn2"))).click();
			Thread.sleep(1000);

			// Put the memo information
			driver.findElement(By.xpath(pro.getProperty("OpeningMemo2")))
					.sendKeys("Edit Opening Vendor CN by Automation Script");
			Thread.sleep(1000);

			// Change Amount for opening
			WebElement Amountchange = driver.findElement(By.xpath(pro.getProperty("AmountforOpening2")));
			Amountchange.clear();
			Thread.sleep(1000);
			Amountchange.sendKeys("810");
			Thread.sleep(2000);

			driver.findElement(By.xpath(pro.getProperty("OpeningMemo2"))).click();
			Thread.sleep(1000);

			Utilities.clickButton("Save", 1000, driver);
			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);

			System.out.println(
					"\"Opening Vendor Credit Note\" is edited successfully with foreign currency: " + documentid1);

		} catch (Exception EX) {
			System.out.println("Opening Vendor CN is not edited");
			Utilities.handleError(EX, driver);
		}
		Thread.sleep(1000);
	}

	// -------------------------------------------------------------Delete
	// Opening Vendor CN
	// Documents---------------------------------------------------------

	public void Delete_OPVendorCN_With_BaseandForeignCurrency(String documentid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			String documentid1 = documentid + "BaseVendCN";
			Properties pro = Utilities.fetchProValue("OR_CreditNoteOtherwise.properties");
			Thread.sleep(1000);

			// Click on mass selection row check box for selecting record
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("Massrow2")))).click();
			Thread.sleep(1000);

			// Click on "Delete" button
			driver.findElement(By.xpath(pro.getProperty("DeleteBtn2"))).click();
			Thread.sleep(1000);

			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 6000, driver);

			System.out.println("Opening Vendor Credit Notes are deleted successfully");

		} catch (Exception EX) {
			System.out.println("Opening Vendor CN are not deleted");
			Utilities.handleError(EX, driver);
		}
		Thread.sleep(1000);
	}

	// ----------------------------------------Again Create Opening Vendor CN
	// with Base and Foreign Currency-------------------------------------------

	public void Create_OPVendorCN_With_BaseCurrency1(String documentid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			String documentid1 = documentid + "BaseVendCN";
			Properties pro = Utilities.fetchProValue("OR_CreditNoteOtherwise.properties");
			Thread.sleep(1000);

			// Click on "Create New" button
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("CreatenewforOpen2"))))
					.click();
			Thread.sleep(1000);

			// Fill the information for Opening Vendor Credit Note document

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("TransactioNo2"))))
					.sendKeys(documentid1);
			Thread.sleep(1000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("AmountforOpening2"))))
					.sendKeys("100");
			Thread.sleep(1000);

			// Click on Save button
			driver.findElement(By.xpath(pro.getProperty("Save2"))).click();
			Thread.sleep(1000);

			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);

			System.out.println(
					"\"Opening Vendor Credit Note\" is created successfully with Base currency: " + documentid1);

		} catch (Exception EX) {
			System.out.println("Opening Vendor CN is not created");

			Utilities.handleError(EX, driver);
		}
		Thread.sleep(1000);

	}
	// ------------------------------------------- Opening Vendor CN with
	// Foreign
	// Currency---------------------------------------------------------------------

	public void Create_OPVendorCN_With_ForeignCurrency1(String documentid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			String documentid1 = documentid + "ForeignVendCN";
			Properties pro = Utilities.fetchProValue("OR_CreditNoteOtherwise.properties");
			Thread.sleep(2000);

			// Click on "Create New" button
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("CreatenewforOpen2"))))
					.click();
			Thread.sleep(2000);

			// Fill the information for Opening Vendor CN document

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("TransactioNo2"))))
					.sendKeys(documentid1);
			Thread.sleep(1000);

			// Select the currency
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("CurrencyforOpening2"))))
					.click();
			Thread.sleep(2000);
			driver.findElement(By.xpath(pro.getProperty("CurrencyforOpening2"))).sendKeys("US Dollars (USD)");
			Thread.sleep(1000);
			driver.findElement(By.xpath(pro.getProperty("CurrencySelector2"))).click();
			Thread.sleep(1000);

			// Putting Amount
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("AmountforOpening2"))))
					.sendKeys("81");
			Thread.sleep(1000);

			// Click on Save button
			driver.findElement(By.xpath(pro.getProperty("Save2"))).click();
			Thread.sleep(1000);

			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 1000, driver);

			System.out.println(
					"\"Opening Vendor Credit Note\" is created successfully with foreign currency: " + documentid1);

		} catch (Exception EX) {
			System.out.println("Opening Vendor CN is not created");

			Utilities.handleError(EX, driver);
		}
		Thread.sleep(1000);

	}

	// --------------------------------------- Verify Opening Vendor Credit Note
	// document through Credit Note
	// Report--------------------------------------------

	public void OpeningVendorCNDocument_Verifcation(WebDriver driver, String VendorID)
			throws IOException, InterruptedException, AWTException {
		try {
			Properties pro = Utilities.fetchProValue("OR_CreditNoteOtherwise.properties");
			Thread.sleep(1000);

			// Open Credit Note Report
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("CNReport")))).click();
			Thread.sleep(2000);

			// Choose "Opening document" from View drop down
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("Viewdropdown")))).click();
			Thread.sleep(1000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("Opendocusel1")))).click();
			Thread.sleep(2000);

			// Search Vendor ID
			WebElement Search1 = new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("QuickSearch1"))));
			Search1.click();
			Search1.sendKeys(VendorID + "Name");
			Thread.sleep(1000);
			Search1.sendKeys(Keys.ENTER);
			Thread.sleep(4000);

			// Check document is present or not
			String expected = "Displaying 1 - 2 of 2";
			String actual = driver.findElement(By.xpath(pro.getProperty("Bottomselct"))).getText();
			if (expected.equalsIgnoreCase(actual)) {
				System.out.println("Opening Vendor Credit Note records are shown successfully");
			}
		} catch (Exception EX) {
			System.out.println("No Opening Vendor Credit Note record is shown");

			Utilities.handleError(EX, driver);
		}
		Thread.sleep(1000);
	}

}
