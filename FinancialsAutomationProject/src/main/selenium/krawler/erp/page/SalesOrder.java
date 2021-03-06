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

public class SalesOrder {

	public static void create_salesOrder(String documentid, String productid, String customerid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {

		try {

			documentid = "So" + documentid;
			Properties SalesOrder = Utilities.fetchProValue("OR_SalesOrder.properties");

			Thread.sleep(3000);
			// WebDriverWait wait = new WebDriverWait(driver, 30);

			// clicked on main icon
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(SalesOrder.getProperty("CreateSalesOrderIcon"))));
			driver.findElement(By.xpath(SalesOrder.getProperty("CreateSalesOrderIcon"))).click();
			Thread.sleep(10000);// pro

			// enter source name
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(SalesOrder.getProperty("customerName"))));
			WebElement customer = driver.findElement(By.xpath(SalesOrder.getProperty("customerName")));
			customer.clear();
			customer.sendKeys(customerid);
			Thread.sleep(3000);// pro
			customer.sendKeys(Keys.ENTER);

			// sequence format document no.
			WebElement squence = driver.findElement(By.xpath(SalesOrder.getProperty("sequenceFormat")));

			Thread.sleep(1000);
			squence.clear();
			squence.sendKeys("NA");
			Thread.sleep(1000);// pro
			squence.sendKeys(Keys.ENTER);
			Thread.sleep(2000);// pro

			// sequence format document no.
			WebElement term = driver.findElement(By.xpath(SalesOrder.getProperty("CreditTerm")));
			term.clear();
			term.sendKeys("NET 45");
			Thread.sleep(1000);// pro
			term.sendKeys(Keys.ENTER);
			Thread.sleep(2000);// pro

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(SalesOrder.getProperty("SalesOrderNo"))));
			driver.findElement(By.xpath(SalesOrder.getProperty("SalesOrderNo"))).sendKeys(documentid);

			// click on add button
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(SalesOrder.getProperty("addButton"))));
			driver.findElement(By.xpath(SalesOrder.getProperty("addButton"))).click();

			// search product id
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(SalesOrder.getProperty("productId"))));
			driver.findElement(By.xpath(SalesOrder.getProperty("productId"))).sendKeys(productid);
			Thread.sleep(4000);// pro

			// select check box
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(SalesOrder.getProperty("checkBox"))));
			driver.findElement(By.xpath(SalesOrder.getProperty("checkBox"))).click();
			Thread.sleep(2000);// pro

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(SalesOrder.getProperty("clickAdd"))));
			driver.findElement(By.xpath(SalesOrder.getProperty("clickAdd"))).click();
			Thread.sleep(3000);

			for (int i = 1; i <= driver
					.findElements(By
							.xpath("//*[@id='salesordereditproductdetailsgrid']/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td"))
					.size(); i++) {
				// System.out.println(driver.findElement(By.xpath("//div[@id='requisitioneditproductdetailsgrid']/div/div/div/div/div[1]/div[1]/div/table/thead/tr/td["+i+"]/div")).getText());
				if (driver.findElement(By
						.xpath("//*[@id='salesordereditproductdetailsgrid']/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText().equalsIgnoreCase("Quantity")) {
					driver.findElement(By
							.xpath("//*[@id='salesordereditproductdetailsgrid']/div/div/div/div[1]/div[2]/div[1]/div[1]/table/tbody/tr/td["
									+ i + "]/div"))
							.click();
					break;
				}
			}

			/*
			 * //Click on select NA of customer ID new
			 * WebDriverWait(driver,30).until(ExpectedConditions.
			 * elementToBeClickable(By.xpath(SalesOrder.getProperty(
			 * "selectNAcustomerId")))); WebElement quantity =
			 * driver.findElement(By.xpath(SalesOrder.getProperty(
			 * "selectNAcustomerId"))); quantity.click();
			 * Thread.sleep(3000);//pro
			 */

			Thread.sleep(2000);
			Utilities.sendText("1");
			Thread.sleep(2000);

			driver.findElement(By.xpath(SalesOrder.getProperty("Memo"))).click();
			// ------------------------------------------------------------------------------------------------------------------------------------------------

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(SalesOrder.getProperty("saveButton"))));
			driver.findElement(By.xpath(SalesOrder.getProperty("saveButton"))).click();
			Thread.sleep(2000);

			// Click on yes
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(SalesOrder.getProperty("yesButton"))));
			driver.findElement(By.xpath(SalesOrder.getProperty("yesButton"))).click();

			Thread.sleep(2000);
			Utilities.clickButton("OK", 300, driver);
			Thread.sleep(1000);

			// Email code
			String subjectLine = "Sales Order - testsmoke - " + documentid;
			EmailFunctionality.email_AfterSave(documentid, subjectLine, driver);
			Thread.sleep(1000);

			System.out.println("* * * * Sales Order Created for :" + documentid);

			// Click on OK
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(SalesOrder.getProperty("clickOk"))));
			driver.findElement(By.xpath(SalesOrder.getProperty("clickOk"))).click();

		} catch (Exception EX) {
			Utilities.handleError(EX, driver);
		}

	}
	// ********************************************verification**************************************************

	public static void verify_salesOrder(String documentid, String productid, String Customer, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {

			documentid = "So" + documentid;
			WebDriverWait wait = new WebDriverWait(driver, 100);
			Properties pro = Utilities.fetchProValue("OR_SalesOrder.properties");
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("SalesOrderReport"))));
			driver.findElement(By.xpath(pro.getProperty("SalesOrderReport"))).click();
			Thread.sleep(5000);// pro

			WebElement search = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("QuickSearch"))));

			search.sendKeys(documentid);
			search.sendKeys(Keys.TAB);
			Thread.sleep(2000);
			int e = driver
					.findElements(By
							.xpath(".//div[@id='gridmsg18SalesOrderListEntry']/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td/div"))
					.size();
			// System.out.println(e);
			Utilities.zoomOut();
			Thread.sleep(3000);
			for (int i = 1; i < e + 1; i++) {
				// System.out.println(driver.findElement(By.xpath(".//div[@id='gridmsg18SalesOrderListEntry']/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["+i+"]/div")).getText());
				String header = driver.findElement(By
						.xpath(".//div[@id='gridmsg18SalesOrderListEntry']/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();

				if (header.equals("Sales Order No")) {
					assertEquals(documentid,
							driver.findElement(By
									.xpath(".//div[@id='gridmsg18SalesOrderListEntry']/div/div/div/div[1]/div[2]/div/div[1]/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
				}

				else if (header.equals("Customer")) {
					assertEquals(Customer + "Name",
							driver.findElement(By
									.xpath(".//div[@id='gridmsg18SalesOrderListEntry']/div/div/div/div[1]/div[2]/div/div[1]/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
				}

				else if (header.equals("Customer Code")) {
					assertEquals(Customer,
							driver.findElement(By
									.xpath(".//div[@id='gridmsg18SalesOrderListEntry']/div/div/div/div[1]/div[2]/div/div[1]/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
				}

				else if (header.equals("Credit Term")) {
					assertEquals("NET 45",
							driver.findElement(By
									.xpath(".//div[@id='gridmsg18SalesOrderListEntry']/div/div/div/div[1]/div[2]/div/div[1]/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
				}
			}
			Utilities.zoomIn();
			Thread.sleep(500);
			Utilities.refresh();
			System.out.println("Sales Order Successfully verified");

		} catch (Exception EX) {
			Thread.sleep(3000);// pro
			Utilities.handleError(EX, driver);
		}
	}

	public static void EmailCopyEditDelete_salesOrder(String documentid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {

			documentid = "So" + documentid;
			WebDriverWait wait = new WebDriverWait(driver, 100);
			Properties pro = Utilities.fetchProValue("OR_SalesOrder.properties");
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("SalesOrderReport"))));
			driver.findElement(By.xpath(pro.getProperty("SalesOrderReport"))).click();
			Thread.sleep(5000);

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "uncheck", driver);
			WebElement search = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("QuickSearch"))));
			search.sendKeys(documentid);
			search.sendKeys(Keys.TAB);
			Thread.sleep(2000);

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);
			Thread.sleep(1000);

			// Report Email code
			String subjectLine = "Sales Order - testsmoke - " + documentid;
			EmailFunctionality.email_fromReports(documentid, subjectLine, driver);
			Thread.sleep(2000);

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "uncheck", driver);
			search.clear();
			search.sendKeys(documentid);
			Thread.sleep(1000);
			search.sendKeys(Keys.TAB);
			Thread.sleep(3000);

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);
			Thread.sleep(1000);

			WebElement copySalesOrderButton = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("copySalesOrderButton"))));
			copySalesOrderButton.click();
			Thread.sleep(2000);

			String CopySalesOrderID = "Copy" + documentid;

			new WebDriverWait(driver, 60)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("copySalesOrderId"))));
			driver.findElement(By.xpath(pro.getProperty("copySalesOrderId"))).sendKeys(CopySalesOrderID);

			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("saveCopiedSalesOrderId"))));

			driver.findElement(By.xpath(pro.getProperty("saveCopiedSalesOrderId"))).click();

			Thread.sleep(3000);

			Utilities.clickButton("Yes", 500, driver);
			Thread.sleep(1000);
			Utilities.clickButton("OK", 1500, driver);

			try {
				new WebDriverWait(driver, 30).until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath(pro.getProperty("CopySalesOrderClose"))));
				driver.findElement(By.xpath(pro.getProperty("CopySalesOrderClose"))).click();
				Thread.sleep(3000);
			} catch (Exception EX) {
				EX.printStackTrace();
			}

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "uncheck", driver);
			search.clear();
			search.sendKeys(CopySalesOrderID);
			search.sendKeys(Keys.TAB);
			Thread.sleep(3000);

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);
			Thread.sleep(1000);

			WebElement editSalesOrderButton = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("EditSalesOrderButton"))));
			editSalesOrderButton.click();
			Thread.sleep(3000);

			WebElement SalesOrderMemo = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("SalesOrderMemo"))));
			SalesOrderMemo.sendKeys("Update SO Memo");

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("SalesOrderEditSave"))));
			driver.findElement(By.xpath(pro.getProperty("SalesOrderEditSave"))).click();
			Thread.sleep(3000);

			Utilities.clickButton("Yes", 500, driver);
			Thread.sleep(1000);
			Utilities.clickButton("OK", 1500, driver);

			try {
				new WebDriverWait(driver, 30).until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath(pro.getProperty("EditSalesOrderClose"))));
				driver.findElement(By.xpath(pro.getProperty("EditSalesOrderClose"))).click();
				Thread.sleep(3000);
			} catch (Exception EX) {
				EX.printStackTrace();

			}

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "uncheck", driver);
			search.clear();
			search.sendKeys(CopySalesOrderID);
			search.sendKeys(Keys.TAB);
			Thread.sleep(2500);

			try {
				Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);
				Thread.sleep(3000);
				if (driver
						.findElement(By
								.xpath("//div[@id='SalesOrderListEntry']/div/div/div/div/div/div[2]/div/div[3]/div/table/following-sibling::div"))
						.getText().equalsIgnoreCase("Displaying 1 - 1 of 1")) {
					System.out.println("Sales Order " + CopySalesOrderID + " is edited Successfully");
				} else {
					System.out.println("Sales Order " + CopySalesOrderID + " is not edited Successfully");
				}

			} catch (Exception EX) {
				EX.printStackTrace();
			}

			new WebDriverWait(driver, 30).until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath(pro.getProperty("deleteSalesOrdernButton"))));
			driver.findElement(By.xpath(pro.getProperty("deleteSalesOrdernButton"))).click();

			Thread.sleep(2000);
			new WebDriverWait(driver, 30).until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath(pro.getProperty("deleteSalesOrderPermButton"))));
			driver.findElement(By.xpath(pro.getProperty("deleteSalesOrderPermButton"))).click();
			Thread.sleep(2000);

			Utilities.clickButton("Yes", 500, driver);
			Utilities.clickButton("OK", 0, driver);

			Thread.sleep(3000);

			try {
				Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "uncheck", driver);
				search.clear();
				search.sendKeys(CopySalesOrderID);
				search.sendKeys(Keys.TAB);
				Thread.sleep(3000);

				if (driver
						.findElement(By
								.xpath("//div[@id='SalesOrderListEntry']/div/div/div/div/div/div[2]/div/div[3]/div/table/following-sibling::div"))
						.getText().equalsIgnoreCase("No results to display")) {
					System.out.println("Sales Order " + CopySalesOrderID + "  is Deleted Successfully");
				} else {
					System.out.println("Sales Order " + CopySalesOrderID + "  is not Deleted Successfully");
				}
			} catch (Exception exp) {
				System.out.println("Sales Order " + CopySalesOrderID + " is Successfully deleted");
			}

			// li[@id='as__SalesOrderMainTabEntry']/a[1]
			Thread.sleep(2000);
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath("//li[@id='as__SalesOrderMainTabEntry']/a[1]")));
			driver.findElement(By.xpath("//li[@id='as__SalesOrderMainTabEntry']/a[1]")).click();

		} catch (Exception EX) {
			Thread.sleep(3000);
			Utilities.handleError(EX, driver);
		}
	}

	// * * * * * * * EXPORT Sales Order * * * * * *

	public static void Export_SalesOrder(WebDriver driver) throws InterruptedException, AWTException, IOException {
		String BtnName2 = "Export List";
		Properties pro = Utilities.fetchProValue("OR_SalesOrder.properties");
		String reportIcon = pro.getProperty("SalesOrderReport");
		String waitForQuickSearch = pro.getProperty("DocumentCheckBox");
		String closeReportPage = "//li[@id='as__SalesOrderMainTabEntry']/a[1]";
		String ModuleName = "Sales Order";

		ExportFunction.Export_TodayDate(BtnName2, reportIcon, waitForQuickSearch, closeReportPage, ModuleName, driver);

	}

}
