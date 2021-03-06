package krawler.erp.page;

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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import krawler.erp.utils.SikulliUtil;

public class VendorInvoice {

	public static void create_vendorInvoice(String documentid, String productid, String vendorid, WebDriver driver)
			throws InterruptedException, AWTException, IOException

	{
		try {
			documentid = "PurInvo" + documentid;
			Properties VendorInvoice = Utilities.fetchProValue("OR_VendorInvoice.properties");

			Thread.sleep(3000);

			// clicked on Purchase Invoice
			new WebDriverWait(driver, 60).until(ExpectedConditions
					.elementToBeClickable(By.xpath(VendorInvoice.getProperty("CreateVendorInvoiceIcon"))));
			driver.findElement(By.xpath(VendorInvoice.getProperty("CreateVendorInvoiceIcon"))).click();
			Thread.sleep(5000);// pro

			new WebDriverWait(driver, 100)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(VendorInvoice.getProperty("VendorId"))));
			// enter vendor name
			WebElement vendor = driver.findElement(By.xpath(VendorInvoice.getProperty("VendorId")));
			vendor.clear();
			vendor.sendKeys(vendorid);
			Thread.sleep(3000);// pro
			vendor.sendKeys(Keys.ENTER);

			// sequen format document no.
			WebElement squence = driver.findElement(By.xpath(VendorInvoice.getProperty("sequenceFormat")));
			Thread.sleep(500);// pro
			squence.clear();
			squence.sendKeys("NA");
			Thread.sleep(3000);// pro
			squence.sendKeys(Keys.ENTER);
			Thread.sleep(2000);// pro

			new WebDriverWait(driver, 30).until(ExpectedConditions
					.elementToBeClickable(By.xpath(VendorInvoice.getProperty("VendorInvoiceNumber"))));
			driver.findElement(By.xpath(VendorInvoice.getProperty("VendorInvoiceNumber"))).sendKeys(documentid);

			// enter vendor name
			WebElement term = driver.findElement(By.xpath(VendorInvoice.getProperty("DebitTerm")));
			term.clear();
			term.sendKeys("NET 45");
			Thread.sleep(1000);// pro
			term.sendKeys(Keys.ENTER);

