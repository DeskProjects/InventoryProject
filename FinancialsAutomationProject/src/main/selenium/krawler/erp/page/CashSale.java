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
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import krawler.erp.utils.SikulliUtil;

public class CashSale {

	public static void create_cashSale(String documentid, String productid, String CustomerId, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			documentid = "CS" + documentid;
			Properties CashSale = Utilities.fetchProValue("OR_CashSale.properties");
			Thread.sleep(3000);
			// WebDriverWait wait = new WebDriverWait(driver, 30);

			// clicked on Purchase Invoice
			new WebDriverWait(driver, 60).until(
					ExpectedConditions.elementToBeClickable(By.xpath(CashSale.getProperty("CreateCashSaleIcon"))));
			driver.findElement(By.xpath(CashSale.getProperty("CreateCashSaleIcon"))).click();
			Thread.sleep(3000);// pro

			// enter vendor name
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(CashSale.getProperty("CustomerId"))));
			WebElement customer = driver.findElement(By.xpath(CashSale.getProperty("CustomerId")));
			customer.clear();
			customer.sendKeys(CustomerId);
			Thread.sleep(3000);// pro
			customer.sendKeys(Keys.ENTER);

			// sequen format document no.
			WebElement squence = driver.findElement(By.xpath(CashSale.getProperty("sequenceFormat")));
			Thread.sleep(500);
			squence.clear();
			squence.sendKeys("NA");
			Thread.sleep(1000);// pro
			squence.sendKeys(Keys.ENTER);
			Thread.sleep(2000);// pro

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(CashSale.getProperty("CashSaleNo"))));
			driver.findElement(By.xpath(CashSale.getProperty("CashSaleNo"))).sendKeys(documentid);

			// click on add button
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(CashSale.getProperty("addButton"))));
			driver.findElement(By.xpath(CashSale.getProperty("addButton"))).click();

			// search product id
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(CashSale.getProperty("productId"))));
			driver.findElement(By.xpath(CashSale.getProperty("productId"))).sendKeys(productid);
			Thread.sleep(2000);// pro

			// select check box
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(CashSale.getProperty("clickOncheckBox"))));
			driver.findElement(By.xpath(CashSale.getProperty("clickOncheckBox"))).click();
			Thread.sleep(2000);// pro

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(CashSale.getProperty("clickAdd"))));
			driver.findElement(By.xpath(CashSale.getProperty("clickAdd"))).click();
			Thread.sleep(3000);

			for (int i = 1; i <= driver
					.findElements(By
							.xpath("//*[@id='salesreceipteditproductdetailsgrid']/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td"))
					.size(); i++) {
				// System.out.println(driver.findElement(By.xpath("//*[@id='salesreceipteditproductdetailsgrid']/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td{"+i+"]/div")).getText());
				if (driver.findElement(By
						.xpath("//*[@id='salesreceipteditproductdetailsgrid']/div/div/div/div[1]/div[1]/div/div/table/thead/tr/td["
								+ i + "]/div"))
						.getText().equalsIgnoreCase("Quantity")) {
					driver.findElement(By
							.xpath("//*[@id='salesreceipteditproductdetailsgrid']/div/div/div/div[1]/div[2]/div[1]/div[1]/table/tbody/tr/td["
									+ i + "]/div"))
							.click();
					break;
				}
			}
			Thread.sleep(2000);
			Utilities.sendText("1");
			Thread.sleep(2000);

			driver.findElement(By.xpath(CashSale.getProperty("Memo"))).click();

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(CashSale.getProperty("buttonSave"))));
			driver.findElement(By.xpath(CashSale.getProperty("buttonSave"))).click();
			Thread.sleep(2000);

			// Click on yes
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(CashSale.getProperty("clickYes"))));
			driver.findElement(By.xpath(CashSale.getProperty("clickYes"))).click();
			Thread.sleep(1000);

			Utilities.clickButton("OK", 1000, driver);
			Thread.sleep(1500);

			// Email code
			String subjectLine = "Cash Sale - testsmoke - " + documentid;
			EmailFunctionality.email_AfterSave(documentid, subjectLine, driver);
			Thread.sleep(1000);

			System.out.println("* * * * Cash Sales Created for :" + documentid);

			// Click on OK
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(CashSale.getProperty("CloseCashSale"))));
			driver.findElement(By.xpath(CashSale.getProperty("CloseCashSale"))).click();
		} catch (Exception EX) {
			Utilities.handleError(EX, driver);
		}

	}
	// **********************************************Verification****************************************************

	public static void verify_cashSale(String documentId, String productid, String customerID, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {
			Thread.sleep(2000);
			documentId = "CS" + documentId;
			WebDriverWait wait = new WebDriverWait(driver, 100);
			Properties pro = Utilities.fetchProValue("OR_CashSale.properties");
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("SalesInvoiceReport"))));
			driver.findElement(By.xpath(pro.getProperty("SalesInvoiceReport"))).click();
			Thread.sleep(5000);// pro

			WebElement search = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("QuickSearch"))));
			search.sendKeys(documentId);
			Thread.sleep(2000);
			search.sendKeys(Keys.TAB);

			Thread.sleep(2000);
			int e = driver
					.findElements(By
							.xpath(".//*[@id='gridmsg16InvoiceListEntry']/div/div/div/div[1]/div[1]/div[1]/div/table/thead/tr/td/div"))
					.size();
			// System.out.println(e);
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
			}

			Thread.sleep(500);
			String xpathOfelement = "//li[@id='as__InvoiceListMainTabEntry']/a[1]";
			Utilities.HoverandClick(xpathOfelement, driver);

			System.out.println("Cash Sales Successfully verified");
		} catch (Exception EX) {
			Thread.sleep(3000);// pro
			Utilities.handleError(EX, driver);
		}

	}

	public static void EmailCopyEditDelete_cashSale(String documentid, WebDriver driver)
			throws InterruptedException, AWTException, IOException {
		try {

			documentid = "CS" + documentid;
			WebDriverWait wait = new WebDriverWait(driver, 100);
			Properties pro = Utilities.fetchProValue("OR_CashSale.properties");
			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("SalesInvoiceReport"))));
			driver.findElement(By.xpath(pro.getProperty("SalesInvoiceReport"))).click();
			Thread.sleep(3000);

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "uncheck", driver);
			WebElement search = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("QuickSearch"))));
			search.sendKeys(documentid);
			search.sendKeys(Keys.TAB);
			Thread.sleep(2000);

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);
			Thread.sleep(1000);

			// Report Email code
			String subjectLine = "Cash Sale - testsmoke - " + documentid;
			EmailFunctionality.email_fromReports(documentid, subjectLine, driver);
			Thread.sleep(2000);

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "uncheck", driver);
			search.clear();
			search.sendKeys(documentid);
			search.sendKeys(Keys.TAB);
			Thread.sleep(2000);

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);
			Thread.sleep(1000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("copyCashSaleButton"))));
			driver.findElement(By.xpath(pro.getProperty("copyCashSaleButton"))).click();

			String CopyCashSaleID = "Copy" + documentid;

			WebElement enterCopyId = new WebDriverWait(driver, 60)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("copyCashSaleId"))));
			Thread.sleep(1000);
			enterCopyId.sendKeys(CopyCashSaleID);
			Thread.sleep(2000);

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("saveCopiedCashSaleId"))));
			driver.findElement(By.xpath(pro.getProperty("saveCopiedCashSaleId"))).click();
			Thread.sleep(3000);

			Utilities.clickButton("Yes", 500, driver);
			Thread.sleep(1000);
			Utilities.clickButton("OK", 1500, driver);
			Thread.sleep(1000);

			try {
				new WebDriverWait(driver, 30).until(
						ExpectedConditions.visibilityOfElementLocated(By.xpath(pro.getProperty("CopyCashSaleClose"))));
				driver.findElement(By.xpath(pro.getProperty("CopyCashSaleClose"))).click();
				Thread.sleep(3000);
			} catch (Exception EX) {
				EX.printStackTrace();

			}

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "uncheck", driver);
			Thread.sleep(500);
			search.clear();
			search.sendKeys(CopyCashSaleID);
			search.sendKeys(Keys.TAB);
			Thread.sleep(2000);

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);
			Thread.sleep(1000);

			WebElement editCashSaleButton = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("EditCashSaleButton"))));
			editCashSaleButton.click();
			Thread.sleep(3000);

			WebElement CashSale = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("CashSaleMemo"))));
			CashSale.sendKeys("Update Cash Sales Memo");

			new WebDriverWait(driver, 30)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(pro.getProperty("CashSaleEditSave"))));
			driver.findElement(By.xpath(pro.getProperty("CashSaleEditSave"))).click();
			Thread.sleep(3000);

			Utilities.clickButton("Yes", 500, driver);
			Thread.sleep(1000);
			Utilities.clickButton("OK", 1500, driver);
			Thread.sleep(1000);

			try {
				new WebDriverWait(driver, 30).until(
						ExpectedConditions.visibilityOfElementLocated(By.xpath(pro.getProperty("EditCashSaleClose"))));
				driver.findElement(By.xpath(pro.getProperty("EditCashSaleClose"))).click();
				Thread.sleep(5000);
			} catch (Exception EX) {
				EX.printStackTrace();
			}

			Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "uncheck", driver);
			Thread.sleep(500);
			search.clear();
			search.sendKeys(CopyCashSaleID);
			search.sendKeys(Keys.TAB);
			Thread.sleep(2000);

			try {
				Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "check", driver);
				Thread.sleep(3000);
				if (driver
						.findElement(By
								.xpath("//div[@id='InvoiceListEntry']/div/div/div/div/div/div[2]/div/div[3]/div/table/following-sibling::div"))
						.getText().equalsIgnoreCase("Displaying 1 - 1 of 1")) {
					System.out.println("Cash Sale " + CopyCashSaleID + " is edited Successfully");
				} else {
					System.out.println("Cash Sale " + CopyCashSaleID + " is not edited Successfully");
				}

			} catch (Exception EX) {
			}

			new WebDriverWait(driver, 30).until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath(pro.getProperty("deleteCashSaleButton"))));
			driver.findElement(By.xpath(pro.getProperty("deleteCashSaleButton"))).click();

			Thread.sleep(2000);
			new WebDriverWait(driver, 30).until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath(pro.getProperty("deleteCashSalePermButton"))));
			driver.findElement(By.xpath(pro.getProperty("deleteCashSalePermButton"))).click();
			Thread.sleep(3000);

			Utilities.clickButton("Yes", 500, driver);
			Utilities.clickButton("OK", 0, driver);
			Thread.sleep(2000);

			try {
				Utilities.clickCheckBox(pro.getProperty("DocumentCheckBox"), "uncheck", driver);
				Thread.sleep(500);
				search.clear();
				search.sendKeys(CopyCashSaleID);
				search.sendKeys(Keys.TAB);
				Thread.sleep(4000);

				if (driver
						.findElement(By
								.xpath("//div[@id='InvoiceListEntry']/div/div/div/div/div/div[2]/div/div[3]/div/table/following-sibling::div"))
						.getText().equalsIgnoreCase("No results to display")) {
					System.out.println("Cash Sale " + CopyCashSaleID + "  is Deleted Successfully");
				} else {
					System.out.println("Cash Sale " + CopyCashSaleID + "  is not Deleted Successfully");
				}
			} catch (Exception exp) {
				System.out.println("Cash Sale " + CopyCashSaleID + " is Successfully deleted");
			}

			String xpathOfelement = "//li[@id='as__InvoiceListMainTabEntry']/a[1]";
			Utilities.HoverandClick(xpathOfelement, driver);

		} catch (Exception EX) {
			Thread.sleep(3000);
			Utilities.handleError(EX, driver);
		}
	}

	// * * * * * * * EXPORT Sales Invoice & Cash Sale * * * * * *

	public static void Export_TodayDate(WebDriver driver) throws InterruptedException, AWTException, IOException {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		Properties pro = Utilities.fetchProValue("OR_ExportFunction.properties");

		String ExportButtonName = "Export List";
		String reportIcon = ".//map[@name='AlienAreas3']/area[10]";
		String waitForQuickSearch = ".//div[@id='gridmsg16InvoiceListEntry']/div/div/div/div/div[2]/div/div[1]/table/tbody/tr/td[1]/div";
		String closeReportPage = "//li[@id='as__InvoiceListMainTabEntry']/a[1]";
		String ModuleName = "Sales Invoice & Cash Sale";

		try {

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

				// CSV File
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

				// Summary Detail PDF
				String parentWindow = driver.getWindowHandle();
				Utilities.clickButton(ExportButtonName, 500, driver);
				Utilities.justHover(pro.getProperty("ExportToPDFtagA"), driver);
				Utilities.HoverandClick(pro.getProperty("DetailPDF"), driver);
				Thread.sleep(1000);
				SikulliUtil.sikulli_waitClick(driver, "PDFtype");
				Thread.sleep(1000);

				Set s = driver.getWindowHandles(); // this method will gives you
													// the handles of all opened
													// windows
				Iterator ite = s.iterator();

				while (ite.hasNext()) {
					String childWindow = ite.next().toString();
					if (!childWindow.contains(parentWindow)) {
						driver.switchTo().window(childWindow);
						Thread.sleep(1000);
						System.out.println("* * * * * * EXPORT for [DetailPDF] completed successfully * * * * * * *");
						driver.close();
						driver.switchTo().window(parentWindow);
						Thread.sleep(1000);
						Utilities.HoverandClick(waitForQuickSearch, driver);
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

	// get button path for Sikulli
	public static String button_path(String BtnName) {

		String file_path = System.getProperty("user.dir") + "\\sikulliSnaps\\ExportSnap\\Common\\" + BtnName + ".PNG";
		return file_path;

	}

}
