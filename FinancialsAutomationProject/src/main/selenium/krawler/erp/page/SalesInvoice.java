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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SalesInvoice {

	public static void create_salesInvoice(String documentId, String productid, String customerid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			documentId = "SI" + documentId;
			Properties pro = Utilities.fetchProValue("OR_SalesInvoice.properties");
			// clicked on main icon
			new WebDriverWait(driver, 100)
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath(pro.getProperty("SalesInvoiceIcon"))));
			driver.findElement(By.xpath(pro.getProperty("SalesInvoiceIcon"))).click();
			Thread.sleep(3000);// pro

			// enter source name
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("Customer"))));
			WebElement customer = driver.findElement(By.xpath(pro.getProperty("Customer")));
			customer.clear();
			customer.sendKeys(customerid);
			Thread.sleep(3000);// pro
			customer.sendKeys(Keys.ENTER);

			// sequence format document no.
			WebElement squence = driver.findElement(By.xpath(pro.getProperty("SequenceFormat")));
			Thread.sleep(1000);// pro
			squence.clear();
			Thread.sleep(1000);// pro
			squence.sendKeys("NA");
			Thread.sleep(3000);// pro
			squence.sendKeys(Keys.ARROW_DOWN);
			Thread.sleep(2000);// pro
			squence.sendKeys(Keys.ENTER);

			Thread.sleep(2000);// pro

			// sequence format document no.
			WebElement term = driver.findElement(By.xpath(pro.getProperty("Term")));
			term.clear();
			term.sendKeys("NET 45");
			Thread.sleep(1000);// pro
			term.sendKeys(Keys.ENTER);
			Thread.sleep(2000);// pro

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("DocumentNo"))));
			driver.findElement(By.xpath(pro.getProperty("DocumentNo"))).sendKeys(documentId);

			// click on add button
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("AddButton"))));
			driver.findElement(By.xpath(pro.getProperty("AddButton"))).click();

			// search product id
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("ProductSearch"))));
			driver.findElement(By.xpath(pro.getProperty("ProductSearch"))).sendKeys(productid);
			Thread.sleep(2000);// pro

			// select check box
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("SelectProduct"))));
			driver.findElement(By.xpath(pro.getProperty("SelectProduct"))).click();
			Thread.sleep(2000);// pro

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("AddProduct"))));
			driver.findElement(By.xpath(".//button[text()='Add']")).click();
			Thread.sleep(3000);

			for (int i = 1; i <= driver
					.findElements(By
							.xpath("//*[@id='Invoiceeditproductdetailsgrid']/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td"))
					.size(); i++) {
				// System.out.println(driver.findElement(By.xpath("//div[@id='requisitioneditproductdetailsgrid']/div/div/div/div/div[1]/div[1]/div/table/thead/tr/td["+i+"]/div")).getText());
				if (driver.findElement(By
						.xpath("//*[@id='Invoiceeditproductdetailsgrid']/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText().equalsIgnoreCase("Quantity")) {
					driver.findElement(By
							.xpath("//*[@id='Invoiceeditproductdetailsgrid']/div/div/div/div[1]/div[2]/div[1]/div[1]/table/tbody/tr/td["
									+ i + "]/div"))
							.click();
					break;
				}
			}
			/*
			 * // Click on select NA of customer ID new WebDriverWait(driver,
			 * 30).until(ExpectedConditions.elementToBeClickable(By.xpath(pro.
			 * getProperty("Quantity")))); WebElement quantity =
			 * driver.findElement(By.xpath(pro.getProperty("Quantity")));
			 * quantity.click();
			 */
			Thread.sleep(1000);// pro
			Utilities.sendText("100");
			Thread.sleep(1000);// pro
			driver.findElement(By.xpath(pro.getProperty("Memo"))).click();
			driver.findElement(By.xpath(pro.getProperty("Memo"))).sendKeys("memo" + documentId);
			// ------------------------------------------------------------------------------------------------------------------------------------------------

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("SaveButton"))));
			driver.findElement(By.xpath("//button[text()='Save']")).click();
			Thread.sleep(2000);

			// Click on yes
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("YesButton"))));
			driver.findElement(By.xpath(pro.getProperty("YesButton"))).click();

			Thread.sleep(1000);
			Utilities.clickButton("OK", 1500, driver);
			Thread.sleep(1000);

			// Email code
			String subjectLine = "Sales Invoice - testsmoke - " + documentId;
			EmailFunctionality.email_AfterSave(documentId, subjectLine, driver);
			Thread.sleep(1000);

			System.out.println("Sales Invoice successfully Created for :" + documentId);
			// Click on OK
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("CloseSalesInvoice"))));
			driver.findElement(By.xpath(pro.getProperty("CloseSalesInvoice"))).click();

		} catch (Exception EX) {
			System.out.println("Sales Invoice is NOT Created for :[" + documentId + "] checlk LOG");
			Utilities.handleError(EX, driver);
		}

	}

	public static void verify_SalesInvoice(String documentId, String productid, String customerID, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {

			documentId = "SI" + documentId;
			WebDriverWait wait = new WebDriverWait(driver, 100);
			Properties pro = Utilities.fetchProValue("OR_SalesInvoice.properties");
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("SalesInvoiceReport"))));
			driver.findElement(By.xpath(pro.getProperty("SalesInvoiceReport"))).click();
			Thread.sleep(5000);// pro

			WebElement search = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("QuickSearch"))));
			search.sendKeys(documentId);
			search.sendKeys(Keys.TAB);
			Thread.sleep(2000);
			int e = driver
					.findElements(By
							.xpath(".//*[@id='gridmsg16InvoiceListEntry']/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td/div"))
					.size();
			System.out.println(e);
			Utilities.zoomOut();
			Thread.sleep(3000);
			for (int i = 1; i < e + 1; i++) {
				// System.out.println(driver.findElement(By.xpath(".//*[@id='gridmsg16InvoiceListEntry']/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["+i+"]/div")).getText());
				String header = driver.findElement(By
						.xpath(".//*[@id='gridmsg16InvoiceListEntry']/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();

				if (header.equals("Invoice No")) {
					assertEquals(documentId,
							driver.findElement(By
									.xpath(".//div[@id='gridmsg16InvoiceListEntry']/div/div/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
				}

				else if (header.equals("Customer")) {
					assertEquals(customerID + "Name",
							driver.findElement(By
									.xpath(".//div[@id='gridmsg16InvoiceListEntry']/div/div/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
				}

				else if (header.equals("Customer Code")) {
					assertEquals(customerID,
							driver.findElement(By
									.xpath(".//div[@id='gridmsg16InvoiceListEntry']/div/div/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
				}

				// else if(header.equals("Memo"))
				// {assertEquals("memo"+documentId,
				// driver.findElement(By.xpath(".//div[@id='gridmsg16InvoiceListEntry']/div/div/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["+i+"]/div")).getText());}

			}
			Utilities.zoomIn();

			// Click on Close
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("CloseCustomerReport"))));
			driver.findElement(By.xpath(pro.getProperty("CloseCustomerReport"))).click();
			Thread.sleep(500);
			Utilities.refresh();

			System.out.println("Sales Invoice Successfully verified");

		} catch (Exception EX) {
			Thread.sleep(3000);// pro
			Utilities.handleError(EX, driver);
		}

	}

	public static void EmailCopyEditDelete_SalesInvoice(String documentid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {

			documentid = "SI" + documentid;
			WebDriverWait wait = new WebDriverWait(driver, 100);
			Properties pro = Utilities.fetchProValue("OR_SalesInvoice.properties");
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("SalesInvoiceReport"))));
			driver.findElement(By.xpath(pro.getProperty("SalesInvoiceReport"))).click();
			Thread.sleep(5000);

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "uncheck", driver);
			WebElement search = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("QuickSearch"))));
			search.sendKeys(documentid);
			search.sendKeys(Keys.TAB);
			Thread.sleep(3000);

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);
			Thread.sleep(1000);

			// Report Email code
			String subjectLine = "Sales Invoice - testsmoke - " + documentid;
			EmailFunctionality.email_fromReports(documentid, subjectLine, driver);
			Thread.sleep(2000);

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "uncheck", driver);
			search.clear();
			search.sendKeys(documentid);
			search.sendKeys(Keys.TAB);
			Thread.sleep(2000);

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);
			Thread.sleep(1000);

			WebElement copySalesInvoiceButton = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("copySalesInvoiceButton"))));
			copySalesInvoiceButton.click();
			Thread.sleep(2000);

			String CopySalesInvoiceID = "Copy" + documentid;

			new WebDriverWait(driver, 60)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("copySalesInvoiceId"))));
			driver.findElement(By.xpath(pro.getProperty("copySalesInvoiceId"))).sendKeys(CopySalesInvoiceID);

			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("saveCopiedSalesInvoiceId"))));

			driver.findElement(By.xpath(pro.getProperty("saveCopiedSalesInvoiceId"))).click();

			Thread.sleep(3000);

			Robot r = new Robot();
			Utilities.clickButton("Yes", 500, driver);
			Thread.sleep(1000);
			Utilities.clickButton("OK", 1500, driver);
			Thread.sleep(1000);

			try {
				new WebDriverWait(driver, 30).until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath(pro.getProperty("CopySalesInvoiceClose"))));
				driver.findElement(By.xpath(pro.getProperty("CopySalesInvoiceClose"))).click();
				Thread.sleep(3000);
			} catch (Exception EX) {
				r.keyPress(KeyEvent.VK_F5);
				r.keyRelease(KeyEvent.VK_F5);

			}

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "uncheck", driver);
			search.clear();
			search.sendKeys(CopySalesInvoiceID);
			Thread.sleep(1000);
			search.sendKeys(Keys.TAB);
			Thread.sleep(2000);

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);
			Thread.sleep(1000);

			WebElement editSalesInvoiceButton = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("EditSalesInvoiceButton"))));
			editSalesInvoiceButton.click();
			Thread.sleep(3000);

			WebElement SalesInvoiceMemo = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("SalesInvoiceMemo"))));
			SalesInvoiceMemo.sendKeys("Update Sales Invoice Memo");
			Thread.sleep(2000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("SalesInvoiceEditSave"))));
			driver.findElement(By.xpath(pro.getProperty("SalesInvoiceEditSave"))).click();
			Thread.sleep(2000);

			Utilities.clickButton("Yes", 1000, driver);
			Thread.sleep(1000);
			Utilities.clickButton("OK", 1500, driver);
			Thread.sleep(1000);
			try {
				new WebDriverWait(driver, 30).until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath(pro.getProperty("EditSalesInvoiceClose"))));
				driver.findElement(By.xpath(pro.getProperty("EditSalesInvoiceClose"))).click();
				Thread.sleep(3000);
			} catch (Exception EX) {
				r.keyPress(KeyEvent.VK_F5);
				r.keyRelease(KeyEvent.VK_F5);

			}

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "uncheck", driver);
			search.clear();
			search.sendKeys(CopySalesInvoiceID);
			Thread.sleep(1000);
			search.sendKeys(Keys.TAB);
			Thread.sleep(2000);

			try {
				Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);

				Thread.sleep(3000);
				if (driver
						.findElement(By
								.xpath("//div[@id='InvoiceListEntry']/div/div/div/div/div/div[2]/div/div[3]/div/table/following-sibling::div"))
						.getText().equalsIgnoreCase("Displaying 1 - 1 of 1")) {
					System.out.println("Sales Invoice " + CopySalesInvoiceID + " is edited Successfully");
				} else {
					System.out.println("Sales Invoice " + CopySalesInvoiceID + " is not edited Successfully");
				}

			} catch (Exception EX) {
				r.keyPress(KeyEvent.VK_F5);
				r.keyRelease(KeyEvent.VK_F5);
			}

			new WebDriverWait(driver, 30).until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath(pro.getProperty("deleteSalesInvoiceButton"))));
			driver.findElement(By.xpath(pro.getProperty("deleteSalesInvoiceButton"))).click();

			Thread.sleep(2000);
			new WebDriverWait(driver, 30).until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath(pro.getProperty("deleteSalesInvoicePermButton"))));
			driver.findElement(By.xpath(pro.getProperty("deleteSalesInvoicePermButton"))).click();
			Thread.sleep(2000);

			Utilities.clickButton("Yes", 1000, driver);
			Utilities.clickButton("OK", 0, driver);
			Thread.sleep(2000);

			try {
				Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "uncheck", driver);
				search.clear();
				search.sendKeys(CopySalesInvoiceID);
				Thread.sleep(1000);
				search.sendKeys(Keys.TAB);
				Thread.sleep(4000);

				if (driver
						.findElement(By
								.xpath("//div[@id='InvoiceListEntry']/div/div/div/div/div/div[2]/div/div[3]/div/table/following-sibling::div"))
						.getText().equalsIgnoreCase("No results to display")) {
					System.out.println("Sales Invoice " + CopySalesInvoiceID + "  is Deleted Successfully");
				} else {
					System.out.println("Sales Invoice " + CopySalesInvoiceID + "  is not Deleted Successfully");
				}
			} catch (Exception exp) {
				System.out.println("Sales Invoice " + CopySalesInvoiceID + " is Successfully deleted");
			}

			Utilities.refresh();

		} catch (Exception EX) {
			Thread.sleep(3000);
			Utilities.handleError(EX, driver);
		}
	}

}
