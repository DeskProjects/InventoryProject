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

public class PurchaseOrder {

	public static void create_PurchaseOrder(String documentid, String productid, String vendorid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {

		try {
			documentid = "PO" + documentid;
			Properties PurchaseOrder = Utilities.fetchProValue("OR_PurchaseOrder.properties");

			Thread.sleep(3000);
			// WebDriverWait wait = new WebDriverWait(driver, 30);

			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(PurchaseOrder.getProperty("PurchaseOrderIcon"))));
			driver.findElement(By.xpath(PurchaseOrder.getProperty("PurchaseOrderIcon"))).click();
			System.out.println("clicked on Purchase Order");
			Thread.sleep(5000);// pro

			// new
			// WebDriverWait(driver,59).until(ExpectedConditions.visibilityOfAllElementsLocatedBy((By.xpath("//button[text()='Save']"))));

			// enter vendor name
			WebElement vendor = driver.findElement(By.xpath(PurchaseOrder.getProperty("VendorId")));
			Thread.sleep(500);// pro
			vendor.clear();
			Thread.sleep(500);// pro
			vendor.sendKeys(vendorid);
			Thread.sleep(3000);// pro
			vendor.sendKeys(Keys.ENTER);

			// enter vendor name
			WebElement na = driver.findElement(By.xpath(PurchaseOrder.getProperty("sequencFormat")));
			Thread.sleep(500);
			na.clear();
			Thread.sleep(500);
			na.sendKeys("NA");
			Thread.sleep(1000);// pro
			na.sendKeys(Keys.ENTER);
			Thread.sleep(2000);// pro

			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(PurchaseOrder.getProperty("PurchaseOrderNo"))));
			driver.findElement(By.xpath(PurchaseOrder.getProperty("PurchaseOrderNo"))).sendKeys(documentid);

			// enter vendor name
			WebElement term = driver.findElement(By.xpath(PurchaseOrder.getProperty("DebitTerm")));
			term.clear();
			term.sendKeys("NET 45");
			Thread.sleep(1000);// pro
			term.sendKeys(Keys.ENTER);

			// click on add button
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(PurchaseOrder.getProperty("AddButton"))));
			driver.findElement(By.xpath(PurchaseOrder.getProperty("AddButton"))).click();

			// search product id
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(PurchaseOrder.getProperty("searchProductId"))));
			driver.findElement(By.xpath(PurchaseOrder.getProperty("searchProductId"))).sendKeys(productid);
			Thread.sleep(2000);// pro

			// select check box
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(PurchaseOrder.getProperty("clickCheckBox"))));
			driver.findElement(By.xpath(PurchaseOrder.getProperty("clickCheckBox"))).click();
			Thread.sleep(2000);// pro

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(PurchaseOrder.getProperty("clickAdd"))));
			driver.findElement(By.xpath(PurchaseOrder.getProperty("clickAdd"))).click();
			Thread.sleep(3000);

			for (int i = 1; i <= driver
					.findElements(By
							.xpath("//div[@id='purchaseorderinvoicegrid']/div[2]/div/div/div[1]/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td/div"))
					.size(); i++) {
				if (driver.findElement(By
						.xpath("//div[@id='purchaseorderinvoicegrid']/div[2]/div/div/div[1]/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText().equalsIgnoreCase("Quantity")) {
					driver.findElement(By
							.xpath("//div[@id='purchaseorderinvoicegrid']/div[2]/div/div/div[1]/div/div/div[1]/div[2]/div/div/table/tbody/tr/td["
									+ i + "]/div"))
							.click();
					Thread.sleep(2000);
					Utilities.sendText("10");
					break;
				}
			}

			driver.findElement(By.xpath(PurchaseOrder.getProperty("memo"))).click();
			// ------------------------------------------------------------------------------------------------------------------------------------------------

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(PurchaseOrder.getProperty("buttonSave"))));
			driver.findElement(By.xpath(PurchaseOrder.getProperty("buttonSave"))).click();
			Thread.sleep(2000);

			// Click on yes
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(PurchaseOrder.getProperty("buttonYes"))));
			driver.findElement(By.xpath(PurchaseOrder.getProperty("buttonYes"))).click();

			// Click on OK
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(PurchaseOrder.getProperty("buttonOk"))));
			driver.findElement(By.xpath(PurchaseOrder.getProperty("buttonOk"))).click();
			Thread.sleep(1000);

			// Email code
			String subjectLine = "Purchase Order with Product - testsmoke - " + documentid;
			EmailFunctionality.email_AfterSave(documentid, subjectLine, driver);

			System.out.println("Purchase Order with Product - testsmoke - " + documentid + " Created !!!!!!");

			// Click on OK
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(PurchaseOrder.getProperty("ClosePurchaseOrder"))));
			Thread.sleep(1200);
			driver.findElement(By.xpath(PurchaseOrder.getProperty("ClosePurchaseOrder"))).click();
		} catch (Exception EX) {
			System.out.println("Purchase Order with Product - testsmoke - " + documentid + " NOT Created !!!!!!");
			Utilities.handleError(EX, driver);

		}

	}

	// **************************************** verifuy
	public static void verify_PurchaseOrder(String documentid, String productid, String Vendor, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {

			// documentid="PO"+documentid;
			WebDriverWait wait = new WebDriverWait(driver, 100);
			Properties pro = Utilities.fetchProValue("OR_PurchaseOrder.properties");
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("PurchaseOrderReport"))));
			driver.findElement(By.xpath(pro.getProperty("PurchaseOrderReport"))).click();
			Thread.sleep(5000);// pro

			WebElement search = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("QuickSearch"))));

			search.sendKeys(documentid);
			search.sendKeys(Keys.TAB);
			Thread.sleep(2000);
			int e = driver
					.findElements(By
							.xpath(".//div[@id='gridmsg17PurchaseOrderListEntry']/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td/div"))
					.size();
			// System.out.println(e);
			Utilities.zoomOut();
			Thread.sleep(3000);
			for (int i = 1; i < e + 1; i++) {
				// System.out.println(driver.findElement(By.xpath(".//div[@id='gridmsg17PurchaseOrderListEntry']/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["+i+"]/div")).getText());
				String header = driver.findElement(By
						.xpath(".//div[@id='gridmsg17PurchaseOrderListEntry']/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText();

				if (header.equals("Purchase Order No")) {
					assertEquals(documentid,
							driver.findElement(By
									.xpath(".//div[@id='gridmsg17PurchaseOrderListEntry']/div/div/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
				}

				else if (header.equals("Vendor")) {
					assertEquals(Vendor + "Name",
							driver.findElement(By
									.xpath(".//div[@id='gridmsg17PurchaseOrderListEntry']/div/div/div/div[1]/div[2]/div/div/table/tbody/tr[1]/td["
											+ i + "]/div"))
									.getText());
				}

			}
			Utilities.zoomIn();
			Thread.sleep(2000);
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("ClosePurchaseOrderReportTab"))));
			driver.findElement(By.xpath(pro.getProperty("ClosePurchaseOrderReportTab"))).click();

			System.out.println("Purchase Order [" + documentid + "] Successfully verified");

		} catch (Exception EX) {
			System.out.println("Purchase Order [" + documentid + "] not Verified");
			Thread.sleep(3000);// pro
			Utilities.handleError(EX, driver);
		}

	}

	// * * * * * * * EXPORT Purchase Order * * * * * *

	public static void Export_PurchaseOrder(WebDriver driver) throws InterruptedException, AWTException, IOException {
		String BtnName2 = "Export List";
		Properties pro = Utilities.fetchProValue("OR_PurchaseOrder.properties");
		String reportIcon = pro.getProperty("PurchaseOrderReport");
		String waitForQuickSearch = pro.getProperty("DocumentCheckBox");
		String closeReportPage = "//li[@id='as__PurchaseOrderListEntry']/a[1]";
		String ModuleName = "Purchase Order";

		ExportFunction.Export_TodayDate(BtnName2, reportIcon, waitForQuickSearch, closeReportPage, ModuleName, driver);

	}

}// class