			// click on add button
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(VendorInvoice.getProperty("addButton"))));
			driver.findElement(By.xpath(VendorInvoice.getProperty("addButton"))).click();

			// search product id
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(VendorInvoice.getProperty("searchProductId"))));
			driver.findElement(By.xpath(VendorInvoice.getProperty("searchProductId"))).sendKeys(productid);
			Thread.sleep(2000);// pro

			// select check box
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(VendorInvoice.getProperty("clickCheckBox"))));
			driver.findElement(By.xpath(VendorInvoice.getProperty("clickCheckBox"))).click();
			Thread.sleep(2000);// pro

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(VendorInvoice.getProperty("clickAdd"))));
			driver.findElement(By.xpath(VendorInvoice.getProperty("clickAdd"))).click();
			Thread.sleep(3000);

			for (int i = 1; i <= driver
					.findElements(By
							.xpath(".//div[@id='goodsreceipteditproductdetailsgrid']/div/div/div/div/div[1]/div[1]/div/table/thead/tr/td"))
					.size(); i++) {
				// System.out.println(driver.findElement(By.xpath("//div[@id='requisitioneditproductdetailsgrid']/div/div/div/div/div[1]/div[1]/div/table/thead/tr/td["+i+"]/div")).getText());
				if (driver.findElement(By
						.xpath(".//div[@id='goodsreceipteditproductdetailsgrid']/div/div/div/div/div[1]/div[1]/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText().equalsIgnoreCase("Quantity")) {
					driver.findElement(By
							.xpath(".//div[@id='goodsreceipteditproductdetailsgrid']/div/div/div/div/div[2]/div/div/table/tbody/tr/td["
									+ i + "]/div"))
							.click();
				}
			}

			/*
			 * //Click on select NA of customer ID new
			 * WebDriverWait(driver,30).until(ExpectedConditions.
			 * elementToBeClickable(By.xpath(VendorInvoice.getProperty(
			 * "clickOnQuantity")))); WebElement quantity =
			 * driver.findElement(By.xpath(VendorInvoice.getProperty(
			 * "clickOnQuantity"))); quantity.click();
			 */

			Thread.sleep(2000);
			Utilities.sendText("100");
			Thread.sleep(2000);

			driver.findElement(By.xpath(VendorInvoice.getProperty("memo"))).click();
			// ------------------------------------------------------------------------------------------------------------------------------------------------

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(VendorInvoice.getProperty("buttonSave"))));
			driver.findElement(By.xpath(VendorInvoice.getProperty("buttonSave"))).click();
			Thread.sleep(2000);

			// Click on yes
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(VendorInvoice.getProperty("clickYes"))));
			driver.findElement(By.xpath(VendorInvoice.getProperty("clickYes"))).click();

			// new
			// WebDriverWait(driver,30).until(ExpectedConditions.elementToBeClickable(By.xpath(VendorInvoice.getProperty("clickYes"))));
			// driver.findElement(By.xpath(VendorInvoice.getProperty("clickYes"))).click();

			// Click on OK
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(VendorInvoice.getProperty("clickOk"))));
			driver.findElement(By.xpath(VendorInvoice.getProperty("clickOk"))).click();
			Thread.sleep(1000);

			// Email code
			String subjectLine = "Vendor Invoice with Product - testsmoke - " + documentid;
			EmailFunctionality.email_AfterSave(documentid, subjectLine, driver);
			Thread.sleep(2000);

			// Click on OK
			new WebDriverWait(driver, 30).until(
					ExpectedConditions.elementToBeClickable(By.xpath(VendorInvoice.getProperty("closeVendorInvoice"))));
			driver.findElement(By.xpath(VendorInvoice.getProperty("closeVendorInvoice"))).click();

			System.out.println("Vendor Invoice [" + documentid + "] Successfully Created ");
		}

		catch (Exception EX) {
			Utilities.handleError(EX, driver);
		}
	}

	// *************************************************
	// Verification*************************************************
	public static void verify_VendorInvoice(String documentid, String productid, String vendorid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {

			// documentid="PurInvo"+documentid;
			WebDriverWait wait = new WebDriverWait(driver, 100);
			Properties pro = Utilities.fetchProValue("OR_CashPurchase.properties");
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("SalesInvoiceReport"))));
			driver.findElement(By.xpath(pro.getProperty("SalesInvoiceReport"))).click();
			Thread.sleep(8000);// pro

			WebElement search = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("QuickSearch"))));

			search.sendKeys(documentid);
			search.sendKeys(Keys.TAB);
			Thread.sleep(2000);
			int e = driver
					.findElements(By
							.xpath(".//div[@id='gridmsg21GRListEntry']/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td/div"))
					.size();
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
			String tab_ID = "//li[@id='as__GRListMainTabEntry']/a[1]";
			Utilities.HoverandClick(tab_ID, driver);

			System.out.println("Vendor Invoice [" + documentid + "] is Verified !!!! ");
		} catch (Exception EX) {
			Thread.sleep(3000);// pro
			Utilities.handleError(EX, driver);
		}

	}

	// * * * * * * * EXPORT Purchase Invoice * * * * * *

	public static void Export_PurchaseInvoice(WebDriver driver) throws InterruptedException, AWTException, IOException {
		String buttonName = "Export List";
		Properties pro2 = Utilities.fetchProValue("OR_VendorInvoice.properties");
		String reportIcon = pro2.getProperty("SalesInvoiceReport");
		String waitForQuickSearch = pro2.getProperty("DocumentCheckBox");
		String closeReportPage = "//li[@id='as__GRListMainTabEntry']/a[1]";
		String ModuleName = "Purchase Invoice";

		try {

			WebDriverWait wait = new WebDriverWait(driver, 60);
			Properties pro = Utilities.fetchProValue("OR_ExportFunction.properties");

			String ExportButtonName = null;

			if (buttonName.equalsIgnoreCase("Export List")) {
				ExportButtonName = "Export List";
			} else if (buttonName.equalsIgnoreCase("Export")) {
				ExportButtonName = "Export ";
			}

			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(reportIcon)));
			element.click();
			Thread.sleep(1000);

			Utilities.clickCheckBox(waitForQuickSearch, "uncheck", driver);

			// select from date
			Utilities.HoverandClick(pro.getProperty("fromIcon"), driver);
			Utilities.HoverandClick(pro.getProperty("selectToday"), driver);
			Thread.sleep(1000);

			// select To date
			Utilities.HoverandClick(pro.getProperty("toIcon"), driver);
			Utilities.HoverandClick(pro.getProperty("selectToday"), driver);
			Thread.sleep(1000);

			Utilities.clickButton("Fetch", 500, driver);
			Thread.sleep(1000);
			try {
				Utilities.clickCheckBox(waitForQuickSearch, "check", driver);

				// ****** CSV FILE *****
				Utilities.clickButton(ExportButtonName, 500, driver);
				Utilities.HoverandClick(pro.getProperty("ExportToCSV"), driver);
				element = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("WindowExportBtn"))));
				element.click();
				Thread.sleep(1000);
				SikulliUtil.sikulli_waitClick(driver, "CSVtype");
				SikulliUtil.sikulli_waitClick(driver, "ClsX");
				System.out.println("* * * * * * EXPORT for [.CSV] completed successfully * * * * * * *");
				Thread.sleep(1500);

				// PDF
				Utilities.clickButton(ExportButtonName, 500, driver);
				String parentWindow = driver.getWindowHandle();
				Utilities.justHover("//*[contains(@style,'visible')]//*[text()='Export to PDF File']", driver);
				Utilities.HoverandClick("//*[contains(@style,'visible')]//*[text()='Export to Invoice Detail PDF']",
						driver);
				Thread.sleep(1000);
				SikulliUtil.sikulli_waitClick(driver, "PDFtype");
				Thread.sleep(1500);
				Set s = driver.getWindowHandles(); // this method will gives you
													// the handles of all opened
													// windows
				Iterator ite = s.iterator();
				while (ite.hasNext()) {
					String childWindow = ite.next().toString();
					if (!childWindow.contains(parentWindow)) {
						driver.switchTo().window(childWindow);
						Thread.sleep(1000);
						System.out.println("* * * * * * EXPORT for [PDF] completed successfully * * * * * * *");
						driver.close();
						driver.switchTo().window(parentWindow);
						Thread.sleep(1000);
					}
				}

				// Excel in Details
				Utilities.clickButton(ExportButtonName, 500, driver);
				Actions action = new Actions(driver);
				WebElement hoverElement = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("ExportToExcel"))));
				action.moveToElement(hoverElement).build().perform();
				Thread.sleep(1000);
				Utilities.HoverandClick(pro.getProperty("ExcelInDetail"), driver);
				element = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("WindowExportBtn"))));
				element.click();
				Thread.sleep(1000);
				SikulliUtil.sikulli_waitClick(driver, "XLStype");
				SikulliUtil.sikulli_waitClick(driver, "ClsX");
				System.out.println("* * * * * * EXPORT for [.Excel in Detail ] completed successfully * * * * * * *");
				Thread.sleep(1500);

				// Excel Summary
				Utilities.clickButton(ExportButtonName, 500, driver);
				hoverElement = new WebDriverWait(driver, 30)
						.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("ExportToExcel"))));
				action.moveToElement(hoverElement).build().perform();
				Thread.sleep(1000);
				Utilities.HoverandClick(pro.getProperty("ExcelInSummary"), driver);
				element = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("WindowExportBtn"))));
				element.click();
				Thread.sleep(1000);
				SikulliUtil.sikulli_waitClick(driver, "XLStype");
				SikulliUtil.sikulli_waitClick(driver, "ClsX");
				System.out.println("* * * * * * EXPORT for [.Excel Summary ] completed successfully * * * * * * *");
				Thread.sleep(1500);

				// Export to HTML
				Utilities.clickButton(ExportButtonName, 500, driver);
				parentWindow = driver.getWindowHandle();
				Utilities.HoverandClick(pro.getProperty("ExportToHtml"), driver);
				Utilities.clickButton("Print", 1000, driver);
				s = driver.getWindowHandles(); // this method will gives you the
												// handles of all opened windows
				ite = s.iterator();
				while (ite.hasNext()) {
					String childWindow = ite.next().toString();
					if (!childWindow.contains(parentWindow)) {
						driver.switchTo().window(childWindow);
						driver.manage().window().maximize();
						Thread.sleep(1000);
						System.out.println("* * * * * * EXPORT with [Print] completed successfully * * * * * * *");
						driver.close();
						driver.switchTo().window(parentWindow);
						Thread.sleep(1500);
					}
				}
				// Exported

				Utilities.HoverandClick(closeReportPage, driver);
				System.out.println(
						"* * * * * * EXPORT for module [" + ModuleName + "] completed successfully * * * * * * *");
			}

			catch (Exception noData) {
				System.out.println("* * * * * * Today's data are Not Present ! ! ! * * * * * * ");
				Utilities.handleError(noData, driver);
			}
		}

		catch (Exception EX) {
			System.out.println("* * * * * * !!!!!!! EXPORT for [" + ModuleName + "] FAILED !!!!!! * * * * * * *");
			Utilities.handleError(EX, driver);
		}

	}

}
