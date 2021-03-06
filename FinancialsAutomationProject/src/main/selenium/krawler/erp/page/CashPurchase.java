package krawler.erp.page;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CashPurchase {

	public static void create_cashPurchase(String documentid, String productid, String vendorid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {

		try {

			documentid = "CshPur" + documentid;
			Properties CashPurchase = Utilities.fetchProValue("OR_CashPurchase.properties");

			Thread.sleep(3000);
			// WebDriverWait wait = new WebDriverWait(driver, 30);

			// clicked on Purchase Invoice
			// System.out.println(driver.findElement(By.xpath(CashPurchase.getProperty("CreateCashPurchaseIcon"))).isEnabled());
			new WebDriverWait(driver, 60).until(ExpectedConditions
					.elementToBeClickable(By.xpath(CashPurchase.getProperty("CreateCashPurchaseIcon"))));
			driver.findElement(By.xpath(CashPurchase.getProperty("CreateCashPurchaseIcon"))).click();
			Thread.sleep(10000);// pro

			// enter vendor name
			WebElement vendor = driver.findElement(By.xpath(CashPurchase.getProperty("vendorName")));
			vendor.clear();
			vendor.sendKeys(vendorid);
			Thread.sleep(3000);// pro
			vendor.sendKeys(Keys.ENTER);

			// sequen format document no.
			WebElement squence = driver.findElement(By.xpath(CashPurchase.getProperty("sequenceFormat")));
			Thread.sleep(1000);// pro
			squence.clear();
			Thread.sleep(1000);
			squence.sendKeys("NA");
			Thread.sleep(1000);// pro
			squence.sendKeys(Keys.ENTER);
			Thread.sleep(2000);// pro

			// vendor invoice no.

			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(CashPurchase.getProperty("vendorInvoiceNo"))));
			driver.findElement(By.xpath(CashPurchase.getProperty("vendorInvoiceNo"))).sendKeys(documentid);

			// click on add button
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(CashPurchase.getProperty("addButton"))));
			driver.findElement(By.xpath(CashPurchase.getProperty("addButton"))).click();

			// search product id
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(CashPurchase.getProperty("productId"))));
			driver.findElement(By.xpath(CashPurchase.getProperty("productId"))).sendKeys(productid);
			Thread.sleep(4000);// pro

			// select check box
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(CashPurchase.getProperty("checkBox"))));
			driver.findElement(By.xpath(CashPurchase.getProperty("checkBox"))).click();
			Thread.sleep(2000);// pro

			// click Add

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(CashPurchase.getProperty("clickAdd"))));
			driver.findElement(By.xpath(CashPurchase.getProperty("clickAdd"))).click();
			Thread.sleep(3000);

			// Click on select NA of customer ID

			for (int i = 1; i <= driver
					.findElements(By
							.xpath("//div[@id='purchasereceiptinvoicegrid']/div[2]/div/div/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td"))
					.size(); i++) {
				if (driver.findElement(By
						.xpath("//div[@id='purchasereceiptinvoicegrid']/div[2]/div/div/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText().equalsIgnoreCase("Quantity")) {
					driver.findElement(By
							.xpath("//div[@id='purchasereceiptinvoicegrid']/div[2]/div/div/div/div/div/div[1]/div[2]/div[1]/div[1]/table/tbody/tr/td["
									+ i + "]/div"))
							.click();
					break;
				}
			}

			/*
			 * new WebDriverWait(driver,30).until(ExpectedConditions.
			 * elementToBeClickable(By.xpath(CashPurchase.getProperty(
			 * "selectNAcustomerId")))); WebElement quantity =
			 * driver.findElement(By.xpath(CashPurchase.getProperty(
			 * "selectNAcustomerId"))); quantity.click();
			 * Thread.sleep(1000);//pro
			 */

			Thread.sleep(2000);
			Utilities.sendText("1");
			Thread.sleep(2000);

			driver.findElement(By.xpath(CashPurchase.getProperty("memo"))).click(); // memo
			// ------------------------------------------------------------------------------------------------------------------------------------------------

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(CashPurchase.getProperty("saveButton"))));
			driver.findElement(By.xpath(CashPurchase.getProperty("saveButton"))).click();
			Thread.sleep(2000);

			// Click on yes
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(CashPurchase.getProperty("yesButton"))));
			driver.findElement(By.xpath(CashPurchase.getProperty("yesButton"))).click();

			// Click on OK
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(CashPurchase.getProperty("okButton"))));
			driver.findElement(By.xpath(CashPurchase.getProperty("okButton"))).click();
			Thread.sleep(1000);

			// Email code
			String subjectLine = "Cash Purchase(Product) - testsmoke - " + documentid;
			EmailFunctionality.email_AfterSave(documentid, subjectLine, driver);
			Thread.sleep(1000);

			System.out.println("Cash Purchase(Product) Successfull for  - " + documentid);

			// Click on OK
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(CashPurchase.getProperty("clickOk"))));
			driver.findElement(By.xpath(CashPurchase.getProperty("clickOk"))).click();
		} catch (Exception EX) {
			System.out.println("Cash Purchase(Product) !!!!! Failed !!!!!! for  - " + documentid);
			Utilities.handleError(EX, driver);
		}

	}

	// *******************************************verification***************************************************

	public static void verify_CashPurchase(String documentid, String productid, String vendorid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {

			// documentid="CshPur"+documentid;
			WebDriverWait wait = new WebDriverWait(driver, 100);
			Properties pro = Utilities.fetchProValue("OR_CashPurchase.properties");
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("SalesInvoiceReport"))));
			driver.findElement(By.xpath(pro.getProperty("SalesInvoiceReport"))).click();
			Thread.sleep(5000);// pro

			WebElement search = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("QuickSearch"))));

			search.sendKeys(documentid);
			search.sendKeys(Keys.TAB);
			Thread.sleep(2000);
			int e = driver
					.findElements(By
							.xpath(".//div[@id='gridmsg21GRListEntry']/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td/div"))
					.size();
			// System.out.println(e);
			Thread.sleep(3000);
			for (int i = 1; i < e + 1; i++) {
				// System.out.println(driver.findElement(By.xpath(".//div[@id='gridmsg21GRListEntry']/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["+i+"]/div")).getText());
				String header = driver.findElement(By
						.xpath(".//div[@id='gridmsg21GRListEntry']/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();

				if (header.equals("Purchase Invoice No")) {
					assertEquals(documentid,
							driver.findElement(By
									.xpath(".//div[@id='gridmsg21GRListEntry']/div/div/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
				}

				else if (header.equals("Vendor")) {
					assertEquals(vendorid + "Name",
							driver.findElement(By
									.xpath(".//div[@id='gridmsg21GRListEntry']/div/div/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
				}

				else if (header.equals("Vendor Code")) {
					assertEquals(vendorid,
							driver.findElement(By
									.xpath(".//div[@id='gridmsg21GRListEntry']/div/div/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
				}

			}
			Thread.sleep(500);
			String xpathOfelement = "//li[@id='as__GRListMainTabEntry']/a[1]";
			Utilities.HoverandClick(xpathOfelement, driver);

			System.out.println("Cash Purchase Successfully verified for [" + documentid + "]");

		} catch (Exception EX) {
			Thread.sleep(3000);// pro
			Utilities.handleError(EX, driver);
		}

	}

	// **************************** Email-Copy-Edit-Delete
	// ********************************************************
	// >>>>>>>pro.getProperty("DocumentCheckBox")
	public static void EmailCopyEditDelete_CashPurchase(String documentid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {

			// documentid="CshPur"+documentid;
			WebDriverWait wait = new WebDriverWait(driver, 100);
			Properties pro = Utilities.fetchProValue("OR_CashPurchase.properties");
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("SalesInvoiceReport"))));
			driver.findElement(By.xpath(pro.getProperty("SalesInvoiceReport"))).click();
			Thread.sleep(5000);

			WebElement search = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("QuickSearch"))));

			search.sendKeys(documentid);
			search.sendKeys(Keys.TAB);
			Thread.sleep(2000);

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "Check", driver);

			// Email code
			String subjectLine = "Cash Purchase(Product)- testsmoke - " + documentid;
			EmailFunctionality.email_fromReports(documentid, subjectLine, driver);
			Thread.sleep(2000);

			search.clear();
			search.sendKeys(documentid);
			search.sendKeys(Keys.TAB);
			Thread.sleep(4000);

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "Check", driver);

			WebElement copyCashPurchaseButton = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("copyCashPurchaseButton"))));
			copyCashPurchaseButton.click();
			Thread.sleep(2000);

			String CopyCashPurchaseID = "Copy" + documentid;

			new WebDriverWait(driver, 60)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("copyCashPurchaseId"))));
			driver.findElement(By.xpath(pro.getProperty("copyCashPurchaseId"))).sendKeys(CopyCashPurchaseID);

			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("saveCopiedCashPurchaseId"))));
			driver.findElement(By.xpath(pro.getProperty("saveCopiedCashPurchaseId"))).click();
			Thread.sleep(1000);

			Robot r = new Robot();
			Utilities.clickButton("Yes", 500, driver);

			Utilities.clickButton("OK", 500, driver);

			try {
				new WebDriverWait(driver, 30).until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath(pro.getProperty("CopyCashPurchaseClose"))));
				driver.findElement(By.xpath(pro.getProperty("CopyCashPurchaseClose"))).click();
				Thread.sleep(3000);
			} catch (Exception EX) {
				r.keyPress(KeyEvent.VK_F5);
				r.keyRelease(KeyEvent.VK_F5);

			}

			Thread.sleep(3000);
			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "uncheck", driver);
			search.clear();
			search.sendKeys(CopyCashPurchaseID);
			search.sendKeys(Keys.TAB);
			Thread.sleep(2000);

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "Check", driver);

			WebElement editCashPurchaseButton = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("EditCashPurchaseButton"))));
			editCashPurchaseButton.click();
			Thread.sleep(3000);

			WebElement CashPurchaseMemo = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("CashPurchaseMemo"))));
			CashPurchaseMemo.sendKeys("Update Cash Purchase Memo");
			Thread.sleep(3000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("CashPurchaseEditSave"))));
			driver.findElement(By.xpath(pro.getProperty("CashPurchaseEditSave"))).click();
			Thread.sleep(1000);

			Utilities.clickButton("Yes", 500, driver);
			Thread.sleep(2000);
			Utilities.clickButton("OK", 1000, driver);
			try {
				new WebDriverWait(driver, 30).until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath(pro.getProperty("EditCashPurchaseClose"))));
				driver.findElement(By.xpath(pro.getProperty("EditCashPurchaseClose"))).click();
				Thread.sleep(3000);
			} catch (Exception EX) {
				r.keyPress(KeyEvent.VK_F5);
				r.keyRelease(KeyEvent.VK_F5);

			}

			Thread.sleep(3000);
			search.clear();
			search.sendKeys(CopyCashPurchaseID);
			Thread.sleep(2000);
			search.sendKeys(Keys.TAB);

			Thread.sleep(2000);

			try {
				new WebDriverWait(driver, 30).until(
						ExpectedConditions.visibilityOfElementLocated(By.xpath(pro.getProperty("DocumentCheckBox"))));
				driver.findElement(By.xpath(pro.getProperty("DocumentCheckBox"))).click();
				Thread.sleep(3000);
				if (driver
						.findElement(By
								.xpath("//div[@id='GRListEntry']/div/div/div/div/div/div[2]/div/div[3]/div/table/following-sibling::div"))
						.getText().equalsIgnoreCase("Displaying 1 - 1 of 1")) {
					System.out.println("Cash Purchase " + CopyCashPurchaseID + " is edited Successfully");
				} else {
					System.out.println("Cash Purchase " + CopyCashPurchaseID + " is not edited Successfully");
				}

			} catch (Exception EX) {
				r.keyPress(KeyEvent.VK_F5);
				r.keyRelease(KeyEvent.VK_F5);
			}

			new WebDriverWait(driver, 30).until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath(pro.getProperty("deleteCashPurchaseButton"))));
			driver.findElement(By.xpath(pro.getProperty("deleteCashPurchaseButton"))).click();

			Thread.sleep(2000);
			new WebDriverWait(driver, 30).until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath(pro.getProperty("deleteCashPurchasePermButton"))));
			driver.findElement(By.xpath(pro.getProperty("deleteCashPurchasePermButton"))).click();
			Thread.sleep(2000);

			r.keyPress(KeyEvent.VK_SPACE);
			r.keyRelease(KeyEvent.VK_SPACE);

			Thread.sleep(2000);

			r.keyPress(KeyEvent.VK_SPACE);
			r.keyRelease(KeyEvent.VK_SPACE);

			Thread.sleep(2000);

			try {
				search.clear();
				search.sendKeys(CopyCashPurchaseID);
				Thread.sleep(2000);

				if (driver
						.findElement(By
								.xpath("//div[@id='GRListEntry']/div/div/div/div/div/div[2]/div/div[3]/div/table/following-sibling::div"))
						.getText().equalsIgnoreCase("No results to display")) {
					System.out.println("Cash Purchase " + CopyCashPurchaseID + "  is Deleted Successfully");
				} else {
					System.out.println("Cash Purchase " + CopyCashPurchaseID + "  is not Deleted Successfully");
				}
			} catch (Exception exp) {
				System.out.println("Cash Purchase " + CopyCashPurchaseID + " is Successfully deleted");
			}
			String xpathOfelement = "//li[@id='as__GRListMainTabEntry']/a[1]";
			Utilities.HoverandClick(xpathOfelement, driver);
		} catch (Exception EX) {
			Thread.sleep(3000);
			Utilities.handleError(EX, driver);
		}
	}// meth

	// * * * * * * * EXPORT Cash Purchase * * * * * *

	public static void Export_CashPurchase(WebDriver driver) throws InterruptedException, AWTException, IOException {
		String BtnName2 = "Export List";
		Properties pro = Utilities.fetchProValue("OR_CashPurchase.properties");
		String reportIcon = pro.getProperty("SalesInvoiceReport");
		String waitForQuickSearch = pro.getProperty("DocumentCheckBox");
		String closeReportPage = "//li[@id='as__GRListMainTabEntry']/a[1]";
		String ModuleName = "Cash Purchase";

		ExportFunction.Export_TodayDate(BtnName2, reportIcon, waitForQuickSearch, closeReportPage, ModuleName, driver);

	}

}// class
